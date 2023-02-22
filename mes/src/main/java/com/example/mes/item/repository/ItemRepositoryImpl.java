package com.example.mes.item.repository;

import com.example.mes.item.entity.Item;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import static com.example.mes.item.entity.QItem.item;


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
        return queryFactory.selectFrom(item)
                .where(eqCompanyName(companyName),eqItemType(itemType))
                .fetch();
    }

    private BooleanExpression eqCompanyName(String companyName) {
        if(companyName == null || companyName.isEmpty()) {
            return null;
        }
        return item.companyName.eq(companyName);
    }

    private BooleanExpression eqItemType(String itemType) {
        if(itemType == null || itemType.isEmpty()) {
            return null;
        }
        return item.itemType.eq(itemType);
    }



}
