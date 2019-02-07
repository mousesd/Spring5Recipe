package net.homenet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.annotation.ServiceActivator;

public class CustomerDeleteServiceActivator {
    private static final Logger logger = LoggerFactory.getLogger(CustomerDeleteServiceActivator.class);

    @ServiceActivator
    public Customer deleteCustomer(String customerId) {
        System.out.println("The id of the customer to delete is " + customerId);

        Customer customer = new Customer();
        customer.setId(Long.valueOf(customerId));
        return customer;
    }
}
