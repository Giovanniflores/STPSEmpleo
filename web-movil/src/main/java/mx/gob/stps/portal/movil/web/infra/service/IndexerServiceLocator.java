package mx.gob.stps.portal.movil.web.infra.service;

import java.util.Properties;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import mx.gob.stps.portal.core.search.service.IndexadorServiceRemote;
import mx.gob.stps.portal.core.search.service.PortalEmpleoBuscadorServiceRemote;
import mx.gob.stps.portal.movil.web.infra.utils.PropertiesLoaderWeb;

public final class IndexerServiceLocator {
	 
	private static final String JNDI_EJB_WS_PORTAL_EMPLEO_BUSCADOR = "PortalIndexadorService#mx.gob.stps.portal.core.search.service.PortalEmpleoBuscadorServiceRemote";
	private static final String JNDI_EJB_WS_PORTAL_INDEXADOR = "PortalIndexadorService#mx.gob.stps.portal.core.search.service.IndexadorServiceRemote";	
	
	private IndexerServiceLocator(){}
	
	private static Context ctx = null;
	
	public static void CloseContext(){
		try {
			if(ctx != null){
				ctx.close();
			}
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	
	
	public static IndexadorServiceRemote getIndexadorService() {
		IndexadorServiceRemote object = null;
		
		try{
			System.out.println("+++++++++++++++++++++++++++++  INVOCACION REMOTA  ++++++++++++++++++++++++++++++++++++++++++++");
			
			object = (IndexadorServiceRemote)getInitialContext().lookup(JNDI_EJB_WS_PORTAL_INDEXADOR);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return object;
	}


	public static PortalEmpleoBuscadorServiceRemote getIndexadorServiceRemote() {
		PortalEmpleoBuscadorServiceRemote object = null;
		
		try{
			object = (PortalEmpleoBuscadorServiceRemote)getInitialContext().lookup(JNDI_EJB_WS_PORTAL_EMPLEO_BUSCADOR);			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return object;
	}

	private static Context getInitialContext() throws NamingException {
		
//		ctx = new InitialContext(getProperties()); // REMOTO
		ctx = new InitialContext(); // LOCAL
		return ctx;		
	}


	
	private static Properties getProperties() {
		String ip = PropertiesLoaderWeb.getInstance().getProperty("indexer.ip.remote.service");
		
		Properties props = new Properties();
		props.put(javax.naming.Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
		props.put(javax.naming.Context.PROVIDER_URL, "t3://"+ ip);
		//props.put("java.naming.security.principal", properties.PRINCIPAL);
		//props.put("java.naming.security.credentials", properties.CREDENTIALS);
		return props;
	}	
}