package com.bankapp.BankApp.repository;

import com.bankapp.BankApp.models.CheckingAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckingAccountRepository extends JpaRepository<CheckingAccount, Integer> {
}
