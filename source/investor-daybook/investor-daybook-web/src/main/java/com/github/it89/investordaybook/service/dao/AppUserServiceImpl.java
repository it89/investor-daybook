package com.github.it89.investordaybook.service.dao;

import com.github.it89.investordaybook.dao.AppUserDAO;
import com.github.it89.investordaybook.model.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("appUserService")
@Transactional
public class AppUserServiceImpl implements AppUserService {
    @Autowired
    private AppUserDAO dao;

    @Override
    public AppUser findById(long id) {
        return null;
    }

    @Override
    public AppUser findByLogin(String login) {
        return dao.findByLogin(login);
    }
}
