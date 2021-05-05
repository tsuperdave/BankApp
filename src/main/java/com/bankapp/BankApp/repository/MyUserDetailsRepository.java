package com.bankapp.BankApp.repository;

import com.bankapp.BankApp.models.MyUserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface MyUserDetailsRepository extends JpaRepository<MyUserDetails, Integer> {
    Optional<MyUserDetails> findByUserName(String userName);
}
