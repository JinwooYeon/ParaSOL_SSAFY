package com.parasol.BaaS.service;

import com.parasol.BaaS.api_param.BankLoginParam;
import com.parasol.BaaS.api_request.BankConnectionRequest;
import com.parasol.BaaS.api_response.BankConnectionResponse;
import com.parasol.BaaS.api_result.BankLoginResult;
import com.parasol.BaaS.db.entity.BankConnection;
import com.parasol.BaaS.db.entity.User;
import com.parasol.BaaS.db.repository.BankConnectionRepository;
import com.parasol.BaaS.modules.BankLoginRequestFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import javax.transaction.Transactional;
import java.util.NoSuchElementException;
import java.util.Objects;

@Service
public class BankConnectionService {

    @Autowired
    private BankLoginRequestFactory bankLoginRequestFactory;

    @Autowired
    private BankConnectionRepository bankConnectionRepository;

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
                });
    }

}
