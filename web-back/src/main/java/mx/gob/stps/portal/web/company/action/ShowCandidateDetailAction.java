package mx.gob.stps.portal.web.company.action;


import static mx.gob.stps.portal.web.infra.utils.Constantes.BODY_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.DESCRIPCION_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FACEBOOK_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_TEMPLATE_FORM;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_TEMPLATE_MI_ESP_EMP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TITULO_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TWITTER_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.URL_ESPECIFICA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.formatDate;
import static mx.gob.stps.portal.web.infra.utils.Constantes.formatDateForma;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.gob.stps.portal.core.candidate.dao.CandidatoDAO;
import mx.gob.stps.portal.core.candidate.vo.*;
import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.mail.exception.MailException;
import mx.gob.stps.portal.core.infra.mail.service.NotificacionService;
import mx.gob.stps.portal.core.infra.mail.vo.DestinatarioVO;
import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.core.infra.utils.Constantes.ESTATUS;
import mx.gob.stps.portal.core.infra.vo.EntrevistaVO;
import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaCandidatoVO;
import mx.gob.stps.portal.core.oferta.vo.OfertaEmpleoVO;
import mx.gob.stps.portal.core.search.service.IndexerServiceLocator;
import mx.gob.stps.portal.persistencia.vo.EstandarConocerVO;
import mx.gob.stps.portal.persistencia.vo.TelefonoVO;
import mx.gob.stps.portal.utils.Catalogos;
import mx.gob.stps.portal.utils.Catalogos.MOTIVOS_FUERA_PPC;
import mx.gob.stps.portal.web.candidate.delegate.AdmonCandidatosBusDelegate;
import mx.gob.stps.portal.web.candidate.delegate.AdmonCandidatosBusDelegateImpl;
import mx.gob.stps.portal.web.candidate.delegate.CandidatoBusDelegate;
import mx.gob.stps.portal.web.candidate.delegate.CandidatoBusDelegateImpl;
import mx.gob.stps.portal.web.entrevista.delegate.EntrevistaBusDelegate;
import mx.gob.stps.portal.web.entrevista.delegate.EntrevistaBusDelegateImpl;
import mx.gob.stps.portal.web.infra.action.GenericAction;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.infra.utils.DateConverter;
import mx.gob.stps.portal.web.infra.utils.PropertiesLoader;
import mx.gob.stps.portal.web.infra.utils.Utils;
import mx.gob.stps.portal.web.infra.vo.ResultVO;
import mx.gob.stps.portal.web.oferta.delegate.OfertaBusDelegate;
import mx.gob.stps.portal.web.oferta.delegate.OfertaBusDelegateImpl;
import mx.gob.stps.portal.web.oferta.form.SpaceCompanyForm;
import mx.gob.stps.portal.web.offer.action.OfferDetailsAction;
import mx.gob.stps.portal.web.offer.delegate.OfferBusDelegate;
import mx.gob.stps.portal.web.offer.delegate.OfferBusDelegateImpl;
import mx.gob.stps.portal.web.security.vo.UsuarioWebVO;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ShowCandidateDetailAction extends GenericAction {

	private static final long  ACCION_ENTREVISTA = 9999;
	private static Logger logger = Logger.getLogger(OfferDetailsAction.class);
	private static final String OFERTA_CANDIDATO_VINCULACION = "ofertaCandidato";
	private static final String COMPATIBILIDAD_ATTR = "fulfillment";
	
	@Override
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		long idOfertaCandidato = 0;
		long idCandidato = 0;
		long idOfertaEmpleo = 0;
		InformacionGeneralVO infoVO = null;
		
		HttpSession session = request.getSession();
		try {
			//id de la oferta candidato (no es el id de la oferta de empleo)
			if (request.getParameter("idoc") != null && !request.getParameter("idoc").isEmpty()) {			
				AdmonCandidatosBusDelegate services = AdmonCandidatosBusDelegateImpl.getInstance();
				idOfertaCandidato = Long.parseLong(request.getParameter("idoc"));
				session.setAttribute("idOferta", idOfertaCandidato);				 
				OfertaCandidatoResumenVo ofertaCand = services.obtenerOfertaCandidatoResumen((Long) session.getAttribute("idOferta"));
				//OfferBusDelegate servicesOffer = OfferBusDelegateImpl.getInstance();
				//int compatibilidad = servicesOffer.match(ofertaCand.getIdOfertaEmpleo(), ofertaCand.getIdCandidato());
				int compatibilidad = IndexerServiceLocator.getIndexerServiceRemote().match(ofertaCand.getIdOfertaEmpleo(), ofertaCand.getIdCandidato());
				ofertaCand.setCompatibilidad(compatibilidad);

				request.setAttribute(COMPATIBILIDAD_ATTR, compatibilidad);
				
				// Se registra para su almacenamiento en caso de vincular al candidato
				session.setAttribute(COMPATIBILIDAD_ATTR, compatibilidad);
				
				session.setAttribute("idEstatus", ofertaCand.getIdEstatus());
				request.setAttribute("estatus", ofertaCand.getDescEstatus());
				idCandidato = ofertaCand.getIdCandidato();
				request.setAttribute(OFERTA_CANDIDATO_VINCULACION, ofertaCand);
				session.setAttribute(OFERTA_CANDIDATO_VINCULACION, ofertaCand);
				//Se indica la pagina padre (solo admonCandidatos envia el parametro idoc)
				idOfertaEmpleo = ofertaCand.getIdOfertaEmpleo();
				//request.getSession().setAttribute("_urlpageinvoke", "admonCandidatos.do?method=init&ido=" + ofertaCand.getIdOfertaEmpleo());	
			}else{
				idCandidato = Long.parseLong(request.getParameter("idc"));
				idOfertaCandidato = Long.parseLong(request.getParameter("ido"));
				
			} 
			if (idCandidato > 0) {
				Integer estatusOfertaCandidato = recuperaEstatusOfertaCandidato(idOfertaEmpleo,idCandidato);
				session.setAttribute("estatusOfertaCandidato", String.valueOf(estatusOfertaCandidato));
				
				CandidatoBusDelegate candidateServices = CandidatoBusDelegateImpl.getInstance();
				infoVO = candidateServices.showCandidateDetail(idCandidato);
				String strDecVideo = getDecoratedVideo(infoVO);
				request.setAttribute("decoratedVideo",strDecVideo);

				candidateServices.contabilizaDetalleCandidato(idCandidato);

				List<GradoAcademicoVO> gradoAcademicoVOs = candidateServices.buscarGrados(idCandidato);
				if (gradoAcademicoVOs != null && !gradoAcademicoVOs.isEmpty()) {
					Comparator<GradoAcademicoVO> comparator = new Comparator<GradoAcademicoVO>() {
						@Override
						public int compare(GradoAcademicoVO obj1, GradoAcademicoVO obj2) {
							//objects, including type-safe enums, follow this form
							//note that null objects will throw an exception here
							return mx.gob.stps.portal.utils.Utils.compareInt(obj1.getPrincipal(), obj2.getPrincipal());
						}
					};
					Collections.sort(gradoAcademicoVOs, comparator);
					infoVO.setSituacionAcademica(gradoAcademicoVOs.get(0).getSituacion());	// Principal one
				}

				List<IdiomaVO> idiomaVOs = candidateServices.buscarIdiomas(idCandidato);
				if (idiomaVOs != null && !idiomaVOs.isEmpty()) {
					Comparator<IdiomaVO> comparator = new Comparator<IdiomaVO>() {
						@Override
						public int compare(IdiomaVO obj1, IdiomaVO obj2) {
							//objects, including type-safe enums, follow this form
							//note that null objects will throw an exception here
							return mx.gob.stps.portal.utils.Utils.compareInt(obj1.getPrincipal(), obj2.getPrincipal());
						}
					};
					Collections.sort(idiomaVOs, comparator);
					infoVO.setIdiomas(idiomaVOs);
				}

				List<ExpectativaLaboralVO> expectativaLaboralVOs = candidateServices.buscarExpecLaboral(idCandidato);
				if (expectativaLaboralVOs != null && !expectativaLaboralVOs.isEmpty()) {
					infoVO.setOcupacionDeseada1(expectativaLaboralVOs.get(0).getOcupacionDeseada());
					infoVO.setExperienciaOcupacion1(Catalogos.EXPERIENCIA.getDescripcion(expectativaLaboralVOs.get(0) != null ? expectativaLaboralVOs.get(0).getIdExperiencia() : 1));
					infoVO.setSalarioPretendido(mx.gob.stps.portal.core.infra.utils.Utils.formatMoney(Double.valueOf(String.valueOf(expectativaLaboralVOs.get(0).getSalarioPretendido()))));
					if (expectativaLaboralVOs.size() > 1) {
						infoVO.setOcupacionDeseada2(expectativaLaboralVOs.get(1).getOcupacionDeseada());
						infoVO.setExperienciaOcupacion2(Catalogos.EXPERIENCIA.getDescripcion(expectativaLaboralVOs.get(1) != null ? expectativaLaboralVOs.get(1).getIdExperiencia() : 1));
					}
				}

				List<TelefonoVO> telefonoVOs = candidateServices.getTelefonoAppService().consultarTelefonos(idCandidato, Catalogos.TIPO_PROPIETARIO.CANDIDATO.getIdTipoPropietario());
				if (telefonoVOs != null && !telefonoVOs.isEmpty()) {
					Comparator<TelefonoVO> comparator = new Comparator<TelefonoVO>() {
						@Override
						public int compare(TelefonoVO obj1, TelefonoVO obj2) {
							//objects, including type-safe enums, follow this form
							//note that null objects will throw an exception here
							return mx.gob.stps.portal.utils.Utils.compareInt(obj1.getPrincipal(), obj2.getPrincipal());
						}
					};
					Collections.sort(telefonoVOs, comparator);
					infoVO.setTelefonos(telefonoVOs);
				}

				// Datos de certificaciones (si tiene y ha activado la opciï¿½n)
				try {
					request.removeAttribute("listaEstandares");
					ConocerConfigVO conocerConfigVO = candidateServices.consultaConocerConfigByIdCandidato(idCandidato);
					if (conocerConfigVO != null){

						if (conocerConfigVO.getDeseaPublicar() != null &&
							conocerConfigVO.getDeseaPublicar().equals(Long.valueOf((Constantes.PUBLICAR_ESTANDARES.SI.getIdOpcion())))){
								List<EstandarConocerVO> estandares = candidateServices.consultaConocer(idCandidato);
								if (estandares != null && estandares.size() > 0) {
									request.setAttribute("listaEstandares", estandares);
									logger.info("El candidato idCandidato="+idCandidato+" tiene "+estandares.size()+" estï¿½ndar(es) en el registro Conocer");
								}
						}
					}
				} catch(Exception e){
					logger.error("Error al cargar el bloque de certificaciones del idCandidato="+idCandidato);
					e.printStackTrace();
				}			

			}
		} catch (NumberFormatException nfe) {
			idCandidato = 0;
		} catch (PersistenceException e) {
			e.printStackTrace();
		} catch (BusinessException e) {
			e.printStackTrace();
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		} catch (SQLException e) { e.printStackTrace(); }
		request.setAttribute("detalle", infoVO);
		request.setAttribute("idCandidato", idCandidato);
		//Variables de session para oferta de candidato
		request.setAttribute("idEstatusProceso", ESTATUS.EN_PROCESO.getIdOpcion());
		request.setAttribute("idEstatusRechazo", ESTATUS.RECHAZADA.getIdOpcion());
		request.setAttribute("idEstatusContrato", ESTATUS.CONTRATADO.getIdOpcion());
		request.setAttribute("idEstatusVinculado", ESTATUS.VINCULADO.getIdOpcion());
		request.setAttribute("idEstatusSeleccionado", ESTATUS.SELECCIONADO.getIdOpcion());
		request.setAttribute("idEstatusPostulado", ESTATUS.POSTULADO.getIdOpcion());
		request.setAttribute("idEstatusSeleccionada", ESTATUS.SELECCIONADA.getIdOpcion());
		request.setAttribute("idAccionEntrevista", ACCION_ENTREVISTA);
		
		ocultaBarraArticulos(request);
		request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
        PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Detalle candidato");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Detalle candidato, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo.");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
		return mapping.findForward(FORWARD_TEMPLATE_MI_ESP_EMP);
	}
	
	public ActionForward mostrarDetalle(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		return null;
	}
	
		
	public final ActionForward cambiarEstatusOfertaCandidato(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException {
		long estatusOC = 0L;
		ResultVO resultVO = null;
		Integer estatusPPC = null;
		DestinatarioVO destinatarioVO = new DestinatarioVO();
		UsuarioWebVO userLogged = getUsuario(request.getSession());
		AdmonCandidatosBusDelegate services = AdmonCandidatosBusDelegateImpl.getInstance();
		CandidatoBusDelegate delegate = CandidatoBusDelegateImpl.getInstance();
		long accion = Long.parseLong(request.getParameter("accion"));
		try {
			OfertaCandidatoResumenVo ofertaCand = (OfertaCandidatoResumenVo)request.getSession().getAttribute(OFERTA_CANDIDATO_VINCULACION);
			if (ofertaCand==null) throw new IllegalArgumentException("Se ha perdido la referencias de la vinculacion del candidato hacia la oferta, favor de intentar de nuevo o notificar al administrador.");
			estatusOC = ofertaCand.getIdEstatus();
			estatusPPC = delegate.consultarPpcEstatus(ofertaCand.getIdCandidato());
			//procesar		
			if (accion == ESTATUS.EN_PROCESO.getIdOpcion()) {
				logger.info("---Entrando a procesar candidato");				
				services.procesarCandidato(ofertaCand.getIdOfertaCandidato(), userLogged.getIdUsuario());
				logger.info("---Saliendo de procesar candidato");
			}else if (accion == ESTATUS.RECHAZADA.getIdOpcion()) {
			//no aceptar
				destinatarioVO.addMail(ofertaCand.getCorreoCandidato());
				destinatarioVO.setMensaje(getMensaje(request, "can.oferta.rechazar.msg", ofertaCand.getNombreEmpresa(), ofertaCand.getTituloOferta()));
				destinatarioVO.setNombreCandidato(ofertaCand.getNombreCandidato());
				services.rechazarCandidato(ofertaCand.getIdOfertaCandidato(), Integer.parseInt(request.getParameter("idMotivo")), request.getParameter("motivoDesc"), destinatarioVO);
				if (null != estatusPPC && estatusPPC.intValue() == mx.gob.stps.portal.utils.Catalogos.ESTATUS.INACTIVO_PPC.getIdOpcion() && !existPostPending(ofertaCand.getIdCandidato()))
					delegate.actualizaEstatusPPC(ofertaCand.getIdCandidato(), mx.gob.stps.portal.utils.Catalogos.ESTATUS.ACTIVO_PPC.getIdOpcion(), null);
			}else if (accion == ESTATUS.CONTRATADO.getIdOpcion()) {
				//contratar
				Date fecha = new Date();
				DateConverter dtConvert =  new DateConverter();
				dtConvert.setFormatPattern(formatDateForma);
				destinatarioVO.setIdCandidato(String.valueOf(userLogged.getIdUsuario()));
				destinatarioVO.addMail(ofertaCand.getCorreoCandidato());
				destinatarioVO.setNombreCandidato(ofertaCand.getNombreCandidato());
				destinatarioVO.setMensaje(getMensaje(request, "can.oferta.contratar.msg", ofertaCand.getNombreEmpresa(), ofertaCand.getTituloOferta()));
				fecha =  (Date) ConvertUtils.convert(request.getParameter("fechaContrato"), Date.class); 
				services.contratarCandidato(ofertaCand.getIdOfertaCandidato(), fecha, destinatarioVO);
				actionsTakenAfterContract(ofertaCand.getIdOfertaEmpleo());
				if (null != estatusPPC && estatusPPC.intValue() == mx.gob.stps.portal.utils.Catalogos.ESTATUS.ACTIVO_PPC.getIdOpcion() || estatusPPC == mx.gob.stps.portal.utils.Catalogos.ESTATUS.INACTIVO_PPC.getIdOpcion())
					delegate.setContratadoPPC(ofertaCand.getIdCandidato(), mx.gob.stps.portal.utils.Catalogos.ESTATUS.FUERA_PPC.getIdOpcion(),
							MOTIVOS_FUERA_PPC.CANDIDATO_COLOCADO_EN_OFERTA_SNE.getIdOpcion(), request.getParameter("fechaContrato"), 
							ofertaCand.getMedioColocacion(), ofertaCand.getNombreEmpresa(), ofertaCand.getTituloOferta());
				updatePendingPostByCandidate(ofertaCand.getIdCandidato());
			}
			resultVO = new ResultVO(getMensaje(request, "can.oferta.procesar.ext"), ResultVO.TYPE_SUCCESS);
			
			//contactar (vincular)
			if (accion == ESTATUS.VINCULADO.getIdOpcion()) {
				destinatarioVO.addMail(ofertaCand.getCorreoCandidato());
								
				//XXX NO SOLICITADO EN CU destinatarioVO.addMailCc(ofertaCand.getCorreoEmpresa());
				
				destinatarioVO.setMensaje(getMensaje(request, "can.oferta.contactar.msg", ofertaCand.getNombreEmpresa(), ofertaCand.getTituloOferta()));

				services.contactarCandidato(ofertaCand.getIdOfertaCandidato(), destinatarioVO);
				resultVO = new ResultVO(getMensaje(request, "can.oferta.contactar.confirm", ofertaCand.getNombreCandidato()), ResultVO.TYPE_SUCCESS);
			}
			if (accion ==ACCION_ENTREVISTA) {
				// entrevista
				Date fecha = new Date();
				DateConverter dtConvert =  new DateConverter();
				dtConvert.setFormatPattern(formatDateForma);
				fecha =  (Date) ConvertUtils.convert(request.getParameter("fechaEntrevista"), Date.class); 
				dtConvert.setFormatPattern(formatDateForma);
				
				SimpleDateFormat sdf = new SimpleDateFormat(formatDate);
				String fechaStr = sdf.format(fecha);

				String hora = request.getParameter("valorHora");
				
				destinatarioVO.addMail(ofertaCand.getCorreoCandidato());
				destinatarioVO.setMensaje(getMensaje(request, "can.oferta.entrevista.msg", ofertaCand.getNombreEmpresa(), ofertaCand.getTituloOferta(), fechaStr, hora));
				destinatarioVO.setNombre(ofertaCand.getNombreCandidato());
				
				services.entrevistarCandidato( ofertaCand.getIdOfertaCandidato(), fecha, hora,destinatarioVO);
				resultVO = new ResultVO(getMensaje(request, "can.oferta.entrevista.confirm", fechaStr, hora), ResultVO.TYPE_SUCCESS);
			}
		} catch (BusinessException e1) {
			e1.printStackTrace();
			if (estatusOC == ESTATUS.SELECCIONADO.getIdOpcion() || estatusOC == ESTATUS.VINCULADO.getIdOpcion()) {
				resultVO = new ResultVO(getMensaje(request, "can.oferta.procesar.nctr"), ResultVO.TYPE_ERROR);
			}else
				resultVO = new ResultVO(getMensaje(request, "can.oferta.procesar.ctr"), ResultVO.TYPE_ERROR);
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
		} catch (IllegalArgumentException e) {
			resultVO = new ResultVO(e.getMessage(), ResultVO.TYPE_ERROR);
			e.printStackTrace();
		} catch (Exception e) {
			resultVO = new ResultVO(getMensaje(request, "can.oferta.procesar.err"), ResultVO.TYPE_ERROR);
			e.printStackTrace();
		}		
		
		String json = toJson(resultVO);
		redirectJsonResponse(response, json);
				
		return null;
	}

	/**
	 * Mï¿½todo que asocia el candidato con la oferta con estatus vinculado
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward asociarCandidatoOferta(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		OfferBusDelegate ofertaCandidatoDelegate = OfferBusDelegateImpl.getInstance();
		OfertaCandidatoVO ofertaCandidato = new OfertaCandidatoVO ();
		ofertaCandidato.setIdCandidato(Utils.parseLong(request.getParameter("idCandidato")));
		ofertaCandidato.setIdOfertaEmpleo(Utils.parseLong(request.getParameter("idOfertaEmpleo")));
		ofertaCandidato.setEstatus(Constantes.ESTATUS.VINCULADO.getIdOpcion());
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
		request.getSession().setAttribute(TITULO_PAGINA, "Detalle del candidato");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Reg&iacute;strate en nuestra Bolsa de trabajo y postula la oferta de empleo que m&aacute;s se apegue seg&uacute;n tu curriculum vitae sin costo f&aacute;cil y sin intermediarios.");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
		return mapping.findForward(FORWARD_TEMPLATE_FORM);
	}
	
	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward programarEntrevista(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		EntrevistaBusDelegate entrevistaBusDelegate = EntrevistaBusDelegateImpl.getInstance();	
		SpaceCompanyForm spaceCompany = (SpaceCompanyForm) form;
		EntrevistaVO entrevistaVo = new EntrevistaVO();
		entrevistaVo.setIdCandidato(Utils.parseLong(request.getParameter("idCandidato")));
		entrevistaVo.setIdOfertaEmpleo(Utils.parseLong(request.getParameter("idOfertaEmpleo")));
		entrevistaVo.setEstatus(Constantes.ESTATUS.NUEVA.getIdOpcion());
		entrevistaVo.setFechaString(Utils.converterDate(spaceCompany.getEntrevistaVo().getFecha()));
		entrevistaVo.setHora(spaceCompany.getEntrevistaVo().getHora());
		try {
			entrevistaBusDelegate.save(entrevistaVo);
			enviarNotificacion(entrevistaVo);
		} catch (BusinessException e) {
			logger.error(e);
		} catch (ServiceLocatorException e) {
			logger.error(e);
		} catch (MailException e) {
			logger.error(e);
		}
        PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Detalle del candidato");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Reg&iacute;strate en nuestra Bolsa de trabajo y postula la oferta de empleo que m&aacute;s se apegue seg&uacute;n tu curriculum vitae sin costo f&aacute;cil y sin intermediarios.");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
		return mapping.findForward(FORWARD_TEMPLATE_FORM);
	}
	
	/**
	 * Mï¿½todo que notifica por correo electrï¿½nico la entrevista del candidato
	 * @param entrevistaVO
	 * @throws MailException
	 */
	private void enviarNotificacion(EntrevistaVO entrevistaVO) throws MailException{		
		NotificacionService notificacionService = new NotificacionService();
		notificacionService.notificacionEntrevista(entrevistaVO);
	}
	
	/**
	 * Mï¿½todo que asocia el candidato con la oferta con estatus de seleccionado
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
		request.getSession().setAttribute(TITULO_PAGINA, "Detalle del candidato");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Reg&iacute;strate en nuestra Bolsa de trabajo y postula la oferta de empleo que m&aacute;s se apegue seg&uacute;n tu curriculum vitae sin costo f&aacute;cil y sin intermediarios.");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
		return mapping.findForward(FORWARD_TEMPLATE_FORM);
	}	
	
	private String getDecoratedVideo(InformacionGeneralVO infoVo){
		String strDecorated = "No disponible";
		char cAmpersand = '&';
		if(infoVo.getUrlVideoEstatus() ==1){			
			String strUrlVideo = infoVo.getUrlVideoCV();
			logger.info("strUrlVideo :"+ strUrlVideo);
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
			logger.info("video strDecorated :"+ strDecorated);
			//http://www.youtube.com/watch?v=vhw2LZ46gWQ
			//http://www.youtube.com/watch?v=vhw2LZ46gWQ&feature=player_embedded
			//http://www.youtube.com/watch?v=Fo9ZuvkeKJI&feature=fvst
				
			//<iframe type="text/html" width="300" height="220" src="http://www.youtube.com/embed/5IDqMEs-ZGA" frameborder="0"> </iframe>			
			
		}
		
		return strDecorated;
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
	
	private void updatePendingPostByCandidate(long idCandidato) {
		List<OfertaCandidatoVO> ofertaCandidatoList;
		OfferBusDelegate offerService = OfferBusDelegateImpl.getInstance();
		try {
			ofertaCandidatoList = offerService.findAllOffersByCandidate(idCandidato);
			for (OfertaCandidatoVO oc : ofertaCandidatoList) {
				if (oc.getEstatus() == ESTATUS.POSTULADO.getIdOpcion() || oc.getEstatus() == ESTATUS.EN_PROCESO.getIdOpcion()) {
					oc.setEstatus(ESTATUS.CONTRATADO_EN_OTRA_OFERTA.getIdOpcion());
					offerService.update(oc);
				}
			}
		} catch (Exception e) {
			logger.error("Error updatePendingPostByCandidate ", e);
			e.printStackTrace();
		}
	}
	
	private boolean existPostPending(long idCandidato) {
		List<OfertaCandidatoVO> ofertaCandidatoList;
		OfferBusDelegate offerService = OfferBusDelegateImpl.getInstance();
		try {
			ofertaCandidatoList = offerService.findAllOffersByCandidate(idCandidato);
			for (OfertaCandidatoVO oc : ofertaCandidatoList) {
				if (null != oc.getFechaEntrevista()) {
					if (oc.getEstatus() == ESTATUS.POSTULADO.getIdOpcion())
						return true;
					else if (oc.getEstatus() == ESTATUS.EN_PROCESO.getIdOpcion()) {
						Calendar cal = Calendar.getInstance();
						cal.setTime(new Date());
						cal.add(Calendar.DATE, -1);
						if (oc.getFechaEntrevista().before(cal.getTime()))
							return true;
					}
				}
			}
		} catch (Exception e) {
			logger.error("Error existPostPending ", e);
			e.printStackTrace();
		}
		return false;
	}
	
	private void closePendingPostRelatedOffer(long idOfertaEmpleo) throws BusinessException, ServiceLocatorException {
		OfferBusDelegate offerService = OfferBusDelegateImpl.getInstance();
		offerService.closePendingPostRelatedOffer(idOfertaEmpleo);
	}
	
	private void actionsTakenAfterContract(long idOferta) {
		OfertaEmpleoVO ofertaVO = null;
		OfertaBusDelegate offerService = OfertaBusDelegateImpl.getInstance();
		try {
			ofertaVO = offerService.consultaOfertaEmpleo(idOferta);
			int awardedVacancies = null != ofertaVO.getPlazasCubiertas() ? ofertaVO.getPlazasCubiertas().intValue() + 1 : 1;
			ofertaVO.setPlazasCubiertas(awardedVacancies);
			offerService.update(ofertaVO);
			if (ofertaVO.getNumeroPlazas() == awardedVacancies) {
				offerService.actualizaEstatus(idOferta, ESTATUS.CUBIERTA.getIdOpcion());
				closePendingPostRelatedOffer(idOferta);
			}
		} catch (Exception e) {
			logger.error("Error in actionsTakenAfterContract", e);
			e.printStackTrace();
		}
	}
}