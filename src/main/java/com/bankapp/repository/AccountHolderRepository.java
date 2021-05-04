package com.bankapp.repository;

import com.bankapp.models.AccountHolder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountHolderRepository extends JpaRepository<AccountHolder, Integer> {
}
