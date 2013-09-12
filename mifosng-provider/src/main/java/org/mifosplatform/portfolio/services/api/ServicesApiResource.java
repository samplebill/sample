package org.mifosplatform.portfolio.services.api;

import java.util.Arrays;
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
import org.mifosplatform.infrastructure.core.data.CommandProcessingResult;
import org.mifosplatform.infrastructure.core.data.EnumOptionData;
import org.mifosplatform.infrastructure.security.service.PlatformSecurityContext;
import org.mifosplatform.portfolio.billingproduct.PortfolioApiDataBillingConversionService;
import org.mifosplatform.portfolio.billingproduct.PortfolioApiJsonBillingSerializerService;
import org.mifosplatform.portfolio.payment.service.PaymentWritePlatformService;
import org.mifosplatform.portfolio.plan.data.ServiceData;
import org.mifosplatform.portfolio.servicemaster.commands.ServicesCommand;
import org.mifosplatform.portfolio.services.service.ServiceReadPlatformService;
import org.mifosplatform.portfolio.services.service.ServiceWritePlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Path("/services")
@Component
@Scope("singleton")
public class ServicesApiResource {


	@Autowired
	private PaymentWritePlatformService paymentWritePlatformService;

	@Autowired
	private PortfolioApiDataBillingConversionService apiDataConversionService;

    @Autowired
    private ServiceWritePlatformService serviceWritePlatformService;

    @Autowired
    private ServiceReadPlatformService serviceReadPlatformService;

	@Autowired
	private PortfolioApiJsonBillingSerializerService apiJsonSerializerService;

	private final String entityType = "PAYMENTS";
	@Autowired
	private PlatformSecurityContext context;
	private static final Set<String> typicalResponseParameters = new HashSet<String>(
			Arrays.asList("id","serviceCode","serviceDescription","serviceType"));

	@POST
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response createDepositAccount(final String jsonRequestBody){

		ServicesCommand command = this.apiDataConversionService.convertJsonToServiceCommand(null, jsonRequestBody);

CommandProcessingResult entityIdentifier = this.serviceWritePlatformService.createService(command);

		return Response.ok().entity(entityIdentifier).build();
	}
	@GET
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public String retrieveAllSubscription(@Context final UriInfo uriInfo) {

	context.authenticatedUser().validateHasReadPermission(entityType);

		Set<String> typicalResponseParameters = new HashSet<String>(
				Arrays.asList("id","serviceCode","serviceDescription","serviceType"));

		Set<String> responseParameters = ApiParameterHelper.extractFieldsForResponseIfProvided(uriInfo.getQueryParameters());
		if (responseParameters.isEmpty()) {
			responseParameters.addAll(typicalResponseParameters);
		}
		boolean prettyPrint = ApiParameterHelper.prettyPrint(uriInfo.getQueryParameters());

		  List<EnumOptionData> datas=this.serviceReadPlatformService.retrieveServiceType();
		  ServiceData data=new ServiceData(datas);
		return this.apiJsonSerializerService.serializeServiceToJson(prettyPrint, responseParameters, data);
	}


}
