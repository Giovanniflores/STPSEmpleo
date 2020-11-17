package mx.gob.stps.portal.web.candidate.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Collections;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import mx.gob.stps.portal.core.candidate.vo.CandidatoVo;
import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO;
import mx.gob.stps.portal.persistencia.vo.HistoricoBusquedaPPCVO;
import mx.gob.stps.portal.utils.Catalogos;
import mx.gob.stps.portal.web.infra.action.PagerAction;
import mx.gob.stps.portal.web.infra.delegate.CatalogoOpcionDelegate;
import mx.gob.stps.portal.web.infra.delegate.CatalogoOpcionDelegateImpl;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.infra.utils.PropertiesLoader;
import mx.gob.stps.portal.web.infra.vo.CatalogoVO;
import mx.gob.stps.portal.web.candidate.delegate.AdministracionNoPostulacionesBusDelegate;
import mx.gob.stps.portal.web.candidate.delegate.AdministracionNoPostulacionesBusDelegateImpl;
import mx.gob.stps.portal.web.candidate.form.AdministracionNoPostulacionesForm;
import mx.gob.stps.portal.web.security.vo.UsuarioWebVO;
import static mx.gob.stps.portal.web.infra.utils.Constantes.BODY_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.DESCRIPCION_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FACEBOOK_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_TEMPLATE_MI_ESP_CAND;//32
import static mx.gob.stps.portal.web.infra.utils.Constantes.TITULO_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TWITTER_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.URL_ESPECIFICA;

/**********************************************************
//
// Nombre : OPR  Fecha:	8.10.14
// Action correspondiente al Modulo Administracion de No Postulaciones 
// para el seguro de desempleo
//
/************************************************************/
public class AdministracionNoPostulacionesAction extends PagerAction{
	private static Logger logger = Logger.getLogger(AdministracionNoPostulacionesAction.class);
    private SortField sortField;
    private SortType sortType;

    public enum SortField {
        PUESTO ("puesto"),
        EMPRESA ("empresa"),
        UBICACION ("ubicacion"),
        SALARIO("salario"),
        COMPATIBILIDAD ("compatibilidad"),
        MOTIVO ("motivo");

        private String fieldName;

        SortField(String fieldName) {
            this.fieldName = fieldName;
        }

        public String getFieldName() {
            return fieldName;
        }

        public static SortField sortFieldForName(String fieldNameStr) {
            for (SortField sortField : SortField.values()) {
                if (sortField.fieldName.equals(fieldNameStr)) {
                    return sortField;
                }
            }

            return null;
        }
    }//--23  //59

    public enum SortType {
        ASC ("asc"),
        DESC ("desc");

        private String type;

        SortType(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }

        public static SortType sortTypeForName(String typeStr) {
            for (SortType sortType : SortType.values()) {
                if (sortType.type.equals(typeStr)) {
                    return sortType;
                }
            }

            return null;
        }
    }//--19  //78
	
	@Override
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		AdministracionNoPostulacionesForm administracionNoPostulacionesForm;
		HttpSession 				 	  session 				   = request.getSession(true);
		List<HistoricoBusquedaPPCVO> 	  listaOfertasNoPostuladas = null;
		
		this.PAGE_NUM_ROW = 5;
		try{
			UsuarioWebVO usuario 			  = getUsuario(request.getSession());		
			CandidatoVo candidato 			  = getCandidato(usuario.getIdPropietario()); 
			administracionNoPostulacionesForm = (AdministracionNoPostulacionesForm) form;
			administracionNoPostulacionesForm.reset();
			session.removeAttribute(FULL_LIST);
			session.removeAttribute(PAGE_LIST);
			
			/* PRECONDICIONES  */
			if(candidato == null || candidato.getPpcEstatus() == 0 || candidato.getPpcEstatus() == Catalogos.ESTATUS.FUERA_PPC.getIdOpcion()){
				request.setAttribute("OFFER_NO_POST_LIST_SIZE", 0);
				session.setAttribute("CAT_MOTIVOS",null);
				
				administracionNoPostulacionesForm.setPrerrequisitos(false);
			} else{
				listaOfertasNoPostuladas = getListadoOfertasNoPostuladas(usuario.getIdPropietario());
				administracionNoPostulacionesForm.setAdministracionNoPostulacionesVO(listaOfertasNoPostuladas);	
				administracionNoPostulacionesForm.setPrerrequisitos(true);
				
				session.setAttribute(FULL_LIST, listaOfertasNoPostuladas);
				request.setAttribute("OFFER_NO_POST_LIST_SIZE", listaOfertasNoPostuladas.size());
				session.setAttribute("CAT_MOTIVOS",obtenerMotivosNoPostulaciones());
			}
			
		} catch (PersistenceException pe) { pe.printStackTrace();
		} catch (Exception te) { te.printStackTrace(); }
		
