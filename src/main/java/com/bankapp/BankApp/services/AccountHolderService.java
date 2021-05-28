package com.bankapp.BankApp.services;

import com.bankapp.BankApp.models.*;

import java.util.List;

public interface AccountHolderService {
    public AccountHolder addAccountHolder(AccountHolder accountHolder);
    public AccountHolder getAccountHolderById(Integer id);
    public List<AccountHolder> getAccountHolders();
    public List<CheckingAccount> getCheckingAccounts(Integer id);
    public List<SavingsAccount> getSavingsAccounts(Integer id);
    public List<CDAccount> getCDAccounts(Integer id);
    public AccountHolderContactDetails addContactDetails(Integer id, AccountHolderContactDetails accountHolderContactDetails);
}
