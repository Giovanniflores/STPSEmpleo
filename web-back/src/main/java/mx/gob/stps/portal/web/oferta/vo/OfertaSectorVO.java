package mx.gob.stps.portal.web.oferta.vo;

import java.io.Serializable;
import java.util.Date;

public class OfertaSectorVO implements Serializable {
	private static final long serialVersionUID = 8794969350527313325L;

	protected int idOfertaSector;

	protected int idOfertaEmpleo;

	protected int idSector;

	protected Date fechaAlta;

	protected int estatus;

	protected Date fechaModificacion;

	
	public int getIdOfertaSector()
	{
		return idOfertaSector;
	}

	
	public void setIdOfertaSector(int idOfertaSector)
	{
		this.idOfertaSector = idOfertaSector;
	}

	
	public int getIdOfertaEmpleo()
	{
		return idOfertaEmpleo;
	}

	
	public void setIdOfertaEmpleo(int idOfertaEmpleo)
	{
		this.idOfertaEmpleo = idOfertaEmpleo;
	}

	
	public int getIdSector()
	{
		return idSector;
	}

	
	public void setIdSector(int idSector)
	{
		this.idSector = idSector;
	}

	
	public Date getFechaAlta()
	{
		return fechaAlta;
	}

	
	public void setFechaAlta(Date fechaAlta)
	{
		this.fechaAlta = fechaAlta;
	}

	
	public int getEstatus()
	{
		return estatus;
	}

	
	public void setEstatus(int estatus)
	{
		this.estatus = estatus;
	}

	
	public Date getFechaModificacion()
	{
		return fechaModificacion;
	}

	
	public void setFechaModificacion(Date fechaModificacion)
	{
		this.fechaModificacion = fechaModificacion;
	}

}
