//package com.example.mes.controller;
//
//import com.example.mes.item.controller.ItemController;
//import com.example.mes.item.dto.ItemAddRequest;
//import com.example.mes.item.dto.ItemDto;
//import com.example.mes.item.service.ItemService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.BDDMockito.given;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//
//@WebMvcTest(ItemController.class)
//class ItemControllerTest {
//
//    @Autowired
//    MockMvc mockMvc;
//
//    @MockBean
//    ItemService itemService;
//
//    @Autowired
//    ObjectMapper objectMapper;
//
//    @Test
//    @DisplayName("품목 등록")
//    void findDetail() {
//        // given
//        ItemAddRequest request = ItemAddRequest.builder()
//                .itemName("아이폰")
//                .itemCode("AW423DFH4")
//                .itemType("ㅇㅇ")
//                .itemGroup("ㅇㅇ")
//                .companyName("애플")
//                .build();
//
//        given(itemService.addItem(any(ItemAddRequest.class)))
//                .willReturn(ItemDto.builder()
//                        .itemName(request.getItemName())
//                        .build());
//
//        // when & then
//        mockMvc.perform(post("/api/v1/items")
//                .with(csrf()))
//
//    }
//}