//package com.example.login.security;
//
//import com.vaadin.spring.access.ViewAccessControl;
//import com.vaadin.ui.UI;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//
//@Component
//public class CustomViewAccessControl implements ViewAccessControl {
//    @Override
//    public boolean isAccessGranted(UI ui, String beanName) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication != null) {
//            if (beanName.equals("adminView")) {
//                return authentication.getAuthorities().contains(new SimpleGrantedAuthority("ADMIN"));
//            } else {
//                return authentication.getAuthorities().contains(new SimpleGrantedAuthority("USER"));
//            }
//        }
//        return false;
//    }
//}
