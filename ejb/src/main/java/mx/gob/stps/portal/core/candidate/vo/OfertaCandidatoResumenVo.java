package mx.gob.stps.portal.core.candidate.vo;

import java.io.Serializable;
import java.util.Date;

import mx.gob.stps.portal.core.infra.vo.EntrevistaVO;
import mx.gob.stps.portal.utils.Catalogos;

public class OfertaCandidatoResumenVo implements Serializable {
	private static final long serialVersionUID = -3274893546080105510L;
	
	protected String 	mensaje;
	
	protected long 		idCandidato;
	protected long 		idEmpresa;
	protected long 		idOfertaCandidato;
	protected long 		idOfertaEmpleo;

	protected String 	nombreCandidato;
	protected String 	nombreEmpresa;
	protected String 	tituloOferta;
	
	protected String 	correoEmpresa;
	protected String 	correoCandidato;

	protected long 		idEstatus;
	protected String 	descEstatus;

	protected long 		idMotivo;
	
	protected Date 		fecha;
	
	protected int 		compatibilidad;

	protected EntrevistaVO entrevista;
	
	private long fuente;
	
	private String medioColocacion;

	/**
	 * @return the mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * @param mensaje the mensaje to set
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	/**
	 * @return the idCandidato
	 */
	public long getIdCandidato() {
		return idCandidato;
	}

	/**
	 * @param idCandidato the idCandidato to set
	 */
	public void setIdCandidato(long idCandidato) {
		this.idCandidato = idCandidato;
	}

	/**
	 * @return the idEmpresa
	 */
	public long getIdEmpresa() {
		return idEmpresa;
	}

	/**
	 * @param idEmpresa the idEmpresa to set
	 */
	public void setIdEmpresa(long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	/**
	 * @return the idOfertaCandidato
	 */
	public long getIdOfertaCandidato() {
		return idOfertaCandidato;
	}

	/**
	 * @param idOfertaCandidato the idOfertaCandidato to set
	 */
	public void setIdOfertaCandidato(long idOfertaCandidato) {
		this.idOfertaCandidato = idOfertaCandidato;
	}

	/**
	 * @return the idOfertaEmpleo
	 */
	public long getIdOfertaEmpleo() {
		return idOfertaEmpleo;
	}

	/**
	 * @param idOfertaEmpleo the idOfertaEmpleo to set
	 */
	public void setIdOfertaEmpleo(long idOfertaEmpleo) {
		this.idOfertaEmpleo = idOfertaEmpleo;
	}

	/**
	 * @return the nombreCandidato
	 */
	public String getNombreCandidato() {
		return nombreCandidato;
	}

	/**
	 * @param nombreCandidato the nombreCandidato to set
	 */
	public void setNombreCandidato(String nombreCandidato) {
		this.nombreCandidato = nombreCandidato;
	}

	/**
	 * @return the nombreEmpresa
	 */
	public String getNombreEmpresa() {
		return nombreEmpresa;
	}

	/**
	 * @param nombreEmpresa the nombreEmpresa to set
	 */
	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}

	/**
	 * @return the tituloOferta
	 */
	public String getTituloOferta() {
		return tituloOferta;
	}

	/**
	 * @param tituloOferta the tituloOferta to set
	 */
	public void setTituloOferta(String tituloOferta) {
		this.tituloOferta = tituloOferta;
	}

	/**
	 * @return the correoEmpresa
	 */
	public String getCorreoEmpresa() {
		return correoEmpresa;
	}

	/**
	 * @param correoEmpresa the correoEmpresa to set
	 */
	public void setCorreoEmpresa(String correoEmpresa) {
		this.correoEmpresa = correoEmpresa;
	}

	/**
	 * @return the correoCandidato
	 */
	public String getCorreoCandidato() {
		return correoCandidato;
	}

	/**
	 * @param correoCandidato the correoCandidato to set
	 */
	public void setCorreoCandidato(String correoCandidato) {
		this.correoCandidato = correoCandidato;
	}

	/**
	 * @return the idEstatus
	 */
	public long getIdEstatus() {
		return idEstatus;
	}

	/**
	 * @param idEstatus the idEstatus to set
	 */
	public void setIdEstatus(long idEstatus) {
		this.idEstatus = idEstatus;
	}

	/**
	 * @return the descEstatus
	 */
	public String getDescEstatus() {
		return descEstatus;
	}

	/**
	 * @param descEstatus the descEstatus to set
	 */
	public void setDescEstatus(String descEstatus) {
		this.descEstatus = descEstatus;
	}

	/**
	 * @return the idMotivo
	 */
	public long getIdMotivo() {
		return idMotivo;
	}

	/**
	 * @param idMotivo the idMotivo to set
	 */
	public void setIdMotivo(long idMotivo) {
		this.idMotivo = idMotivo;
	}

	/**
	 * @return the fecha
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	/**
	 * @return the compatibilidad
	 */
	public int getCompatibilidad() {
		return compatibilidad;
	}

	/**
	 * @param compatibilidad the compatibilidad to set
	 */
	public void setCompatibilidad(int compatibilidad) {
		this.compatibilidad = compatibilidad;
	}

	/**
	 * @return the entrevista
	 */
	public EntrevistaVO getEntrevista() {
		return entrevista;
	}

	/**
	 * @param entrevista2 the entrevista to set
	 */
	public void setEntrevista(EntrevistaVO entrevista) {
		this.entrevista = entrevista;
	}

	public long getFuente() {
		return fuente;
	}

	public void setFuente(long fuente) {
		this.fuente = fuente;
	}

	public String getMedioColocacion() {
		return fuente > 0 ? Catalogos.CATALOGO_FUENTE_QUE_REGISTRA_OFERTA.getDescripcion((int)fuente) : "";
	}

	public void setMedioColocacion(String medioColocacion) {
		this.medioColocacion = medioColocacion;
	}

	@Override
	public String toString() {
		return "OfertaCandidatoResumenVo [mensaje=" + mensaje
				+ ", idCandidato=" + idCandidato + ", idEmpresa=" + idEmpresa
				+ ", idOfertaCandidato=" + idOfertaCandidato
				+ ", idOfertaEmpleo=" + idOfertaEmpleo + ", nombreCandidato="
				+ nombreCandidato + ", nombreEmpresa=" + nombreEmpresa
				+ ", tituloOferta=" + tituloOferta + ", correoEmpresa="
				+ correoEmpresa + ", correoCandidato=" + correoCandidato
				+ ", idEstatus=" + idEstatus + ", descEstatus=" + descEstatus
				+ ", idMotivo=" + idMotivo + ", fecha=" + fecha
				+ ", compatibilidad=" + compatibilidad + ", entrevista="
				+ entrevista + ", fuente=" + fuente + ", medioColocacion="
				+ medioColocacion + "]";
	}
}
