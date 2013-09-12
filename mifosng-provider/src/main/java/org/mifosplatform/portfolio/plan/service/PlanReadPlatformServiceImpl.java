package org.mifosplatform.portfolio.plan.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.joda.time.LocalDate;
import org.mifosplatform.infrastructure.core.data.EnumOptionData;
import org.mifosplatform.infrastructure.core.domain.JdbcSupport;
import org.mifosplatform.infrastructure.core.service.TenantAwareRoutingDataSource;
import org.mifosplatform.infrastructure.security.service.PlatformSecurityContext;
import org.mifosplatform.portfolio.billingorder.data.BillRuleData;
import org.mifosplatform.portfolio.order.data.SavingStatusEnumaration;
import org.mifosplatform.portfolio.plan.data.PlanData;
import org.mifosplatform.portfolio.plan.data.ServiceData;
import org.mifosplatform.portfolio.plan.domain.StatusTypeEnum;
import org.mifosplatform.portfolio.subscription.data.SubscriptionData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

@Service
public class PlanReadPlatformServiceImpl implements PlanReadPlatformService {

	private final JdbcTemplate jdbcTemplate;
	private final PlatformSecurityContext context;

	@Autowired
	public PlanReadPlatformServiceImpl(final PlatformSecurityContext context,
			final TenantAwareRoutingDataSource dataSource) {
		this.context = context;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<ServiceData> retrieveAllServices() {

		context.authenticatedUser();
		PlanMapper mapper = new PlanMapper();

		String sql = "select " + mapper.schema();

		return this.jdbcTemplate.query(sql, mapper, new Object[] {});

	}

	private static final class PlanMapper implements RowMapper<ServiceData> {

		public String schema() {
			return "da.id as id, da.service_code as service_code, da.service_description as service_description "
					+ " from service da where da.is_deleted='n' ";

		}

		@Override
		public ServiceData mapRow(final ResultSet rs,
				@SuppressWarnings("unused") final int rowNum)
				throws SQLException {

			Long id = rs.getLong("id");
			String serviceCode = rs.getString("service_code");
			String serviceDescription = rs.getString("service_description");
			//String serviceDescription = rs.getString("service_description");
			return new ServiceData(id,null,null,null,serviceCode, serviceDescription);

		}
	}

	@Override
	public List<BillRuleData> retrievebillRules() {

		context.authenticatedUser();

		BillRuleDataMapper mapper = new BillRuleDataMapper();

		String sql = "select " + mapper.schema();

		return this.jdbcTemplate.query(sql, mapper, new Object[] {});
	}

	private static final class BillRuleDataMapper implements
			RowMapper<BillRuleData> {

		public String schema() {
			return "b.id as id,b.billing_rule as billingRule from billing_rules b;";

		}

		@Override
		public BillRuleData mapRow(ResultSet rs, int rowNum)
				throws SQLException {

			Long id = rs.getLong("id");
			String billrules = rs.getString("billingRule");
			BillRuleData data = new BillRuleData(id, billrules);

			return data;

		}

	}

	@Override
	public List<PlanData> retrievePlanData() {

		context.authenticatedUser();

		PlanDataMapper mapper = new PlanDataMapper();

		String sql = "select " + mapper.schema();

		return this.jdbcTemplate.query(sql, mapper, new Object[] {});
	}

	private static final class PlanDataMapper implements RowMapper<PlanData> {

		public String schema() {


			return " pm.id,pm.plan_code,pm.plan_description,pm.start_date,pm.end_date,pm.plan_status "

					 +"from  plan_master pm  where pm.is_deleted='n' ";

		}

		@Override
		public PlanData mapRow(ResultSet rs, int rowNum) throws SQLException {

			Long id = rs.getLong("id");
			String plan_code = rs.getString("plan_code");
			String service_description = rs.getString("plan_description");
			LocalDate start_date = JdbcSupport.getLocalDate(rs, "start_date");
		    int plan_status = JdbcSupport.getInteger(rs,"plan_status");
			LocalDate end_date = JdbcSupport.getLocalDate(rs, "end_date");
			long plan=plan_status;
			EnumOptionData status=SavingStatusEnumaration.interestCompoundingPeriodType(plan_status);
			return new PlanData(id, plan_code, service_description, start_date,
					plan, end_date,status);
		}
	}

	@Override
	public List<SubscriptionData> retrieveSubscriptionData() {

		context.authenticatedUser();

		SubscriptionDataMapper mapper = new SubscriptionDataMapper();

		String sql = "select " + mapper.schema();

		return this.jdbcTemplate.query(sql, mapper, new Object[] {});
	}

	private static final class SubscriptionDataMapper implements
			RowMapper<SubscriptionData> {

		public String schema() {
			return " sb.id as id,sb.contract_period as subscription_type,sb.contract_duration as units "
					+ " from contract_period sb where is_deleted=0";

		}

		@Override
		public SubscriptionData mapRow(ResultSet rs, int rowNum)
				throws SQLException {

			Long id = rs.getLong("id");
			String subscription_type = rs.getString("subscription_type");
			String units = rs.getString("units");

			SubscriptionData data = new SubscriptionData(id, subscription_type);

			return data;

		}

	}

	@Override
	public List<EnumOptionData> retrieveNewStatus() {
		EnumOptionData active = SavingStatusEnumaration
				.interestCompoundingPeriodType(StatusTypeEnum.ACTIVE);
		EnumOptionData inactive = SavingStatusEnumaration
				.interestCompoundingPeriodType(StatusTypeEnum.INACTIVE);
		List<EnumOptionData> categotyType = Arrays.asList(active, inactive);
		return categotyType;

	}

	@Override
	public PlanData retrievePlanData(Long planCode) {
		  context.authenticatedUser();

	        String sql = "SELECT pm.id as id,pm.plan_code as plan_code,pm.plan_description as plan_description,pm.start_date as start_date,"
				+"pm.end_date as end_date,pm.plan_status as plan_status,pm.bill_rule as bill_rule,pm.contract_period as contract_period "
				+"  from plan_master pm  Where pm.id=? and pm.is_deleted='n'";


	        RowMapper<PlanData> rm = new ServiceMapper();

	        return this.jdbcTemplate.queryForObject(sql, rm, new Object[] { planCode });
	}


	 private static final class ServiceMapper implements RowMapper<PlanData> {

	        @Override
	        public PlanData mapRow(final ResultSet rs, @SuppressWarnings("unused") final int rowNum) throws SQLException {

	        Long id = rs.getLong("id");
	            String plan_code = rs.getString("plan_code");
	            LocalDate startDate = JdbcSupport.getLocalDate(rs, "start_date");
	            LocalDate endDate = JdbcSupport.getLocalDate(rs, "end_date");
	            Long bill_rule = rs.getLong("bill_rule");
	            int plan_status = JdbcSupport.getInteger(rs,"plan_status");
	            String plan_description = rs.getString("plan_description");
	            String contractPeriod = rs.getString("contract_period");

	            EnumOptionData status = SavingStatusEnumaration.interestCompoundingPeriodType(plan_status);
	            long status1=plan_status;
	            return new PlanData(id,plan_code,startDate,endDate,bill_rule,contractPeriod,status1,plan_description,status1);
	        }
	}

	@Override
	public List<ServiceData> getselectedService(List<ServiceData> data,
			List<ServiceData> services) {
		// TODO Auto-generated method stub
		return null;
	}



	}
