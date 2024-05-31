package core.users;

public class Restaurant extends User{
	private double[] location;
	private Menu menu;
	private double genericDiscount;
	private double specialDiscount;

	public Restaurant(String newUsername, String password) {
		super(newUsername, password);
	}

	public double[] getLocation() {
		return location;
	}

	public void setLocation(double[] location) {
		this.location = location;
	}

	public double getGenericDiscount() {
		return genericDiscount;
	}

	public void setGenericDiscount(double genericDiscount) {
		this.genericDiscount = genericDiscount;
	}

	public double getSpecialDiscount() {
		return specialDiscount;
	}

	public void setSpecialDiscount(double specialDiscount) {
		this.specialDiscount = specialDiscount;
	}

	public Menu getMenu() {
		return menu;
	}
	
	
	

}
