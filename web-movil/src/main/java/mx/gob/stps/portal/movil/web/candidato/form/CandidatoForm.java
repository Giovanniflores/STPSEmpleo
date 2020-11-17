package mx.gob.stps.portal.movil.web.candidato.form;

import java.util.List;

import mx.gob.stps.portal.core.candidate.vo.CandidatoVo;
import mx.gob.stps.portal.core.candidate.vo.ConocimientoComputacionVO;
import mx.gob.stps.portal.core.candidate.vo.InformacionGeneralVO;
import mx.gob.stps.portal.core.infra.utils.Constantes.CONTACTO_CORREO;
import mx.gob.stps.portal.core.infra.utils.Constantes.CONTACTO_TELEFONO;
import mx.gob.stps.portal.core.infra.vo.EntrevistaVO;
import mx.gob.stps.portal.core.oferta.busqueda.vo.OfertaPorPerfilVO;
import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaEmpleoJB;
import mx.gob.stps.portal.movil.web.oferta.form.PerfilJB;
import mx.gob.stps.portal.core.oferta.detalle.helper.Utils;

import org.apache.struts.action.ActionForm;

public final class CandidatoForm extends ActionForm {
	private static final long serialVersionUID = -5188252558646874086L;

	private long idCandidato;

	private CandidatoVo candidato;
	private List<CandidatoVo> candidatos;
	
	
	private PerfilJB perfilLaboral;
	private InformacionGeneralVO informacionGeneralVO;
	
	private List<OfertaPorPerfilVO> ofertas;
	private List<OfertaEmpleoJB> misOfertas;
	private List<OfertaEmpleoJB> empresas;
	private List<EntrevistaVO> entrevistas;
	private ConocimientoComputacionVO conocimientoComputacion;
	
	public long getIdCandidato() {
		return idCandidato;
	}

	public void setIdCandidato(long idCandidato) {
		this.idCandidato = idCandidato;
	}

	public CandidatoVo getCandidato() {
		return candidato;
	}

	public void setCandidato(CandidatoVo candidato) {
		this.candidato = candidato;
	}

	public PerfilJB getPerfilLaboral() {
		return perfilLaboral;
	}

	public void setPerfilLaboral(PerfilJB perfilLaboral) {
		this.perfilLaboral = perfilLaboral;
	}

	public List<OfertaPorPerfilVO> getOfertas() {
		return ofertas;
	}

	public void setOfertas(List<OfertaPorPerfilVO> ofertas) {
		this.ofertas = ofertas;
	}

	public List<OfertaEmpleoJB> getMisOfertas() {
		return misOfertas;
	}

	public void setMisOfertas(List<OfertaEmpleoJB> misOfertas) {
		this.misOfertas = misOfertas;
	}

	public List<OfertaEmpleoJB> getEmpresas() {
		return empresas;
	}

	public void setEmpresas(List<OfertaEmpleoJB> empresas) {
		this.empresas = empresas;
	}

	public List<EntrevistaVO> getEntrevistas() {
		return entrevistas;
	}

	public void setEntrevistas(List<EntrevistaVO> entrevistas) {
		this.entrevistas = entrevistas;
	}

	public boolean isPerfilContactoTelefono(){
		return (perfilLaboral!=null && perfilLaboral.getContactoTelefono() == CONTACTO_TELEFONO.SI.getIdContactoTelefono());
	}

	public boolean isPerfilContactoCorreo(){
		return (perfilLaboral!=null && perfilLaboral.getPerfilLaboral()!=null &&
				perfilLaboral.getPerfilLaboral().getContactoCorreo() == CONTACTO_CORREO.SI.getIdContactoCorreo());
	}
	
	public String getPerfilTipoTelefono(){
		String tipoTelefono = null;
/*
		if(perfilLaboral!=null && perfilLaboral.getPrincipal().getIdTipoTelefono() == TIPO_TELEFONO.FIJO.getIdOpcion()){
			tipoTelefono = TIPO_TELEFONO.FIJO.getOpcion();
		} else if(perfilLaboral!=null && perfilLaboral.getPrincipal().getIdTipoTelefono() == TIPO_TELEFONO.CELULAR.getIdOpcion()){
			tipoTelefono = TIPO_TELEFONO.CELULAR.getOpcion();
		}
	*/	
		return tipoTelefono;
	}
	
	public String getPerfilDisponibilidadViajar(){
		String disponibilidad = null;
		
		if (perfilLaboral!=null && perfilLaboral.getPerfilLaboral()!=null &&
			perfilLaboral.getPerfilLaboral().getDisponibilidadViajar() > 0){
			disponibilidad = Utils.getLblDisponibilidad(perfilLaboral.getPerfilLaboral().getDisponibilidadViajar());
		}
		
		return disponibilidad;
	}

	public String getPerfilDisponibilidadRadicar(){
		String disponibilidad = null;
		
		if (perfilLaboral!=null && perfilLaboral.getPerfilLaboral()!=null &&
			perfilLaboral.getPerfilLaboral().getDisponibilidadRadicar() > 0){
			disponibilidad = Utils.getLblDisponibilidad(perfilLaboral.getPerfilLaboral().getDisponibilidadRadicar());
		}
		
		return disponibilidad;
	}

	public void setConocimientoComputacion(ConocimientoComputacionVO conocimientoComputacion) {
		this.conocimientoComputacion = conocimientoComputacion;
	}

	public ConocimientoComputacionVO getConocimientoComputacion() {
		return conocimientoComputacion;
	}

	public List<CandidatoVo> getCandidatos() {
		return candidatos;
	}

	public void setCandidatos(List<CandidatoVo> candidatos) {
		this.candidatos = candidatos;
	}

	public InformacionGeneralVO getInformacionGeneralVO() {
		return informacionGeneralVO;
	}

	public void setInformacionGeneralVO(InformacionGeneralVO informacionGeneralVO) {
		this.informacionGeneralVO = informacionGeneralVO;
	}
	
}
