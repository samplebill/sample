package org.mifosplatform.portfolio.order.data;

import org.mifosplatform.portfolio.order.data.OrderData;

public class OrderingData {

	private final OrderData data;

	public OrderingData(final OrderData datas)
	{
		this.data=datas;
	}

	public OrderData getData() {
		return data;
	}


}
