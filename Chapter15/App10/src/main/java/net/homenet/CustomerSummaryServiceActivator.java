package net.homenet;

import org.springframework.integration.annotation.ServiceActivator;

import java.util.Collection;

public class CustomerSummaryServiceActivator {
    @ServiceActivator
    public void customerDeleteSummary(Collection<Customer> customers) {
        System.out.printf("Removed %s customer.\r\n", customers.size());
    }
}
