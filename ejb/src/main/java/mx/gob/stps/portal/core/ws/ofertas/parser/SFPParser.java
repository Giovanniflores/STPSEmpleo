package mx.gob.stps.portal.core.ws.ofertas.parser;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import mx.gob.stps.portal.core.domicilio.vo.DomicilioVO;
import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.core.infra.utils.Constantes.ETIQUETAS_SFP;
import mx.gob.stps.portal.core.oferta.registro.vo.OfertaCarreraEspecialidadVO;
import mx.gob.stps.portal.core.ws.ofertas.vo.OfertaEmpleoSFPVO;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;



public class SFPParser extends DefaultHandler {
	private int CONTADOR_ESPECIALIDAD = 0;
	OfertaEmpleoSFPVO vo = null;
	private List<OfertaEmpleoSFPVO> vacantes;
	private List<OfertaCarreraEspecialidadVO> especialidades;
	private OfertaCarreraEspecialidadVO especialidad;
	private DomicilioVO domicilio;
	private StringBuilder valor = null;
	private InputStream inputStream;
	private ETIQUETAS_SFP tags;
	private boolean principal = false;
	private int contador = 0;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	

	
	public SFPParser(byte[] in, Constantes.ETIQUETAS_SFP tags){
		this.inputStream = new ByteArrayInputStream(in);
		this.vacantes = new ArrayList<OfertaEmpleoSFPVO>();
		
		this.tags = tags;
	}

	public SFPParser(InputStream inputStream){
		this.inputStream = inputStream;
		this.vacantes = new ArrayList<OfertaEmpleoSFPVO>();
	}

	public List<OfertaEmpleoSFPVO> cargaVacantes() throws SAXException, ParserConfigurationException, IOException {
		SAXParserFactory parserFactory = SAXParserFactory.newInstance();
		SAXParser parser = null;
		parser = parserFactory.newSAXParser();
		parser.parse(inputStream, this);
		return vacantes;
	}
	
	@Override
	public void startDocument() throws SAXException {
		valor = new StringBuilder();
	}
	
	public void startElement (String uri, String localName, String qName, Attributes attributes) throws SAXException{
		if(qName.equalsIgnoreCase(tags.getTagRegistro())){
			vo = new OfertaEmpleoSFPVO();
			especialidad = new OfertaCarreraEspecialidadVO();
			especialidades = new ArrayList<OfertaCarreraEspecialidadVO>();
			domicilio = new DomicilioVO();
			vo.setDomicilio(domicilio);
			vo.setCarreras(especialidades);
			vacantes.add(vo);
			principal = false;
			CONTADOR_ESPECIALIDAD = 10;
		}
	}

	public void endElement (String uri, String localName, String qName) throws SAXException{
		String valorstr = valor.toString();
		if(qName.equalsIgnoreCase(tags.getTagVacante())){
			if(valorstr==null || valorstr.equals("") || valorstr.equals("null"))
				valorstr="0";
			vo.setIdOfertaBolsaSFP(Long.parseLong(valorstr));
		}else if(qName.equalsIgnoreCase(tags.getTagEmpresa())){
			if(valorstr==null || valorstr.equals("") || valorstr.equals("null"))
				valorstr="0";
				vo.setIdEmpresa(Long.parseLong(valorstr));
		}else if(qName.equalsIgnoreCase(tags.getTagPuesto())){
			vo.setTituloOferta(valorstr);
		}else if(qName.equalsIgnoreCase(tags.getTagOcupacion())){
			if(valorstr==null || valorstr.equals("") || valorstr.equals("null"))
				valorstr="0";
			vo.setIdOcupacion(Long.parseLong(valorstr));
			vo.setIdAreaLaboral(Long.parseLong(valorstr.substring(0,2)));
		}else if(qName.equalsIgnoreCase(tags.getTagFunciones())){
			vo.setFunciones(valorstr);
		}else if(qName.equalsIgnoreCase(tags.getTagSalario())){
			vo.setSalario(Double.parseDouble(valorstr));
		}else if(qName.equalsIgnoreCase(tags.getTagObservaciones())){
			vo.setObservaciones(valorstr);
		}else if(qName.equalsIgnoreCase(tags.getTagFechaAlta())){
			try {
				vo.setFechaAlta(sdf.parse(valorstr));
				especialidad.setFechaAlta(sdf.parse(valorstr));
				contador++;
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}else if(qName.equalsIgnoreCase(tags.getTagFechaVencimiento())){
			try {
				vo.setFechaFin(sdf.parse(valorstr));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}else if(qName.equalsIgnoreCase(tags.getTagEscolaridad())){
			if(valorstr==null || valorstr.equals("") || valorstr.equals("null"))
				valorstr="0";
			vo.setIdNivelEstudio(Long.parseLong(valorstr));
		}else if(qName.equalsIgnoreCase(tags.getTagSituacion())){
			if(valorstr==null || valorstr.equals("") || valorstr.equals("null"))
				valorstr="0";
			vo.setIdSituacionAcademica(Long.parseLong(valorstr));
		}else if(qName.equalsIgnoreCase(tags.getTagEspecialidad())){
			if(valorstr==null || valorstr.equals("") || valorstr.equals("null"))
				valorstr="0";
			especialidad.setId(Long.parseLong(valorstr));
			contador++;
			if (principal == false){
				especialidad.setPrincipal(Constantes.CARRERA_ESPECIALIDAD_PRINCIPAL);
				CONTADOR_ESPECIALIDAD = 2;
				contador =2;
				principal = true;
			}
		}else if(qName.equalsIgnoreCase(tags.getTagHabilidad())){
			vo.setHabilidadGeneral(valorstr);
		}else if(qName.equalsIgnoreCase(tags.getTagExperiencia())){
			if(valorstr==null || valorstr.equals("") || valorstr.equals("null"))
				valorstr="0";
			vo.setExperienciaAnios(Integer.parseInt(valorstr));
		}else if (qName.equalsIgnoreCase(tags.getTagEntidad())){
			if(valorstr==null || valorstr.equals("") || valorstr.equals("null"))
				valorstr="0";
			domicilio.setIdEntidad(Integer.parseInt(valorstr));
		}else if (qName.equalsIgnoreCase(tags.getTagMunicipio())){
			if(valorstr==null || valorstr.equals("") || valorstr.equals("null"))
				valorstr="0";
			domicilio.setIdMunicipio(Integer.parseInt(valorstr));
		}else if (qName.equalsIgnoreCase(tags.getTagCalle())){
			domicilio.setCalle(valorstr);
		}else if (qName.equalsIgnoreCase(tags.getTagCodigoPostal())){
			if(valorstr==null || valorstr.equals("") || valorstr.equals("null"))
				valorstr="0";
			domicilio.setCodigoPostal(valorstr);
		}else if (qName.equalsIgnoreCase(tags.getTagNumeroExt())){
			if(valorstr==null || valorstr.equals("") || valorstr.equals("null"))
				valorstr="0";
			domicilio.setNumeroExterior(valorstr);
		}
		if(contador == CONTADOR_ESPECIALIDAD){
			especialidades.add(especialidad);
			this.especialidad = new OfertaCarreraEspecialidadVO();
			especialidad.setFechaAlta(vo.getFechaAlta());
			contador = 0;
			CONTADOR_ESPECIALIDAD = 1;
		}
		valor = new StringBuilder();
		
	}
	
	public void characters(char buf[], int offset, int len)	throws SAXException{
		String valAux = new String(buf, offset, len); 
		if(valAux!=null){
			valor.append(valAux.trim());
		}
	}	

}
