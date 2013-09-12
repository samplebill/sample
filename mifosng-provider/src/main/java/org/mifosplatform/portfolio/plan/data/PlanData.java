package org.mifosplatform.portfolio.plan.data;


import java.util.List;

import org.joda.time.LocalDate;
import org.mifosplatform.infrastructure.core.data.EnumOptionData;
import org.mifosplatform.portfolio.billingorder.data.BillRuleData;
import org.mifosplatform.portfolio.subscription.data.SubscriptionData;


public class PlanData {

		private final Long id;
		private final Long billRule;

		private final String plan_code;
		private final String plan_description;
		private  LocalDate startDate;
		private final LocalDate endDate;
		private final Long status;
		private  EnumOptionData planstatus;
		private final String service_description;
		private  final List<ServiceData> services;
		private final List<ServiceData>selectedServices;
		private List<String> contractPeriod;
		private List<SubscriptionData> subscriptiondata;
		private List<BillRuleData> statusType;
		private List<EnumOptionData> planStatus;
		private final String Period;

		private PlanData datas;
		private long statusname;



		public  PlanData(final Long id,final String plan_code)
		{

			this.id=id;
			this.plan_code=plan_code;
			this.plan_description=null;
			this.endDate=null;
			this.startDate=null;
			this.subscriptiondata=null;
			this.status=null;
			this.statusType=null;
			this.services=null;
			this.service_description=null;
			this.billRule=null;
			this.Period=null;
			this.datas=null;
			this.selectedServices=null;

		}



		public PlanData(Long id, String plan_code,
				String service_description, LocalDate start_date,
				Long plan_status, LocalDate end_date,EnumOptionData status) {
			this.id=id;
			this.plan_code=plan_code;
			this.service_description=service_description;
			this.startDate=start_date;
			this.status=plan_status;
			this.endDate=end_date;
			this.billRule=null;
			this.plan_description=null;
			this.services=null;
			this.statusType=null;
			this.Period=null;
			this.datas=null;
			this.selectedServices=null;
			this.planstatus=status;
		}


		public PlanData(Long id, String plan_code, Long charge_code,
				String contract_period, LocalDate startDate, LocalDate endDate) {
			this.id=id;
			this.plan_code=plan_code;
			this.service_description=null;
			this.startDate=startDate;
			this.status=charge_code;
			this.billRule=null;
			this.endDate=endDate;
			this.plan_description=contract_period;
			this.services=null;
			this.statusType=null;
			this.Period=null;
			this.datas=null;
			this.selectedServices=null;
		}

		public PlanData(List<ServiceData> data, List<BillRuleData> billData,
				List<SubscriptionData> contractPeriod,
			List<EnumOptionData> status,PlanData datas,List<ServiceData> selectedservice) {
			this.id=datas.getId();
		this.plan_code=datas.getPlan_code();
			this.subscriptiondata=contractPeriod;
			this.startDate=datas.getStartDate();
			this.status=datas.getStatus();
		this.billRule=datas.getBillRule();
		this.endDate=datas.getEndDate();
			this.plan_description=datas.getPlan_description();
		this.services=data;

		this.selectedServices=selectedservice;
		this.statusType=billData;
		this.planStatus=status;
			this.service_description=null;
			this.Period=null;
			this.datas=datas;
			this.datas=null;

		}




		public PlanData(List<ServiceData> data, List<BillRuleData> billData,
				List<SubscriptionData> contractPeriod,
				List<EnumOptionData> status) {

			this.id=null;
			this.plan_code=null;
			this.subscriptiondata=contractPeriod;
			this.startDate=null;
			this.status=null;
			this.billRule=null;
			this.endDate=null;
			this.plan_description=null;
			this.services=data;
			this.statusType=billData;
			this.planStatus=status;
			this.service_description=null;
			this.Period=null;
			this.datas=null;
			this.selectedServices=null;
			this.startDate=new LocalDate();
		}



		public PlanData(Long id, String plan_code, LocalDate startDate,
				LocalDate endDate, Long bill_rule, String contractPeriod,
				 long status, String plan_description,
				long status1) {

			this.id=id;
			this.plan_code=plan_code;
		this.service_description=null;
			this.startDate=startDate;
			this.status=status;
		this.billRule=bill_rule;
			this.endDate=endDate;
			this.plan_description=plan_description;
		this.services=null;
			this.statusType=null;
		this.Period=contractPeriod;

			this.selectedServices=null;
			this.statusname=status1;
		}



		public EnumOptionData getPlanstatus() {
			return planstatus;
		}



		public PlanData getDatas() {
			return datas;
		}

		public List<ServiceData> getSelectedServices() {
			return selectedServices;
		}


		public long getStatusname() {
			return statusname;
		}







		public List<EnumOptionData> getPlanStatus() {
			return planStatus;
		}



		public String getPeriod() {
			return Period;
		}

		public void setContractPeriod(List<String> contractPeriod) {
			this.contractPeriod = contractPeriod;
		}

		public List<BillRuleData> getStatusType() {
			return statusType;
		}




		public Long getId() {
			return id;
		}

		public String getPlan_code() {
			return plan_code;
		}

		public String getPlan_description() {
			return plan_description;
		}

		public LocalDate getStartDate() {
			return startDate;
		}

		public LocalDate getEndDate() {
			return endDate;
		}

		public Long getStatus() {
			return status;
		}

		public List<ServiceData> getServicedata() {
			return services;
		}



		public Long getBillRule() {
			return billRule;
		}



		public List<String> getContractPeriod() {
			return contractPeriod;
		}





		public String getService_description() {
			return service_description;
		}



		public List<SubscriptionData> getSubscriptiondata() {
			return subscriptiondata;
		}




	}
