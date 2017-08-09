package com.price.basket.bjss.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;
import java.util.Properties;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public class ReferenceDataImpl implements ReferenceDataDAO {

	public Hashtable<String, String> getItemPrices() {

		Properties prop = new Properties();
		InputStream input = null;

		try {

			final String filename = "data.properties";
			input = ReferenceDataImpl.class.getClassLoader().getResourceAsStream(filename);
			if (input == null) {
				System.out.println("Sorry, unable to find " + filename);
				throw new FileNotFoundException();
			}

			// load a properties file
			prop.load(input);

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			closeConnection(input);
		}

		return (Hashtable) prop;
	}

	private void closeConnection(InputStream input) {
		if (input != null) {
			try {
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
