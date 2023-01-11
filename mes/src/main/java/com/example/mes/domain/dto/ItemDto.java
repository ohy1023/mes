package com.example.mes.domain.dto;

import com.example.mes.domain.entity.Item;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class ItemDto {

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
    public ItemDto(Long id, String companyName, String itemCode, String itemName, String itemType, String partNumber, String itemGroup, String standard, String receiptPaymentUnit, String purchaseUnit, int purchaseUnitQuantity, String requiredUnit, int requiredUnitQuantity, String yieldUnit, int yieldUnitQuantity) {
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

    public static List<ItemDto> toDtoList(List<Item> items) {
        List<ItemDto> itemDtos = items.stream().map(item -> ItemDto.builder()
                        .id(item.getId())
                        .companyName(item.getCompanyName())
                        .itemName(item.getItemName())
                        .itemType(item.getItemType())
                        .itemCode(item.getItemCode())
                        .itemGroup(item.getItemGroup())
                        .partNumber(item.getPartNumber())
                        .receiptPaymentUnit(item.getReceiptPaymentUnit())
                        .standard(item.getStandard())
                        .purchaseUnit(item.getPurchaseUnit())
                        .purchaseUnitQuantity(item.getPurchaseUnitQuantity())
                        .requiredUnit(item.getRequiredUnit())
                        .requiredUnitQuantity(item.getRequiredUnitQuantity())
                        .yieldUnit(item.getYieldUnit())
                        .yieldUnitQuantity(item.getYieldUnitQuantity())
                        .build())
                .collect(Collectors.toList());
        return itemDtos;
    }
}
