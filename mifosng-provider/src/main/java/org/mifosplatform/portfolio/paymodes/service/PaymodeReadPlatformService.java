package org.mifosplatform.portfolio.paymodes.service;

import java.util.Collection;
import java.util.List;

import org.mifosplatform.infrastructure.core.data.EnumOptionData;
import org.mifosplatform.portfolio.paymodes.data.PaymodeData;
import org.mifosplatform.portfolio.paymodes.data.PaymodeTypesData;

public interface PaymodeReadPlatformService {



	Collection<PaymodeData> retrieveAllPaymodes();

	PaymodeData retrieveSinglePaymode(Long paymodeId);


}
