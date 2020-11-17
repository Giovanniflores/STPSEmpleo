package mx.gob.stps.portal.web.candidate.action;

import static mx.gob.stps.portal.web.infra.utils.Constantes.BODY_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.CANDIDATO_REG_MIS_DATOS;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_TEMPLATE_MI_ESP_CAND;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TAB_MENU;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TAB_MI_ESPACIO;
import static mx.gob.stps.portal.web.infra.utils.Constantes.USUARIO_APP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TITULO_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.DESCRIPCION_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FACEBOOK_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TWITTER_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.URL_ESPECIFICA;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.gob.stps.portal.core.candidate.vo.ConocerConfigVO;
import mx.gob.stps.portal.core.candidate.vo.PerfilLaboralVo;
import mx.gob.stps.portal.core.infra.utils.Constantes.VOLVER_PREGUNTAR;
import mx.gob.stps.portal.core.infra.utils.Utils;
import mx.gob.stps.portal.persistencia.vo.CandidatoQuebecVO;
import mx.gob.stps.portal.persistencia.vo.EstandarConocerVO;
import mx.gob.stps.portal.persistencia.vo.ExpectativaLaboralVO;
import mx.gob.stps.portal.utils.Catalogos;
import mx.gob.stps.portal.utils.Catalogos.MULTIREGISTRO;
import mx.gob.stps.portal.utils.WebUtils;
import mx.gob.stps.portal.web.candidate.delegate.CandidatoBusDelegate;
import mx.gob.stps.portal.web.candidate.delegate.CandidatoBusDelegateImpl;
import mx.gob.stps.portal.web.candidate.delegate.CandidatosRegistroBusDelegate;
import mx.gob.stps.portal.web.candidate.delegate.CandidatosRegistroBusDelegateImpl;
import mx.gob.stps.portal.web.infra.utils.CandidatoHeadUtil;
import mx.gob.stps.portal.web.infra.utils.PropertiesLoader;
import mx.gob.stps.portal.web.oferta.search.form.OfertasPorPerfilForm;
import mx.gob.stps.portal.web.offer.wrapper.PerfilJB;
import mx.gob.stps.portal.web.reg_unico.SingleRegisterBusDelegate;
import mx.gob.stps.portal.web.reg_unico.impl.SingleRegisterBusDelegateImpl;
import mx.gob.stps.portal.web.security.action.LoginAction;
import mx.gob.stps.portal.web.security.vo.UsuarioWebVO;
import mx.gob.stps.portal.ws.conocer.exception.ConocerWSException;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class CandidatosAction extends Action { 

	private static Logger logger = Logger.getLogger(CandidatosAction.class);
	
	private final static String CANDIDATO_NOTIFICADO = "CANDIDATO_NOTIFICADO";
	private final static String NOTIFICADO 			 = "NOTIFICADO";
	private final static String CANDIDATO_NOTIFICACION = "CANDIDATO_NOTIFICACION";
	private final static String PERFIL_SIN_HABILIDADES = "PERFIL_SIN_HABILIDADES";
	private final static String ACCEPT_LANGUAGE = "ACCEPT_LANGUAGE";
	private final static String FORWARD_ACTION_ADMIN_PARAMETROS 	= "ACTION_PARAMETROS";
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		OfertasPorPerfilForm ofertasForm = (OfertasPorPerfilForm)form;
		ActionForward forward = null; 
		
		try {
			
			UsuarioWebVO usuario = (UsuarioWebVO)session.getAttribute(USUARIO_APP);

			if (usuario!=null && CandidatoHeadUtil.isPerfilCandidato(usuario.getIdPerfil())) {
				
				/** Se verifica si se redirecciona al TAB de Mis Datos despues del registro del candidato **/
				String redirectMisDatos = request.getParameter(CANDIDATO_REG_MIS_DATOS);
				if (redirectMisDatos!=null && !redirectMisDatos.isEmpty()){
					return mapping.findForward("ACTION_MIS_DATOS");
				}
				
				CandidatosRegistroBusDelegate delegate = CandidatosRegistroBusDelegateImpl.getInstance();
				CandidatoQuebecVO applicant = delegate.findByID(usuario.getIdPropietario());
				if (null != applicant) request.getSession().setAttribute("_quebec", "done");
				
				CandidatoBusDelegate candidateServices = CandidatoBusDelegateImpl.getInstance();
				//InformacionGeneralVO infoVO = candidateServices.showCandidateDetail(usuario.getIdPropietario());
				PerfilLaboralVo perfilLaboral = candidateServices.consultaPerfilLaboral(usuario.getIdPropietario());
				CandidatoHeadUtil.actualizarEstatusPpcHeader(request, usuario);
				String urlVideoCV = null;
				int urlVideoEstatus = 0;
				
				if (perfilLaboral!=null){
					urlVideoCV 		= perfilLaboral.getUrlVideoc();
					urlVideoEstatus = perfilLaboral.getEstatusVideoc();
				}
				
				String strDecVideo = getDecoratedVideo(urlVideoCV, urlVideoEstatus);

		        request.setAttribute("decoratedVideo",strDecVideo);
		        request.setAttribute("idCandidato", usuario.getIdPropietario());
		        
 		        //int porcentajeCV  = infoVO.getPorcentajeCV();
		        //request.setAttribute("porcentajeCV",porcentajeCV);// Jmhg : cambio
				
				//request.setAttribute("detalle", infoVO);

				if (ofertasForm!=null){

				try {
					ofertasForm.setIdCandidato(usuario.getIdPropietario());

					/*
					OfferBusDelegate offerBusDelegate = OfferBusDelegateImpl.getInstance();
					List<OfertaPorPerfilVO> ofertasPerfil = offerBusDelegate.buscaOfertasRecomendadas(usuario.getIdPropietario());

					if (ofertasPerfil.size() > 5)
						ofertasPerfil = ofertasPerfil.subList(0, 5);				
					ofertasForm.setOfertas(ofertasPerfil);*/
				} catch (Exception e) { logger.error(e); }

				if (ofertasForm.getEstandares()==null || ofertasForm.getEstandares().isEmpty()){
					try{				
						request.getSession().removeAttribute("titulosCertificaciones");
						
						List<EstandarConocerVO> estandares = candidateServices.consultaConocer(usuario.getIdPropietario());
						if (!estandares.isEmpty())
							request.getSession().setAttribute("titulosCertificaciones", estandares);

						ofertasForm.setEstandares(estandares);
					}catch(ConocerWSException e){
						e.printStackTrace(); logger.error(e);
					}
				}

				// Datos de certificaciones (si tiene y ha activado la opción)
				request.removeAttribute("volverPreguntarConocer");			
				try {
					logger.info("Consultamos las preferencias sobre los estándares de Conocer del idCandidato="+usuario.getIdPropietario());
					ConocerConfigVO conocerConfigVO = candidateServices.consultaConocerConfigByIdCandidato(usuario.getIdPropietario());
					if (conocerConfigVO != null){
						if (conocerConfigVO.getVolverPreguntar()!=null &&
							conocerConfigVO.getVolverPreguntar().equals(Utils.toLong(VOLVER_PREGUNTAR.SI.getIdOpcion())))
							request.setAttribute("volverPreguntarConocer", String.valueOf(VOLVER_PREGUNTAR.SI.getIdOpcion()));
						else 
							request.setAttribute("volverPreguntarConocer", String.valueOf(VOLVER_PREGUNTAR.NO.getIdOpcion()));
					} else if (ofertasForm.getEstandares()!=null && !ofertasForm.getEstandares().isEmpty()) {
						request.setAttribute("volverPreguntarConocer", String.valueOf(VOLVER_PREGUNTAR.SI.getIdOpcion()));
					}
				}catch(Exception e){
					e.printStackTrace();
				}
				
				}
				
				// Se notifica al candidato si no cuenta con el perfil actualizado [nuevo catalogo de habilidades]
				String candidatoNoticado = (String)session.getAttribute(CANDIDATO_NOTIFICADO);
				SingleRegisterBusDelegate srbd = SingleRegisterBusDelegateImpl.getInstance();
				if (null == candidatoNoticado || !candidatoNoticado.equalsIgnoreCase(NOTIFICADO)){
					long idCandidato = usuario.getIdPropietario();
					//List<Long> idHabilidades = candidateServices.consultaHabilidades(idCandidato);
					List<ExpectativaLaboralVO> expectativas = srbd.expectativaLaboralList(idCandidato);
					for (ExpectativaLaboralVO expectativa : expectativas) {
						if (expectativa.getPrincipal() == MULTIREGISTRO.PRINCIPAL.getIdOpcion()) {
							if (null == expectativa.getIdAreaLaboralDeseada() || null == expectativa.getIdSubAreaLaboralDeseada()
									|| expectativa.getIdSubAreaLaboralDeseada() <=0) {
								request.setAttribute(CANDIDATO_NOTIFICACION, PERFIL_SIN_HABILIDADES);
								session.setAttribute(CANDIDATO_NOTIFICADO, NOTIFICADO);
							}
						}
					}
				}
				
				request.removeAttribute("volverPreguntarConocerSI");
				request.removeAttribute("volverPreguntarConocerNO");				
				request.setAttribute("volverPreguntarConocerSI", String.valueOf(VOLVER_PREGUNTAR.SI.getIdOpcion()));
				request.setAttribute("volverPreguntarConocerNO", String.valueOf(VOLVER_PREGUNTAR.NO.getIdOpcion()));
				
				session.setAttribute(BODY_JSP, mapping.getInput());

				PropertiesLoader properties = PropertiesLoader.getInstance();
				request.getSession().setAttribute(TITULO_PAGINA, "Mi espacio candidato");
				request.getSession().setAttribute(DESCRIPCION_PAGINA, "Espacio Candidato, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo");
				request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
				request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
				request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
				forward = mapping.findForward(FORWARD_TEMPLATE_MI_ESP_CAND);

				// Detect if it is Candidato CONALEP
				PerfilJB perfillJB = candidateServices.loadPerfil(usuario.getIdPropietario());
				if(perfillJB != null){
					request.setAttribute("ultAct", perfillJB.getPerfilVO().getUltimaActualizacion());
				}
				if (perfillJB.getGradoPrincipal().getIdNivelEstudio() == Catalogos.GRADO_ESTUDIOS.PROFESIONAL_TECNICO.getIdOpcion()) {
					request.setAttribute("esCandidatoConalep", true);
				}

			} else {
				long idPerfil = 0; 
				String username = "";
				
				if (usuario!=null){
					idPerfil = usuario.getIdPerfil(); 
					username = usuario.getUsuario();
				}
				
				LoginAction loginAction = new LoginAction();
				forward = loginAction.redirectSWB(idPerfil, mapping, request, response, username);
				//forward = mapping.findForward(FORWARD_ACTION_ADMIN_PARAMETROS);
			}
				
		}catch(Exception e){e.printStackTrace(); logger.error(e);}
		
		session.setAttribute(TAB_MENU, TAB_MI_ESPACIO);
		return forward;
	}

	

	private String getDecoratedVideo(String urlVideoCV, int urlVideoEstatus) {
		String strUrlVideo = "";
		String strDecorated = "No disponible";	
		
		if(null != urlVideoCV)
			strUrlVideo = urlVideoCV;
		else
			return strDecorated;
		
		if(urlVideoEstatus == 1){
			//logger.info("strUrlVideo :"+ strUrlVideo);
			if(strUrlVideo.contains("http://www.youtube.com/watch?v=")){
				String strTemp = strUrlVideo.replace("http://www.youtube.com/watch?v=", "http://www.youtube.com/embed/");
				//logger.info("video strTemp :"+ strTemp);
				int iFin = strTemp.indexOf("&");
				if(iFin>-1){
					strTemp = strTemp.substring(0,iFin);
					//logger.info("video strTemp :"+ strTemp);
				}				
				strDecorated =  strTemp; 
			} else if(strUrlVideo.contains("http://youtu.be/")){
				String strTemp = strUrlVideo.replace("http://youtu.be/", "http://www.youtube.com/embed/");				
				int iFin = strTemp.indexOf("?");
				if(iFin>-1){
					strTemp = strTemp.substring(0,iFin);
					//logger.info("video strTemp 2:"+ strTemp);
				}				
				strDecorated =  strTemp; 
			} else if(strUrlVideo.contains("http://www.youtube.com/embed/")) {
				strDecorated = strUrlVideo;
			} else {
				strDecorated = strUrlVideo;
			}
			//logger.info("video strDecorated :" + strDecorated);			
		}
		return strDecorated;
	}
}