package mx.gob.stps.portal.web.oferta.vo;

import java.io.Serializable;
import java.util.Date;

public class OfertaCarreraEspecVO implements Serializable {
	private static final long serialVersionUID = 2516323075171726500L;

	protected int idOfertaCarrera;

	protected int idOfertaEmpleo;

	protected int idCarreraEspecialidad;

	protected Date fechaAlta;

	protected int estatus;

	protected Date fechaModificacion;

	
	public int getIdOfertaCarrera()
	{
		return idOfertaCarrera;
	}

	
	public void setIdOfertaCarrera(int idOfertaCarrera)
	{
		this.idOfertaCarrera = idOfertaCarrera;
	}

	
	public int getIdOfertaEmpleo()
	{
		return idOfertaEmpleo;
	}

	
	public void setIdOfertaEmpleo(int idOfertaEmpleo)
	{
		this.idOfertaEmpleo = idOfertaEmpleo;
	}

	
	public int getIdCarreraEspecialidad()
	{
		return idCarreraEspecialidad;
	}

	
	public void setIdCarreraEspecialidad(int idCarreraEspecialidad)
	{
		this.idCarreraEspecialidad = idCarreraEspecialidad;
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
