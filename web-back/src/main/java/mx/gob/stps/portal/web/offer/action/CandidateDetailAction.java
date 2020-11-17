package mx.gob.stps.portal.web.offer.action;


import static mx.gob.stps.portal.web.infra.utils.Constantes.BODY_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_TEMPLATE_OFFER;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_TEMPLATE_MI_ESP_EMP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TITULO_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.DESCRIPCION_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FACEBOOK_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TWITTER_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.URL_ESPECIFICA;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.gob.stps.portal.core.candidate.dao.CandidatoDAO;
import mx.gob.stps.portal.core.candidate.vo.ConocerConfigVO;
import mx.gob.stps.portal.core.candidate.vo.InformacionGeneralVO;
import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.mail.exception.MailException;
import mx.gob.stps.portal.core.infra.mail.service.NotificacionService;
import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.core.infra.utils.Constantes.ESTATUS;
import mx.gob.stps.portal.core.infra.utils.Utils;
import mx.gob.stps.portal.core.infra.vo.EntrevistaVO;
import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaCandidatoVO;
import mx.gob.stps.portal.persistencia.vo.EstandarConocerVO;
import mx.gob.stps.portal.utils.Catalogos;
import mx.gob.stps.portal.utils.Catalogos.POSTULACION_CARTERA;
import mx.gob.stps.portal.utils.ConstantesGenerales;
import mx.gob.stps.portal.web.candidate.delegate.CandidatoBusDelegate;
import mx.gob.stps.portal.web.candidate.delegate.CandidatoBusDelegateImpl;
import mx.gob.stps.portal.web.entrevista.delegate.EntrevistaBusDelegate;
import mx.gob.stps.portal.web.entrevista.delegate.EntrevistaBusDelegateImpl;
import mx.gob.stps.portal.web.infra.action.GenericAction;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.infra.utils.PropertiesLoader;
import mx.gob.stps.portal.web.infra.vo.ResultVO;
import mx.gob.stps.portal.web.offer.delegate.OfferBusDelegate;
import mx.gob.stps.portal.web.offer.delegate.OfferBusDelegateImpl;
import mx.gob.stps.portal.web.security.vo.UsuarioWebVO;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class CandidateDetailAction extends GenericAction {

	//private static final long  ACCION_ENTREVISTA = 9999;
	private static Logger logger = Logger.getLogger(CandidateDetailAction.class);
	private static final String COMPATIBILIDAD_ATTR = "fulfillment";
	//private static final int POSTULACION_CARTERA_FALSE = 0;
	
	@Override
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		long idOferta = 0;
		int compatibilidad;
		long idCandidato = 0;
		HttpSession session = request.getSession();
		InformacionGeneralVO infoVO = null;
		OfferBusDelegate servicesOffer = OfferBusDelegateImpl.getInstance();
		EntrevistaBusDelegate entrevistaBusDelegate = EntrevistaBusDelegateImpl.getInstance();
		if (request.getParameter("idc") != null) {
			idCandidato = Utils.parseLong(request.getParameter("idc"));
			session.setAttribute("idCandidato", idCandidato);
			idOferta = Utils.parseLong(request.getParameter("ido"));
			session.setAttribute("idOferta", idOferta);
			if (idCandidato > 0) {
				Integer estatusOfertaCandidato = recuperaEstatusOfertaCandidato(idOferta,idCandidato);
				session.setAttribute("estatusOfertaCandidato", estatusOfertaCandidato);
				CandidatoBusDelegate candidateServices = CandidatoBusDelegateImpl.getInstance();
				infoVO = candidateServices.showCandidateDetail(idCandidato);
				candidateServices.contabilizaDetalleCandidato(idCandidato);
				infoVO.setDescEstatus(request.getParameter("ec"));
				session.setAttribute("detalle", infoVO);
				String strDecVideo = getDecoratedVideo(infoVO);
				request.setAttribute("decoratedVideo",strDecVideo);				
				try {
					compatibilidad = servicesOffer.match(idOferta, idCandidato);
					// Se registra para su almacenamiento en caso de vincular al candidato
					session.setAttribute(COMPATIBILIDAD_ATTR, compatibilidad);
				} catch (ServiceLocatorException se) { se.printStackTrace(); }
				try {					
					EntrevistaVO entrevistaVo = entrevistaBusDelegate.buscaEntrevistaOfertaCandidatoActiva(idCandidato, idOferta);
					session.setAttribute("interview", entrevistaVo);				 
				} catch (PersistenceException e) {
					logger.error(e.getMessage());
					e.printStackTrace();
				} catch (ServiceLocatorException e) {
					logger.error(e.getMessage());
					e.printStackTrace();
				}
				if (null != request.getParameter("type")) infoVO.setEmpresaConfidencial(false);
				// Datos de certificaciones (si tiene y ha activado la opción)
				try {
					request.removeAttribute("listaEstandares");				
					ConocerConfigVO conocerConfigVO = candidateServices.consultaConocerConfigByIdCandidato(idCandidato);
					if (conocerConfigVO != null){
						if (conocerConfigVO.getDeseaPublicar() != null &&
					        conocerConfigVO.getDeseaPublicar().equals(Utils.toLong(Constantes.PUBLICAR_ESTANDARES.SI.getIdOpcion()))){
								List<EstandarConocerVO> estandares = candidateServices.consultaConocer(idCandidato);
								if (estandares != null && estandares.size() > 0) {
									request.setAttribute("listaEstandares", estandares);							
									logger.info("El candidato idCandidato="+idCandidato+" tiene "+estandares.size()+" estándar(es) en el registro Conocer");							
								}
						}
					}
				
				}
				catch(Exception e){
					logger.error("Error al cargar el bloque de certificaciones del idCandidato="+idCandidato);
					e.printStackTrace();
				}
				//
			}
		}
		request.getSession().setAttribute("_urlpageinvoke", "detalleOfertaCreada.do?method=init&ido=" + idOferta);
		request.getSession().setAttribute("_urlbackto", "detalleOfertaCreada.do?method=init&ido=" + idOferta);
		request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
        PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Detalle de candidato");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Detalle de candidato, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo.");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
		return mapping.findForward(FORWARD_TEMPLATE_MI_ESP_EMP);
	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward programarEntrevista(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		String type = null;
		StringBuilder msg = new StringBuilder();
		EntrevistaBusDelegate entrevistaBusDelegate = EntrevistaBusDelegateImpl.getInstance();
		EntrevistaVO entrevistaVo = new EntrevistaVO();
		entrevistaVo.setIdCandidato(Utils.parseLong(request.getParameter("idCandidato")));
		entrevistaVo.setIdOfertaEmpleo(Utils.parseLong(request.getParameter("idOfertaEmpleo")));
		entrevistaVo.setEstatus(Constantes.ESTATUS.NUEVA.getIdOpcion());
		//============ FORMATO A LA FECHA =====================
		//SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		String fecha = (String)request.getParameter("fechaEntrevista");
		entrevistaVo.setFechaString(fecha);
		//=====================================================
		entrevistaVo.setHora((String)request.getParameter("hora"));
		entrevistaVo.setFecha(mx.gob.stps.portal.web.infra.utils.Utils.convertWebDate(fecha));
		entrevistaVo.setFechaAlta(new Date());
		
		int compatibilidad = recuperaCompatibilidad(request, entrevistaVo.getIdOfertaEmpleo(), entrevistaVo.getIdCandidato());
		
		try {
			if(entrevistaVo.getIdCandidato() > 0 && entrevistaVo.getIdOfertaEmpleo() > 0 && null != entrevistaVo.getFecha()) {
				entrevistaBusDelegate.save(entrevistaVo);
				entrevistaVo.setEmailMensaje(getEmailMsg(request.getParameter("empresa"), request.getParameter("tituloOferta"), mx.gob.stps.portal.core.oferta.detalle.helper.Utils.formatDate(mx.gob.stps.portal.web.infra.utils.Utils.convertWebDate(fecha)), entrevistaVo.getHora()));
				entrevistaVo.setNombre(request.getParameter("nombre"));
				entrevistaVo.setTipoOperacion("Candidato");
				entrevistaVo.setCorreo(request.getParameter("email"));
				//entrevistaVo.setCorreo("stellez@infotec.com.mx");
				entrevistaVo.setAsunto("Entrevista en línea");
				enviarNotificacion(entrevistaVo);
				msg.append("La entrevista ha sido programada para el día ");
				msg.append(mx.gob.stps.portal.core.oferta.detalle.helper.Utils.formatDate(mx.gob.stps.portal.web.infra.utils.Utils.convertWebDate(fecha)) + " a las " + entrevistaVo.getHora() + " hrs.");
				msg.append("\nEsta información fue enviada al correo electrónico del candidato, favor de esperar la confirmación de la misma.");
				msg.append("\nLe recomendamos iniciar sesión en el Portal del Empleo 15 minutos antes de la entrevista.");
				type = ResultVO.TYPE_SUCCESS;
				vincularCandidato(entrevistaVo.getIdOfertaEmpleo(), entrevistaVo.getIdCandidato(), compatibilidad);
			}
		}catch (MailException me) { logger.error(me);
		}catch (BusinessException be) { logger.error(be);
		}catch (ServiceLocatorException se) { logger.error(se); }
		try {
			String json = toJson(new ResultVO(msg.toString(), type));
			redirectJsonResponse(response, json);
		} catch (IOException e) {
			e.printStackTrace();
			request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
	        PropertiesLoader properties = PropertiesLoader.getInstance();
			request.getSession().setAttribute(TITULO_PAGINA, "Detalle de candidato");
			request.getSession().setAttribute(DESCRIPCION_PAGINA, "Detalle de candidato, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo");
			request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
			request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
			request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
	        return mapping.findForward(FORWARD_TEMPLATE_OFFER);
		}
		return null;
	}
	
	private void vincularCandidato(long idOfertaEmpleo, long idCandidato, int compatibilidad) {
		OfertaCandidatoVO vo = null;
		OfferBusDelegate services = OfferBusDelegateImpl.getInstance();
		try {
			List<OfertaCandidatoVO> list = services.findByOfferCandidate(idOfertaEmpleo, idCandidato);
			if (null == list || list.isEmpty()) {
				vo = new OfertaCandidatoVO();
				vo.setFechaAlta(Calendar.getInstance().getTime());
				vo.setIdCandidato(idCandidato);
				vo.setIdOfertaEmpleo(idOfertaEmpleo);
				vo.setEstatus(ESTATUS.VINCULADO.getIdOpcion());
				vo.setCompatibilidad(compatibilidad);
				
				services.create(vo);
			}else {
				Iterator<OfertaCandidatoVO> it = list.iterator();
				while (it.hasNext()) {
					vo = it.next();
					if (vo.getEstatus() < ESTATUS.VINCULADO.getIdOpcion() || vo.getEstatus() == Constantes.ESTATUS.SELECCIONADO.getIdOpcion())
						vo.setEstatus(ESTATUS.VINCULADO.getIdOpcion());
					services.update(vo);
				}
			}
		}catch(Exception e) { logger.error(e); }
    }
	
	/**
	 * Método que asocia el candidato con la oferta con estatus vinculado
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward asociarCandidatoOferta(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		String type = null;
		StringBuilder msg = new StringBuilder();
		OfferBusDelegate services = OfferBusDelegateImpl.getInstance();
		HttpSession session = request.getSession();
		UsuarioWebVO usuario = getUsuario(session);		
		try {
			List<OfertaCandidatoVO> list = services.findByOfferCandidate(Utils.parseLong(request.getParameter("idOfertaEmpleo")), Utils.parseLong(request.getParameter("idCandidato")));
			if (null == list || list.isEmpty()) {
				OfertaCandidatoVO ofertaCandidato = new OfertaCandidatoVO ();
				ofertaCandidato.setIdOfertaEmpleo(Utils.parseLong(request.getParameter("idOfertaEmpleo")));
				ofertaCandidato.setIdCandidato(Utils.parseLong(request.getParameter("idCandidato")));
				ofertaCandidato.setFechaAlta(new Date());				
				ofertaCandidato.setEstatus(Constantes.ESTATUS.SELECCIONADO.getIdOpcion());				
				ofertaCandidato.setCompatibilidad(recuperaCompatibilidad(request, ofertaCandidato.getIdOfertaEmpleo(), ofertaCandidato.getIdCandidato()));
				ofertaCandidato.setIdFuente((long)Catalogos.CATALOGO_FUENTE_QUE_REGISTRA_OFERTA.PORTAL.getIdOpcion());
				ofertaCandidato.setIdUsuario(usuario.getIdUsuario());
				ofertaCandidato.setIdOficina(ConstantesGenerales.PORTAL_ID_OFICINA);
				ofertaCandidato.setPostulacionCartera(POSTULACION_CARTERA.NO.getIdOpcion());
				//id_vinculado se le pone en OfertaCandidatoAppService
				
				services.create(ofertaCandidato);
			}
			msg.append("Se agregó al candidato a la carpeta empresarial");
			type = ResultVO.TYPE_SUCCESS;
		} catch (BusinessException be) { logger.error(be);
		} catch (ServiceLocatorException se) { logger.error(se); }
		try {
			String json = toJson(new ResultVO(msg.toString(), type));
			redirectJsonResponse(response, json);
		} catch (IOException e) {
			e.printStackTrace();
			request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
	        return mapping.findForward(FORWARD_TEMPLATE_OFFER);
		}
		return null;
	}
	
	public ActionForward mostrarDetalle(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		return null;
	}

	/*public final ActionForward cambiarEstatusOfertaCandidato(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws IOException{
		ResultVO resultVO = null;
		DestinatarioVO destinatarioVO = new DestinatarioVO();
		AdmonCandidatosBusDelegate services = AdmonCandidatosBusDelegateImpl.getInstance();
		
		long accion = Long.parseLong(request.getParameter("accion"));
	
		// cambiar estatus
		try {
			//procesar		
			if (accion == ESTATUS.EN_PROCESO.getIdOpcion()){
				services.procesarCandidato(this.ofertaCandidatoVo.getIdOfertaCandidato());
			}else if (accion == ESTATUS.RECHAZADA.getIdOpcion()){
			//no aceptar
				destinatarioVO.addMail(this.getOfertaCandidatoVo().getCorreoCandidato());
				destinatarioVO.setMensaje(getMensaje(request, "can.oferta.rechazar.msg", 
						this.getOfertaCandidatoVo().getNombreEmpresa(), this.getOfertaCandidatoVo().getTituloOferta()));
				destinatarioVO.setNombre(this.getOfertaCandidatoVo().getNombreCandidato());
				
				services.rechazarCandidato( this.ofertaCandidatoVo.getIdOfertaCandidato(), 
						Integer.parseInt(request.getParameter("idMotivo")), destinatarioVO);
			}else if (accion == ESTATUS.CONTRATADO.getIdOpcion()){
			//contratar
				destinatarioVO.addMail(this.getOfertaCandidatoVo().getCorreoCandidato());
				destinatarioVO.setMensaje(getMensaje(request, "can.oferta.contratar.msg", 
						this.getOfertaCandidatoVo().getNombreEmpresa(), this.getOfertaCandidatoVo().getTituloOferta()));
				destinatarioVO.setNombre(this.getOfertaCandidatoVo().getNombreCandidato());
				
				Date fecha = new Date();
				DateConverter dtConvert =  new DateConverter();
				dtConvert.setFormatPattern(formatDateForma);
				fecha =  (Date) ConvertUtils.convert(request.getParameter("fechaContrato"), Date.class); 
				System.out.println("fecha entra: "	+ request.getParameter("fechaContrato"));
				System.out.println("fecha sale: " + fecha);
			
				services.contratarCandidato( this.ofertaCandidatoVo.getIdOfertaCandidato(), 
					fecha, destinatarioVO);
			}
			resultVO = new ResultVO(getMensaje(request, "can.oferta.procesar.ext"), ResultVO.TYPE_SUCCESS);
			//contactar (vincular)
			if (accion == ESTATUS.VINCULADO.getIdOpcion())
			{
				destinatarioVO.addMail(this.getOfertaCandidatoVo().getCorreoCandidato());
				destinatarioVO.setMensaje(getMensaje(request, "can.oferta.contactar.msg", 
						this.getOfertaCandidatoVo().getNombreEmpresa(), this.getOfertaCandidatoVo().getTituloOferta()));
				
				services.contactarCandidato(this.ofertaCandidatoVo.getIdOfertaCandidato(), destinatarioVO);
				resultVO = new ResultVO(getMensaje(request, "can.oferta.contactar.confirm", this.getOfertaCandidatoVo().getNombreCandidato()), ResultVO.TYPE_SUCCESS);
			}
			if (accion ==ACCION_ENTREVISTA){
			// entrevista
				Date fecha = new Date();
				DateConverter dtConvert =  new DateConverter();
				dtConvert.setFormatPattern(formatDateForma);
				fecha =  (Date) ConvertUtils.convert(request.getParameter("fechaEntrevista"), Date.class); 
				dtConvert.setFormatPattern(formatDateForma);
				
				SimpleDateFormat sdf = new SimpleDateFormat(formatDate);
				String fechaStr = sdf.format(fecha);
				
				System.out.println("fecha sale: " + fecha);
				System.out.println("fechaStr sale: " + fechaStr);
				String hora = request.getParameter("valorHora");
				
				destinatarioVO.addMail(this.getOfertaCandidatoVo().getCorreoCandidato());
				destinatarioVO.setMensaje(getMensaje(request, "can.oferta.entrevista.msg", 
						this.getOfertaCandidatoVo().getNombreEmpresa(), this.getOfertaCandidatoVo().getTituloOferta()
						, fechaStr, hora));
				destinatarioVO.setNombre(this.getOfertaCandidatoVo().getNombreCandidato());
				
				services.entrevistarCandidato( this.ofertaCandidatoVo.getIdOfertaCandidato(), 
					fecha, hora,destinatarioVO);
				resultVO = new ResultVO(getMensaje(request, "can.oferta.entrevista.confirm", fechaStr, hora), ResultVO.TYPE_SUCCESS);
				
			}
		} catch (BusinessException e1) {
			e1.printStackTrace();
			resultVO = new ResultVO(getMensaje(request, "can.oferta.procesar.err"), ResultVO.TYPE_ERROR);
		} catch (ServiceLocatorException e1) {
			e1.printStackTrace();
			resultVO = new ResultVO(getMensaje(request, "can.oferta.procesar.err"), ResultVO.TYPE_ERROR);
		} catch (PersistenceException e) {
			e.printStackTrace();
			resultVO = new ResultVO(getMensaje(request, "can.oferta.procesar.err"), ResultVO.TYPE_ERROR);
		} catch (SQLException e) {
			e.printStackTrace();
			resultVO = new ResultVO(getMensaje(request, "can.oferta.procesar.err"), ResultVO.TYPE_ERROR);
		} catch (NumberFormatException e) {
			resultVO = new ResultVO(getMensaje(request, "can.oferta.procesar.err"), ResultVO.TYPE_ERROR);
			e.printStackTrace();
		} catch (MailException e) {
			resultVO = new ResultVO(getMensaje(request, "can.oferta.procesar.err"), ResultVO.TYPE_ERROR);
			e.printStackTrace();
		}
		
		System.out.println("response ");
		String json = toJson(resultVO);
		redirectJsonResponse(response, json);
				
		return null;
	}*/

	/**
	 * Método que notifica por correo electrónico la entrevista del candidato
	 * @param entrevistaVO
	 * @throws MailException
	 */
	private void enviarNotificacion(EntrevistaVO entrevistaVO) throws MailException{		
		NotificacionService notificacionService = new NotificacionService();
		notificacionService.notificacionEntrevista(entrevistaVO);
	}
	
	/**
	 * Método que asocia el candidato con la oferta con estatus de seleccionado
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward agregarCarpeta(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		OfferBusDelegate ofertaCandidatoDelegate = OfferBusDelegateImpl.getInstance();
		OfertaCandidatoVO ofertaCandidato = new OfertaCandidatoVO ();
		ofertaCandidato.setIdCandidato(Utils.parseLong(request.getParameter("idCandidato")));
		ofertaCandidato.setIdOfertaEmpleo(Utils.parseLong(request.getParameter("idOfertaEmpleo")));
		ofertaCandidato.setEstatus(Constantes.ESTATUS.SELECCIONADO.getIdOpcion());
		ofertaCandidato.setFechaAlta(new Date ());
		ofertaCandidato.setCompatibilidad(recuperaCompatibilidad(request, ofertaCandidato.getIdOfertaEmpleo(), ofertaCandidato.getIdCandidato()));
		
		try {
			ofertaCandidatoDelegate.create(ofertaCandidato);
		} catch (BusinessException e) {
			logger.error(e);
		} catch (ServiceLocatorException e) {
			logger.error(e);
		}
        PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Detalle de candidato");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Detalle de candidato, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
		return mapping.findForward(FORWARD_TEMPLATE_OFFER);
	}

	private String getDecoratedVideo(InformacionGeneralVO infoVo){
		String strDecorated = "No disponible";
		char cAmpersand = '&';
		if(infoVo.getUrlVideoEstatus() ==1){			
			String strUrlVideo = infoVo.getUrlVideoCV();
			logger.info("strUrlVideo :"+ strUrlVideo);
			if(null != strUrlVideo){
				if(strUrlVideo.contains("http://www.youtube.com/watch?v=")){
					String strTemp = strUrlVideo.replace("http://www.youtube.com/watch?v=", "http://www.youtube.com/embed/");
					logger.info("video strTemp :"+ strTemp);
					int iFin = strTemp.indexOf(cAmpersand);
					if(iFin>-1){
						strTemp = strTemp.substring(0,iFin);
						logger.info("video strTemp :"+ strTemp);
					}
					
					//strDecorated = "<iframe type=\"text/html\" width=\"300\" height=\"220\" src=\"" + strTemp + "\" frameborder=\"0\"> </iframe>	";
					strDecorated =  strTemp; 
				} else if(strUrlVideo.contains("http://youtu.be/")){
					String strTemp = strUrlVideo.replace("http://youtu.be/", "http://www.youtube.com/embed/");				
					int iFin = strTemp.indexOf("?");
					if(iFin>-1){
						strTemp = strTemp.substring(0,iFin);
						logger.info("video strTemp 2:"+ strTemp);
					}				
					strDecorated =  strTemp; 
				} else if(strUrlVideo.contains("http://www.youtube.com/embed/")) {
					strDecorated = strUrlVideo;
				} else {
					strDecorated = strUrlVideo;
				}
				
			}
			logger.info("video strDecorated :"+ strDecorated);
			//http://www.youtube.com/watch?v=vhw2LZ46gWQ
			//http://www.youtube.com/watch?v=vhw2LZ46gWQ&feature=player_embedded
			//http://www.youtube.com/watch?v=Fo9ZuvkeKJI&feature=fvst
				
			//<iframe type="text/html" width="300" height="220" src="http://www.youtube.com/embed/5IDqMEs-ZGA" frameborder="0"> </iframe>			
			
		}
		
		return strDecorated;
	}		
	
	
	private String getEmailMsg(String empresa, String tituloOferta, String fechaEntrevista, String hora) {
		StringBuilder msg = new StringBuilder();
		msg.append("La Empresa " + (null != empresa ? empresa : "") + " ha visto tu perfil laboral y está interesada en realizar ");
		msg.append("una entrevista en línea a través del Portal del Empleo para ocupar el puesto ");
		msg.append(tituloOferta + " por lo que ha sido programada para el día ");
		msg.append(fechaEntrevista + " a las " + hora + " hrs. ");
		msg.append("Para confirmar la entrevista, favor de Ingresar al Espacio para candidatos del ");
		msg.append("Portal del Empleo y seleccionar Entrevista en Línea, en dicha sección se enlistarán ");
		msg.append("las entrevistas que tienes programadas y podrás aceptar o rechazar la invitación.");
		return msg.toString();
	}

	private int recuperaCompatibilidad(HttpServletRequest request, long idOfertaEmpleo, long idCandidato){
		int compatibilidad = 0;
		
		HttpSession session = request.getSession();
		Object compatibilidadAtt = session.getAttribute(COMPATIBILIDAD_ATTR);

		if (compatibilidadAtt==null){
			
			logger.info("Atributo del porcentaje de compatibilidad no localizado, se invocara el proceso para su almacenamiento en la relacion del candidato con la oferta.");
			
			try{
				OfferBusDelegate servicesOffer = OfferBusDelegateImpl.getInstance();
				compatibilidad = servicesOffer.match(idOfertaEmpleo, idCandidato);
			}catch(Exception e){
				e.printStackTrace(); logger.error(e);
			}
		} else {
			compatibilidad = mx.gob.stps.portal.core.infra.utils.Utils.toInt(compatibilidadAtt);
		}
		
		return compatibilidad;
	}
	
	private Integer recuperaEstatusOfertaCandidato(long idOfertaEmpleo, long idCandidato){
		Integer estatus = null;
		try {
			CandidatoDAO dao = new CandidatoDAO();
			estatus = dao.consultaEstatusOfertaCandidato(idOfertaEmpleo, idCandidato);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}			
		return estatus;
	}

}
