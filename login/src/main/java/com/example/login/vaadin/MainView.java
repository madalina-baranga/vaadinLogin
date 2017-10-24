package com.example.login.vaadin;


import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@UIScope
@SpringView(name = MainView.MAIN_VIEW_NAME)
public class MainView extends VerticalLayout implements View {
    public static final String MAIN_VIEW_NAME = "main";

    public MainView(){
        addComponent(new Label("login successful"));
    }
}
