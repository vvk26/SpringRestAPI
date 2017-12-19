package com.DAO;

import java.sql.Connection;
import java.sql.DriverManager;

import org.springframework.beans.factory.annotation.Autowired;

import com.util.FetchProperties;


public class OracleConnection {


	static public Connection getConnection() {

	Connection connection = null;
	String DB_HOST = FetchProperties.getDBProperty("DB_HOST");
	String DB_URL = FetchProperties.getDBProperty("DB_URL");
	String DB_USER = FetchProperties.getDBProperty("DB_USER");
	String DB_PWD = FetchProperties.getDBProperty("DB_PWD");

	try {
		Class.forName(DB_HOST).newInstance();
		connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
	} catch (Exception e) {
		e.printStackTrace();
		return null;
	}
	return connection;
	}
}
