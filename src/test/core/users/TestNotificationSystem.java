package test.core.users;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

import core.MyFoodora;
import core.exceptions.SubscriberAlreadyExistsException;
import core.exceptions.SubscriberNotFoundException;
import core.food.Meal;
import core.food.Dish;
import core.users.Customer;
import core.users.Restaurant;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestNotificationSystem {
	
	MyFoodora app = MyFoodora.getInstance();
	
	@BeforeEach
	void setUp() {
	}

	@Test
    void testAddSubscriber() throws SubscriberAlreadyExistsException {
        Restaurant restaurant = new Restaurant("Restaurant1", "password1");
        Customer subscriber1 = new Customer("Customer1", "password1");
        //Customer subscriber2 = new Customer("Customer2", "password2");

        // Testing adding a subscriber
        app.addSubscriber(subscriber1);
        assertTrue(app.getSubscribedCustomers().contains(subscriber1));
        
        // Test adding a subscriber that already exists
        assertThrows(SubscriberAlreadyExistsException.class, () -> app.addSubscriber(subscriber1));
    }
	
	@Test
    void testRemoveSubscriber() throws SubscriberNotFoundException, SubscriberAlreadyExistsException {
        Restaurant restaurant = new Restaurant("Restaurant1", "password1");
        Customer subscriber1 = new Customer("Customer3", "password1");
        Customer subscriber2 = new Customer("Customer4", "password2");

        // Adding subscribers
        app.addSubscriber(subscriber1);
        app.addSubscriber(subscriber2);
        
        // Testing removal of a subscriber
        app.removeSubscriber(subscriber1);
        assertFalse(app.getSubscribedCustomers().contains(subscriber1));
     
        // Test remove a subscriber that doesn't exist
        assertThrows(SubscriberNotFoundException.class, () -> app.removeSubscriber(subscriber1));
        
    }
	@Test
    void testNotifySubscribers() throws SubscriberAlreadyExistsException {
        Restaurant restaurant = new Restaurant("Restaurant1", "password1");
        Customer subscriber1 = new Customer("Customer5", "password1");
        Customer subscriber2 = new Customer("Customer6", "password2");

        // Adding subscribers
        app.addSubscriber(subscriber1);
        app.addSubscriber(subscriber2);

        // Adding a special Offer
        Meal specialOffer = new Meal("Special Offer", new ArrayList<Dish>());
        restaurant.setSpecialOffer(specialOffer);


        // Verifying that customers not subscribed are not notified
        assertFalse(subscriber1.getReceivedEmails().contains("New Special Offer available from restaurant" + restaurant.getSurname()+" : "+ specialOffer));
        assertFalse(subscriber2.getReceivedEmails().contains("New Special Offer available from restaurant" + restaurant.getSurname()+" : "+ specialOffer));
        
        subscriber1.setNotificationsOn(true);
        subscriber2.setNotificationsOn(true);

        // Verifying that subscribers are notified
        Meal specialOffer2 = new Meal("Special Offer", new ArrayList<Dish>());
        restaurant.setSpecialOffer(specialOffer2);
        assertTrue(subscriber1.getReceivedEmails().contains("New Special Offer available from restaurant" + restaurant.getSurname()+" : "+ specialOffer2));
        assertTrue(subscriber2.getReceivedEmails().contains("New Special Offer available from restaurant" + restaurant.getSurname()+" : "+ specialOffer2));
    }

}
