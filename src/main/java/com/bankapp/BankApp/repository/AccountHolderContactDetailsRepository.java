package com.bankapp.BankApp.repository;

import com.bankapp.BankApp.models.AccountHolderContactDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountHolderContactDetailsRepository extends JpaRepository<AccountHolderContactDetails, Integer> {

}
