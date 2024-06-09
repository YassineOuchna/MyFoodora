package core.policies;

import core.exceptions.CourierNotFoundException;
import core.orders.Order;

public interface DeliveryPolicy {
	public void assignCourrier(Order order) throws CourierNotFoundException;
}
