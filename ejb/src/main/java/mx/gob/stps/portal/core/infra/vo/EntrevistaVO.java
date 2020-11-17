package mx.gob.stps.portal.core.infra.vo;

import java.io.Serializable;
import java.util.Date;

public class EntrevistaVO implements Serializable {
	private static final long serialVersionUID = -6558784347371780702L;

	private long idEmpresa ;
	private long IdUsuario;
	
	private String 	tituloOferta;
	private String 	razonSocial;

	private String 	contactoEmpresa;
	private String	nombre;
	private String  fechaString;
	private String  fechaFin;
	private String  nombreEmpresa;
	private String  ofertaEmpleo;

	private String 	msn1;
	private String 	msn2;
	private String  tipo;
	private String  tipoOperacion;
	
	private String correoEmpresa;	
	private String correoCandidato;
	private String correo;
	private String mensajeBitacora;

	private long idEntrevista;

	private long idOfertaEmpleo;

	private long idCandidato;

	private Date fecha;

	private String hora;

	private Date fechaAlta;

	private Date fechaModificacion;

	private int estatus;		
	
	private String emailEmpresa;
	
	private String emailCandidato;
	
	private String emailMensaje;
	
	private String asunto;
	
	public EntrevistaVO(){}
	
	public EntrevistaVO(long idEntrevista, long idOfertaEmpleo, long idCandidato,
			Date fecha, String hora, Date fechaAlta, Date fechaModificacion, int estatus){
		this.idEntrevista = idEntrevista;
		this.idOfertaEmpleo = idOfertaEmpleo;
		this.idCandidato = idCandidato;
		this.fecha = fecha;
		this.hora = hora;
		this.fechaAlta = fechaAlta;
		this.fechaModificacion = fechaModificacion;
		this.estatus = estatus;
		
	}

	public long getIdEntrevista() {
		return idEntrevista;
	}

	public void setIdEntrevista(long idEntrevista) {
		this.idEntrevista = idEntrevista;
	}

	public long getIdOfertaEmpleo() {
		return idOfertaEmpleo;
	}

	public void setIdOfertaEmpleo(long idOfertaEmpleo) {
		this.idOfertaEmpleo = idOfertaEmpleo;
	}

	public long getIdCandidato() {
		return idCandidato;
	}

	public void setIdCandidato(long idCandidato) {
		this.idCandidato = idCandidato;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}
	
	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public int getEstatus() {
		return estatus;
	}

	public void setEstatus(int estatus) {
		this.estatus = estatus;
	}

	/**
	 * @return the idEmpresa
	 */
	public long getIdEmpresa() {
		return idEmpresa;
	}

	/**
	 * @return the tituloOferta
	 */
	public String getTituloOferta() {
		return tituloOferta;
	}

	/**
	 * @return the razonSocial
	 */
	public String getRazonSocial() {
		return razonSocial;
	}

