package com.example.login.services;

import javax.servlet.http.Cookie;

public interface CookiesManager {
    void createCookie(String value);

    Cookie getCookieByName();

    void removeCookie();
}
