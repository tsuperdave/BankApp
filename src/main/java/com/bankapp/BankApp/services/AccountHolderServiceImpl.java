package com.bankapp.BankApp.services;

import com.bankapp.BankApp.models.*;
import com.bankapp.BankApp.repository.AccountHolderContactDetailsRepository;
import com.bankapp.BankApp.repository.AccountHolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountHolderServiceImpl implements AccountHolderService {

    @Autowired
    private AccountHolderRepository accountHolderRepository;
    @Autowired
    private AccountHolderContactDetailsRepository accountHolderContactDetailsRepository;

    @Override
    public AccountHolder addAccountHolder(AccountHolder accountHolder) {
        return accountHolderRepository.save(accountHolder);
    }

    @Override
    public AccountHolder getAccountHolderById(Integer id) {
        return accountHolderRepository.getOne(id);
    }

    @Override
    public List<AccountHolder> getAccountHolders() {
        return accountHolderRepository.findAll();
    }

    @Override
    public List<CheckingAccount> getCheckingAccounts(Integer id) {
        // TODO add exception if Account Holder is not found
        if(accountHolderRepository.existsById(id)) {
            AccountHolder accountHolder = accountHolderRepository.getOne(id);
            return accountHolder.getCheckingAccountList();
        }
        return null;
    }

    @Override
    public List<SavingsAccount> getSavingsAccounts(Integer id) {
        // TODO add exception if Account Holder is not found
        if(accountHolderRepository.existsById(id)) {
            AccountHolder accountHolder = accountHolderRepository.getOne(id);
            return accountHolder.getSavingsAccountsList();
        }
        return null;
    }

    @Override
    public List<CDAccount> getCDAccounts(Integer id) {
        // TODO add exception if Account Holder is not found
        if(accountHolderRepository.existsById(id)) {
            AccountHolder accountHolder = accountHolderRepository.getOne(id);
            return accountHolder.getCdAccountList();
        }
        return null;
    }

    @Override
    public AccountHolderContactDetails addContactDetails(Integer id, AccountHolderContactDetails accountHolderContactDetails) {
        return null;
    }
}
