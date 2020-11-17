package mx.gob.stps.portal.core.domicilio.vo;

import java.io.Serializable;
import java.util.Date;

import oracle.spatial.geometry.JGeometry;


/**
 * @author Mario Alberto Vázquez Flores
 * @since 08/03/2011
 **/
public final class DomicilioVO implements Serializable {

	private static final long serialVersionUID = -392250583158120650L;

	private long idDomicilio;
	private long idTipoPropietario;
	private long idEntidad;
	private long idMunicipio;
	private long idColonia;
	private String calle;
	private String numeroInterior;
	private String numeroExterior;
	private String entreCalle;
	private String yCalle;
	private String codigoPostal;
	private Date fechaAlta;
	protected int estatus;
	protected Date fechaModificacion;
	private long idLocalidad;
	private String domicilioReferencia;
	private long idPropietario;

	private String entidad;
	private String municipio;
	private String colonia;
	private String domiciloCompletoString;
	private String entidadFederativaString;
	private Double latitud;
	private Double longitud;

	

	
	/**
	 * @return the idDomicilio
	 */
	public long getIdDomicilio() {
		return idDomicilio;
	}

	/**
	 * @param idDomicilio
	 *            the idDomicilio to set
	 */
	public void setIdDomicilio(long idDomicilio) {
		this.idDomicilio = idDomicilio;
	}

	/**
	 * @return the idTipoPropietario
	 */
	public long getIdTipoPropietario() {
		return idTipoPropietario;
	}

	/**
	 * @param idTipoPropietario
	 *            the idTipoPropietario to set
	 */
	public void setIdTipoPropietario(long idTipoPropietario) {
		this.idTipoPropietario = idTipoPropietario;
	}

	/**
	 * @return the idEntidad
	 */
	public long getIdEntidad() {
		return idEntidad;
	}

	/**
	 * @param idEntidad
	 *            the idEntidad to set
	 */
	public void setIdEntidad(long idEntidad) {
		this.idEntidad = idEntidad;
	}

	/**
	 * @return the idMunicipio
	 */
	public long getIdMunicipio() {
		return idMunicipio;
	}

	/**
	 * @param idMunicipio
	 *            the idMunicipio to set
	 */
	public void setIdMunicipio(long idMunicipio) {
		this.idMunicipio = idMunicipio;
	}

	/**
	 * @return the idColonia
	 */
	public long getIdColonia() {
		return idColonia;
	}

	/**
	 * @param idColonia
	 *            the idColonia to set
	 */
	public void setIdColonia(long idColonia) {
		this.idColonia = idColonia;
	}

	/**
	 * @return the calle
	 */
	public String getCalle() {
		return calle;
	}

	/**
	 * @param calle
	 *            the calle to set
	 */
	public void setCalle(String calle) {
		this.calle = calle;
	}

	/**
	 * @return the numeroInterior
	 */
	public String getNumeroInterior() {
		return numeroInterior;
	}

	/**
	 * @param numeroInterior
	 *            the numeroInterior to set
	 */
	public void setNumeroInterior(String numeroInterior) {
		this.numeroInterior = numeroInterior;
	}

	/**
	 * @return the numeroExterior
	 */
	public String getNumeroExterior() {
		return numeroExterior;
	}

	/**
	 * @param numeroExterior
	 *            the numeroExterior to set
	 */
	public void setNumeroExterior(String numeroExterior) {
		this.numeroExterior = numeroExterior;
	}

	/**
	 * @return the entreCalle
	 */
	public String getEntreCalle() {
		return entreCalle;
	}

	/**
	 * @param entreCalle
	 *            the entreCalle to set
	 */
	public void setEntreCalle(String entreCalle) {
		this.entreCalle = entreCalle;
	}

	/**
	 * @return the yCalle
	 */
	public String getyCalle() {
		return yCalle;
	}

	/**
	 * @param yCalle
	 *            the yCalle to set
	 */
	public void setyCalle(String yCalle) {
		this.yCalle = yCalle;
	}

	/**
	 * @return the codigoPostal
	 */
	public String getCodigoPostal() {
		if(null==this.codigoPostal)
			return "";			
		return codigoPostal;
	}

	/**
	 * @param codigoPostal
	 *            the codigoPostal to set
	 */
	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	/**
	 * @return the fechaAlta
	 */
	public Date getFechaAlta() {
		return fechaAlta;
	}

	/**
	 * @param fechaAlta
	 *            the fechaAlta to set
	 */
	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	/**
	 * @return the idPropietario
	 */
	public long getIdPropietario() {
		return idPropietario;
	}
	/**
	 * @param idPropietario the idPropietario to set
	 */
	public void setIdPropietario(long idPropietario) {
		this.idPropietario = idPropietario;
	}
	
	public String getEntidad() {
		return entidad;
	}

	public void setEntidad(String entidad) {
		this.entidad = entidad;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getColonia() {
		return colonia;
	}

	public void setColonia(String colonia) {
		this.colonia = colonia;
	}
	
	public int getEstatus() {
		return estatus;
	}

	public void setEstatus(int estatus) {
		this.estatus = estatus;
	}

	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}
	
	
	public String getDomiciloCompletoString() {
		return domiciloCompletoString;
	}

	public void setDomiciloCompletoString(String domiciloCompletoString) {
		this.domiciloCompletoString = domiciloCompletoString;
	}

	public String getEntidadFederativaString() {
		return entidadFederativaString;
	}

	public void setEntidadFederativaString(String entidadFederativaString) {
		this.entidadFederativaString = entidadFederativaString;
	}

	public void setGeoReferencia(JGeometry georeferencia){
		if(georeferencia!=null){
			this.latitud = georeferencia.getJavaPoint().getX();
			this.longitud = georeferencia.getJavaPoint().getY();
		}
	}
	
	public Double getLatitud() {
		return latitud;
	}

	public void setLatitud(Double latitud) {
		this.latitud = latitud;
	}

	public Double getLongitud() {
		return longitud;
	}

	public void setLongitud(Double longitud) {
		this.longitud = longitud;
	}
	
	public long getIdLocalidad() {
		return idLocalidad;
	}

	public void setIdLocalidad(long idLocalidad) {
		this.idLocalidad = idLocalidad;
	}

	public String getDomicilioReferencia() {
		return domicilioReferencia;
	}

	public void setDomicilioReferencia(String domicilioReferencia) {
		this.domicilioReferencia = domicilioReferencia;
	}

	@Override
	public String toString() {
		return "DomicilioVO [idDomicilio=" + idDomicilio
				+ ", idTipoPropietario=" + idTipoPropietario + ", idEntidad="
				+ idEntidad + ", idMunicipio=" + idMunicipio + ", idColonia="
				+ idColonia + ", calle=" + calle + ", numeroInterior="
				+ numeroInterior + ", numeroExterior=" + numeroExterior
				+ ", entreCalle=" + entreCalle + ", yCalle=" + yCalle
				+ ", codigoPostal=" + codigoPostal + ", fechaAlta=" + fechaAlta
				+ ", estatus=" + estatus + ", fechaModificacion="
				+ fechaModificacion + ", idLocalidad=" + idLocalidad
				+ ", domicilioReferencia=" + domicilioReferencia
				+ ", idPropietario=" + idPropietario + ", entidad=" + entidad
				+ ", municipio=" + municipio + ", colonia=" + colonia
				+ ", latitud=" + latitud + ", longitud=" + longitud + "]";
	}
}
