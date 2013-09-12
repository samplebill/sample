package org.mifosplatform.portfolio.paymodes.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import org.mifosplatform.infrastructure.core.service.TenantAwareRoutingDataSource;
import org.mifosplatform.infrastructure.security.service.PlatformSecurityContext;
import org.mifosplatform.portfolio.paymodes.data.PaymodeData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class paymodeReadPlatformServiceImpl implements PaymodeReadPlatformService{


	private final JdbcTemplate jdbcTemplate;
	private final PlatformSecurityContext context;

	@Autowired
	public  paymodeReadPlatformServiceImpl(final PlatformSecurityContext context, final TenantAwareRoutingDataSource dataSource) {
		this.context = context;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}


   @SuppressWarnings("null")
@Transactional
	@Override
	public Collection<PaymodeData> retrieveAllPaymodes() {
	   
	
	   PaymodeMapper mapper=new PaymodeMapper();
			String sql = "select "+ mapper.paymodeSchema();
	

		return this.jdbcTemplate.query(sql, mapper, new Object[]{});
	}


private static final class PaymodeMapper implements RowMapper<PaymodeData>{

	public String paymodeSchema(){
		return " p.id as id,p.paymode_code as paymodeCode,p.paymode_description as description from paymodes p where p.is_deleted='n' ";

	}


		@Override
		public PaymodeData mapRow(ResultSet rs, @SuppressWarnings("unused") int rowNum)
				throws SQLException {

			Long id = rs.getLong("id");
			String paymodeCode = rs.getString("paymodeCode");
			String description = rs.getString("description");

			return new PaymodeData(id,paymodeCode,description);
		}

}


@Override
public PaymodeData retrieveSinglePaymode(Long paymodeId) {
	   PaymodeMapper mapper=new PaymodeMapper();
				String sql = "select "+ mapper.paymodeSchema()+"and p.id="+paymodeId;
		

			return this.jdbcTemplate.queryForObject(sql, mapper, new Object[]{});
		}
}



