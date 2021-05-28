package com.bankapp.BankApp.controller;

import com.bankapp.BankApp.exceptions.AccountNotFoundException;
import com.bankapp.BankApp.models.AccountHolder;
import com.bankapp.BankApp.models.AccountHolderContactDetails;
import com.bankapp.BankApp.services.AccountHolderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/accountholders")
public class AccountHolderController {

    @Autowired
    private AccountHolderService accountHolderService;

    @PostMapping(value = "/")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('admin')")
    public AccountHolder addAccountHolder(@RequestBody AccountHolder accountHolder) {
        return accountHolderService.addAccountHolder(accountHolder);
    }

    @GetMapping(value = "/")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('admin')")
    public List<AccountHolder> getAccountHolders() {
        return accountHolderService.getAccountHolders();
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('admin')")
    public AccountHolder getAccountHolderById(@PathVariable Integer id) throws AccountNotFoundException {
        AccountHolder accountHolder;
        try {
            log.info("Grabbing Account Holder ${id}");
            accountHolder = accountHolderService.getAccountHolderById(id);
        } catch (Exception e) {
            log.debug("getting Account Holder by ID" + e);
            throw new AccountNotFoundException("Account ${id} not found");
        }
        log.info("Grabbing Account Holder ${id}");
        return accountHolder;
    }

    @GetMapping(value = "/{id}/contactDetails")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('admin')")
    public AccountHolderContactDetails addContactDetails(@PathVariable Integer id, @RequestBody AccountHolderContactDetails accountHolderContactDetails) {
        return  accountHolderService.addContactDetails(id, accountHolderContactDetails);
    }

}
