package com.example.mes.controller;

import com.example.mes.common.exception.ErrorCode;
import com.example.mes.common.exception.MesAppException;
import com.example.mes.user.controller.UserController;
import com.example.mes.user.dto.UserJoinRequest;
import com.example.mes.user.dto.UserJoinResponse;
import com.example.mes.user.dto.UserLoginRequest;
import com.example.mes.user.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static com.example.mes.common.exception.ErrorCode.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserService userService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("회원가입 성공")
    @WithMockUser
    void join() throws Exception {
        // given
        UserJoinRequest request = UserJoinRequest.builder()
                .email("ohy1023@naver.com")
                .userName("오형상")
                .password("ohy1023")
                .build();
        given(userService.join(any(UserJoinRequest.class)))
                .willReturn(new UserJoinResponse(1, request.getEmail()));

        //when & then
        mockMvc.perform(post("/api/v1/users/join")
                        .with(csrf())
                        .content(objectMapper.writeValueAsBytes(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultCode").value("SUCCESS"))
                .andExpect(jsonPath("$.result.userId").value(1))
                .andExpect(jsonPath("$.result.email").value("ohy1023@naver.com"))
                .andDo(print());
    }

    @Test
    @DisplayName("회원가입 실패 - email 중복")
    @WithMockUser
    void joinError() throws Exception {
        // given
        UserJoinRequest request = UserJoinRequest.builder()
                .email("ohy1023@naver.com")
                .userName("오형상")
                .password("ohy1023")
                .build();
        given(userService.join(any(UserJoinRequest.class)))
                .willThrow(new MesAppException(DUPLICATED_EMAIL, DUPLICATED_EMAIL.getMessage()));

        // when & then
        mockMvc.perform(post("/api/v1/users/join")
                        .with(csrf())
                        .content(objectMapper.writeValueAsBytes(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.resultCode").value("ERROR"))
                .andExpect(jsonPath("$.result.errorCode").value("DUPLICATED_EMAIL"))
                .andDo(print());
    }


}
