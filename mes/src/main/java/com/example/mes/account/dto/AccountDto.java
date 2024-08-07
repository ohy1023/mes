package com.example.mes.account.dto;

import com.example.mes.account.entity.Account;
import com.example.mes.account.enums.TransactionType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class AccountDto {

    private Long id;
    // 거래처 코드
    private String accountCode;
    // 거래처명
    private String accountName;
    // 거래처 전화번호
    private String accountTel;
    // 거래 유형
    private TransactionType transactionType;
    // 사업자 번호
    private String businessNumber;
    // 대표명
    private String representativeName;
    // 비고
    private String note;

    @Builder
    public AccountDto(Long id, String accountCode, String accountName, String accountTel, TransactionType transactionType, String businessNumber, String representativeName, String note) {
        this.id = id;
        this.accountCode = accountCode;
        this.accountName = accountName;
        this.accountTel = accountTel;
        this.transactionType = transactionType;
        this.businessNumber = businessNumber;
        this.representativeName = representativeName;
        this.note = note;
    }



    public static List<AccountDto> toDtoList(List<Account> accounts) {
        return accounts.stream().map(AccountDto::toAccountDto)
                .collect(Collectors.toList());
    }

    public static AccountDto toAccountDto(Account account) {
        return AccountDto.builder()
                .id(account.getId())
                .accountCode(account.getAccountCode())
                .accountName(account.getAccountName())
                .accountTel(account.getAccountTel())
                .transactionType(account.getTransactionType())
                .businessNumber(account.getBusinessNumber())
                .representativeName(account.getRepresentativeName())
                .note(account.getNote())
                .build();
    }
}

