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
	private static String f, l, bd, g, e, u, p, n, str, c, sta, z;
	private static int i;
	
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
		doPost(request, response);
	}
	
	public void SubmitEdit(HttpServletRequest request, HttpServletResponse response) {
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String email = request.getParameter("email");
		String oldpass = request.getParameter("oldpass");
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
		String birthday = day + "-" + month + "-" + year;
		
		String salt = addSalt(oldpass);
		boolean exists = false;
		
		String query = "SELECT * FROM users;";
		SQLConnector conn = new SQLConnector();
		ResultSet rs = conn.Login(query);
		
		try {
			while(rs.next()) {
				String pass = rs.getString("password");
				int id = rs.getInt("id");
				if(id==i) {
					System.out.println("user found");
					exists = true;
					String newsalt=addSalt(password);
					if(!(day.equals("Day") && month.equals("Month") && year.equals("Year"))) {
						bd=birthday;
					}
					if(str!=street) {
						str=street;
					}
					if(c!=city) {
						c=city;
					}
					if(sta!=state) {
						sta=state;
					}
					if(z!=zip) {
						z=zip;
					}
					if(firstname!=f) {
						f=firstname;
						System.out.println("firstname");
					}
					if(lastname!=l) {
						l=lastname;
					}
					if(gender!=g && gender!=null) {
						g=gender;
					}
					if(email!=e) {
						e=email;
					}
					if(!(p.equals(newsalt)) && !(password.equals(""))) {
						System.out.println("PASSWORD: " + password);
						p=newsalt;
					}
					if(n!=number) {
						n=number;
					}
					conn.UpdateUser(f, l, e, p, n, str, c, sta, z, g, bd, i);
					DefaultObjectWrapperBuilder df = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
					SimpleHash root = new SimpleHash(df.build());
					root.put("username", u);
					String templateName = "index.ftl";
					process.processTemplate(templateName, root, request, response);
				}
				
			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
 	
	public void EditProfile(HttpServletRequest request, HttpServletResponse response) {
		DefaultObjectWrapperBuilder df = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
		SimpleHash root = new SimpleHash(df.build());
		root.put("firstname", f);
		root.put("lastname", l);
		root.put("dob", bd);
		root.put("username", u);
		root.put("gender", g);
		root.put("street", str);
		root.put("city", c);
		root.put("state", sta);
		root.put("zip", z);
		root.put("password", p);
		root.put("email", e);
		root.put("phone", n);		
		String templateName = "editprofile.ftl";
		process.processTemplate(templateName, root, request, response);
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
				if(user.equals(username) && pass.equals(newpass)) {
					found=true;
					f=rs.getString("first_name");
					l=rs.getString("last_name");
					bd=rs.getString("DOB");
					g=rs.getString("gender");
					e=rs.getString("email");
					n=rs.getString("phone");
					str=rs.getString("street");
					c=rs.getString("city");
					sta=rs.getString("state");
					z=rs.getString("zip");
					u = user;
					p = pass;
					i = rs.getInt("id");
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
        String p = addPepper(password);
		//String password 
		String s = p;
		String pass = "";
		 // String s= (args[0]);
	        for (int i = s.length()-1; i >= 0; i--) {            
	        	pass += s.charAt(i);    
	        }
	        
	        return pass;
	}
	

	public String addPepper(String password){
		String pass = password + "8b24c1252";
		return pass;
	}

	public void addUserToDatabase(){
		//call connection to SQL database 
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String register = request.getParameter("register");
		String login = request.getParameter("loginbutton"); 
		String editprofile = request.getParameter("editprofile");
		String submitedit = request.getParameter("submitedit");
		
		if(submitedit!=null) {
			System.out.println("submitted edit profile");
			SubmitEdit(request, response);
		}
		if(editprofile!=null) {
			EditProfile(request,response);
			System.out.println("editprofile pressed.");
		}
		if(login != null){
			LoginUser(request, response); 
		}
		if(register!=null) {
			Register(request, response);
		}
	}

}
