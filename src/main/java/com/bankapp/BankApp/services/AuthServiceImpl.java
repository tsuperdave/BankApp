package com.bankapp.BankApp.services;

import com.bankapp.BankApp.models.*;
import com.bankapp.BankApp.repository.AccountHolderRepository;
import com.bankapp.BankApp.repository.RoleRepository;
import com.bankapp.BankApp.repository.UserRepository;
import com.bankapp.BankApp.security.models.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class AuthServiceImpl implements AuthService{

    @Autowired
    private AccountHolderRepository accountHolderRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<?> registerUser(RegisterRequest registerRequest) {
        if(userRepository.existsByUsername(registerRequest.getUsername())) { return ResponseEntity.badRequest().body("Username is taken!"); }
        if(userRepository.existsByEmail(registerRequest.getEmail())) { return ResponseEntity.badRequest().body("Email is taken!"); }

        // add new user and will need to be able to select role assignment
        User user = new User(registerRequest.getUsername(), passwordEncoder.encode(registerRequest.getPassword()));
        user.setEmail(registerRequest.getEmail());

        // TODO debug finding Role name from Role repo to apply to new user
        System.out.println(RoleName.user.getClass().getSimpleName());
        System.out.println(RoleName.user);
        Role userRole = roleRepository.findByRoleName(RoleName.user)
                .orElseThrow(() -> new RuntimeException("Role not applied."));

        user.setRoles(Collections.singleton(userRole));
        userRepository.save(user);

        return ResponseEntity.ok("Registration Complete!");
    }

    @Override
    public AccountHolder addAccountHolder(AccountHolder accountHolder) {
        return accountHolderRepository.save(accountHolder);
    }

    @Override
    public List<AccountHolder> getAccountHolders() {
        return accountHolderRepository.findAll();
    }
}
