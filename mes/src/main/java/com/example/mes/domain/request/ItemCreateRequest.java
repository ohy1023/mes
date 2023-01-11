package com.example.mes.domain.request;


import com.example.mes.domain.entity.Item;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ItemCreateRequest {

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

    public Item toEntity() {
        return Item.builder()
                .companyName(this.companyName)
                .itemName(this.itemName)
                .itemCode(this.itemCode)
                .itemGroup(this.itemGroup)
                .standard(this.standard)
                .itemType(this.itemType)
                .partNumber(this.partNumber)
                .receiptPaymentUnit(this.receiptPaymentUnit)
                .purchaseUnit(this.purchaseUnit)
                .purchaseUnitQuantity(this.purchaseUnitQuantity)
                .requiredUnit(this.requiredUnit)
                .requiredUnitQuantity(this.requiredUnitQuantity)
                .yieldUnit(this.yieldUnit)
                .yieldUnitQuantity(this.yieldUnitQuantity)
                .build();
    }

}

