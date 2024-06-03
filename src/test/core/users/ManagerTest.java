package test.core.users;

import core.orders.Order;
import core.policies.DeliveryPolicy;
import core.policies.FastestDelivery;
import core.policies.FairOcuppationDelivery;
import core.users.Courrier;
import core.users.Manager;
import core.users.Restaurant;
import core.users.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ManagerTest {

    private Manager manager;
    private Restaurant restaurant;
    private Customer customer;
    private Order order;
    private ArrayList<Courrier> courriers;

    @BeforeEach
    void setUp() {
        manager = new Manager("ManagerUser", "password");

        // Set up restaurant and customer locations
        restaurant = new Restaurant("RestaurantUser", "password");
        restaurant.setLocation(new double[]{0, 0});
        
        customer = new Customer("CustomerUser", "password");
        customer.setAddress(new double[]{10, 10});

        order = new Order(restaurant, "Order1",customer);

        courriers = new ArrayList<>();
        courriers.add(new Courrier("Courier1","pass", new double[]{1, 1}, true, 5));
        courriers.add(new Courrier("Courier2","pass", new double[]{2, 2}, true, 3));
        courriers.add(new Courrier("Courier3","pass", new double[]{8, 8}, true, 2));
    }

    @Test
    void testSetAndUseFastestDeliveryPolicy() {
        DeliveryPolicy fastestDelivery = new FastestDelivery();
        manager.setDeliveryPolicy(fastestDelivery);

        Courrier assignedCourier = manager.getDeliveryPolicy().assignCourrier(courriers, order);
        assertNotNull(assignedCourier);
        assertEquals("Courier1", assignedCourier.getUsername());
    }

    @Test
    void testSetAndUseFairOccupationDeliveryPolicy() {
        DeliveryPolicy fairOccupationDelivery = new FairOcuppationDelivery();
        manager.setDeliveryPolicy(fairOccupationDelivery);

        Courrier assignedCourier = manager.getDeliveryPolicy().assignCourrier(courriers, order);
        assertNotNull(assignedCourier);
        assertEquals("Courier3", assignedCourier.getUsername());
    }
}

