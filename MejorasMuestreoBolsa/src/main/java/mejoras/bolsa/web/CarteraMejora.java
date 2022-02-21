package mejoras.bolsa.web;

import java.util.ArrayList;
import java.util.List;

import dao.DaoExceptions;
import dao.OperacionActualDao;
import mysql.MysqlOperacionActualDao;

public class CarteraMejora {
	
	private ArrayList<OperacionMejora> operaciones=new ArrayList();
	public CarteraMejora() {

	}

	public List<OperacionMejora> getOperacionesRecientes() {
		return operaciones;

	}

}
