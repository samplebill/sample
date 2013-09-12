package org.mifosplatform.portfolio.adjustment.api;

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
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.mifosplatform.infrastructure.core.api.ApiParameterHelper;
import org.mifosplatform.portfolio.adjustment.commands.AdjustmentCommand;
import org.mifosplatform.portfolio.adjustment.data.AdjustmentCodeData;
import org.mifosplatform.portfolio.adjustment.data.AdjustmentData;
import org.mifosplatform.portfolio.adjustment.service.AdjustmentReadPlatformService;
import org.mifosplatform.portfolio.adjustment.service.AdjustmentWritePlatformService;
import org.mifosplatform.portfolio.adjustment.service.ClientBalanceReadPlatformService;
import org.mifosplatform.portfolio.billingproduct.PortfolioApiDataBillingConversionService;
import org.mifosplatform.portfolio.billingproduct.PortfolioApiJsonBillingSerializerService;
import org.mifosplatform.portfolio.clientbalance.data.ClientBalanceData;
import org.mifosplatform.portfolio.discountmaster.commands.Discount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
@Path("/adjustments")
@Component
@Scope("singleton")
public class AdjustmentApiResource {
	@Autowired
	private AdjustmentWritePlatformService adjustmentWritePlatformService;
	@Autowired
	private PortfolioApiDataBillingConversionService apiDataConversionService;
	@Autowired
	private PortfolioApiJsonBillingSerializerService apiJsonSerializerService;
	@Autowired
	private  ClientBalanceReadPlatformService clientBalanceReadPlatformService;
	@Autowired
	private AdjustmentReadPlatformService adjustmentReadPlatformService;

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
	        final Collection<Discount> datass=new ArrayList<Discount>();
	        Discount d=new Discount();
	        d.setadjustment_type();
	        datass.add(d);
	        List<AdjustmentData> data=this.adjustmentReadPlatformService.retrieveAllAdjustmentsCodes();
	        AdjustmentCodeData datas=new AdjustmentCodeData(datass,data);

		return this.apiJsonSerializerService.serializeDiscountMasterDataToJsonTemplete(prettyPrint, responseParameters,datas );
	    }
	   @POST
	    @Path("{clientId}")
		@Consumes({MediaType.APPLICATION_JSON})
		@Produces({MediaType.APPLICATION_JSON})
		public Response addNewAdjustment(@PathParam("clientId") final Long clientId, final String jsonRequestBody) {
			final AdjustmentCommand command= this.apiDataConversionService.convertJsonToAdjustmentCommand(null, jsonRequestBody);
		   //	ClientBalanceData clientBalanceData=clientBalanceReadPlatformService.retrieveClientBalanceId(clientId);

									//clientBalancedatas
			List<ClientBalanceData> clientBalancedatas = clientBalanceReadPlatformService.retrieveAllClientBalances(clientId);

									//adjustmentBalancesDatas
			List<ClientBalanceData> adjustmentBalancesDatas = adjustmentReadPlatformService.retrieveAllAdjustments(clientId);

			Long id=Long.valueOf(-1);
			if(clientBalancedatas.size() == 1 && adjustmentBalancesDatas.size() == 1)
																// list.get(0)
			id=adjustmentWritePlatformService.createAdjustment(clientBalancedatas.get(0).getId(),adjustmentBalancesDatas.get(0).getId(),clientId,command);
			else
			id=	adjustmentWritePlatformService.createAdjustment(clientId,clientId,clientId,command);
			return Response.ok().entity(id).build();
		}

/*	    @PUT
	    @Path("{clientId}")
	    @Consumes({MediaType.APPLICATION_JSON})
	    @Produces({MediaType.APPLICATION_JSON})
	    public Response update(@PathParam("clientId") final Long clientId, final String jsonRequestBody){

		//AdjustmentData data=adjustmentReadPlatformService.retrieveAdjustment(id);

		final ChargeCommand command = this.apiDataConversionService.convertJsonToChargeCommand(clientId, jsonRequestBody);

		//finl ChargeData=
		//final Long entityId = this.chargeWritePlatformService.updateCharge(command);

	        return Response.ok().entity(new EntityIdentifier(entityId)).build();
	    }*/

}
