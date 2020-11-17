package mx.gob.stps.portal.web.oferta.action;

import static mx.gob.stps.portal.web.infra.utils.Constantes.BODY_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_TEMPLATE_MI_ESP_EMP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.OFERTAS_POR_PAGINA_LISTADO;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TITULO_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.DESCRIPCION_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FACEBOOK_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TWITTER_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.URL_ESPECIFICA;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.utils.Constantes.ESTATUS;
import mx.gob.stps.portal.core.oferta.vo.OfertaEmpleoVO;
import mx.gob.stps.portal.utils.ConstantesGenerales;
import mx.gob.stps.portal.web.infra.action.PagerAction;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.infra.utils.PropertiesLoader;
import mx.gob.stps.portal.web.oferta.delegate.OfertaBusDelegate;
import mx.gob.stps.portal.web.oferta.delegate.OfertaBusDelegateImpl;
import mx.gob.stps.portal.web.oferta.form.RecuperaOfertasForm;

import org.apache.http.ParseException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

//TODO ELIMINAR CLASE, SE CAMBIO LA CLASE PARA LA ADMINISTRACION DE OFERTAS
public class RecuperaOfertasAction extends PagerAction {

	private static Logger logger = Logger.getLogger(OfertaEdicionAction.class);

	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		request.getSession().setAttribute("OFERTAS_POR_PAGINA_LISTADO", OFERTAS_POR_PAGINA_LISTADO + "");
		limpiaSesion(request);

		ocultaBarraArticulos(request);
		
