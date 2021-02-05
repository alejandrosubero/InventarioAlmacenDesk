package com.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.entidades.User;
import com.tool.seguridad.EncriptadoPassword;
import com.tool.seguridad.ImplementaConexionUsuario;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JButton btnIngresar = new JButton();
	private JButton btnCancelar = new JButton();
	static Login frameLogin;
	private  String roll;
	private int mensaje;
	private JLabel label_MENSAJE;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				StartVentana();
			
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	public static void StartVentana() {
		
		try {
			frameLogin = new Login();
			frameLogin.setVisible(true);
			frameLogin.setLocationRelativeTo(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public Login() {
		
		setAlwaysOnTop( true );
		setTitle("Pantalla de  Ingreso");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 540, 387);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		
		
		lblNewLabel_3 = new JLabel("User");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));

		lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));

		textField = new JTextField();
		textField.setColumns(10);

		passwordField = new JPasswordField();
		GroupLayout(lblNewLabel_3, lblNewLabel_2);

	}

	
	private void trabajar(String roll, String nombre) {
		
		if (roll.equals("USER") || roll.equals("ADMIN") || roll.equals("INVITADO")) {
			Windowsprincipal windowsprincipal = new Windowsprincipal();
			windowsprincipal.rolAccion(roll, nombre);
			windowsprincipal.iniciarVentana();
		}
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	
	private void ingresarBoton() {
		
		btnIngresar.setText("Ingresar");
		btnIngresar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("deprecation")
				
				//String password = EncriptadoPassword.encriptaPassword(passwordField.getText());
				String password = passwordField.getText();
				String usuario = textField.getText();
				
				ImplementaConexionUsuario impl = new ImplementaConexionUsuario(new User(usuario, password));
				
				if (impl.checkUser()) {
					roll = impl.llave();
					
					Windowsprincipal iniciar = new Windowsprincipal();
					iniciar.setRol(roll);
					iniciar.setUserName(usuario);
					iniciar.iniciarVentana();
				    frameLogin.setVisible(false);
				    
				}else {
					label_MENSAJE.setText("clave o usuario incorrecto");
				}
				
			}
		});
		
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	
	private void cancelarBoton() {
		btnCancelar.setText("Cancelar");
		
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				Windowsprincipal programa = new Windowsprincipal();
				//frame.setVisible(false);
				programa.closePrograma();
				
			}
		});
		

	}

	private JButton inyectaboton(JButton btn) {
		return btn;
	}

	
	
	public void GroupLayout(JLabel lblNewLabel, JLabel lblNewLabel_1) {

		ingresarBoton();
		cancelarBoton();
		
		JLabel lblIngreseSusDatos = new JLabel("Ingrese sus datos para accesar ");
		lblIngreseSusDatos.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		label_MENSAJE = new JLabel("");
		label_MENSAJE.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_MENSAJE.setForeground(Color.RED);

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(154)
							.addComponent(lblIngreseSusDatos))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(135)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblNewLabel_3)
									.addGap(70)
									.addComponent(textField, GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblNewLabel_2)
									.addGap(34)
									.addComponent(passwordField, GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)))))
					.addGap(122))
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(219, Short.MAX_VALUE)
					.addComponent(label_MENSAJE, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE)
					.addGap(114))
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(168, Short.MAX_VALUE)
					.addComponent(btnCancelar)
					.addGap(49)
					.addComponent(btnIngresar)
					.addGap(131))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(48)
					.addComponent(lblIngreseSusDatos)
					.addGap(38)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(32)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(label_MENSAJE)
					.addGap(26)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancelar)
						.addComponent(btnIngresar))
					.addContainerGap(67, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);

	}

	public int getMensaje() {
		return mensaje;
	}

	public void setMensaje(int mensaje) {
		this.mensaje = mensaje;
	}
	
	
	
	
}
