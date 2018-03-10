package com.github.it89.investordaybook.repository;

import com.github.it89.investordaybook.model.AppUser;
import com.github.it89.investordaybook.model.daybook.Security;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SecurityRepository extends CrudRepository<Security, Long> {
    public Security findByIsinAndAppUser(String isin, AppUser appUser);
    public Security findByCodeGRNAndAppUser(String codeGRN, AppUser appUser);
    @Query("SELECT s FROM Security s WHERE UPPER(s.caption) = UPPER(:caption) AND s.appUser = :appUser")
    public Security findByCaptionAndAppUser(@Param("caption") String caption, @Param("appUser") AppUser appUser);
    public List<Security> findByAppUser(AppUser appUser);
}
