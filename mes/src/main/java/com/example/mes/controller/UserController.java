package com.example.mes.controller;

import com.example.mes.common.dto.Response;
import com.example.mes.domain.user.dto.*;
import com.example.mes.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/users")
public class UserController {
    private final UserService userService;

    @PostMapping("/join")
    public Response<UserJoinResponse> joinUser(@RequestBody UserJoinRequest userJoinRequest) {
        UserJoinResponse userJoinResponse = userService.join(userJoinRequest);

        return Response.success(userJoinResponse);
    }

//
//    @PostMapping("/login")
//    public String login(@RequestBody UserLoginRequest userLoginRequest) {
//        return "ok";
//    }

    @GetMapping
    public Response<UserGetResponse> getUserInfo(Authentication authentication) {
        String email = authentication.getName();
        log.info("userEmail:{}", email);
        UserGetResponse info = userService.getInfo(email);
        return Response.success(info);
    }

    @PostMapping("/password")
    public Response<Boolean> validatePassword(@RequestBody UserPasswordRequest request, Authentication authentication) {
        String email = authentication.getName();
        log.info("userEmail:{}", email);
        String password = request.getPassword();
        boolean validation = userService.validate(email, password);
        return Response.success(validation);
    }

    @PutMapping("/password")
    public Response<String> updatePassword(@RequestBody UserUpdatePasswordRequest request, Authentication authentication) {
        String email = authentication.getName();
        log.info("userEmail:{}", email);
        Long userId = userService.modifyPassword(email, request);
        return Response.success(" 비밀번호가 변경되었습니다.\n 다시 로그인해 주세요.");
    }


    @PostMapping
    public Response<UserPutResponse> modifyUser(MultipartFile image, String userName, int removeClick, Authentication authentication) throws IOException {

        String email = authentication.getName();
        log.info("image:{}", image);
        log.info("userName:{}", userName);
        log.info("userEmail:{}", email);
        UserPutResponse response = userService.modify(image, userName, email, removeClick);
        return Response.success(response);
    }

    @DeleteMapping
    public Response<String> deleteUser(Authentication authentication) {
        String email = authentication.getName();
        log.info("userEmail:{}", email);
        Long userId = userService.delete(email);
        return Response.success("회원이 탈퇴되었습니다.");
    }


    @PostMapping("/{userId}")
    public Response<UserRoleResponse> changeRole(@PathVariable Long userId, Authentication authentication) {
        UserRoleResponse response = userService.changeRole(userId, authentication.getName());
        return Response.success(response);
    }

    @GetMapping("/reissuance")
    public Response<String> reissue() {
        return Response.success("SUCCESS");
    }


}
