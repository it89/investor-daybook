package com.github.it89.investordaybook.service;

import com.github.it89.investordaybook.model.AppUser;

public interface AppUserService {
    AppUser findByLogin(String login);
}
