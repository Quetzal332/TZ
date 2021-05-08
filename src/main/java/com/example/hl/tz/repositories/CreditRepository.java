package com.example.hl.tz.repositories;

import com.example.hl.tz.entities.Credit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CreditRepository extends JpaRepository<Credit, UUID> {
    List<Credit> findByOrderByCreditLimitDesc();
    Credit getCreditById(UUID uuid);
    void deleteCreditById(UUID id);
}
