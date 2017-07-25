package boundary;


import java.io.IOException;
import java.math.RoundingMode;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
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

import logiclayer.LogicConnector;
import object.User;


/**
 * Servlet implementation class BookServlet
 */
@WebServlet("/BookServlet")
public class BookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String templateDir = "/WEB-INF/templates";
	private TemplateProcessor process;
	private static String f, l, bd, g, e, u, p, n, str, c, sta, z;
	private static int i, totalorders;
	private static double t;

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
		LogicConnector logic = new LogicConnector();
		ResultSet rs = logic.Login(query);

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

					User user = new User(f,l,e,p,n,str,c,sta,z,g,bd,u,i);
					int check = logic.UpdateUser(user);
					//conn.UpdateUser(f, l, e, p, n, str, c, sta, z, g, bd, i);
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
		String username = request.getParameter("username");
		String newpass = addSalt(password);

		String query = "SELECT * FROM users;";
		LogicConnector logic = new LogicConnector();
		ResultSet rs = logic.Login(query);

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
			request.setAttribute("username", u);
			try {
				request.getRequestDispatcher("loggedin.jsp").forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
		boolean unique = UniqueUsername(username);

		User u = new User(firstname, lastname, email, newpass, number, street, city, state, zip, gender, birthday, username);

		if(unique) {
			LogicConnector logic = new LogicConnector();
			int check = logic.addUser(u);
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
		LogicConnector logic = new LogicConnector();
		ResultSet rs = logic.UserCheck(query);
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

	public void AddToCart(HttpServletRequest request, HttpServletResponse response) {
		String isbn = request.getParameter("isbn");
		String price = request.getParameter("price");
		String title = request.getParameter("title");
		LogicConnector logic = new LogicConnector();

		ResultSet rs = logic.ReturnBooks(isbn);

		try {
			while(rs.next()) {
				String query = "INSERT INTO cart(username, ISBN, title, price) VALUES('" + u + "', '" + isbn + "', '" + title + "', '" + price + "');";
				logic.AddToCart(query);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("username", u);
		try {
			request.getRequestDispatcher("loggedin.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void Logout(HttpServletRequest request, HttpServletResponse response) {
		f=null;
		l=null;
		bd=null;
		g=null;
		e=null;
		u=null;
		p=null;
		n=null;
		str=null;
		c=null;
		sta=null;
		z=null;
		i=-99;
		t=0.0;
		try {
			response.sendRedirect("signin.html");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public double PriceToDouble(String price) {
		String sub = "";
		for(int i=1; i<price.length(); i++) {
			sub+= price.charAt(i);
		}
		
		double price2 = Double.parseDouble(sub);
		return price2;
	}
	
	public void RemoveFromCart(HttpServletRequest request, HttpServletResponse response) {
		String c = request.getParameter("cartid");
		int cartid = Integer.parseInt(c);
		
		LogicConnector logic = new LogicConnector();
		logic.remove(cartid);
		
		double total = Total(request, response);
		LoadCart(request, response, total);
	}
	
	public double Total(HttpServletRequest request, HttpServletResponse response) {
		String query = "SELECT * FROM cart WHERE username='" + u + "';";
		Double total=0.0;
		LogicConnector logic = new LogicConnector();
		ResultSet rs = logic.Total(query);
		
		try {
			while(rs.next()) {
				String price = rs.getString("price");
				total+= PriceToDouble(price);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		t=total;
		return total;
	}
	
	public void LoadCart(HttpServletRequest request, HttpServletResponse response, double total) {
		//double total = Total(request, response);
		request.setAttribute("username", u);
		request.setAttribute("total", total);
		t=total;
		try {
			request.getRequestDispatcher("cart.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean CheckCart(HttpServletRequest request, HttpServletResponse response) {
		String query = "SELECT * FROM cart WHERE username='" + u + "';";
		LogicConnector logic = new LogicConnector();
		ResultSet rs = logic.CheckCart(query);
		int count=0;
		
		try {
			while(rs.next()) {
				count++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(count>0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void Promo(HttpServletRequest request, HttpServletResponse response) {
		DecimalFormat df = new DecimalFormat("#.##");
		df.setRoundingMode(RoundingMode.CEILING);
		double total = Total(request, response);
		String code = request.getParameter("code");
		String query = "SELECT percentage FROM promos WHERE code='" + code + "';";
		
		LogicConnector logic = new LogicConnector();
		ResultSet rs = logic.CheckPromo(query);
		int p=1;
		try {
			while(rs.next()) {
				p = rs.getInt("percentage");
				System.out.println("PEE: " + p);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(p!=1) {
			double pee = (double)p;
			double discount = (pee/100.00);
			System.out.println("DISCOUNT: " + discount);
			total-=(total*discount);
		}
		System.out.println("TOTAL: " + total);
		String tot = df.format(total);
		double tota = Double.parseDouble(tot);
		t=tota;
		LoadCart(request, response, t);
	}
	
	public void SubmitOrder(HttpServletRequest request, HttpServletResponse response) {
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String number = request.getParameter("number");
		String street = request.getParameter("street");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String zip = request.getParameter("zip");
		String card = request.getParameter("cardnumber");
		String name=firstname + " " + lastname;
				
		String query = "SELECT * FROM cart WHERE username='" + u + "';";
		LogicConnector logic = new LogicConnector();
		ResultSet rs = logic.CheckCart(query);
		totalorders++;
		try {
			while(rs.next()) {
				String isbn = rs.getString("ISBN");
				String query2 = "INSERT into orders(orderid, isbn, name, phone, card, street, city, state, zip, username, date) values ('" + totalorders + "', '"+ isbn + "', '" + name + "', '" + number + "', '" + card + "', '" + street + "', '" + city + "', '" + state + "', '" + zip + "', '" + u + "', CURDATE());";
				int check = logic.AddOrder(query2);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String query3 = "SELECT * FROM cart WHERE username='" + u + "';";
		ResultSet result = logic.CheckCart(query3);
		
		try {
			while(result.next()) {
				String isbn = result.getString("ISBN");
				String query5 = "UPDATE books SET quantity = quantity-1 WHERE ISBN = '" + isbn + "';";
				logic.UpdateQuantity(query5);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String query4 = "DELETE FROM cart WHERE username='" + u + "';";
		int check = logic.Delete(query4);
		
		request.setAttribute("username", u);
		try {
			request.getRequestDispatcher("loggedin.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String register = request.getParameter("register");
		String login = request.getParameter("loginbutton"); 
		String editprofile = request.getParameter("editprofile");
		String submitedit = request.getParameter("submitedit");
		String add = request.getParameter("addToCart");
		String logout = request.getParameter("logout");
		String loadcart = request.getParameter("cartButton");
		String remove = request.getParameter("removeFromCart");
		String complete = request.getParameter("completeOrder");
		String promo = request.getParameter("promoCode");
		String submitOrder = request.getParameter("submitOrder");
		
		if(submitOrder!=null) {
			SubmitOrder(request, response);
		}
		if(promo!=null) {
			System.out.println("PROMO PRESSED");
			Promo(request, response);
		}
		if(complete!=null) {
			if(CheckCart(request, response)) {
				response.sendRedirect("checkout.jsp");
			}
			else {
				double total = Total(request, response);
				LoadCart(request, response, total);
			}
		}
		if(remove!=null) {
			RemoveFromCart(request, response);
		}
		if(loadcart!=null) {
			double total = Total(request, response);
			LoadCart(request, response, total);
		}
		if(logout!=null) {
			Logout(request, response);
		}
		if(add!=null) {
			if(u != null && !u.equals("")) {
				AddToCart(request, response);
			}
			else {
				try {
					response.sendRedirect("signin.html");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
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
