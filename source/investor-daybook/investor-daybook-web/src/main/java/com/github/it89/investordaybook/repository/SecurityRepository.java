package com.github.it89.investordaybook.repository;

import com.github.it89.investordaybook.model.AppUser;
import com.github.it89.investordaybook.model.daybook.Security;
import org.springframework.data.repository.CrudRepository;

public interface SecurityRepository extends CrudRepository<Security, Long> {
    public Security findByIsinAndAppUser(String isin, AppUser appUser);
    public Security findByCodeGRNAndAppUser(String codeGRN, AppUser appUser);
}
