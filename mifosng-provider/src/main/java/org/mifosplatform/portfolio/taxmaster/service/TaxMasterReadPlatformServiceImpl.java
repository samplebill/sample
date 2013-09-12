package org.mifosplatform.portfolio.taxmaster.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import org.mifosplatform.infrastructure.core.service.TenantAwareRoutingDataSource;
import org.mifosplatform.infrastructure.security.service.PlatformSecurityContext;
import org.mifosplatform.portfolio.taxmaster.data.TaxMasterData;
import org.mifosplatform.portfolio.taxmaster.data.TaxMasterDataOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
@Service
public class TaxMasterReadPlatformServiceImpl implements TaxMasterReadPlatformService {

	private final JdbcTemplate jdbcTemplate;
	private final PlatformSecurityContext context;

	@Autowired
	public  TaxMasterReadPlatformServiceImpl(final PlatformSecurityContext context, final TenantAwareRoutingDataSource dataSource) {
		this.context = context;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public Collection<TaxMasterData> retrieveAllTaxMasterData() {
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
			String taxType=rs.getString("taxType");


			return new TaxMasterData(id,taxType);

		}


		public String schema() {
			return "d.id as id , d.taxType as taxType from m_taxmaster d";
		}
	}


	@Override
	public Collection<TaxMasterDataOptions> retrieveAllTaxes() {
		this.context.authenticatedUser();
	TaxMapper mapper= new TaxMapper();
		String sql="select "+mapper.schema()+"where dp.is_deleted=0";
		return this.jdbcTemplate.query(sql,mapper, new Object[]{});
	}

	private static final class TaxMapper implements RowMapper<TaxMasterDataOptions>{

		public String schema(){
			return " t.id as id,t.tax_code as taxCode,t.tax_description as taxDescription,t.tax_type as taxType from tax_master t where t.is_deleted='n'";

		}

		@Override
		public TaxMasterDataOptions mapRow(ResultSet rs, @SuppressWarnings("unused") int rowNum)
				throws SQLException {

			Long id = rs.getLong("id");
			String taxCode = rs.getString("taxCode");
			String taxDescription = rs.getString("taxDescription");
			Long units = rs.getLong("units");




			return new TaxMasterDataOptions(null);
		}
	}
}
