package org.mifosplatform.portfolio.adjustment.data;
import java.math.BigDecimal;
import java.util.Date;
import org.joda.time.LocalDate;
public class AdjustmentData {
	Long id;
	Long client_id;
	LocalDate adjustment_date;
	String adjustment_code;
	String adjustment_type;
	BigDecimal amount_paid;
	Long bill_id;
	Long external_id;
	//boolean is_deleted;
	String Remarks;
/*	Long createdby_id;
	LocalDate created_date;
	LocalDate lastmodified_date;
	Long lastmodifiedby_id;*/

	public AdjustmentData(Long id,Long client_id,LocalDate adjustment_date,String adjustment_code,String adjustment_type,BigDecimal amount_paid,Long bill_id,Long external_id,
			String Remarks)
	{
		this.id=id;
		this.client_id=client_id;
		this.adjustment_date=adjustment_date;
		this.adjustment_code=adjustment_code;
		this.adjustment_type=adjustment_type;
		this.amount_paid=amount_paid;
		this.bill_id=bill_id;
		this.external_id=external_id;
		//this.is_deleted=is_deleted;
		this.Remarks=Remarks;
	/*	this.createdby_id=createdby_id;
		this.created_date=created_date;
		this.lastmodified_date=lastmodified_date;
		this.lastmodifiedby_id=lastmodifiedby_id;*/

	}



	public AdjustmentData(Long id, String adjustment_code,
			String adjustment_description) {

		this.id=id;
		this.adjustment_code=adjustment_code;
		this.adjustment_type=adjustment_description;
		// TODO Auto-generated constructor stub
	}



	public LocalDate getAdjustment_date() {
		return adjustment_date;
	}

	public void setAdjustment_date(LocalDate adjustment_date) {
		this.adjustment_date = adjustment_date;
	}

	public String getAdjustment_code() {
		return adjustment_code;
	}

	public void setAdjustment_code(String adjustment_code) {
		this.adjustment_code = adjustment_code;
	}

	public String getAdjustment_type() {
		return adjustment_type;
	}

	public void setAdjustment_type(String adjustment_type) {
		this.adjustment_type = adjustment_type;
	}

	public BigDecimal getAmount_paid() {
		return amount_paid;
	}

	public void setAmount_paid(BigDecimal amount_paid) {
		this.amount_paid = amount_paid;
	}

	public Long getBill_id() {
		return bill_id;
	}

	public void setBill_id(Long bill_id) {
		this.bill_id = bill_id;
	}

public Long getExternal_id() {
		return external_id;
	}

	public void setExternal_id(Long external_id) {
		this.external_id = external_id;
	}



	public String getRemarks() {
		return Remarks;
	}

	public void setRemarks(String remarks) {
		Remarks = remarks;
	}

/*	public Long getCreatedby_id() {
		return createdby_id;
	}

	public void setCreatedby_id(Long createdby_id) {
		this.createdby_id = createdby_id;
	}

	public LocalDate getCreated_date() {
		return created_date;
	}

	public void setCreated_date(LocalDate created_date) {
		this.created_date = created_date;
	}

	public LocalDate getLastmodified_date() {
		return lastmodified_date;
	}

	public void setLastmodified_date(LocalDate lastmodified_date) {
		this.lastmodified_date = lastmodified_date;
	}

	public Long getLastmodifiedby_id() {
		return lastmodifiedby_id;
	}

	public void setLastmodifiedby_id(Long lastmodifiedby_id) {
		this.lastmodifiedby_id = lastmodifiedby_id;
	}*/


}
