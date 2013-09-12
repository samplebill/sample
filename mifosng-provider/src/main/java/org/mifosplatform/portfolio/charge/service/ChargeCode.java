package org.mifosplatform.portfolio.charge.service;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "charge_codes")
public class ChargeCode extends AbstractPersistable<Long>{

	@Column(name = "charge_code", nullable = false, length = 20, unique=true)
	private String chargeCode;

	@Column(name = "charge_description", nullable = false, length = 20)
	private String chargeDescription;

	@Column(name = "charge_type", nullable = false, length = 20)
	private String chargeType;

	public ChargeCode(String chargeCode,String chargeDescription,String chargeType)
	{
		this.chargeCode=chargeCode;
		this.chargeDescription=chargeDescription;
		this.chargeType=chargeType;
	}
	public static ChargeCode create(String chargeCode,String chargeDescription,String chargeType)
	{
		return new ChargeCode(chargeCode,chargeDescription,chargeType);
	}
	public ChargeCode() {
		// TODO Auto-generated constructor stub
	}

}
