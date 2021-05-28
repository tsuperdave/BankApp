package com.bankapp.BankApp.repository;

import com.bankapp.BankApp.models.AccountHolder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountHolderRepository extends JpaRepository<AccountHolder, Integer> {
    Optional<AccountHolder> findById(Integer id);
}
