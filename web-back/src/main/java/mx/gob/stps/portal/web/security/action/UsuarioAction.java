package mx.gob.stps.portal.web.security.action;

import static mx.gob.stps.portal.web.infra.utils.Constantes.BODY_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.CAT_ENTIDADES;
import static mx.gob.stps.portal.web.infra.utils.Constantes.CAT_PERFIL;
import static mx.gob.stps.portal.web.infra.utils.Constantes.CAT_TIPO_USUARIO;
import static mx.gob.stps.portal.web.infra.utils.Constantes.DESCRIPCION_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FACEBOOK_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_TEMPLATE_FORM;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TITULO_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TWITTER_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.URL_ESPECIFICA;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.gob.stps.portal.core.infra.exception.EncodingException;
import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.core.infra.utils.Password;
import mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO;
import mx.gob.stps.portal.core.seguridad.exception.CorreoRepetidoException;
import mx.gob.stps.portal.core.seguridad.exception.LoginRepetidoException;
import mx.gob.stps.portal.core.seguridad.vo.UsuarioVO;
import mx.gob.stps.portal.web.infra.action.GenericAction;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.infra.utils.PropertiesLoader;
import mx.gob.stps.portal.web.infra.vo.CatalogoVO;
import mx.gob.stps.portal.web.infra.vo.ResultVO;
import mx.gob.stps.portal.web.security.delegate.SecutityDelegate;
import mx.gob.stps.portal.web.security.delegate.SecutityDelegateImpl;
import mx.gob.stps.portal.web.security.form.UsuarioForm;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Controla el modulo de Registro de Usuarios
 * 
 * @author oscar.manzo
 *
 */
public class UsuarioAction extends GenericAction {
	private static Logger logger = Logger.getLogger(UsuarioAction.class);
	
	/**
	 * Inicializa los controles y datos para el registro de usuarios
	 */
	@Override
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		UsuarioForm usuform = (UsuarioForm)form;
		usuform.reset();
		
		HttpSession session = request.getSession();
		
		try{
			SecutityDelegate services = SecutityDelegateImpl.getInstance();

			session.setAttribute(CAT_ENTIDADES, services.consultaCatalogo(Constantes.CATALOGO_OPCION_ENTIDAD_FEDERATIVA));
			session.setAttribute(CAT_PERFIL, services.consultaCatalogo(Constantes.CATALOGO_OPCION_PERFIL));
			session.setAttribute(CAT_TIPO_USUARIO, services.consultaCatalogo(Constantes.CATALOGO_OPCION_TIPO_USUARIO));
		} catch (ServiceLocatorException e) {
			logger.error(e);
			registraError(request, "app.exp.locator.err");
		} 	

		request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
        PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Registro de Usuario");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Reg&iacute;strate en nuestra Bolsa de trabajo y postula la oferta de empleo que m&aacute;s se apegue seg&uacute;n tu curriculum vitae sin costo f&aacute;cil y sin intermediarios.");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
        return mapping.findForward(FORWARD_TEMPLATE_FORM);
	}
	
	/**
	 * Consulta y genera la salida en el formato necesario para generar el catalogo de Entidades
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward entidades(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();

		@SuppressWarnings("unchecked")
		List<CatalogoOpcionVO> opciones = (List<CatalogoOpcionVO>)session.getAttribute(CAT_ENTIDADES);

		CatalogoVO cat = getCatalogo(opciones);
		String json = toJson(cat);
		redirectJsonResponse(response, json);
		
		return null;
	}

	/**
	 * Consulta y genera la salida en el formato necesario para generar el catalogo de Perfiles
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward perfiles(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();

		@SuppressWarnings("unchecked")
		List<CatalogoOpcionVO> opciones = (List<CatalogoOpcionVO>)session.getAttribute(CAT_PERFIL);

		CatalogoVO cat = getCatalogo(opciones);
		String json = toJson(cat);
		redirectJsonResponse(response, json);
		return null;
	}

	/**
	 * Consulta y genera la salida en el formato necesario para generar el catalogo de Tipos de Usuario
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward tiposusuario(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();

		@SuppressWarnings("unchecked")
		List<CatalogoOpcionVO> opciones = (List<CatalogoOpcionVO>)session.getAttribute(CAT_TIPO_USUARIO);

		CatalogoVO cat = getCatalogo(opciones);
		String json = toJson(cat);
		redirectJsonResponse(response, json);
		return null;
	}
	
	/**
	 * Obtiene los datos capturados e invoca el registro del Usuario
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward registrar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		UsuarioForm usuform = (UsuarioForm)form;

		String msg = null;
		String type = null;
		
		try {
			String passEncoded = Password.codificaPassword(usuform.getContrasena());
			usuform.setContrasena(passEncoded);
			
			// Se poblan los datos del usuario
			UsuarioVO vo = null;

			try{
				vo = new UsuarioVO();
				PropertyUtils.copyProperties(vo, usuform);				
			}catch(Exception e){
				e.printStackTrace();
			}
			
			SecutityDelegate services = SecutityDelegateImpl.getInstance();
			services.registraUsuario(vo);
			
			msg = "Usuario registrado exitosamente.";
			type = ResultVO.TYPE_SUCCESS;
		} catch (LoginRepetidoException e) {
			logger.error(e);
			msg = "Ya existe un usuario con el nombre de usuario indicado.";
			type = ResultVO.TYPE_ERROR;
		}catch (CorreoRepetidoException e) {
			logger.error(e);
			msg = "Ya existe un usuario con la cuenta de correo indicada.";
			type = ResultVO.TYPE_ERROR;
		}catch (EncodingException e) {
			logger.error(e);
			msg = "Error en la codificación de la contraseña.";
			type = ResultVO.TYPE_ERROR;
		} catch(ServiceLocatorException e) {
			logger.error(e);
			msg = getMensaje(request, "app.exp.locator.err");
			type = ResultVO.TYPE_ERROR;
		}
		
		try {
			String json = toJson(new ResultVO(msg, type));
			redirectJsonResponse(response, json);
			return null;
		} catch (IOException e) {
			logger.error(e);
			request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
	        PropertiesLoader properties = PropertiesLoader.getInstance();
			request.getSession().setAttribute(TITULO_PAGINA, "Registro de Usuario");
			request.getSession().setAttribute(DESCRIPCION_PAGINA, "Reg&iacute;strate en nuestra Bolsa de trabajo y postula la oferta de empleo que m&aacute;s se apegue seg&uacute;n tu curriculum vitae sin costo f&aacute;cil y sin intermediarios.");
			request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
			request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
			request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
	        return mapping.findForward(FORWARD_TEMPLATE_FORM);
		}
	}

}
