package com.example.mes.item.repository;

import com.example.mes.item.entity.Item;

import java.util.List;

public interface ItemCustomRepository {
    List<String> findCompanyList();

    List<Item> findCondition(String companyName, String itemType);
}
