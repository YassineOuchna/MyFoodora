package core.policies;

import core.MyFoodora;
import core.exceptions.CourierNotFoundException;
import core.orders.Order;
import core.users.Courier;

public class FairOcuppationDelivery implements DeliveryPolicy{

	@Override
	public void assignCourrier(Order order) throws CourierNotFoundException {
		Courier selectedCourier = null;
        int leastDeliveries = Integer.MAX_VALUE;

        for (Courier courier : MyFoodora.getInstance().getCourriers()) {
        	if (courier.isOnDuty()) {
        		int deliveries = courier.getCompletedDeliveries();
                if (deliveries < leastDeliveries) {
                    leastDeliveries = deliveries;
                    selectedCourier = courier;
        	}
            
            }
        }
        if (selectedCourier == null) {
        	throw new CourierNotFoundException("Available courier not found");
        } else {
        	order.setCourier(selectedCourier);
        	MyFoodora app = MyFoodora.getInstance();
        	app.addCompletedOrder(order);
        }
         
    }
	}


