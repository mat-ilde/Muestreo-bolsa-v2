package dao;

import java.util.List;

import mejoras.bolsa.web.HistoricoOperacionMejora;
import mejoras.bolsa.web.OperacionMejora;

public interface HistoricoOperacionDao extends Dao< HistoricoOperacionMejora, String> {

	void insertBbdd(OperacionMejora tipoDato) throws DaoExceptions;

	HistoricoOperacionMejora getOperacion(float precio);


}
