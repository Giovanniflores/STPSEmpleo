package mx.gob.stps.portal.core.ws.renapo.impl;

import javax.xml.namespace.QName;

import mx.gob.stps.portal.core.infra.utils.PropertiesLoader;

import org.apache.axis2.Constants;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;
import org.apache.axis2.transport.http.HTTPConstants;

public final class ClienteConsultaPorCurp {

	private final PropertiesLoader properties = PropertiesLoader.getInstance();
	
	private ClienteConsultaPorCurp(){}
	
	public static final ClienteConsultaPorCurp getInstance(){
		return new ClienteConsultaPorCurp();
	}

	public String consultaDatosPersonalesPorCURP(String CURP, String targetEndpoint) throws Exception {

		String result = null;
		RPCServiceClient serviceClient = null;
		
		try{
		
		System.setProperty("javax.net.ssl.trustStore", properties.getProperty("curpServices.keyStore"));
		System.setProperty("javax.net.ssl.trustStorePassword", properties.getProperty("curpServices.keyStore.password"));
		
		serviceClient = new RPCServiceClient();

		Options options = serviceClient.getOptions();

		EndpointReference targetEPR = new EndpointReference(targetEndpoint);

		options.setTo(targetEPR);
		options.setProperty(HTTPConstants.REUSE_HTTP_CLIENT, Constants.VALUE_TRUE);
		options.setProperty(HTTPConstants.CACHED_HTTP_CLIENT, serviceClient);
		
		DatosConsultaCurp datos = new DatosConsultaCurp();
		datos.setTipoTransaccion(1);
		datos.setUsuario(properties.getProperty("curpServices.user"));
		datos.setPassword(properties.getProperty("curpServices.password"));
		datos.setDireccionIp(properties.getProperty("curpServices.ipHomologada"));

		datos.setCveCurp(CURP);
		datos.setCveEntidadEmisora("1");

		QName opSetAlta = new QName("http://services.wserv.ecurp.dgti.segob.gob.mx", "consultarPorCurp"); // urn:ConsultaCurpService

		Object[] altaServiceArgs = new Object[] { datos };
		Class<?>[] returnTypes = new Class[] { String.class };

		Object[] response = serviceClient.invokeBlocking(opSetAlta, altaServiceArgs, returnTypes);

		result = (String) response[0];

		if (result == null) {
			System.out.println("Consulta Curp Service didn't initialize!");
			return null;
		}

		} catch (Exception e){
			e.printStackTrace();
		} finally {
			
			if (serviceClient != null) {
				try {
					serviceClient.cleanupTransport();
				} catch (Exception e) {e.printStackTrace();}
			
				try {
					serviceClient.cleanup();
				} catch (Exception e) {e.printStackTrace();}
			}
		}

		return result;
	}

}