package org.mifosplatform.portfolio.adjustment.service;

import java.util.List;

import org.mifosplatform.portfolio.clientbalance.data.ClientBalanceData;

public interface ClientBalanceReadPlatformService {
	ClientBalanceData retrieveClientBalanceId(Long id);
	List<ClientBalanceData> retrieveAllClientBalances(Long id);
}
