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
	

	/*
	public void DisplayUserData(){
		try{
			String query = "SELECT * FROM USERS;"; 
			results = statement.executeQuery(query); 
		
			
			System.out.println("Results from database");
			while(results.next()){
				
				String first_name = results.getString("first_name");
				String last_name = results.getString("last_name");
				String id = results.getString("id");
				String DOB = results.getString("DOB");
				String street = results.getString("street");
				String city =results.getString("city");
				String state = results.getString("state");
				String password = results.getString("password");
				String email = results.getString("email");
				String gender = results.getString("gender");
	
				System.out.println("--------USER---------");
				System.out.println("ID: " + id );
				System.out.println("First Name: " + first_name);
				System.out.println("Last Name: " + last_name);
				System.out.println("E-mail: " + email);
				System.out.println("Gender: " + gender);
				System.out.println("DOB: " + DOB);
				System.out.println("City: " + city);
				System.out.println("State: " + state);
				System.out.println("Street: " + street);
				System.out.println("Password: " + password);
				System.out.println("\n");
	
			}
		}catch(Exception e){
			System.out.println("Error: " + e); 
		}
	}
		
		public void DisplayBookData(){
			try{
				String query = "SELECT * FROM BOOKS;"; 
				results = statement.executeQuery(query); 
				
				System.out.println("Results from database");
				while(results.next()){		
					String title = results.getString("title");
					String publisher = results.getString("publisher");
					String author = results.getString("author");
					String ISBN = results.getString("ISBN");
					String price = results.getString("price");
					String quantity =results.getString("quantity");
					String description = results.getString("description");
					String genre = results.getString("genre");
					
					System.out.println("--------BOOKS---------");
					System.out.println("Title: " + title );
					System.out.println("Publisher: " + publisher);
					System.out.println("Author: " + author);
					System.out.println("ISBN: " + ISBN);
					System.out.println("Quantity: " + quantity);
					System.out.println("Description: " + description);
					System.out.println("Genre: " + genre);
					System.out.println("Price: " + price);
					System.out.println("\n");
				}
			} catch(Exception e){
				System.out.println("Error: " + e); 
			}
		}

		*/
		
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

































