package com.github.it89.investordaybook.service.dao;

import com.github.it89.investordaybook.model.AppUser;

public interface AppUserService {
    AppUser findById(long id);
    AppUser findByLogin(String login);
}
