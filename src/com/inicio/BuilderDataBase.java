package com.inicio;

import java.sql.Connection;
import java.sql.Statement;

import com.inter.conexion.Conexion;
import com.inter.conexion.IBuilderdatabases;

public class BuilderDataBase implements IBuilderdatabases, Conexion{

	private Statement statement;
	private Connection com;
	private String query = "";
	private String url = "jdbc:mysql://localhost:3306?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC"; // "jdbc:mysql://localhost:3306";
			
	
	public BuilderDataBase() {
		this.com = this.startedConexion(com, url);
		this.statement=this.createstament(com);
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@Override
	public void builderDaseDate() {
		builDataBaseAlmacen();
		builTableUser();
		builTableLicencia();
		builTableInventario();
		builTableInventarioSalida();
		builTableInventarioIngreso();
		builTableInventarioIngresoBorrado();
		
		this.closeStatement(com, statement);
		this.closeConexion(com, url);
	}

	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	@Override
	public void builDataBaseAlmacen() {
		query = "create database almacen;";
		this.addDataBase(query,statement);
		
	}

	@Override
	public void usarDataBase() {
		query = "use almacen;";
		this.addDataBase(query,statement);
	}
	
	@Override
	public void builTableUser() {
		usarDataBase();
		url = "jdbc:mysql://localhost:3306/almacen?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		query = "CREATE TABLE register ( `id` INT NOT NULL AUTO_INCREMENT, `user` VARCHAR(45) NULL,  `password` VARCHAR(50) NULL, `rol` VARCHAR(45) NULL, PRIMARY KEY (`id`));";
		this.addDataBase(query,statement);
		
		query ="INSERT INTO register (user, password,rol) values ('admin','admin','admin');";
		this.addDataBase(query,statement);
		
		query ="INSERT INTO register (user, password,rol) values ('user','user','user');";
		this.addDataBase(query,statement);
		
		query ="INSERT INTO register (user, password,rol) values ('invite','invite','invaite');";
		this.addDataBase(query,statement);
		
	}

	
	
	@Override
	public void builTableLicencia() {
		usarDataBase();
		url = "jdbc:mysql://localhost:3306/almacen?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		query = "CREATE TABLE licencia1 (`idlicencia1` INT NOT NULL AUTO_INCREMENT, `fechainicio` DATE NULL, `duracion` long NULL, PRIMARY KEY (`idlicencia1`));";
		this.addDataBase(query,statement);
		query ="INSERT INTO licencia1 (fechainicio,duracion) values (CURDATE(), 10);";/// NOW()
		this.addDataBase(query,statement);

	}
	
	

	@Override
	public void builTableInventario() {
		usarDataBase();
		url = "jdbc:mysql://localhost:3306/almacen?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		query = "CREATE TABLE inventario (`idinventario` INT NOT NULL AUTO_INCREMENT,`codigo` VARCHAR(45) NULL, `cantidad` INT NOT NULL," + 
				"  `almacenarea` VARCHAR(45) NULL, `almacenseccion` VARCHAR(45) NULL, `fechaultimoingreso` VARCHAR(45) NULL," + 
				"  `fechaultimasalida` VARCHAR(45) NULL, `nota` VARCHAR(100) NULL,  PRIMARY KEY (`idinventario`));";
		this.addDataBase(query,statement);
		
	}

	@Override
	public void builTableInventarioSalida() {
		usarDataBase();
		url = "jdbc:mysql://localhost:3306/almacen?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		query = "CREATE TABLE salida ( `idsalida` INT NOT NULL AUTO_INCREMENT, `codigo` VARCHAR(45) NULL, `cantidadsalida` INT NOT NULL," + 
				"  `almacenarea` VARCHAR(45) NULL, `almacenseccion` VARCHAR(45) NULL, `fechasalida` DATE NULL, `nota` VARCHAR(45) NULL, `cliente` VARCHAR(45) NULL," + 
				"  `usuario` VARCHAR(45) NULL, `rol` VARCHAR(45) NULL,  PRIMARY KEY (`idsalida`));";
		this.addDataBase(query,statement);
		
	}

	@Override
	public void builTableInventarioIngreso() {
		usarDataBase();
		url = "jdbc:mysql://localhost:3306/almacen?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		query = "CREATE TABLE ingreso (`idingreso` INT NOT NULL AUTO_INCREMENT,`codigo` VARCHAR(45) NULL,`cantidadrecibida` INT NOT NULL," + 
				"  `almacenarea` VARCHAR(45) NULL,  `almacenseccion` VARCHAR(45) NULL, `fechaingreso` DATE NULL, `nota` VARCHAR(100) NULL,`usuario` VARCHAR(45) NULL,`rol` VARCHAR(45) NULL," + 
				"  PRIMARY KEY (`idingreso`));";
		this.addDataBase(query,statement);
		
	}

	@Override
	public void builTableInventarioIngresoBorrado() {
		usarDataBase();
		url = "jdbc:mysql://localhost:3306/almacen?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		query = "CREATE TABLE editado (`idingreso` INT NOT NULL AUTO_INCREMENT,`codigo` VARCHAR(45) NULL,`cantidadrecibida` INT NOT NULL," + 
				"  `almacenarea` VARCHAR(45) NULL,  `almacenseccion` VARCHAR(45) NULL, `fechaingreso` DATE NULL, `nota` VARCHAR(100) NULL,`usuario` VARCHAR(45) NULL,`rol` VARCHAR(45) NULL," + 
				"  PRIMARY KEY (`idingreso`));";
		this.addDataBase(query,statement);
		
	}


	

	
	
}
