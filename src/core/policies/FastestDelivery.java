package core.policies;

import java.util.ArrayList;

import core.orders.Order;
import core.users.Courrier;

public class FastestDelivery implements DeliveryPolicy{

	@Override
	public Courrier assignCourrier(ArrayList<Courrier> courriers, Order order) {
		Courrier selectedCourier = null;
        double shortestDistance = Double.MAX_VALUE;

        for (Courrier courier : courriers) {
        	if (courier.isOnDuty()) {
        		double distance=calculateDistance(courier.getPosition(), order.getRestaurant().getLocation(), order.getCustomer().getAddress());
            if (distance < shortestDistance) {
                shortestDistance = distance;
                selectedCourier = courier;}
        	
            }
        }

        return selectedCourier;
	}
	private double calculateDistance(double[] courierLocation, double[] restaurantLocation, double[] customerLocation) {
        // Calculate the distance between the courier, the restaurant, and the customer
        double distanceToRestaurant = getDistance(courierLocation, restaurantLocation);
        double distanceToCustomer = getDistance(restaurantLocation, customerLocation);
        return distanceToRestaurant + distanceToCustomer;
    }
	private double getDistance(double[] loc1, double[] loc2) {
        // Implementation of distance calculation using Euclidean distance formula
        return Math.sqrt(Math.pow(loc1[0] - loc2[0], 2) + Math.pow(loc1[1] - loc2[1], 2));
    }

}