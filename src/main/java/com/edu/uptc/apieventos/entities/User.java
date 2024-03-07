package com.edu.uptc.apieventos.entities;

public class User {

    private Integer id;
    private boolean isAdmin;

    public User(Integer id, boolean isAdmin) {
        this.id = id;
        this.isAdmin = isAdmin;
    }

    public Integer getId() {
        return id;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

}
