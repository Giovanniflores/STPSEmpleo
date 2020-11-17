package mx.gob.stps.portal.core.infra.mail.service;

import mx.gob.stps.portal.core.infra.mail.template.util.TemplateHTML;
import mx.gob.stps.portal.core.infra.mail.vo.DestinatarioVO;
import mx.gob.stps.portal.core.infra.utils.Constantes.Plantilla;

/**
 * Clase TemplateService
 * 
 * Funcionalidades para aplicar una plantilla
 * 
 * @author oscar.manzo
 *
 */
public class TemplateService {

	/**
	 * Coordina la aplicacion de la plantilla
	 * @param idGaceta
	 * @param destinatario
	 * @param idPlantilla
	 * @return
	 * @throws Exception
	 */
//	public byte[] aplicaPlantillaOficioPuestaCirculacionPDF(int idGaceta, DestinatarioVO destinatario, int idPlantilla) throws Exception {
//		TemplateHTML template = TemplateHTML.getIntance();
//		// AGREGA EL PREFIJO PARA EL NOMBRE DEL ARCHIVO A UTILIZAR
//		destinatario.setPrefijoNomArchivo(idGaceta +"_");
//		Plantilla plantilla = null;
//		
//		// TODO VERIFICAR PLANTILLA A UTILIZAR
//		/*if (idPlantilla == 1){
//			plantilla  = Plantilla.PDF_OFICIO_PUESTA_CIRCULACION_1;			
//		} else{
//			plantilla = Plantilla.PDF_OFICIO_PUESTA_CIRCULACION_1;			
//		}*/
//		
//		return aplicaPlantilla(template, destinatario, plantilla);
//	}

	/**
	 * Aplica la plantilla
	 * @param template Plantilla a aplicar
	 * @param destinatario datos para aplicar en la plantilla
	 * @param plantilla Tipo de plantilla
	 * @return datos de plantilla poblada
	 * @throws Exception
	 */
//	private byte[] aplicaPlantilla(TemplateHTML template, DestinatarioVO destinatario, Plantilla plantilla) throws Exception {
//		byte[] data = template.aplicaPlantilla(destinatario, plantilla);
//		return data;
//	}

}
