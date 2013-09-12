package org.mifosplatform.portfolio.charge.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.mifosplatform.portfolio.billingproduct.PortfolioApiDataBillingConversionService;
import org.mifosplatform.portfolio.billingproduct.PortfolioApiJsonBillingSerializerService;
import org.mifosplatform.portfolio.charge.commands.ChargeCodeCommand;
import org.mifosplatform.portfolio.chargecode.service.ChargeCodeWritePlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Path("/chargecodes")
@Component
@Scope("singleton")
public class ChargeCodeApiResource {
	@Autowired
	private ChargeCodeWritePlatformService chargeCodeWritePlatformService;
	@Autowired
	private PortfolioApiDataBillingConversionService apiDataConversionService;
	@Autowired
	private PortfolioApiJsonBillingSerializerService apiJsonSerializerService;
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response createDiscountMaster(final String jsonRequestBody) {
		final ChargeCodeCommand command = this.apiDataConversionService.convertJsonToChargeCodeCommand(null, jsonRequestBody);
		Long id=chargeCodeWritePlatformService.createChargeCode(command);
		return Response.ok().entity(id).build();
	}

}
