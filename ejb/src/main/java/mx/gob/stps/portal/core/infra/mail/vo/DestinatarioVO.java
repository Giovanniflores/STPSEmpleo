package mx.gob.stps.portal.core.infra.mail.vo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase DestinatarioTO
 * Contenedor de los datos del Destinatario
 */
@SuppressWarnings("unused")
public class DestinatarioVO {
	private String mensaje;
	private String nombre;
	private String cargo;
	private String area;
	private String folio;
	private String fhFolio;
    private String url;
    private String urlcode;

	private String sistema;

	private String login;
	private String password;
	
	private String urlLogo;
	
	private String dia;
	private String mes;
	private String anio;
		
	private String datatable;

	//usados en notificacion postulacion oferta
	private String idCandidato;
	private String nombreCandidato;
	private String fechaPostulacion;
	private String tituloOfertaEmpleo;
	private String curriculum;
	private String estatusOfertaCandidato;
	private String nombreEmpresa;
	private String nombreContacto;
	private String inscritoPPC;
	
	private String tipoPersona;
	
	// Propiedades para plantilla de PDF
	private String oficio;
	private String fecha;
	private String ejemplar;

	private boolean hasAttachment = false;
	private File attachment;
	private String attachmentName;
		
	private String FechaActualizacion;

	private String prefijoNomArchivo;
	
	//Propiedades para plantilla reporte INFONAVIT
	private String fechaInicialRegistroCandidato;
	private String fechaFinalRegistroCandidato;
	private String totalCandidatosRegistrados;
	
	private String idPortalEmpleo;
	
	private String[] attachmentNames;
	private List<File> attachments;
	private List<byte[]> attachmentsAsByteArray;
	
	private String aacute;
	private String eacute;
	private String iacute;
	private String oacute;
	private String uacute;
	private String ntilde;
	
	public String getUrlcode() {
		return urlcode;
	}

	public void setUrlcode(String urlcode) {
		this.urlcode = urlcode;
	}

	public String getUrlLogo() {
		return urlLogo;
	}

