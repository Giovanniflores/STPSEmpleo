
package mx.gob.stps.portal.web.offer.action;

import static mx.gob.stps.portal.web.infra.utils.Constantes.BODY_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_NEXT;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_REDIRECT_SWB;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_TEMPLATE_OFFER;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_TEMPLATE_REGISTRO_EMPRESA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.JSP_SUCCESS;
import static mx.gob.stps.portal.web.infra.utils.Constantes.URL_REDIRECT_SWB;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TITULO_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.DESCRIPCION_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FACEBOOK_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TWITTER_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.URL_ESPECIFICA;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.gob.stps.portal.core.candidate.vo.GradoAcademicoVO;
import mx.gob.stps.portal.core.candidate.vo.IdiomaVO;
import mx.gob.stps.portal.core.domicilio.vo.DomicilioVO;
import mx.gob.stps.portal.core.empresa.vo.EmpresaVO;
import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.mail.service.NotificacionService;
import mx.gob.stps.portal.core.infra.utils.Constantes.CONFIDENCIALIDAD;
import mx.gob.stps.portal.core.infra.utils.Constantes.CONTACTO_CORREO;
import mx.gob.stps.portal.core.infra.utils.Constantes.CONTACTO_TELEFONO;
import mx.gob.stps.portal.core.infra.utils.Constantes.ESTATUS;
import mx.gob.stps.portal.core.infra.utils.Constantes.EXPERIENCIA;
import mx.gob.stps.portal.core.infra.utils.Constantes.HABILIDADES_ACTITUDES;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_TELEFONO;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_USUARIO;
import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaCandidatoVO;
import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaEmpleoJB;
import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaPostulacionVO;
import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaVerDetalleVO;
import mx.gob.stps.portal.core.oferta.pregunta.vo.OfertaPreguntaVO;
import mx.gob.stps.portal.core.oferta.vo.OfertaEmpleoVO;
import mx.gob.stps.portal.core.seguridad.vo.UsuarioVO;
import mx.gob.stps.portal.persistencia.vo.ConocimientoHabilidadVO;
import mx.gob.stps.portal.persistencia.vo.TelefonoVO;
import mx.gob.stps.portal.utils.Catalogos;
import mx.gob.stps.portal.web.candidate.delegate.CandidatoBusDelegate;
import mx.gob.stps.portal.web.candidate.delegate.CandidatoBusDelegateImpl;
import mx.gob.stps.portal.web.company.delegate.RegisterBusDelegate;
import mx.gob.stps.portal.web.company.delegate.RegisterBusDelegateImpl;
import mx.gob.stps.portal.web.infra.action.GenericAction;
import mx.gob.stps.portal.web.infra.delegate.CatalogoOpcionDelegate;
import mx.gob.stps.portal.web.infra.delegate.CatalogoOpcionDelegateImpl;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.infra.utils.Constantes;
import mx.gob.stps.portal.web.infra.utils.HtmlUtils;
import mx.gob.stps.portal.web.infra.utils.PropertiesLoader;
import mx.gob.stps.portal.web.infra.utils.Utils;
import mx.gob.stps.portal.web.infra.vo.ResultVO;
import mx.gob.stps.portal.web.oferta.delegate.OfertaBusDelegate;
import mx.gob.stps.portal.web.oferta.delegate.OfertaBusDelegateImpl;
import mx.gob.stps.portal.web.offer.delegate.OfferBusDelegate;
import mx.gob.stps.portal.web.offer.delegate.OfferBusDelegateImpl;
import mx.gob.stps.portal.web.offer.form.OfferQuestionForm;
import mx.gob.stps.portal.web.offer.wrapper.OfertaJB;
import mx.gob.stps.portal.web.offer.wrapper.PerfilJB;
import mx.gob.stps.portal.web.security.action.LoginAction;
import mx.gob.stps.portal.web.security.delegate.SecutityDelegate;
import mx.gob.stps.portal.web.security.delegate.SecutityDelegateImpl;
import mx.gob.stps.portal.web.security.vo.UsuarioWebVO;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.thymeleaf.context.WebContext;


/**
 * @author Sergio Téllez
 *
 */
public final class PortalOfferDetailsAction extends GenericAction {
	
	private static final String ID_OFERTA_EMPLEO = Constantes.ID_OFERTA_EMPLEO;
	
	private static final String ID_OFERTA_PREGUNTA = "id_oferta_pregunta";
	private static final String COMPATIBILIDAD_ATTR = "fulfillment";
	
	private static Logger logger = Logger.getLogger(OfferDetailsAction.class);

	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		
		CandidatoBusDelegate service = CandidatoBusDelegateImpl.getInstance();		
		OfferQuestionForm offer = (OfferQuestionForm)form;
		offer.reset();
		
		int offerID = Utils.parseInt(request.getParameter(ID_OFERTA_EMPLEO));
		UsuarioWebVO usuario = getUsuario(request.getSession());
		
		if(offerID==-1){
    		String strIdOferta = (request.getSession().getAttribute(ID_OFERTA_EMPLEO)==null? "0": request.getSession().getAttribute(ID_OFERTA_EMPLEO).toString());  			
			offerID = Utils.parseInt(strIdOferta);
		}		
		if (offerID > -1) {		
			request.getSession().setAttribute(ID_OFERTA_EMPLEO, offerID);
		}  
		
		long idCandidato = 0;
		if(isLogged(request.getSession()) && null != usuario && usuario.getIdTipoUsuario() == TIPO_USUARIO.CANDIDATO.getIdTipoUsuario()) {
			request.getSession().setAttribute("_user", this.getUsuario(request.getSession()));
			idCandidato = usuario.getIdPropietario();
		}
		//se consulta si esta inscrito al ppc
		try {
			int ppcEstatus = service.consultarPpcEstatus(idCandidato);
			if (ppcEstatus==59 || ppcEstatus==60) offer.setInscritoPPC(true);
		} catch (PersistenceException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
		} catch (ServiceLocatorException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
		}		
		//request.getSession().removeAttribute("_urlpageinvoke");
		
		if (offerID > -1) {
						
			offer.setIdOfertaEmpleo(offerID);
			offer.setIdCandidato(idCandidato);

			OfertaJB ofertaJB = getoffer(offerID); /** Se consulta el detalle de la oferta de empleo **/
			offer.setOfertaJB(ofertaJB);
		
			increaseOfferCount(offerID); // Se contabiliza la consulta de la oferta
			
			List<OfertaPreguntaVO> preguntas = getOfertaPreguntasList(offerID);
			offer.setOfertaPreguntasList(preguntas);
			
			if (idCandidato > 0) {

				try {
					CandidatoBusDelegate services = CandidatoBusDelegateImpl.getInstance();
					PerfilJB profile = services.loadPerfil(idCandidato);
					
					if (null == profile) profile = new PerfilJB();
					offer.setPerfilJB(profile);
					
				} catch (Exception se) { se.printStackTrace(); logger.error(se);}

				int compatibility = compatibility(offerID, idCandidato);
				offer.setCompatibility(compatibility);

				// Se registra para su almacenamiento en caso de vincular al candidato
				request.getSession().setAttribute(COMPATIBILIDAD_ATTR, compatibility);
				
				boolean postulated = isPostulated(offerID, idCandidato);
				offer.setPostulated(postulated);
				
				int estatusOfertaCandidato = getEstatusOfertaCandidato(offerID, idCandidato);
				offer.getOfertaJB().setEstatusOfertaCandidato(Integer.toString(estatusOfertaCandidato));

				// si provenimos de miEspacioCandidato, agregamos el atributo _urlpageinvoke que será utilizado en offerdetails.jsp para
				// determinar si se muestra la liga "agregar a mis ofertas de empleo"
				if ("miEspacioCandidato.do".equals(request.getParameter("_urlpageinvoke")))
					request.getSession().setAttribute("_urlpageinvoke", "miEspacioCandidato.do");
			} else {
				logger.error("ERROR PortalOfferDetailsAction.init id_Candidato=0, id_oferta_empleo=" + offer.getIdOfertaEmpleo() + ", id_usuario=" + getUsuario(request.getSession()).getIdUsuario());
			}

			if ("ofertasRecientesTodas".equals(request.getParameter("_urlpageinvoke")))
				request.getSession().setAttribute("_urlpageinvoke", "ofertasRecientes.do?method=ofertasRecientesTodas");

			if ("ofertasDestacadasTodas".equals(request.getParameter("_urlpageinvoke")))
				request.getSession().setAttribute("_urlpageinvoke", "ofertasRecientes.do?method=ofertasDestacadasTodas");	

			if ("admonCandidatos".equals(request.getParameter("_urlpageinvoke")))
				request.getSession().setAttribute("_urlpageinvoke", "admonCandidatos.do?method=init&ido="+offerID);
		}
		
