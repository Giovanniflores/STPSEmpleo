package mx.gob.stps.portal.web.security.listener;

import static mx.gob.stps.portal.core.infra.utils.Constantes.SESION_INACTIVA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.ACCIONES_REQ_AUTENTICACION;
import static mx.gob.stps.portal.web.infra.utils.Constantes.USUARIO_APP;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import mx.gob.stps.portal.core.infra.utils.Constantes.PERFIL;
import mx.gob.stps.portal.core.seguridad.RegistroUsuarioAplicacion;
import mx.gob.stps.portal.core.seguridad.vo.AccionVO;
import mx.gob.stps.portal.web.autorization.delegate.AutorizationBusDelegate;
import mx.gob.stps.portal.web.autorization.delegate.AutorizationBusDelegateImpl;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.security.delegate.SecutityDelegate;
import mx.gob.stps.portal.web.security.delegate.SecutityDelegateImpl;
import mx.gob.stps.portal.web.security.vo.UsuarioWebVO;

import org.apache.log4j.Logger;

/**
 * Se invocan los metodos correspondientes al crear y finalizar una session de usuario
 * 
 * @author oscar.manzo
 *
 */
public class SessionListener implements HttpSessionListener {

	private static Logger logger = Logger.getLogger(SessionListener.class);

	private static final String COUNT_SESSION = "COUNT_SESSION";
	
	public void sessionCreated(HttpSessionEvent event) {
		HttpSession session = event.getSession();
		//logger.info(" ***** Session creada ["+ session.getId() +"] ***** ");

		ServletContext context = session.getServletContext();
		Long countUsers = (Long)context.getAttribute(COUNT_SESSION);
		if (countUsers!=null) countUsers++; else countUsers = new Long(1);		
		context.setAttribute(COUNT_SESSION, countUsers);
		
		UsuarioWebVO usuario = (UsuarioWebVO)session.getAttribute(USUARIO_APP);

		/** Se consultan los accesos que requieren un usuario autenticado **/
		consultaAccionesRequierenAutenticacion(session);

		if (usuario==null) return;
		//logger.info("Inicia session de Usuario "+ usuario.getCorreoElectronico());
		
	}

	public void sessionDestroyed(HttpSessionEvent event) {
		HttpSession session = event.getSession();
		//logger.info(" ***** Session destruida ["+ session.getId() +"] ***** ");

		ServletContext context = session.getServletContext();
		Long countUsers = (Long)context.getAttribute(COUNT_SESSION);
		if (countUsers!=null) countUsers--; else countUsers = new Long(0);		
		context.setAttribute(COUNT_SESSION, countUsers);

		UsuarioWebVO usuario = (UsuarioWebVO)session.getAttribute(USUARIO_APP);

		if (usuario==null) return;

		inactivaSesionUsuario(usuario);
		
		//logger.info("Finaliza session de Usuario "+ usuario.getCorreoElectronico());

		// Para usuario con perfil Publicador
		if (usuario.getIdPerfil() == PERFIL.PUBLICADOR.getIdOpcion() ||
			usuario.getIdPerfil() == PERFIL.SUPERVISOR_SNETEL.getIdOpcion() ||
			usuario.getIdPerfil() == PERFIL.ADMINISTRADOR_SNETEL.getIdOpcion()){
			desasignaRegistrosPorValidar(usuario.getIdUsuario());
		}
		
		SecutityDelegateImpl.getInstance().finalizaSesion(usuario.getIdUsuario(), usuario.getIdPerfil());
		//Eliminar foto/logo del hash
		SecutityDelegateImpl.getInstance().eliminaImagen(Long.valueOf(usuario.getIdUsuario()).intValue());
		
		// Remueve usuario del mapa al cerrar la sesion
		RegistroUsuarioAplicacion.elimarRegistroUsuarioLogeado(usuario.getUsuario());
	}

	/**
	 * Invoca el proceso para desasignar los Registros por Validar del Publicador
	 * 
	 * @param idUsuario
	 */
	private void desasignaRegistrosPorValidar(long idUsuario){
		try{
			AutorizationBusDelegate services = AutorizationBusDelegateImpl.getInstance();

			services.desasignaRegistrosPorValidar(idUsuario);

		}catch(ServiceLocatorException e){
			logger.error(e);
		}catch(Exception e){
			logger.error(e);
		}
	}
	
	private void inactivaSesionUsuario(UsuarioWebVO usuario) {
		// Desactiva la sesión del usuario (SESION_ACTIVA)
		SecutityDelegate services = SecutityDelegateImpl.getInstance();
		try {
			services.actualizaSesionActiva(usuario.getIdUsuario(), SESION_INACTIVA);
		} catch (Exception e) {
			logger.error(e);
		}
	}

	private void consultaAccionesRequierenAutenticacion(HttpSession session){
		try {
			//logger.info("Se cargan permisos sobre recursos del sitio.");
			SecutityDelegate services = SecutityDelegateImpl.getInstance();
			List<AccionVO> acciones = services.consultaAccionesRequierenAutenticacion();

			List<String> accionesPath = new ArrayList<String>();
			
			if (acciones!=null && !acciones.isEmpty()){
				for (AccionVO accion : acciones){
					accionesPath.add(accion.getVinculo());
				}
			}

			session.setAttribute(ACCIONES_REQ_AUTENTICACION, accionesPath);
		} catch (Exception e) {logger.error(e);}

	}
}