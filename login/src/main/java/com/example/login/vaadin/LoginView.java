package com.example.login.vaadin;

import com.example.login.LoginUI;
import com.vaadin.event.ShortcutAction;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;

@UIScope
@SpringView(name = LoginView.VIEW_NAME)
public class LoginView extends VerticalLayout implements View {
    public static final String VIEW_NAME = "login";
    private Label failureLbl;

    public LoginView() {
        buildLoginForm();
    }

    private void buildLoginForm() {

        Panel panel = new Panel("Login");

        FormLayout form = new FormLayout();

        TextField username = new TextField("Username");
        username.setPlaceholder("username");
        username.setIcon(VaadinIcons.USER);
        username.setSizeFull();
        username.setRequiredIndicatorVisible(true);
        username.setId("txt1");

        PasswordField password = new PasswordField("Password");
        password.setPlaceholder("password");
        password.setIcon(VaadinIcons.LOCK);
        password.setSizeFull();
        password.setRequiredIndicatorVisible(true);
        password.setId("txt2");

        CheckBox rememberMe = new CheckBox("Remember me");
        rememberMe.setValue(false);
        rememberMe.setId("chk1");

        Button loginBtn = new Button("Sign in");
        loginBtn.addClickListener(e -> authenticate(username.getValue(), password.getValue(), rememberMe.getValue()));
        loginBtn.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        loginBtn.setId("btn1");
        loginBtn.setSizeFull(); //increases width to match field's width

        form.addComponents(username, password, rememberMe, loginBtn);
        form.setMargin(true);

        failureLbl = new Label("Wrong credentials! Try again!");
        failureLbl.setId("failureLbl");
        failureLbl.setVisible(false);
        failureLbl.setStyleName("warning");

        VerticalLayout main = new VerticalLayout();
        main.setSizeUndefined(); //without this the failure label is not displaying the icon inline
        main.addComponents(failureLbl, form);
        main.setComponentAlignment(failureLbl, Alignment.TOP_CENTER);

        panel.setContent(main);
        panel.setSizeUndefined();
        panel.setWidth("400");

        addComponent(panel);
        setComponentAlignment(panel, Alignment.TOP_CENTER);
    }

    private void authenticate(String username, String password, Boolean rememberMe) {
        LoginUI ui = (LoginUI) UI.getCurrent();
        if (!ui.authenticationRequest(username, password, rememberMe)) {
            failureLbl.setVisible(true);
        }
    }
}
