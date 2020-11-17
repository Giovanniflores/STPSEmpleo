package mx.gob.stps.portal.movil.web.infra.utils;

import mx.gob.stps.portal.utils.ConstantesGenerales.CONOCE_CURP;

/**
 * Clase para constantes Web del sistema
 * 
 * @author oscar.manzo
 */
public final class ConstantesMovil {

	private ConstantesMovil() {}
	
	public static final String formatDate 		= "dd/MM/yyyy";

	/** Nombre de archivo de propiedades de la aplicacion **/
	public static final String APPLICATION_PROPERTIES = "portal-web-movil.properties";
	
	public static final String APPLICATION_MESSAGES = "messages-movil.properties";
	
	/** Nombre del forward que direcciona a la pagina principal **/
	public static final String FORWARD_JSP = "JSP";

	/** Nombre del forward que direcciona a la pagina de Inicio **/
	public static final String FORWARD_HOME = "HOME";

	/** Identificador de Usuario en session **/
	public static final String USERLOGGED = "USERLOGGED";
	
	/** Identificador de la cookie para el usuario firmado **/
	public static final String COOKIE_USUARIO = "USERLOGGED";	

	public static final String USER_MENU = "USER_MENU";
	
	/** Nombre de la pagina utilizada como plantilla para pantalla de administrador **/
	public static final String FORWARD_TEMPLATE = "TEMPLATE";
	
	/** Ruta imagen por defecto empresa **/
	public static final String RUTA_IMAGEN_POR_DEFECTO_EMPRESA = "images/img_no_disponible_gde.jpg";
	/** Ruta imagen por defecto candidato **/
	public static final String RUTA_IMAGEN_POR_DEFECTO_CANDIDATO = "images/img_no_disponible_gde.jpg";

	/** Identificador candidato **/
	public static final String ID_CANDIDATO = "ID_CANDIDATO";
	
	/** Identificador empresa **/
	public static final String ID_EMPRESA = "ID_EMPRESA";

	public final static String PARAM_ENTREVISTA_MENSAJE = "ENTREVISTA_MENSAJE";
	
	public enum TIPOACTION {
		
		LISTAOFERTAS(1,"ListaOfertas"),
		DETALLEOFERTA(2,"DetalleOferta"),
		CANDIDATOSPOSTULADO(3,"CandidatoPostulados"),
		CANDIDATOSRELACIONADOS(4,"CandidatosRelacionados"),
		CANDIDATOSSELECIONADOSVINCULADOS(5,"CandidatosSelecionadosVinuculados"),
		/****/
		LISTACANDIDATOS(6, "ListaCandidatos"),
		DETALLECANDIDATO(7, "DetalleCandidato");


		private int idTipoAction;
		private String tipoAction;
		
		private TIPOACTION(int idTipoAction, String tipoAction){
			idTipoAction = idTipoAction;
			tipoAction = tipoAction;
		}
		
		public int getIdTipoAction(){
			return idTipoAction;
		}
	
		public String getTipoAction(){
			return tipoAction;
		}
		
		public String getTipoAction(int idTipoAction){
			String descripcion = null;			
			for (TIPOACTION tipo : TIPOACTION.values()){
				if (tipo.getIdTipoAction() == idTipoAction){
					descripcion = tipo.getTipoAction();
					break;
				}
			}
			return descripcion;
		}
	}

}