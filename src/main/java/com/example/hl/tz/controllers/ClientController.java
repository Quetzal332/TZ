package com.example.hl.tz.controllers;

import com.example.hl.tz.entities.Bank;
import com.example.hl.tz.entities.Client;
import com.example.hl.tz.entities.Credit;
import com.example.hl.tz.repositories.ClientRepository;
import com.example.hl.tz.services.BankService;
import com.example.hl.tz.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Access;
import javax.validation.Valid;
import java.util.UUID;

@Controller
@RequestMapping("/clients")
public class ClientController {
   private ClientService clientService;
   private BankService bankService;

   @Autowired
    public void setBankService(BankService bankService) {
        this.bankService = bankService;
    }

    @Autowired
    public void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }


   @GetMapping("/edit/{id}")
    public String editClientPage(Model model, @PathVariable(value = "id") UUID id) {
        Client client = clientService.getClientById(id);
        model.addAttribute("client", client);

        return "client/editClient";
    }
    @PostMapping("/edit/{id}")
    public String editClient(@ModelAttribute(value = "client") Client client){
        clientService.editClient(client);
        String id = client.getBank().getId().toString();
        return "redirect:/banks/" + id + "/clients/";
    }
    @GetMapping("/create/{id}")
    public String createClientForm(@PathVariable(value = "id")UUID id, Model model, Client client){
       Bank bank = bankService.getBankById(id);
       client.setBank(bank);
        model.addAttribute("client",client);
        return "client/editClient";
    }
    @PostMapping("/create/{id}")
    public String createClient(@ModelAttribute(value = "client") @Valid Client client){
        clientService.editClient(client);
        String id = client.getBank().getId().toString();
        return "redirect:/banks/" + id + "/clients/";
    }
    @GetMapping("/{id}/creditOffers/")
    public String creditOffers(@PathVariable(value = "id")UUID id,Model model){
       Client client = clientService.getClientById(id);
       model.addAttribute("client",client);
       return "creditOffer/creditOffers";
    }
    @GetMapping("/dell/{id}")
    public String dellClient(@PathVariable(value = "id") UUID id){
       String bid = clientService.getClientById(id).getBank().getId().toString();
        clientService.dell(id);
        return "redirect:/banks/" + bid + "/clients/";
    }
}
