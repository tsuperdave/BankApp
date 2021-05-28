package com.bankapp.BankApp.services;

import com.bankapp.BankApp.models.User;
import com.bankapp.BankApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User registerUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUser(int id) {
        return userRepository.getOne(id);
    }

    @Override
    public User getUserByUserName(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }
}
