package core.food;

public abstract class MenuItem {
	private String name;
	protected double price;

	public String getName() {
		return this.name;
	}

	public abstract double getPrice();
	public int orderFrequency=0;

	protected void setName(String name) {
		this.name = name;
	}
	
}
