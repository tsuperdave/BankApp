package com.bankapp.BankApp.models;

import lombok.Data;

import javax.persistence.*;
import java.util.*;

@Data
@Entity(name = "CDOffering")
@Table(name = "CheckingAccount")
public class CDOffering {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "offering_id")
    private Integer id;

    Integer term;
    double interestRate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cdOffering")
    private List<CDAccount> cdAccountList;

}

