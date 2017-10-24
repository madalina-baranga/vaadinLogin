package com.example.login.vaadin;

import com.example.login.LoginUI;
import com.example.login.entities.User;
import com.example.login.services.AuthenticationService;
import com.vaadin.event.ShortcutAction;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.hibernate.annotations.Check;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import java.util.Optional;

@Scope("prototype")
@SpringView(name = LoginPage.VIEW_NAME)
public class LoginPage extends VerticalLayout implements View {
    public static final String VIEW_NAME = "";

    @Autowired
    public AuthenticationService authentication;

    public LoginPage() {
        buildLoginForm();
    }

    private void buildLoginForm() {

        Panel panel = new Panel("Login");
        addComponent(panel);

        FormLayout form = new FormLayout();
        TextField username = new TextField("Username");
        username.setPlaceholder("username");
        username.setIcon(VaadinIcons.USER);
        username.setSizeFull();

        PasswordField password = new PasswordField("Password");
        password.setPlaceholder("password");
        password.setIcon(VaadinIcons.LOCK);
        password.setSizeFull();
        password.setRequiredIndicatorVisible(true);


        CheckBox rememberMe = new CheckBox("Remember me");

        Button loginBtn = new Button("Sign in");
        loginBtn.addClickListener(e -> authenticate(username.getValue(), password.getValue()));
        loginBtn.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        loginBtn.setSizeFull();

        form.addComponents(username, password, rememberMe,loginBtn);
        panel.setContent(form);
        panel.setHeight("300");
        panel.setWidth("400");
        form.setMargin(true);
        setComponentAlignment(panel, Alignment.TOP_CENTER);
    }

    public void authenticate(String username, String password) {
        if (authenticationRequest(username, password)) {
            //LoginUI.navigator.navigateTo(MainView.MAIN_VIEW_NAME);
            Page.getCurrent().setUriFragment(MainView.MAIN_VIEW_NAME);
        } else {
            Notification.show("Invalid credentials", Notification.Type.ERROR_MESSAGE);
        }

    }

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


}
