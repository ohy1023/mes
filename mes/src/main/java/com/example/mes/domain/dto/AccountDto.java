package com.example.mes.domain.dto;

import com.example.mes.domain.entity.Account;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class AccountDto {

    private Long id;
    private String accountCode;
    private String accountName;

    @Builder
    public AccountDto(Long id, String accountCode, String accountName) {
        this.id = id;
        this.accountCode = accountCode;
        this.accountName = accountName;
    }

    public static List<AccountDto> toDtoList(List<Account> accounts) {
        return accounts.stream().map(account -> AccountDto.builder()
                        .id(account.getId())
                        .accountCode(account.getAccountCode())
                        .accountName(account.getAccountName())
                        .build())
                .collect(Collectors.toList());
    }

    public static AccountDto toAccountDto(Account account) {
        return AccountDto.builder()
                .id(account.getId())
                .accountCode(account.getAccountCode())
                .accountName(account.getAccountName())
                .build();
    }
}

