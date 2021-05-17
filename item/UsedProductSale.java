package item;

/**
 * Class that models a used product sale object.
 * 
 * @author nzetocha2 and jbargen3
 *
 */
public class UsedProductSale extends UsedProduct {

	private int quantity;
	private final double tax = 0.0725;
	
	/**
	 * Used product sale constructor.
	 * @param code
	 * @param name
	 * @param basePrice
	 * @param quantity
	 */
	public UsedProductSale(String code, String name, double basePrice, int quantity) {
		super(code, name, basePrice);
		this.quantity = quantity;
	}
	
	/**
	 * Getter method for quantity of new product items.
	 */
	public int getQuantity() {
		return quantity;
	}
	
	@Override
	/**
	 * Setter method for tax on new product.
	 */
	public double getTax() {
		return this.tax;
	}
	
	@Override
	/**
	 * Getter method for pre total of new products.
	 */
	public double getPreTotal() {
		return Math.round((getUsedPrice() * quantity) * 100.0) / 100.0;
	}
	
	@Override
	/**
	 * To string method for sale info of used product.
	 */
	public String saleInfoToString() {
		return "   (" + getType() + " #" + getCode() + " " + getQuantity() + "@" + getUsedPrice() + "/ea)";
	}
	
	/**
	 * Getter method to get the price of used products.
	 */	
	public double getUsedPrice() {
		return Math.round((getPrice() * 0.8) * 100.0) / 100.0;
	}
	
	
}
