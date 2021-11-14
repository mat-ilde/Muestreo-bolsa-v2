package mejoras.bolsa.web;

import java.util.ArrayList;
import java.util.List;

import dao.DaoExceptions;
import dao.HistoricoOperacionDao;
import mysql.MysqlHistoricoOperacionDao;

public class CarteraMejora {

	private MysqlHistoricoOperacionDao historico;

	private List<CarteraMejora> operaciones = new ArrayList<CarteraMejora>();
	
	public CarteraMejora(HistoricoOperacionDao historico) {
		this.historico = (MysqlHistoricoOperacionDao) historico;

	}

	public List<OperacionMejora> getAcciones(HistoricoOperacionDao historico) throws DaoExceptions {
		return historico.getOperaciones();

	}
	
		
}
	

