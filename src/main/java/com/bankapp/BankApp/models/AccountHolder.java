package com.bankapp.BankApp.models;

import lombok.Data;

import javax.persistence.*;
import java.util.*;

@Data
@Entity(name = "AccountHolder")
@Table(name = "AccountHolder")
public class AccountHolder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_holder_id")
    Integer id;

    String firstName;
    String middleName;
    String lastName;
    String ssn;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_holder_contact_details_id")
    AccountHolderContactDetails accountHolderContactDetails;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "accountHolder")
    private List<CheckingAccount> checkingAccountList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "accountHolder")
    private List<SavingsAccount> savingsAccountsList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "accountHolder")
    private List<CDAccount> cdAccountList;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

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
        return getCheckingBalance() + getSavingsBalance() + getCDBalance();
    }

}
