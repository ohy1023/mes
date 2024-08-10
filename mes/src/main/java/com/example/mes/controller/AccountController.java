package com.example.mes.controller;

import com.example.mes.domain.account.dto.AccountDto;
import com.example.mes.domain.account.dto.AccountCreateRequest;
import com.example.mes.domain.account.dto.AccountUpdateRequest;
import com.example.mes.common.dto.MessageResponse;
import com.example.mes.common.dto.Response;
import com.example.mes.service.AccountService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public ResponseEntity<Response<AccountDto>> createAccount(@Valid @RequestBody AccountCreateRequest request) {
        AccountDto account = accountService.createAccount(request);
        return ResponseEntity.ok().body(Response.success(account));
    }

    @ApiOperation(value = "거래처 수정")
    @PutMapping("/{accountId}")
    public ResponseEntity<Response<AccountDto>> updateAccount(@PathVariable Long accountId, @RequestBody AccountUpdateRequest request) {
        AccountDto updatedAccount = accountService.update(accountId, request);
        return ResponseEntity.ok().body(Response.success(updatedAccount));
    }

    @ApiOperation(value = "거래처 삭제")
    @DeleteMapping("/{accountId}")
    public ResponseEntity<Response<MessageResponse>> deleteAccount(@PathVariable Long accountId) {
        Long id = accountService.delete(accountId);
        return ResponseEntity.ok().body(Response.success(new MessageResponse(id, "거래처 삭제 완료")));
    }
}
