package mx.gob.stps.portal.core.oferta.registro.vo;

import java.io.Serializable;
import java.util.Date;

public class OfertaSectorVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2590138171643286466L;

	protected long idOfertaSector;

	protected long idOfertaEmpleo;

	protected long idSector;

	protected Date fechaAlta;

	public long getIdOfertaSector() {
		return idOfertaSector;
	}

	public void setIdOfertaSector(long idOfertaSector) {
		this.idOfertaSector = idOfertaSector;
	}

	public long getIdOfertaEmpleo() {
		return idOfertaEmpleo;
	}

	public void setIdOfertaEmpleo(long idOfertaEmpleo) {
		this.idOfertaEmpleo = idOfertaEmpleo;
	}

	public long getIdSector() {
		return idSector;
	}

	public void setIdSector(long idSector) {
		this.idSector = idSector;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	
	
	
}
