//package com.example.login.security;
//
//import com.vaadin.spring.annotation.EnableVaadin;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.vaadin.spring.security.annotation.EnableVaadinSecurity;
//
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(securedEnabled = true)
//public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .inMemoryAuthentication()
//                .withUser("admin").password("p").roles("ADMIN")
//                .and()
//                .withUser("user").password("p").roles("USER");
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
////        http
////                .csrf().disable() // Use Vaadin's CSRF protection
////                .authorizeRequests().antMatchers("/THEME", "/VAADIN/**", "/PUSH/**", "/UIDL/**", "/login", "/error/**", "/accessDenied/**", "/vaadinServlet/**").permitAll()
////                .and()
//////                .formLogin().loginPage("/login").permitAll() // Login page is accessible to anybody
//////                .and()
////                .authorizeRequests().antMatchers("/securedPages").hasRole("ADMIN");
//////                .and()
//////                .sessionManagement().sessionFixation().newSession(); // Create completely new session
////
////        http.csrf().disable();
////        http.exceptionHandling().accessDeniedPage("/access-denied");
//
//        // Authentication is not needed for the login page
//        // Permit access to VAADIN resources explicitly
//        // TODO: For some reason I need the "auth" URL parameter. W/o the VAADIN UI does not load
//        http.formLogin().loginPage("/login?auth");
//
//        http.logout().logoutSuccessUrl("/login");
//
//        // Any request needs to be authenticated. If a user is not authenticated => Login Page
//        //http.authorizeRequests().anyRequest().authenticated();
//        http.authorizeRequests().antMatchers("/THEME", "/VAADIN/**", "/PUSH/**", "/UIDL/**", "/login", "/error/**", "/accessDenied/**", "/vaadinServlet/**").permitAll();
//        http.csrf().disable();
//    }
//
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/css/*"); // Static resources are ignored
//    }
//}