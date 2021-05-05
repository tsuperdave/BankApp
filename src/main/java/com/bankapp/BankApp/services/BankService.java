package com.bankapp.BankApp.services;

import com.bankapp.BankApp.exceptions.AccountNotFoundException;
import com.bankapp.BankApp.exceptions.ExceedsCombinedBalanceLimitException;
import com.bankapp.BankApp.models.AccountHolder;
import com.bankapp.BankApp.models.CheckingAccount;
import com.bankapp.BankApp.models.SavingsAccount;
import com.bankapp.BankApp.repository.AccountHolderRepository;
import com.bankapp.BankApp.repository.CheckingAccountRepository;
import com.bankapp.BankApp.repository.SavingsAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class BankService {

    private static final double BANK_ACCOUNT_BALANCE_LIMIT = 25000;

    @Autowired
    private AccountHolderRepository accountHolderRepository;
    @Autowired
    private CheckingAccountRepository checkingAccountRepository;
    @Autowired
    private SavingsAccountRepository savingsAccountRepository;

    public AccountHolder addAccountHolder(AccountHolder accountHolder) {
        return accountHolderRepository.save(accountHolder);
    }

    public List<AccountHolder> getAccountHolders() {
        return accountHolderRepository.findAll();
    }

    public AccountHolder getAccountHolderById(Integer id) throws AccountNotFoundException {
        return accountHolderRepository.findById(id).orElse(null);
    }

    public CheckingAccount addCheckingAccount(CheckingAccount checkingAccount, Integer id) throws AccountNotFoundException, ExceedsCombinedBalanceLimitException {
        //need to grab account by id to add checking
        AccountHolder accountHolder = getAccountHolderById(id);
        if(accountHolder.getCombinedAccountBalance() + checkingAccount.getBalance() > BANK_ACCOUNT_BALANCE_LIMIT) {
            throw new ExceedsCombinedBalanceLimitException("Total balance exceeds threshold. Cannot create a Checking Account at this time");
        }
        accountHolder.setCheckingAccountList(Arrays.asList(checkingAccount));
        checkingAccount.setAccountHolder(accountHolder);
        checkingAccountRepository.save(checkingAccount);
        return checkingAccount;
    }

    public List<CheckingAccount> getCheckingAccountById(Integer id) throws AccountNotFoundException {
        return getAccountHolderById(id).getCheckingAccountList();
    }

    public SavingsAccount addSavingsAccount(SavingsAccount savingsAccount, Integer id) throws AccountNotFoundException, ExceedsCombinedBalanceLimitException {
        AccountHolder accountHolder = getAccountHolderById(id);
        if(accountHolder.getCombinedAccountBalance() + savingsAccount.getBalance() > BANK_ACCOUNT_BALANCE_LIMIT) {
            throw new ExceedsCombinedBalanceLimitException("Total balance exceeds threshold. Cannot create a Savings Account at this time");
        }
        accountHolder.setSavingsAccountsList(Arrays.asList(savingsAccount));
        savingsAccount.setAccountHolder(accountHolder);
        savingsAccountRepository.save(savingsAccount);
        return savingsAccount;
    }

    public List<SavingsAccount> getSavingsAccountById(Integer id) throws AccountNotFoundException {
        return getAccountHolderById(id).getSavingsAccountsList();
    }
}
