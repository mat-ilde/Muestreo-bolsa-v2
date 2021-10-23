package mysql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.DaoExceptions;
import dao.OperacionDao;
import mejoras.bolsa.web.HistoricoOperacionMejora;
import mejoras.bolsa.web.OperacionMejora;

public class MysqlOperacionDAO implements OperacionDao {
	
	final String INSERT=" INSERT INTO Operacion(ticker,precio, numero_acciones,tipo_operacion,fecha) VALUES(?,?,?,?,?)";
	final String GETALLOPERACIONES="SELECT ticker,precio, numero_acciones,tipo_operacion,fecha FROM Operacion";
	final String GETOPERACION="SELCECT ticker,precio, numero_acciones,tipo_operacion,fecha WHERE ticker=? ";
	private Connection conn;
	public MysqlOperacionDAO(Connection conn) {
		this.conn=conn;
	}
	@Override
	public void insertBbdd(OperacionMejora tipoDato) throws DaoExceptions {
		PreparedStatement stat= null;		
		try {
			
			stat= conn.prepareStatement(INSERT);
			stat.setString(1, tipoDato.getTicker());
			stat.setFloat(2, tipoDato.getPrecio());
			stat.setInt(3, tipoDato.getNumeroOperaciones());
			stat.setString(4, tipoDato.getTipoOperacion());
			stat.setDate(5,new Date((tipoDato.getFecha().getTime())));
			if(stat.executeUpdate()==0) {
				throw new  DaoExceptions("No se han insertado los datos correctamente");
			}
			else {
				System.out.println("Datos introducidos correctamente");
			}
		} catch (SQLException ex) {
			throw new  DaoExceptions("Error en SQL ", ex);
		}finally{
			if(stat !=null) {
				try {
					stat.close();
				}catch(SQLException ex){
					throw new  DaoExceptions("Error en SQL ", ex);
				}
			}
		}
	}
	//método que toma los datos de la base de datos y los convierte en objeto operacion
	private OperacionMejora convert(ResultSet rs) throws SQLException {
		
		String ticker= rs.getString("ticker");
		float precio= rs.getFloat("precio");
		int numeroAcciones=rs.getInt("numero_acciones");
		String tipoOperacion=rs.getString("tipo_operacion");
		Date fecha =rs.getDate("fecha");
		OperacionMejora nuevaOperacion= new OperacionMejora(ticker,precio, numeroAcciones,tipoOperacion,fecha);
		return nuevaOperacion;
		
	}
	@Override
	public OperacionMejora getOperacion(String ticker) throws DaoExceptions {
		
		PreparedStatement stat=null;
		ResultSet rs=null;
		OperacionMejora operacion= null;
		try {
			stat.setString(1, ticker);
			rs=stat.executeQuery();
			if(rs.next()) {
				
				operacion=convert(rs);
			}else {
				throw new  DaoExceptions("No tienes operaciones con ese ticker ");
			}
			
		} catch (DaoExceptions | SQLException e) {
			throw new  DaoExceptions("Error en SQL",e);
		}
		try {
			
			try {
				stat= conn.prepareStatement(GETOPERACION);
			} catch (SQLException ex) {
				throw new  DaoExceptions("Error en SQL ", ex);	
		}
		}finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					throw new  DaoExceptions("Error en SQL",e);
				}
			}if(stat!=null) {
				try {
					stat.close();
				} catch (SQLException e) {
					throw new  DaoExceptions("Error en SQL",e);
				}
			}
		}
		
		return operacion;
	}
																									
	@Override
	public List<OperacionMejora> getOperaciones() throws DaoExceptions {
		PreparedStatement stat = null;
		ResultSet rs=null;
		List<OperacionMejora> operaciones= new ArrayList<>();
		try {
			stat=conn.prepareStatement(GETALLOPERACIONES);
			rs=stat.executeQuery();
			while(rs.next()) {
				operaciones.add(convert(rs));
			}
			
		} catch (SQLException e) {
			throw new  DaoExceptions("Error en SQL",e);
		}
		try {
			
			try {
				stat= conn.prepareStatement(GETALLOPERACIONES);
			} catch (SQLException ex) {
				throw new  DaoExceptions("Error en SQL ", ex);
		}
		}finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					throw new  DaoExceptions("Error en SQL",e);
				}
			}if(stat!=null) {
				try {
					stat.close();
				} catch (SQLException e) {
					throw new  DaoExceptions("Error en SQL",e);
				}
			}
		}
		
		return operaciones;
	}
	@Override
	public HistoricoOperacionMejora getData() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
