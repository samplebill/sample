package org.mifosplatform.portfolio.payterms.commands;

import java.util.Set;

public class PaytermsCommand {


	private final Long payterm_period;

	private final String payterm_type;

	private final Long units;

	private final Long id;

	public PaytermsCommand(final Long id,final Long paytermn_period,final String payterm_type,final Long units)
	{
		this.id=id;
		this.payterm_period=paytermn_period;
		this.payterm_type=payterm_type;
		this.units=units;

	}
	public Long getPayterm_period() {
		return payterm_period;
	}
	public String getPayterm_type() {
		return payterm_type;
	}
	public Long getUnits() {
		return units;
	}
	public Long getId() {
		return id;
	}



}
