package com.example.mes.common.login.service;

import com.example.mes.common.exception.MesAppException;
import com.example.mes.domain.user.User;
import com.example.mes.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import static com.example.mes.common.exception.ErrorCode.EMAIL_NOT_FOUND;


@Service
@RequiredArgsConstructor
public class LoginService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new MesAppException(EMAIL_NOT_FOUND, EMAIL_NOT_FOUND.getMessage()));

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .roles(user.getUserRole().name())
                .build();
    }
}
