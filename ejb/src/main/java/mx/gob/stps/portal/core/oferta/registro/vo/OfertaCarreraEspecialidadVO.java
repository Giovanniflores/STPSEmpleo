package mx.gob.stps.portal.core.oferta.registro.vo;

import java.io.Serializable;
import java.util.Date;

public class OfertaCarreraEspecialidadVO implements Serializable{
	private static final long serialVersionUID = -8196628641561550854L;
	private long idRegistro;
	private long id;
	private int principal;
	private Date fechaAlta;
	
	public long getIdRegistro() {
		return idRegistro;
	}
	public void setIdRegistro(long idRegistro) {
		this.idRegistro = idRegistro;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
