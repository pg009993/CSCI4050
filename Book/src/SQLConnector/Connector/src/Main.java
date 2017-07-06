package SQLConnector.Connector.src;

public class Main {
	public static void main(String args[]){
		//System.out.println("Hello world");
		SQLConnector connection = new SQLConnector(); 
		connection.DisplayUserData(); 
		connection.DisplayBookData();
		//connection.DislpayBooksXML();
	}
}