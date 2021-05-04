package com.bankapp.BankApp.models;

import lombok.Data;

import javax.persistence.*;
import java.util.*;

@Data
@Entity(name = "AccountHolder")
@Table(name = "AccountHolder")
public class AccountHolder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "account_holder_id")
    Integer id;

    // TODO add User
    //private User user;

    String firstName;
    String middleName;
    String lastName;
    String ssn;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_holder_contact_details_id")
    AccountHolderContactDetails accountHolderContactDetails;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "accountHolder")
    private List<CheckingAccount> checkingAccountList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "accountHolder")
    private List<SavingsAccount> savingsAccountsList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "accountHolder")
    private List<CDAccount> cdAccountList;

}
