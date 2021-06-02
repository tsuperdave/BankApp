package com.bankapp.BankApp.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(length = 20)
    private RoleName roleName;

    public Role(RoleName roleName) {
        this.roleName = roleName;
    }

}
