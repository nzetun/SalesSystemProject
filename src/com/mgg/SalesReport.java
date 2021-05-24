package com.mgg;

import java.util.List;

import io.DBParser;
import list.AdtList;
import list.AdtReport;
import list.CustomerComparator;
import list.GrandTotalComparator;
import list.StoreComparator;
import sale.Sale;

/**
 * This program loads database data for the Items, Persons and Stores
 * classes and uses the resulting list to output the data to 
 * the standard output.
 * 
 * @author nzetocha2 and jbargen3
 *
 */
public class SalesReport {
	
	public static void main(String args[]) {
		
		List<Sale> sales = DBParser.loadSalesDatabase();
		
//		Employee.employeeSummaryReport(sales);
//		Store.storeSummaryReport(sales);
//		Sale.listReport(sales);
		
		AdtList<Sale> customerSort = AdtReport.sortReport(sales, new CustomerComparator());
		AdtList<Sale> totalSort = AdtReport.sortReport(sales, new GrandTotalComparator());
		AdtList<Sale> storeSort = AdtReport.sortReport(sales, new StoreComparator());
		AdtReport.printReport(customerSort, "Customer");
		AdtReport.printReport(totalSort, "Total");
		AdtReport.printReport(storeSort, "Store");
		
	}
}