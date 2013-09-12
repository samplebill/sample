package org.mifosplatform.portfolio.discountmaster.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
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

import org.mifosplatform.infrastructure.core.api.ApiParameterHelper;
import org.mifosplatform.infrastructure.core.data.CommandProcessingResult;
import org.mifosplatform.portfolio.billingproduct.PortfolioApiDataBillingConversionService;
import org.mifosplatform.portfolio.billingproduct.PortfolioApiJsonBillingSerializerService;
import org.mifosplatform.portfolio.discountmaster.commands.Discount;
import org.mifosplatform.portfolio.discountmaster.commands.DiscountMasterCommand;
import org.mifosplatform.portfolio.discountmaster.data.DiscountMasterData;
import org.mifosplatform.portfolio.discountmaster.service.DiscountMasterReadPlatformService;
import org.mifosplatform.portfolio.discountmaster.service.DiscountMasterWritePlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Path("/discountmasters")
@Component
@Scope("singleton")
public class DiscountMasterApiResource {


	@Autowired
	private DiscountMasterWritePlatformService discountMasterWritePlatformService;
	@Autowired
	private PortfolioApiDataBillingConversionService apiDataConversionService;
	@Autowired
	private PortfolioApiJsonBillingSerializerService apiJsonSerializerService;
	@Autowired
	private DiscountMasterReadPlatformService discountMasterReadPlatformService;

	@GET
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public String retrieveDiscountMasterDatas(@Context final UriInfo uriInfo) {

		Set<String> typicalResponseParameters = new HashSet<String>(
				Arrays.asList("id","discountCode" ,"discountDescription", "discounType","discountValue"));

		Set<String> responseParameters = ApiParameterHelper.extractFieldsForResponseIfProvided(uriInfo.getQueryParameters());
		if (responseParameters.isEmpty()) {
			responseParameters.addAll(typicalResponseParameters);
		}
		boolean prettyPrint = ApiParameterHelper.prettyPrint(uriInfo.getQueryParameters());

		final Collection<DiscountMasterData> datas = this.discountMasterReadPlatformService.retrieveAllDiscountMasterData();

		return this.apiJsonSerializerService.serializeDiscountMasterDataToJson(prettyPrint, responseParameters, datas);
	}
	@GET
	@Path("{chartcode}")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public String retrieveDiscountMasterData(@PathParam("id") final Long id,@Context final UriInfo uriInfo) {

		Set<String> responseParameters = ApiParameterHelper.extractFieldsForResponseIfProvided(uriInfo.getQueryParameters());
		boolean prettyPrint = ApiParameterHelper.prettyPrint(uriInfo.getQueryParameters());

		final DiscountMasterData data = this.discountMasterReadPlatformService.retrieveDiscountMasterData(id);

		return this.apiJsonSerializerService.serializeDiscountMasterDataToJson(prettyPrint, responseParameters, data);

	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response createDiscountMaster(final String jsonRequestBody) {

		final DiscountMasterCommand command = this.apiDataConversionService.convertJsonToDiscountMasterCommand(null, jsonRequestBody);
		Long id=discountMasterWritePlatformService.createDiscountMaster(command);
		return Response.ok().entity(id).build();
	}

    @GET
    @Path("template")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public String retrieveTempleteInfo(@Context final UriInfo uriInfo) {

	Set<String> typicalResponseParameters = new HashSet<String>(
				Arrays.asList("discountOptions"));

		Set<String> responseParameters = ApiParameterHelper.extractFieldsForResponseIfProvided(uriInfo.getQueryParameters());
		if (responseParameters.isEmpty()) {
			responseParameters.addAll(typicalResponseParameters);
		}

	final boolean prettyPrint = ApiParameterHelper.prettyPrint(uriInfo.getQueryParameters());

     //  final Collection<DiscountMasterData> datas = this.discountMasterReadPlatformService.retrieveAllDiscountMasterData();
       final Collection<Discount> datass=new ArrayList<Discount>();
       Discount d=new Discount();
       d.setdiscount1();
       datass.add(d);
	//final Collection<Discount> datas = new ArrayList<Discount>();

		//return this.apiJsonSerializerService.serializeDiscountMasterDataToJsonTemplete(prettyPrint, responseParameters, d);
	return this.apiJsonSerializerService.serializeDiscountMasterDataToJsonTemplete(prettyPrint, responseParameters,datass );
    }


	@PUT
	@Path("{Id}")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response updateCode(@PathParam("Id") final Long codeId, final String jsonRequestBody) {

		final DiscountMasterCommand command = this.apiDataConversionService.convertJsonToDiscountMasterCommand(codeId, jsonRequestBody);
		//Long id=discountMasterWritePlatformService.updateDiscountMaster(command);

		//return Response.ok().entity(new EntityIdentifier(id)).build();
return null;

	}

	@DELETE
	@Path("{codeId}")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response deleteCodeApplication(@PathParam("codeId") final Long id) {

		CommandProcessingResult identifier = this.discountMasterWritePlatformService.deleteDiscountMaster(id);

		return Response.ok().entity(identifier).build();
	}


}
