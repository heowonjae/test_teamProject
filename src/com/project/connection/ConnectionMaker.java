package com.project.connection;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;

public interface ConnectionMaker {

	public Connection makeConnection() throws SQLException, NamingException;
}
