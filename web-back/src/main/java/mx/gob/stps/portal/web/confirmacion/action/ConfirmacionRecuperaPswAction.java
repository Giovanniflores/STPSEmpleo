package mx.gob.stps.portal.web.confirmacion.action;

import static mx.gob.stps.portal.core.candidate.vo.ConfirmacionRegistroVo.TAMANO_CORRECTO;
import static mx.gob.stps.portal.web.infra.utils.Constantes.BODY_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_TEMPLATE_CAND;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.stps.portal.core.candidate.vo.ConfirmacionRegistroVo;
import mx.gob.stps.portal.core.infra.exception.BusinessException;
import static mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_PROPIETARIO;
import static mx.gob.stps.portal.core.infra.utils.Constantes.SEPARADOR_PARAM;

import mx.gob.stps.portal.core.infra.utils.Utils;
import mx.gob.stps.portal.core.seguridad.vo.UsuarioVO;
import mx.gob.stps.portal.web.confirmacion.form.ConfirmacionRegistroForm;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.infra.utils.PropertiesLoader;
import mx.gob.stps.portal.web.security.action.LoginAction;
import mx.gob.stps.portal.web.security.delegate.SecutityDelegate;
import mx.gob.stps.portal.web.security.delegate.SecutityDelegateImpl;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

//TODO ELIMINAR CLASE, CON LA NUEVA VERSION YA SE NO UTILIZA
public class ConfirmacionRecuperaPswAction extends Action {
	private static Logger logger = Logger.getLogger(ConfirmacionRecuperaPswAction.class);
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		ConfirmacionRegistroForm confirmacionRegistroForm = (ConfirmacionRegistroForm) form;
		ConfirmacionRegistroVo argumetos = null;
		//logger.info("----ENTRE a ConfirmacionRecuperaPswAction.execute");
		
		try {
			String argumetCodec = request.getParameter("argumet");
			String argumet = Utils.decodifica(argumetCodec);			
			argumetos = getArgumentos(argumet);
			
			logger.info("Argumentos para la confirmacion de cuenta:" + argumetos.toString());

			long idPropietario = argumetos.getIdPropietario();
			String correo = argumetos.getCorreo();
			String username = argumetos.getUsuario();
			String contrasena = argumetos.getPassword();

			SecutityDelegate service = SecutityDelegateImpl.getInstance();
			
			String correoActual = null;
			boolean usuarioAutorizado = false;
			
			if (TIPO_PROPIETARIO.CANDIDATO.getIdTipoPropietario() == argumetos.getIdTipoPropietario()) {
				correoActual = service.confirmaContrasenaCandidato(idPropietario, correo, contrasena);
				usuarioAutorizado = true;
				
			}else if (TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario() == argumetos.getIdTipoPropietario()) {
				correoActual = service.confirmaContrasenaEmpresa(idPropietario, correo, contrasena);
				usuarioAutorizado = true;
			}

			logger.info("Se confirma la recuperacion de contraseña para el usuario con Correo:"+ correoActual);
		
			if(usuarioAutorizado){
				logger.info("Usuario autorizado ("+ correoActual +"), se firma dentro del Portal");
				
				LoginAction loginAction = new LoginAction();
				
				SecutityDelegate services = SecutityDelegateImpl.getInstance();
				UsuarioVO usuario = services.consultaUsuarioPorLogin(username);

				//if (loginAction.validatePassword(usuario.getContrasena(), contrasena)){
				loginAction.firmaUsuarioPortal(request, response, usuario);
				
				ActionForward forward = loginAction.redirectSWB(usuario.getIdPerfil(), mapping, request, response, username);
				return forward;
				//}
			} else {
				logger.info("Usuario NO autorizado ("+ correoActual +"), se direcciona a pagina de error");
			}
		}catch (BusinessException e) {
			e.printStackTrace(); logger.error(e);
			registraError(request, "notificaciones.exception.BusinessException", e.getMessage());
		} catch (ServiceLocatorException e) {
			e.printStackTrace(); logger.error(e);
			registraError(request, "notificaciones.exception.ServiceLocatorException");
		} catch (Exception e) {
			e.printStackTrace(); logger.error(e);
			registraError(request, "notificaciones.exception.Exception");
		}
				
		logger.error("El usuario no se pudo Firmar dentro del Portal del Empleo");	
		envioUrl(confirmacionRegistroForm, argumetos);
	
