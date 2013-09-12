package org.mifosplatform.portfolio.billingorder.api;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.joda.time.LocalDate;
import org.mifosplatform.infrastructure.core.data.CommandProcessingResult;
import org.mifosplatform.portfolio.adjustment.service.AdjustmentReadPlatformService;
import org.mifosplatform.portfolio.billingorder.commands.BillingOrderCommand;
import org.mifosplatform.portfolio.billingorder.commands.InvoiceCommand;
import org.mifosplatform.portfolio.billingorder.data.BillingOrderData;
import org.mifosplatform.portfolio.billingorder.domain.BillingOrder;
import org.mifosplatform.portfolio.billingorder.domain.Invoice;
import org.mifosplatform.portfolio.billingorder.exceptions.BillingOrderNoRecordsFoundException;
import org.mifosplatform.portfolio.billingorder.service.BillingOrderReadPlatformService;
import org.mifosplatform.portfolio.billingorder.service.BillingOrderWritePlatformService;
import org.mifosplatform.portfolio.billingorder.service.GenerateBillingOrderService;
import org.mifosplatform.portfolio.billingproduct.PortfolioApiDataBillingConversionService;
import org.mifosplatform.portfolio.billingproduct.PortfolioApiJsonBillingSerializerService;
import org.mifosplatform.portfolio.clientbalance.data.ClientBalanceData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Path("/billingorder")
@Component
@Scope("singleton")
public class BillingOrderApiResourse {


	@Autowired
	private BillingOrderReadPlatformService billingOrderReadPlatformService;

	@Autowired
	private GenerateBillingOrderService generateBillingOrderService;

	@Autowired
	private PortfolioApiJsonBillingSerializerService apiJsonSerializerService;

	@Autowired
	private BillingOrderWritePlatformService billingOrderWritePlatformService;

    @Autowired
    private PortfolioApiDataBillingConversionService apiDataConversionService;

	@Autowired
	private AdjustmentReadPlatformService adjustmentReadPlatformService;

	 @POST
	 @Path("{clientId}")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response retrieveBillingProducts(@PathParam("clientId") final Long clientId,final String jsonRequestBody) {

		 	LocalDate  processDate = this.apiDataConversionService.convertJsonToBillingProductCommand(null, jsonRequestBody);

		 	List<Long> orderIds = billingOrderReadPlatformService.retrieveOrderIds(clientId,processDate);
		    if(orderIds.size()==0){
		    	throw new BillingOrderNoRecordsFoundException();
		    }
		 	for(Long orderId : orderIds){
		 	
			// Charges
		 	List<BillingOrderData> products = this.billingOrderReadPlatformService.retrieveBillingOrderData(clientId,processDate,orderId);
			
			List<BillingOrderCommand> billingOrderCommands = this.generateBillingOrderService.generatebillingOrder(products);
			List<BillingOrder> listOfBillingOrders = billingOrderWritePlatformService.createBillingProduct(billingOrderCommands);
			
			// Invoice
			InvoiceCommand invoiceCommand = this.generateBillingOrderService.generateInvoice(billingOrderCommands);
			List<ClientBalanceData> clientBalancesDatas =  adjustmentReadPlatformService.retrieveAllAdjustments(clientId);
			Invoice invoice = billingOrderWritePlatformService.createInvoice(invoiceCommand,clientBalancesDatas);

			// Update invoice-tax
			billingOrderWritePlatformService.updateInvoiceTax(invoice,billingOrderCommands,listOfBillingOrders);
			
			// Update charge
			 billingOrderWritePlatformService.updateInvoiceCharge(invoice, listOfBillingOrders);
			
			// Update orders
			billingOrderWritePlatformService.updateBillingOrder(billingOrderCommands);

			// Update order-price
			CommandProcessingResult entityIdentifier = billingOrderWritePlatformService.updateOrderPrice(billingOrderCommands);

		}
		return Response.ok().entity(1l).build();
	}

}
