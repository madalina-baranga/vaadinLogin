package com.example.login;


import com.example.login.entities.User;
import com.example.login.services.AuthenticationService;
import com.example.login.vaadin.AccessDeniedView;
import com.example.login.vaadin.ErrorView;
import com.example.login.vaadin.LoginPage;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.NavigationStateManager;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.VaadinSession;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringNavigator;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.annotation.WebServlet;
import java.util.Optional;

@SpringUI
@Theme("valo")
public class LoginUI extends UI {

    @Autowired
    public AuthenticationService authentication;

    @Autowired
    public SpringNavigator navigator;

    @Autowired
    SpringViewProvider viewProvider;

    @Override
    protected void init(VaadinRequest request) {
        getPage().setTitle("Scrum Up");

        navigator.init(this, this);
        navigator.addProvider(viewProvider);
        navigator.addView(LoginPage.VIEW_NAME, LoginPage.class);
        navigator.setErrorView(ErrorView.class);
        viewProvider.setAccessDeniedViewClass(AccessDeniedView.class);

        Page.getCurrent().addUriFragmentChangedListener(new Page.UriFragmentChangedListener() {
            @Override
            public void uriFragmentChanged(Page.UriFragmentChangedEvent event) {
                System.out.println("============= xxx" + event.getUriFragment());
                navigator.navigateTo(event.getUriFragment());
            }
        });

        // Set default view
        NavigationStateManager stateManager = new Navigator.UriFragmentManager(getPage());
        stateManager.setState(LoginPage.VIEW_NAME);
    }

    /**
     * Checks if the username and password match.
     * If they do, the session is initialized for the user, otherwise error notification is shown.
     *
     * @param username the username
     * @param password the password
     * @return true if authentication succeeds, false otherwise
     */
    public Boolean authenticationRequest(String username, String password) {
        boolean accessGranted = false;

        Optional<User> user = authentication.authenticate(username, password);

        if (user.isPresent()) {
            VaadinSession.getCurrent().setAttribute(User.class.getName(), user.get());
            accessGranted = true;
        } else {
            Notification.show("Invalid credentials", Notification.Type.ERROR_MESSAGE);
        }

        return accessGranted;
    }

    /**
     * On logout, the current session is closed and user is redirected to the login page
     */
    public void logout() {
        VaadinSession.getCurrent().close();
        navigator.navigateTo(LoginPage.VIEW_NAME);
    }


    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = LoginUI.class, productionMode = false)
    public static class Servlet extends VaadinServlet {
    }

}