	/**
	 * @return the contactoEmpresa
	 */
	public String getContactoEmpresa() {
		return contactoEmpresa;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @return the msn1
	 */
	public String getMsn1() {
		return msn1;
	}

	/**
	 * @return the msn2
	 */
	public String getMsn2() {
		return msn2;
	}

	/**
	 * @param idEmpresa the idEmpresa to set
	 */
	public void setIdEmpresa(long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	/**
	 * @param tituloOferta the tituloOferta to set
	 */
	public void setTituloOferta(String tituloOferta) {
		this.tituloOferta = tituloOferta;
	}

	/**
	 * @param razonSocial the razonSocial to set
	 */
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	/**
	 * @param contactoEmpresa the contactoEmpresa to set
	 */
	public void setContactoEmpresa(String contactoEmpresa) {
		this.contactoEmpresa = contactoEmpresa;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	/**
	 * @param msn1 the msn1 to set
	 */
	public void setMsn1(String msn1) {
		this.msn1 = msn1;
	}

	/**
	 * @param msn2 the msn2 to set
	 */
	public void setMsn2(String msn2) {
		this.msn2 = msn2;
	}

	/**
	 * @param fechaString the fechaString to set
	 */
	public void setFechaString(String fechaString) {
		this.fechaString = fechaString;
	}

	/**
	 * @return the fechaString
	 */
	public String getFechaString() {
		return fechaString;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * @param fechaFin the fechaFin to set
	 */
	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}

	/**
	 * @return the fechaFin
	 */
	public String getFechaFin() {
		return fechaFin;
	}

	/**
	 * @return the emailEmpresa
	 */
	public String getEmailEmpresa() {
		return emailEmpresa;
	}

	/**
	 * @return the emailCandidato
	 */
	public String getEmailCandidato() {
		return emailCandidato;
	}

	/**
	 * @return the emailMensaje
	 */
	public String getEmailMensaje() {
		return emailMensaje;
	}

	/**
	 * @return the asunto
	 */
	public String getAsunto() {
		return asunto;
	}

	/**
	 * @param emailEmpresa the emailEmpresa to set
	 */
	public void setEmailEmpresa(String emailEmpresa) {
		this.emailEmpresa = emailEmpresa;
	}

	/**
	 * @param emailCandidato the emailCandidato to set
	 */
	public void setEmailCandidato(String emailCandidato) {
		this.emailCandidato = emailCandidato;
	}

	/**
	 * @param emailMensaje the emailMensaje to set
	 */
	public void setEmailMensaje(String emailMensaje) {
		this.emailMensaje = emailMensaje;
	}

	/**
	 * @param asunto the asunto to set
	 */
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	/**
	 * @return the correoEmpresa
	 */
	public String getCorreoEmpresa() {
		return correoEmpresa;
	}

	/**
	 * @return the correoCandidato
	 */
	public String getCorreoCandidato() {
		return correoCandidato;
	}

	/**
	 * @param correoEmpresa the correoEmpresa to set
	 */
	public void setCorreoEmpresa(String correoEmpresa) {
		this.correoEmpresa = correoEmpresa;
	}

	/**
	 * @param correoCandidato the correoCandidato to set
	 */
	public void setCorreoCandidato(String correoCandidato) {
		this.correoCandidato = correoCandidato;
	}

	/**
	 * @param tipoOperacion the tipoOperacion to set
	 */
	public void setTipoOperacion(String tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}

	/**
	 * @return the tipoOperacion
	 */
	public String getTipoOperacion() {
		return tipoOperacion;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "EntrevistaVO [idEmpresa=" + idEmpresa + ", tituloOferta="
				+ tituloOferta + ", razonSocial=" + razonSocial
				+ ", contactoEmpresa=" + contactoEmpresa + ", nombre=" + nombre
				+ ", fechaString=" + fechaString + ", fechaFin=" + fechaFin
				+ ", msn1=" + msn1 + ", msn2=" + msn2 + ", tipo=" + tipo
				+ ", idEntrevista=" + idEntrevista + ", idOfertaEmpleo="
				+ idOfertaEmpleo + ", idCandidato=" + idCandidato + ", fecha="
				+ fecha + ", hora=" + hora + ", fechaAlta=" + fechaAlta
				+ ", fechaModificacion=" + fechaModificacion + ", estatus="
				+ estatus + ", emailEmpresa=" + emailEmpresa
				+ ", emailCandidato=" + emailCandidato + ", emailMensaje="
				+ emailMensaje + ", asunto=" + asunto + "]";
	}

	/**
	 * @param nombreEmpresa the nombreEmpresa to set
	 */
	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}

	/**
	 * @return the nombreEmpresa
	 */
	public String getNombreEmpresa() {
		return nombreEmpresa;
	}

	/**
	 * @param ofertaEmpleo the ofertaEmpleo to set
	 */
	public void setOfertaEmpleo(String ofertaEmpleo) {
		this.ofertaEmpleo = ofertaEmpleo;
	}

	/**
	 * @return the ofertaEmpleo
	 */
	public String getOfertaEmpleo() {
		return ofertaEmpleo;
	}

	/**
	 * @param correo the correo to set
	 */
	public void setCorreo(String correo) {
		this.correo = correo;
	}

	/**
	 * @return the correo
	 */
	public String getCorreo() {
		return correo;
	}

	/**
	 * @param idUsuario the idUsuario to set
	 */
	public void setIdUsuario(long idUsuario) {
		IdUsuario = idUsuario;
	}

	/**
	 * @return the idUsuario
	 */
	public long getIdUsuario() {
		return IdUsuario;
	}

	/**
	 * @param mensajeBitacora the mensajeBitacora to set
	 */
	public void setMensajeBitacora(String mensajeBitacora) {
		this.mensajeBitacora = mensajeBitacora;
	}

	/**
	 * @return the mensajeBitacora
	 */
	public String getMensajeBitacora() {
		return mensajeBitacora;
	}	
	
}