		request.getSession().setAttribute(BODY_JSP, mapping.getInput());
		return mapping.findForward(FORWARD_TEMPLATE_CAND);
	}

	/** Armando la Url con los valores para el Login
	 * @param confirmacionRegistroVo
	 * @return
	 */
	/*private String getUrlLogin(String correo, String passw, int tipo){
		StringBuilder sb = new StringBuilder();

		sb.append("&isHome=true");
		sb.append("&username=" + correo);
		sb.append("&password=" + passw);		

		if (TIPO_PROPIETARIO.CANDIDATO.getIdTipoPropietario() == tipo)
			sb.append("&login_page=cand");
		else if	(TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario() == tipo)
			sb.append("&login_page=emp");

		return sb.toString();
	}*/

	/** Seteando valores a un ConfirmacionRegistroVo
	 * @param argumentos
	 * @return
	 * @throws BusinessException
	 */
	private ConfirmacionRegistroVo getArgumentos(String argumentos)throws Exception, BusinessException {
		ConfirmacionRegistroVo args = new ConfirmacionRegistroVo();
		String[] arg = argumentos.split(SEPARADOR_PARAM);

		if (tamanoArreglo(arg) && sonNumero(arg[0], arg[1])) {
			
			args.setIdPropietario(Utils.parseLong(arg[0]));
			args.setIdTipoPropietario(Utils.parseInt(arg[1]));
			args.setCorreo(arg[2]);
			args.setPassword(arg[3]);
			
			if (arg.length>=5)
				args.setUsuario(arg[4]);
		}

		correctoTipoPropietario(args.getIdTipoPropietario());

		return args;
	}

	/** Validando el numero de argumentos
	 * @param arg
	 * @return
	 * @throws BusinessException
	 */
	private boolean tamanoArreglo(String[] arg) throws BusinessException {
		boolean bandera = false;

		if (arg.length == TAMANO_CORRECTO) {
			bandera = true;
		} else {
			throw new BusinessException("No contiene el numero de argumentos necesarios para la activacion");
		}

		return bandera;
	}

	/** Validando si los Id y el tipo son valores numericos
	 * @param id
	 * @param tipo
	 * @return
	 * @throws BusinessException
	 */
	private boolean sonNumero(String id, String tipo) throws BusinessException {
		boolean bandera = false;

		if (Utils.esNumero(id) && Utils.esNumero(tipo)) {
			bandera = true;
		} else {
			throw new BusinessException("Los valor id y/o tipo no son valores numericos");
		}
		
		return bandera;
	}

	/** Validamos que los valores sean de tipo CANDIDATO o EMPRESA
	 * @param tipoPropietario
	 * @param tipo
	 * @return
	 * @throws BusinessException
	 */
	private boolean correctoTipoPropietario(int idTipoPropietario) throws BusinessException {
		boolean bandera = false;
		
		if (TIPO_PROPIETARIO.CANDIDATO.getIdTipoPropietario() == idTipoPropietario || 
			TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario() == idTipoPropietario) {
			bandera = true;
		} else {
			throw new BusinessException("El tipo es distinto a CANDIDATO o  EMPRESAS");
		}
		return bandera;
	}
	
	/** Setiando valores para uso de las Url para sesion y registro
	 * @param confirmacionRegistroForm
	 * @param confirmacionRegistroVo
	 * @param request
	 */
	private void envioUrl(ConfirmacionRegistroForm confirmacionRegistroForm, ConfirmacionRegistroVo confirmacionRegistroVo){
		PropertiesLoader properties = PropertiesLoader.getInstance();

		if (confirmacionRegistroForm!=null){
		confirmacionRegistroForm.setHome(properties.getProperty("app.swb.redirect.home"));		

		if (TIPO_PROPIETARIO.CANDIDATO.getIdTipoPropietario() == confirmacionRegistroVo.getIdTipoPropietario()) {
			confirmacionRegistroForm.setUrlInicioSesion(properties.getProperty("app.swb.redirect.registro.candidato"));
			confirmacionRegistroForm.setUrlRegistro(properties.getProperty("app.swb.redirect.registro.candidato"));

		} else if (TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario() == confirmacionRegistroVo.getIdTipoPropietario()) {
			confirmacionRegistroForm.setUrlInicioSesion(properties.getProperty("app.swb.redirect.registro.empresa"));
			confirmacionRegistroForm.setUrlRegistro(properties.getProperty("app.swb.redirect.registro.empresa"));
		}
		}
	}
	
	private void registraError(HttpServletRequest request, String mensaje){
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(mensaje));
		saveErrors(request, errors);
	}

	private void registraError(HttpServletRequest request, String mensaje, String param01){
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(mensaje, param01));
		saveErrors(request, errors);
	}	
}
