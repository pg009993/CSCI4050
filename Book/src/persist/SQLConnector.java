package persist;

import java.sql.*; 
import object.User;

public class SQLConnector {
	private Connection connection;
	private Statement statement; 
	private ResultSet results; 
	private int check;
	
	public SQLConnector(){
		try{
			Class.forName("com.mysql.jdbc.Driver"); 
			//connection created 
			//connection uses SSL to ensure secure connection
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce?verifyServerCertificate=false&useSSL=true","root", "paw22nvc3");
			statement = connection.createStatement(); 
		} catch(Exception e){
			System.out.print("Error" + e);
		}
	}
	
	public void DisplayBooksXML(){
		try{
			String query = "SELECT * FROM BOOKS;"; 
			results = statement.executeQuery(query); 
		
			//https://www.mkyong.com/java/how-to-create-xml-file-in-java-dom/
			
		}catch(Exception e){
			System.out.println("Error: " + e);
		}
	}
	
	public ResultSet ReturnBooks(String isbn) {
		String query = "SELECT * FROM books WHERE ISBN='" + isbn + "'";
		try {
			results = statement.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return results;
	}
	
	public int AddToCart(String query) {
		try {
			Statement s = connection.createStatement();
			check = s.executeUpdate(query);
		}catch(Exception e){
			System.out.println(e); 
		}
		return check;
	}
	
	public ResultSet Suspended(String query) {
		try {
			results = statement.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return results;
	}
	
	public void remove(int cartid) {
		String query = "DELETE FROM cart WHERE cartid='" + cartid + "';";
		try {
			Statement s = connection.createStatement();
			check = s.executeUpdate(query);
		}catch(Exception e){
			System.out.println(e); 
		}
	}
	
	public ResultSet CheckCart(String query) {
		try {
			results = statement.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return results;
	}
	
	public ResultSet CheckPromo(String query) {
		try {
			results = statement.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return results;
	}
	
	public ResultSet Total(String query) {
		try {
			results = statement.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return results;
	}
	
	
		public int UpdateUser(User u) {
			String query = "UPDATE users SET first_name='" + u.getFirst() + "', last_name='" + u.getLast() + "', email='" + u.getEmail() + "', password='" + u.getPassword() + "', phone='" + u.getNumber() + "', street='" + u.getStreet() + "', city='" + u.getCity() + "', state='" + u.getState() + "', zip='" + u.getZip() + "', gender='" + u.getGender() + "', DOB='" + u.getBirthday() + "' WHERE id=" + u.getId() + ";";
			try {
				Statement s = connection.createStatement();
				check = s.executeUpdate(query);
			}catch(Exception e){
				System.out.println(e); 
			}
			return check;
		}
		
		public int AddOrder(String query) {
			try {
				Statement s = connection.createStatement();
				check = s.executeUpdate(query);
			}catch(Exception e){
				System.out.println(e); 
			}
			return check;
		}
		
		public int UpdateQuantity(String query) {
			try {
				Statement s = connection.createStatement();
				check = s.executeUpdate(query);
			}catch(Exception e){
				System.out.println(e); 
			}
			return check;
		}
		
		public int Delete(String query) {
			try {
				Statement s = connection.createStatement();
				check = s.executeUpdate(query);
			}catch(Exception e){
				System.out.println(e); 
			}
			return check;
		}
	
		public int InsertUser(User u) {
			String query = "INSERT into users(first_name, last_name, email, gender, DOB, city, state, street, password, phone, username, zip) values ('" + u.getFirst() + "', '" + u.getLast() + "', '" + u.getEmail() + "', '" + u.getGender() + "', '" + u.getBirthday() + "', '" + u.getCity() + "', '" + u.getState() + "', '" + u.getStreet() + "', '" + u.getPassword() + "', '" + u.getNumber() + "', '" + u.getUsername() + "', '" + u.getZip() + "');";		
			try {
				Statement s = connection.createStatement();
				check = s.executeUpdate(query);
			}catch(Exception e){
				System.out.println(e); 
			}
			return check;
		}
		
		public ResultSet UserCheck(String query) {
			try {
				results = statement.executeQuery(query);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return results;
		}
		
		public ResultSet Login(String query) {
			try {
				results = statement.executeQuery(query);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return results;
		}
/*
		public void InsertBook(String title, String publisher, String description, String isbn, String genre, String price){
			String query = "INSERT into books(title, publisher, description, isbn, genre, price) values(NEED TO INSERT VALUES);"; 
				Statement s = connection.createStatement();
				int check = s.executeUpdate(query);
			}catch(Exception e){
				System.out.println(e); 
			}
		}
*/

		public void ApplyPromotion(int percentage){
			//call this in the function when you calculate the total of the cart
		}




}//end of class 

































