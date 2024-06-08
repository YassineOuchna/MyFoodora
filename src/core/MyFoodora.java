package core;

import core.policies.DeliveryPolicy;
import core.policies.TargetProfitPolicy;

public class MyFoodora {
	private static DeliveryPolicy deliveryPolicy;
	private static TargetProfitPolicy profitPolicy;
	
	public static DeliveryPolicy getDeliveryPolicy() {
		return deliveryPolicy;
	}
	public static void setDeliveryPolicy(DeliveryPolicy deliveryPolicy) {
		MyFoodora.deliveryPolicy = deliveryPolicy;
	}
	public static TargetProfitPolicy getProfitPolicy() {
		return profitPolicy;
	}
	public static void setProfitPolicy(TargetProfitPolicy profitPolicy) {
		MyFoodora.profitPolicy = profitPolicy;
	}
}
