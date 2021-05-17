package person;

import java.util.List;

import com.mgg.Address;

/**
 * Class that models a stores person object and provides
 * getter and file I/O functionality.
 * 
 * @author nzetocha2 and jbargen
 *
 */
public abstract class Person implements Comparable<Person> {
	
	private String personCode;
	private String lastName;
	private String firstName;
	private Address address;
	private List<String> emails;
	
	/**
	 * Persons constructor.
	 * @param personCode
	 * @param type
	 * @param lastName
	 * @param firstName
	 * @param address
	 * @param email
	 */
	public Person(String personCode, String type, String lastName, 
				   String firstName, Address address, List<String> emails) {
		this.personCode = personCode;
		this.lastName = lastName;
		this.firstName = firstName;
		this.address = address;
		this.emails = emails;
	}
	
	/**
	 * Getter method for individual persons code.
	 * @return
	 */
	public String getPersonCode() {
		return this.personCode;
	}
	
	/**
	 * Getter method for person type.
	 * @return
	 */
	public abstract String getType();
	
	/**
	 * Getter method for person last name.
	 * @return
	 */
	public String getLastName() {
		return this.lastName;
	}
	
	/**
	 * Getter method for person first name.
	 * @return
	 */
	public String getFirstName() {
		return this.firstName;
	}
	
	/**
	 * Getter method for person address.
	 * @return
	 */
	public Address getAddress() {
		return this.address;
	}
	
	/**
	 * Getter method for persons email.
	 * @return
	 */
	public List<String> getEmail() {
		return this.emails;
	}
	
	/**
	 * Method to return formatted string of persons name and email.
	 * @return
	 */
	@Override
	public String toString() {
		return this.lastName + ", " + this.firstName + " " + "(" + this.emails + ")" + "\n        " + this.address;
	}
	
	/**
	 * Method to return the persons last then first names.
	 * @return
	 */
	public String personNameToString() {
		return this.lastName + ", " + this.firstName;
	}
	
	/**
	 * Abstract method that for getting the specified discount.
	 * @return
	 */
	public abstract double getDiscount();
	
	/**
	 * Complete the implementation of this method that will be used for sorting
	 * collections of people.
	 * 
	 * @param p
	 */
	public int compareTo(Person p) {
		int a = p.getLastName().compareTo(lastName);
		if(a == 0) {
			a = p.getFirstName().compareTo(firstName);
		}
		return a;
	}
	
}