package com.example.mes.domain.item;

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
@SQLDelete(sql = "UPDATE ITEM SET deleted_at = CURRENT_TIMESTAMP WHERE item_id = ?")
public class Item extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    private String companyName;

    private String itemCode;

    private String itemName;

    private String itemType;

    private String partNumber;

    private String itemGroup;

    private String standard;

    private String receiptPaymentUnit;

    private String purchaseUnit;

    private int purchaseUnitQuantity;

    private String requiredUnit;

    private int requiredUnitQuantity;

    private String yieldUnit;

    private int yieldUnitQuantity;

    @Builder
    public Item(Long id, String companyName, String itemCode, String itemName, String itemType, String partNumber, String itemGroup, String standard, String receiptPaymentUnit, String purchaseUnit, int purchaseUnitQuantity, String requiredUnit, int requiredUnitQuantity, String yieldUnit, int yieldUnitQuantity) {
        this.id = id;
        this.companyName = companyName;
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.itemType = itemType;
        this.partNumber = partNumber;
        this.itemGroup = itemGroup;
        this.standard = standard;
        this.receiptPaymentUnit = receiptPaymentUnit;
        this.purchaseUnit = purchaseUnit;
        this.purchaseUnitQuantity = purchaseUnitQuantity;
        this.requiredUnit = requiredUnit;
        this.requiredUnitQuantity = requiredUnitQuantity;
        this.yieldUnit = yieldUnit;
        this.yieldUnitQuantity = yieldUnitQuantity;
    }
}
