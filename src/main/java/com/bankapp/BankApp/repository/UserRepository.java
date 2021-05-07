package com.bankapp.BankApp.repository;

import com.bankapp.BankApp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface UserRepository extends JpaRepository<User, Integer> {
    public User findByUsername(String userName);

    public boolean existsByUsername(String username);
}
