package org.mifosplatform.portfolio.charge.commands;

public class ChargeCodeCommand {
private Long id;
public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

private String chargeCode;
private String chargeDescription;
private String chargeType;

public ChargeCodeCommand(String chargeCode,String chargeDescription,String chargeType)
{
	this.chargeCode=chargeCode;
	this.chargeDescription=chargeDescription;
	this.chargeType=chargeType;
}

public String getChargeCode() {
	return chargeCode;
}

public void setChargeCode(String chargeCode) {
	this.chargeCode = chargeCode;
}

public String getChargeDescription() {
	return chargeDescription;
}

public void setChargeDescription(String chargeDescription) {
	this.chargeDescription = chargeDescription;
}

public String getChargeType() {
	return chargeType;
}

public void setChargeType(String chargeType) {
	this.chargeType = chargeType;
}

}
