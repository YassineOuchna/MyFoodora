package core.users;
import java.util.Date;
import core.policies.*;

public class Manager extends User{
	
	/*
	 * The manager is the one who sets the policy (i.e strategy) for the delivery and the profit
	 */
	private DeliveryPolicy deliveryPolicy;
	private TargetProfitPolicy profitPolicy;

	

	public Manager(String newUsername, String password) {
		super(newUsername, password);
	}
	
	public Manager(String newUsername, String password, TargetProfitPolicy targetProfitPolicy) {
        super(newUsername, password);
        this.setProfitPolicy(targetProfitPolicy);
    }
	
	public Manager(String newUsername, String password, TargetProfitPolicy targetProfitPolicy, DeliveryPolicy deliveryPolicy) {
        super(newUsername, password);
        this.setProfitPolicy(targetProfitPolicy);
        this.deliveryPolicy=deliveryPolicy;
    }
	
	public DeliveryPolicy getDeliveryPolicy() {
		return deliveryPolicy;
	}

	public void setDeliveryPolicy(DeliveryPolicy deliveryPolicy) {
		this.deliveryPolicy = deliveryPolicy;
	}
	public void addUser(User u) {}
	public void removeUser(User u) {}
	public void activateUser(User u) {}
	public void desactivateUser(User u) {}
	public void setServiceFee(double fee) {}
	public double totalProfit(Date start,Date end) {return 0;}
	public double totalIncome(Date start, Date end) {return 0;}
	public double averageIncomeByCustomer(Date start, Date end) {return 0;}
	
	
	
	
	
	public TargetProfitPolicy getProfitPolicy() {
		return profitPolicy;
	}
	public void setProfitPolicy(TargetProfitPolicy profitPolicy) {
		this.profitPolicy = profitPolicy;
	}
	
	/*
	 * Computing profit parameters
	 */
	public double calculateDeliveryCost(double targetProfit, int totalIncome, double serviceFee, double markup) {
        if (profitPolicy == null) {
            throw new IllegalStateException("No target profit policy set.");
        }
        return profitPolicy.computeDeliveryCost(targetProfit, totalIncome, serviceFee, markup);
    }

    public double calculateServiceFee(double targetProfit, int totalIncome, double deliveryCost, double markup) {
        if (profitPolicy == null) {
            throw new IllegalStateException("No target profit policy set.");
        }
        return profitPolicy.computeServiceFee(targetProfit, totalIncome, deliveryCost, markup);
    }

    public double calculateMarkup(double targetProfit, int totalIncome, double deliveryCost, double serviceFee) {
        if (profitPolicy == null) {
            throw new IllegalStateException("No target profit policy set.");
        }
        return profitPolicy.computeMarkup(targetProfit, totalIncome, deliveryCost, serviceFee);
    }

		
	
}
