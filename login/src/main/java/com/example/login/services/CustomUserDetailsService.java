//package com.example.login.services;
//
//import com.example.login.entities.Role;
//import com.example.login.entities.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class CustomUserDetailsService implements UserDetailsService {
////    @Override
////    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
////        User user = new User();
////        List<Role> roles = new ArrayList<>();
////        if(username.equals("user"))
////        {
////            Role role = new Role();
////            user.setUsername("user");
////            user.setPassword("123");
////            role.setName("ROLE_ADMIN");
////            roles.add(role);
////            user.setRoles(roles);
////        }
////
////        return user;
////    }
//}
