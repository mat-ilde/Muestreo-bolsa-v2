package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import mejoras.bolsa.web.HistoricoOperacionMejora;
import mejoras.bolsa.web.OperacionMejora;
import mysql.MysqlOperacionDAO;

public class Main {

	public static void main(String[] args) throws SQLException, DaoExceptions {
		
		Connection conn=null;
		try {
			 conn =  DriverManager.getConnection("jdbc:mysql://localhost:3306/Mejorabolsa","root", "hola123");
			 //creo un objeto operaciondao y le añado una nueva conexión													//arreglar las fechas!!!
			 OperacionDao dao= new MysqlOperacionDAO(conn);
			 //creo un objeto operacion
			 @SuppressWarnings("deprecation")
			OperacionMejora operacionGoogle = new OperacionMejora("Google",(float) 250.00,25,"compra",new Date(2020,10,25));
			 @SuppressWarnings("deprecation")
			OperacionMejora operacionAmazon= new OperacionMejora("Amazon",(float) 150.00,25,"compra",new Date(2019,05,12));
			 @SuppressWarnings("deprecation")
			OperacionMejora operacionApple = new OperacionMejora("Apple",(float) 100.00,25,"compra",new Date(2018,07,15));
			 //le agrego un nuevo precio
			 //operacion.setPrecio(25.00);
			 // añado esa operación a la base de datos
			 dao.insertBbdd(operacionGoogle);
			 dao.insertBbdd(operacionAmazon);
			 dao.insertBbdd(operacionApple);
			 //dao.getOperacion("Amazon");
			 HistoricoOperacionMejora historico =new HistoricoOperacionMejora();
			 historico.setOperacion(operacionGoogle);
			 historico.setOperacion(operacionAmazon);
			 historico.setOperacion(operacionApple);
			 //historico.getOperacion(operacion);
			 
			 // crearía una lista porque ese método devuelve un array
			 /*List<OperacionMejora> operaciones=dao.getOperaciones();
			 for(OperacionMejora a:operaciones) {
				 System.out.println();
			 }*/
		}finally{
			
			if(conn!=null) {
				conn.close();
			}
		}

	}

}
