//package com.example.mes.domain.category;
//
//import com.example.mes.common.entity.BaseEntity;
//import lombok.AccessLevel;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//
//@Entity
//@Getter
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
//public class SubCategory extends BaseEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subCategorySeq")
//    @SequenceGenerator(name = "subCategorySeq", sequenceName = "SUB_CATEGORY_SEQ", allocationSize = 1)
//    private Long id;
//
//    private String name; // 분류명
//    private int order; // 정렬기준
//    private boolean isActive; // 사용여부
//
//    @ManyToOne
//    @JoinColumn(name = "item_category_id")
//    private ItemCategory itemCategory;
//
//}