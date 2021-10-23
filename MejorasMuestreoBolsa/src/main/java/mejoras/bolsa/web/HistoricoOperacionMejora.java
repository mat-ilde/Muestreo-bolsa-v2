package mejoras.bolsa.web;

import java.util.ArrayList;

public class HistoricoOperacionMejora {

	private ArrayList<OperacionMejora> operaciones = new ArrayList<>();
	private OperacionMejora operacion;

	public HistoricoOperacionMejora() {
		
		
	}

	public OperacionMejora getOperacion(OperacionMejora operacion) {
		return operacion;
	}

	public void setOperacion(OperacionMejora operacion) {
		this.operacion = operacion;
	}

	public ArrayList<OperacionMejora> getOperaciones() {
		return operaciones;
	}

	public void setOperaciones(ArrayList<OperacionMejora> operaciones) {
		this.operaciones = operaciones;
	}
}
