package com.price.basket.bjss.dao.test;

import static org.junit.Assert.*;

import java.util.Hashtable;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.price.basket.bjss.dao.ReferenceDataDAO;

@ContextConfiguration(locations = "classpath:application-context-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class ReferenceDataDAOTest {

	@Autowired
	private ReferenceDataDAO referenceDataDao;
	
	private Hashtable<String, String> itemPrices;
	
	@Before
	public void setUp() throws Exception {
				
		itemPrices = new Hashtable<String, String>();
		itemPrices.put("bread","0.80");
		itemPrices.put("milk", "1.30");
		itemPrices.put("apples", "1.00");
		itemPrices.put("soup","0.65");
		itemPrices.put("applediscountprice", "0.10");
		itemPrices.put("loafdiscountprice","0.40");
	}

	@Test
	public void testGetItemPrices() throws Exception {
		
		assertEquals(itemPrices, referenceDataDao.getItemPrices());
		
	}

}
