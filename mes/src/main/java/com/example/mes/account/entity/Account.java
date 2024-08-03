package com.example.mes.account.entity;


import com.example.mes.account.dto.AccountUpdateRequest;
import com.example.mes.account.enums.TransactionType;
import com.example.mes.common.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Where(clause = "deleted_at IS NULL")
@SQLDelete(sql = "UPDATE ACCOUNT SET deleted_at = CURRENT_TIMESTAMP WHERE account_id = ?")
@SequenceGenerator(
        name = "ACCOUNT_SEQ_GENERATOR",
        sequenceName = "ACCOUNT_SEQ", //매핑할 데이터베이스 시퀀스 이름
        initialValue = 1, allocationSize = 1)
public class Account extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACCOUNT_SEQ_GENERATOR")
    @Column(name = "account_id")
    private Long id;

    // 거래처 코드
    private String accountCode;

    // 거래처명
    private String accountName;

    // 거래처 전화번호
    private String accountTel;

    // 거래 유형
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    // 사업자 번호
    private String businessNumber;

    // 대표명
    private String representativeName;

    // 비고
    private String note;

    @Builder
    public Account(Long id, String accountCode, String accountName, String accountTel, TransactionType transactionType, String businessNumber, String representativeName, String note) {
        this.id = id;
        this.accountCode = accountCode;
        this.accountName = accountName;
        this.accountTel = accountTel;
        this.transactionType = transactionType;
        this.businessNumber = businessNumber;
        this.representativeName = representativeName;
        this.note = note;
    }

    public void updateAccount(AccountUpdateRequest accountUpdateRequest) {
        this.accountCode = accountUpdateRequest.getAccountCode();
        this.accountName = accountUpdateRequest.getAccountName();
        this.accountTel = accountUpdateRequest.getAccountTel();
        this.transactionType = accountUpdateRequest.getTransactionType();
        this.businessNumber = accountUpdateRequest.getBusinessNumber();
        this.representativeName = accountUpdateRequest.getRepresentativeName();
        this.note = accountUpdateRequest.getNote();
    }
}
