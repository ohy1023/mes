package com.example.mes.item.repository.repository;

import com.example.mes.item.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long>,ItemCustomRepository {

}
