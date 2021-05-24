package com.mgg;

/**
 * Class models a multiline address with toString
 * capabilities.
 * 
 * @author nzetocha2 and jbargen
 *
 */
public class Address {
	
	private String street;
	private String city;
	private String state;
	private String zipCode;
	private String country;
	
	/**
	 * Address constructor.
	 * @param street
	 * @param city
	 * @param state
	 * @param zip
	 */
	public Address(String street, String city, String state, String zipCode, String country) {
		super();
		this.street = street;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.country = country;
	}
	
	/**
	 * Getter method for street line of address.
	 * @return
	 */
	public String getStreet() {
		return this.street;
	}
	
	/**
	 * Getter method for city.
	 * @return
	 */
	public String getCity() {
		return this.city;
	}
	
	/**
	 * Getter method for state.
	 * @return
	 */
	public String getState() {
		return this.state;
	}
	
	/**
	 * Getter method for zip code.
	 * @return
	 */
	public String getZipCode() {
		return this.zipCode;
	}
	
	/**
	 * Getter method for country.
	 * @return
	 */
	public String getCountry() {
		return this.country;
	}
	
	/**
	 * To string method for outputting complete address.
	 */
	@Override
	public String toString() {
		return this.street + "\n        " + this.city + " " + this.state + " " + this.zipCode + " " + this.country;
	}
	
}