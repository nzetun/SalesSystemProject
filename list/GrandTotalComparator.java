package list;

import java.util.Comparator;

import sale.Sale;

/**
 * Comparator to compare the grand totals in set of sales.
 * 
 * @author jbargen and nzetocha
 */
public class GrandTotalComparator implements Comparator<Sale> {

	/**
	 * Method for collections of sales grand totals.
	 * 
	 * @param s
	 */
	@Override
	public int compare(Sale z, Sale y) {
		if(z.getGrandTotal() > y.getGrandTotal()) {
			return 1;
		} else if(z.getGrandTotal() < y.getGrandTotal()) {
			return -1;
		} else {
			return 0;
		}
	}
	
}
