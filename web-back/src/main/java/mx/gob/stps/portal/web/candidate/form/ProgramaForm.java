package mx.gob.stps.portal.web.candidate.form;

import java.util.List;

import org.apache.struts.action.ActionForm;

import mx.gob.stps.portal.web.offer.wrapper.PerfilJB;
import mx.gob.stps.portal.core.candidate.vo.IdiomaVO;
import mx.gob.stps.portal.web.infra.helper.DiscapacidadBO;
import mx.gob.stps.portal.core.candidate.vo.GradoAcademicoVO;
import mx.gob.stps.portal.core.infra.utils.Constantes.MODALIDAD;
import mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO;
import mx.gob.stps.portal.persistencia.vo.FormatoLPAVO;
import mx.gob.stps.portal.persistencia.vo.FormatoPTATCultivosVO;
import mx.gob.stps.portal.persistencia.vo.FormatoPTATHclinicaVO;
import mx.gob.stps.portal.persistencia.vo.FormatoPTATMaquinariaVO;
import mx.gob.stps.portal.persistencia.vo.FormatoPTATVO;
import mx.gob.stps.portal.persistencia.vo.FormatoSNE01VO;
import mx.gob.stps.portal.persistencia.vo.BeneficiarioVO;
import mx.gob.stps.portal.persistencia.vo.ReferenciaLaboralVO;
import mx.gob.stps.portal.persistencia.vo.ModalidadCandidatoVO;

