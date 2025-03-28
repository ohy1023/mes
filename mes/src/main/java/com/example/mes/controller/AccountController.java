package com.example.mes.controller;

import com.example.mes.domain.account.dto.AccountDto;
import com.example.mes.domain.account.dto.AccountCreateRequest;
import com.example.mes.domain.account.dto.AccountUpdateRequest;
import com.example.mes.common.dto.MessageResponse;
import com.example.mes.common.dto.Response;
import com.example.mes.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/accounts")
public class AccountController {

    private final AccountService accountService;

    @GetMapping
    public Response<List<AccountDto>> findAll() {
        List<AccountDto> accounts = accountService.findAllAccount();
        return Response.success(accounts);
    }

    @PostMapping
    public Response<AccountDto> createAccount(@Valid @RequestBody AccountCreateRequest request) {
        AccountDto account = accountService.createAccount(request);
        return Response.success(account);
    }

    @PutMapping("/{accountId}")
    public Response<AccountDto> updateAccount(@PathVariable Long accountId, @RequestBody AccountUpdateRequest request) {
        AccountDto updatedAccount = accountService.update(accountId, request);
        return Response.success(updatedAccount);
    }

    @DeleteMapping("/{accountId}")
    public Response<MessageResponse> deleteAccount(@PathVariable Long accountId) {
        Long id = accountService.delete(accountId);
        return Response.success(new MessageResponse(id, "거래처 삭제 완료"));
    }
}
