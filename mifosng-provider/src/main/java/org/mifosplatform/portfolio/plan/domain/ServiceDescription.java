package org.mifosplatform.portfolio.plan.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;


@Entity
@Table(name = "service")
public class ServiceDescription extends AbstractPersistable<Long>{


		@Column(name ="service_code", length=50)
	    private String service_code;


		@Column(name = "service_description", nullable = false)
		private String ser_description;

		@Column(name = "type", nullable = false)
		private String type;

		public ServiceDescription() {
			// TODO Auto-generated constructor stub
		}

		public String getService_code() {
			return service_code;
		}

		public void setService_code(String service_code) {
			this.service_code = service_code;
		}

		public String getSer_description() {
			return ser_description;
		}

		public void setSer_description(String ser_description) {
			this.ser_description = ser_description;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}






}
