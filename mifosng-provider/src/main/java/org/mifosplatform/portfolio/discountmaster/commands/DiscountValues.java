package org.mifosplatform.portfolio.discountmaster.commands;

public class DiscountValues {
	public String discountType;

	public DiscountValues(String discountType)
	{
		this.discountType=discountType;
	}
	public String getDiscountType() {
		return discountType;
	}

	public void setDiscountType(String discountType) {
		this.discountType = discountType;
	}


}