package com.github.it89.investordaybook.dao;

import com.github.it89.investordaybook.model.User;

public interface UserDAO {
    User findByLogin(String login);

    void save(User user);
}
