package org.mifosplatform.portfolio.chargecode.service;

import org.mifosplatform.portfolio.charge.commands.ChargeCodeCommand;

public interface ChargeCodeWritePlatformService {
	Long createChargeCode(ChargeCodeCommand command);
}
