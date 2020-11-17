package mx.gob.stps.portal.core.oferta.vo;

import java.io.Serializable;
import java.util.Date;

public class OfertaUbicacionVO implements Serializable {

	private static final long serialVersionUID = -8359258800753618118L;

	protected int idOfertaUbicacion;

	protected long idOfertaEmpleo;

	protected int idEntidad;

	protected int idMunicipio;

	protected Date fechaAlta;

	protected int estatus;

	protected Date fechaModificacion;

	public int getIdOfertaUbicacion() {
		return idOfertaUbicacion;
	}

	public void setIdOfertaUbicacion(int idOfertaUbicacion) {
		this.idOfertaUbicacion = idOfertaUbicacion;
	}

	public long getIdOfertaEmpleo() {
		return idOfertaEmpleo;
	}

	public void setIdOfertaEmpleo(long idOfertaEmpleo) {
		this.idOfertaEmpleo = idOfertaEmpleo;
	}

	public int getIdEntidad() {
		return idEntidad;
	}

	public void setIdEntidad(int idEntidad) {
		this.idEntidad = idEntidad;
	}

	public int getIdMunicipio() {
		return idMunicipio;
	}

	public void setIdMunicipio(int idMunicipio) {
		this.idMunicipio = idMunicipio;
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
