package person;

import java.util.ArrayList;
import java.util.List;

import com.mgg.Address;

import sale.Sale;

/**
 * Class that models a Platinum Customer object.
 * 
 * @author nzetocha2 and jbargen3
 *
 */
public class PlatinumCustomer extends Customer {
	
	private double discount = 0.10;
	private List<Sale> purchase = new ArrayList<Sale>();
	
	@Override
	/**
	 * Getter method for customer type.
	 */
	public String getType() {
		return "PlatinumCustomer";
	}
	
	/**
	 * Platinum customer constructor.
	 * @param personCode
	 * @param type
	 * @param lastName
	 * @param firstName
	 * @param address
	 * @param emails
	 */
	public PlatinumCustomer(String personCode, String type, String lastName, String firstName, Address address,
			List<String> emails) {
		super(personCode, type, lastName, firstName, address, emails);
		
	}
	
	@Override
	/**
	 * Getter method for returning platinum customer discount.
	 */
	public double getDiscount() {
		return discount;
	}
	
	@Override
	/**
	 * Getter method for returning purchases made by a customer.
	 */
	public List<Sale> getPurchases() {
		return this.purchase;
	}
	
	@Override
	/**
	 * Method for adding a purchase to a customer.
	 */
	public void addPurchase(Sale s) {
		purchase.add(s);
	}
	
}
