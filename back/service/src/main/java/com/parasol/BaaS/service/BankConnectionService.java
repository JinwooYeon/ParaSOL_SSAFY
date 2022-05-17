package com.parasol.BaaS.service;

import com.parasol.BaaS.api_model.AccountInfo;
import com.parasol.BaaS.api_param.BankLoginParam;
import com.parasol.BaaS.api_param.QueryAccountListParam;
import com.parasol.BaaS.api_request.BankConnectionRequest;
import com.parasol.BaaS.api_request.QueryAccountListRequest;
import com.parasol.BaaS.api_response.BankConnectionResponse;
import com.parasol.BaaS.api_result.BankLoginResult;
import com.parasol.BaaS.api_result.QueryAccountListResult;
import com.parasol.BaaS.db.entity.BankConnection;
import com.parasol.BaaS.db.entity.PayLedger;
import com.parasol.BaaS.db.entity.User;
import com.parasol.BaaS.db.repository.BankConnectionRepository;
import com.parasol.BaaS.db.repository.PayLedgerRepository;
import com.parasol.BaaS.modules.BankLoginRequestFactory;
import com.parasol.BaaS.modules.QueryAccountListRequestFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import javax.transaction.Transactional;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

@Service
public class BankConnectionService {

    @Autowired
    private BankLoginRequestFactory bankLoginRequestFactory;

    @Autowired
    private BankConnectionRepository bankConnectionRepository;

    @Autowired
    private PayLedgerRepository payLedgerRepository;

    @Autowired
    private QueryAccountListRequestFactory accountListRequestFactory;

    @Transactional
    public Mono<BankConnectionResponse> addBankConnection(User user, BankConnectionRequest request) throws IllegalArgumentException, NullPointerException, NoSuchElementException {
        Long userSeq = user.getUserSeq();
        String bankName = request.getBankName();
        String id = request.getId();
        String password = request.getPassword();

        if (
                bankName == null || bankName.isEmpty() ||
                        id == null || id.isEmpty() ||
                        password == null || password.isEmpty()
        )
            throw new IllegalArgumentException();

        bankConnectionRepository.findByUser_UserSeqAndBankName(userSeq, bankName)
                .ifPresent(bankConnection -> { throw new ResponseStatusException(HttpStatus.BAD_REQUEST);});

        BankLoginParam param = BankLoginParam.builder()
                .id(request.getId())
                .password(request.getPassword())
                .build();

        return bankLoginRequestFactory.create(param)
                .doOnError(throwable -> {
                    WebClientResponseException ex = (WebClientResponseException) throwable;
                    throw new ResponseStatusException(ex.getStatusCode());
                })
                .flatMap(result -> Mono.just(
                        BankConnectionResponse.builder()
                                .isSuccess(result.getIsSuccess())
                                .build()
                ))
                .filter(BankConnectionResponse::getIsSuccess)
                .doOnSuccess(result ->
                {
                    BankConnection bankConnection = new BankConnection();
                    bankConnection.setBankName(bankName);
                    bankConnection.setBankId(id);
                    bankConnection.setBankPassword(password);
                    bankConnection.setUser(user);

                    bankConnectionRepository.save(bankConnection);
                })
                .filter(result -> {
                    Optional<PayLedger> ledger = payLedgerRepository.findByOwnerUserId(user.getUserId());
                    if (ledger.isEmpty()) return false;

                    boolean hasId = StringUtils.hasText(ledger.get().getBankName());
                    boolean hasAccountNumber = StringUtils.hasText(ledger.get().getBankAccountNumber());
                    return !hasId || !hasAccountNumber;
                })
                .flatMap(result ->
                {
                    PayLedger ledger = payLedgerRepository.findByOwnerUserId(user.getUserId())
                            .orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "invalid account"));

                    QueryAccountListParam queryParam = QueryAccountListParam.builder()
                            .id(id)
                            .password(password)
                            .build();

                    return accountListRequestFactory.create(queryParam)
                            .map(queryResult -> {
                                if (
                                    queryResult.getAccounts() != null && queryResult.getAccounts().size() > 0
                                ) {
                                    AccountInfo bankAccountInfo = queryResult.getAccounts().get(0);
                                    String bankAccountNumber = bankAccountInfo.getAccountNumber();

                                    if (StringUtils.hasText(bankAccountNumber)) {
                                        ledger.setBankName(bankName);
                                        ledger.setBankAccountNumber(bankAccountNumber);
                                        payLedgerRepository.save(ledger);
                                    }
                                }

                                return BankConnectionResponse.builder()
                                                .isSuccess(result.getIsSuccess())
                                                .build();
                            });
                });
    }

    public Mono<BankConnectionResponse> checkBankConnection(User user, BankConnectionRequest request) throws IllegalArgumentException, NullPointerException, NoSuchElementException {
        Long userSeq = user.getUserSeq();
        String bankName = request.getBankName();

        if (
                bankName == null || bankName.isEmpty()
        )
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        BankConnection bankConnection = bankConnectionRepository.findByUser_UserSeqAndBankName(userSeq, bankName)
                .orElseThrow(() -> { throw new ResponseStatusException(HttpStatus.NOT_FOUND); });

        return Mono.just(
                BankConnectionResponse.builder()
                        .isSuccess(true)
                        .build()
        );
    }

    @Transactional
    public Mono<BankConnectionResponse> deleteBankConnection(User user, BankConnectionRequest request) throws IllegalArgumentException, NullPointerException, NoSuchElementException {
        Long userSeq = user.getUserSeq();
        String bankName = request.getBankName();

        if (
                bankName == null || bankName.isEmpty()
        )
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        BankConnection bankConnection = bankConnectionRepository.findByUser_UserSeqAndBankName(userSeq, bankName)
                .orElseThrow(() -> { throw new ResponseStatusException(HttpStatus.NOT_FOUND); });

        bankConnectionRepository.delete(bankConnection);

        Optional<PayLedger> payLedgerObj = payLedgerRepository.findByOwnerUserId(user.getUserId());

        if (payLedgerObj.isPresent()) {
            PayLedger ledger = payLedgerObj.get();
            ledger.setBankName(null);
            ledger.setBankAccountNumber(null);

            payLedgerRepository.save(ledger);
        }

        return Mono.just(
                BankConnectionResponse.builder()
                    .isSuccess(true)
                    .build()
        );
    }

}
