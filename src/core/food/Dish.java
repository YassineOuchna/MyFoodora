package core.food;

import core.enums.DishCategory;
import core.enums.FoodType;

public class Dish implements MenuItem{

	private String name;
	private double price;
	private boolean hasGluten;
	private FoodType foodType;
	private DishCategory dishCategory;

	public Dish(String name, double price, boolean hasGluten, FoodType foodType, DishCategory dishCategory) {
		this.name = name;
		this.price = price;
		this.hasGluten = hasGluten;
		this.foodType = foodType;
		this.setDishCategory(dishCategory);
	}
	
	@Override
	public String toString() {
		return ""+ dishCategory + " : " + this.name + 
				(hasGluten ? "" : ", gluten free") +
				", " + foodType + ", " + price + "$" ;
	}

	public DishCategory getDishCategory() {
		return dishCategory;
	}

	public void setDishCategory(DishCategory dishCategory) {
		this.dishCategory = dishCategory;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public boolean isHasGluten() {
		return hasGluten;
	}

	public FoodType getFoodType() {
		return foodType;
	}

}
