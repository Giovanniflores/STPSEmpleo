package mx.gob.stps.portal.web.oferta.vo;


import java.io.Serializable;
import java.util.Date;


public class OfertaRequisitoVO implements Serializable  {
	private static final long serialVersionUID = 1520004905866600143L;

	protected int idOfertaRequisito;

	protected int idOfertaEmpleo;

	protected int idTipoRequisito;

	protected String descripcion;

	protected int idExperiencia;

	protected int idDominio;

	protected int idValor;

	protected Date fechaAlta;

	protected int estatus;

	protected Date fechaModificacion;

	
	
	public int getIdOfertaRequisito() {
		return idOfertaRequisito;
	}

	
	public void setIdOfertaRequisito(int idOfertaRequisito) {
		this.idOfertaRequisito = idOfertaRequisito;
	}

	
	public int getIdOfertaEmpleo() {
		return idOfertaEmpleo;
	}

	
	public void setIdOfertaEmpleo(int idOfertaEmpleo) {
		this.idOfertaEmpleo = idOfertaEmpleo;
	}

	
	public int getIdTipoRequisito() {
		return idTipoRequisito;
	}

	
	public void setIdTipoRequisito(int idTipoRequisito) {
		this.idTipoRequisito = idTipoRequisito;
	}

	
	public String getDescripcion() {
		return descripcion;
	}

	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	
	public int getIdExperiencia() {
		return idExperiencia;
	}

	
	public void setIdExperiencia(int idExperiencia) {
		this.idExperiencia = idExperiencia;
	}

	
	public int getIdDominio() {
		return idDominio;
	}

	
	public void setIdDominio(int idDominio) {
		this.idDominio = idDominio;
	}

	
	public int getIdValor() {
		return idValor;
	}

	
	public void setIdValor(int idValor) {
		this.idValor = idValor;
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

}
