package io;


import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.mgg.Address;
import com.mgg.Store;

import item.GiftCard;
import item.GiftCardSale;
import item.Item;
import item.NewProduct;
import item.NewProductSale;
import item.Service;
import item.ServiceSale;
import item.Subscription;
import item.SubscriptionSale;
import item.UsedProduct;
import item.UsedProductSale;
import person.Customer;
import person.Employee;
import person.Person;
import sale.Sale;


/**
 * Class for parsing csv data.
 * @author nzetocha2 and jbargen 
 */
public class Parser {
	
	/**
	 * Method for loading a file containing csv data from a given
	 * filename, and outputting it into a new List of items.
	 * 
	 * @param fileName
	 * @return
	 */
	public static List<Item> loadItemsData(String fileName) {
		
		List<Item> myList = new ArrayList<>();
		Scanner s = null;
    	try {
			s = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
    	String line = s.nextLine();
		String token[] = line.split(",");
    	int total = Integer.parseInt(token[0]);
    	for(int i = 0; i < total; i++) {
    		double price = 0.0;
    		line = s.nextLine();
    		String tokens[] = line.split(",");
    		String code = tokens[0];
    		String type = tokens[1];
    		String name = tokens[2];
    		if(!type.equals("PG")) {
    			price = Double.parseDouble(tokens[3]);
    		}
    		Item b = null;
    		if(type.contentEquals("PN")) {
    			b = new NewProduct(code, name, price);
    		} else if (type.contentEquals("PU")) {
    			b = new UsedProduct(code, name, price);
    		} else if (type.contentEquals("SV")) {
    			b = new Service(code, name, price);
    		} else if (type.contentEquals("SB")) {
    			b = new Subscription(code, name, price);
    		} else {
    			b = new GiftCard(code, name);
    		}
    		myList.add(b);
    	}
    	
    	return myList;
    	
	}
	
	/**
	 * Method for loading a file containing csv data from a given
	 * filename, and outputting it into a new List of Persons.
	 * 
	 * @param fileName
	 * @return
	 */
	public static List<Person> loadPersonsData(String fileName) {
		
		List<Person> myList = new ArrayList<>();
		Scanner s = null;
    	try {
			s = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
    	String line = s.nextLine();
		String token[] = line.split(",");
    	int total = Integer.parseInt(token[0]);
    	for(int i = 0; i < total; i++) {
    		line = s.nextLine();
    		String tokens[] = line.split(",");
    		String personCode = tokens[0];
    		String type = tokens[1];
    		String lastName = tokens[2];
    		String firstName = tokens[3];
    		String street = tokens[4];
    		String city = tokens[5];
    		String state = tokens[6];
    		String country = tokens[8];
    		String zip = tokens[7];
    		int j = 9;
    		List<String> emails = new ArrayList<String>();
    		if(j < tokens.length) {
    			emails.add(tokens[j]);
    			j++;
    			while(j  < tokens.length) {
    				emails.add(tokens[j]);
    				j++;
    			}
    		}
    		Address address = new Address(street, city, state, country, zip);
    		Person p = null;
    		if(type.equals("C")) {
    			p = new person.RegCustomer(personCode, type, lastName, 
    					firstName, address, emails);
    		} else if(type.equals("G")) {
    			p = new person.GoldCustomer(personCode, type, lastName, 
						firstName, address, emails);
    		} else if(type.equals("P")) {
    			p = new person.PlatinumCustomer(personCode, type, lastName, 
						firstName, address, emails);
    		} else if(type.equals("E")) {
    			p = new person.Employee(personCode, type, lastName, 
						firstName, address, emails);
    		}
    		myList.add(p);
    	}
    	
    	return myList;
	}
	
	/**
	 * Method for loading a file containing csv data from a given
	 * filename, and outputting it into a new List of stores.
	 * 
	 * @param fileName
	 * @return
	 */
	public static List<com.mgg.Store> loadStoresData(String fileName) {
		
		List<com.mgg.Store> myList = new ArrayList<>();
		Scanner s = null;
    	try {
			s = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
    	
    	List<Person> persons = Parser.loadPersonsData("data/Persons.csv");
    	
    	String line = s.nextLine();
		String token[] = line.split(",");
    	int total = Integer.parseInt(token[0]);
    	for(int i = 0; i < total; i++) {
    		line = s.nextLine();
    		String tokens[] = line.split(",");
    		String storeCode = tokens[0];
    		String managerCode = tokens[1];
    		Person p = null;
    		for (Person p1 : persons) {
	    		if(managerCode.equals(p1.getPersonCode())) {
	    			p = p1;
	    		}
    		}
    		String street = tokens[2];
    		String city = tokens[3];
    		String state = tokens[4];
    		String country = tokens[6];
    		String zip = tokens[5];
    		
    		Address address = new Address(street, city, state, country, zip);
    		Store store = new Store(storeCode, p, address);
    		myList.add(store);
    	}
    	
    	return myList;
	
	}
	
	/**
	 * Method for loading a file containing csv data from a given filename, and
	 * outputting it into a new List of sales items.
	 * 
	 * @param fileName
	 * @return
	 * @throws ParseException
	 */
	public static List<Sale> loadSalesData(String fileName) {

		List<Sale> myList = new ArrayList<>();
		Scanner s = null;
		try {
			s = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}

		List<Person> persons = Parser.loadPersonsData("data/Persons.csv");
		List<Item> items = Parser.loadItemsData("data/Items.csv");
		List<Store> stores = Parser.loadStoresData("data/Stores.csv");
		
		String line = s.nextLine();
		String token[] = line.split(",");
		int total = Integer.parseInt(token[0]);
		
		for (int i = 0; i < total; i++) {
			List<Item> itemList = new ArrayList<>();
			line = s.nextLine();
			String tokens[] = line.split(",");
			String saleCode = tokens[0];
			String storeCode = tokens[1];

			Store store = null;
			for (Store s1 : stores) {
				if (storeCode.equals(s1.getStoreCode())) {
					store = s1;
				}
			}
			String customerCode = tokens[2];
			Customer customer = null;
			for (Person c1 : persons) {
				if (customerCode.equals(c1.getPersonCode())) {
					customer = (Customer) c1;
				}
			}
			String salesPersonCode = tokens[3];
			Employee seller = null;
			for (Person sell1 : persons) {
				if (salesPersonCode.equals(sell1.getPersonCode())) {
					seller = (Employee) sell1;
				}
			}
			
			int j = 4;
			while (j < tokens.length) {
				String itemCode = tokens[j];
				for (Item it1 : items) {
					if (itemCode.equals(it1.getCode())) {
						if (it1.getType().equals("NewProduct")) {
							int quantity = Integer.parseInt(tokens[j + 1]);
							NewProductSale item = new NewProductSale(it1.getCode(), it1.getName(), it1.getPrice(), quantity);
							itemList.add(item);
							j += 2;
						} else if (it1.getType().equals("UsedProduct")) {
							int quantity = Integer.parseInt(tokens[j + 1]);
							UsedProductSale item = new UsedProductSale(it1.getCode(), it1.getName(), it1.getPrice(), quantity);
							itemList.add(item);
							j += 2;
						} else if (it1.getType().equals("GiftCard")) {
							double giftAmount = Double.parseDouble(tokens[j + 1]);
							GiftCardSale item = new GiftCardSale(it1.getCode(), it1.getName(), giftAmount);
							itemList.add(item);
							j += 2;
						} else if (it1.getType().equals("Service")) {
							String servicerCode = tokens[j + 1];
							Person servicer1 = null;
							for (Person service : persons) {
								if (servicerCode.equals(service.getPersonCode())) {
									servicer1 = service;
								}
							}
							double numHours = Double.parseDouble(tokens[j + 2]);
							ServiceSale item = new ServiceSale(it1.getCode(), it1.getName(), it1.getPrice(), numHours, servicer1);
							itemList.add(item);
							j += 3;
						} else if (it1.getType().equals("Subscription")) {
							LocalDate beginDate = LocalDate.parse(tokens[j + 1]);
							LocalDate endDate = LocalDate.parse(tokens[j + 2]);
							SubscriptionSale item = new SubscriptionSale(it1.getCode(), it1.getName(), it1.getPrice(), beginDate, endDate);
							itemList.add(item);
							j += 3;
						}
					}
				}
			}

			Sale sale = new Sale(saleCode, store, customer, seller, itemList);
			myList.add(sale);
		}

		return myList;

	}

}

