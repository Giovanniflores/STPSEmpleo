package mx.gob.stps.portal.web.offer.action;

import static mx.gob.stps.portal.web.infra.utils.Constantes.BODY_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_NEXT;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_TEMPLATE_MI_ESP_CAND;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TAB_MENU;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TAB_MIS_OFERTAS;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TITULO_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.DESCRIPCION_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FACEBOOK_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TWITTER_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.URL_ESPECIFICA;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO;
import mx.gob.stps.portal.persistencia.vo.PostulacionExternaVO;
import mx.gob.stps.portal.utils.Catalogos;
import mx.gob.stps.portal.utils.ConstantesGenerales;
import mx.gob.stps.portal.utils.Utils;
import mx.gob.stps.portal.web.candidate.form.RegistroCandidatoForm;
import mx.gob.stps.portal.web.infra.action.PagerAction;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.infra.utils.PropertiesLoader;
import mx.gob.stps.portal.web.infra.utils.Constantes.MESES;
import mx.gob.stps.portal.web.infra.vo.CatalogoVO;
import mx.gob.stps.portal.web.infra.vo.ResultVO;
import mx.gob.stps.portal.web.offer.delegate.OfferBusDelegate;
import mx.gob.stps.portal.web.offer.delegate.OfferBusDelegateImpl;
import mx.gob.stps.portal.web.offer.form.RegistroPostulacionExternaForm;
import mx.gob.stps.portal.web.security.vo.UsuarioWebVO;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;


public final class RegistroPostulacionExternaAction extends PagerAction {
	
	private static Logger logger = Logger.getLogger(RegistroPostulacionExternaAction.class);
	
	private static final String FORWARD_REGISTRAR_POSTULACIONES_EXTERNAS = "ACTION_REGISTRAR_POSTULACION_EXTERNA";	
	private static final String ERROR_MSG = "ERROR_MSG";
	

	public ActionForward init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();		
        session.setAttribute(TAB_MENU, TAB_MIS_OFERTAS);
        ocultaBarraArticulos(request);
        session.removeAttribute("registroPostulacionExternaForm");    

        RegistroPostulacionExternaForm registroForm = (RegistroPostulacionExternaForm) form;
        
        registroForm.setAction(registroForm.ACTION_INIT);       
		request.setAttribute("CLAVE_TELEFONO_CELULAR", Constantes.CLAVE_TELEFONO_CELULAR);
		request.setAttribute("CLAVE_TELEFONO_FIJO", Constantes.CLAVE_TELEFONO_FIJO);
		request.setAttribute("TELEFONO_CELULAR", Constantes.TELEFONO_CELULAR);
		request.setAttribute("TELEFONO_FIJO", Constantes.TELEFONO_FIJO);
				            	
		request.setAttribute("CONFIRMAR_OTRA_MAS", false);
		
