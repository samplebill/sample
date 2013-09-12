package org.mifosplatform.portfolio.pricing.api;

import java.util.Arrays;
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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.mifosplatform.infrastructure.core.api.ApiParameterHelper;
import org.mifosplatform.infrastructure.core.data.CommandProcessingResult;
import org.mifosplatform.infrastructure.core.data.EnumOptionData;
import org.mifosplatform.infrastructure.security.service.PlatformSecurityContext;
import org.mifosplatform.portfolio.billingproduct.PortfolioApiDataBillingConversionService;
import org.mifosplatform.portfolio.billingproduct.PortfolioApiJsonBillingSerializerService;
import org.mifosplatform.portfolio.charge.data.ChargesData;
import org.mifosplatform.portfolio.discountmaster.data.DiscountMasterData;
import org.mifosplatform.portfolio.plan.data.ServiceData;
import org.mifosplatform.portfolio.pricing.commands.PricingCommand;
import org.mifosplatform.portfolio.pricing.data.PricingData;
import org.mifosplatform.portfolio.pricing.service.PriceReadPlatformService;
import org.mifosplatform.portfolio.pricing.service.PriceWritePlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Path("/prices")
@Component
@Scope("singleton")
public class PricingApiResource {

	private static final String planCode = null;

	@Autowired
	private PortfolioApiDataBillingConversionService apiDataConversionService;

	@Autowired
	private PortfolioApiJsonBillingSerializerService apiJsonSerializerService;

	@Autowired
	private PriceWritePlatformService priceWritePlatformService;

	@Autowired
	private PriceReadPlatformService priceReadPlatformService;

	@Autowired
	private PlatformSecurityContext context;

	private final String entityType = "PRICE";

	@POST
	@Path("{planId}")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response createSubscription(@PathParam("planId") final Long planId,
			final String jsonRequestBody) {

		PricingCommand command = this.apiDataConversionService
				.convertJsonToPricingCommand(null, jsonRequestBody);
		List<ServiceData> serviceData = this.priceReadPlatformService
				.retrieveServiceCodeDetails(planId);
		CommandProcessingResult userId = this.priceWritePlatformService
				.createPricing(command, serviceData, planId);
		return Response.ok().entity(userId).build();
	}

	@GET
	@Path("template")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public String retrievePricing(@QueryParam("plan_code") final Long planCode,
			@Context final UriInfo uriInfo) {

		// context.authenticatedUser().validateHasReadPermission("CLIENT");

		final Set<String> responseParameters = ApiParameterHelper
				.extractFieldsForResponseIfProvided(uriInfo
						.getQueryParameters());
		final boolean prettyPrint = ApiParameterHelper.prettyPrint(uriInfo
				.getQueryParameters());

		List<ServiceData> serviceData = this.priceReadPlatformService
				.retrievePrcingDetails(planCode);
		List<ChargesData> chargeDatas = this.priceReadPlatformService
				.retrieveChargeCode();
		List<EnumOptionData> datas = this.priceReadPlatformService
				.retrieveChargeVariantData();
		List<DiscountMasterData> discountdata = this.priceReadPlatformService
				.retrieveDiscountDetails();

		PricingData data = new PricingData(serviceData, chargeDatas, datas,
				discountdata, serviceData.get(0).getCode(), planCode,null);

		return this.apiJsonSerializerService.serializePricingDataToJson(
				prettyPrint, responseParameters, data);
	}

	@GET
	@Path("{planCode}")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public String retrievePrice(@PathParam("planCode") final String planCode,
			@Context final UriInfo uriInfo) {

		// context.authenticatedUser().validateHasReadPermission("CLIENT");

		final Set<String> responseParameters = ApiParameterHelper
				.extractFieldsForResponseIfProvided(uriInfo
						.getQueryParameters());
		final boolean prettyPrint = ApiParameterHelper.prettyPrint(uriInfo
				.getQueryParameters());

		List<ServiceData> serviceData = this.priceReadPlatformService
				.retrievePriceDetails(planCode);

		PricingData data = new PricingData(serviceData);

		return this.apiJsonSerializerService.serializePricingDataToJson(
				prettyPrint, responseParameters, data);
	}

	@GET
	@Path("{priceId}/update")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public String retrieveIndividualPrice(
			@PathParam("priceId") final String priceId,
			@Context final UriInfo uriInfo) {

		// context.authenticatedUser().validateHasReadPermission("CLIENT");

		Set<String> typicalResponseParameters = new HashSet<String>(
				Arrays.asList("plan_code", "planId", "serviceId", "chargeId",
						"price", "chargeVariantId", "discountId", "planCode",
						"id", "serviceData", "chargeData", "data",
						"charge_code", "charge_varaint", "price"));

		Set<String> responseParameters = ApiParameterHelper
				.extractFieldsForResponseIfProvided(uriInfo
						.getQueryParameters());
		if (responseParameters.isEmpty()) {
			responseParameters.addAll(typicalResponseParameters);
		}
		boolean prettyPrint = ApiParameterHelper.prettyPrint(uriInfo
				.getQueryParameters());

		boolean template = ApiParameterHelper.template(uriInfo
				.getQueryParameters());

		PricingData singlePriceData = this.priceReadPlatformService
				.retrieveSinglePriceDetails(priceId);

		List<ServiceData> serviceData = this.priceReadPlatformService
				.retrievePrcingDetails(singlePriceData.getPlanId());
		List<ChargesData> chargeDatas = this.priceReadPlatformService
				.retrieveChargeCode();
		List<EnumOptionData> datas = this.priceReadPlatformService
				.retrieveChargeVariantData();
		List<DiscountMasterData> discountdata = this.priceReadPlatformService
				.retrieveDiscountDetails();

		singlePriceData = new PricingData(serviceData, chargeDatas, datas,
				discountdata, serviceData.get(0).getCode(),
				singlePriceData.getPlanId(),singlePriceData);

		// PricingData data=new PricingData(serviceData);

		return this.apiJsonSerializerService.serializePricingDataToJson(
				prettyPrint, responseParameters, singlePriceData);
	}

	@PUT
	@Path("{priceId}/update")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response updatePrice(@PathParam("priceId") final Long priceId,
			final String jsonRequestBody) {

		PricingCommand command = this.apiDataConversionService
				.convertJsonToPricingCommand(null, jsonRequestBody);

		CommandProcessingResult entityIdentifier = this.priceWritePlatformService
				.updatePrice(command, priceId);
		return Response.ok().entity(entityIdentifier).build();
	}

	@DELETE
	@Path("{priceId}")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response deletePrice(@PathParam("priceId") final Long priceId) {

		this.priceWritePlatformService.deletePrice(priceId);

		return Response.ok(1).build();
	}
}
