package com.parasol.core.service;

import com.parasol.core.api_model.ClientCreateResponse;
import com.parasol.core.entity.Client;
import com.parasol.core.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ValidationService validationService;

    public ClientCreateResponse create(String name, String residentNumber) {
        ClientCreateResponse result = new ClientCreateResponse();
        Client client = Client.builder()
                .name(name)
                .residentNumber(residentNumber)
                .build();
        clientRepository.save(client);

        result.setId(clientRepository.findByNameAndResidentNumber(name, residentNumber).get().getId());
        return result;
    }

    public Client findById(Long id) {
        Optional<Client> result = clientRepository.findById(id);
        return result.orElse(null);
    }

    public Client findByNameAndResidentNumber(String name, String residentNumber) {
        Optional<Client> result = clientRepository.findByNameAndResidentNumber(name, residentNumber);

        return validationService.generateClient(result.orElse(new Client()));
    }
}
