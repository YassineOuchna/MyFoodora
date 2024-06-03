package core.users;
import java.util.Date;

public class Manager extends User{

	public Manager(String newUsername, String password) {
		super(newUsername, password);
	}
	
	public void addUser(User u) {}
	public void removeUser(User u) {}
	public void activateUser(User u) {}
	public void desactivateUser(User u) {}
	public void setServiceFee(double fee) {}
	public double totalProfit(Date start,Date end) {return 0;}
	public double totalIncome(Date start, Date end) {return 0;}
	public double averageIncomeByCustomer(Date start, Date end) {return 0;}
	
	
	
	
	//public void setDeliveryPolicy(DeliveryPolicy policy) {}
	
	
}
