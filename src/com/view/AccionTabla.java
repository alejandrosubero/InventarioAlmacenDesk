package com.view;

import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.entidades.Inventario;
import com.view.panel.Accion;

public class AccionTabla {

	private Accion conexionAccion;
	private DefaultTableModel modelo;
	private String buscapor;
	private JTable table;
	
	
	private String codigo;
	
	private String areaalmacen;
	private String areaseccion;
	private String nota;
	
	
	private String usuario;
	private String rol;
	
	
	
	//constructor de la clace aciontala
	public AccionTabla(DefaultTableModel modelo, String textField, JTable table, String codigotxt,
			String textField_areaalmacen, String textField_areaseccion, String textField_nota, String usuario, String rol) {	
		
		
		super();
		
		this.conexionAccion=new Accion();
		
		this.modelo = modelo;
		this.buscapor = textField;
		this.table = table;
		
		this.usuario= usuario;
		this.rol=rol;
		
		this.codigo = codigotxt;
	
		this.areaalmacen = textField_areaalmacen;
		this.areaseccion = textField_areaseccion;
		this.nota = textField_nota;
		
	}

	
	
	
	public AccionTabla(DefaultTableModel modelo, String textField, JTable table) {	
		super();
		this.conexionAccion=new Accion();
		this.modelo = modelo;
		this.buscapor = textField;
		this.table = table;

	}
	
	//int newcantidad, String cliente, String operacion
	public void updateSalida(String nameOfTable, int cantidadSalida, String cliente) {	

		int id_inventario=0;
		int cantidad_inventario=0;
		int cantidad=0;
		
	for (Inventario inventario : conexionAccion.ListaTablasAlmacenSalidaEditar(codigo, areaalmacen, areaseccion) ) {
			id_inventario= inventario.getId();
			cantidad_inventario= inventario.getCantidadProducto();
		}
		
		conexionAccion.addinventariototalSalida(codigo, cantidadSalida, areaalmacen, areaseccion, nota, cliente, usuario, rol);
		cantidad = cantidad_inventario - cantidadSalida;

		conexionAccion.inventarioUpdateSalida(cantidad, id_inventario);
		JOptionPane.showMessageDialog(null, "Se ejecuto la salida del producto");
		findInventario();
		
	}
	
	
	
	
	public void updateInventario(String nameOfTable, int cantidad, JTable table) {
		conexionAccion.addProducto(codigo, cantidad, areaalmacen, areaseccion, nota, usuario, rol);
		JOptionPane.showMessageDialog(null, "Ingresado");
		//eliminarTodaLaTabla();
		findTableOfType(nameOfTable);
	}
	
	
	public void findTableOfType(String nameOfTable) {
		if (nameOfTable.contentEquals("inventario")) {
			findInventario();
		}else {
			findInventarioIngresoEditado(nameOfTable);
		}
	}
	
	
	
	public void findInventario() {// este metodo agrega a la tabla el model extraido de la base de datos
		
		String[] titulos = { "Id", "Codigo del producto", "Cantidad", "Almacen Area", "Almacen Seccion",
				" Fecha del ultimoingreso", "Fecha de la ultima Salida", "Nota" };
		
		modelo = new DefaultTableModel(titulos, 0);
		modelo.setColumnIdentifiers(titulos);
		ArrayList<Inventario> inventarios = conexionAccion.inventarioLista(buscapor);
		
		Object[] row = new Object[8];

		for (int i = 0; i < inventarios.size(); i++) {
			row[0] = inventarios.get(i).getId();
			row[1] = inventarios.get(i).getCodigoProducto();
			row[2] = inventarios.get(i).getCantidadProducto();
			row[3] = inventarios.get(i).getAreaAlmacen();
			row[4] = inventarios.get(i).getSeccionAlmacen();
			row[5] = inventarios.get(i).getFechaUltimoIngreso();
			row[6] = inventarios.get(i).getFechaSalida();
			row[7] = inventarios.get(i).getNota();
			modelo.addRow(row);
		}
		table.setModel(modelo);
	}

	

	
	
	//Metodo pensado para auditoria y ver que se edita o agrega durante el ingreso de productos
	public void findInventarioIngresoEditado(String nameOfTable) {

		String[] titulos = { "Id", "Codigo del producto", "Cantidad Recibida", "Almacen Area", "Almacen Seccion",
				"Fecha del ultimoingreso", "Nota", "Usuario"};
		modelo = new DefaultTableModel(titulos, 0);
		modelo.setColumnIdentifiers(titulos);                                              
		ArrayList<Inventario> inventarios = conexionAccion.ListaTablasAlmacen(buscapor,nameOfTable);
		
		Object[] row = new Object[8];

		for (int i = 0; i < inventarios.size(); i++) {
			row[0] = inventarios.get(i).getId();
			row[1] = inventarios.get(i).getCodigoProducto();
			row[2] = inventarios.get(i).getCantidadRecibida();
			row[3] = inventarios.get(i).getAreaAlmacen();
			row[4] = inventarios.get(i).getSeccionAlmacen();
			row[5] = inventarios.get(i).getFechaUltimoIngreso();
			row[6] = inventarios.get(i).getNota();
			row[7] = inventarios.get(i).getUsuario();
			modelo.addRow(row);
		}
		table.setModel(modelo);
	}
	
	
	
