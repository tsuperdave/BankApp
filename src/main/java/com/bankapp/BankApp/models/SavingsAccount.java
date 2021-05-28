package com.bankapp.BankApp.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
public class SavingsAccount extends BankAccount{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public SavingsAccount(double balance) {
        super(balance);
    }

}
