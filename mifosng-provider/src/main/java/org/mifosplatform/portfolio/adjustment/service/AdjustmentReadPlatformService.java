package org.mifosplatform.portfolio.adjustment.service;

import java.util.List;

import org.mifosplatform.portfolio.adjustment.data.AdjustmentData;
import org.mifosplatform.portfolio.clientbalance.data.ClientBalanceData;

public interface AdjustmentReadPlatformService {
	List<ClientBalanceData> retrieveAllAdjustments(Long id);

	List<AdjustmentData> retrieveAllAdjustmentsCodes();
}
