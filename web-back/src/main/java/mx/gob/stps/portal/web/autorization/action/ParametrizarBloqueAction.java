package mx.gob.stps.portal.web.autorization.action;

import static mx.gob.stps.portal.core.infra.utils.Constantes.ID_PARAMETRO_TOTAL_VACANTES_ADECCO;
import static mx.gob.stps.portal.core.infra.utils.Constantes.ID_PARAMETRO_TOTAL_VACANTES_BUMERAN;
import static mx.gob.stps.portal.core.infra.utils.Constantes.ID_PARAMETRO_TOTAL_VACANTES_MANPOWER;
import static mx.gob.stps.portal.core.infra.utils.Constantes.ID_PARAMETRO_TOTAL_VACANTES_OCC;
import static mx.gob.stps.portal.core.infra.utils.Constantes.ID_PARAMETRO_TOTAL_VACANTES_STPS;
import static mx.gob.stps.portal.core.infra.utils.Constantes.ID_PARAMETRO_TOTAL_VACANTES_UNIVERSAL;
import static mx.gob.stps.portal.core.infra.utils.Constantes.ID_PARAMETRO_TOTAL_VACANTES_ZONA_JOBS;
import static mx.gob.stps.portal.core.infra.utils.Constantes.ID_PARAMETRO_TAM_BLOQUE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.BODY_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_TEMPLATE_ESP_ADMIN;
import static mx.gob.stps.portal.web.infra.utils.Constantes.URL_REDIRECT_SWB;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.stps.portal.core.autorizacion.vo.ParametroVO;
import mx.gob.stps.portal.core.infra.utils.Constantes.EVENTO;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_PROPIETARIO;
import mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO;
import mx.gob.stps.portal.core.seguridad.vo.UsuarioVO;
import mx.gob.stps.portal.web.autorization.delegate.AutorizationBusDelegate;
import mx.gob.stps.portal.web.autorization.delegate.AutorizationBusDelegateImpl;
import mx.gob.stps.portal.web.autorization.form.ParametrizarForm;
import mx.gob.stps.portal.web.infra.action.GenericAction;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.infra.utils.PropertiesLoader;
import mx.gob.stps.portal.web.infra.utils.Utils;
import mx.gob.stps.portal.web.infra.vo.CatalogoVO;
import mx.gob.stps.portal.web.infra.vo.ResultVO;
import mx.gob.stps.portal.web.security.delegate.SecutityDelegateImpl;
import mx.gob.stps.portal.web.security.vo.UsuarioWebVO;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Clase Action para el registro de parametros de configuracion de proceso de Asignacion de registros
 * 
 * @author oscar.manzo
 *
 */
public class ParametrizarBloqueAction extends GenericAction {
	private static Logger logger = Logger.getLogger(ParametrizarBloqueAction.class);

