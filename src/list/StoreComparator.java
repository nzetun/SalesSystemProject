package list;

import java.util.Comparator;

import sale.Sale;

/**
 * Comparator to compare the stores in set of sales.
 * 
 * @author jbargen and nzetocha
 */
public class StoreComparator implements Comparator<Sale> {

	/**
	 * Method for collections of sales stores.
	 * 
	 * @param s
	 */
	@Override
	public int compare(Sale a, Sale b) {
		int z = b.getStore().getStoreCode().compareTo(a.getStore().getStoreCode());
		if(z == 0) {
			z = b.getSalesPerson().getLastName().compareTo(a.getSalesPerson().getLastName());
			if(z == 0) {
				z = b.getSalesPerson().getFirstName().compareTo(a.getSalesPerson().getFirstName());
				if(z == 0) {
					b.getSaleCode().compareTo(a.getSaleCode());
				}
			}
		}
		
		return z;
	}

	
}
