package org.mifosplatform.portfolio.savingsaccount;

import java.util.Collection;
import java.util.Set;

import org.mifosplatform.portfolio.billingcycle.data.BillingCycleData;
import org.mifosplatform.portfolio.plan.data.PlanData;
import org.mifosplatform.portfolio.plan.data.ServiceData;
import org.mifosplatform.portfolio.savingsaccount.data.SavingAccountData;
import org.mifosplatform.portfolio.savingsaccount.data.SavingScheduleData;
import org.mifosplatform.portfolio.savingsaccountproduct.data.SavingProductData;
import org.mifosplatform.portfolio.savingsdepositaccount.data.DepositAccountData;
import org.mifosplatform.portfolio.savingsdepositproduct.data.DepositProductData;
import org.mifosplatform.portfolio.servicemaster.data.SericeMasterOptionsData;

import antlr.collections.List;

public interface PortfolioApiJsonSerializerService {

    String serializeSavingProductDataToJson(boolean prettyPrint, Set<String> responseParameters, Collection<SavingProductData> products);

    String serializeSavingProductDataToJson(boolean prettyPrint, Set<String> responseParameters, SavingProductData savingProduct);

    String serializeDepositProductDataToJson(boolean prettyPrint, Set<String> responseParameters, Collection<DepositProductData> products);

    String serializeDepositProductDataToJson(boolean prettyPrint, Set<String> responseParameters, DepositProductData depositProduct);

    String serializeDepositAccountDataToJson(boolean prettyPrint, Set<String> responseParameters, Collection<DepositAccountData> accounts);

    String serializeDepositAccountDataToJson(boolean prettyPrint, Set<String> responseParameters, DepositAccountData account);

    String serializeSavingAccountsDataToJson(boolean prettyPrint, Set<String> responseParameters, SavingAccountData account);

    String serializeSavingAccountsDataToJson(boolean prettyPrint, Set<String> responseParameters, Collection<SavingAccountData> accounts);

    String serializeSavingScheduleDataToJson(boolean prettyPrint, Set<String> responseParameters, SavingScheduleData savingScheduleData);

	//String serializeBillingCycleToJson(boolean prettyPrint,	Set<String> responseParameters, BillingCycleData products);

	
	String serializePlanDataToJson(boolean prettyPrint,Set<String> responseParameters,List products);

	String serializePlanDataToJson(boolean prettyPrint,Set<String> responseParameters, PlanData account);

	String serializeServiceMasterDataToJson(boolean prettyPrint,
			Set<String> responseParameters,
			Collection<SericeMasterOptionsData> serviceMasterData);

	String serializeServiceToJson(boolean prettyPrint,	Set<String> responseParameters, ServiceData products);

	String serializeBillingCycleToJson(boolean prettyPrint,	Set<String> responseParameters, BillingCycleData products);

}