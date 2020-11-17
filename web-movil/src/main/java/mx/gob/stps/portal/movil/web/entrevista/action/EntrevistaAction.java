package mx.gob.stps.portal.movil.web.entrevista.action;


import java.util.Date;
import java.util.List;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.core.infra.utils.Constantes.PERFIL;
import mx.gob.stps.portal.core.infra.utils.Utils;
import mx.gob.stps.portal.core.infra.vo.EntrevistaVO;
import mx.gob.stps.portal.movil.web.entrevista.delegate.EntrevistaDelegateImpl;
import mx.gob.stps.portal.movil.web.entrevista.form.EntrevistaForm;
import mx.gob.stps.portal.movil.web.infra.action.GenericAction;
import mx.gob.stps.portal.movil.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.movil.web.seguridad.vo.UsuarioFirmadoVO;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import static mx.gob.stps.portal.movil.web.infra.utils.ConstantesMovil.PARAM_ENTREVISTA_MENSAJE;

public class EntrevistaAction extends GenericAction{
	
	private static final String FORWARD_ENTREVISTA_EMPRESA = "JSP_ENTREVISTA_EMPRESA";
	private static final String FORWARD_ENTREVISTA_MODIFICA_ESTATUS = "JSP_ENTREVISTA_MODIFICA_ESTATUS";
	private static final String FORWARD_ENTREVISTA_INICIO_REPROGRAMAR = "JSP_ENTREVISTA_REPROGRAMAR";
	private static final String FORWARD_ENTREVISTA_INICIO_PROGRAMAR = "JSP_ENTREVISTA_PROGRAMAR";
	private static final String FORDWARD_CONFIRMACION = "FORDWARD_CONFIRMACION";
	public static final String REPROGRAMAR			= "Reprogramación";

	private static Logger logger = Logger.getLogger(EntrevistaAction.class);
	
