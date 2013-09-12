package org.mifosplatform.portfolio.adjustment.service;

import java.math.BigDecimal;

import org.mifosplatform.infrastructure.security.service.PlatformSecurityContext;
import org.mifosplatform.portfolio.adjustment.domain.ClientBalance;
import org.mifosplatform.portfolio.adjustment.domain.ClientBalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateClientBalance {

	private final PlatformSecurityContext context;
	private final ClientBalanceRepository clientBalanceRepository;


	@Autowired
	public UpdateClientBalance(final PlatformSecurityContext context,final ClientBalanceRepository clientBalanceRepository) {
		this.context = context;
		this.clientBalanceRepository = clientBalanceRepository;
	}

	public ClientBalance doUpdateClientBalance(String transactionType,
			BigDecimal amount, Long clientId, ClientBalance clientBalance) {

		if (transactionType.equalsIgnoreCase("DEBIT")) {

			clientBalance.setBalanceAmount(clientBalance.getBalanceAmount().add(amount));

		} else if (transactionType.equalsIgnoreCase("CREDIT")) {

			clientBalance.setBalanceAmount(clientBalance.getBalanceAmount().subtract(amount));
		}

		return clientBalance;

	}

	public ClientBalance createClientBalance(String transactionType, BigDecimal amount, Long clientId, ClientBalance clientBalance) {
		BigDecimal balanceAmount = BigDecimal.ZERO;
		if (transactionType.equalsIgnoreCase("DEBIT")) {
			balanceAmount = amount;
		} else if (transactionType.equalsIgnoreCase("CREDIT")) {
			balanceAmount = BigDecimal.ZERO.subtract(amount);
		}

		clientBalance = new ClientBalance(clientId, balanceAmount);

		return clientBalance;
	}

	public ClientBalance saveClientBalanceEntity(ClientBalance clientBalance){
		ClientBalance resultantClientBalance =  this.clientBalanceRepository.save(clientBalance);
		return resultantClientBalance;
	}

}
