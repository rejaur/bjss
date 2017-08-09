package com.price.basket.bjss.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.price.basket.bjss.AppLauncher;
import com.price.basket.bjss.dao.ReferenceDataDAO;
import com.price.basket.bjss.helper.BasketHelper;
import com.price.basket.bjss.validation.ValidationFactory;

@Service
public class BasketServiceImpl implements BasketService {

	@Autowired
	private ReferenceDataDAO referenceDataDao;
	
	@Autowired
	private ValidationFactory validationFactory;

	@Autowired
	private BasketHelper basketHelper;
	
	private Hashtable<String, String> referenceData;

	final static Logger logger = Logger.getLogger(AppLauncher.class);

	private List<String> listItems;

	public void createBasket(String[] args) {
		
		referenceData = referenceDataDao.getItemPrices();
		
		validationFactory.validateInputData(args, referenceData);
		
		listItems = new ArrayList<String>(Arrays.asList(args));
	}

	public void processPriceBasket(String[] args) {
		createBasket(args);

		basketHelper.displayOuput(listItems, referenceData);
	}

}
