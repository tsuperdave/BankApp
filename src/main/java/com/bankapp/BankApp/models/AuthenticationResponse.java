package com.bankapp.BankApp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class AuthenticationResponse {

   private final String jwt;
}
