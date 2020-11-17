package mx.gob.stps.portal.web.company.form;

import java.util.List;

import org.apache.struts.action.ActionForm;

public class ShowCompanyForm extends ActionForm {

	private long idUsuario;
	private long idTipoUsuario;
	private long idPerfil;
	private long idEmpresa;
	private String nombreEmpresa;
	private String descripcion;
	private String paginaWeb;
	private List lstOfertas;
	private List lstEmpresaOfrece;
	private double salarioMinimo;
	private double salarioMaximo;
	
	
	public void reset() {
		nombreEmpresa = null;
		descripcion = null;
		paginaWeb =  null;
		salarioMinimo = 0.00;
		salarioMaximo = 0.00;
		lstOfertas =  null;	
		lstEmpresaOfrece =  null;	
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
	public List getLstOfertas() {
		return lstOfertas;
	}

	/**
	 * @param lstOfertas the lstOfertas to set
	 */
	public void setLstOfertas(List lstOfertas) {
		this.lstOfertas = lstOfertas;
	}

	/**
	 * @return the salarioMinimo
	 */
	public double getSalarioMinimo() {
		return salarioMinimo;
	}

	/**
	 * @param salarioMinimo the salarioMinimo to set
	 */
	public void setSalarioMinimo(double salarioMinimo) {
		this.salarioMinimo = salarioMinimo;
	}

	/**
	 * @return the salarioMaximo
	 */
	public double getSalarioMaximo() {
		return salarioMaximo;
	}

	/**
	 * @param salarioMaximo the salarioMaximo to set
	 */
	public void setSalarioMaximo(double salarioMaximo) {
		this.salarioMaximo = salarioMaximo;
	}


	/**
	 * @return the lstEmpresaOfrece
	 */
	public List getLstEmpresaOfrece() {
		return lstEmpresaOfrece;
	}


	/**
	 * @param lstEmpresaOfrece the lstEmpresaOfrece to set
	 */
	public void setLstEmpresaOfrece(List lstEmpresaOfrece) {
		this.lstEmpresaOfrece = lstEmpresaOfrece;
	}
	
	
	
}
