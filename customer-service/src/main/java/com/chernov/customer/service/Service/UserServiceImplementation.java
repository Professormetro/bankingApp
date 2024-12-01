package com.chernov.customer.service.Service;

import com.chernov.customer.service.Dto.AccountInfo;
import com.chernov.customer.service.Dto.BankResponse;
import com.chernov.customer.service.Dto.EmailDetails;
import com.chernov.customer.service.Dto.UserRequest;
import com.chernov.customer.service.Entity.User;
import com.chernov.customer.service.Repository.UserRepository;
import com.chernov.customer.service.Utils.AccountUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;
    private final EmailService emailService;

    @Autowired
    public UserServiceImplementation(UserRepository userRepository, EmailService emailService) {
        this.userRepository = userRepository;
        this.emailService = emailService;
    }


    @Override
    public BankResponse createAccount(UserRequest userRequest) {

        //Validation if user already has an account
        if(userRepository.existsByEmail(userRequest.getEmail())) {
            return BankResponse.builder()
                    .responseCode(AccountUtils.ACCOUNT_EXISTS_CODE)
                    .responseMessage(AccountUtils.ACCOUNT_EXIST_MESSAGE)
                    .accountInfo(null)
                    .build();
        }

        User newUser = User.builder()
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .otherName(userRequest.getOtherName())
                .gender(userRequest.getGender())
                .address(userRequest.getAddress())
                .stateOfOrigin(userRequest.getStateOfOrigin())
                .accountNumber(AccountUtils.generateAccountNumber())
                .accountBalance(BigDecimal.ZERO)
                .email(userRequest.getEmail())
                .phoneNumber(userRequest.getPhoneNumber())
                .alternativePhoneNumber(userRequest.getAlternativePhoneNumber())
                .status("ACTIVE")
                .build();

        User savedUser = userRepository.save(newUser);

        /*
            TODO: Вынести отправку уведомления о регистрации в Notification сервис
         */
        //Send email to registered user
        EmailDetails emailDetails = EmailDetails.builder()
                .recipient(savedUser.getEmail())
                .subject("ACCOUNT CREATED")
                .messageBody(savedUser.getLastName()
                        + ", " + "congratulations! Your account has been successfully created. Now u can us our products.")
                .build();
        emailService.sendEmailAlert(emailDetails);


        return BankResponse.builder()
                .responseCode(AccountUtils.ACCOUNT_CREATION_SUCCESS_CODE)
                .responseMessage(AccountUtils.ACCOUNT_CREATION_MESSAGE)
                .accountInfo(
                        AccountInfo.builder()
                                .accountBalance(savedUser.getAccountBalance())
                                .accountNumber(savedUser.getAccountNumber())
                                .accountName(savedUser.getFirstName() + " " + savedUser.getLastName() + " " + savedUser.getOtherName())
                                .build()
                )
                .build();

    }
}
