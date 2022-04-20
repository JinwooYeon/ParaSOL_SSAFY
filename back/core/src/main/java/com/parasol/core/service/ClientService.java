package com.parasol.core.service;

import com.parasol.core.entity.Client;
import com.parasol.core.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public Long create(Client client) {
        clientRepository.save(client);
        return client.getId();
    }

    public Client findById(Long id) {
        Optional<Client> result = clientRepository.findById(id);
        return result.orElse(null);
    }
}
