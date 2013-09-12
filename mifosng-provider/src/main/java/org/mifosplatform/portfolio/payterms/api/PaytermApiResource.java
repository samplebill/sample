package org.mifosplatform.portfolio.payterms.api;

import java.util.List;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.mifosplatform.infrastructure.configuration.data.PeriodData;
import org.mifosplatform.infrastructure.configuration.service.PeriodReadPlatformService;
import org.mifosplatform.infrastructure.core.api.ApiParameterHelper;
import org.mifosplatform.infrastructure.core.data.CommandProcessingResult;
import org.mifosplatform.infrastructure.security.service.PlatformSecurityContext;
import org.mifosplatform.portfolio.billingproduct.PortfolioApiDataBillingConversionService;
import org.mifosplatform.portfolio.billingproduct.PortfolioApiJsonBillingSerializerService;
import org.mifosplatform.portfolio.payterms.commands.PaytermsCommand;
import org.mifosplatform.portfolio.payterms.service.PaytermsWritePlatformService;
import org.mifosplatform.portfolio.subscription.data.SubscriptionData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Path("/paytypes")
@Component
@Scope("singleton")
public class PaytermApiResource {

	@Autowired
	private PortfolioApiDataBillingConversionService apiDataConversionService;

	@Autowired
	private PortfolioApiJsonBillingSerializerService apiJsonSerializerService;

	@Autowired
	private PaytermsWritePlatformService paytermsWritePlatformService;

	@Autowired
	private  PeriodReadPlatformService periodReadPlatformService;

	@Autowired
	private PlatformSecurityContext context;

	private final String entityType = "ORDER";

	@POST
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response createPlan(final String jsonRequestBody) {

		PaytermsCommand command = this.apiDataConversionService.convertJsonToPaytermsCommand(null, jsonRequestBody);

		CommandProcessingResult userId = this.paytermsWritePlatformService.createPayterm(command);
		return Response.ok().entity(userId).build();
	}
	 @GET
	    @Path("template")
	    @Consumes({ MediaType.APPLICATION_JSON })
	    @Produces({ MediaType.APPLICATION_JSON })
	    public String retrieveDetailsForNewLoanApplicationStepOne( @Context final UriInfo uriInfo) {


	        final Set<String> responseParameters = ApiParameterHelper.extractFieldsForResponseIfProvided(uriInfo.getQueryParameters());
	        final boolean prettyPrint = ApiParameterHelper.prettyPrint(uriInfo.getQueryParameters());
       List<PeriodData> allowedtypes = this.periodReadPlatformService.retrieveAllPlatformPeriod();
       SubscriptionData product = new SubscriptionData(allowedtypes);

		return this.apiJsonSerializerService.serializeSubscriptionToJson(prettyPrint, responseParameters, product);


	 }


}
