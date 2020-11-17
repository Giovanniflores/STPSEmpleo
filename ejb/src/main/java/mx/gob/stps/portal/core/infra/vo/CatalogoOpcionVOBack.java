package mx.gob.stps.portal.core.infra.vo;

import java.io.Serializable;
import java.util.Date;
//TODO quitar este clase despues de probar que funciona el de general
public final class CatalogoOpcionVOBack implements Serializable {
	private static final long serialVersionUID = 8934979100501630692L;

	private long idCatalogoOpcion;

	private long idCatalogo;

	private String opcion;

	private Date fechaAlta;

	private int estatus;

	private Date fechaModificacion;	
	
	private long idCatalagoAsociado;

	protected String idCorto;
	
	/**
	 * Constructor por defecto
	 * 
	 * @return CatalogoOpcionVO
	 */			
	public CatalogoOpcionVOBack(){}
	
	/**
	 * Constructor que acepta todos los valores de una opcion de catalogo
	 * 
	 * @param idCatalogoOpcion
	 * @param idCatalogo
	 * @param opcion
	 * @param fechaAlta
	 * @param estatus
	 * @param fechaModificacion
	 * @param idCatalagoAsociado
	 * @return CatalogoOpcionVO
	 */			
	public CatalogoOpcionVOBack(long idCatalogoOpcion, long idCatalogo, String opcion,Date fechaAlta, int estatus, Date fechaModificacion, long idCatalagoAsociado, String idCorto){
		this.idCatalogoOpcion = idCatalogoOpcion;
		this.idCatalogo = idCatalogo;
		this.opcion = opcion;
		this.fechaAlta = fechaAlta;
		this.estatus = estatus;
		this.fechaModificacion = fechaModificacion;
		this.idCatalagoAsociado = idCatalagoAsociado;
		this.idCorto = idCorto;
	}

	public long getIdCatalogoOpcion() {
		return idCatalogoOpcion;
	}

	public void setIdCatalogoOpcion(long idCatalogoOpcion) {
		this.idCatalogoOpcion = idCatalogoOpcion;
	}

	public long getIdCatalogo() {
		return idCatalogo;
	}

	public void setIdCatalogo(long idCatalogo) {
		this.idCatalogo = idCatalogo;
	}

	public String getOpcion() {
		return opcion;
	}

	public void setOpcion(String opcion) {
		this.opcion = opcion;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public int getEstatus() {
		return estatus;
	}

	public void setEstatus(int estatus) {
		this.estatus = estatus;
	}

	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	/**
	 * @param idCatalagoAsociado the idCatalagoAsociado to set
	 */
	public void setIdCatalagoAsociado(long idCatalagoAsociado) {
		this.idCatalagoAsociado = idCatalagoAsociado;
	}

	/**
	 * @return the idCatalagoAsociado
	 */
	public long getIdCatalagoAsociado() {
		return idCatalagoAsociado;
	}

	public String getIdCorto() {
		return idCorto;
	}

	public void setIdCorto(String idCorto) {
		this.idCorto = idCorto;
	}
}
