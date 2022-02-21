package mejoras.bolsa.web;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import dao.DaoExceptions;

public class HistoricoOperacionMejora {

	private ArrayList<OperacionMejora> operaciones = new ArrayList();
	private OperacionMejora operacion;
	private String Id;
	private String ticker;

	public HistoricoOperacionMejora( ) {
		super();

	}

	public List<OperacionMejora> getRecordOperaciones() {
		return operaciones;
	}

	public String getTipoOperacion(OperacionMejora operacion ) {
		
		return operacion.getTipoOperacion();
	}
	public int getNumeroAcciones(OperacionMejora operacion) {
		return operacion.getNumeroAcciones();
	}
	public String getId() {
		return Id;
	}

	public void setId(String Id) {
		this.Id = Id;
	}
	public String getTicker() {
		return ticker;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
	}
}

