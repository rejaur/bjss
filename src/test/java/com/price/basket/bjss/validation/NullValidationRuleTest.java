package com.price.basket.bjss.validation;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.price.basket.bjss.dao.ReferenceDataDAO;
import com.price.basket.bjss.exception.ValidationException;

@ContextConfiguration(locations="classpath:application-context-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class NullValidationRuleTest {

	@Autowired
	private NullValidationRule nullValidationRule;
	
	@Autowired 
	private ReferenceDataDAO referenceDataDao;

	@Test (expected = ValidationException.class) 
	public void testValidateWithNull() {
		
		String[] dataInput = {null, "Apples"};
		nullValidationRule.validate(dataInput, referenceDataDao.getItemPrices());
	}
	
	@Test (expected = ValidationException.class) 
	public void testValidate() {
		
		String[] dataInput = {"", "Apples"};
		nullValidationRule.validate(dataInput, referenceDataDao.getItemPrices());
	}
	
	@Test  
	public void testValidateCorrectItems() {
		
		String[] dataInput = {"Bread", "Apples"};
		nullValidationRule.validate(dataInput, referenceDataDao.getItemPrices());
	}
}
