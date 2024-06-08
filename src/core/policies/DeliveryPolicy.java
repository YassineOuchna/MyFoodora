package core.policies;
import java.util.ArrayList;
import core.users.*;
import core.orders.*;

public interface DeliveryPolicy {
	public Courierr assignCourrier(ArrayList<Courierr> courriers, Order order);

}
