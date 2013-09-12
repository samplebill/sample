package org.mifosplatform.portfolio.taxmaster.domain;


import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.joda.time.LocalDate;
import org.springframework.data.jpa.domain.AbstractPersistable;
@Entity
@Table(name = "tax_mapping_rate")
public class TaxMappingRate extends AbstractPersistable<Long> {

	@Column(name = "charge_code", nullable = false, length = 20)
	private String chargeCode;
	@Column(name = "tax_code", nullable = false, length = 20)
	private String taxCode;
	@Column(name="start_date",nullable=false)
	private Date startdate;

	@Column(name = "type", nullable = false, length = 20)
	private String type;

	@Column(name = "rate", nullable = false, length = 20)
	private BigDecimal value;

	public TaxMappingRate(String chargeCode,String taxCode,LocalDate startdate,String type,BigDecimal value)
	{
		this.chargeCode=chargeCode;
		this.taxCode=taxCode;
		this.startdate=startdate.toDate();
		this.type=type;
		this.value=value;
	}
	public static TaxMappingRate create(String chargeCode,String taxCode,LocalDate startdate,String type,BigDecimal value)
	{
		return new TaxMappingRate(chargeCode,taxCode,startdate,type,value);
	}
	public TaxMappingRate() {
		// TODO Auto-generated constructor stub
	}
}
