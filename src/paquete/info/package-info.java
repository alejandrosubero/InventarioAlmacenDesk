package paquete.info;

import java.awt.Choice;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

//import javax.swing.JOptionPane;

//JOptionPane.showMessageDialog(null, "Por el dia no se admiten mas pacientes");



/*DefaultTableModel modeloTabla;
DefaultTableCellRenderer alinearCentro, alinearDerecha, alinearIzquierda;

public Frame() {
    asignarModeloTabla();
    inicializarRenderers();
    initComponents();
}

private void asignarModeloTabla(){
    modeloTabla = new DefaultTableModel();
    modeloTabla.addColumn("Codigo");
    modeloTabla.addColumn("Nombre");
    modeloTabla.addColumn("Sueldo");
}

private void inicializarRenderers(){
     alinearCentro = new DefaultTableCellRenderer();
     alinearCentro.setHorizontalAlignment(SwingConstants.CENTER);

     alinearDerecha = new DefaultTableCellRenderer();
     alinearDerecha.setHorizontalAlignment(SwingConstants.RIGHT);

     alinearIzquierda = new DefaultTableCellRenderer();
     alinearIzquierda.setHorizontalAlignment(SwingConstants.LEFT);
}*/
















/*private void Screentocharge() {

	
	JTextField nombre = new JTextField();
	JTextField edad = new JTextField();
	JTextField raza = new JTextField();
	JTextField cuento = new JTextField();
	Choice tipo = new Choice();
					
	tipo.add("Perro");
	tipo.add("Gato");
	tipo.add("Conejo");
	
	edad.addKeyListener(new KeyAdapter()
	{
	   public void keyTyped(KeyEvent e)
	   {
	      char caracter = e.getKeyChar();
	      if(((caracter<'0')||(caracter>'9'))&&(caracter!='\b'))
	      {
	         e.consume(); 
	      }
	   }
	});
	
	Object[] message = { "Tipo:", tipo, "Nombre:", nombre, "Edad", edad, "Raza", raza, "Causa", cuento };

	UIManager.put("OptionPane.minimumSize",new Dimension(500,400));
	int option = JOptionPane.showConfirmDialog(null, message, "Ingreso de Pasiente", JOptionPane.OK_CANCEL_OPTION);
	if (option == JOptionPane.OK_OPTION) {	
		if (edad.getText().equals("")) {
			edad.setText("0");
			returnAnimal(tipo.getSelectedItem(), nombre.getText(), raza.getText(), 
					Integer.valueOf(edad.getText()), cuento.getText());
		}
		    returnAnimal(tipo.getSelectedItem(), nombre.getText(), raza.getText(), 
						Integer.valueOf(edad.getText()), cuento.getText());
	}
	
	nombre = null;
	edad =null;
	raza = null;
	cuento = null;
	tipo =null;
	
}*/
