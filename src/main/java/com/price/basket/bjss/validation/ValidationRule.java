package com.price.basket.bjss.validation;

import java.util.Hashtable;

public interface ValidationRule {

	void validate(String[] dataInput, Hashtable<String, String> dataReference);
}
