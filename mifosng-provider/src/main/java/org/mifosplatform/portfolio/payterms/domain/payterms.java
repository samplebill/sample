package org.mifosplatform.portfolio.payterms.domain;

	import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;


	@Entity
	@Table(name = "m_payments")
	public class payterms extends AbstractPersistable <Long>{

		@Column(name = "payterm_period", nullable = false)
		private Long payterm_period;

		@Column(name = "payterm_type", length=100)
		private String payterm_type;

		@Column(name = "units")
		private Long units;


		public payterms(final Long payterm_period,final Long units,final String payterm_type)
		{

			this.payterm_period=payterm_period;
			this.payterm_type=payterm_type;
			this.units=units;

		}


		public Long getPayterm_period() {
			return payterm_period;
		}


		public void setPayterm_period(Long payterm_period) {
			this.payterm_period = payterm_period;
		}


		public String getPayterm_type() {
			return payterm_type;
		}


		public void setPayterm_type(String payterm_type) {
			this.payterm_type = payterm_type;
		}


		public Long getUnits() {
			return units;
		}


		public void setUnits(Long units) {
			this.units = units;
		}






}
