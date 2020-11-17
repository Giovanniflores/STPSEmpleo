package mx.gob.stps.portal.web.company.form;

import java.util.List;

import mx.gob.stps.portal.core.oferta.busqueda.vo.OfertaPorCanalVO;

import org.apache.struts.action.ActionForm;



public class ShowMoreCompanyOffersForm extends ActionForm{

	private long idUsuario;
	private long idTipoUsuario;
	private long idPerfil;
	private long idEmpresa;
	private String nombreEmpresa;
	private String descripcion;
	private String paginaWeb;	
	private List<OfertaPorCanalVO> lstOfertas;
	
	public void reset(){
		nombreEmpresa = null;
		descripcion = null;
		paginaWeb =  null;
		lstOfertas = null;
	}

	/**
	 * @return the idUsuario
	 */
	public long getIdUsuario() {
		return idUsuario;
	}

	/**
	 * @param idUsuario the idUsuario to set
	 */
	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}

	/**
	 * @return the idTipoUsuario
	 */
	public long getIdTipoUsuario() {
		return idTipoUsuario;
	}

	/**
	 * @param idTipoUsuario the idTipoUsuario to set
	 */
	public void setIdTipoUsuario(long idTipoUsuario) {
		this.idTipoUsuario = idTipoUsuario;
	}

	/**
	 * @return the idPerfil
	 */
	public long getIdPerfil() {
		return idPerfil;
	}

	/**
	 * @param idPerfil the idPerfil to set
	 */
	public void setIdPerfil(long idPerfil) {
		this.idPerfil = idPerfil;
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
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the paginaWeb
	 */
	public String getPaginaWeb() {
		return paginaWeb;
	}

	/**
	 * @param paginaWeb the paginaWeb to set
	 */
	public void setPaginaWeb(String paginaWeb) {
		this.paginaWeb = paginaWeb;
	}

	/**
	 * @return the lstOfertas
	 */
	public List<OfertaPorCanalVO> getLstOfertas() {
		return lstOfertas;
	}

	/**
	 * @param lstOfertas the lstOfertas to set
	 */
	public void setLstOfertas(List<OfertaPorCanalVO> lstOfertas) {
		this.lstOfertas = lstOfertas;
	}	
	
	
	
}
