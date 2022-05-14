package com.parasol.core.service;

import com.parasol.core.api_model.BankUserCreateRequest;
import com.parasol.core.api_model.BankUserCreateResponse;
import com.parasol.core.api_model.BankUserLoginRequest;
import com.parasol.core.api_model.BankUserLoginResponse;
import com.parasol.core.entity.BankUser;
import com.parasol.core.entity.Client;
import com.parasol.core.repository.BankUserRepository;
import com.parasol.core.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
    public BankUserCreateResponse createBankUser(@Valid BankUserCreateRequest request) throws IllegalStateException{
        String id = UUID.randomUUID().toString();
        String username = request.getId();
        String password = request.getPassword();

        BankUser bankUser = BankUser.builder()
                .id(id)
                .username(username)
                .password(password)
                .build();

        Client client = clientRepository.findByNameAndResidentNumber(request.getName(), request.getResidentNumber())
                .orElseThrow(() -> {
                    throw new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            "BankUserService :: createBankUser :: client is null"
                    );
                });

        bankUser.setClient(client);

        return BankUserCreateResponse.builder()
                .id(id)
                .build();
    }

    public BankUserLoginResponse login(@Valid BankUserLoginRequest request) throws ResponseStatusException {
        BankUser bankUser = bankUserRepository.findByUsername(request.getId())
                .orElseThrow(() -> {
                    throw new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            "BankUserService :: login :: bankUser does not exist"
                    );
                });

        if (!bankUser.getPassword().equals(request.getPassword())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "BankUserService :: login :: password is not matched"
            );
        }

        Client client = bankUser.getClient();

        if (client == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "BankUserService :: login :: client does not exist"
            );
        }

        return BankUserLoginResponse.builder()
                .cusno(client.getId())
                .build();
    }
}
