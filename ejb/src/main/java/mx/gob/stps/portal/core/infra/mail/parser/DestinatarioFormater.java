package mx.gob.stps.portal.core.infra.mail.parser;

import mx.gob.stps.portal.core.infra.mail.vo.DestinatarioVO;


/**
 * Clase DestinatarioXMLFormater
 * Crea la fuente de datos para la informacion proporcionada del Destinatario
 */
public class DestinatarioFormater {

	/** Parametros que son enviados a las plantillas,
	 * son utilizados para generar el XML de las plantillas HTML
	 * y para identificar los campos de la plantilla PDF */
	private static final String P_NOMBRE = "nombre";
	private static final String P_CARGO = "cargo";
	private static final String P_AREA = "area";
	private static final String P_FOLIO = "folio";
	private static final String P_FH_FOLIO = "fhFolio";
	private static final String P_SISTEMA = "sistema";
	private static final String P_LOGIN = "login";
	private static final String P_PASSW = "password";
	private static final String P_DIA = "dia";
	private static final String P_MES = "mes";
	private static final String P_ANIO = "anio";
	private static final String P_DATA_TABLE = "datatable";
	private static final String P_URL_ENCODE = "encode";
	private static final String P_URLCODE = "urlcode";
	private static final String P_MENSAJE = "mensaje";
	private static final String P_LOGO = "logo";
	
	private static final String P_IDCANDIDATO = "idCandidato";
	private static final String P_NOMBRECANDIDATO = "nombreCandidato";
	private static final String P_FECHAPOSTULACION = "fechaPostulacion";
	private static final String P_TITULOOFERTA = "tituloOfertaEmpleo";
	private static final String P_CURRICULUM = "curriculum";
	
	private static final String P_FECHA_INICIAL_REGISTRO = "fechaInicial";
	private static final String P_FECHA_FINAL_REGISTRO = "fechaActual";
	private static final String P_TOTAL_CANDIDATOS_REGISTRADOS = "totalCandidatos";
	
	private static final String P_IDPORTALEMPLEO = "idPortalEmpleo";
	
	private static final String P_ESTATUSOFERTACANDIDATO = "estatusOfertaCandidato";
	private static final String P_NOMBREEMPRESA = "nombreEmpresa";
	private static final String P_TIPOPERSONA = "tipoPersona";
	private static final String P_NOMBRECONTACTO = "nombreContacto";
	private static final String P_INSCRITOPPC = "inscritoPPC";
	
	private static final String P_AACUTE = "aacute";
	private static final String P_EACUTE = "eacute";
	private static final String P_IACUTE = "iacute";
	private static final String P_OACUTE = "oacute";
	private static final String P_UACUTE = "uacute";
	private static final String P_NTILDE = "ntilde";
	
	/**
	 * Genera la fuente de datos en formato XML para la extraccion de los mismos hacia la Plantilla
	 * @param destinatario Datos del destinatario
	 * @return
	 */
	public String formatDestinatario(DestinatarioVO destinatario){
		StringBuilder buf = new StringBuilder();
		buf.append("<?xml version='1.0' encoding='ISO-8859-1'?>");
		buf.append("<destinatario>");
		
		buf.append(createElement(P_NOMBRE, destinatario.getNombre()));
		buf.append(createElement(P_CARGO, destinatario.getCargo()));
		buf.append(createElement(P_AREA, destinatario.getArea()));
		buf.append(createElement(P_FOLIO, destinatario.getFolio()));
		buf.append(createElement(P_FH_FOLIO, destinatario.getFhFolio()));		
		buf.append(createElement(P_SISTEMA, destinatario.getSistema()));
		buf.append(createElement(P_LOGIN, destinatario.getLogin()));
		buf.append(createElement(P_PASSW, destinatario.getPassword()));
		buf.append(createElement(P_DIA, destinatario.getDia()));
		buf.append(createElement(P_MES, destinatario.getMes()));
		buf.append(createElement(P_ANIO, destinatario.getAnio()));
		buf.append(createElement(P_DATA_TABLE, destinatario.getDatatable()));		
		buf.append(createElement(P_URL_ENCODE, destinatario.getUrl()));
		buf.append(createElement(P_URLCODE, destinatario.getUrlcode()));
		buf.append(createElement(P_MENSAJE, destinatario.getMensaje()));	
		buf.append(createElement(P_LOGO, destinatario.getUrlLogo()));	
		
		buf.append(createElement(P_IDCANDIDATO, destinatario.getIdCandidato()));
		buf.append(createElement(P_NOMBRECANDIDATO, destinatario.getNombreCandidato()));
		buf.append(createElement(P_FECHAPOSTULACION, destinatario.getFechaPostulacion()));
		buf.append(createElement(P_TITULOOFERTA, destinatario.getTituloOfertaEmpleo()));
		buf.append(createElement(P_CURRICULUM, destinatario.getCurriculum()));
		
		buf.append(createElement(P_FECHA_INICIAL_REGISTRO, destinatario.getFechaInicialRegistroCandidato()));
		buf.append(createElement(P_FECHA_FINAL_REGISTRO, destinatario.getFechaFinalRegistroCandidato()));
		buf.append(createElement(P_TOTAL_CANDIDATOS_REGISTRADOS, destinatario.getTotalCandidatosRegistrados()));
		
		buf.append(createElement(P_IDPORTALEMPLEO, destinatario.getIdPortalEmpleo()));
		
		buf.append(createElement(P_ESTATUSOFERTACANDIDATO, destinatario.getEstatusOfertaCandidato()));
		buf.append(createElement(P_NOMBREEMPRESA, destinatario.getNombreEmpresa()));
		buf.append(createElement(P_TIPOPERSONA, destinatario.getTipoPersona()));
		buf.append(createElement(P_NOMBRECONTACTO, destinatario.getNombreContacto()));
		buf.append(createElement(P_INSCRITOPPC, destinatario.getInscritoPPC()));
	
		buf.append(createElement(P_AACUTE, destinatario.getAacute()));
		buf.append(createElement(P_EACUTE, destinatario.getEacute()));
		buf.append(createElement(P_IACUTE, destinatario.getIacute()));
		buf.append(createElement(P_OACUTE, destinatario.getOacute()));
		buf.append(createElement(P_UACUTE, destinatario.getUacute()));
		buf.append(createElement(P_NTILDE, destinatario.getNtilde()));
		buf.append("</destinatario>");
		return buf.toString();
	}
	
	/**
	 * Crea un elemento para el documento XML
	 * @param tagname Nombre de la etiqueta
	 * @param value Valor contenido en la etiqueta
	 * @return
	 */
	private String createElement(String tagname, String value){
		String tag = null;

		if (value!=null && !value.trim().isEmpty()){
			tag = "<"+ tagname +">"+ value +"</"+ tagname +">";
		}else{
			tag = "<"+ tagname +"/>";
		}
		
		return tag;
	}
	
}
