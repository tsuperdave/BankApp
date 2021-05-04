package com.bankapp.controller;

import com.bankapp.models.AccountHolder;
import com.bankapp.services.BankService;
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

    @RequestMapping("/")
    @ResponseBody
    public String start() {
        return "Welcome to the jungle";
    }

    @PostMapping(value = "/AccountHolders")
    public AccountHolder addAccountHolder(@RequestBody AccountHolder accountHolder) throws AccountNotFoundException {
        return bankService.addAccountHolder(accountHolder);
    }

    @GetMapping(value = "/AccountHolders")
    public List<AccountHolder> getAccountHolders() {
        return bankService.getAccountHolders();
    }
}
