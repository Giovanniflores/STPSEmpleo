
package mx.gob.stps.portal.web.entrevista.action;

import static mx.gob.stps.portal.web.entrevista.helper.EntrevistaHelper.CANDIDATO;
import static mx.gob.stps.portal.web.entrevista.helper.EntrevistaHelper.CORREO_ASUNTO_ACEPTADA_ENTREVISTA;
import static mx.gob.stps.portal.web.entrevista.helper.EntrevistaHelper.CORREO_ASUNTO_CANCELAR_ENTREVISTA;
import static mx.gob.stps.portal.web.entrevista.helper.EntrevistaHelper.CORREO_ASUNTO_RECHAZAR_ENTREVISTA;
import static mx.gob.stps.portal.web.entrevista.helper.EntrevistaHelper.CORREO_ASUNTO_REPROGRAMACION_ENTREVISTA;
import static mx.gob.stps.portal.web.entrevista.helper.EntrevistaHelper.EMPRESA;
import static mx.gob.stps.portal.web.entrevista.helper.EntrevistaHelper.REPROGRAMAR;
import static mx.gob.stps.portal.web.infra.utils.Constantes.BODY_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.DESCRIPCION_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FACEBOOK_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_TEMPLATE_FORM;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_TEMPLATE_MI_ESP_CAND;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_TEMPLATE_MI_ESP_EMP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TITULO_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TWITTER_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.URL_ESPECIFICA;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.mail.exception.MailException;
import mx.gob.stps.portal.core.infra.mail.service.NotificacionService;
import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.core.infra.utils.Constantes.PERFIL;
import mx.gob.stps.portal.core.infra.vo.EntrevistaVO;
import mx.gob.stps.portal.web.entrevista.delegate.EntrevistaBusDelegate;
import mx.gob.stps.portal.web.entrevista.delegate.EntrevistaBusDelegateImpl;
import mx.gob.stps.portal.web.entrevista.form.EntrevistaForm;
import mx.gob.stps.portal.web.entrevista.helper.EntrevistaHelper;
import mx.gob.stps.portal.web.infra.action.GenericAction;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.infra.utils.PropertiesLoader;
import mx.gob.stps.portal.web.infra.utils.Utils;
import mx.gob.stps.portal.web.infra.vo.ResultVO;
import mx.gob.stps.portal.web.security.vo.UsuarioWebVO;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author jose.hernandez
 * 
 */
public class EntrevistaAction extends GenericAction {
	
	private static final String MENSAJE_ERROR = null;
	
	private String json	= null;
	private String msg 	= null;
	private String type = null;	
	
	

