package org.mifosplatform.portfolio.billingcycle.api;

import java.util.Arrays;
import java.util.HashSet;
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

import org.mifosplatform.infrastructure.core.api.ApiParameterHelper;
import org.mifosplatform.infrastructure.core.data.CommandProcessingResult;
import org.mifosplatform.infrastructure.security.service.PlatformSecurityContext;
import org.mifosplatform.portfolio.billingcycle.command.BillingCycleCommand;
import org.mifosplatform.portfolio.billingcycle.data.BillingCycleData;
import org.mifosplatform.portfolio.billingcycle.service.BillingCycleReadPlatformService;
import org.mifosplatform.portfolio.billingcycle.service.BillingCycleWritePlatformService;
import org.mifosplatform.portfolio.billingproduct.PortfolioApiDataBillingConversionService;
import org.mifosplatform.portfolio.billingproduct.PortfolioApiJsonBillingSerializerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Path("/billingcycles")
@Component
@Scope("singleton")
public class BillingCycleApiResource {

	@Autowired
	private PortfolioApiDataBillingConversionService apiDataConversionService;

	@Autowired
	private PortfolioApiJsonBillingSerializerService apiJsonSerializerService;

	@Autowired
	private PlatformSecurityContext context;

	@Autowired
	private BillingCycleWritePlatformService billingCycleWritePlatformService;

	@Autowired
	private BillingCycleReadPlatformService billingCycleReadPlatformService;



	private final String entityType = "BILLINGCYCLE";

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response createSubscription(final String jsonRequestBody) {

		BillingCycleCommand command = this.apiDataConversionService
				.convertJsonToBillingCycleCommand(null, jsonRequestBody);

		CommandProcessingResult userId = this.billingCycleWritePlatformService
				.createBillingCycle(command);
		return Response.ok().entity(userId).build();
	}
	@GET
	@Path("template")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public String retrieveNewDepositProductDetails(@Context final UriInfo uriInfo) {

	context.authenticatedUser().validateHasReadPermission(entityType);

		Set<String> typicalResponseParameters = new HashSet<String>(
				Arrays.asList("id","day_num","day_name"));

		Set<String> responseParameters = ApiParameterHelper.extractFieldsForResponseIfProvided(uriInfo.getQueryParameters());
		if (responseParameters.isEmpty()) {
			responseParameters.addAll(typicalResponseParameters);
		}
		boolean prettyPrint = ApiParameterHelper.prettyPrint(uriInfo.getQueryParameters());

		responseParameters.addAll(Arrays.asList("allowedtypes"));
		BillingCycleData allowedtypes = this.billingCycleReadPlatformService.retrieveNewBillingCycleData();


		return this.apiJsonSerializerService.serializeBillingCycleToJson(prettyPrint, responseParameters, allowedtypes);
	}


}
