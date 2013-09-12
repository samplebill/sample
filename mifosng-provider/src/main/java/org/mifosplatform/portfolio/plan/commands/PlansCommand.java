package org.mifosplatform.portfolio.plan.commands;

import java.util.Set;

import org.joda.time.LocalDate;

public class PlansCommand {


		private final String plan_code;

		private final String plan_description;
		private final String contractPeriod;

		 private final LocalDate startDate;
		 private final LocalDate endDate;

		private Long status;
		private final String[] services;
		private String charge_code;
		private final Set<String> modifiedParameters;
		private final Long bill_rule;

		public PlansCommand(final Set<String> modifiedParameters,
				final String plan_code,final String plan_description,final LocalDate startDate,
				final LocalDate endDate,final Long status,String[] services,
				final Long bill_rule,final String charge_code,final String contractPeriod) {
			this.plan_code = plan_code;
            this.plan_description=plan_description;
			this.startDate = startDate;
			this.endDate = endDate;;
			this.status = status;
			this.services=services;
			this.charge_code=charge_code;
			this.bill_rule=bill_rule;
			this.contractPeriod=contractPeriod;

			this.modifiedParameters = modifiedParameters;
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



		public Long getBillRule() {
			return bill_rule;
		}



		public String[] getServices() {
			return services;
		}



		public String getCharge_code() {
			return charge_code;
		}

		public Set<String> getModifiedParameters() {
			return modifiedParameters;
		}



		public String getContractPeriod() {
			return contractPeriod;
		}



		public Long getBill_rule() {
			return bill_rule;
		}

		public boolean isplanCodeChanged() {
			return this.modifiedParameters.contains("plan_code");
		}

		public boolean isplanDescriptionChanged() {
			return this.modifiedParameters.contains("plan_description");
		}
		public boolean isStartDateChanged() {
			return this.modifiedParameters.contains("startDate");
		}
		public boolean isendDateChanged() {
			return this.modifiedParameters.contains("endDate");
		}

		public boolean isPlanStatusChanged() {
			return this.modifiedParameters.contains("status");
		}

		public boolean isBillingCycleChanged() {
			return this.modifiedParameters.contains("anyDayAllowed");
		}

		public boolean isBillingRuleChanged() {
			return this.modifiedParameters.contains("bill_rule");
		}

		public boolean isServicesChanged() {
			return this.modifiedParameters.contains("services");
		}

		public boolean isContractPeriodChanged() {
			return this.modifiedParameters.contains("contractPeriod");
		}


		}
