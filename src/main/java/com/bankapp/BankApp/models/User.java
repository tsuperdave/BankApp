package com.bankapp.BankApp.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;


@Data
@Entity
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;

    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
    @NotEmpty
    private String role;
    private boolean active;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
//    @JoinColumn(name = "account_holder_id")
//    @JsonIgnore
    AccountHolder accountHolder;

    public User(String username) {
        this.username = username;
    }

}
