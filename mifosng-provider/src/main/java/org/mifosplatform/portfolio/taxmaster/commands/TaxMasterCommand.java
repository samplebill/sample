package org.mifosplatform.portfolio.taxmaster.commands;

public class TaxMasterCommand {


	private String taxCode;
	private String taxType;
	private String taxDescription;


public TaxMasterCommand(String taxCode,String taxType,String taxDescription)
{
	this.taxCode=taxCode;
	this.taxType=taxType;
	this.taxDescription=taxDescription;
}

public String getTaxCode() {
	return taxCode;
}

public void setTaxCode(String taxCode) {
	this.taxCode = taxCode;
}

public String getTaxType() {
	return taxType;
}

public void setTaxType(String taxType) {
	this.taxType = taxType;
}

public String getTaxDescription() {
	return taxDescription;
}

public void setTaxDescription(String taxDescription) {
	this.taxDescription = taxDescription;
}

}
