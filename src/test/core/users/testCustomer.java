package test.core.users;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import core.users.Customer;
import core.users.User;

class testCustomer {

	@Test
	void tesingConstructor() {
		Customer sam = new Customer("Sam", "password");
		Customer john = new Customer("John", "password");
		assertEquals(0, sam.getAddress()[0]);
		assertEquals(0, sam.getAddress()[1]);
		assertEquals(john.getHashedPassword(), sam.getHashedPassword());
		assertEquals(1, sam.getId());
		assertEquals(2, john.getId());

	}

}
