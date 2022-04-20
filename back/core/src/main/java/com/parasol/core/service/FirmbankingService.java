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

    public Optional<Firmbanking> getFirmbankingByName(String name) {
        Optional<Firmbanking> result = firmbankingRepository.findFirmbankingsByFirmbankingName(name);

        return Optional.ofNullable(result).orElse(Optional.of(new Firmbanking()));
    }

    public Optional<Firmbanking> getFirmbankingByNameAndType(String name, String type) {
        Optional<Firmbanking> result = firmbankingRepository.findFirmbankingsByFirmbankingNameAndFirmbankingType(name, type);

        return Optional.ofNullable(result).orElse(Optional.of(new Firmbanking()));
    }

    public Optional<Firmbanking> getFirmbankingByWithdrawAccount(String account) {
        Optional<Firmbanking> result = firmbankingRepository.findFirmbankingsByFirmbankingWithdrawAccountNo(account);

        return Optional.ofNullable(result).orElse(Optional.of(new Firmbanking()));
    }

    public Optional<Firmbanking> getFirmbankingByDeposiAccount(String account) {
        Optional<Firmbanking> result = firmbankingRepository.findFirmbankingsByFirmbankingDepositAccountNo(account);

        return Optional.ofNullable(result).orElse(Optional.of(new Firmbanking()));
    }
}
