package com.prototype.connection;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBConnection implements ConnectionMaker {
	private Connection c;

	@Override
	public Connection makeConnection() throws SQLException, NamingException {
		Context initContext = new InitialContext();
		DataSource ds = (DataSource) initContext.lookup("java:comp/env/jdbc/mysql");
		c = ds.getConnection();
		
		return c;
	}
}
