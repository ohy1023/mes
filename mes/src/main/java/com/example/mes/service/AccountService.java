package com.example.mes.service;

import com.example.mes.domain.account.dto.AccountDto;
import com.example.mes.domain.account.dto.AccountCreateRequest;
import com.example.mes.domain.account.dto.AccountUpdateRequest;
import com.example.mes.domain.account.Account;
import com.example.mes.domain.account.repository.AccountRepository;
import com.example.mes.common.exception.MesAppException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
        String accountCode = generateAccountCode();
        log.info("code:{}", accountCode);
        log.info("name:{}", request.getAccountName());
        return AccountDto.toAccountDto(accountRepository.save(request.toEntity(accountCode)));
    }

    @Transactional
    public AccountDto update(Long accountId, AccountUpdateRequest request) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> {
                    throw new MesAppException(ACCOUNT_NOT_FOUND, ACCOUNT_NOT_FOUND.getMessage());
                });

        account.updateAccount(request);

        return AccountDto.toAccountDto(account);
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

    private String generateAccountCode() {
        String prefix = "ACCOUT";
        Optional<Account> latestAccount = accountRepository.findFirstByOrderByCreatedAtDesc();
        int sequence = 1;

        if (latestAccount.isPresent()) {
            String latestCode = latestAccount.get().getAccountCode();
            String[] parts = latestCode.split("-");
            sequence = Integer.parseInt(parts[1]) + 1;
        }

        return String.format("%s-%04d", prefix, sequence);
    }
}
