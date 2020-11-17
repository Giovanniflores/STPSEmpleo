package mx.gob.stps.portal.core.ws.ofertas.parser;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import mx.gob.stps.portal.core.infra.utils.Constantes.ETIQUETAS;
import mx.gob.stps.portal.core.ws.ofertas.vo.OfertaEmpleoOutVO;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;



public class OfertasEmpleoParser extends DefaultHandler {

	OfertaEmpleoOutVO vo = null;
	private List<OfertaEmpleoOutVO> vacantes;
	private StringBuilder valor = null;
	private InputStream inputStream;
	private ETIQUETAS tags;
	

	public OfertasEmpleoParser(byte[] in, ETIQUETAS tags){
		/*String str = null;
		try {
			str = new String(in, "ISO-8859-1");
			//str = new String(in, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		this.inputStream = new ByteArrayInputStream(str.getBytes());
		*/
		this.inputStream = new ByteArrayInputStream(in);
		this.vacantes = new ArrayList<OfertaEmpleoOutVO>();
		this.tags = tags;
	}

	public OfertasEmpleoParser(InputStream inputStream){
		this.inputStream = inputStream;
		this.vacantes = new ArrayList<OfertaEmpleoOutVO>();
	}

	public List<OfertaEmpleoOutVO> cargaVacantes() throws SAXException, ParserConfigurationException, IOException {
		SAXParserFactory parserFactory = SAXParserFactory.newInstance();
		SAXParser parser = null;
		parser = parserFactory.newSAXParser();
		/*
		Reader isr = new InputStreamReader(inputStream);
		InputSource is = new InputSource(isr);
		is.setEncoding("ISO-8859-2");
		//is.setEncoding("UTF-8");
		//is.setEncoding("UTF-8");
		parser.parse(is, this);
		 * */
		/*Reader isr = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
		InputSource is = new InputSource();
		is.setCharacterStream(isr);		
		parser.parse(is, this);
		*/		
		parser.parse(inputStream, this);
		return vacantes;
	}
	
	@Override
	public void startDocument() throws SAXException {
		valor = new StringBuilder();
	}
	
	public void startElement (String uri, String localName, String qName, Attributes attributes) throws SAXException{
		if(qName.equalsIgnoreCase(tags.getTagRegistro())){
			vo = new OfertaEmpleoOutVO();
			vacantes.add(vo);
		}
	}

	public void endElement (String uri, String localName, String qName) throws SAXException{
		String valorstr = valor.toString();
		//try {
			//valorstr = new String(valorstr.getBytes(), "ISO-8859-1");
			//valorstr = new String(valorstr.getBytes(), "UTF-8");
		//} catch (UnsupportedEncodingException e) {
		//	e.printStackTrace();
		//}
		
		if(qName.equalsIgnoreCase(tags.getTagEmpresa())){			
			vo.setEmpresa(valorstr);
		}else if(qName.equalsIgnoreCase(tags.getTagEstado())){			
			vo.setEstado(valorstr);
		} else if(qName.equalsIgnoreCase(tags.getTagFecha())){			
			vo.setFecha(valorstr);
		}else if(qName.equalsIgnoreCase(tags.getTagPuesto())){
			vo.setPuesto(valorstr);
		}else if(qName.equalsIgnoreCase(tags.getTagUrl())){			
			vo.setUrl(valorstr);
		}else if(qName.equalsIgnoreCase(tags.getTagDescripcion())){			
			vo.setDescripcion(valorstr);
		}else if(qName.equalsIgnoreCase(tags.getTagCiudad())){			
			vo.setCiudad(valorstr);
		}else if(qName.equalsIgnoreCase(tags.getTagidDiscapacidad())){
			vo.setIdDiscapacidad(valorstr);
		}else if(qName.equalsIgnoreCase(tags.getTagImagen())){
			String[] imagenes = valorstr.split(",");
			vo.setImagen(imagenes);
		}

		valor = new StringBuilder();
	}
	
	public void characters(char buf[], int offset, int len)	throws SAXException{			
		String valAux = new String(buf, offset, len); 
		//System.out.println("---OfertasEmpleoParser.characters.valAux:" +  valAux);	
		if(valAux!=null){
			valor.append(valAux.trim());
		}
	}	

}
