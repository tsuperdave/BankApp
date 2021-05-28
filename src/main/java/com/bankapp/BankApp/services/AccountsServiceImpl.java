package com.bankapp.BankApp.services;

import com.bankapp.BankApp.exceptions.AccountNotFoundException;
import com.bankapp.BankApp.exceptions.ExceedsCombinedBalanceLimitException;
import com.bankapp.BankApp.exceptions.InvalidArgumentException;
import com.bankapp.BankApp.models.AccountHolder;
import com.bankapp.BankApp.models.CDAccount;
import com.bankapp.BankApp.models.CheckingAccount;
import com.bankapp.BankApp.models.SavingsAccount;
import com.bankapp.BankApp.repository.AccountHolderRepository;
import com.bankapp.BankApp.repository.CDAccountRepository;
import com.bankapp.BankApp.repository.CheckingAccountRepository;
import com.bankapp.BankApp.repository.SavingsAccountRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@NoArgsConstructor
public class AccountsServiceImpl implements AccountsService {

    private static final double BANK_ACCOUNT_BALANCE_LIMIT = 25000;

    @Autowired
    AccountHolderRepository accountHolderRepository;
    @Autowired
    CheckingAccountRepository checkingAccountRepository;
    @Autowired
    SavingsAccountRepository savingsAccountRepository;
    @Autowired
    CDAccountRepository cdAccountRepository;

    @Override
    public CheckingAccount addCheckingAccount(int id, CheckingAccount checkingAccount) throws AccountNotFoundException, ExceedsCombinedBalanceLimitException {
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

    @Override
    public SavingsAccount addSavingsAccount(int id, SavingsAccount savingsAccount) throws AccountNotFoundException, ExceedsCombinedBalanceLimitException, InvalidArgumentException {
        AccountHolder accountHolder = getAccountHolderById(id);
        if(accountHolder.getCombinedAccountBalance() + savingsAccount.getBalance() > BANK_ACCOUNT_BALANCE_LIMIT) {
            throw new ExceedsCombinedBalanceLimitException("Total balance exceeds threshold. Cannot create a Savings Account at this time");
        }
        accountHolder.setSavingsAccountsList(Arrays.asList(savingsAccount));
        savingsAccount.setAccountHolder(accountHolder);
        savingsAccountRepository.save(savingsAccount);
        return savingsAccount;
    }


    @Override
    public CDAccount addCDAccount(int id, CDAccount cdAccount) throws AccountNotFoundException, ExceedsCombinedBalanceLimitException, InvalidArgumentException {
        AccountHolder accountHolder = getAccountHolderById(id);
        if(accountHolder.getCombinedAccountBalance() + cdAccount.getBalance() > BANK_ACCOUNT_BALANCE_LIMIT) {
            throw new ExceedsCombinedBalanceLimitException("Total balance exceeds threshold. Cannot create a CD Account at this time");
        }
        accountHolder.setCdAccountList(Arrays.asList(cdAccount));
        cdAccount.setAccountHolder(accountHolder);
        cdAccountRepository.save(cdAccount);
        return cdAccount;
    }

    public AccountHolder getAccountHolderById(Integer id) throws AccountNotFoundException {
        return accountHolderRepository.findById(id).orElse(null);
    }

    public List<CheckingAccount> getCheckingAccountById(int id) throws AccountNotFoundException {
        return getAccountHolderById(id).getCheckingAccountList();
    }

    public List<SavingsAccount> getSavingsAccountById(Integer id) throws AccountNotFoundException {
        return getAccountHolderById(id).getSavingsAccountsList();
    }

    public List<CDAccount> getCDAccountById(Integer id) throws AccountNotFoundException {
        return getAccountHolderById(id).getCdAccountList();
    }

}
