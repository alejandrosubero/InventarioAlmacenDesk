package com.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

public class PanelIngreso extends JFrame {

	

	private JLabel lblNewLabel_1;
	private JLabel lblCantidadProducto_1;
	private JLabel lblAreaDeAlmacenaje_1;
	private JLabel lblSeccinDeAlmacenaje_1;
	
	
	//botones 
	private JButton ingresarButton;
	private JButton EditarSalvarButton;
	private JButton editarButton;
	private JButton cancelarButton;
	private JButton Borrar_Button;
	private JTextField id_oculto;
	
	
	
	private JPanel panel_1;
	private JPanel panel_contenedor1;
	private JScrollPane scrollPane;
	private JTable table;
	private DefaultTableModel model;
	
	
	private JTextField codigotxt;
	private JTextField textField_cantidad;
	private int cantidad;
	private JTextField textField_areaalmacen;
	private JTextField textField_areaseccion;
	private JTextField textField_nota;
	private JTextField buscar;
	private JLabel notalabel;
	
	
	
	private static String rol;
	private static String UserName;
	private int idII;
	private boolean editar = false;
	private boolean ingreso=false;

	public  PanelIngreso () {
		
	}
	
	public  PanelIngreso (String rol, String UserName) {
		this.rol =rol;
		this.UserName=UserName;
		//PanelIngreso pa = new PanelIngreso();
	}
	
