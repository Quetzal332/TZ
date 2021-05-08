package com.example.hl.tz.controllers;

import com.example.hl.tz.entities.Bank;
import com.example.hl.tz.entities.Client;
import com.example.hl.tz.entities.Credit;
import com.example.hl.tz.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/banks")
public class BankController {
    private BankService bankService;
    private CreditService creditService;
    private ClientService clientService;

    @Autowired
    public void setCreditService(CreditService creditService) {
        this.creditService = creditService;
    }
    @Autowired
    public void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }

    @Autowired
    public void setBankService(BankService bankService) {
        this.bankService = bankService;
    }



    @GetMapping()
    public String show(Model model){
        List<Bank> banks = bankService.getAll();
        model.addAttribute("banks",banks);
        return "bank/bankList";
    }
    @GetMapping("/edit/{id}")
    public String editPage(Model model, @PathVariable(value = "id") UUID id){
        Bank bank = bankService.getBankById(id);
        List<Credit> credits = creditService.getAll();
        List<Client> clients = clientService.getALl();
        model.addAttribute("bank",bank);
        model.addAttribute("credits",credits);
        model.addAttribute("clients",clients);
        return "bank/editBank";
    }
    @PostMapping("/edit/{id}")
    public String editBank(@ModelAttribute(value = "bank")Bank bank){
       bankService.save(bank);
        return "redirect:/banks/";
    }
    @GetMapping("/create/")
    public String createPage(Model model){
        Bank bank = new Bank();
        model.addAttribute("bank",bank);
        return "bank/createBank";
    }
    @GetMapping("/{id}/clients/")
    public String bankClients(@PathVariable(value = "id")UUID id,Model model){
        Bank bank = bankService.getBankById(id);
        model.addAttribute("bank",bank);
        return "client/clientsList2";
    }
    @GetMapping("/{id}/credits/")
    public String bankCredits(@PathVariable(value = "id")UUID id,Model model){
        Bank bank = bankService.getBankById(id);
        model.addAttribute("bank",bank);
        return "credit/credits";
    }
    @PostMapping("/create/")
    public String createBank(@ModelAttribute(value = "bank")Bank bank){
        bankService.save(bank);
        System.out.println(bank);
        System.out.println(bankService.getAll());
        return "redirect:/banks";
    }
    @GetMapping("/dell/{id}")
    public String dellBank(@PathVariable(value = "id")UUID id){
        bankService.dellBank(id);
        return "redirect:/banks";
    }

}
