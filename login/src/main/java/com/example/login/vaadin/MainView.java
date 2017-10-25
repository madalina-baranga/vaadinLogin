package com.example.login.vaadin;


import com.example.login.LoginUI;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

@UIScope
@SpringView(name = MainView.MAIN_VIEW_NAME)
public class MainView extends VerticalLayout implements View {
    public static final String MAIN_VIEW_NAME = "";

    public MainView(){
        addComponent(new Label("login successful"));
        Button logout = new Button("Logout");
        logout.addClickListener(e -> {
            LoginUI ui = (LoginUI) UI.getCurrent();
            ui.logout();
        });

        addComponent(logout);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        Notification notification = new Notification("Login successful!");
        notification.setPosition(Position.TOP_RIGHT);
        notification.setStyleName(ValoTheme.NOTIFICATION_SUCCESS);
        notification.setDelayMsec(2000);
        notification.show(Page.getCurrent());
    }
}
