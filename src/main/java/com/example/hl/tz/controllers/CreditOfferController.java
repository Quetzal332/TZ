package com.example.hl.tz.controllers;

import com.example.hl.tz.entities.*;
import com.example.hl.tz.repositories.ClientRepository;
import com.example.hl.tz.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.persistence.Access;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/creditOffers")
public class CreditOfferController {
    private ClientService clientService;
    private CreditOfferService creditOfferService;
    private CreditService creditService;
    private PaymentScheduleService paymentScheduleService;


    @Autowired
    public void setCreditOfferService(CreditOfferService creditOfferService) {
        this.creditOfferService = creditOfferService;
    }
    @Autowired
    public void setPaymentScheduleService(PaymentScheduleService paymentScheduleService) {
        this.paymentScheduleService = paymentScheduleService;
    }

    @Autowired
    public void setCreditService(CreditService creditService) {
        this.creditService = creditService;
    }

    @Autowired
    public void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }



    @GetMapping("/edit/{id}")
    public String editOfferPage(Model model, @PathVariable(value = "id") UUID id) {
        CreditOffer creditOffer = creditOfferService.findById(id);
        Credit credit = creditOffer.getCredit();
        LocalDate date = LocalDate.now();
        BigDecimal creditSum =creditOffer.getCreditSum();
        double percent = credit.getInterestRate()/100/12;
        Integer month = creditOffer.getMonth();

        List<PaymentSchedule> paymentSchedules =
                paymentScheduleService.getPaymentSchedules(date, month, percent,creditSum, creditOffer);

        paymentScheduleService.saveAll(paymentSchedules);

        model.addAttribute("graphics", paymentSchedules);
        model.addAttribute("offer", creditOffer);
        return "creditOffer/edit";
    }

    @PostMapping("/edit/{id}")
    public String editOffer(@ModelAttribute(value = "offer") CreditOffer creditOffer){
        creditOfferService.edit(creditOffer);
        String id = creditOffer.getId().toString();
        return "redirect:/creditOffers/edit/" + id;
    }

    @GetMapping("/create/{id}")
    public String createOfferForm(@PathVariable(value = "id")UUID id, Model model){
        Client client = clientService.getClientById(id);
        CreditOffer creditOffer = new CreditOffer();
        Bank bank = client.getBank();
        creditOffer.setClient(client);
        model.addAttribute("offer",creditOffer);
        model.addAttribute("bank",bank);
        return "creditOffer/create";
    }

    @PostMapping("/create/{id}")
    public String createOffer(@ModelAttribute(value = "offer") @Valid CreditOffer creditOffer,BindingResult bindingResult){
       if (creditOffer.getCreditSum().doubleValue() > creditOffer.getCredit().getCreditLimit().doubleValue()){
           bindingResult.addError(new FieldError("offer","creditSum","сумма больше лимита"));
       }
       if(bindingResult.hasErrors()) return "creditOffer/create";
        creditOfferService.edit(creditOffer);
        String id = creditOffer.getId().toString();
        System.out.println(creditOffer.getId());

        return "redirect:/creditOffers/edit/" + id;
    }

    @GetMapping("/dell/{id}")
    public String dellClient(@PathVariable(value = "id") UUID id){
        String cid = creditOfferService.findById(id).getClient().getId().toString();
        creditOfferService.delete(id);
        return "redirect:/clients/" + cid + "/creditOffers/";
    }
}
