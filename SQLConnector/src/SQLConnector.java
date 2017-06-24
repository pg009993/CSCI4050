import java.sql.*; 

public class SQLConnector {
	private Connection connection;
	private Statement statement; 
	private ResultSet results; 
	
	public SQLConnector(){
		try{
			Class.forName("com.mysql.jdbc.Driver"); 
			//connection created 
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/USERS","root", "root");
			//statement 
			statement = connection.createStatement(); 
		} catch(Exception e){
			System.out.print("Error" + e);
		}
	}
	
	public void DisplayData(){
		
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
	
	
}
