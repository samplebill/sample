package org.mifosplatform.portfolio.paymodes.commands;

import java.util.Set;

public class PaymodeCommand {


			private final String paymodeCode;

			private  String paymodeDescription;
			private final Set<String> modifiedParameters;

			private final Long id;
			private  String category;
			public PaymodeCommand(final Set<String> modifiedParameters,
					final Long id,
					final String paymode,
					final String description,
					final String category) {

				this.paymodeCode=paymode;
				this.paymodeDescription=description;
				this.category=category;
				this.id=id;
				this.modifiedParameters=modifiedParameters;



			}
			public String getDescription() {
				return paymodeDescription;
			}
			
			
		
			public String getPaymode() {
				return paymodeCode;
			}
			public Long getId() {
				return id;
			}

			public boolean ispaymodeCodeChanged() {
				return this.modifiedParameters.contains("paymode");
			}

			public boolean ispaymodeDescChanged() {
				return this.modifiedParameters.contains("description");
			}






}
