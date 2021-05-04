package com.bankapp.repository;

import com.bankapp.models.CDAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CDAccountRepository extends JpaRepository<CDAccount, Integer> {
}
