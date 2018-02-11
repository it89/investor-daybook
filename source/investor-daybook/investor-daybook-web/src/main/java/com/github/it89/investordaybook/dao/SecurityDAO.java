package com.github.it89.investordaybook.dao;

import com.github.it89.investordaybook.model.AppUser;
import com.github.it89.investordaybook.model.daybook.Security;

public interface SecurityDAO {
    Security findById(long id);
    Long findIdByIsin(String isin, long idAppUser);
    void save(Security security);
    Security findByCodeGRN(String codeGRN, AppUser appUser);
    Security findByCaption(String caption, AppUser appUser);
}
