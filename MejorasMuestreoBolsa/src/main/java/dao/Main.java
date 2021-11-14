package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import mejoras.bolsa.web.CarteraMejora;
import mejoras.bolsa.web.HistoricoOperacionMejora;
import mejoras.bolsa.web.OperacionMejora;
import mysql.MysqlCartera;
import mysql.MysqlHistoricoOperacionDao;
import mysql.MysqlOperacionDAO;

public class Main {

	public static void main(String[] args) throws Exception {

		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Mejorabolsa", "root", "hola123");
			// creo un objeto operaciondao y le añado una nueva conexión //arreglar las
			// fechas!!!

			OperacionDao dao = new MysqlOperacionDAO(conn);

			HistoricoOperacionDao h = new MysqlHistoricoOperacionDao(conn);
			// creo un objeto operacion
			@SuppressWarnings("deprecation")
			OperacionMejora operacionGoogle = new OperacionMejora("Google", (float) 250.00, 25, "compra",
					new Date(2020, 10, 25));

			@SuppressWarnings("deprecation")
			OperacionMejora operacionAmazon = new OperacionMejora("Amazon", (float) 150.00, 25, "compra",
					new Date(2019, 05, 12));

			@SuppressWarnings("deprecation")
			OperacionMejora operacionApple = new OperacionMejora("Apple", (float) 100.00, 25, "compra",
					new Date(2018, 07, 15));

			HistoricoOperacionMejora historico = new HistoricoOperacionMejora("Google", (float) 250.00, "compra",
					new Date(2020, 10, 25), 500);
			HistoricoOperacionMejora historic = new HistoricoOperacionMejora("Google", (float) 225.00, "compra",
					new Date(2020, 10, 25), 500);

			//System.out.println(h.getOperaciones());

			/*h.insertBbdd(operacionApple);
			h.insertBbdd(operacionAmazon);
			h.insertBbdd(operacionGoogle); // le agrego un nuevo precio
			// operacion.setPrecio(25.00); // añado esa operación a la base de datos
			/*
			 * dao.insertBbdd(operacionGoogle); dao.insertBbdd(operacionAmazon);
			 * dao.insertBbdd(operacionApple); //dao.getOperacion("Amazon");
			 * 
			 * // crearía una lista porque ese método devuelve un array
			 * /*List<OperacionMejora> operaciones=dao.getOperaciones(); for(OperacionMejora
			 * a:operaciones) { System.out.println(); }
			 */
			// trae las operaciones desde la base de datos a través de la clase carpeta
			//CarteraMejora cartera =new CarteraMejora(h);
			MysqlCartera cartera=new MysqlCartera (operacionAmazon, conn);
			cartera.getCartera();
			
		} finally {

			if (conn != null) {
				conn.close();
			}
		}

	}

}
