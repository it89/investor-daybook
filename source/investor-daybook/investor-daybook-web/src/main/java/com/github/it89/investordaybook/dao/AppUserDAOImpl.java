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
import java.util.List;

@Repository
@Qualifier("appUserDAO")
public class AppUserDAOImpl extends AbstractDAO<AppUser> implements AppUserDAO {
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public AppUser findById(long id) {
        String sql = "select * from app_user where id = :id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        List<AppUser> queryList = jdbcTemplate.query(sql, params, new AppUserRowMapper());

        return getOneRecord(queryList);
    }

    @Override
    public AppUser findByLogin(String login) {
        String sql = "select * from app_user where upper(login) = upper(:login)";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("login", login);

        List<AppUser> queryList = jdbcTemplate.query(sql, params, new AppUserRowMapper());

        if (queryList.size() == 1) {
            return queryList.get(0);
        }
        else if (queryList.isEmpty()) {
            return null;
        } else {
            throw new AssertionError("Too many rows");
        }
    }

    @Override
    public void save(AppUser appUser) {
        // TODO: implement
    }

    private static final class AppUserRowMapper implements RowMapper<AppUser> {

        @Override
        public AppUser mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new AppUser(rs.getLong("id"), rs.getString("login"), rs.getString("password"));
        }

    }
}
