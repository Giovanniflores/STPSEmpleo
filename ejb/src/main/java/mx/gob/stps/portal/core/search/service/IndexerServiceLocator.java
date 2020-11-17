package mx.gob.stps.portal.core.search.service;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import mx.gob.stps.portal.core.search.service.IndexadorServiceRemote;
import mx.gob.stps.portal.core.infra.utils.PropertiesLoader;

public final class IndexerServiceLocator {
	 
	private static final String JNDI_EJB_WS_PORTAL_EMPLEO_BUSCADOR = "PortalIndexadorService#mx.gob.stps.portal.core.search.service.PortalEmpleoBuscadorServiceRemote";
	
	private static final String JNDI_EJB_WS_PORTAL_EMPLEO_INDEXER = "PortalIndexadorService#mx.gob.stps.portal.core.search.service.IndexadorServiceRemote";
	
	private static final String JNDI_EJB_WS_PORTAL_EMPLEO_BUS_PARAMETRIZABLES = "SearchParameterService#mx.gob.stps.portal.core.search.service.IndexadorParametrizableServiceRemote";
	
	private IndexerServiceLocator(){}
	
	public static PortalEmpleoBuscadorServiceRemote getIndexadorServiceRemote() {
		PortalEmpleoBuscadorServiceRemote object = null;
		
		try{
			object = (PortalEmpleoBuscadorServiceRemote)getInitialContext().lookup(JNDI_EJB_WS_PORTAL_EMPLEO_BUSCADOR);			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return object;
	}	
	
	public static IndexadorServiceRemote getIndexerServiceRemote() {
		IndexadorServiceRemote object = null;
		
		try{
			object = (IndexadorServiceRemote)getInitialContext().lookup(JNDI_EJB_WS_PORTAL_EMPLEO_INDEXER);			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return object;
	}

	public static IndexadorParametrizableServiceRemote getIndexerParamServiceRemote() {
		IndexadorParametrizableServiceRemote object = null;
		
		try{
			object = (IndexadorParametrizableServiceRemote)getInitialContext().lookup(JNDI_EJB_WS_PORTAL_EMPLEO_BUS_PARAMETRIZABLES);			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return object;
	}
	
	private static Context getInitialContext() throws NamingException {
		Context ctx = null;
		//ctx = new InitialContext(getProperties()); // REMOTO
		ctx = new InitialContext(); // LOCAL
		return ctx;		
	}

	private static Properties getProperties() {
		PropertiesLoader properties = PropertiesLoader.getInstance();
		String ip = properties.getProperty("indexer.ip.remote.service");
		
		Properties props = new Properties();
		props.put(javax.naming.Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
		props.put(javax.naming.Context.PROVIDER_URL, "t3://"+ ip);
		//props.put("java.naming.security.principal", properties.PRINCIPAL);
		//props.put("java.naming.security.credentials", properties.CREDENTIALS);
		return props;
	}	
}