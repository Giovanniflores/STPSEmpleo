package mx.gob.stps.portal.web.oferta.search.action;

import static mx.gob.stps.portal.web.infra.utils.Constantes.BODY_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_TEMPLATE_CAND;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_TEMPLATE_MI_ESP_CAND;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TITULO_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.DESCRIPCION_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FACEBOOK_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TWITTER_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.URL_ESPECIFICA;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.gob.stps.portal.core.domicilio.vo.DomicilioVO;
import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.utils.Constantes.MOSTRAR_OFERTA_PERFIL;
import mx.gob.stps.portal.core.oferta.busqueda.vo.OfertaPorPerfilVO;
import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaCandidatoVO;
import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaEmpleoJB;
import mx.gob.stps.portal.core.search.service.IndexerServiceLocator;
import mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO;
import mx.gob.stps.portal.utils.Catalogos;
import mx.gob.stps.portal.utils.Catalogos.ESTATUS;
import mx.gob.stps.portal.web.candidate.delegate.CandidatoBusDelegate;
import mx.gob.stps.portal.web.candidate.delegate.CandidatoBusDelegateImpl;
import mx.gob.stps.portal.web.infra.action.PagerAction;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.infra.utils.Constantes;
import mx.gob.stps.portal.web.infra.utils.PropertiesLoader;
import mx.gob.stps.portal.web.oferta.search.delegate.OfertasPorPerfilBusDelegate;
import mx.gob.stps.portal.web.oferta.search.delegate.OfertasPorPerfilBusDelegateImpl;
import mx.gob.stps.portal.web.oferta.search.form.OfertasPorPerfilForm;
import mx.gob.stps.portal.web.offer.delegate.OfferBusDelegateImpl;
import mx.gob.stps.portal.web.security.delegate.SecutityDelegateImpl;
//import mx.gob.stps.portal.web.offer.delegate.OfferBusDelegateImpl;
import mx.gob.stps.portal.web.security.vo.UsuarioWebVO;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Mario Alberto Vázquez Flores
 * @since 18/03/2011
 **/
public class OfertasPorPerfilAction extends PagerAction {
	
	 public enum SortField {
	        PUESTO ("puesto"),
	        EMPRESA ("empresa"),
	        UBICACION ("ubicacion"),
	        SALARIO("salario");

	        private String fieldName;

	        SortField(String fieldName) {
	            this.fieldName = fieldName;
	        }

	        public String getFieldName() {
	            return fieldName;
	        }

	        public static SortField sortFieldForName(String fieldNameStr) {
	            for (SortField sortField : SortField.values()) {
	                if (sortField.fieldName.equals(fieldNameStr)) {
	                    return sortField;
	                }
	            }

	            assert true : "Invalid fieldNameStr";
	            return null;
	        }
	    }
	 
	 public enum SortType {
	        ASC ("asc"),
	        DESC ("desc");

	        private String type;

	        SortType(String type) {
	            this.type = type;
	        }

	        public String getType() {
	            return type;
	        }

	        public static SortType sortTypeForName(String typeStr) {
	            for (SortType sortType : SortType.values()) {
	                if (sortType.type.equals(typeStr)) {
	                    return sortType;
	                }
	            }

	            assert true : "Invalid typeStr";
	            return null;
	        }
	    }	

	private static Logger logger = Logger.getLogger(OfertasPorPerfilAction.class);
	
    private SortType sortType;
    private SortField sortField;
    


	/**
	 * Obtiene las ofertas activas por perfil
	 * 
	 * @author Mario Alberto Vázquez Flores
	 * @since 18/03/2011
	 **/
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		OfertasPorPerfilForm ofertasForm = (OfertasPorPerfilForm) form;
		UsuarioWebVO usuario = super.getUsuario(session);
		CandidatoBusDelegate services = CandidatoBusDelegateImpl.getInstance();
		OfferBusDelegateImpl service = (OfferBusDelegateImpl) OfferBusDelegateImpl.getInstance();
		session.removeAttribute("showMsg");		
		boolean postulado=false;
		boolean detailViewed= (String) session.getAttribute("detalle")!=null ? true:false;		
		
