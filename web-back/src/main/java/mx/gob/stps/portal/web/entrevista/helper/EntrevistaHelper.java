package mx.gob.stps.portal.web.entrevista.helper;

import java.io.Serializable;

import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.web.entrevista.delegate.EntrevistaBusDelegate;
import mx.gob.stps.portal.web.entrevista.delegate.EntrevistaBusDelegateImpl;
import mx.gob.stps.portal.web.infra.utils.PropertiesLoader;
import mx.gob.stps.portal.web.infra.utils.Utils;


public class EntrevistaHelper implements Serializable {
	
	private static final long serialVersionUID = -5081098413300425750L;
	public static final String MENSAJE_SUCCESS 		= "El cambio del estatus de la Entrevista fue realizado con éxito.";
	public static final String MENSAJE_ERROR 		= "La transacción no pudo ser completada, favor de intentarlo nuevamente después de unos instantes";
	
	public static final String ICONO_ACEPTADA 		= "images/bt_aceptada.gif";
	public static final String ICONO_CANCELADA 		= "images/bt_cancelar.gif";
	public static final String ICONO_REPROGRAMADA 	= "images/bt_reprogramar.gif";
	public static final String ICONO_RECHAZADA		= "images/bt_rechazada.gif";
	public static final String ICONO_NUEVA			= "images/bt_nueva.gif";
	
	public static final String CLASS_TR_ODD			= "class='odd'";
	public static final String CLASS_TR_ODD_BLANCO	= "class='odd_Blanco'";
	
	
	public static final String ACEPTAR				= "Aceptada";
	public static final String REPROGRAMAR			= "Reprogramación";
	public static final String RECHAZAR				= "Rechazar";
	public static final String CANCELADA			= "Cancelada";
	public static final String NUEVA				= "Nueva";
	
	public static final	String EMPRESA				= "Empresa";
	public static final	String CANDIDATO			= "Candidato";
	
	public static final Integer ID_ACEPTAR			= Constantes.ESTATUS.ACEPTADA.getIdOpcion();
	public static final Integer ID_REPROGRAMADA		= Constantes.ESTATUS.REPROGRAMADA.getIdOpcion();
	public static final Integer ID_RECHAZADA		= Constantes.ESTATUS.RECHAZADA.getIdOpcion();
	public static final Integer ID_CANCELADA		= Constantes.ESTATUS.CANCELADA.getIdOpcion();
	public static final Integer ID_NUEVA			= Constantes.ESTATUS.NUEVA.getIdOpcion();	
	
	public static final Integer ID_ELIMINADA_EMP				= Constantes.ESTATUS.ELIMINADA_EMP.getIdOpcion();
	public static final Integer ID_ELIMINADA_ADMIN 				= Constantes.ESTATUS.ELIMINADA_ADMIN.getIdOpcion();
	public static final Integer ID_ELIMINADA_VIG 				= Constantes.ESTATUS.ELIMINADA_VIG.getIdOpcion();
	public static final Integer ID_PENDIENTE_PUBLICAR 			= Constantes.ESTATUS.PENDIENTE_PUBLICAR.getIdOpcion();
	public static final Integer	ID_ELIMINADA_EMP_FRAUDULENTA	= Constantes.ESTATUS.ELIMINADA_EMP_FRAUDULENTA.getIdOpcion();
	
	
	
