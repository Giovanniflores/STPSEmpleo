package mx.gob.stps.portal.web.offer.action;

import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_ENTIDAD_FEDERATIVA;
import static mx.gob.stps.portal.core.infra.utils.Constantes.NUM_REGISTROS_SIGUIENTES_OFERTAS;
import static mx.gob.stps.portal.web.infra.utils.Constantes.BODY_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_TEMPLATE_RESPONSIVE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TITULO_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.DESCRIPCION_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FACEBOOK_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TWITTER_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.URL_ESPECIFICA;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.gob.stps.portal.core.infra.utils.Constantes.ENTIDADES_FEDERATIVAS;
import mx.gob.stps.portal.core.infra.utils.Constantes.OCUPATE_CAMPO_ORDEN;
import mx.gob.stps.portal.core.infra.utils.Constantes.OCUPATE_ORDEN_DIRECCION;
import mx.gob.stps.portal.core.infra.utils.Utils;
import mx.gob.stps.portal.core.oferta.busqueda.vo.OfertaPorCanalVO;
import mx.gob.stps.portal.core.oferta.busqueda.vo.OfertaPorPerfilVO;
import mx.gob.stps.portal.core.oferta.vo.OfertaDetalleCortoVO;
import mx.gob.stps.portal.core.search.BuildSearchMessage;
import mx.gob.stps.portal.core.search.ResultInfoBO;
import mx.gob.stps.portal.core.search.SearchMessage;
import mx.gob.stps.portal.web.infra.delegate.CatalogoOpcionDelegate;
import mx.gob.stps.portal.web.infra.delegate.CatalogoOpcionDelegateImpl;
import mx.gob.stps.portal.web.infra.utils.Constantes;
import mx.gob.stps.portal.web.infra.utils.PropertiesLoader;
import mx.gob.stps.portal.web.infra.utils.TablaOfertasOcupate;
import mx.gob.stps.portal.web.infra.utils.UrlUtils;
import mx.gob.stps.portal.web.jobvacancies.delegate.BolsasTrabajoBusDelegate;
import mx.gob.stps.portal.web.jobvacancies.delegate.BolsasTrabajoBusDelegateImpl;
import mx.gob.stps.portal.web.offer.delegate.OfferBusDelegate;
import mx.gob.stps.portal.web.offer.delegate.OfferBusDelegateImpl;
import mx.gob.stps.portal.web.security.delegate.SecutityDelegateImpl;
import mx.gob.stps.portal.web.security.vo.UsuarioWebVO;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class SearchOffersIndex extends PagerOffersAction {
	
	private static Logger logger = Logger.getLogger(SearchOffersIndex.class);
	private static final String ID_OFERTA_EMPLEO = Constantes.ID_OFERTA_EMPLEO;

	private static final String ENCODING_REQUEST = "ISO-8859-1";
	
	private static final String SEARCH_QUERY_PARAM = "searchQ";
	private static final String SEARCH_PLACE_PARAM = "searchPlace";

	@Override
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		session.removeAttribute("entity");
		session.removeAttribute(SEARCH_QUERY_PARAM);
		session.removeAttribute(SEARCH_PLACE_PARAM);
		
		/*String encoding = request.getCharacterEncoding();
		
		if(!ENCODING_REQUEST.equalsIgnoreCase(request.getCharacterEncoding())) {
			try {
				request.setCharacterEncoding(ENCODING_REQUEST);
			} catch(Exception e) {
				logger.error("Error al tratar de cambiar el encoding: "+encoding, e);
			}
		}*/
		
		String query = request.getParameter(SEARCH_QUERY_PARAM); /** REQUEST **/
		int place = Utils.parseInt(request.getParameter(SEARCH_PLACE_PARAM));
		limpiarPalabras(query);
		
		request.getSession().setAttribute("countOferOcp", "countOferOcp");
		logger.info("EUPU SEGUNDO");
		buscar(request, true, query, place, null, null);
		
		page(mapping, form, request, response); // Primera paginacion
//		request.getSession().setAttribute("TITULO", "Buscador de empleo \"Ocúpate\"");
		request.setAttribute("TITULO", "Buscador de empleo \"Ocúpate\"");
		request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
		
        PropertiesLoader properties = PropertiesLoader.getInstance();
        if(query.isEmpty()){
			request.getSession().setAttribute(TITULO_PAGINA, "Ofertas de empleo en " + estadoRepublica(place) );
			request.getSession().setAttribute(DESCRIPCION_PAGINA, "Ofertas de empleo en " +  estadoRepublica(place) + ", Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo.");
			request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
			request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
			request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
        }else{
        	request.getSession().setAttribute(TITULO_PAGINA, "Ofertas de empleo de " + limpiarPalabras(query) + " en " + estadoRepublica(place) );
			request.getSession().setAttribute(DESCRIPCION_PAGINA, "Ofertas de empleo de " + limpiarPalabras(query) + " en " +  estadoRepublica(place) + ", Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo.");
			request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
			request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
			request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
        }
		return mapping.findForward(FORWARD_TEMPLATE_RESPONSIVE);
	}
	
	private void buscar(HttpServletRequest request, boolean esServicio, String query, int place, OCUPATE_CAMPO_ORDEN ocupateCampo, OCUPATE_ORDEN_DIRECCION direccion) {
		logger.info("EUPU buscar " + query + " " + ocupateCampo + " " + direccion);
		HttpSession session = request.getSession();
		SearchMessage searchMessage = new SearchMessage();
//		session.removeAttribute("entity");
//		session.removeAttribute(SEARCH_QUERY_PARAM);
//		session.removeAttribute(SEARCH_PLACE_PARAM);
//		
//		String encoding = request.getCharacterEncoding();
//		
//		if(!ENCODING_REQUEST.equalsIgnoreCase(request.getCharacterEncoding())) {
//			try {
//				request.setCharacterEncoding(ENCODING_REQUEST);
//			} catch(Exception e) {
//				logger.error("Error al tratar de cambiar el encoding: "+encoding, e);
//			}
//		}
//
//		String query = request.getParameter(SEARCH_QUERY_PARAM);
//		int place = Utils.parseInt(request.getParameter(SEARCH_PLACE_PARAM));

		Integer location = null;

		if (place != -1)
			location = new Integer(place);	

		if (null == query) query = "";

		int hashcode = query.hashCode();

		List<ResultInfoBO> indices = new ArrayList<ResultInfoBO>();

		try {
			if (location!=null) {
				String entity = "";
				ENTIDADES_FEDERATIVAS entidad = ENTIDADES_FEDERATIVAS.getEntidad(location);

				if (entidad!=null) {
					entity = entidad.getDescripcion();
				} else {
					CatalogoOpcionDelegate catalogoOpcionDelegate = CatalogoOpcionDelegateImpl.getInstance();
					entity = catalogoOpcionDelegate.getOpcionById(CATALOGO_OPCION_ENTIDAD_FEDERATIVA, location.intValue());
				}
				session.setAttribute("entity", entity);
			}

			BolsasTrabajoBusDelegate services = BolsasTrabajoBusDelegateImpl.getInstance();

			if (ocupateCampo != null) {
//				searchMessage = searchMessageDummy();
				searchMessage = services.MsgBuscarOfertasEmpleo(query, location, ocupateCampo, direccion); 

				//indices = services.buscarOfertasEmpleo(query, location, ocupateCampo, direccion);
			} else {
//				searchMessage = searchMessageDummy();
				searchMessage = services.MsgBuscarOfertasEmpleo(query, location);

				//indices = services.buscarOfertasEmpleo(query, location);
			}

			indices = searchMessage.getListResultInfoBO();

			String mensaje = "";

			if (session.getAttribute("countOferOcp") != null) {
				Integer totalPlazas = obtenerTotalNumeroPlazas(indices);
				searchMessage.setTotalPlazasDisponibles(totalPlazas != null ? totalPlazas.intValue() : 0);
				mensaje = BuildSearchMessage.getMessage(searchMessage);
				session.setAttribute("totalNumeroPlazas", totalPlazas);
				session.setAttribute("msjOcp", mensaje);
			} else {
				session.setAttribute("totalNumeroPlazas", session.getAttribute("totalNumeroPlazas"));
				searchMessage.setTotalPlazasDisponibles(session.getAttribute("totalNumeroPlazas") != null ? Integer.parseInt(session.getAttribute("totalNumeroPlazas").toString()) : 0);
				mensaje = (String) session.getAttribute("msjOcp");
			}

			session.setAttribute("searchMessage", mensaje); 

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			indices = new ArrayList<ResultInfoBO>();
		} finally {
			if (esServicio) {
				UsuarioWebVO usuario = getUsuario(request.getSession());

				if (usuario!=null)
					SecutityDelegateImpl.getInstance().busquedaOcupate(usuario.getIdUsuario(), usuario.getIdPerfil());
			}
		}
		session.removeAttribute(NUM_PAGE_LIST);
		session.removeAttribute(NUM_PAGE_LIST + hashcode);
		session.setAttribute(FULL_LIST, indices);
		session.setAttribute(FULL_LIST + hashcode, indices);
		session.setAttribute("_urlpageinvoke", "ocupate.do?method=between&searchQ=" + query);
		session.setAttribute("NUM_REGISTROS", PAGE_NUM_ROW);
		session.setAttribute(SEARCH_QUERY_PARAM, query);
		session.setAttribute(SEARCH_PLACE_PARAM, String.valueOf(place));
	}

	private SearchMessage searchMessageDummy() {
		SearchMessage searchMessage = new SearchMessage();
		searchMessage.setMensaje("EUPU");
		searchMessage.setCadenaOriginal("::");
		List<ResultInfoBO> lista = new ArrayList<ResultInfoBO>();
		for (int i = 0; i < 5; i++) {
			ResultInfoBO result = new ResultInfoBO();
			result.setOcupacion("Chofer" + i);
			result.setFecha(new Date(new java.util.Date().getTime()));
			List<String> listaStr = new ArrayList<String>();
			listaStr.add("ASDF");
			listaStr.add("BASDF");
			listaStr.add("CASDF");
			listaStr.add("DASDF");

			result.setCarreras(listaStr);
			result.setConocimientos(listaStr);
			result.setTitulo("Titulo" + i);
			result.setId(i); lista.add(result);
		} searchMessage.setListResultInfoBO(lista);
		return searchMessage;
	} //XXX EUPU

	public ActionForward orderByColumn(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();

		logger.info("JGLC baja countOferOcp de sesion");
		session.removeAttribute("countOferOcp");
		
		String encoding = request.getCharacterEncoding();
		
		if(!ENCODING_REQUEST.equalsIgnoreCase(request.getCharacterEncoding())) {
			try {
				request.setCharacterEncoding(ENCODING_REQUEST);
			} catch(Exception e) {
				logger.error("Error al tratar de cambiar el encoding: "+encoding, e);
			}
		}
		
		String tipoOrdenamiento = request.getParameter("tipoOrden");
		int numeroColumna 		= Utils.parseInt(request.getParameter("tipoColumna"));

		String query = (String)session.getAttribute(SEARCH_QUERY_PARAM);  /** SESSION **/ 
		int place	 = Utils.parseInt((String)session.getAttribute(SEARCH_PLACE_PARAM));

		OCUPATE_CAMPO_ORDEN ocupateCampo = null;
		OCUPATE_ORDEN_DIRECCION direccion = null;
		
		if (tipoOrdenamiento.equals(TablaOfertasOcupate.ORDENAMIENTO_ASCENDENTE))
			direccion = OCUPATE_ORDEN_DIRECCION.ASCENDENTE;
		else if (tipoOrdenamiento.equals(TablaOfertasOcupate.ORDENAMIENTO_DESCENDENTE))
			direccion = OCUPATE_ORDEN_DIRECCION.DESCENDENTE;

		if (numeroColumna == 1)
			ocupateCampo = OCUPATE_CAMPO_ORDEN.TITULO_OFERTA;
		else if (numeroColumna == 2)
			ocupateCampo = OCUPATE_CAMPO_ORDEN.UBICACION;
		else if (numeroColumna == 3)
			ocupateCampo = OCUPATE_CAMPO_ORDEN.EMPRESA;
    	else if (numeroColumna == 4) {
    		ocupateCampo = OCUPATE_CAMPO_ORDEN.SALARIO;
    	} else if (numeroColumna == 5) {
    		ocupateCampo = OCUPATE_CAMPO_ORDEN.FECHA;
    	}

		buscar(request, false, query, place, null, null);

		ActionForward actionReturn = page(mapping, form, request, response);

		int hashcode = 0;
		String pattern = "";
		if (null != request.getParameter("searchQ")) {
			pattern = request.getParameter("searchQ");
			request.setAttribute("searchQ", UrlUtils.suprXSS(request.getParameter("searchQ")));
			hashcode = request.getParameter("searchQ").hashCode();
		} else if (null != session.getAttribute("searchQ")) {
			pattern = (String) session.getAttribute("searchQ");
			request.setAttribute("searchQ", UrlUtils.suprXSS((String)session.getAttribute("searchQ")));
			hashcode = pattern.hashCode();
		}
		List<OfertaPorCanalVO> rowsPage = (List<OfertaPorCanalVO>) session.getAttribute(PAGE_LIST + hashcode);
		try { //XXX EUPU
    		ordenamientoLista(rowsPage, ocupateCampo, direccion);
    	} catch (Exception e) {
    		logger.error("EUPU FAILEO");
    	}
		return actionReturn; // Primera paginacion
	}

	public Integer obtenerTotalNumeroPlazas(List<ResultInfoBO> indices){
		Integer totalPlazas = 0;
		List<Long> indicesOfertas = new ArrayList<Long>();
		Iterator<ResultInfoBO> itIndices = indices.iterator();
		try {
			while(itIndices.hasNext()){
				ResultInfoBO infoBo = (ResultInfoBO)itIndices.next();
				indicesOfertas.add(infoBo.getId());
			}
			OfferBusDelegate offerBusDelegate = OfferBusDelegateImpl.getInstance();			
			totalPlazas = offerBusDelegate.contarNumeroPlazasResultados(indicesOfertas);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return totalPlazas;
	}
	
	public ActionForward between(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		/**
		 * Navega entre las ofertas indexadas en base al par&aacute;metro de búsqueda
		 */
        PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
		request.getSession().setAttribute(TITULO_PAGINA, "Ofertas de empleo");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Ofertas de empleo, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
		return mapping.findForward(FORWARD_TEMPLATE_RESPONSIVE);
	}
	
	public ActionForward masOfertasDeEmpleo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {	
	
		Long idOfertaEmpleo = Utils.parseLong(request.getParameter(ID_OFERTA_EMPLEO));
		
		List<Long> todasOfertas = new ArrayList<Long>();

		// el atributo FULL_LIST puede venir como una lista de objetos Long (búsquedas ocúpate) o como una lista de objetos OfertaPorCanalVO (ofertas destacadas)
		// realizamos una conversión a Long según sea el caso
		@SuppressWarnings("unchecked")
		List<Object> listaOfertasTodasObject = (List<Object>)request.getSession().getAttribute("FULL_LIST");
		logger.info("JGLC " + listaOfertasTodasObject);
		if (listaOfertasTodasObject != null) {
			for (Object siguienteOfertaObject : listaOfertasTodasObject) {
				Long siguienteOfertaLong = null;

				// si provenimos de una búsqueda de ofertas recientes/destacadas
				if (siguienteOfertaObject instanceof OfertaPorCanalVO) {
					OfertaPorCanalVO ofertaCanalVO = (OfertaPorCanalVO)siguienteOfertaObject;
					siguienteOfertaLong = Utils.toLong(ofertaCanalVO.getIdOfertaEmpleo());
				// si provenimos de una búsqueda de ocúpate		
				} else if (siguienteOfertaObject instanceof ResultInfoBO){
					siguienteOfertaLong = ((ResultInfoBO)siguienteOfertaObject).getId();
				// si provenimos de una búsqueda Buscar por mi perfil			
				} else if (siguienteOfertaObject instanceof OfertaPorPerfilVO){
					siguienteOfertaLong = ((OfertaPorPerfilVO)siguienteOfertaObject).getIdOfertaEmpleo();
				}else if(siguienteOfertaObject instanceof Long){
					
					siguienteOfertaLong = (Long) siguienteOfertaObject;
					
				}
				if(siguienteOfertaLong!=null)todasOfertas.add(siguienteOfertaLong);
			}
		}

		List<Long> siguientesOfertas = new ArrayList<Long>();

		if (todasOfertas != null && !todasOfertas.isEmpty() && idOfertaEmpleo != null && idOfertaEmpleo > 0) {

			// iteramos la lista
			boolean extraeDeLaLista = false;
			
			int contador = 0;
			for (Long registro : todasOfertas) {

				if (extraeDeLaLista) {
					contador++;

					siguientesOfertas.add(registro);
					if (contador == NUM_REGISTROS_SIGUIENTES_OFERTAS) break;
				}

				// cuando encontremos un elemento n-ésimo que coincida con el idOfertaEmpleo, cambiamos la bandera para que a partir del n+1 se
				// obtenga la información de la oferta y se retorne en la lista
				if (registro.equals(idOfertaEmpleo))
					extraeDeLaLista = true;
			}
		}

		if (siguientesOfertas!=null && !siguientesOfertas.isEmpty()){
			try {
				request.removeAttribute("LISTA_SIGUIENTES_OFERTAS");
				logger.info("EUPU siguientes ofertas");
				OfferBusDelegate service = OfferBusDelegateImpl.getInstance();
				List<OfertaDetalleCortoVO> ofertasSiguientes = service.consultaOfertasDescripcionCorta(siguientesOfertas);

				request.setAttribute("LISTA_SIGUIENTES_OFERTAS", ofertasSiguientes);
				request.setAttribute("idOfertaEmpleoConsultada", idOfertaEmpleo);
			} catch (Exception e) {
				logger.error(e); e.printStackTrace();
			}
		}
		return mapping.findForward("JSP_SIGUIENTES_OFERTAS");
	}

	private String estadoRepublica(int idEstado) {
		switch(idEstado) {
		case 1: return "Aguascalientes";
		case 2: return "Baja California";
        case 3: return "Baja California Sur";
        case 4: return "Campeche";
        case 5: return "Coahuila";
        case 6: return "Colima";
        case 7: return "Chiapas";
        case 8: return "Chihuahua";
        case 9: return "Ciudad de M&eacute;xico";
        case 10: return "Durango";
        case 11: return "Guanajuato";
        case 12: return "Guerrero";
        case 13: return "Hidalgo";
        case 14: return "Jalisco";
        case 15: return "Estado de M&eacute;xico";
        case 16: return "Michoac&aacute;n";
        case 17: return "Morelos";
        case 18: return "Nayarit";
        case 19: return "Nuevo le&oacute;n";
        case 20: return "Oaxaca";
        case 21: return "Puebla";
        case 22: return "Quer&eacute;taro";
        case 23: return "Quintana Roo";
        case 24: return "San Luis Potos&iacute;";
        case 25: return "Sinaloa";
        case 26: return "Sonora";
        case 27: return "Tabasco";
        case 28: return "Tamaulipas";
        case 29: return "Tlaxcala";
        case 30: return "Veracruz";
        case 31: return "Yucat&aacute;n";
        case 32: return "Zacatecas";
		default: return "M&eacute;xico";
		}
	}
	private String limpiarPalabras(String cadena){	
		cadena = cadena.toLowerCase();
		cadena = cadena.replace("á", "a");
		cadena = cadena.replace("é", "e");
		cadena = cadena.replace("í", "i");
		cadena = cadena.replace("ó", "o");
		cadena = cadena.replace("ú", "u");
		cadena = cadena.replace("ñ", "n");		
		cadena = cadena.replace("Á", "a");
		cadena = cadena.replace("É", "e");
		cadena = cadena.replace("Í", "i");
		cadena = cadena.replace("Ó", "o");
		cadena = cadena.replace("Ú", "u");
		cadena = cadena.replace("Ñ", "n");
		cadena = cadena.replace("-"," ");
		if(cadena.length()>100){
			cadena = cadena.replace(" de ", " ");
			cadena = cadena.replace(" para ", " ");
			cadena = cadena.replace(" ante ", " ");
			cadena = cadena.replace(" bajo ", " ");
			cadena = cadena.replace(" con ", " ");
			cadena = cadena.replace(" contra ", " ");
			cadena = cadena.replace(" a ", " ");
			cadena = cadena.replace(" desde ", " ");
			cadena = cadena.replace(" durante ", " ");
			cadena = cadena.replace(" entre ", " ");
			cadena = cadena.replace(" hacia ", " ");
			cadena = cadena.replace(" hasta ", " ");
			cadena = cadena.replace(" para ", " ");
			cadena = cadena.replace(" por ", " ");
			cadena = cadena.replace(" segun ", " ");
			cadena = cadena.replace(" sin ", " ");
			cadena = cadena.replace(" sobre ", " ");
			cadena = cadena.replace(" tras ", " ");
			cadena = cadena.replace(" el ", " ");
			cadena = cadena.replace(" la ", " ");
			cadena = cadena.replace(" los ", " ");
			cadena = cadena.replace(" las ", " ");
		}
		return cadena;
	}
	
	private void ordenamientoLista(List<OfertaPorCanalVO> rowsPage, OCUPATE_CAMPO_ORDEN ocupateCampo, OCUPATE_ORDEN_DIRECCION direccion) {
		switch (ocupateCampo) {
        case TITULO_OFERTA: 
       	 switch (direccion) {
    	 	case ASCENDENTE:
    	 		Collections.sort(rowsPage, new Comparator<OfertaPorCanalVO>() {
         		    @Override
         		    public int compare(OfertaPorCanalVO o1, OfertaPorCanalVO o2) {
         		    	if (o1 == null) {
         		    		return -1;
         		    	} else if (o2 == null) {
         		    		return 1;
         		    	}
         		    	if (o1.getTituloOferta() == null) {
         		    		return -1;
         		    	} else if (o2.getTituloOferta() == null) {
         		    		return 1;
         		    	}
         		        return o1.getTituloOferta().compareTo(o2.getTituloOferta());
         		    }
         		});
    	 		break;
    	 	case DESCENDENTE:
    	 		Collections.sort(rowsPage, new Comparator<OfertaPorCanalVO>() {
         		    @Override
         		    public int compare(OfertaPorCanalVO o1, OfertaPorCanalVO o2) {
         		    	if (o2 == null) {
         		    		return -1;
         		    	} else if (o1 == null) {
         		    		return 1;
         		    	}
         		    	if (o2.getTituloOferta() == null) {
         		    		return -1;
         		    	} else if (o1.getTituloOferta() == null) {
         		    		return 1;
         		    	}
         		        return o2.getTituloOferta().compareTo(o1.getTituloOferta());
         		    }
         		});
    	 		break;
    	    default: break;
    	 }
         break;
        case UBICACION:
       	 switch (direccion) {
       	 	case ASCENDENTE:
       	 		Collections.sort(rowsPage, new Comparator<OfertaPorCanalVO>() {
            		    @Override
            		    public int compare(OfertaPorCanalVO o1, OfertaPorCanalVO o2) {
            		    	if (o1 == null) {
             		    		return -1;
             		    	} else if (o2 == null) {
             		    		return 1;
             		    	}
            		    	if (o1.getUbicacion() == null) {
             		    		return -1;
             		    	} else if (o2.getUbicacion() == null) {
             		    		return 1;
             		    	}
            		        return o1.getUbicacion().compareTo(o2.getUbicacion());
            		    }
            		});
       	 		break;
       	 	case DESCENDENTE:
       	 		Collections.sort(rowsPage, new Comparator<OfertaPorCanalVO>() {
            		    @Override
            		    public int compare(OfertaPorCanalVO o1, OfertaPorCanalVO o2) {
            		    	if (o2 == null) {
             		    		return -1;
             		    	} else if (o1 == null) {
             		    		return 1;
             		    	}
            		    	if (o2.getUbicacion() == null) {
             		    		return -1;
             		    	} else if (o1.getUbicacion() == null) {
             		    		return 1;
             		    	}
            		        return o2.getUbicacion().compareTo(o1.getUbicacion());
            		    }
            		});
       	 		break;
       	    default: break;
       	 }
        break;
        case EMPRESA:
       	 switch (direccion) {
    	 	case ASCENDENTE:
    	 		Collections.sort(rowsPage, new Comparator<OfertaPorCanalVO>() {
         		    @Override
         		    public int compare(OfertaPorCanalVO o1, OfertaPorCanalVO o2) {
         		    	if (o1 == null) {
         		    		return -1;
         		    	} else if (o2 == null) {
         		    		return 1;
         		    	}
         		    	if (o1.getEmpresa() == null) {
         		    		return -1;
         		    	} else if (o2.getEmpresa() == null) {
         		    		return 1;
         		    	}
         		        return o1.getEmpresa().compareTo(o2.getEmpresa());
         		    }
         		});
    	 		break;
    	 	case DESCENDENTE:
    	 		Collections.sort(rowsPage, new Comparator<OfertaPorCanalVO>() {
         		    @Override
         		    public int compare(OfertaPorCanalVO o1, OfertaPorCanalVO o2) {
         		    	if (o2 == null) {
         		    		return -1;
         		    	} else if (o1 == null) {
         		    		return 1;
         		    	}
         		    	if (o2.getEmpresa() == null) {
         		    		return -1;
         		    	} else if (o1.getEmpresa() == null) {
         		    		return 1;
         		    	}
         		        return o2.getEmpresa().compareTo(o1.getEmpresa());
         		    }
         		});
    	 		break;
    	    default: break;
    	 }
         break;
        case SALARIO: 
       	 switch (direccion) {
    	 	case ASCENDENTE:
    	 		Collections.sort(rowsPage, new Comparator<OfertaPorCanalVO>() {
         		    @Override
         		    public int compare(OfertaPorCanalVO o1, OfertaPorCanalVO o2) {
         		    	if (o1 == null) {
         		    		return -1;
         		    	} else if (o2 == null) {
         		    		return 1;
         		    	}
         		        return new Double(o1.getSalario() - o2.getSalario()).intValue();
         		    }
         		});
    	 		break;
    	 	case DESCENDENTE:
    	 		Collections.sort(rowsPage, new Comparator<OfertaPorCanalVO>() {
         		    @Override
         		    public int compare(OfertaPorCanalVO o1, OfertaPorCanalVO o2) {
         		    	if (o2 == null) {
         		    		return -1;
         		    	} else if (o1 == null) {
         		    		return 1;
         		    	}
         		    	return new Double(o2.getSalario() - o1.getSalario()).intValue();
         		    }
         		});
    	 		break;
    	    default: break;
    	 }
         break;
        case FECHA:
       	switch (direccion) {
    	 	case ASCENDENTE:
    	 		Collections.sort(rowsPage, new Comparator<OfertaPorCanalVO>() {
         		    @Override
         		    public int compare(OfertaPorCanalVO o1, OfertaPorCanalVO o2) {
         		    	if (o1 == null) {
         		    		return -1;
         		    	} else if (o2 == null) {
         		    		return 1;
         		    	}
         		    	if (o1.getFechaInicio() == null) {
         		    		return -1;
         		    	} else if (o2.getFechaInicio() == null) {
         		    		return 1;
         		    	}
         		        return o1.getFechaInicio().compareTo(o2.getFechaInicio());
         		    }
         		});
    	 		break;
    	 	case DESCENDENTE:
    	 		Collections.sort(rowsPage, new Comparator<OfertaPorCanalVO>() {
         		    @Override
         		    public int compare(OfertaPorCanalVO o1, OfertaPorCanalVO o2) {
         		    	if (o2 == null) {
         		    		return -1;
         		    	} else if (o1 == null) {
         		    		return 1;
         		    	}
         		    	if (o2.getFechaInicio() == null) {
         		    		return -1;
         		    	} else if (o1.getFechaInicio() == null) {
         		    		return 1;
         		    	}
         		        return o2.getFechaInicio().compareTo(o1.getFechaInicio());
         		    }
         		});
    	 		break;
    	    default: break;
    	 }
         break;
        default:
            break;
		 }
	}
}
