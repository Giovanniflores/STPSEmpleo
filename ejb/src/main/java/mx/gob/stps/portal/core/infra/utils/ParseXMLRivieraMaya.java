package mx.gob.stps.portal.core.infra.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import mx.gob.stps.portal.core.oferta.vo.OfertaRivieraMayaVO;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


public class ParseXMLRivieraMaya {
	
	private static Logger logger = Logger.getLogger(ParseXMLRivieraMaya.class);

	
	
	public ParseXMLRivieraMaya() {};

	// convert from internal Java String format -> UTF-8
    public static String convertToUTF8(String s) {
        String out = null;
        try {
            out = new String(s.getBytes("UTF-8"), "ISO-8859-1");
        } catch (java.io.UnsupportedEncodingException e) {
            return null;
        }
        return out;
    }
	
	public List<OfertaRivieraMayaVO> obtenerOfertas(InputSource xmlOfertas) throws ParserConfigurationException, SAXException, IOException, TransformerConfigurationException, TransformerFactoryConfigurationError{

		List<OfertaRivieraMayaVO> ofertas = null;
//		
//		InputStream stream = null;
//		
//		stream = new ByteArrayInputStream(xmlOfertas.getBytes());
		
		
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(xmlOfertas);
		doc.getDocumentElement().normalize();

		logger.info("El elemento raíz del XML es: "	+ doc.getDocumentElement().getNodeName());
		
		NodeList listaPersonas = doc.getElementsByTagName("oferta");

		logger.info("Numero de ofertas en el XML: " + listaPersonas.getLength());
		
		ofertas = new ArrayList<OfertaRivieraMayaVO>();
		
		if(listaPersonas.getLength() != 0){
			
			OfertaRivieraMayaVO ofertaRivieraMayaVO = null;
			
			for (int i = 0; i < listaPersonas.getLength(); i++) {
					
					Node persona = listaPersonas.item(i);
					ofertaRivieraMayaVO = new OfertaRivieraMayaVO();
					
				if (persona.getNodeType() == Node.ELEMENT_NODE) {
					Element elemento = (Element) persona;
	
					ofertaRivieraMayaVO.setPrestaciones(getTagValue("prestaciones", elemento));
					ofertaRivieraMayaVO.setVigencia(getTagValue("vigencia", elemento));
					ofertaRivieraMayaVO.setGradoescolaridad(getTagValue("gradoescolaridad", elemento));
					ofertaRivieraMayaVO.setUbicacion(getTagValue("ubicacion", elemento));
					ofertaRivieraMayaVO.setConocimientos(getTagValue("conocimientos", elemento));
					ofertaRivieraMayaVO.setExperiencia(getTagValue("experiencia", elemento));
					ofertaRivieraMayaVO.setEstatus(getTagValue("estatus", elemento));
					ofertaRivieraMayaVO.setDescripcion(getTagValue("descripcion", elemento));
					ofertaRivieraMayaVO.setIdEempresa(Utils.parseLong(getTagValue("id_empresa", elemento)));
					ofertaRivieraMayaVO.setIdOferta(Utils.parseLong(getTagValue("id_oferta", elemento)));
					ofertaRivieraMayaVO.setSueldo(getTagValue("sueldo", elemento));
					ofertaRivieraMayaVO.setNombrepuesto(getTagValue("nombrepuesto", elemento));
					ofertaRivieraMayaVO.setNombreContacto(getTagValue("nombre_contacto", elemento));
					ofertaRivieraMayaVO.setNombre(getTagValue("nombre", elemento));
					ofertaRivieraMayaVO.setRazonSocial(getTagValue("razonsocial", elemento));
					ofertaRivieraMayaVO.setCorreoContacto(getTagValue("correo_contacto", elemento));
				}
				ofertas.add(ofertaRivieraMayaVO);
			}

		}
		
		logger.info("Termina proceso de parseo, numero de ofertas parseadas: " + ofertas.size());
		
		return ofertas;
	}

	private static String getTagValue(String sTag, Element eElement) {
		String valorNodo = null;
		NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();
		Node nValue = (Node) nlList.item(0);
		if(nValue == null)
			valorNodo = "";
		else
			valorNodo = nValue.getNodeValue();
		
		return valorNodo;
	}
}
