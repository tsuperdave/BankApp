package com.bankapp.BankApp.services;


import com.bankapp.BankApp.models.AccountHolder;
import com.bankapp.BankApp.security.models.RegisterRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AuthService {
    public ResponseEntity<?> registerUser(RegisterRequest registerRequest);
    public AccountHolder addAccountHolder(AccountHolder accountHolder);
    public List<AccountHolder> getAccountHolders();
}
