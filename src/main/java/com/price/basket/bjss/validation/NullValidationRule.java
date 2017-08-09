package com.price.basket.bjss.validation;

import java.util.Hashtable;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.google.common.base.Strings;
import com.price.basket.bjss.exception.ValidationException;

@Component
public class NullValidationRule implements ValidationRule {

	@Override
	public void validate(final String[] dataInput,  Map<String, String> referenceData) {
		
		for(String data : dataInput){
			
			if(Strings.isNullOrEmpty(data)) {
				throw new ValidationException("The data input is incorrect. Please check the input data, again!");
			}
		}	
	}
}
