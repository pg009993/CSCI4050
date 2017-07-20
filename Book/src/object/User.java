package object;

public class User {

	private String first, last, email, password, number, street, city, state, zip, gender, birthday, username;
	private int id;
	
	public User(String firstname, String lastname, String email, String password, String number, String street, String city, String state, String zip, String gender, String birthday, String username, int id) {
		first = firstname;
		last=lastname;
		this.username=username;
		this.email = email;
		this.password = password;
		this.number = number;
		this.street=street;
		this.city=city;
		this.state=state;
		this.zip=zip;
		this.gender=gender;
		this.id=id;
		this.birthday=birthday;
	}
	
	public User(String firstname, String lastname, String email, String password, String number, String street, String city, String state, String zip, String gender, String birthday, String username) {
		first = firstname;
		last=lastname;
		this.username = username;
		this.email = email;
		this.password = password;
		this.number = number;
		this.street=street;
		this.city=city;
		this.state=state;
		this.zip=zip;
		this.gender=gender;
		this.birthday=birthday;
	}

	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public String getLast() {
		return last;
	}

	public void setLast(String last) {
		this.last = last;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username=username;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
