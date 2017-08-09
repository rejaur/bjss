package com.price.basket.bjss.validation;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import com.price.basket.bjss.exception.ValidationException;

@Component
public class DataValidationRule implements ValidationRule {

	@SuppressWarnings("rawtypes")
	@Override
	public void validate(final String[] dataInput, Map<String, String> referenceData) {

		final List<String> dataInputArray = Arrays.asList(dataInput);  
		
		final List<String> dataInputLowerCase = dataInputArray.stream().map(String::toLowerCase).collect(Collectors.toList());
		List<String> matchedOnes = new ArrayList<>();

		final Set<String> keySet = referenceData.keySet();
		for (String key : keySet) {
			for (String data : dataInput) {
				if (key.equalsIgnoreCase(data)) {
					matchedOnes.add(key.toLowerCase());
				}
			}
		}

		dataInputLowerCase.removeAll(matchedOnes);

		if (!dataInputLowerCase.isEmpty()) {
			throw new ValidationException("The inputdata is inconsistent with Reference Date...");
		}
	}
}
