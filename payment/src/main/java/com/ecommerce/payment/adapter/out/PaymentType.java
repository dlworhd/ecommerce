package com.ecommerce.payment.adapter.out;

public enum PaymentType {
	MONEY("MONEY"), CARD("CARD");

	private String value;

	PaymentType(String value) {
		this.value = value;
	}

	public static PaymentType getPaymentType(String value){
		if(value.equals("MONEY")){
			return MONEY;
		} else {
			return CARD;
		}
	}
}
