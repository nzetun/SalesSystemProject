package person;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.mgg.Address;

import io.DBParser;
import sale.Sale;

/**
 * Class that models an intermediate Employee object.
 * 
 * @author nzetocha2 and jbargen3
 *
 */
public class Employee extends Person {
	
	private double discount = 0.15;
	private List<Sale> salesMade = new ArrayList<Sale>();
	
	/**
	 * Employee constructor.
	 * @param personCode
	 * @param type
	 * @param lastName
	 * @param firstName
	 * @param address
	 * @param email
	 */
	public Employee(String personCode, String type, String lastName, 
				   String firstName, Address address, List<String> emails) {
		
		super(personCode, type, lastName, firstName, address, emails);
		
	}
	
	/**
	 * Getter method for made sales.
	 */
	public List<Sale> getSalesMade() {
		return this.salesMade;
	}
	
	/**
	 * Method to add sales when salesperson makes a sale.
	 */
	public void addSaleMade(Sale s) {
		salesMade.add(s);
	}
	
	/**
	 * Getter method for returning employee discount.
	 */
	public double getDiscount() {
		return this.discount;
	}
	
	@Override
	/**
	 * Getter method for returning person type.
	 */
	public String getType() {
		return "Employee";
	}

	/**
	 * Saves a list of all employees from the person data.
	 */
	public static List<Employee> employeeList() {
		
		List<Person> people = DBParser.loadPersonDatabase();
		List<Employee> employees = new ArrayList<>();
		
		for(Person person : people) {
			if(person.getType().contains("Employee")) {
				employees.add((Employee) person);
			}
		}
		
		Collections.sort(employees, new Comparator<Person>() {
			@Override
			public int compare(Person a, Person b) {
				int nameCompare = a.getLastName().compareTo(b.getLastName());
				return nameCompare;
			}
		});
		
		return employees;
	}
	
	/**
	 * Adds each sale made to their seller.
	 * 
	 * @param sales
	 */
	public static List<Employee> employeeSales(List<Sale> sales) {
		List<Employee> employees = Employee.employeeList();
		
		for(Employee salesman : employees) {
			for(Sale newSale : sales) {
				Employee seller = newSale.getSalesPerson();
				if(salesman.getPersonCode().equals(seller.getPersonCode())) {
					salesman.addSaleMade(newSale);
				}
			}
		}
	
		return employees;
	}
	
	/**
	 * Method for returning a sales amount for each employee based on
	 * sales data and output to the standard output.
	 * @param sales
	 */
	public static void employeeSummaryReport(List<Sale> sales) {
		System.out.println("+-----------------------------------------------------+");
		System.out.println("| Salesperson Summary Report                          |");
		System.out.println("+-----------------------------------------------------+");
		System.out.println("Salesperson                    # Sales    Grand Total");
		
		int total = 0;
		double finalTotal = 0;
		
		List<Employee> employeeSales = employeeSales(sales);
		
		for (Employee e : employeeSales) {
			int count = 0;
			double grandTotal = 0.0;
			List<Sale> employeeSaleList = e.getSalesMade();
			for (Sale sale1 : employeeSaleList) {
				count++;
				grandTotal += sale1.getGrandTotal();
			}
			System.out.printf("%-30s %-10d $ %9.2f\n", e.personNameToString(), count, grandTotal);
			total += count;
			finalTotal += grandTotal;
		}
		System.out.println("+-----------------------------------------------------+");
		System.out.printf("%32d          $ %9.2f\n\n", total, finalTotal);
	}
	
}
