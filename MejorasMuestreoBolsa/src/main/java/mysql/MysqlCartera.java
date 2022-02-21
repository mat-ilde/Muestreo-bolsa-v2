package mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.DaoCartera;
import dao.DaoExceptions;
import mejoras.bolsa.web.CarteraMejora;
import mejoras.bolsa.web.HistoricoOperacionMejora;
import mejoras.bolsa.web.OperacionMejora;
import java.sql.Connection;
import java.sql.Date;

public class MysqlCartera implements DaoCartera {

	final String GETALLOPERACIONES = "SELECT Id,ticker,precio,tipo_operacion, fecha,numero_acciones FROM Operacion";
	private Connection conn;
	private List<OperacionMejora> operaciones = new ArrayList<>();

	public MysqlCartera(Connection conn) {

		this.conn = conn;

	}

	private OperacionMejora convert(ResultSet rs) throws SQLException {
		//java.util.UUID Id = ( java.util.UUID ) rs.getObject( "Id" );
		String Id = rs.getString("Id");
		String ticker = rs.getString("ticker");
		float precio = rs.getFloat("precio");
		int numeroAcciones = rs.getInt("numero_acciones");
		String tipoOperacion = rs.getString("tipo_operacion");
		Date fecha = rs.getDate("fecha");
		//String fecha = rs.getString("fecha");
		OperacionMejora nuevaOperacion = new OperacionMejora(Id,ticker, precio, numeroAcciones, tipoOperacion, fecha);
		return nuevaOperacion;

	}

	public List<OperacionMejora> getRecordOperaciones() throws DaoExceptions {

		PreparedStatement stat = null;
		ResultSet rs = null;
		List<OperacionMejora> operaciones = new ArrayList<>();

		try {
			stat = conn.prepareStatement(GETALLOPERACIONES);
			rs = stat.executeQuery();
			while (rs.next()) {

				operaciones.add(convert(rs));

			}

		} catch (SQLException e) {
			throw new DaoExceptions("Error en SQL", e);

		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					throw new DaoExceptions("Error en SQL", e);
				}
			}
			if (stat != null) {
				try {
					stat.close();
				} catch (SQLException e) {
					throw new DaoExceptions("Error en SQL", e);
				}
			}
		}

		return operaciones;
	}

	public List<OperacionMejora> getOp() throws DaoExceptions {

		List<OperacionMejora> ops = new ArrayList<>();
		MysqlCartera ps = new MysqlCartera(conn);
		ops=ps.getRecordOperaciones();
		int compra=0;
		int venta=0;
		int sum=0;
		for(OperacionMejora s:ops) {
			String t=s.getTipoOperacion();
			if(t.contentEquals("compra")) {
				compra=s.getNumeroAcciones();
			}
			if (t.contentEquals("venta")){
				venta=s.getNumeroAcciones();
			}
			sum=compra-venta;
			//s.setNumeroAcciones(sum);
			System.out.println(venta);
		}
		return ops;

	}

	public void insertBbdd(CarteraMejora a) throws DaoExceptions {
		// TODO Auto-generated method stub
		
	}

	public List<OperacionMejora> getOperaciones() throws DaoExceptions {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertBbdd(HistoricoOperacionMejora a) throws DaoExceptions {
		// TODO Auto-generated method stub
		
	}


	
	@Override
	public int TotalAccions(OperacionMejora operacion, String ticker) throws DaoExceptions {
		return 0;
	}

	@Override
	public void sellActions(OperacionMejora operacion, String ticker) throws DaoExceptions {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<OperacionMejora> getActualOperaciones() throws DaoExceptions {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteData(HistoricoOperacionMejora a) throws DaoExceptions {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void updateData(HistoricoOperacionMejora a, String b) throws DaoExceptions {
		// TODO Auto-generated method stub
		
	}

	

}