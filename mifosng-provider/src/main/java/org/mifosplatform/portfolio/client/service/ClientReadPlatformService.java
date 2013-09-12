package org.mifosplatform.portfolio.client.service;

import java.util.Collection;
import java.util.List;

import org.mifosplatform.portfolio.client.data.ClientAccountSummaryCollectionData;
import org.mifosplatform.portfolio.client.data.ClientAccountSummaryData;
import org.mifosplatform.portfolio.client.data.ClientData;
import org.mifosplatform.portfolio.client.data.ClientIdentifierData;
import org.mifosplatform.portfolio.client.data.ClientLookup;
import org.mifosplatform.portfolio.client.data.NoteData;
import org.mifosplatform.portfolio.order.data.OrderData;

public interface ClientReadPlatformService {

    Collection<ClientData> retrieveAllIndividualClients(String extraCriteria);

    ClientData retrieveIndividualClient(Long clientId);

    ClientData retrieveNewClientDetails();

    Collection<ClientLookup> retrieveAllIndividualClientsForLookup(String extraCriteria);

    Collection<ClientLookup> retrieveAllIndividualClientsForLookupByOfficeId(Long officeId);

    ClientAccountSummaryCollectionData retrieveClientAccountDetails(Long clientId);

    Collection<ClientAccountSummaryData> retrieveClientLoanAccountsByLoanOfficerId(Long clientId, Long loanOfficerId);

    Collection<NoteData> retrieveAllClientNotes(Long clientId);

    NoteData retrieveClientNote(Long clientId, Long noteId);

    Collection<ClientIdentifierData> retrieveClientIdentifiers(Long clientId);

    ClientIdentifierData retrieveClientIdentifier(Long clientId, Long clientIdentifierId);

    ClientData retrieveClientByIdentifier(Long identifierTypeId, String identifierKey);

	List<OrderData> retrieveClientOrderDetails(Long clientId);
}