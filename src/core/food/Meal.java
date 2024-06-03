package core.food;

import java.util.ArrayList;

import core.enums.FoodType;
import core.enums.MealSize;

public class Meal extends MenuItem{

	private boolean hasGluten;
	private FoodType foodType;
	protected ArrayList<Dish> dishes;
	protected MealSize mealSize;

	public Meal(String mealName, ArrayList<Dish> dishesList) {
		super();
		this.setName(mealName);
		this.dishes = dishesList;
		this.hasGluten = false;
		this.foodType = FoodType.VEGETARIAN;
		this.mealSize = MealSize.FULLMEAL;
		
		// Checking for gluten and/or standard dish
		for (Dish dish : dishesList) {
			if (dish.isHasGluten()) {
				this.hasGluten = true;
			}
			if (dish.getFoodType() == FoodType.STANDARD) {
				this.foodType = FoodType.STANDARD;
			}
		}

		// Pricing the meal depending on its contents
		double price = 0;
		for (Dish dish : dishes) {
			price += dish.getPrice();
		}
		this.setPrice(price);
		
		if (dishesList.size() == 2) {
			this.mealSize = MealSize.HALFMEAL;
		}
	}
	
	@Override
	public String toString() {
		return "Meal : "+ getName() + ", " + foodType+ ", " + (hasGluten ? "" : ", gluten free") + '\n'+
				dishes;
	}
	
	public void setPrice(double price) {
		super.price = price;
	}

	public double getPrice() {
		return price;
	}

	public boolean isHasGluten() {
		return hasGluten;
	}

	public FoodType getFoodType() {
		return foodType;
	}

	public ArrayList<Dish> getDishes() {
		return dishes;
	}

	public MealSize getMealSize() {
		return mealSize;
	}
	
	
}
