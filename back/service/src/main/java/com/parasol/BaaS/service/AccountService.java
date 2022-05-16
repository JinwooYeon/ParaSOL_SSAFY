package com.parasol.BaaS.service;

import com.parasol.BaaS.api_model.AccountInfo;
import com.parasol.BaaS.api_param.*;
import com.parasol.BaaS.api_request.*;
import com.parasol.BaaS.api_response.*;
import com.parasol.BaaS.auth.jwt.UserDetail;
import com.parasol.BaaS.db.entity.BankConnection;
import com.parasol.BaaS.db.entity.User;
import com.parasol.BaaS.db.repository.BankConnectionRepository;
import com.parasol.BaaS.db.repository.UserRepository;
import com.parasol.BaaS.modules.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.util.NoSuchElementException;

@Service
public class AccountService {

    @Autowired
    QueryAccountBalanceRequestFactory queryAccountBalanceRequestFactory;

    @Autowired
    QueryAccountListRequestFactory queryAccountListRequestFactory;

    @Autowired
    QueryAccountHistoryRequestFactory queryAccountHistoryRequestFactory;

    @Autowired
    DepositRequestFactory depositRequestFactory;

    @Autowired
    WithdrawRequestFactory withdrawRequestFactory;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BankConnectionRepository bankConnectionRepository;

    public Mono<QueryAccountBalanceResponse> getBalance(
            QueryAccountBalanceRequest request
    ) {
        Authentication authentication = request.getAuthentication();

        if (authentication == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        UserDetail userDetail = (UserDetail) authentication.getDetails();
        String id = userDetail.getUsername();

        if (!StringUtils.hasText(id)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        User user = userRepository.findByUserId(id)
                .orElseThrow(() -> { throw new ResponseStatusException(HttpStatus.NOT_FOUND); });

        String bankName = request.getBankName();
        String bankAccountNumber = request.getBankAccountNumber();

        BankConnection bankConnection = getBankConnection(user, bankName);

        if (!bankName.equals("SBJ"))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "We can support SBJ Bank only.");

        QueryAccountBalanceParam param = QueryAccountBalanceParam.builder()
                .accountNumber(bankAccountNumber)
                .id(bankConnection.getBankId())
                .password(bankConnection.getBankPassword())
                .build();

        return queryAccountBalanceRequestFactory.create(param)
                .map(result -> QueryAccountBalanceResponse.builder()
                        .bankName(bankName)
                        .bankAccountNumber(bankAccountNumber)
                        .bankAccountBalance(result.getBalance())
                        .build()
                );
    }

    public Mono<QueryAccountListResponse> getAccountList(
            QueryAccountListRequest request
    ) throws IllegalStateException, IllegalArgumentException, AccessDeniedException, NoSuchElementException {
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
                .orElseThrow(() -> { throw new ResponseStatusException(HttpStatus.NOT_FOUND); });

        String bankName = request.getBankName();

        BankConnection bankConnection = getBankConnection(user, bankName);

        if (!bankName.equals("SBJ"))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "We can support SBJ Bank only.");

        QueryAccountListParam param = QueryAccountListParam.builder()
                .id(bankConnection.getBankId())
                .password(bankConnection.getBankPassword())
                .build();

        return queryAccountListRequestFactory.create(param)
                .map(result -> QueryAccountListResponse.builder()
                        .bankName(bankName)
                        .bankAccounts(result.getAccounts())
                        .build()
                );
    }

    public Mono<QueryAccountHistoryResponse> getAccountHistory(
            QueryAccountHistoryRequest request
    ) throws IllegalStateException, IllegalArgumentException, AccessDeniedException, NoSuchElementException {
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
                .orElseThrow(() -> { throw new ResponseStatusException(HttpStatus.NOT_FOUND); });

        String bankName = request.getBankName();
        String bankAccountNumber = request.getBankAccountNumber();

        BankConnection bankConnection = getBankConnection(user, bankName);

        if (!bankName.equals("SBJ"))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "We can support SBJ Bank only.");

        QueryAccountHistoryParam param = QueryAccountHistoryParam.builder()
                .accountNumber(bankAccountNumber)
                .id(bankConnection.getBankId())
                .password(bankConnection.getBankPassword())
                .build();

        return queryAccountHistoryRequestFactory.create(param)
                .map(result -> QueryAccountHistoryResponse.builder()
                        .bankName(bankName)
                        .bankAccountNumber(bankAccountNumber)
                        .bankAccountHistories(result.getAccountHistories())
                        .build()
                );
    }

    public Mono<DepositResponse> deposit(
            DepositRequest request
    ) throws IllegalStateException, IllegalArgumentException, AccessDeniedException, NoSuchElementException {
        String bankName = request.getBankName();
        Long amount = request.getAmount();
        String nameFrom = request.getNameFrom();
        AccountInfo accountTo = request.getAccountTo();

        if (!bankName.equals("SBJ"))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "We can support SBJ Bank only.");

        DepositParam param = DepositParam.builder()
                .amount(amount)
                .accountTo(accountTo)
                .nameFrom(nameFrom)
                .build();

        return depositRequestFactory.create(param)
                .map(result -> DepositResponse.builder()
                        .amount(amount)
                        .nameFrom(nameFrom)
                        .accountTo(accountTo)
                        .build()
                );
    }

    public Mono<WithdrawResponse> withdraw(
            WithdrawRequest request
    ) throws IllegalStateException, IllegalArgumentException, AccessDeniedException, NoSuchElementException {
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
                .orElseThrow(() -> { throw new ResponseStatusException(HttpStatus.NOT_FOUND); });

        String bankName = request.getBankName();
        String accountPassword = request.getBankAccountPassword();
        Long amount = request.getAmount();
        String nameTo = request.getNameTo();
        AccountInfo accountFrom = request.getAccountFrom();

        BankConnection bankConnection = getBankConnection(user, bankName);

        if (!bankName.equals("SBJ"))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "We can support SBJ Bank only.");

        WithdrawParam param = WithdrawParam.builder()
                .accountPassword(accountPassword)
                .amount(amount)
                .accountFrom(accountFrom)
                .nameTo(nameTo)
                .id(bankConnection.getBankId())
                .password(bankConnection.getBankPassword())
                .build();

        return withdrawRequestFactory.create(param)
                .map(result -> WithdrawResponse.builder()
                        .amount(amount)
                        .nameTo(nameTo)
                        .accountFrom(accountFrom)
                        .build()
                );
    }

    public BankConnection getBankConnection(User user, String bankName) throws IllegalStateException {
        return bankConnectionRepository
                .findByUser_UserSeqAndBankName(user.getUserSeq(), bankName)
                .orElseThrow(() -> { throw new ResponseStatusException(HttpStatus.NOT_FOUND); });
    }

}
