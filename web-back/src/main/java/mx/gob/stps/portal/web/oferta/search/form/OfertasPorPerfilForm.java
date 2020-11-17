package mx.gob.stps.portal.web.oferta.search.form;

import java.util.List;

import mx.gob.stps.portal.core.oferta.busqueda.vo.OfertaPorPerfilVO;
import mx.gob.stps.portal.persistencia.vo.EstandarConocerVO;

import org.apache.struts.action.ActionForm;

/**
 * @author Mario Alberto Vázquez Flores
 * @since 18/03/2011
 **/
public class OfertasPorPerfilForm extends ActionForm {

	private static final long serialVersionUID = 1315111748295857610L;

	private long idCandidato;
	private long idEmpresa;
	private int tipoConsulta;
	private boolean inscritoPPC;

	private List<OfertaPorPerfilVO> ofertas;
	
	List<EstandarConocerVO> estandares;
	
	private int goToPageNumber;


	/**
	 * @return the idCandidato
	 */
	public long getIdCandidato() {
		return idCandidato;
	}

	/**
	 * @param idCandidato
	 *            the idCandidato to set
	 */
	public void setIdCandidato(long idCandidato) {
		this.idCandidato = idCandidato;
	}

	/**
	 * @return the idEmpresa
	 */
	public long getIdEmpresa() {
		return idEmpresa;
	}

	/**
	 * @param idEmpresa
	 *            the idEmpresa to set
	 */
	public void setIdEmpresa(long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	/**
	 * @return the tipoConsulta
	 */
	public int getTipoConsulta() {
		return tipoConsulta;
	}

	/**
	 * @param tipoConsulta
	 *            the tipoConsulta to set
	 */
	public void setTipoConsulta(int tipoConsulta) {
		this.tipoConsulta = tipoConsulta;
	}

	/**
	 * @return the ofertas
	 */
	public List<OfertaPorPerfilVO> getOfertas() {
		return ofertas;
	}

	/**
	 * @param ofertas
	 *            the ofertas to set
	 */
	public void setOfertas(List<OfertaPorPerfilVO> ofertas) {
		this.ofertas = ofertas;
	}

	public void setGoToPageNumber(int goToPageNumber) {
		this.goToPageNumber = goToPageNumber;
	}

	public int getGoToPageNumber() {
		return goToPageNumber;
	}

	public List<EstandarConocerVO> getEstandares() {
		return estandares;
	}

	public void setEstandares(List<EstandarConocerVO> estandares) {
		this.estandares = estandares;
	}

	public boolean isInscritoPPC() {
		return inscritoPPC;
	}

	public void setInscritoPPC(boolean inscritoPPC) {
		this.inscritoPPC = inscritoPPC;
	}

	
	
}
