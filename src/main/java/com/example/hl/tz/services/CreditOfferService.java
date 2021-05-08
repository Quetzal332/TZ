package com.example.hl.tz.services;

import com.example.hl.tz.entities.CreditOffer;
import com.example.hl.tz.repositories.CreditOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CreditOfferService {

   private CreditOfferRepository creditOfferRepository;

    @Autowired
    public void setCreditOfferRepository(CreditOfferRepository creditOfferRepository) {
        this.creditOfferRepository = creditOfferRepository;
    }


    public List<CreditOffer> findAll() {
        return creditOfferRepository.findAll();
    }
    public CreditOffer findById(UUID id){return creditOfferRepository.getCreditOfferById(id);}
    public void edit(CreditOffer creditOffer) {
        creditOfferRepository.save(creditOffer);
    }


    public void delete(UUID id) {
        creditOfferRepository.deleteById(id);
    }



}
