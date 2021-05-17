package item;

/**
 * Class that models a service item.
 * 
 * @author nzetocha2 and jbargen3
 *
 */
public class Service extends Item {

	private static final String type = "Service";
	protected double hourlyRate;
	
	/**
	 * Service item constructor.
	 * @param code
	 * @param name
	 * @param hourlyRate
	 */
	public Service(String code, String name, double hourlyRate) {
		super(code, name);
		this.hourlyRate = hourlyRate;
	}
	
	@Override
	/**
	 * Getter method for service type.
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * Getter method for service price.
	 */
	public double getPrice() {
		return hourlyRate;
	}
	
	/**
	 * To string method for name of service.
	 */
	public String nameToString() {
		return nameToString();
	}
	
	/**
	 * To string method for sale info of service.
	 */
	public String saleInfoToString() {
		return saleInfoToString();
	}

	/**
	 * Getter method for service tax.
	 */
	public double getTax() {
		return getTax();
	}

	/**
	 * Getter method for service total price for sale.
	 */
	public double getPreTotal() {
		return getPreTotal();
	}
	
	
}
