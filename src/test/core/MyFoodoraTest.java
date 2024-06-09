package test.core;
import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import core.MyFoodora;
import core.exceptions.*;
import core.orders.Order;
import core.policies.FairOcuppationDelivery;
import core.users.*;

class MyFoodoraTest {

    private MyFoodora app;
    private Order order1;
    private Order order2;

    @BeforeEach
    void setUp() throws ItemNotInMenuException, InvalidItemDescription, CourierNotFoundException {
        app = MyFoodora.getInstance();
        app.getUsers().clear();
        app.getCompletedOrders().clear();
        app.getSubscribedCustomers().clear();
        app.setMarkupPercentage(0.1);
        app.setDeliveryCost(5.0);
        app.setServiceFee(2.0);
        Restaurant r = new Restaurant("heh", "pass");
        order1 = new Order(r, "order1",new Customer("john.doe", "password", "John Doe", "john@example.com"));
        order2 = new Order(r, "order2", new Customer("john.doe", "password", "John Doe", "john@example.com"));
		String [] bigMacDesc = new String[] {"Big Mac",
				"maindish", 
				"Standard",
				"1",
				"150"
		};
		String [] ChickenNuggiesDesc = new String[] {"Chicken Nuggies",
				"Starter", 
				"Standard",
				"1",
				"150"
		};
        r.addMenuItem("dish", bigMacDesc);
        r.addMenuItem("dish", ChickenNuggiesDesc);
        order1.addItem2Order(r.getMenu().getItem("Big Mac"));
        order2.addItem2Order(r.getMenu().getItem("Chicken Nuggies"));
        
        // Adding couriers
        Courier c = new Courier("courier", "pass");
        app.addUser(c);
        c.setOnDuty(true);
        app.setDeliveryPolicy(new FairOcuppationDelivery());
        order1.endOrder();
        order2.endOrder();
    }

    @Test
    void testSingleton() {
        MyFoodora anotherInstance = MyFoodora.getInstance();
        assertSame(app, anotherInstance);
    }

    @Test
    void testAddUser() {
        User user = new Customer("john.doe", "password", "John Doe", "john@example.com");

        app.addUser(user);

        assertTrue(app.getUsers().contains(user));
        assertTrue(app.getHashedPasswords().containsKey(user.getId()));
        assertEquals("password".hashCode(), app.getHashedPasswords().get(user.getId()));
    }

    @Test
    void testRemoveUser() throws UserNotFoundException {
        User user = new Customer("john.doe", "password", "John Doe", "john@example.com");

        app.addUser(user);
        app.removeUser(user);

        assertFalse(app.getUsers().contains(user));
        assertFalse(app.getHashedPasswords().containsKey(1));
    }

    @Test
    void testComputeTotalIncome() {

    	// Items have a total of 300e
        assertEquals(300.0, app.computeTotalIncome());
    }


    @Test
    void testComputeTotalProfit() {

    	// two dishes have a total of 300
        assertEquals(24, app.computeTotalProfit()); 
    }

    @Test
    void testAddSubscriber() throws SubscriberAlreadyExistsException {
        SubscriberObserver subscriber = new Customer("john.doe", "password", "John Doe", "john@example.com");

        app.addSubscriber(subscriber);

        assertTrue(app.getSubscribedCustomers().contains(subscriber));
    }

    @Test
    void testAddDuplicateSubscriber() {
        SubscriberObserver subscriber = new Customer("john.doe", "password", "John Doe", "john@example.com");

        assertThrows(SubscriberAlreadyExistsException.class, () -> {
            app.addSubscriber(subscriber);
            app.addSubscriber(subscriber);
        });
    }

    @Test
    void testRemoveSubscriber() throws SubscriberNotFoundException, SubscriberAlreadyExistsException {
        SubscriberObserver subscriber = new Customer("john.doe", "password", "John Doe", "john@example.com");

        app.addSubscriber(subscriber);
        app.removeSubscriber(subscriber);

        assertFalse(app.getSubscribedCustomers().contains(subscriber));
    }

    @Test
    void testRemoveNonExistentSubscriber() {
        SubscriberObserver subscriber = new Customer("john.doe", "password", "John Doe", "john@example.com");

        assertThrows(SubscriberNotFoundException.class, () -> {
            app.removeSubscriber(subscriber);
        });
    }

}
