package com.bankapp.BankApp.models;

import lombok.Data;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@Entity(name = "AccountHolderContactDetails")
@Table(name = "AccountHolderContactDetails")
public class AccountHolderContactDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_holder_contact_details_id")
    Integer id;

    @NotBlank
    Integer phoneNumber;

    @NaturalId
    @NotBlank
    @Email
    String email;

}
