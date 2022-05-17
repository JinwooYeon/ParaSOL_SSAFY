package com.parasol.BaaS.service;

import com.parasol.BaaS.api_model.AccountInfo;
import com.parasol.BaaS.api_model.BankInfo;
import com.parasol.BaaS.api_model.PayHistoryItem;
import com.parasol.BaaS.api_param.WithdrawParam;
import com.parasol.BaaS.api_request.*;
import com.parasol.BaaS.api_response.*;
import com.parasol.BaaS.auth.jwt.UserDetail;
import com.parasol.BaaS.db.entity.BioInfo;
import com.parasol.BaaS.db.entity.PayHistory;
import com.parasol.BaaS.db.entity.PayLedger;
import com.parasol.BaaS.db.entity.User;
import com.parasol.BaaS.db.repository.BioInfoRepository;
import com.parasol.BaaS.db.repository.PayHistoryRepository;
import com.parasol.BaaS.db.repository.PayLedgerRepository;
import com.parasol.BaaS.db.repository.UserRepository;
import com.parasol.BaaS.enums.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    @Autowired
    private BioInfoRepository bioInfoRepository;

    @Autowired
    private UserService userService;

    public Mono<PayInfoResponse> getPayInfo(
            PayInfoRequest request
    ) throws IllegalArgumentException {
        Authentication authentication = request.getAuthentication();

        if (authentication == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "give me a token");
        }

        UserDetail userDetail = (UserDetail) authentication.getDetails();
        String id = userDetail.getUsername();

        if (!StringUtils.hasText(id)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        PayLedger payLedger = payLedgerRepository.findByOwnerUserId(id)
                .orElseThrow(() -> { throw new ResponseStatusException(HttpStatus.NOT_FOUND); } );

//        if (payLedger.getBankAccountNumber() == null) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "주거래계좌 등록 필요");
//        }

        Long payLedgerBalance = payLedger.getBalance();
        BankInfo payLedgerMainAccount = BankInfo.builder()
                .bankName(payLedger.getBankName())
                .bankNum(payLedger.getBankAccountNumber())
                .build();

        return Mono.just(
                PayInfoResponse.builder()
                        .id(id)
                        .balance(payLedgerBalance)
                        .bankInfo(payLedgerMainAccount)
                        .build()
        );
    }

    @Transactional
    public Mono<PayTransactionResponse> doPayTransact(
            PayTransactionRequest request
    ) throws IllegalArgumentException {
        Authentication authentication = request.getAuthentication();

        if (authentication == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "give me a token");
        }

        UserDetail userDetail = (UserDetail) authentication.getDetails();
        String id = userDetail.getUsername();

        if (!StringUtils.hasText(id)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        String transactionTo = request.getTransactionTo();

        User from = userRepository.findByUserId(id)
                .orElseThrow(() -> { throw new ResponseStatusException(HttpStatus.NOT_FOUND); } );
        User to = userRepository.findByUserId(transactionTo)
                .orElseThrow(() -> { throw new ResponseStatusException(HttpStatus.NOT_FOUND); } );

        PayLedger fromPayLedger = payLedgerRepository.findByOwnerUserId(id)
                .orElseThrow(() -> { throw new ResponseStatusException(HttpStatus.NOT_FOUND); } );
        PayLedger toPayLedger = payLedgerRepository.findByOwnerUserId(transactionTo)
                .orElseThrow(() -> { throw new ResponseStatusException(HttpStatus.NOT_FOUND); } );

//        if(fromPayLedger.getBankAccountNumber() == null) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "주거래계좌 등록");
//        }
//
//        if(toPayLedger.getBankAccountNumber() == null) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "송금 계좌 확인");
//        }

        Long price = request.getPrice();
        LocalDateTime now = LocalDateTime.now();

        Long beforeFromBalance = fromPayLedger.getBalance();
        Long beforeToBalance = toPayLedger.getBalance();

        Long afterFromBalance = beforeFromBalance - price;
        Long afterToBalance = beforeToBalance + price;

        if (price <= 0L) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        if (afterFromBalance < 0L) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        if (afterToBalance < 0L) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        // 보내는 사람 계좌 잔액 차감, 거래 내역 추가
        fromPayLedger.setBalance(afterFromBalance);
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
        toPayLedger.setBalance(afterToBalance);
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
                        .balance(afterFromBalance)
                        .build()
        );
    }

    @Transactional
    public Mono<PayChargeResponse> doPayCharge(
            PayChargeRequest request
    ) throws IllegalArgumentException {
        Authentication authentication = request.getAuthentication();

        if (authentication == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "give me a token");
        }

        UserDetail userDetail = (UserDetail) authentication.getDetails();
        String id = userDetail.getUsername();

        if (!StringUtils.hasText(id)) {
            throw new IllegalStateException();
        }

        User user = userRepository.findByUserId(id)
                .orElseThrow(() -> { throw new ResponseStatusException(HttpStatus.NOT_FOUND); } );

        PayLedger payLedger = payLedgerRepository.findByOwnerUserId(id)
                .orElseThrow(() -> { throw new ResponseStatusException(HttpStatus.NOT_FOUND); } );

        Long price = request.getPrice();

        if(payLedger.getBankAccountNumber() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "주거래계좌 등록");
        }

        if (price <= 0L) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        WithdrawRequest withdrawRequest = WithdrawRequest.builder()
                .authentication(authentication)
                .bankName(payLedger.getBankName())
                .bankAccountPassword("1234") // TODO : 비밀번호 필요합니다
                .amount(price)
                .accountFrom(
                        AccountInfo.builder()
                                .accountNumber(payLedger.getBankAccountNumber())
                                .build()
                )
                .nameTo("ParaSOL Pay")
                .build();

        return accountService.withdraw(withdrawRequest)
                .doOnSuccess(result -> {
                    Long beforeBalance = payLedger.getBalance();
                    Long afterBalance = beforeBalance + price;

                    payLedger.setBalance(afterBalance);
                    payLedgerRepository.save(payLedger);

                    PayHistory payHistory = PayHistory.builder()
                            .user(user)
                            .txDatetime(LocalDateTime.now())
                            .txOpponent("ParaSOL pay")
                            .amount(price)
                            .type(TransactionType.DEPOSIT)
                            .build();

                    payHistoryRepository.save(payHistory);
                })
                .map(result -> PayChargeResponse.builder()
                        .balance(payLedger.getBalance())
                        .build());
    }

    @Transactional
    public Mono<PayWithdrawResponse> doPayWithdraw(
            PayWithdrawRequest request
    ) throws IllegalArgumentException {
        Authentication authentication = request.getAuthentication();

        if (authentication == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "give me a token");
        }

        UserDetail userDetail = (UserDetail) authentication.getDetails();
        String id = userDetail.getUsername();

        if (!StringUtils.hasText(id)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        User user = userRepository.findByUserId(id)
                .orElseThrow(() -> { throw new ResponseStatusException(HttpStatus.NOT_FOUND); } );

        PayLedger payLedger = payLedgerRepository.findByOwnerUserId(id)
                .orElseThrow(() -> { throw new ResponseStatusException(HttpStatus.NOT_FOUND); } );

        Long price = request.getPrice();

        if(payLedger.getBankAccountNumber() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "주거래계좌 등록");
        }

        if (price <= 0L) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        DepositRequest depositRequest = DepositRequest.builder()
                .bankName(payLedger.getBankName())
                .amount(price)
                .nameFrom("ParaSOL pay")
                .accountTo(
                        AccountInfo.builder()
                                .accountNumber(payLedger.getBankAccountNumber())
                                .build()
                )
                .build();

        return accountService.deposit(depositRequest)
                .doOnSuccess(result -> {
                    Long beforeBalance = payLedger.getBalance();
                    Long afterBalance = beforeBalance - price;

                    payLedger.setBalance(afterBalance);
                    payLedgerRepository.save(payLedger);

                    PayHistory payHistory = PayHistory.builder()
                            .user(user)
                            .txDatetime(LocalDateTime.now())
                            .txOpponent("ParaSOL pay")
                            .amount(price)
                            .type(TransactionType.WITHDRAW)
                            .build();

                    payHistoryRepository.save(payHistory);
                })
                .map(result -> PayWithdrawResponse.builder()
                                .balance(payLedger.getBalance())
                                .build());
    }

    public Mono<PayHistoryResponse> getPayHistory(
            PayHistoryRequest request
    ) throws IllegalArgumentException {
        Authentication authentication = request.getAuthentication();

        if (authentication == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "give me a token");
        }

        UserDetail userDetail = (UserDetail) authentication.getDetails();
        String id = userDetail.getUsername();

        if (!StringUtils.hasText(id)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        PayLedger payLedger = payLedgerRepository.findByOwnerUserId(id)
                .orElseThrow(() -> { throw new ResponseStatusException(HttpStatus.NOT_FOUND); } );

        if(payLedger.getBankAccountNumber() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "주거래계좌 등록");
        }

        List<PayHistory> list = payHistoryRepository.findByUser_UserId(id);

        return Mono.just(
                PayHistoryResponse.builder()
                        .total(Long.valueOf(list.size()))
                        .data(list.stream().map(payHistory -> {
                            String formatPrice = String.valueOf(payHistory.getAmount()).replaceAll("\\B(?=(\\d{3})+(?!\\d))", ",");
                            String txTime = payHistory.getTxDatetime().format(DateTimeFormatter.ofPattern(
                                    "yyyy년 MM월 dd일 HH시 mm분 ss초"
                            ));
                            // 페이 충전일 때는 +
                            if (payHistory.getType().equals(TransactionType.DEPOSIT)) {
                                return PayHistoryItem.builder()
                                        .id(txTime)
                                        .title(payHistory.getTxOpponent())
                                        .price("+" + formatPrice)
                                        .build();
                            } else {
                                // 페이 송금, 출금일 때는 -
                                return PayHistoryItem.builder()
                                        .id(txTime)
                                        .title(payHistory.getTxOpponent())
                                        .price("-" + formatPrice)
                                        .build();
                            }
                        }).collect(Collectors.toList()))
                    .build()
        );
    }

    public Mono<PayQueryTwoFactorResponse> queryTwoFactor(
            PayQueryTwoFactorRequest request
    ) {
        Authentication authentication = request.getAuthentication();

        if (authentication == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "give me a token");
        }

        UserDetail userDetail = (UserDetail) authentication.getDetails();
        String id = userDetail.getUsername();

        if (!StringUtils.hasText(id)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        boolean isOtpRegistered = false;
        boolean isBioRegistered = bioInfoRepository.findByOwnerUserId(id).isPresent();

        return Mono.just(
                PayQueryTwoFactorResponse.builder()
                        .otp(isOtpRegistered)
                        .bio(isBioRegistered)
                        .build()
        );
    }

    public Mono<PayRegisterBioResponse> registerBio(
            PayRegisterBioRequest request
    ) {
        Authentication authentication = request.getAuthentication();

        if (authentication == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "give me a token");
        }

        UserDetail userDetail = (UserDetail) authentication.getDetails();
        String id = userDetail.getUsername();

        if (!StringUtils.hasText(id)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        User user = userService.getUserByUserId(id);
        String serial_no = request.getId();
        String model = request.getModel();

        if (!StringUtils.hasText(serial_no)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        if (!StringUtils.hasText(model)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        if (bioInfoRepository.findByOwnerUserId(id).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        BioInfo bioInfo = BioInfo.builder()
                .owner(user)
                .serial_no(serial_no)
                .model(model)
                .build();

        bioInfoRepository.save(bioInfo);

        return Mono.just(
                PayRegisterBioResponse.builder()
                        .isSuccess(true)
                        .build()
        );
    }
}
