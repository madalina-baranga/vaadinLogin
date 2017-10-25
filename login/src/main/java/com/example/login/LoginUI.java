package com.example.login;


import com.example.login.entities.User;
import com.example.login.services.AuthenticationService;
import com.example.login.services.CookiesManager;
import com.example.login.vaadin.AccessDeniedView;
import com.example.login.vaadin.ErrorView;
import com.example.login.vaadin.LoginView;
import com.example.login.vaadin.MainView;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.*;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringNavigator;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.UI;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.annotation.WebServlet;
import java.util.Optional;

@SpringUI
@Theme("mytheme")
public class LoginUI extends UI {

    @Autowired
    private AuthenticationService authentication;

    @Autowired
    private SpringNavigator navigator;

    @Autowired
    private SpringViewProvider viewProvider;

    @Autowired
    private CookiesManager cookieManager;

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

        if (cookieManager.getCookieByName() == null) {
            navigator.navigateTo(LoginView.VIEW_NAME);
        }
    }

    /**
     * Checks if the username and password match.
     * If they do, the session is initialized for the user, otherwise error notification is shown.
     *
     * @param username the username
     * @param password the password
     * @return true if authentication succeeds, false otherwise
     */
    public Boolean authenticationRequest(String username, String password, Boolean rememberMe) {


        boolean accessGranted = false;

        Optional<User> user = authentication.authenticate(username, password);

        if (user.isPresent()) {
            VaadinSession.getCurrent().setAttribute(User.class.getName(), user.get());

            if (rememberMe != null && rememberMe) {
                cookieManager.createCookie(username);
            }

            Page.getCurrent().setUriFragment(MainView.MAIN_VIEW_NAME);
            accessGranted = true;

        }

        return accessGranted;
    }

    /**
     * On logout, the current session is closed and user is redirected to the login page
     */
    public void logout() {
        cookieManager.removeCookie();
        getUI().getPage().setLocation("/");
        VaadinSession.getCurrent().close();
        VaadinService.getCurrentRequest().getWrappedSession().invalidate();
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = LoginUI.class, productionMode = false)
    public static class Servlet extends VaadinServlet {
    }

}
