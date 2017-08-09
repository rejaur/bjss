package com.price.basket.bjss.validation;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.price.basket.bjss.dao.ReferenceDataDAO;
import com.price.basket.bjss.exception.ValidationException;

@ContextConfiguration(locations = "classpath:application-context-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class DataValidationRuleTest {

	@Autowired
	private DataValidationRule dataValidationRule;
	
	@Autowired
	private ReferenceDataDAO referenceData;

	@Test
	public void testValidateHappyPath() {
		String[] args = {"Apples", "Milk", "Bread", "Soup" };

		dataValidationRule.validate(args, referenceData.getItemPrices());
	}

	@Test (expected = ValidationException.class) 
	public void testValidateInconsistData() {
		String[] args = { "Apples", "Milk", "Bread", "Soup", "Butter" };

		dataValidationRule.validate(args, referenceData.getItemPrices());
	}

}
