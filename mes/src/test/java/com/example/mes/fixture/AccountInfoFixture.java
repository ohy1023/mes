package com.example.mes.fixture;

import com.example.mes.account.entity.Account;

public class AccountInfoFixture {
    public static Account get() {
        return Account.builder()
                .id(1L)
                .accountName("name")
                .accountCode("code")
                .build();
    }
}
