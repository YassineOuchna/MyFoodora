package core.food;

public class MenuItemFactoryProducer {
	
	public MenuItemFactory getFactory(String itemType) {
		if (itemType.equalsIgnoreCase("DISH")) {
			return new DishFactory();
		}
		if (itemType.equalsIgnoreCase("MEAL")) {
			return new MealFactory();
		}
		return null;
	}

}
