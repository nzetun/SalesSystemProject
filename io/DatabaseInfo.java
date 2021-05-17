package io;

/**
 * Class for holding database user, password, and url.
 * 
 * @author jbargen and nzetocha
 *
 */
public class DatabaseInfo {

	/**
	 * Connection parameters that are necessary for CSE's configuration
	 */
	public static final String PARAMETERS = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

	public static final String USERNAME = "nzetocha";
	public static final String PASSWORD = "4dA-8X";
	public static final String URL = "jdbc:mysql://cse.unl.edu/" + USERNAME + PARAMETERS;
	
}
