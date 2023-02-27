package com.example.mes.account.entity;


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

    private String accountCode;

    private String accountName;

    @Builder
    public Account(Long id, String accountCode, String accountName) {
        this.id = id;
        this.accountCode = accountCode;
        this.accountName = accountName;
    }
}
