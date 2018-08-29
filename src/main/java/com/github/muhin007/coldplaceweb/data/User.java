package com.github.muhin007.coldplaceweb.data;

public class User {
    private int id;
    private String login;
    private String pass;
    private  String email;


    void setID(int id) { this.id = id; }

    void setLogin(String login) {
        this.login = login;
    }

    void setPass(String pass) {
        this.pass = pass;
    }

    void setEmail (String email) {this.email = email;}

    @Override
    public String toString() {
        return id + " " + login + " " + pass + " " + email;
    }

    public int getID() {return id;}

    public String getLogin() {
        return login;
    }

    public String getPass() {return pass; }

    public String getEmail() {return email; }


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
        result = 31 * result+id;
        return result;
    }
}
