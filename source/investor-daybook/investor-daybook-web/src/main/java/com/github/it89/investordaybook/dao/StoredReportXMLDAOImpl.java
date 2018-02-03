package com.github.it89.investordaybook.dao;

import com.github.it89.investordaybook.model.AppUser;
import com.github.it89.investordaybook.model.daybook.StoredReportXML;
import com.github.it89.investordaybook.service.dao.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@Qualifier("storedReportXMLDAO")
public class StoredReportXMLDAOImpl extends AbstractDAO<StoredReportXML> implements StoredReportXMLDAO {
    private NamedParameterJdbcTemplate jdbcTemplate;
    private DataSource mDataSource;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        mDataSource = dataSource;
    }

    @Override
    public StoredReportXML findById(long id) {

        String sql = "SELECT * " +
                "       FROM stored_report_xml " +
                "      WHERE id = :id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("ID", id);
        List<StoredReportXML> queryList = jdbcTemplate.query(sql, params, new StoredReportXMLMapper());

        return getOneRecord(queryList);
    }

    @Override
    public List<StoredReportXML> getList(AppUser appUser) {
        String sql = "SELECT * " +
                "       FROM stored_report_xml " +
                "      WHERE app_user_id = :appUser";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("appUser", appUser.getId());
        return jdbcTemplate.query(sql, params, new StoredReportXMLMapper());
    }

    @Override
    public void save(StoredReportXML storedReportXML) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(mDataSource)
                .withSchemaName("stored_report_xml_pkg")
                .withFunctionName("save")
                .withReturnValue();

        SqlParameterSource args = new MapSqlParameterSource()
                .addValue("p_id", storedReportXML.getId())
                .addValue("p_app_user_id", storedReportXML.getAppUser().getId())
                .addValue("p_filename", storedReportXML.getFilename())
                .addValue("p_text", storedReportXML.getText());

        Long id = jdbcCall.executeFunction(Long.class, args);
        storedReportXML.setId(id);
    }

    private static final class StoredReportXMLMapper implements RowMapper<StoredReportXML> {
        @Autowired
        private AppUserService appUserService;

        public StoredReportXML mapRow(ResultSet rs, int rowNum) throws SQLException {
            StoredReportXML storedReport = new StoredReportXML(appUserService.findById(rs.getLong("id_app_user")));
            storedReport.setFilename(rs.getString("filename"));
            storedReport.setText(rs.getString("text"));
            return storedReport;
        }
    }
}
