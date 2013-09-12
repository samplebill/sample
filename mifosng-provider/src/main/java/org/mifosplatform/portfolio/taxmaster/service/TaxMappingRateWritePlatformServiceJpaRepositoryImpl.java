package org.mifosplatform.portfolio.taxmaster.service;
import org.mifosplatform.infrastructure.security.service.PlatformSecurityContext;
import org.mifosplatform.portfolio.chargecode.domain.ChargeCodeRepository;
import org.mifosplatform.portfolio.taxmaster.commands.TaxMappingRateCommand;
import org.mifosplatform.portfolio.taxmaster.domain.TaxMappingRate;
import org.mifosplatform.portfolio.taxmaster.domain.TaxMappingRateRepository;
import org.mifosplatform.portfolio.taxmaster.domain.TaxMaster;
import org.mifosplatform.portfolio.taxmaster.domain.TaxMasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class TaxMappingRateWritePlatformServiceJpaRepositoryImpl implements TaxMappingRateWritePlatformService{
	private final PlatformSecurityContext context;
	private final TaxMappingRateRepository taxMappingRateRepository;
	private final TaxMasterRepository taxMasterRepository;
	private final ChargeCodeRepository chargeCodeRepository;
@Autowired
 public TaxMappingRateWritePlatformServiceJpaRepositoryImpl(final PlatformSecurityContext context,final TaxMappingRateRepository taxMappingRateRepository,final TaxMasterRepository taxMasterRepository,final ChargeCodeRepository chargeCodeRepository)
{
	this.context=context;
	this.taxMappingRateRepository=taxMappingRateRepository;
	this.taxMasterRepository=taxMasterRepository;
	this.chargeCodeRepository=chargeCodeRepository;
}
@Transactional
@Override
public Long createtaxMasterMapping(final TaxMappingRateCommand command)
	{
	try {
		context.authenticatedUser();
		TaxMaster taxMaster;

		TaxMappingRate taxMappingRate = TaxMappingRate.create(command.getChargeCode(),command.getTaxCode(),command.getStartdate(),command.getType(),command.getValue());
		this.taxMappingRateRepository.save(taxMappingRate);
		return taxMappingRate.getId();
	} catch (DataIntegrityViolationException dve) {
		 return Long.valueOf(-1);
	}
	}

}
