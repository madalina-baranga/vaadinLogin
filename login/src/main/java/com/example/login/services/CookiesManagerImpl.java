package com.example.login.services;

import com.vaadin.server.VaadinService;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import java.util.Arrays;
import java.util.Optional;

@Service
public class CookiesManagerImpl implements CookiesManager {

    private static final String COOKIE_NAME = "VAADIN-COOKIE";
    private static final String COOKIE_PATH = "/";
    private static final int MAX_AGE = 120;

    @Override
    public void createCookie(String value){
        Cookie myCookie = new Cookie(COOKIE_NAME, value);
        myCookie.setMaxAge(MAX_AGE);
        myCookie.setPath(COOKIE_PATH);
        VaadinService.getCurrentResponse().addCookie(myCookie);
    }

    @Override
    public Cookie getCookieByName(){
        Cookie[] cookies = VaadinService.getCurrentRequest().getCookies();

        Optional<Cookie> cookie = Arrays.stream(cookies)
                        .filter(c -> c.getName().equals(COOKIE_NAME))
                        .findAny();

        if(cookie.isPresent()){
            return cookie.get();
        }

        return null;
    }

    @Override
    public void removeCookie(){
        Cookie cookie = new Cookie(COOKIE_NAME, "");
        cookie.setPath(COOKIE_PATH);
        cookie.setMaxAge(0);
        VaadinService.getCurrentResponse().addCookie(cookie);

    }
}
