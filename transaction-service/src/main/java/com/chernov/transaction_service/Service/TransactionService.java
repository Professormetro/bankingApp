package com.chernov.transaction_service.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TransactionService {

    private RestTemplate restTemplate;

    private final String CUSTOMER_SERVICE_URL = "http://localhost:8080/customer-service/customers/";

    public User getUserById(Long userId) {
        return restTemplate.getForObject(CUSTOMER_SERVICE_URL + userId, User.class);
    }
}


}
