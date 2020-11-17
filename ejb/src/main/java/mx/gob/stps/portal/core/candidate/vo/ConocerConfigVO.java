package mx.gob.stps.portal.core.candidate.vo;

import java.io.Serializable;
import java.util.Date;

public final class ConocerConfigVO implements Serializable {

	protected Long idConocer;
	protected Long idCandidato;
	protected Long deseaPublicar;
	protected Long publicarEnPerfil;
	protected Long volverPreguntar;
	protected Date fechaModificacion;

	private static final long serialVersionUID = 2308713437483969608L;

	public Long getIdConocer() {
		return idConocer;
	}

	public void setIdConocer(Long idConocer) {
		this.idConocer = idConocer;
	}

	public Long getIdCandidato() {
		return this.idCandidato;
	}

	public void setIdCandidato(Long idCandidato) {
		this.idCandidato = idCandidato;
	}

	public Long getDeseaPublicar() {
		return this.deseaPublicar;
	}

	public void setDeseaPublicar(Long deseaPublicar) {
		this.deseaPublicar = deseaPublicar;
	}

	public Long getVolverPreguntar() {
		return this.volverPreguntar;
	}

	public void setVolverPreguntar(Long volverPreguntar) {
		this.volverPreguntar = volverPreguntar;
	}

	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public Long getPublicarEnPerfil() {
		return this.publicarEnPerfil;
	}

	public void setPublicarEnPerfil(Long publicarEnPerfil) {
		this.publicarEnPerfil = publicarEnPerfil;
	}

	public String toString() {

		String str = new String();
		
		str= "idConocer" + idConocer + ", idCandidato=" + idCandidato
				+ ", deseaPublicar=" + deseaPublicar + ", publicarEnPerfil="
				+ publicarEnPerfil + ", volverPreguntar=" + volverPreguntar;
		if (fechaModificacion != null) {
			str = str + ", fechaModificacion=" + fechaModificacion.toString();				
		}
		
		return str;
 
	}

}
