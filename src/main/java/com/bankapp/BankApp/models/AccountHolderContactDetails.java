package com.bankapp.BankApp.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "AccountHolderContactDetails")
@Table(name = "AccountHolderContactDetails")
public class AccountHolderContactDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_holder_contact_details_id")
    Integer id;

    Integer phoneNumber;

}
