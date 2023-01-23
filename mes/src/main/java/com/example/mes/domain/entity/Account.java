package com.example.mes.domain.entity;


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
public class Account extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
