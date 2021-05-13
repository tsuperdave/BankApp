package com.bankapp.BankApp.services;


import com.bankapp.BankApp.models.MyUserDetails;
import com.bankapp.BankApp.models.User;
import com.bankapp.BankApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    /**
     *
     * @param username is for locating user in repository
     * @return instance of user Obj
     * @throws UsernameNotFoundException if user is not found
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //        return new User("foo", "foo", new ArrayList<>());
        Optional<User> user = userRepository.findByUsername(username);

        user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + username));

        return user.map(MyUserDetails::new).get();
    }

    public void addUser(User user) {
        userRepository.save(user);
    }

    public boolean isUser(User user) {
        String role = user.getRole();
        return role.equalsIgnoreCase("admin");
    }

    public boolean isAdmin(User user) {
        String role = user.getRole();
        return role.equalsIgnoreCase("user");
    }
}
