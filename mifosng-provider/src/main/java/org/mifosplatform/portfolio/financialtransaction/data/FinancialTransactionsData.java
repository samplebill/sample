package org.mifosplatform.portfolio.financialtransaction.data;

import java.math.BigDecimal;
import java.util.Date;

import org.joda.time.LocalDate;

public class FinancialTransactionsData {

	private Long id;
	private Long transactionId;
	private Date transactionDate;
	private String transactionType;
	private BigDecimal amount;
	private LocalDate transDate;
	private LocalDate transactionalDate;
	private LocalDate billDate;
	private LocalDate dueDate;

	public FinancialTransactionsData(final Long transactionId,final Date transactionDate,String transactionType,BigDecimal amount) {
		this.transactionId = transactionId;
		this.transactionDate = transactionDate;
		this.transactionType = transactionType;
		this.amount = amount;
	}

	public FinancialTransactionsData(Long transactionId, LocalDate transDate,
			String transactionType, BigDecimal amount) {

		this.transactionId = transactionId;
		this.transDate = transDate;
		this.transactionType = transactionType;
		this.amount = amount;

	}



	public FinancialTransactionsData(Long transctionId,
			String transactionType, LocalDate transactionDate, BigDecimal amount) {
		this.transactionId = transctionId;
		this.transactionalDate = transactionDate;
		this.transactionType = transactionType;
		this.amount = amount;
	}

	public FinancialTransactionsData(Long id, LocalDate billDate,
			LocalDate dueDate, BigDecimal amount) {
		this.id=id;
		this.billDate=billDate;
		this.dueDate=dueDate;
		this.amount=amount;
	}

	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public LocalDate getTransDate() {
		return transDate;
	}

	public LocalDate getTransactionalDate() {
		return transactionalDate;
	}

	public Long getId() {
		return id;
	}

	public LocalDate getBillDate() {
		return billDate;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}


}
