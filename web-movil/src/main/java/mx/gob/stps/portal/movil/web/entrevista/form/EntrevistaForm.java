package mx.gob.stps.portal.movil.web.entrevista.form;

import java.util.List;

import mx.gob.stps.portal.core.infra.vo.EntrevistaVO;

import org.apache.struts.action.ActionForm;

public class EntrevistaForm extends ActionForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3399823677003951690L;
	
	private List<EntrevistaVO> entrevistas;
	private String tituloTipo;
	private String tituloEtiqueta;
	
	private int idEntrevista;
	private String tituloOferta;
	private String nombreCandidato;
	private String fechaEntrevista;
	private String horaEntrevista;
	private int idCandidato;
	private String razonSocial; 
	
	private int idPerfil;
	
	private long idOferta;
	
	public List<EntrevistaVO> getEntrevistas() {
		return entrevistas;
	}
	public void setEntrevistas(List<EntrevistaVO> entrevistas) {
		this.entrevistas = entrevistas;
	}
	public String getTituloTipo() {
		return tituloTipo;
	}
	public void setTituloTipo(String tituloTipo) {
		this.tituloTipo = tituloTipo;
	}
	public String getTituloEtiqueta() {
		return tituloEtiqueta;
	}
	public void setTituloEtiqueta(String tituloEtiqueta) {
		this.tituloEtiqueta = tituloEtiqueta;
	}
	public int getIdEntrevista() {
		return idEntrevista;
	}
	public void setIdEntrevista(int idEntrevista) {
		this.idEntrevista = idEntrevista;
	}
	public String getTituloOferta() {
		return tituloOferta;
	}
	public void setTituloOferta(String tituloOferta) {
		this.tituloOferta = tituloOferta;
	}
	public String getNombreCandidato() {
		return nombreCandidato;
	}
	public void setNombreCandidato(String nombreCandidato) {
		this.nombreCandidato = nombreCandidato;
	}
	public String getFechaEntrevista() {
		return fechaEntrevista;
	}
	public void setFechaEntrevista(String fechaEntrevista) {
		this.fechaEntrevista = fechaEntrevista;
	}
	public String getHoraEntrevista() {
		return horaEntrevista;
	}
	public void setHoraEntrevista(String horaEntrevista) {
		this.horaEntrevista = horaEntrevista;
	}
	public void setIdCandidato(int idCandidato) {
		this.idCandidato = idCandidato;
	}
	public int getIdCandidato() {
		return idCandidato;
	}
	public void setIdPerfil(int idPerfil) {
		this.idPerfil = idPerfil;
	}
	public int getIdPerfil() {
		return idPerfil;
	}
	public void setIdOferta(long idOferta) {
		this.idOferta = idOferta;
	}
	public long getIdOferta() {
		return idOferta;
	}
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	public String getRazonSocial() {
		return razonSocial;
	}
	
	
	

}
