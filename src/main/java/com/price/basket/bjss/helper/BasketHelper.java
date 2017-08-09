package com.price.basket.bjss.helper;

import static com.price.basket.bjss.utils.BasketUtils.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import com.price.basket.bjss.enums.Item;

@Component
public final class BasketHelper {

	public BigDecimal calculateSubtotal(final List<String> listItems, final Hashtable<String, String> referenceData) {

		List<BigDecimal> listPrice = new ArrayList<>();

		listItems.forEach(item -> {
			listPrice.add(referenceData.containsKey(item.toLowerCase())
					? new BigDecimal(referenceData.get(item.toLowerCase())) : new BigDecimal("0.00"));
		});

		return getPriceFromList(listPrice);
	}

	public BigDecimal calculateAppleDiscounts(final List<String> listItems,
			final Hashtable<String, String> referenceData) {

		List<BigDecimal> listPrice = new ArrayList<>();

		listItems.forEach(item -> {

			listPrice.add(item.equalsIgnoreCase(Item.APPLES.getValue())
					? calculateAppleDiscount(new BigDecimal(referenceData.get(Item.APPLES.getValue())),
							new BigDecimal(referenceData.get(Item.APPLES_DISCOUNT_PRICE.getValue())))
					: new BigDecimal("0"));
		});

		return getPriceFromList(listPrice);
	}

	public BigDecimal calculateLoafDiscount(final List<String> listItems,
			final Hashtable<String, String> referenceData) {

		List<BigDecimal> listPrice = new ArrayList<>();

		final Predicate<String> predicate = s -> s.equalsIgnoreCase(Item.SOUP.getValue());
		long quantity = listItems.stream().filter(predicate).count();

		listPrice.add(quantity >= 2
				? calculateLoafDiscountOnQuantity(quantity, referenceData.get(Item.LOAF_DISCOUNT_PRICE.getValue()))
				: new BigDecimal("0.00"));

		return getPriceFromList(listPrice);
	}

	public BigDecimal getPriceAfterAllOfferDiscounts(final BigDecimal subTotal, final BigDecimal appleDiscount,
			BigDecimal loafDiscount) {
		BigDecimal priceAfterAppleDiscount = subTotal.subtract(appleDiscount);
		BigDecimal resultAllDiscounts = priceAfterAppleDiscount.subtract(loafDiscount);
		return resultAllDiscounts;
	}

	public void displayOuput(final List<String> listItems, final Hashtable<String, String> referenceData) {
		final BigDecimal subTotal = calculateSubtotal(listItems, referenceData);

		final BigDecimal appleDiscounts = calculateAppleDiscounts(listItems, referenceData);

		final boolean breadExists = listItems.stream().anyMatch(s -> s.equalsIgnoreCase(Item.BREAD.getValue()));

		BigDecimal loafDiscount = breadExists == true ? calculateLoafDiscount(listItems, referenceData)
				: new BigDecimal("0.0");

		final BigDecimal totalAfterAllDiscountOffers = getPriceAfterAllOfferDiscounts(subTotal, appleDiscounts,
				loafDiscount);

		displayOnCMDLine(subTotal, appleDiscounts, loafDiscount, totalAfterAllDiscountOffers);
	}

	private void displayOnCMDLine(final BigDecimal subTotal, final BigDecimal appleDiscountPrice,
			BigDecimal loafDiscountPrice, BigDecimal totalAfterAllDiscountOffers) {

		System.out.println("Subtotal: £" + subTotal);

		displayOffer(appleDiscountPrice, loafDiscountPrice);

		System.out.println("Total: £" + totalAfterAllDiscountOffers);
	}

	private void displayOffer(final BigDecimal appleDiscountPrice, final BigDecimal loafDiscountPrice) {
		if (appleDiscountPrice.compareTo(BigDecimal.ZERO) > 0 || loafDiscountPrice.compareTo(BigDecimal.ZERO) > 0) {

			if (appleDiscountPrice.compareTo(BigDecimal.ZERO) > 0) {
				System.out.println("Apples 10% off: -" + appleDiscountPrice + "p");
			}

			if (loafDiscountPrice.compareTo(BigDecimal.ZERO) > 0) {
				System.out.println("Bread 50% off: -" + loafDiscountPrice + "p");
			}
		} else {
			System.out.println("(No offers available)");
		}
	}
}
