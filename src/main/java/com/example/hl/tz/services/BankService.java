package com.example.hl.tz.services;

import com.example.hl.tz.entities.Bank;
import com.example.hl.tz.entities.Client;
import com.example.hl.tz.entities.Credit;
import com.example.hl.tz.repositories.BankRepository;
import com.example.hl.tz.repositories.ClientRepository;
import com.example.hl.tz.repositories.CreditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class BankService {

    BankRepository bankRepository;

    @Autowired
    private void setBankRepository(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }


    public List<Bank> getAll() {
        return bankRepository.findAll();
    }


    public void save(Bank bank) {
       bankRepository.save(bank);
    }

    public Bank getBankById(UUID id) {
        return bankRepository.getBankById(id);
    }

    @Transactional
    public void dellBank(UUID id) {
        bankRepository.deleteBankById(id);
    }
}
