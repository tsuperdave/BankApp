package com.bankapp.BankApp.repository;

import com.bankapp.BankApp.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByUsername(String username);
}
