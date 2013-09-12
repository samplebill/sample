package org.mifosplatform.portfolio.order.domain;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.joda.time.LocalDate;
import org.mifosplatform.infrastructure.core.domain.JdbcSupport;
import org.mifosplatform.infrastructure.security.service.PlatformSecurityContext;
import org.mifosplatform.portfolio.plan.data.PlanData;
import org.mifosplatform.portfolio.plan.data.ServiceData;
import org.mifosplatform.portfolio.pricing.data.PriceData;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class OrderReadPlatformImpl {

	private final JdbcTemplate jdbcTemplate;
	private final PlatformSecurityContext context;



	public OrderReadPlatformImpl(PlatformSecurityContext context2,
			JdbcTemplate jdbcTemplate2) {

		this.context=context2;
		this.jdbcTemplate=jdbcTemplate2;

	}



	public List<ServiceData> retrieveAllServices(Long plan_code) {


		PlanMapper mapper = new PlanMapper();

		String sql = "select " + mapper.schema()+" and da.plan_id = '"+plan_code+"'" ;
		return this.jdbcTemplate.query(sql, mapper, new Object[] {});

	}

	private static final class PlanMapper implements RowMapper<ServiceData> {

		public String schema() {
			return "da.id as id,se.id as serviceId, da.service_code as service_code, da.plan_id as plan_code"
					+" from plan_detail da,service se where da.service_code = se.service_code";

		}

		@Override
		public ServiceData mapRow(final ResultSet rs,
				@SuppressWarnings("unused") final int rowNum)
				throws SQLException {
			Long id = rs.getLong("id");
			String serviceCode = rs.getString("service_code");
			String planCode = rs.getString("plan_code");
			Long serviceid = rs.getLong("serviceId");




			return new ServiceData(id,serviceid,serviceCode, planCode,null,null);

		}
	}
		public List<PriceData> retrieveAllPrices(Long plan_code,String billingFreq) {


			PriceMapper mapper1 = new PriceMapper();

			String sql = "select " + mapper1.schema()+" and da.plan_code = '"+plan_code+"' and (c.billfrequency_code='"+billingFreq+"'  or c.billfrequency_code='Once')";
			return this.jdbcTemplate.query(sql, mapper1, new Object[] {});

		}

		private static final class PriceMapper implements RowMapper<PriceData> {

			public String schema() {
				return "da.id as id, se.id as serviceId,da.service_code as service_code, da.charge_code as charge_code,da.charging_variant as charging_variant,"
        +" c.charge_type as charge_type,c.charge_duration as charge_duration,c.duration_type as duration_type,"
						+"da.price as price from plan_pricing da,charge_codes c,service se where da.charge_code = c.charge_code and da.service_code=se.service_code and da.is_deleted='n' ";

			}

			@Override
			public PriceData mapRow(final ResultSet rs,
					@SuppressWarnings("unused") final int rowNum)
					throws SQLException {

				Long id = rs.getLong("id");
				String service_code = rs.getString("service_code");
				String charge_code = rs.getString("charge_code");
				String charging_variant = rs.getString("charging_variant");
				BigDecimal price=rs.getBigDecimal("price");
				String charge_type = rs.getString("charge_type");
				String charge_duration = rs.getString("charge_duration");
				String duration_type = rs.getString("duration_type");
				Long serviceid = rs.getLong("serviceId");
				return new PriceData(id, service_code, charge_code,charging_variant,price,charge_type,charge_duration,duration_type,serviceid);

			}
	}
public PlanData retrievePlanData(Long id) {


	PlanMapper1 mapper2 = new PlanMapper1();

			String sql = "select " + mapper2.schema()+" where da.id = "+id ;
			return this.jdbcTemplate.queryForObject(sql, mapper2, new Object[] {});

		}

		private static final class PlanMapper1 implements RowMapper<PlanData> {

			public String schema() {
				return "da.id as id, da.plan_code as plan_code, da.start_date as start_date,da.end_date as end_date,"
						+ "da.plan_status as plan_status, da.contract_period as contract_period from plan_master da";

			}

			@Override
			public PlanData mapRow(final ResultSet rs,
					@SuppressWarnings("unused") final int rowNum)
					throws SQLException {

				Long id = rs.getLong("id");
				String plan_code = rs.getString("plan_code");
				Long charge_code =rs.getLong("plan_status");
				String contract_period = rs.getString("contract_period");
				LocalDate startDate=JdbcSupport.getLocalDate(rs,"start_date");
				LocalDate endDate=JdbcSupport.getLocalDate(rs,"end_date");



				return new PlanData(id, plan_code, charge_code,contract_period,startDate,endDate);

			}
	}


}