		request.setAttribute("init", "init"); // para distinguir en el form cuando hemos accedido por primera vez
		request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
        PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Recuperar ofertas de empleo");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Espacio Empresa, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo ");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
		return mapping.findForward(FORWARD_TEMPLATE_MI_ESP_EMP);
	}

	public ActionForward obteterOfertasFolio(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		long idEmpresa = request.getSession().getAttribute("idEmpresa") != null ? 
				         Long.parseLong((String) request.getSession().getAttribute("idEmpresa")) : 0;
		limpiaSesion(request);

		RecuperaOfertasForm forma = (RecuperaOfertasForm) form;
		long folio = forma.getFolio() != null ? Long.parseLong(forma.getFolio()) : 0;
		List<OfertaEmpleoVO> listaOfertas = null;
		OfertaBusDelegate services = OfertaBusDelegateImpl.getInstance();

		try {
			if (folio > 0)
				listaOfertas = services.obtenerOfertasEliminadasFolio(
						idEmpresa, ESTATUS.ELIMINADA_EMP.getIdOpcion(),
						ESTATUS.ELIMINADA_VIG.getIdOpcion(), folio);
			limpiaSesion(request);
		

		} catch (BusinessException e) {
			e.printStackTrace();
			logger.error(e); // TODO Notificar error al usuario
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
			logger.error(e); // TODO Notificar error al usuario
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e); // TODO Notificar error al usuario
		}
	    
		ocultaBarraArticulos(request);
		request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
        PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Recuperar oferta");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Recuperar oferta, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo.");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
		return mapping.findForward(FORWARD_TEMPLATE_MI_ESP_EMP);
	}

	public ActionForward busquedaFolio(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		long idEmpresa = Long.parseLong((String) request.getSession().getAttribute("idEmpresa"));
		limpiaSesion(request);
		RecuperaOfertasForm forma = (RecuperaOfertasForm) form;

		long folio = Long.parseLong((String) forma.getFolio());
		
		try {
			OfertaBusDelegate services = OfertaBusDelegateImpl.getInstance();
			ArrayList<OfertaEmpleoVO> listaOfertas = (ArrayList<OfertaEmpleoVO>) services.obtenerOfertasEliminadasFolio(idEmpresa,
																													    ESTATUS.ELIMINADA_EMP.getIdOpcion(),
																													    ESTATUS.ELIMINADA_VIG.getIdOpcion(), folio);
			setTipoOferta(listaOfertas);
			
			this.PAGE_NUM_ROW = 10;
		    this.PAGE_JUMP_SIZE = 10;

		    request.getSession().removeAttribute("tablaPager");
		    request.getSession().removeAttribute("NUM_PAGE_LIST");
		    
		    request.getSession().removeAttribute(FULL_LIST);
		    request.getSession().setAttribute(FULL_LIST, listaOfertas);

		    request.getSession().removeAttribute("NUM_REGISTROS");			
			request.getSession().setAttribute("NUM_REGISTROS", PAGE_NUM_ROW);	    

		} catch (BusinessException e) {
			e.printStackTrace();
			logger.error(e); // TODO Notificar error al usuario
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
			logger.error(e); // TODO Notificar error al usuario
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e); // TODO Notificar error al usuario
		}

		ocultaBarraArticulos(request);
		request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
        PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Recuperar oferta");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Recuperar oferta, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo.");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
		return mapping.findForward(FORWARD_TEMPLATE_MI_ESP_EMP);
	}

	public ActionForward busquedaFecha(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		long idEmpresa = Long.parseLong((String) request.getSession().getAttribute("idEmpresa"));
		
		limpiaSesion(request);
		
		RecuperaOfertasForm forma = (RecuperaOfertasForm) form;

		Date f1=null;
		Date f2=null;
		
		try {
			f1 = sdf.parse(forma.getFechaDe());
			f2 = sdf.parse(forma.getFechaA());
			
			if(f1.compareTo(f2)>0){
				request.getSession().setAttribute("errorFecha","1");
			}
			else
			try {
				OfertaBusDelegate services = OfertaBusDelegateImpl.getInstance();
				ArrayList<OfertaEmpleoVO> listaOfertas = (ArrayList<OfertaEmpleoVO>) services
						.obtenerOfertasEliminadasFecha(idEmpresa,
								ESTATUS.ELIMINADA_EMP.getIdOpcion(),
								ESTATUS.ELIMINADA_VIG.getIdOpcion(),
								f1,f2);
				
				setTipoOferta(listaOfertas);

				this.PAGE_NUM_ROW = 10;
			    this.PAGE_JUMP_SIZE = 10;

			    request.getSession().removeAttribute("tablaPager");
			    request.getSession().removeAttribute("NUM_PAGE_LIST");
			    
			    request.getSession().removeAttribute(FULL_LIST);
			    request.getSession().setAttribute(FULL_LIST, listaOfertas);

			    request.getSession().removeAttribute("NUM_REGISTROS");			
				request.getSession().setAttribute("NUM_REGISTROS", PAGE_NUM_ROW);	    
				
			} catch (ParseException e) {
				e.printStackTrace();
			} catch (BusinessException e) {
				e.printStackTrace();
				logger.error(e); // TODO Notificar error al usuario
			} catch (ServiceLocatorException e) {
				e.printStackTrace();
				logger.error(e); // TODO Notificar error al usuario
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e); // TODO Notificar error al usuario
			}

		} catch (java.text.ParseException e1) {
			e1.printStackTrace();
		}

		ocultaBarraArticulos(request);
		request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
        PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Recuperar oferta");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Recuperar oferta, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo.");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
		return mapping.findForward(FORWARD_TEMPLATE_MI_ESP_EMP);
	}

	public void limpiaSesion(HttpServletRequest request) {
		request.getSession().removeAttribute("listaOfertasEliminadas");
		request.getSession().removeAttribute("ultimaOferta");
		request.getSession().removeAttribute("ofertaMostradas");
		request.getSession().removeAttribute("listaRecuperaOfertas");
	    
		request.getSession().removeAttribute("tablaPager");
	    request.getSession().removeAttribute("NUM_PAGE_LIST");	    
	    request.getSession().removeAttribute(FULL_LIST);
	    request.getSession().removeAttribute("NUM_REGISTROS");			

	}

	public ActionForward busquedaTitulo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		long idEmpresa = request.getSession().getAttribute("idEmpresa") != null ? Long
				.parseLong((String) request.getSession().getAttribute("idEmpresa")) : 0;
		limpiaSesion(request);
		RecuperaOfertasForm forma = (RecuperaOfertasForm) form;
		String titulo = forma.getTitulo();
		List<OfertaEmpleoVO> listaOfertas = null;
		OfertaBusDelegate services = OfertaBusDelegateImpl.getInstance();

		try {

			listaOfertas = services.obtenerOfertasEliminadasTitulo(idEmpresa,
					ESTATUS.ELIMINADA_EMP.getIdOpcion(),
					ESTATUS.ELIMINADA_VIG.getIdOpcion(), titulo);
			setTipoOferta(listaOfertas);

			this.PAGE_NUM_ROW = 10;
		    this.PAGE_JUMP_SIZE = 10;

		    request.getSession().removeAttribute("tablaPager");
		    request.getSession().removeAttribute("NUM_PAGE_LIST");
		    
		    request.getSession().removeAttribute(FULL_LIST);
		    request.getSession().setAttribute(FULL_LIST, listaOfertas);

		    request.getSession().removeAttribute("NUM_REGISTROS");			
			request.getSession().setAttribute("NUM_REGISTROS", PAGE_NUM_ROW);	    
			
		} catch (BusinessException e) {
			e.printStackTrace();
			logger.error(e); // TODO Notificar error al usuario
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
			logger.error(e); // TODO Notificar error al usuario
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e); // TODO Notificar error al usuario
		}

		request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
        PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Recuperar oferta");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Recuperar oferta, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo.");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
		return mapping.findForward(FORWARD_TEMPLATE_MI_ESP_EMP);
	}
	
	private void setTipoOferta(List<OfertaEmpleoVO> ofertaList){
		OfertaBusDelegate services = OfertaBusDelegateImpl.getInstance();
		
		if(ofertaList!=null && ofertaList.size()>0){
			for (OfertaEmpleoVO list :ofertaList){
				//Tipo de oferta
				try {
					if (services.esOfertaBecate(list.getIdOfertaEmpleo())){
						list.setTipoOferta(ConstantesGenerales.TIPO_OFERTA.OFERTA_BECATE.getIdOpcion());
					}else{
						list.setTipoOferta(ConstantesGenerales.TIPO_OFERTA.OFERTA_NORMAL.getIdOpcion());				
					}
				} catch (ServiceLocatorException | BusinessException e) {
					e.printStackTrace();
					logger.error(e); // TODO Notificar error al usuario
				}
			}
		}
	}

	public ActionForward paginacion(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		int ultimaOferta = request.getSession().getAttribute("ultimaOferta") != null ? Integer
				.parseInt(request.getSession().getAttribute("ultimaOferta")
						.toString()) : 0;
		int ofertaMostradas = request.getSession().getAttribute(
				"ofertaMostradas") != null ? Integer.parseInt(request
				.getSession().getAttribute("ofertaMostradas").toString()) : 0;
		String siguiente = request.getParameter("siguiente");
		String siguienteP = request.getParameter("siguienteP");
		String atras = request.getParameter("atras");
		String atrasP = request.getParameter("atrasp");

		if (request.getParameter("siguienteP").toString().compareTo("true") == 0) {
			request.getSession()
					.setAttribute("ultimaOferta", ultimaOferta + "");
		}

		if (request.getParameter("atrasP").toString().compareTo("true") == 0) {
			if (ultimaOferta > OFERTAS_POR_PAGINA_LISTADO)
				ultimaOferta = ultimaOferta - OFERTAS_POR_PAGINA_LISTADO
						- ofertaMostradas;
			if (ultimaOferta >= 0)
				request.getSession().setAttribute("ultimaOferta",
						ultimaOferta + "");
			else
				request.getSession().removeAttribute("ultimo");
		}

		request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
        PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Recuperar oferta");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Recuperar oferta, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo.");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
		return mapping.findForward(FORWARD_TEMPLATE_MI_ESP_EMP);
	}

}
