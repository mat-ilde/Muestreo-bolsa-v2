package mejoras.bolsa.web;

import java.sql.Date;
import java.util.ArrayList;

public class HistoricoOperacionMejora {
	
	private String ticker;
	private String tipoOperacion;
	private Date fecha;
	private int numeroAcciones;
	private OperacionMejora operacion;
	private float precio;
	private ArrayList<HistoricoOperacionMejora> operaciones=new ArrayList();
	
	public HistoricoOperacionMejora(String ticker, float precio, String tipoOperacion, Date fecha, int numeroAcciones) {
		super();
		this.ticker = ticker;
		this.tipoOperacion = tipoOperacion;
		this.fecha = fecha;
		this.numeroAcciones = numeroAcciones;
		this.precio=precio;
		
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public ArrayList<HistoricoOperacionMejora> getOperaciones() {
		return operaciones;	
	}
	@Override
	public String toString() {
		return " ticker " + ticker + "  tipoOperacion " + tipoOperacion + " fecha " + fecha
				+ "  numeroAcciones " + numeroAcciones + "  operacion " + operacion ;
	}


	public String getTicker() {
		return ticker;
	}



	public void setTicker(String ticker) {
		this.ticker = ticker;
	}



	public String getTipoOperacion() {
		return tipoOperacion;
	}



	public void setTipoOperacion(String tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}



	public Date getFecha() {
		return fecha;
	}



	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}



	public int getNumeroAcciones() {
		return numeroAcciones;
	}

	public void setOperacion(OperacionMejora operacion) {
		this.operacion=operacion;
		operaciones.add(this);
	}

	public void setNumeroAcciones(int numeroAcciones) {
		this.numeroAcciones = numeroAcciones;
	}

	public HistoricoOperacionMejora() {
		
		
	}

	

	
}