	@Override
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {		
		request.getSession().setAttribute(BODY_JSP,	mapping.findForward(FORWARD_JSP).getPath());
        PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Entrevista");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Reg&iacute;strate en nuestra Bolsa de trabajo y postula la oferta de empleo que m&aacute;s se apegue seg&uacute;n tu curriculum vitae sin costo f&aacute;cil y sin intermediarios.");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
		return mapping.findForward(FORWARD_TEMPLATE_FORM);
	}
	
	
	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */	
	public ActionForward entrevistaProgramadaCandidato(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {			
			EntrevistaBusDelegate entrevistaBusDelegate = EntrevistaBusDelegateImpl.getInstance();
			EntrevistaVO entrevistaVO = getIdSession(request.getSession());
		
		try {
			List<EntrevistaVO>  listEntrevistaVo = entrevistaBusDelegate.getEntrevistaProgramadaCandidato(entrevistaVO);
			addRequest(request,"ENTREVISTA_PROGRAMADA",listEntrevistaVo);			
			addRequest(request,"TITULO_TIPO",CANDIDATO);
			addRequest(request,"TITULO_ETIQUETA","Nombre de la empresa");
		} catch (BusinessException e) {		
			e.printStackTrace();
		} catch (ServiceLocatorException e) {			
			e.printStackTrace();
		}		
		return mapping.findForward(FORWARD_JSP);
	}
	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward entrevistaProgramadaEmpresa(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {		
			EntrevistaBusDelegate entrevistaBusDelegate = EntrevistaBusDelegateImpl.getInstance();
			EntrevistaVO entrevistaVO = getIdSession(request.getSession());
		try {			
			List<EntrevistaVO>  listEntrevistaVo =
					entrevistaBusDelegate.getEntrevistaProgramadaEmpresa(entrevistaVO);
			addRequest(request,"ENTREVISTA_PROGRAMADA",listEntrevistaVo);
			addRequest(request,"TITULO_TIPO",EMPRESA);
			addRequest(request,"TITULO_ETIQUETA","Nombre completo del candidato");		
			
		} catch (BusinessException e) {
			e.printStackTrace();
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		}
		return mapping.findForward(FORWARD_JSP);
	}
	
	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */	
	public ActionForward entrevistaProgramadaCandidatoEnLinea(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {	
			EntrevistaForm entrevistaForm = (EntrevistaForm)form;
			EntrevistaBusDelegate entrevistaBusDelegate = EntrevistaBusDelegateImpl.getInstance();
			
		try {
			EntrevistaVO entrevistaVO = getIdSession(request.getSession());
			List<EntrevistaVO>  listEntrevistaVo =
					entrevistaBusDelegate.getEntrevistaProgramadaCandidatoEnLinea(entrevistaVO);
			entrevistaForm.setListEntrevistaVo(listEntrevistaVo);
			entrevistaForm.setNombreUsuario(entrevistaVO.getCorreoCandidato());
			entrevistaForm.setEtiqueta("Nombre de la persona de contacto");
			entrevistaForm.setTipo(CANDIDATO);
			entrevistaForm.setTipoNumeric(2);	
			
		} catch (BusinessException e) {
			e.printStackTrace();
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		}
		return mapping.findForward("LINEA");
	}
	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward entrevistaProgramadaEmpresaEnLinea(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {	
			EntrevistaForm entrevistaForm = (EntrevistaForm)form;
			EntrevistaBusDelegate entrevistaBusDelegate = EntrevistaBusDelegateImpl.getInstance();
			
		try {
			EntrevistaVO entrevistaVO = getIdSession(request.getSession());
			List<EntrevistaVO>  listEntrevistaVo = entrevistaBusDelegate.getEntrevistaProgramadaEmpresaEnLinea(entrevistaVO);

			entrevistaForm.setListEntrevistaVo(listEntrevistaVo);			
			entrevistaForm.setNombreUsuario(entrevistaVO.getCorreoCandidato());
			entrevistaForm.setEtiqueta("Candidato");
			entrevistaForm.setTipo(EMPRESA);
			entrevistaForm.setTipoNumeric(1);
			
		} catch (BusinessException e) {
			e.printStackTrace();
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		}		
		return mapping.findForward("LINEA");
	}
		
	
	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward reprogramar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {	
		EntrevistaBusDelegate entrevistaBusDelegate = EntrevistaBusDelegateImpl.getInstance();	
		EntrevistaForm entrevistaForm = (EntrevistaForm)form;
		EntrevistaVO entrevistaVo = getEntrevistaVo(entrevistaForm,request.getSession());
		entrevistaVo.setEstatus(Constantes.ESTATUS.REPROGRAMADA.getIdOpcion());
		entrevistaVo.setTipo(REPROGRAMAR);
		entrevistaVo.setMensajeBitacora("Reprogramando la entrevista con id " + entrevistaVo.getIdEntrevista());
		if (EMPRESA.equals(entrevistaForm.getTipo())) {
			msg  = EntrevistaHelper.getMensaje1(
					Utils.converterDate(entrevistaVo.getFecha()),entrevistaVo.getHora());
			type = ResultVO.TYPE_SUCCESS;

			entrevistaVo.setAsunto(CORREO_ASUNTO_REPROGRAMACION_ENTREVISTA);
			entrevistaVo.setEmailMensaje(
					EntrevistaHelper.getMensaje2(
							entrevistaForm.getNombreEmpresa(),								
							entrevistaVo.getTituloOferta(),
							Utils.converterDate(entrevistaVo.getFecha()),
							entrevistaVo.getHora()));
			entrevistaVo.setContactoEmpresa(entrevistaVo.getContactoEmpresa());
			entrevistaVo.setTipoOperacion(CANDIDATO);
			entrevistaVo.setTipoOperacion(EMPRESA);
			entrevistaVo.setNombre(entrevistaVo.getNombre());
			entrevistaVo.setCorreo(entrevistaForm.getCorreoCandidato());
		}
		if (CANDIDATO.equals(entrevistaForm.getTipo())) {
			msg = EntrevistaHelper.getMensaje3(entrevistaForm.getTituloOferta(), 
					Utils.converterDate(entrevistaVo.getFecha()),entrevistaVo.getHora());
			type = ResultVO.TYPE_SUCCESS;
			entrevistaVo.setAsunto(CORREO_ASUNTO_REPROGRAMACION_ENTREVISTA);

			entrevistaVo.setEmailMensaje(
					EntrevistaHelper.getMensaje4(								
							entrevistaForm.getNombreCandidato(),
							entrevistaVo.getTituloOferta(),
							Utils.converterDate(entrevistaVo.getFecha()),
							entrevistaVo.getHora()));
			entrevistaVo.setContactoEmpresa(entrevistaVo.getContactoEmpresa());
			entrevistaVo.setTipoOperacion(CANDIDATO);
			entrevistaVo.setNombre(entrevistaForm.getNombreCandidato());
			entrevistaVo.setCorreo(entrevistaForm.getCorreoEmpresa());
		}
		try {
			entrevistaBusDelegate.reprogramar(entrevistaVo);
			enviarNotificacion(entrevistaVo);
		}catch (BusinessException e) {
			msg  =	MENSAJE_ERROR;
			e.printStackTrace();
		} catch (ServiceLocatorException e) {
			msg  =	MENSAJE_ERROR;
			e.printStackTrace();
		}catch (MailException e) {
			msg  =	e.getMessage();
			e.printStackTrace();
		}	
		try {
			json = toJson(new ResultVO(msg, type));
			redirectJsonResponse(response, json);
			return null;
		} catch (IOException e) {						
			request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
	        PropertiesLoader properties = PropertiesLoader.getInstance();
			request.getSession().setAttribute(TITULO_PAGINA, "Entrevista");
			request.getSession().setAttribute(DESCRIPCION_PAGINA, "Reg&iacute;strate en nuestra Bolsa de trabajo y postula la oferta de empleo que m&aacute;s se apegue seg&uacute;n tu curriculum vitae sin costo f&aacute;cil y sin intermediarios.");
			request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
			request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
			request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
			return mapping.findForward(FORWARD_TEMPLATE_FORM);
		}		


	}
	

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward cancelar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {	
		EntrevistaBusDelegate entrevistaBusDelegate = EntrevistaBusDelegateImpl.getInstance();
			EntrevistaForm entrevistaForm = (EntrevistaForm)form;
			EntrevistaVO   entrevistaVo = getEntrevistaVo(entrevistaForm,request.getSession());
				entrevistaVo.setEstatus(Constantes.ESTATUS.CANCELADA.getIdOpcion());	
				entrevistaVo.setMensajeBitacora("Cancelando la entrevista con id " + entrevistaVo.getIdEntrevista());
			
			try {				
				entrevistaBusDelegate.aceptar(entrevistaVo);
				
				entrevistaVo.setEmailMensaje(
						EntrevistaHelper.getMensaje8(
								entrevistaVo.getNombreEmpresa(),
								entrevistaVo.getTituloOferta(),
								entrevistaVo.getFechaString(),
								entrevistaVo.getHora()));
				entrevistaVo.setContactoEmpresa(entrevistaVo.getNombre());
				entrevistaVo.setTipoOperacion(EMPRESA);
				entrevistaVo.setNombre(entrevistaForm.getNombreContactoCandidato());
				entrevistaVo.setCorreo(entrevistaForm.getCorreoCandidato());
				entrevistaVo.setAsunto(CORREO_ASUNTO_CANCELAR_ENTREVISTA);
				enviarNotificacion(entrevistaVo);
				
				msg  = EntrevistaHelper.MENSAJE_SUCCESS;
				type = ResultVO.TYPE_SUCCESS;				
				} catch (BusinessException e) {
					msg  =	MENSAJE_ERROR;
					e.printStackTrace();
				} catch (ServiceLocatorException e) {
					msg  =	MENSAJE_ERROR;
					e.printStackTrace();
				} catch (MailException e) {
					msg  =	MENSAJE_ERROR;
					e.printStackTrace();
				}
				
			try {
				json = toJson(new ResultVO(msg, type));
				redirectJsonResponse(response, json);
				return null;
				} catch (IOException e) {						
					request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
			    return mapping.findForward(FORWARD_TEMPLATE_FORM);
				}
			
	}
	
	

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward aceptar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {	
		EntrevistaBusDelegate entrevistaBusDelegate = EntrevistaBusDelegateImpl.getInstance();	
			String mensaje = "";
			EntrevistaForm entrevistaForm = (EntrevistaForm)form;
			EntrevistaVO   entrevistaVo = getEntrevistaVo(entrevistaForm,request.getSession());
				entrevistaVo.setEstatus(Constantes.ESTATUS.ACEPTADA.getIdOpcion());
				entrevistaVo.setMensajeBitacora("Aceptando la entrevista con id " + entrevistaVo.getIdEntrevista());
				entrevistaVo.setNombre(entrevistaForm.getNombreCandidato());
			try {				
				entrevistaBusDelegate.aceptar(entrevistaVo);
				
				if(EMPRESA.equals(entrevistaForm.getTipo())) {
					mensaje = EntrevistaHelper.getMensaje9(
							entrevistaVo.getNombreEmpresa(),
							entrevistaVo.getTituloOferta(),
							entrevistaVo.getFechaString(),
							entrevistaVo.getHora());
					entrevistaVo.setCorreo(entrevistaForm.getCorreoCandidato());					
				}else{
					mensaje = EntrevistaHelper.getMensaje10(
							entrevistaForm.getNombreCandidato(),
							entrevistaVo.getTituloOferta(),
							entrevistaVo.getFechaString(),
							entrevistaVo.getHora());
					entrevistaVo.setCorreo(entrevistaForm.getCorreoEmpresa());
				}
				
				entrevistaVo.setEmailMensaje(mensaje);			
				entrevistaVo.setContactoEmpresa(entrevistaVo.getNombre());
				entrevistaVo.setTipoOperacion(EMPRESA);
								
				entrevistaVo.setAsunto(CORREO_ASUNTO_ACEPTADA_ENTREVISTA);
				enviarNotificacion(entrevistaVo);
				
				msg  = EntrevistaHelper.MENSAJE_SUCCESS;
				type = ResultVO.TYPE_SUCCESS;				
				} catch (BusinessException e) {
					msg  =	MENSAJE_ERROR;
					e.printStackTrace();
				} catch (ServiceLocatorException e) {
					msg  =	MENSAJE_ERROR;
					e.printStackTrace();
				} catch (MailException e) {
					msg  =	MENSAJE_ERROR;
					e.printStackTrace();
				}
				
			try {
				json = toJson(new ResultVO(msg, type));
				redirectJsonResponse(response, json);
				return null;
				} catch (IOException e) {						
					request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
			    return mapping.findForward(FORWARD_TEMPLATE_FORM);
				}
			
	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward rechazar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {	

		EntrevistaBusDelegate entrevistaBusDelegate = EntrevistaBusDelegateImpl.getInstance();

		EntrevistaForm entrevistaForm = (EntrevistaForm)form;
		EntrevistaVO   entrevistaVo = getEntrevistaVo(entrevistaForm,request.getSession());
		entrevistaVo.setNombre(entrevistaForm.getNombreCandidato());
		entrevistaVo.setEstatus(Constantes.ESTATUS.RECHAZADA.getIdOpcion());
		entrevistaVo.setMensajeBitacora("Rechazando la entrevista con id " + entrevistaVo.getIdEntrevista());

		try {
			entrevistaBusDelegate.rechazar(entrevistaVo);
			entrevistaVo.setAsunto(CORREO_ASUNTO_RECHAZAR_ENTREVISTA);
			entrevistaVo.setEmailMensaje(
					EntrevistaHelper.getMensaje6(
							entrevistaVo.getNombre(),
							entrevistaVo.getTituloOferta(),
							entrevistaVo.getFechaString(),
							entrevistaVo.getHora()));
			entrevistaVo.setContactoEmpresa(entrevistaVo.getContactoEmpresa());
			entrevistaVo.setTipoOperacion(EMPRESA);
			entrevistaVo.setNombre(entrevistaVo.getNombre());
			entrevistaVo.setCorreo(entrevistaForm.getCorreoEmpresa());
			entrevistaVo.setContactoEmpresa(entrevistaForm.getNombreContactoEmpresa());
			enviarNotificacion(entrevistaVo);

			msg  = EntrevistaHelper.MENSAJE_SUCCESS;
			type = ResultVO.TYPE_SUCCESS;				
		} catch (BusinessException e) {
			msg  =	MENSAJE_ERROR;
			e.printStackTrace();
		} catch (ServiceLocatorException e) {
			msg  =	MENSAJE_ERROR;
			e.printStackTrace();
		} catch (MailException e) {
			msg  =	MENSAJE_ERROR;
			e.printStackTrace();
		}				
		try {
			json = toJson(new ResultVO(msg, type));
			redirectJsonResponse(response, json);
			return null;
		} catch (IOException e) {						
			request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
			return mapping.findForward(FORWARD_TEMPLATE_FORM);
		}

	}
	
	/**
	 * @param request
	 * @param id
	 * @param object
	 */
	private void addRequest(HttpServletRequest request,String id, Object object){	
		request.setAttribute(id,object);		
	}
	
 	/**
	 * @param entrevistaForm
	 * @return
	 */
	private EntrevistaVO getEntrevistaVo(EntrevistaForm entrevistaForm,HttpSession session){
		
		EntrevistaVO entrevistaVo = new EntrevistaVO();
			entrevistaVo.setIdEmpresa(entrevistaVo.getIdEmpresa());
			entrevistaVo.setIdCandidato(entrevistaVo.getIdCandidato());						
			entrevistaVo.setIdEntrevista(entrevistaForm.getIdEntrevista());
			entrevistaVo.setTipo(entrevistaForm.getTipo());
			entrevistaVo.setHora(entrevistaForm.getHora());
			entrevistaVo.setCorreoEmpresa(entrevistaForm.getCorreoEmpresa());			
			entrevistaVo.setCorreoCandidato(entrevistaForm.getCorreoCandidato());
			entrevistaVo.setNombreEmpresa(entrevistaForm.getNombreEmpresa());			
			entrevistaVo.setTituloOferta(entrevistaForm.getTituloOferta());
			entrevistaVo.setNombre(entrevistaForm.getNombreContactoEmpresa());
			entrevistaVo.setFechaString(entrevistaForm.getFecha());
			entrevistaVo.setHora(entrevistaForm.getHora());
			entrevistaVo.setFechaModificacion(new Date());
			entrevistaVo.setIdUsuario(getIdSession(session).getIdUsuario());	
			entrevistaVo.setContactoEmpresa(entrevistaForm.getNombreContactoEmpresa());
			if(entrevistaForm.getFechaEntrevista() != null){
				entrevistaVo.setFecha(Utils.convertWebDate(entrevistaForm.getFechaEntrevista()));
			}
			
		return entrevistaVo;
		
	}

	/**
	 * @param entrevistaVO
	 * @throws MailException
	 */
	private void enviarNotificacion(EntrevistaVO entrevistaVO) throws MailException{		
		NotificacionService notificacionService = new NotificacionService();
		notificacionService.notificacionEntrevista(entrevistaVO);		
	}
	
	/** Tomando valores para de la session para entrevista en linea
	 * @param session
	 * @return
	 */
	private EntrevistaVO getIdSession(HttpSession session){
		EntrevistaVO entrevistaVo = new EntrevistaVO();
		UsuarioWebVO usuario = super.getUsuario(session);	
		if(usuario.getIdPerfil() == PERFIL.CANDIDATO.getIdOpcion()){
			entrevistaVo.setIdCandidato(usuario.getIdPropietario());
			entrevistaVo.setCorreoCandidato(usuario.getCorreoElectronico());
			entrevistaVo.setIdUsuario(usuario.getIdPropietario());
		}if(usuario.getIdPerfil() == PERFIL.EMPRESA.getIdOpcion()){
			entrevistaVo.setIdEmpresa(usuario.getIdPropietario());
			entrevistaVo.setCorreoCandidato(usuario.getCorreoElectronico());
			entrevistaVo.setIdUsuario(usuario.getIdPropietario());
		}
		return entrevistaVo;
	}
	
	
	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */	
	public ActionForward entrevistaProgramadaEnlineaCandidato(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {		
		request.getSession().setAttribute(BODY_JSP,	mapping.findForward("ENTREVISTA_CANDIDATO").getPath());
        PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Consultar mis entrevistas en l&iacute;nea");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Espacio Candidato, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
        return mapping.findForward(FORWARD_TEMPLATE_MI_ESP_CAND);
	}
	
	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */	
	public ActionForward entrevistaProgramadaEnlineaEmpresa(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {		
		request.getSession().setAttribute(BODY_JSP,	mapping.findForward("ENTREVISTA_EMPRESA").getPath());

        PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Entrevistas en linea");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Espacio Empresa, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo ");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
        return mapping.findForward(FORWARD_TEMPLATE_MI_ESP_EMP);
	}
	
	
	
}
