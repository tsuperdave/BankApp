package com.bankapp.BankApp.controller;

import com.bankapp.BankApp.exceptions.*;
import com.bankapp.BankApp.models.*;
import com.bankapp.BankApp.services.AccountsService;
import com.bankapp.BankApp.services.UserService;
import com.bankapp.BankApp.security.util.JwtUtil;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AccountsService accountsService;

    @GetMapping("/me")
    @PreAuthorize("hasAuthority('AccountHolder')")
    public AccountHolder getAccountHolderById() {
        String username = jwtUtil.getCurrentUserName();
        User user = userService.getUserByUserName(username);
        return user.getAccountHolder();
    }

    @PostMapping("/me/checkingaccounts")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('AccountHolder')")
    public CheckingAccount addCheckingAccount(@RequestBody CheckingAccount checkingAccount)
            throws NoResourceFoundException, NegativeAmountException, ExceedsCombinedBalanceLimitException,
            AccountNotFoundException, InvalidArgumentException {
        if (checkingAccount.getBalance() < 0) {
            throw new NegativeAmountException("Must have funds to open checking account!");
        }
        String username = jwtUtil.getCurrentUserName();
        User user = userService.getUserByUserName(username);
        AccountHolder accountHolder = user.getAccountHolder();
        if (accountHolder == null) {
            throw new NoResourceFoundException("Invalid id");
        }
        if (accountHolder.getCombinedAccountBalance() + checkingAccount.getBalance() > 250000) {
            throw new ExceedsCombinedBalanceLimitException("exceeds limit of amount 250,000 max");
        }

        return accountsService.addCheckingAccount(accountHolder.getId(), checkingAccount);
    }

    @GetMapping("/me/checkingaccounts")
    @PreAuthorize("hasAuthority('AccountHolder')")
    public List<CheckingAccount> getCheckingAccount() {
        String username = jwtUtil.getCurrentUserName();
        User user = userService.getUserByUserName(username);
        return user.getAccountHolder().getCheckingAccountList();
    }

    @PostMapping("/me/savingsaccounts")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('AccountHolder')")
    public SavingsAccount addSavingsAccount(@RequestBody SavingsAccount savingsAccount)
            throws NoResourceFoundException, NegativeAmountException, ExceedsCombinedBalanceLimitException,
            AccountNotFoundException, InvalidArgumentException {
        if (savingsAccount.getBalance() < 0) {
            throw new NegativeAmountException("Must have funds to open savings account!");
        }
        String username = jwtUtil.getCurrentUserName();
        User user = userService.getUserByUserName(username);
        AccountHolder accHolder = user.getAccountHolder();
        if (accHolder == null) {
            throw new NoResourceFoundException("Invalid id");
        }
        if (accHolder.getCombinedAccountBalance() + savingsAccount.getBalance() > 250000) {
            throw new ExceedsCombinedBalanceLimitException("exceeds limit of amount 250,000 max");
        }
        return accountsService.addSavingsAccount(accHolder.getId(), savingsAccount);
    }

    @GetMapping("/Me/savingsaccounts")
    @PreAuthorize("hasAuthority('AccountHolder')")
    public List<SavingsAccount> getSavingsAccount() {
        String username = jwtUtil.getCurrentUserName();
        User user = userService.getUserByUserName(username);
        return user.getAccountHolder().getSavingsAccountsList();
    }

    @PostMapping("/Me/cdaccounts")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('AccountHolder')")
    public CDAccount addCDAccount(@RequestBody CDAccount cdAccount)
            throws NoResourceFoundException, NegativeAmountException, AccountNotFoundException,
            ExceedsCombinedBalanceLimitException, InvalidArgumentException {
        if (cdAccount.getBalance() < 0) {
            throw new NegativeAmountException("Must have funds to open CD account!");
        }
        String username = jwtUtil.getCurrentUserName();
        User user = userService.getUserByUserName(username);
        AccountHolder accHolder = user.getAccountHolder();

        if (accHolder == null) {
            throw new NoResourceFoundException("Invalid id");
        }
        return accountsService.addCDAccount(accHolder.getId(), cdAccount);
    }

    @GetMapping("/Me/cdaccounts")
    @PreAuthorize("hasAuthority('AccountHolder')")
    public List<CDAccount> getCDAccount() {
        String username = jwtUtil.getCurrentUserName();
        User user = userService.getUserByUserName(username);
        return user.getAccountHolder().getCdAccountList();
    }

}
