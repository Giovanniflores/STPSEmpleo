package mx.gob.stps.portal.core.persistencia.facade;

import java.rmi.RemoteException;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.xml.rpc.ServiceException;
import mx.gob.stps.portal.core.ws.quejas.PortalEmpleoLocator;
import mx.gob.stps.portal.core.ws.quejas.PortalEmpleoSoap;
import mx.gob.stps.portal.persistencia.entity.SugerenciaPortal;


import org.apache.log4j.Logger;

import com.google.gson.Gson;

@Stateless
public class SugerenciaFacade implements SugerenciaFacadeLocal {

	@PersistenceContext
	private EntityManager entityManager;
	private String json;
	
	private static Logger logger = Logger.getLogger(SugerenciaFacade.class);

	@Override
	public long create(long idCategoria, String asunto, String mensaje, String email, String nombre, String apellido1, String apellido2, String telefono, int tipoTelefono) throws PersistenceException {
		int result = 1;
		java.util.Date fechaCreacion = new Date();
		try {
			String certificatesTrustStorePath = "/usr/java/jdk1.7.0_21/jre/lib/security/cacerts";
			System.setProperty("javax.net.ssl.trustStore", certificatesTrustStorePath);
			Integer Categoria = (int)(long)idCategoria;
			String[] parts = telefono.split(" ");
			String clave_lada2 = parts[0];
			String telefono2 = parts[1];
		
			
			QuejasGabssa gabssa = new QuejasGabssa("0",Categoria,asunto,mensaje,email,nombre,apellido1,apellido2,tipoTelefono,clave_lada2,telefono2);
			
			Gson gson = new Gson();
			json = gson.toJson(gabssa); 
			
			//result = envioJson(json);
			result = envioSoap(json);
			
			SugerenciaPortal entity = getEntity(fechaCreacion, idCategoria, asunto, mensaje, email, nombre, apellido1, apellido2, telefono, tipoTelefono,result);
			entityManager.persist(entity);
	
		}catch (Exception e) { result = -1; logger.error("Error al persistir sugerencia", e);logger.info("Error al persistir sugerencia", e); }
		
		return result;
	}
	
	private int envioSoap(String Json) {
		int result = 1;

		PortalEmpleoLocator serviceLocator = null;
		PortalEmpleoSoap soap = null;
		String actualizacionDatos;
		serviceLocator = new PortalEmpleoLocator();

		try {
			soap = serviceLocator.getPortalEmpleoSoap();

			actualizacionDatos = soap.enviodeQuejas(Json);
			if (actualizacionDatos != null) {

				QuejasGabssaLocal container = new Gson().fromJson(actualizacionDatos, QuejasGabssaLocal.class);
				result = container.getContacto_portal_id();
			}
			else
			{
				result = 0;
			}
		}

		catch (RemoteException e) {
			logger.error("Error en el envío RemoteException ", e);
			result = 0;
			e.printStackTrace();
		} catch (ServiceException e) {
			logger.error("Error en el envío ServiceException ", e);
			result = 0;
		}

		return result;
	}
	
	
	private SugerenciaPortal getEntity(Date fechaCreacion,long idCategoria, String asunto, String mensaje, String email, String nombre, String apellido1, String apellido2, String telefono, int tipoTelefono, int contacto_portal_id){
		SugerenciaPortal entity = new SugerenciaPortal();
		entity.setFechaCreacion(fechaCreacion);
		entity.setApellido1(apellido1);
		entity.setApellido2(apellido2);
		entity.setAsunto(asunto);
		entity.setEmail(email);
		entity.setIdCategoria(idCategoria);
		entity.setMensaje(mensaje);
		entity.setNombre(nombre);
		entity.setTelefono(telefono);
		entity.setTipoTelefono(tipoTelefono);
		entity.setContactoPortalId(contacto_portal_id);
		return entity;
	}
}