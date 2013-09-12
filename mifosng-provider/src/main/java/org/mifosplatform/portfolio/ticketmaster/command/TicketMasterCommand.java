package org.mifosplatform.portfolio.ticketmaster.command;

import org.joda.time.LocalDate;

public class TicketMasterCommand {

	private final Long id;
	private final Long clientId;
	private final String priority;
	private final LocalDate ticketDate;
	private final String problemCode;
	private final String description;
	private final String status;
	private final String resolutionDescription;
	private final String assignedTo;
	private final String comments;
	private final Long ticketId;
	public TicketMasterCommand(final Long clientId,final String priority,
	final String description,final String problemCode,final String status,
	final String resolutionDescription, final String assignedTo,final LocalDate ticketDate){		
		
		this.id=null;
		this.clientId=clientId;
		this.priority=priority;
		this.ticketDate=ticketDate;
		this.problemCode=problemCode;
		this.description=description;
		this.status=status;
		this.resolutionDescription=resolutionDescription;
		this.assignedTo=assignedTo;
		this.comments=null;
		this.ticketId=null;
	}
	public TicketMasterCommand(Long ticketId, String comments, String status,
			String assignedTo) {
		
		this.id=null;
		this.clientId=null;
		this.priority=null;
		this.ticketDate=null;
		this.problemCode=null;
		this.description=null;
		this.status=status;
		this.resolutionDescription=null;
		this.assignedTo=assignedTo;
		this.comments=comments;
		this.ticketId=ticketId;
		
	}
	public Long getId() {
		return id;
	}
	public Long getClientId() {
		return clientId;
	}
	public String getPriority() {
		return priority;
	}
	public LocalDate getTicketDate() {
		return ticketDate;
	}
	public String getProblemCode() {
		return problemCode;
	}
	public String getDescription() {
		return description;
	}
	public String getStatus() {
		return status;
	}
	public String getResolutionDescription() {
		return resolutionDescription;
	}
	public String getAssignedTo() {
		return assignedTo;
	}
	public String getComments() {
		return comments;
	}
	public Long getTicketId() {
		return ticketId;
	}
	
	
	
}
