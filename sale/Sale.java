package sale;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import com.mgg.Store;

import item.Item;
import list.AdtList;
import person.Employee;
import person.Person;

/**
 * Class for modeling a sale object.
 * 
 * @author nzetocha2 and jbargen3
 *
 */
public class Sale implements Comparable<Sale> {
	
	private String saleCode;
	private Store store;
	private Person customer;
	private Employee salesPerson;
	private List<Item> salesItem;
	
	/**
	 * Getter method for returning sale code specific to 
	 * sale.
	 * @return
	 */
	public String getSaleCode() {
		return saleCode;
	}
	
	/**
	 * Getter method for returning store object associated
	 * with sale.
	 * @return
	 */
	public Store getStore() {
		return store;
	}
	
	/**
	 * Getter method for returning customer object associated
	 * with sale.
	 * @return
	 */
	public Person getCustomer() {
		return customer;
	}
	
	/**
	 * Getter method for returning sales person object associated
	 * with sale.
	 * @return
	 */
	public Employee getSalesPerson() {
		return this.salesPerson;
	}

	/**
	 * Getter method for returning item list associated with sale.
	 * @return
	 */
	public List<Item> getSalesItem() {
		return this.salesItem;
	}
	
	/**
	 * Sale constructor.
	 * @param saleCode
	 * @param storeCode
	 * @param customer
	 * @param seller
	 * @param salesItem
	 */
	public Sale(String saleCode, Store storeCode, Person customer, Employee seller, List<Item> salesItem) {
		super();
		this.saleCode = saleCode;
		this.store = storeCode;
		this.customer = customer;
		this.salesPerson = seller;
		this.salesItem = salesItem;
	}
	
	/**
	 * Returns the sale grand total.
	 */
	public double getGrandTotal() {
		double subTotal = 0.0; 
		double tax = 0.0;
		double grandTotal = 0.0; 
		double discount = 0.0;
		double taxAmount = 0.0;
		for(Item listItem : this.salesItem) {
			subTotal += listItem.getPreTotal();
			tax = (listItem.getTax() * listItem.getPreTotal());
			tax = Math.round(tax * 100.0) / 100.0;
			taxAmount += tax;
		}
		discount = (subTotal + taxAmount) * (this.getCustomer().getDiscount());
		discount = Math.round(discount * 100.0) / 100.0;
		grandTotal = (subTotal + taxAmount) - discount;
		
		return grandTotal;
	}
	
	/**
	 * Complete the implementation of this method that will be used for sorting
	 * collections of sales by their grand totals.
	 * 
	 * @param s
	 */
	public int compareTo(Sale s) {
		Double a = s.getGrandTotal();
		Double b = this.getGrandTotal();
		int c = a.compareTo(b);
		return c;
	}
	
	@Override
	/**
	 * To string method for outputting sale object as a string.
	 * @return
	 */
	public String toString() {
		return "Sale  #" + saleCode + 
				"\nStore #" + store + 
				"\nCustomer:\n" + customer.toString() + 
				"\n\nSales Person:\n" + salesPerson.toString() + 
				"\n\nItem                                                               Total\r\n" + 
				"-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-                          -=-=-=-=-=-";
	}
	
	/**
	 * Method to print out only basic sale info.
	 */
	public static void basicToString(AdtList<Sale> sales, String sortType) {
		System.out.println("+-----------------------------------------------------------------------------+");
		System.out.printf("| Sales by %s                        			              |\n", sortType);
		System.out.println("+-----------------------------------------------------------------------------+");
		System.out.println("Sale         Store        Customer             Salesperson                Total");
		for(Sale s : sales) {
			System.out.printf("%-12s %-12s %-20s %-20s $ %9.2f\n", 
					s.getSaleCode(), s.getStore().getStoreCode(), s.getCustomer().personNameToString(), s.getSalesPerson().personNameToString(), s.getGrandTotal());
		}
		
		return;
	}
	
	/**
	 * Method for returning a list report of all sales made
	 * based on the sales data and output to the standard
	 * output.
	 * @param sales
	 */
	public static void listReport(List<Sale> sales) {
		
		Collections.sort(sales, new Comparator<Sale>() {
			@Override
			public int compare(Sale a, Sale b) {
				int nameCompare = a.getCustomer().getLastName().compareTo(b.getCustomer().getLastName());
				return nameCompare;
			}
		});
		
		for(Sale s : sales) {
			System.out.println(s.toString());
			List<Item> salesItem = s.getSalesItem();
			Person p = s.getCustomer();
			
			double subTotal = 0.0; 
			double tax = 0.0;
			double grandTotal = 0.0; 
			double discount = 0.0;
			double taxAmount = 0.0;
			for(Item listItem : salesItem) {
				System.out.printf("%s%-60s", listItem.nameToString(), listItem.saleInfoToString()); 
				System.out.printf(" $ %9.2f\n", listItem.getPreTotal());
				subTotal += listItem.getPreTotal();
				tax = (listItem.getTax() * listItem.getPreTotal());
				tax = Math.round(tax * 100.0) / 100.0;
				taxAmount += tax;
			}
			discount = (subTotal + taxAmount) * (p.getDiscount());
			discount = Math.round(discount * 100.0) / 100.0;
			grandTotal = (subTotal + taxAmount) - discount;
			
			System.out.printf("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-                          -=-=-=-=-=-\n");
			System.out.printf("%60s $ %9.2f\n", "Subtotal", subTotal);
			System.out.printf("%60s $ %9.2f\n", "Tax", taxAmount);
			if(p.getDiscount() == .15) {
				System.out.printf("%51s (%.2f%%) $ %9.2f\n", "Discount", (p.getDiscount() * 100), discount);
			}else if (p.getDiscount() == .10) {
				System.out.printf("%51s (%.2f%%) $ %9.2f\n", "Discount", (p.getDiscount() * 100), discount);
			} else if (p.getDiscount() == .05) {
				System.out.printf("%52s (%.2f%%) $ %9.2f\n", "Discount", (p.getDiscount() * 100), discount);
			} else if (p.getDiscount() == 0.0) {
				//Do not print discount
			}
			System.out.printf("%60s $ %9.2f\n", "Grand Total", grandTotal);
		}
		
		
		
	}

	
}
