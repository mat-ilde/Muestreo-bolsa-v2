package mysql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.DaoExceptions;
import dao.HistoricoOperacionDao;
import mejoras.bolsa.web.HistoricoOperacionMejora;
import mejoras.bolsa.web.OperacionMejora;

public class MysqlHistoricoOperacionDao implements HistoricoOperacionDao {

	final String INSERT = " INSERT INTO HistoricoOperacion(ticker,precio,tipo_operacion, fecha ,numero_acciones) VALUES(?,?,?,?,?)ON DUPLICATE KEY UPDATE ticker=ticker,precio=precio,tipo_operacion=tipo_operacion, fecha=fecha, numero_acciones=numero_acciones";
	final String GETALLOPERACIONES = "SELECT ticker,precio,tipo_operacion, fecha,numero_acciones FROM HistoricoOperacion";
	final String GETOPERACION = "SELECT ticker,precio,tipo_operacion, fecha,numero_acciones WHERE ticker=? ";
	private Connection conn;
	private OperacionMejora operacion;

	public MysqlHistoricoOperacionDao(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insertBbdd(OperacionMejora tipoDato) throws DaoExceptions {
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(INSERT);
			stat.setString(1, tipoDato.getTicker());
			stat.setFloat(2, tipoDato.getPrecio());
			stat.setString(3, tipoDato.getTipoOperacion());
			stat.setDate(4, new Date((tipoDato.getFecha().getTime())));
			stat.setInt(5, tipoDato.getNumeroAcciones());

			if (stat.executeUpdate() == 0) {
				throw new DaoExceptions("No se han insertado los datos correctamente");
			} else {
				System.out.println("Datos introducidos correctamente " + tipoDato.toString());
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

	// método que devuelve los datos en forma de objeto
	private OperacionMejora convert(ResultSet rs) throws SQLException {

		String ticker = rs.getString("ticker");
		float precio = rs.getFloat("precio");
		int numeroAcciones = rs.getInt("numero_acciones");
		String tipoOperacion = rs.getString("tipo_operacion");
		Date fecha = rs.getDate("fecha");
		OperacionMejora nuevaOperacion = new OperacionMejora(ticker, precio, numeroAcciones, tipoOperacion, fecha);
		return nuevaOperacion;

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

	/*public float getPrecio() throws DaoExceptions {

		PreparedStatement stat = null;
		ResultSet rs = null;
		OperacionMejora operacion = null;
		float precio;
		try {
			stat = conn.prepareStatement(GETOPERACION);

			rs = stat.executeQuery();

			precio = operacion.getPrecio();

		} catch (SQLException e) {
			throw new DaoExceptions("Error en SQL", e);
		}
		try {

			try {
				stat = conn.prepareStatement(GETOPERACION);
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

		return precio;
	}
	public String tipoOperacion() throws DaoExceptions {

		PreparedStatement stat = null;
		ResultSet rs = null;
		OperacionMejora operacion = null;
		String tipoOperacion;
		try {
			stat = conn.prepareStatement(GETOPERACION);

			rs = stat.executeQuery();

			tipoOperacion = operacion.getTipoOperacion();

		} catch (SQLException e) {
			throw new DaoExceptions("Error en SQL", e);
		}
		try {

			try {
				stat = conn.prepareStatement(GETOPERACION);
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

		return tipoOperacion;
	}


	public int getNumeroAcciones() throws DaoExceptions {

		PreparedStatement stat = null;
		ResultSet rs = null;
		
		int accion;
		try {
			stat = conn.prepareStatement(GETOPERACION);

			rs = stat.executeQuery();

			accion = operacion.getNumeroAcciones();

		} catch (SQLException e) {
			throw new DaoExceptions("Error en SQL", e);
		}
		try {

			try {
				stat = conn.prepareStatement(GETOPERACION);
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

		return accion;
	}
*/
	@Override
	public HistoricoOperacionMejora getOperacion(float precio) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertBbdd(HistoricoOperacionMejora a) throws DaoExceptions {
		// TODO Auto-generated method stub

	}

}
