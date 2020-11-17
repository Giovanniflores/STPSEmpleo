package mx.gob.stps.portal.core.oferta.vo;

import java.io.Serializable;
import java.util.Date;

public class OfertaPrestacionVO implements Serializable {

	private static final long serialVersionUID = -1925272423569840477L;
	
	protected long idOfertaPrestacion;

	protected long idOfertaEmpleo;

	protected long idPrestacion;

	protected Date fechaAlta;

	public long getIdOfertaPrestacion() {
		return idOfertaPrestacion;
	}

	public void setIdOfertaPrestacion(long idOfertaPrestacion) {
		this.idOfertaPrestacion = idOfertaPrestacion;
	}

	public long getIdOfertaEmpleo() {
		return idOfertaEmpleo;
	}

	public void setIdOfertaEmpleo(long idOfertaEmpleo) {
		this.idOfertaEmpleo = idOfertaEmpleo;
	}

	public long getIdPrestacion() {
		return idPrestacion;
	}

	public void setIdPrestacion(long idPrestacion) {
		this.idPrestacion = idPrestacion;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	
	

}
