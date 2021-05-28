package com.bankapp.BankApp.controller;

import com.bankapp.BankApp.models.*;
import com.bankapp.BankApp.security.models.AuthenticationRequest;
import com.bankapp.BankApp.security.models.AuthenticationResponse;
import com.bankapp.BankApp.services.MyUserDetailsService;
import com.bankapp.BankApp.services.UserService;
import com.bankapp.BankApp.security.util.JwtUtil;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api")
public class BankController {

    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private MyUserDetailsService myUserDetailsService;
    @Autowired
    private JwtUtil jwtTokenUtil;

    // START
    @GetMapping(value = "/")
    public String start() {
        return "Welcome to the jungle";
    }

    @PreAuthorize("hasAuthority('AccountHolder')")
    @GetMapping(value = "/user")
    public String user() {
        return ("Welcome Account Holder");
    }

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping(value = "/admin")
    public String admin() {
        return ("Welcome Admin");
    }

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping(value="/adminTest")
    public String adminTest() {
        return ("Secured Admin Test Working");
    }

    @PostMapping(value = "/authenticate")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        } catch(BadCredentialsException bce) {
            throw new Exception("Incorrect username or password", bce);
        }
        final UserDetails userDetails = myUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @PreAuthorize("hasAuthority('admin')")
    @PostMapping(value = "/authenticate/registerUser")
    @ResponseStatus(HttpStatus.CREATED)
    public User registerUser(@RequestBody User user) {
        return userService.registerUser(user);
    }


}