	public void findInventarioSalida(String nameOfTable) {
		
		String[] titulos = { "Id", "Codigo del producto", "Cantidad que Salio", "Area donde se Almacenaba ", "Seccion donde se Almacenaba ",
				"Fecha de salida", "Nota", "Cliente", "Usuario"};
		
		modelo = new DefaultTableModel(titulos, 0);
		modelo.setColumnIdentifiers(titulos); 
		
		ArrayList<Inventario> inventarios = conexionAccion.ListaTablasAlmacen(buscapor,nameOfTable);
			
		Object[] row = new Object[9];

		for (int i = 0; i < inventarios.size(); i++) {	
			row[0] = inventarios.get(i).getId();
			row[1] = inventarios.get(i).getCodigoProducto();
			row[2] = inventarios.get(i).getCantidadSalida();
			row[3] = inventarios.get(i).getAreaAlmacen();
			row[4] = inventarios.get(i).getSeccionAlmacen();
			row[5] = inventarios.get(i).getFechaSalida();
			row[6] = inventarios.get(i).getCliente();
			row[7] = inventarios.get(i).getNota();
			row[8] = inventarios.get(i).getUsuario();
			modelo.addRow(row);
		}
		table.setModel(modelo);
	}
	
	
	
	
	public void eliminarTodaLaTabla() {
	 conexionAccion.borrar(codigo,usuario, rol);
	}
	
	
	
	public void botonDeSalveEdition(String usuario, String rol, int cantidad, int id) {
		conexionAccion.salvarEdicion(codigo, usuario, rol);
		conexionAccion.inventarioUpdate(codigo, cantidad, areaalmacen, areaseccion, id);
	}
	
	
	public void corregirSalida(int newcantidad, String cliente, String operacion) {
		
		int id_inventario=0;
		int cantidad_inventario=0;
		int cantidad=0;
		
	for (Inventario inventario : conexionAccion.ListaTablasAlmacenSalidaEditar(codigo, areaalmacen, areaseccion) ) {
			id_inventario= inventario.getId();
			cantidad_inventario= inventario.getCantidadProducto();
		}
		
	if (operacion.equals("suma")) {
	cantidad = cantidad_inventario + newcantidad;
	
	}else if (operacion.equals("resta")) {
		conexionAccion.addinventariototalSalida(codigo, newcantidad, areaalmacen, areaseccion, nota, cliente, usuario, rol);	
		cantidad = cantidad_inventario - newcantidad;

	}
	
	conexionAccion.inventarioUpdateSalida(cantidad, id_inventario);
	conexionAccion.addinventariototalSalidaII(codigo, newcantidad, areaalmacen, areaseccion, nota, cliente, usuario, rol, operacion);
	JOptionPane.showMessageDialog(null, "Se ejecuto la Corrección");
	}
	
	

	
	/*public void corregirSalida2(int newcantidad, String cliente) {
		
		int id_inventario=0;
		int cantidad_inventario=0;
		int id_salida=microSalidaId(codigo);
		int cantidad_salida=microSalidaCantidad (codigo);
		for (Inventario inventario : conexionAccion.ListaTablasAlmacen(codigo, "inventario") ) {
			
			id_inventario= inventario.getId();
			cantidad_inventario= inventario.getCantidadProducto();
		}
		
		int cantidad = cantidad_inventario + cantidad_salida;
		conexionAccion.inventarioUpdate(codigo, cantidad, areaalmacen, areaseccion, id_inventario);
		conexionAccion.salidaUpdate(codigo, newcantidad, codigo, cliente, id_salida);
	}*/
	
	
	
	
	/*public int microSalidaId(String codigo) {
		
		int id_salida=0;
		ArrayList<Inventario> inventarioSalida =conexionAccion.ListaTablasAlmacen(codigo, "salida");
		
		for (int i=0; i > inventarioSalida.size();i++) {	
			id_salida=inventarioSalida.get(inventarioSalida.size()-1).getId();
			return id_salida;
		}
		return id_salida;
	}
	
	
	
	public int microSalidaCantidad (String codigo) {
		
		int cantidad_salida=0;
		ArrayList<Inventario> inventarioSalida =conexionAccion.ListaTablasAlmacen(codigo, "salida");
		for (int i=0; i >inventarioSalida.size();i++) {	
			cantidad_salida=inventarioSalida.get(inventarioSalida.size()-1).getCantidadSalida();
		}
		return cantidad_salida;
	}*/
	
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////no definida la implementacion //////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	
	public void eliminarSeleccionEnTabla() {
		
		int fila = table.getSelectedRow();
		if (fila>=0) {
			modelo.removeRow(fila);
		}else {	
			JOptionPane.showMessageDialog(null, "Seleccione fila");
		}		
	}
	

	public void iniciarModelo(JTable tabla) {
		
		modelo= new DefaultTableModel();
		modelo.addColumn("");
		modelo.addColumn("");
		modelo.addColumn("");
		modelo.addColumn("");

		tabla.setModel(modelo);
	}
	
	
	
	public void insestarEnModeloTabla( JTextField a1, JTextField a2, JTextField a3,JTextField a4) {
		
		String [] info = new String [4];
		
		info [0] = a1.getText();
		info [1] = a2.getText();
		info [2] = a3.getText();
		info [3] = a4.getText();
		
		modelo.addRow(info);
		
		a1.setText("");
		a2.setText("");
		a3.setText("");
		a4.setText("");
		
	}
	
	

	
}
