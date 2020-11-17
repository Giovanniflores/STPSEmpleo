package mx.gob.stps.portal.web.offer.action;

import static mx.gob.stps.portal.web.infra.utils.Constantes.BODY_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_TEMPLATE_MI_ESP_CAND;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TITULO_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.DESCRIPCION_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FACEBOOK_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TWITTER_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.URL_ESPECIFICA;

import java.io.IOException;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.core.infra.utils.Constantes.EDAD_REQUISITO;
import mx.gob.stps.portal.core.infra.utils.Constantes.ESTATUS;
import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaCandidatoVO;
import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaEmpleoJB;
import mx.gob.stps.portal.utils.Catalogos;
import mx.gob.stps.portal.web.candidate.delegate.CandidatoBusDelegate;
import mx.gob.stps.portal.web.candidate.delegate.CandidatoBusDelegateImpl;
import mx.gob.stps.portal.web.infra.action.PagerAction;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.infra.utils.PropertiesLoader;
import mx.gob.stps.portal.web.infra.utils.Utils;
import mx.gob.stps.portal.web.offer.delegate.OfferBusDelegate;
import mx.gob.stps.portal.web.offer.delegate.OfferBusDelegateImpl;
import mx.gob.stps.portal.web.offer.form.MyOffersForm;
import mx.gob.stps.portal.web.security.vo.UsuarioWebVO;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public final class MyOffersAction extends PagerAction {

    public enum SortField {
        PUESTO ("puesto"),
        EMPRESA ("empresa"),
        UBICACION ("ubicacion"),
        VIGENCIA("vigencia"),
        ESTATUS ("estatus");

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

            assert true : "Invalid fieldNameStr";
            return null;
        }
    }

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

            assert true : "Invalid typeStr";
            return null;
        }
    }
	
	private static Logger logger = Logger.getLogger(MyOffersAction.class);

	private static final String TABLA_MIS_POSTULACIONES = "_MIS_POSTULACIONES";
	private static final String TABLA_MIS_OFERTAS_GUARDADAS = "_MIS_OFERTAS_GUARDADAS";
	private static final String TABLA_EMPRESAS_QUE_ME_BUSCAN = "_EMPRESAS_QUE_ME_BUSCAN";
