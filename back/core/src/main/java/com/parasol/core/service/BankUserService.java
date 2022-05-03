package com.parasol.core.service;

import com.parasol.core.api_model.BankUserCreateRequest;
import com.parasol.core.entity.BankUser;
import com.parasol.core.entity.Client;
import com.parasol.core.repository.BankUserRepository;
import com.parasol.core.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

@Service
public class BankUserService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private BankUserRepository bankUserRepository;

    public String create(@Valid BankUserCreateRequest request) throws IllegalStateException{
        BankUser bankUser = BankUser.builder()
                .id(UUID.randomUUID().toString())
                .username(request.getId())
                .password(request.getPassword())
                .build();

        Optional<Client> client = clientRepository.findByNameAndResidentNumber(request.getName(), request.getResidentNumber());
        client.ifPresentOrElse(c -> c.setBankUser(bankUser), () -> { throw new IllegalStateException("해당 고객이 없습니다."); });

        return bankUserRepository.save(bankUser).getId();
    }
}
