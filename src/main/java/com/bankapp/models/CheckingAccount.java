package com.bankapp.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "CheckingAccount")
@Table(name = "CheckingAccount")
public class CheckingAccount extends BankAccount{
	
	double interestRate = 0.0001;
	
}
