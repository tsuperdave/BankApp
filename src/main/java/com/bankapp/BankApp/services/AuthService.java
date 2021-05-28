package com.bankapp.BankApp.services;

import com.bankapp.BankApp.exceptions.AccountNotFoundException;
import com.bankapp.BankApp.exceptions.ExceedsCombinedBalanceLimitException;
import com.bankapp.BankApp.models.*;
import com.bankapp.BankApp.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveSecurityAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AuthService {

    @Autowired
    private AccountHolderRepository accountHolderRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    public ResponseEntity<?> registerUser(RegisterRequest registerRequest) {
        //TODO may need to add exceptions if roles are not found
        Set<Role> roles = new HashSet<>();
        Set<String> rolesString = registerRequest.getRole();

        if(userRepository.existsByUsername(registerRequest.getUsername())) {
            return ResponseEntity.badRequest().body("Username is taken!");
        }
        // add new user and will need to be able to select role assignment
        User user = new User(registerRequest.getUsername(), registerRequest.getPassword());

        if(rolesString == null) {
            Role userRole = roleRepository.findByName(RoleName.AccountHolder).orElseThrow(() -> new RuntimeException("ERROR: Role of AccountHolder not found."));
            roles.add(userRole);
        } else {
            rolesString.forEach(role -> {
                switch(role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(RoleName.admin).orElseThrow(() -> new RuntimeException("ERROR: Role of admin not found"));
                        roles.add(adminRole);
                        break;
                    case "AccountHolder":
                        Role accountHolderRole = roleRepository.findByName(RoleName.AccountHolder).orElseThrow(() -> new RuntimeException("ERROR: Role of AccountHolder not found"));
                        roles.add(accountHolderRole);
                }
            });
        }

        user.setActive(registerRequest.isActive());
        user.setRoles(roles);
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
