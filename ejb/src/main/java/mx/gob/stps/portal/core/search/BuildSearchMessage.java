package mx.gob.stps.portal.core.search;

import java.util.List;

public final class BuildSearchMessage {
	
	private static final int TERMINOS = 1;
	private static final int SINONIMOS_ABREVIACIONES = 2;
	private static final int CARRERAS = 3;
	private static final int OCUPACIONES = 4;
	private static final int CARRERAS_OCUPACIONES = 5;
	private static final int SIN_RESULTADOS = 6;
	private static final String CABECERA_LISTA = "<ul class=\"list-SearchMessage\">";
	private static final String PIE_LISTA = "</ul>";
	private static final String CABECERA_PANEL = "<div class=\"panel-SearchMessage\">";
	private static final String MSG_RESULTADOS_RELACIONADOS = "Resultados relacionados con: <br/>"; 
	
	/**
	 * Método encargado de obtener el mensaje de la busqueda hacia el indexador
	 * regresando un formato en html para su presentación
	 * @param searchMessage
	 * @return
	 */
	public static String getMessage(SearchMessage searchMessage){
		String resultado = "";
		
		if(searchMessage!=null){
			if(tieneElementos(searchMessage.getCarreras()) && tieneElementos(searchMessage.getOcupaciones())){
				resultado = buildMessage(CARRERAS_OCUPACIONES, searchMessage);
			}else if(tieneElementos(searchMessage.getCarreras())){
				resultado = buildMessage(CARRERAS, searchMessage);
			}else if(tieneElementos(searchMessage.getOcupaciones())){
				resultado = buildMessage(OCUPACIONES, searchMessage);
			}else if(tieneSinonimosAbrev(searchMessage.getSinonimosAbreviaturas(), searchMessage.getTerminos())){
				resultado = buildMessage(SINONIMOS_ABREVIACIONES, searchMessage);
			}else if(tieneElementos(searchMessage.getTerminos())){
				resultado = buildMessage(TERMINOS, searchMessage);
			}else{
				resultado = buildMessage(SIN_RESULTADOS, searchMessage);
			}
		}
		
		return resultado;
	}
	
	/**
	 * Método encargado de contruir el mensaje dependiendo de la opción solicitada
	 * @param opcion Tipo de opción para el retorno del tipo de mensaje
	 * @param searchMessage Objeto que contiene los datos de la busqueda hacia el indexador
	 * @return cadena que contiene el mensaje junto con la estructura de html para presentarlo en la vista
	 */
	private static String buildMessage(int opcion, SearchMessage searchMessage){
		StringBuilder sb = new StringBuilder();
		
		sb.append(CABECERA_PANEL);
		
		switch (opcion) {
		case TERMINOS:
			generarMensajeTerminos(searchMessage, sb);
			break;
		case SINONIMOS_ABREVIACIONES:
			generarMensajeResultados(searchMessage, sb);
			sb.append(MSG_RESULTADOS_RELACIONADOS);
			sb.append(obtenerTextoNegrita(" Sinónimos y Abreviaturas : "));
			generarLista(sb,searchMessage.getSinonimosAbreviaturas());
			break;
		case CARRERAS:
			generarMensajeResultados(searchMessage, sb);
			sb.append(MSG_RESULTADOS_RELACIONADOS);
			sb.append(obtenerTextoNegrita(" Carrera (s): "));
			generarLista(sb,searchMessage.getCarreras());
			break;
		case OCUPACIONES:
			generarMensajeResultados(searchMessage, sb);
			sb.append(MSG_RESULTADOS_RELACIONADOS);
			sb.append(obtenerTextoNegrita(" Ocupación (es): "));
			generarLista(sb,searchMessage.getOcupaciones());
			break;
		case CARRERAS_OCUPACIONES:
			generarMensajeResultados(searchMessage, sb);
			sb.append(MSG_RESULTADOS_RELACIONADOS);
			sb.append(obtenerTextoNegrita(" Carrera (s): "));
			generarLista(sb,searchMessage.getCarreras());
			sb.append(obtenerTextoNegrita(" Ocupación (es): "));
			generarLista(sb,searchMessage.getOcupaciones());
			break;

		default:
			sb.append("No se encontraron resultados para la búsqueda: " + 
						"<strong>" +searchMessage.getCadenaOriginal() + "</strong>");
		}
		
		sb.append("</div>");
		
		return sb.toString();
	}
	
