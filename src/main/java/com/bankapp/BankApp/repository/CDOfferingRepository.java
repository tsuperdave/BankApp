package com.bankapp.BankApp.repository;

import com.bankapp.BankApp.models.CDOffering;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CDOfferingRepository extends JpaRepository<CDOffering, Integer> {

}

