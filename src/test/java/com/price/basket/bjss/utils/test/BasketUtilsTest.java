package com.price.basket.bjss.utils.test;

import static com.price.basket.bjss.utils.BasketUtils.*;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class BasketUtilsTest {

	private static final String LOAF_DISCOUNT_PRICE = "0.40";
	private static final String FORTY_PENCE = LOAF_DISCOUNT_PRICE;
	private static final String TEN_PENCE = "0.10";
	private static final String ONE_POUND = "1.00";
	private static final String FIVE_POUNDS = "5";
	List<BigDecimal> listPrice;
	BigDecimal applePrice;
	BigDecimal appleDiscountPrice;

	@Before
	public void setUp() throws Exception {
		listPrice = new ArrayList<>();
		listPrice.add(new BigDecimal(FIVE_POUNDS));
		listPrice.add(new BigDecimal(FIVE_POUNDS));

		applePrice = new BigDecimal(ONE_POUND);
		appleDiscountPrice = new BigDecimal(TEN_PENCE);
	}

	@Test
	public void testGetPriceFromList() throws Exception {
		getPriceFromList(listPrice);

		assertEquals(10, getPriceFromList(listPrice).intValue());
	}

	@Test
	public void testRoundedToNearestPenny() throws Exception {
		assertEquals(new BigDecimal("1.01"), rounded(new BigDecimal(1.010)));
	}

	@Test
	public void testRoundedToNearestPennyAddPenny() throws Exception {
		assertEquals(new BigDecimal("1.01"), rounded(new BigDecimal(1.007)));
	}

	@Test
	public void testCalculateAppleDiscount() throws Exception {
		assertEquals(new BigDecimal(TEN_PENCE), calculateAppleDiscount(applePrice, appleDiscountPrice));
	}

	@Test
	public void testCalculateLoafDiscount_1_Quantity() throws Exception {
		assertEquals(new BigDecimal("0.00"), calculateLoafDiscountOnQuantity(1, LOAF_DISCOUNT_PRICE));
	}

	@Test
	public void testCalculateLoafDiscount_2_Quantity() throws Exception {
		assertEquals(new BigDecimal(FORTY_PENCE), calculateLoafDiscountOnQuantity(2, LOAF_DISCOUNT_PRICE));
	}

	@Test
	public void testCalculateLoafDiscount_3_Quantity() throws Exception {
		assertEquals(new BigDecimal(FORTY_PENCE), calculateLoafDiscountOnQuantity(3, LOAF_DISCOUNT_PRICE));
	}

	@Test
	public void testCalculateLoafDiscount_4_Quantity() throws Exception {
		assertEquals(new BigDecimal("0.80"), calculateLoafDiscountOnQuantity(4, LOAF_DISCOUNT_PRICE));
	}
}
