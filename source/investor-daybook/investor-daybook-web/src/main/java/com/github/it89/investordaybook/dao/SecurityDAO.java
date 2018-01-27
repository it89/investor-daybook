package com.github.it89.investordaybook.dao;

import com.github.it89.investordaybook.model.daybook.Security;

public interface SecurityDAO {
    Security findById(long id);
    Long findIdByIsin(String isin, long idAppUser);
    void save(Security security);
}
