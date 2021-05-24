package io;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class holds methods for creating and closing
 * a connection to an SQL database.
 * 
 * @author jbargen and nzetocha
 */
public class ConnectToDB {

	/**
	 * Method to create a connection to database.
	 * @return 
	 */
	public static Connection createConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		return conn;
	}
	
	/**
	 * Method for closing connection, resultset,
	 * and prepared statements.
	 */
	public static void close(Connection conn, PreparedStatement ps, ResultSet rs) {
		try {
			if(rs != null && !rs.isClosed())
				rs.close();
			if(ps != null && !ps.isClosed())
				ps.close();
			if(conn != null && !conn.isClosed())
				conn.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Method for closing connection
	 * and prepared statements.
	 */
	public static void close(Connection conn, PreparedStatement ps) {
		try {
			if(ps != null && !ps.isClosed())
				ps.close();
			if(conn != null && !conn.isClosed())
				conn.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Method used for executing updates.
	 */
	public static void queryUpdate(String query, Connection conn, PreparedStatement ps) {
		try {
			ps = conn.prepareStatement(query);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
}
