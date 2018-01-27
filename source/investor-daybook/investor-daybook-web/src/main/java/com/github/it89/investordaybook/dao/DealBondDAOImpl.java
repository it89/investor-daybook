package com.github.it89.investordaybook.dao;

import com.github.it89.investordaybook.model.daybook.DealBond;
import com.github.it89.investordaybook.model.daybook.SecurityBond;
import com.github.it89.investordaybook.model.daybook.TradeOperation;
import com.github.it89.investordaybook.service.AppUserService;
import com.github.it89.investordaybook.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@Qualifier("dealBondDAO")
public class DealBondDAOImpl extends AbstractDAO<DealBond> implements DealBondDAO {
    private NamedParameterJdbcTemplate jdbcTemplate;
    private DataSource mDataSource;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        mDataSource = dataSource;
    }

    @Override
    public DealBond findById(long id) {
        String sql = "SELECT ds.*, o.code as operation_code " +
                "       FROM deal_bond_v ds, trade_operation o " +
                "      WHERE ds.id_trade_operation = o.id AND s.id = :id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("ID", id);
        List<DealBond> queryList = jdbcTemplate.query(sql, params, new DealBondRowMapper());

        return getOneRecord(queryList);
    }

    @Override
    public Long findIdByDealNumber(String dealNumber, long idAppUser) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(mDataSource)
                .withSchemaName("deal_pkg")
                .withFunctionName("find_deal_id_by_deal_number")
                .withReturnValue();

        SqlParameterSource args = new MapSqlParameterSource()
                .addValue("p_deal_number", dealNumber)
                .addValue("p_app_user_id", idAppUser);

        Long id = jdbcCall.executeFunction(Long.class, args);
        return id;
    }

    @Override
    public void save(DealBond deal) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(mDataSource)
                .withSchemaName("deal_pkg")
                .withFunctionName("save_deal_bond")
                .withReturnValue();

        SqlParameterSource args = new MapSqlParameterSource()
                .addValue("p_id", deal.getId())
                .addValue("p_security_id", deal.getSecurity().getId())
                .addValue("p_deal_number", deal.getDealNumber())
                .addValue("p_date_time", deal.getDateTime().toString())
                .addValue("p_trade_operation_code", deal.getOperation().toString())
                .addValue("p_amount", deal.getAmount())
                .addValue("p_volume", deal.getVolume())
                .addValue("p_commission", deal.getCommission())
                .addValue("p_app_user_id", deal.getAppUser().getId())
                .addValue("p_price_pct", deal.getPricePct())
                .addValue("p_accumulated_coupon_yield", deal.getAccumulatedCouponYield());

        Long id = jdbcCall.executeFunction(Long.class, args);
        deal.setId(id);
    }

    private static final class DealBondRowMapper implements RowMapper<DealBond> {
        @Autowired
        private AppUserService appUserService;
        @Autowired
        private SecurityService securityService;

        @Override
        public DealBond mapRow(ResultSet rs, int rowNum) throws SQLException {
            TradeOperation operation = TradeOperation.valueOf(rs.getString("operation_code"));
            return new DealBond.Builder(rs.getString("deal_number"))
                    .id(rs.getLong("id"))
                    .security((SecurityBond)securityService.findById(rs.getLong("id_security")))
                    .dateTime(LocalDateTime.parse(rs.getString("date_time")))
                    .operation(operation)
                    .amount(rs.getLong("amount"))
                    .volume(new BigDecimal(rs.getString("volume")))
                    .commission(new BigDecimal(rs.getString("commission")))
                    .appUser(appUserService.findById(rs.getLong("id_app_user")))
                    .pricePct(new BigDecimal(rs.getString("price_pct")))
                    .accumulatedCouponYield(new BigDecimal(rs.getString("accumulated_coupon_yield")))
                    .build();
        }

    }
}
