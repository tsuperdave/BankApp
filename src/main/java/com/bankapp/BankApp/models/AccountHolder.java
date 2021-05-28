package com.bankapp.BankApp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.*;

@Data
@Entity
@NoArgsConstructor
public class AccountHolder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_holder_id")
    Integer id;

    @NotBlank(message = "First Name cannot be blank")
    @NotNull(message = "First name cannot be blank")
    String firstName;
    String middleName;
    @NotBlank(message = "First Name cannot be blank")
    @NotNull(message = "First name cannot be blank")
    String lastName;
    @NotBlank(message = "First Name cannot be blank")
    @NotNull(message = "First name cannot be blank")
    @Length(min = 9, max = 9)
    String ssn;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "accountHolder")
    private List<CheckingAccount> checkingAccountList = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "accountHolder")
    private List<SavingsAccount> savingsAccountsList = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "accountHolder")
    private List<CDAccount> cdAccountList = new ArrayList<>();

    @OneToOne (fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "accountHolder")
    @JoinColumn(name = "account_holder_contact_details_id")
    private AccountHolderContactDetails accountHolderContactDetails;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private double combinedBal;

    public AccountHolder(String firstName, String middleName, String lastName, String ssn) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.ssn = ssn;
    }

    public double getNumberOfCheckingAccounts() {
        if(checkingAccountList != null) {
            return checkingAccountList.size();
        }
        return 0;
    }

    public double getNumberOfSavingsAccounts() {
        if(checkingAccountList != null) {
            return savingsAccountsList.size();
        }
        return 0;
    }

    public double getNumberOfCDAccounts() {
        if(checkingAccountList != null) {
            return cdAccountList.size();
        }
        return 0;
    }

    public double getCheckingBalance() {
        double total = 0;
        if(checkingAccountList != null) {
            for(CheckingAccount ca: checkingAccountList) {
                total += ca.getBalance();
            }
            return total;
        }
        return 0;
    }

    public double getSavingsBalance() {
        double total = 0;
        if(checkingAccountList != null) {
            for(SavingsAccount sa: savingsAccountsList) {
                total += sa.getBalance();
            }
            return total;
        }
        return 0;
    }

    public double getCDBalance() {
        double total = 0;
        if(cdAccountList != null) {
            for(CDAccount cda: cdAccountList) {
                total += cda.getBalance();
            }
            return total;
        }
        return 0;
    }

    public double getCombinedAccountBalance() {
        combinedBal = getCheckingBalance() + getSavingsBalance() + getCDBalance();
        return combinedBal;
    }

    @Override
    public String toString() {
        return lastName + "," + middleName + "," + firstName + "," + ssn;
    }

}
