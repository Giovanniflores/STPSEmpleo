package mx.gob.stps.portal.web.autorization.action;

import static mx.gob.stps.portal.web.infra.utils.Constantes.BODY_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_TEMPLATE_ESP_ADMIN;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.web.autorization.delegate.AutorizationBusDelegate;
import mx.gob.stps.portal.web.autorization.delegate.AutorizationBusDelegateImpl;
import mx.gob.stps.portal.web.autorization.form.ParametrizarForm;
import mx.gob.stps.portal.web.infra.action.GenericAction;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.infra.vo.ResultVO;

import org.apache.log4j.Logger;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 * Clase Action para el registro de parametros de configuracion de proceso de Asignacion de registros
 * 
 * @author oscar.manzo
 *
 */
public class ParametrizarAsignacionAction extends GenericAction {
	private static Logger logger = Logger.getLogger(ParametrizarAsignacionAction.class);

	/**
	 * Inicializa los objetos y datos necesarios para presentar la pagina
	 */
	@Override
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		ParametrizarForm paramform = (ParametrizarForm)form;
		paramform.reset();

		try{
			AutorizationBusDelegate services = AutorizationBusDelegateImpl.getInstance();
			int tiempoReasignacion = services.consultaTiempoAsignacion();

			paramform.setTiempoReasignacion(tiempoReasignacion);

		} catch (ServiceLocatorException e) {
			logger.error(e);
			registraError(request, "app.exp.locator.err");
		} catch(BusinessException e){
			logger.error(e);
			registraError(request, "app.exp.negocio.err", e.getMessage());
		}
		
		request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
		//return mapping.findForward(FORWARD_TEMPLATE_FORM);
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
		ParametrizarForm paramform = (ParametrizarForm)form;

		String msg = null;
		String type = null;
		
		ActionErrors errors = paramform.validate(mapping, request);

		if (errors==null){ // Sin errores de datos

			int minutos = paramform.getTiempoReasignacion();
			
			try {
				AutorizationBusDelegate services = AutorizationBusDelegateImpl.getInstance();
				services.registraTiempoAsignacion(minutos);

				msg = getMensaje(request, "aut.param.msg1");
				type = ResultVO.TYPE_SUCCESS;

			} catch (BusinessException e) {
				logger.error(e);
				msg = getMensaje(request, "app.exp.negocio.err", e.getMessage());
				type = ResultVO.TYPE_ERROR;
			} catch (ServiceLocatorException e) {
				logger.error(e);
				msg = getMensaje(request, "app.exp.locator.err");
				type = ResultVO.TYPE_ERROR;
			} catch (Exception e) {
				logger.error(e);
				msg = getMensaje(request, "aut.param.msg2");
				type = ResultVO.TYPE_ERROR;
			}
			
		}else{
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.message", "Favor de revisar los datos proporcionados."));
			request.setAttribute(Globals.ERROR_KEY, errors);
		}
		
		try {
			String json = toJson(new ResultVO(msg, type));
			redirectJsonResponse(response, json);
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
			//return mapping.findForward(FORWARD_TEMPLATE_FORM);
			return mapping.findForward(FORWARD_TEMPLATE_ESP_ADMIN);
		}
	}

	
}
