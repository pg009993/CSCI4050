package edu.uga.cs4300.objectlayer;

public class User {
	private int id;
	private String first_name;
	private String last_name;
	private String username;
	private String password;
	private String email;
	private String image64;
	
	
	public String getImage64() {
		return image64;
	}

	public void setImage64(String image64) {
		this.image64 = image64;
	}

	public User(int id, String fname, String lname, String uname, String password, String email) {
		this.setId(id);
		this.setFirst_name(fname);
		this.setLast_name(lname);
		this.setUsername(uname);
		this.setPassword(password);
		this.setEmail(email);
	}
	
	public User(String fname, String lname, String uname, String password, String email) {
		this.setFirst_name(fname);
		this.setLast_name(lname);
		this.setUsername(uname);
		this.setPassword(password);
		this.setEmail(email);
	}
	
	
	public User(int id2, String firstName, String lastName, String userName2, String passWord2, String email2,
			String image64) {
		this.setId(id2);
		this.setFirst_name(firstName);
		this.setLast_name(lastName);
		this.setUsername(userName2);
		this.setPassword(passWord2);
		this.setEmail(email2);
		this.setImage64(image64);
		
	}
	
	public User(int id, String username, String image64)
	{
		this.setId(id);
		this.setUsername(username);
		this.setImage64(image64);
	}
	
	public User(String firstName, String lastName, String image64, int id) {
		this.setId(id);
		this.setFirst_name(firstName);
		this.setLast_name(lastName);
		this.setImage64(image64);
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
