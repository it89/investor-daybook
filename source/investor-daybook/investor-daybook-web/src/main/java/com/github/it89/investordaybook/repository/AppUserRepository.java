package com.github.it89.investordaybook.repository;

import com.github.it89.investordaybook.model.AppUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface AppUserRepository extends CrudRepository<AppUser, Long> {
    @Query("SELECT u FROM AppUser u WHERE UPPER(u.login) = UPPER(:login)")
    public AppUser findByLogin(@Param("login") String login);
}
