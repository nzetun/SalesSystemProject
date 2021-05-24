package com.mgg;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import io.ConnectToDB;
import io.DBParser;

/**
 * Database interface class
 */
public class SalesData {

	/**
	 * Removes all sales records from the database.
	 */
	public static void removeAllSales() {
		Connection conn = ConnectToDB.createConnection();
		
		String query1 = "delete from SaleItem;";
		String query2 = "delete from Sale;";

		PreparedStatement ps = null;

		ConnectToDB.queryUpdate(query1, conn, ps);
		ConnectToDB.queryUpdate(query2, conn, ps);

		ConnectToDB.close(conn, ps);
	}

	/**
	 * Removes the single sales record associated with the given
	 * <code>saleCode</code>
	 * 
	 * @param saleCode
	 */
	public static void removeSale(String saleCode) {
		if(saleCode.length() > 20) {
			throw new IllegalArgumentException("IllegalArgumentException: a parameter is out of bounds or should not be null");
		}
		
		int existingSale = DBParser.getSaleId(saleCode);
		if(existingSale == -1) {
			//Sale is already deleted
		} else {
			Connection conn = ConnectToDB.createConnection();
			int saleId = DBParser.getSaleId(saleCode);
			
			String query1 = "delete from SaleItem where saleId = ?;";
			String query2 = "delete from Sale where saleCode = ?";
			PreparedStatement ps = null;
		
			try {
				ps = conn.prepareStatement(query1);
				ps.setInt(1, saleId);
				ps.executeUpdate();
				ps.close();
				ps = conn.prepareStatement(query2);
				ps.setString(1, saleCode);
				ps.executeUpdate();
				ps.close();
			} catch (SQLException e) {
				System.out.println("SQLException: ");
				e.printStackTrace();
				throw new RuntimeException(e);
			}

			ConnectToDB.close(conn, ps);
		}
	}

	/**
	 * Clears all tables of the database of all records.
	 */
	public static void clearDatabase() {
		Connection conn = ConnectToDB.createConnection();
		
		String query1 = "delete from SaleItem;";
		String query2 = "delete from Sale;";
		String query3 = "delete from Store;";
		String query4 = "delete from Item;";
		String query5 = "delete from PersonEmail;";
		String query6 = "delete from Email;";
		String query7 = "delete from Person;";
		String query8 = "delete from Address;";
		String query9 = "delete from State;";
		String query10 = "delete from Country;";

		PreparedStatement ps = null;

		ConnectToDB.queryUpdate(query1, conn, ps);
		ConnectToDB.queryUpdate(query2, conn, ps);
		ConnectToDB.queryUpdate(query3, conn, ps);
		ConnectToDB.queryUpdate(query4, conn, ps);
		ConnectToDB.queryUpdate(query5, conn, ps);
		ConnectToDB.queryUpdate(query6, conn, ps);
		ConnectToDB.queryUpdate(query7, conn, ps);
		ConnectToDB.queryUpdate(query8, conn, ps);
		ConnectToDB.queryUpdate(query9, conn, ps);
		ConnectToDB.queryUpdate(query10, conn, ps);

		ConnectToDB.close(conn, ps);
	}
	
	/**
	 * Method to get or add a state record to the database with provided data.
	 * 
	 * @param state
	 * @return
	 */
	public static int getOrAddState(String state) {
		if(state.length() > 20) {
			throw new IllegalArgumentException("IllegalArgumentException: a parameter is out of bounds or should not be null");
		}
		
		int stateId = 0;
		
		int existingStateId = DBParser.getStateId(state);
		if(existingStateId == -1) {
			Connection conn = ConnectToDB.createConnection();
			
			String query = "insert into State (name) values (?);";

			PreparedStatement ps = null;
			ResultSet keys = null;
			
			try {
				ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, state);
				ps.executeUpdate();
				keys = ps.getGeneratedKeys();
				keys.next();
				stateId = keys.getInt(1);
				ps.close();
				conn.close();
			} catch (SQLException e) {
				System.out.println("SQLException: ");
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		
			ConnectToDB.close(conn, ps, keys);
			
			return stateId;
		} else {
			return existingStateId;
		}
		
	}
	
