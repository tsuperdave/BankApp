package com.bankapp.BankApp.services;

import com.bankapp.BankApp.models.User;

public interface UserService {
    public User registerUser(User user);
    public User getUser(int id);
    public User getUserByUserName(String username);
}
