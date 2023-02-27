package com.example.mes.account.service;

import com.example.mes.account.dto.AccountDto;
import com.example.mes.account.dto.AccountCreateRequest;
import com.example.mes.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    @Transactional(readOnly = true)
    public List<AccountDto> findAllAccount() {
        return AccountDto.toDtoList(accountRepository.findAll());
    }

    @Transactional
    public AccountDto createAccount(AccountCreateRequest request) {
        log.info("code:{}",request.getAccountCode());
        log.info("name:{}",request.getAccountName());
        return AccountDto.toAccountDto(accountRepository.save(request.toEntity()));
    }

}
