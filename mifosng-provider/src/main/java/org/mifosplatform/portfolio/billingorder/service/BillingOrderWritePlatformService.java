package org.mifosplatform.portfolio.billingorder.service;

import java.util.List;

import org.joda.time.LocalDate;
import org.mifosplatform.infrastructure.core.data.CommandProcessingResult;
import org.mifosplatform.portfolio.adjustment.domain.ClientBalance;
import org.mifosplatform.portfolio.billingorder.commands.BillingOrderCommand;
import org.mifosplatform.portfolio.billingorder.commands.InvoiceCommand;
import org.mifosplatform.portfolio.billingorder.commands.InvoiceTaxCommand;
import org.mifosplatform.portfolio.billingorder.domain.BillingOrder;
import org.mifosplatform.portfolio.billingorder.domain.Invoice;
import org.mifosplatform.portfolio.billingorder.domain.InvoiceTax;
import org.mifosplatform.portfolio.clientbalance.data.ClientBalanceData;

public interface BillingOrderWritePlatformService {

	List<BillingOrder> createBillingProduct(List<BillingOrderCommand> billingOrderCommands);
	CommandProcessingResult updateBillingOrder(List<BillingOrderCommand> billingOrderCommands);
	CommandProcessingResult updateOrderPrice(List<BillingOrderCommand> billingOrderCommands);
	List<InvoiceTax> createInvoiceTax(List<InvoiceTaxCommand> command);
	Invoice createInvoice(InvoiceCommand command,List<ClientBalanceData> clientBalanceDatas);
	public void updateInvoiceTax(Invoice invoice,List<BillingOrderCommand> billingOrderCommands,List<BillingOrder> billingOrders);
	void updateInvoiceCharge(Invoice invoice,List<BillingOrder>  billingOrder);

}
