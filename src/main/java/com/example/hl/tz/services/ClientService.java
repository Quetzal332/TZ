package com.example.hl.tz.services;

import com.example.hl.tz.entities.Client;
import com.example.hl.tz.entities.CreditOffer;
import com.example.hl.tz.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class ClientService {
    @Autowired
    ClientRepository clientRepository;

    public List<Client> getALl(){

        return clientRepository.findByOrderByLastName();
    }
    public Client getClientByName(String name){
       return clientRepository.getClientByLastName(name);
    }

    public Client getClientById(UUID id){
        return clientRepository.getClientById(id);
    }
    public void editClient(Client client){
        clientRepository.save(client);
    }
    @Transactional
    public void dell(UUID id){
        clientRepository.deleteById(id);
    }




}
