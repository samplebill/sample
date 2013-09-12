package org.mifosplatform.portfolio.adjustment.service;

import org.mifosplatform.infrastructure.security.service.PlatformSecurityContext;
import org.mifosplatform.portfolio.adjustment.commands.AdjustmentCommand;
import org.mifosplatform.portfolio.adjustment.domain.Adjustment;
import org.mifosplatform.portfolio.adjustment.domain.AdjustmentCommandValidator;
import org.mifosplatform.portfolio.adjustment.domain.AdjustmentRepository;
import org.mifosplatform.portfolio.adjustment.domain.ClientBalance;
import org.mifosplatform.portfolio.adjustment.domain.ClientBalanceRepository;
import org.mifosplatform.portfolio.pricing.domain.PricingCommandValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdjustmentWritePlatformServiceJpaRepositoryImpl implements
		AdjustmentWritePlatformService {

	private final org.mifosplatform.infrastructure.security.service.PlatformSecurityContext context;
	private final AdjustmentRepository adjustmentRepository;
	private final ClientBalanceRepository clientBalanceRepository;
	private final UpdateClientBalance updateClientBalance;

	@Autowired
	public AdjustmentWritePlatformServiceJpaRepositoryImpl(
			final PlatformSecurityContext context,
			final AdjustmentRepository adjustmentRepository,
			final ClientBalanceRepository clientBalanceRepository,
			final UpdateClientBalance updateClientBalance) {
		this.context = context;
		this.adjustmentRepository = adjustmentRepository;
		this.clientBalanceRepository = clientBalanceRepository;
		this.updateClientBalance = updateClientBalance;

	}

	@SuppressWarnings("unused")
	@Transactional
	@Override
	public Long createAdjustment(Long id2, Long id, Long clientid,
			AdjustmentCommand command) {
		// TODO Auto-generated method stub

		try {
			this.context.authenticatedUser();
			AdjustmentCommandValidator validator=new AdjustmentCommandValidator(command);
			validator.validateForCreate();

			Adjustment adjustment = null;
//			if (id2 != null)
//				adjustment = adjustmentRepository.findOne(id2);
//			if (adjustment == null) {
				adjustment = Adjustment.create(clientid,
						command.getAdjustment_date(),
						command.getAdjustment_code(),
						command.getAdjustment_type(), command.getAmount_paid(),
						command.getBill_id(), command.getExternal_id(),
						command.getRemarks());
//			}
//			else {
//
//
//
//			}
			// List<ClientBalance> clientBalances =
			// adjustment.getClientBalances();
			// for(ClientBalance clientBalance : clientBalances)



			/* Manoj code for updating client balance */

			ClientBalance clientBalance = null;
			if(id!=null)
			clientBalance = clientBalanceRepository.findOne(id);

			if(clientBalance != null){

				clientBalance = updateClientBalance.doUpdateClientBalance(command.getAdjustment_type(),command.getAmount_paid(),clientid,clientBalance);

			}else if(clientBalance == null){

				clientBalance = updateClientBalance.createClientBalance(command.getAdjustment_type(),command.getAmount_paid(),clientid,clientBalance);
			}

			updateClientBalance.saveClientBalanceEntity(clientBalance);

			this.adjustmentRepository.saveAndFlush(adjustment);

		/*	ClientBalance clientBalance = null;

			if (id != null)
				clientBalance = clientBalanceRepository.findOne(id);

			if (clientBalance != null) {

				updateClientBalance.doUpdateClientBalance();

				if (command.getAdjustment_type().equalsIgnoreCase("DR")) {

					clientBalance.setBalanceAmount(clientBalance
							.getBalanceAmount().add(command.getAmount_paid()));

				} else if (command.getAdjustment_type().equalsIgnoreCase("CR")) {

					clientBalance.setBalanceAmount(clientBalance
							.getBalanceAmount().subtract(
									command.getAmount_paid()));

				}

				this.clientBalanceRepository.saveAndFlush(clientBalance);

			} else {
				BigDecimal currentBalance = null;
				if (command.getAdjustment_type().equalsIgnoreCase("DR")) {
					currentBalance = command.getAmount_paid();
				} else if (command.getAdjustment_type().equalsIgnoreCase("CR")) {
					currentBalance = new BigDecimal(0.0).subtract(command
							.getAmount_paid());
				}
				clientBalance = new ClientBalance(clientid, currentBalance);
				adjustment.updateclientBalances(clientBalance);
			}*/

			//this.adjustmentRepository.saveAndFlush(adjustment);

			return adjustment.getId();

		} catch (DataIntegrityViolationException dve) {
			return Long.valueOf(-1);
		}
	}
}
