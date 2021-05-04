package com.bankapp.services;

import com.bankapp.exceptions.AccountNotFoundException;
import com.bankapp.models.AccountHolder;
import com.bankapp.repository.AccountHolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankService {

    @Autowired
    private AccountHolderRepository accountHolderRepository;

    public AccountHolder addAccountHolder(AccountHolder accountHolder) throws AccountNotFoundException {
        return accountHolderRepository.save(accountHolder);
    }

    public List<AccountHolder> getAccountHolders() {
        return accountHolderRepository.findAll();
    }
}
