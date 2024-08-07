package com.example.mes.account.dto;

import com.example.mes.account.entity.Account;
import com.example.mes.account.enums.TransactionType;
import com.example.mes.common.aop.PhoneNumber;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class AccountUpdateRequest {

    @NotBlank
    private String accountName; // 거래처명
    @PhoneNumber
    private String accountTel; // 거래처 전화번호
    @NotNull
    private TransactionType transactionType; // 거래 유형
    @NotBlank
    private String businessNumber; // 사업자 번호
    @NotBlank
    private String representativeName; // 대표명
    private String note; // 비고

    @Builder
    public AccountUpdateRequest(String accountName, String accountTel, TransactionType transactionType, String businessNumber, String representativeName, String note) {
        this.accountName = accountName;
        this.accountTel = accountTel;
        this.transactionType = transactionType;
        this.businessNumber = businessNumber;
        this.representativeName = representativeName;
        this.note = note;
    }
}
