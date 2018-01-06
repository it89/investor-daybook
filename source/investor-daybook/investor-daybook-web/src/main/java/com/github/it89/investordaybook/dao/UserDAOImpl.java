package com.github.it89.investordaybook.dao;

import com.github.it89.investordaybook.model.User;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository("userDao")
public class UserDAOImpl extends AbstractDao<Long, User> implements UserDAO {
    @Override
    public User findByLogin(String login) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("login", login));
        User user = (User) crit.uniqueResult();
        return user;
    }

    @Override
    public void save(User user) {
        persist(user);
    }
}
