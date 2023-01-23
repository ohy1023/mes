package com.example.mes.domain.request;

import com.example.mes.domain.entity.Account;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AccountCreateRequest {
    private String accountCode;
    private String accountName;

    @Builder
    public AccountCreateRequest(String accountCode, String accountName) {
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
