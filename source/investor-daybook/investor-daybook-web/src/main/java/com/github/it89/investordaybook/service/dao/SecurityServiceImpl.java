package com.github.it89.investordaybook.service.dao;

import com.github.it89.investordaybook.dao.SecurityDAO;
import com.github.it89.investordaybook.model.AppUser;
import com.github.it89.investordaybook.model.daybook.Security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("securityService")
@Transactional
public class SecurityServiceImpl implements SecurityService {
    private final SecurityDAO dao;

    @Autowired
    public SecurityServiceImpl(SecurityDAO dao) {
        this.dao = dao;
    }

    @Override
    public Security findById(long id) {
        return dao.findById(id);
    }

    @Override
    public Long findIdByIsin(String isin, AppUser appUser) {
        return dao.findIdByIsin(isin, appUser.getId());
    }

    @Override
    public void save(Security security) {
        Long id = findIdByIsin(security.getIsin(), security.getAppUser());
        if (id != null) {
            security.setId(id);
            dao.save(security);
        } else {
            dao.save(security);
            id = findIdByIsin(security.getIsin(), security.getAppUser());
            security.setId(id);
        }
    }

    @Override
    public Security findByCodeGRN(String codeGRN, AppUser appUser) {
        return dao.findByCodeGRN(codeGRN, appUser);
    }

    @Override
    public Security findByCaption(String caption, AppUser appUser) {
        return dao.findByCaption(caption, appUser);
    }

    @Override
    public List<Security> getList(AppUser appUser) {
        if (appUser == null) {
            throw new IllegalArgumentException("AppUser is null");
        }
        return dao.getList(appUser);
    }


}
