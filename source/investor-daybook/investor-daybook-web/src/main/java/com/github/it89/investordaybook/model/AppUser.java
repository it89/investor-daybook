package com.github.it89.investordaybook.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "app_user")
public class AppUser {
    private long id;
    private String login;
    private String password;

    public AppUser() {}

    public AppUser(long id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    @NotNull
    @Column(name = "login", nullable = false)
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }

    @NotNull
    @Column(name = "password", nullable = false)
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "AppUser{" +
                "id=" + id +
                ", login='" + login + '\'' +
                '}';
    }
}
