package com.bankapp.BankApp.controller;

import com.bankapp.BankApp.exceptions.AccountNotFoundException;
import com.bankapp.BankApp.exceptions.ExceedsCombinedBalanceLimitException;
import com.bankapp.BankApp.models.*;
import com.bankapp.BankApp.services.BankService;
import com.bankapp.BankApp.services.MyUserDetailsService;
import com.bankapp.BankApp.util.JwtUtil;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@Slf4j
@RestController
public class BankController {

    @Autowired
    private BankService bankService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private MyUserDetailsService myUserDetailsService;
    @Autowired
    private JwtUtil jwtTokenUtil;

    // START
//    @GetMapping(value = "/")
//    public String start() {
//        return "Welcome to the jungle";
//    }

    @PreAuthorize("hasAuthority('AccountHolder')")
    @GetMapping(value = "/user")
    public String user() {
        return ("Welcome Account Holder");
    }

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping(value = "/admin")
    public String admin() {
        return ("Welcome Admin");
    }

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping(value="/adminTest")
    public String adminTest() {
        return ("Secured Admin Test Working");
    }

    /**
     *
     * @param authenticationRequest instance will get user/pass
     * @return jwt
     * @throws Exception is throw if user/pass is not correct
     */
    @PostMapping(value = "/authenticate")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        } catch(BadCredentialsException bce) {
            throw new Exception("Incorrect username or password", bce);
        }
        final UserDetails userDetails = myUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @PreAuthorize("hasAuthority('admin')")
    @PostMapping(value = "/admin/createUser")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest registerRequest) {
        return bankService.registerUser(registerRequest);
    }

    /**
     *
     * @param accountHolder post account holder info
     * @return account holder
     */
    @PreAuthorize("hasAuthority('admin')")
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
