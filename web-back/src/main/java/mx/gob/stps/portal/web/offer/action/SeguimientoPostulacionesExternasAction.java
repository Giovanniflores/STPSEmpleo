package mx.gob.stps.portal.web.offer.action;

import static mx.gob.stps.portal.web.infra.utils.Constantes.BODY_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_TEMPLATE_MI_ESP_CAND;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TAB_MENU;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TAB_MIS_OFERTAS;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TITULO_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.DESCRIPCION_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FACEBOOK_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TWITTER_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.URL_ESPECIFICA;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.gob.stps.portal.mail.exception.MailException;
import mx.gob.stps.portal.mail.service.MailService;
import mx.gob.stps.portal.mail.template.FieldVO;
import mx.gob.stps.portal.persistencia.entity.PostulacionExterna;
import mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO;
import mx.gob.stps.portal.utils.Catalogos;
import mx.gob.stps.portal.utils.ConstantesGenerales;
import mx.gob.stps.portal.utils.PropertiesLoader;
import mx.gob.stps.portal.web.candidate.vo.CandidatoAjaxVO;
import mx.gob.stps.portal.web.infra.action.PagerAction;
import mx.gob.stps.portal.web.infra.delegate.CatalogoOpcionDelegate;
import mx.gob.stps.portal.web.infra.delegate.CatalogoOpcionDelegateImpl;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.infra.utils.Constantes;
import mx.gob.stps.portal.web.infra.utils.Constantes.MESES;
import mx.gob.stps.portal.web.infra.utils.TablaBusquedaPostulacionesExternas;
import mx.gob.stps.portal.web.infra.vo.CatalogoVO;
import mx.gob.stps.portal.web.offer.delegate.OfferBusDelegate;
import mx.gob.stps.portal.web.offer.delegate.OfferBusDelegateImpl;
import mx.gob.stps.portal.web.offer.form.RegistroPostulacionExternaForm;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class SeguimientoPostulacionesExternasAction extends PagerAction {
	
	private static Logger logger = Logger.getLogger(SeguimientoPostulacionesExternasAction.class);
	
	 private RegistroPostulacionExternaForm registroPostulacionForm;
     private CandidatoAjaxVO candidato;
     private List<PostulacionExterna> postulaciones;
     private PostulacionExterna postulacion;
     private MailService mailService = MailService.getInstance();
     private PostulacionExterna postulacionExternaContratado;
	
	public ActionForward init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		setPostulaciones(new ArrayList<PostulacionExterna>());
		HttpSession session = request.getSession();
		session.setAttribute(TAB_MENU, TAB_MIS_OFERTAS);
		registroPostulacionForm  = (RegistroPostulacionExternaForm) form;
		candidato = (CandidatoAjaxVO) session.getAttribute(Constantes.CANDIDATO_HEAD);
		OfferBusDelegate delegate = OfferBusDelegateImpl.getInstance();
		session.removeAttribute("showDateMsj");
		session.removeAttribute("showMsjExito");
		session.removeAttribute("showMsjNo");
		session.removeAttribute("showMsjProcess");
		session.removeAttribute("showStatCol");
		//CatalogoOpcionDelegate catalogo = CatalogoOpcionDelegateImpl.getInstance();
		try {
			List<PostulacionExterna> postulacionAux = new ArrayList<PostulacionExterna>();
			postulacionAux = delegate.obtenerPostulacionContratado(candidato.getIdCandidato());
			
			if(!postulacionAux.isEmpty()){
				setPostulaciones(postulacionAux);
				session.setAttribute("showStatCol", true);
			}else{
				setPostulaciones(delegate.obtenerPostulacionesCandidato(candidato.getIdCandidato()));
				session.setAttribute("showStatCol", false);
			}
			
			//List<CatalogoOpcionVO> opciones = new ArrayList<CatalogoOpcionVO>();
			//opciones = catalogo.consultarCatalogo(ConstantesGenerales.CATALOGO_MOTIVOS_RECHAZO_POSTULACION_EXTERNA);
			//registroPostulacionForm.setMotivos(opciones);
			registroPostulacionForm.setPostulaciones(getPostulaciones());
			this.PAGE_NUM_ROW = 5;
			request.getSession().removeAttribute("NUM_PAGE_LIST");
			request.getSession().removeAttribute("NUM_REGISTROS");			
			request.getSession().setAttribute("NUM_REGISTROS", PAGE_NUM_ROW);
			session.setAttribute(FULL_LIST, getPostulaciones());
			request.getSession().removeAttribute("total");			
			request.getSession().setAttribute("total", getPostulaciones().size());
		} catch (ServiceLocatorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		session.setAttribute(BODY_JSP, mapping.findForward("JSP").getPath());
        PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Seguimiento Postulaci&oacute;n");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Seguimiento Postulaci&oacute;n, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
		return mapping.findForward(FORWARD_TEMPLATE_MI_ESP_CAND);
	}
	
	@SuppressWarnings("unchecked")
	public ActionForward orderByColumn(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		registroPostulacionForm = (RegistroPostulacionExternaForm) form; 
		String orderType = request.getParameter("orderType");
		String columnNumber = request.getParameter("columnNumber");
		List<PostulacionExterna> resultado = null;
		
		try{	
			registroPostulacionForm.setOrderType(orderType);
			registroPostulacionForm.setColumnNumber(Integer.parseInt(columnNumber));
				
			resultado = (List<PostulacionExterna>) session.getAttribute(FULL_LIST);
			
			TablaBusquedaPostulacionesExternas tabla = new TablaBusquedaPostulacionesExternas(resultado);
			tabla.ordenarTabla(Integer.parseInt(columnNumber), orderType);
			resultado = tabla.getLista();
		
			session.removeAttribute(FULL_LIST);
			session.setAttribute(FULL_LIST, resultado);
			this.PAGE_NUM_ROW = 5;
			request.getSession().removeAttribute("NUM_PAGE_LIST");
			request.getSession().removeAttribute("NUM_REGISTROS");			
			request.getSession().setAttribute("NUM_REGISTROS", PAGE_NUM_ROW);
			request.getSession().removeAttribute("total");			
			request.getSession().setAttribute("total", resultado.size());
			//logger.info("se ordenó la lista");
		}catch(Exception e){
			e.printStackTrace();
		}
		//return mapping.findForward(FORWARD_TEMPLATE_REGISTRO_CANDIDATO_EVENTO);
		return page(mapping, form, request, response);
	}
	
	
	public ActionForward cargaDetallePostulacion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		//registroPostulacionForm = new RegistroPostulacionExternaForm();
		//registroPostulacionForm.setPostulaciones(getPostulaciones());
		request.getSession().removeAttribute("POSTULACION");
		if(getPostulacionExternaContratado() == null){
			Long idPostulacionExterna;
			if(request.getParameter("idPostulacionExterna") != null){
				idPostulacionExterna = Long.parseLong(request.getParameter("idPostulacionExterna"));
				for(int i = 0; i < postulaciones.size(); i++){
					if(postulaciones.get(i).getIdPostulacionExterna() == idPostulacionExterna ){
				//		registroPostulacionForm.setPostulacion(postulaciones.get(i));
						setPostulacion(postulaciones.get(i));
						request.getSession().setAttribute("POSTULACION", postulaciones.get(i));
					}
				}
			}
		}else{
			request.getSession().setAttribute("POSTULACION",getPostulacionExternaContratado());
		}
		
		
		request.getSession().removeAttribute(BODY_JSP);
		request.getSession().setAttribute(BODY_JSP, mapping.findForward("ACTION_SEGUIMIENTO_POSTULACION").getPath());

        PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Seguimiento Postulaci&oacute;n");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Seguimiento Postulaci&oacute;n, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
		return mapping.findForward(FORWARD_TEMPLATE_MI_ESP_CAND);
	}
	
	public ActionForward cerrarMsjs(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		request.getSession().removeAttribute("ERROR_MSG");
		request.getSession().removeAttribute("showDateMsj");
		return null;
	}
	
	
	
	public ActionForward dias(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			List<CatalogoOpcionVO> opciones = new ArrayList<CatalogoOpcionVO>();

			CatalogoOpcionVO zero = new CatalogoOpcionVO();
			zero.setIdCatalogo(0);
			zero.setOpcion("Día");
			opciones.add(zero);

			for (int i=1; i<=31; i++){
				CatalogoOpcionVO opcion = new CatalogoOpcionVO();
				opcion.setIdCatalogoOpcion(i);
				opcion.setOpcion(String.valueOf(i));
				opciones.add(opcion);
			}

			redirectJsonCatalogo(opciones, response);
		} catch (Exception e) {e.printStackTrace(); logger.error(e);}

		return null;
	}

	
	public ActionForward motivos(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		List<CatalogoOpcionVO> opciones = new ArrayList<CatalogoOpcionVO>();
		CatalogoOpcionVO zero = new CatalogoOpcionVO();
		zero.setIdCatalogo(0);
		zero.setOpcion("Seleccione un motivo");
		
		try {
			CatalogoOpcionDelegate catalogo = CatalogoOpcionDelegateImpl.getInstance();
			opciones = catalogo.consultarCatalogo(ConstantesGenerales.CATALOGO_MOTIVOS_RECHAZO_POSTULACION_EXTERNA);
			opciones.add(0, zero);
			redirectJsonCatalogo(opciones, response);
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
		return null;
	}
	
	public ActionForward registrar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		RegistroPostulacionExternaForm registroForm = (RegistroPostulacionExternaForm)form;
		ActionForward forward;
		HttpSession session = request.getSession();
		OfferBusDelegate delegate = OfferBusDelegateImpl.getInstance();
		long now = System.currentTimeMillis();
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(now);
		Date fechaSeguimiento = cal.getTime();//cambiar nombre a variable or fechaHoy
		session.removeAttribute("showDateMsj");
		session.removeAttribute("showMsjExito");
		session.removeAttribute("showMsjNo");
		session.removeAttribute("showMsjProcess");
		session.removeAttribute("ERROR_MSG");
		getPostulacion().setFuenteSeguimiento(Catalogos.APLICACION.PORTAL_DEL_EMPLEO.getIdOpcion());
		int dia = 0;
		int mes = 0;
		int anio = 0;
		if(request.getParameter("contratado").equals("1")){
			getPostulacion().setFechaSeguimiento(fechaSeguimiento);
			if(request.getParameter("anioSelect") != null){
				anio = Integer.parseInt(request.getParameter("anioSelect"));
			}
			if(request.getParameter("mesSelect") != null){
				mes = Integer.parseInt(request.getParameter("mesSelect"));
			}
			if(request.getParameter("diaSelect") != null){
				dia = Integer.parseInt(request.getParameter("diaSelect"));
			}
			Calendar calendar = Calendar.getInstance();
			calendar.set(anio, mes-1,dia);
			Date  fechaContrato = calendar.getTime();
			if(fechaContrato.after(getPostulacion().getFechaAlta()) || fechaContrato.equals(getPostulacion().getFechaAlta())){
				getPostulacion().setFechaInicioColocacion(fechaContrato);
				getPostulacion().setEstatus(Catalogos.ESTATUS_POSTULACION_EXTERNA.CONTRATADO.getIdOpcion());
				setPostulacionExternaContratado(getPostulacion());
				postulaciones.remove(postulacion);
				//para cambiar de estatus las ofertas
				for(PostulacionExterna p: postulaciones){
					if(p.getEstatus() == Catalogos.ESTATUS_POSTULACION_EXTERNA.EN_PROCESO.getIdOpcion() || p.getEstatus() == Catalogos.ESTATUS_POSTULACION_EXTERNA.REGISTRADO.getIdOpcion())
						p.setEstatus(Catalogos.ESTATUS_POSTULACION_EXTERNA.CONTRATADO_OTRA_OFERTA.getIdOpcion());
				}
				try {
					delegate.darSeguimientoPostulacionExternaContratado(postulacion, postulaciones);
					delegate.actualizaPostulacionesSNE(candidato.getIdCandidato());
					delegate.registraBitacora(candidato.getIdUsuario(), getPostulacion().getIdPostulacionExterna(), candidato.getIdCandidato(),getPostulacion().getEstatus());
					session.setAttribute("showMsjExito", 1);
				} catch (ServiceLocatorException e) {
					session.setAttribute("ERROR_MSG", getMensaje(request, "notificaciones.exception.ServiceLocatorException"));
					e.printStackTrace();
				}
				
				if(candidato.getPpcEstatusOpcion() != null){
					try {
						delegate.actualizaEstatusPPCCandidato(candidato.getIdCandidato());
					} catch (ServiceLocatorException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if(candidato.getCorreoElectronico() != null){
						List<FieldVO> parametros = new ArrayList<FieldVO>();
						parametros.add(FieldVO.getInstance("nombreCandidato", candidato.getNombre()));
						SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
						parametros.add(FieldVO.getInstance("fechaColocacion",df.format(getPostulacion().getFechaInicioColocacion())));
						parametros.add(FieldVO.getInstance("medioColocacion",Catalogos.APLICACION.PORTAL_DEL_EMPLEO.getOpcion()));
						String correo = candidato.getCorreoElectronico();
						String asunto ="Notificación fuera programa PPC";
						//String remitente =PropertiesLoader.getInstance().getProperty("email.remitente.ferias");
						String remitente = "seguro_desempleo@stps.gob.mx";
						try {
							mailService.sendMail(Catalogos.PLANTILLA_CORREO.NOTIFICACION_CANDIDATO_FUERA_PPC, remitente, asunto, correo, parametros);
							
						} catch (MailException e) {
							e.printStackTrace();
						}
					}
				}
				
			}else{
				session.setAttribute("showDateMsj", 1);
			}
			
		}else if(request.getParameter("contratado").equals("2")){
			getPostulacion().setFechaSeguimiento(fechaSeguimiento);
			Long motivo = null;
			getPostulacion().setEstatus(Catalogos.ESTATUS_POSTULACION_EXTERNA.NO_ACEPTADO.getIdOpcion());
			if(request.getParameter("motivoSelect") != null){
				motivo = Long.parseLong(request.getParameter("motivoSelect"));
				getPostulacion().setIdMotivoNoContratacion(motivo);
			}
			getPostulacion().setOtroMotivo(registroForm.getOtroMotivo());
			try {
				delegate.darSeguimientoPostulacion(getPostulacion());
				delegate.registraBitacora(candidato.getIdUsuario(), getPostulacion().getIdPostulacionExterna(), candidato.getIdCandidato(),getPostulacion().getEstatus());
				session.setAttribute("showMsjNo", 1);
			} catch (ServiceLocatorException e) {
				session.setAttribute("ERROR_MSG", getMensaje(request, "notificaciones.exception.ServiceLocatorException"));
				e.printStackTrace();
			}
		}else if(request.getParameter("contratado").equals("3")){
			getPostulacion().setEstatus(Catalogos.ESTATUS_POSTULACION_EXTERNA.EN_PROCESO.getIdOpcion());
			getPostulacion().setFechaInicioContratacion(fechaSeguimiento);
			try {
				delegate.darSeguimientoPostulacion(getPostulacion());
				delegate.registraBitacora(candidato.getIdUsuario(), getPostulacion().getIdPostulacionExterna(), candidato.getIdCandidato(),getPostulacion().getEstatus());
				session.setAttribute("showMsjProcess", 1);
			} catch (ServiceLocatorException e) {
				session.setAttribute("ERROR_MSG", getMensaje(request, "notificaciones.exception.ServiceLocatorException"));
				e.printStackTrace();
			}
		}
		
		
	//	return null;
		request.getSession().removeAttribute(BODY_JSP);
        PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(BODY_JSP, mapping.findForward("ACTION_SEGUIMIENTO_POSTULACION").getPath());
		request.getSession().setAttribute(TITULO_PAGINA, "Seguimiento Postulaci&oacute;n");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Seguimiento Postulaci&oacute;n, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
		return mapping.findForward(FORWARD_TEMPLATE_MI_ESP_CAND);
		
		
	}
	
		
	
	public ActionForward meses(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			List<CatalogoOpcionVO> opciones = new ArrayList<CatalogoOpcionVO>();

			CatalogoOpcionVO zero = new CatalogoOpcionVO();
			zero.setIdCatalogo(0);
			zero.setOpcion("Mes");
			opciones.add(zero);

			for (MESES mes : MESES.values()){
				CatalogoOpcionVO opcion = new CatalogoOpcionVO();
				opcion.setIdCatalogoOpcion(mes.getIdOpcion());
				opcion.setOpcion(String.valueOf(mes.getIdOpcion()));
				opciones.add(opcion);
			}

			redirectJsonCatalogo(opciones, response);
		} catch (Exception e) {e.printStackTrace(); logger.error(e);}

		return null;
	}

	
	
	
	public ActionForward anios(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			List<CatalogoOpcionVO> opciones = new ArrayList<CatalogoOpcionVO>();

			CatalogoOpcionVO zero = new CatalogoOpcionVO();
			zero.setIdCatalogo(0);
			zero.setOpcion("Año");
			opciones.add(zero);			

			Calendar hoy = Calendar.getInstance();
			int year = hoy.get(Calendar.YEAR);
			int month = hoy.get(Calendar.MONTH);

			if(month == Calendar.JANUARY){
				
				CatalogoOpcionVO opcion = new CatalogoOpcionVO();
				opcion.setIdCatalogoOpcion(year-1);
				opcion.setOpcion(String.valueOf(year-1));
				opciones.add(opcion);
				
			}
			
			CatalogoOpcionVO opcion = new CatalogoOpcionVO();
			opcion.setIdCatalogoOpcion(year);
			opcion.setOpcion(String.valueOf(year));
			opciones.add(opcion);			
			
			if(month == Calendar.DECEMBER){
				
				opcion = new CatalogoOpcionVO();
				opcion.setIdCatalogoOpcion(year+1);
				opcion.setOpcion(String.valueOf(year+1));
				opciones.add(opcion);
				
			}	

			redirectJsonCatalogo(opciones, response);
		} catch (Exception e) {		
			e.printStackTrace(); logger.error(e);
		}		
		return null;
	}
	
	private void redirectJsonCatalogo(List<CatalogoOpcionVO> opciones, HttpServletResponse response) throws IOException {
		CatalogoVO cat = getCatalogo(opciones);
		String json = toJson(cat);
		redirectJsonResponse(response, json);
	}

	public List<PostulacionExterna> getPostulaciones() {
		return postulaciones;
	}

	public void setPostulaciones(List<PostulacionExterna> postulaciones) {
		this.postulaciones = postulaciones;
	}

	public PostulacionExterna getPostulacion() {
		return postulacion;
	}

	public void setPostulacion(PostulacionExterna postulacion) {
		this.postulacion = postulacion;
	}

	public PostulacionExterna getPostulacionExternaContratado() {
		return postulacionExternaContratado;
	}

	public void setPostulacionExternaContratado(
			PostulacionExterna postulacionExternaContratado) {
		this.postulacionExternaContratado = postulacionExternaContratado;
	}



}
