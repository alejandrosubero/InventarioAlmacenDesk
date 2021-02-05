package com.view;

import java.awt.EventQueue;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTabbedPane;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLayeredPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import com.toedter.calendar.JDayChooser;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JTextArea;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;
import javax.swing.JCheckBox;
import javax.swing.JMenu;

public class Windowsprincipal {

	private JFrame frame;
	private JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	
	private static String rol;
	private static String UserName;
	private int idII;
	
	private boolean editar = false;
	private boolean ingreso=false;
	
	private ActionListener accion;
	private AccionTabla accionTabla;
	
	
	private JTextField codigotxt;
	private JTextField textField_cantidad;
	private JTextField textField_areaalmacen;
	private JTextField textField_areaseccion;
	private JTextField textField_nota;
	private JTextField buscar;
	
	
	private JLabel lblNewLabel_1;
	private JLabel lblCantidadProducto_1;
	private JLabel lblAreaDeAlmacenaje_1;
	private JLabel lblSeccinDeAlmacenaje_1;
	private JLabel notalabel;
	
	
	
	//botones 
	private JButton ingresarButton;
	private JButton EditarSalvarButton;
	private JButton editarButton;
	private JButton cancelarButton;
	private JButton Borrar_Button;
	private JTextField id_oculto;
	
	
	private JPanel panel_contenedor1;

	
	private DefaultTableModel model;
	//private DefaultTableModel model_1;
	private JScrollPane scrollPane;
	private JTable table;
	private JPanel panel_1;
	private int cantidad;
	
	
	private JTable table_1;
	private JTextField buscarInventario;
	
	private PanelIngreso ingresoProducto;
	private PanelInventario inventario;
	private JPanel panel_2;
	private JLabel lblSalidoDeProductos;
	private JScrollPane scrollPane_2;
	private JTable table_2;
	private JLabel lblNewLabel_2;
	private JTextField codigo_textField;
	private JLabel lblCantidad;
	private JTextField cantidad_textField;
	private JLabel lblNota;
	private JLabel lblCliente;
	private JTextField cliente_textField;
	
	
	private JPanel panel_3;
	private JPanel corregir_panel;
	
	private JTextField nota_textField;
	private JTextField buscar_salida;
	private JButton salida_editar_Button;
	private JTextField areaalmacen;
	private JTextField areaseccion;
	private JTextField id_Salida_oculto;
	private JMenu mnFile;
	private JMenuItem mntmNewMenuItem;
	private JMenuItem mntmNewMenuItem_1;
	
	
	

	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Windowsprincipal window = new Windowsprincipal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */

	
	public Windowsprincipal() {
		initialize();
		frame.setLocationRelativeTo(null);
	}

	public Windowsprincipal(String roll, String Name) {
		this.rol = roll;
		this.UserName = Name;
	}

	public void iniciarVentana() {
		frame.setVisible(true);
		rolAccion(rol, UserName);
	}

	
	public void closePrograma() {
		System.exit(0);
	}

	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1134, 727);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		tabbedPane();
		menuBarra();
	}

	
	
	public void menuBarra() {
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		mntmNewMenuItem = new JMenuItem("Add User");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				TablaUserAdd addUser = new TablaUserAdd();
				addUser.inicializar();
			}
		});
		mnFile.add(mntmNewMenuItem);
		
		mntmNewMenuItem_1 = new JMenuItem("Auditar");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		mnFile.add(mntmNewMenuItem_1);
		
		
	}
	
///////////////////////////////////////////////////////////metodos de los panel padre//////////////////////////////////////////////////////////////////////////
	
	
	
	
	private void tabbedPane() {

		// tabbedPane.setEnabled(false);

		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());

		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 1183, Short.MAX_VALUE).addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
				groupLayout.createSequentialGroup().addContainerGap(50, Short.MAX_VALUE)
						.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 547, GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));
		
		
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		Inventario(tabbedPane);
		CargaInventaro(tabbedPane);
		salidaInventario(tabbedPane);
		
		