public class ProgramaForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 940881586223348972L;
	
	private MODALIDAD modalidad;
	
	private String descPrograma;
	
	private String descRequisitos;
	
	private String descDocumentos;
	
	private List<MODALIDAD> path;
	
	private List<String> requisitos;
	
	private List<String> documentos;
	
	/**PTAT **/
	
	private FormatoPTATVO ptat;
	
	private String experienciaAgricola;
	
	private ModalidadCandidatoVO modalidadCandidato;
	
	private List<FormatoPTATCultivosVO> products;
	
	private List<FormatoPTATHclinicaVO> enfermedades;
	
	private List<FormatoPTATMaquinariaVO> engines;
	
	/**Movilidad Interna **/
	
	private PerfilJB perfil;
	
	private FormatoSNE01VO sne;
	
	private DiscapacidadBO discapacidad;
	
	private BeneficiarioVO beneficiario;
	
	/**Movilidad Externa **/
	
	private int licManejoTiene;
	
	private int licManejoTipo;
	
	private FormatoLPAVO mml;
	
	private long idNivelEqCanadiense;
	
	private GradoAcademicoVO ultimoGrado;
	
	private ReferenciaLaboralVO referenciaPrincipal;
	
	/**Fortalecer Idiomas **/
	
	private IdiomaVO idiomaReqIng;
	
	private IdiomaVO idiomaReqFrn;
	
	private int totalIdiomasAdicionales;
	
	private String[] idiomasDependientes;
	
	private List<IdiomaVO> idiomasAdicionales;
	
	private List<CatalogoOpcionVO> catalogoIdiomas;
	
	private List<CatalogoOpcionVO> catalogoDominios;
	
	private List<CatalogoOpcionVO> idiomasMultiRegistro1;
	
	private List<CatalogoOpcionVO> idiomasMultiRegistro2;
	
	private List<CatalogoOpcionVO> idiomasMultiRegistro3;

	public String getDescPrograma() {
		return descPrograma;
	}

	public void setDescPrograma(String descPrograma) {
		this.descPrograma = descPrograma;
	}

	public List<MODALIDAD> getPath() {
		return path;
	}

	public void setPath(List<MODALIDAD> path) {
		this.path = path;
	}

	public String getDescRequisitos() {
		return descRequisitos;
	}

	public void setDescRequisitos(String descRequisitos) {
		this.descRequisitos = descRequisitos;
	}

	public List<String> getRequisitos() {
		return requisitos;
	}

	public void setRequisitos(List<String> requisitos) {
		this.requisitos = requisitos;
	}

	public ModalidadCandidatoVO getModalidadCandidato() {
		return modalidadCandidato;
	}

	public void setModalidadCandidato(ModalidadCandidatoVO modalidadCandidato) {
		this.modalidadCandidato = modalidadCandidato;
	}

	public MODALIDAD getModalidad() {
		return modalidad;
	}

	public void setModalidad(MODALIDAD modalidad) {
		this.modalidad = modalidad;
	}

	public long getIdNivelEqCanadiense() {
		return idNivelEqCanadiense;
	}

	public void setIdNivelEqCanadiense(long idNivelEqCanadiense) {
		this.idNivelEqCanadiense = idNivelEqCanadiense;
	}

	public ReferenciaLaboralVO getReferenciaPrincipal() {
		return referenciaPrincipal;
	}

	public void setReferenciaPrincipal(ReferenciaLaboralVO referenciaPrincipal) {
		this.referenciaPrincipal = referenciaPrincipal;
	}

	public GradoAcademicoVO getUltimoGrado() {
		return ultimoGrado;
	}

	public void setUltimoGrado(GradoAcademicoVO ultimoGrado) {
		this.ultimoGrado = ultimoGrado;
	}

	public PerfilJB getPerfil() {
		return perfil;
	}

	public void setPerfil(PerfilJB perfil) {
		this.perfil = perfil;
	}

	public List<String> getDocumentos() {
		return documentos;
	}

	public void setDocumentos(List<String> documentos) {
		this.documentos = documentos;
	}

	public String getDescDocumentos() {
		return descDocumentos;
	}

	public void setDescDocumentos(String descDocumentos) {
		this.descDocumentos = descDocumentos;
	}

	public FormatoSNE01VO getSne() {
		return sne;
	}

	public void setSne(FormatoSNE01VO sne) {
		this.sne = sne;
	}

	public DiscapacidadBO getDiscapacidad() {
		return discapacidad;
	}

	public void setDiscapacidad(DiscapacidadBO discapacidad) {
		this.discapacidad = discapacidad;
	}

	public BeneficiarioVO getBeneficiario() {
		return beneficiario;
	}

	public void setBeneficiario(BeneficiarioVO beneficiario) {
		this.beneficiario = beneficiario;
	}

	public FormatoLPAVO getMml() {
		return mml;
	}

	public void setMml(FormatoLPAVO mml) {
		this.mml = mml;
	}

	public int getLicManejoTiene() {
		return licManejoTiene;
	}

	public void setLicManejoTiene(int licManejoTiene) {
		this.licManejoTiene = licManejoTiene;
	}

	public int getLicManejoTipo() {
		return licManejoTipo;
	}

	public void setLicManejoTipo(int licManejoTipo) {
		this.licManejoTipo = licManejoTipo;
	}

	public int getTotalIdiomasAdicionales() {
		return totalIdiomasAdicionales;
	}

	public void setTotalIdiomasAdicionales(int totalIdiomasAdicionales) {
		this.totalIdiomasAdicionales = totalIdiomasAdicionales;
	}

	public List<CatalogoOpcionVO> getCatalogoIdiomas() {
		return catalogoIdiomas;
	}

	public void setCatalogoIdiomas(List<CatalogoOpcionVO> catalogoIdiomas) {
		this.catalogoIdiomas = catalogoIdiomas;
	}

	public List<CatalogoOpcionVO> getCatalogoDominios() {
		return catalogoDominios;
	}

	public void setCatalogoDominios(List<CatalogoOpcionVO> catalogoDominios) {
		this.catalogoDominios = catalogoDominios;
	}

	public List<CatalogoOpcionVO> getIdiomasMultiRegistro1() {
		return idiomasMultiRegistro1;
	}

	public void setIdiomasMultiRegistro1(List<CatalogoOpcionVO> idiomasMultiRegistro1) {
		this.idiomasMultiRegistro1 = idiomasMultiRegistro1;
	}

	public List<CatalogoOpcionVO> getIdiomasMultiRegistro2() {
		return idiomasMultiRegistro2;
	}

	public void setIdiomasMultiRegistro2(
			List<CatalogoOpcionVO> idiomasMultiRegistro2) {
		this.idiomasMultiRegistro2 = idiomasMultiRegistro2;
	}

	public List<CatalogoOpcionVO> getIdiomasMultiRegistro3() {
		return idiomasMultiRegistro3;
	}

	public void setIdiomasMultiRegistro3(
			List<CatalogoOpcionVO> idiomasMultiRegistro3) {
		this.idiomasMultiRegistro3 = idiomasMultiRegistro3;
	}

	public List<IdiomaVO> getIdiomasAdicionales() {
		return idiomasAdicionales;
	}

	public void setIdiomasAdicionales(List<IdiomaVO> idiomasAdicionales) {
		this.idiomasAdicionales = idiomasAdicionales;
	}

	public String[] getIdiomasDependientes() {
		return idiomasDependientes;
	}

	public void setIdiomasDependientes(String[] idiomasDependientes) {
		this.idiomasDependientes = idiomasDependientes;
	}

	public FormatoPTATVO getPtat() {
		return ptat;
	}

	public void setPtat(FormatoPTATVO ptat) {
		this.ptat = ptat;
	}

	public String getExperienciaAgricola() {
		return experienciaAgricola;
	}

	public void setExperienciaAgricola(String experienciaAgricola) {
		this.experienciaAgricola = experienciaAgricola;
	}

	public IdiomaVO getIdiomaReqIng() {
		return idiomaReqIng;
	}

	public void setIdiomaReqIng(IdiomaVO idiomaReqIng) {
		this.idiomaReqIng = idiomaReqIng;
	}

	public IdiomaVO getIdiomaReqFrn() {
		return idiomaReqFrn;
	}

	public void setIdiomaReqFrn(IdiomaVO idiomaReqFrn) {
		this.idiomaReqFrn = idiomaReqFrn;
	}

	public List<FormatoPTATCultivosVO> getProducts() {
		return products;
	}

	public void setProducts(List<FormatoPTATCultivosVO> products) {
		this.products = products;
	}

	public List<FormatoPTATHclinicaVO> getEnfermedades() {
		return enfermedades;
	}

	public void setEnfermedades(List<FormatoPTATHclinicaVO> enfermedades) {
		this.enfermedades = enfermedades;
	}

	public List<FormatoPTATMaquinariaVO> getEngines() {
		return engines;
	}

	public void setEngines(List<FormatoPTATMaquinariaVO> engines) {
		this.engines = engines;
	}
}