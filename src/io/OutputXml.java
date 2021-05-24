package io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

import com.mgg.Store;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import item.Item;
import person.Person;

public class OutputXml {
	
	/**
	 * Method for outputting loaded list of stores into 
	 * xml file format in the data folder by using the
	 * xstream and marshal capabilities.
	 * 
	 * @param stores
	 */
	public static void outputXmlStore(List<Store> stores) {
		File f = new File("data/Stores.xml");
		XStream xstream = new XStream(new StaxDriver());
		try {
			PrintWriter pw = new PrintWriter(f);
			xstream.setMode(XStream.NO_REFERENCES);
			xstream.alias("stores", List.class);
			xstream.alias("Store", Store.class);
			xstream.aliasType("manager", Person.class);
			xstream.alias("email", String.class);
			xstream.marshal(stores, new PrettyPrintWriter(pw));
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Method for outputting loaded list of persons into 
	 * xml file format in the data folder by using the
	 * xstream and marshal capabilities.
	 * 
	 * @param persons
	 */
	public static void outputXmlPerson(List<Person> persons) {
		File f = new File("data/Persons.xml");
		XStream xstream = new XStream(new StaxDriver());
		try {
			PrintWriter pw = new PrintWriter(f);
			xstream.setMode(XStream.NO_REFERENCES);
			xstream.alias("persons", List.class);
			xstream.alias("PlatinumCustomer", person.PlatinumCustomer.class);
			xstream.alias("GoldCustomer", person.GoldCustomer.class);
			xstream.alias("Customer", person.RegCustomer.class);
			xstream.alias("Employee", person.Employee.class);
			xstream.alias("email", String.class);
			xstream.marshal(persons, new PrettyPrintWriter(pw));
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Method for outputting loaded list of items into 
	 * xml file format in the data folder by using the
	 * xstream and marshal capabilities.
	 * 
	 * @param items
	 */
	public static void outputXmlItem(List<Item> items) {
		File f = new File("data/Items.xml");
		XStream xstream = new XStream(new StaxDriver());
		try {
			PrintWriter pw = new PrintWriter(f);
			xstream.alias("items", List.class);
			xstream.alias("GiftCard", item.GiftCard.class);
			xstream.alias("NewProduct", item.NewProduct.class);
			xstream.alias("Service", item.Service.class);
			xstream.alias("Subscription", item.Subscription.class);
			xstream.alias("UsedProduct", item.UsedProduct.class);
			xstream.marshal(items, new PrettyPrintWriter(pw));
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
}
