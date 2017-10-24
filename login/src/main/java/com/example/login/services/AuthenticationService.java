package com.example.login.services;

import com.example.login.entities.User;

import java.util.Optional;

public interface AuthenticationService {
    Optional<User> authenticate(String userName, String password);
}
