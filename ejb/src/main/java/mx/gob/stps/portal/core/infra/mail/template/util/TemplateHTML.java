package mx.gob.stps.portal.core.infra.mail.template.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import mx.gob.stps.portal.core.infra.mail.exception.TemplateException;
import mx.gob.stps.portal.core.infra.mail.parser.DestinatarioFormater;
import mx.gob.stps.portal.core.infra.mail.vo.DestinatarioVO;
import mx.gob.stps.portal.core.infra.utils.Constantes.Plantilla;
import mx.gob.stps.portal.core.infra.utils.Utils;

/**
 * Clase TemplateLoader
 * Contiene los metodos para la obtencion y aplicacion de la plantilla a la fuente de datos
 */
public class TemplateHTML {
	
	private TemplateHTML(){}
	
	public static TemplateHTML getIntance(){
		return new TemplateHTML();
	}
	
//	public byte[] aplicaPlantilla(DestinatarioVO destinatario, Plantilla plantilla) throws TemplateException {
//		byte[] data = null;
//		InputStream inTemplate = null;
//
//		try{
//			// obtiene el flujo hacia la plantilla
//			inTemplate = getStreamPlantilla(plantilla);
//
//			data = aplicaPlantilla(destinatario, inTemplate);
//		}catch (FileNotFoundException e) {
//			e.printStackTrace();
//			throw new TemplateException("Error al obtener la plantilla, no se localizo el archivo de la plantilla.", e);
//		} catch (IOException e) {
//			e.printStackTrace();
//			throw new TemplateException("Error al obtener la plantilla, error de carga de la plantilla.", e);
//		}
//		
//		return data;
//	}
	
	public byte[] aplicaPlantilla(DestinatarioVO destinatario, InputStream plantilla) throws TemplateException {
		byte[] data = null;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		TransformerFactory tFactory = TransformerFactory.newInstance();			
		ByteArrayInputStream inDatos = null;
		DestinatarioFormater formater = null;
		
		try {
			// Da formato a los datos del destinatario
			formater = new DestinatarioFormater();
			String datosDest = formater.formatDestinatario(destinatario);
			
			// Flujo de lectura a los datos del destinatario
			inDatos = new ByteArrayInputStream(datosDest.getBytes());

			// Realiza la transformacion de los datos a la plantilla
			Transformer transformer = tFactory.newTransformer(new StreamSource(plantilla));
			transformer.transform(new StreamSource(inDatos), new StreamResult(out));

			data = out.toByteArray();
		} catch (TransformerException e) {
			e.printStackTrace();
			throw new TemplateException("Error al aplicar la plantilla, problema de transformacion.",e);
		}

		return data;
	}

	/**
	 * Obtiene el flujo hacia el archivo de la Plantilla indicada
	 * @param plantilla Identificador de la plantilla a aplicar
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
//	private InputStream getStreamPlantilla(Plantilla plantilla) throws FileNotFoundException, IOException {
//		ByteArrayInputStream in = null;
//		InputStream inTemplate = null;
//		String plantillaPath = null;
//		
//		// TODO OBTENER RUTA HACIA LA UBIRACION DE LAS PLANTILLAS
//		//plantillaPath = APPLICATION_PATH + Constantes.RUTA_PLANTILLAS + prefijo + plantilla.getArchivoPlantilla();
//
//		File file = new File(plantillaPath);
//		inTemplate = new FileInputStream(file);
//		//inTemplate = this.getClass().getClassLoader().getResourceAsStream(APPLICATION_PATH + Constantes.CONTEXT_PATH + templateName);
//		
//		byte[] data = Utils.getBytes(inTemplate);
//		in = new ByteArrayInputStream(data);
//
//		return in;
//	}

}
