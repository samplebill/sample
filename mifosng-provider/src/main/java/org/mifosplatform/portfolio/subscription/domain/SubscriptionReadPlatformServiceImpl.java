package org.mifosplatform.portfolio.subscription.domain;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import org.mifosplatform.infrastructure.core.service.TenantAwareRoutingDataSource;
import org.mifosplatform.infrastructure.security.service.PlatformSecurityContext;
import org.mifosplatform.portfolio.subscription.data.SubscriptionData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;


@Service
public class SubscriptionReadPlatformServiceImpl implements SubscriptionReadPlatformService {



	private final PlatformSecurityContext context;
	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public SubscriptionReadPlatformServiceImpl(final PlatformSecurityContext context,final TenantAwareRoutingDataSource dataSource) {
		this.context=context;
		jdbcTemplate=new JdbcTemplate(dataSource);
	}


	@Override
	public Collection<SubscriptionData> retrieveSubscriptionDetails() {


		this.context.authenticatedUser();


	    SuscriptionMapper depositProductMapper= new SuscriptionMapper();
		String sql="select "+depositProductMapper.depositProductSchema();
		return this.jdbcTemplate.query(sql,depositProductMapper, new Object[]{});

	}

	@Override
	public Collection<SubscriptionData> retrieveAllSubscription() {
		this.context.authenticatedUser();
		SuscriptionMapper mapper= new SuscriptionMapper();
		String sql="select "+mapper.depositProductSchema()+"where dp.is_deleted=0";
		return this.jdbcTemplate.query(sql,mapper, new Object[]{});
	}

	@Override
	public SubscriptionData retrieveSubscriptionData(Long subscriptionId) {
		SuscriptionMapper depositProductMapper=new SuscriptionMapper();
		String sql = "select "+ depositProductMapper.depositProductSchema() +" where dp.id = ? and dp.is_deleted=0";

		return this.jdbcTemplate.queryForObject(sql, depositProductMapper, new Object[]{subscriptionId});
	}


private static final class SuscriptionMapper implements RowMapper<SubscriptionData>{

		public String depositProductSchema(){
			return " dp.id as id,dp.contract_period as  subscription_period,dp.contract_type as subscription_type,dp.contract_duration as units from contract_period dp ";

		}

		@Override
		public SubscriptionData mapRow(ResultSet rs, @SuppressWarnings("unused") int rowNum)
				throws SQLException {

			Long id = rs.getLong("id");
			String subscription_period = rs.getString("subscription_period");
			String subscription_type = rs.getString("subscription_type");
			Long units = rs.getLong("units");




			return new SubscriptionData(id,subscription_period,subscription_type,units,null,null);
		}



	}




}
