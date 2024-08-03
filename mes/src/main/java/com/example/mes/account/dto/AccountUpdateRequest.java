package com.example.mes.account.dto;

import com.example.mes.account.entity.Account;
import com.example.mes.account.enums.TransactionType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AccountUpdateRequest {
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
    public AccountUpdateRequest(String accountCode, String accountName, String accountTel, TransactionType transactionType, String businessNumber, String representativeName, String note) {
        this.accountCode = accountCode;
        this.accountName = accountName;
        this.accountTel = accountTel;
        this.transactionType = transactionType;
        this.businessNumber = businessNumber;
        this.representativeName = representativeName;
        this.note = note;
    }

    public Account toEntity() {
        return Account.builder()
                .accountCode(this.accountCode)
                .accountName(this.accountName)
                .accountTel(this.accountTel)
                .transactionType(this.transactionType)
                .businessNumber(this.businessNumber)
                .representativeName(this.representativeName)
                .note(this.note)
                .build();
    }
}
