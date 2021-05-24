package item;

/**
 * Class that models a gift card object.
 * 
 * @author nzetocha2 and jbargen3
 *
 */
public class GiftCard extends Product {

	private static final String type = "GiftCard";
	
	/**
	 * Gift card constructor.
	 * @param code
	 * @param name
	 */
	public GiftCard(String code, String name) {
		super(code, name);
	}
	
	@Override
	/**
	 * Getter method for item type.
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * Getter method for item price.
	 */
	public double getPrice() {
		return 0.0;
	}
	
	@Override
	/**
	 * To string method for name of gift card item.
	 */
	public String nameToString() {
		return getName() + "\n";
	}
	
	/**
	 * To string method for sale info of gift card
	 */
	public String saleInfoToString() {
		return saleInfoToString();
	}

	/**
	 * Getter method for item tax.
	 */
	public double getTax() {
		return this.getTax();
	}

	/**
	 * Getter method for item total dollar amount during sale.
	 */
	public double getPreTotal() {
		return this.getPreTotal();
	}
	
	
}
