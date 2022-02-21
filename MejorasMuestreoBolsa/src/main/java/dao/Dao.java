package dao;

import java.util.List;

import mejoras.bolsa.web.CarteraMejora;
import mejoras.bolsa.web.HistoricoOperacionMejora;
import mejoras.bolsa.web.OperacionMejora;

@SuppressWarnings("unused")
public interface Dao<clase, tipoDatoClavePrimaria> {
	
	void insertBbdd(clase a) throws DaoExceptions;
	void deleteData(clase a) throws DaoExceptions;
	void updateData(clase a,String b) throws DaoExceptions;
	List<OperacionMejora> getOperaciones()throws DaoExceptions;

	List<OperacionMejora> getActualOperaciones() throws DaoExceptions;
	int TotalAccions(OperacionMejora operacion,String ticker) throws DaoExceptions;
	public void sellActions(OperacionMejora operacion,String ticker)throws DaoExceptions;
	


	
	
}
