package list;

import java.util.Comparator;

import sale.Sale;

/**
 * Comparator to compare the customers in set of sales.
 * 
 * @author jbargen and nzetocha
 */
public class CustomerComparator implements Comparator<Sale>{
		
	/**
	 * Method for collection of sales customers.
	 * 
	 * @param p
	 */
	@Override
	public int compare(Sale a, Sale b) {
		int z = b.getCustomer().getLastName().compareTo(a.getCustomer().getLastName());
		if(z == 0) {
			z = b.getCustomer().getFirstName().compareTo(a.getCustomer().getFirstName());
			if(z == 0) {
				z = b.getSaleCode().compareTo(a.getSaleCode());
			}
		}
		return z;
	}
	
}
