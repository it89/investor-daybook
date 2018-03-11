package com.github.it89.investordaybook.repository;

import com.github.it89.investordaybook.model.AppUser;
import com.github.it89.investordaybook.model.daybook.DealBond;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DealBondRepository extends CrudRepository<DealBond, Long> {
    @Query("SELECT d FROM DealBond d, Security s WHERE d.security = s AND s.appUser = :appUser")
    List<DealBond> getAllByAppUser(@Param("appUser") AppUser appUser);
}
