package org.mifosplatform.portfolio.charge.data;

public class CategoryOptionData {

		private final Long id;
		private final String code;
		private final String value;

		public CategoryOptionData(final Long id, final String code, final String value) {
			this.id = id;
			this.code = code;
			this.value = value;
		}

		public Long getId() {
			return id;
		}

		public String getCode() {
			return code;
		}

		public String getValue() {
			return value;
		}
	}
