package core.food;

import core.enums.FoodType;

public abstract class MenuItem {
	protected double price;
	protected String name;
	protected boolean hasGlutten;
	
	// if the food is vegetarian or standard
	protected FoodType foodType;
	
	public MenuItem(String itemName, double itemPrice, boolean glutten, FoodType foodtype) {
		this.name = itemName;
		this.price = itemPrice;
		this.hasGlutten = glutten;
		this.foodType = foodtype;
	}
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isHasGlutten() {
		return hasGlutten;
	}
	public void setHasGlutten(boolean hasGlutten) {
		this.hasGlutten = hasGlutten;
	}
	public FoodType getFoodType() {
		return foodType;
	}
	public void setFoodType(FoodType foodType) {
		this.foodType = foodType;
	}
	
	
}
