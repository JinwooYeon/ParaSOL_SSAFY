package com.parasol.BaaS.service;

import com.parasol.BaaS.api_request.BankConnectionRequest;
import com.parasol.BaaS.api_request.BankLoginRequest;
import com.parasol.BaaS.api_request.LoginRequest;
import com.parasol.BaaS.api_response.BankLoginResultResponse;
import com.parasol.BaaS.db.entity.BankConnection;
import com.parasol.BaaS.db.entity.User;
import com.parasol.BaaS.db.repository.BankConnectionRepository;
import com.parasol.BaaS.modules.BankLoginRequestFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.transaction.Transactional;
import java.util.Objects;

@Service
public class BankConnectionService {

    @Autowired
    private BankLoginRequestFactory bankLoginRequestFactory;

    @Autowired
    private BankConnectionRepository bankConnectionRepository;

    @Transactional
    public void addBankConnection(User user, BankConnectionRequest request) throws IllegalArgumentException, NullPointerException {
        String bankName = request.getBankName();
        String id = request.getId();
        String password = request.getPassword();

        if (
                bankName.isBlank() ||
                id.isBlank() ||
                password.isBlank()
        )
            throw new IllegalArgumentException();

        BankLoginRequest in_request = BankLoginRequest.builder()
                .id(request.getId())
                .password(request.getPassword())
                .build();

        BankLoginResultResponse response = bankLoginRequestFactory.create(in_request);
        if (response.getIsSuccess()) {
            BankConnection bankConnection = new BankConnection();
            bankConnection.setBankName(bankName);
            bankConnection.setBankId(id);
            bankConnection.setBankPassword(password);
            bankConnection.setUser(user);

            bankConnectionRepository.save(bankConnection);
        }
    }

}
