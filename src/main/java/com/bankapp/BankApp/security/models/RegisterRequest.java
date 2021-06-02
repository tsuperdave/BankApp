package com.bankapp.BankApp.security.models;

import lombok.Data;

import javax.validation.constraints.*;
import java.util.Set;

@Data
public class RegisterRequest {

    @NotEmpty
    @Size(min = 3, max = 30)
    private String firstName;

    @NotEmpty
    @Size(min = 3, max = 30)
    private String lastName;

    @NotEmpty
    @Size(min = 3, max = 30)
    private String username;

    @NotEmpty
    @Size(max = 50)
    @Email
    private String email;

    @NotEmpty
    @Size(min = 5)
    private String password;

}
