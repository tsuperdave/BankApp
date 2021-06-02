package com.bankapp.BankApp.repository;

import com.bankapp.BankApp.models.Role;
import com.bankapp.BankApp.models.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByRoleName(RoleName name);
}
