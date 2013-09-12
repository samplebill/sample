package org.mifosplatform.portfolio.adjustment.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.joda.time.LocalDate;
import org.mifosplatform.infrastructure.core.domain.AbstractAuditableCustom;
import org.mifosplatform.useradministration.domain.AppUser;

@Entity
@Table(name = "adjustment")
public class Adjustment extends AbstractAuditableCustom<AppUser, Long> {

	@Column(name = "client_id", nullable = false, length = 20)
	private Long client_id;

	@Column(name = "adjustment_date", nullable = false)
	private Date adjustment_date;

	@Column(name = "adjustment_code", nullable = false, length = 10)
	private String adjustment_code;

	@Column(name = "adjustment_type", nullable = false, length = 20)
	private String adjustment_type;

	@Column(name = "adjustment_amount", nullable = false, length = 20)
	private BigDecimal amount_paid;

	@Column(name = "bill_id", nullable = false, length = 20)
	private Long bill_id;

	@Column(name = "external_id", nullable = false, length = 20)
	private Long external_id;
	/*
	 * @Column(name = "is_deleted", nullable = false) private boolean
	 * is_deleted;
	 */

	@Column(name = "remarks", nullable = false, length = 200)
	private String Remarks;

	@OrderBy(value = "id")
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "clientId", orphanRemoval = true)
	private List<ClientBalance> clientBalances = new ArrayList<ClientBalance>();

	@OrderBy(value = "id")
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "client_id", orphanRemoval = true)
	private List<Adjustment> adjustment = new ArrayList<Adjustment>();



	/*
	 * @Column(name = "createdby_id", nullable = false, length = 20) private
	 * Long createdby_id;
	 *
	 * @Column(name = "created_date", nullable = false) private Date
	 * created_date;
	 *
	 * @Column(name = "lastmodified_date", nullable = false) private Date
	 * lastmodified_date;
	 *
	 * @Column(name = "lastmodifiedby_id", nullable = false, length = 20)
	 * private Long lastmodifiedby_id;
	 */


	public Adjustment(Long client_id, LocalDate adjustment_date,
			String adjustment_code, String adjustment_type,
			BigDecimal amount_paid, Long bill_id, Long external_id,
			String Remarks) {
		this.client_id = client_id;
		this.adjustment_date = adjustment_date.toDate();
		this.adjustment_code = adjustment_code;
		this.adjustment_type = adjustment_type;
		this.amount_paid = amount_paid;
		this.bill_id = bill_id;
		this.external_id = external_id;
		// this.is_deleted=is_deleted;
		this.Remarks = Remarks;
		/*
		 * this.createdby_id=createdby_id;
		 * this.created_date=created_date.toDate();
		 * this.lastmodified_date=lastmodified_date.toDate();
		 * this.lastmodifiedby_id=lastmodifiedby_id;
		 */
	}

	public static Adjustment create(Long client_id, LocalDate adjustment_date,
			String adjustment_code, String adjustment_type,
			BigDecimal amount_paid, Long bill_id, Long external_id,
			String Remarks) {
		return new Adjustment(client_id, adjustment_date, adjustment_code,
				adjustment_type, amount_paid, bill_id, external_id, Remarks);
	}

	public Adjustment() {

	}

	public void updateclientBalances(ClientBalance clientBalance) {
		clientBalance.updateClient(client_id);
		this.clientBalances.add(clientBalance);

	}

	public void updateAdjustmen(Adjustment adjustment)
	{
		adjustment.updateAdjustmen(adjustment);
		this.adjustment.add(adjustment);
	}


	public List<ClientBalance> getClientBalances() {
		return clientBalances;
	}

	public void updateBillId(Long billId) {
	this.bill_id=billId;

	}

}