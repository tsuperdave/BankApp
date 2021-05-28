package com.bankapp.BankApp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
public class CDAccount extends BankAccount{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "offering_id")
    private CDOffering cdOffering;

    public CDAccount(double balance, CDOffering cdOffering) {
        super(balance);
        this.cdOffering = cdOffering;
    }
}
