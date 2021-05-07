package com.bankapp.BankApp.controller;

import com.bankapp.BankApp.exceptions.AccountNotFoundException;
import com.bankapp.BankApp.exceptions.ExceedsCombinedBalanceLimitException;
import com.bankapp.BankApp.models.*;
import com.bankapp.BankApp.services.BankService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
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
     * @param accountHolder post account holder info
     * @return
     */
    @PostMapping(value = "/AccountHolders")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountHolder addAccountHolder(@RequestBody AccountHolder accountHolder) {
        return bankService.addAccountHolder(accountHolder);
    }

    /**
     *
     * @return list of account holders
     */
    @GetMapping(value = "/AccountHolders")
    @ResponseStatus(HttpStatus.OK)
    public List<AccountHolder> getAccountHolders() {
        return bankService.getAccountHolders();
    }

    /**
     *
     * @param id gof account holder
     * @return returns specified account holder
     * @throws AccountNotFoundException if account is not found
     */
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

    /**
     *
     * @param checkingAccount to add
     * @param id of account holder
     * @return checking account
     * @throws AccountNotFoundException if account is not found
     * @throws ExceedsCombinedBalanceLimitException if combined bal is over threshold
     */
    @PostMapping(value = "/AccountHolders/{id}/CheckingAccounts")
    @ResponseStatus(HttpStatus.CREATED)
    public CheckingAccount addCheckingAccount(@RequestBody CheckingAccount checkingAccount, @PathVariable Integer id) throws AccountNotFoundException, ExceedsCombinedBalanceLimitException {
        return bankService.addCheckingAccount(checkingAccount, id);
    }

    /**
     *
     * @param id of account holder
     * @return checking account for account holder
     * @throws AccountNotFoundException if account is not found
     */
    @GetMapping(value = "/AccountHolders/{id}/CheckingAccounts")
    @ResponseStatus(HttpStatus.OK)
    public List<CheckingAccount> getCheckingAccountById(@PathVariable Integer id) throws AccountNotFoundException {
        try {
            return bankService.getCheckingAccountById(id);
        } catch (Exception e) {
            throw new AccountNotFoundException("Checking Account ${id} not found");
            // return null;
        }
    }

    // SAVINGS ACCOUNTS

    /**
     *
     * @param savingsAccount to add
     * @param id of account holder
     * @return savings account
     * @throws AccountNotFoundException if account not found
     * @throws ExceedsCombinedBalanceLimitException if combined bal is over threshold
     */
    @PostMapping(value = "/AccountHolders/{id}/SavingsAccounts")
    @ResponseStatus(HttpStatus.CREATED)
    public SavingsAccount addSavingsAccount(@RequestBody SavingsAccount savingsAccount, @PathVariable Integer id) throws AccountNotFoundException, ExceedsCombinedBalanceLimitException {
        return bankService.addSavingsAccount(savingsAccount, id);
    }

    /**
     *
     * @param id of account holder
     * @return savings account for account holder
     * @throws AccountNotFoundException if account not found
     */
    @GetMapping(value = "/AccountHolders/{id}/SavingsAccounts")
    @ResponseStatus(HttpStatus.OK)
    public List<SavingsAccount> getSavingsAccountById(@PathVariable Integer id) throws AccountNotFoundException {
        try{
            return bankService.getSavingsAccountById(id);
        } catch (Exception e) {
            throw new AccountNotFoundException("Savings Account ${id} not found");
        }
    }

    // CD ACCOUNTS

    /**
     *
     * @param cdAccount to add
     * @param id of account holder
     * @return cd account
     * @throws AccountNotFoundException if account not found
     * @throws ExceedsCombinedBalanceLimitException if combined bal is over threshold
     */
    @PostMapping(value = "/AccountHolders/{id}/CDAccounts")
    @ResponseStatus(HttpStatus.CREATED)
    public CDAccount addCDAccount(@RequestBody CDAccount cdAccount, @PathVariable Integer id) throws AccountNotFoundException, ExceedsCombinedBalanceLimitException {
        return bankService.addCDAccount(cdAccount, id);
    }

    /**
     *
     * @param id of account holder
     * @return cd account for account holder
     * @throws AccountNotFoundException if account not found
     */
    @GetMapping(value = "/AccountHolders/{id}/CDAccounts")
    @ResponseStatus(HttpStatus.OK)
    public List<CDAccount> getCDAccountsById(@PathVariable Integer id) throws AccountNotFoundException {
        try {
            return bankService.getCDAccountById(id);
        } catch(Exception e) {
            throw new AccountNotFoundException("CD Account ${id} not found");
        }
    }

    // CD OFFERINGS

    /**
     *
     * @param cdOffering to add
     * @return cd offering
     */
    @PostMapping(value = "/CDOfferings")
    @ResponseStatus(HttpStatus.CREATED)
    public CDOffering addCDOffering(@RequestBody CDOffering cdOffering) {
        return bankService.addCDOffering(cdOffering);
    }

    /**
     *
     * @return list of cd offerings
     */
    @GetMapping(value = "/CDOfferings")
    @ResponseStatus(HttpStatus.OK)
    public List<CDOffering> getCDOfferings() {
        return bankService.getCDOfferings();
    }
}
