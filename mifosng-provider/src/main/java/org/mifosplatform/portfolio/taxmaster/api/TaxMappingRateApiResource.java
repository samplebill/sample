package org.mifosplatform.portfolio.taxmaster.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
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
import org.mifosplatform.portfolio.billingproduct.PortfolioApiDataBillingConversionService;
import org.mifosplatform.portfolio.billingproduct.PortfolioApiJsonBillingSerializerService;
import org.mifosplatform.portfolio.charge.data.ChargeCodeData;
import org.mifosplatform.portfolio.discountmaster.commands.DiscountValues;
import org.mifosplatform.portfolio.taxmaster.commands.TaxMappingRateCommand;
import org.mifosplatform.portfolio.taxmaster.data.TaxMappingRateOptionsData;
import org.mifosplatform.portfolio.taxmaster.data.TaxMasterData;
import org.mifosplatform.portfolio.taxmaster.service.TaxMappingRateReadPlatformService;
import org.mifosplatform.portfolio.taxmaster.service.TaxMappingRateWritePlatformService;
import org.mifosplatform.portfolio.taxmaster.service.TaxMasterWritePlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Path("/taxmappingrates")
@Component
@Scope("singleton")
public class TaxMappingRateApiResource {
	@Autowired
	private TaxMasterWritePlatformService taxMasterWritePlatformService;
	@Autowired
	private PortfolioApiDataBillingConversionService apiDataConversionService;
	@Autowired
	private PortfolioApiJsonBillingSerializerService apiJsonSerializerService;
	@Autowired
	private TaxMappingRateWritePlatformService taxMappingRateWritePlatformService;
	@Autowired
	private  TaxMappingRateReadPlatformService taxMappingRateReadPlatformService;


	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response createTaxMappingRate(final String jsonRequestBody) {
		final TaxMappingRateCommand command = this.apiDataConversionService.convertJsonToTaxMappingRateCommand(null, jsonRequestBody);
		Long id=taxMappingRateWritePlatformService.createtaxMasterMapping(command);
		return Response.ok().entity(id).build();
	}


@GET
@Path("template")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public String retrieveTempleteInfo(@Context final UriInfo uriInfo) {


	Set<String> typicalResponseParameters = new HashSet<String>(
			Arrays.asList("id","taxCode","taxMasterOptions"));

	Set<String> responseParameters = ApiParameterHelper.extractFieldsForResponseIfProvided(uriInfo.getQueryParameters());
	if (responseParameters.isEmpty()) {
		responseParameters.addAll(typicalResponseParameters);
	}

	final boolean prettyPrint = ApiParameterHelper.prettyPrint(uriInfo.getQueryParameters());
   //final Collection<TaxMasterData> datas = this.taxMasterReadPlatformService.retrieveAllTaxMasterData();

	final Collection<DiscountValues> datass=new ArrayList<DiscountValues>();
	DiscountValues  d1=new DiscountValues("Percentage ");
	DiscountValues  d2=new DiscountValues("Flat ");
	datass.add(d1);
	datass.add(d2);
    final Collection<TaxMasterData> taxMasterData=taxMappingRateReadPlatformService.retrieveTaxMappingRateforTemplate();
    final Collection<ChargeCodeData> chargeCodeData=taxMappingRateReadPlatformService.retrieveChargeCodeForTemplate();
    TaxMappingRateOptionsData data=new TaxMappingRateOptionsData(chargeCodeData,taxMasterData,datass);
    Collection<TaxMappingRateOptionsData> optionsData=new ArrayList<TaxMappingRateOptionsData>();
    optionsData.add(data);
	return this.apiJsonSerializerService.serializeTaxMappingRateTemplateDataToJson(prettyPrint, responseParameters, optionsData);
}
}