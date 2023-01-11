package com.example.mes.domain.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ItemUpdateRequest {
    private String companyName;
    private String itemType;
    private String itemGroup;
    private String itemCode;
    private String itemName;

    @Builder
    public ItemUpdateRequest(String companyName, String itemType, String itemGroup, String itemCode, String itemName) {
        this.companyName = companyName;
        this.itemType = itemType;
        this.itemGroup = itemGroup;
        this.itemCode = itemCode;
        this.itemName = itemName;
    }
}
