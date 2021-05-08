package com.example.hl.tz.controllers;

import com.example.hl.tz.entities.Bank;
import com.example.hl.tz.entities.Client;
import com.example.hl.tz.entities.Credit;
import com.example.hl.tz.services.BankService;
import com.example.hl.tz.services.ClientService;
import com.example.hl.tz.services.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping()
public class MainCont {

    @GetMapping
    public String mainPage(){

        return "mainPage/mainPage";

    }

}
