package dao;

import java.util.List;

import mejoras.bolsa.web.CarteraMejora;
import mejoras.bolsa.web.HistoricoOperacionMejora;
import mejoras.bolsa.web.OperacionMejora;

@SuppressWarnings("unused")
public interface Dao<clase, tipoDatoClavePrimaria> {
	
	void insertBbdd(clase a) throws DaoExceptions;
	
	List<OperacionMejora> getOperaciones()throws DaoExceptions;
	
	clase getOperacion(float id) throws DaoExceptions, Exception;


	
	
}
