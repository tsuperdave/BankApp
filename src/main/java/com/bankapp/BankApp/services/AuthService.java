package com.bankapp.BankApp.services;

import com.bankapp.BankApp.exceptions.AccountNotFoundException;
import com.bankapp.BankApp.exceptions.ExceedsCombinedBalanceLimitException;
import com.bankapp.BankApp.models.*;
import com.bankapp.BankApp.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveSecurityAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AuthService {

    @Autowired
    private AccountHolderRepository accountHolderRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseEntity<?> registerUser(RegisterRequest registerRequest) {
        //TODO may need to add exceptions if roles are not found

        if(userRepository.existsByUsername(registerRequest.getUsername())) { return ResponseEntity.badRequest().body("Username is taken!"); }
        if(userRepository.existsByEmail(registerRequest.getEmail())) { return ResponseEntity.badRequest().body("Email is taken!"); }

        // add new user and will need to be able to select role assignment
        User user = new User(registerRequest.getUsername(), passwordEncoder.encode(registerRequest.getPassword()));
        user.setEmail(registerRequest.getEmail());

        Role userRole = roleRepository.findByName(RoleName.user)
                .orElseThrow(() -> new RuntimeException("Role not applied."));

        user.setRoles(Collections.singleton(userRole));
        userRepository.save(user);

        return ResponseEntity.ok("Registration Complete!");
    }

    public AccountHolder addAccountHolder(AccountHolder accountHolder) {
        return accountHolderRepository.save(accountHolder);
    }

    public List<AccountHolder> getAccountHolders() {
        return accountHolderRepository.findAll();
    }


}
