package com.github.it89.investordaybook.service;

import com.github.it89.investordaybook.dao.UserDAO;
import com.github.it89.investordaybook.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO dao;

    @Override
    public User findByLogin(String login) {
        User user = dao.findByLogin(login);
        return user;
    }
}
