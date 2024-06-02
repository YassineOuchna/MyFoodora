package test.core.users;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


import core.exceptions.SubscriberAlreadyExistsException;
import core.exceptions.SubscriberNotFoundException;
import core.food.Meal;
import core.food.Dish;
import core.users.Customer;
import core.users.Restaurant;

import org.junit.jupiter.api.Test;

class TestNotificationSystem {

	@Test
    void testAddSubscriber() throws SubscriberAlreadyExistsException {
        Restaurant restaurant = new Restaurant("Restaurant1", "password1");
        Customer subscriber1 = new Customer("Customer1", "password1");
        //Customer subscriber2 = new Customer("Customer2", "password2");

        // Testing adding a subscriber
        restaurant.addSubscriber(subscriber1);
        assertTrue(restaurant.getSubscribedCustomers().contains(subscriber1));
        
        // Test adding a subscriber that already exists
        assertThrows(SubscriberAlreadyExistsException.class, () -> restaurant.addSubscriber(subscriber1));
    }
	
	@Test
    void testRemoveSubscriber() throws SubscriberNotFoundException, SubscriberAlreadyExistsException {
        Restaurant restaurant = new Restaurant("Restaurant1", "password1");
        Customer subscriber1 = new Customer("Customer3", "password1");
        Customer subscriber2 = new Customer("Customer4", "password2");

        // Adding subscribers
        restaurant.addSubscriber(subscriber1);
        restaurant.addSubscriber(subscriber2);
        
        // Testing removal of a subscriber
        restaurant.removeSubscriber(subscriber1);
        assertFalse(restaurant.getSubscribedCustomers().contains(subscriber1));
     
        // Test remove a subscriber that doesn't exist
        assertThrows(SubscriberNotFoundException.class, () -> restaurant.removeSubscriber(subscriber1));
        
    }
	@Test
    void testNotifySubscribers() throws SubscriberAlreadyExistsException {
        Restaurant restaurant = new Restaurant("Restaurant1", "password1");
        Customer subscriber1 = new Customer("Customer5", "password1");
        Customer subscriber2 = new Customer("Customer6", "password2");

        // Adding subscribers
        restaurant.addSubscriber(subscriber1);
        restaurant.addSubscriber(subscriber2);

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
