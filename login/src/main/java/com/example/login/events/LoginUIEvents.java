package com.example.login.events;

/**
 * Stores custom classes for custom events
 */
public class LoginUIEvents {

    /**
     * Custom class that holds the data necessary for authentication
     */
    public static class UserLoginRequestedEvent{
        private final String username, password;
        private final Boolean rememberMe;

        public UserLoginRequestedEvent(final String username, final String password, Boolean rememberMe){
            this.username = username;
            this.password = password;
            this.rememberMe = rememberMe;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }

        public Boolean getRememberMe() {return rememberMe; }
    }

}
