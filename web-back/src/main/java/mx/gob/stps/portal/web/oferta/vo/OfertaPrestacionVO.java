package mx.gob.stps.portal.web.oferta.vo;


import java.io.Serializable;
import java.util.Date;

public class OfertaPrestacionVO implements Serializable {
	private static final long serialVersionUID = -5666669456529058810L;

	protected int idOfertaPrestacion;

	protected int idOfertaEmpleo;

	protected int idPrestacion;

	protected Date fechaAlta;

	protected int estatus;

	protected Date fechaModificacion;

	
	public int getIdOfertaPrestacion()
	{
		return idOfertaPrestacion;
	}

	
	public void setIdOfertaPrestacion(int idOfertaPrestacion)
	{
		this.idOfertaPrestacion = idOfertaPrestacion;
	}

	
	public int getIdOfertaEmpleo()
	{
		return idOfertaEmpleo;
	}

	
	public void setIdOfertaEmpleo(int idOfertaEmpleo)
	{
		this.idOfertaEmpleo = idOfertaEmpleo;
	}

	
	public int getIdPrestacion()
	{
		return idPrestacion;
	}

	
	public void setIdPrestacion(int idPrestacion)
	{
		this.idPrestacion = idPrestacion;
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
