package test.core.users;

import core.orders.Order;
import core.users.Courier;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class CourierTest {

    private Courier courier;

    @BeforeEach
    public void setUp() {
        courier = new Courier("courier", "password");
    }

    @Test
    public void testDefaultConstructor() {
        assertEquals("courier", courier.getUsername());
        assertEquals("password".hashCode(), courier.getHashedPassword());
        assertEquals(0, courier.getCompletedDeliveries());
        assertFalse(courier.isOnDuty());
        assertNotNull(courier.getDeliveredOrders());
        assertTrue(courier.getDeliveredOrders().isEmpty());
    }

    @Test
    public void testParameterizedConstructor() {
        double[] position = {1.0, 2.0};
        Courier courier = new Courier("courier", "password", position, true, 5);

        assertEquals("courier", courier.getUsername());
        assertEquals("password".hashCode(), courier.getHashedPassword());
        assertArrayEquals(position, courier.getPosition());
        assertTrue(courier.isOnDuty());
        assertEquals(5, courier.getCompletedDeliveries());
        assertNull(courier.getDeliveredOrders());
    }

    @Test
    public void testToString() {
        double[] position = {1.0, 2.0};
        courier.setPosition(position);
        courier.setOnDuty(true);
        courier.increaseCounter();

        String expected = "Courier [completedDeliveries=1, position=1.0,2.0, phoneNumber=null, deliveredOrders=[], onDuty=true]";
        assertEquals(expected, courier.toString());
    }

    @Test
    public void testGetPosition() {
        double[] position = {1.0, 2.0};
        courier.setPosition(position);
        assertArrayEquals(position, courier.getPosition());
    }

    @Test
    public void testSetPosition() {
        double[] position = {1.0, 2.0};
        courier.setPosition(position);
        assertArrayEquals(position, courier.getPosition());
    }

    @Test
    public void testIsOnDuty() {
        assertFalse(courier.isOnDuty());
        courier.setOnDuty(true);
        assertTrue(courier.isOnDuty());
    }

    @Test
    public void testSetOnDuty() {
        assertFalse(courier.isOnDuty());
        courier.setOnDuty(true);
        assertTrue(courier.isOnDuty());
    }

    @Test
    public void testGetPhoneNumber() {
        assertNull(courier.getPhoneNumber());
    }

    @Test
    public void testGetDeliveredOrders() {
        assertNotNull(courier.getDeliveredOrders());
        assertTrue(courier.getDeliveredOrders().isEmpty());
    }

    @Test
    public void testGetCompletedDeliveries() {
        assertEquals(0, courier.getCompletedDeliveries());
        courier.increaseCounter();
        assertEquals(1, courier.getCompletedDeliveries());
    }

    @Test
    public void testIncreaseCounter() {
        assertEquals(0, courier.getCompletedDeliveries());
        courier.increaseCounter();
        assertEquals(1, courier.getCompletedDeliveries());
    }
}
