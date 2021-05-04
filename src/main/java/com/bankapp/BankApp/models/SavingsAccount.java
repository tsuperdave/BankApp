package com.bankapp.BankApp.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "SavingsAccount")
@Table(name = "SavingsAccount")
public class SavingsAccount extends BankAccount{

    double interestRate = 0.01;

}
