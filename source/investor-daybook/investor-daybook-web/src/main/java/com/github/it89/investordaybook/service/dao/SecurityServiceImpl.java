package com.github.it89.investordaybook.service.dao;

import com.github.it89.investordaybook.model.AppUser;
import com.github.it89.investordaybook.model.daybook.Security;
import com.github.it89.investordaybook.repository.SecurityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Repository
@Transactional
public class SecurityServiceImpl implements SecurityService {
    private final SecurityRepository securityRepository;

    @Autowired
    public SecurityServiceImpl(SecurityRepository securityRepository) {
        this.securityRepository = securityRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Security findById(long id) {
        return securityRepository.findById(id).get();
    }

    @Override
    @Transactional(readOnly = true)
    public Security findByIsin(String isin, AppUser appUser) {
        return securityRepository.findByIsinAndAppUser(isin, appUser);
    }

    @Override
    public void save(Security security) {
        securityRepository.save(security);
    }

    @Override
    public Security findByCodeGRN(String codeGRN, AppUser appUser) {
        return securityRepository.findByCodeGRNAndAppUser(codeGRN, appUser);
    }

    @Override
    public Security findByCaption(String caption, AppUser appUser) {
        return securityRepository.findByCaptionAndAppUser(caption, appUser);
    }

    @Override
    public List<Security> getList(AppUser appUser) {
        return securityRepository.findByAppUser(appUser);
    }
}
