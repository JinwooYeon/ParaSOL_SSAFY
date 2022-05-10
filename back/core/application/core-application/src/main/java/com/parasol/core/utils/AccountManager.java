package com.parasol.core.utils;

import java.util.Random;

public class AccountManager {
    public static String GenerateAccountNumber() {
        Random random = new Random();
        int accountNumberHead = random.nextInt(900) + 100;
        int accountNumberBody = random.nextInt(999) + 1;
        int accountNumberTail = random.nextInt(999999) + 1;
        return String.format("%03d-%03d-%06d", accountNumberHead, accountNumberBody, accountNumberTail);
    }
}