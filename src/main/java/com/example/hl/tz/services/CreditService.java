package com.example.hl.tz.services;

import com.example.hl.tz.entities.Credit;
import com.example.hl.tz.repositories.CreditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class CreditService {
    @Autowired
    CreditRepository creditRepository;

    public List<Credit> getAll(){
        return creditRepository.findByOrderByCreditLimitDesc();
    }
    public Credit getById(UUID id){
        return creditRepository.getCreditById(id);
    }
    public void editCredit(Credit credit){
        creditRepository.save(credit);
    }
    @Transactional
    public void dellCreditById(UUID id){
        creditRepository.deleteCreditById(id);
    }
}
