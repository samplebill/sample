
package org.mifosplatform.portfolio.savingsaccount;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.mifosplatform.infrastructure.core.serialization.GoogleGsonSerializerHelper;
import org.mifosplatform.portfolio.billingcycle.data.BillingCycleData;
import org.mifosplatform.portfolio.plan.data.PlanData;
import org.mifosplatform.portfolio.plan.data.ServiceData;
import org.mifosplatform.portfolio.savingsaccount.data.SavingAccountData;
import org.mifosplatform.portfolio.savingsaccount.data.SavingScheduleData;
import org.mifosplatform.portfolio.savingsaccountproduct.data.SavingProductData;
import org.mifosplatform.portfolio.savingsdepositaccount.data.DepositAccountData;
import org.mifosplatform.portfolio.savingsdepositproduct.data.DepositProductData;
import org.mifosplatform.portfolio.servicemaster.data.SericeMasterOptionsData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import antlr.collections.List;

import com.google.gson.Gson;

/**
 * Implementation of {@link PortfolioApiJsonSerializerService} that uses
 * google-gson to serialize Java object representation into JSON.
 */
@Service
public class GoogleGsonPortfolioApiJsonSerializerService implements PortfolioApiJsonSerializerService {

    private static final Set<String> SAVINGS_PRODUCT_DATA_PARAMETERS = new HashSet<String>(Arrays.asList("currencyOptions", "id",
            "createdOn", "lastModifedOn", "locale", "name", "description", "currencyCode", "digitsAfterDecimal", "interstRate",
            "minInterestRate", "maxInterestRate", "savingsDepositAmount", "savingProductType", "tenureType", "tenure", "frequency",
            "interestType", "interestCalculationMethod", "minimumBalanceForWithdrawal", "isPartialDepositAllowed", "isLockinPeriodAllowed",
            "lockinPeriod", "lockinPeriodType", "currencyOptions", "savingsProductTypeOptions", "tenureTypeOptions", "depositEvery",
            "savingFrequencyOptions", "savingsInterestTypeOptions", "lockinPeriodTypeOptions", "interestCalculationOptions"));

    private static final Set<String> SAVINGS_DEPOSIT_PRODUCT_DATA_PARAMETERS = new HashSet<String>(Arrays.asList("currencyOptions",
            "interestCompoundedEveryPeriodTypeOptions", "id", "externalId", "name", "description", "createdOn", "lastModifedOn",
            "currencyCode", "digitsAfterDecimal", "minimumBalance", "maximumBalance", "tenureInMonths", "maturityDefaultInterestRate",
            "maturityMinInterestRate", "maturityMaxInterestRate", "interestCompoundedEvery", "interestCompoundedEveryPeriodType",
            "renewalAllowed", "preClosureAllowed", "preClosureInterestRate", "interestCompoundingAllowed", "isLockinPeriodAllowed",
            "lockinPeriod", "lockinPeriodType", "currency"));

    private static final Set<String> SAVINGS_DEPOSIT_ACCOUNT_DATA_PARAMETERS = new HashSet<String>(Arrays.asList("productOptions",
            "interestCompoundedEveryPeriodTypeOptions", "id", "externalId", "clientId", "clientName", "productId", "productName", "status",
            "currency", "deposit", "maturityInterestRate", "tenureInMonths", "interestCompoundedEvery",
            "interestCompoundedEveryPeriodType", "renewalAllowed", "preClosureAllowed", "preClosureInterestRate", "withdrawnonDate",
            "rejectedonDate", "closedonDate", "transactions", "permissions", "isInterestWithdrawable", "interestPaid",
            "interestCompoundingAllowed", "availableInterestForWithdrawal", "availableWithdrawalAmount", "todaysDate",
            "isLockinPeriodAllowed", "lockinPeriod", "lockinPeriodType", "printFDdetailsLocation", "availableInterest",
            "interestPostedAmount", "lastInterestPostedDate", "nextInterestPostedDate", "fatherName", "address", "imageKey"));

    private static final Set<String> SAVINGS_ACCOUNTS_DATA_PARAMETERS = new HashSet<String>(Arrays.asList("id", "status", "externalId",
            "clientId", "clientName", "productId", "productName", "productType", "currencyData", "savingsDepostiAmountPerPeriod",
            "savingsFrequencyType", "totalDepositAmount", "reccuringInterestRate", "savingInterestRate", "interestType",
            "interestCalculationMethod", "tenure", "tenureType", "projectedCommencementDate", "actualCommencementDate", "maturesOnDate",
            "projectedInterestAccuredOnMaturity", "actualInterestAccured", "projectedMaturityAmount", "actualMaturityAmount","outstandingAmount",
            "preClosureAllowed", "preClosureInterestRate", "withdrawnonDate", "rejectedonDate", "closedonDate", "isLockinPeriodAllowed",
            "lockinPeriod", "lockinPeriodType", "productOptions", "currencyOptions", "savingsProductTypeOptions", "tenureTypeOptions",
            "savingFrequencyOptions", "savingsInterestTypeOptions", "lockinPeriodTypeOptions", "interestCalculationOptions","permissions","savingScheduleDatas",
            "dueAmount", "savingScheduleData", "transactions","interestPostEvery","interestPostFrequency"));

