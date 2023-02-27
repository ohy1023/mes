package com.example.mes.account.service;

import com.example.mes.account.dto.AccountDto;
import com.example.mes.account.dto.AccountCreateRequest;
import com.example.mes.account.dto.AccountUpdateRequest;
import com.example.mes.account.entity.Account;
import com.example.mes.account.repository.AccountRepository;
import com.example.mes.common.exception.MesAppException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.mes.common.exception.ErrorCode.*;


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
        log.info("code:{}", request.getAccountCode());
        log.info("name:{}", request.getAccountName());
        return AccountDto.toAccountDto(accountRepository.save(request.toEntity()));
    }

    @Transactional
    public Long update(Long accountId, AccountUpdateRequest request) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> {
                    throw new MesAppException(ACCOUNT_NOT_FOUND, ACCOUNT_NOT_FOUND.getMessage());
                });

        account.updateAccount(request.getAccountCode(), request.getAccountName());

        return account.getId();
    }

    @Transactional
    public Long delete(Long accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> {
                    throw new MesAppException(ACCOUNT_NOT_FOUND, ACCOUNT_NOT_FOUND.getMessage());
                });

        accountRepository.deleteById(account.getId());

        return account.getId();
    }
}
