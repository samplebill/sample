package org.mifosplatform.portfolio.subscription.api;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
import org.mifosplatform.portfolio.billingproduct.PortfolioApiJsonBillingSerializerService;
import org.mifosplatform.portfolio.savingsaccount.PortfolioApiDataConversionService;
import org.mifosplatform.portfolio.subscription.commands.SubscriptionCommand;
import org.mifosplatform.portfolio.subscription.data.SubscriptionData;
import org.mifosplatform.portfolio.subscription.domain.SubscriptionReadPlatformService;
import org.mifosplatform.portfolio.subscription.service.SubcriptionWritePlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Path("/subscriptions")
@Component
@Scope("singleton")
public class SubscriptionApiResource {

	@Autowired
	private  PeriodReadPlatformService periodReadPlatformService;

	@Autowired
	private PortfolioApiDataConversionService apiDataConversionService;

	@Autowired
	private PortfolioApiJsonBillingSerializerService apiJsonSerializerService;

	@Autowired
	private PlatformSecurityContext context;

	@Autowired
	private SubcriptionWritePlatformService  subcriptionWritePlatformService;

	@Autowired
	private SubscriptionReadPlatformService subcriptionReadPlatformService;

	private final String entityType = "SUBSCRIPTION";

	@POST
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response createSubscription(final String jsonRequestBody) {

		SubscriptionCommand command = this.apiDataConversionService.convertJsonToSubscriptionCommand(null, jsonRequestBody);

		CommandProcessingResult userId = this.subcriptionWritePlatformService.createSubscription(command);
		return Response.ok().entity(userId).build();
	}

	@GET
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public String retrieveAllSubscription(@Context final UriInfo uriInfo) {

	context.authenticatedUser().validateHasReadPermission(entityType);

		Set<String> typicalResponseParameters = new HashSet<String>(
				Arrays.asList("id","subscription_period","subscription_type","units","subscriptionTypeId","day_name"));

		Set<String> responseParameters = ApiParameterHelper.extractFieldsForResponseIfProvided(uriInfo.getQueryParameters());
		if (responseParameters.isEmpty()) {
			responseParameters.addAll(typicalResponseParameters);
		}
		boolean prettyPrint = ApiParameterHelper.prettyPrint(uriInfo.getQueryParameters());

		Collection<SubscriptionData> products=this.subcriptionReadPlatformService.retrieveAllSubscription();
		return this.apiJsonSerializerService.serializeSubscriptionToJson(prettyPrint, responseParameters, products);
	}

	@GET
	@Path("{SubscriptionId}")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public String retrieveDepositProductDetails(@PathParam("SubscriptionId") final Long SubscriptionId, @Context final UriInfo uriInfo) {

	context.authenticatedUser().validateHasReadPermission(entityType);

		Set<String> typicalResponseParameters = new HashSet<String>(
				Arrays.asList("id","subscription_period","subscription_type","units","day_name","subscriptionTypeId" ));

		Set<String> responseParameters = ApiParameterHelper.extractFieldsForResponseIfProvided(uriInfo.getQueryParameters());
		if (responseParameters.isEmpty()) {
			responseParameters.addAll(typicalResponseParameters);
		}
		boolean prettyPrint = ApiParameterHelper.prettyPrint(uriInfo.getQueryParameters());

		SubscriptionData productData = this.subcriptionReadPlatformService.retrieveSubscriptionData(SubscriptionId);

		boolean template = ApiParameterHelper.template(uriInfo.getQueryParameters());
		if (template) {
			responseParameters.addAll(Arrays.asList("allowedtypes"));
			List<PeriodData> allowedtypes = this.periodReadPlatformService.retrieveAllPlatformPeriod();
			productData = new SubscriptionData(allowedtypes, productData);
		}

		return this.apiJsonSerializerService.serializeSubscriptionToJson(prettyPrint, responseParameters, productData);
	}

	@GET
	@Path("template")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public String retrieveNewDepositProductDetails(@Context final UriInfo uriInfo) {

	context.authenticatedUser().validateHasReadPermission(entityType);

		Set<String> typicalResponseParameters = new HashSet<String>(
				Arrays.asList("id","subscription_period","subscription_type","day_name"));

		Set<String> responseParameters = ApiParameterHelper.extractFieldsForResponseIfProvided(uriInfo.getQueryParameters());
		if (responseParameters.isEmpty()) {
			responseParameters.addAll(typicalResponseParameters);
		}
		boolean prettyPrint = ApiParameterHelper.prettyPrint(uriInfo.getQueryParameters());

		responseParameters.addAll(Arrays.asList("allowedtypes"));
		List<PeriodData> allowedtypes = this.periodReadPlatformService.retrieveAllPlatformPeriod();
         SubscriptionData product = new SubscriptionData(allowedtypes);

		return this.apiJsonSerializerService.serializeSubscriptionToJson(prettyPrint, responseParameters, product);
	}


	@PUT
	@Path("{SubscriptionId}")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response updateSubscription(@PathParam("SubscriptionId") final Long SubscriptionId, final String jsonRequestBody){

		final SubscriptionCommand command=this.apiDataConversionService.convertJsonToSubscriptionCommand(SubscriptionId, jsonRequestBody);
		CommandProcessingResult entityIdentifier=this.subcriptionWritePlatformService.updateSubscriptionProduct(command);
		return Response.ok().entity(entityIdentifier).build();
	}

	@DELETE
	@Path("{SubscriptionId}")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response deleteSubscription(@PathParam("SubscriptionId") final Long SubscriptionId) {

		this.subcriptionWritePlatformService.deleteSubscription(SubscriptionId);

		return Response.ok(SubscriptionId).build();
	}




}
