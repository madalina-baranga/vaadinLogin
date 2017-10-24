package com.example.login.services;

import com.example.login.entities.User;
import com.example.login.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Checks if the username and password match any user from the database.
     * Also checks if the found user is active
     *
     * @param username the username
     * @param password the password
     * @return the user if found
     */
    public Optional<User> authenticate(String username, String password){
        User user = userRepository.findByUsername(username);

        if(user != null && user.getPassword().equals(password) && user.isActive()){

            user.setLastLogin(new Date());
            return Optional.of(user);
        }

        return Optional.empty();
    }
}
