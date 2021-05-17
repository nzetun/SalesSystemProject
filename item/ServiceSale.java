package item;

import person.Person;

/**
 * Class that models a service sale object.
 * 
 * @author nzetocha2 and jbargen3
 *
 */
public class ServiceSale extends Service {

	private final double tax = 0.0285;
	private double numberOfHours;
	private Person servicer;

	/**
	 * Service sale constructor.
	 * @param code
	 * @param name
	 * @param hourlyRate
	 * @param numberOfHours
	 * @param servicer
	 */
	public ServiceSale(String code, String name, double hourlyRate, double numberOfHours, Person servicer) {
		super(code, name, hourlyRate);
		this.numberOfHours = numberOfHours;
		this.servicer = servicer;
	}
	
	@Override
	/**
	 * Getter method for service tax.
	 */
	public double getTax() {
		return this.tax;
	}
	
	/**
	 * Getter method for number of hours of service.
	 */
	public double getNumberOfHours() {
		return this.numberOfHours;
	}
	
	/**
	 * Getter method for servicer person.
	 */
	public Person getServicer() {
		return this.servicer;
	}
	
	@Override
	/**
	 * Getter method for pre total of service completion.
	 */
	public double getPreTotal() {
		return Math.round((this.hourlyRate * numberOfHours) * 100.0) / 100.0;
	}
	
	@Override
	/**
	 * To string method for name of service type.
	 */
	public String nameToString() {
		return getName() + "\n";
	}
	
	@Override
	/**
	 * To string method for sale info of service.
	 */
	public String saleInfoToString() {
		return "   " + "(" + getType() + " #" + getCode() + " by " + servicer.getLastName() + ", " + servicer.getFirstName() +
				" " + numberOfHours + "hrs" + "@" + this.hourlyRate + "/hr)";
	}
	
}
