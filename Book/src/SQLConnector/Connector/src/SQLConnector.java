package SQLConnector.Connector.src;

import java.sql.*; 

public class SQLConnector {
	private Connection connection;
	private Statement statement; 
	private ResultSet results; 
	
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
		
		public void InsertUser(String firstname, String lastname, String email, String password, String number, String street, String city, String state, String zip) {
			//WRITE FUNCTION FOR ID
			// ADD GENDER AND DOB
			int id = 7;
			String query = "INSERT into users(id, first_name, last_name, email, gender, DOB, city, state, street, password) values ('7', '" + firstname + "', '" + lastname + "', '" + email + "', 'Male', '1979-08-25 06:11:33', '" + city + "', '" + state + "', '" + street + "', '" + password + "');";		
			try {
				Statement s = connection.createStatement();
				int check = s.executeUpdate(query);
			}catch(Exception e){
				System.out.println(e); 
			}
		}
		
		
		public void Login(String email, String password) {
			try {
			String query = "SELECT u.email AND u.password FROM users AS u;";
			} catch(Exception e){
				System.out.println("Invalid credentials. Please try again."); 
			}
		}
}
