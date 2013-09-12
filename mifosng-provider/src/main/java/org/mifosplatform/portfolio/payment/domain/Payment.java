package org.mifosplatform.portfolio.payment.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.joda.time.LocalDate;
import org.mifosplatform.infrastructure.core.domain.AbstractAuditableCustom;
import org.mifosplatform.useradministration.domain.AppUser;

@Entity
@Table(name = "payments")
public class Payment extends AbstractAuditableCustom<AppUser, Long> {

	@Column(name = "client_id", nullable = false)
	private Long client_id;

	@Column(name = "amount_paid", scale = 6, precision = 19, nullable = false)
	private BigDecimal amountPaid;

	@Column(name = "bill_id", nullable = false)
	private Long statementId;


	@Column(name = "is_deleted", nullable = false)
	private boolean deleted = false;

	@Temporal(TemporalType.DATE)
	@Column(name = "payment_date")
	private Date paymentDate;

	@Column(name = "Remarks")
	private String remarks;

	@Column(name = "paymode_code")
	private String paymode_code;

	public Payment()
	{}

	public Payment(final Long client_id, final Long payment_id,
			final Long external_id, final BigDecimal amountPaid,
			final Long statmentId, final LocalDate paymentDate,
			final String remark, final String Paymode_code) {

		this.client_id = client_id;

		this.statementId = statmentId;
		this.amountPaid = amountPaid;
		this.paymentDate = paymentDate.toDate();
		this.remarks = remark;
		this.paymode_code = Paymode_code;

	}

	public Long getClient_id() {
		return client_id;
	}

	public BigDecimal getAmountPaid() {
		return amountPaid;
	}

	public Long getStatementId() {
		return statementId;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public String getRemarks() {
		return remarks;
	}

	public String getPaymode_code() {
		return paymode_code;
	}

	public void updateBillId(Long billId) {
		this.statementId=billId;

	}

}
