package com.example.hl.tz.repositories;

import com.example.hl.tz.entities.Bank;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.UUID;


public interface BankRepository extends JpaRepository<Bank, UUID> {
    Bank getBankById(UUID id);
    void deleteBankById(UUID id);


}
