package item;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Class that models a subscription sale object.
 * 
 * @author nzetocha2 and jbargen3
 *
 */
public class SubscriptionSale extends Subscription {
	
	private final double tax = 0.00;
	private double totalCost;
	private LocalDate beginDate;
	private LocalDate endDate;
	
	/**
	 * Subscription sale constructor.
	 * @param code
	 * @param name
	 * @param annualFee
	 * @param beginDate
	 * @param endDate
	 */
	public SubscriptionSale(String code, String name, double annualFee, LocalDate beginDate, LocalDate endDate) {
		super(code, name, annualFee);
		this.beginDate = beginDate;
		this.endDate = endDate;
	}
	
	/**
	 * Getter method for beginning date of subscription.
	 */
	public LocalDate getBeginDate() {
		return this.beginDate;
	}
	
	/**
	 * Getter method for ending date of subscription.
	 */
	public LocalDate getEndDate() {
		return this.endDate;
	}

	/**
	 * Getter method for total cost of subscription.
	 */
	public double getTotalCost() {
		return totalCost;
	}
	
	@Override
	/**
	 * Getter method for subscription tax amount.
	 */
	public double getTax() {
		return this.tax;
	}
	
	/**
	 * Getter method for days between beginning and 
	 * ending date.
	 * @return
	 */
	public long getDays() {
		return ChronoUnit.DAYS.between(beginDate, endDate) + 1;
	}
	
	@Override
	/**
	 * Getter method for pre total of subscription.
	 */
	public double getPreTotal() {
		return Math.round(((getDays() / 365.0) * this.annualFee) * 100.0) / 100.0;
	}
	
	@Override
	/**
	 * To string method for name of subscription.
	 */
	public String nameToString() {
		return getName() + "\n";
	}
	
	@Override
	/**
	 * To string method for sale info of subscription.
	 */
	public String saleInfoToString() {
		return "   " + "(" + getType() + " #" + getCode() + " " + getDays() + " days" + " @" + annualFee + "/yr)";
	}
	
	
}
