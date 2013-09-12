package org.mifosplatform.portfolio.taxmaster.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
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

import org.mifosplatform.infrastructure.core.api.ApiParameterHelper;
import org.mifosplatform.infrastructure.core.data.EnumOptionData;
import org.mifosplatform.infrastructure.security.service.PlatformSecurityContext;
import org.mifosplatform.portfolio.billingproduct.PortfolioApiDataBillingConversionService;
import org.mifosplatform.portfolio.billingproduct.PortfolioApiJsonBillingSerializerService;
import org.mifosplatform.portfolio.subscription.data.SubscriptionData;
import org.mifosplatform.portfolio.taxmaster.commands.TaxMasterCommand;
import org.mifosplatform.portfolio.taxmaster.data.TaxMasterDataOptions;
import org.mifosplatform.portfolio.taxmaster.service.TaxMasterReadPlatformService;
import org.mifosplatform.portfolio.taxmaster.service.TaxMasterWritePlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Path("/taxmasters")
@Component
@Scope("singleton")
public class TaxMasterApiResource {

	@Autowired
	private TaxMasterWritePlatformService taxMasterWritePlatformService;
	@Autowired
	private PortfolioApiDataBillingConversionService apiDataConversionService;
	@Autowired
	private PortfolioApiJsonBillingSerializerService apiJsonSerializerService;
	@Autowired
	private TaxMasterReadPlatformService taxMasterReadPlatformService;

	@Autowired
	private PlatformSecurityContext context;

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response createNewTax(final String jsonRequestBody) {
		final TaxMasterCommand command = this.apiDataConversionService.convertJsonToTaxMasterCommand(null, jsonRequestBody);
		Long id=taxMasterWritePlatformService.createtaxMaster(command);
		return Response.ok().entity(id).build();
	}
    @GET
    @Path("template")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public String retrieveTempleteInfo(@Context final UriInfo uriInfo) {


	Set<String> typicalResponseParameters = new HashSet<String>(
				Arrays.asList("id","taxType","taxMasterOptions"));

		Set<String> responseParameters = ApiParameterHelper.extractFieldsForResponseIfProvided(uriInfo.getQueryParameters());
		if (responseParameters.isEmpty()) {
			responseParameters.addAll(typicalResponseParameters);
		}

	final boolean prettyPrint = ApiParameterHelper.prettyPrint(uriInfo.getQueryParameters());
       // final Collection<TaxMasterData> datas = this.taxMasterReadPlatformService.retrieveAllTaxMasterData();

         List<EnumOptionData> enumOptionData;
		enumOptionData=new ArrayList<EnumOptionData>();
		enumOptionData.add(new EnumOptionData(new Long(1),"Tax","notrequired"));
		enumOptionData.add(new EnumOptionData(new Long(2),"Tax on Tax","notrequired"));
        TaxMasterDataOptions taxMasterOptionsData=new TaxMasterDataOptions(enumOptionData);

        Collection<TaxMasterDataOptions> optionsData=new ArrayList<TaxMasterDataOptions>();
        optionsData.add(taxMasterOptionsData);


		return this.apiJsonSerializerService.serializeTaxMasterDataToJson(prettyPrint, responseParameters, optionsData);
    }

    @GET
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public String retrieveAllTaxMaster(@Context final UriInfo uriInfo) {


		Set<String> typicalResponseParameters = new HashSet<String>(
				Arrays.asList("id","subscription_period","subscription_type","units","subscriptionTypeId","day_name"));

		Set<String> responseParameters = ApiParameterHelper.extractFieldsForResponseIfProvided(uriInfo.getQueryParameters());
		if (responseParameters.isEmpty()) {
			responseParameters.addAll(typicalResponseParameters);
		}
		boolean prettyPrint = ApiParameterHelper.prettyPrint(uriInfo.getQueryParameters());

		Collection<TaxMasterDataOptions> taxes=this.taxMasterReadPlatformService.retrieveAllTaxes();
		return this.apiJsonSerializerService.serializeTaxMasterDataToJson(prettyPrint, responseParameters, taxes);
	}

}
