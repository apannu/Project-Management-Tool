package com.capgemini.cisco.portal.utility;

import java.sql.Connection;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBConnection {

	@Resource(name = "jdbc/datasource")
	private static DataSource ds;

	private static Connection connection;

	public static Connection getDBConnection() {

		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/datasource");
			connection = ds.getConnection();

		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Error in Database Connection");
			e.printStackTrace();
		}
		return connection;
	}
}
