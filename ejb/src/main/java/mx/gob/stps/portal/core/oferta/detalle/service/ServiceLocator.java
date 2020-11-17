package mx.gob.stps.portal.core.oferta.detalle.service;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * Class <b>ServiceLocator</b> Clase ServiceLocator permite obtener e
 * inicializar el servicio de contexto
 */
public final class ServiceLocator {

	/**
	 * Constructor <b>ServiceLocator</b>
	 */
	private ServiceLocator() {
	}

	/**
	 * Method <b>getSessionRemote</b> Obtiene la session
	 * 
	 * @param jndi
	 * @return object
	 * @throws ServiceLocatorException
	 *             excepcion mandada cuando el servicio no puede ser cargado
	 */
	public static Object getSessionRemote(String jndi)
			throws ServiceLocatorException {
		Object object = null;

		try {
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
			// ctx = new InitialContext(getProperties());
			ctx = new InitialContext();
		} catch (NamingException e) {
			e.printStackTrace();
			throw new ServiceLocatorException(e);
		}

		return ctx;
	}

	/*
	 * private static Properties getProperties() throws NamingException {
	 * Properties props = new Properties();
	 * props.put(javax.naming.Context.INITIAL_CONTEXT_FACTORY,
	 * "weblogic.jndi.WLInitialContextFactory");
	 * props.put(javax.naming.Context.PROVIDER_URL, "t3://localhost:7001");
	 * //props.put("java.naming.security.principal", properties.PRINCIPAL);
	 * //props.put("java.naming.security.credentials", properties.CREDENTIALS);
	 * return props; }
	 */
}