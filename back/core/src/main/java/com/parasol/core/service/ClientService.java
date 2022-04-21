package com.parasol.core.service;

import com.parasol.core.entity.Client;
import com.parasol.core.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public String create(String name, String residentNumber) {
        Client client = Client.builder()
                .id(UUID.randomUUID().toString())
                .name(name)
                .residentNumber(residentNumber)
                .build();

        return clientRepository.save(client).getId();
    }

    public Client findById(String id) {
        Optional<Client> result = clientRepository.findById(id);
        return result.orElse(null);
    }

    public Client findByNameAndResidentNumber(String name, String residentNumber) {
        Optional<Client> result = clientRepository.findByNameAndResidentNumber(name, residentNumber);
        return result.orElse(null);
    }
}
