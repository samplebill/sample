package org.mifosplatform.portfolio.discountmaster.domain;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "m_discountmaster")
public class DiscountMaster extends AbstractPersistable<Long> {
	@Column(name = "discountcode", nullable = false, length = 20)
	private long discountCode;
	@Column(name = "discountdescription", nullable = false, length = 100)
	private String discountDescription;
	@Column(name = "discountype", nullable = false, length = 100)
	private String discounType;
	@Column(name = "discountvalue", nullable = false, length = 20)
	private long discountValue;
	public DiscountMaster(long discountCode,String discountDescription,String discounType,long discountValue)
	{
		this.discountCode=discountCode;
		this.discountDescription=discountDescription;
		this.discounType=discounType;
		this.discountValue=discountValue;
	}

	public static DiscountMaster create(long discountCode,String discountDescription,String discounType,long discountvalue)
	{
		return new DiscountMaster(discountCode,discountDescription,discounType,discountvalue);
	}

	public long getDiscountCode() {
		return discountCode;
	}

	public void setDiscountCode(long discountCode) {
		this.discountCode = discountCode;
	}

	public String getDiscountDescription() {
		return discountDescription;
	}

	public void setDiscountDescription(String discountDescription) {
		this.discountDescription = discountDescription;
	}

	public String getDiscounType() {
		return discounType;
	}

	public void setDiscounType(String discounType) {
		this.discounType = discounType;
	}

	public long getDiscountValue() {
		return discountValue;
	}

	public void setDiscountValue(long discountValue) {
		this.discountValue = discountValue;
	}



}
