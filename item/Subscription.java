package item;


/**
 * Class that models a subscription item.
 * 
 * @author nzetocha2 and jbargen3
 *
 */
public class Subscription extends Item {

	private static final String type = "Subscription";
	protected double annualFee;
	
	/**
	 * Subscription item constructor.
	 * @param code
	 * @param name
	 * @param annualFee
	 */
	public Subscription(String code, String name, double annualFee) {
		super(code, name);
		this.annualFee = annualFee;
	}

	@Override
	/**
	 * Getter method for subscription type.
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * Getter method for subscription annual fee.
	 */
	public double getPrice() {
		return annualFee;
	}
	
	/**
	 * To string method for name of subscription.
	 */
	public String nameToString() {
		return nameToString();
	}
	
	/**
	 * To string method for sale info of subscription.
	 */
	public String saleInfoToString() {
		return saleInfoToString();
	}
	
	/**
	 * Getter method for subscription tax.
	 */
	public double getTax() {
		return getTax();
	}

	/**
	 * Getter method for subscription total price for sale.
	 */
	public double getPreTotal() {
		return getPreTotal();
	}
	
	

}
