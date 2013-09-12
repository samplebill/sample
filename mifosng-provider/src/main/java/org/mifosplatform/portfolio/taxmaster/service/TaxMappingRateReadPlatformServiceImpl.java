package org.mifosplatform.portfolio.taxmaster.service;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import org.mifosplatform.infrastructure.core.service.TenantAwareRoutingDataSource;
import org.mifosplatform.infrastructure.security.service.PlatformSecurityContext;
import org.mifosplatform.portfolio.charge.data.ChargeCodeData;
import org.mifosplatform.portfolio.taxmaster.data.TaxMasterData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
@Service
public class TaxMappingRateReadPlatformServiceImpl implements TaxMappingRateReadPlatformService {

	private final JdbcTemplate jdbcTemplate;
	private final PlatformSecurityContext context;

	@Autowired
	public  TaxMappingRateReadPlatformServiceImpl(final PlatformSecurityContext context, final TenantAwareRoutingDataSource dataSource) {
		this.context = context;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	@Override
	public Collection<TaxMasterData> retrieveTaxMappingRateforTemplate() {
		this.context.authenticatedUser();

		TaxMasterMapper mapper = new TaxMasterMapper();
		String sql = "select " + mapper.schema();

		return this.jdbcTemplate.query(sql, mapper, new Object[] {});

	}


	protected static final class TaxMasterMapper implements RowMapper<TaxMasterData> {

		@Override
		public TaxMasterData mapRow(final ResultSet rs, @SuppressWarnings("unused") final int rowNum)
				throws SQLException {
			Long id = rs.getLong("id");
			String taxCode=rs.getString("tax_code");
			return new TaxMasterData(id,taxCode);
		}
		public String schema() {
			return "d.id as id , d.tax_code as tax_code from tax_master d";
		}
	}
	@Override
	public Collection<ChargeCodeData> retrieveChargeCodeForTemplate() {
		this.context.authenticatedUser();

		ChargeCodeMapper mapper = new ChargeCodeMapper();
		String sql = "select " + mapper.schema();

		return this.jdbcTemplate.query(sql, mapper, new Object[] {});
	}
	protected static final class ChargeCodeMapper implements RowMapper<ChargeCodeData> {

		@Override
		public ChargeCodeData mapRow(final ResultSet rs, @SuppressWarnings("unused") final int rowNum)
				throws SQLException {
			Long id = rs.getLong("id");
			String chargeCode=rs.getString("charge_code");
			return new ChargeCodeData(id,chargeCode);

		}

		public String schema() {
			return "d.id as id , d.charge_code as charge_code from charge_codes d";
		}
	}


}