	/**
	 * Method to get or add a country record to the database with provided data.
	 * 
	 * @param country
	 * @return
	 */
	public static int getOrAddCountry(String country) {
		if(country.length() > 20) {
			throw new IllegalArgumentException("IllegalArgumentException: a parameter is out of bounds or should not be null");
		}
		
		int countryId = 0;
		
		int existingCountryId = DBParser.getCountryId(country);
		if(existingCountryId == -1) {
			Connection conn = ConnectToDB.createConnection();
			
			String query = "insert into Country (name) values (?);";

			PreparedStatement ps = null;
			ResultSet keys = null;
			
			try {
				ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, country);
				ps.executeUpdate();
				keys = ps.getGeneratedKeys();
				keys.next();
				countryId = keys.getInt(1);
				ps.close();
				conn.close();
			} catch (SQLException e) {
				System.out.println("SQLException: ");
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		
			ConnectToDB.close(conn, ps, keys);
			
			return countryId;
		} else {
			return existingCountryId;
		}
		
	}
	
	/**
	 * Method to get or add an address record to the database with provided data.
	 * 
	 * @param street
	 * @param city
	 * @param state
	 * @param zipCode
	 * @param country
	 * @return
	 */
	public static int getOrAddAddress(String street, String city, String state, String zipCode, String country) {
		if(street.length() > 255 || city.length() > 100 || zipCode.length() > 5) {
			throw new IllegalArgumentException("IllegalArgumentException: a parameter is out of bounds or should not be null");
		}
		
		int addressId = 0;
		
		int existingAddressId = DBParser.getAddressId(street, city, getOrAddState(state), zipCode, getOrAddCountry(country));
		if(existingAddressId == -1) {
			Connection conn = ConnectToDB.createConnection();
			
			String query = "insert into Address (street,city,stateId,zipCode,countryId) values (?,?,?,?,?);";

			PreparedStatement ps = null;
			ResultSet keys = null;
			
			try {
				ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, street);
				ps.setString(2, city);
				ps.setInt(3, getOrAddState(state));
				ps.setString(4, zipCode);
				ps.setInt(5, getOrAddCountry(country));
				ps.executeUpdate();
				keys = ps.getGeneratedKeys();
				keys.next();
				addressId = keys.getInt(1);
				ps.close();
				conn.close();
			} catch (SQLException e) {
				System.out.println("SQLException: ");
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		
			ConnectToDB.close(conn, ps, keys);
			
			return addressId;
		} else {
			return existingAddressId;
		}
		
	}

