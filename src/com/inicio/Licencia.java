package com.inicio;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import javax.swing.JOptionPane;

import com.view.Login;

public class Licencia {

	private long duracion;
	private String FirsDate;
	private LocalDate currentDate;
	private int mensajelicencia;
	
	
	public Licencia() {
		
	}

	
	public void checkLicencia(Statement statement) {
		ValidarLicencia(getDataLicencia(statement));
	}
	

	
	private String getDataLicencia(Statement statement) {
		
		String FirsDate="";
		try {
			ResultSet rt = statement.executeQuery("select * from licencia1;");
			while (rt.next()) {
				FirsDate=rt.getString(2);
			}
			rt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return FirsDate;
	}
	
	

	private boolean ValidarLicencia(String uno) {

		boolean acceso=false;
		LocalDate currentDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd");
		//System.out.println(uno);
		
		LocalDate date1 = LocalDate.parse(uno, formatter);
		
		LocalDate date2 = currentDate;
		long dayBetween = ChronoUnit.DAYS.between(date1, date2);
		
		if (dayBetween > duracion) {
			mensajelicencia=1;
			
		}else if ((duracion-dayBetween < 11 )) {
			mensajelicencia=0;
			
			acceso=true;
		}else {
			acceso=true;
		}
		return acceso;
	}

	
	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/*private void actualizalicencia() {

Login login = new Login();
login.setMensaje(1);

if (mensaje ==1) {
JOptionPane.showMessageDialog(null, "La  licencia no esta vigente debe de actualizar la licencia");
}else if (mensaje==0) {
JOptionPane.showMessageDialog(null, "Solo le quedan 10 dias de licencia vigente");
}

}*/


/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	
	public long getDuracion() {
		return duracion;
	}


	public void setDuracion(long duracion) {
		this.duracion = duracion;
	}


	public String getFirsDate() {
		return FirsDate;
	}


	public void setFirsDate(String firsDate) {
		FirsDate = firsDate;
	}


	@Override
	public String toString() {
		return "Licencia [duracion=" + duracion + ", FirsDate=" + FirsDate + ", currentDate=" + currentDate + "]";
	}
	
	
	
	
}
