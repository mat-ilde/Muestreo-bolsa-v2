package dao;

import java.util.List;

import mejoras.bolsa.web.CarteraMejora;
import mejoras.bolsa.web.OperacionMejora;

public interface CarteraDao extends Dao<CarteraMejora, String> {

	List<OperacionMejora> getRecordOperaciones() throws DaoExceptions;
	
	
		
}