package org.mifosplatform.portfolio.charge.data;

public class ChargesData {



		private final Long id;

		private final String charge_code;
		private final String charge_description;

		public ChargesData(final Long id,final String charge_code,final String charge_description)
		{

			this.charge_description=charge_description;
			this.id=id;
			this.charge_code=charge_code;
		}

		public ChargesData(Long id, String charge_code) {
			this.charge_description=null;
			this.id=id;
			this.charge_code=charge_code;
		}

		public Long getId() {
			return id;
		}

		public String getChargeCode() {
			return charge_code;
		}

		public String getChargeDescription() {
			return charge_description;
		}



	}
