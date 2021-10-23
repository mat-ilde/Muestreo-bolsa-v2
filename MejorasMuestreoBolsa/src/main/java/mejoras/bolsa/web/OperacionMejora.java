package mejoras.bolsa.web;

import java.sql.Date;

public class OperacionMejora {
	
	private String ticker;
	private float precio;
	private int numeroOperaciones;
	private String tipoOperacion;
	private Date fecha;
	
	public OperacionMejora(String ticker, float precio, int numeroOperaciones, String tipoOperacion, Date fecha) {
		super();
		this.ticker = ticker;
		this.precio = precio;
		this.numeroOperaciones = numeroOperaciones;
		this.tipoOperacion = tipoOperacion;
		this.fecha = fecha;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public String getTicker() {
		return ticker;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	public float getPrecio() {
		return precio;
	}
	
	public void setPrecio(double d) {
		this.precio = (float) d;
	}

	public int getNumeroOperaciones() {
		return numeroOperaciones;
	}

	public void setNumeroOperaciones(int numeroOperaciones) {
		this.numeroOperaciones = numeroOperaciones;
	}

	public String getTipoOperacion() {
		return tipoOperacion;
	}

	public void setTipoOperacion(String tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}
	
}
