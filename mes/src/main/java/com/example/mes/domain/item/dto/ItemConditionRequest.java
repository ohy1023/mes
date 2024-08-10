package com.example.mes.domain.item.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ItemConditionRequest {
    private String companyName;

    @Builder
    public ItemConditionRequest(String companyName) {
        this.companyName = companyName;
    }
}
