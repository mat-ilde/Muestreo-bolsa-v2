package dao;

import java.sql.Connection;

import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mejoras.bolsa.web.CarteraMejora;
import mejoras.bolsa.web.HistoricoOperacionMejora;
import mejoras.bolsa.web.OperacionMejora;
import mysql.MysqlCartera;
import mysql.MysqlHistoricoOperacionDAO;
import mysql.MysqlOperacionActualDao;

import java.util.Random;
import java.util.UUID;


public class Main {

	public static void main(String[] args) throws Exception {

		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Mejorabolsa", "root", "hola123");
			// creating an operaciondao object and connected with a new connection

			//HACER QUE SE ACTUALICE LA INFORMACIÓN DE LAS ACCIONES AL VENDERLAS EN OPERACION ACTUAL
			
			OperacionActualDao opActual = new MysqlOperacionActualDao(conn);
			OperacionDao nuevaConexion= new MysqlHistoricoOperacionDAO(conn);
			//OperacionMejora obtenerId=new OperacionMejora();
			/*function call to set random Id
			obtenerId.setId();
			//function call to get an Id
			UUID id = obtenerId.getId();*/

			String fechaOperacionGoogle = "2016-03-31";
			Date datefechaOperacionGoogle = Date.valueOf(fechaOperacionGoogle);// converting string into sql date
			//OperacionActualDao h = new MysqlOperacionActualDao(conn);

			// creating an operacion object
			OperacionMejora operacionGoogle = new OperacionMejora("GOOC20150331","Google", (float) 350.00, 15, "compra",
					datefechaOperacionGoogle);
			OperacionMejora operacionGoogleVenta = new OperacionMejora("GOOV20160331","Google", (float) 350.00, 15, "venta",
					datefechaOperacionGoogle);
			
			// converting date as string
			String fechaOperacionAmazon = "2016-03-31";
			Date datefechaOperacionAmazon = Date.valueOf(fechaOperacionAmazon);// converting string into sql date
			
			OperacionMejora operacionAmazon = new OperacionMejora("AMZC20150331","Amazon", (float) 150.00, 10, "compra",
					datefechaOperacionAmazon);
			OperacionMejora operacionAmazonVenta = new OperacionMejora("AMZV20160331","Amazon", (float) 150.00, 5, "venta",
					datefechaOperacionAmazon);

			
			String fechaOperacionApple = "2016-03-31";
			Date datefechaOperacionApple = Date.valueOf(fechaOperacionApple);// converting string into sql date
			
			OperacionMejora operacionApple = new OperacionMejora("APPC20150331","Apple", (float) 100.00, 25, "compra",
					datefechaOperacionApple);
			OperacionMejora operacionAppleVenta = new OperacionMejora("APPV20160331","Apple", (float) 100.00, 15, "venta",
					datefechaOperacionApple);

			HistoricoOperacionMejora historicoGoogle = new HistoricoOperacionMejora();
								
			//nuevaConexion.insertBbdd(operacionApple);
			
			
			//nuevaConexion.TotalAccions(operacionGoogle, "Google");
			//String n=s.getTicker();
			//System.out.println(operacionAppleVenta.getTicker());
			
			
			nuevaConexion.sellActions(operacionAppleVenta, "Apple");
			//nuevaConexion.getOperacion("Amazon");	
			//opActual.updateData(operacionApple);
			
			//RETRIEVE THE OPERACIONS FROM DDBB
			//MysqlHistoricoOperacionDao my= new MysqlHistoricoOperacionDao(conn);
			/*for(OperacionMejora a: carteraGoogles.getRecordOperaciones()) {
				System.out.println(a);
			}*/
			//carteraGoogles.getOp();
			
			
			//System.out.println(historicoApple.getRecordOperaciones());	
					
			//Así es como tiene que salir en historico de operacion
			//System.out.println(carteraGoogleVenta.getCartera());
			//System.out.println(carteraGoogles.getCartera());

		} finally {

			if (conn != null) {
				conn.close();
			}
		}

	}

}
