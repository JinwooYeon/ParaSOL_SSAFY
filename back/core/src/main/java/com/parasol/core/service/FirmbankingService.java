package com.parasol.core.service;

import com.parasol.core.api_model.FirmbankingRegisterRequest;
import com.parasol.core.entity.Firmbanking;
import com.parasol.core.repository.FirmbankingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FirmbankingService {
    @Autowired
    private FirmbankingRepository firmbankingRepository;

    public Firmbanking createFirmbanking(FirmbankingRegisterRequest request) {
        Firmbanking firmbanking = new Firmbanking();

        firmbanking.setFirmbankingName(request.getFirmbankingName());
        firmbanking.setFirmbankingType(request.getFirmbankingType());
        firmbanking.setFirmbankingDepositAccountNo(request.getFirmbankingDepositAccountNo());
        firmbanking.setFirmbankingWithdrawAccountNo(request.getFirmbankingWithdrawAccountNo());

        return firmbankingRepository.save(firmbanking);
    }

    public Firmbanking updateFirmbanking(FirmbankingRegisterRequest request) {
        Firmbanking firmbanking = new Firmbanking();

        firmbanking.setFirmbankingName(request.getFirmbankingName());
        firmbanking.setFirmbankingType(request.getFirmbankingType());
        firmbanking.setFirmbankingDepositAccountNo(request.getFirmbankingDepositAccountNo());
        firmbanking.setFirmbankingWithdrawAccountNo(request.getFirmbankingWithdrawAccountNo());

        return firmbankingRepository.save(firmbanking);
    }

    public void deleteFirmbanking(FirmbankingRegisterRequest request) {
        Firmbanking firmbanking = new Firmbanking();

        firmbanking.setFirmbankingName(request.getFirmbankingName());
        firmbanking.setFirmbankingType(request.getFirmbankingType());
        firmbanking.setFirmbankingDepositAccountNo(request.getFirmbankingDepositAccountNo());
        firmbanking.setFirmbankingWithdrawAccountNo(request.getFirmbankingWithdrawAccountNo());

        firmbankingRepository.delete(firmbanking);
    }

    public Firmbanking getFirmbanking(String name) {
        Optional<Firmbanking> result = firmbankingRepository.findFirmbankingsByFirmbankingName(name);

        if(result.isEmpty())
            return null;
        return result.get();
    }
}
