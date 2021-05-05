package com.bankapp.BankApp.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "Roles")
@Table(name = "Roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


}
