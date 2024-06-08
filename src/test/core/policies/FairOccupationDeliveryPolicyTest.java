package test.core.policies;

import core.orders.Order;
import core.policies.FairOcuppationDelivery;
import core.users.Courierr;
import core.users.Restaurant;
import core.users.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class FairOccupationDeliveryPolicyTest {

    private FairOcuppationDelivery fairOccupationDeliveryPolicy;
    private ArrayList<Courierr> courriers;
    private Restaurant restaurant;
    private Customer customer;
    private Order order;

    @BeforeEach
    void setUp() {
        fairOccupationDeliveryPolicy = new FairOcuppationDelivery();
        courriers = new ArrayList<>();
        
        // Set up restaurant and customer locations
        restaurant = new Restaurant("RestaurantUser", "password");
        restaurant.setLocation(new double[]{0, 0});
        
        customer = new Customer("CustomerUser", "password");
        customer.setAddress(new double[]{10, 10});

        order = new Order(restaurant, "Order1",customer);
        

        // Add couriers
        courriers.add(new Courierr("Courier1","password", new double[]{1, 1}, true, 5));
        courriers.add(new Courierr("Courier2","password", new double[]{2, 2}, true, 3));
        courriers.add(new Courierr("Courier3","password", new double[]{8, 8}, true, 2));
    }

    @Test
    void testAssignCourier() {
        Courierr assignedCourier = fairOccupationDeliveryPolicy.assignCourrier(courriers, order);
        assertNotNull(assignedCourier);
        assertEquals("Courier3", assignedCourier.getUsername());
    }

    @Test
    void testAssignCourierWhenNoCouriersOnDuty() {
        courriers.forEach(courier -> courier.setOnDuty(false));
        Courierr assignedCourier = fairOccupationDeliveryPolicy.assignCourrier(courriers, order);
        assertNull(assignedCourier);
    }
}

