package com.bankapp.repository;

import com.bankapp.models.AccountHolderContactDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountHolderContactDetailsRepository extends JpaRepository<AccountHolderContactDetails, Integer> {
}