	public static final	String CORREO_ASUNTO_REPROGRAMACION_ENTREVISTA	= "Portal del Empleo - Se ha reprogramado una entrevista";
	public static final	String CORREO_ASUNTO_RECHAZAR_ENTREVISTA		= "Portal del Empleo - Se ha rechazado una entrevista";
	public static final	String CORREO_ASUNTO_CANCELAR_ENTREVISTA		= "Portal del Empleo - Se ha cancelado una entrevista";
	public static final	String CORREO_ASUNTO_ACEPTADA_ENTREVISTA		= "Portal del Empleo - Se ha aceptado una entrevista";
	

	
	/**
	 * @param tipo
	 * @param estatus
	 * @param contador
	 * @return
	 */
	public static String getIcono(String tipo,Integer estatus,Integer contador){
		
		String urlIcono 	= "";
		String idOperacion 	= "";

	
			if(ID_ACEPTAR == estatus ){
				urlIcono = ICONO_ACEPTADA;
				idOperacion = ACEPTAR;
			}
		    if(ID_REPROGRAMADA == estatus ){
				urlIcono = ICONO_REPROGRAMADA;
				idOperacion = REPROGRAMAR;
			}
		    if(ID_CANCELADA == estatus ||
		    		ID_ELIMINADA_EMP == estatus ||
					  ID_ELIMINADA_ADMIN == estatus ||
						ID_ELIMINADA_VIG == estatus ||
							ID_PENDIENTE_PUBLICAR == estatus ||
								ID_ELIMINADA_EMP_FRAUDULENTA == estatus	){	
					urlIcono = ICONO_CANCELADA;
					idOperacion = CANCELADA;
			}
		    if(ID_RECHAZADA == estatus ){					
				urlIcono = ICONO_RECHAZADA;
				idOperacion = RECHAZAR;
			}
		    if(ID_NUEVA == estatus ){					
				urlIcono = ICONO_NUEVA;
				idOperacion = NUEVA;
			}
			return " <img  id = 'estatusIcono_" + contador + "' " +
					" name = 'estatusIcono' src='" + urlIcono + "'   " +
				    " title = '" + idOperacion + "'  > ";
	}
	
	/**
	 * @param status
	 * @param contador
	 * @param idEntrevista
	 * @param tipo
	 * @param nombre
	 * @param oferta
	 * @param fechaFin
	 * @param correoCandidato
	 * @param correoEmpresa
	 * @param razonSocial
	 * @return
	 */
	public static String getIconoOpciones(Integer status,Integer contador,
				long idEntrevista,String tipo,String nombre,String oferta,String fechaFin,
				String correoCandidato,String correoEmpresa,String razonSocial,String fecha,String hora,String candidato) {
			if(ID_ACEPTAR == status || 
					ID_CANCELADA == status  || 
						ID_RECHAZADA == status ||
							ID_ELIMINADA_EMP == status ||
								ID_ELIMINADA_ADMIN == status ||
									ID_ELIMINADA_VIG == status ||
										ID_PENDIENTE_PUBLICAR == status ||
											ID_ELIMINADA_EMP_FRAUDULENTA == status){
				return "";
			}
			
			StringBuilder sb = new StringBuilder();
				
				sb.append("<img  src=\"" + ICONO_ACEPTADA + "\" " +
							  "onClick= \"showDialogEntrevistaEnLinea(" +
							  	"'" + contador +
							  	  "','"+ idEntrevista + 
								  "','" + tipo + 
								  "','" + ACEPTAR + 
								  "','"+ nombre  +
								  "','"+ fechaFin  +
								  "','"+ correoCandidato  +
								  "','"+ correoEmpresa  +
								  "','"+ razonSocial  +
								  "','"+ candidato  +
								  "','"+ fecha  +
								  "','"+ hora  +
								  "','"+ oferta  + "')\"   title = '" + ACEPTAR + "' style='cursor: pointer'         /> ");
								  
								  
				sb.append("<img  src=\"" + ICONO_REPROGRAMADA + "\" " +
						"onClick= \"showDialogEntrevistaEnLinea(" +
						  	  	"'" + contador +
						  	  	  "','"+ idEntrevista + 
						  	  	  "','" + tipo + 
						  	  	  "','" + REPROGRAMAR + 
						  	  	  "','"+ nombre  + 
						  	  	  "','"+ fechaFin  +
						  	  	  "','"+ correoCandidato  +
						  	  	  "','"+ correoEmpresa  +
						  	  	  "','"+ razonSocial  +
						  	  	  "','"+ candidato  +
						  	  	  "','"+ fecha  +
						  	  	  "','"+ hora  +
						  	  	  "' ,'"+ oferta  + "')\"   title = '" + REPROGRAMAR + "' style='cursor: pointer'         /> ");
				
			  if(EMPRESA.equals(tipo)){
					sb.append("<img  src=\"" + ICONO_CANCELADA+ "\" " +
							  "onClick= \"showDialogEntrevistaEnLinea(" +
							  	"'" + contador +
							  	  "','"+ idEntrevista + 
								  "','" + tipo + 
								  "','" + CANCELADA + 
								  "','"+ nombre  + 
								  "','"+ fechaFin  +
								  "','"+ correoCandidato  +
								  "','"+ correoEmpresa  +
								  "','"+ razonSocial  +
								  "','"+ candidato  +
								  "','"+ fecha  +
								  "','"+ hora  +
								  "' ,'"+ oferta  + "')\"   title = '" + CANCELADA + "' style='cursor: pointer'         /> ");
				
				}if(CANDIDATO.equals(tipo)){
					
						sb.append("<img  src=\"" + ICONO_RECHAZADA + "\" " +
								  "onClick= \"showDialogEntrevistaEnLinea(" +
								  	  "'" + contador +
								  	  "','"+ idEntrevista + 
									  "','" + tipo + 
									  "','" + RECHAZAR + 
									  "','"+ nombre  +
									  "','"+ fechaFin  +
									  "','"+ correoCandidato  +
									  "','"+ correoEmpresa  +
									  "','"+ razonSocial  +
									  "','"+ candidato  +
									  "','"+ fecha  +
									  "','"+ hora  +
									  "' ,'"+ oferta  + "')\"   title = '" + RECHAZAR + "' style='cursor: pointer'         /> ");
			}		
		
		return sb.toString();
	}
	
	

	
	/**
	 * @param tipo
	 * @param nombre
	 * @param razonSocial
	 * @return
	 */
	public static String getTipoDato(String tipo,String nombre,String razonSocial){	
		if(EMPRESA.equals(tipo)){
			return nombre;
		} else{			
			return razonSocial;
		}
	}
	
