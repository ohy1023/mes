package com.example.mes.repository;

import com.example.mes.domain.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query(value = "SELECT DISTINCT m.COMPANY_NAME FROM Item m",nativeQuery = true)
    List<String> findDistinctCompanyName();

    List<Item> findAllByCompanyNameEqualsAndItemTypeEquals(String companyName,String itemType);
}
