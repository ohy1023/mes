package com.example.mes.controller;

import com.example.mes.common.exception.MesAppException;
import com.example.mes.domain.item.dto.ItemAddRequest;
import com.example.mes.domain.item.dto.ItemDto;
import com.example.mes.service.ItemService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static com.example.mes.common.exception.ErrorCode.INVALID_PERMISSION;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ItemController.class)
class ItemControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ItemService itemService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("품목 등록 성공")
    @WithMockUser
    void createItem() throws Exception {
        // given
        ItemAddRequest request = ItemAddRequest.builder()
                .itemName("아이폰")
                .itemCode("AW423DFH4")
                .itemType("ㅇㅇ")
                .itemGroup("ㅇㅇ")
                .companyName("애플")
                .build();

        given(itemService.addItem(any(ItemAddRequest.class)))
                .willReturn(ItemDto.builder()
                        .itemName(request.getItemName())
                        .build());

        // when & then
        mockMvc.perform(post("/api/v1/items")
                        .with(csrf())
                        .content(objectMapper.writeValueAsBytes(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.itemName").value("아이폰"))
                .andDo(print());

    }

    @Test
    @DisplayName("품목 등록 실패 - 권한이 없는 경우")
    void createItemFail01() throws Exception {
        // given
        ItemAddRequest request = ItemAddRequest.builder()
                .itemName("아이폰")
                .itemCode("AW423DFH4")
                .itemType("무선 이어폰")
                .itemGroup("전자기기")
                .companyName("애플")
                .build();

        given(itemService.addItem(any(ItemAddRequest.class)))
                .willThrow(new MesAppException(INVALID_PERMISSION, INVALID_PERMISSION.getMessage()));

        // when & then
        mockMvc.perform(post("/api/v1/items")
                        .with(csrf())
                        .content(objectMapper.writeValueAsBytes(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andDo(print());

    }
}