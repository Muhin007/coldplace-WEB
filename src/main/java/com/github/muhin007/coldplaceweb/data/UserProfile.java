package com.github.muhin007.coldplaceweb.data;

import java.io.Serializable;

public class UserProfile implements Serializable {

    private String login;
    private String pass;
    private String email;
    private String role;


    public UserProfile(String login, String pass, String email, String role) {
        this.login = login;
        this.pass = pass;
        this.email = email;
        this.role = role;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public  void setRole (String role) {this.role = role; }

    public String getLogin() {
        return login;
    }

    public String getPass() {
        return pass;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {return role; }

    @Override
    public String toString() {
        return login + " " + pass + " " + email + " " + role ;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserProfile profile = (UserProfile) o;
        return login.equals(profile.login);
    }

    @Override
    public int hashCode() {
        int result = login.hashCode();
        result = 31 * result;
        return result;
    }
}
