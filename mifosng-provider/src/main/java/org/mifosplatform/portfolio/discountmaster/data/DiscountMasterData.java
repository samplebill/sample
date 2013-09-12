package org.mifosplatform.portfolio.discountmaster.data;

public class DiscountMasterData {
	    private long id;
		private String discountCode;
		private String discountDescription;
		private String discounType;
		private long discountValue;

		public DiscountMasterData(long id,long discountCode,String discountDescription,String discounType,long discountValue)
		{   this.id=id;
			this.discountDescription=discountDescription;
			this.discounType=discounType;
			this.discountValue=discountValue;

		}
		public DiscountMasterData(Long id, String discountcode,String discountdesc) {
			this.id=id;
			this.discountCode=discountcode;
			this.discountDescription=discountdesc;
			this.discounType=null;
			//this.discountValue=;
		}
		public long getId() {
			return id;
		}
		public void setId(long id) {
			this.id = id;
		}
		public String getDiscountCode() {
			return discountCode;
		}
		public void setDiscountCode(String discountCode) {
			this.discountCode = discountCode;
		}
		public String getDiscountDescription() {
			return discountDescription;
		}
		public void setDiscountDescription(String discountDescription) {
			this.discountDescription = discountDescription;
		}
		public String getDiscounType() {
			return discounType;
		}
		public void setDiscounType(String discounType) {
			this.discounType = discounType;
		}
		public long getDiscountValue() {
			return discountValue;
		}
		public void setDiscountValue(long discountValue) {
			this.discountValue = discountValue;
		}
}