	/**
	 * @param contador
	 * @return
	 */
	public static String getEstiloTr(Integer contador){
		if(contador%2 == 1)
			return CLASS_TR_ODD;
		else 
			return CLASS_TR_ODD_BLANCO;
	}
	
	/**
	 * @param fecha
	 * @param hora
	 * @return
	 */
	public static String  getMensaje1(String fecha,String hora){		
		StringBuilder sb = new StringBuilder();		
			sb.append("La entrevista ha sido reprogramada para el día " + fecha + " a las " + hora + " hrs. 							\n");
			sb.append("Esta información fue enviada al correo electrónico del candidato, favor de esperar la confirmación de la misma.  \n");
			sb.append(" Le recomendamos iniciar sesión en el Portal del Empleo 15 minutos antes de la entrevista. 						\n");
		return sb.toString();
	}
	
	/**
	 * @param empresa
	 * @param oferta
	 * @param fecha
	 * @param hora
	 * @return
	 */
	public static String  getMensaje2(String empresa,String oferta,String fecha,String hora){		
		StringBuilder sb = new StringBuilder();
			sb.append("La empresa " + empresa + " ha reprogramado la entrevista para la  oferta  de empleo 			      ");
			sb.append(oferta + " para el día " + fecha + " a las  " + hora + " hrs.\n	  							      ");
			sb.append("Favor de confirmar la entrevista.																\n");
			sb.append("Gracias.																							\n");
		return sb.toString();
	}
	
	
	/**
	 * @param oferta
	 * @param fecha
	 * @param hora
	 * @return
	 */
	public static String  getMensaje3(String oferta,String fecha,String hora){		
		StringBuilder sb = new StringBuilder();		
			sb.append("La solicitud de reprogramación de la entrevista de la oferta " + oferta);
			sb.append(" ha sido registrada para el día " + fecha + " a las " + hora + " hrs.\n");
		return sb.toString();
	}
	

