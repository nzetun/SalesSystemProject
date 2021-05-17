package person;

import java.util.ArrayList;
import java.util.List;

import com.mgg.Address;

import sale.Sale;

/**
 * Class that models a gold customer object.
 * 
 * @author nzetocha2 and jbargen3
 *
 */
public class GoldCustomer extends Customer {
	
	private double discount = 0.05;
	private List<Sale> purchase = new ArrayList<Sale>();

	@Override
	/**
	 * Getter method for staff type.
	 */
	public String getType() {
		return "GoldCustomer";
	}
	
	/**
	 * Gold customer constructor.
	 * @param personCode
	 * @param type
	 * @param lastName
	 * @param firstName
	 * @param address
	 * @param emails
	 */
	public GoldCustomer(String personCode, String type, String lastName, String firstName, Address address,
			List<String> emails) {
		super(personCode, type, lastName, firstName, address, emails);
	
	}
	
	@Override
	/**
	 * Getter method for returning gold customer discount.
	 */
	public double getDiscount() {
		return this.discount;
	}
	
	@Override
	/**
	 * Getter method for returning purchases by customer.
	 */
	public List<Sale> getPurchases() {
		return this.purchase;
	}
	
	@Override
	/**
	 * Method for adding a purchase made by a customer.
	 */
	public void addPurchase(Sale s) {
		purchase.add(s);
	}
	
}