//    private static final String REGISTRAR_POSTULACIONES_EXTERNAS = "_REGISTRAR_POSTULACIONES_EXTERNAS";
//    private static final String SEGUIMIENTO_A_POSTULACIONES_EXTERNAS = "_SEGUIMIENTO_A_POSTULACIONES_EXTERNAS";
//    private static final String REGISTRAR_MOTIVO_DE_NO_POSTULACION = "_REGISTRAR_MOTIVO_DE_NO_POSTULACION";
//    private static final String FORWARD_REGISTRAR_POSTULACIONES_EXTERNAS = "ACTION_REGISTRAR_POSTULACION_EXTERNA";

    private static final String TABLA_PAGER = "tablaPager";

    private SortField sortField;
    private SortType sortType;

    @Override
    public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

	public ActionForward remove(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        UsuarioWebVO usuario = this.getUsuario(session);
        long idCandidato = usuario.getIdPropietario();

        MyOffersForm myoffers = (MyOffersForm)form;
		String[] offers = request.getParameterValues("myoffers");

		OfferBusDelegate offerService = OfferBusDelegateImpl.getInstance();
		
		if (offers!=null){
			for (int i=0; i<offers.length; i++) {
				int idOfertaCandidato = Utils.parseInt(offers[i]);
				
				if (idOfertaCandidato > 0) {
					try {
						OfertaCandidatoVO vo = offerService.findOCById(idOfertaCandidato);
						if (vo != null) {
							if (vo.getEstatus() == Constantes.ESTATUS.SELECCIONADA.getIdOpcion()) {
                                offerService.removeOC(idOfertaCandidato);
                            } else {
								if (vo.getEstatus() == Constantes.ESTATUS.VINCULADO.getIdOpcion()) {
                                    vo.setEstatus(Constantes.ESTATUS.NO_INTERESADO.getIdOpcion());
                                } else if (vo.getEstatus() == Constantes.ESTATUS.POSTULADO.getIdOpcion()) {
                                    vo.setEstatus(Constantes.ESTATUS.DESPOSTULADO.getIdOpcion());
                                }
								offerService.update(vo);
							}
							
							myoffers.setMessage("Las ofertas de empleo seleccionadas han sido eliminadas del listado");
						}else {
                            myoffers.setMessage("La transacción no pudo ser completada, favor de intentarlo nuevamente después de unos instantes");
                        }
					}catch (Exception se) {
                        logger.error(se);
                    }
				}
			}			
		}

		myoffers.setAction(MyOffersForm.ACTION_REMOVE);
		/*request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
	    return mapping.findForward(FORWARD_TEMPLATE_FORM);*/
		//return init(mapping, form, request, response);

        CandidatoBusDelegate candidatoService = CandidatoBusDelegateImpl.getInstance();
        Integer ppcEstatus = null;
        try {
            ppcEstatus = candidatoService.consultarPpcEstatus(idCandidato);

        } catch (ServiceLocatorException e) {
            e.printStackTrace();
            logger.error(e);
            return null;
        }
        if (ppcEstatus == null) {
            ppcEstatus = 0;
        }
        myoffers.reassignPpcStatus(ppcEstatus);

        String difTablaPager = myoffers.getDifTablaPager();
        if (difTablaPager.equals(TABLA_MIS_POSTULACIONES)) {
            return misPostulaciones(mapping, form, request, response);
        } else if (difTablaPager.equals(TABLA_MIS_OFERTAS_GUARDADAS)) {
            return misOfertasGuardadas(mapping, form, request, response);
        } else if (difTablaPager.equals(TABLA_EMPRESAS_QUE_ME_BUSCAN)) {
            return empresasQueMeBuscan(mapping, form, request, response);
        }

        assert true : "Something is wrong, this line shall never be reached";
        return null;
    }
	
	public ActionForward pageTable(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		//MyOffersForm myoffers = (MyOffersForm) form;
		String difTablaPager = request.getParameter(TABLA_PAGER);
		int pagenum = 1;
		return this.page(pagenum, mapping, session, difTablaPager);
	}

    public ActionForward misPostulaciones(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

    	int ofertasContratadoPpc = 0;
    	MyOffersForm myoffers = (MyOffersForm)form;
        HttpSession session = request.getSession();
        UsuarioWebVO usuario = this.getUsuario(session);
        long idCandidato = usuario.getIdPropietario();
        List<OfertaEmpleoJB> misPostulacionesList = new ArrayList<OfertaEmpleoJB>();
        OfertaEmpleoJB dummy = new OfertaEmpleoJB(new HashMap<String, String>());
        try {
            OfferBusDelegate offerService = OfferBusDelegateImpl.getInstance();
            if (myoffers.getAction() == MyOffersForm.ACTION_INIT)
                myoffers.setMessage("");
            if (idCandidato > 0) {
                misPostulacionesList = offerService.misPostulaciones(idCandidato);
                if (!misPostulacionesList.isEmpty()) {
                	dummy = misPostulacionesList.get(0);
                	misPostulacionesList.remove(0);
                }
                //ofertasContratadoPpc = offerService.consultarOfertasContratadoPpc(idCandidato);
            }else
                myoffers.setMessage("Al parecer no tiene acceso o no tiene los permisos necesarios");

            session.setAttribute(String.format("FULL_LIST%s", TABLA_MIS_POSTULACIONES), misPostulacionesList);
            session.setAttribute(String.format("NUM_REGISTROS%s", TABLA_MIS_POSTULACIONES), this.PAGE_NUM_ROW);

            session.setAttribute("estatusActivoIdOpcion", ESTATUS.ACTIVO.getIdOpcion());
            session.setAttribute("estatusInactivoIdOpcion", ESTATUS.INACTIVO.getIdOpcion());
            session.setAttribute("edadRequisitoSiIdOpcion", EDAD_REQUISITO.SI.getIdOpcion());

        } catch(Exception e) {
            e.printStackTrace();
            logger.error(e);
        }
        myoffers.setAction(MyOffersForm.ACTION_INIT);
        myoffers.setDifTablaPager(TABLA_MIS_POSTULACIONES); // Value set in order to know the 'forward mapping'

        myoffers.setOfertasContratadoPpc(ofertasContratadoPpc);

        CandidatoBusDelegate candidatoService = CandidatoBusDelegateImpl.getInstance();
        Integer ppcEstatus = null;
        try {
            ppcEstatus = candidatoService.consultarPpcEstatus(idCandidato);
        } catch (ServiceLocatorException e) {
            e.printStackTrace();
            logger.error(e);
            return null;
        }
        if (ppcEstatus == null) ppcEstatus = 0;
        myoffers.reassignPpcStatus(ppcEstatus);
        if (null != dummy.getUbicaciones().get("query")) 
        	request.getSession().setAttribute("QUERY", dummy.getUbicaciones().get("query"));
        StringBuilder code = new StringBuilder("POST VIEW: " + String.valueOf(misPostulacionesList.size()));
        code.append(" ").append(" POST SERV: " + dummy.getUbicaciones().get("total"));
        code.append(" ").append(" PPC ESTS: " + dummy.getUbicaciones().get("ppcEstatus"));
        code.append(" ").append(" PPC HNBT: " + dummy.getUbicaciones().get("hasNeverBeenInTouchWithPpc"));
        code.append(" ").append(" ERROR: " + dummy.getUbicaciones().get("error"));
        myoffers.setMessage(code.toString());
        session.setAttribute("_urlpageinvoke", request.getContextPath() + "/misofertas.do?method=misPostulaciones");
        session.setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
        PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Mis postulaciones");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Espacio Candidato, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
        return mapping.findForward(FORWARD_TEMPLATE_MI_ESP_CAND);
    }

    public ActionForward miContratacionPpc(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();
        UsuarioWebVO usuario = this.getUsuario(session);
        long idCandidato = usuario.getIdPropietario();

        MyOffersForm myoffers = (MyOffersForm)form;

        int ofertasContratadoPpc = 1;

        // Read idOfertaCandidato from Query string
        long idOfertaCandidato = 0;
        try {
            idOfertaCandidato = Long.valueOf(request.getParameter("idOC"));
        } catch (Exception e){
            e.printStackTrace();
        }

        try {
            List<OfertaEmpleoJB> misPostulacionesList = new ArrayList<OfertaEmpleoJB>();

            OfferBusDelegate offerService = OfferBusDelegateImpl.getInstance();

            if (myoffers.getAction() == MyOffersForm.ACTION_INIT) {
                myoffers.setMessage("");
            }

            if (usuario != null) {
                misPostulacionesList = offerService.misContratacionPpc(idOfertaCandidato);
//                ofertasContratadoPpc = offerService.consultarOfertasContratadoPpc(idCandidato);
            } else {
                myoffers.setMessage("Al parecer no tiene acceso o no tiene los permisos necesarios");
            }

            // NOTE: Use the same view as "Mis postulaciones"
            session.setAttribute(String.format("FULL_LIST%s", TABLA_MIS_POSTULACIONES), misPostulacionesList);
            session.setAttribute(String.format("NUM_REGISTROS%s", TABLA_MIS_POSTULACIONES), this.PAGE_NUM_ROW);

            session.setAttribute("estatusActivoIdOpcion", ESTATUS.ACTIVO.getIdOpcion());
            session.setAttribute("estatusInactivoIdOpcion", ESTATUS.INACTIVO.getIdOpcion());
            session.setAttribute("edadRequisitoSiIdOpcion", EDAD_REQUISITO.SI.getIdOpcion());

        } catch(Exception e) {
            e.printStackTrace();
            logger.error(e);
        }

        myoffers.setAction(MyOffersForm.ACTION_INIT);
        myoffers.setDifTablaPager(TABLA_MIS_POSTULACIONES); // Value set in order to know the 'forward mapping'

        myoffers.setOfertasContratadoPpc(ofertasContratadoPpc);

        CandidatoBusDelegate candidatoService = CandidatoBusDelegateImpl.getInstance();
        Integer ppcEstatus = null;
        try {
            ppcEstatus = candidatoService.consultarPpcEstatus(idCandidato);
        } catch (ServiceLocatorException e) {
            e.printStackTrace();
            logger.error(e);
            return null;
        }
        if (ppcEstatus == null) {
            ppcEstatus = 0;
        }
        myoffers.reassignPpcStatus(ppcEstatus);

//        session.setAttribute("_urlpageinvoke", "misofertas.do?method=misPostulaciones");
        session.setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
        PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Mis ofertas");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Mis Ofertas, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
        return mapping.findForward(FORWARD_TEMPLATE_MI_ESP_CAND);
    }

    public ActionForward ordenarMisPostulaciones(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        final int VERY_FIRST_PAGE = 1;

        HttpSession session = request.getSession();
        MyOffersForm myoffers = (MyOffersForm)form;
        UsuarioWebVO usuario = this.getUsuario(session);

        String tipoOrden = request.getParameter("tipoOrden");    // asc | desc
        sortType = SortType.sortTypeForName(tipoOrden);
        String tipoColumna = request.getParameter("tipoColumna");   // puesto | empresa | ubicacion | vigenciaOferta
        sortField = SortField.sortFieldForName(tipoColumna);

        List<OfertaEmpleoJB> misPostulacionesList = new ArrayList<OfertaEmpleoJB>();
        if (usuario != null) {
            misPostulacionesList = (List<OfertaEmpleoJB>) session.getAttribute(String.format("FULL_LIST%s", TABLA_MIS_POSTULACIONES));

            Comparator<OfertaEmpleoJB> comparator = new Comparator<OfertaEmpleoJB>() {
                @Override
                public int compare(OfertaEmpleoJB obj1, OfertaEmpleoJB obj2) {
                    int test = 0;

                    //objects, including type-safe enums, follow this form
                    //note that null objects will throw an exception here
                    switch (sortField) {
                        case PUESTO:
                            String puesto1 = obj1.getTituloOferta().toLowerCase().trim();
                            String puesto2 = obj2.getTituloOferta().toLowerCase().trim();
                            test = puesto1.compareTo(puesto2);
                            break;
                        case EMPRESA:
                            String empresa1 = obj1.getEmpresaNombre().toLowerCase().trim();
                            String empresa2 = obj2.getEmpresaNombre().toLowerCase().trim();
                            test = empresa1.compareTo(empresa2);
                            break;
                        case UBICACION:
                            String ubicacion1 = obj1.getUbicacion().toLowerCase().trim();
                            String ubicacion2 = obj2.getUbicacion().toLowerCase().trim();
                            test = ubicacion1.compareTo(ubicacion2);
                            break;
                        case VIGENCIA:
                            Date vigencia1 = obj1.getFechaVigencia();
                            Date vigencia2 = obj2.getFechaVigencia();
                            test = vigencia1.compareTo(vigencia2);
                            break;
                        case ESTATUS:
                            String estatus1 = obj1.getEstatusOfertaCandidato().toLowerCase().trim();
                            String estatus2 = obj2.getEstatusOfertaCandidato().toLowerCase().trim();
                            test = estatus1.compareTo(estatus2);
                            break;
                    }

                    return test;
                }
            };

            Collections.sort(misPostulacionesList, comparator);
            if (sortType == SortType.DESC) {
                Collections.reverse(misPostulacionesList);
            }

            session.setAttribute(String.format("FULL_LIST%s", TABLA_MIS_POSTULACIONES), misPostulacionesList);
            session.setAttribute(String.format("NUM_REGISTROS%s", TABLA_MIS_POSTULACIONES), this.PAGE_NUM_ROW);
            session.setAttribute(String.format("NUM_PAGE_LIST%s", TABLA_MIS_POSTULACIONES), VERY_FIRST_PAGE); // Shows page number
//
//            session.setAttribute("estatusActivoIdOpcion", ESTATUS.ACTIVO.getIdOpcion());
//            session.setAttribute("estatusInactivoIdOpcion", ESTATUS.INACTIVO.getIdOpcion());
//            session.setAttribute("edadRequisitoSiIdOpcion", EDAD_REQUISITO.SI.getIdOpcion());
        }

        myoffers.setDifTablaPager(TABLA_MIS_POSTULACIONES);

//        session.setAttribute("_urlpageinvoke", String.format("misofertas.do?method=ordenarMisPostulaciones&tipoOrden=%s&tipoColumna=%s", sortType.type, sortField.fieldName));    // Used by the pagination
//        session.setAttribute("_urlpageinvoke", String.format("misofertas.do?method=misPostulaciones"));    // Used by the pagination
//        session.setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
//        return mapping.findForward(FORWARD_TEMPLATE_MI_ESP_CAND);

        return page(VERY_FIRST_PAGE, mapping, session, TABLA_MIS_POSTULACIONES);  // Primera paginacion
    }

    public ActionForward misOfertasGuardadas(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        UsuarioWebVO usuario = this.getUsuario(session);
        long idCandidato = usuario.getIdPropietario();

        MyOffersForm myoffers = (MyOffersForm)form;

        try {
            List<OfertaEmpleoJB> misOfertasGuardadasList = new ArrayList<OfertaEmpleoJB>();

            OfferBusDelegate services = OfferBusDelegateImpl.getInstance();

            if (myoffers.getAction() == MyOffersForm.ACTION_INIT) {
                myoffers.setMessage("");
            }

            if (usuario != null) {
                misOfertasGuardadasList = services.misOfertasGuardadas(idCandidato);
            } else {
                myoffers.setMessage("Al parecer no tiene acceso o no tiene los permisos necesarios");
            }
            session.setAttribute(String.format("FULL_LIST%s", TABLA_MIS_OFERTAS_GUARDADAS), misOfertasGuardadasList);
            session.setAttribute(String.format("NUM_REGISTROS%s", TABLA_MIS_OFERTAS_GUARDADAS), this.PAGE_NUM_ROW);
            session.setAttribute("estatusActivoIdOpcion", ESTATUS.ACTIVO.getIdOpcion());
            session.setAttribute("estatusInactivoIdOpcion", ESTATUS.INACTIVO.getIdOpcion());
            session.setAttribute("edadRequisitoSiIdOpcion", EDAD_REQUISITO.SI.getIdOpcion());
        } catch(Exception e) {
            e.printStackTrace();
            logger.error(e);
        }
        myoffers.setAction(MyOffersForm.ACTION_INIT);
        myoffers.setDifTablaPager(TABLA_MIS_OFERTAS_GUARDADAS);
        session.setAttribute("_urlpageinvoke", request.getContextPath() + "/misofertas.do?method=misOfertasGuardadas");
        session.setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
        PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Mis ofertas guardadas");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Espacio Candidato, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
        return mapping.findForward(FORWARD_TEMPLATE_MI_ESP_CAND);
    }

    public ActionForward empresasQueMeBuscan(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        UsuarioWebVO usuario = this.getUsuario(session);
        long idCandidato = usuario.getIdPropietario();

        MyOffersForm myoffers = (MyOffersForm)form;

        try {
            List<OfertaEmpleoJB> empresasQueMeBuscanList = new ArrayList<OfertaEmpleoJB>();

            OfferBusDelegate services = OfferBusDelegateImpl.getInstance();

            if (myoffers.getAction() == MyOffersForm.ACTION_INIT) {
                myoffers.setMessage("");
            }

            if (usuario != null) {
                empresasQueMeBuscanList = services.empresasMeBuscanOfertas(idCandidato);
            } else {
                myoffers.setMessage("Al parecer no tiene acceso o no tiene los permisos necesarios");
            }

            session.setAttribute(String.format("FULL_LIST%s", TABLA_EMPRESAS_QUE_ME_BUSCAN), empresasQueMeBuscanList);
            session.setAttribute(String.format("NUM_REGISTROS%s", TABLA_EMPRESAS_QUE_ME_BUSCAN), this.PAGE_NUM_ROW);

            session.setAttribute("estatusActivoIdOpcion", ESTATUS.ACTIVO.getIdOpcion());
            session.setAttribute("estatusInactivoIdOpcion", ESTATUS.INACTIVO.getIdOpcion());
            session.setAttribute("edadRequisitoSiIdOpcion", EDAD_REQUISITO.SI.getIdOpcion());

        }catch(Exception e){
            e.printStackTrace();
            logger.error(e);
        }

//        session.setAttribute(TAB_MENU, TAB_MIS_OFERTAS);    // TODO: What's this for???
//        ocultaBarraArticulos(request);

        myoffers.setAction(MyOffersForm.ACTION_INIT);
        myoffers.setDifTablaPager(TABLA_EMPRESAS_QUE_ME_BUSCAN);

        session.setAttribute("_urlpageinvoke", "misofertas.do?method=empresasQueMeBuscan");
        session.setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
        PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Empresas que me buscan");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Espacio Candidato, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
        return mapping.findForward(FORWARD_TEMPLATE_MI_ESP_CAND);
    }
    
}