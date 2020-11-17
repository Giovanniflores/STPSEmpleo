package mx.gob.stps.portal.core.cil.vo;

import java.io.Serializable;
import java.util.Date;

public class CilCodigoAccesoVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3423952428196398895L;
	protected long idCilCodigoAcceso;
	protected long idCil;
	protected String contrasena;
	protected int estatus;
	protected Date fechaAlta;	
	
	/**
	 * Method 'CilCodigoAcceso'
	 * 
	 */	
	public CilCodigoAccesoVO(){		
	}

	/**
	 * Method 'getIdCilCodigoAcceso'
	 * 
	 * @return the idCilCodigoAcceso
	 */
	public long getIdCilCodigoAcceso() {
		return idCilCodigoAcceso;
	}

	/**
	 * Method 'setIdCilCodigoAcceso'
	 * 
	 * @param idCilCodigoAcceso the idCilCodigoAcceso to set
	 */
	public void setIdCilCodigoAcceso(long idCilCodigoAcceso) {
		this.idCilCodigoAcceso = idCilCodigoAcceso;
	}

	/**
	 * Method 'getIdCil'
	 * 
	 * @return the idCil
	 */
	public long getIdCil() {
		return idCil;
	}

	/**
	 * Method 'setIdCil'
	 * 
	 * @param idCil the idCil to set
	 */
	public void setIdCil(long idCil) {
		this.idCil = idCil;
	}

	/**
	 * Method 'getContrasena'
	 * 
	 * @return the contrasena
	 */
	public String getContrasena() {
		return contrasena;
	}

	/**
	 * Method 'setContrasena'
	 * 
	 * @param contrasena the contrasena to set
	 */
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	/**
	 * Method 'getEstatus'
	 * 
	 * @return the estatus
	 */
	public int getEstatus() {
		return estatus;
	}

	/**
	 * Method 'setEstatus'
	 * 
	 * @param estatus the estatus to set
	 */
	public void setEstatus(int estatus) {
		this.estatus = estatus;
	}

	/**
	 * Method 'getFechaAlta'
	 * 
	 * @return the fechaAlta
	 */
	public Date getFechaAlta() {
		return fechaAlta;
	}

	/**
	 * Method 'setFechaAlta'
	 * 
	 * @param fechaAlta the fechaAlta to set
	 * 
	 */
	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}	
	
	
	
}