		request.setAttribute("ESTATUS_SELECCIONADA", Integer.toString(ESTATUS.SELECCIONADA.getIdOpcion()));
		request.setAttribute("ESTATUS_POSTULADO", Integer.toString(ESTATUS.POSTULADO.getIdOpcion()));
		request.setAttribute("ESTATUS_VINCULADO", Integer.toString(ESTATUS.VINCULADO.getIdOpcion()));
		request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
        return mapping.findForward("templateOfferDetail");
    }
	
	public ActionForward toRecuperaContrasena(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		PropertiesLoader properties = PropertiesLoader.getInstance();
		request.setAttribute(URL_REDIRECT_SWB, properties.getProperty("app.swb.redirect.login.recuperar"));
		return mapping.findForward(FORWARD_REDIRECT_SWB);
	}	
	
	public ActionForward loginUser(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			String idOfertaEmpleo = request.getParameter("idOfertaEmpleo");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			LoginAction loginAction = new LoginAction();		
			
			SecutityDelegate services = SecutityDelegateImpl.getInstance();
			
			UsuarioVO usuario = services.consultaUsuarioPorLogin(username);
			
			boolean isPswValid = false;
			
			if (usuario!=null)
				isPswValid = loginAction.validatePassword(usuario.getContrasena(), password);
			
			if(usuario!=null && isPswValid){
				logger.info("Usuario autorizado ("+ username +"), se firma dentro del Portal");
				loginAction.firmaUsuarioPortal(request, response, usuario);				
				PropertiesLoader properties = PropertiesLoader.getInstance();
        		String urlredirect = properties.getProperty("app.swb.redirect.detalle.oferta");
        		urlredirect += "?method=init&id_oferta_empleo="+ idOfertaEmpleo;     
        		//System.out.println("----LoginAction login urlredirect: " + urlredirect);
        		//logger.info("----LoginAction login urlredirect: " + urlredirect);
        		request.setAttribute(URL_REDIRECT_SWB, properties.getProperty(urlredirect));
        		return mapping.findForward("ACTION_OFERTA_PREVIA");      
			} else {
				logger.info("Usuario NO autorizado ("+ username +"), se direcciona a pagina de error");
				registraError(request, "seguridad.login.msg.pwd.no");			
				request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
		        PropertiesLoader properties = PropertiesLoader.getInstance();
				request.getSession().setAttribute(TITULO_PAGINA, "Detalle de oferta");
				request.getSession().setAttribute(DESCRIPCION_PAGINA, "Detalle de oferta, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo");
				request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
				request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
				request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
		        return mapping.findForward(FORWARD_TEMPLATE_OFFER);				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
	        return mapping.findForward(FORWARD_TEMPLATE_OFFER);
		}
    }		

	public ActionForward addQuestion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		String msg = null;
		String type = null;
		OfferQuestionForm offer = (OfferQuestionForm)form;
		/*ActionErrors errors = offer.validate(mapping, request);
		if (errors == null) {*/
			OfferBusDelegate services = OfferBusDelegateImpl.getInstance();
			OfertaPreguntaVO vo = getOfferQuestionVO(request, offer);
			if (null != vo && vo.getPregunta()!=null) {
				try {
					services.create(vo);
					msg = "La pregunta fue enviada correctamente";
					type = ResultVO.TYPE_SUCCESS;
				}catch(Exception e) { logger.error(e); }
			}else {
				msg = "Ha ocurrido un error, por favor intente más tarde";
				type = ResultVO.TYPE_ERROR;
			}
		/*}else {
			msg = "Debe escribir una pregunta";
			type = ResultVO.TYPE_ERROR;
		}*/
		try {
			String json = toJson(new ResultVO(msg, type));
			redirectJsonResponse(response, json);
		} catch (IOException e) {
			e.printStackTrace();
			request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
	        return mapping.findForward(FORWARD_TEMPLATE_OFFER);
		}
		return null;
    }
	
	public ActionForward answerQuestion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		OfferQuestionForm offer = (OfferQuestionForm)form;
		String respuesta = request.getParameter("respuesta");
		OfferBusDelegate services = OfferBusDelegateImpl.getInstance();
		long idOfertaPregunta = Utils.parseLong(request.getParameter("idOfertaPregunta"));
		if (null != respuesta && !"".equals(respuesta) && idOfertaPregunta > -1) {
			OfertaPreguntaVO vo = getOfertaPregunta(offer.getOfertaPreguntasList(), idOfertaPregunta);
			vo.setRespuesta(respuesta);
			vo.setFechaRespuesta(Calendar.getInstance().getTime());
			try {
				services.update(vo);
			} catch (BusinessException be) { be.printStackTrace();
			} catch (ServiceLocatorException se) { se.printStackTrace();}
		}
		return questionList(mapping, form, request, response);
    }
	
	public ActionForward questionList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		int idOfertaEmpleo = 0;
		OfferQuestionForm offer = (OfferQuestionForm)form;
		List<OfertaPreguntaVO> preguntas = new ArrayList<OfertaPreguntaVO>();
		if (null != request.getParameter(ID_OFERTA_EMPLEO))
			idOfertaEmpleo = Utils.parseInt(request.getParameter(ID_OFERTA_EMPLEO));
		else
			idOfertaEmpleo = (int)offer.getIdOfertaEmpleo();
		if (idOfertaEmpleo > 0) {
			preguntas = getOfertaPreguntasList(idOfertaEmpleo);
			offer.setOfertaJB(getoffer(idOfertaEmpleo));
		}
		offer.setOfertaPreguntasList(preguntas);
		request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_NEXT).getPath());
        PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Detalle de oferta");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Detalle de oferta, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
	    return mapping.findForward(FORWARD_TEMPLATE_OFFER);
    }
	
	public ActionForward answer(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		OfferQuestionForm offer = (OfferQuestionForm)form;
		int idOfertaPregunta = Utils.parseInt(request.getParameter(ID_OFERTA_PREGUNTA));
		if(isLogged(request.getSession()))
			request.getSession().setAttribute("_user", this.getUsuario(request.getSession()));
		offer.setIdOfertaPregunta(idOfertaPregunta);
		offer.setPregunta(getPregunta(offer.getOfertaPreguntasList(), idOfertaPregunta));
		request.getSession().setAttribute(BODY_JSP, mapping.findForward(JSP_SUCCESS).getPath());
        PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Detalle de oferta");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Detalle de oferta, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
	    return mapping.findForward(FORWARD_TEMPLATE_OFFER);
    }
	
	private OfertaPreguntaVO getOfertaPregunta(List<OfertaPreguntaVO> preguntas, long idOfertaPregunta) {
		Iterator<OfertaPreguntaVO> it = preguntas.iterator();
		while (it.hasNext()) {
			OfertaPreguntaVO pregunta = it.next();
			if (pregunta.getIdOfertaPregunta() == idOfertaPregunta)
				return pregunta;
		}
		return null;
	}
	
	private String getPregunta(List<OfertaPreguntaVO> preguntas, long idOfertaPregunta) {
		Iterator<OfertaPreguntaVO> it = preguntas.iterator();
		while (it.hasNext()) {
			OfertaPreguntaVO pregunta = it.next();
			if (pregunta.getIdOfertaPregunta() == idOfertaPregunta)
				return pregunta.getPregunta();
		}
		return "";
	}
	
	public ActionForward addToMyOffers(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		String msg = null;
		String msgPopUp = null;
		String type = null;
		OfferBusDelegate services = OfferBusDelegateImpl.getInstance();
		try {
			long idCandidato = Utils.parseLong(request.getParameter("idCandidato"));
			long idOfertaEmpleo = Utils.parseLong(request.getParameter("idOfertaEmpleo"));
			List<OfertaCandidatoVO> list = services.findByOfferCandidate(idOfertaEmpleo, idCandidato);
			if (null == list || list.isEmpty()) {
				OfertaCandidatoVO vo = new OfertaCandidatoVO();
				vo.setEstatus(ESTATUS.SELECCIONADA.getIdOpcion());
				vo.setFechaAlta(Calendar.getInstance().getTime());
				vo.setIdCandidato(idCandidato);
				vo.setIdOfertaEmpleo(idOfertaEmpleo);
				vo.setCompatibilidad(recuperaCompatibilidad(request, idOfertaEmpleo, idCandidato));
				
				services.create(vo);
			}
			msg = "<p>La oferta se agregó a <br /><strong>Mis ofertas de empleo</strong> exitosamente.</p>";
			msgPopUp = "La oferta se agregó a Mis ofertas de empleo exitosamente.";
			type = ResultVO.TYPE_SUCCESS;
		}catch(Exception e) { logger.error(e); }
		try {
			
			List<ResultVO> listaResultado = new ArrayList<ResultVO>();
			listaResultado.add(new ResultVO(msg, type));
			listaResultado.add(new ResultVO(msgPopUp, type));		
			String json = toJson(listaResultado);
			redirectJsonResponse(response, json);
		} catch (IOException e) {
			e.printStackTrace();
			request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
	        return mapping.findForward(FORWARD_TEMPLATE_OFFER);
		}
		return null;
    }
	
	public ActionForward offerPost(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		OfferQuestionForm offer = (OfferQuestionForm)form;
		OfferBusDelegate services = OfferBusDelegateImpl.getInstance();
		request.removeAttribute("postuladoOK");
		boolean inscritoPPC = offer.isInscritoPPC();
		
		
		try {
			List<OfertaCandidatoVO> list = services.findByOfferCandidate(offer.getIdOfertaEmpleo(), offer.getIdCandidato());
	    	byte[] buf = null;

			if (null == list || list.isEmpty()) {
				if(offer.getIdCandidato() > 0){
					OfertaCandidatoVO vo = new OfertaCandidatoVO();
					vo.setFechaAlta(Calendar.getInstance().getTime());
					vo.setIdCandidato(offer.getIdCandidato());
					vo.setIdOfertaEmpleo(offer.getIdOfertaEmpleo());
					vo.setEstatus(ESTATUS.POSTULADO.getIdOpcion());
					vo.setCompatibilidad(recuperaCompatibilidad(request, vo.getIdOfertaEmpleo(), vo.getIdCandidato()));
					
					services.create(vo);
					//CREACION DEL PDF
					buf = generarCartaPresentacion(form, request, response);					
					generarNotificacionPostulacion(vo, buf, inscritoPPC);											
				}

			}else {

				for (OfertaCandidatoVO ofertaCandidato :list) {
					if (ofertaCandidato==null) continue;
					
					if (ofertaCandidato.getEstatus() < ESTATUS.POSTULADO.getIdOpcion() && ofertaCandidato.getIdCandidato()>0)
						ofertaCandidato.setEstatus(ESTATUS.POSTULADO.getIdOpcion());

					services.update(ofertaCandidato);
					//CREACION DEL PDF
					buf = generarCartaPresentacion(form, request, response);
					generarNotificacionPostulacion(ofertaCandidato, buf, inscritoPPC);	
				}
			}

			offer.setPostulated(true);

			increasePostCount(offer.getIdOfertaEmpleo());

			request.setAttribute("postuladoOK", true);

			long idOfertaEmpleo = offer.getIdOfertaEmpleo();
			/** Se envia notificacion de postulacion **/
			/*FIXME quitar			
			String idContacto = null;

			OfertaJB ofertajb = offer.getOfertaJB();
			if (ofertajb!=null){
				idContacto = ofertajb.getIdContacto();
			}
			*/
			avisoCandidatoPostuladoAction(idOfertaEmpleo, request);

		}catch(Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		
		//request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
		//request.getSession().setAttribute(BODY_JSP, mapping.findForward("DESPUES_POSTULACION").getPath());		
		//return mapping.findForward(FORWARD_TEMPLATE_OFFER);

		request.getSession().setAttribute(BODY_JSP, mapping.findForward("CANDIDATO_POSTULADO").getPath());
        PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Detalle de oferta");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Detalle de oferta, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
		return mapping.findForward(FORWARD_TEMPLATE_OFFER);
		//return mapping.findForward(FORWARD_TEMPLATE_REGISTRO_CANDIDATO);        
    }
	
	private boolean isPostulated(long idOfertaEmpleo, long idCandidato) {
		boolean postulated = false;
		OfferBusDelegate services = OfferBusDelegateImpl.getInstance();
		try {
			List<OfertaCandidatoVO> list = services.findByOfferCandidate(idOfertaEmpleo, idCandidato);
			if (null != list && !list.isEmpty()) {
				Iterator<OfertaCandidatoVO> it = list.iterator();
				while (it.hasNext()) {
					OfertaCandidatoVO vo = it.next();
					
					if (null != vo && vo.getEstatus() >= ESTATUS.POSTULADO.getIdOpcion())
						postulated = true;
				}
			}
		}catch(Exception e) { logger.error(e); }
		return postulated;
	}
	
	private int getEstatusOfertaCandidato(long idOfertaEmpleo, long idCandidato) {
		OfferBusDelegate services = OfferBusDelegateImpl.getInstance();
		
		int estatus = 0;
		
		try {
			List<OfertaCandidatoVO> list = services.findByOfferCandidate(idOfertaEmpleo, idCandidato);
			if (list != null) {
				for (OfertaCandidatoVO vo :list){
					if (vo != null)
						estatus = vo.getEstatus();
				}
			}
		}catch(Exception e) { logger.error(e); }
		return estatus;
	}	
	
	
	/*
	public ActionForward paintLogo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("image/jpeg");
		//OfferQuestionForm offer = (OfferQuestionForm)form;
		//OfertaEmpleoJB jb = offer.getOfertaEmpleoJB();
		try {
			//byte[] content = jb.getEmpresaLogo();
			FileInputStream in = new FileInputStream("D:/eclipse/workspace/empleoweb/jsp/portal/monitoring/logo_bimbo.gif");   
			int size = in.available();
			byte[] content = new byte[size];   
			in.read(content);
			OutputStream out = response.getOutputStream();
			out.write(content);
			out.close();
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	*/
	
	private void increaseOfferCount(int idOfertaEmpleo) {
		OfferBusDelegate services = OfferBusDelegateImpl.getInstance();
		try {
			OfertaVerDetalleVO offerDetailVO = services.findByCount(idOfertaEmpleo, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH));
			if (null != offerDetailVO) {
				int count = offerDetailVO.getContador();
				offerDetailVO.setContador(count + 1);
				services.update(offerDetailVO);
			}else {
				offerDetailVO = new OfertaVerDetalleVO();
				offerDetailVO.setAnio(Calendar.getInstance().get(Calendar.YEAR));
				offerDetailVO.setContador(1);
				offerDetailVO.setIdOfertaEmpleo(idOfertaEmpleo);
				offerDetailVO.setMes(Calendar.getInstance().get(Calendar.MONTH));
				services.create(offerDetailVO);
			}
		}catch(Exception e) { logger.error(e); }
	}
	
	private void increasePostCount(long idOfertaEmpleo) {
		OfferBusDelegate services = OfferBusDelegateImpl.getInstance();
		try {
			OfertaPostulacionVO vo = services.findOPById(idOfertaEmpleo, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH));
			if (null != vo) {
				int count = vo.getContador();
				vo.setContador(count + 1);
				services.update(vo);
			}else {
				vo = new OfertaPostulacionVO();
				vo.setAnio(Calendar.getInstance().get(Calendar.YEAR));
				vo.setContador(1);
				vo.setIdOfertaEmpleo(idOfertaEmpleo);
				vo.setMes(Calendar.getInstance().get(Calendar.MONTH));
				services.create(vo);
			}
		}catch(Exception e) { logger.error(e); }
	}
	
	private OfertaPreguntaVO getOfferQuestionVO(HttpServletRequest request, OfferQuestionForm form) {
		OfertaPreguntaVO vo = null;
		long idCandidato = Utils.parseLong(request.getParameter("idCandidato"));
		long idOfertaEmpleo = Utils.parseLong(request.getParameter("idOfertaEmpleo"));
		if (idCandidato > -1 && idOfertaEmpleo > -1)
			vo = new OfertaPreguntaVO(idOfertaEmpleo, idCandidato, form.getPregunta(), "", Calendar.getInstance().getTime(), null);
		return vo;
	}
	
	//Compatibility class
	private int compatibility(long idOfertaEmpleo, long idCandidato) {
		int compatibility = 0;
		if (idOfertaEmpleo > 0 && idCandidato > 0) {
			try {
				OfferBusDelegate services = OfferBusDelegateImpl.getInstance();
				compatibility = services.match(idOfertaEmpleo, idCandidato);
			} catch (ServiceLocatorException se) { se.printStackTrace(); }
		}
		return compatibility;
	}
	
	private OfertaJB getoffer(long offerID) {
		OfertaJB offer = new OfertaJB();

		try {
			OfferBusDelegate services = OfferBusDelegateImpl.getInstance();
			OfertaEmpleoJB jb = services.buscarOfertaEmpleo(offerID);
		
			BeanUtils.copyProperties(offer, jb);
		}catch (BusinessException be) { logger.error(be);
		}catch (ServiceLocatorException le) { logger.error(le);
		} catch (IllegalAccessException iae) { iae.printStackTrace();
		} catch (InvocationTargetException ite) { ite.printStackTrace();}

		return offer;
	}
	
	private List<OfertaPreguntaVO> getOfertaPreguntasList(long offerID) {
		ArrayList<OfertaPreguntaVO> list = null;
		try {
			OfferBusDelegate services = OfferBusDelegateImpl.getInstance();
			list = (ArrayList<OfertaPreguntaVO>)services.ofertaPreguntasList(offerID);
		}catch (BusinessException be) { logger.error(be);
		}catch (ServiceLocatorException le) { logger.error(le); }

		if (null == list)
			list = new ArrayList<OfertaPreguntaVO>();
		
		return list;
	}

	public ActionForward returnHome(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		PropertiesLoader properties = PropertiesLoader.getInstance();
		request.setAttribute(URL_REDIRECT_SWB, properties.getProperty("app.swb.redirect.home"));
		return mapping.findForward(FORWARD_REDIRECT_SWB);		
	}
	
	
	private String generarCurriculum(PerfilJB perfilCandidato, String candidatoNombre){
		StringBuilder curriculum = new StringBuilder();
		curriculum.append(candidatoNombre +"<br/>");
		if(perfilCandidato!=null){
			if (perfilCandidato.getEdad()>0 ){
				curriculum.append("<b>Edad:</b>" + perfilCandidato.getEdad() + "<br/>");
			}		
			curriculum.append("<b>Teléfono:</b> ");
			if (perfilCandidato.getPerfilLaboral().getContactoTelefono() == CONTACTO_TELEFONO.NO.getIdContactoTelefono()) {
				curriculum.append(perfilCandidato.getPrincipal().getAcceso() + 
						"-" + perfilCandidato.getPrincipal().getClave() + "-" + perfilCandidato.getPrincipal().getTelefono());
				if(null!=perfilCandidato.getPrincipal().getExtension() && !perfilCandidato.getPrincipal().getExtension().equalsIgnoreCase("")){
					curriculum.append(" ext. " + perfilCandidato.getPrincipal().getExtension());
				}
			}
			curriculum.append("<br/>");
		
			if(perfilCandidato.getPrincipal().getIdTipoTelefono() == TIPO_TELEFONO.FIJO.getIdOpcion()){
				curriculum.append("<b>Tipo de teléfono:</b>" + TIPO_TELEFONO.FIJO.getOpcion());
			} else if(perfilCandidato.getPrincipal().getIdTipoTelefono() == TIPO_TELEFONO.CELULAR.getIdOpcion()){
				curriculum.append("<b>Tipo de teléfono:</b>" + TIPO_TELEFONO.CELULAR.getOpcion());
			}				
			curriculum.append("<br/><b>Horario de contacto:</b>" + perfilCandidato.getHorarioLlamar());						
			curriculum.append("<br/><b>Correo electrónico:</b>");
			if (perfilCandidato.getPerfilLaboral().getContactoCorreo() == CONTACTO_CORREO.NO.getIdContactoCorreo()){
				curriculum.append(perfilCandidato.getCorreoElectronico());
			}
			curriculum.append("<br/>");		
			curriculum.append("<b>Dirección:</b>" + perfilCandidato.getColonia());
			curriculum.append("<br/><b>Código postal:</b>" + perfilCandidato.getCodigoPostal());						
			curriculum.append("<br/><b>Entidad:</b>" + perfilCandidato.getEntidad());
			curriculum.append("<br/><b>Municipio o delegación:</b>" + perfilCandidato.getMunicipio());
			curriculum.append("<br/><b>Disponibilidad para viajar:</b>" + 
			(perfilCandidato.getPerfilLaboral().getDisponibilidadViajar() > 0 ? mx.gob.stps.portal.core.oferta.detalle.helper.Utils.getLblDisponibilidad(perfilCandidato.getPerfilLaboral().getDisponibilidadViajar()) : ""));
			curriculum.append("<br/><b>Disponibilidad para radicar fuera:</b> " + 
			(perfilCandidato.getPerfilLaboral().getDisponibilidadRadicar() > 0 ? mx.gob.stps.portal.core.oferta.detalle.helper.Utils.getLblDisponibilidad(perfilCandidato.getPerfilLaboral().getDisponibilidadRadicar()) : ""));		
			//FORMACION PROFESIONAL
			curriculum.append("<br/><br/><b>Formación profesional</b><br/>");
			curriculum.append("<b>Grado de estudios:</b>" + (null != perfilCandidato.getGradoPrincipal().getNivel() ? perfilCandidato.getGradoPrincipal().getNivel() : "") + "<br/>");
			curriculum.append("<b>Carrera o especialidad:</b>" + (null != perfilCandidato.getGradoPrincipal().getCarrera() ? perfilCandidato.getGradoPrincipal().getCarrera() : "") + "<br/>");
			curriculum.append("<b>Escuela de procedencia:</b>" + (null != perfilCandidato.getGradoPrincipal().getEscuela() ? perfilCandidato.getGradoPrincipal().getEscuela() : "") + "<br/>");
			curriculum.append("<b>Año de inicio:</b>" + (perfilCandidato.getGradoPrincipal().getInicio()> 0 ? perfilCandidato.getGradoPrincipal().getInicio() : "") + "<br/>");
			curriculum.append("<b>Año de terminación:</b>" + (perfilCandidato.getGradoPrincipal().getFin()> 0 ? perfilCandidato.getGradoPrincipal().getFin() : "") + "<br/>");
			curriculum.append("<b>Situación académica:</b>" + (null != perfilCandidato.getGradoPrincipal().getSituacion() ? perfilCandidato.getGradoPrincipal().getSituacion() : "") + "<br/>");				
			//CONOCIMIENTOS PRINCIPALES
			if (perfilCandidato.getIdiomaPrincipal()!=null ){
				curriculum.append("<b>Idioma:</b>" + (null != perfilCandidato.getIdiomaPrincipal().getIdioma() ? perfilCandidato.getIdiomaPrincipal().getIdioma() : "") + "<br/>");
				curriculum.append("<b>Certificación:</b>" + (null != perfilCandidato.getIdiomaPrincipal().getCertificacion() ? perfilCandidato.getIdiomaPrincipal().getCertificacion() : "") + "<br/>");
				curriculum.append("<b>Dominio:</b>" + (null != perfilCandidato.getIdiomaPrincipal().getDominio() ? perfilCandidato.getIdiomaPrincipal().getDominio() : "") + "<br/>");
			}
			curriculum.append("<b>Conocimientos:</b>" + (null != perfilCandidato.getConocimientoPrincipal().getConocimientoHabilidad() ? perfilCandidato.getConocimientoPrincipal().getConocimientoHabilidad() : "") + "<br/>");
			curriculum.append("<b>Dominio:</b>" + (null != perfilCandidato.getConocimientoPrincipal().getDominio() ? perfilCandidato.getConocimientoPrincipal().getDominio() : "") + "<br/>");
			curriculum.append("<b>Descripción:</b>" + (null != perfilCandidato.getConocimientoPrincipal().getDescripcion() ? perfilCandidato.getConocimientoPrincipal().getDescripcion() : "") + "<br/>");
			curriculum.append("<b>Experiencia:</b>" + (null != perfilCandidato.getConocimientoPrincipal().getExperiencia() ? perfilCandidato.getConocimientoPrincipal().getExperiencia() : "") + "<br/>");
			curriculum.append("<b>Habilidades:</b>" + (null != perfilCandidato.getHabilidadPrincipal().getConocimientoHabilidad() ? perfilCandidato.getHabilidadPrincipal().getConocimientoHabilidad() : "") + "<br/>");
			curriculum.append("<b>Dominio:</b>" + (null != perfilCandidato.getHabilidadPrincipal().getDominio() ? perfilCandidato.getHabilidadPrincipal().getDominio() : "") + "<br/>");
			curriculum.append("<b>Descripción:</b>" + (null != perfilCandidato.getHabilidadPrincipal().getDescripcion() ? perfilCandidato.getHabilidadPrincipal().getDescripcion() : "") + "<br/>");
			curriculum.append("<b>Experiencia:</b>" + (null != perfilCandidato.getHabilidadPrincipal().getExperiencia() ? perfilCandidato.getHabilidadPrincipal().getExperiencia() : "") + "<br/>");		
			//EXPERIENCIA RELEVANTE
			curriculum.append("<br/><b>Experiencia relevante</b><br/>");		
			curriculum.append("<b>Experiencia total:</b>" + (perfilCandidato.getPerfilLaboral().getIdExperienciaTotal() > 0 ? EXPERIENCIA.getDescripcion((int)perfilCandidato.getPerfilLaboral().getIdExperienciaTotal()) : "") + "<br/>");
			curriculum.append("<b>Sector de mayor experiencia:</b>" + (null != perfilCandidato.getSector() ? perfilCandidato.getSector() : "") + "<br/>");
			curriculum.append("<b>Área de mayor experiencia:</b>" + (null != perfilCandidato.getArea() ? perfilCandidato.getArea() : "") + "<br/>");
			curriculum.append("<b>Puesto con mayor experiencia:</b>" + (null != perfilCandidato.getPerfilLaboral().getPuestoMayorExpr() ? perfilCandidato.getPerfilLaboral().getPuestoMayorExpr() : "") + "<br/>");
			curriculum.append("<b>Ocupación con mayor experiencia:</b>" + (null != perfilCandidato.getOcupacion() ? perfilCandidato.getOcupacion() : "") + "<br/>");		
			//System.out.println("-----curriculum:" + curriculum.toString());			
		} 
		return curriculum.toString();
	}
	
	private void generarNotificacionPostulacion(OfertaCandidatoVO vo, byte[] buf, boolean inscritoPPC){
		String candidatoNombre = "";
		String candidatoEmail = "";
		String tituloOferta = "";
		String ofertaCorreoElectronicoContacto = "";
		String ofertaNombreEmpresa = "";

		long idCandidato = vo.getIdCandidato();	
		long idOfertaEmpleo = vo.getIdOfertaEmpleo();	

		logger.info("---OfferDetailsAction.generarNotificacionPostulacion idCandidato:" + idCandidato + " idOfertaEmpleo:" + idOfertaEmpleo);
		try {			
			OfertaEmpleoVO oferta = OfertaBusDelegateImpl.getInstance().consultaOfertaEmpleo(idOfertaEmpleo);
			 Date date = Calendar.getInstance().getTime();
		     SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy");	
		     String today = formatter.format(date);			
		      
			//if(oferta.getContactoCorreo() == Catalogos.CONTACTO_CORREO.SI.getIdContactoCorreo()){
				
				if(null!=oferta.getTituloOferta() && !oferta.getTituloOferta().equalsIgnoreCase("")){
					tituloOferta = oferta.getTituloOferta();
				}
				
				if(null!=oferta.getCorreoElectronicoContacto() && !oferta.getCorreoElectronicoContacto().equalsIgnoreCase("")){
					ofertaCorreoElectronicoContacto = oferta.getCorreoElectronicoContacto();
				}					
				
				if(null!=oferta.getNombreEmpresa() && !oferta.getNombreEmpresa().equalsIgnoreCase("")){
					ofertaNombreEmpresa = oferta.getNombreEmpresa();
				}			
				
				CandidatoBusDelegate candidateServices = CandidatoBusDelegateImpl.getInstance();
				
				PerfilJB perfilCandidato = candidateServices.loadPerfil(idCandidato);

				if(null!=perfilCandidato.getNombre() && !perfilCandidato.getNombre().equalsIgnoreCase("")){
					candidatoNombre = candidatoNombre + " " + perfilCandidato.getNombre();
				}					
				if(null!=perfilCandidato.getApellido1() && !perfilCandidato.getApellido1().equalsIgnoreCase("")){
					candidatoNombre = candidatoNombre + " " + perfilCandidato.getApellido1();
				}				
				if(null!=perfilCandidato.getApellido2() && !perfilCandidato.getApellido2().equalsIgnoreCase("")){
					candidatoNombre = candidatoNombre + " " + perfilCandidato.getApellido2();
				}			

				if(null!=perfilCandidato.getCorreoElectronico() && !perfilCandidato.getCorreoElectronico().equalsIgnoreCase("")){
					candidatoEmail = perfilCandidato.getCorreoElectronico();
				}					
				
				if(null!=oferta.getTituloOferta() && !oferta.getTituloOferta().equalsIgnoreCase("")){
					tituloOferta = oferta.getTituloOferta();
				}
				
				if(null!=oferta.getCorreoElectronicoContacto() && !oferta.getCorreoElectronicoContacto().equalsIgnoreCase("")){
					ofertaCorreoElectronicoContacto = oferta.getCorreoElectronicoContacto();
				}					
				
				if(null!=oferta.getNombreEmpresa() && !oferta.getNombreEmpresa().equalsIgnoreCase("")){
					ofertaNombreEmpresa = oferta.getNombreEmpresa();
				}			

				RegisterBusDelegate empresaService = RegisterBusDelegateImpl.getInstance();  

				EmpresaVO empresaVO = empresaService.findEmpresaById(oferta.getIdEmpresa());

				if (empresaVO!=null && empresaVO.getCorreoElectronico()!=null && !empresaVO.getCorreoElectronico().isEmpty()){

					String pdfOutputName = "CP"+"_"+ perfilCandidato.getCurp()+"_"+today+".pdf";

					NotificacionService notificacionService = new NotificacionService();
					notificacionService.notificacionPostuladoToEmpresa(empresaVO, vo, candidatoNombre, candidatoEmail, 
							tituloOferta, ofertaCorreoElectronicoContacto, ofertaNombreEmpresa, buf, pdfOutputName, inscritoPPC);
				}
				
		//	}
		} catch (BusinessException e) {
			e.printStackTrace(); logger.error(e);
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		} /*catch (MailException e) {
			e.printStackTrace();
		} */catch (SQLException e) {
			e.printStackTrace();  logger.error(e);
		} catch (Exception e) {
			e.printStackTrace();  logger.error(e);
		}
	}

	/*

	public ActionForward ofertasEmpresa(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		long idEmpresa = 0;
		List<OfertaEmpleoVO> offers = null;
		OfertaEmpleoJB jb = new OfertaEmpleoJB();
		OfertaJB offerJB = new OfertaJB();
		OfferQuestionForm offer = (OfferQuestionForm)form;
		if (null != request.getParameter("idEmpresa")) {
			idEmpresa = Utils.parseLong(request.getParameter("idEmpresa"));
			if (!isLogged(request.getSession())) {
				if (idEmpresa > -1) {
					jb.setIdEmpresa(idEmpresa);
					offerJB.setEmpresaNombre(jb.getEmpresaNombre());
					offerJB.setIdEmpresa(jb.getIdEmpresa());
					if (null != jb.getEmpresa())
						offerJB.setEmpresaOfrece(jb.getEmpresa().getDescripcion());
					OfferBusDelegate offerService = OfferBusDelegateImpl.getInstance();
					try {
						offers = offerService.listaOfertasEstatus(idEmpresa, Constantes.ESTATUS_OFERTA_ACTIVA);
					} catch (BusinessException be) { be.printStackTrace();
					} catch (ServiceLocatorException se) { se.printStackTrace();}
				}
			}
		}
		offer.setOfertaJB(offerJB);
		offer.setOffers(offers);
		request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_REINTENTAR).getPath());
        return mapping.findForward(FORWARD_TEMPLATE_OFFER);
	}*/
	/**/
	
	public ActionForward avisoCandidatoPostuladoAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		
		OfferQuestionForm offer = (OfferQuestionForm)form;
		
		long idOfertaEmpleo = offer.getIdOfertaEmpleo();
		/*FIXME quitar
		String idContacto = null;
		
		OfertaJB ofertajb = offer.getOfertaJB();
		if (ofertajb!=null){
			idContacto = ofertajb.getIdContacto();	
		}
		*/
		avisoCandidatoPostuladoAction(idOfertaEmpleo, request);
		
		request.getSession().setAttribute(BODY_JSP, mapping.findForward("CANDIDATO_POSTULADO").getPath());
        PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Detalle de oferta");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Detalle de oferta, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
		return mapping.findForward(FORWARD_TEMPLATE_OFFER);
		//return mapping.findForward(FORWARD_TEMPLATE_REGISTRO_CANDIDATO);
	}

	public void avisoCandidatoPostuladoAction(long idOfertaEmpleo, HttpServletRequest request) {

		if (idOfertaEmpleo > 0) {

			//OfertaJB ofertajb = offer.getOfertaJB();			
			try {
				
				OfertaBusDelegate ofertaServices = OfertaBusDelegateImpl.getInstance();
				OfertaEmpleoVO oferta = ofertaServices.consultaOfertaEmpleo(idOfertaEmpleo);

				if (oferta != null) {
					request.setAttribute("nombreContacto", oferta.getNombreContacto());					
					request.setAttribute("correoElectronico", oferta.getCorreoElectronicoContacto());					
					request.setAttribute("telefonos", oferta.getTelefonos());
					
					DomicilioVO domicilioVO = oferta.getDomicilio();
					if (domicilioVO != null) {
						
						String domicilio = new String();

						if (domicilioVO.getCalle()!=null && !"".equals(domicilioVO.getCalle()))
							domicilio = domicilioVO.getCalle();		
						
						if(domicilioVO.getNumeroExterior() != null && 
						   domicilio!=null && !"".equals(domicilio) && 
						   domicilioVO.getNumeroExterior()!=null    && !"".equals(domicilioVO.getNumeroExterior()))							
						    domicilio = domicilio + " No. " + domicilioVO.getNumeroExterior();					
						
						if (domicilioVO.getColonia() != null && !"".equals(domicilioVO.getColonia())){
							if(!"".equals(domicilio))
								domicilio = domicilio + ", ";
							domicilio = domicilio + "Colonia " + domicilioVO.getColonia();
						}

						if (domicilioVO.getCodigoPostal() != null && !"".equals(domicilioVO.getCodigoPostal())){							
							if(!"".equals(domicilio))
								domicilio = domicilio + ", ";							
							domicilio = domicilio + "C.P. " + domicilioVO.getCodigoPostal();
						}

						if (domicilioVO.getMunicipio() != null && !"".equals(domicilioVO.getMunicipio())){
							if(!"".equals(domicilio))
								domicilio = domicilio + ", ";
							domicilio = domicilio + "Delegacion " + domicilioVO.getMunicipio();
						}

						if (domicilioVO.getEntidad() != null && !"".equals(domicilioVO.getEntidad())){
							if(!"".equals(domicilio))
								domicilio = domicilio + ", ";
							domicilio = domicilio + domicilioVO.getEntidad();
						}
						request.setAttribute("domicilio", domicilio);
					}				
				}
			} catch(Exception e){
				logger.error("Error al recuperar los datos de contacto de la oferta de empleo");
				e.printStackTrace();
			}

		}
	}
	
	public ActionForward postulado(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		request.getSession().setAttribute(BODY_JSP, mapping.findForward("OFERTA_POSTULADO").getPath());
        PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Detalle de oferta");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Detalle de oferta, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
		return mapping.findForward(FORWARD_TEMPLATE_OFFER);
	}
	
	public ActionForward regresaDetalle(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
        PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Detalle de oferta");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Detalle de oferta, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
	    return mapping.findForward(FORWARD_TEMPLATE_OFFER);
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
	
	public byte[] generarCartaPresentacion(ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        UsuarioWebVO usuario = getUsuario(request.getSession());
		OfferQuestionForm offer = (OfferQuestionForm)form;	        
		OfertaEmpleoVO oferta = null;
		try {
			oferta = OfertaBusDelegateImpl.getInstance().consultaOfertaEmpleo(offer.getIdOfertaEmpleo());
		} catch (ServiceLocatorException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        TIPO_USUARIO tipoUsuario = TIPO_USUARIO.forIdTipoUsuario((int) usuario.getIdTipoUsuario());

        CandidatoBusDelegate services = CandidatoBusDelegateImpl.getInstance();
        CatalogoOpcionDelegate catService = CatalogoOpcionDelegateImpl.getInstance();

        long idCandidato = usuario.getIdPropietario();
        logger.debug(String.format("idCandidato: %d", idCandidato));

        PerfilJB perfil = null;

        String CONFIDENCIAL = "Confidencial";

        // Prepare the Thymeleaf evaluation context
        final WebContext ctx = new WebContext(request, response, request.getServletContext());

        // Carga del template
        String templateName ="cartaPresentacion/cartaTemplate";
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");			
		//
		Date fechaInicio = oferta.getFechaInicio();
		String fechaIni = format.format(fechaInicio);
					
        try {
            if (idCandidato > -1) {
                perfil = services.loadPerfil(idCandidato);
            }
            if (perfil == null) {
                perfil = new PerfilJB();
            }

            //* BEGINING DATA GATHERING *//
             ctx.setVariable("nombre_Contacto", oferta.getNombreContacto());
             ctx.setVariable("nombre_Empresa", oferta.getNombreEmpresa());	            
             ctx.setVariable("titulo_Oferta", oferta.getTituloOferta());
             ctx.setVariable("id_Oferta", oferta.getIdOfertaEmpleo());	             
             ctx.setVariable("puesto_requerido", oferta.getOcupacionDescripcion());	 
             ctx.setVariable("fecha", fechaIni);	 
            
            // --- Profile --- //

            String profile_fullName = perfil.getNombre() + " " + perfil.getApellido1() + (perfil.getApellido2() == null || perfil.getApellido2().trim().equals("") ? "" : " " + perfil.getApellido2());
            
            // ---ADRESS --//
            StringBuilder direccion = new StringBuilder();
            direccion.append(perfil.getCalle()).append(" "); // Calle
            direccion.append(perfil.getNumeroExterior()); // # Exterior
            if (perfil.getNumeroInterior() != null && !perfil.getNumeroInterior().equals("")) {
            	direccion.append("-").append(perfil.getNumeroInterior()).append(" ");    // # Interior
            }

            // --
            // RN03. Si el candidato activa la casilla de verificacion de confidencialidad de datos, el sistema no mostrara los datos
            // personales del candidato (Género, CURP, edad, Fecha nacimiento, Entidad de nacimiento, dirección, teléfono, correo electrónico)  
            // a las empresas que deseen ver su perfil, caso contrario (si no activa esta casilla) todos los
            // datos del candidato seran visibles en su perfil para que las empresas puedan consultarlo.
            //Pero siempre debe mostrar el nombre.
            if (isDataConfidential(perfil, tipoUsuario)) {
                ctx.setVariable("profile_fullName", profile_fullName.toUpperCase());
                ctx.setVariable("direccion", CONFIDENCIAL);	          
                ctx.setVariable("cp", CONFIDENCIAL);	   
                ctx.setVariable("delegacion", CONFIDENCIAL);	   
                ctx.setVariable("entidad", CONFIDENCIAL);	  
                
            } else { // Non Confidential
                ctx.setVariable("profile_fullName", profile_fullName.toUpperCase());
                ctx.setVariable("direccion", direccion);	                
                ctx.setVariable("cp", perfil.getNumeroInterior());	                
                ctx.setVariable("delegacion", perfil.getMunicipio());
                ctx.setVariable("entidad", perfil.getEntidad());
            }

            // --- Contact --- //
            String contact_email = String.format("e-mail: %s", perfil.getCorreoElectronico());
            //--
            if (isDataConfidential(perfil, tipoUsuario)) {
                // Business Rule:
                // Si el medio de contacto elegido por el candidato fue "correo electronico", cuando la empresa consulte su perfil laboral
                // solo se mostrara este dato.
                if (perfil.getContactoCorreo() == CONTACTO_CORREO.SI.getIdContactoCorreo()) {
                    ctx.setVariable("contact_email", contact_email);
                } else if(perfil.getContactoCorreo() == CONTACTO_CORREO.NO.getIdContactoCorreo()){
                    ctx.setVariable("contact_email", CONFIDENCIAL);
                }
            } else {
                ctx.setVariable("contact_email", contact_email);
            }

            // Retrieve all phone numbers
            // NOTE: PerfilJB.getSecundarios retrieves all phone numbers, including the main one.
            for (int i = 0; i < perfil.getSecundarios().size(); i++) {
                TelefonoVO telefonoVO = perfil.getSecundarios().get(i);
                StringBuilder contact_phone = new StringBuilder();
                contact_phone.append("Tel. ").append(TIPO_TELEFONO.getTipoTelefono(telefonoVO.getIdTipoTelefono())).append(": ");
                contact_phone.append("(").append(telefonoVO.getAcceso()).append(")");    // Acceso
                contact_phone.append("(").append(telefonoVO.getClave()).append(")");    // Clave Lada
                contact_phone.append(telefonoVO.getTelefono());    // No. Telefonico
                if (telefonoVO.getExtension() != null && !telefonoVO.getExtension().equals("")) {
                    contact_phone.append(" ext ").append(telefonoVO.getExtension()); // Extension
                }
                // --
                // Business Rule:
                // Si el medio de contacto elegido por el candidato fue telefono, al menos debe proporcionarse
                // a la empresa el nombre completo y los datos de telefono.
                if (isDataConfidential(perfil, tipoUsuario)) {
                	if(perfil.getContactoTelefono() == CONTACTO_TELEFONO.SI.getIdContactoTelefono())
                		ctx.setVariable(String.format("contact_phone%d", i + 1), contact_phone.toString());
                	else if(perfil.getContactoTelefono() ==  CONTACTO_TELEFONO.NO.getIdContactoTelefono())
                		ctx.setVariable(String.format("contact_phone%d", i + 1), CONFIDENCIAL);
                } else {
                    ctx.setVariable(String.format("contact_phone%d", i + 1), contact_phone.toString());
                }
            }

            // --- Education --- //

            GradoAcademicoVO gradoAcademicoVO = perfil.getGradoPrincipal();
            String education_grade = gradoAcademicoVO.getNivel();  
            String education_career = gradoAcademicoVO.getCarrera();
            String education_status = gradoAcademicoVO.getSituacion();
            // --
            ctx.setVariable("education_grade", education_grade);
            ctx.setVariable("education_career", education_career);
            ctx.setVariable("education_status", education_status);
            
            // --- Knowledge --- //

            IdiomaVO idiomaVO = perfil.getIdiomaPrincipal();
            // Retrieve main language
            if (idiomaVO != null) {
                String knowledge_lang1 = idiomaVO.getIdioma();
                String knowledge_langCert1 = idiomaVO.getCertificacion().equals("Ninguna")? "" : idiomaVO.getCertificacion();
                String knowledge_langLevel1 = idiomaVO.getDominio();
                // --
                ctx.setVariable("knowledge_lang1", knowledge_lang1);
                ctx.setVariable("knowledge_langCert1", knowledge_langCert1);
                ctx.setVariable("knowledge_langLevel1", knowledge_langLevel1);
            
            }
            // Retrieve aditional languages
            if(perfil.getIdiomas()!=null && !perfil.getIdiomas().isEmpty())
	            for (int i = 0; i < perfil.getIdiomas().size(); i++) {
	                idiomaVO = perfil.getIdiomas().get(i);
	                // --
	                ctx.setVariable(String.format("knowledge_lang%d", i + 2), idiomaVO.getIdioma());
	                ctx.setVariable(String.format("knowledge_langCert%d", i + 2), idiomaVO.getCertificacion().equals("Ninguna")? "" : idiomaVO.getCertificacion());
	                ctx.setVariable(String.format("knowledge_langLevel%d", i + 2), idiomaVO.getDominio());                
	            }

			if (ctx.getVariables().containsKey("knowledge_lang1") ||
				ctx.getVariables().containsKey("knowledge_lang2") ||
				ctx.getVariables().containsKey("knowledge_lang3")) {
					ctx.setVariable("knowledge_lang", true);
				} else {
					ctx.setVariable("knowledge_lang", false);
				}

            if (perfil.getConocimientoComputacionVO() != null) {
                StringBuilder knowledge_computerBasics = new StringBuilder();
                if (perfil.getConocimientoComputacionVO().getProcesadorTxt() == 1) {
                    knowledge_computerBasics.append("Procesador de Texto").append(", ");
                }
                if (perfil.getConocimientoComputacionVO().getHojaCalculo() == 1) {
                    knowledge_computerBasics.append("Hojas de Calculo").append(", ");
                }
                if (perfil.getConocimientoComputacionVO().getInternet() == 1) {
                    knowledge_computerBasics.append("Internet").append(", ");
                }
                if (perfil.getConocimientoComputacionVO().getRedesSociales() == 1) {
                    knowledge_computerBasics.append("Redes Sociales").append(", ");
                }
                if (knowledge_computerBasics.length() > 0) {
                    knowledge_computerBasics.setLength(knowledge_computerBasics.length() - 2); // remove last two characters (comma and blank_space)
                }
                // --
                ctx.setVariable("knowledge_computerBasics", knowledge_computerBasics.toString());

                if (perfil.getConocimientoComputacionVO().getOtros() != null) {
                    String knowledge_computerOthers = perfil.getConocimientoComputacionVO().getOtros();
                    // --
                    ctx.setVariable("knowledge_computerOthers", knowledge_computerOthers);
                }
            }

            ConocimientoHabilidadVO conocimientoVO = perfil.getConocimientoPrincipal();
            // Retrieve main understanding
            if (conocimientoVO != null) {
                String knowledge_understanding1 = conocimientoVO.getConocimientoHabilidad();
                String knowledge_understandingExp1 = conocimientoVO.getExperiencia();
                String knowledge_understandingDesc1 = conocimientoVO.getDescripcion();
                // --
                ctx.setVariable("knowledge_understanding1", knowledge_understanding1);
                ctx.setVariable("knowledge_understandingExp1", knowledge_understandingExp1);
                ctx.setVariable("knowledge_understandingDesc1", knowledge_understandingDesc1);
            }
            // Retrieve aditional understandings
            for (int i = 0; i < perfil.getConocimientos().size(); i++) {
                conocimientoVO = perfil.getConocimientos().get(i);
                // --
                ctx.setVariable(String.format("knowledge_understanding%d", i + 2), conocimientoVO.getConocimientoHabilidad());
                ctx.setVariable(String.format("knowledge_understandingExp%d", i + 2), conocimientoVO.getExperiencia());
                ctx.setVariable(String.format("knowledge_understandingDesc%d", i + 2), conocimientoVO.getDescripcion());
            }

			if (ctx.getVariables().containsKey("knowledge_understanding1") ||
				ctx.getVariables().containsKey("knowledge_understanding2") ||
				ctx.getVariables().containsKey("knowledge_understanding3")) {
					ctx.setVariable("knowledge_understanding", true);
				} else {
					ctx.setVariable("knowledge_understanding", false);
				}
			
            // Skills
            List<Long> skillIds = services.consultaHabilidades(idCandidato);
            StringBuilder knowledge_skillsAndAttitudes = new StringBuilder();
            for (long i : skillIds) {
                String opcion = "";
                try {
                    opcion = HABILIDADES_ACTITUDES.forIdOpcion(i).getOpcion();
                } catch (Exception e) {
                    logger.warn(e.getMessage());
                }
                knowledge_skillsAndAttitudes.append(opcion).append(", ");
            }
            if (knowledge_skillsAndAttitudes.length() > 0) {
                knowledge_skillsAndAttitudes.setLength(knowledge_skillsAndAttitudes.length() - 2); // remove last two characters (comma and blank_space)
            }
            // --
            ctx.setVariable("knowledge_skillsAndAttitudes", knowledge_skillsAndAttitudes.toString());

			// To know if knowledge must be displayed
            if (ctx.getVariables().containsKey("knowledge_lang") ||
                ctx.getVariables().containsKey("knowledge_computerBasics") ||
                ctx.getVariables().containsKey("knowledge_computerOthers") ||
                ctx.getVariables().containsKey("knowledge_understanding") ||
                ctx.getVariables().containsKey("knowledge_skillsAndAttitudes")) {

                ctx.setVariable("knowledge", true);
            } else {
                ctx.setVariable("knowledge", false);
            }

            // --- Requested Job --- //

            String requestedJob_reqPost = catService.getOpcionById(mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_OCUPACION, perfil.getExpectativaPrincipal().getIdOcupacionDeseada());
            String requestedJob_jobPostExp = EXPERIENCIA.getDescripcion((int) (perfil.getPerfilLaboral().getIdExperienciaTotal()));
            String requestedJob_jobExpDesc = perfil.getPerfilLaboral().getExperiencia();// from perfil laboral
            // --
            ctx.setVariable("requestedJob_reqPost", requestedJob_reqPost);
            ctx.setVariable("requestedJob_jobPostExp", requestedJob_jobPostExp);
            ctx.setVariable("requestedJob_jobExpDesc", requestedJob_jobExpDesc);

			if ((requestedJob_reqPost != null && !requestedJob_reqPost.equals("")) ||
                (requestedJob_jobPostExp != null && !requestedJob_jobPostExp.equals("")) ||
                (requestedJob_jobExpDesc != null && !requestedJob_jobExpDesc.equals(""))) {
					ctx.setVariable("requestedJob", true);
            } else {
                ctx.setVariable("requestedJob", false);
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
        }

        String html = HtmlUtils.processHtmlTemplate(templateName, ctx);
        return  HtmlUtils.convertHtmlToPdfAndWriteToBrowseStream(html, request);

 }

    private boolean isDataConfidential(PerfilJB perfil, TIPO_USUARIO tipoUsuario) {
        if (perfil.getConfidencialidad() == CONFIDENCIALIDAD.SI.getIdOpcion()) {
            if (tipoUsuario != TIPO_USUARIO.CANDIDATO) {
                return true;
            }
        }
        return false;
    }	 
	 	

}