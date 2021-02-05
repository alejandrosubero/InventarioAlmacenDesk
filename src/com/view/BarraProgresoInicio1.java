package com.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.Timer;

import com.inicio.StarteLogin;

public class BarraProgresoInicio1 {

	private JFrame frame;
	private Timer timer;
	private ActionListener ac;
	private int incremento = 0;
	private JProgressBar progressBar = new JProgressBar();
	JLabel lblNewLabel = new JLabel();
	StarteLogin st = new StarteLogin();
	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BarraProgresoInicio1 window = new BarraProgresoInicio1();
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
	public BarraProgresoInicio1() {
		initialize();
		frame.setLocationRelativeTo(null);
		ac= new ActionListener() {
			
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				incremento =incremento+1;
				progressBar.setValue(incremento);
				
				
				if (progressBar.getValue()==3) {
					
					lblNewLabel.setText("Cargando bases de datos...");
					lblNewLabel.setText("Cargando intancias");
					st.checkDatabase();
					lblNewLabel.setText(" Finalizando carga....");
				}
				
				
				if (progressBar.getValue()==10) {
					
					//Windowsprincipal iniciar = new Windowsprincipal();
					//iniciar.iniciarVentana();
					
					Login.StartVentana();
					frame.setVisible(false);
					timer.stop();
				}
			}
		};
		
		timer = new Timer(200, ac); 
		timer.start();
	}


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Iniciando Control de Almacen");
		frame.setAlwaysOnTop(true);
		frame.setBounds(100, 100, 540, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		progressBar.setMaximum(10);
		progressBar.setForeground(Color.BLUE);
		
		progressBar.setBounds(47, 105, 435, 33);
		frame.getContentPane().add(progressBar);
		
		lblNewLabel.setText("New label");
		lblNewLabel.setBounds(233, 62, 195, 16);
		frame.getContentPane().add(lblNewLabel);
		
	}

}
