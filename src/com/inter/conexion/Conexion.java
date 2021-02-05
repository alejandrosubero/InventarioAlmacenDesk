package com.inter.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public interface Conexion {

	// metodo que devuelve una conecion a MySQL a la base de datos seleccion
	default public Connection startedConexion(Connection com, String url) {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			com = DriverManager.getConnection(url, "root", "admin");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return com;
	}

	

	default public Statement createstament(Connection com) {

		Statement st = null;

		if (com != null) {
			try {
				st = com.createStatement();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return st;
	}

	
	default public void addDataBase(String query, Statement st) {

		if (st != null) {
			try {
				int count = st.executeUpdate(query);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	
	
	
	default public void closeConexion(Connection com, String url) {
		if (startedConexion(com, url) != null) {
			try {
				com.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

	}
	
	
	default public void closeStatement(Connection com, Statement st) {
		try {
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
