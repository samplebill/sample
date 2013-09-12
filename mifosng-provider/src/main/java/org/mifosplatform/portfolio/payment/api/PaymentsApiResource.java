package org.mifosplatform.portfolio.payment.api;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.mifosplatform.infrastructure.core.api.ApiParameterHelper;
import org.mifosplatform.infrastructure.core.data.CommandProcessingResult;
import org.mifosplatform.infrastructure.security.service.PlatformSecurityContext;
import org.mifosplatform.portfolio.adjustment.service.ClientBalanceReadPlatformService;
import org.mifosplatform.portfolio.billingproduct.PortfolioApiDataBillingConversionService;
import org.mifosplatform.portfolio.billingproduct.PortfolioApiJsonBillingSerializerService;
import org.mifosplatform.portfolio.clientbalance.data.ClientBalanceData;
import org.mifosplatform.portfolio.loanaccount.data.LoanBasicDetailsData;
import org.mifosplatform.portfolio.loanaccount.service.LoanReadPlatformService;
import org.mifosplatform.portfolio.payment.command.Paymentcommand;
import org.mifosplatform.portfolio.payment.service.PaymentWritePlatformService;
import org.mifosplatform.portfolio.paymodes.data.PaymodeData;
import org.mifosplatform.portfolio.paymodes.service.PaymodeReadPlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Path("/payments")
@Component
@Scope("singleton")
public class PaymentsApiResource {



	@Autowired
	private PaymentWritePlatformService paymentWritePlatformService;

	@Autowired
	private PortfolioApiDataBillingConversionService apiDataConversionService;

	@Autowired
	private PaymodeReadPlatformService paymodeReadPlatformService;

    @Autowired
    private LoanReadPlatformService loanReadPlatformService;

    @Autowired
    private ClientBalanceReadPlatformService clientBalanceReadPlatformService;

	@Autowired
	private PortfolioApiJsonBillingSerializerService apiJsonSerializerService;

	private final String entityType = "PAYMENTS";
	@Autowired
	private PlatformSecurityContext context;
	private static final Set<String> typicalResponseParameters = new HashSet<String>(
			Arrays.asList("payment_id","clientId","payment_date","payment_code","amount_paid","statment_id","externalId",
					"cancellation_date","cancellation_remarks","Remarks"));

	@POST
	 @Path("{clientId}")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response createDepositAccount(@PathParam("clientId") final Long clientId,final String jsonRequestBody){

		final Paymentcommand command = this.apiDataConversionService.convertJsonToPaymentCommand(null, jsonRequestBody);
		List<ClientBalanceData> clientBalancedatas = clientBalanceReadPlatformService.retrieveAllClientBalances(clientId);
		Long clientBalanceId = null;
		if(clientBalancedatas.size()==1){
			clientBalanceId = clientBalancedatas.get(0).getId();
		}
		 CommandProcessingResult entityIdentifier = this.paymentWritePlatformService.createPayment(command,clientId,clientBalanceId);
		return Response.ok().entity(entityIdentifier).build();
	}

	 @GET
	    @Path("template")
	    @Consumes({ MediaType.APPLICATION_JSON })
	    @Produces({ MediaType.APPLICATION_JSON })
	    public String retrieveDetailsForNewLoanApplicationStepOne(@QueryParam("clientId") final Long clientId,
			 @QueryParam("productId") final Long productId,  @Context final UriInfo uriInfo) {


	        final Set<String> responseParameters = ApiParameterHelper.extractFieldsForResponseIfProvided(uriInfo.getQueryParameters());
	        final boolean prettyPrint = ApiParameterHelper.prettyPrint(uriInfo.getQueryParameters());
	        LoanBasicDetailsData loanBasicDetails;
		Collection<PaymodeData> data = this.paymodeReadPlatformService.retrieveAllPaymodes();
	            loanBasicDetails = this.loanReadPlatformService.retrieveClientAndProductDetails(clientId, productId);

	           PaymodeData datas= new PaymodeData(data);

	            return this.apiJsonSerializerService.serializePaymodeToJson(prettyPrint, responseParameters, loanBasicDetails,datas);



	 }
}
