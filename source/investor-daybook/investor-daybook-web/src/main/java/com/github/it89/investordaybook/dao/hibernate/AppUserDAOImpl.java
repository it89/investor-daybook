package com.github.it89.investordaybook.dao.hibernate;

import com.github.it89.investordaybook.dao.AppUserDAO;
import com.github.it89.investordaybook.model.AppUser;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository("appUserDAO")
public class AppUserDAOImpl implements AppUserDAO {
    @Override
    @Transactional(readOnly = true)
    public AppUser findById(long id) {
        AppUser appUser = new AppUser();
        appUser.setId(-1);
        appUser.setLogin("1");
        appUser.setPassword("1");
        return appUser;
    }

    @Override
    @Transactional(readOnly = true)
    public AppUser findByLogin(String login) {
        AppUser appUser = new AppUser();
        appUser.setId(-1);
        appUser.setLogin("Find by");
        appUser.setPassword("Login");
        return appUser;
    }

    @Override
    public void save(AppUser appUser) {

    }
}
