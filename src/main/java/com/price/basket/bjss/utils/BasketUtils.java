package com.price.basket.bjss.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public final class BasketUtils {

	private final static int DECIMALS_2 = 2;
	private final static int ROUNDING_MODE = BigDecimal.ROUND_HALF_EVEN;

	public static BigDecimal getPriceFromList(final List<BigDecimal> listPrice) {
		return rounded(listPrice.stream().reduce(BigDecimal.ZERO, BigDecimal::add));
	}

	public static BigDecimal rounded(final BigDecimal aNumber) {
		return aNumber.setScale(DECIMALS_2, ROUNDING_MODE);
	}

	public static BigDecimal calculateAppleDiscount(final BigDecimal applePrice, final BigDecimal discount) {
		return rounded(applePrice.multiply(discount));
	}

	public static BigDecimal calculateLoafDiscountOnQuantity(final long quantity, final String loafDiscountPrice) {
		final BigDecimal countBD = new BigDecimal(quantity);

		final BigDecimal divideResult = countBD.divide(new BigDecimal(2), 0, RoundingMode.FLOOR);

		final BigDecimal discountOnLoaf = new BigDecimal(loafDiscountPrice);

		return divideResult.multiply(discountOnLoaf);
	}

	public static void displayOnCMDLine(final BigDecimal subTotal, final BigDecimal appleDiscountPrice,
			final BigDecimal loafDiscountPrice, final BigDecimal totalAfterAllDiscountOffers) {

		System.out.println("Subtotal: £" + subTotal);

		displayOffer(appleDiscountPrice, loafDiscountPrice);

		System.out.println("Total: £" + totalAfterAllDiscountOffers);
	}

	private static void displayOffer(final BigDecimal appleDiscountPrice, final BigDecimal loafDiscountPrice) {
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

	public static boolean printUsage() {
		System.out.println("Usage: java PriceBasket [items]\n" + "eg. java PriceBasket Apple Milk Bread");
		System.exit(0);
		return false;
	}
}
