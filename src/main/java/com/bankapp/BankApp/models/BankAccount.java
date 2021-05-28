package com.bankapp.BankApp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Data
@NoArgsConstructor
@MappedSuperclass
public abstract class BankAccount {
	
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private Integer id;

		private static long nextAccountNumber = 1;

		@ManyToOne
		@JoinColumn(name = "account_holder_id")
		@JsonIgnore
		private AccountHolder accountHolder;

		private long accountNumber;
		private double balance;
		private LocalDateTime openedOn;
		private double interestRate;

		public BankAccount(double balance) {
			this.accountNumber = nextAccountNumber++;
			this.balance = balance;
			this.interestRate = 0.01;
			this.openedOn = LocalDateTime.now();
		}
}
