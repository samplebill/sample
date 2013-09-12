package org.mifosplatform.portfolio.payment.service;

import org.mifosplatform.infrastructure.core.data.CommandProcessingResult;
import org.mifosplatform.infrastructure.security.service.PlatformSecurityContext;
import org.mifosplatform.portfolio.adjustment.domain.ClientBalance;
import org.mifosplatform.portfolio.adjustment.domain.ClientBalanceRepository;
import org.mifosplatform.portfolio.adjustment.service.UpdateClientBalance;
import org.mifosplatform.portfolio.payment.command.Paymentcommand;
import org.mifosplatform.portfolio.payment.domain.Payment;
import org.mifosplatform.portfolio.payment.domain.PaymentRepository;
import org.mifosplatform.portfolio.payment.domain.PaymentValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PaymentWritePlatformServiceImpl implements
		PaymentWritePlatformService {

	private final PlatformSecurityContext context;
	private final PaymentRepository paymentRepository;
	private final UpdateClientBalance updateClientBalance;
	private final ClientBalanceRepository clientBalanceRepository;

	@Autowired
	public PaymentWritePlatformServiceImpl(
			final PlatformSecurityContext context,
			PaymentRepository paymentRepository,
			final UpdateClientBalance updateClientBalance,
			final ClientBalanceRepository clientBalanceRepository) {

		this.context = context;
		this.paymentRepository = paymentRepository;
		this.updateClientBalance = updateClientBalance;
		this.clientBalanceRepository = clientBalanceRepository;
	}

	@Transactional
	@Override
	public CommandProcessingResult createPayment(Paymentcommand command, Long clientId,Long clientBalanceId) {
		try {
			this.context.authenticatedUser();
			PaymentValidator validator = new PaymentValidator(command);
			validator.validateForCreate();
			Payment data = new Payment(clientId, command.getPaymentId(),
					command.getExternalId(), command.getAmountPaid(),
					command.getStatementId(), command.getPaymentDate(),
					command.getRemarks(), command.getPaymentCode());

			this.paymentRepository.save(data);

			ClientBalance clientBalance = null;
			if(clientBalanceId != null){
				clientBalance = clientBalanceRepository.findOne(clientBalanceId);
			}

			if(clientBalance == null){

				clientBalance = updateClientBalance.createClientBalance("CREDIT",command.getAmountPaid(),clientId,clientBalance);
			}else if(clientBalance != null){

				clientBalance = updateClientBalance.doUpdateClientBalance("CREDIT",command.getAmountPaid(),clientId,clientBalance);

			}

			updateClientBalance.saveClientBalanceEntity(clientBalance);




			return new CommandProcessingResult(data.getId());
		} catch (DataIntegrityViolationException dve) {
			handleDataIntegrityIssues(command, dve);
			return new CommandProcessingResult(Long.valueOf(-1));
		}
	}

	private void handleDataIntegrityIssues(Paymentcommand command,
			DataIntegrityViolationException dve) {
		// TODO Auto-generated method stub

	}
}
