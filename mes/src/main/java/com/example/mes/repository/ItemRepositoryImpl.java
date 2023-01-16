package com.example.mes.repository;

import com.example.mes.domain.entity.Item;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.mes.domain.entity.QItem.*;

@Repository
@RequiredArgsConstructor
public class ItemRepositoryImpl implements ItemCustomRepository {

    private final JPAQueryFactory queryFactory;
    @Override
    public List<String> findCompanyList() {
        return queryFactory.select(item.companyName)
                .distinct()
                .from(item)
                .fetch();
    }

    @Override
    public List<Item> findCondition(String companyName, String itemType) {
        return null;
    }
}
