//These windows headers are required for sockets and threading
//If you're doing stuff on apple devices, you'll have to replace those bits
#include <winsock2.h>
#include <WS2tcpip.h>
#include <Windows.h>
#include <direct.h>

#include <stdio.h>
#include <fstream>
#include <string>
#include <vector>

//Details for this header can be found at https://github.com/nodejs/http-parser
#pragma warning(disable : 4244)
#include "http_parser.h"
#pragma warning(default : 4244)

#if 1
#define Assert(Expression) if(!Expression) {*((int *)nullptr) = 0;}
#else
#define Assert(Expression)
#endif

//So we connect at port 80: "localhost:80"
#define DEFAULT_PORT "80"

//This will be passed to the http_parser, we can add more to this if needed
struct client_data {
	std::string *Url;
};

//Generate the HttpHeader, given the message
//Right now it's pretty hardcoded, we can change that later
std::string GenerateHttpHeader(std::string Content) {
	std::string Result;
	std::string FirstPart = "HTTP/1.1 200 OK\r\n"
		"Date: Mon, 27 Jul 2009 12:28:53 GMT\r\n"
		"Server: Apache/2.2.14 (Win32)\r\n"
		"Last-Modified: Wed, 22 Jul 2009 19:15:56 GMT\r\n"
		"Content-Length: ";
	std::string ContentLength = std::to_string(Content.size());
	std::string SecondPart = "\r\n"
		"Content-Type: text\\html\r\n"
		"Connection: Keep-Alive\r\n"
		"\r\n";
	Result = FirstPart + ContentLength + SecondPart;
	return Result;
}

//Given a request, strip out the queries and return them in a list of std::string pairs
//In a url, queries look like ?Hello=World&Funtimes=true after the url proper
std::vector<std::pair<std::string, std::string>> StripQueries(std::string *Request) {
	std::vector<std::pair<std::string, std::string>> Result;
	int QueryStart = Request->find_first_of('?', 0);
	if (QueryStart != std::string::npos) {
		//NOTE (Jack): OOB?
		std::string Queries = Request->substr(QueryStart + 1);
		*Request = Request->substr(0, QueryStart);

		int QuerySplit = 0;
		std::vector<std::string> QueryPairVector;
		while ((QuerySplit = Queries.find_first_of('&', 0)) != std::string::npos) {
			QueryPairVector.push_back(Queries.substr(0, QuerySplit));
			Queries = Queries.substr(QuerySplit + 1);
		}
		QueryPairVector.push_back(Queries);

		for (int i = 0; i < QueryPairVector.size(); ++i) {
			std::pair<std::string, std::string> QueryPair;
			std::string Query;
			std::string QueryValue;
			int SplitPos = QueryPairVector[i].find_first_of('=', 0);
			Assert(SplitPos == std::string::npos); //If we fail here, malformed url, what do we do?
			Query = QueryPairVector[i].substr(0, SplitPos);
			QueryValue = QueryPairVector[i].substr(SplitPos + 1);
			QueryPair.first = Query;
			QueryPair.second = QueryValue;
			Result.push_back(QueryPair);
		}
	}
	return Result;
}

//Given a filename, return the contents of that file as a string
//Although it works, this function should almost certainly be revised
std::string LoadFileToMemory(std::string Filename) {
	std::string Result;
	std::ifstream input(Filename.c_str(), std::ifstream::ate | std::ifstream::binary);
	if (!input) {
		//If we request a file that doesn't exist, this line will print that out
		printf("%s requested, not found\n", Filename.c_str());
		return Result;
	}
	int Size = input.tellg();
	input.seekg(0, input.beg);
	//Result starts with a size of 1
	//is reserving necessary? I don't know how to C++ strings
	Result.reserve(Size - Result.capacity());
	while (true) {
		int ch = input.get();
		if (input.eof()) {
			break;
		}
		Result += ch;
	}
	return Result;
}

//All of these callbacks are required (even if they are empty) to parse the http request
//In the future, we can fill these functions out to get more info from the http request

//Stores the requested url in client_data
int UrlCallback(http_parser *Parser, const char *at, size_t length) {
	client_data *Data = (client_data *)Parser->data;
	//FIXME (Jack): Probably buggy
	//This gets rid of the leading backslash
	Data->Url->assign(at + 1, length - 1);
	return 0;
}

int BodyCallback(http_parser *Parser, const char *at, size_t length) {
	return 0;
}

int ChunkCompleteCallback(http_parser *Parser) {
	return 0;
}

int ChunkHeaderCallback(http_parser *Parser) {
	return 0;
}

int HeaderFieldCallback(http_parser *Parser, const char *at, size_t length) {
	return 0;
}

int HeaderValueCallback(http_parser *Parser, const char *at, size_t length) {
	return 0;
}

int HeadersCompleteCallback(http_parser *Parser) {
	return 0;
}

int MessageBeginCallback(http_parser *Parser) {
	return 0;
}

int MessageCompleteCallback(http_parser *Parser) {
	return 0;
}

int StatusCallback(http_parser *Parser, const char *at, size_t length) {
	return 0;
}

