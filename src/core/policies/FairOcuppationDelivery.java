package core.policies;

import java.util.ArrayList;

import core.orders.Order;
import core.users.Courrier;

public class FairOcuppationDelivery implements DeliveryPolicy{

	@Override
	public Courrier assignCourrier(ArrayList<Courrier> courriers, Order order) {
		Courrier selectedCourier = null;
        int leastDeliveries = Integer.MAX_VALUE;

        for (Courrier courier : courriers) {
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


