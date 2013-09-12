package org.mifosplatform.portfolio.discountmaster.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import org.mifosplatform.infrastructure.core.domain.JdbcSupport;
import org.mifosplatform.infrastructure.core.service.TenantAwareRoutingDataSource;
import org.mifosplatform.infrastructure.security.service.PlatformSecurityContext;
import org.mifosplatform.portfolio.discountmaster.data.DiscountMasterData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
@Service
public class DiscountMasterReadPlatformServiceImpl implements DiscountMasterReadPlatformService{

	private final JdbcTemplate jdbcTemplate;
	private final PlatformSecurityContext context;

	@Autowired
	public  DiscountMasterReadPlatformServiceImpl(final PlatformSecurityContext context, final TenantAwareRoutingDataSource dataSource) {
		this.context = context;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public Collection<DiscountMasterData> retrieveAllDiscountMasterData() {
		this.context.authenticatedUser();

		DiscountMasterMapper mapper = new DiscountMasterMapper();
		String sql = "select " + mapper.schema();

		return this.jdbcTemplate.query(sql, mapper, new Object[] {});

	}

	@Override
	public DiscountMasterData retrieveDiscountMasterData(Long id) {

           this.context.authenticatedUser();

           DiscountMasterMapper mapper = new DiscountMasterMapper();
		String sql = "select " + mapper.schema() + " where d.id=?";

		DiscountMasterData discountMasterData=this.jdbcTemplate.queryForObject(sql, mapper, new Object[] {id});
		return discountMasterData;

	}

	protected static final class DiscountMasterMapper implements RowMapper<DiscountMasterData> {

		@Override
		public DiscountMasterData mapRow(final ResultSet rs, @SuppressWarnings("unused") final int rowNum)
				throws SQLException {
            Long id=JdbcSupport.getLong(rs, "id");
			Long discountCode = JdbcSupport.getLong(rs, "discountCode");
			String discountDescription = rs.getString("discountDescription");
			String discounType = rs.getString("discounType");
			long discountValue=rs.getLong("discountValue");


			return new DiscountMasterData(id,discountCode,discountDescription,discounType,discountValue);

		}


		public String schema() {
			return "d.id as id, d.discountcode as discountcode , d.discountdescription as discountdescription, d.discountype as discountype ,d.discountvalue as " +
					"discountvalue from m_discountmaster d";
		}
}

}