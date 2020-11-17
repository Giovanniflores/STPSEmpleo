package mx.gob.stps.portal.core.oferta.detalle.vo;

import java.io.Serializable;
import java.util.Date;

public class OfertaCarreraEspecVO implements Serializable {

	private static final long serialVersionUID = 1259996805043792103L;
	
	private long idOfertaCarrera;

	private long idOfertaEmpleo;

	private long idCarreraEspecialidad;

	private int principal;
	
	private Date fechaAlta;

	public long getIdOfertaCarrera() {
		return idOfertaCarrera;
	}

	public void setIdOfertaCarrera(long idOfertaCarrera) {
		this.idOfertaCarrera = idOfertaCarrera;
	}

	public long getIdOfertaEmpleo() {
		return idOfertaEmpleo;
	}

	public void setIdOfertaEmpleo(long idOfertaEmpleo) {
		this.idOfertaEmpleo = idOfertaEmpleo;
	}

	public long getIdCarreraEspecialidad() {
		return idCarreraEspecialidad;
	}

	public void setIdCarreraEspecialidad(long idCarreraEspecialidad) {
		this.idCarreraEspecialidad = idCarreraEspecialidad;
	}

	public int getPrincipal() {
		return principal;
	}

	public void setPrincipal(int principal) {
		this.principal = principal;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
}
