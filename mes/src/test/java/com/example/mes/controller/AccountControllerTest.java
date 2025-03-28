//package com.example.mes.controller;
//
//import com.example.mes.domain.Address;
//import com.example.mes.domain.account.dto.AccountCreateRequest;
//import com.example.mes.domain.account.dto.AccountDto;
//import com.example.mes.domain.account.dto.AccountUpdateRequest;
//import com.example.mes.domain.account.TransactionType;
//import com.example.mes.service.AccountService;
//import com.example.mes.common.dto.MessageResponse;
//import com.example.mes.common.exception.MesAppException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.security.test.context.support.WithAnonymousUser;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static com.example.mes.common.exception.ErrorCode.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.BDDMockito.given;
//import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest(AccountController.class)
//class AccountControllerTest {
//    @Autowired
//    MockMvc mockMvc;
//
//    @MockBean
//    AccountService accountService;
//
//    @Autowired
//    ObjectMapper objectMapper;
//
//    @Test
//    @DisplayName("거래처 전체 조회")
//    @WithMockUser
//    void findAllAccount() throws Exception {
//        // given
//        AccountDto dto1 = AccountDto.builder()
//                .id(1L)
//                .accountCode("10002")
//                .accountName("이버푸드")
//                .accountTel("010-2345-2515")
//                .transactionType(TransactionType.입고)
//                .businessNumber("220-51-23451")
//                .representativeName("허영돈")
//                .address(new Address("디지털로 64길 15", "1203호", "05652"))
//                .build();
//        AccountDto dto2 = AccountDto.builder()
//                .id(2L)
//                .accountCode("10003")
//                .accountName("공주푸드")
//                .accountTel("010-5443-1254")
//                .transactionType(TransactionType.입고)
//                .businessNumber("123-42-42151")
//                .representativeName("김미지")
//                .address(new Address("디지털로 64길 16", "1204호", "05653"))
//                .build();
//
//        List<AccountDto> response = new ArrayList<>();
//        response.add(dto1);
//        response.add(dto2);
//
//        given(accountService.findAllAccount())
//                .willReturn(response);
//
//        // when & then
//        mockMvc.perform(get("/api/v1/accounts")
//                        .with(csrf())
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.resultCode").value("SUCCESS"))
//                .andExpect(jsonPath("$.result.size()").value(response.size()))
//                .andDo(print());
//
//    }
//
//    @Test
//    @DisplayName("거래처 등록 성공")
//    @WithMockUser
//    void createItem() throws Exception {
//        // given
//        AccountCreateRequest request = AccountCreateRequest.builder()
//                .accountName("이버푸드")
//                .accountTel("010-2345-2515")
//                .transactionType(TransactionType.입고)
//                .businessNumber("220-51-23451")
//                .representativeName("허영돈")
//                .zipCode("05652")
//                .streetAdr("디지털로 64길 15")
//                .detailAdr("1203호")
//                .build();
//
//        AccountDto response = AccountDto.builder()
//                .id(1L)
//                .accountCode("ACCOUNT-0001")
//                .accountName("이버푸드")
//                .accountTel("010-2345-2515")
//                .transactionType(TransactionType.입고)
//                .businessNumber("220-51-23451")
//                .representativeName("허영돈")
//                .address(new Address("디지털로 64길 15", "1203호", "05652"))
//                .build();
//
//        given(accountService.createAccount(any(AccountCreateRequest.class)))
//                .willReturn(response);
//
//        // when & then
//        mockMvc.perform(post("/api/v1/accounts")
//                        .with(csrf())
//                        .content(objectMapper.writeValueAsBytes(request))
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.resultCode").value("SUCCESS"))
//                .andExpect(jsonPath("$.result.id").value(response.getId()))
//                .andExpect(jsonPath("$.result.accountCode").value(response.getAccountCode()))
//                .andExpect(jsonPath("$.result.accountName").value(response.getAccountName()))
//                .andDo(print());
//
//    }
//
//    @Test
//    @DisplayName("거래처 등록 실패 - 권한 없음")
//    @WithAnonymousUser
//    void createItemFail01() throws Exception {
//        // given
//        AccountCreateRequest request = AccountCreateRequest.builder()
//                .accountName("이버푸드")
//                .accountTel("010-2345-2515")
//                .transactionType(TransactionType.입고)
//                .businessNumber("220-51-23451")
//                .representativeName("허영돈")
//                .zipCode("05652")
//                .streetAdr("디지털로 64길 15")
//                .detailAdr("1203호")
//                .build();
//
//        // when & then
//        mockMvc.perform(post("/api/v1/accounts")
//                        .with(csrf())
//                        .content(objectMapper.writeValueAsBytes(request))
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isUnauthorized())
//                .andDo(print());
//
//    }
//
//    @Test
//    @DisplayName("거래처 수정 성공")
//    @WithMockUser
//    void updateAccount() throws Exception {
//        // given
//        AccountUpdateRequest request = AccountUpdateRequest.builder()
//                .accountName("히피푸드")
//                .accountTel("010-2345-2515")
//                .transactionType(TransactionType.입고)
//                .businessNumber("220-51-23451")
//                .representativeName("허영돈")
//                .zipCode("05652")
//                .streetAdr("디지털로 64길 15")
//                .detailAdr("1203호")
//                .build();
//
//        AccountDto response = AccountDto.builder()
//                .accountName("new히피푸드")
//                .accountTel("010-2345-2515")
//                .transactionType(TransactionType.입고)
//                .businessNumber("220-51-23451")
//                .representativeName("허영돈")
//                .address(new Address("디지털로 64길 15", "1203호", "05652"))
//                .build();
//
//        given(accountService.update(any(Long.class), any(AccountUpdateRequest.class)))
//                .willReturn(response);
//
//        // when & then
//        mockMvc.perform(put("/api/v1/accounts/1")
//                        .with(csrf())
//                        .content(objectMapper.writeValueAsBytes(request))
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.resultCode").value("SUCCESS"))
////                .andExpect(jsonPath("$.result.id").value(response.getId()))
////                .andExpect(jsonPath("$.result.message").value(response.getMessage()))
//                .andDo(print());
//
//    }
//
//    @Test
//    @DisplayName("거래처 수정 실패 - 해당 거래처가 없을 경우")
//    @WithMockUser
//    void updateAccountFail01() throws Exception {
//        // given
//        AccountUpdateRequest request = AccountUpdateRequest.builder()
//                .accountName("이버푸드")
//                .accountTel("010-2345-2515")
//                .transactionType(TransactionType.입고)
//                .businessNumber("220-51-23451")
//                .representativeName("허영돈")
//                .zipCode("05652")
//                .streetAdr("디지털로 64길 15")
//                .detailAdr("1203호")
//                .build();
//
//        given(accountService.update(any(Long.class), any(AccountUpdateRequest.class)))
//                .willThrow(new MesAppException(ACCOUNT_NOT_FOUND, ACCOUNT_NOT_FOUND.getMessage()));
//
//        // when & then
//        mockMvc.perform(put("/api/v1/accounts/1")
//                        .with(csrf())
//                        .content(objectMapper.writeValueAsBytes(request))
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isNotFound())
//                .andExpect(jsonPath("$.resultCode").value("ERROR"))
//                .andExpect(jsonPath("$.result.errorCode").value("ACCOUNT_NOT_FOUND"))
//                .andExpect(jsonPath("$.result.message").value(ACCOUNT_NOT_FOUND.getMessage()))
//                .andDo(print());
//
//    }
//
//    @Test
//    @DisplayName("거래처 삭제 성공")
//    @WithMockUser
//    void deleteAccount() throws Exception {
//        // given
//
//        MessageResponse response = MessageResponse.builder()
//                .id(1L)
//                .message("거래처 삭제 완료")
//                .build();
//
//        given(accountService.delete(any(Long.class)))
//                .willReturn(response.getId());
//
//        // when & then
//        mockMvc.perform(delete("/api/v1/accounts/1")
//                        .with(csrf())
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.resultCode").value("SUCCESS"))
//                .andExpect(jsonPath("$.result.id").value(response.getId()))
//                .andExpect(jsonPath("$.result.message").value(response.getMessage()))
//                .andDo(print());
//
//    }
//
//    @Test
//    @DisplayName("거래처 삭제 실패 - 해당 거래처가 없을 경우")
//    @WithMockUser
//    void deleteAccountFail01() throws Exception {
//        // given
//        given(accountService.delete(any(Long.class)))
//                .willThrow(new MesAppException(ACCOUNT_NOT_FOUND, ACCOUNT_NOT_FOUND.getMessage()));
//
//        // when & then
//        mockMvc.perform(delete("/api/v1/accounts/1")
//                        .with(csrf())
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isNotFound())
//                .andExpect(jsonPath("$.resultCode").value("ERROR"))
//                .andExpect(jsonPath("$.result.errorCode").value("ACCOUNT_NOT_FOUND"))
//                .andExpect(jsonPath("$.result.message").value(ACCOUNT_NOT_FOUND.getMessage()))
//                .andDo(print());
//
//    }
//
//
//
//}