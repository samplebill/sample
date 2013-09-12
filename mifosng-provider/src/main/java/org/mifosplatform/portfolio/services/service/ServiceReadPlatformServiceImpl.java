package org.mifosplatform.portfolio.services.service;

import java.util.Arrays;
import java.util.List;

import org.mifosplatform.infrastructure.core.data.EnumOptionData;
import org.mifosplatform.portfolio.services.data.SavingServiceEnumaration;
import org.mifosplatform.portfolio.services.service.ServiceReadPlatformService;
import org.mifosplatform.portfolio.services.service.ServiceTypeEnum;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ServiceReadPlatformServiceImpl  implements ServiceReadPlatformService {


	   @SuppressWarnings("null")
	   @Transactional
		@Override
		public List<EnumOptionData> retrieveServiceType() {
			EnumOptionData general = SavingServiceEnumaration.interestCompoundingPeriodType(ServiceTypeEnum.GENERAL);
			List<EnumOptionData> serviceType = Arrays.asList(general);
			return serviceType;
		}



}