	/**
	 * @param nombre
	 * @param oferta
	 * @param fecha
	 * @param hora
	 * @return
	 */
	public static String  getMensaje4(String nombre,String oferta,String fecha,String hora){		
		StringBuilder sb = new StringBuilder();		
			sb.append("El candidato " + nombre+ " solicita la reprogramación de la entrevista 					\n");
			sb.append("para la oferta  " + oferta + "  para el día "+ fecha + " a las " + hora + " hrs.			\n");
			sb.append("Favor de aceptar o rechazar la solicitud de reprogramación.								\n");
		return sb.toString();
	}
	
	/**
	 * @param nombre
	 * @param oferta
	 * @param fecha
	 * @param hora
	 * @return
	 */
	public static String  getMensaje6(String nombre,String oferta,String fecha,String hora){		
		StringBuilder sb = new StringBuilder();
			sb.append("El candidato " + nombre + " no aceptó la entrevista para la \n");
			sb.append("oferta " + oferta + "  para el día "+ fecha + " a las " + hora + " hrs.	\n");
		return sb.toString();
	}

	
	
	/**
	 * @param empresa
	 * @param oferta
	 * @param hora
	 * @return
	 */
	public static String  getMensaje8(String empresa,String oferta,String fecha,String hora){		
		StringBuilder sb = new StringBuilder();
			sb.append("La empresa " + empresa + " ha cancelado la entrevista para la oferta de empleo " + oferta);
			sb.append(" para el día " + fecha + " a las " + hora + " hrs.\n");
			sb.append("Gracias.	\n");
		return sb.toString();
	}	
	
	
	/**
	 * @param empresa
	 * @param oferta
	 * @param fecha
	 * @param hora
	 * @return
	 */
	public static String  getMensaje9(String empresa,String oferta,String fecha,String hora){		
		StringBuilder sb = new StringBuilder();			
			sb.append("La empresa " + empresa + " ha aceptado la entrevista para la oferta de empleo " + oferta);
			sb.append(" para el día " + fecha + " a las " + hora + "  hrs.");
			sb.append("Te recomendamos iniciar sesión en el Portal del Empleo 15 minutos antes de la entrevista.");
		return sb.toString();
	}	
	
	/**
	 * @param empresa
	 * @param oferta
	 * @param fecha
	 * @param hora
	 * @return
	 */
	public static String  getMensaje10(String nombre,String oferta,String fecha,String hora){		
		StringBuilder sb = new StringBuilder();					
			sb.append("El candidato " + nombre + " aceptó la entrevista para la oferta " + oferta);
			sb.append(" para el día " + fecha + " a las " + hora + " hrs.\n");
			sb.append(" Le recomendamos iniciar sesión en el Portal del Empleo 15 minutos antes de la entrevista.");
		return sb.toString();
	}	
	
	/** Validamos si la entrevista enta en tiempos
	 * @param idEntrevista
	 * @return
	 */
	public static Boolean validarEntrevistaEnLinea(String idEntrevista){
		try{
		Long idEntrevistaLong = Long.parseLong(idEntrevista);
		EntrevistaBusDelegate entrevistaBusDelegate = EntrevistaBusDelegateImpl.getInstance();		
			return entrevistaBusDelegate.validarEntrevistaEnLinea(idEntrevistaLong);
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/** Recortamos la cadena al tamaño adecuado 
	 * @param cadena
	 * @return
	 */
	public static String cadenaRecortada(String cadena){		
		if(cadena.length() > 16){			
			return Utils.limpiarAcentos(cadena.substring(0, 13) + "..." );
		} else {
			return Utils.limpiarAcentos(cadena);
		}
		
	}
	
	/** Nos otorga el valor de la propiedad
	 * @return
	 */
	public static String getContextoChat(){
		PropertiesLoader properties = PropertiesLoader.getInstance();		
		return properties.getProperty("app.swb.redirect.entrevista.chat");
	}	
	
	
}
