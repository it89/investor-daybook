package com.github.it89.investordaybook.service.dao;

import com.github.it89.investordaybook.model.AppUser;
import com.github.it89.investordaybook.model.daybook.Security;

import java.util.List;

public interface SecurityService {
    Security findById(long id);
    Security findByIsin(String isin, AppUser appUser);
    void save(Security security);
    Security findByCodeGRN(String codeGRN, AppUser appUser);
    Security findByCaption(String caption, AppUser appUser);
    List<Security> getList(AppUser appUser);
}
