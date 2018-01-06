package com.github.it89.investordaybook.service;

import com.github.it89.investordaybook.model.User;

public interface UserService {
    User findByLogin(String login);
}
