package com.example.mes.account.controller;

import com.example.mes.account.dto.AccountDto;
import com.example.mes.account.dto.AccountCreateRequest;
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
}
