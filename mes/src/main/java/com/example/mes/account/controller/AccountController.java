package com.example.mes.account.controller;

import com.example.mes.account.dto.AccountDto;
import com.example.mes.account.dto.AccountCreateRequest;
import com.example.mes.account.dto.AccountUpdateRequest;
import com.example.mes.common.dto.MessageResponse;
import com.example.mes.common.dto.Response;
import com.example.mes.account.service.AccountService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/accounts")
public class AccountController {

    private final AccountService accountService;

    @ApiOperation(value = "거래처 전체 조회")
    @GetMapping
    public ResponseEntity<Response<List<AccountDto>>> findAll() {
        List<AccountDto> accounts = accountService.findAllAccount();
        return ResponseEntity.ok().body(Response.success(accounts));
    }

    @ApiOperation(value = "거래처 등록")
    @PostMapping
    public ResponseEntity<Response<AccountDto>> createAccount(@RequestBody AccountCreateRequest request) {
        AccountDto account = accountService.createAccount(request);
        return ResponseEntity.ok().body(Response.success(account));
    }

    @ApiOperation(value = "거래처 수정")
    @PutMapping("/{accountId}")
    public ResponseEntity<Response<MessageResponse>> updateAccount(@PathVariable Long accountId, @RequestBody AccountUpdateRequest request) {
        Long id = accountService.update(accountId, request);
        return ResponseEntity.ok().body(Response.success(MessageResponse.builder()
                .id(id)
                .message("거래처 수정 완료")
                .build()));
    }

    @ApiOperation(value = "거래처 삭제")
    @DeleteMapping("/{accountId}")
    public ResponseEntity<Response<MessageResponse>> deleteAccount(@PathVariable Long accountId) {
        Long id = accountService.delete(accountId);
        return ResponseEntity.ok().body(Response.success(MessageResponse.builder()
                .id(id)
                .message("거래처 삭제 완료")
                .build()));
    }
}
