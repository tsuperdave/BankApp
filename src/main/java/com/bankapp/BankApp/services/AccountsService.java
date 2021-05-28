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
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

public interface AccountsService {

    public CheckingAccount addCheckingAccount(int id, CheckingAccount checkingAccount)
            throws AccountNotFoundException,
            ExceedsCombinedBalanceLimitException,
            InvalidArgumentException;

    public SavingsAccount addSavingsAccount(int id, SavingsAccount savingsAccount)
            throws AccountNotFoundException,
            ExceedsCombinedBalanceLimitException,
            InvalidArgumentException;

    public CDAccount addCDAccount(int id, CDAccount cdAccount)
            throws AccountNotFoundException,
            ExceedsCombinedBalanceLimitException,
            InvalidArgumentException;

}
