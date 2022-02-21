package dao;

import java.util.List;

import mejoras.bolsa.web.HistoricoOperacionMejora;
import mejoras.bolsa.web.OperacionMejora;

public interface OperacionActualDao extends Dao< OperacionMejora, String> {

	

	List<OperacionMejora> getOperaciones()throws DaoExceptions;

	void insertBbdd(OperacionMejora a) throws DaoExceptions;
	OperacionMejora getOperacionActual(String ticker) throws DaoExceptions;


}
