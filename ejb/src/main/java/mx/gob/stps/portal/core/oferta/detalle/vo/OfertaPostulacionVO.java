package mx.gob.stps.portal.core.oferta.detalle.vo;

import java.io.Serializable;

public class OfertaPostulacionVO implements Serializable {

	private static final long serialVersionUID = -2733725514733221237L;

	private long idOfertaEmpleo;
	private int anio;
	private int mes;
	private int contador;
	private long idOfertaPostulacion;
	
	/**
	 * Method 'OfertaPostulacion'
	 * 
	 */
	public OfertaPostulacionVO() {
	}

	/**
	 * Constructor parametrizado
	 * 
	 */
	public OfertaPostulacionVO(long idOfertaPostulacion, long idOfertaEmpleo, int anio, int mes, int contador) {
		this.idOfertaPostulacion = idOfertaPostulacion;
		this.anio = anio;
		this.contador = contador;
		this.idOfertaEmpleo = idOfertaEmpleo;
		this.mes = mes;
	}
	
	/**
	 * Method 'getIdOfertaEmpleo'
	 * 
	 * @return long
	 */
	public long getIdOfertaPostulacion() {
		return idOfertaPostulacion;
	}

	/**
	 * Method 'setIdOfertaEmpleo'
	 * 
	 * @param idOfertaEmpleo
	 */
	public void setIdOfertaPostulacion(long idOfertaPostulacion) {
		this.idOfertaPostulacion = idOfertaPostulacion;
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