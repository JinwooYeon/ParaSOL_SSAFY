package com.parasol.BaaS.service;

import com.parasol.BaaS.api_model.AccountInfo;
import com.parasol.BaaS.api_model.BankInfo;
import com.parasol.BaaS.api_model.PayHistoryItem;
import com.parasol.BaaS.api_request.*;
import com.parasol.BaaS.api_response.*;
import com.parasol.BaaS.auth.jwt.UserDetail;
import com.parasol.BaaS.db.entity.PayHistory;
import com.parasol.BaaS.db.entity.PayLedger;
import com.parasol.BaaS.db.entity.User;
import com.parasol.BaaS.db.repository.PayHistoryRepository;
import com.parasol.BaaS.db.repository.PayLedgerRepository;
import com.parasol.BaaS.db.repository.UserRepository;
import com.parasol.BaaS.enums.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class PayService {
    @Autowired
    private AccountService accountService;

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

        PayLedger payLedger = payLedgerRepository.findByOwnerUserId(id)
                .orElseThrow(NoSuchElementException::new);

        return Mono.just(
                PayInfoResponse.builder()
                        .id(id)
                        .balance(payLedger.getBalance())
                        .bankInfo(
                                BankInfo.builder()
                                        .bankName(payLedger.getAccount().getBankName())
                                        .bankNum(payLedger.getAccount().getBankAccountNumber())
                                        .build()
                        )
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

        String transactionTo = request.getTransactionTo();

        User from = userRepository.findByUserId(id)
                .orElseThrow(NoSuchElementException::new);
        User to = userRepository.findByUserId(transactionTo)
                .orElseThrow(NoSuchElementException::new);

        PayLedger fromPayLedger = payLedgerRepository.findByOwnerUserId(id)
                .orElseThrow(IllegalStateException::new);


        PayLedger toPayLedger = payLedgerRepository.findByOwnerUserId(transactionTo)
                .orElseThrow(IllegalStateException::new);

        Long price = request.getPrice();

        LocalDateTime now = LocalDateTime.now();
        // 보내는 사람 계좌 잔액 차감, 거래 내역 추가
        fromPayLedger.setBalance(fromPayLedger.getBalance() - price);
        PayHistory fromPayHistory = PayHistory.builder()
                .user(from)
                .txDatetime(now)
                .txOpponent(to.getUserName())
                .amount(price)
                .type(TransactionType.WITHDRAW)
                .build();

        payLedgerRepository.save(fromPayLedger);
        payHistoryRepository.save(fromPayHistory);


        // 받는 사람 계좌 잔액 차증, 거래 내역 추가
        toPayLedger.setBalance(fromPayLedger.getBalance() + price);
        PayHistory toPayHistory = PayHistory.builder()
                .user(to)
                .txDatetime(now)
                .txOpponent(from.getUserName())
                .amount(price)
                .type(TransactionType.DEPOSIT)
                .build();

        payLedgerRepository.save(toPayLedger);
        payHistoryRepository.save(toPayHistory);

        return Mono.just(
                PayTransactionResponse.builder()
                        .balance(fromPayLedger.getBalance())
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

        PayLedger payLedger = payLedgerRepository.findByOwnerUserId(id)
                .orElseThrow(IllegalStateException::new);

        if(payLedger.getAccount() == null) {
            throw new IllegalArgumentException("주거래계좌 등록");
        }

        accountService.withdraw(
                WithdrawRequest.builder()
                        .authentication(authentication)
                        .bankName(payLedger.getAccount().getBankName())
                        .bankAccountPassword("") // TODO : 비밀번호 필요합니다
                        .amount(request.getPrice())
                        .accountFrom(
                                AccountInfo.builder()
                                        .accountNumber(payLedger.getAccount().getBankAccountNumber())
                                        .build()
                        )
                        .nameTo("ParaSOL pay")
                        .build()
        );

        payLedger.setBalance(payLedger.getBalance() + request.getPrice());
        payLedgerRepository.save(payLedger);

        return Mono.just(
                PayChargeResponse.builder()
                        .balance(payLedger.getBalance())
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

        PayLedger payLedger = payLedgerRepository.findByOwnerUserId(id)
                .orElseThrow(IllegalStateException::new);

        if(payLedger.getAccount() == null) {
            throw new IllegalArgumentException("주거래계좌 등록");
        }

        accountService.deposit(
                DepositRequest.builder()
                        .bankName("SBJ")
                        .amount(request.getPrice())
                        .nameFrom("ParaSOL pay")
                        .accountTo(
                                AccountInfo.builder()
                                        .accountNumber(payLedger.getAccount().getBankAccountNumber())
                                        .build()
                        )
                        .build()
        );

        payLedger.setBalance(payLedger.getBalance() - request.getPrice());
        payLedgerRepository.save(payLedger);


        return Mono.just(
                PayWithdrawResponse.builder()
                        .balance(payLedger.getBalance())
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

        PayLedger payLedger = payLedgerRepository.findByOwnerUserId(id)
                .orElseThrow(IllegalStateException::new);

        List<PayHistory> list = payHistoryRepository.findByUser_UserId(id);

        return Mono.just(
                PayHistoryResponse.builder()
                        .total(Long.valueOf(list.size()))
                        .data(list.stream().map(payHistory -> {
                            String formatPrice = String.valueOf(payHistory.getAmount()).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",");
                            // 페이 충전일 때는 +
                            if (payHistory.getType().equals(TransactionType.DEPOSIT)) {
                                return PayHistoryItem.builder()
                                        .id(payHistory.getTxDatetime())
                                        .title(payHistory.getTxOpponent())
                                        .price("+" + formatPrice)
                                        .build();
                            } else {
                                // 페이 송금, 출금일 때는 -
                                return PayHistoryItem.builder()
                                        .id(payHistory.getTxDatetime())
                                        .title(payHistory.getTxOpponent())
                                        .price("-" + formatPrice)
                                        .build();
                            }
                        }).collect(Collectors.toList()))
                        .build()
        );
    }
}
