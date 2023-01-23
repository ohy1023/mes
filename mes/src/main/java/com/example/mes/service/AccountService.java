package com.example.mes.service;

import com.example.mes.domain.dto.AccountDto;
import com.example.mes.domain.request.AccountCreateRequest;
import com.example.mes.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    public List<AccountDto> findAllAccount() {
        return AccountDto.toDtoList(accountRepository.findAll());
    }

    public AccountDto createAccount(AccountCreateRequest request) {
        log.info("code:{}",request.getAccountCode());
        log.info("name:{}",request.getAccountName());
        return AccountDto.toAccountDto(accountRepository.save(request.toEntity()));
    }

}
