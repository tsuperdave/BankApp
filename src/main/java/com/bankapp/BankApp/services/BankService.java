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
