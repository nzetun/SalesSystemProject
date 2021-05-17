package com.mgg;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import io.DBParser;
import person.Person;
import sale.Sale;

/**
 * Class that models a stores store object and provides
 * getter and file I/O functionality.
 * 
 * @author nzetocha2 and jbargen3
 *
 */
public class Store implements Comparable<Store> {
	
	private String storeCode;
	private Person manager;
	private Address address;
	private List<Sale> sales = new ArrayList<Sale>();
	private double storeTotal;
	
	
	/**
	 * Getter method for a store code.
	 * @return
	 */
	public String getStoreCode() {
		return this.storeCode;
	}
	
	/**
	 * Getter method for a stores manager code.
	 * @return
	 */
	public Person getManager() {
		return this.manager;
	}
	
	/**
	 * Getter method for a store address.
	 * @return
	 */
	public Address getAddress() {
		return this.address;
	}
	
	/**
	 * Stores constructor.
	 * @param storeCode
	 * @param p
	 * @param address
	 */
	public Store(String storeCode, Person person, Address address) {
		super();
		this.storeCode = storeCode;
		this.manager = person;
		this.address = address;
	}
	
	/**
	 *  Method to return formatted string of stores code
	 *  manager code and address.
	 */
	@Override
	public String toString() {
		return this.storeCode;
	}
	
	/**
	 * Getter method for what sales store made.
	 */
	public List<Sale> getSales() {
		return this.sales;
	}
	
	/**
	 * Method for adding a sale to a store.
	 */
	public void addSale(Sale s) {
		sales.add(s);
	}
	
	/**
	 * Method for adding a sale total to a store.
	 */
	public void addStoreTotal(double total) {
		storeTotal = total;
	}

	/**
	 * Method for keeping sales for all stores.
	 * 
	 * @param sales
	 */
	public static List<Store> storeSales(List<Sale> sales) {
		List<Store> stores = DBParser.loadStoresDatabase();
		
		for (Store store : stores) {
			double total = 0.0;
			for (Sale sale1 : sales) {
				Store newStore = sale1.getStore();
				String storeCode = newStore.getStoreCode();

				if (storeCode.equals(store.getStoreCode())) {
					total += sale1.getGrandTotal();
					store.addSale(sale1);
					
				}
			}
			store.addStoreTotal(total);
		}
		
		Collections.sort(stores, new Comparator<Store>() {
			@Override
			public int compare(Store a, Store b) {
				return Double.compare(b.storeTotal, a.storeTotal);
			}
		});
		
		return stores;
	}
	
	/**
	 * Complete the implementation of this method that will be used for sorting
	 * collections of stores.
	 * 
	 * @param s
	 */
	public int compareTo(Store s) {
		int a = s.getStoreCode().compareTo(this.getStoreCode());
		if(a == 0) {
			a = s.getManager().getLastName().compareTo(this.getManager().getLastName());
			if(a == 0) {
				a = s.getManager().getFirstName().compareTo(this.getManager().getFirstName());
			}
		}
		return a;
	}
	
	/**
	 * Method for returning sales that are made by store and output to the 
	 * standard output.
	 * @param stores
	 */
	public static void storeSummaryReport(List<Sale> sales) {
		System.out.println("+----------------------------------------------------------------+");
		System.out.println("| Store Sales Summary Report                                     |");
		System.out.println("+----------------------------------------------------------------+");
		System.out.println("Store      Manager                        # Sales    Grand Total");
		
		List<Store> stores = storeSales(sales);
		
		double netTotal = 0.0;
		for (Store s : stores) {
			int numSales = 0;
			double grandTotal = 0.0;
			Person p = s.getManager();
			List<Sale> storeSaleList = s.getSales();
			for (Sale sale1 : storeSaleList) {
				numSales++;
				grandTotal += sale1.getGrandTotal();
			}
			if (numSales == 0) {
				grandTotal = 0.00;
			}
			System.out.printf("%-10s %-30s %-10d $%10.2f\n", s.getStoreCode(), p.personNameToString(), numSales,
					grandTotal);
			netTotal += grandTotal;
		}
		
		System.out.println("+----------------------------------------------------------------+");
		System.out.printf("%43d %20.2f\n\n", sales.size(), netTotal);
	}
	
}