	public void setUrlLogo(String urlLogo) {
		this.urlLogo = urlLogo;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	public String getPrefijoNomArchivo() {
		return prefijoNomArchivo;
	}

	public void setPrefijoNomArchivo(String prefijoNomArchivo) {
		this.prefijoNomArchivo = prefijoNomArchivo;
	}

	public String getDatatable() {
		return datatable;
	}

	public void setDatatable(String datatable) {
		this.datatable = datatable;
	}
	
	public String getFechaActualizacion() {
		return FechaActualizacion;
	}

	public void setFechaActualizacion(String fechaActualizacion) {
		FechaActualizacion = fechaActualizacion;
	}
	
	public File getAttachment() {
		return attachment;
	}

	public String getAttachmentName() {
		return attachmentName;
	}

	public void addAttachment(File attachment, String attachmentName) {
		this.attachment = attachment;
		this.attachmentName = attachmentName;
		hasAttachment = true;
	}	
	
	public boolean hasAttachment() {
		return hasAttachment;
	}
	
	public void addAttachments(List<File> attachments, String[] attachmentNames) {
		this.attachments = attachments;
		this.attachmentNames = attachmentNames;
		hasAttachment = true;
	}	
	
	public void addAttachmentsAsByteArray(List<byte[]> attachmentsAsByteArray, String[] attachmentNames) {
		this.attachmentsAsByteArray = attachmentsAsByteArray;
		this.attachmentNames = attachmentNames;
		hasAttachment = true;
	}		

	private ArrayList<String> mails;
	private ArrayList<String> mailsCc;
	private ArrayList<String> mailsCco;

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getSistema() {
		return sistema;
	}

	public void setSistema(String sistema) {
		this.sistema = sistema;
	}

	public void setMails(ArrayList<String> mails) {
		this.mails = mails;
	}

	public String[] getMails() {
		String[] arrayMails = null;
		if (mails!=null){
			arrayMails = new String[mails.size()];
			mails.toArray(arrayMails);
		}		
		
		return arrayMails;
	}

	public void addMail(String mail) {
		if (mails==null){mails = new ArrayList<String>();}
		mails.add(mail);
	}

	public void setMailsCc(ArrayList<String> mailsCc) {
		this.mailsCc = mailsCc;
	}
	public void setMailsCco(ArrayList<String> mailsCco) {
		this.mailsCco = mailsCco;
	}

	public String[] getMailsCc() {
		String[] arrayMails = null;
		if (mailsCc!=null){
			arrayMails = new String[mailsCc.size()];
			mailsCc.toArray(arrayMails);
		}		
		return arrayMails;
	}

	public String[] getMailsCco() {
		String[] arrayMails = null;
		if (mailsCco!=null){
			arrayMails = new String[mailsCco.size()];
			mailsCco.toArray(arrayMails);
		}		
		return arrayMails;
	}

	public void addMailCc(String mail) {
		if (mailsCc==null){mailsCc = new ArrayList<String>();}
		mailsCc.add(mail);
	}

	public void addMailCco(String mail) {
		if (mailsCco==null){mailsCco = new ArrayList<String>();}
		mailsCco.add(mail);
	}

	public String getAnio() {
		return anio;
	}

	public void setAnio(String anio) {
		this.anio = anio;
	}

	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public String getFhFolio() {
		return fhFolio;
	}

	public void setFhFolio(String fhFolio) {
		this.fhFolio = fhFolio;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEjemplar() {
		return ejemplar;
	}

	public void setEjemplar(String ejemplar) {
		this.ejemplar = ejemplar;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getOficio() {
		return oficio;
	}

	/**
	 * @param mensaje the mensaje to set
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	/**
	 * @return the mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}

	public void setOficio(String oficio) {
		this.oficio = oficio;
	}

	/**
	 * @return the idCandidato
	 */
	public String getIdCandidato() {
		return idCandidato;
	}

	/**
	 * @param idCandidato the idCandidato to set
	 */
	public void setIdCandidato(String idCandidato) {
		this.idCandidato = idCandidato;
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
	 * @return the fechaPostulacion
	 */
	public String getFechaPostulacion() {
		return fechaPostulacion;
	}

	/**
	 * @param fechaPostulacion the fechaPostulacion to set
	 */
	public void setFechaPostulacion(String fechaPostulacion) {
		this.fechaPostulacion = fechaPostulacion;
	}

	/**
	 * @return the tituloOfertaEmpleo
	 */
	public String getTituloOfertaEmpleo() {
		return tituloOfertaEmpleo;
	}

	/**
	 * @param tituloOfertaEmpleo the tituloOfertaEmpleo to set
	 */
	public void setTituloOfertaEmpleo(String tituloOfertaEmpleo) {
		this.tituloOfertaEmpleo = tituloOfertaEmpleo;
	}

	/**
	 * @return the curriculum
	 */
	public String getCurriculum() {
		return curriculum;
	}

	/**
	 * @param curriculum the curriculum to set
	 */
	public void setCurriculum(String curriculum) {
		this.curriculum = curriculum;
	}

	/**
	 * @return the estatusOfertaCandidato
	 */
	public String getEstatusOfertaCandidato() {
		return estatusOfertaCandidato;
	}

	/**
	 * @param estatusOfertaCandidato the estatusOfertaCandidato to set
	 */
	public void setEstatusOfertaCandidato(String estatusOfertaCandidato) {
		this.estatusOfertaCandidato = estatusOfertaCandidato;
	}

	/**
	 * 
	 * @return
	 */
	public String getFechaInicialRegistroCandidato() {
		return fechaInicialRegistroCandidato;
	}

	/**
	 * 
	 * @param fechaInicialRegistroCandidato
	 */
	public void setFechaInicialRegistroCandidato(
			String fechaInicialRegistroCandidato) {
		this.fechaInicialRegistroCandidato = fechaInicialRegistroCandidato;
	}

	/**
	 * 
	 * @return
	 */
	public String getFechaFinalRegistroCandidato() {
		return fechaFinalRegistroCandidato;
	}

	/**
	 * 
	 * @param fechaFinalRegistroCandidato
	 */
	public void setFechaFinalRegistroCandidato(String fechaFinalRegistroCandidato) {
		this.fechaFinalRegistroCandidato = fechaFinalRegistroCandidato;
	}

	/**
	 * 
	 * @return
	 */
	public String getTotalCandidatosRegistrados() {
		return totalCandidatosRegistrados;
	}

	/**
	 * 
	 * @param totalCandidatosRegistrados
	 */
	public void setTotalCandidatosRegistrados(String totalCandidatosRegistrados) {
		this.totalCandidatosRegistrados = totalCandidatosRegistrados;
	}

	/**
	 * @return the idPortalEmpleo
	 */
	public String getIdPortalEmpleo() {
		return idPortalEmpleo;
	}

	/**
	 * @param idPortalEmpleo the idPortalEmpleo to set
	 */
	public void setIdPortalEmpleo(String idPortalEmpleo) {
		this.idPortalEmpleo = idPortalEmpleo;
	}

	public String getNombreEmpresa() {
		return nombreEmpresa;
	}

	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}

	public String getTipoPersona() {
		return tipoPersona;
	}

	public void setTipoPersona(String tipoPersona) {
		this.tipoPersona = tipoPersona;
	}

	public String[] getAttachmentNames() {
		return attachmentNames;
	}

	public void setAttachmentNames(String[] attachmentNames) {
		this.attachmentNames = attachmentNames;
	}

	public List<File> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<File> attachments) {
		this.attachments = attachments;
	}

	public List<byte[]> getAttachmentsAsByteArray() {
		return attachmentsAsByteArray;
	}

	public void setAttachmentsAsByteArray(List<byte[]> attachmentsAsByteArray) {
		this.attachmentsAsByteArray = attachmentsAsByteArray;
	}

	public String getNombreContacto() {
		return nombreContacto;
	}

	public void setNombreContacto(String nombreContacto) {
		this.nombreContacto = nombreContacto;
	}

	public String getInscritoPPC() {
		return inscritoPPC;
	}

	public void setInscritoPPC(String inscritoPPC) {
		this.inscritoPPC = inscritoPPC;
	}

	public String getAacute() {
		return "á";
	}

	public void setAacute(String aacute) {
		this.aacute = aacute;
	}

	public String getEacute() {
		return "é";
	}

	public void setEacute(String eacute) {
		this.eacute = eacute;
	}

	public String getIacute() {
		return "í";
	}

	public void setIacute(String iacute) {
		this.iacute = iacute;
	}

	public String getOacute() {
		return "ó";
	}

	public void setOacute(String oacute) {
		this.oacute = oacute;
	}

	public String getUacute() {
		return "ú";
	}

	public void setUacute(String uacute) {
		this.uacute = uacute;
	}

	public String getNtilde() {
		return "ñ";
	}

	public void setNtilde(String ntilde) {
		this.ntilde = ntilde;
	}
}
