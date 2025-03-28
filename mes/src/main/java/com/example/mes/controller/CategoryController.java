//package com.example.mes.controller;
//
//import com.example.mes.common.dto.Response;
//import com.example.mes.domain.category.dto.ItemCategoryDto;
//import com.example.mes.service.CategoryService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/api/v1/category")
//public class CategoryController {
//
//
//    private final CategoryService categoryService;
//
////    @GetMapping
////    public Response<List<AccountDto> findAll() {
////        List<AccountDto> accounts = accountService.findAllAccount();
////        return Response.success(accounts);
////    }
//
//    @PostMapping
//    public Response<ItemCategoryDto> createItemCategory() {
//        ItemCategoryDto itemCategoryDto = categoryService.createItemCategory();
//        return Response.success(itemCategoryDto);
//    }
//
//
//}
