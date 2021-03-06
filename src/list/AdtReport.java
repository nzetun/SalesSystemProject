package list;

import java.util.Comparator;
import java.util.List;

import sale.Sale;

/**
 * ADT sale reporting class.
 * @author nzetocha and jbargen
 *
 */
public class AdtReport {
	
	/**
	 * Method for outputting a sorted report with a given
	 * comparator.
	 * 
	 * @param <T>
	 * @param sortType
	 */
	public static <T> AdtList<Sale> sortReport(List<Sale> sales, Comparator<Sale> cmp) {
		AdtList<Sale> sortedSales = new AdtList<Sale>(cmp);
		for(Sale s : sales) {
			sortedSales.addInOrder(s);
		}
		return sortedSales;
	}
	
	/**
	 * Method for printing out a sorted report.
	 */
	public static void printReport(AdtList<Sale> sales, String sortType) {
		Sale.basicToString(sales, sortType);
	}
	
	/**
	 * GUI Use: Method for printing out a sorted report as a string.
	 */
	public static String guiReport(AdtList<Sale> sales, String sortType) {
		return Sale.saleString(sales, sortType);
	}
	
	/**
	 * GUI use: Method for printing out a sale list.
	 */
	public static String salesReport(AdtList<Sale> sales) {
		return Sale.stringListSale(sales);
	}

}
