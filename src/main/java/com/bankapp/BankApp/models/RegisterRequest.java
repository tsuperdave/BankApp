package com.bankapp.BankApp.models;

import lombok.Data;

import javax.validation.constraints.*;
import java.util.Set;

@Data
public class RegisterRequest {

    @NotEmpty
    @Size(min = 3, max = 30)
    private String username;

    @NotEmpty
    @Size(min = 5)
    private String password;

    private String email;

    private boolean active;

    private Set<String> role;
}
