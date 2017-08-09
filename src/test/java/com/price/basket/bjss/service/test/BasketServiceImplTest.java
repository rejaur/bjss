package com.price.basket.bjss.service.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.price.basket.bjss.service.BasketService;

@ContextConfiguration (locations="classpath:application-context-test.xml") 
@RunWith(SpringJUnit4ClassRunner.class)
public class BasketServiceImplTest {

	
	@Autowired
	private BasketService basketService;

	@Test
	public void testCreateBasket() {
		final String[] args = {"Apples", "Milk", "Bread", "Soup"}; 
		
		basketService.createBasket(args);
	}

	@Test
	public void testProcessPriceBasket() {
		final String[] args = {"Apples", "Milk", "Bread", "Soup"}; 
		
		basketService.processPriceBasket(args);
	}

}
