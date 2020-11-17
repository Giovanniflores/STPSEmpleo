package mx.gob.stps.portal.web.oferta.form;

import java.util.List;
import mx.gob.stps.portal.core.candidate.vo.CandidatoVo;
import mx.gob.stps.portal.core.infra.vo.EntrevistaVO;
import mx.gob.stps.portal.core.oferta.busqueda.vo.OfertaPorPerfilVO;
import mx.gob.stps.portal.core.oferta.vo.OfertaEmpleoVO;
import org.apache.struts.action.ActionForm;
public class SpaceCompanyForm extends ActionForm{
	
	/**
	 * Control de versión del serializado.
	 */
	private static final long serialVersionUID = -3761798619965073039L;
	//Objeto que contiene los datos del detalle de la oferta 
	private OfertaPorPerfilVO ofertaDetalle;
	//Lista de los candidatos de la oferta
	private List<CandidatoVo> candidatos;
	//Lista de los postulados de la oferta
	private List<CandidatoVo> postulados;
	//Objeto que contiene los datos del detalle de la oferta 
	private OfertaEmpleoVO ofertaCreada;
	//Lista de ofertas activas creadas por la empresa
	private List<OfertaEmpleoVO> listOfertasCreadas;
	//Mensaje en caso de no haber candidatos asociados a la oferta creada
	private String msg = "";
	//Indice del listado donde se sacra el objeto
	private int index;
	//Objeto que nos permite guardar una entrevista en linea
	private EntrevistaVO entrevistaVo;
	
	/**
	 * @return the candidatos
	 */
	public List<CandidatoVo> getCandidatos() {
		return candidatos;
	}
	
	/**
	 * @param candidatos the candidatos to set
	 */
	public void setCandidatos(List<CandidatoVo> candidatos) {
		this.candidatos = candidatos;
	}

	/**
	 * @return the ofertaDetalle
	 */
	public OfertaPorPerfilVO getOfertaDetalle() {
		return ofertaDetalle;
	}

	/**
	 * @param ofertaDetalle the ofertaDetalle to set
	 */
	public void setOfertaDetalle(OfertaPorPerfilVO ofertaDetalle) {
		this.ofertaDetalle = ofertaDetalle;
	}

	/**
	 * @return the postulados
	 */
	public List<CandidatoVo> getPostulados() {
		return postulados;
	}

	/**
	 * @param postulados the postulados to set
	 */
	public void setPostulados(List<CandidatoVo> postulados) {
		this.postulados = postulados;
	}

	/**
	 * @return the ofertaCreada
	 */
	public OfertaEmpleoVO getOfertaCreada() {
		return ofertaCreada;
	}

	/**
	 * @param ofertaCreada the ofertaCreada to set
	 */
	public void setOfertaCreada(OfertaEmpleoVO ofertaCreada) {
		this.ofertaCreada = ofertaCreada;
	}

	/**
	 * @return the listOfertasCreadas
	 */
	public List<OfertaEmpleoVO> getListOfertasCreadas() {
		return listOfertasCreadas;
	}

	/**
	 * @param listOfertasCreadas the listOfertasCreadas to set
	 */
	public void setListOfertasCreadas(List<OfertaEmpleoVO> listOfertasCreadas) {
		this.listOfertasCreadas = listOfertasCreadas;
	}

	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * @param msg the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

	/**
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * @param index the index to set
	 */
	public void setIndex(int index) {
		this.index = index;
	}

	/**
	 * @return the entrevistaVo
	 */
	public EntrevistaVO getEntrevistaVo() {
		return entrevistaVo;
	}

	/**
	 * @param entrevistaVo the entrevistaVo to set
	 */
	public void setEntrevistaVo(EntrevistaVO entrevistaVo) {
		this.entrevistaVo = entrevistaVo;
	}
		
}