	@Override
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		return null;
	}
	
	public ActionForward inicioProgramarEntrevista(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		
		EntrevistaForm entrevistaForm = (EntrevistaForm) form;
		UsuarioFirmadoVO usuario = getUsuarioFirmado(request.getSession());
		entrevistaForm.setIdPerfil((int) usuario.getIdPerfil());
		
		return mapping.findForward(FORWARD_ENTREVISTA_INICIO_PROGRAMAR);
		
	}

	public ActionForward entrevistaProgramadaEmpresas(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		EntrevistaForm entrevistaForm = (EntrevistaForm) form;
		EntrevistaDelegateImpl service = EntrevistaDelegateImpl.getInstance();
		EntrevistaVO entrevistaVO = getEntrevistaVO(request.getSession());
		
		String mensaje = null;
		
		try {
			List<EntrevistaVO> listaEntrevistas = service.getEntrevistaProgramadaEmpresa(entrevistaVO);
			entrevistaForm.setEntrevistas(listaEntrevistas);
			entrevistaForm.setTituloEtiqueta("Nombre completo del candidato");
			entrevistaForm.setTituloTipo(PERFIL.EMPRESA.getOpcion());
			
		} catch (PersistenceException e) {
			e.printStackTrace(); logger.error(e);
			mensaje = getMensaje(request, "errors.persistenceException");
		} catch (ServiceLocatorException e) {
			e.printStackTrace(); logger.error(e);
			mensaje = getMensaje(request, "errors.serviceLocatorException");
		} catch (Exception e) {
			e.printStackTrace(); logger.error(e);
			mensaje = getMensaje(request, "errors.exception");
		}
				
		if (mensaje!=null)
			request.setAttribute(PARAM_ENTREVISTA_MENSAJE, mensaje);

		return mapping.findForward(FORWARD_ENTREVISTA_EMPRESA);
	}
	
	public ActionForward modificarEstatusEntrevista(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		EntrevistaForm entrevistaForm = (EntrevistaForm) form;
		UsuarioFirmadoVO usuario = getUsuarioFirmado(request.getSession());
		entrevistaForm.setIdPerfil((int) usuario.getIdPerfil());
		return mapping.findForward(FORWARD_ENTREVISTA_MODIFICA_ESTATUS);
	}
	
	public ActionForward aceptarEntrevista(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		ActionForward forward = mapping.findForward(FORWARD_ENTREVISTA_MODIFICA_ESTATUS);
		String mensaje = null;
		
		UsuarioFirmadoVO usuario = getUsuarioFirmado(request.getSession());
		
		try {
			EntrevistaForm entrevistaForm = (EntrevistaForm) form;
			EntrevistaVO entrevistaVo = new EntrevistaVO();
			EntrevistaDelegateImpl service = EntrevistaDelegateImpl.getInstance();

			entrevistaVo.setIdEntrevista(entrevistaForm.getIdEntrevista());
			entrevistaVo.setTipo(entrevistaForm.getTituloTipo());
			entrevistaVo.setEstatus(Constantes.ESTATUS.ACEPTADA.getIdOpcion());
			entrevistaVo.setMensajeBitacora("Aceptando la entrevista con id " + entrevistaVo.getIdEntrevista());
			entrevistaVo.setFechaModificacion(new Date());
			entrevistaVo.setIdCandidato(entrevistaForm.getIdCandidato());
			entrevistaVo.setIdUsuario(usuario.getIdUsuario());
			
			service.aceptarEntrevista(entrevistaVo);

			mensaje = getMensaje(request, "entrevista.confirm.msg.aceptada");

			if (usuario.getIdPerfil() == PERFIL.EMPRESA.getIdOpcion()){
				forward = entrevistaProgramadaEmpresas(mapping, form, request, response);
			} else if (usuario.getIdPerfil() == PERFIL.CANDIDATO.getIdOpcion()){
				//forward = mapping.findForward("FORDWARD_ESPACIO_CANDIDATO");
				forward = mapping.findForward(FORDWARD_CONFIRMACION);
			}
		} catch (PersistenceException e) {
			e.printStackTrace(); logger.error(e);
			mensaje = getMensaje(request, "errors.persistenceException");
		} catch (ServiceLocatorException e) {
			e.printStackTrace(); logger.error(e);
			mensaje = getMensaje(request, "errors.serviceLocatorException");
		} catch (Exception e) {
			e.printStackTrace(); logger.error(e);
			mensaje = getMensaje(request, "errors.exception");
		}

		if (mensaje!=null)
			request.setAttribute(PARAM_ENTREVISTA_MENSAJE, mensaje);

		return forward;
	}
	
	public ActionForward cancelarEntrevista(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		ActionForward forward = mapping.findForward(FORWARD_ENTREVISTA_MODIFICA_ESTATUS);
		String mensaje = null;
		UsuarioFirmadoVO usuario = getUsuarioFirmado(request.getSession());

		try {
			EntrevistaForm entrevistaForm = (EntrevistaForm) form;
			EntrevistaVO entrevistaVo = new EntrevistaVO();
			EntrevistaDelegateImpl service = EntrevistaDelegateImpl.getInstance();
			
			entrevistaVo.setIdEntrevista(entrevistaForm.getIdEntrevista());
			entrevistaVo.setTipo(entrevistaForm.getTituloTipo());
			entrevistaVo.setEstatus(Constantes.ESTATUS.CANCELADA.getIdOpcion());	
			entrevistaVo.setMensajeBitacora("Cancelando la entrevista con id " + entrevistaVo.getIdEntrevista());
			entrevistaVo.setFechaModificacion(new Date());
			entrevistaVo.setIdCandidato(entrevistaForm.getIdCandidato());
			entrevistaVo.setIdUsuario(getUsuarioFirmado(request.getSession()).getIdUsuario());

			service.cancelarEntrevista(entrevistaVo);
		
			mensaje = getMensaje(request, "entrevista.confirm.msg.cancelada");

			if (usuario.getIdPerfil() == PERFIL.EMPRESA.getIdOpcion()){
				forward = entrevistaProgramadaEmpresas(mapping, form, request, response);
			} else if (usuario.getIdPerfil() == PERFIL.CANDIDATO.getIdOpcion()){
				//forward = mapping.findForward("FORDWARD_ESPACIO_CANDIDATO");
				forward = mapping.findForward(FORDWARD_CONFIRMACION);
			}
		} catch (PersistenceException e) {
			e.printStackTrace(); logger.error(e);
			mensaje = getMensaje(request, "errors.persistenceException");
		} catch (ServiceLocatorException e) {
			e.printStackTrace(); logger.error(e);
			mensaje = getMensaje(request, "errors.serviceLocatorException");
		} catch (Exception e) {
			e.printStackTrace(); logger.error(e);
			mensaje = getMensaje(request, "errors.exception");
		}
				
		if (mensaje!=null)
			request.setAttribute(PARAM_ENTREVISTA_MENSAJE, mensaje);

		return forward;
	}
	
	public ActionForward rechazarEntrevista(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		ActionForward forward = mapping.findForward(FORWARD_ENTREVISTA_MODIFICA_ESTATUS);
		String mensaje = null;
		UsuarioFirmadoVO usuario = getUsuarioFirmado(request.getSession());

		try {
			EntrevistaForm entrevistaForm = (EntrevistaForm) form;
			EntrevistaVO entrevistaVo = new EntrevistaVO();
			EntrevistaDelegateImpl service = EntrevistaDelegateImpl.getInstance();
			
			entrevistaVo.setIdEntrevista(entrevistaForm.getIdEntrevista());
			entrevistaVo.setTipo(entrevistaForm.getTituloTipo());
			entrevistaVo.setEstatus(Constantes.ESTATUS.RECHAZADA.getIdOpcion());
			entrevistaVo.setMensajeBitacora("Rechazando la entrevista con id " + entrevistaVo.getIdEntrevista());
			entrevistaVo.setFechaModificacion(new Date());
			entrevistaVo.setIdCandidato(entrevistaForm.getIdCandidato());
			entrevistaVo.setIdUsuario(getUsuarioFirmado(request.getSession()).getIdUsuario());
			
			service.rechazarEntrevista(entrevistaVo);
			mensaje = getMensaje(request, "entrevista.confirm.msg.rechazada");

			if (usuario.getIdPerfil() == PERFIL.EMPRESA.getIdOpcion()){
				forward = entrevistaProgramadaEmpresas(mapping, form, request, response);
			} else if (usuario.getIdPerfil() == PERFIL.CANDIDATO.getIdOpcion()){
				//forward = mapping.findForward("FORDWARD_ESPACIO_CANDIDATO");
				forward = mapping.findForward(FORDWARD_CONFIRMACION);
			}
		} catch (PersistenceException e) {
			e.printStackTrace(); logger.error(e);
			mensaje = getMensaje(request, "errors.persistenceException");
		} catch (ServiceLocatorException e) {
			e.printStackTrace(); logger.error(e);
			mensaje = getMensaje(request, "errors.serviceLocatorException");
		} catch (Exception e) {
			e.printStackTrace(); logger.error(e);
			mensaje = getMensaje(request, "errors.exception");
		}

		if (mensaje!=null)
			request.setAttribute(PARAM_ENTREVISTA_MENSAJE, mensaje);

		return forward;
	}
	
	
	public ActionForward reprogramar(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		ActionForward forward = mapping.findForward(FORWARD_ENTREVISTA_MODIFICA_ESTATUS);
		String mensaje = null;
		UsuarioFirmadoVO usuario = getUsuarioFirmado(request.getSession());

		try {
			EntrevistaForm entrevistaForm = (EntrevistaForm) form;
			EntrevistaVO entrevistaVo = new EntrevistaVO();
			EntrevistaDelegateImpl service = EntrevistaDelegateImpl.getInstance();
			
			entrevistaVo.setIdEntrevista(entrevistaForm.getIdEntrevista());
			entrevistaVo.setTipo(REPROGRAMAR);
			entrevistaVo.setEstatus(Constantes.ESTATUS.REPROGRAMADA.getIdOpcion());
			entrevistaVo.setMensajeBitacora("reprogramando la entrevista con id " + entrevistaVo.getIdEntrevista());
			entrevistaVo.setFechaModificacion(new Date());
			entrevistaVo.setIdCandidato(entrevistaForm.getIdCandidato());
			entrevistaVo.setIdUsuario(getUsuarioFirmado(request.getSession()).getIdUsuario());
			entrevistaVo.setFecha(Utils.convert(entrevistaForm.getFechaEntrevista()));
			entrevistaVo.setHora(entrevistaForm.getHoraEntrevista());
			logger.info(entrevistaVo.getFecha());
			logger.info(entrevistaVo.getHora());
			service.reprogramarEntrevista(entrevistaVo);
			mensaje = getMensaje(request, "entrevista.confirm.msg.reprog");

			if (usuario.getIdPerfil() == PERFIL.EMPRESA.getIdOpcion()){
				forward = entrevistaProgramadaEmpresas(mapping, form, request, response);
			} else if (usuario.getIdPerfil() == PERFIL.CANDIDATO.getIdOpcion()){
				//forward = mapping.findForward("FORDWARD_ESPACIO_CANDIDATO");
				forward = mapping.findForward(FORDWARD_CONFIRMACION);
			}
		} catch (PersistenceException e) {
			e.printStackTrace(); logger.error(e);
			mensaje = getMensaje(request, "errors.persistenceException");
		} catch (ServiceLocatorException e) {
			e.printStackTrace(); logger.error(e);
			mensaje = getMensaje(request, "errors.serviceLocatorException");
		} catch (Exception e) {
			e.printStackTrace(); logger.error(e);
			mensaje = getMensaje(request, "errors.exception");
		}

		if (mensaje!=null)
			request.setAttribute(PARAM_ENTREVISTA_MENSAJE, mensaje);

		return forward;
	}
	
	
	public ActionForward reprogramarEntrevista(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		return mapping.findForward(FORWARD_ENTREVISTA_INICIO_REPROGRAMAR);
		
	}
	
	
	public ActionForward programarEntrevista(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		EntrevistaDelegateImpl entrevistaService = EntrevistaDelegateImpl.getInstance();	
		EntrevistaForm entrevistaForm = (EntrevistaForm) form;
		EntrevistaVO entrevistaVo = new EntrevistaVO();
		entrevistaVo.setIdCandidato(entrevistaForm.getIdCandidato());
		entrevistaVo.setIdOfertaEmpleo(entrevistaForm.getIdOferta());
		entrevistaVo.setEstatus(Constantes.ESTATUS.NUEVA.getIdOpcion());
		entrevistaVo.setFecha(Utils.convert(entrevistaForm.getFechaEntrevista()));
		entrevistaVo.setHora(entrevistaForm.getHoraEntrevista());
		entrevistaVo.setFechaAlta(new Date());
		try {
			entrevistaService.save(entrevistaVo);
			//enviarNotificacion(entrevistaVo);
		} catch (ServiceLocatorException e) {
			logger.error(e);
		}
		return entrevistaProgramadaEmpresas(mapping, form, request, response);
	}
	
	
	private EntrevistaVO getEntrevistaVO(HttpSession session) {
		UsuarioFirmadoVO usuario = getUsuarioFirmado(session);
		EntrevistaVO entrevistaVo = new EntrevistaVO();
	
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
}
