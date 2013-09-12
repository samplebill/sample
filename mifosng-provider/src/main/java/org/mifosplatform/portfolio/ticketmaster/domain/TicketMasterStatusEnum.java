package org.mifosplatform.portfolio.ticketmaster.domain;

import org.mifosplatform.infrastructure.core.data.EnumOptionData;
import org.mifosplatform.portfolio.servicemaster.api.TicketMasterStatus;

public class TicketMasterStatusEnum {
	
			public static EnumOptionData statusType(final int id) {
				return statusType(TicketMasterStatus.fromInt(id));
			}

			public static EnumOptionData statusType(final TicketMasterStatus type) {
				final String codePrefix = "deposit.interest.compounding.period.";
				EnumOptionData optionData = null;
				switch (type) {
				case ACTIVE:
					optionData = new EnumOptionData(TicketMasterStatus.ACTIVE.getValue().longValue(), codePrefix + TicketMasterStatus.ACTIVE.getCode(), "NOW/ACTIVE");
					break;
				case RESOLVED:
					optionData = new EnumOptionData(TicketMasterStatus.RESOLVED.getValue().longValue(), codePrefix + TicketMasterStatus.RESOLVED.getCode(), "RESOLVED");
					break;

				case CLOSED:
					optionData = new EnumOptionData(TicketMasterStatus.CLOSED.getValue().longValue(), codePrefix + TicketMasterStatus.CLOSED.getCode(), "CLOSED");
					break;

				case TESTING:
					optionData = new EnumOptionData(TicketMasterStatus.TESTING.getValue().longValue(), codePrefix + TicketMasterStatus.TESTING.getCode(), "TESTING");
					break;

				case WORKING:
					optionData = new EnumOptionData(TicketMasterStatus.WORKING.getValue().longValue(), codePrefix + TicketMasterStatus.WORKING.getCode(), "WORKING");
					break;

				

				default:
					optionData = new EnumOptionData(TicketMasterStatus.INVALID.getValue().longValue(), TicketMasterStatus.INVALID.getCode(), "INVALID");
					break;
				}
				return optionData;
			}

		}