	/**
	 * Inicializa los objetos y datos necesarios para presentar la pagina
	 */
	@Override
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		ParametrizarForm paramform = (ParametrizarForm)form;
		paramform.reset();
		UsuarioWebVO usuario = getUsuario(request.getSession());
		try {
		UsuarioVO usuarioVo = SecutityDelegateImpl.getInstance().consultaUsuario(usuario.getIdUsuario());
		
		
		request.getSession().setAttribute("TITULO", "Administrador");
		request.getSession().setAttribute("datosAdmin", usuarioVo.getNombre()+" "+usuarioVo.getApellido1()+" "+usuarioVo.getApellido2());
		/*try{
			AutorizationBusDelegate services = AutorizationBusDelegateImpl.getInstance();
			int bloqueAsignacion = services.consultaBloqueAsignacion();

			paramform.setBloqueAsignacion(bloqueAsignacion);

		} catch (ServiceLocatorException e) {
			logger.error(e);
			registraError(request, "app.exp.locator.err");
		} catch(BusinessException e){
			logger.error(e);
			registraError(request, "app.exp.negocio.err", e.getMessage());
		}*/
		request.getSession().setAttribute("TITULO", "Administrador");
		} catch (ServiceLocatorException e) {
			logger.error(e);
			registraError(request, "app.exp.locator.err");
		}
		request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
		return mapping.findForward(FORWARD_TEMPLATE_ESP_ADMIN);
	}

	
	public ActionForward cancelar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		PropertiesLoader properties = PropertiesLoader.getInstance();
		request.setAttribute(URL_REDIRECT_SWB, properties.getProperty("app.swb.redirect.home"));
		return mapping.findForward(FORWARD_TEMPLATE_ESP_ADMIN);
	}		
	
	/**
	 * Obtiene los datos capturados por el usuario e invoca el registros de prametro
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward registrar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		String msg = "";
		String type = "";
		StringBuilder detail = new StringBuilder();
		ParametrizarForm paramform = (ParametrizarForm)form;
		UsuarioWebVO user = getUsuario(request.getSession());
		ActionErrors errors = null; //paramform.validateBloque(mapping, request);
		
		if (errors==null){ // Sin errores de datos
		
			try {
				AutorizationBusDelegate services = AutorizationBusDelegateImpl.getInstance();
				services.actualizaParametro(paramform.getIdParametro(), paramform.getValor());
				ParametroVO param = services.consultaParametro(paramform.getIdParametro());
				detail.append(param.getDescripcion()).append("=").append(paramform.getValor());
				services.bitacoraEstatus(EVENTO.ACTUALIZAR_PARAMETRO, user.getIdUsuario(), EVENTO.ACTUALIZAR_PARAMETRO.getEvento(), null, paramform.getIdParametro(),
						Utils.parseInt(param.getValor()), detail.toString(), TIPO_PROPIETARIO.ADMINISTRADOR.getIdTipoPropietario());
				msg = getMensaje(request, "aut.param.msg1");
				type = ResultVO.TYPE_SUCCESS;

			} /*catch (BusinessException e) {
				logger.error(e);
				msg = getMensaje(request, "app.exp.negocio.err", e.getMessage());
				type = ResultVO.TYPE_ERROR;
			} */catch (ServiceLocatorException e) {
				logger.error(e);
				msg = getMensaje(request, "app.exp.locator.err");
				type = ResultVO.TYPE_ERROR;
			} catch (Exception e) {
				logger.error(e);
				msg = getMensaje(request, "aut.param.msg2");
				type = ResultVO.TYPE_ERROR;
			}
			
		}/*else{
			//errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.message", "Favor de revisar los datos proporcionados."));
			//request.setAttribute(Globals.ERROR_KEY, errors);
			msg = "La Cantidad de registros por asignar proporcionada no es un dato válido (5-50)";
			type = ResultVO.TYPE_ERROR;
		}*/
		
		try {
			String json = toJson(new ResultVO(msg, type));
			redirectJsonResponse(response, json);
			return null;
		} catch (IOException e) {
			logger.error(e);
			request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
	        return mapping.findForward(FORWARD_TEMPLATE_ESP_ADMIN);
		}
	}

	public ActionForward parametros(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		List<CatalogoOpcionVO> opciones = new ArrayList<CatalogoOpcionVO>();
		
		try {
			UsuarioWebVO usuario = getUsuario(request.getSession());
			
			AutorizationBusDelegate services = AutorizationBusDelegateImpl.getInstance();

			List<ParametroVO> parametros = services.consultaParametros();
			for (ParametroVO parametro : parametros){
				
				if (usuario.getAdminTipoA()){
					switch ((int)parametro.getIdParametro()){
						case (int)ID_PARAMETRO_TOTAL_VACANTES_OCC:
						case (int)ID_PARAMETRO_TOTAL_VACANTES_BUMERAN:
						case (int)ID_PARAMETRO_TOTAL_VACANTES_MANPOWER:
						case (int)ID_PARAMETRO_TOTAL_VACANTES_ADECCO:
						case (int)ID_PARAMETRO_TOTAL_VACANTES_UNIVERSAL:
						case (int)ID_PARAMETRO_TOTAL_VACANTES_STPS:
						case (int)ID_PARAMETRO_TOTAL_VACANTES_ZONA_JOBS:
							opciones.add(getCatalogoOpcionVO(parametro));
						break;
					}
				}else if (usuario.getAdministradorSnetel()){
					switch ((int)parametro.getIdParametro()){
						case (int)ID_PARAMETRO_TAM_BLOQUE:
							opciones.add(getCatalogoOpcionVO(parametro));
						break;
					}
				}else{
					opciones.add(getCatalogoOpcionVO(parametro));
				}
			}

			CatalogoVO cat = getCatalogo(opciones);
			String json = toJson(cat);
			
			redirectJsonResponse(response, json);
			
		} catch (ServiceLocatorException e) {logger.error(e);}
		  catch (IOException e) {logger.error(e);}

		return null;
	}	

	public ActionForward valorparametro(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		
		long idParametro = Utils.parseLong(request.getParameter("idParametro"));
		
		try {
			AutorizationBusDelegate services = AutorizationBusDelegateImpl.getInstance();

			ParametroVO parametro = services.consultaParametro(idParametro);

			String json = toJson(parametro);
			redirectJsonResponse(response, json);
			
		} catch (ServiceLocatorException e) {logger.error(e);}
		  catch (IOException e) {logger.error(e);}
		
		return null;
	}	
	
	private CatalogoOpcionVO getCatalogoOpcionVO(ParametroVO parametro){
		CatalogoOpcionVO vo = new CatalogoOpcionVO();
		vo.setIdCatalogoOpcion(parametro.getIdParametro());
		vo.setOpcion(parametro.getDescripcion());
		return vo;
	}	

}
