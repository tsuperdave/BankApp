package com.bankapp.BankApp.repository;

import com.bankapp.BankApp.models.SavingsAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SavingsAccountRepository extends JpaRepository<SavingsAccount, Integer> {
}
