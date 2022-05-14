package com.parasol.core.service;

import com.parasol.core.api_model.ClientCreateRequest;
import com.parasol.core.api_model.ClientCreateResponse;
import com.parasol.core.api_model.QueryClientResponse;
import com.parasol.core.api_model.QueryClientRequest;
import com.parasol.core.entity.Client;
import com.parasol.core.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ValidationService validationService;

    public ClientCreateResponse create(ClientCreateRequest request) {
        String name = request.getName();
        String residentNumber = request.getResidentNumber();

        Client client = Client.builder()
                .name(name)
                .residentNumber(residentNumber)
                .build();
        clientRepository.save(client);

        long id = client.getId();

        return ClientCreateResponse.builder()
                .id(id)
                .build();
    }

    public QueryClientResponse getClient(QueryClientRequest request) {
        Long id = request.getId();

        Client client = this.findById(id);

        String name = client.getName();
        String residentNumber = client.getResidentNumber();

        String maskedName = maskName(name);
        String maskedResidentNumber = maskResidentNumber(residentNumber);

        return QueryClientResponse.builder()
                .id(id)
                .name(maskedName)
                .residentNumber(maskedResidentNumber)
                .build();
    }

    public Client findById(Long id) throws ResponseStatusException {
        return clientRepository.findById(id)
                .orElseThrow(() -> {
                    throw new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            "ClientService :: findById :: queryClient is null"
                    );
                });
    }

    public String maskName(String rawName) {
        String lastName = rawName.substring(0, 0);

        return lastName + "**";
    }

    public String maskResidentNumber(String rawResidentNumber) {
        String birth = rawResidentNumber.substring(0, 6);
        String sex = rawResidentNumber.substring(0, 8);

        return birth + "-" + sex + "******";
    }

    public Client findByNameAndResidentNumber(String name, String residentNumber) {
        Optional<Client> result = clientRepository.findByNameAndResidentNumber(name, residentNumber);

        return validationService.generateClient(result.orElse(new Client()));
    }
}
