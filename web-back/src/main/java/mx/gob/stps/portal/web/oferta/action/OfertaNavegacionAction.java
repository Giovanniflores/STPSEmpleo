package mx.gob.stps.portal.web.oferta.action;

import static mx.gob.stps.portal.web.company.action.MiEspacioEmpresasAction.EXISTEN_OFERTAS_ACTIVAS;
import static mx.gob.stps.portal.web.company.action.MiEspacioEmpresasAction.NO;
import static mx.gob.stps.portal.web.company.action.MiEspacioEmpresasAction.SI;
import static mx.gob.stps.portal.web.infra.utils.Constantes.BODY_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_NEXT;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_TEMPLATE_MI_ESP_EMP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.OFERTAS_POR_PAGINA_LISTADO;
import static mx.gob.stps.portal.web.infra.utils.Constantes.SIN_ARTICULOS;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TAB_MENU;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TAB_MIS_OFERTAS;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TITULO_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.DESCRIPCION_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FACEBOOK_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TWITTER_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.URL_ESPECIFICA;

import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.core.infra.utils.Constantes.PERFIL;
import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaCandidatoVO;
import mx.gob.stps.portal.core.oferta.vo.OfertaEmpleoVO;
import mx.gob.stps.portal.persistencia.vo.CanalizacionCandidatoVO;
import mx.gob.stps.portal.persistencia.vo.ModalidadCandidatoVO;
import mx.gob.stps.portal.persistencia.vo.ModalidadOfertaVO;
//fixme import mx.gob.stps.portal.core.search.service.IndexerServiceLocator;
import mx.gob.stps.portal.utils.Catalogos.POSTULACION_CARTERA;
import mx.gob.stps.portal.utils.ConstantesGenerales;
import mx.gob.stps.portal.web.candidate.delegate.CandidatoBusDelegate;
import mx.gob.stps.portal.web.candidate.delegate.CandidatoBusDelegateImpl;
import mx.gob.stps.portal.web.infra.action.PagerAction;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.infra.utils.PropertiesLoader;
import mx.gob.stps.portal.web.infra.utils.TablaOfertasCandidato;
import mx.gob.stps.portal.web.infra.utils.Utils;
import mx.gob.stps.portal.web.infra.vo.ResultVO;
import mx.gob.stps.portal.web.oferta.delegate.OfertaBusDelegate;
import mx.gob.stps.portal.web.oferta.delegate.OfertaBusDelegateImpl;
import mx.gob.stps.portal.web.oferta.form.NavegacionForm;
import mx.gob.stps.portal.web.offer.delegate.OfferBusDelegate;
import mx.gob.stps.portal.web.offer.delegate.OfferBusDelegateImpl;
import mx.gob.stps.portal.web.offer.delegate.RecentOfferSummaryBusDelegate;
import mx.gob.stps.portal.web.offer.delegate.RecentOfferSummaryBusDelegateImpl;
import mx.gob.stps.portal.web.security.vo.UsuarioWebVO;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class OfertaNavegacionAction extends PagerAction {

	private static Logger logger = Logger.getLogger(OfertaNavegacionAction.class);
	private static Boolean NO_POSTULADO_POR_CARTERA = false;
	
	
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		UsuarioWebVO usuario =getUsuario(session);
		session.removeAttribute(EXISTEN_OFERTAS_ACTIVAS);
		long idEmpresa = 0;
		long perfil = 0;
		
		if (usuario!=null){
			idEmpresa = usuario.getIdPropietario();
			perfil    = usuario.getIdPerfil();			
		}
		
		session.setAttribute("ESTATUS_ACTIVO", Constantes.ESTATUS.ACTIVO.getIdOpcion());
		session.setAttribute("ESTATUS_ABRIENDO_ESPACIOS_ACTIVO", Constantes.ESTATUS.ABRIENDO_ESPACIOS_ACTIVO.getIdOpcion());
		
		session.setAttribute("OFERTA_BECATE", ConstantesGenerales.TIPO_OFERTA.OFERTA_BECATE.getIdOpcion());
		session.setAttribute("OFERTA_NORMAL", ConstantesGenerales.TIPO_OFERTA.OFERTA_NORMAL.getIdOpcion());
		
		session.setAttribute("_urlpageinvoke", "OfertaNavegacion.do?method=init"); // atributo necesario para invocacion de detalle de oferta.
		
		session.setAttribute("idEmpresa", ""+ idEmpresa); //atributo requerido en la administración de ofertas
		
		session.setAttribute("perfil", ""+ perfil); //atributo requerido en la administración de ofertas

		limpaSesion(request);

		session.setAttribute("OFERTAS_POR_PAGINA_LISTADO",OFERTAS_POR_PAGINA_LISTADO+"");
		try {
			
			session.removeAttribute("ofertas");
			
			OfertaBusDelegate services = OfertaBusDelegateImpl.getInstance();
			List<OfertaEmpleoVO> ofertas = services.consultaMisOfertas(idEmpresa);	
			
			if (request.getParameter("idCandidato") != null && !request.getParameter("idCandidato").isEmpty()){

				for (int i = 0; i < ofertas.size(); i++){
					if (ofertas.get(i).getEstatus() != Constantes.ESTATUS.ACTIVO.getIdOpcion() 
							&& ofertas.get(i).getEstatus() != Constantes.ESTATUS.ABRIENDO_ESPACIOS_ACTIVO.getIdOpcion()){
						ofertas.remove(i);
						i--;
					}
				}		
				
				// El comparador nos ayudará a ordenar la lista por fecha inicio descendente
				Comparator<OfertaEmpleoVO> comparator = new Comparator<OfertaEmpleoVO>(){
						@Override
						public int compare(OfertaEmpleoVO arg0, OfertaEmpleoVO arg1) {
							if (arg1.getFechaInicio().equals(arg0.getFechaInicio()))								
								return Integer.valueOf(String.valueOf(arg0.getIdOfertaEmpleo() - arg1.getIdOfertaEmpleo()));
							else
								return arg1.getFechaInicio().compareTo(arg0.getFechaInicio());
						}
					};									

				Collections.sort(ofertas, comparator);						
				
			}			
			
			for (int i = 0; i < ofertas.size(); i++){
				//Tipo de oferta
				if (services.esOfertaBecate(ofertas.get(i).getIdOfertaEmpleo())){
					ofertas.get(i).setTipoOferta(ConstantesGenerales.TIPO_OFERTA.OFERTA_BECATE.getIdOpcion());
				}else{
					ofertas.get(i).setTipoOferta(ConstantesGenerales.TIPO_OFERTA.OFERTA_NORMAL.getIdOpcion());				
				}
			}
			
			session.setAttribute("ofertas", ofertas);
						

			this.PAGE_NUM_ROW = 5;
		    this.PAGE_JUMP_SIZE = 5;			
			
		    session.removeAttribute("tablaPager");
		    session.removeAttribute("NUM_PAGE_LIST");	    
		    
			session.removeAttribute(FULL_LIST);
			session.setAttribute(FULL_LIST, ofertas);

			session.removeAttribute("NUM_REGISTROS");			
		    session.setAttribute("NUM_REGISTROS", PAGE_NUM_ROW);
		    
			request.setAttribute(SIN_ARTICULOS, "true");
		    
			if(session.getAttribute("noContactosDisplayed")!=null){
				session.removeAttribute("noContactosDisplayed");
				
				session.setAttribute(BODY_JSP, mapping.findForward("NEXT_CONTACTO").getPath());
				return mapping.findForward("NEXT_CONTACTO");
			}
			
			//Obtiene las ofertas y postulaciones recientes
			RecentOfferSummaryBusDelegate summaryServices = RecentOfferSummaryBusDelegateImpl.getInstance();
			//Verifica que exista al menos una oferta con estatus de activa
			Long numeroOfertasActivas =  summaryServices.getCountOfertasActivas(idEmpresa);
			if(numeroOfertasActivas >= 1){
				request.getSession().setAttribute(EXISTEN_OFERTAS_ACTIVAS, SI);
			} else {
				request.getSession().setAttribute(EXISTEN_OFERTAS_ACTIVAS, NO);
			}			
			
			
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
			registraError(request, "aut.error.locator");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			registraError(request, "aut.autorizacion.msg.error");
		}


		ocultaBarraArticulos(request);
		session.setAttribute(TAB_MENU, TAB_MIS_OFERTAS);
		session.setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
        PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Mis ofertas de empleo");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Espacio Empresa, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo ");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
		return mapping.findForward(FORWARD_TEMPLATE_MI_ESP_EMP);
	}
 
	private void limpaSesion(HttpServletRequest request){
		
		request.getSession().removeAttribute("ultimaOferta");
		request.getSession().removeAttribute("ofertaMostradas");
		
	}	


	
	public ActionForward paginacion(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
          int ultimaOferta= request.getSession().getAttribute("ultimaOferta")!=null?Integer.parseInt(request.getSession().getAttribute("ultimaOferta").toString()):0;
          int ofertaMostradas=request.getSession().getAttribute("ofertaMostradas")!=null?Integer.parseInt(request.getSession().getAttribute("ofertaMostradas").toString()):0;
          /*String siguiente=request.getParameter("siguiente");
          String siguienteP=request.getParameter("siguienteP");
          String atras=request.getParameter("atras");
          String atrasP=request.getParameter("atrasp");*/
          
          if(request.getParameter("siguienteP").toString().compareTo("true")==0){
        	  request.getSession().setAttribute("ultimaOferta", ultimaOferta+"");
          }
          
          
          if(request.getParameter("atrasP").toString().compareTo("true")==0){
        	 if(ultimaOferta>OFERTAS_POR_PAGINA_LISTADO)
        		 ultimaOferta=ultimaOferta-OFERTAS_POR_PAGINA_LISTADO-ofertaMostradas;
        	 if(ultimaOferta>=0)
        	  request.getSession().setAttribute("ultimaOferta", ultimaOferta+"");
        	 else
        		request.getSession().removeAttribute("ultimo"); 
          }

          
		request.getSession().setAttribute(BODY_JSP,mapping.findForward(FORWARD_JSP).getPath());
        PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Oferta empleo");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Espacio Empresa, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo ");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
		return mapping.findForward(FORWARD_TEMPLATE_MI_ESP_EMP);
	}

	public ActionForward cancelaOferta(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		long idVacante = Long.parseLong((String) request.getParameter("id"));

		try {
			UsuarioWebVO usuario = getUsuario(request.getSession());
			
			OfertaBusDelegate services = OfertaBusDelegateImpl.getInstance();
			services.cancelaOfertaEmpleo(idVacante, usuario.getIdUsuario());
			
//FIXME: OracleText 
/*
			SearchService searchService = SearchService.getInstance();
			searchService.eliminaVacante(idVacante);		

			try{
				Invocacion remota de indexador
				IndexerServiceLocator.getIndexerServiceRemote().eliminaOfertaindice(idVacante);
			} catch(Exception e){e.printStackTrace();}
			
 */
			request.getSession().removeAttribute("ultimaOferta");
			request.getSession().setAttribute("cancelada", idVacante+"");

		} catch (ServiceLocatorException e) {
			logger.error(e);
			registraError(request, "aut.error.locator");
		} catch (Exception e) {
			logger.error(e);
			registraError(request, "aut.autorizacion.msg.error");
		}

		return mapping.findForward(FORWARD_NEXT);
	}
	
	public ActionForward activaOferta(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		UsuarioWebVO usuario = getUsuario(request.getSession());
		
		long idVacante = Long.parseLong((String) request.getParameter("id"));

		try {
			OfertaBusDelegate services = OfertaBusDelegateImpl.getInstance();

			services.activaOfertaEmpleo(idVacante, usuario.getIdUsuario());
			services.updateFechaOfertaCanceladaActivada(idVacante);
				
			request.getSession().removeAttribute("ultimaOferta");
			request.getSession().setAttribute("activada", idVacante+"");
			
		} catch (BusinessException e) {
			logger.error(e);
			registraError(request, "aut.autorizacion.msg.error");
		} catch (ServiceLocatorException e) {
			logger.error(e);
			registraError(request, "aut.error.locator");
		} catch (Exception e) {
			logger.error(e);
			registraError(request, "aut.autorizacion.msg.error");
		}
		
		return mapping.findForward(FORWARD_NEXT);
	}

	public ActionForward eliminaOferta(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		UsuarioWebVO usuario = getUsuario(request.getSession());
		
		long idVacante = Long.parseLong((String) request.getParameter("id"));
					
		try {
			OfertaBusDelegate services = OfertaBusDelegateImpl.getInstance();
		
			long perfil = usuario.getIdPerfil();
			
			/**if(perfil == PERFIL.EMPRESA.getIdOpcion()){
				services.eliminaOfertaPorEmpresa(idVacante, usuario.getIdUsuario());
			} else **/
			if (perfil == PERFIL.ADMINISTRADOR.getIdOpcion())
				services.eliminaOfertaPorAdministrador(idVacante, usuario.getIdUsuario());
			else 
				services.eliminaOfertaPorEmpresa(idVacante, usuario.getIdUsuario());
			
//FIXME: OracleText
/*
			//Se utiliza el nuevo método para desindexar ofertas en la eliminación
			//SearchService searchService = SearchService.getInstance();
			//searchService.eliminaVacante(idVacante);

			IndexerServiceLocator.getIndexerServiceRemote().eliminaOfertaindice(idVacante);
 */		
			
			request.getSession().removeAttribute("ultimaOferta");
			request.getSession().setAttribute("idOfertaEliminada", idVacante+"");
			
		} /*catch (BusinessException e) {
			logger.error(e);
			registraError(request, "aut.autorizacion.msg.error");
		} */catch (ServiceLocatorException e) {
			logger.error(e);
			registraError(request, "aut.error.locator");
		} catch (Exception e) {
			logger.error(e);
			registraError(request, "aut.autorizacion.msg.error");
		}
		
		//request.getSession().setAttribute(BODY_JSP,mapping.findForward(FORWARD_JSP).getPath());
		return mapping.findForward(FORWARD_NEXT);

	}
	
	@SuppressWarnings({ "unchecked" })
	public ActionForward orderByColumn(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		String tipoOrdenamiento = request.getParameter("tipoOrden");
		String numeroColumna = request.getParameter("tipoColumna");
		List<OfertaEmpleoVO> ofertas =(List<OfertaEmpleoVO>) session.getAttribute(FULL_LIST);
		
		TablaOfertasCandidato tablaOfertasCandidato = new TablaOfertasCandidato(ofertas);
		if(tipoOrdenamiento.equals("asc"))
			tablaOfertasCandidato.ordenarTabla(new Integer(numeroColumna), TablaOfertasCandidato.ORDENAMIENTO_ASCENDENTE);
		else if(tipoOrdenamiento.equals("desc"))
			tablaOfertasCandidato.ordenarTabla(new Integer(numeroColumna), TablaOfertasCandidato.ORDENAMIENTO_DESCENDENTE);
			
		return page(mapping,form,request,response);
	}

	public ActionForward ofertasActivasCanceladas(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		NavegacionForm navegacionForm = (NavegacionForm) form;
		UsuarioWebVO usuario =getUsuario(session);
		Long folioOferta = null;
		String tituloOferta = null;
		long idEmpresa = 0;
		if (usuario!=null)
			idEmpresa = usuario.getIdPropietario();
		try{
			OfertaBusDelegate services = OfertaBusDelegateImpl.getInstance();
			session.removeAttribute("ofertas");
			
			if(navegacionForm.getFolio() > 0)
				folioOferta = new Long(navegacionForm.getFolio());
			
			if(!navegacionForm.getTitulo().trim().isEmpty())
				tituloOferta = navegacionForm.getTitulo().trim();
			
			List<OfertaEmpleoVO> ofertas = services.busquedaOfertaActivaCancelada(idEmpresa, folioOferta, tituloOferta);
			
			/*si la oferta buscada no esta activa se envia mensaje */
			if(ofertas.isEmpty())
				request.setAttribute("vacanteInactiva", Boolean.TRUE);
			
			session.setAttribute("ofertas", ofertas);

			session.setAttribute("ESTATUS_ACTIVO", Constantes.ESTATUS.ACTIVO.getIdOpcion());
			session.setAttribute("ESTATUS_ABRIENDO_ESPACIOS_ACTIVO", Constantes.ESTATUS.ABRIENDO_ESPACIOS_ACTIVO.getIdOpcion());

			this.PAGE_NUM_ROW = 5;
		    this.PAGE_JUMP_SIZE = 5;			
			
		    //tablaPager
		    session.removeAttribute("tablaPager");
		    session.removeAttribute("NUM_PAGE_LIST");	    
		    
			session.removeAttribute(FULL_LIST);
			//logger.info("ofertas: "+ofertas.size());
			session.setAttribute(FULL_LIST, ofertas);

			session.removeAttribute("NUM_REGISTROS");			
		    session.setAttribute("NUM_REGISTROS", PAGE_NUM_ROW);
		    
			request.setAttribute(SIN_ARTICULOS, "true");
		    
			if(session.getAttribute("noContactosDisplayed")!=null){
				//logger.info("----OfertaNavegacionAction.init.noContactosDisplayed:" + session.getAttribute("noContactosDisplayed"));
				session.removeAttribute("noContactosDisplayed");

				//ActionForward forward = mapping.findForward(FORWARD_TEMPLATE_MI_ESP_EMP);
				
				session.setAttribute(BODY_JSP, mapping.findForward("NEXT_CONTACTO").getPath());
				return mapping.findForward("NEXT_CONTACTO");
			}
			
			//Obtiene las ofertas y postulaciones recientes
			RecentOfferSummaryBusDelegate summaryServices = RecentOfferSummaryBusDelegateImpl.getInstance();
			//Verifica que exista al menos una oferta con estatus de activa
			Long numeroOfertasActivas =  summaryServices.getCountOfertasActivas(idEmpresa);
			if(numeroOfertasActivas >= 1)
				request.getSession().setAttribute(EXISTEN_OFERTAS_ACTIVAS, SI);
			else
				request.getSession().setAttribute(EXISTEN_OFERTAS_ACTIVAS, NO);
			
		}catch(SQLException sqlex){
			logger.error(sqlex);
			registraError(request, "aut.error.sql.persist");
		}
		catch (ServiceLocatorException e) {
			logger.error(e);
			registraError(request, "aut.error.locator");
		}catch (Exception e) {
			logger.error(e);
			registraError(request, "aut.autorizacion.msg.error");
		} 
		
		ocultaBarraArticulos(request);
		session.setAttribute(TAB_MENU, TAB_MIS_OFERTAS);
		session.setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
        PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Oferta empleo");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Espacio Empresa, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo ");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
		return mapping.findForward(FORWARD_TEMPLATE_MI_ESP_EMP);
	}

	public ActionForward enviaCandidato(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String msg = new String();
		String typemsg = new String();
		try {
			HttpSession session = request.getSession();
			UsuarioWebVO usuario = getUsuario(session);			
			long idCandidato    = Long.valueOf(request.getParameter("idCandidato"));
			long idOfertaEmpleo = Long.valueOf(request.getParameter("idOfertaEmpleo"));
			long idFuente = Constantes.CATALOGO_FUENTE_QUE_REGISTRA_OFERTA.PORTAL.getIdOpcion();
			if (idCandidato > 0) {
				if (idOfertaEmpleo > 0) {						
					OfferBusDelegate offerServices = OfferBusDelegateImpl.getInstance();
					List<OfertaCandidatoVO> allOffersCandidate = offerServices.findAllOffersByCandidate(idCandidato);
					boolean candPostulado = false;					
					if (allOffersCandidate != null) {
						for (OfertaCandidatoVO of : allOffersCandidate){
							if (null != of.getIdFuente())
								idFuente = of.getIdFuente();
							if (of.getIdOfertaEmpleo() == idOfertaEmpleo && !candPostulado) {
								if (of.getEstatus() == Constantes.ESTATUS.SELECCIONADO.getIdOpcion()) {
									candPostulado = true;
									msg = "El candidato ya está seleccionado para la oferta de empleo";
									typemsg = ResultVO.TYPE_SUCCESS;								
									break;
								} else if (of.getEstatus() == Constantes.ESTATUS.SELECCIONADA.getIdOpcion()) {
									candPostulado = true;
									msg = "El candidato ya está seleccionado para la oferta de empleo";
									typemsg = ResultVO.TYPE_SUCCESS;								
									break;
								} else if (of.getEstatus() == Constantes.ESTATUS.POSTULADO.getIdOpcion()) {
									candPostulado = true;
									msg = "El candidato ya está postulado para la oferta de empleo";
									typemsg = ResultVO.TYPE_SUCCESS;								
									break;
								} else if (of.getEstatus() == Constantes.ESTATUS.VINCULADO.getIdOpcion()) {
									candPostulado = true;
									msg = "El candidato ya está vinculado para la oferta de empleo";
									typemsg = ResultVO.TYPE_SUCCESS;								
									break;
								} else if (of.getEstatus() == Constantes.ESTATUS.EN_PROCESO.getIdOpcion()) {
									candPostulado = true;
									msg = "El candidato ya está en proceso de selección para la oferta de empleo";
									typemsg = ResultVO.TYPE_SUCCESS;								
									break;
								} else if (of.getEstatus() == Constantes.ESTATUS.CONTRATADO.getIdOpcion()) {
									candPostulado = true;
									msg = "El candidato ya ha sido contratado para la oferta de empleo";
									typemsg = ResultVO.TYPE_SUCCESS;								
									break;	
								} else if (of.getEstatus() == Constantes.ESTATUS.NO_ACEPTADO.getIdOpcion()) {
									candPostulado = true;
									msg = "El candidato ya estuvo seleccionado para la oferta de empleo pero finalmente no fue aceptado";
									typemsg = ResultVO.TYPE_SUCCESS;								
									break;
								} else if (of.getEstatus() == Constantes.ESTATUS.NO_INTERESADO.getIdOpcion()) {
									candPostulado = true;
									msg = "El candidato ya estuvo seleccionado para la oferta de empleo pero finalmente se mostró no interesado";
									typemsg = ResultVO.TYPE_SUCCESS;								
									break;
								}
							}
						}
					}
					/*
					List<OfertaCandidatoVO> myoffers = offerServices.misOfertas(idCandidato);
					boolean candPostulado = false;					
					if (myoffers != null){
						for (OfertaCandidatoVO of : myoffers){						
							if (of.getIdOfertaEmpleo() == idOfertaEmpleo){
								candPostulado = true;
								msg = "El candidato ya está postulado en la oferta de empleo";
								typemsg = ResultVO.TYPE_SUCCESS;								
								break;
							}
						}
					}
					if (!candPostulado){
						 myoffers = offerServices.findCandidatesByEstatus(idOfertaEmpleo, Constantes.ESTATUS.SELECCIONADO.getIdOpcion());
							for (OfertaCandidatoVO of : myoffers){						
								if (of.getIdCandidato() == idCandidato    &&
									of.getIdOfertaEmpleo() == idOfertaEmpleo){
									candPostulado = true;
									msg = "El candidato ya está seleccionado para la oferta de empleo";
									typemsg = ResultVO.TYPE_SUCCESS;								
									break;
								}
						}						 
					}
					*/
					boolean[] statusChanneling = checkChanneling(idOfertaEmpleo, idCandidato);
					if (!statusChanneling[0]) {
						candPostulado = true;
						msg = "Se detectó que el candidato no se encuentra canalizado. Invítalo a acudir a la oficina del SNE más cercana para realizar este trámite.";
						typemsg = ResultVO.TYPE_SUCCESS;								
					}else if (statusChanneling[1]) {
						candPostulado = true;
						msg = "Se detectó que está canalizado a otro programa, favor de acudir a la oficina del SNE más cercana para verificar proceso de canalización.";
						typemsg = ResultVO.TYPE_SUCCESS;								
					}
					if (!candPostulado) {
						logger.info("OfertaNavegacionAction.enviaCandidato !candPostulado");
						OfertaCandidatoVO ofertaCandidato = new OfertaCandidatoVO();
						ofertaCandidato.setIdCandidato(idCandidato);
						ofertaCandidato.setIdOfertaEmpleo(idOfertaEmpleo);
						ofertaCandidato.setEstatus(Constantes.ESTATUS.SELECCIONADO.getIdOpcion());
						ofertaCandidato.setFechaAlta(new Date());
						ofertaCandidato.setCompatibilidad(recuperaCompatibilidad(idOfertaEmpleo, idCandidato));
						ofertaCandidato.setIdOficina(ConstantesGenerales.PORTAL_ID_OFICINA);
						ofertaCandidato.setIdUsuario(usuario.getIdUsuario());
						ofertaCandidato.setPostulacionCartera(POSTULACION_CARTERA.NO.getIdOpcion());
						ofertaCandidato.setIdFuente(idFuente);
						logger.info("OfertaNavegacionAction.enviaCandidato ofertaCandidato:" + ofertaCandidato.toString());						
						OfferBusDelegate ofertaCandidatoDelegate = OfferBusDelegateImpl.getInstance();						
						ofertaCandidatoDelegate.create(ofertaCandidato);
						msg = "El candidato se ha seleccionado correctamente";
						typemsg = ResultVO.TYPE_SUCCESS;
					}
				}
				else{
					msg = "Es preciso seleccionar una oferta";
					typemsg = ResultVO.TYPE_ERROR;
				}
			} else {
				msg = "Es preciso seleccionar un candidato";
				typemsg = ResultVO.TYPE_ERROR;
			}
		} catch (Exception e){
			e.printStackTrace(); 
			logger.error(e);
			msg = "Ha ocurrido un error al enviar el candidato";
			typemsg = ResultVO.TYPE_ERROR;			
		}
		
		try{
			ResultVO result = new ResultVO(msg, typemsg);
			String json = Utils.toJson(result);
			redirectJsonResponse(response, json);
		} catch (Exception e){
			e.printStackTrace(); 
			logger.error(e);			
			throw e;
		}
		return null;
	}
	
	private int recuperaCompatibilidad(long idOfertaEmpleo, long idCandidato) throws ServiceLocatorException{

		int compatibilidad = 0;
		
		if (idOfertaEmpleo > 0 && idCandidato > 0){
			OfferBusDelegate servicesOffer = OfferBusDelegateImpl.getInstance();			
			compatibilidad = servicesOffer.match(idOfertaEmpleo, idCandidato);
		}
		return compatibilidad;
	}

	private boolean[] checkChanneling(long offerID, long idCandidato) {
		ModalidadOfertaVO mo = null;
		boolean isChanneling = true;
		boolean checkChanneling = false;
		boolean[] statusChanneling = {isChanneling, checkChanneling};
		CandidatoBusDelegate service = CandidatoBusDelegateImpl.getInstance();
		try {
			//se consulta si la oferta está inscrita en algún subprograma/modalidad
			OfertaBusDelegate offerDelegate = OfertaBusDelegateImpl.getInstance();
			try {
				mo = offerDelegate.obtenerModalidadOfertaByIdOferta(offerID);
			}catch(javax.ejb.EJBTransactionRolledbackException ejb) {
				checkChanneling = false;
			}
			if (null != mo && mo.getIdModalidadOferta()>0) {
				ModalidadCandidatoVO mc = service.getModalidadCandidato(idCandidato, mo.getIdModalidad(), mo.getIdSubprograma());
				if (null != mc && mc.getIdModalidadCandidato()>0) {
					//se consulta si el candidato ha realizado el proceso de canalización
					List<CanalizacionCandidatoVO> cList = service.getCanalizacionCandidatoList(idCandidato);
					if (null != cList && !cList.isEmpty()) {
						//se consulta si el candidato está canalizado a otro subprograma
						for (CanalizacionCandidatoVO vo : cList) {
							if (null != vo.getFechaCanalizacion()) {
								if (vo.getIdModalidadCandidato() != mc.getIdModalidadCandidato()) {
									checkChanneling = true;
									break;
								}
							}
						}
					}else isChanneling = false;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e);
		}
		return statusChanneling;
	}
}