//This is the function that is called as a new thread upon receiving a connection
//Gotta see how the threading works, I think this leaks with the http_parser malloc and the client_data malloc
DWORD WINAPI ClientHandler(void *sock_) {
	SOCKET sock = (SOCKET)sock_;
	char Buffer[2048];
	size_t NumParsed = 0;
	client_data *ClientData = (client_data *)malloc(sizeof(client_data));
	ClientData->Url = new std::string();
	http_parser *Parser = (http_parser *)malloc(sizeof(http_parser));
	http_parser_init(Parser, HTTP_REQUEST);
	Parser->data = ClientData;
	http_parser_settings Settings;
	Settings.on_body = BodyCallback;
	Settings.on_chunk_complete = ChunkCompleteCallback;
	Settings.on_chunk_header = ChunkHeaderCallback;
	Settings.on_header_field = HeaderFieldCallback;
	Settings.on_header_value = HeaderValueCallback;
	Settings.on_headers_complete = HeadersCompleteCallback;
	Settings.on_message_begin = MessageBeginCallback;
	Settings.on_message_complete = MessageCompleteCallback;
	Settings.on_status = StatusCallback;
	Settings.on_url = UrlCallback;
	//Are 2048 bytes big enough for all http requests? probably?
	int BytesReceived = recv(sock, Buffer, 2048, 0);
	char *Temp = (char *)malloc(BytesReceived + 1);
	memcpy(Temp, Buffer, BytesReceived);
	Temp[BytesReceived] = '\0';
	//This line will print out the request to the console.
	printf("%s\n", Temp);
	//Fill client_data with details from the http request
	NumParsed = http_parser_execute(Parser, &Settings, Buffer, BytesReceived);

	//This is where the server logic really happens
	//If the user only requests the initial page (localhost:80) then we give them a page called index.htm
	if (ClientData->Url->size() < 0) {
		*ClientData->Url = std::string("index.htm");
	}
	//Get queries in a manageable form, as well as remove them from the request url
	std::vector<std::pair<std::string, std::string>> Queries = StripQueries(ClientData->Url);
	//Get the page they requested into a string.  Later we can change this step to generate the html required, rather than just reading a file.
	std::string RequestedPage = LoadFileToMemory(*ClientData->Url);
	//If the page isn't found, RequestedPage will be empty and we won't send them anything.  In the future we should instead send them a 404 page or something
	if (RequestedPage.size() > 0) {
		//Make the Http header, which requires knowing the size of the RequestedPage
		std::string BaseResponse = GenerateHttpHeader(RequestedPage);
		//We want to send the Header + the page
		std::string HttpPacket = BaseResponse + RequestedPage;
		//Send it dog
		send(sock, HttpPacket.data(), HttpPacket.size(), 0);
	}
	//Returns the number of "lines" parsed by http_parser_execute.  Not really useful right now.
	return (DWORD)NumParsed;
}

int main() {
#if 0
	char CurrentPath[FILENAME_MAX];
	_getcwd(CurrentPath, sizeof(CurrentPath));
#endif

	//Setup Winsock
	WSADATA wsaData;
	if (WSAStartup(MAKEWORD(2, 2), &wsaData) != 0) {
		//fail
		return 0;
	}

	addrinfo *Result = NULL;
	addrinfo Hints = { 0 };
	Hints.ai_family = AF_INET;
	Hints.ai_socktype = SOCK_STREAM;
	Hints.ai_protocol = IPPROTO_TCP;
	Hints.ai_flags = AI_PASSIVE;

	int iResult = getaddrinfo(NULL, DEFAULT_PORT, &Hints, &Result);
	if(iResult) {
		printf("getaddrinfo failed with error: %d\n", iResult);
		WSACleanup();
		return 1;
	}

	//Setup listener
	SOCKET ListenSocket;
	ListenSocket = socket(Result->ai_family, Result->ai_socktype, Result->ai_protocol);
	if (ListenSocket != INVALID_SOCKET) {
		iResult = bind(ListenSocket, Result->ai_addr, (int)Result->ai_addrlen);
		if (iResult == SOCKET_ERROR) {
			printf("bind failed with error: %d\n", iResult);
			closesocket(ListenSocket);
			WSACleanup();
			return 1;
		}
		iResult = listen(ListenSocket, SOMAXCONN);
		if (iResult == SOCKET_ERROR) {
			printf("listen failed with error: %d\n", WSAGetLastError());
			closesocket(ListenSocket);
			WSACleanup();
			return 1;
		}
	}
	//Loop forever waiting for connections
	while (1) {
		//Accept Connection
		SOCKET sock = accept(ListenSocket, NULL, NULL);
		if (sock == INVALID_SOCKET) {
			printf("accept failed with error: %d\n", WSAGetLastError());
			closesocket(ListenSocket);
			WSACleanup();
			return 1;
		}
		DWORD nThreadID;
		//Create the thread
		CreateThread(0, 0, ClientHandler, (void *)sock, 0, &nThreadID);
	}

	//Cleanup Winsock
	WSACleanup();
}