		String nextAction = FORWARD_TEMPLATE_MI_ESP_CAND;

		if (ofertasForm.getTipoConsulta() == MOSTRAR_OFERTA_PERFIL.TOP.getTipoOfertaPerfil()){			
			nextAction = FORWARD_JSP;
			//numRegistros = 5; //OFERTA_PERFIL_TOP;			
		}
		
		List<OfertaPorPerfilVO> ofertasPerfilFiltradas = new ArrayList<OfertaPorPerfilVO>();

		try {

			if (usuario!=null){
				int compatibilityLimit = Constantes.COMPATIBILITY_LIMIT;
				//se consulta si esta inscrito al ppc
				try {
					int ppcEstatus = services.consultarPpcEstatus(usuario.getIdPropietario());
					
					if (ppcEstatus==ESTATUS.ACTIVO_PPC.getIdOpcion() || ppcEstatus==ESTATUS.INACTIVO_PPC.getIdOpcion()){
						ofertasForm.setInscritoPPC(true);
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
				if(ofertasForm.getOfertas() == null)
				{
					List<Long[]> idOffers = IndexerServiceLocator.getIndexerServiceRemote().buscaOfertasRecomendadas(usuario.getIdPropietario(),compatibilityLimit);
					ofertasPerfilFiltradas = getOfertasPerfilFiltradas(idOffers);
				}else{
					ofertasPerfilFiltradas = ofertasForm.getOfertas();
				}

			}
			//Se verifica si existe almenos una oferta en la que se haya postulado el candidato
			if (ofertasPerfilFiltradas.size() > 0){

				List<Long> listOfertas = new ArrayList<Long>();
				for(OfertaPorPerfilVO oferta : ofertasPerfilFiltradas){
					listOfertas.add(Long.valueOf(oferta.getIdOfertaEmpleo()));
				}
				
				postulado = service.validaPostulacionXOferta(usuario.getIdPropietario(), listOfertas );

			}

			// Set de ofertas y resumen de ofertas
			//ofertasForm.setOfertas(ofertasPerfil);
			ofertasForm.setOfertas(ofertasPerfilFiltradas);	
			//session.setAttribute(FULL_LIST, ofertasPerfil);
			session.setAttribute(FULL_LIST, ofertasPerfilFiltradas);
			session.setAttribute("NUM_REGISTROS", PAGE_NUM_ROW);
			session.setAttribute("_urlpageinvoke", "ofertasPerfiles.do?method=init");		
			

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		} finally {
			if(!detailViewed){
				session.setAttribute("showMsg", "Show_Message");
				int idMovimiento=0;
				if (usuario!=null)
					/*registro en MovimientoUsuario**/
					idMovimiento = SecutityDelegateImpl.getInstance().busquedaPorPerfil(usuario.getIdUsuario(), usuario.getIdPerfil());
					session.setAttribute("idMovimiento", idMovimiento);
					
				   /*registro en HistoricoBusquedaPpc**/
				if (ofertasPerfilFiltradas.size() > 0 && ofertasForm.isInscritoPPC()){
				    SecutityDelegateImpl.getInstance().busquedaPorPerfilPPC(usuario.getIdPropietario(), idMovimiento, ofertasPerfilFiltradas, postulado);
				}
			}
		}
		session.removeAttribute("detalle");								
		ocultaBarraArticulos(request);
		request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
        PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Ofertas por perfil");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Espacio Candidato, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
		return mapping.findForward(nextAction);
	}

	/**
	 * Obtiene las ofertas activas por perfil y empresa
	 * 
	 * @author Mario Alberto Vázquez Flores
	 * @since 18/03/2011
	 **/
	public ActionForward ofertasPerfilEmpresa(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		OfertasPorPerfilForm ofertasForm = (OfertasPorPerfilForm) form;
		ofertasForm.reset(mapping, request);

		OfertasPorPerfilBusDelegate services = OfertasPorPerfilBusDelegateImpl
				.getInstance();

		// Obtiene ofertas segun el perfil del candidato y la empresa
		try {
			List<OfertaPorPerfilVO> ofertasPerfilEmpresa = services
					.obtenerOfertasPorPerfilEmpresa(
							ofertasForm.getIdCandidato(),
							ofertasForm.getIdEmpresa());

			ofertasForm.setOfertas(ofertasPerfilEmpresa);

		} catch (SQLException e) {
			logger.error(e); // TODO NOTIFICAR ERROR
		} catch (ServiceLocatorException e) {
			logger.error(e); // TODO NOTIFICAR ERROR
		}

		request.getSession().setAttribute(BODY_JSP,
				mapping.findForward(FORWARD_JSP).getPath());
		return mapping.findForward(FORWARD_TEMPLATE_CAND);
	}

	/**
	 * Obtiene las ofertas activas por empresa
	 * 
	 * @author Mario Alberto Vázquez Flores
	 * @since 18/03/2011
	 **/
	public ActionForward ofertasEmpresa(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		OfertasPorPerfilForm ofertasForm = (OfertasPorPerfilForm) form;
		ofertasForm.reset(mapping, request);

		OfertasPorPerfilBusDelegate services = OfertasPorPerfilBusDelegateImpl
				.getInstance();

		// Obtiene ofertas segun el id_empresa
		try {
			List<OfertaPorPerfilVO> ofertasEmpresa = services
					.obtenerOfertasPorEmpresa(ofertasForm.getIdEmpresa());

			ofertasForm.setOfertas(ofertasEmpresa);

		} catch (SQLException e) {
			logger.error(e); // TODO NOTIFICAR ERROR
		} catch (ServiceLocatorException e) {
			logger.error(e); // TODO NOTIFICAR ERROR
		}

		request.getSession().setAttribute(BODY_JSP,
				mapping.findForward(FORWARD_JSP).getPath());
		return mapping.findForward(FORWARD_TEMPLATE_CAND);
	}
	
	public List<OfertaPorPerfilVO> getOfertasPerfilFiltradas(List<Long[]> indices) {
		List<Long> uniques = new ArrayList<Long>();
		OfferBusDelegateImpl service = (OfferBusDelegateImpl) OfferBusDelegateImpl.getInstance();
		List<OfertaPorPerfilVO> ofertasPerfilFiltradas = new ArrayList<OfertaPorPerfilVO>();
		if (indices != null && !indices.isEmpty()) {
			for(Long[] id:indices) {
				OfertaEmpleoJB ofertaEmpleoJB = null;	
				OfertaPorPerfilVO ofertaPorPerfilVO = new OfertaPorPerfilVO();
				try {
					if (!uniques.contains(id[0])) {
						uniques.add(id[0]);
						ofertaEmpleoJB = service.buscarOfertaEmpleo(id[0]);
					}
				} catch (BusinessException e) {
					e.printStackTrace();
				} catch (ServiceLocatorException e) {
					e.printStackTrace();
				}
				if (null != ofertaEmpleoJB && ofertaEmpleoJB.getEstatusOffer() == Catalogos.ESTATUS.ACTIVO.getIdOpcion()) {
					ofertaPorPerfilVO.setIdEmpresa(Long.valueOf(ofertaEmpleoJB.getIdEmpresa()));
					ofertaPorPerfilVO.setIdOfertaEmpleo(Long.valueOf(ofertaEmpleoJB.getIdOfertaEmpleo()));
					ofertaPorPerfilVO.setTituloOferta(ofertaEmpleoJB.getTituloOferta());
					ofertaPorPerfilVO.setUbicacion(ofertaEmpleoJB.getUbicacion());
					ofertaPorPerfilVO.setEmpresa(ofertaEmpleoJB.getEmpresaNombre());
					ofertaPorPerfilVO.setSalario(Double.valueOf(ofertaEmpleoJB.getSalario()));
					ofertaPorPerfilVO.setFuente(ofertaEmpleoJB.getFuenteId());
					ofertaPorPerfilVO.setFunciones(ofertaEmpleoJB.getFunciones());
					ofertaPorPerfilVO.setNumeroPlazas(Integer.valueOf(ofertaEmpleoJB.getNumeroPlazas()));
					ofertaPorPerfilVO.setMedioContacto(ofertaEmpleoJB.getMedioContacto());
					ofertaPorPerfilVO.setGradoEstudio(ofertaEmpleoJB.getGradoEstudios());
					ofertaPorPerfilVO.setCarrera(ofertaEmpleoJB.getEspecialidades());
					ofertaPorPerfilVO.setOcupacion(ofertaEmpleoJB.getOcupacion());		
					ofertaPorPerfilVO.setCompatibilidad(id[1].intValue());  //id[1]  compatibilidad
					ofertaPorPerfilVO.setIdiomas(ofertaEmpleoJB.getIdiomas());
					ofertaPorPerfilVO.setIdiomasCert(ofertaEmpleoJB.getIdiomasCert());
					ofertaPorPerfilVO.setHabilidades(getHabilidades(ofertaEmpleoJB.getHabilidades()));	
					ofertaPorPerfilVO.setHabilidadGeneral(ofertaEmpleoJB.getHabilidadGeneral());
					ofertaPorPerfilVO.setEstatus(ofertaEmpleoJB.getEstatusOffer());			
					ofertaPorPerfilVO.setContactoCorreo(ofertaEmpleoJB.getContactoCorreo());
					ofertaPorPerfilVO.setContactoTel(ofertaEmpleoJB.getContactoTel());	
					ofertaPorPerfilVO.setContactoDomicilio(ofertaEmpleoJB.getContactoDomicilio());					
					ofertaPorPerfilVO.setCorreoElectronicoContacto(ofertaEmpleoJB.getCorreoElectronicoContacto());
					ofertaPorPerfilVO.setTelefonoOferta(ofertaEmpleoJB.getTelefonoOferta());	
					ofertaPorPerfilVO.setDomicilio(getDomicilio(ofertaEmpleoJB.getDomicilio()));
					ofertasPerfilFiltradas.add(ofertaPorPerfilVO);	
				}
			}				
		}
		return ofertasPerfilFiltradas;
	}
    
	private String getHabilidades(List<CatalogoOpcionVO> habilidadesCandidato) {
		String habilidades ="";
		if(habilidadesCandidato.size()>0){
			for(CatalogoOpcionVO vo: habilidadesCandidato){
				if(vo!=null){
					if(habilidades.equals(""))
						habilidades=vo.getOpcion();
					else 
						habilidades = habilidades+", "+vo.getOpcion();
				}
			}
		}
		return habilidades;
	}
	
	private String getDomicilio(DomicilioVO domicilioVO) {
        StringBuilder domicilio = new StringBuilder();
		
		if (domicilioVO != null) {
	
			if (domicilioVO.getCalle()!=null && !"".equals(domicilioVO.getCalle()))
				domicilio.append(domicilioVO.getCalle());		
	
			if(domicilioVO.getNumeroExterior() != null && !"".equals(domicilioVO.getNumeroExterior())){
				if(domicilio.length() > 0)
					domicilio.append(",").append(" número ext. ").append(domicilioVO.getNumeroExterior());		
				}

			if(domicilioVO.getNumeroInterior() != null  && !"".equals(domicilioVO.getNumeroInterior())){
				if(domicilio.length() > 0)
					domicilio.append(",").append(" número int. ").append(domicilioVO.getNumeroInterior());		
				}
		
			if(domicilioVO.getEntreCalle() != null && !"".equals(domicilioVO.getEntreCalle())){		
				if(domicilio.length() > 0)
					domicilio.append(",").append(" entre ").append(domicilioVO.getEntreCalle());	
				}
			
			if(domicilioVO.getyCalle() != null && !"".equals(domicilioVO.getyCalle())){
				if(domicilio.length() > 0)
					domicilio.append(" y ").append(domicilioVO.getyCalle());
				}
			
			if (domicilioVO.getEntidad() != null && !"".equals(domicilioVO.getEntidad())){
				if(domicilio.length() > 0)
					domicilio.append(", ").append(domicilioVO.getEntidad());
			}
			
			if (domicilioVO.getMunicipio() != null && !"".equals(domicilioVO.getMunicipio())){
				if(domicilio.length() > 0)
					domicilio.append(", ").append(domicilioVO.getMunicipio());
			}
	
			if (domicilioVO.getColonia() != null && !"".equals(domicilioVO.getColonia())){
				if(domicilio.length() > 0)
					domicilio.append(", ").append(domicilioVO.getColonia());
			}
	
			if (domicilioVO.getCodigoPostal() != null && !"".equals(domicilioVO.getCodigoPostal())){							
				if(domicilio.length() > 0)
					domicilio.append(", ").append("C.P. ").append(domicilioVO.getCodigoPostal());							
			}
		}
		return domicilio.toString();
	}

	 public ActionForward ordenarOfertas(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

	        final int veryFirstPage = 1;

	        HttpSession session = request.getSession();
	        OfertasPorPerfilForm ofertasForm = (OfertasPorPerfilForm)form;
	        UsuarioWebVO usuario = this.getUsuario(session);

	        String tipoOrden = request.getParameter("tipoOrden");    // asc | desc
	        sortType = SortType.sortTypeForName(tipoOrden);
	        String tipoColumna = request.getParameter("tipoColumna");   // puesto | empresa | ubicacion | salario
	        sortField = SortField.sortFieldForName(tipoColumna);

			List<OfertaPorPerfilVO> ofertasPerfil = new ArrayList<OfertaPorPerfilVO>();
	        if (null != usuario) {
	        	ofertasPerfil = (List<OfertaPorPerfilVO>) session.getAttribute(FULL_LIST);

	            Comparator<OfertaPorPerfilVO> comparator = new Comparator<OfertaPorPerfilVO>() {
	                @Override
	                public int compare(OfertaPorPerfilVO obj1, OfertaPorPerfilVO obj2) {
	                    int test = 0;

	                    switch (sortField) {
	                        case PUESTO:
	                            test = obj1.getTituloOferta().toLowerCase().trim().compareTo(obj2.getTituloOferta().toLowerCase().trim());
	                            break;
	                        case EMPRESA:
	                            test = obj1.getEmpresa().toLowerCase().trim().compareTo(obj2.getEmpresa().toLowerCase().trim());
	                            break;
	                        case UBICACION:
	                            test = obj1.getUbicacion().toLowerCase().trim().compareTo(obj2.getUbicacion().toLowerCase().trim());
	                            break;
	                        case SALARIO:
	                            test = Double.compare(obj2.getSalario(), obj1.getSalario());
	                            break;
	                    }

	                    return test;
	                }
	            };

	            Collections.sort(ofertasPerfil, comparator);
	            if (sortType == SortType.DESC) {
	                Collections.reverse(ofertasPerfil);
	            }

	            session.setAttribute(FULL_LIST, ofertasPerfil);
	            session.setAttribute("NUM_REGISTROS", PAGE_NUM_ROW);
	    		session.setAttribute("_urlpageinvoke", "ofertasPerfiles.do?method=init");

	        }

			ofertasForm.setOfertas(ofertasPerfil);				  
			
			ocultaBarraArticulos(request);
			request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());			
	        return page(veryFirstPage, mapping, session, "");
	    }	
	
}
