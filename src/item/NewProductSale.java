package item;

/**
 * Class that models a new product sale object.
 * 
 * @author nzetocha2 and jbargen3
 *
 */
public class NewProductSale extends NewProduct {

	private int quantity;
	private final double tax = 0.0725;
	
	/**
	 * New product sale constructor.
	 * @param code
	 * @param name
	 * @param basePrice
	 * @param quantity
	 */
	public NewProductSale(String code, String name, double basePrice, int quantity) {
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
		return Math.round((getPrice() * quantity) * 100.0) / 100.0;
	}
	
	@Override
	/**
	 * To string method for sale info of new product.
	 */
	public String saleInfoToString() {
		return "   (" + getType() + " #" + getCode() + " " + getQuantity() + "@" + getPrice() + "/ea)";
	}
	
}
