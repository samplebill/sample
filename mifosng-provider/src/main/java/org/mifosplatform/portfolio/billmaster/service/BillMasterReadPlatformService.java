package org.mifosplatform.portfolio.billmaster.service;

import java.math.BigDecimal;
import java.util.List;

import org.mifosplatform.portfolio.adjustment.domain.ClientBalance;
import org.mifosplatform.portfolio.billingorder.data.BillDetailsData;
import org.mifosplatform.portfolio.financialtransaction.data.FinancialTransactionsData;

public interface BillMasterReadPlatformService {

	List<FinancialTransactionsData> retrieveFinancialData(Long clientId);
	List<FinancialTransactionsData> retrieveInvoiceFinancialData(Long clientId);
	BillDetailsData retrievebillDetails(Long clientId);
	List<FinancialTransactionsData> getFinancialTransactionData(Long id);
	List<FinancialTransactionsData> retrieveStatments(Long clientId);
	BigDecimal retrieveClientBalance(Long clientId);

}
