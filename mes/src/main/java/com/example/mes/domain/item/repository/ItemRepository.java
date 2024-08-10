package com.example.mes.domain.item.repository;

import com.example.mes.domain.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long>,ItemCustomRepository {

}
