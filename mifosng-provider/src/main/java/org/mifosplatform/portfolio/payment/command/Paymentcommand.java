package org.mifosplatform.portfolio.payment.command;

import java.math.BigDecimal;

import org.joda.time.LocalDate;


public class Paymentcommand {

	private final Long clientId;
	private final Long paymentId;
	private final Long externalId;
	private final Long statementId;
	private final String paymentCode;
	private final String remarks;
	private final BigDecimal amountPaid;
	private final LocalDate paymentDate;


public Paymentcommand(final Long clientId,final Long paymentId,final Long externalId,final Long statementId,final String paymentCode,
		final String remarks, final BigDecimal amountPaid,final LocalDate paymentDate )

{
	this.clientId=clientId;
	this.paymentId=paymentId;
	this.externalId=externalId;
	this.statementId=statementId;
	this.paymentCode=paymentCode;
	this.remarks=remarks;
	this.amountPaid=amountPaid;
	this.paymentDate=paymentDate;
}


public Long getClientId() {
	return clientId;
}


public Long getPaymentId() {
	return paymentId;
}


public Long getExternalId() {
	return externalId;
}


public Long getStatementId() {
	return statementId;
}


public String getPaymentCode() {
	return paymentCode;
}




public String getRemarks() {
	return remarks;
}


public BigDecimal getAmountPaid() {
	return amountPaid;
}




public LocalDate getPaymentDate() {
	return paymentDate;
}


}
