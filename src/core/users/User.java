package core.users;

public class User {
	// Each user has a surname
	protected String surname;
	
	/*
	 * Each user has a unique id,
	 * static variable nextId is used to 
	 * generate an id upon construction 
	 * of an instance
	 */
	protected int id;
	private static int nextId = 0;

	
	// login info
	protected String username;
	private int hashedPassword; 
	
	public User(String newUsername, String password) {
		this.username = newUsername;
		
		// By default, surname is the same as the username
		this.surname = username;
		
		// hashing the password for security reasons
		this.hashedPassword = password.hashCode();
		
		// IDs start from 1 
		nextId = nextId + 1;
		this.id = nextId;
	}
	
	public void login() {
		// TODO
	}

	public int getHashedPassword() {
		return hashedPassword;
	}
	public int getId() {
		return id;
	}
}
