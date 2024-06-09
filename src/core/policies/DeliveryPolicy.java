package core.policies;
import java.util.ArrayList;
import core.users.*;
import core.orders.*;

public interface DeliveryPolicy {
	public Courier assignCourrier(ArrayList<Courier> courriers, Order order);

}
