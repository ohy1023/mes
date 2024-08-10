package com.example.mes.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Address {

    private String streetAdr;
    private String detailAdr;
    private String zipcode;

    @Builder
    public Address(String streetAdr, String detailAdr, String zipcode) {
        this.streetAdr = streetAdr;
        this.detailAdr = detailAdr;
        this.zipcode = zipcode;
    }
}