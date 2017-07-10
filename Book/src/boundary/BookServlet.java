package boundary;


import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapperBuilder;
import freemarker.template.SimpleHash;

import SQLConnector.Connector.src.Main;
import SQLConnector.Connector.src.SQLConnector;


/**
 * Servlet implementation class BookServlet
 */
@WebServlet("/BookServlet")
public class BookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private String templateDir = "/WEB-INF/templates";
	private TemplateProcessor process;
	
    public BookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		process = new TemplateProcessor(templateDir, getServletContext());
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String signup = request.getParameter("signup");
		String startover = request.getParameter("startover");
		String register = request.getParameter("register");
		String login = request.getParameter("loginbutton"); 
		
		if(login != null){
			LoginUser(request, response); 
		}
		if(signup!=null) {
			VerifyUser(request, response);
		}
		if(startover!=null) {
			StartOver(request, response);
		}
		if(register!=null) {
			Register(request, response);
		}
		
	}
	
	public void LoginUser(HttpServletRequest request, HttpServletResponse response){
		String password = request.getParameter("password");
		//USERNAME NOT EMAIL
		//String email = request.getParameter("email");
		String username = request.getParameter("username");
		String newpass = addSalt(password);
		//SQLConnector conn = new SQLConnector();
		//conn.Login(username, newpass);
		String query = "SELECT * FROM users;";
		SQLConnector conn = new SQLConnector();
		ResultSet rs = conn.Login(query);
		
		boolean found=false;
		
		try {
			while(rs.next()) {
				String user = rs.getString("username");
				String pass = rs.getString("password");
				if(user.equals(username) && pass.equals(password)) {
					found=true;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(found) {
			DefaultObjectWrapperBuilder df = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
			SimpleHash root = new SimpleHash(df.build());
			root.put("username", username);
			String templateName = "index.ftl";
			process.processTemplate(templateName, root, request, response);
		}
		else {
			try {
				response.sendRedirect("signinerror.html");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	

	public void VerifyUser(HttpServletRequest request, HttpServletResponse response) {
		DefaultObjectWrapperBuilder df = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
		SimpleHash root = new SimpleHash(df.build());
		root.put("firstname", request.getParameter("firstname"));
		root.put("lastname", request.getParameter("lastname"));
		root.put("month", request.getParameter("DOBMonth"));
		root.put("day", request.getParameter("DOBDay"));
		root.put("year", request.getParameter("DOBYear"));
		root.put("gender", request.getParameter("gender"));
		root.put("street", request.getParameter("street"));
		root.put("city", request.getParameter("city"));
		root.put("state", request.getParameter("state"));
		root.put("zip", request.getParameter("zip"));
		root.put("username", request.getParameter("username"));
		root.put("password", request.getParameter("password"));
		root.put("email", request.getParameter("email"));
		root.put("phone", request.getParameter("phone"));		
		String templateName = "code.ftl";
		process.processTemplate(templateName, root, request, response);
	}
	
	public void StartOver(HttpServletRequest request, HttpServletResponse response) {
		DefaultObjectWrapperBuilder df = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
		SimpleHash root = new SimpleHash(df.build());
		String templateName = "signup.ftl";
		process.processTemplate(templateName, root, request, response);
	}
	
	public void Register(HttpServletRequest request, HttpServletResponse response) {
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String number = request.getParameter("number");
		String street = request.getParameter("street");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String zip = request.getParameter("zip");
		String gender = request.getParameter("gender");
		String month = request.getParameter("DOBMonth");
		String day = request.getParameter("DOBDay");
		String year = request.getParameter("DOBYear");
		String username = request.getParameter("username");
		String birthday = day + "-" + month + "-" + year;
		System.out.println("BIRTHDAY: " + birthday);
		String newpass = addSalt(password);
		//Check username method
		boolean unique = UniqueUsername(username);
		
		if(unique) {
		SQLConnector conn = new SQLConnector();
		conn.InsertUser(firstname, lastname, email, newpass, number, street, city, state, zip, gender, birthday, username);
		try {
			response.sendRedirect("signin.html");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		}
		else {
			try {
				response.sendRedirect("registererror.html");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
	public boolean UniqueUsername(String username) {
		String query = "SELECT * FROM users;";
		SQLConnector conn = new SQLConnector();
		ResultSet rs = conn.UserCheck(query);
		boolean unique = true;
		
		try {
			while(rs.next()) {
				String user = rs.getString("username");
				if(user.equals(username)) {
					unique = false;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return unique;
		
	}

	public String addSalt(String password) {
		//we want to add pepper first then salt  
		//password = pass;
        addPepper(password);
		//String password 
		String s = password;
		String pass = "";
		 // String s= (args[0]);
	        for (int i = s.length()-1; i >= 0; i--) {            
	        	pass += s.charAt(i);    
	        }
	        
	        return pass;
	}
	

	public void addPepper(String password){
		password = password + "8b24c1252"; 
		System.out.println(password);
	}

	public void sendVerificationEmail(HttpServletRequest request, HttpServletResponse response){
		//randomly generate 6 digit verification code 
		//int verificatoinCode = 100000 + random_float() * 900000;
		String email = request.getParameter("email");
		String firstname = request.getParameter("firstname");

		//email format 
		//Hi "firstname", here's your verfication code for our awesome book webaite. 
		
		

	}

	public void addUserToDatabase(){
		//call connection to SQL database 
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
