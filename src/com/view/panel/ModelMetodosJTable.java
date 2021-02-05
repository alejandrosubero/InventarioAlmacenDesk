package com.view.panel;

import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.entidades.User;
import com.tool.seguridad.ImplementaConexionUsuario;

public class ModelMetodosJTable {

	private DefaultTableModel modelo;
	private JTable table;
	private JTextField textField;
	
	private JTextField textField_id;
	private JTextField textField_User;
	private JTextField textField_Password;
	private JTextField textField_Rol;
	
	
	public ModelMetodosJTable(DefaultTableModel model, JTable tabla, JTextField textField, JTextField textField_id, JTextField textField_User
			, JTextField textField_Password, JTextField textField_Rol) {
		
		this.modelo = model;
		this.table=tabla;
		this.textField=textField;
		this.textField_id=textField_id;
		this.textField_User =textField_User;
		this.textField_Password = textField_Password;
		this.textField_Rol=textField_Rol;
	}
	
	
	public void update() {		
		addUser();
		JOptionPane.showMessageDialog(null, "usuario ingresado");
		clearBoxs();
		findUser();
		
	}
	
	public void findUser() {

		String[] titulos = { "id", "user", "rol" };

		modelo = new DefaultTableModel(titulos, 0);
		modelo.setColumnIdentifiers(titulos);


		ImplementaConexionUsuario conex = new ImplementaConexionUsuario();
		
		ArrayList<User> users = conex.UserList(textField.getText());

		Object[] row = new Object[3];

		for (int i = 0; i < users.size(); i++) {
			row[0] = users.get(i).getId();
			row[1] = users.get(i).getUser();
			row[2] = users.get(i).getRol();

			modelo.addRow(row);
		}
		table.setModel(modelo);
	}
	
	
	
	public void addUser( ) {
		String query = "INSERT INTO register (user, password,rol) values ('" + textField_User.getText() + "','"
				+ textField_Password.getText() + "','" + textField_Rol.getText() + "');";
		ImplementaConexionUsuario conectar = new ImplementaConexionUsuario();
		conectar.addAndUse(query);
	}
	
	

	public void clearBoxs() {
		textField_id.setText("");
		textField_User.setText("");
		textField_Password.setText("");
		textField_Rol.setText("");
	}

	
}