	private  void clearCeldas() {
	 	 codigotxt.setText("");
		 textField_cantidad.setText("");
		 textField_areaalmacen.setText("");
		 textField_areaseccion.setText("");
		 textField_nota.setText("");
	}

	
	public void CargaInventaro(JTabbedPane tabbedPane) {
		
		JPanel CargaInventaro = new JPanel();
		tabbedPane.addTab("Carga de Inventario", null, CargaInventaro, null);
		tabbedPane.setBackgroundAt(1, new Color(255, 255, 255));
		
		
		panel_contenedor1 = new JPanel();
		
		lblNewLabel_1 = new JLabel("Codigo Producto");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		lblCantidadProducto_1 = new JLabel("Cantidad");
		lblCantidadProducto_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		lblAreaDeAlmacenaje_1 = new JLabel("Area de Almacen");
		lblAreaDeAlmacenaje_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		lblSeccinDeAlmacenaje_1 = new JLabel("Secci\u00F3n de Almacen");
		lblSeccinDeAlmacenaje_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		notalabel = new JLabel("Nota");
		notalabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		codigotxt = new JTextField();
		codigotxt.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				idII=1;
			}
		});
		codigotxt.setColumns(10);
		
		textField_cantidad = new JTextField();
		textField_cantidad.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				ingreso=true;
			}
		});
		textField_cantidad.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent even) {
				
				char c = even.getKeyChar();
				if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
				JOptionPane.showMessageDialog(null,"No puede ingresar letras!!!","Ventana Error Datos",JOptionPane.ERROR_MESSAGE);
				even.consume();
				}
			}
		});
		textField_cantidad.setColumns(10);
		
		textField_areaalmacen = new JTextField();
		textField_areaalmacen.setColumns(10);
		
		textField_areaseccion = new JTextField();
		textField_areaseccion.setColumns(10);
		
		id_oculto = new JTextField();
		id_oculto.setColumns(10);
		id_oculto.setVisible(false);
		
		table = new JTable(model);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {//evento de seleccion 
					
				if (editar) {
					int seleccion =table.rowAtPoint(evt.getPoint());
					id_oculto.setText(String.valueOf(table.getValueAt(seleccion, 0)));
					codigotxt.setText(String.valueOf(table.getValueAt(seleccion, 1)));
					textField_cantidad.setText(String.valueOf(table.getValueAt(seleccion, 2)));
					textField_areaalmacen.setText(String.valueOf(table.getValueAt(seleccion, 3)));
					textField_areaseccion.setText(String.valueOf(table.getValueAt(seleccion, 4)));
					textField_nota.setText(String.valueOf(table.getValueAt(seleccion, 7)));
				}
			}
		});
		scrollPane = new JScrollPane();
		scrollPane.setViewportView(table);
		
		textField_nota = new JTextField();
		textField_nota.setColumns(10);
	
		buscar = new JTextField();
		buscar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent even) {
				
				//int id=Integer.parseInt(id_oculto.getText()); 
				
				AccionTabla accionTabla = new AccionTabla(model, buscar.getText(), table, codigotxt.getText(), textField_areaalmacen.getText(), textField_areaseccion.getText()
						, textField_nota.getText(), UserName, rol);
				accionTabla.findInventario();
			}
		});
		
		buscar.setColumns(10);
		
		JLabel lblBuscarPor = new JLabel("Buscar por");
		lblBuscarPor.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		
		panelBotones(2);
		
		//botonDentroIngreso(panel_1);
	  
		
		AccionTabla accionTabla = new AccionTabla(model, buscar.getText(), table, codigotxt.getText(), textField_areaalmacen.getText(), textField_areaseccion.getText()
				, textField_nota.getText(), UserName, rol);
		accionTabla.findTableOfType("inventario");
		
		
		GroupLayout(CargaInventaro, lblNewLabel_1, lblCantidadProducto_1
				,lblAreaDeAlmacenaje_1, lblSeccinDeAlmacenaje_1, notalabel,ingresarButton , textField_nota, panel_contenedor1,lblBuscarPor,panel_1 );

	}
	
	public void panelBotones(int numero) {
		
		panel_1 = new JPanel();
		panel_1.setLayout(null);
		
		if (numero ==2) {
			botonDentroIngreso(panel_1);
		}
	}
		
	public void botonDentroIngreso(JPanel panel_1) {
			
		Borrar_Button = new JButton("Borrar");
		Borrar_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
								
				AccionTabla accionTabla = new AccionTabla(model, buscar.getText(), table, codigotxt.getText()
						, textField_areaalmacen.getText(), textField_areaseccion.getText()
						, textField_nota.getText(), UserName, rol);
				
				accionTabla.eliminarTodaLaTabla();
				JOptionPane.showMessageDialog(null,"Campo borrrado","Ventana Error Datos",JOptionPane.ERROR_MESSAGE);
				accionTabla.findTableOfType("inventario");
				clearCeldas();
			}
		});
		Borrar_Button.setBounds(283, 62, 97, 25);
		panel_1.add(Borrar_Button);
		Borrar_Button.setVisible(false);
		
		
		cancelarButton = new JButton("Salir de editar");
		cancelarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				editar=false;	
				ingreso=false;
				EditarSalvarButton.setVisible(false);
				cancelarButton.setVisible(false);
				Borrar_Button.setVisible(false);
				ingresarButton.setEnabled(true);
				clearCeldas();
			}
		});
		cancelarButton.setBounds(188, 108, 141, 25);
		panel_1.add(cancelarButton);
		cancelarButton.setVisible(false);
		
		
		editarButton = new JButton("Editar");
		editarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				editar=true;
				EditarSalvarButton.setVisible(true);
				cancelarButton.setVisible(true);
				Borrar_Button.setVisible(true);
			}
		});
		editarButton.setVisible(true);
		editarButton.setBounds(147, 24, 97, 25);
		panel_1.add(editarButton);
		

		EditarSalvarButton = new JButton("Guardar cambios");
		EditarSalvarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actioneditar) {
				
				if (ingreso || idII == 1) {
					
					
					
					AccionTabla accionTabla = new AccionTabla(model, buscar.getText(), table, codigotxt.getText()
							, textField_areaalmacen.getText(), textField_areaseccion.getText()
							, textField_nota.getText(), UserName, rol);
					
					cantidad = Integer.parseInt(textField_cantidad.getText());
					accionTabla.botonDeSalveEdition(UserName, rol, cantidad, Integer.parseInt(id_oculto.getText())); 
					
					//JOptionPane.showMessageDialog(null,"","Ventana Error Datos",JOptionPane.ERROR_MESSAGE);	
					accionTabla.findTableOfType("inventario");
					clearCeldas();
				}
			}
		});
		EditarSalvarButton.setBounds(104, 62, 148, 25);
		panel_1.add(EditarSalvarButton);
		EditarSalvarButton.setVisible(false);
		
			
		ingresarButton = new JButton("Ingresar");
		ingresarButton.setVisible(true);
		ingresarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (ingreso && !editar) {
					 
					AccionTabla accionTabla = new AccionTabla(model, buscar.getText(), table, codigotxt.getText()
							, textField_areaalmacen.getText(), textField_areaseccion.getText()
							, textField_nota.getText(), UserName, rol);
					
					cantidad = Integer.parseInt(textField_cantidad.getText());
					accionTabla.updateInventario("inventario", cantidad,table);
					clearCeldas();
					ingreso=false;
				}else if (!ingreso && editar){
					JOptionPane.showMessageDialog(null,"Esta en modo editar la funsionabilidad de ingreso se activa al salvar las edición","Ventana Error Datos",JOptionPane.ERROR_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null,"Llene todos los campos","Ventana Error Datos",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		ingresarButton.setBounds(273, 24, 95, 25);
		panel_1.add(ingresarButton);
		
	}
	
	
	private void GroupLayout(  JPanel CargaInventaro,JLabel lblNewLabel,JLabel lblCantidadProducto
			,JLabel lblAreaDeAlmacenaje, JLabel lblSeccinDeAlmacenaje, JLabel lblFechaDeIngreso, JButton btnNewButton,JTextField textField_nota,JPanel panel,JLabel lblBuscarPor,JPanel panel_1) {
	
		GroupLayout gl_CargaInventaro = new GroupLayout(CargaInventaro);
		gl_CargaInventaro.setHorizontalGroup(
			gl_CargaInventaro.createParallelGroup(Alignment.LEADING)
				.addComponent(panel_contenedor1, GroupLayout.DEFAULT_SIZE, 1111, Short.MAX_VALUE)
		);
		gl_CargaInventaro.setVerticalGroup(
			gl_CargaInventaro.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_CargaInventaro.createSequentialGroup()
					.addGap(49)
					.addComponent(panel_contenedor1, GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE)
					.addGap(99))
		);
		GroupLayout gl_panel_contenedor1 = new GroupLayout(panel_contenedor1);
		gl_panel_contenedor1.setHorizontalGroup(
			gl_panel_contenedor1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_contenedor1.createSequentialGroup()
					.addGap(12)
					.addGroup(gl_panel_contenedor1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_contenedor1.createSequentialGroup()
							.addGap(29)
							.addComponent(lblBuscarPor, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
							.addGap(54)
							.addComponent(buscar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_contenedor1.createSequentialGroup()
							.addGap(218)
							.addComponent(id_oculto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_contenedor1.createSequentialGroup()
							.addGap(29)
							.addComponent(lblNewLabel_1)
							.addGap(72)
							.addComponent(codigotxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_contenedor1.createSequentialGroup()
							.addGap(29)
							.addComponent(lblCantidadProducto_1)
							.addGap(127)
							.addComponent(textField_cantidad, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_contenedor1.createSequentialGroup()
							.addGap(29)
							.addComponent(lblAreaDeAlmacenaje_1)
							.addGap(74)
							.addComponent(textField_areaalmacen, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_contenedor1.createSequentialGroup()
							.addGap(29)
							.addComponent(lblSeccinDeAlmacenaje_1)
							.addGap(54)
							.addComponent(textField_areaseccion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_contenedor1.createSequentialGroup()
							.addGap(29)
							.addComponent(notalabel)
							.addGap(155)
							.addComponent(textField_nota, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 477, GroupLayout.PREFERRED_SIZE))
					.addGap(47)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 539, Short.MAX_VALUE)
					.addGap(36))
		);
		gl_panel_contenedor1.setVerticalGroup(
			gl_panel_contenedor1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_contenedor1.createSequentialGroup()
					.addGap(13)
					.addGroup(gl_panel_contenedor1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_contenedor1.createSequentialGroup()
							.addGroup(gl_panel_contenedor1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_contenedor1.createSequentialGroup()
									.addGap(3)
									.addComponent(lblBuscarPor, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
								.addComponent(buscar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(13)
							.addComponent(id_oculto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(9)
							.addGroup(gl_panel_contenedor1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_contenedor1.createSequentialGroup()
									.addGap(2)
									.addComponent(lblNewLabel_1))
								.addComponent(codigotxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(14)
							.addGroup(gl_panel_contenedor1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_contenedor1.createSequentialGroup()
									.addGap(2)
									.addComponent(lblCantidadProducto_1))
								.addComponent(textField_cantidad, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_panel_contenedor1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_contenedor1.createSequentialGroup()
									.addGap(2)
									.addComponent(lblAreaDeAlmacenaje_1))
								.addComponent(textField_areaalmacen, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(20)
							.addGroup(gl_panel_contenedor1.createParallelGroup(Alignment.LEADING)
								.addComponent(lblSeccinDeAlmacenaje_1)
								.addGroup(gl_panel_contenedor1.createSequentialGroup()
									.addGap(4)
									.addComponent(textField_areaseccion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addGap(13)
							.addGroup(gl_panel_contenedor1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_contenedor1.createSequentialGroup()
									.addGap(2)
									.addComponent(notalabel))
								.addComponent(textField_nota, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(31)
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE))
					.addGap(13))
		);	
		
		panel_contenedor1.setLayout(gl_panel_contenedor1);
		CargaInventaro.setLayout(gl_CargaInventaro);
	}
	
	
	
	
	
	
	
	
	
}
