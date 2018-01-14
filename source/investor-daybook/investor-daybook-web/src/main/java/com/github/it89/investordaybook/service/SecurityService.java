package com.github.it89.investordaybook.service;

import com.github.it89.investordaybook.model.AppUser;
import com.github.it89.investordaybook.model.daybook.Security;

public interface SecurityService {
    Security findById(long id);
    Long findIdByIsin(String isin, AppUser appUser);
    void save(Security security);
}
