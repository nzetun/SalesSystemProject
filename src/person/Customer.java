package person;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.mgg.Address;

import io.DBParser;
import sale.Sale;

/**
 * Class that models an intermediate Customer object.
 * 
 * @author nzetocha2 and jbargen3
 *
 */
public abstract class Customer extends Person {
	
	private double discount = 0.0;
	
	/**
	 * Customer constructor.
	 * @param personCode
	 * @param type
	 * @param lastName
	 * @param firstName
	 * @param address
	 * @param email
	 */
	public Customer(String personCode, String type, String lastName, 
				   String firstName, Address address, List<String> emails) {
		
		super(personCode, type, lastName, firstName, address, emails);
	
	}
	
	/**
	 * Method for getting the specified discount for intermediate customer
	 * class.
	 */
	public double getDiscount() {
		return this.discount;
	}

	/**
	 * Abstract method for returning purchases by customer.
	 */
	public abstract List<Sale> getPurchases();
	
	/**
	 * Abstract method for adding a purchase to a customer.
	 */
	public abstract void addPurchase(Sale s);
	
	/**
	 * Method for returning a sales amount for each customer based on
	 * sales data.
	 * @param sales
	 */
	public static void customerPurchases(List<Sale> sales) {
		
		List<Person> people = DBParser.loadPersonDatabase();
		List<Customer> customers = new ArrayList<>();
		
		for(Person person : people) {
			if(person.getType().contains("GoldCustomer") || person.getType().contains("PlatinumCustomer")
			   || person.getType().contains("RegCustomer")) {
				customers.add((Customer) person);
			}
		}
		
		Collections.sort(customers, new Comparator<Person>() {
			@Override
			public int compare(Person a, Person b) {
				int nameCompare = a.getLastName().compareTo(b.getLastName());
				return nameCompare;
			}
		});
		
		for(Customer buyer : customers) {
			for(Sale newSale : sales) {
				Person newBuyer = newSale.getCustomer();
				
				if(buyer.getPersonCode().equals(newBuyer.getPersonCode())) {
					buyer.addPurchase(newSale);
				}
				
			}
			System.out.println(buyer.personNameToString());
			List<Sale> buyerSales = buyer.getPurchases();
			for(Sale s : buyerSales) {
				System.out.println(s.getSaleCode());
			}
		}
		
	
	}	
}
