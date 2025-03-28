//package com.example.mes.domain.category;
//
//import com.example.mes.common.entity.BaseEntity;
//import lombok.AccessLevel;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//import java.util.List;
//
//@Entity
//@Getter
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
//public class ItemCategory extends BaseEntity {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "itemCategorySeq")
//    @SequenceGenerator(name = "itemCategorySeq", sequenceName = "ITEM_CATEGORY_SEQ", allocationSize = 1)
//    private Long id;
//
//    private String name;
//    private int order;
//    private boolean isActive;
//
//    @OneToMany(mappedBy = "itemCategory", cascade = CascadeType.ALL)
//    private List<SubCategory> subCategories;
//
//}
