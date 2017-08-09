package com.price.basket.bjss.validation;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class ValidationFactory {

	public static void validateInputData(final String[] args,  Map<String, String> dataReference) {
		List<ValidationRule> rules = new ArrayList<>();

		rules.add(new NullValidationRule());

		rules.add(new DataValidationRule());
		
		//TODO: More validations to go here....

		for (ValidationRule rule : rules) {

			rule.validate(args, dataReference);
		}
	}
}
