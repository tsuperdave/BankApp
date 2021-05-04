package com.bankapp.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "CDAccount")
@Table(name = "CDAccount")
public class CDAccount extends BankAccount{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "offering_id")
    private CDOffering cdOffering;
}
