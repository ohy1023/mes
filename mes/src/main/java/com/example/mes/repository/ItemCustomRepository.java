package com.example.mes.repository;

import com.example.mes.domain.entity.Item;

import java.util.List;

public interface ItemCustomRepository {
    List<String> findCompanyList();

    List<Item> findCondition(String companyName, String itemType);
}
