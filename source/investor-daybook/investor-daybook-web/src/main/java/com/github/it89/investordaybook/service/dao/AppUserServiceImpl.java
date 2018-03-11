package com.github.it89.investordaybook.service.dao;

import com.github.it89.investordaybook.model.AppUser;
import com.github.it89.investordaybook.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Repository
@Transactional
public class AppUserServiceImpl implements AppUserService{
    private final AppUserRepository appUserRepository;

    @Autowired
    public AppUserServiceImpl(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public AppUser findById(long id) {
        return appUserRepository.findById(id).get();
    }

    @Override
    @Transactional(readOnly = true)
    public AppUser findByLogin(String login) {
        return appUserRepository.findByLogin(login);
    }
}
