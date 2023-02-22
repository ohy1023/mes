package com.example.mes.account.controller;

import com.example.mes.account.dto.AccountDto;
import com.example.mes.account.dto.AccountCreateRequest;
import com.example.mes.common.dto.Response;
import com.example.mes.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    @GetMapping
    public ResponseEntity<Response<List<AccountDto>>> findAll() {
        List<AccountDto> accounts = accountService.findAllAccount();
        return ResponseEntity.ok().body(Response.success(accounts));
    }

    @PostMapping
    public ResponseEntity<Response<AccountDto>> createAccount(@RequestBody AccountCreateRequest request) {
        AccountDto account = accountService.createAccount(request);
        return ResponseEntity.ok().body(Response.success(account));
    }
}
