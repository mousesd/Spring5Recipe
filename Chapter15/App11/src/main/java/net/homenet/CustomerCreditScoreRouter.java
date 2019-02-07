package net.homenet;

import org.springframework.integration.annotation.Router;

public class CustomerCreditScoreRouter {
    @Router
    public String routeByCusomterCreditScore(Customer customer) {
        if (customer.getCreditScore() > 770)
            return "safeCustomerChannel";
        return "riskyCustomerChannel";
    }
}
