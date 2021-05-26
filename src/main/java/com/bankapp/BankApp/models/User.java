package com.bankapp.BankApp.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;


@Data
@NoArgsConstructor
@Entity(name = "User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;

    @NotEmpty
    private String username;
    @NotEmpty
    private String password;

    @Column
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name="users_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    private boolean active;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
//    @JoinColumn(name = "account_holder_id")
//    @JsonIgnore
    AccountHolder accountHolder;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
