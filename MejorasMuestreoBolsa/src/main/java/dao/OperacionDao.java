package dao;

import mejoras.bolsa.web.OperacionMejora;

public interface OperacionDao extends  Dao<OperacionMejora , String> {


	OperacionMejora getOperacion(String ticker) throws DaoExceptions;

}
