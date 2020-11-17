package mx.gob.stps.portal.web.confirmacion.action;

import static mx.gob.stps.portal.core.candidate.vo.ConfirmacionRegistroVo.TAMANO_CORRECTO;
import static mx.gob.stps.portal.web.infra.utils.Constantes.BODY_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_TEMPLATE_CAND;

import static mx.gob.stps.portal.core.infra.utils.Constantes.SEPARADOR_PARAM;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.stps.portal.core.candidate.vo.ConfirmacionRegistroVo;
import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_PROPIETARIO;
import mx.gob.stps.portal.core.infra.utils.Utils;
import mx.gob.stps.portal.core.seguridad.vo.UsuarioVO;
import mx.gob.stps.portal.web.candidate.delegate.CandidatosRegistroBusDelegate;
import mx.gob.stps.portal.web.candidate.delegate.CandidatosRegistroBusDelegateImpl;
import mx.gob.stps.portal.web.company.delegate.CompanyBusDelegate;
import mx.gob.stps.portal.web.company.delegate.CompanyBusDelegateImpl;
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

// TODO ELIMINAR CLASE, CON LA NUEVA VERSION YA SE NO UTILIZA
public class ConfirmacionRegistroAction extends Action {

	private static Logger logger = Logger.getLogger(ConfirmacionRegistroAction.class);

	public static final String mensaje_error = "La liga de Activación es incorrecta";
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		ConfirmacionRegistroForm confirmacionRegistroForm = (ConfirmacionRegistroForm) form;
		
		ConfirmacionRegistroVo argumetos = null;		
		
		try {
			if (confirmacionRegistroForm.getArgumet() != null) {
				String argumet = confirmacionRegistroForm.getArgumet();
				String decode = Utils.decodifica(argumet);
				argumetos = getArgumentos(decode);
				
				long idPropietario = argumetos.getIdPropietario();
				String correo    = argumetos.getCorreo();
				String username    = argumetos.getUsuario();
				String contrasena  = argumetos.getPassword();
				
				boolean usuarioAutorizado = false;
				
				if (TIPO_PROPIETARIO.CANDIDATO.getIdTipoPropietario() == argumetos.getIdTipoPropietario()) {			
					CandidatosRegistroBusDelegate candidatosRegistroBusDelegate = CandidatosRegistroBusDelegateImpl.getInstance();
					candidatosRegistroBusDelegate.activarCandidato(idPropietario, correo);
					usuarioAutorizado = true;
					
				} else if (TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario() == argumetos.getIdTipoPropietario()) {
					CompanyBusDelegate services = CompanyBusDelegateImpl.getInstance();	
					services.activarEmpresa(idPropietario);
					usuarioAutorizado = true;
				}
				
				logger.info("Se confirma el registro para el usuario con Correo:"+ username +", usuario autorizado : "+ usuarioAutorizado);
				
				if(usuarioAutorizado){
					logger.info("Usuario autorizado ("+ username +"), se firma dentro del Portal");
					
					LoginAction loginAction = new LoginAction();
					
					SecutityDelegate services = SecutityDelegateImpl.getInstance();
					UsuarioVO usuario = services.consultaUsuarioPorLogin(username);

					if (loginAction.validatePassword(usuario.getContrasena(), contrasena)){
						loginAction.firmaUsuarioPortal(request, response, usuario);
					
						// Establece la ruta para direccionamiento dependiendo el perfil del usuario
						ActionForward forward = loginAction.redirectSWB(usuario.getIdPerfil(), mapping, request, response, username);
						forward = mapping.findForward("ACTION_GRACIAS"); // En lugar de direccionar al Espacio del usuario, se envia a la pagina de agradecimiento
						return forward;					
					}else{
						logger.error("Password incorrecto !!!");
						registraError(request, "seguridad.login.msg.pwd.no");
					}
				} else {
					logger.info("Usuario NO autorizado ("+ username +"), se direcciona a pagina de error");
				}
				
			}else{
				registraError(request, "seguridad.confirm.msg.noparams");
			}
		} catch (BusinessException e) {
			e.printStackTrace(); logger.error(e);
			registraError(request, "notificaciones.exception.BusinessException", e.getMessage());
		} catch (ServiceLocatorException e) {
			e.printStackTrace();  logger.error(e);
			registraError(request, "notificaciones.exception.ServiceLocatorException");
		} catch (Exception e) {	
			e.printStackTrace();  logger.error(e);
			registraError(request, "notificaciones.exception.Exception");
		}

