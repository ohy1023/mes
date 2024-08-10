package com.example.mes.domain.item.repository;

import com.example.mes.domain.item.Item;

import java.util.List;

public interface ItemCustomRepository {
    List<String> findCompanyList();

    List<Item> findCondition(String companyName, String itemType);
}
