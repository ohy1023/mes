package com.example.mes.domain.account.dto;

import com.example.mes.domain.account.TransactionType;
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
    @NotBlank
    private String zipCode; // 우편 번호 ex 07422
    @NotBlank
    private String streetAdr; // 주소 ex) 도림로 54길 14
    private String detailAdr; // 상세 주소 ex) 102동 1203호

    @Builder
    public AccountUpdateRequest(String accountName, String accountTel, TransactionType transactionType, String businessNumber, String representativeName, String zipCode, String streetAdr, String detailAdr) {
        this.accountName = accountName;
        this.accountTel = accountTel;
        this.transactionType = transactionType;
        this.businessNumber = businessNumber;
        this.representativeName = representativeName;
        this.zipCode = zipCode;
        this.streetAdr = streetAdr;
        this.detailAdr = detailAdr;
    }
}
