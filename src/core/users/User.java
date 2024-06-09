package core.users;

public class User {
	// Each user has a name and a surname
	private String surname;
	private String name;
	private String phoneNumber;
	private String email;
	
	// User can be activated or deactivated
	private boolean isActive;
	
	public String getSurname() {
		return surname;
	}
	/*
	 * Each user has a unique id,
	 * static variable nextId is used to 
	 * generate an id upon construction 
	 * of an instance
	 */
	private int id;
	private static int nextId = 0;

	// login info
	private String username;
	private int hashedPassword; 
	
	public User(String newUsername, String password) {
		this.username = newUsername;
		
		// By default, name is the same as the username
		// and an empty surname
		this.name = username;
		this.surname = "";
		
		// hashing the password for security reasons
		this.hashedPassword = password.hashCode();
		
		// IDs start from 1 
		nextId = nextId + 1;
		this.id = nextId;
		
		// User is active by default
		isActive = true;
	}
	

	public int getHashedPassword() {
		return hashedPassword;
	}
	public int getId() {
		return id;
	}


	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getUsername() {
		return username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public static int getNextId() {
		return nextId;
	}

	public String getUserType() {
		if (this.getClass() == Customer.class) {
			return "Customer";
		}
		if (this.getClass() == Restaurant.class) {
			return "Restaurant";
		}
		if (this.getClass() == Courier.class) {
			return "Courier";
		}
		if (this.getClass() == Manager.class) {
			return "Manager";
		}
		return null;
	}
}
