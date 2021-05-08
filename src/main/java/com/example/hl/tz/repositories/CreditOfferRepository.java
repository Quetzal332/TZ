package com.example.hl.tz.repositories;

import com.example.hl.tz.entities.CreditOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

public interface CreditOfferRepository extends JpaRepository<CreditOffer, UUID> {

    CreditOffer getCreditOfferById(UUID id);
    void deleteById(UUID id);
}
