package com.github.it89.investordaybook.dao;

import com.github.it89.investordaybook.model.AppUser;

public interface AppUserDAO {
    AppUser findByLogin(String login);

    void save(AppUser appUser);
}
