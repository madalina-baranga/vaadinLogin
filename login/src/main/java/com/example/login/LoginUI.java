package com.example.login;


import com.example.login.services.AuthenticationManager;
import com.example.login.vaadin.*;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.*;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringNavigator;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.UI;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.annotation.WebServlet;

/**
 * The main ui for the application
 */
@SpringUI
@Theme("mytheme") // custom theme found in resources/VAADIN/themes/mytheme
public class LoginUI extends UI {
    @Autowired
    private SpringNavigator navigator;

    @Autowired
    private SpringViewProvider viewProvider;

    @Autowired
    private AuthenticationManager authManager;

    @Override
    protected void init(VaadinRequest request) {
        getPage().setTitle("Login example");

        navigator.init(this, this);
        navigator.addProvider(viewProvider);
        navigator.addView(LoginView.VIEW_NAME, LoginView.class);
        navigator.setErrorView(ErrorView.class);
        viewProvider.setAccessDeniedViewClass(AccessDeniedView.class);

        //in vaadin 8.1.0 addUriFragmentChangedListener is deprecated but there's no replacement for it
        Page.getCurrent().addUriFragmentChangedListener(event -> navigator.navigateTo(event.getUriFragment()));

        if (authManager.getCookieManager().getCookieByName() == null) {
            navigator.navigateTo(LoginView.VIEW_NAME);
        }
    }

    public void logout() {
        authManager.logout();
    }

    public Boolean login(String username, String password, Boolean rememberMe){
        return authManager.authenticationRequest(username, password, rememberMe);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = LoginUI.class, productionMode = true)
    public static class Servlet extends VaadinServlet {
    }

}
