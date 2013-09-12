package org.mifosplatform.portfolio.order.data;

import java.util.List;

import org.joda.time.LocalDate;
import org.mifosplatform.infrastructure.core.data.EnumOptionData;
import org.mifosplatform.portfolio.payterms.data.PaytermData;
import org.mifosplatform.portfolio.plan.data.PlanCodeData;
import org.mifosplatform.portfolio.subscription.data.SubscriptionData;



public class OrderData {
	private final Long id;
	private final Long pdid;
	private final Long pcid;
	private final String service_code;
	private final String plan_code;
	private final String chargeCode;
	private  double price;
	private final String variant;
	private final EnumOptionData status;
	private final Long period;
	private LocalDate startDate;
	private LocalDate endDate;
	private String billingFrequency;
	private final List<PlanCodeData> plandata;
	private  List<PaytermData> paytermdata;
	private final  List<SubscriptionData> subscriptiondata;

	public OrderData(final Long id,final Long did,final Long cid, final String service_code,final String plan_code,final double price,final String variant,final String charge_code)
	{
		this.id=id;
		this.pcid=did;
		this.pdid=cid;
		this.service_code=service_code;
		this.plan_code=plan_code;
		this.price=price;
		this.variant=variant;
		this.chargeCode=charge_code;
		this.plandata=null;
		this.paytermdata=null;
		this.subscriptiondata=null;
		this.status=null;
		this.period=null;
	}

	public OrderData(List<PlanCodeData> allowedtypes, List<PaytermData> data, List<SubscriptionData> subscription)
	{
		this.id=null;
		this.plan_code=null;
		this.pcid=null;
		this.pdid=null;
		this.service_code=null;
	 this.startDate=new LocalDate();
		this.variant=null;
		this.chargeCode=null;
		this.paytermdata=data;
		this.plandata=allowedtypes;
		this.subscriptiondata=subscription;
		this.status=null;
		this.period=null;
	}










	public OrderData(Long id, Long plan_id, LocalDate start_date,
			Long billing_frequency, Long contarctPeriod) {

		this.id=id;
		this.pdid=plan_id;
		this.plan_code=null;
		this.status=null;
		this.period=contarctPeriod;
		this.pcid=billing_frequency;
		this.service_code=null;

		this.startDate=start_date;
		this.variant=null;
		this.chargeCode=null;
		this.paytermdata=null;
		this.plandata=null;
		this.subscriptiondata=null;

	}

	public OrderData(List<PlanCodeData> allowedtypes, List<PaytermData> data1,
			List<SubscriptionData> contractPeriod, OrderData data) {

		this.id=data.getId();
		this.pdid=data.getPdid();
		this.plan_code=data.getPlan_code();
		this.status=null;
		this.period=data.getPeriod();
		this.pcid=data.getPcid();
		this.service_code=null;

		this.startDate=data.getStartDate();
		this.variant=null;
		this.chargeCode=null;
		this.paytermdata=data1;
		this.plandata=allowedtypes;
		this.subscriptiondata=contractPeriod;

	}

	public OrderData(Long id, Long planId, String plancode,
			EnumOptionData status1, LocalDate startDate, LocalDate endDate,
			double price) {
		this.id=id;
		this.pdid=planId;
		this.plan_code=plancode;
		this.status=status1;
		this.period=null;
		this.startDate=startDate;
		this.endDate=endDate;
		this.pcid=null;
		this.service_code=null;
		this.price=price;
		this.variant=null;
		this.chargeCode=null;
		this.paytermdata=null;
		this.plandata=null;
		this.subscriptiondata=null;
	}

	public OrderData(Long id, Long orderId, Long serviceId, Long status,String serviceType) {

		this.id=id;
		this.pdid=orderId;
		this.plan_code=null;
		this.status=null;
		this.period=status;
		this.startDate=null;
		this.endDate=null;
		this.pcid=serviceId;
		this.service_code=serviceType;
		this.billingFrequency=serviceType;
		this.variant=null;
		this.chargeCode=null;
		this.paytermdata=null;
		this.plandata=null;
		this.subscriptiondata=null;

	}

	public Long getId() {
		return id;
	}

	public Long getPdid() {
		return pdid;
	}

	public Long getPcid() {
		return pcid;
	}

	public String getService_code() {
		return service_code;
	}



	public LocalDate getEndDate() {
		return endDate;
	}

	public EnumOptionData getStatus() {
		return status;
	}

	public Long getPeriod() {
		return period;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public String getPlan_code() {
		return plan_code;
	}

	public double getPrice() {
		return price;
	}

	public String getVariant() {
		return variant;
	}

	public String getChargeCode() {
		return chargeCode;
	}

	public List<PlanCodeData> getPlandata() {
		return plandata;
	}

	public List<PaytermData> getPaytermdata() {
		return paytermdata;
	}

	public List<SubscriptionData> getSubscriptiondata() {
		return subscriptiondata;
	}
public void setPaytermData(List<PaytermData> data)
{
this.paytermdata=data;
}
}
