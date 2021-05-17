package io;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.mgg.Address;
import com.mgg.Store;

import item.GiftCard;
import item.GiftCardSale;
import item.Item;
import item.NewProduct;
import item.NewProductSale;
import item.Service;
import item.ServiceSale;
import item.Subscription;
import item.SubscriptionSale;
import item.UsedProduct;
import item.UsedProductSale;
import person.Employee;
import person.GoldCustomer;
import person.Person;
import person.PlatinumCustomer;
import person.RegCustomer;
import sale.Sale;

/**
 * Class for parsing data from a database.
 * @author jbargen and nzetocha
 *
 */
public class DBParser {

	/**
	 * Method for loading from a given database
	 * and outputting it into a new List of items.
	 * 
	 * @return
	 */
	public static List<Item> loadItemsDatabase() {
		List<Item> items = new ArrayList<>();
		
		Connection conn = ConnectToDB.createConnection();
		
		String query = "select itemId from Item;";
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Item i = getItem(rs.getInt("itemId"));
				items.add(i);
			}
			rs.close();
		} catch(SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		ConnectToDB.close(conn, ps, rs);
		
		return items;
	}
	
	/**
	 * Method for loading a single item from
	 * the database.
	 * 
	 * @param itemId
	 * @return
	 */
	public static Item getItem(int itemId) {
		Item i = null;
		
		Connection conn = ConnectToDB.createConnection();

		String query = "select itemCode, type, name, basePrice from Item where itemId = ?;";
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, itemId);
			rs = ps.executeQuery();
			if(rs.next()) {
				String itemCode = rs.getString("itemCode");
				String type = rs.getString("type");
				String name = rs.getString("name");
				int basePrice = rs.getInt("basePrice");
								
				if(type.contentEquals("PN")) {
					i = new NewProduct(itemCode, name, basePrice);
				} else if (type.contentEquals("PU")) {
					i = new UsedProduct(itemCode, name, basePrice);
				} else if (type.contentEquals("SV")) {
					i = new Service(itemCode, name, basePrice);
				} else if (type.contentEquals("SB")) {
					i = new Subscription(itemCode, name, basePrice);
				} else {
					i = new GiftCard(itemCode, name);
				}
			} else {
				throw new IllegalStateException("No such item with itemId = " + itemId);
			}
			rs.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		ConnectToDB.close(conn, ps, rs);
		
		return i;
	}
	
	/**
	 * Method for returning a specific itemId for an item
	 * from the database.
	 * 
	 * @param itemCode
	 * @return
	 */
	public static int getItemId(String itemCode) {
		int itemId = 0;
		
		Connection conn = ConnectToDB.createConnection();
		
		String query = "select itemId from Item where itemCode = ?;";

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, itemCode);
			rs = ps.executeQuery();
			if(rs.next()) {
				itemId = rs.getInt("itemId");
			} else {
				itemId = -1;
			}
			rs.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		ConnectToDB.close(conn, ps, rs);
		
		return itemId;
	}
	
	/**
	 * Method for returning a specific state for an address
	 * from the database.
	 * 
	 * @param stateId
	 * @return
	 */
	public static String getState(int stateId) {
		String state = null;
		
		Connection conn = ConnectToDB.createConnection();

		String query = "select name from State where stateId = ?;";
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, stateId);
			rs = ps.executeQuery();
			if(rs.next()) {
				state = rs.getString("name");
			} else {
				throw new IllegalStateException("No such state with stateId = " + stateId);
			}
			rs.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		ConnectToDB.close(conn, ps, rs);
		
		return state;
	}
	
	/**
	 * Method for returning a specific stateId for an address
	 * from the database.
	 * 
	 * @param name
	 * @return
	 */
	public static int getStateId(String name) {
		int stateId = 0;
		
		Connection conn = ConnectToDB.createConnection();
		
		String query = "select stateId from State where name = ?;";

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, name);
			rs = ps.executeQuery();
			if(rs.next()) {
				stateId = rs.getInt("stateId");
			} else {
				stateId = -1;
			}
			rs.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		ConnectToDB.close(conn, ps, rs);
		
		return stateId;
	}
	
	/**
	 * Method for returning a specific country name for an address
	 * from the database.
	 * 
	 * @param countryId
	 * @return
	 */
	public static String getCountry(int countryId) {
		String country = null;
		
		Connection conn = ConnectToDB.createConnection();
		
		String query = "select name from Country where countryId = ?;";
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, countryId);
			rs = ps.executeQuery();
			if(rs.next()) {
				country = rs.getString("name");
			} else {
				throw new IllegalStateException("No such country with countryId = " + countryId);
			}
			rs.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		ConnectToDB.close(conn, ps, rs);
		
		return country;
	}
	
	/**
	 * Method for returning a specific countryId for an address
	 * from the database.
	 * 
	 * @param name
	 * @return
	 */
	public static int getCountryId(String name) {
		int countryId = 0;
		
		Connection conn = ConnectToDB.createConnection();
		
		String query = "select countryId from Country where name = ?;";

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, name);
			rs = ps.executeQuery();
			if(rs.next()) {
				countryId = rs.getInt("countryId");
			} else {
				countryId = -1;
			}
			rs.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		ConnectToDB.close(conn, ps, rs);
		
		return countryId;
	}
	
	/**
	 * Method for returning a specific address from database.
	 * 
	 * @param addressId
	 * @return
	 */
	public static Address getAddress(int addressId) {
		Address a = null;
		
		Connection conn = ConnectToDB.createConnection();

		String query = "select street, city, stateId, zipCode, countryId from Address where addressId = ?;";

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, addressId);
			rs = ps.executeQuery();
			if(rs.next()) {
				String street = rs.getString("street");
				String city = rs.getString("city");
				String state = getState(rs.getInt("stateId"));
				String zipCode = rs.getString("zipCode");
				String country = getCountry(rs.getInt("countryId"));
				a = new Address(street, city, state, zipCode, country);
			} else {
				throw new IllegalStateException("No such address with addressId = " + addressId);
			}
			rs.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		ConnectToDB.close(conn, ps, rs);
		
		return a;
	}
	
	/**
	 * Method for returning a specific addressId for an address
	 * from the database.
	 * 
	 * @param street
	 * @param city
	 * @param stateId
	 * @param zipCode
	 * @param countryId
	 * @return
	 */
	public static int getAddressId(String street, String city, int stateId, String zipCode, int countryId) {
		int addressId = 0;
		
		Connection conn = ConnectToDB.createConnection();
		
		String query = "select addressId from Address where street = ? and city = ? and stateId = ? and zipCode = ? and countryId = ?;";

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, street);
			ps.setString(2, city);
			ps.setInt(3, stateId);
			ps.setString(4, zipCode);
			ps.setInt(5, countryId);
			rs = ps.executeQuery();
			if(rs.next()) {
				addressId = rs.getInt("addressId");
			} else {
				addressId = -1;
			}
			rs.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		ConnectToDB.close(conn, ps, rs);
		
		return addressId;
	}
	
	/**
	 * Method to return all emails related to a person
	 * from database based off of the given personId.
	 * 
	 * @param personId
	 * @return
	 */
	public static List<String> getEmails(int personId) {
		List<String> emails = new ArrayList<>();
		
		Connection conn = ConnectToDB.createConnection();

		String query = "select email from PersonEmail pe"
						+ "	join Person p on pe.personId = p.personId"
						+ " join Email e on pe.emailId = e.emailId"
						+ " where p.personId = ?;";

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, personId);
			rs = ps.executeQuery();
			if(rs.next()) {
				emails.add(rs.getString("email"));
				while(rs.next()) {
					emails.add(rs.getString("email"));
				}
			} 
			rs.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		ConnectToDB.close(conn, ps, rs);
		
		return emails;
	}
	
	/**
	 * Method for returning a specific emailId for an email
	 * from the database.
	 * 
	 * @param email
	 * @return
	 */
	public static int getEmailId(String email) {
		int emailId = 0;
		
		Connection conn = ConnectToDB.createConnection();
		
		String query = "select emailId from Email where email = ?;";

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, email);
			rs = ps.executeQuery();
			if(rs.next()) {
				emailId = rs.getInt("emailId");
			} else {
				emailId = -1;
			}
			rs.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		ConnectToDB.close(conn, ps, rs);
		
		return emailId;
	}
	
	/**
	 * Method for returning a specific email linked to a person
	 * from the database.
	 * 
	 * @param personCode
	 * @param email
	 * @return
	 */
	public static int getPersonEmailId(String personCode, String email) {
		int personEmailId = 0;
		
		Connection conn = ConnectToDB.createConnection();
		
		String query = "select personEmailId from PersonEmail where emailId = ? and personId = ?;";

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, getEmailId(email));
			ps.setInt(2, getPersonId(personCode));
			rs = ps.executeQuery();
			if(rs.next()) {
				personEmailId = rs.getInt("personEmailId");
			} else {
				personEmailId = -1;
			}
			rs.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		ConnectToDB.close(conn, ps, rs);
		
		return personEmailId;
	}
	
	/**
	 * Method for loading from a given database
	 * and outputting it into a new List of persons.
	 * 
	 * @return
	 */
	public static List<Person> loadPersonDatabase() {
		List<Person> persons = new ArrayList<>();
		
		Connection conn = ConnectToDB.createConnection();

		String personQuery = "select personId from Person;";

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement(personQuery);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Person person = getPerson(rs.getInt("personId"));
				persons.add(person);
			}
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		ConnectToDB.close(conn, ps, rs);
		
		return persons;
	}
	
	/**
	 * Method for returning a specific person from database
	 * based on personId.
	 * 
	 * @param
	 * @return
	 */
	public static Person getPerson(int personId) {
		Person p = null;
		
		Connection conn = ConnectToDB.createConnection();

		String query = "select personCode, type, lastName, firstName, addressId from Person where personId = ?;";

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, personId);
			rs = ps.executeQuery();
			if(rs.next()) {
				String personCode = rs.getString("personCode");
				String type = rs.getString("type");
				String lastName = rs.getString("lastName");
				String firstName = rs.getString("firstName");
				Address a = getAddress(rs.getInt("addressId"));
				List<String> emails = getEmails(personId);
				if(type.contentEquals("C")) {
					p = new RegCustomer(personCode, type, lastName, firstName, a, emails);
				} else if(type.equals("G")) {
	    			p = new GoldCustomer(personCode, type, lastName, firstName, a, emails);
	    		} else if(type.equals("P")) {
	    			p = new PlatinumCustomer(personCode, type, lastName, firstName, a, emails);
	    		} else if(type.equals("E")) {
	    			p = new Employee(personCode, type, lastName, firstName, a, emails);
				}
			} else {
				throw new IllegalStateException("No such person with personId = " + personId);
			}
			rs.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		ConnectToDB.close(conn, ps, rs);
		
		return p;
	}
	
	/**
	 * Method for returning a specific personId for a person
	 * from the database.
	 * 
	 * @param personCode
	 * @return
	 */
	public static int getPersonId(String personCode) {
		int personId = 0;
		
		Connection conn = ConnectToDB.createConnection();
		
		String query = "select personId from Person where personCode = ?;";

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, personCode);
			rs = ps.executeQuery();
			if(rs.next()) {
				personId = rs.getInt("personId");
			} else {
				personId = -1;
			}
			rs.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		ConnectToDB.close(conn, ps, rs);
		
		return personId;
	}
	
	/**
	 * Method for loading from a given database
	 * and outputting it into a new List of stores.
	 * 
	 * @return
	 */
	public static List<Store> loadStoresDatabase() {
		List<Store> stores = new ArrayList<>();
		Store s = null;
		
		Connection conn = ConnectToDB.createConnection();
		
		String query = "select storeId from Store;";
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				s = getStore(rs.getInt("storeId"));
				stores.add(s);
			}
			rs.close();
		} catch(SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		ConnectToDB.close(conn, ps, rs);
		
		return stores;
	}
	
	/**
	 * Method for returning a specific store from database
	 * based on storeId.
	 * 
	 * @param storeId
	 * @return
	 */
	public static Store getStore(int storeId) {
		Store s = null;
		
		Connection conn = ConnectToDB.createConnection();
		
		String query = "select storeCode, managerId, addressId from Store where storeId = ?;";
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, storeId);
			rs = ps.executeQuery();
			if(rs.next()) {
				String storeCode = rs.getString("storeCode");
				Person manager = getPerson(rs.getInt("managerId"));
				Address address = getAddress(rs.getInt("addressId"));
				s = new Store(storeCode, manager, address);
			} else {
				throw new IllegalStateException("No such store with storeId = " + storeId);
			}
			rs.close();
		} catch(SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		ConnectToDB.close(conn, ps, rs);
		
		return s;
	}
	
	/**
	 * Method for returning a specific storeId for a store
	 * from the database.
	 * 
	 * @param storeCode
	 * @return
	 */
	public static int getStoreId(String storeCode) {
		int storeId = 0;
		
		Connection conn = ConnectToDB.createConnection();
		
		String query = "select storeId from Store where storeCode = ?;";

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, storeCode);
			rs = ps.executeQuery();
			if(rs.next()) {
				storeId = rs.getInt("storeId");
			} else {
				storeId = -1;
			}
			rs.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		ConnectToDB.close(conn, ps, rs);
		
		return storeId;
	}
	
	/**
	 * Method for loading from a given database
	 * and outputting it into a new List of items.
	 * 
	 * @return
	 */
	public static List<Sale> loadSalesDatabase() {
		List<Sale> sales = new ArrayList<Sale>();
		
		Connection conn = ConnectToDB.createConnection();

		PreparedStatement ps = null;
		ResultSet rs = null;

		String personQuery = "select saleId, saleCode, storeId, customerId, salepersonId from Sale";

		try {
			ps = conn.prepareStatement(personQuery);
			rs = ps.executeQuery();
			List<Item> saleItems = new ArrayList<>();
			while(rs.next()) {
				String saleCode	= rs.getString("saleCode");
				Store store = getStore(rs.getInt("storeId"));
				Person customer = getPerson(rs.getInt("customerId"));
				Employee employee = (Employee) getPerson(rs.getInt("salepersonId"));
				saleItems = getSaleItems(rs.getInt("saleId"));
				
				Sale sale = new Sale(saleCode, store, customer, employee, saleItems);
				sales.add(sale);
			}
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		ConnectToDB.close(conn, ps, rs);
		
		return sales;
	}
	
	/**
	 * Method for returning a specific saleId for a sale
	 * from the database.
	 * 
	 * @param saleCode
	 * @return
	 */
	public static int getSaleId(String saleCode) {
		int saleId = 0;
		
		Connection conn = ConnectToDB.createConnection();
		
		String query = "select saleId from Sale where saleCode = ?;";

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, saleCode);
			rs = ps.executeQuery();
			if(rs.next()) {
				saleId = rs.getInt("saleId");
			} else {
				saleId = -1;
			}
			rs.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		ConnectToDB.close(conn, ps, rs);
		
		return saleId;
	}
	
	/**
	 * Method for returning a list of items for each specific sale.
	 * 
	 * @param storeId
	 * @return
	 */
	public static List<Item> getSaleItems(int saleId) {
		List<Item> saleItems = new ArrayList<>();
		
		Connection conn = ConnectToDB.createConnection();
		
		String query = "select it.itemCode, it.type, it.name, it.basePrice, si.productQuantity,"
						+ " si.numberOfHours, si.employeeId, si.beginDate, si.endDate, si.giftCardPrice"
						+ " from Item it "
						+ " join SaleItem si on it.itemId = si.itemId"
						+ " join Sale s on si.saleId = s.saleId"
						+ " where s.saleId = ?;";


		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, saleId);
			rs = ps.executeQuery();
			while(rs.next()) {
				String itemCode = rs.getString("itemCode");
				String type = rs.getString("type");
				String name = rs.getString("name");
				double basePrice = rs.getDouble("basePrice");

				Item i = null;
				if(type.contentEquals("PN")) {
					int quantity = rs.getInt("productQuantity");
					i = new NewProductSale(itemCode, name, basePrice, quantity);
				} else if (type.contentEquals("PU")) {
					int quantity = rs.getInt("productQuantity");
					i = new UsedProductSale(itemCode, name, basePrice, quantity);
				} else if (type.contentEquals("SV") && rs.getInt("employeeId") != 0) {
					double numHours	= rs.getDouble("numberOfHours");
					Employee servicer = (Employee) getPerson(rs.getInt("employeeId"));
					i = new ServiceSale(itemCode, name, basePrice, numHours, servicer);
				} else if (type.contentEquals("SB")) {
					LocalDate beginDate = LocalDate.parse(rs.getString("beginDate"));
					LocalDate endDate = LocalDate.parse(rs.getString("endDate"));
					i = new SubscriptionSale(itemCode, name, basePrice, beginDate, endDate);;
				} else {
					double giftAmount = rs.getDouble("giftCardPrice");
					i = new GiftCardSale(itemCode, name, giftAmount);
				}
				saleItems.add(i);
			}
			rs.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		ConnectToDB.close(conn, ps, rs);
		
		return saleItems;
	}
	
	/**
	 * Method for returning a specific saleItemId for a saleItem
	 * from the database.
	 * 
	 * @param saleCode
	 * @return
	 */
	public static int getSaleItemId(String saleCode, String itemCode) {
		int saleItemId = 0;
		
		Connection conn = ConnectToDB.createConnection();
		
		String query = "select saleItemId from SaleItem where saleId = ? and itemId = ?;";

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, getSaleId(saleCode));
			ps.setInt(2, getItemId(itemCode));
			rs = ps.executeQuery();
			if(rs.next()) {
				saleItemId = rs.getInt("saleItemId");
			} else {
				saleItemId = -1;
			}
			rs.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		ConnectToDB.close(conn, ps, rs);
		
		return saleItemId;
	}
	
}