///////////////////////////////////////////////////////////// **************************************************************	
		System.out.println(rol);
	}

	/////////////////////////////////////////////////////////// metodos de los panel
	/////////////////////////////////////////////////////////// hijos//////////////////////////////////////////////////////////////////////////
	
	public void rolAccion(String roll, String Name) {
		
		System.out.println(rol);

		inventario = new PanelInventario();
		ingresoProducto = new PanelIngreso(rol, UserName);
		
		
		
		if (rol.equals("admin")) {

			tabbedPane.setEnabled(true);
			inventario.Inventario(tabbedPane);
			//Inventario(tabbedPane);
			//CargaInventaro(tabbedPane);
			ingresoProducto.CargaInventaro(tabbedPane);
			salidaInventario(tabbedPane);

		} else if (rol.equals("invite")) {
			tabbedPane.setEnabled(true);
			inventario.Inventario(tabbedPane);
			//Inventario(tabbedPane);

		} else if (rol.equals("user")) {
			tabbedPane.setEnabled(true);
			
			inventario.Inventario(tabbedPane);
			//Inventario(tabbedPane);
			ingresoProducto.CargaInventaro(tabbedPane);
			//CargaInventaro(tabbedPane);
			salidaInventario(tabbedPane);
					
		}
	}

	
	private void salidaInventario(JTabbedPane tabbedPane) {
		
		JPanel salidaInventario = new JPanel();
		salidaInventario.addAncestorListener(new AncestorListener() {
			public void ancestorAdded(AncestorEvent event) {
				editar=false;
				ingreso=false;
				
			}
			public void ancestorMoved(AncestorEvent event) {
			}
			public void ancestorRemoved(AncestorEvent event) {
			}
		});
		
		tabbedPane.addTab("Salida de Inventario", null, salidaInventario, null);
		salidaInventario.setLayout(null);
		
		panel_2 = new JPanel();
		panel_2.setBounds(26, 83, 1054, 481);
		salidaInventario.add(panel_2);
		panel_2.setLayout(null);
		
		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(426, 27, 597, 428);
		panel_2.add(scrollPane_2);
		
		table_2 = new JTable(model);
		table_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				
				if (!editar) {
					int seleccion2 =table_2.rowAtPoint(evt.getPoint());
					id_Salida_oculto.setText(String.valueOf(table_2.getValueAt(seleccion2, 0)));
					codigo_textField.setText(String.valueOf(table_2.getValueAt(seleccion2, 1)));
					areaalmacen.setText(String.valueOf(table_2.getValueAt(seleccion2, 3)));
					areaseccion.setText(String.valueOf(table_2.getValueAt(seleccion2, 4)));
					nota_textField.setText(String.valueOf(table_2.getValueAt(seleccion2, 7)));
					
				}else if (editar) {
					
					int seleccion3 =table_2.rowAtPoint(evt.getPoint());
					id_Salida_oculto.setText(String.valueOf(table_2.getValueAt(seleccion3, 0)));
					codigo_textField.setText(String.valueOf(table_2.getValueAt(seleccion3, 1)));
					cantidad_textField.setText(String.valueOf(table_2.getValueAt(seleccion3, 2)));
					areaalmacen.setText(String.valueOf(table_2.getValueAt(seleccion3, 3)));
					areaseccion.setText(String.valueOf(table_2.getValueAt(seleccion3, 4)));
					cliente_textField.setText(String.valueOf(table_2.getValueAt(seleccion3, 7)));
					nota_textField.setText(String.valueOf(table_2.getValueAt(seleccion3, 6)));
				}
				
			}
		});
		scrollPane_2.setViewportView(table_2);
		
		lblNewLabel_2 = new JLabel("Codigo del Producto");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setBounds(37, 102, 156, 21);
		panel_2.add(lblNewLabel_2);
		
		id_Salida_oculto = new JTextField();
		id_Salida_oculto.setBounds(12, 24, 77, 22);
		panel_2.add(id_Salida_oculto);
		id_Salida_oculto.setColumns(10);
		id_Salida_oculto.setVisible(false);

		
		codigo_textField = new JTextField();
		codigo_textField.setBounds(205, 101, 116, 22);
		panel_2.add(codigo_textField);
		codigo_textField.setColumns(10);
		
		lblCantidad = new JLabel("Cantidad");
		lblCantidad.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCantidad.setBounds(37, 151, 93, 21);
		panel_2.add(lblCantidad);
		
		
		cantidad_textField = new JTextField();
		cantidad_textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				ingreso=true;
			}
			@Override
			public void keyTyped(KeyEvent even) {
				char c = even.getKeyChar();
				if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
				JOptionPane.showMessageDialog(null,"No puede ingresar letras!!!","Ventana Error Datos",JOptionPane.ERROR_MESSAGE);
				even.consume();
				}
			}
		});
	
		
		cantidad_textField.setColumns(10);
		cantidad_textField.setBounds(205, 150, 116, 22);
		panel_2.add(cantidad_textField);
		
		lblNota = new JLabel("Nota");
		lblNota.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNota.setBounds(37, 245, 93, 21);
		panel_2.add(lblNota);
		
		lblCliente = new JLabel("Cliente");
		lblCliente.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCliente.setBounds(37, 196, 93, 21);
		panel_2.add(lblCliente);
		
		cliente_textField = new JTextField();
		cliente_textField.setColumns(10);
		cliente_textField.setBounds(205, 195, 116, 22);
		panel_2.add(cliente_textField);
		
		panel_3 = new JPanel();
		panel_3.setBounds(24, 279, 363, 189);
		panel_2.add(panel_3);
		
		
		nota_textField = new JTextField();
		nota_textField.setColumns(10);
		nota_textField.setBounds(205, 244, 116, 22);
		panel_2.add(nota_textField);
		
		areaalmacen = new JTextField();
		areaalmacen.setColumns(10);
		areaalmacen.setBounds(24, 65, 116, 22);
		areaalmacen.setVisible(false);
		panel_2.add(areaalmacen);
		
		areaseccion = new JTextField();
		areaseccion.setBounds(205, 62, 116, 22);
		panel_2.add(areaseccion);
		areaseccion.setVisible(false);
		areaseccion.setColumns(10);
		
		
		buscar_salida = new JTextField();
		buscar_salida.setBounds(205, 27, 116, 22);
		panel_2.add(buscar_salida);
		buscar_salida.setColumns(10);
		
		lblSalidoDeProductos = new JLabel("SALIDA DE PRODUCTOS");
		lblSalidoDeProductos.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblSalidoDeProductos.setBounds(406, 34, 259, 25);
		salidaInventario.add(lblSalidoDeProductos);
		
		Botones();
		
		
	}
	
	
	public void clearInput() {
		id_Salida_oculto.setText("");
		codigo_textField.setText("");
		cantidad_textField.setText("");
		areaalmacen.setText("");
		areaseccion.setText("");
		cliente_textField.setText("");
		nota_textField.setText("");
	}
	
	
	public void Botones() {
		
		JCheckBox chckbxRestarALa = new JCheckBox("Restar a la Salida");
		JCheckBox sumarCheckBox = new JCheckBox("Sumar a la Salida");
		JButton Busqueda_Button_1 = new JButton("Buscar");
		
		Busqueda_Button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {///////////////////////////////////////////////////////////////////////////
				
				editar=false;
				corregir_panel.setVisible(false);
				AccionTabla accionTabla = new AccionTabla(model, buscar_salida.getText(), table_2);
				accionTabla.findTableOfType("inventario");
				
			}
		});
		Busqueda_Button_1.setBounds(94, 23, 81, 25);
		panel_2.add(Busqueda_Button_1);
		
		
		JButton salida_Button = new JButton("Guardar");
		salida_Button.setBounds(198, 26, 97, 25);
		salida_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String operacion="";
				
				if (ingreso && editar) {
					if(chckbxRestarALa.isSelected() || sumarCheckBox.isSelected() ) {
						
						if (chckbxRestarALa.isSelected()) {
							operacion="resta";
						}else if (sumarCheckBox.isSelected()) {
							operacion="suma";
						}
						
						AccionTabla accionTabla = new AccionTabla(model, buscar_salida.getText(), table_2
								,codigo_textField.getText(), areaalmacen.getText(), areaseccion.getText()
								,nota_textField.getText(), UserName, rol);
										
						cantidad = Integer.parseInt(cantidad_textField.getText());
						
						accionTabla.corregirSalida(cantidad, cliente_textField.getText(), operacion);			
						ingreso = false;
						editar =false;
						clearInput();
						corregir_panel.setVisible(false);
						salida_Button.setText("Guardar");
						
					}else {
						JOptionPane.showMessageDialog(null, "Para editar la salida debe de seleccionar si le resta o le suma al inventario");
					}
					
				}else if(ingreso) {
										
					AccionTabla accionTabla = new AccionTabla(model, buscar_salida.getText(), table_2
							,codigo_textField.getText(), areaalmacen.getText(), areaseccion.getText()
							,nota_textField.getText(), UserName, rol);
					
					cantidad = Integer.parseInt(cantidad_textField.getText());
					
					accionTabla.updateSalida("salida", cantidad, cliente_textField.getText());

					ingreso = false;
					editar =false;
					salida_Button.setText("Guardar");
					clearInput();

				}
			}
		});
		
		
		panel_3.setLayout(null);
		panel_3.add(salida_Button);
		
		salida_editar_Button = new JButton("Corregir");
		salida_editar_Button.setBounds(81, 26, 97, 25);
		salida_editar_Button.addActionListener(new ActionListener() {////////////////////////////***********************************************************////////////////////////
			public void actionPerformed(ActionEvent e) {
				editar=true;
				
				if (editar) {
					AccionTabla accionTabla = new AccionTabla(model, buscar_salida.getText(), table_2);
					accionTabla.findInventarioSalida("salida");
					corregir_panel.setVisible(true);
					salida_Button.setText("Salvar");
					
				}
			}
		});
		
		panel_3.add(salida_editar_Button);
		
		
		corregir_panel = new JPanel();
		corregir_panel.setBounds(12, 67, 339, 109);
		panel_3.add(corregir_panel);
		corregir_panel.setLayout(null);
		corregir_panel.setVisible(false);
		
		JLabel corregir_tituloLabel_3 = new JLabel("Acciones a Realizar");
		corregir_tituloLabel_3.setBounds(99, 13, 132, 16);
		corregir_panel.add(corregir_tituloLabel_3);
		
		
		sumarCheckBox.setBounds(8, 38, 139, 25);
		corregir_panel.add(sumarCheckBox);
		sumarCheckBox.addActionListener(new ActionListener() {///////////////////////////////***********************************************///////////////////////////////////////////
			
			public void actionPerformed(ActionEvent e) {
				if (sumarCheckBox.isSelected()) {
					chckbxRestarALa.setSelected(false);
				}

			}
		});
		
		
		chckbxRestarALa.setBounds(181, 38, 139, 25);
		corregir_panel.add(chckbxRestarALa);
		chckbxRestarALa.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				if ( chckbxRestarALa.isSelected()) {
					sumarCheckBox.setSelected(false);
				}	
			}
		});

		JButton cancelar_Button = new JButton("Cancelar");
		cancelar_Button.addActionListener(new ActionListener() {/////////////////////////*************************************************//////////////////////////////////////////
			public void actionPerformed(ActionEvent e) {
				
				corregir_panel.setVisible(false);
				editar=false;
				ingreso=false;
				salida_Button.setText("Guardar");
			}
		});
		cancelar_Button.setBounds(113, 72, 97, 25);
		corregir_panel.add(cancelar_Button);
	}
	

	
	
	
	private void Inventario(JTabbedPane tabbedPane) {
		
		
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
		gl_Inventario.setHorizontalGroup(
			gl_Inventario.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_Inventario.createSequentialGroup()
					.addGap(430)
					.addComponent(tituloInventarioDeAlmacen, GroupLayout.PREFERRED_SIZE, 224, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_Inventario.createSequentialGroup()
					.addGap(32)
					.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 1042, Short.MAX_VALUE)
					.addGap(37))
				.addGroup(gl_Inventario.createSequentialGroup()
					.addGap(32)
					.addComponent(buscarInventario, GroupLayout.PREFERRED_SIZE, 224, GroupLayout.PREFERRED_SIZE)
					.addGap(33)
					.addComponent(busqueda_boton))
		);
		gl_Inventario.setVerticalGroup(
			gl_Inventario.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_Inventario.createSequentialGroup()
					.addGap(35)
					.addComponent(tituloInventarioDeAlmacen, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
					.addGap(13)
					.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 433, Short.MAX_VALUE)
					.addGap(24)
					.addGroup(gl_Inventario.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_Inventario.createSequentialGroup()
							.addGap(1)
							.addComponent(buscarInventario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(busqueda_boton))
					.addGap(63))
		);
		Inventario.setLayout(gl_Inventario);
		
	}
	

	
	private  void clearCeldas() {
		 	 codigotxt.setText("");
			 textField_cantidad.setText("");
			 textField_areaalmacen.setText("");
			 textField_areaseccion.setText("");
			 textField_nota.setText("");
	}
	

		
	
	private void CargaInventaro(JTabbedPane tabbedPane) {
		
		JPanel CargaInventaro = new JPanel();
		CargaInventaro.addAncestorListener(new AncestorListener() {
			public void ancestorAdded(AncestorEvent arg0) {
				editar=false;
				ingreso=false;

			}
			public void ancestorMoved(AncestorEvent arg0) {
			}
			public void ancestorRemoved(AncestorEvent arg0) {
			}
		});

		
		tabbedPane.addTab("Carga de Inventario", null, CargaInventaro, null);
		//tabbedPane.setBackgroundAt(1, new Color(255, 255, 255));
		
		panel_contenedor1 = new JPanel();
		panel_contenedor1.setBounds(0, 67, 1111, 476);
		
		lblNewLabel_1 = new JLabel("Codigo Producto");
		lblNewLabel_1.setBounds(41, 81, 117, 17);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		lblCantidadProducto_1 = new JLabel("Cantidad");
		lblCantidadProducto_1.setBounds(41, 117, 62, 17);
		lblCantidadProducto_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		lblAreaDeAlmacenaje_1 = new JLabel("Area de Almacen");
		lblAreaDeAlmacenaje_1.setBounds(41, 157, 115, 17);
		lblAreaDeAlmacenaje_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		lblSeccinDeAlmacenaje_1 = new JLabel("Secci\u00F3n de Almacen");
		lblSeccinDeAlmacenaje_1.setBounds(41, 197, 135, 17);
		lblSeccinDeAlmacenaje_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		notalabel = new JLabel("Nota");
		notalabel.setBounds(41, 238, 34, 17);
		notalabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		codigotxt = new JTextField();
		codigotxt.setBounds(230, 79, 116, 22);
		codigotxt.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				idII=1;
			}
		});
		codigotxt.setColumns(10);
		
		textField_cantidad = new JTextField();
		textField_cantidad.setBounds(230, 115, 116, 22);
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
		textField_areaalmacen.setBounds(230, 155, 116, 22);
		textField_areaalmacen.setColumns(10);
		
		textField_areaseccion = new JTextField();
		textField_areaseccion.setBounds(230, 201, 116, 22);
		textField_areaseccion.setColumns(10);
		
		id_oculto = new JTextField();
		id_oculto.setBounds(230, 48, 116, 22);
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
		scrollPane.setBounds(536, 13, 539, 450);
		scrollPane.setViewportView(table);
		
		textField_nota = new JTextField();
		textField_nota.setBounds(230, 236, 116, 22);
		textField_nota.setColumns(10);
	
		buscar = new JTextField();
		buscar.setBounds(230, 13, 116, 22);
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
		lblBuscarPor.setBounds(41, 16, 135, 16);
		lblBuscarPor.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		
		panelBotones(2);
		
		//botonDentroIngreso(panel_1);
		//id_oculto.setText("0");
		//int id=Integer.parseInt(id_oculto.getText()); 
		
		AccionTabla accionTabla = new AccionTabla(model, buscar.getText(), table, codigotxt.getText(), textField_areaalmacen.getText(), textField_areaseccion.getText()
				, textField_nota.getText(), UserName, rol);
		
		accionTabla.findTableOfType("inventario");
		CargaInventaro.setLayout(null);
		panel_contenedor1.setLayout(null);
		
		CargaInventaro.add(panel_contenedor1);
		
		GroupLayout(CargaInventaro, lblNewLabel_1, lblCantidadProducto_1
				,lblAreaDeAlmacenaje_1, lblSeccinDeAlmacenaje_1, notalabel,ingresarButton , textField_nota, panel_contenedor1,lblBuscarPor,panel_1 );
		
		
		panel_contenedor1.add(lblBuscarPor);
		panel_contenedor1.add(buscar);
		panel_contenedor1.add(id_oculto);
		panel_contenedor1.add(lblNewLabel_1);
		panel_contenedor1.add(codigotxt);
		panel_contenedor1.add(lblCantidadProducto_1);
		panel_contenedor1.add(textField_cantidad);
		panel_contenedor1.add(lblAreaDeAlmacenaje_1);
		panel_contenedor1.add(textField_areaalmacen);
		panel_contenedor1.add(lblSeccinDeAlmacenaje_1);
		panel_contenedor1.add(textField_areaseccion);
		panel_contenedor1.add(notalabel);
		panel_contenedor1.add(textField_nota);
		panel_contenedor1.add(panel_1);
		panel_contenedor1.add(scrollPane);
		
		JLabel lblCargaDeProducto = new JLabel("CARGA DE PRODUCTO A INVENTARIO");
		lblCargaDeProducto.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblCargaDeProducto.setBounds(344, 25, 401, 27);
		CargaInventaro.add(lblCargaDeProducto);
	
	}
	
	public void panelBotones(int numero) {
		
		panel_1 = new JPanel();
		panel_1.setBounds(12, 289, 477, 174);
		panel_1.setLayout(null);
		
		if (numero ==2) {
			botonDentroIngreso(panel_1);
		}
	}
		
	
	
	
	public void botonDentroIngreso(JPanel panel_1) {
			
		Borrar_Button = new JButton("Borrar");
		Borrar_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//int id=Integer.parseInt(id_oculto.getText()); 
				
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
					
					//int id=Integer.parseInt(id_oculto.getText()); 
					
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
					
					//int id=Integer.parseInt(id_oculto.getText()); 
					
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
	}
	
	
	
	
	
	
	
	
	

	public static String getRol() {
		return rol;
	}

	public static void setRol(String rol) {
		Windowsprincipal.rol = rol;
	}

	public static String getUserName() {
		return UserName;
	}

	public static void setUserName(String userName) {
		UserName = userName;
	}
}
