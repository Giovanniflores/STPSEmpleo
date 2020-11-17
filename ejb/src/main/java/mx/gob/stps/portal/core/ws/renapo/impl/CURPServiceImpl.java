package mx.gob.stps.portal.core.ws.renapo.impl;

import static mx.gob.stps.portal.core.ws.renapo.impl.ParserXmlRenapo.CONSULTA_CURP;
import static mx.gob.stps.portal.core.ws.renapo.impl.ParserXmlRenapo.CONSULTA_DATOS_PERSONALES;
import static mx.gob.stps.portal.core.ws.renapo.impl.ParserXmlRenapo.NO_EXITOSO;
import static mx.gob.stps.portal.core.ws.renapo.impl.ParserXmlRenapo.OPERACION_EXITOSA;
import static mx.gob.stps.portal.core.ws.renapo.impl.ParserXmlRenapo.OPERACION_NO_EXITOSA;
import mx.gob.stps.portal.core.candidate.vo.CandidatoVo;
import mx.gob.stps.portal.core.infra.utils.Utils;
import mx.gob.stps.portal.core.ws.renapo.CURPService;
import mx.gob.stps.portal.core.ws.renapo.help.CURPServiceHelper;

/**
 * @author jose.hernandez
 *
 */
public final class CURPServiceImpl implements CURPService {

	private CURPServiceImpl(){}
	
	public static CURPService getInstance(){
		return new CURPServiceImpl();
	}
	
	@Override
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
	
	@Override
	public synchronized CandidatoVo consultaDatosPersonalesPorCURP(String CURP, String targetEndpoint) throws Exception {
				
		ClienteConsultaPorCurp serv = ClienteConsultaPorCurp.getInstance();
		String resultadoXml = serv.consultaDatosPersonalesPorCURP(CURP, targetEndpoint);

		String resultadoUTF = new String(resultadoXml.getBytes(), "UTF-8");

		return parseoXml(resultadoUTF , CONSULTA_CURP);
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

	private String reemplazaCaracter(String cadena, char oldChar, char newChar){
		if (cadena==null) return null;
		
		cadena = cadena.replace(oldChar, newChar);
		
		return cadena;
	}

}