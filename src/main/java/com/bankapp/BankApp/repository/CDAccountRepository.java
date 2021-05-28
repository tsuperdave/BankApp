package com.bankapp.BankApp.repository;

import com.bankapp.BankApp.models.CDAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CDAccountRepository extends JpaRepository<CDAccount, Integer> {

}
