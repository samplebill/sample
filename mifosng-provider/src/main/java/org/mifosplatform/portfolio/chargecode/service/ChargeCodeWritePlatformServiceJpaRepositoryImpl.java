package org.mifosplatform.portfolio.chargecode.service;

import org.mifosplatform.infrastructure.security.service.PlatformSecurityContext;
import org.mifosplatform.portfolio.charge.commands.ChargeCodeCommand;
import org.mifosplatform.portfolio.charge.service.ChargeCode;
import org.mifosplatform.portfolio.chargecode.domain.ChargeCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class ChargeCodeWritePlatformServiceJpaRepositoryImpl  implements ChargeCodeWritePlatformService{
	private final PlatformSecurityContext context;
	private final ChargeCodeRepository chargeCodeRepository;

@Autowired
 public ChargeCodeWritePlatformServiceJpaRepositoryImpl(final PlatformSecurityContext context,final ChargeCodeRepository chargeCodeRepository)
{
	this.context=context;
	this.chargeCodeRepository=chargeCodeRepository;
}

@Transactional
@Override
	public Long createChargeCode(final ChargeCodeCommand command)
	{
	try {
		context.authenticatedUser();
		ChargeCode chargeCode = ChargeCode.create(command.getChargeCode(),command.getChargeDescription(),command.getChargeType());
			this.chargeCodeRepository.save(chargeCode);
		return chargeCode.getId();
	} catch (DataIntegrityViolationException dve) {
		 return Long.valueOf(-1);
	}

	}


}
