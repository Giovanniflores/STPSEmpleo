package mx.gob.stps.portal.web.offer.form;


import java.util.List;

import mx.gob.stps.portal.core.oferta.busqueda.vo.OfertaPorCanalVO;

import org.apache.struts.action.ActionForm;


/**
 * Contiene la estructura de datos necesaria para presentar las ofertas por canal.
 * @author jose.jimenez
 *
 */
public class OffersPerCanalForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2194208526616127894L;

	/**
	 * Contiene el tipo de consulta a realizar de acuerdo al canal correspondiente
	 */
	private String searchType;

	/**
	 * Contiene las ofertas correspondientes al tipo de consulta
	 */
	private List<OfertaPorCanalVO> offers;

	/**
	 * @return the searchType
	 */
	public String getSearchType() {
		return searchType;
	}

	/**
	 * @param searchType the searchType to set
	 */
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	/**
	 * @return the offers
	 */
	public List<OfertaPorCanalVO> getOffers() {
		return offers;
	}

	/**
	 * @param offers the offers to set
	 */
	public void setOffers(List<OfertaPorCanalVO> offers) {
		this.offers = offers;
	}
	
}
