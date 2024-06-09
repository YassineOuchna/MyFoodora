package core.policies;

import core.MyFoodora;
import core.orders.Order;
import core.users.Courier;

public class FairOcuppationDelivery implements DeliveryPolicy{

	@Override
	public void assignCourrier(Order order) {
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
         order.setCourier(selectedCourier);
    }
	}


