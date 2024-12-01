package com.chernov.customer.service.Service;

import com.chernov.customer.service.Dto.BankResponse;
import com.chernov.customer.service.Dto.UserRequest;

public interface UserService {
    BankResponse createAccount(UserRequest userRequest);
}