    private static final Set<String> SAVING_SCHEDULE_DATA_PARAMETERS = new HashSet<String>(Arrays.asList("periods", "cumulativeDepositDue"));

   
    private static final Set<String> SUBSCRIPTION_DATA_PARAMETERS = new HashSet<String>(Arrays.asList("id",
	           "subscription_period","subscription_type","units","allowedtypes","day_name","subscriptionTypeId"));
	    
	    private static final Set<String> SERVICE_DATA_PARAMETERS = new HashSet<String>(Arrays.asList("id","serviceCode","serviceDescription","serviceType","serviceData"));
	    
	    private static final Set<String> ORDER_DATA_PARAMETERS = new HashSet<String>(Arrays.asList("id","pdid","status","contractperiod",
	            "plan_code","units","service_code","allowedtypes","data","servicedata","billing_frequency", "start_date", "contract_period","startDate"));
	    
	    private static final Set<String> BILLINGCYCLE_DATA_PARAMETERS = new HashSet<String>(Arrays.asList("id",
	            "billing_code","description","frequency","every","day_name","day_num","allowedtypes"));
	    
	    private static final Set<String> PAYMODE_DATA_PARAMETERS = new HashSet<String>(Arrays.asList("id",
	            "code","descrition","type","categorytypes"));

	private static final Set<String> SERVICE_MASTER_DATA_PARAMETERS_TEMPLETE=new HashSet<String>(Arrays.asList("id","serviceType","serviceCode","serviceDescription"));


	    
	    private static final Set<String> PAYMENT_DATA_PARAMETERS = new HashSet<String>(Arrays.asList("id","paymentTypes"));


	
	    
	    private static final Set<String> PLAN_DATA_PARAMETERS = new HashSet<String>(Arrays.asList("id", "planCode", "plan_description", "startDate",
	            "endDate", "status", "service_code", "service_description", "charge_code", "charge_description","servicedata","contractPeriod",
	            "service_type", "charge_type", "allowedtypes","selectedservice","bill_rule","billiingcycle","servicedata","services","statusname","planstatus"));

	    private static final Set<String> PRICING_DATA_PARAMETERS = new HashSet<String>(Arrays.asList("plan_code","planId","serviceId","chargeId","price",
	    		"chargeVariantId","discountId","planCode","id", "serviceData","chargeData","data", "charge_code","charge_varaint","price"));
	 

    
    
    private final GoogleGsonSerializerHelper helper;

    @Autowired
    public GoogleGsonPortfolioApiJsonSerializerService(final GoogleGsonSerializerHelper helper) {
        this.helper = helper;
    }

    @Override
    public String serializeSavingProductDataToJson(final boolean prettyPrint, final Set<String> responseParameters,
            final Collection<SavingProductData> products) {
        final Gson gsonDeserializer = helper.createGsonBuilderWithParameterExclusionSerializationStrategy(SAVINGS_PRODUCT_DATA_PARAMETERS,
                prettyPrint, responseParameters);
        return helper.serializedJsonFrom(gsonDeserializer, products.toArray(new SavingProductData[products.size()]));
    }

    @Override
    public String serializeSavingProductDataToJson(final boolean prettyPrint, final Set<String> responseParameters,
            final SavingProductData savingProduct) {
        final Gson gsonDeserializer = helper.createGsonBuilderWithParameterExclusionSerializationStrategy(SAVINGS_PRODUCT_DATA_PARAMETERS,
                prettyPrint, responseParameters);
        return helper.serializedJsonFrom(gsonDeserializer, savingProduct);
    }

    @Override
    public String serializeDepositProductDataToJson(final boolean prettyPrint, final Set<String> responseParameters,
            final Collection<DepositProductData> products) {
        final Gson gsonDeserializer = helper.createGsonBuilderWithParameterExclusionSerializationStrategy(
                SAVINGS_DEPOSIT_PRODUCT_DATA_PARAMETERS, prettyPrint, responseParameters);
        return helper.serializedJsonFrom(gsonDeserializer, products.toArray(new DepositProductData[products.size()]));
    }

    @Override
    public String serializeDepositProductDataToJson(final boolean prettyPrint, final Set<String> responseParameters,
            final DepositProductData depositProduct) {
        final Gson gsonDeserializer = helper.createGsonBuilderWithParameterExclusionSerializationStrategy(
                SAVINGS_DEPOSIT_PRODUCT_DATA_PARAMETERS, prettyPrint, responseParameters);
        return helper.serializedJsonFrom(gsonDeserializer, depositProduct);
    }

