package com.example.hl.tz.repositories;

import com.example.hl.tz.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, UUID> {
    Client getClientByLastName(String name);
    List<Client> findByOrderByLastName();
   Client getClientById(UUID id);
}
