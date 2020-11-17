package mx.gob.stps.portal.web.entrevista.form;

import java.util.List;

import mx.gob.stps.portal.core.infra.vo.EntrevistaVO;

import org.apache.struts.action.ActionForm;

/**
 * @author jose.hernandez
 *
 */
public class EntrevistaForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9121374567282110890L;

	private long idEmpresa ;
	private long idCandidato ;

	
	private String 	tipo;
	private String 	fechaEntrevista;
	private long	idEntrevista;
	private String 	hora;
	private String 	ofertaEmpleo;
	private String 	empresa;
	private String  correoCandidato;
	private String  correoEmpresa;
	private String  nombreContactoEmpresa;
	private String  nombreContactoCandidato;
	private String  nombreEmpresa;
	private String  tituloOferta;
	private String  nombreCandidato;	
	private String  fecha;
	private long	idUsuario;
	private String 	etiqueta;
	private String	nombreUsuario;
	private int 	tipoNumeric;
	
	private List<EntrevistaVO>  listEntrevistaVo;
	

	
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
	 * @param idEntrevista the idEntrevista to set
	 */
	public void setIdEntrevista(long idEntrevista) {
		this.idEntrevista = idEntrevista;
	}
	/**
	 * @return the idEntrevista
	 */
	public long getIdEntrevista() {
		return idEntrevista;
	}
	/**
	 * @param hora the hora to set
	 */
	public void setHora(String hora) {
		this.hora = hora;
	}
	/**
	 * @return the hora
	 */
	public String getHora() {
		return hora;
	}
	/**
	 * @param fechaEntrevista the fechaEntrevista to set
	 */
	public void setFechaEntrevista(String fechaEntrevista) {
		this.fechaEntrevista = fechaEntrevista;
	}
	/**
	 * @return the fechaEntrevista
	 */
	public String getFechaEntrevista() {
		return fechaEntrevista;
	}
	/**
	 * @param empresa the empresa to set
	 */
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	/**
	 * @return the empresa
	 */
	public String getEmpresa() {
		return empresa;
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
	 * @return the idEmpresa
	 */
	public long getIdEmpresa() {
		return idEmpresa;
	}
	/**
	 * @return the idCandidato
	 */
	public long getIdCandidato() {
		return idCandidato;
	}
	/**
	 * @param idEmpresa the idEmpresa to set
	 */
	public void setIdEmpresa(long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	/**
	 * @param idCandidato the idCandidato to set
	 */
	public void setIdCandidato(long idCandidato) {
		this.idCandidato = idCandidato;
	}
	/**
	 * @return the correoCandidato
	 */
	public String getCorreoCandidato() {
		return correoCandidato;
	}
	/**
	 * @return the correoEmpresa
	 */
	public String getCorreoEmpresa() {
		return correoEmpresa;
	}
	/**
	 * @param correoCandidato the correoCandidato to set
	 */
	public void setCorreoCandidato(String correoCandidato) {
		this.correoCandidato = correoCandidato;
	}
	/**
	 * @param correoEmpresa the correoEmpresa to set
	 */
	public void setCorreoEmpresa(String correoEmpresa) {
		this.correoEmpresa = correoEmpresa;
	}
	/**
	 * @return the nombreContactoEmpresa
	 */
	public String getNombreContactoEmpresa() {
		return nombreContactoEmpresa;
	}
	/**
	 * @return the nombreContactoCandidato
	 */
	public String getNombreContactoCandidato() {
		return nombreContactoCandidato;
	}
	/**
	 * @param nombreContactoEmpresa the nombreContactoEmpresa to set
	 */
	public void setNombreContactoEmpresa(String nombreContactoEmpresa) {
		this.nombreContactoEmpresa = nombreContactoEmpresa;
	}
	/**
	 * @param nombreContactoCandidato the nombreContactoCandidato to set
	 */
	public void setNombreContactoCandidato(String nombreContactoCandidato) {
		this.nombreContactoCandidato = nombreContactoCandidato;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "EntrevistaForm [idEmpresa=" + idEmpresa + ", idCandidato="
				+ idCandidato + ", tipo=" + tipo + ", fechaEntrevista="
				+ fechaEntrevista + ", idEntrevista=" + idEntrevista
				+ ", hora=" + hora + ", ofertaEmpleo=" + ofertaEmpleo
				+ ", empresa=" + empresa + ", correoCandidato="
				+ correoCandidato + ", correoEmpresa=" + correoEmpresa
				+ ", nombreContactoEmpresa=" + nombreContactoEmpresa
				+ ", nombreContactoCandidato=" + nombreContactoCandidato
				+ ", nombreEmpresa=" + nombreEmpresa + ", tituloOferta="
				+ tituloOferta + ", nombreCandidato=" + nombreCandidato
				+ ", fecha=" + fecha + "]";
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
	 * @param tituloOferta the tituloOferta to set
	 */
	public void setTituloOferta(String tituloOferta) {
		this.tituloOferta = tituloOferta;
	}
	/**
	 * @return the tituloOferta
	 */
	public String getTituloOferta() {
		return tituloOferta;
	}
	/**
	 * @param nombreCandidato the nombreCandidato to set
	 */
	public void setNombreCandidato(String nombreCandidato) {
		this.nombreCandidato = nombreCandidato;
	}
	/**
	 * @return the nombreCandidato
	 */
	public String getNombreCandidato() {
		return nombreCandidato;
	}
	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	/**
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}
	/**
	 * @param listEntrevistaVo the listEntrevistaVo to set
	 */
	public void setListEntrevistaVo(List<EntrevistaVO> listEntrevistaVo) {
		this.listEntrevistaVo = listEntrevistaVo;
	}
	/**
	 * @return the listEntrevistaVo
	 */
	public List<EntrevistaVO> getListEntrevistaVo() {
		return listEntrevistaVo;
	}
	/**
	 * @param idUsuario the idUsuario to set
	 */
	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}
	/**
	 * @return the idUsuario
	 */
	public long getIdUsuario() {
		return idUsuario;
	}
	/**
	 * @param etiqueta the etiqueta to set
	 */
	public void setEtiqueta(String etiqueta) {
		this.etiqueta = etiqueta;
	}
	/**
	 * @return the etiqueta
	 */
	public String getEtiqueta() {
		return etiqueta;
	}
	/**
	 * @param nombreUsuario the nombreUsuario to set
	 */
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	/**
	 * @return the nombreUsuario
	 */
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	/**
	 * @param tipoNumeric the tipoNumeric to set
	 */
	public void setTipoNumeric(int tipoNumeric) {
		this.tipoNumeric = tipoNumeric;
	}
	/**
	 * @return the tipoNumeric
	 */
	public int getTipoNumeric() {
		return tipoNumeric;
	}



}
