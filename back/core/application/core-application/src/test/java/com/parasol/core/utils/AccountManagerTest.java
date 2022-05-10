package com.parasol.core.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class AccountManagerTest {

    @Test
    void generateAccountNumber() {
        List<String> result = List.of(AccountManager.GenerateAccountNumber().split("-"));

        Assertions.assertEquals(true, Integer.valueOf(result.get(0)) >= 100);
        Assertions.assertEquals(true, Integer.valueOf(result.get(0)) <= 999);

        Assertions.assertEquals(true, Integer.valueOf(result.get(1)) >= 1);
        Assertions.assertEquals(true, Integer.valueOf(result.get(1)) <= 999);

        Assertions.assertEquals(true, Integer.valueOf(result.get(2)) >= 100000);
        Assertions.assertEquals(true, Integer.valueOf(result.get(2)) <= 999999);

        System.out.println("result = " + result);
    }
}