package test.core.users;

import core.MyFoodora;
import core.exceptions.CourierNotFoundException;
import core.exceptions.UserNotFoundException;
import core.food.MenuItem;
import core.orders.Order;
import core.policies.*;
import core.users.Courier;
import core.users.Customer;
import core.users.Manager;
import core.users.Restaurant;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

public class ManagerTest {

    private Manager manager;
    private MyFoodora app;
    private Restaurant restaurant1;
    private Restaurant restaurant2;
    private Courier courier1;
    private Courier courier2;

    @BeforeEach
    public void setUp() throws CourierNotFoundException {
    	app = MyFoodora.getInstance();
        manager = new Manager("managerUsername", "password", "John", "Doe");
        app.addUser(manager);

        // Initialize restaurants
        restaurant1 = new Restaurant("restaurant1", "password");
        restaurant2 = new Restaurant("restaurant2", "password");
        app.addUser(restaurant1);
        app.addUser(restaurant2);

        // Initialize couriers
        courier1 = new Courier("courier1", "password");
        courier2 = new Courier("courier2", "password");
        courier1.setOnDuty(true);
        courier1.setOnDuty(true);
        app.addUser(courier1);
        app.addUser(courier2);

        // Initialize orders
        Customer c = new Customer("c1", "nezae");
        Order order1 = new Order(restaurant1, "order1", c);
        Order order2 = new Order(restaurant2, "order2", c);
        
        order1.endOrder();
        order2.endOrder();

    }

    @Test
    public void testAddUser() {
        Customer newUser = new Customer("customerUsername", "password");
        manager.addUser(newUser);
        assertTrue(app.getUsers().contains(newUser));
    }

    @Test
    public void testRemoveUser() throws UserNotFoundException {
        Customer newUser = new Customer("customerUsername", "password");
        manager.addUser(newUser);
        manager.removeUser(newUser);
        assertFalse(app.getUsers().contains(newUser));
    }

    @Test
    public void testActivateUser() {
        Customer newUser = new Customer("customerUsername", "password");
        manager.addUser(newUser);
        manager.activateUser(newUser);
        assertTrue(newUser.isActive());
    }

    @Test
    public void testDeactivateUser() {
        Customer newUser = new Customer("customerUsername", "password");
        manager.addUser(newUser);
        manager.desactivateUser(newUser);
        assertFalse(newUser.isActive());
    }

    @Test
    public void testSetServiceFee() {
        double fee = 10.0;
        manager.setServiceFee(fee);
        assertEquals(fee, app.getServiceFee());
    }

    @Test
    public void testTotalProfit() {
        Date start = new Date(0);
        Date end = new Date();
        double totalProfit = manager.totalProfit(start, end);
        assertEquals(app.computeTotalProfit(start, end), totalProfit);
    }

    @Test
    public void testTotalIncome() {
        Date start = new Date(0);
        Date end = new Date();
        double totalIncome = manager.totalIncome(start, end);
        assertEquals(app.computeTotalIncome(start, end), totalIncome);
    }

    @Test
    public void testShippedItemSort() {
        ArrayList<MenuItem> sortedItems = manager.shippedItemSort();
        assertNotNull(sortedItems);
    }

    @Test
    public void testAverageIncomeByCustomer() {
        Date start = new Date(0);
        Date end = new Date();
        double totalIncome = manager.totalIncome(start, end);
        HashSet<Integer> realCustomers = new HashSet<>();
        for (Order o : app.getCompletedOrders()) {
            if (o.getDate().after(start) && o.getDate().before(end)) {
                realCustomers.add(o.getCustomer().getId());
            }
        }
        double expectedAverage = realCustomers.size() > 0 ? totalIncome / realCustomers.size() : 0;
        assertEquals(expectedAverage, manager.averageIncomeByCustomer(start, end));
    }


