package com.example.login.services;

import com.example.login.entities.User;
import com.example.login.events.LoginEventBus;
import com.example.login.events.LoginUIEvents;
import com.example.login.vaadin.MainView;
import com.google.common.eventbus.Subscribe;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Handles the login/logout functionality and provides access to the cookie management
 */
@Service
public class AuthenticationManager {

    @Autowired
    private AuthenticationService authService;

    @Autowired
    private CookieManager cookieManager;

    public AuthenticationManager() {
        LoginEventBus.register(this);
    }

    /**
     * Called only when using guava event subscription.
     * Checks if a user exists for the given username and password.
     * If remember me os checked, it creates a cookie on server side.
     * @param userEvent that holds the data necessary for authentication
     * @return nothing because google guava is not supporting this feature yet
     */
    @Subscribe
    public void authenticationRequest(LoginUIEvents.UserLoginRequestedEvent userEvent) {

        Optional<User> user = authService.authenticate(userEvent.getUsername(), userEvent.getPassword());

        if (user.isPresent()) {
            VaadinSession.getCurrent().setAttribute(User.class.getName(), user.get());

            if (userEvent.getRememberMe() != null && userEvent.getRememberMe()) {
                cookieManager.createCookie(userEvent.getUsername());
            }

            Page.getCurrent().setUriFragment(MainView.MAIN_VIEW_NAME);

        }
    }

    /**
     * Called when using mainstream authentication (by method calling).
     * Redirected from the main ui class.
     * @param username
     * @param password
     * @param rememberMe
     * @return
     */
    public Boolean authenticationRequest(String username, String password, Boolean rememberMe) {


        boolean accessGranted = false;

        Optional<User> user = authService.authenticate(username, password);

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
     * The current session is closed and user is redirected to the login page.
     * Removes cookie for the current user if found
     */
    public void logout() {
        cookieManager.removeCookie();
        (UI.getCurrent()).getUI().getPage().setLocation("/");
        VaadinSession.getCurrent().close();
        VaadinService.getCurrentRequest().getWrappedSession().invalidate();
    }

    /**
     * Getter for the Cookie Manager
     * @return Cookie Manager
     */
    public CookieManager getCookieManager(){
        return cookieManager;
    }
}
