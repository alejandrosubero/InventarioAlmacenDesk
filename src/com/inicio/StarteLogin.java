package com.inicio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.entidades.User;
import com.inter.conexion.Conexion;
import com.inter.conexion.ICheckDataBase;
import com.inter.conexion.IUserConnection;

public class StarteLogin implements Conexion, ICheckDataBase {

	private String databaseName = "almacen";
	private String query = "";
	private String url = "jdbc:mysql://localhost:3306?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC"; // "jdbc:mysql://localhost:3306";

	private Statement statement;
	private Connection com;
	private BuilderDataBase builder;

	List<String> lista = new ArrayList<String>();
	private Licencia licencia = new Licencia();

	public StarteLogin() {
		this.com = this.startedConexion(com, url);
		this.statement = this.createstament(com);
	}

	

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public void checkDatabase() {

		if (DatabaseExist()) {
			accion("register");
			accion("licencia1");
		} else {
			createbaseDate();
		}
		this.closeStatement(com, statement);
		this.closeConexion(com, url);
	}

	
	
	public void verificaLicencia() {//no se a implementado a un a la fecha 27/10/2019.**************************
		usarDataBase();
		licencia.checkLicencia(statement);
	}
	
	
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public void getDataBaseQuery(String query) {
		try {
			ResultSet rt = statement.executeQuery(query);
			while (rt.next()) {
				lista.add(rt.getString(1));
			}
			rt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	@Override
	public void usarDataBase() {
		query = "use almacen;";
		this.addDataBase(query, statement);
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public void createbaseDate() {
		builder = new BuilderDataBase();
		builder.builderDaseDate();
	}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	

	private boolean DatabaseExist() {
		boolean valor = false;
		query = "show databases;";
		getDataBaseQuery(query);
		for (String listado : lista) {
			if (listado.equals(databaseName)) {
				valor = true;
			}
		}
		return valor;
	}

	
	private boolean tablasExist(String tabla) {
		boolean valor = false;
		usarDataBase();
		url = "jdbc:mysql://localhost:3306/prueba?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		query = "show tables;";
		getDataBaseQuery(query);
		for (String listado : lista) {
			if (listado.equals(tabla)) {
				return valor = true;
			}
		}
		return valor;
	}

	/// funcionabilidad que pueda que se remueva dejandola solo a la base de datos
	private void accion(String tabla) {
		
		if (!tablasExist(tabla)) {
			builder = new BuilderDataBase();
			
			switch (tabla) {
			case "register":
				builder.builTableUser();
				break;
			case "licencia1":
				builder.builTableLicencia();
				break;
			case "3":
				builder.builTableInventario();
				break;
			case "4":
				builder.builTableInventarioIngreso();	
				break;
			case "5":
				builder.builTableInventarioIngresoBorrado();			
			     break;
			case "6":
				builder.builTableInventarioSalida();			
				break;
		
			default:
				break;
			}
			System.out.println("crea las tablas");// comentarios de pruebas 
			
		}else {
			System.out.println("todo bien");// comentarios de pruebas 
		}
	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