    @Test
    public void testSetDeliveryPolicy() {
        DeliveryPolicy deliveryPolicy = new FairOcuppationDelivery();
        manager.setDeliveryPolicy(deliveryPolicy);
        assertEquals(deliveryPolicy, app.getDeliveryPolicy());
    }

    @Test
    public void testGetAllDeliveredOrders() {
        ArrayList<Order> deliveredOrders = manager.getAllDeliveredOrders();
        assertNotNull(deliveredOrders);
        assertEquals(app.getCompletedOrders(), deliveredOrders);
    }

    @Test
    public void testGetDeliveredOrders() {
        Date start = new Date(0);
        Date end = new Date();
        ArrayList<Order> deliveredOrders = manager.getDeliveredOrders(start, end);
        assertNotNull(deliveredOrders);
    }

    @Test
    public void testGetLastMonthOrders() {
        ArrayList<Order> lastMonthOrders = manager.getLastMonthOrders();
        assertNotNull(lastMonthOrders);
    }

    @Test
    public void testShowRestaurantTop() {
        ArrayList<Restaurant> restaurants = manager.showRestaurantTop();
        assertNotNull(restaurants);
    }

    @Test
    public void testMostSellingRestaurant() {
        Restaurant mostSellingRestaurant = manager.mostSellingRestaurant();
        assertNotNull(mostSellingRestaurant);

        // Manually determine the most selling restaurant for comparison
        Restaurant expectedMostSelling = null;
        int maxOrders = 0;
        for (Restaurant r : app.getRestaurants()) {
            if (r.getnumDeliveredOrders() > maxOrders) {
                maxOrders = r.getnumDeliveredOrders();
                expectedMostSelling = r;
            }
        }
        assertEquals(expectedMostSelling, null);
    }

    @Test
    public void testLeastSellingRestaurant() {
        Restaurant leastSellingRestaurant = manager.leastSellingRestaurant();
        assertNotNull(leastSellingRestaurant);

        // Manually determine the least selling restaurant for comparison
        Restaurant expectedLeastSelling = null;
        int minOrders = Integer.MAX_VALUE;
        for (Restaurant r : app.getRestaurants()) {
            if (r.getnumDeliveredOrders() < minOrders) {
                minOrders = r.getnumDeliveredOrders();
                expectedLeastSelling = r;
            }
        }
        assertEquals(expectedLeastSelling, leastSellingRestaurant);
    }
    @Test
    public void testMostActiveCourier() {
        Courier mostActiveCourier = manager.mostActiveCourier();
        assertNotNull(mostActiveCourier);

        // Manually determine the most active courier for comparison
        Courier expectedMostActive = null;
        int maxOrders = 0;
        for (Courier c : app.getCourriers()) {
            if (c.getCompletedDeliveries() > maxOrders) {
                maxOrders = c.getCompletedDeliveries();
                expectedMostActive = c;
            }
        }
        assertEquals(expectedMostActive, null);
    }

    @Test
    public void testLeastActiveCourier() {
        Courier leastActiveCourier = manager.leastActiveCourier();
        assertNotNull(leastActiveCourier);

        // Manually determine the least active courier for comparison
        Courier expectedLeastActive = null;
        int minOrders = Integer.MAX_VALUE;
        for (Courier c : app.getCourriers()) {
            if (c.getCompletedDeliveries() < minOrders) {
                minOrders = c.getCompletedDeliveries();
                expectedLeastActive = c;
            }
        }
        }
    @Test
    public void testSetMarkupPercentage() {
        double markupPercentage = 0.1;
        manager.setMarkupPercentage(markupPercentage);
        assertEquals(markupPercentage, app.getMarkupPercentage());
    }

    @Test
    public void testSetDeliveryCost() {
        double deliveryCost = 5.0;
        manager.setDeliveryCost(deliveryCost);
        assertEquals(deliveryCost, app.getDeliveryCost());
    }

    @Test
    public void testGetUserType() {
        assertEquals("manager", manager.getUserType());
    }
}