	/**
	 * Method to add a person record to the database with the provided data. The
	 * <code>type</code> will be one of "E", "G", "P" or "C" depending on the type
	 * (employee or type of customer).
	 * 
	 * @param personCode
	 * @param type
	 * @param firstName
	 * @param lastName
	 * @param street
	 * @param city
	 * @param state
	 * @param zip
	 * @param country
	 */
	public static void addPerson(String personCode, String type, String firstName, String lastName, String street,
			String city, String state, String zip, String country) {
		if(personCode.length() > 6 || personCode.equals(null) || type.equals(null) || !(type.equals("C") || type.equals("P") || type.equals("G") || type.equals("E")) ||
					firstName.length() > 50 || firstName.equals(null) || lastName.length() > 50 || lastName.equals(null)) {
			throw new IllegalArgumentException("IllegalArgumentException: a parameter is out of bounds or should not be null");
		}
		
		int existingPerson = DBParser.getPersonId(personCode);
		if(existingPerson == -1) {
			Connection conn = ConnectToDB.createConnection();
		
			String query = "insert into Person (personCode,type,lastName,firstName,addressId) values (?,?,?,?,?);";
			
			PreparedStatement ps = null;
		
			try {
				ps = conn.prepareStatement(query);
				ps.setString(1, personCode);
				ps.setString(2, type);
				ps.setString(3, lastName);
				ps.setString(4, firstName);
				ps.setInt(5, getOrAddAddress(street, city, state, zip, country));
				ps.executeUpdate();
				ps.close();
				conn.close();
			} catch (SQLException e) {
				System.out.println("SQLException: ");
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		
			ConnectToDB.close(conn, ps);
		}
	}

	/**
	 * Method to get or add an email record to the database with provided data.
	 * 
	 * @param email
	 * @return
	 */
	public static int getOrAddEmail(String email) {
		if(email.length() > 255) {
			throw new IllegalArgumentException("IllegalArgumentException: a parameter is out of bounds or should not be null");
		}
		
		int emailId = 0;
		
		int existingEmailId = DBParser.getEmailId(email);
		if(existingEmailId == -1) {
			Connection conn = ConnectToDB.createConnection();
			
			String query = "insert into Email (email) values (?);";

			PreparedStatement ps = null;
			ResultSet keys = null;
			
			try {
				ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, email);
				ps.executeUpdate();
				keys = ps.getGeneratedKeys();
				keys.next();
				emailId = keys.getInt(1);
				ps.close();
				conn.close();
			} catch (SQLException e) {
				System.out.println("SQLException: ");
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		
			ConnectToDB.close(conn, ps, keys);
			
			return emailId;
		} else {
			return existingEmailId;
		}
		
	}
	
	/**
	 * Adds an email record corresponding person record corresponding to the
	 * provided <code>personCode</code>
	 * 
	 * @param personCode
	 * @param email
	 */
	public static void addEmail(String personCode, String email) {
		if(personCode.length() > 6 || personCode.equals(null)) {
			throw new IllegalArgumentException("IllegalArgumentException: a parameter is out of bounds or should not be null");
		}
		
		int existingPersonEmail = DBParser.getPersonEmailId(personCode, email);
		if(existingPersonEmail == -1) {
			Connection conn = ConnectToDB.createConnection();
			
			String query = "insert into PersonEmail (emailId,personId) values (?,?);";

			PreparedStatement ps = null;
		
			try {
				ps = conn.prepareStatement(query);
				ps.setInt(1, getOrAddEmail(email));
				ps.setInt(2, DBParser.getPersonId(personCode));
				ps.executeUpdate();
				ps.close();
				conn.close();
			} catch (SQLException e) {
				System.out.println("SQLException: ");
				e.printStackTrace();
				throw new RuntimeException(e);
			}
	
			ConnectToDB.close(conn, ps);
		}
	}

	/**
	 * Adds a store record to the database managed by the person identified by the
	 * given code.
	 * 
	 * @param storeCode
	 * @param managerCode
	 * @param street
	 * @param city
	 * @param state
	 * @param zip
	 * @param country
	 */
	public static void addStore(String storeCode, String managerCode, String street, String city, String state,
			String zip, String country) {
		if(storeCode.length() > 8 || storeCode.equals(null) || managerCode.length() > 6 || managerCode.equals(null)) {
			throw new IllegalArgumentException("IllegalArgumentException: a parameter is out of bounds or should not be null");
		}
		
		int existingStore = DBParser.getStoreId(storeCode);
		if(existingStore == -1) {
			Connection conn = ConnectToDB.createConnection();
		
			String query = "insert into Store (storeCode,managerId,addressId) values (?,?,?);";

			PreparedStatement ps = null;
			
			try {
				ps = conn.prepareStatement(query);
				ps.setString(1, storeCode);
				ps.setInt(2, DBParser.getPersonId(managerCode));
				ps.setInt(3, getOrAddAddress(street, city, state, zip, country));
				ps.executeUpdate();
				ps.close();
				conn.close();
			} catch (SQLException e) {
				System.out.println("SQLException: ");
				e.printStackTrace();
				throw new RuntimeException(e);
			}
			
			ConnectToDB.close(conn, ps);
		}
	}

	/**
	 * Adds a sales item (product, service, subscription) record to the database
	 * with the given <code>name</code> and <code>basePrice</code>. The type of item
	 * is specified by the <code>type</code> which may be one of "PN", "PU", "PG",
	 * "SV", or "SB". These correspond to new products, used products, gift cards
	 * (for which <code>basePrice</code> will be <code>null</code>), services, and
	 * subscriptions.
	 * 
	 * @param itemCode
	 * @param type
	 * @param name
	 * @param basePrice
	 */
	public static void addItem(String itemCode, String type, String name, Double basePrice) {
		if(itemCode.length() > 6 || itemCode.equals(null) || type.equals(null) || !(type.equals("PN") || type.equals("PU") || type.equals("PG") || type.equals("SU") || type.equals("SV") ||
					name.equals(name) || name.length() > 255 || !(basePrice > 0))) {
			throw new IllegalArgumentException("IllegalArgumentException: a parameter is out of bounds or should not be null");
		}
		
		if(basePrice == null) {
			basePrice = 0.0;
		}
		
		int existingItem = DBParser.getItemId(itemCode);
		if(existingItem == -1) {
			Connection conn = ConnectToDB.createConnection();
			
			String query = "insert into Item (itemCode,type,name,baseprice) values (?,?,?,?);";

			PreparedStatement ps = null;
			
			try {
				ps = conn.prepareStatement(query);
				ps.setString(1, itemCode);
				ps.setString(2, type);
				ps.setString(3, name);
				ps.setDouble(4, basePrice);
				ps.executeUpdate();
				ps.close();
				conn.close();
			} catch (SQLException e) {
				System.out.println("SQLException: ");
				e.printStackTrace();
				throw new RuntimeException(e);
			}
			
			ConnectToDB.close(conn, ps);
		}
	}

	/**
	 * Adds a sales record to the database with the given data.
	 * 
	 * @param saleCode
	 * @param storeCode
	 * @param customerCode
	 * @param salesPersonCode
	 */
	public static void addSale(String saleCode, String storeCode, String customerCode, String salesPersonCode) {
		if(saleCode.length() > 20 || saleCode.equals(null) || storeCode.length() > 8 || storeCode.equals(null) || customerCode.length() > 6 ||
					customerCode.equals(null) || salesPersonCode.length() > 6 || salesPersonCode.equals(null)) {
			throw new IllegalArgumentException("IllegalArgumentException: a parameter is out of bounds or should not be null");
		}
		
		int existingSale = DBParser.getSaleId(saleCode);
		if(existingSale == -1) {
			Connection conn = ConnectToDB.createConnection();
			
			String query = "insert into Sale (saleCode,storeId,customerId,salepersonId) values (?,?,?,?);";

			PreparedStatement ps = null;
			
			try {
				ps = conn.prepareStatement(query);
				ps.setString(1, saleCode);
				ps.setInt(2, DBParser.getStoreId(storeCode));
				ps.setInt(3, DBParser.getPersonId(customerCode));
				ps.setInt(4, DBParser.getPersonId(salesPersonCode));
				ps.executeUpdate();
				ps.close();
				conn.close();
			} catch (SQLException e) {
				System.out.println("SQLException: ");
				e.printStackTrace();
				throw new RuntimeException(e);
			}
			
			ConnectToDB.close(conn, ps);
		}
	}

	/**
	 * Adds a particular product (new or used, identified by <code>itemCode</code>)
	 * to a particular sale record (identified by <code>saleCode</code>) with the
	 * specified quantity.
	 * 
	 * @param saleCode
	 * @param itemCode
	 * @param quantity
	 */
	public static void addProductToSale(String saleCode, String itemCode, int quantity) {
		if(!(quantity >= 0)) {
			throw new IllegalArgumentException("IllegalArgumentException: a parameter is out of bounds or should not be null");
		}
		
		int existingProductSale = DBParser.getSaleItemId(saleCode, itemCode);
		if(existingProductSale == -1) {
			Connection conn = ConnectToDB.createConnection();
			
			String query = "insert into SaleItem (saleId,itemId,productQuantity) values (?,?,?);";

			PreparedStatement ps = null;
			
			try {
				ps = conn.prepareStatement(query);
				ps.setInt(1, DBParser.getSaleId(saleCode));
				ps.setInt(2, DBParser.getItemId(itemCode));
				ps.setInt(3, quantity);
				ps.executeUpdate();
				ps.close();
				conn.close();
			} catch (SQLException e) {
				System.out.println("SQLException: ");
				e.printStackTrace();
				throw new RuntimeException(e);
			}
			
			ConnectToDB.close(conn, ps);
		}
	}

	/**
	 * Adds a particular gift card (identified by <code>itemCode</code>) to a
	 * particular sale record (identified by <code>saleCode</code>) in the specified
	 * amount.
	 * 
	 * @param saleCode
	 * @param itemCode
	 * @param amount
	 */
	public static void addGiftCardToSale(String saleCode, String itemCode, double amount) {
		if(!(amount >= 0)) {
			throw new IllegalArgumentException("IllegalArgumentException: a parameter is out of bounds or should not be null");
		}
		
		int existingGiftCardSale = DBParser.getSaleItemId(saleCode, itemCode);
		if(existingGiftCardSale == -1) {
			Connection conn = ConnectToDB.createConnection();
			
			String query = "insert into SaleItem (saleId,itemId,giftCardPrice) values (?,?,?);";

			PreparedStatement ps = null;
			
			try {
				ps = conn.prepareStatement(query);
				ps.setInt(1, DBParser.getSaleId(saleCode));
				ps.setInt(2, DBParser.getItemId(itemCode));
				ps.setDouble(3, amount);
				ps.executeUpdate();
				ps.close();
				conn.close();
			} catch (SQLException e) {
				System.out.println("SQLException: ");
				e.printStackTrace();
				throw new RuntimeException(e);
			}
			
			ConnectToDB.close(conn, ps);
		}
	}

	/**
	 * Adds a particular service (identified by <code>itemCode</code>) to a
	 * particular sale record (identified by <code>saleCode</code>) which
	 * will be performed by the given employee for the specified number of
	 * hours.
	 * 
	 * @param saleCode
	 * @param itemCode
	 * @param employeeCode
	 * @param billedHours
	 */
	public static void addServiceToSale(String saleCode, String itemCode, String employeeCode, double billedHours) {
		if(!(billedHours >= 0)) {
			throw new IllegalArgumentException("IllegalArgumentException: a parameter is out of bounds or should not null");
		}
		
		int existingServiceSale = DBParser.getSaleItemId(saleCode, itemCode);
		if(existingServiceSale == -1) {
			Connection conn = ConnectToDB.createConnection();
			
			String query = "insert into SaleItem (saleId,itemId,employeeId,numberOfHours) values (?,?,?,?);";

			PreparedStatement ps = null;
			
			try {
				ps = conn.prepareStatement(query);
				ps.setInt(1, DBParser.getSaleId(saleCode));
				ps.setInt(2, DBParser.getItemId(itemCode));
				ps.setInt(3, DBParser.getPersonId(employeeCode));
				ps.setDouble(4, billedHours);
				ps.executeUpdate();
				ps.close();
				conn.close();
			} catch (SQLException e) {
				System.out.println("SQLException: ");
				e.printStackTrace();
				throw new RuntimeException(e);
			}
			
			ConnectToDB.close(conn, ps);
		}
	}

	/**
	 * Adds a particular subscription (identified by <code>itemCode</code>) to a
	 * particular sale record (identified by <code>saleCode</code>) which
	 * is effective from the <code>startDate</code> to the <code>endDate</code>
	 * inclusive of both dates.
	 * 
	 * @param saleCode
	 * @param itemCode
	 * @param startDate
	 * @param endDate
	 */
	public static void addSubscriptionToSale(String saleCode, String itemCode, String startDate, String endDate) {
		if(startDate.length() > 10 || startDate.equals(null) || endDate.length() > 10 || endDate.equals(null)) {
			throw new IllegalArgumentException("IllegalArgumentException: a parameter is out of bounds or should not be null");
		}
		
		int existingServiceSale = DBParser.getSaleItemId(saleCode, itemCode);
		if(existingServiceSale == -1) {
			Connection conn = ConnectToDB.createConnection();
			
			String query = "insert into SaleItem (saleId,itemId,beginDate,endDate) values (?,?,?,?);";

			PreparedStatement ps = null;
			
			try {
				ps = conn.prepareStatement(query);
				ps.setInt(1, DBParser.getSaleId(saleCode));
				ps.setInt(2, DBParser.getItemId(itemCode));
				ps.setString(3, startDate);
				ps.setString(4, endDate);
				ps.executeUpdate();
				ps.close();
				conn.close();
			} catch (SQLException e) {
				System.out.println("SQLException: ");
				e.printStackTrace();
				throw new RuntimeException(e);
			}
			
			ConnectToDB.close(conn, ps);
		}
	}


}
