package core.policies;

import java.util.ArrayList;

import core.orders.Order;
import core.users.Courierr;

public class FairOcuppationDelivery implements DeliveryPolicy{

	@Override
	public Courierr assignCourrier(ArrayList<Courierr> courriers, Order order) {
		Courierr selectedCourier = null;
        int leastDeliveries = Integer.MAX_VALUE;

        for (Courierr courier : courriers) {
        	if (courier.isOnDuty()) {
        		int deliveries = courier.getCompletedDeliveries();
                if (deliveries < leastDeliveries) {
                    leastDeliveries = deliveries;
                    selectedCourier = courier;
        	}
            
            }
        }
        return selectedCourier;
    }
	}


