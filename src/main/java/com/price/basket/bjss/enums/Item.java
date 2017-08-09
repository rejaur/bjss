package com.price.basket.bjss.enums;

public enum Item {

	MILK("milk"), APPLES("apples"), BREAD("bread"), SOUP("soup"), APPLES_DISCOUNT_PRICE("applediscountprice"), LOAF_DISCOUNT_PRICE("loafdiscountprice");

	private String value;

	Item(final String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return this.getValue();
	}
}
