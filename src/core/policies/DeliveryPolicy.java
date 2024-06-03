package core.policies;
import java.util.ArrayList;
import core.users.*;
import core.orders.*;

public interface DeliveryPolicy {
	public Courrier assignCourrier(ArrayList<Courrier> courriers, Order order);

}
