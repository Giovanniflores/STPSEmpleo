package mx.gob.stps.portal.movil.web.infra.service;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import mx.gob.stps.portal.movil.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.movil.web.infra.utils.PropertiesLoaderWeb;

public final class ServiceLocator {

	/**
	 * Constructor <b>ServiceLocator</b>
	 */
	private ServiceLocator() {}

	/**
	 * Method <b>getSessionRemote</b> Obtiene la session
	 * 
	 * @param jndi
	 * @return object
	 * @throws ServiceLocatorException
	 *             excepcion mandada cuando el servicio no puede ser caragdo
	 */
	public static Object getSessionRemote(String jndi) throws ServiceLocatorException {
		Object object = null;
		
		try{
			object = getInitialContext().lookup(jndi);
		} catch (NamingException e) {
			e.printStackTrace();
			throw new ServiceLocatorException(e);
		}

		return object;
	}

	/**
	 * Method <b>getInitialContext</b> Obtiene el contexto
	 * 
	 * @return Context
	 * @throws ServiceLocatorException
	 *             excepcion mandada cuando no se puede cargar el servicio
	 */
	private static Context getInitialContext() throws ServiceLocatorException {
		Context ctx = null;
		ctx = iniInitialContext(); // INICIALIZA EL CONTEXTO
		return ctx;
	}

	/**
	 * Method <b>iniInitialContext</b> Inicializa el contexto
	 * 
	 * @return
	 * @throws NamingException 
	 * @throws ServiceLocatorException
	 */
	private static Context iniInitialContext() throws ServiceLocatorException {
		Context ctx = null;

		try {
			//ESTE EN PRODUCCION
			//ctx = new InitialContext(getProperties());
			//ESTE EN QA
			ctx = new InitialContext();
		} catch (NamingException e) {
			e.printStackTrace();
			throw new ServiceLocatorException(e);
		}
		
		return ctx;
	}

	private static Properties getProperties() throws NamingException {
		Properties props = new Properties();
		String providerUrl = PropertiesLoaderWeb.getInstance().getProperty("service.locator.provider.url");
		props.put(javax.naming.Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
		props.put(javax.naming.Context.PROVIDER_URL, providerUrl);
		//props.put("java.naming.security.principal", properties.PRINCIPAL);
		//props.put("java.naming.security.credentials", properties.CREDENTIALS);
		return props;
	}

}
