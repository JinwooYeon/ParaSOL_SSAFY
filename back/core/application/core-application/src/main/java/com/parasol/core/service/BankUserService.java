package com.parasol.core.service;

import com.parasol.core.api_model.BankUserCreateRequest;
import com.parasol.core.api_model.BankUserLoginRequest;
import com.parasol.core.api_model.BankUserLoginResponse;
import com.parasol.core.entity.BankUser;
import com.parasol.core.entity.Client;
import com.parasol.core.repository.BankUserRepository;
import com.parasol.core.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

@Service
public class BankUserService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private BankUserRepository bankUserRepository;

    @Transactional
    public String createBankUser(@Valid BankUserCreateRequest request) throws IllegalStateException{
        BankUser bankUser = BankUser.builder()
                .id(UUID.randomUUID().toString())
                .username(request.getId())
                .password(request.getPassword())
                .build();

        Optional<Client> client = clientRepository.findByNameAndResidentNumber(request.getName(), request.getResidentNumber());
        client.ifPresentOrElse(bankUser::setClient, () -> { throw new IllegalStateException("해당 고객이 없습니다."); });

        return bankUserRepository.save(bankUser).getId();
    }

    public BankUserLoginResponse login(@Valid BankUserLoginRequest request) throws IllegalStateException {
        BankUserLoginResponse response = BankUserLoginResponse.builder()
                .isSuccess(false)
                .build();

        Optional<BankUser> bankUser = bankUserRepository.findByUsername(request.getId());
        bankUser.ifPresentOrElse(b -> {
            if (!b.getPassword().equals(request.getPassword()))
                throw new IllegalStateException("비밀번호가 틀립니다");

            Client client = b.getClient();
            response.setSuccess(true);
            response.setCusno(client.getId());
        }, () -> { throw new IllegalStateException("해당 아이디를 찾을 수 없습니다."); });

        return response;
    }
}