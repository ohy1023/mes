package com.example.mes.account.enums;

public enum TransactionType {
    입고("입고"),
    출고("출고");

    private final String type;

    TransactionType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}