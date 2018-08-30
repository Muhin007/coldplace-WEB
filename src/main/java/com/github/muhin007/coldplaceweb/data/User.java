package com.github.muhin007.coldplaceweb.data;

public class User {
    private String login;
    private String pass;
    private  String email;
    private String role;



    void setLogin(String login) {
        this.login = login;
    }

    void setPass(String pass) {
        this.pass = pass;
    }

    void setEmail (String email) {this.email = email;}

    void setRole (String role) {this.role = role;}

    @Override
    public String toString() {
        return login + " " + pass + " " + email + " " + role;
    }


    public String getLogin() {
        return login;
    }

    public String getPass() {return pass; }

    public String getEmail() {return email; }

    public String getRole() {return role; }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;
        return login.equals(user.login);
    }

    @Override
    public int hashCode() {
        int result = login.hashCode();
        result = 31 * result;
        return result;
    }
}
