package org.mifosplatform.infrastructure.configuration.service;

import java.util.List;

import org.mifosplatform.infrastructure.configuration.data.PeriodData;

public interface PeriodReadPlatformService {

	 List<PeriodData> retrieveAllPlatformPeriod();

}
