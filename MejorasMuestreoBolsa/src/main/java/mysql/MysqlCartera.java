package mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.DaoExceptions;
import mejoras.bolsa.web.OperacionMejora;
import java.sql.Connection;

public class MysqlCartera {

	final String GETALLOPERACIONES = "SELECT ticker,precio,tipo_operacion, fecha,numero_acciones FROM HistoricoOperacion";
	private Connection conn;
	private OperacionMejora historico;

	public MysqlCartera(OperacionMejora historico, Connection conn) {
		
		this.conn = conn;
		this.historico=historico;
	}

	public void getCartera() throws DaoExceptions {

		PreparedStatement stat = null;
		ResultSet rs = null;
		List<OperacionMejora> operaciones = new ArrayList<>();
		float precio;
		try {
			stat = conn.prepareStatement(GETALLOPERACIONES);

			rs = stat.executeQuery();
			while (rs.next()) {
				operaciones.add(Shistorico);
				for (OperacionMejora historico : operaciones) {
					System.out.println(historico.getPrecio());
				}

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

	}
}