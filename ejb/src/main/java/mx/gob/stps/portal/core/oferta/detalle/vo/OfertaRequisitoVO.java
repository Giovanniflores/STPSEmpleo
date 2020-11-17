package mx.gob.stps.portal.core.oferta.detalle.vo;

import java.io.Serializable;
import java.util.Date;

public class OfertaRequisitoVO implements Serializable{

	private static final long serialVersionUID = 3565814926463750359L;

	private long idOfertaRequisito;

	private long idOfertaEmpleo;

	private long idTipoRequisito;

	private String descripcion;

	private long idExperiencia;

	private long idDominio;

	private int principal;
	
	private Date fechaAlta;

	/**
	 * Method 'OfertaRequisito'
	 * 
	 */
	public OfertaRequisitoVO() {
	}

	/**
	 * Method 'getIdOfertaRequisito'
	 * 
	 * @return int
	 */
	public long getIdOfertaRequisito() {
		return idOfertaRequisito;
	}

	/**
	 * Method 'setIdOfertaRequisito'
	 * 
	 * @param idOfertaRequisito
	 */
	public void setIdOfertaRequisito(long idOfertaRequisito) {
		this.idOfertaRequisito = idOfertaRequisito;
	}

	/**
	 * Method 'getIdOfertaEmpleo'
	 * 
	 * @return int
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
	 * Method 'getIdTipoRequisito'
	 * 
	 * @return int
	 */
	public long getIdTipoRequisito() {
		return idTipoRequisito;
	}

	/**
	 * Method 'setIdTipoRequisito'
	 * 
	 * @param idTipoRequisito
	 */
	public void setIdTipoRequisito(long idTipoRequisito) {
		this.idTipoRequisito = idTipoRequisito;
	}

	/**
	 * Method 'getDescripcion'
	 * 
	 * @return String
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Method 'setDescripcion'
	 * 
	 * @param descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Method 'getIdExperiencia'
	 * 
	 * @return int
	 */
	public long getIdExperiencia() {
		return idExperiencia;
	}

	/**
	 * Method 'setIdExperiencia'
	 * 
	 * @param idExperiencia
	 */
	public void setIdExperiencia(long idExperiencia) {
		this.idExperiencia = idExperiencia;
	}

	/**
	 * Method 'getIdDominio'
	 * 
	 * @return int
	 */
	public long getIdDominio() {
		return idDominio;
	}

	/**
	 * Method 'setIdDominio'
	 * 
	 * @param idDominio
	 */
	public void setIdDominio(long idDominio) {
		this.idDominio = idDominio;
	}

	/**
	 * Method 'getFechaAlta'
	 * 
	 * @return Date
	 */
	public Date getFechaAlta() {
		return fechaAlta;
	}

	/**
	 * Method 'setFechaAlta'
	 * 
	 * @param fechaAlta
	 */
	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	
	public int getPrincipal() {
		return principal;
	}

	public void setPrincipal(int principal) {
		this.principal = principal;
	}
}
