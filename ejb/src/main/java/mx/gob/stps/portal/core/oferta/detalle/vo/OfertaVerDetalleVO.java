package mx.gob.stps.portal.core.oferta.detalle.vo;

import java.io.Serializable;

public final class OfertaVerDetalleVO implements Serializable{

	private static final long serialVersionUID = -1003948512892443864L;

	private long idOfertaEmpleo;

	private int anio;

	private int mes;

	private int contador;
	
	private long idOfertaVerDetalle;

	/**
	 * Constructor vacio
	 * 
	 */
	public OfertaVerDetalleVO() {
	}

	/**
	 * Constructor parametrizado
	 * 
	 */
	public OfertaVerDetalleVO(long idOfertaVerDetalle, long idOfertaEmpleo, int anio, int mes, int contador) {
		this.idOfertaVerDetalle = idOfertaVerDetalle;
		this.anio = anio;
		this.contador = contador;
		this.idOfertaEmpleo = idOfertaEmpleo;
		this.mes = mes;
	}
	
	/**
	 * Method 'getIdOfertaVerDetalle'
	 * 
	 * @return long
	 */
	public long getIdOfertaVerDetalle() {
		return idOfertaVerDetalle;
	}

	/**
	 * Method 'setIdOfertaVerDetalle'
	 * 
	 * @param idOfertaEmpleo
	 */
	public void setIdOfertaVerDetalle(long idOfertaVerDetalle) {
		this.idOfertaVerDetalle = idOfertaVerDetalle;
	}
	
	/**
	 * Method 'getIdOfertaEmpleo'
	 * 
	 * @return long
	 */
	public long getIdOfertaEmpleo() {
		return idOfertaEmpleo;
	}

	/**
	 * Method 'setIdOfertaEmpleo'
	 * 
	 * @param idOfertaEmpleo
	 */
	public void setIdOfertaEmpleo(long idOfertaEmpleo) {
		this.idOfertaEmpleo = idOfertaEmpleo;
	}

	/**
	 * Method 'getAnio'
	 * 
	 * @return int
	 */
	public int getAnio() {
		return anio;
	}

	/**
	 * Method 'setAnio'
	 * 
	 * @param anio
	 */
	public void setAnio(int anio) {
		this.anio = anio;
	}

	/**
	 * Method 'getMes'
	 * 
	 * @return int
	 */
	public int getMes() {
		return mes;
	}

	/**
	 * Method 'setMes'
	 * 
	 * @param mes
	 */
	public void setMes(int mes) {
		this.mes = mes;
	}

	/**
	 * Method 'getContador'
	 * 
	 * @return int
	 */
	public int getContador() {
		return contador;
	}

	/**
	 * Method 'setContador'
	 * 
	 * @param contador
	 */
	public void setContador(int contador) {
		this.contador = contador;
	}
}
