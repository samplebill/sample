package org.mifosplatform.portfolio.discountmaster.commands;

import java.util.Set;

public class DiscountMasterCommand {
private long discountCode;
private String discountDescription;
private String discounType;
private long discountValue;
private final Set<String> modifiedParameters;

public DiscountMasterCommand(long discountCode,String discountDescription,String discounType,long discountValue,Set<String> modifiedParameters)
{
	this.discountCode=discountCode;
	this.discountDescription=discountDescription;
	this.discounType=discounType;
	this.discountValue=discountValue;
	this.modifiedParameters=modifiedParameters;
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

public Set<String> getModifiedParameters() {
	return modifiedParameters;
}

public boolean isDiscountCodeChanged() {
    return this.modifiedParameters.contains("discountCode");
}

public boolean isDiscountDescriptionChanged() {
    return this.modifiedParameters.contains("discountDescription");
}
public boolean isDiscounTypeChanged() {
    return this.modifiedParameters.contains("discounType");
}
public boolean isDiscountValueChanged() {
    return this.modifiedParameters.contains("discountValue");
}




}