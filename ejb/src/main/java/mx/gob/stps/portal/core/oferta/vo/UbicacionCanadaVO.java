package mx.gob.stps.portal.core.oferta.vo;


import java.io.Serializable;

public class UbicacionCanadaVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1369743027910346934L;

	private long idProvincia;
	private long idCiudad;
	private String provincia;
	private String ciudad;
	public long getIdProvincia() {
		return idProvincia;
	}
	public void setIdProvincia(long idProvincia) {
		this.idProvincia = idProvincia;
	}
	public long getIdCiudad() {
		return idCiudad;
	}
	public void setIdCiudad(long idCiudad) {
		this.idCiudad = idCiudad;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	
	
	
	
}

