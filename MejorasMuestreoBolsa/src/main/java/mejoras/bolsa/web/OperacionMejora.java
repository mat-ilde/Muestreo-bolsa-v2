package mejoras.bolsa.web;

import java.sql.Date;
import java.util.UUID;

//import java.sql.Date;

public class OperacionMejora {
	//In case I want to use UUID
	//private UUID Id;
	private String Id;
	private String ticker;
	private float precio;
	private int numeroAcciones;
	private String tipoOperacion;
	private Date fecha;
	
	public OperacionMejora(String Id,String ticker, float precio, int numeroAcciones, String tipoOperacion, java.util.Date  fecha) {
		super();
		this.Id = Id;
		this.ticker = ticker;
		this.precio = precio;
		this.numeroAcciones = numeroAcciones;
		this.tipoOperacion = tipoOperacion;
		this.fecha = (Date) fecha;
	}
	public OperacionMejora() {
		
	}
	
	public void setId(String Id ) {
		//UUID uuid=UUID.randomUUID();
		this.Id = Id;
	}
	/*public UUID getId() {
		return Id;
	}*/
	public String getId() {
		return Id;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Date getFecha() {
		return fecha;
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

	public int getNumeroAcciones() {
		return numeroAcciones;
	}

	@Override
	public String toString() {
		return " Id " + Id + " Ticker " + ticker + " Precio " + precio + " Número de acciones a día de hoy " + numeroAcciones
				+ "  Tipo de operación realizada " + tipoOperacion + " fecha " + fecha ;
	}

	public void setNumeroAcciones(int numeroOperaciones) {
		this.numeroAcciones = numeroOperaciones;
	}

	public String getTipoOperacion() {
		return tipoOperacion;
	}

	public void setTipoOperacion(String tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}
	public OperacionMejora getOperacion() {
		return this;
	}
	
}
