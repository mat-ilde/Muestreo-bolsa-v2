package mysql;

import java.nio.ByteBuffer;
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
import dao.OperacionDao;
import mejoras.bolsa.web.CarteraMejora;
import mejoras.bolsa.web.HistoricoOperacionMejora;
import mejoras.bolsa.web.OperacionMejora;

public class MysqlHistoricoOperacionDAO implements OperacionDao {
	//ON DUPLICATE KEY UPDATE
	final String INSERT = "INSERT INTO Historico_operacion(Id,ticker,precio, numero_acciones,tipo_operacion,fecha) VALUES(?,?,?,?,?,?)" /*Id=Id,ticker=ticker,precio=precio,numero_acciones=numero_acciones,tipo_operacion=tipo_operacion,fecha=fecha";*/;
	final String GETOPERACION = "SELECT Id,ticker,precio, numero_acciones,tipo_operacion,fecha from Historico_operacion where ticker = ?";
	final String GETALLOPERACIONES = "SELECT Id,ticker,precio,numero_acciones,tipo_operacion,fecha from Historico_operacion";
	private Connection conn;
	public OperacionMejora operacion;
	public List<OperacionMejora> operaciones=new ArrayList();
	private MysqlOperacionActualDao operacionActual;
	private  String operacione;
	

	public MysqlHistoricoOperacionDAO(Connection conn) {
		this.conn = conn;
	}
	
	//functions to return total of actions bought of a company 
	public int TotalAccions(OperacionMejora operacion,String ticker) throws DaoExceptions {
		
		int numeroAccionesCompra = 0;
		
		OperacionActualDao opActual=new MysqlOperacionActualDao(conn);
		for(OperacionMejora operacionFromBdDd: opActual.getActualOperaciones()) {
			
			if (operacionFromBdDd.getTipoOperacion().equals("compra")&& operacionFromBdDd.getTicker().equals(ticker)) {
				numeroAccionesCompra =operacionFromBdDd.getNumeroAcciones();
			}
			/*if(operacion.getTipoOperacion().equals("venta")&& operacionFromBdDd.getTicker().equals(ticker)) {
				numeroAccionesVenta= operacion.getNumeroAcciones();
			}*/
			
		}
		//numeroAccionesFinal=numeroAccionesCompra-numeroAccionesVenta;
		return numeroAccionesCompra;
	}
	
/* si el resultado de la función anterior es menor que el numero de acciones que quiero vender
 * teniendo en cuenta el numero de acciones que recibo mediante parametros= OperacionMejora y ticker
 * accediendo a través de parametro miro si la cantidad que quiero vender no excede la que tengo comprada
 * */

	@Override
	public void sellActions(OperacionMejora operacion, String ticker) throws DaoExceptions {
		int numeroAccionesFinal = 0;
		OperacionActualDao opActual=new MysqlOperacionActualDao(conn);
		HistoricoOperacionMejora opH= new HistoricoOperacionMejora();
		OperacionDao op= new MysqlHistoricoOperacionDAO(conn);
		boolean mayor=false;
		int acc=0;
		if(op.TotalAccions(operacion, ticker)>=operacion.getNumeroAcciones()) {
			numeroAccionesFinal=op.TotalAccions(operacion, ticker)-operacion.getNumeroAcciones();
			mayor=true;
			//cambio el valor de las acciones de la operacion
			//operacion.setNumeroAcciones(numeroAccionesFinal);
			//inserto la venta de la acción en la base de datos
			op.insertBbdd(operacion);
			operacion.setNumeroAcciones(numeroAccionesFinal);
			//si es mayor que 0
			if(mayor==true) {
				for(OperacionMejora opM:opActual.getActualOperaciones()) {
					
					if(opM.getTipoOperacion().equals("compra")&& opM.getTicker().equals(ticker) ) {
						
						acc=opM.getNumeroAcciones();
						opActual.deleteData(opM.getOperacion());
						opActual.insertBbdd(operacion);
						
						
					}
				}
				
					
			}
		}
		if(op.TotalAccions(operacion, ticker)<operacion.getNumeroAcciones() && op.TotalAccions(operacion, ticker)>0) {
			System.out.println("Tu total de acciones es: "  + acc + " No tienes suficiente acciones de " + operacion.getTicker() + " para vender ");
		}
		
		//TENER EN CUENTA QUE NO TENGA NINGUNA ACCION
}
		


	@Override
	public void insertBbdd(OperacionMejora tipoDato) throws DaoExceptions {
		PreparedStatement stat = null;
		OperacionActualDao opActual=new MysqlOperacionActualDao(conn);
		
		
		
		try {
			if(tipoDato.getTipoOperacion().equals("compra")) {
				opActual.insertBbdd(tipoDato);
			}
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
				//operaciones.add(tipoDato);
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
	
	// método que toma los datos de la base de datos y los convierte en objeto
	// operacion
	private OperacionMejora convert(ResultSet rs) throws SQLException {
		//java.util.UUID Id = ( java.util.UUID ) rs.getObject( "Id" );
		String Id = rs.getString("Id");
		String ticker = rs.getString("ticker");
		float precio = rs.getFloat("precio");

		int numero_acciones = rs.getInt("numero_acciones");
		String tipoOperacion = rs.getString("tipo_operacion");
		Date fecha = rs.getDate("fecha");
		OperacionMejora nuevaOperacion = new OperacionMejora(Id,ticker, precio, numero_acciones, tipoOperacion, fecha);
		return nuevaOperacion;

	}
	
	// convert the a byte array to string 
	public static String getGuidFromByteArray(byte[] bytes) {
	    ByteBuffer bb = ByteBuffer.wrap(bytes);
	    long high = bb.getLong();
	    long low = bb.getLong();
	    UUID uuid = new UUID(high, low);
	    return uuid.toString();
	}
	//convert UUID to byte
	public byte[] getIdAsByte(UUID uuid)
	{
	    ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
	    bb.putLong(uuid.getMostSignificantBits());
	    bb.putLong(uuid.getLeastSignificantBits());
	    return bb.array();
	}
	

	@Override
	public OperacionMejora getOperacion(String ticker) throws DaoExceptions {
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
				
				//System.out.println("Ticker: " + nombreAccion + " Precio: " + precio + " " + " Número de acciones: "
						//+  numeroAcciones + " Tipo de Operación: " + tipoOperacion);

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
	public List<OperacionMejora> getOperaciones() throws DaoExceptions {
	
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
	public List<OperacionMejora> getActualOperaciones() throws DaoExceptions {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteData(OperacionMejora a) throws DaoExceptions {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public void updateData(OperacionMejora a, String b) throws DaoExceptions {
		// TODO Auto-generated method stub
		
	}

	
	

	



	

}
