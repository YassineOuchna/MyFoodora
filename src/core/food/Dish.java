package core.food;

import core.enums.DishCategory;

public abstract class Dish {
	
	private DishCategory dishCategory;

	public Dish(DishCategory category) {
		this.setDishCategory(category);
	}

	public DishCategory getDishCategory() {
		return dishCategory;
	}

	public void setDishCategory(DishCategory dishCategory) {
		this.dishCategory = dishCategory;
	}

}