		request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());

		PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Administraci&oacute;n no postulaciones");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Administraci&oacute;n no postulaciones, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
		return mapping.findForward(FORWARD_TEMPLATE_MI_ESP_CAND);
	}//--30  //108
	
	/**********************************************************
	//
	// Nombre: OPR  Fecha:	13.10.14
	// Redirecciona a la pagina de miEspacioCandidato
	//
	/************************************************************/
	public ActionForward toMiEspacioCandidato(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		final String MI_ESPACIO_CANDIDATO = "MI_ESPACIO_CANDIDATO";
		return mapping.findForward(MI_ESPACIO_CANDIDATO);
	}//--4  //112
	
	/**********************************************************
	//
	// Nombre: OPR  Fecha:	13.10.14
	// Obtiene la informacion del Candidato a partir de su idCandidato
	//
	/************************************************************/
	private CandidatoVo getCandidato(long idCandidato){
		AdministracionNoPostulacionesBusDelegate services = AdministracionNoPostulacionesBusDelegateImpl.getInstance();
		CandidatoVo candidato = null;
		
		try{
			candidato = services.getCandidatoPorId(idCandidato);
		} catch(SQLException sqle){
			sqle.printStackTrace();
		} catch(ServiceLocatorException e){
			e.printStackTrace();
		}
		return candidato;
	}//--12 //--2+4+6+1  //124
	
	/**********************************************************
	//
	// Nombre: OPR  Fecha:	13.10.14
	// Obtiene una lista de ofertas no postuladas del candidato
	//
	/************************************************************/
	private List<HistoricoBusquedaPPCVO> getListadoOfertasNoPostuladas(long idCandidato){
		AdministracionNoPostulacionesBusDelegate services 			 = AdministracionNoPostulacionesBusDelegateImpl.getInstance(); 
		List<HistoricoBusquedaPPCVO> 			 ofertasNoPostuladas = null;
		
		try {
			ofertasNoPostuladas = services.getListadoOfertasNoPostuladas(idCandidato);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		}
		
		return ofertasNoPostuladas;
	}//--12  //136

	/**********************************************************
	//
	// Nombre: OPR  Fecha:	17.10.14
	// Obtiene una lista de ofertas no postuladas del candidato
	//
	/************************************************************/
	public ActionForward registrar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		AdministracionNoPostulacionesForm 		 administracionNoPostulacionesForm;
		String 							  		 descripcion;
		AdministracionNoPostulacionesBusDelegate services 			 	  = AdministracionNoPostulacionesBusDelegateImpl.getInstance(); 
		HttpSession 				 	  		 session 				  = request.getSession(true);
		UsuarioWebVO 							 usuario 				  = getUsuario(session);
		List<HistoricoBusquedaPPCVO> 			 listaOfertasNoPostuladas = null;
		this.PAGE_NUM_ROW = 5;
		
		try {
			administracionNoPostulacionesForm = (AdministracionNoPostulacionesForm) form;
			CandidatoVo candidato 			  = getCandidato(usuario.getIdPropietario()); 
			session.removeAttribute(FULL_LIST);
			session.removeAttribute(PAGE_LIST);
			
			if(administracionNoPostulacionesForm.getIdMotivo() == Catalogos.MOTIVOS_NO_POSTULACION_OFERTA.OTROS.getIdOpcion())
				descripcion = administracionNoPostulacionesForm.getMotivoDesc();
			else
				descripcion = Catalogos.MOTIVOS_NO_POSTULACION_OFERTA.getDescripcion((int) administracionNoPostulacionesForm.getIdMotivo());
			
			if(services.actualizarMotivoNoPostulacion(candidato.getIdCandidato(), usuario.getIdUsuario(), administracionNoPostulacionesForm.getIdOfertaEmpleo(), administracionNoPostulacionesForm.getIdMotivo(), 
					descripcion, Catalogos.CATALOGO_FUENTE_QUE_REGISTRA_OFERTA.PORTAL.getIdOpcion()))
				request.setAttribute("REGISTRO_MSG", getMensaje(request, "registro.candidato.NoPostulaciones.exito"));//getMensaje(request, "Los datos han sido guardados exitosamente."));registraMensaje(request, "messages");
			else
				request.setAttribute("REGISTRO_MSG", getMensaje(request, "registro.candidato.NoPostulaciones.error"));//registraMensaje(request, "registro.candidato.NoPostulaciones.error");
			
			administracionNoPostulacionesForm.reset();
			listaOfertasNoPostuladas = services.getListadoOfertasNoPostuladas(usuario.getIdPropietario());
			administracionNoPostulacionesForm.setAdministracionNoPostulacionesVO(listaOfertasNoPostuladas);	
			administracionNoPostulacionesForm.setPrerrequisitos(true);
			
			session.setAttribute(FULL_LIST, 				listaOfertasNoPostuladas);
			request.setAttribute("OFFER_NO_POST_LIST_SIZE", listaOfertasNoPostuladas.size());
			session.setAttribute("CAT_MOTIVOS", 			obtenerMotivosNoPostulaciones());
			
		} catch (SQLException e) {
			logger.info("Problema al actualizar registro: " + e.getMessage());
			e.printStackTrace();
		} catch (ServiceLocatorException e) {
			logger.info("No encuentra el recurso: " + e.getMessage());
			e.printStackTrace();
		}
		
		request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());

		PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Administraci&oacute;n no postulaciones");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Administraci&oacute;n no postulaciones, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
		return mapping.findForward(FORWARD_TEMPLATE_MI_ESP_CAND);
	}//--39  //175
	
	/**********************************************************
	//
	// Nombre: OPR  Fecha:	30.10.14
	// Metodo que se invoca desde la vista par ser mostrado en un combo
	//
	/************************************************************/
	public ActionForward motivosNoPostulacion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			List<CatalogoOpcionVO> opciones = obtenerMotivosNoPostulaciones();
			redirectJsonCatalogo(opciones, response);
		} catch (Exception e) {e.printStackTrace(); logger.error(e);}

		return null;
	}//--7  //182

	/**********************************************************
	//
	// Nombre: OPR  Fecha:	17.10.14
	// Obtiene el catalogo de motivos de no postulacion para las ofertas
	//
	/************************************************************/
	public List<CatalogoOpcionVO> obtenerMotivosNoPostulaciones(){
		CatalogoOpcionDelegate services = CatalogoOpcionDelegateImpl.getInstance();
		List<CatalogoOpcionVO> motivos = null;
		try {
			motivos = services.consultarCatalogo(Constantes.CATALOGO_OPCION_MOTIVO_NO_POSTULACION);
			motivos.remove(8);//***Remover No Obligado***//
		} catch (ServiceLocatorException e) {
			logger.info("No encuentra el Catalogo: " + e.getMessage());
			e.printStackTrace();
		}
		return motivos;
	}//--11  //193
	
	/**********************************************************
	//
	// Nombre: OPR  Fecha:	30.10.14
	// Obtiene la cadena JSON para mostrar los catalogos en la vista
	//
	/************************************************************/
	private void redirectJsonCatalogo(List<CatalogoOpcionVO> opciones, HttpServletResponse response) throws IOException {
		CatalogoVO cat = getCatalogo(opciones);
		String json = toJson(cat);
		redirectJsonResponse(response, json);
	}//--5   //198
	
	/**********************************************************
	//
	// Nombre: OPR  Fecha:	15.1.15
	// Ordena columnas de listado de Ofertas No postuladas
	//
	/************************************************************/
	@SuppressWarnings("unchecked")
	public ActionForward ordenarMisOfertasNoPostuladas(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        final int VERY_FIRST_PAGE = 1;
		List<HistoricoBusquedaPPCVO> 	  listaOfertasNoPostuladas = new ArrayList<HistoricoBusquedaPPCVO>();
		HttpSession 				 	  session 				   = request.getSession(true);
		
		this.PAGE_NUM_ROW = 5;
		try{
			UsuarioWebVO usuario 			  = getUsuario(request.getSession());		
			AdministracionNoPostulacionesForm administracionNoPostulacionesForm = (AdministracionNoPostulacionesForm) form;
	        String tipoOrden = request.getParameter("tipoOrden");    // asc | desc
	        sortType = SortType.sortTypeForName(tipoOrden);
	        String tipoColumna = request.getParameter("tipoColumna");   // puesto | empresa | ubicacion | vigenciaOferta
	        sortField = SortField.sortFieldForName(tipoColumna);			
			
	        if (usuario != null) {
	        	listaOfertasNoPostuladas = (List<HistoricoBusquedaPPCVO>) session.getAttribute("FULL_LIST");
		        
	            Comparator<HistoricoBusquedaPPCVO> comparator = new Comparator<HistoricoBusquedaPPCVO>() {
					@Override
					public int compare(HistoricoBusquedaPPCVO obj1, HistoricoBusquedaPPCVO obj2) {
	                    int test = 0;
	                    
	                    switch (sortField){
		                    case PUESTO:{
		                    	String puesto1 = obj1.getTituloOferta().toLowerCase().trim();
		                    	String puesto2 = obj2.getTituloOferta().toLowerCase().trim();
		                    	test = puesto1.compareTo(puesto2);
		                    	break;
		                    }
		                    case EMPRESA:{
		                    	String empresa1 = obj1.getNombreEmpresa().toLowerCase().trim();
		                    	String empresa2 = obj2.getNombreEmpresa().toLowerCase().trim();
		                    	test = empresa1.compareTo(empresa2);
		                    	break;	                    	
		                    }
		                    case UBICACION:{
		                    	/*final String   DELIMITADOR = ",";
		                    	
		                    	String[] localizacion1 = obj1.getUbicacion().toLowerCase().trim().split(DELIMITADOR);
		                    	String[] localizacion2 = obj2.getUbicacion().toLowerCase().trim().split(DELIMITADOR);
		                    	test = localizacion1[0].compareToIgnoreCase(localizacion2[0]);
		                    	if(test != 0 || test != -1)
		                    		test = localizacion1[1].compareToIgnoreCase(localizacion2[1]);*/
		                    	test = obj1.getUbicacion().toLowerCase().trim().compareTo(obj2.getUbicacion().toLowerCase().trim());
		                    	break;
		                    }
		                    case SALARIO:{
		                    	Double salario1 = obj1.getSalario();
		                    	Double salario2 = obj2.getSalario(); 
		                    	test = Double.valueOf(salario1).compareTo(Double.valueOf(salario2));
		                    	break;
		                    }
		                    case COMPATIBILIDAD:{
		                    	Long compatibilidad1 = obj1.getCompatibilidad();
		                    	Long compatibilidad2 = obj2.getCompatibilidad();
		                    	test = Long.valueOf(compatibilidad1).compareTo(Long.valueOf(compatibilidad2));
		                    	break;
		                    }
		                    case MOTIVO:{break;}
	                    }
	                    
						return test;
					}
	            };//253

	            Collections.sort(listaOfertasNoPostuladas, comparator);
	            if (sortType == SortType.DESC) {
	                Collections.reverse(listaOfertasNoPostuladas);
	            }

	            session.setAttribute("FULL_LIST", listaOfertasNoPostuladas);
	            session.setAttribute("NUM_REGISTROS", this.PAGE_NUM_ROW);
	            session.setAttribute("NUM_PAGE_LIST", VERY_FIRST_PAGE); // Shows page number
	            
		        administracionNoPostulacionesForm.setAdministracionNoPostulacionesVO(listaOfertasNoPostuladas);
	        }
	        
		} catch (PersistenceException pe) { pe.printStackTrace();
		} catch (Exception te) { te.printStackTrace(); }
		
		return page(1, mapping, session);
    }//--68	
}//267