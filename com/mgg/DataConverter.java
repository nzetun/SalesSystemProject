package com.mgg;

import java.util.List;

import io.OutputXml;
import io.Parser;
import item.Item;
import person.Person;

/**
 * This program loads csv data for the Items, Persons and Stores
 * classes and uses the resulting list to output the data to 
 * xml files.
 * 
 * @author nzetocha2 and jbargen3
 *
 */
public class DataConverter {
	
	public static void main(String args[]) {
		
		List<Item> items = Parser.loadItemsData("data/Items.csv");
		List<Person> persons = Parser.loadPersonsData("data/Persons.csv");
		List<Store> stores = Parser.loadStoresData("data/Stores.csv");
		
		OutputXml.outputXmlPerson(persons);
		OutputXml.outputXmlStore(stores);
		OutputXml.outputXmlItem(items);
		
	}
	
}