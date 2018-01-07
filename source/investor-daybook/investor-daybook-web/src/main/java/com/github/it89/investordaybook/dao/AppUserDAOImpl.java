package com.github.it89.investordaybook.dao;

import com.github.it89.investordaybook.model.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
@Qualifier("appUserDAO")
public class AppUserDAOImpl implements AppUserDAO {
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public AppUser findByLogin(String login) {
        String sql = "select * from app_user where upper(login) = upper(:login)";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("login", login);

        return jdbcTemplate.queryForObject(sql, params, new AppUserRowMapper());
    }

    @Override
    public void save(AppUser appUser) {
        // TODO: implement
    }

    private static final class AppUserRowMapper implements RowMapper<AppUser> {

        @Override
        public AppUser mapRow(ResultSet rs, int rowNum) throws SQLException {
            AppUser appUser = new AppUser(rs.getLong("id"), rs.getString("login"), rs.getString("password"));
            return appUser;
        }

    }
}
