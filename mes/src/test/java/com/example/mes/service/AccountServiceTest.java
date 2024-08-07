package com.example.mes.service;

import com.example.mes.account.dto.AccountCreateRequest;
import com.example.mes.account.dto.AccountDto;
import com.example.mes.account.dto.AccountUpdateRequest;
import com.example.mes.account.entity.Account;
import com.example.mes.account.enums.TransactionType;
import com.example.mes.account.repository.AccountRepository;
import com.example.mes.account.service.AccountService;
import com.example.mes.common.exception.MesAppException;
import com.example.mes.fixture.AccountInfoFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.example.mes.common.exception.ErrorCode.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @InjectMocks
    private AccountService accountService;

    @Mock
    private AccountRepository accountRepository;

    Account account = AccountInfoFixture.get();

    @Test
    @DisplayName("거래처 등록 성공")
    void createAccount() {

        // given
        AccountCreateRequest request = AccountCreateRequest.builder()
                .accountName("이버푸드")
                .accountTel("010-2345-2515")
                .transactionType(TransactionType.입고)
                .businessNumber("220-51-23451")
                .representativeName("허영돈")
                .note(null)
                .build();


        given(accountRepository.save(any()))
                .willReturn(account);

        // when
        AccountDto dto = accountService.createAccount(request);

        // then
        assertThat(dto.getId()).isEqualTo(account.getId());

    }

    @Test
    @DisplayName("거래처 등록 실패 - 권한 없음")
    void createAccountFail01() {
        // given
        AccountCreateRequest request = AccountCreateRequest.builder()
                .accountName("이버푸드")
                .accountTel("010-2345-2515")
                .transactionType(TransactionType.입고)
                .businessNumber("220-51-23451")
                .representativeName("허영돈")
                .note(null)
                .build();

        given(accountRepository.save(any()))
                .willThrow(new MesAppException(INVALID_PERMISSION, INVALID_PERMISSION.getMessage()));

        // when & then
        assertThatThrownBy(() -> accountService.createAccount(request))
                .isExactlyInstanceOf(MesAppException.class)
                .hasMessage(INVALID_PERMISSION.getMessage());

    }

    @Test
    @DisplayName("거래처 수정 성공")
    void updateAccount() {

        // given
        AccountUpdateRequest request = AccountUpdateRequest.builder()
                .accountName("new 이버푸드")
                .accountTel("010-2345-2515")
                .transactionType(TransactionType.입고)
                .businessNumber("220-51-23451")
                .representativeName("허영돈")
                .note(null)
                .build();


        given(accountRepository.findById(any(Long.class)))
                .willReturn(Optional.of(account));

        // when
        AccountDto updatedAccount = accountService.update(account.getId(), request);

        // then
        assertThat(updatedAccount.getId()).isEqualTo(account.getId());
        assertThat(updatedAccount.getAccountName()).isEqualTo(request.getAccountName());

    }


    @Test
    @DisplayName("거래처 수정 실패 - 해당 거래처 X")
    void updateAccountFail01() {
        // given
        AccountUpdateRequest request = AccountUpdateRequest.builder()
                .accountName("NEW 이버푸드")
                .accountTel("010-2345-2515")
                .transactionType(TransactionType.입고)
                .businessNumber("220-51-23451")
                .representativeName("허영돈")
                .note(null)
                .build();

        given(accountRepository.findById(any(Long.class)))
                .willReturn(Optional.empty());

        // when & then
        assertThatThrownBy(() -> accountService.update(account.getId(), request))
                .isExactlyInstanceOf(MesAppException.class)
                .hasMessage(ACCOUNT_NOT_FOUND.getMessage());

    }

    @Test
    @DisplayName("거래처 삭제 성공")
    void deleteAccount() {

        // given
        given(accountRepository.findById(any(Long.class)))
                .willReturn(Optional.of(account));

        // when
        Long accountId = accountService.delete(account.getId());

        // then
        assertThat(accountId).isEqualTo(account.getId());

    }

    @Test
    @DisplayName("거래처 삭제 실패 - 해당 거래처 X")
    void deleteAccountFail01() {

        // given
        given(accountRepository.findById(any(Long.class)))
                .willReturn(Optional.empty());

        // when & then
        assertThatThrownBy(() -> accountService.delete(account.getId()))
                .isExactlyInstanceOf(MesAppException.class)
                .hasMessage(ACCOUNT_NOT_FOUND.getMessage());

    }
}