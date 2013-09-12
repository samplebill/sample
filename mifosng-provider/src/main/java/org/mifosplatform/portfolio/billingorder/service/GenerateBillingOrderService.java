package org.mifosplatform.portfolio.billingorder.service;

import java.math.BigDecimal;
import java.util.List;

import org.mifosplatform.portfolio.billingorder.commands.BillingOrderCommand;
import org.mifosplatform.portfolio.billingorder.commands.InvoiceCommand;
import org.mifosplatform.portfolio.billingorder.commands.InvoiceTaxCommand;
import org.mifosplatform.portfolio.billingorder.data.BillingOrderData;
import org.mifosplatform.portfolio.billingorder.domain.BillingOrder;
import org.mifosplatform.portfolio.billingorder.domain.InvoiceTax;
import org.mifosplatform.portfolio.taxmaster.data.TaxMappingRateData;

public interface GenerateBillingOrderService {

	public List<BillingOrderCommand> generatebillingOrder(List<BillingOrderData> products);

	public List<InvoiceTaxCommand> generateInvoiceTax(List<TaxMappingRateData> taxMappingRateDatas,BigDecimal price,Long clientId);

	public InvoiceCommand generateInvoice(List<BillingOrderCommand> billingOrderCommands);

	
}
