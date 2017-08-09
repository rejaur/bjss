package com.price.basket.bjss;

import static com.price.basket.bjss.utils.BasketUtils.*;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.price.basket.bjss.service.BasketService;

@Component
public class AppLauncher {

	final static Logger logger = Logger.getLogger(AppLauncher.class);

	public static void main(String[] args) {
		
		boolean isReadyToRun = (args == null || args.length == 0)? printUsage(): true; 

		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application-context.xml");

		BasketService appLauncher = applicationContext.getBean(BasketService.class);
		appLauncher.processPriceBasket(args);
	}
}
