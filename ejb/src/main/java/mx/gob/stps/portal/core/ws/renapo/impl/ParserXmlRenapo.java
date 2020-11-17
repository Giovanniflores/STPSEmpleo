package mx.gob.stps.portal.core.ws.renapo.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import mx.gob.stps.portal.core.candidate.vo.CandidatoVo;
import mx.gob.stps.portal.core.ws.renapo.help.CURPServiceHelper;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * @author jose.hernandez
 *
 */
public class ParserXmlRenapo extends DefaultHandler {
	
	
	public static final int CONSULTA_DATOS_PERSONALES 	= 1;
	public static final int CONSULTA_CURP			 	= 2;
	
	public static final int OPERACION_EXITOSA		 	= 1;
	public static final int OPERACION_NO_EXITOSA		= 2;
	
	public static final String EXITOSO					= "statusOper=\"EXITOSO\"";
	public static final String NO_EXITOSO				= "statusOper=\"NO EXITOSO\"";
	
	private StringBuilder valor 	= null;
	private InputStream inputStream = null;
	private CandidatoVo candidato 	= null;
	
	private	int tipoConsulta		= 0;
	private	int tipoOperacion		= 0;
	
	/**Construtor
	 * @param in
	 * @param consulta
	 * @param tipoOperacion
	 */
	public ParserXmlRenapo(byte[] in,int consulta,int tipoOperacion){
		this.tipoConsulta = consulta;
		this.tipoOperacion = tipoOperacion;
		this.inputStream  = new ByteArrayInputStream(in);
	}

	/** Construtor
	 * @param inputStream
	 * @param consulta
	 * @param tipoOperacion
	 */
	public ParserXmlRenapo(InputStream inputStream,int consulta,int tipoOperacion){
		this.inputStream = inputStream;
	}

	/** Otor los valores del Parseo
	 * @return
	 * @throws SAXException
	 * @throws ParserConfigurationException
	 * @throws IOException
	 */
	public CandidatoVo extraeCandidato() throws SAXException, ParserConfigurationException, IOException {

		SAXParserFactory parserFactory = SAXParserFactory.newInstance();
		SAXParser parser = parserFactory.newSAXParser();
		parser.parse(inputStream, this);

		return candidato ;
	}

	@Override
	public void startDocument() throws SAXException {
		candidato = new CandidatoVo();
		valor = new StringBuilder();
	}
	
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		
		if(CONSULTA_CURP == tipoConsulta && qName.equalsIgnoreCase("CURPStruct")) {	
				 candidato.setStatusOper(attributes.getValue("statusOper"));
				 candidato.setMessage(attributes.getValue("message"));
				 candidato.setTipoError(attributes.getValue("TipoError"));
				 candidato.setCodigoError(attributes.getValue("CodigoError"));	
		} 		
		if(CONSULTA_DATOS_PERSONALES == tipoConsulta && qName.equalsIgnoreCase("CURPCollection") && OPERACION_EXITOSA == tipoOperacion){	
				 candidato.setStatusOper(attributes.getValue("statusOper"));
				 candidato.setMessage(attributes.getValue("message"));
				 candidato.setTipoError(attributes.getValue("TipoError"));
				 candidato.setCodigoError(attributes.getValue("CodigoError"));			
		}
		if(CONSULTA_DATOS_PERSONALES == tipoConsulta && qName.equalsIgnoreCase("CURPStruct") && OPERACION_NO_EXITOSA == tipoOperacion ){
				 candidato.setStatusOper(attributes.getValue("statusOper"));
				 candidato.setMessage(attributes.getValue("message"));
				 candidato.setTipoError(attributes.getValue("TipoError"));
				 candidato.setCodigoError(attributes.getValue("CodigoError"));			
		}
	}

	public void characters(char buf[], int offset, int len) throws SAXException {
		String valAux = new String(buf, offset, len);
		
		if (valAux!=null)
			valor.append(valAux.trim());
	}
	
	public void endElement(String uri, String localName, String qName) throws SAXException {
		
		String valorstr = valor.toString();
		
		if (qName.equalsIgnoreCase("CURP")) {
			candidato.setCurp(valorstr);
		} else if (qName.equalsIgnoreCase("nombres")) {
			candidato.setNombre(valorstr);
		}else if (qName.equalsIgnoreCase("apellido1")) {
			candidato.setApellido1(valorstr);
		}else if (qName.equalsIgnoreCase("apellido2")) {
			candidato.setApellido2(valorstr);
		}else if (qName.equalsIgnoreCase("sexo")) {
			candidato.setGenero(valorstr.equals("H")?1:2);
		}else if (qName.equalsIgnoreCase("cveEntidadNac")) {
			candidato.setIdEntidadNacimiento(CURPServiceHelper.tipoEntidadFederativa(valorstr));
		}else if (qName.equalsIgnoreCase("fechNac")) {			
			candidato.setFechaNacimientoString(valorstr);
		}else if (qName.equalsIgnoreCase("statusCurp")) {			
			candidato.setEstatusCurp(valorstr);
		}
		
		valor = new StringBuilder();
	}

	
}