package test.core.users;

import core.MyFoodora;
import core.fidelityCards.BasicFidelityCard;
import core.fidelityCards.LotteryFidelityCard;
import core.fidelityCards.PointFidelityCard;
import core.orders.Order;
import core.users.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerTest {

    private Customer customer;

    @BeforeEach
    public void setUp() {
        customer = new Customer("customer", "password");
    }

    @Test
    public void testDefaultConstructor() {
        assertEquals("customer", customer.getUsername());
        assertEquals("password".hashCode(), customer.getHashedPassword());
        assertFalse(customer.isNotificationsOn());
        assertNotNull(customer.getAddress());
        assertArrayEquals(new double[]{0, 0}, customer.getAddress());
        assertNotNull(customer.getReceivedEmails());
        assertTrue(customer.getReceivedEmails().isEmpty());
        assertTrue(customer.getFidelityCard() instanceof BasicFidelityCard);
    }

    @Test
    public void testParameterizedConstructor() {
        double[] address = {1.0, 2.0};
        Customer customer = new Customer("customer", "password");
        customer.setAddress(address);

        assertEquals("customer", customer.getUsername());
        assertEquals("password".hashCode(), customer.getHashedPassword());
        assertFalse(customer.isNotificationsOn());
        assertArrayEquals(address, customer.getAddress());
        assertNotNull(customer.getReceivedEmails());
        assertTrue(customer.getReceivedEmails().isEmpty());
        assertTrue(customer.getFidelityCard() instanceof BasicFidelityCard);
    }


    @Test
    public void testIsNotificationsOn() {
        assertFalse(customer.isNotificationsOn());
        customer.setNotificationsOn(true);
        assertTrue(customer.isNotificationsOn());
    }

    @Test
    public void testSetNotificationsOn() {
        assertFalse(customer.isNotificationsOn());
        customer.setNotificationsOn(true);
        assertTrue(customer.isNotificationsOn());
    }

    @Test
    public void testGetAddress() {
        assertNotNull(customer.getAddress());
        assertArrayEquals(new double[]{0, 0}, customer.getAddress());
    }

    @Test
    public void testSetAddress() {
        double[] address = {1.0, 2.0};
        customer.setAddress(address);
        assertArrayEquals(address, customer.getAddress());
    }

    @Test
    public void testRegisterFidelityCard() {
        customer.registerFidelityCard(new PointFidelityCard());
        assertTrue(customer.getFidelityCard() instanceof PointFidelityCard);

        customer.registerFidelityCard(new PointFidelityCard());
        assertTrue(customer.getFidelityCard() instanceof PointFidelityCard);
    }

    @Test
    public void testUnregisterFidelityCard() {
        customer.registerFidelityCard(new LotteryFidelityCard());
        assertTrue(customer.getFidelityCard() instanceof LotteryFidelityCard);

        customer.unregisterFidelityCard();
        assertTrue(customer.getFidelityCard() instanceof BasicFidelityCard);
    }

    @Test
    public void testReceiveMail() {
        customer.receiveMail("Test Mail");
        assertEquals(1, customer.getReceivedEmails().size());
        assertTrue(customer.getReceivedEmails().contains("Test Mail"));
    }

    @Test
    public void testGetReceivedEmails() {
        assertNotNull(customer.getReceivedEmails());
        assertTrue(customer.getReceivedEmails().isEmpty());
    }

    @Test
    public void testGetFidelityCard() {
        assertTrue(customer.getFidelityCard() instanceof BasicFidelityCard);
    }

    @Test
    public void testGetUserType() {
        assertEquals("customer", customer.getUserType());
    }

    @Test
    public void testDisplayFidelityInfo() {
        // Test if method runs without error
        customer.displayFidelityInfo();
    }

    @Test
    public void testGetHistoryOfOrders() {
        // Test if method runs without error
        ArrayList<Order> historyOfOrders = customer.getHistoryOfOrders(MyFoodora.getInstance());
        assertNotNull(historyOfOrders);
        assertTrue(historyOfOrders.isEmpty());
    }
}

