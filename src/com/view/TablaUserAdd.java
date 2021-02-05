package com.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.view.panel.ModelMetodosJTable;

import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import javax.swing.JComboBox;

public class TablaUserAdd extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7517097582056763001L;

	/**
	 * 
	 */

	private JPanel contentPane;
	private JTable table;
	private JScrollPane sc;
	private JTextField textField_4;
	private JTextField filterSearch;
	
	
	private DefaultTableModel model;
	private JTextField textField_id;
	private JTextField textField_User;
	private JTextField textField_Password;
	private JTextField textField_Rol;
	
	private ModelMetodosJTable acciones ;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {

			public void run() {
				try {
					TablaUserAdd frame = new TablaUserAdd();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TablaUserAdd() {
		inicializar();
	}

	
	public void inicializar() {
		
		setTitle("Agregar Usuarios");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 940, 504);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(343, 91, 551, 241);

		table = new JTable(model);
		scrollPane.setViewportView(table);

		textField_id = new JTextField();
		textField_id.setBounds(70, 404, 116, 22);
		textField_id.setColumns(10);
		textField_id.setVisible(false);

		JLabel lbl_User = new JLabel("User");
		lbl_User.setBounds(47, 125, 56, 16);

		textField_User = new JTextField();
		textField_User.setBounds(157, 122, 116, 22);
		textField_User.setColumns(10);

		JLabel lbl_Password = new JLabel("Password");
		lbl_Password.setBounds(47, 170, 84, 16);

		textField_Password = new JTextField();
		textField_Password.setBounds(157, 167, 116, 22);
		textField_Password.setColumns(10);

		JLabel lbl_Rol = new JLabel("Rol");
		lbl_Rol.setBounds(47, 218, 56, 16);

		textField_Rol = new JTextField();
		textField_Rol.setBounds(204, 404, 116, 22);
		textField_Rol.setColumns(10);
		textField_Rol.setVisible(false);

		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(176, 299, 97, 25);

		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				acciones = new ModelMetodosJTable(model, table, filterSearch, textField_id, textField_User
						, textField_Password, textField_Rol);
				acciones.update();

			}
		});

		textField_4 = new JTextField();
		textField_4.setBounds(97, 485, 163, 22);
		textField_4.setColumns(10);

		JButton ButtonSearch = new JButton("Search");
		ButtonSearch.setBounds(538, 484, 97, 25);

		JLabel lblNewLabel_1 = new JLabel("Search by");
		lblNewLabel_1.setBounds(12, 488, 73, 16);

		filterSearch = new JTextField();
		filterSearch.setBounds(122, 558, 116, 22);

		filterSearch.setColumns(10);

		JLabel filtrar = new JLabel("Filtrar");
		filtrar.setBounds(12, 561, 56, 16);
		contentPane.setLayout(null);
		contentPane.add(lbl_User);
		contentPane.add(lbl_Password);
		contentPane.add(lbl_Rol);
		contentPane.add(textField_id);
		contentPane.add(textField_User);
		contentPane.add(textField_Password);
		contentPane.add(textField_Rol);
		contentPane.add(scrollPane);
		contentPane.add(btnUpdate);
		contentPane.add(lblNewLabel_1);
		contentPane.add(textField_4);
		contentPane.add(ButtonSearch);
		contentPane.add(filtrar);
		contentPane.add(filterSearch);
		
		JButton salirButton = new JButton("Salir");
		salirButton.setBounds(67, 299, 97, 25);
		contentPane.add(salirButton);
		
		JLabel lblAgregarUsuario = new JLabel("Agregar usuario");
		lblAgregarUsuario.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAgregarUsuario.setBounds(51, 42, 179, 34);
		contentPane.add(lblAgregarUsuario);
		
		JLabel lblListaDeUsuarios = new JLabel("Lista de usuarios");
		lblListaDeUsuarios.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblListaDeUsuarios.setBounds(524, 52, 179, 26);
		contentPane.add(lblListaDeUsuarios);
		

		String[] rols = { "user", "admin", "invitado"};
		JComboBox comboBox = new JComboBox(rols);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String variable = comboBox.getSelectedItem().toString();
				textField_Rol.setText(variable);
			}
		});
		comboBox.setBounds(157, 215, 116, 22);
		comboBox.setSelectedIndex(0);
		contentPane.add(comboBox);
		
		
		String variable = comboBox.getSelectedItem().toString();
		textField_Rol.setText(variable);
		
		acciones = new ModelMetodosJTable(model, table, filterSearch, textField_id, textField_User
				, textField_Password, textField_Rol);
		
		acciones.findUser();

	}
}
