package com.view.panel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.entidades.Inventario;
import com.inter.conexion.Conexion;

public class Accion implements Conexion {

	/*
	 * NOTA:
	 * 
	 * conpletar con la calse modelometodos.
	 * 
	 * 
	 */
	private String databaseName = "almacen";
	private String query = "";
	private String url = "jdbc:mysql://localhost:3306/" + databaseName
			+ "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC"; // "jdbc:mysql://localhost:3306";
	private Statement statement;
	private Connection connection;

	
	
	public Accion() {
		this.connection = this.startedConexion(connection, url);
		this.statement = this.createstament(connection);
	}

	
	public void accion() {
		
		this.connection = this.startedConexion(connection, url);
		this.statement = this.createstament(connection);
	}
	
	
	public void cerrar() {
		this.closeStatement(connection, statement);
		this.closeConexion(connection, url);
	}

	
	// metodo permite buscar un producto por codigo por id o por cantidad en el
	// inventario y area de almacenamiento
	public ArrayList<Inventario> inventarioLista(String valtoSearch) {
	
		accion();
		ArrayList<Inventario> inventarioList = new ArrayList<>();
		if (connection != null) {
			try {
				String query = "SELECT * from inventario WHERE CONCAT_WS(idinventario, codigo, cantidad, almacenarea, almacenseccion) LIKE '%"
						+ valtoSearch + "%';";

				ResultSet rs = statement.executeQuery(query);
				while (rs.next()) {
					inventarioList.add(new Inventario(rs.getInt("idinventario"), rs.getString("codigo"),
							rs.getInt("cantidad"), rs.getString("almacenarea"), rs.getString("almacenseccion"),
							rs.getString("fechaultimoingreso"), rs.getString("fechaultimasalida"),
							rs.getString("nota")));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		cerrar();
		return inventarioList;
	}

	
	
	
	public ArrayList<Inventario> ListaTablasAlmacen(String valtoSearch, String tabla) {

		accion();
		ArrayList<Inventario> inventarioList = new ArrayList<>();
		inventarioList.removeAll(inventarioList);
		if (connection != null) {
			try {
				String query = "SELECT * from " + tabla
						+ " WHERE CONCAT_WS(codigo, almacenarea, almacenseccion) LIKE '%" + valtoSearch + "%';";
				ResultSet rs = statement.executeQuery(query);

				if (tabla.contentEquals("inventario")) {

					while (rs.next()) {
						inventarioList.add(new Inventario(rs.getInt("idinventario"), rs.getString("codigo"),
								rs.getInt("cantidad"), rs.getString("almacenarea"), rs.getString("almacenseccion"),
								rs.getString("fechaultimoingreso"), rs.getString("fechaultimasalida"),
								rs.getString("nota")));
					}
				} else if (tabla.contentEquals("ingreso") || tabla.contentEquals("editado")) {
					// idingreso, codigo, cantidadrecibida, almacenarea, almacenseccion,
					// fechaingreso, nota, usuario, rol
					while (rs.next()) {
						inventarioList.add(new Inventario(rs.getInt("idingreso"), rs.getString("codigo"),
								rs.getInt("cantidadrecibida"), rs.getString("almacenarea"),
								rs.getString("almacenseccion"), rs.getString("fechaingreso"), rs.getString("nota"),
								rs.getString("usuario"), rs.getString("rol")));
					}
				} else if (tabla.contentEquals("salida")) {
					// idsalida, codigo, cantidadsalida, almacenarea, almacenseccion, fechasalida,
					// nota, cliente, usuario, rol
					while (rs.next()) {
						inventarioList.add(new Inventario(rs.getInt("idsalida"), rs.getString("codigo"),
								rs.getInt("cantidadsalida"), rs.getString("almacenarea"),
								rs.getString("almacenseccion"), rs.getString("fechasalida"), rs.getString("nota"),
								rs.getString("cliente"), rs.getString("usuario"), rs.getString("rol")));
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		cerrar();
		return inventarioList;
	}

	
	
	public ArrayList<Inventario> ListaTablasAlmacenSalidaEditar(String valtoSearch, String almacen, String almacenseccion ) {
		accion();
		ArrayList<Inventario> inventarioList = new ArrayList<>();
		inventarioList.removeAll(inventarioList);
		
		if (connection != null) {
			try {
				String query="select * from inventario where codigo = '"+valtoSearch+"' and almacenarea = '"+almacen+"' and almacenseccion = '"+almacenseccion+"';";
				ResultSet rs = statement.executeQuery(query);
					while (rs.next()) {
						inventarioList.add(new Inventario(rs.getInt("idinventario"), rs.getString("codigo"),
								rs.getInt("cantidad"), rs.getString("almacenarea"), rs.getString("almacenseccion"),
								rs.getString("fechaultimoingreso"), rs.getString("fechaultimasalida"),
								rs.getString("nota")));
					}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		cerrar();
		return inventarioList;
	}
	
	
	

	//metodo adiciona un producto al inventario del almacen
	public void addProducto(String codigo, int cantidadrecibida, String almacenarea, String almacenseccion, String nota,
			String usuario, String rol) {
		
		accion();
		addNewIngreso(codigo, cantidadrecibida, almacenarea, almacenseccion, nota, usuario, rol);
		int id = 0;
		int cantidad = 0;
		if (inventarioLista(codigo).size() != 0) {
			for (Inventario it : inventarioLista(codigo)) {
				id = it.getId();
				if(almacenarea.equals(it.getAreaAlmacen())) {
					cantidad = it.getCantidadProducto() + cantidadrecibida;
					inventarioNewUpdate(cantidad, almacenarea, almacenseccion, id);
					return;
				}else {
					addinventariototalIngreso(codigo,cantidadrecibida , almacenarea, almacenseccion, nota);
					return;
				}
			}
		} else {
			addinventariototalIngreso(codigo,cantidadrecibida , almacenarea, almacenseccion, nota);
		}
		cerrar();
	}

	
		
	//metodo adiciona a la tabla de inventario ingreso el nuevo producto
	public void addinventariototalIngreso(String codigo, int cantidad, String almacenarea, String almacenseccion, String nota) {

		accion();
		String query = "INSERT INTO inventario (codigo, cantidad, almacenarea, almacenseccion,fechaultimoingreso,fechaultimasalida,nota) "
				+ "values ('" + codigo + "','" + cantidad + "','" + almacenarea + "','" + almacenseccion
				+ "',curdate(),'','" + nota + "');";
		this.addDataBase(query, statement);
		cerrar();
	}

	
	
	//metodo adiciona a la tabla de inventario salida el nuevo producto
	public void addinventariototalSalida(String codigo, int cantidad, String almacenarea, String almacenseccion, String nota, String cliente, String usuario, String rol ) {
		accion();
		String query = "INSERT INTO salida (codigo, cantidadsalida, almacenarea, almacenseccion, fechasalida, nota, cliente, usuario, rol) "
				+ "values ('"+codigo+"','"+cantidad+"','"+almacenarea+"','"+almacenseccion+"',curdate(), '"+nota+"', '"+cliente+"','"+usuario+"','"+rol+"');";	
		this.addDataBase(query, statement);
		cerrar();
	}

	
	
	//metodo adiciona a la tabla de inventario salida el nuevo producto
	public void addinventariototalSalidaII(String codigo, int cantidad, String almacenarea, String almacenseccion, String nota, String cliente, String usuario, String rol, String operacion ) {
		
		accion();
		String query = "INSERT INTO salida (codigo, cantidadsalida, almacenarea, almacenseccion, fechasalida, nota, cliente, usuario, rol) "
				+ "values ('"+codigo+"','"+cantidad+"','"+almacenarea+"','"+almacenseccion+"',curdate(), 'Secorrigio "+operacion+" ', '"+cliente+"','"+usuario+"','"+rol+"');";
		this.addDataBase(query, statement);
		cerrar();
	}
	
	
	// metodo actualiza en el inventario la cantidad y el area de almacenaja por id
	public void inventarioNewUpdate(int cantidad, String almacenarea, String almacenseccion, int id) {

		accion();
		String query = "UPDATE inventario SET `cantidad` = '" + cantidad + "', `almacenarea` = '" + almacenarea + "'"
				+ ", `almacenseccion` = '" + almacenseccion + "', `fechaultimoingreso` = curdate()"
				+ "WHERE (`idinventario` = '" + id + "');";
		this.addDataBase(query, statement);
		cerrar();
	}

	
	
	// metodo actualiza en el inventario la cantidad y el area de almacenaja por id
	public void inventarioUpdateSalida(int cantidad, int id) {
	
		accion();
		String query = "UPDATE inventario SET `cantidad` = '" + cantidad + "', `fechaultimasalida` = curdate()"
				+ "WHERE (`idinventario` = '" + id + "');";
		this.addDataBase(query, statement);
		cerrar();
	}
	
	
	
	
	public void addNewIngreso(String codigo, int cantidadrecibida, String almacenarea, String almacenseccion,
			String nota, String usuario, String rol) {

		accion();
		String query = "INSERT INTO ingreso (codigo, cantidadrecibida, almacenarea, almacenseccion, fechaingreso, nota, usuario, rol)"
				+ " values ('" + codigo + "','" + cantidadrecibida + "','" + almacenarea + "','" + almacenseccion
				+ "',curdate(),'" + nota + "','" + usuario + "','" + rol + "');";
		this.addDataBase(query, statement);
		cerrar();
	}

	
	
	
	public void addNewSalida(String codigo, int cantidadsalida, String almacenarea, String almacenseccion, String nota,
			String cliente, String usuario, String rol) {

		accion();
		String query = "INSERT INTO salida (codigo, cantidadsalida, almacenarea, almacenseccion, fechasalida, nota, cliente, usuario, rol)"
				+ " values ('" + codigo + "','" + cantidadsalida + "','" + almacenarea + "','" + almacenseccion
				+ "',curdate(), '" + nota + "', '" + cliente + "','" + usuario + "','" + rol + "');";
		this.addDataBase(query, statement);
		cerrar();
	}

	
	
	public void addNewEditada(String codigo, int cantidadrecibida, String almacenarea, String almacenseccion,
			String nota, String usuario, String rol) {

		accion();
		String query = "INSERT INTO editado (codigo, cantidadrecibida, almacenarea, almacenseccion, fechaingreso, nota, usuario, rol)"
				+ " values ('" + codigo + "','" + cantidadrecibida + "','" + almacenarea + "','" + almacenseccion
				+ "', curdate(), '" + nota + "','" + usuario + "','" + rol + "');";
		this.addDataBase(query, statement);
			cerrar();
	}

	
	
	public void borraRowOFdatabase(int id) {
		accion();
		String query = "DELETE FROM inventario WHERE (`idinventario` = '" + id + "');";
		this.addDataBase(query, statement);
		cerrar();
	}

	
	
	
	
	// metodo actualiza en el inventario la cantidad y el area de almacenaja por id
	public void inventarioUpdate(String codigo, int cantidad, String almacenarea, String almacenseccion, int id) {
		
		accion();
		String query = "UPDATE inventario SET `codigo` = '" +codigo+ "', `cantidad` = '" + cantidad + "', `almacenarea` = '" + almacenarea + "'"
				+ ", `almacenseccion` = '" + almacenseccion + "' WHERE (`idinventario` = '" + id + "');";
		this.addDataBase(query, statement);
		cerrar();
	}
	
	
	// metodo actualiza en el inventario la cantidad y el area de almacenaja por id
		public void salidaUpdate(String codigo, int cantidad, String nota, String cliente, int id) {
			
			accion();			
			String query = "UPDATE salida SET `codigo` = '"+codigo+"', `cantidadsalida` = '"+cantidad+"'"
					+ ", `fechasalida` = curdate(), `nota` = '"+nota+"', `cliente` = '"+cliente+"' WHERE (`idsalida` = '"+id+"');";
			this.addDataBase(query, statement);
			cerrar();
		}
	
		
		
		public void borrar(String codigo,String usuario, String rol) {
		
		accion();
		int id = 0;
		if (inventarioLista(codigo).size() != 0) {
			for (Inventario it : inventarioLista(codigo)) {
				id = it.getId();
				addNewEditada(it.getCodigoProducto(), it.getCantidadProducto(), it.getAreaAlmacen(), it.getSeccionAlmacen(),
						it.getNota(), usuario, rol);
				borraRowOFdatabase(id);
				return;
			}
		} 
		cerrar();
	}
	
	
	
	public void salvarEdicion(String codigo,String usuario, String rol) {
		accion();
		if (inventarioLista(codigo).size() != 0) {	
			for (Inventario it : inventarioLista(codigo)) {
				addNewEditada(it.getCodigoProducto(), it.getCantidadProducto(), it.getAreaAlmacen(), it.getSeccionAlmacen(),it.getNota(), usuario, rol);
			}
		} 
		cerrar();
	}
	
	
	
	
	
	//este metodo actualiza a inventario despues de realizar una salida de productos
	public void salidaProducto(String codigo, int cantidadSalida, String almacenarea, String almacenseccion, String nota, String cliente,
			String usuario, String rol, int id) {
		
		int cantidads = 0;
		int ids = 0;
		
		accion();
		addinventariototalSalida(codigo, cantidadSalida, almacenarea, almacenseccion, nota, cliente, usuario, rol);
		
		
		
		if (inventarioLista(codigo).size() != 0) {
			
			for (Inventario it : inventarioLista(codigo)) {
				ids = it.getId();
				cantidads = it.getCantidadProducto() - cantidadSalida;

				if (cantidads>= 0) {
					inventarioUpdateSalida(cantidads,ids);
					
					cerrar();
					JOptionPane.showMessageDialog(null, "Se ejecuto la salida del producto");
					return;
				}else {
					JOptionPane.showMessageDialog(null, "La cantidad que ingreso es superir al inventario existente del producto");
					cerrar();
					return;
				}
			}
		} 
	}
	
	
	
	
	
}
