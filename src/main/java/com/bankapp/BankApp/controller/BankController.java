package com.bankapp.BankApp.controller;

import com.bankapp.BankApp.models.AccountHolder;
import com.bankapp.BankApp.services.BankService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;
import java.util.*;

@Slf4j
@RestController
public class BankController {

    // TODO add post/get for
    //service
    // auth manager
    // userDetail service
    // jwt util
    @Autowired
    private BankService bankService;

    @GetMapping(value = "")
    public String start() {
        return "Welcome to the jungle";
    }

    @PostMapping(value = "/AccountHolders")
    public AccountHolder addAccountHolder(@RequestBody AccountHolder accountHolder) throws AccountNotFoundException, com.bankapp.BankApp.exceptions.AccountNotFoundException {
        return bankService.addAccountHolder(accountHolder);
    }

    @GetMapping(value = "/AccountHolders")
    public List<AccountHolder> getAccountHolders() {
        return bankService.getAccountHolders();
    }
}