    @Override
    public String serializeDepositAccountDataToJson(final boolean prettyPrint, final Set<String> responseParameters,
            final Collection<DepositAccountData> accounts) {
        final Gson gsonDeserializer = helper.createGsonBuilderWithParameterExclusionSerializationStrategy(
                SAVINGS_DEPOSIT_ACCOUNT_DATA_PARAMETERS, prettyPrint, responseParameters);
        return helper.serializedJsonFrom(gsonDeserializer, accounts.toArray(new DepositAccountData[accounts.size()]));
    }

    @Override
    public String serializeDepositAccountDataToJson(final boolean prettyPrint, final Set<String> responseParameters,
            final DepositAccountData account) {
        final Gson gsonDeserializer = helper.createGsonBuilderWithParameterExclusionSerializationStrategy(
                SAVINGS_DEPOSIT_ACCOUNT_DATA_PARAMETERS, prettyPrint, responseParameters);
        return helper.serializedJsonFrom(gsonDeserializer, account);
    }

    @Override
    public String serializeSavingAccountsDataToJson(boolean prettyPrint, Set<String> responseParameters, SavingAccountData account) {
        final Gson gsonDeserializer = helper.createGsonBuilderWithParameterExclusionSerializationStrategy(SAVINGS_ACCOUNTS_DATA_PARAMETERS,
                prettyPrint, responseParameters);
        return helper.serializedJsonFrom(gsonDeserializer, account);
    }

    @Override
    public String serializeSavingAccountsDataToJson(boolean prettyPrint, Set<String> responseParameters,
            Collection<SavingAccountData> accounts) {
        final Gson gsonDeserializer = helper.createGsonBuilderWithParameterExclusionSerializationStrategy(SAVINGS_ACCOUNTS_DATA_PARAMETERS,
                prettyPrint, responseParameters);
        return helper.serializedJsonFrom(gsonDeserializer, accounts.toArray(new SavingAccountData[accounts.size()]));
    }

    @Override
    public String serializeSavingScheduleDataToJson(boolean prettyPrint, Set<String> responseParameters,
            SavingScheduleData savingScheduleData) {
        final Gson gsonDeserializer = helper.createGsonBuilderWithParameterExclusionSerializationStrategy(SAVING_SCHEDULE_DATA_PARAMETERS,
                prettyPrint, responseParameters);
        return helper.serializedJsonFrom(gsonDeserializer, savingScheduleData);
    }

    @Override
    public String serializeBillingCycleToJson(final boolean prettyPrint, final Set<String> responseParameters,
            final BillingCycleData products) {
        final Gson gsonDeserializer = helper.createGsonBuilderWithParameterExclusionSerializationStrategy(
         BILLINGCYCLE_DATA_PARAMETERS, prettyPrint, responseParameters);
        return helper.serializedJsonFrom(gsonDeserializer, products);
    }



    @Override
public String serializePlanDataToJson(boolean prettyPrint,	Set<String> responseParameters, List products)
    {
     final Gson gsonDeserializer = helper.createGsonBuilderWithParameterExclusionSerializationStrategy(PLAN_DATA_PARAMETERS,
                 prettyPrint, responseParameters);
         return helper.serializedJsonFrom(gsonDeserializer, products);
}

    @Override
    public String serializePlanDataToJson(boolean prettyPrint, Set<String> responseParameters, PlanData account) {
        final Gson gsonDeserializer = helper.createGsonBuilderWithParameterExclusionSerializationStrategy(PLAN_DATA_PARAMETERS,
                prettyPrint, responseParameters);
        return helper.serializedJsonFrom(gsonDeserializer, account);
    }

	@Override
public String serializeServiceMasterDataToJson(boolean prettyPrint,Set<String> responseParameters, Collection<SericeMasterOptionsData> serviceMasterData)
{
final Gson gsonDeserializer = helper
.createGsonBuilderWithParameterExclusionSerializationStrategy(
SERVICE_MASTER_DATA_PARAMETERS_TEMPLETE, prettyPrint, responseParameters);
return helper.serializedJsonFrom(gsonDeserializer, serviceMasterData.toArray(new SericeMasterOptionsData[serviceMasterData.size()]));
}

	 @Override
	    public String serializeServiceToJson(final boolean prettyPrint, final Set<String> responseParameters,
	            final ServiceData products) {
	        final Gson gsonDeserializer = helper.createGsonBuilderWithParameterExclusionSerializationStrategy(
	         SERVICE_DATA_PARAMETERS, prettyPrint, responseParameters);
	        return helper.serializedJsonFrom(gsonDeserializer, products);
	    }
}