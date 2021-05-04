package com.bankapp.BankApp.models;

import lombok.Data;

import javax.persistence.*;
import java.util.*;

@Data
//@Entity(name="BankAccount")
@MappedSuperclass
public abstract class BankAccount {
	
		@Id
		@GeneratedValue(strategy=GenerationType.AUTO)
		private Integer id;

		@ManyToOne
		@JoinColumn(name = "account_holder_id")
		private AccountHolder accountHolder;

		double balance;
		Date openedOn;
}
