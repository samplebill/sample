package org.mifosplatform.portfolio.adjustment.service;

import org.mifosplatform.portfolio.adjustment.commands.AdjustmentCommand;

public interface AdjustmentWritePlatformService {
	public Long createAdjustment(final Long id2,final Long id,final Long clientid,final AdjustmentCommand command);

}
