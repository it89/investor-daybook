package com.github.it89.investordaybook.dao.hibernate;

import com.github.it89.investordaybook.dao.AppUserDAO;
import com.github.it89.investordaybook.model.AppUser;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Transactional
@Repository("appUserDAO")
public class AppUserDAOImpl implements AppUserDAO {
    private SessionFactory sessionFactory;

    @Resource(name = "sessionFactory")
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional(readOnly = true)
    public AppUser findById(long id) {
        return (AppUser) sessionFactory.getCurrentSession().
                getNamedQuery("AppUser.findById").
                setParameter("id", id).uniqueResult();
    }

    @Override
    @Transactional(readOnly = true)
    public AppUser findByLogin(String login) {
        return (AppUser) sessionFactory.getCurrentSession().
                getNamedQuery("AppUser.findByLogin").
                setParameter("login", login).uniqueResult();
    }

    @Override
    public void save(AppUser appUser) {
        sessionFactory.getCurrentSession().saveOrUpdate(appUser);
    }
}
