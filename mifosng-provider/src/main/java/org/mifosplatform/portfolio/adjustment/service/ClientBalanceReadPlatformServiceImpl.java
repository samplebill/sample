package org.mifosplatform.portfolio.adjustment.service;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.mifosplatform.infrastructure.core.domain.JdbcSupport;
import org.mifosplatform.infrastructure.core.service.TenantAwareRoutingDataSource;
import org.mifosplatform.infrastructure.security.service.PlatformSecurityContext;
import org.mifosplatform.portfolio.clientbalance.data.ClientBalanceData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

@Service
public class ClientBalanceReadPlatformServiceImpl implements
		ClientBalanceReadPlatformService {

	private final JdbcTemplate jdbcTemplate;
	private final PlatformSecurityContext context;

	@Autowired
	public ClientBalanceReadPlatformServiceImpl(
			final PlatformSecurityContext context,
			final TenantAwareRoutingDataSource dataSource) {
		this.context = context;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	/*
	 * @Override public Long retrieveClientBalanceId(Long id) {
	 * this.context.authenticatedUser(); return
	 * jdbcTemplate.queryForLong("select id from client_balance where client_id=? "
	 * ,id);
	 */

	@Override
	public ClientBalanceData retrieveClientBalanceId(Long id) {

		this.context.authenticatedUser();

		ClientBalanceMapper mapper = new ClientBalanceMapper();
		String sql = "select " + mapper.schema() + " where d.client_id=?";

		ClientBalanceData clientBalanceData = this.jdbcTemplate.queryForObject(
				sql, mapper, new Object[] { id });
		return clientBalanceData;

	}

	protected static final class ClientBalanceMapper implements
			RowMapper<ClientBalanceData> {

		@Override
		public ClientBalanceData mapRow(final ResultSet rs,
				@SuppressWarnings("unused") final int rowNum)
				throws SQLException {
			Long id = JdbcSupport.getLong(rs, "id");
			Long clientId = JdbcSupport.getLong(rs, "client_id");
			BigDecimal balanceAmount = JdbcSupport
					.getBigDecimalDefaultToZeroIfNull(rs, "balance_amount");
			return new ClientBalanceData(id, clientId, balanceAmount);

		}

		public String schema() {
			return "d.id as id, d.client_id as client_id , d.balance_amount as balance_amount  from client_balance d";
		}
	}

	@Override
	public List<ClientBalanceData> retrieveAllClientBalances(Long id) {

		this.context.authenticatedUser();

		ClientBalanceMapper mapper = new ClientBalanceMapper();
		String sql = "select " + mapper.schema() + " where d.client_id=?";
		return this.jdbcTemplate.query(sql, mapper, new Object[] { id });
	}

	/*
	 * @Override public Long retrieveClientBalanceId(Long id) {
	 * this.context.authenticatedUser(); ClientBalancerMapper mapper=new
	 * ClientBalancerMapper(); String sql = "select " + mapper.schema() +
	 * " where client_id=" + id; return
	 * jdbcTemplate.queryForLong("select id from client_balance where client_id=? "
	 * ,id); }
	 */

	/*
	 * protected static final class ClientBalancerMapper implements RowMapper {
	 * 
	 * @Override public Object mapRow(final ResultSet rs,
	 * @SuppressWarnings("unused") final int rowNum) throws SQLException { Long
	 * id=JdbcSupport.getLong(rs, "id"); return id; } public String schema() {
	 * return "d.id as id from client_balance d"; } }
	 */
}
