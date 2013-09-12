package org.mifosplatform.infrastructure.configuration.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.mifosplatform.infrastructure.configuration.data.PeriodData;
import org.mifosplatform.infrastructure.core.service.TenantAwareRoutingDataSource;
import org.mifosplatform.infrastructure.security.service.PlatformSecurityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;


@Service
public class PeriodReadPlatformServiceImpl implements PeriodReadPlatformService{


	  private final JdbcTemplate jdbcTemplate;
	    private final PlatformSecurityContext context;

	    @Autowired
	    public PeriodReadPlatformServiceImpl(final PlatformSecurityContext context, final TenantAwareRoutingDataSource dataSource) {
	        this.context = context;
	        this.jdbcTemplate = new JdbcTemplate(dataSource);
	    }
	@Override
	public List<PeriodData> retrieveAllPlatformPeriod() {
		  context.authenticatedUser();

	        String sql = "select distinct s.contract_type as subscription_type from contract_period s";

	        RowMapper<PeriodData> rm = new PeriodMapper();

	        return this.jdbcTemplate.query(sql, rm, new Object[] {});
	}

	 private static final class PeriodMapper implements RowMapper<PeriodData> {

	        @Override
	        public PeriodData mapRow(final ResultSet rs, @SuppressWarnings("unused") final int rowNum) throws SQLException {

	     //   Long id = rs.getLong("id");
	            String type = rs.getString("subscription_type");
	          //  String contractType = rs.getString("contract_type");

	            return new PeriodData(type,null,type);
	        }
	 }
}