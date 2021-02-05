package paquete.info;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import com.inicio.StarteLogin;

public class test {

	public static void main(String[] args) {
		
		/*String FirsDate ="2019-10-05";
		
		LocalDate currentDate = LocalDate.now();
		
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu/MM/dd");

		LocalDate date1 = LocalDate.parse(FirsDate, formatter);
		LocalDate date2 = currentDate;

		long dayBetween = ChronoUnit.DAYS.between(date1, date2);

		System.out.println(dayBetween);*/
		
		
		StarteLogin lg = new StarteLogin();
		
		lg.checkDatabase();
		
		
		
	}

}
