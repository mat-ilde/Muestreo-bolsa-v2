package dao;

import java.util.List;

import mejoras.bolsa.web.HistoricoOperacionMejora;
import mejoras.bolsa.web.OperacionMejora;

public interface Dao<clase, tipoDatoClavePrimaria> {
	
	void insertBbdd(clase tipoDato) throws DaoExceptions;
	List<OperacionMejora> getOperaciones()throws DaoExceptions;
	OperacionMejora getOperacion(String ticker) throws DaoExceptions, Exception;
	HistoricoOperacionMejora getData();
}
