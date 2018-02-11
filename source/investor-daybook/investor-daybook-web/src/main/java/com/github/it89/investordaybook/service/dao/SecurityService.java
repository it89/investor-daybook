package com.github.it89.investordaybook.service.dao;

import com.github.it89.investordaybook.model.AppUser;
import com.github.it89.investordaybook.model.daybook.Security;

public interface SecurityService {
    Security findById(long id);
    Long findIdByIsin(String isin, AppUser appUser);
    void save(Security security);
    Security findByCodeGRN(String codeGRN, AppUser appUser);
    Security findByCaption(String caption, AppUser appUser);
}
