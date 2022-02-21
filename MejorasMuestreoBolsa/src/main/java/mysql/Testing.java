package mysql;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import dao.DaoExceptions;
import dao.OperacionDao;
import mejoras.bolsa.web.OperacionMejora;

class Testing {
	private Connection conn;
	@Test
	void testSellOperation(OperacionMejora operacion,String ticker) throws DaoExceptions, SQLException {
		
		int numeroAccionesCompra = 0;
		int numeroAccionesVenta = 0;
		int numeroAccionesFinal=0;
		//MysqlHistoricoOperacionActualDao historicoOperacion= new MysqlHistoricoOperacionActualDao(conn);
		//HistoricoOperacionDao h = new MysqlOperacionActualDao(conn);
		OperacionDao op= new MysqlHistoricoOperacionDAO(conn);
		//System.out.println(op.getOperacion(ticker).getTipoOperacion());
		
		conn.close();
	}
	
}
