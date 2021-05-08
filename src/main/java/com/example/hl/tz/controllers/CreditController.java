package com.example.hl.tz.controllers;

import com.example.hl.tz.entities.Bank;
import com.example.hl.tz.entities.Client;
import com.example.hl.tz.entities.Credit;
import com.example.hl.tz.services.BankService;
import com.example.hl.tz.services.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/credits")
public class CreditController {
    private CreditService creditService;
    private BankService bankService;

    @Autowired
    public void setBankService(BankService bankService) {
        this.bankService = bankService;
    }

    @Autowired
    public void setCreditService(CreditService creditService) {
        this.creditService = creditService;
    }


    @GetMapping("/edit/{id}")
    public String editCreditPage(Model model, @PathVariable(value = "id") UUID id){
        model.addAttribute("credit",creditService.getById(id));
        return "credit/editCredit";
    }
    @PostMapping("/edit/{id}")
    public String editCredit(@ModelAttribute(value = "credit")Credit credit){
        creditService.editCredit(credit);
        String id = credit.getBank().getId().toString();
        return "redirect:/banks/" + id + "/credits/";
    }
    @GetMapping("/create/{id}")
    public String createCreditForm(@PathVariable(value = "id")UUID id, Model model,Credit credit){
        Bank bank = bankService.getBankById(id);
        credit.setBank(bank);
        model.addAttribute("credit",credit);
        return "credit/editCredit";
    }
    @PostMapping("/create/{id}")
    public String createCredit(@ModelAttribute(value = "credit") Credit credit){
        creditService.editCredit(credit);
        String id = credit.getBank().getId().toString();
        return "redirect:/banks/" + id + "/credits/";
    }
    @GetMapping("/dell/{id}")
    public String dellCredit(@PathVariable(value = "id") UUID id){
        String bid = creditService.getById(id).getBank().getId().toString();
        creditService.dellCreditById(id);
        return "redirect:/banks/" + bid + "/credits/";
    }
}
