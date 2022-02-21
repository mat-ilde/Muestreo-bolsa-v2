

package mysql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import dao.DaoExceptions;
import dao.OperacionActualDao;
import mejoras.bolsa.web.CarteraMejora;
import mejoras.bolsa.web.HistoricoOperacionMejora;
import mejoras.bolsa.web.OperacionMejora;

//operaciones recientes
public class MysqlOperacionActualDao implements OperacionActualDao {
	
	
	final String INSERT = "INSERT INTO Operacion(Id,ticker,precio, numero_acciones,tipo_operacion,fecha) VALUES(?,?,?,?,?,?) ON DUPLICATE KEY UPDATE Id=Id,ticker=ticker,precio=precio,numero_acciones=numero_acciones,tipo_operacion=tipo_operacion,fecha=fecha";
	final String GETALLOPERACIONES = "SELECT Id,ticker,precio,tipo_operacion, fecha,numero_acciones FROM Operacion";
	final String DELETE ="DELETE  FROM Operacion WHERE Id=?" ;
	final String GETOPERACION = "SELECT Id,ticker,precio, numero_acciones,tipo_operacion,fecha from Operacion where ticker = ?";
	final String UPDATE=" UPDATE Operacion SET  Id=Id,ticker=ticker,precio=precio,numero_acciones=numero_acciones,tipo_operacion=tipo_operacion,fecha=fecha where Id=?";
	private Connection conn;
	public OperacionMejora operacion;
	private List<OperacionMejora> operaciones = new ArrayList<>();

	public MysqlOperacionActualDao(Connection conn) {
		this.conn = conn;
	}

	// método que devuelve los datos en forma de objeto
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

	@Override
	public List<OperacionMejora> getActualOperaciones() throws DaoExceptions {
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
		}
		try {

			try {
				stat = conn.prepareStatement(GETALLOPERACIONES);
			} catch (SQLException ex) {
				throw new DaoExceptions("Error en SQL ", ex);
			}
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
	

	@Override
	public List<OperacionMejora> getOperaciones() throws DaoExceptions {
		// TODO Auto-generated method stub
		return null;
	}

	public void insertBbdd(OperacionMejora tipoDato) throws DaoExceptions {
		PreparedStatement stat = null;
		operaciones.add(tipoDato);
		//System.out.println("Antes de insertar");
		try {
			//System.out.println("dentro de insertar");
			stat = conn.prepareStatement(INSERT);
			stat.setObject(1, tipoDato.getId());
			//stat.setString(1, tipoDato.getId());
			stat.setString(2, tipoDato.getTicker());
			stat.setFloat(3, tipoDato.getPrecio());
			stat.setInt(4, tipoDato.getNumeroAcciones());
			stat.setString(5, tipoDato.getTipoOperacion());
			stat.setDate(6, new Date(tipoDato.getFecha().getTime()));
			//stat.setString(6, tipoDato.getFecha());
			if (stat.executeUpdate() == 0) {
				throw new DaoExceptions("No se han insertado los datos correctamente");
			} else {
				System.out.println("Datos introducidos correctamente: " + tipoDato.toString());
			}
		} catch (SQLException ex) {
			throw new DaoExceptions("Error en SQL ", ex);
		} finally {
			if (stat != null) {
				try {
					stat.close();
				} catch (SQLException ex) {
					throw new DaoExceptions("Error en SQL ", ex);
				}
			}
		}
	}
	@Override
	public OperacionMejora getOperacionActual(String ticker) throws DaoExceptions {
		try {

			PreparedStatement pst = (PreparedStatement) this.conn.prepareStatement(GETOPERACION);
			pst.setString(1, ticker);
			ResultSet set = pst.executeQuery();
			while (set.next()) {
				String Id = set.getString("Id");
				/*byte[] Id =(byte[]) set.getObject( "Id" );
				String identificacion=getGuidFromByteArray(Id);
				UUID id = ( java.util.UUID ) set.getObject( identificacion );*/
			
				String nombreAccion = set.getString("ticker");
				float precio = set.getFloat("precio");
				int numeroAcciones = set.getInt("numero_acciones");
				String tipoOperacion = set.getString("tipo_operacion");
				Date fecha = set.getDate("fecha");
				if (set.next()) {
					OperacionMejora operacione = new OperacionMejora(Id,ticker, precio, numeroAcciones, tipoOperacion,
							fecha);
					operacion = convert(set);
				}
				
				System.out.println("Id: " + Id + " Ticker: " + nombreAccion + " Precio: " + precio + " " + " Número de acciones: "
						+  numeroAcciones + " Tipo de Operación: " + tipoOperacion);

			}

			set.close();
			pst.close();
		} catch (SQLException e) {
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return operacion;

	}


	@Override
	public void deleteData(OperacionMejora tipoDato) throws DaoExceptions {
		PreparedStatement stat = null;
		operaciones.add(tipoDato);
		
		try {
			//System.out.println("dentro de insertar");
			stat = conn.prepareStatement(DELETE);
			stat.setObject(1, tipoDato.getId());
			if (stat.executeUpdate() == 0) {
				throw new DaoExceptions("No se han eliminado los datos correctamente");
			} else {
				System.out.println("Datos eliminados correctamente: " + tipoDato.toString());
			}
		} catch (SQLException ex) {
			throw new DaoExceptions("Error en SQL ", ex);
		} finally {
			if (stat != null) {
				try {
					stat.close();
				} catch (SQLException ex) {
					throw new DaoExceptions("Error en SQL ", ex);
				}
			}
		}
		
	}
	@Override
	public void updateData(OperacionMejora tipoDato, String ticker) throws DaoExceptions {
		PreparedStatement stat = null;
		operaciones.add(tipoDato);
		OperacionActualDao opActual=new MysqlOperacionActualDao(conn);	
		String idActual=opActual.getOperacionActual(ticker).getId();
		String Id;
		
		
		HistoricoOperacionMejora Hm=new HistoricoOperacionMejora();
		if(idActual!=tipoDato.getId()) {
			Id=Hm.getId();
			Hm.setId(Id);
		}
		try {
			stat = conn.prepareStatement(UPDATE);
			stat.setObject(1, Hm.getId());
			if (stat.executeUpdate() == 0) {
				throw new DaoExceptions("No se han eliminado los datos correctamente");
			} else {
				System.out.println("Datos eliminados correctamente: " + tipoDato.toString());
			}
		} catch (SQLException ex) {
			throw new DaoExceptions("Error en SQL ", ex);
		} finally {
			if (stat != null) {
				try {
					stat.close();
				} catch (SQLException ex) {
					throw new DaoExceptions("Error en SQL ", ex);
				}
			}
		}
			
}
	

	@Override
	public int TotalAccions(OperacionMejora operacion, String ticker) throws DaoExceptions {
		return 0;	
	}

	@Override
	public void sellActions(OperacionMejora operacion, String ticker) throws DaoExceptions {
		// TODO Auto-generated method stub
		
	}

	

	
	

	


}
