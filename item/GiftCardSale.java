package item;

/**
 * Class that models a gift card sale object.
 * 
 * @author nzetocha2 and jbargen3
 *
 */
public class GiftCardSale extends GiftCard {

	private double preTotal;
	private final double tax = 0.0725;
	
	/**
	 * Gift card sale constructor.
	 * @param code
	 * @param name
	 * @param preTotal
	 */
	public GiftCardSale(String code, String name, double preTotal) {
		super(code, name);
		this.preTotal = preTotal;
	}

	@Override
	/**
	 * Getter method for pre-total amount.
	 */
	public double getPreTotal() {
		return preTotal;
	}
	
	@Override
	/**
	 * Getter method for tax on gift card.
	 */
	public double getTax() {
		return this.tax;
	}
	
	@Override
	/**
	 * To string method for sale info of gift card
	 */
	public String saleInfoToString() {
		return "   (" + getType() + " #" + getCode() + ")";
	}
	
}
