package com.price.basket.bjss.validation;

import java.util.Hashtable;
import java.util.Map;

public interface ValidationRule {

	void validate(String[] dataInput, Map<String, String> dataReference);
}
