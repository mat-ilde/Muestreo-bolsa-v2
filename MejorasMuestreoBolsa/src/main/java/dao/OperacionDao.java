package dao;

import mejoras.bolsa.web.OperacionMejora;

public interface OperacionDao extends  Dao<OperacionMejora , String> {


	OperacionMejora getOperacion(String ticker) throws DaoExceptions;
	void insertBbdd(OperacionMejora a) throws DaoExceptions;
	int TotalAccions(OperacionMejora a,String ticker) throws DaoExceptions;

}
