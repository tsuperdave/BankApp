package com.bankapp.BankApp.models;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;


@Data
@Entity(name = "User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;

    @Min(value = 1)
    private String username;
    @Min(value = 3)
    private String password;
    private boolean active;
    private String role;


    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
//    @JoinColumn(name = "account_holder_id")
//    @JsonIgnore
    AccountHolder accountHolder;

}
