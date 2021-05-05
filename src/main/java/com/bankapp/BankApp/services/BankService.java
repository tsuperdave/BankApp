package com.bankapp.BankApp.services;

import com.bankapp.BankApp.exceptions.AccountNotFoundException;
import com.bankapp.BankApp.exceptions.ExceedsCombinedBalanceLimitException;
import com.bankapp.BankApp.models.*;
import com.bankapp.BankApp.repository.*;
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
    @Autowired
    private CDAccountRepository cdAccountRepository;
    @Autowired
    private CDOfferingRepository cdOfferingRepository;

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

    public CDAccount addCDAccount(CDAccount cdAccount, Integer id) throws AccountNotFoundException, ExceedsCombinedBalanceLimitException {
        AccountHolder accountHolder = getAccountHolderById(id);
        if(accountHolder.getCombinedAccountBalance() + cdAccount.getBalance() > BANK_ACCOUNT_BALANCE_LIMIT) {
            throw new ExceedsCombinedBalanceLimitException("Total balance exceeds threshold. Cannot create a CD Account at this time");
        }
        accountHolder.setCdAccountList(Arrays.asList(cdAccount));
        cdAccount.setAccountHolder(accountHolder);
        cdAccountRepository.save(cdAccount);
        return cdAccount;
    }

    public List<CDAccount> getCDAccountById(Integer id) throws AccountNotFoundException {
        return getAccountHolderById(id).getCdAccountList();
    }

    public CDOffering addCDOffering(CDOffering cdOffering) {
        return cdOfferingRepository.save(cdOffering);
    }

    public List<CDOffering> getCDOfferings() {
        return cdOfferingRepository.findAll();
    }
}
