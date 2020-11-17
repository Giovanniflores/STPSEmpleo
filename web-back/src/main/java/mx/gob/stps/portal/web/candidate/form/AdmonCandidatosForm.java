package mx.gob.stps.portal.web.candidate.form;

import java.util.List;

import mx.gob.stps.portal.core.candidate.vo.CandidatoVo;
import mx.gob.stps.portal.core.oferta.busqueda.vo.OfertaPorPerfilVO;
import mx.gob.stps.portal.core.oferta.vo.OfertaEmpleoVO;

import org.apache.struts.action.ActionForm;

public class AdmonCandidatosForm extends ActionForm{

	private static final long serialVersionUID = 4153326029243514862L;
	
	private long idEmpresa;
	private String tablaPager;
	private OfertaEmpleoVO oferta;
	private OfertaPorPerfilVO ofertaDetalle;
	
	
	private List<CandidatoVo> candidatos;
	private List<CandidatoVo> postulados;
	private List<CandidatoVo> nominales;
	
	private long idEstatusNuevo;
	
	private String listaIdOfertasCandidatos;

	private int goToPageNumber;
	
	public long getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	public OfertaEmpleoVO getOferta() {
		return oferta;
	}
	public void setOferta(OfertaEmpleoVO oferta) {
		this.oferta = oferta;
	}
	public List<CandidatoVo> getCandidatos() {
		return candidatos;
	}
	public void setCandidatos(List<CandidatoVo> candidatos) {
		this.candidatos = candidatos;
	}
	public List<CandidatoVo> getPostulados() {
		return postulados;
	}
	public void setPostulados(List<CandidatoVo> postulados) {
		this.postulados = postulados;
	}
	public void setIdCandidatoEliminar(long idCandidatoEliminar) {
		this.idEstatusNuevo = idCandidatoEliminar;
	}
	public long getIdCandidatoEliminar() {
		return idEstatusNuevo;
	}
	public void setListaIdOfertasCandidatos(String listaIdOfertasCandidatos) {
		this.listaIdOfertasCandidatos = listaIdOfertasCandidatos;
	}
	public String getListaIdOfertasCandidatos() {
		return listaIdOfertasCandidatos;
	}
	public void setOfertaDetalle(OfertaPorPerfilVO ofertaDetalle) {
		this.ofertaDetalle = ofertaDetalle;
	}
	public OfertaPorPerfilVO getOfertaDetalle() {
		return ofertaDetalle;
	}
	public void setTablaPager(String tablaPager) {
		this.tablaPager = tablaPager;
	}
	public String getTablaPager() {
		return tablaPager;
	}
	public void setGoToPageNumber(int goToPageNumber) {
		this.goToPageNumber = goToPageNumber;
	}
	public int getGoToPageNumber() {
		return goToPageNumber;
	}

	public int getTotalCandidatos() {
		return this.candidatos!=null?this.candidatos.size():0;
	}

	public int getTotalPostulados() {
		return this.postulados!=null?this.postulados.size():0;
	}
	
	public int getTotalNominales() {
		return this.nominales!=null?this.nominales.size():0;
	}
	
	public List<CandidatoVo> getNominales() {
		return nominales;
	}
	
	public void setNominales(List<CandidatoVo> nominales) {
		this.nominales = nominales;
	}
}
