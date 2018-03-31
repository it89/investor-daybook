package com.github.it89.investordaybook.repository;

import com.github.it89.investordaybook.model.AppUser;
import com.github.it89.investordaybook.model.daybook.Security;
import com.github.it89.investordaybook.model.daybook.TradeAccount;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TradeAccountRepository extends CrudRepository<TradeAccount, Long> {
    @Query("SELECT a FROM TradeAccount a WHERE UPPER(a.code) = UPPER(:code) AND a.appUser = :appUser")
    public TradeAccount findByCodeAndAppUser(@Param("code") String code, @Param("appUser") AppUser appUser);
}
