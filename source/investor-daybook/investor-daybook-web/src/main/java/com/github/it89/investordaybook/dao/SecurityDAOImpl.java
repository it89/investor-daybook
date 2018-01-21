package com.github.it89.investordaybook.dao;

import com.github.it89.investordaybook.model.daybook.Security;
import com.github.it89.investordaybook.model.daybook.SecurityBond;
import com.github.it89.investordaybook.model.daybook.SecurityStock;
import com.github.it89.investordaybook.model.daybook.SecurityType;
import com.github.it89.investordaybook.service.AppUserService;
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
@Qualifier("securityDAO")
public class SecurityDAOImpl extends AbstractDAO<Security> implements SecurityDAO {
    private NamedParameterJdbcTemplate jdbcTemplate;

    private static final String ID = "id";
    private static final String ISIN = "isin";
    private static final String TICKER = "ticker";
    private static final String CAPTION = "caption";
    private static final String CODE_GRN = "code_grn";
    private static final String APP_USER_ID = "app_user_id";


    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public Security findById(long id) {
        String sql = "SELECT s.*, st.code as security_type_code " +
                "       FROM security s, security_type st " +
                "      WHERE s.security_type_id = st.id AND s.id = :id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(ID, id);
        List<Security> queryList = jdbcTemplate.query(sql, params, new SecurityDAOImpl.SecurityRowMapper());

        return getOneRecord(queryList);
    }

    @Override
    public Long findIdByIsin(String isin, long idAppUser) {
        String sql = "SELECT max(id) FROM security WHERE UPPER(isin) = UPPER(:isin) and app_user_id = :idAppUser";

        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue(ISIN, isin);
        namedParameters.addValue("idAppUser", idAppUser);

        return jdbcTemplate.queryForObject(sql, namedParameters, Long.class);
    }

    @Override
    public void save(Security security) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        String sql;
        if (security.getId() == null) {
            sql = "INSERT INTO security " +
                    "(isin, ticker, caption, code_grn, app_user_id, security_type_id) " +
                    "SELECT :isin, :ticker, :caption, :code_grn, " +
                    ":app_user_id, (SELECT st.id FROM security_type st WHERE UPPER (st.code) = UPPER(:type))" +
                    "FROM current_timestamp";
        } else {
            sql = "UPDATE security SET isin = :isin, ticker = :ticker, caption = :caption, code_grn = :code_grn," +
                    "app_user_id = :app_user_id, " +
                    "security_type_id = (select st.id from security_type st where upper(st.code) = upper(:type))" +
                    "where id = :id";
            params.addValue("id", security.getId());
        }
        params.addValue(ISIN, security.getIsin());
        params.addValue(TICKER, security.getTicker());
        params.addValue(CAPTION, security.getCaption());
        params.addValue(CODE_GRN, security.getCodeGRN());
        params.addValue(APP_USER_ID, security.getAppUser().getId());
        params.addValue("type", security.getType().toString());

        jdbcTemplate.update(sql, params);
    }

    private static final class SecurityRowMapper implements RowMapper<Security> {
        @Autowired
        private AppUserService appUserService;

        @Override
        public Security mapRow(ResultSet rs, int rowNum) throws SQLException {
            SecurityType securityType = SecurityType.valueOf(rs.getString("security_type_code"));
            Security security;
            if (securityType == SecurityType.STOCK) {
                security = new SecurityStock.Builder(rs.getString(ISIN))
                        .id(rs.getLong(ID))
                        .ticker(rs.getString(TICKER))
                        .caption(rs.getString(CAPTION))
                        .codeGRN(rs.getString(CODE_GRN))
                        .appUser(appUserService.findById(rs.getLong(APP_USER_ID)))
                        .build();
            } else if (securityType == SecurityType.BOND) {
                security = new SecurityBond.Builder(rs.getString(ISIN))
                        .id(rs.getLong(ID))
                        .ticker(rs.getString(TICKER))
                        .caption(rs.getString(CAPTION))
                        .codeGRN(rs.getString(CODE_GRN))
                        .appUser(appUserService.findById(rs.getLong(APP_USER_ID)))
                        .build();
            } else {
                throw new AssertionError("Unknown security type");
            }
            return security;
        }

    }
}
