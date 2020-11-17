package mx.gob.stps.portal.core.seguridad;

import java.io.Serializable;
import java.security.AccessControlException;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

import javax.ejb.Schedule;
import javax.ejb.Singleton;

import org.apache.log4j.Logger;

/**
 * Clase encargada de registrar el intento de accesos de login
 * a la aplicacacion, ademas de contar con una tarea programada
 * que se ejecuta cada minuto para ir eliminando registros 
 * del mapa que contiene la información de los ususarios logeados
 * @author Luis Mendez
 *
 */
@Singleton
public class RegistroUsuarioAplicacion implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(RegistroUsuarioAplicacion.class);
	
	/**
	 * Mapa de ususarios logeados
	 */
	private static final Map<String, Date> usuariosLogeados = new Hashtable<String, Date>();

	/**
	 * Metodo que sirve para registrar el intento de acceso a la aplicacion
	 * @param usuario es el usuario que intenta realizar un acceso a la aplacación
	 * @throws SecurityException se lanza cuando se inteta realizar un acceso con el mismo
	 * usuario en un lapso de no mas de dos segundos
	 */
	public static void registrarUsuarioLogeado(String usuario)throws SecurityException{
		synchronized (usuariosLogeados) {
			if(!usuariosLogeados.containsKey(usuario)){
				usuariosLogeados.put(usuario, Calendar.getInstance().getTime());
				logger.info("registrando al usuario: " + usuario);
			}else{
				//se revisa si el tiempo que lleva el usuario logeado.
				if(revisarSegundosUltimoAcceso(usuariosLogeados.get(usuario))<=2){
					logger.error("posiblemente se trata de un ataque, tiene menos de dos segundos que se inicio session");
					usuariosLogeados.put(usuario, Calendar.getInstance().getTime());
					throw new AccessControlException("Se intenta acceder al mismo recurso en "
							+ "un periodo no mas de dos segundos.");
				}else{
					usuariosLogeados.put(usuario, Calendar.getInstance().getTime());
				}
			}
		}
	}
	
	/**
	 * Metodo para eliminar un usario del mapa de usuarios registrados
	 * @param usuario usuario a eliminar del mapa
	 */
	public static void elimarRegistroUsuarioLogeado(String usuario){
		synchronized (usuariosLogeados) {
			if(usuariosLogeados.remove(usuario)!=null){
				logger.debug("eliminado inicio de session del usaurio: " + usuario);
			}
		}
	}
	
	/**
	 * Metodo para revisar la diferencia en segundos dada una fecha en comparacion
	 * de la fecha actual donde vive la aplicacion
	 * @param ultimoAcceso fecha con la que se desea comparar contra la fecha actual
	 * @return diferencia en segundos de la fecha actual contra la que se pasa como parametro a este metodo
	 */
	private static long revisarSegundosUltimoAcceso(Date ultimoAcceso){
		long segundosDiferencia = (Calendar.getInstance().getTimeInMillis() - ultimoAcceso.getTime())/1000;
		logger.info("tiene: "+ segundosDiferencia + "s que se intento logear el usuario");
		return segundosDiferencia;
	}
	
	/**
	 * Metodo calendarizado para que cada minuto, revise los registros que se encuentran en el 
	 * mapa y elimina los datos que ya son utiles para revisar los intentos de login. 
	 */
	@Schedule(second="0", minute="*", hour ="*", persistent=false )
	public void limpiarMapaAccesos(){
		logger.debug("realizando la limpieza del mapa de registro");
		logger.debug("Existen " + usuariosLogeados.size()+" registrados en el mapa" );
		int i = 0;
		Iterator<Map.Entry<String,Date>> iterator = usuariosLogeados.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<String,Date> entry = iterator.next();
			if(revisarSegundosUltimoAcceso(entry.getValue())>10){
				iterator.remove();
				i++;
			}
			
		}
		logger.debug("Se eliminaron: " + i + " usuarios del mapa");
		logger.debug("el tamaño del mapa es de: " + usuariosLogeados.size());
	}
}