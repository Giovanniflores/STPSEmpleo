package mx.gob.stps.portal.movil.web.oferta.action;

import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_ENTIDAD_FEDERATIVA;
import static mx.gob.stps.portal.movil.web.infra.utils.ConstantesMovil.FORWARD_JSP;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.gob.stps.portal.core.infra.utils.Utils;
import mx.gob.stps.portal.core.infra.utils.Constantes.ENTIDADES_FEDERATIVAS;
import mx.gob.stps.portal.core.infra.utils.Constantes.ESTATUS;
import mx.gob.stps.portal.core.oferta.busqueda.vo.OfertaPorCanalVO;
import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaCandidatoVO;
import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaEmpleoJB;
import mx.gob.stps.portal.core.search.ResultInfoBO;
import mx.gob.stps.portal.movil.web.candidato.util.UtilPostulate;
import mx.gob.stps.portal.movil.web.infra.utils.PropertiesLoaderWeb;
import mx.gob.stps.portal.movil.web.oferta.delegate.OfertaDelegateImpl;
import mx.gob.stps.portal.movil.web.oferta.form.OfertaJB;
import mx.gob.stps.portal.movil.web.oferta.vo.OfertaVO;
import mx.gob.stps.portal.movil.web.seguridad.vo.UsuarioFirmadoVO;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public final class OcupateAction extends PagerOffersAction {

	private static final Logger logger = Logger.getLogger(OcupateAction.class);

	@Override
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String encoding = request.getCharacterEncoding();
		Integer location = null;
		String entity = "";
		if(!"ISO-8859-1".equalsIgnoreCase(request.getCharacterEncoding())) {
			try {
				request.setCharacterEncoding("ISO-8859-1");
			}catch(Exception e) {logger.error("Error al tratar de cambiar el encoding: "+encoding, e);}
		}
		
		String query = request.getParameter("searchQ");
		int place = Utils.parseInt(request.getParameter("idEntidad"));
		
		if (place != -1)
			location = new Integer(place);
		if (null == query)
			query = "";
		logger.info(query);
		logger.info("place "+location);
		List<ResultInfoBO> ofertas = new ArrayList<ResultInfoBO>();
		
		try {
				OfertaDelegateImpl services = OfertaDelegateImpl.getInstance();
				if (null != location) {
					ENTIDADES_FEDERATIVAS entidad = ENTIDADES_FEDERATIVAS.getEntidad(location);
					if (entidad!=null){
						entity = entidad.getDescripcion();
					} else {
						entity = services.getOpcionById(CATALOGO_OPCION_ENTIDAD_FEDERATIVA, location.intValue());
					}
				}
				ofertas = services.buscarOfertasEmpleo(query,location);
				} catch (Exception se) { 
				se.printStackTrace();
				logger.error(se);
			}
		
		// TODO Eliminar

		
		logger.info("Lista Ofertas Ocupate Movil "+ofertas.size());
		logger.info(entity);
		session.removeAttribute(NUM_PAGE_LIST);
		session.setAttribute(FULL_LIST, ofertas);

		session.setAttribute("NUM_REGISTROS", PAGE_NUM_ROW);
		session.setAttribute("searchQ", query);
		session.setAttribute("entity", entity);
		
		page(mapping, form, request, response); // Primera paginacion

		return mapping.findForward(FORWARD_JSP);
	}
	
	@SuppressWarnings("rawtypes")
	protected List getRows(int pagenum, List<ResultInfoBO> index, HttpSession session){
		@SuppressWarnings("unchecked")
		List<OfertaPorCanalVO> rowsPage = super.getRows(pagenum, index, session);
		
		List<OfertaVO> ofertas = new ArrayList<OfertaVO>();
		
		int compatibilityLimit = PropertiesLoaderWeb.getInstance().getPropertyInt("compatibility.upper.limit");
		
		OfertaDelegateImpl services = OfertaDelegateImpl.getInstance();
		
		/*for (OfertaPorCanalVO ofertacanal : rowsPage){
			OfertaVO oferta = OfertaVO.getInstance(ofertacanal);
			
			try {
				OfertaJB ofertadetalle = new OfertaJB();
				OfertaEmpleoJB jb = services.buscarOfertaEmpleo(ofertacanal.getIdOfertaEmpleo());
				BeanUtils.copyProperties(ofertadetalle, jb);
		
				oferta.setDetalle(ofertadetalle);
			} catch (Exception e) { logger.error(e); e.printStackTrace();}

			ofertas.add(oferta);
		}*/
		
		try{
			List<Long> idsOfertas = new ArrayList<Long>();
			
			for (OfertaPorCanalVO ofertacanal : rowsPage){
				long idOfertaEmpleo = ofertacanal.getIdOfertaEmpleo();
				idsOfertas.add(idOfertaEmpleo);
			}

			List<OfertaEmpleoJB> ofertasdetalle = services.buscarOfertasEmpleo(idsOfertas);
			
			Map<Long, OfertaJB> mapDetalles = new HashMap<Long, OfertaJB>();
			for (OfertaEmpleoJB detalle : ofertasdetalle){
				long idOferta = detalle.getOferta().getIdOfertaEmpleo();
				
				OfertaJB offer = new OfertaJB();
				BeanUtils.copyProperties(offer, detalle);
				
				mapDetalles.put(idOferta, offer);
			}
			
			UsuarioFirmadoVO usuario = getUsuarioFirmado(session);

			for (OfertaPorCanalVO ofertacanal : rowsPage){
				OfertaJB ofertadetalle = mapDetalles.get(ofertacanal.getIdOfertaEmpleo());
				
				OfertaVO oferta = OfertaVO.getInstance(ofertacanal);
				oferta.setDetalle(ofertadetalle);

				if (usuario!=null && usuario.isCandidato()){
					boolean postulated = UtilPostulate.isPostulated(ofertacanal.getIdOfertaEmpleo(), usuario.getIdPropietario());
					oferta.setPostulated(postulated);
					
					long compatibility = calculaCompatibilidad(ofertacanal.getIdOfertaEmpleo(), usuario.getIdPropietario());
					oferta.setCompatibility(compatibility);
					
					oferta.setCompatibilityLimit(compatibilityLimit);
				}
				
				ofertas.add(oferta);
			}
			
		} catch (Exception e) { logger.error(e); e.printStackTrace();}
		
		
		return ofertas;
	}

	
	private long calculaCompatibilidad(long idOfertaEmpleo, long idCandidato){
		long compatibility = 0;
		
		try {
			OfertaDelegateImpl services = OfertaDelegateImpl.getInstance();
			compatibility = services.match(idOfertaEmpleo, idCandidato);
		}catch(Exception e) { logger.error(e); }
		
		return compatibility;
	}
	
}
