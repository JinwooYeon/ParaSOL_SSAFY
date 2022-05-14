package com.parasol.core.controller;

import com.parasol.core.api_model.ClientCreateRequest;
import com.parasol.core.api_model.ClientCreateResponse;
import com.parasol.core.api_model.QueryClientResponse;
import com.parasol.core.api_model.QueryClientRequest;
import com.parasol.core.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping("client")
    public ResponseEntity<ClientCreateResponse> CreateClient(
            @RequestBody @Valid ClientCreateRequest request
    ) {
        ClientCreateResponse response = clientService.create(request);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("client")
    public ResponseEntity<QueryClientResponse> GetClient(
            @RequestParam("cusNo") Long cusNo
    ) {
        QueryClientRequest request = QueryClientRequest.builder()
                .id(cusNo)
                .build();

        QueryClientResponse response = clientService.getClient(request);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
