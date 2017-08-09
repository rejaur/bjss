package com.price.basket.bjss.helper;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = "classpath:application-context-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class BasketHelperTest {

	@Autowired
	BasketHelper basketHelper;

	private Hashtable<String, String> referenceData;

	@Before
	public void setUp() throws Exception {

		referenceData = new Hashtable<String, String>();
		referenceData.put("bread", "0.80");
		referenceData.put("milk", "1.30");
		referenceData.put("apples", "1.00");
		referenceData.put("soup", "0.65");
		referenceData.put("applediscountprice", "0.10");
		referenceData.put("loafdiscountprice", "0.40");

	}

	@Test
	public void testCalculateSubtotalHappyPath() throws Exception {

		List<String> listItems = new ArrayList<>();
		listItems.add("Apples");
		listItems.add("Milk");
		listItems.add("Bread");
		listItems.add("Soup");

		assertEquals(new BigDecimal("3.75"), basketHelper.calculateSubtotal(listItems, referenceData));
	}

	@Test
	public void testCalculateSubtotal_2_Apples() throws Exception {

		List<String> listItems = new ArrayList<>();
		listItems.add("Apples");
		listItems.add("Apples");
		listItems.add("Milk");
		listItems.add("Bread");
		listItems.add("Soup");

		assertEquals(new BigDecimal("4.75"), basketHelper.calculateSubtotal(listItems, referenceData));
	}

	@Test
	public void testCalculateAppleDiscountsWith_1_Apple() throws Exception {

		List<String> listItems = new ArrayList<>();
		listItems.add("Apples");
		listItems.add("Milk");
		listItems.add("Bread");
		listItems.add("Soup");

		assertEquals(new BigDecimal("0.10"), basketHelper.calculateAppleDiscounts(listItems, referenceData));
	}

	@Test
	public void testCalculateAppleDiscountsWith_2_Apple() throws Exception {

		List<String> listItems = new ArrayList<>();
		listItems.add("Apples");
		listItems.add("Apples");
		listItems.add("Milk");
		listItems.add("Bread");
		listItems.add("Soup");

		assertEquals(new BigDecimal("0.20"), basketHelper.calculateAppleDiscounts(listItems, referenceData));
	}

	@Test
	public void testCalculateLoafDiscountWithNoSoups() throws Exception {
		List<String> listItems = new ArrayList<>();
		listItems.add("Apples");
		listItems.add("Milk");
		listItems.add("Bread");

		assertEquals(new BigDecimal("0.00"), basketHelper.calculateLoafDiscount(listItems, referenceData));
	}

	@Test
	public void testCalculateLoafDiscountWithNoSoup() throws Exception {
		List<String> listItems = new ArrayList<>();
		listItems.add("Apples");
		listItems.add("Milk");
		listItems.add("Bread");

		assertEquals(new BigDecimal("0.00"), basketHelper.calculateLoafDiscount(listItems, referenceData));
	}

	@Test
	public void testCalculateLoafDiscountWithOneSoup() throws Exception {
		List<String> listItems = new ArrayList<>();
		listItems.add("Apples");
		listItems.add("Milk");
		listItems.add("Bread");
		listItems.add("Soup");

		assertEquals(new BigDecimal("0.00"), basketHelper.calculateLoafDiscount(listItems, referenceData));
	}

	@Test
	public void testCalculateLoafDiscountWithTwoSoup() throws Exception {
		List<String> listItems = new ArrayList<>();
		listItems.add("Apples");
		listItems.add("Milk");
		listItems.add("Bread");
		listItems.add("Soup");
		listItems.add("Soup");

		assertEquals(new BigDecimal("0.40"), basketHelper.calculateLoafDiscount(listItems, referenceData));
	}

	@Test
	public void testCalculateLoafDiscountWithThreeSoup() throws Exception {
		List<String> listItems = new ArrayList<>();
		listItems.add("Apples");
		listItems.add("Milk");
		listItems.add("Bread");
		listItems.add("Soup");
		listItems.add("Soup");
		listItems.add("Soup");

		assertEquals(new BigDecimal("0.40"), basketHelper.calculateLoafDiscount(listItems, referenceData));
	}

	@Test
	public void testCalculateLoafDiscountWithFourSoup() throws Exception {
		List<String> listItems = new ArrayList<>();
		listItems.add("Apples");
		listItems.add("Milk");
		listItems.add("Bread");
		listItems.add("Soup");
		listItems.add("Soup");
		listItems.add("Soup");
		listItems.add("Soup");

		assertEquals(new BigDecimal("0.80"), basketHelper.calculateLoafDiscount(listItems, referenceData));
	}

	@Test
	public void testGetPriceAfterAllOfferDiscountsHappyPath() throws Exception {
		
		final BigDecimal subTotal = new BigDecimal("20");
		final BigDecimal minusAppleDiscount = new BigDecimal("5");
		final BigDecimal minusLoafDiscount =  new BigDecimal("5");
		
		assertEquals(new BigDecimal("10"), basketHelper.getPriceAfterAllOfferDiscounts(subTotal, minusAppleDiscount, minusLoafDiscount));
	}
}
