package org.mifosplatform.portfolio.adjustment.service;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.mifosplatform.infrastructure.core.domain.JdbcSupport;
import org.mifosplatform.infrastructure.core.service.TenantAwareRoutingDataSource;
import org.mifosplatform.infrastructure.security.service.PlatformSecurityContext;
import org.mifosplatform.portfolio.adjustment.data.AdjustmentData;
import org.mifosplatform.portfolio.adjustment.service.ClientBalanceReadPlatformServiceImpl.ClientBalanceMapper;
import org.mifosplatform.portfolio.clientbalance.data.ClientBalanceData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
@Service
public class AdjustmentReadPlatformServiceImpl implements AdjustmentReadPlatformService{
	private final JdbcTemplate jdbcTemplate;
	private final PlatformSecurityContext context;

	@Autowired
	public  AdjustmentReadPlatformServiceImpl(final PlatformSecurityContext context, final TenantAwareRoutingDataSource dataSource) {
		this.context = context;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}


	protected static final class AdjustmentMapperForId implements RowMapper<ClientBalanceData> {

		@Override
		public ClientBalanceData mapRow(final ResultSet rs, @SuppressWarnings("unused") final int rowNum)
				throws SQLException {
            Long id=JdbcSupport.getLong(rs, "id");
			Long clientId = JdbcSupport.getLong(rs, "client_id");
			BigDecimal balanceAmount = JdbcSupport.getBigDecimalDefaultToZeroIfNull(rs,"amount_paid");
			return new ClientBalanceData(id,clientId,balanceAmount);

		}


		public String schema() {
			return "d.id as id, d.client_id as client_id , d.amount_paid as paid_amount  from adjustment d";
		}
}




	@Override
	public List<ClientBalanceData> retrieveAllAdjustments(Long id){
		 this.context.authenticatedUser();
		 ClientBalanceMapper mapper = new ClientBalanceMapper();
		String sql = "select " + mapper.schema()+ " where d.client_id=?";
		return this.jdbcTemplate.query(sql, mapper, new Object[] {id});
	}




	@Override
	public List<AdjustmentData> retrieveAllAdjustmentsCodes() {
		 this.context.authenticatedUser();
		 context.authenticatedUser();
			PlanMapper mapper = new PlanMapper();

			String sql = "select * from adjustment_code";

			return this.jdbcTemplate.query(sql, mapper, new Object[] {});

		}

		private static final class PlanMapper implements RowMapper<AdjustmentData> {



			@Override
			public AdjustmentData mapRow(final ResultSet rs,
					@SuppressWarnings("unused") final int rowNum)
					throws SQLException {

				Long id = rs.getLong("id");
				String adjustment_code = rs.getString("adjustment_code");
				String adjustment_description = rs.getString("adjustment_description");

				return new AdjustmentData(id, adjustment_code, adjustment_description);

			}
	}



}
