package org.mifosplatform.portfolio.ticketmaster.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.joda.time.LocalDate;

@Entity
@Table(name = "ticket_details")
public class TicketDetail {
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;

	@Column(name = "ticket_id", length = 65536)
	private Long ticketId;

	@Column(name = "comments")
	private String comments;

	@Column(name = "attachments")
	private String attachments;
	
	@Column(name = "assigned_to")
	private String assignedTo;
	
	@Column(name = "created_date")
	private Date createdDate;

	
	public TicketDetail() {
		// TODO Auto-generated constructor stub
	}


	public TicketDetail(Long ticketId, String comments, String fileLocation,
			String assignedTo) {
                   this.ticketId=ticketId;
                   this.comments=comments;
                   this.attachments=fileLocation;
                   this.assignedTo=assignedTo;
                   this.createdDate=new LocalDate().toDate();
	
	
	
	}


	public Long getId() {
		return id;
	}


	public Long getTicketId() {
		return ticketId;
	}


	public String getComments() {
		return comments;
	}


	public String getAttachments() {
		return attachments;
	}


	public String getAssignedTo() {
		return assignedTo;
	}



	
	
	 
}
