package org.mifosplatform.portfolio.taxmaster.service;

import org.mifosplatform.infrastructure.security.service.PlatformSecurityContext;
import org.mifosplatform.portfolio.taxmaster.commands.TaxMasterCommand;
import org.mifosplatform.portfolio.taxmaster.domain.TaxMaster;
import org.mifosplatform.portfolio.taxmaster.domain.TaxMasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class TaxMasterWritePlatformServiceJpaRepositoryImpl implements TaxMasterWritePlatformService{

	private final PlatformSecurityContext context;
	private final TaxMasterRepository taxMasterRepository;

@Autowired
 public TaxMasterWritePlatformServiceJpaRepositoryImpl(final PlatformSecurityContext context,final TaxMasterRepository taxMasterRepository)
{
	this.context=context;
	this.taxMasterRepository=taxMasterRepository;
}
@Transactional
@Override
	public Long createtaxMaster(final TaxMasterCommand command)
	{
	try {
		context.authenticatedUser();
		TaxMaster taxMaster = TaxMaster.create(command.getTaxCode(),command.getTaxType(),command.getTaxDescription());
			this.taxMasterRepository.save(taxMaster);
		return taxMaster.getId();
	} catch (DataIntegrityViolationException dve) {
		 return Long.valueOf(-1);
	}

	}
}
