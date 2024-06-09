package core.policies;

import core.orders.Order;

public interface DeliveryPolicy {
	public void assignCourrier(Order order);

}
