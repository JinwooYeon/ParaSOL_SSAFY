package com.parasol.BaaS.service;

import com.parasol.BaaS.api_request.*;
import com.parasol.BaaS.api_response.*;
import com.parasol.BaaS.auth.jwt.UserDetail;
import com.parasol.BaaS.db.entity.User;
import com.parasol.BaaS.db.repository.PayHistoryRepository;
import com.parasol.BaaS.db.repository.PayLedgerRepository;
import com.parasol.BaaS.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Mono;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PayService {
    @Autowired
    private PayLedgerRepository payLedgerRepository;

    @Autowired
    private PayHistoryRepository payHistoryRepository;

    @Autowired
    private UserRepository userRepository;

    public Mono<PayInfoResponse> getPayInfo(
            PayInfoRequest request
    ) throws IllegalArgumentException {
        Authentication authentication = request.getAuthentication();

        if (authentication == null) {
            throw new AccessDeniedException("give me a token");
        }

        UserDetail userDetail = (UserDetail) authentication.getDetails();
        String id = userDetail.getUsername();

        if (!StringUtils.hasText(id)) {
            throw new IllegalStateException();
        }

        User user = userRepository.findByUserId(id)
                .orElseThrow(NoSuchElementException::new);

        if (!StringUtils.hasText(id)) {
            throw new IllegalArgumentException();
        }

        return Mono.just(
                PayInfoResponse.builder()
                        .build()
        );
    }

    public Mono<PayTransactionResponse> doPayTransact(
            PayTransactionRequest request
    ) throws IllegalArgumentException {
        Authentication authentication = request.getAuthentication();

        if (authentication == null) {
            throw new AccessDeniedException("give me a token");
        }

        UserDetail userDetail = (UserDetail) authentication.getDetails();
        String id = userDetail.getUsername();

        if (!StringUtils.hasText(id)) {
            throw new IllegalStateException();
        }

        User user = userRepository.findByUserId(id)
                .orElseThrow(NoSuchElementException::new);

        if (!StringUtils.hasText(id)) {
            throw new IllegalArgumentException();
        }

        return Mono.just(
                PayTransactionResponse.builder()
                        .build()
        );
    }

    public Mono<PayChargeResponse> doPayCharge(
            PayChargeRequest request
    ) throws IllegalArgumentException {
        Authentication authentication = request.getAuthentication();

        if (authentication == null) {
            throw new AccessDeniedException("give me a token");
        }

        UserDetail userDetail = (UserDetail) authentication.getDetails();
        String id = userDetail.getUsername();

        if (!StringUtils.hasText(id)) {
            throw new IllegalStateException();
        }

        User user = userRepository.findByUserId(id)
                .orElseThrow(NoSuchElementException::new);

        if (!StringUtils.hasText(id)) {
            throw new IllegalArgumentException();
        }

        return Mono.just(
                PayChargeResponse.builder()
                        .build()
        );
    }

    public Mono<PayWithdrawResponse> doPayWithdraw(
            PayWithdrawRequest request
    ) throws IllegalArgumentException {
        Authentication authentication = request.getAuthentication();

        if (authentication == null) {
            throw new AccessDeniedException("give me a token");
        }

        UserDetail userDetail = (UserDetail) authentication.getDetails();
        String id = userDetail.getUsername();

        if (!StringUtils.hasText(id)) {
            throw new IllegalStateException();
        }

        User user = userRepository.findByUserId(id)
                .orElseThrow(NoSuchElementException::new);

        if (!StringUtils.hasText(id)) {
            throw new IllegalArgumentException();
        }

        return Mono.just(
                PayWithdrawResponse.builder()
                        .build()
        );
    }

    public Mono<PayHistoryResponse> getPayHistory(
            PayHistoryRequest request
    ) throws IllegalArgumentException {
        Authentication authentication = request.getAuthentication();

        if (authentication == null) {
            throw new AccessDeniedException("give me a token");
        }

        UserDetail userDetail = (UserDetail) authentication.getDetails();
        String id = userDetail.getUsername();

        if (!StringUtils.hasText(id)) {
            throw new IllegalStateException();
        }

        User user = userRepository.findByUserId(id)
                .orElseThrow(NoSuchElementException::new);

        if (!StringUtils.hasText(id)) {
            throw new IllegalArgumentException();
        }

        return Mono.just(
                PayHistoryResponse.builder()
                        .build()
        );
    }
}