        session.setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
        PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Registro Postulaci&oacute;n");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Registro Postulaci&oacute;n, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
        return mapping.findForward(FORWARD_TEMPLATE_MI_ESP_CAND);
	}
	
	
	public ActionForward limpiarFormulario(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
        session.setAttribute(TAB_MENU, TAB_MIS_OFERTAS);
        ocultaBarraArticulos(request);
        
        session.removeAttribute("registroPostulacionExternaForm");        
        
        RegistroPostulacionExternaForm registroFormNueva = new RegistroPostulacionExternaForm();
        registroFormNueva.setAction(registroFormNueva.ACTION_INIT);             
		request.setAttribute("CLAVE_TELEFONO_CELULAR", Constantes.CLAVE_TELEFONO_CELULAR);
		request.setAttribute("CLAVE_TELEFONO_FIJO", Constantes.CLAVE_TELEFONO_FIJO);
		request.setAttribute("TELEFONO_CELULAR", Constantes.TELEFONO_CELULAR);
		request.setAttribute("TELEFONO_FIJO", Constantes.TELEFONO_FIJO);        
            	
		request.setAttribute("CONFIRMAR_OTRA_MAS", false);
		session.setAttribute("registroPostulacionExternaForm", registroFormNueva); 
        session.setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
        PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Registro Postulaci&oacute;n");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Registro Postulaci&oacute;n, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
        return mapping.findForward(FORWARD_TEMPLATE_MI_ESP_CAND);
	}	
	
	
	private ActionForward getForward(ActionMapping mapping, HttpServletRequest request, String forwardName) {
		HttpSession session = request.getSession();
		session.setAttribute(BODY_JSP, mapping.findForward(forwardName).getPath());
        PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Registro Postulaci&oacute;n");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Registro Postulaci&oacute;n, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ request.getRequestURI().toString());
		return mapping.findForward(FORWARD_TEMPLATE_MI_ESP_CAND);
	}		
	
	
	public ActionForward enviarAMiEspacioCandidato(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		request.getSession().removeAttribute("registroPostulacionExternaForm");
		request.getSession().setAttribute(BODY_JSP, mapping.findForward("ACTION_CANDIDATO").getPath());
        PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Registro Postulaci&oacute;n");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Registro Postulaci&oacute;n, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
        return mapping.findForward(FORWARD_TEMPLATE_MI_ESP_CAND);
	}
	

	public ActionForward enviarASeguimiento(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		request.getSession().removeAttribute("registroPostulacionExternaForm");	
		//request.getSession().setAttribute(BODY_JSP, mapping.findForward("NEXT").getPath());
		//return mapping.findForward(FORWARD_TEMPLATE_MI_ESP_CAND);
		return mapping.findForward(FORWARD_NEXT);
	}	
	
	

	
	public ActionForward registrar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		
		ActionForward forward = getForward(mapping, request, "JSP");
		
		RegistroPostulacionExternaForm registroForm = (RegistroPostulacionExternaForm) form;
				
		try{
			int idPostulacionRegistrada  = -1;
									
			UsuarioWebVO usuario = getUsuario(request.getSession());
			if(usuario!=null){
				registroForm.setIdUsuario(usuario.getIdUsuario());
				registroForm.setIdCandidato(usuario.getIdPropietario());
			}			
						
			boolean valido = validaDatosBasicos(registroForm, request);
			
			if (!valido){
				return forward;
				
			} else {	
				
				if(null!= registroForm){

					PostulacionExternaVO postulacionExternaVo = new PostulacionExternaVO();			
					
					postulacionExternaVo.setIdCandidato(registroForm.getIdCandidato());
					postulacionExternaVo.setIdUsuario(registroForm.getIdUsuario());
					postulacionExternaVo.setNombreEmpresa(registroForm.getNombreEmpresa());
					postulacionExternaVo.setContactoEmpresa(registroForm.getNombreContacto());
					postulacionExternaVo.setContactoCargo(registroForm.getCargoContacto());
										
					postulacionExternaVo.setIdTipoTelefono(registroForm.getIdTipoTelefono());				
					postulacionExternaVo.setAcceso(registroForm.getAcceso());
					postulacionExternaVo.setClave(registroForm.getClave());
					postulacionExternaVo.setTelefono(registroForm.getTelefono());
					postulacionExternaVo.setExtension(registroForm.getExtension());
					
					postulacionExternaVo.setContactoCorreoElectronico(registroForm.getCorreoElectronico());

					postulacionExternaVo.setTituloOferta(registroForm.getTituloOferta());
					postulacionExternaVo.setSalario(registroForm.getSalarioMensual());
					postulacionExternaVo.setMedioPortal(registroForm.getComoTeEnterasteOferta());	
									
					postulacionExternaVo.setFechaContacto(convertIntsToDate(registroForm.getDiaContacto(), registroForm.getMesContacto(), registroForm.getAnioContacto()));
					postulacionExternaVo.setFechaEntrevista(convertIntsToDate(registroForm.getDiaEntrevista(), registroForm.getMesEntrevista(), registroForm.getAnioEntrevista()));
												
					postulacionExternaVo.setFechaAlta(new Date());
					postulacionExternaVo.setEstatus(Catalogos.ESTATUS_POSTULACION_EXTERNA.REGISTRADO.getIdOpcion());							
					postulacionExternaVo.setFuente(Catalogos.APLICACION.PORTAL_DEL_EMPLEO.getIdOpcion());				
					postulacionExternaVo.setIdOficina(ConstantesGenerales.PORTAL_ID_OFICINA);	
					
					OfferBusDelegate delegate = OfferBusDelegateImpl.getInstance();
					
					idPostulacionRegistrada  = delegate.registrarPostulacionExterna(postulacionExternaVo, usuario.getIdUsuario());

				}							
				
				if(idPostulacionRegistrada>0){
					request.setAttribute("CONFIRMAR_OTRA_MAS", true);
					forward = getForward(mapping, request, "JSP");
				}								
			}
			
		} catch(PersistenceException e){
			request.setAttribute(ERROR_MSG, getMensaje(request, "notificaciones.exception.PersistenceException"));
			e.printStackTrace(); logger.error(e);
		} catch(ServiceLocatorException e){
			e.printStackTrace(); logger.error(e);
			request.setAttribute(ERROR_MSG, getMensaje(request, "notificaciones.exception.ServiceLocatorException"));		
		} catch(Exception e){
			e.printStackTrace(); logger.error(e);
			request.setAttribute(ERROR_MSG, getMensaje(request, "notificaciones.exception.Exception"));
		}		
		
		return forward;		
	}
	
	
	public ActionForward validarDatosCorrectos(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		
		RegistroPostulacionExternaForm registroForm = (RegistroPostulacionExternaForm) form;

		String type = "exito";
		String message = "";
		
		
		if (registroForm.getNombreEmpresa()==null || registroForm.getNombreEmpresa().trim().isEmpty()){
			type = "error";
			message = "El nombre de la empresa que ofrece la oferta es requerido";
			
		}

		if (registroForm.getNombreContacto()==null || registroForm.getNombreContacto().trim().isEmpty()){
			type = "error";
			message = "El nombre de la persona de contacto es requerido";
			
		}
		
		if (registroForm.getCargoContacto()==null || registroForm.getCargoContacto().trim().isEmpty()){
			type = "error";
			message = "El cargo de la persona de contacto es requerido";
			
		}
		
		if(null==registroForm.getIdTipoTelefono() 
				|| (registroForm.getIdTipoTelefono()!=(int)Catalogos.TIPO_TELEFONO.FIJO.getIdOpcion() 
					&& registroForm.getIdTipoTelefono()!=(int)Catalogos.TIPO_TELEFONO.CELULAR.getIdOpcion())){
			type = "error";
			message = "El tipo del teléfono es requerido";			
		}
		
		if (registroForm.getAcceso()==null || registroForm.getAcceso().trim().isEmpty()){
			type = "error";
			message = "El acceso del teléfono es requerido";			
		}

		if (registroForm.getClave()==null || registroForm.getClave().trim().isEmpty()){
			type = "error";
			message = "La clave LADA del teléfono es requerida";			
		}
		
		if (registroForm.getTelefono()==null || registroForm.getTelefono().trim().isEmpty()){
			type = "error";
			message = "El teléfono es requerido";			
		}

		if (registroForm.getTituloOferta()==null || registroForm.getTituloOferta().trim().isEmpty()){
			type = "error";
			message = "El título de la oferta es requerido";			
		}

		if (registroForm.getSalarioMensual()<=0.0){
			type = "error";
			message = "El salario mensual es requerido";			
		}		
		
		if (registroForm.getComoTeEnterasteOferta()==null || registroForm.getComoTeEnterasteOferta().trim().isEmpty()){
			type = "error";
			message = "El cómo te enteraste de la oferta es requerido";			
		}
		
		if (registroForm.getDiaContacto()<=0){
			type = "error";
			message = "El día de la fecha de contacto es requerido";			
		}
			
		if (registroForm.getMesContacto()<=0){
			type = "error";
			message = "El mes de la fecha de contacto es requerido";			
		}

		if (registroForm.getAnioContacto()<=0){
			type = "error";
			message = "El año de la fecha de contacto es requerido";			
		}
				
		if (registroForm.getDiaEntrevista()<=0){
			type = "error";
			message = "El día de la fecha de entrevista es requerido";			
		}

		if (registroForm.getMesEntrevista()<=0){
			type = "error";
			message = "El mes de la fecha de entrevista es requerido";			
		}
		
		if (registroForm.getAnioEntrevista()<=0){
			type = "error";
			message = "El año de la fecha de entrevista es requerido";			
		}
				
		if(registroForm.getDiaContacto()>0 && registroForm.getMesContacto()>0 && registroForm.getAnioContacto()>0){
			
			boolean fechaContactoValida = isValidDate(registroForm.getDiaContacto(), registroForm.getMesContacto(), registroForm.getAnioContacto());
			
			if(!fechaContactoValida){
				type = "error";
				message = "La fecha de contacto es inválida";			
			
			} else {
				
				if(!isValidContactDate(registroForm.getDiaContacto(), registroForm.getMesContacto(), registroForm.getAnioContacto())){
					type = "error";
					message = "La fecha de contacto debe ser anterior o igual a la fecha actual";			
				}	
			}
		}

		if(registroForm.getDiaEntrevista()>0 && registroForm.getMesEntrevista()>0 && registroForm.getAnioEntrevista()>0){
			
			boolean fechaEntrevistaValida = isValidDate(registroForm.getDiaEntrevista(), registroForm.getMesEntrevista(), registroForm.getAnioEntrevista());
			
			if(!fechaEntrevistaValida){
				type = "error";
				message = "La fecha de entrevista es inválida";			
				
			} else {
				
				if(!isValidInterviewDate(registroForm.getDiaEntrevista(), registroForm.getMesEntrevista(), registroForm.getAnioEntrevista())){
					type = "error";
					message = "La fecha de entrevista debe ser posterior a la fecha actual";			
				}
			}
		}
				
		try {
			ResultVO resultado = new ResultVO(message, type);
			String json = toJson(resultado);
			redirectJsonResponse(response, json);
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
		return null;
	}
	
	
	private boolean isValidContactDate(int dia, int mes, int anio) {
		
		boolean isValid = true;
		
		Calendar hoy = Calendar.getInstance();
		Calendar contactDate = Calendar.getInstance();
		contactDate.set(anio, mes-1, dia);
		
		if(contactDate.after(hoy)){
			isValid = false;
		}
		
		return isValid;
	}
	
	
	private boolean isValidInterviewDate(int dia, int mes, int anio) {
		
		boolean isValid = true;
		
		Calendar hoy = Calendar.getInstance();
		Calendar contactDate = Calendar.getInstance();
		contactDate.set(anio, mes-1, dia);
		
		if(!contactDate.after(hoy)){
			isValid = false;
		}
		
		return isValid;
	}	
	
	
	private boolean isValidDate(int dia, int mes, int anio) {
		boolean isValid = false;

		try{
			GregorianCalendar gc = new GregorianCalendar();
			gc.setLenient(false);
			gc.set(anio, mes-1, dia);
			gc.getTime();
			isValid = true;
			
		} catch(Exception e){
			System.out.println("Invalid date");
		}
		return isValid;
	}	
	
	
	
	private Date convertIntsToDate(int dia, int mes, int anio) {
		Calendar cal1 = Calendar.getInstance();
		cal1.set(anio, mes-1, dia);
		return cal1.getTime();
	}		
	
	
	private boolean validaDatosBasicos(RegistroPostulacionExternaForm registroForm, HttpServletRequest request){
		
		boolean valido = true;
		
		ActionErrors errors =  new ActionErrors();
		
		
		if (registroForm.getIdUsuario()<=0){
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.invalid", "El identificador del usuario es requerido"));
		}
					
		if (registroForm.getIdCandidato()<=0){
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.invalid", "El identificador del candidato es requerido"));
			
		}
				
		
		
		if(errors == null || errors.isEmpty()) return true;
		
		
		
		valido = false;
		
		StringBuilder buf = new StringBuilder();
		
		@SuppressWarnings("unchecked")
		Iterator<ActionMessage> iterator = (Iterator<ActionMessage>) errors.get();
		while(iterator.hasNext()){
			
			ActionMessage message = iterator.next();
			Object[] values = message.getValues();
			
			String msg = null;
			if(values!=null && values.length>0){
				msg = (String) values[0];
				buf.append(msg + "<br/>");
			}			
		}
		
		if(null!=buf && buf.length()>0){
			
			String errmsg = "No se cuenta con los datos básicos para el registro.<br/> " +
					buf.toString() + "<br/>Favor de verificar su captura ó intentar de nuevo el registro y " +
					"notificar al administrador en caso de continuar con el problema";
			
			request.setAttribute(ERROR_MSG, errmsg);			
		}
		
		return valido;
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

	
	
	private List<CatalogoOpcionVO> loadAvailableDays(int month, int year){
		List<CatalogoOpcionVO> availableDays = new ArrayList<CatalogoOpcionVO>();
		
		if (month >= 0 && month <= 11 && year >= 0){
			
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.DAY_OF_MONTH, 1);
			cal.set(Calendar.MONTH, month-1);
			cal.set(Calendar.YEAR, year);			
			
			for (int i = 1; i <= cal.getActualMaximum(Calendar.DAY_OF_MONTH); i++){
				CatalogoOpcionVO opcion = new CatalogoOpcionVO();
				opcion.setIdCatalogoOpcion(i);
				opcion.setOpcion(String.valueOf(i));				
				availableDays.add(opcion);
			}
				
		} else {
			
			for (int i = 1; i <= 31; i++){
				CatalogoOpcionVO opcion = new CatalogoOpcionVO();
				opcion.setIdCatalogoOpcion(i);
				opcion.setOpcion(String.valueOf(i));								
				availableDays.add(opcion);
			}
			
		}
								
		return availableDays;
	}

	
	private int getLastAvailableDay(List<CatalogoOpcionVO> availableDays){
		
		int lastDay = 31;
		
		for (CatalogoOpcionVO d : availableDays){
			
			lastDay = Utils.toInt(d.getOpcion());
		}
				
		return lastDay;
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

	
	public ActionForward aniosContacto(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
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
		
	
	public ActionForward aniosEntrevista(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
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
	
}