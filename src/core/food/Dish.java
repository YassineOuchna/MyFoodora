package core.food;

import core.enums.DishCategory;
import core.enums.FoodType;

public class Dish extends MenuItem{

	private boolean hasGluten;
	private FoodType foodType;
	private DishCategory dishCategory;

	public Dish(String name, double price, boolean hasGluten, FoodType foodType, DishCategory dishCategory) {
		super();
		this.hasGluten = hasGluten;
		setName(name);
		this.setPrice(price);
		this.foodType = foodType;
		this.setDishCategory(dishCategory);
	}
	
	@Override
	public String toString() {
		return ""+ dishCategory + " : " + getName() + 
				(hasGluten ? "" : ", gluten free") +
				", " + foodType + ", " + price + "$" ;
	}

	public DishCategory getDishCategory() {
		return dishCategory;
	}

	public void setDishCategory(DishCategory dishCategory) {
		this.dishCategory = dishCategory;
	}



	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		super.price = price;
	}

	public boolean isHasGluten() {
		return hasGluten;
	}

	public FoodType getFoodType() {
		return foodType;
	}

}