		logger.error("El usuario no se pudo Firmar dentro del Portal del Empleo");
		if (argumetos!=null){
			envioUrl(confirmacionRegistroForm, argumetos);
		}
		
		request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
		return mapping.findForward(FORWARD_TEMPLATE_CAND);
	}

	/** Seteando valores a un ConfirmacionRegistroVo
	 * @param argumentos
	 * @return
	 * @throws BusinessException
	 */
	private ConfirmacionRegistroVo getArgumentos(String argumentos) throws Exception, BusinessException {
		ConfirmacionRegistroVo confirmacionRegistroVo = new ConfirmacionRegistroVo();
		String[] arg = argumentos.split(SEPARADOR_PARAM);

		if (tamanoArreglo(arg) && sonNumero(arg[0], arg[1])) {
			confirmacionRegistroVo.setIdPropietario(Utils.parseLong(arg[0]));
			confirmacionRegistroVo.setIdTipoPropietario(Utils.parseInt(arg[1]));
			confirmacionRegistroVo.setCorreo(arg[2]);
			confirmacionRegistroVo.setPassword(arg[3]);

			if (arg.length>=5)
				confirmacionRegistroVo.setUsuario(arg[4]);
		}

		correctoTipo(confirmacionRegistroVo.getIdTipoPropietario());

		return confirmacionRegistroVo;
	}

	/** Validando el numero de argumentos
	 * @param arg
	 * @return
	 * @throws BusinessException
	 */
	private Boolean tamanoArreglo(String[] arg) throws BusinessException {
		Boolean bandera = false;

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
	private Boolean sonNumero(String id, String tipo) throws BusinessException {
		Boolean bandera = false;
		if (Utils.esNumero(id) && Utils.esNumero(tipo)) {
			bandera = true;
		} else {
			throw new BusinessException("Los valor id y/o tipo no son valores numericos");
		}

		return bandera;
	}

	/** Validamos que los valores sean de tipo CANDIDATO o EMPRESA
	 * @param idTipoPropietario
	 * @param tipo
	 * @return
	 * @throws BusinessException
	 */
	private Boolean correctoTipo(int idTipoPropietario) throws BusinessException {
		Boolean bandera = false;
		if (TIPO_PROPIETARIO.CANDIDATO.getIdTipoPropietario() == idTipoPropietario || TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario() == idTipoPropietario) {
			bandera = true;
		} else {
			throw new BusinessException("El tipo es distinto a CANDIDATO o  EMPRESAS");
		}
		return bandera;
	}

	/** Armando la Url con los valores para el Login
	 * @param confirmacionRegistroVo
	 * @return
	 */
	/*private String getUrlLogin(ConfirmacionRegistroVo confirmacionRegistroVo){
		StringBuilder sb = new StringBuilder();
		sb.append("&isHome=true");
		sb.append("&username=" + confirmacionRegistroVo.getCorreo());
		sb.append("&password=" + confirmacionRegistroVo.getPassword());		
			
		if (TIPO_PROPIETARIO.CANDIDATO.getIdTipoPropietario() == confirmacionRegistroVo.getIdTipoPropietario()) 
			sb.append("&login_page=cand");	
			
		if	(TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario() == confirmacionRegistroVo.getIdTipoPropietario())
			sb.append("&login_page=emp");			

		return sb.toString();
	}*/
	
	/** Setiando valores para uso de las Url para sesion y registro
	 * @param confirmacionRegistroForm
	 * @param confirmacionRegistroVo
	 * @param request
	 */
	private void envioUrl(ConfirmacionRegistroForm confirmacionRegistroForm, ConfirmacionRegistroVo confirmacionRegistroVo){
		PropertiesLoader properties = PropertiesLoader.getInstance();
			
		confirmacionRegistroForm.setHome(properties.getProperty("app.swb.redirect.home"));		
		
		if (TIPO_PROPIETARIO.CANDIDATO.getIdTipoPropietario() == confirmacionRegistroVo.getIdTipoPropietario()) {
			confirmacionRegistroForm.setUrlInicioSesion(properties.getProperty("app.swb.redirect.registro.candidato"));
			confirmacionRegistroForm.setUrlRegistro(properties.getProperty("app.swb.redirect.registro.candidato"));
		}
		
		if (TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario() == confirmacionRegistroVo.getIdTipoPropietario()) {
			confirmacionRegistroForm.setUrlInicioSesion(properties.getProperty("app.swb.redirect.registro.empresa"));
			confirmacionRegistroForm.setUrlRegistro(properties.getProperty("app.swb.redirect.registro.empresa"));
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
