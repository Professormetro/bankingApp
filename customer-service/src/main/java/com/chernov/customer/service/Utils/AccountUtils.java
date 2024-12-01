package com.chernov.customer.service.Utils;

import java.time.Year;

public class AccountUtils {

    public static final String ACCOUNT_EXISTS_CODE = "001";
    public static final String ACCOUNT_EXIST_MESSAGE = "This user already has an account created";
    public static final String ACCOUNT_CREATION_SUCCESS_CODE = "002";
    public static final String ACCOUNT_CREATION_MESSAGE = "Account has been successfully created";

    public static String generateAccountNumber() {

        Year currentYear = Year.now();
        int min = 100000;
        int max = 999999;
        int randomNumber = (int)Math.floor((Math.random() * (max - min + 1) + min));

        String currentYearToString = String.valueOf(currentYear.getValue());
        String randomNumberToString = String.valueOf(randomNumber);

        StringBuilder accountNumber = new StringBuilder();
        accountNumber.append(currentYearToString).append(randomNumberToString);

        return accountNumber.toString();
    }
}
