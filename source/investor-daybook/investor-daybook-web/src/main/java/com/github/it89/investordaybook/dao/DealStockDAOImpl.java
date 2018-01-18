package com.github.it89.investordaybook.dao;

import com.github.it89.investordaybook.model.daybook.*;
import com.github.it89.investordaybook.service.AppUserService;
import com.github.it89.investordaybook.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class DealStockDAOImpl extends AbstractDAO<DealStock> implements DealStockDAO {
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public DealStock findById(long id) {
        String sql = "SELECT ds.*, o.code as operation_code " +
                "       FROM deal_stock_v ds, trade_operation o " +
                "      WHERE ds.id_trade_operation = o.id AND s.id = :id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("ID", id);
        List<DealStock> queryList = jdbcTemplate.query(sql, params, new DealStockRowMapper());

        return getOneRecord(queryList);
    }

    @Override
    public Long findIdByIsin(String dealNumber, long idAppUser) {
        return null;
    }

    @Override
    public void save(DealStock deal) {

    }

    private static final class DealStockRowMapper implements RowMapper<DealStock> {
        @Autowired
        private AppUserService appUserService;
        @Autowired
        private SecurityService securityService;

        @Override
        public DealStock mapRow(ResultSet rs, int rowNum) throws SQLException {
            TradeOperation operation = TradeOperation.valueOf(rs.getString("operation_code"));
            return new DealStock.Builder(rs.getString("deal_number"))
                    .id(rs.getLong("id"))
                    .security(securityService.findById(rs.getLong("id_security")))
                    .dateTime(LocalDateTime.parse(rs.getString("date_time")))
                    .operation(operation)
                    .amount(rs.getLong("amount"))
                    .volume(new BigDecimal(rs.getString("volume")))
                    .commission(new BigDecimal(rs.getString("commission")))
                    .appUser(appUserService.findById(rs.getLong("id_app_user")))
                    .price(new BigDecimal(rs.getString("price")))
                    .build();
        }

    }
}
