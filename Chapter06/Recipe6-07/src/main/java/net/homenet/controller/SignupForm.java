package net.homenet.controller;

import org.springframework.social.connect.UserProfile;

public class SignupForm {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static SignupForm fromProviderUser(UserProfile profile) {
        SignupForm form = new SignupForm();
        form.setUsername(profile.getUsername());
        return form;
    }
}
