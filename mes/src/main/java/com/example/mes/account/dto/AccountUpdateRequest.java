package com.example.mes.account.dto;

import com.example.mes.account.entity.Account;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AccountUpdateRequest {
    private String accountCode;
    private String accountName;

    @Builder
    public AccountUpdateRequest(String accountCode, String accountName) {
        this.accountCode = accountCode;
        this.accountName = accountName;
    }

    public Account toEntity() {
        return Account.builder()
                .accountCode(this.accountCode)
                .accountName(this.accountName)
                .build();
    }
}
