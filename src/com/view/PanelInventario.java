package com.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.table.DefaultTableModel;

public class PanelInventario extends JFrame {

	private DefaultTableModel model;
	private JTable table_1;
	private JTextField buscarInventario;

	public PanelInventario() {

	}

	public void Inventario(JTabbedPane tabbedPane) {

		JPanel Inventario = new JPanel();
		tabbedPane.addTab("Inventario", null, Inventario, null);

		JScrollPane scrollPane_1 = new JScrollPane();

		table_1 = new JTable(model);
		scrollPane_1.setViewportView(table_1);

		buscarInventario = new JTextField();
		buscarInventario.setColumns(10);

		JButton busqueda_boton = new JButton("Busqueda");
		busqueda_boton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				AccionTabla accionTabla = new AccionTabla(model, buscarInventario.getText(), table_1);
				accionTabla.findTableOfType("inventario");
			}
		});

		JLabel tituloInventarioDeAlmacen = new JLabel("Inventario de Almacen");
		tituloInventarioDeAlmacen.setFont(new Font("Tahoma", Font.BOLD, 18));

		AccionTabla accionTabla = new AccionTabla(model, buscarInventario.getText(), table_1);
		accionTabla.findTableOfType("inventario");

		GroupLayout gl_Inventario = new GroupLayout(Inventario);
		gl_Inventario.setHorizontalGroup(gl_Inventario.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_Inventario.createSequentialGroup().addGap(430).addComponent(tituloInventarioDeAlmacen,
						GroupLayout.PREFERRED_SIZE, 224, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_Inventario.createSequentialGroup().addGap(32)
						.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 1042, Short.MAX_VALUE).addGap(37))
				.addGroup(gl_Inventario.createSequentialGroup().addGap(32)
						.addComponent(buscarInventario, GroupLayout.PREFERRED_SIZE, 224, GroupLayout.PREFERRED_SIZE)
						.addGap(33).addComponent(busqueda_boton)));
		gl_Inventario.setVerticalGroup(gl_Inventario.createParallelGroup(Alignment.LEADING).addGroup(gl_Inventario
				.createSequentialGroup().addGap(35)
				.addComponent(tituloInventarioDeAlmacen, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
				.addGap(13).addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 433, Short.MAX_VALUE).addGap(24)
				.addGroup(gl_Inventario.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_Inventario.createSequentialGroup().addGap(1).addComponent(buscarInventario,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(busqueda_boton))
				.addGap(63)));
		Inventario.setLayout(gl_Inventario);

	}

}