	private static void generarMensajeTerminos(SearchMessage searchMessage,StringBuilder sb){
		if(searchMessage.isBusquedaOfertas()){
			if(searchMessage.getListResultInfoBO()!= null && searchMessage.getListResultInfoBO().size() >0){
				generarMensajeResultados(searchMessage, sb);
				sb.append(MSG_RESULTADOS_RELACIONADOS);
				sb.append(obtenerTextoNegrita(" Término (s): "));
				generarLista(sb, searchMessage.getTerminos());
			}else{
				generarMensajeSinResultados(searchMessage, sb);
			}
		}else{
			if(searchMessage.getListCandidatos()!= null && searchMessage.getListCandidatos().size() > 0){
				generarMensajeResultados(searchMessage, sb);
				sb.append(MSG_RESULTADOS_RELACIONADOS);
				sb.append(obtenerTextoNegrita(" Término (s): "));
				generarLista(sb, searchMessage.getTerminos());
			}else{
				generarMensajeSinResultados(searchMessage, sb);
			}
		}
	}
	
	private static void generarMensajeSinResultados(SearchMessage searchMessage,StringBuilder sb){
		sb.append("Su búsqueda de ofertas no puede ser realizada como la ingreso");
		generarLista(sb, searchMessage.getCadenaOriginal());
		sb.append("Favor de verificar e introducir su búsqueda sin caracteres especiales, números y signos de puntuación.");
	}
	
	/**
	 * Método encargado de agregar el conteo de resultados al mensaje
	 * @param searchMessage
	 * @param sb
	 */
	private static void generarMensajeResultados(SearchMessage searchMessage,StringBuilder sb){
		if(searchMessage.isBusquedaOfertas()){
			sb.append(obtenerTextoNegrita(String.valueOf(searchMessage.getTotalPlazasDisponibles())) + 
					" plazas disponibles de " + 
					obtenerTextoNegrita(String.valueOf(searchMessage.getListResultInfoBO()!= null ? searchMessage.getListResultInfoBO().size() : "0" )) + 
					" resultados encontrados para la búsqueda " +
					obtenerTextoNegrita(searchMessage.getCadenaOriginal()  != null ? searchMessage.getCadenaOriginal() : "" ));
			sb.append("<br/><br/>");
		}else{
			if(searchMessage.getListCandidatos()!= null && searchMessage.getListCandidatos().size() > 0){
				sb.append(obtenerTextoNegrita(String.valueOf(searchMessage.getListCandidatos()!= null ? searchMessage.getListCandidatos().size() : "0" )) +
						" resultados encontrados para la búsqueda " +
						obtenerTextoNegrita(searchMessage.getCadenaOriginal()  != null ? searchMessage.getCadenaOriginal() : "" ));
				sb.append("<br/><br/>");
			}
		}	
	}
	
	/**
	 * Método encargado de verificar la existencia de elementos dentro de una lista
	 * @param lista lista de elementos
	 * @return existencia de elementos
	 */
	private static boolean tieneElementos(List<String> lista){
		if(lista!= null && !lista.isEmpty())
			return true;
		else
			return false;
	}
	
	
	/**
	 * Método encargado de verificar si el mensaje tiene Sinonimos y Abreviaturas
	 * @param sinonimosAbreviaturas Sinonimos y Abreviaturas
	 * @param terminos Terminos
	 * @return existencia de elementos
	 */
	private static boolean tieneSinonimosAbrev(List<String> sinonimosAbreviaturas, List<String> terminos){
		boolean resultado = false;
		
		if(tieneElementos(sinonimosAbreviaturas)){
			if(tieneElementos(terminos)){
				if(sinonimosAbreviaturas.size() != terminos.size())
					resultado = true;
			}else
				resultado = true;
		}
		
		return resultado;
	}
	
	
	/**
	 * Método encargado generar una estructura en html de una lista
	 * @param sb contructor del mensaje
	 * @param lista lista de elementos
	 */
	private static void generarLista(StringBuilder sb, List<String> lista){
		sb.append(CABECERA_LISTA);
		for (String elemento : lista) {
			sb.append("<li>" + elemento + "</li>");
		}
		sb.append(PIE_LISTA);
	}
	
	/**
	 * Método encargado generar una estructura en html de una lista
	 * @param sb constructor del mensaje
	 * @param elemento
	 */
	private static void generarLista(StringBuilder sb, String elemento){
		sb.append(CABECERA_LISTA);
			sb.append("<li>" + obtenerTextoNegrita(elemento) + "</li>");
		sb.append(PIE_LISTA);
	}
	
	/**
	 * Método para obtener texto en negrita
	 * @param texto
	 * @return
	 */
	private static String obtenerTextoNegrita(String texto){
		return "<strong>" +  texto + "</strong>";
	}

}
