package com.github.it89.investordaybook.repository;

import com.github.it89.investordaybook.model.AppUser;
import com.github.it89.investordaybook.model.daybook.DealStock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DealStockRepository extends CrudRepository<DealStock, Long> {
    @Query("SELECT d FROM DealStock d, Security s WHERE d.security = s AND s.appUser = :appUser")
    List<DealStock> getAllByAppUser(@Param("appUser") AppUser appUser);
}
