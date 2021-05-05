package com.bankapp.BankApp.controller;

import com.bankapp.BankApp.exceptions.AccountNotFoundException;
import com.bankapp.BankApp.exceptions.ExceedsCombinedBalanceLimitException;
import com.bankapp.BankApp.models.AccountHolder;
import com.bankapp.BankApp.models.CheckingAccount;
import com.bankapp.BankApp.models.SavingsAccount;
import com.bankapp.BankApp.services.BankService;
import lombok.extern.slf4j.Slf4j;

import org.hibernate.annotations.Check;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.annotation.HttpConstraint;
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

    // START
    @GetMapping(value = "")
    public String start() {
        return "Welcome to the jungle";
    }

    /**
     *
     * @param accountHolder get/post account holder info
     * @return
     */
    @PostMapping(value = "/AccountHolders")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountHolder addAccountHolder(@RequestBody AccountHolder accountHolder) {
        return bankService.addAccountHolder(accountHolder);
    }

    @GetMapping(value = "/AccountHolders")
    @ResponseStatus(HttpStatus.OK)
    public List<AccountHolder> getAccountHolders() {
        return bankService.getAccountHolders();
    }

    @GetMapping(value = "/AccountHolders/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AccountHolder getAccountHolderById(@PathVariable Integer id) throws AccountNotFoundException {
        AccountHolder accountHolder;
        try {
            log.info("Grabbing Account Holder ${id}");
            accountHolder = bankService.getAccountHolderById(id);
        } catch (Exception e) {
            log.debug("getting Account Holder by ID" + e);
            throw new AccountNotFoundException("Account ${id} not found");
        }
        log.info("Grabbing Account Holder ${id}");
        return accountHolder;
    }

    // ACCOUNT HOLDER CONTACT DETAILS
    // TODO add methods for retreiving AHCD

    // CHECKING ACCOUNTS
    @PostMapping(value = "/AccountHolders/{id}/CheckingAccounts")
    @ResponseStatus(HttpStatus.CREATED)
    public CheckingAccount addCheckingAccount(@RequestBody CheckingAccount checkingAccount, @PathVariable Integer id) throws AccountNotFoundException, ExceedsCombinedBalanceLimitException {
        return bankService.addCheckingAccount(checkingAccount, id);
    }

    @GetMapping(value = "/AccountHolders/{id}/CheckingAccounts")
    @ResponseStatus(HttpStatus.OK)
    public List<CheckingAccount> getCheckingAccountById(@PathVariable Integer id) throws AccountNotFoundException {
        try {
            return bankService.getCheckingAccountById(id);
        } catch (Exception e) {
            throw new AccountNotFoundException("Account ${id} not found");
            // return null;
        }
    }

    // SAVINGS ACCOUNTS
    @PostMapping(value = "/AccountHolders/{id}/SavingsAccounts")
    @ResponseStatus(HttpStatus.CREATED)
    public SavingsAccount addSavingsAccount(@RequestBody SavingsAccount savingsAccount, @PathVariable Integer id) throws AccountNotFoundException, ExceedsCombinedBalanceLimitException {
        return bankService.addSavingsAccount(savingsAccount, id);
    }

    @GetMapping(value = "/AccountHolders/{id}/SavingsAccounts")
    @ResponseStatus(HttpStatus.OK)
    public List<SavingsAccount> getSavingsAccountById(@PathVariable Integer id) throws AccountNotFoundException {
        try{
            return bankService.getSavingsAccountById(id);
        } catch (Exception e) {
            throw new AccountNotFoundException("Account ${id} not found");
        }
    }
}
