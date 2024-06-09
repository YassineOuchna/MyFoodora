package test.core.policies;

import core.MyFoodora;
import core.exceptions.CourierNotFoundException;
import core.exceptions.UserNotFoundException;
import core.orders.Order;
import core.policies.FairOcuppationDelivery;
import core.users.Courier;
import core.users.Customer;
import core.users.Restaurant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FairOccupationDeliveryPolicyTest {

    private FairOcuppationDelivery fairOcuppationDelivery;
    private MyFoodora app;
    private Restaurant restaurant;
    private Customer customer;
    private Courier courier1;
    private Courier courier2;
    private Courier courier3;

    @BeforeEach
    public void setUp() throws CourierNotFoundException, UserNotFoundException {
        app = MyFoodora.getInstance();
        fairOcuppationDelivery = new FairOcuppationDelivery();

        // Initialize a restaurant
        restaurant = new Restaurant("restaurant", "password");
        app.addUser(restaurant);

        // Initialize a customer
        customer = new Customer("customer", "password");
        app.addUser(customer);

        // Initialize couriers
        courier1 = new Courier("courier1", "password");
        courier2 = new Courier("courier2", "password");
        courier3 = new Courier("courier3", "password");

        // Set couriers on duty
        courier1.setOnDuty(true);
        courier2.setOnDuty(true);
        courier3.setOnDuty(true);
        
        for (Courier c : app.getCourriers()) {
        	app.removeUser(c);
        }

        app.addUser(courier1);
        app.addUser(courier2);
        app.addUser(courier3);
        courier1.setOnDuty(true);
        courier2.setOnDuty(true);
        courier3.setOnDuty(true);

        // Initialize orders and complete them to set delivery counts
        Order order1 = new Order(restaurant, "order1", customer);
        Order order2 = new Order(restaurant, "order2", customer);
        Order order3 = new Order(restaurant, "order3", customer);

        order1.endOrder();
        order2.endOrder();
        order3.endOrder();


        app.addCompletedOrder(order1);
        app.addCompletedOrder(order2);
        app.addCompletedOrder(order3);
    }

    @Test
    public void testAssignCourier() {
        Order newOrder = new Order(restaurant, "newOrder", customer);

        try {
            fairOcuppationDelivery.assignCourrier(newOrder);
        } catch (CourierNotFoundException e) {
            fail("Courier not found");
        }

        Courier assignedCourier = newOrder.getCourier();
        assertNotNull(assignedCourier);

        // Verify that the courier with the least deliveries was assigned
        int deliveriesCourier1 = courier1.getCompletedDeliveries();
        int deliveriesCourier2 = courier2.getCompletedDeliveries();
        int deliveriesCourier3 = courier3.getCompletedDeliveries();

        assertEquals(0, deliveriesCourier1);
        assertEquals(0, deliveriesCourier2);
        assertEquals(0, deliveriesCourier3);
        assertEquals(courier1, assignedCourier);
    }

    @Test
    public void testAssignCourierThrowsExceptionWhenNoCourierAvailable() {
        // Set all couriers off duty
        courier1.setOnDuty(false);
        courier2.setOnDuty(false);
        courier3.setOnDuty(false);

        Order newOrder = new Order(restaurant, "newOrder", customer);

        assertThrows(CourierNotFoundException.class, () -> {
            fairOcuppationDelivery.assignCourrier(newOrder);
        });
    }
}

