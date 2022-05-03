package com.parasol.BaaS.service;

import com.parasol.BaaS.api_request.BankConnectionRequest;
import com.parasol.BaaS.db.entity.BankConnection;
import com.parasol.BaaS.db.repository.BankConnectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankConnectionService {

    @Autowired
    private BankConnectionRepository bankConnectionRepository;

    public void addBankConnection(BankConnectionRequest request) throws IllegalArgumentException {
        String bankName = request.getBankName();
        String id = request.getId();
        String password = request.getPassword();

        // TODO: 각 값에 대한 검증 필요
        BankConnection bankConnection = new BankConnection();
        bankConnection.setBankName(bankName);
        bankConnection.setBankId(id);
        bankConnection.setBankPassword(password);

        bankConnectionRepository.save(bankConnection);
    }

}
