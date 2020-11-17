package mx.gob.stps.portal.core.infra.utils;

import static mx.gob.stps.portal.core.ws.renapo.impl.ParserXmlRenapo.CONSULTA_DATOS_PERSONALES;
import static mx.gob.stps.portal.core.ws.renapo.impl.ParserXmlRenapo.NO_EXITOSO;
import static mx.gob.stps.portal.core.ws.renapo.impl.ParserXmlRenapo.OPERACION_EXITOSA;
import static mx.gob.stps.portal.core.ws.renapo.impl.ParserXmlRenapo.OPERACION_NO_EXITOSA;

import java.util.Date;
import java.util.Properties;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import mx.gob.stps.portal.core.candidate.service.CandidatoRegistroAppServiceRemote;
import mx.gob.stps.portal.core.candidate.vo.CandidatoVo;
import mx.gob.stps.portal.core.ws.renapo.exception.ConsultaWsPorCurpException;
import mx.gob.stps.portal.core.ws.renapo.help.CURPServiceHelper;
import mx.gob.stps.portal.core.ws.renapo.impl.ClienteDetalleCurp;
import mx.gob.stps.portal.core.ws.renapo.impl.ParserXmlRenapo;
import mx.gob.stps.portal.utils.Catalogos;

public final class CurpServiceLocator {

	private static final String JNDI_EJB_CANDIDATOS_REGISTRO = "CandidatoRegistroAppService#mx.gob.stps.portal.core.candidate.service.CandidatoRegistroAppServiceRemote";
	
	private CurpServiceLocator(){}
	
	public static final CurpServiceLocator getInstance(){
		return new CurpServiceLocator();
	}
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public synchronized CandidatoVo consultaCURPPorDatosPersonales(CandidatoVo candidatoVo, String targetEndpoint) throws Exception {
		covertirDatosRenapo(candidatoVo);
		
		ClienteDetalleCurp serv = ClienteDetalleCurp.getInstance();
		String resultadoXml = serv.consultaCURPPorDatosPersonales(candidatoVo.getNombre(), 
																  candidatoVo.getApellido1(), 
																  candidatoVo.getApellido2(),
																  candidatoVo.getFechaNacimientoString(),
																  candidatoVo.getIdEntidadNacimientoString(), 
																  candidatoVo.getGeneroString(),
																  targetEndpoint);

		String resultadoUTF = new String(resultadoXml.getBytes(), "UTF-8");
		
		return parseoXml(resultadoUTF , CONSULTA_DATOS_PERSONALES);
	}
	
	/** Convertinos los valores necesarios para Renapo
	 * @param candidatoVo
	 * @return
	 */
	private CandidatoVo covertirDatosRenapo(CandidatoVo candidatoVo){		
		candidatoVo.setFechaNacimientoString(Utils.getFechaFormato(candidatoVo.getFechaNacimiento()));
		candidatoVo.setGeneroString(CURPServiceHelper.getIdGeneroRenapo(candidatoVo.getGenero()));
		candidatoVo.setIdEntidadNacimientoString(CURPServiceHelper.getIdEntidadRenapo(candidatoVo.getIdEntidadNacimiento()));
		return candidatoVo;		
	}
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public CandidatoVo consultaCURPPorDatosPersonales(String nombre, String apellido1, String apellido2, int genero, Date fechaNacimiento, int idEntidadNacimiento) throws ConsultaWsPorCurpException {
		CandidatoVo candidatoVo = new CandidatoVo();
		candidatoVo.setNombre(nombre);
		candidatoVo.setApellido1(apellido1);
		candidatoVo.setApellido2(apellido2);
		candidatoVo.setGenero(genero);
		
		if(genero == Catalogos.GENERO.FEMENINO.getIdOpcion()){
			candidatoVo.setGeneroString("M");
			
		} else if(genero == Catalogos.GENERO.MASCULINO.getIdOpcion()) {			
			candidatoVo.setGeneroString("H");
		}		
		
		candidatoVo.setFechaNacimiento(fechaNacimiento);
		candidatoVo.setFechaNacimientoString(Utils.getFechaFormato(fechaNacimiento));
		
		candidatoVo.setIdEntidadNacimiento(idEntidadNacimiento);
		candidatoVo.setIdEntidadNacimientoString(String.valueOf(idEntidadNacimiento));
		
		return getCurpServiceRemote().consultaCURPPorDatosPersonales(candidatoVo);
	}
	

	public CandidatoVo consultaDatosPersonalesPorCURP(String CURP) throws ConsultaWsPorCurpException {
		return getCurpServiceRemote().consultaDatosPersonalesPorCURP(CURP);
	}
	

	private static CandidatoRegistroAppServiceRemote getCurpServiceRemote() {
		CandidatoRegistroAppServiceRemote object = null;
		
		try{
			object = (CandidatoRegistroAppServiceRemote)getInitialContext().lookup(JNDI_EJB_CANDIDATOS_REGISTRO);			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return object;
	}
	

	private static Context getInitialContext() throws NamingException {
		Context ctx = null;
		ctx = new InitialContext(getProperties()); // REMOTO
		//ctx = new InitialContext(); // LOCAL
		return ctx;		
	}

	private static Properties getProperties() {

		String ip = "172.18.28.1:9020";

		Properties props = new Properties();
		props.put(javax.naming.Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
		props.put(javax.naming.Context.PROVIDER_URL, "t3://"+ ip);
		//props.put("java.naming.security.principal", properties.PRINCIPAL);
		//props.put("java.naming.security.credentials", properties.CREDENTIALS);
		return props;
	}	
	
	
	/** Paseando los valores de xml a CandidatoVo
	 * @param resultato
	 * @return CandidatoVo
	 */
	private CandidatoVo parseoXml(String resultato,int operacion)throws Exception{

		ParserXmlRenapo parserXML = new ParserXmlRenapo(resultato.getBytes(),operacion,tipoOperacion(resultato));			
		
		CandidatoVo vo = parserXML.extraeCandidato();

		if (vo!=null){
			vo.setNombre   (reemplazaCaracter(vo.getNombre(),    '?', 'Ñ'));
			vo.setApellido1(reemplazaCaracter(vo.getApellido1(), '?', 'Ñ'));
			vo.setApellido2(reemplazaCaracter(vo.getApellido2(), '?', 'Ñ'));
		}
		
		return vo;
	}	
	

	private String reemplazaCaracter(String cadena, char oldChar, char newChar){
		if (cadena==null) return null;
		
		cadena = cadena.replace(oldChar, newChar);
		
		return cadena;
	}
	

	/** Verificado si el parseo envia altun tipo de error
	 * @param cadenaXml
	 * @return
	 */
	private static Integer tipoOperacion(String cadenaXml){		
		if(cadenaXml.contains(NO_EXITOSO)){
			return OPERACION_NO_EXITOSA;
		} else { 
			return OPERACION_EXITOSA;
		}
	}
}