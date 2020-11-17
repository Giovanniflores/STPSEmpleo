
package mx.gob.stps.portal.web.offer.action;

import static mx.gob.stps.portal.web.infra.utils.Constantes.BODY_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_NEXT;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_REDIRECT_SWB;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_TEMPLATE_RESPONSIVE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.JSP_SUCCESS;
import static mx.gob.stps.portal.web.infra.utils.Constantes.URL_REDIRECT_SWB;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TITULO_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.DESCRIPCION_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FACEBOOK_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TWITTER_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.URL_ESPECIFICA;



import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

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
import mx.gob.stps.portal.core.infra.utils.Constantes.EXPERIENCIA;
import mx.gob.stps.portal.core.infra.utils.Constantes.HABILIDADES_ACTITUDES;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_TELEFONO;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_USUARIO;
import mx.gob.stps.portal.core.infra.utils.MessageLoader;
import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaCandidatoVO;
import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaEmpleoJB;
import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaPostulacionVO;
import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaVerDetalleVO;
import mx.gob.stps.portal.core.oferta.pregunta.vo.OfertaPreguntaVO;
import mx.gob.stps.portal.core.oferta.vo.OfertaEmpleoVO;
import mx.gob.stps.portal.core.search.service.IndexerServiceLocator;
import mx.gob.stps.portal.core.seguridad.vo.UsuarioVO;
import mx.gob.stps.portal.persistencia.vo.CanalizacionCandidatoVO;
import mx.gob.stps.portal.persistencia.vo.ConocimientoHabilidadVO;
import mx.gob.stps.portal.persistencia.vo.ModalidadCandidatoVO;
import mx.gob.stps.portal.persistencia.vo.ModalidadOfertaVO;
import mx.gob.stps.portal.persistencia.vo.TelefonoVO;
import mx.gob.stps.portal.utils.Catalogos;
import mx.gob.stps.portal.utils.Catalogos.ESTATUS;
import mx.gob.stps.portal.utils.Catalogos.POSTULACION_CARTERA;
import mx.gob.stps.portal.utils.ConstantesGenerales;
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

public final class OfferDetailsAction extends GenericAction {

	private static final String ID_OFERTA_EMPLEO = Constantes.ID_OFERTA_EMPLEO;
	private static String idcandidato2  =  null;
	private static final String ID_OFERTA_PREGUNTA = "id_oferta_pregunta";
	private static final String COMPATIBILIDAD_ATTR = "fulfillment";
	

	private static Logger logger = Logger.getLogger(OfferDetailsAction.class);

	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			idcandidato2 = request.getParameter("id_candidato");
			OfferQuestionForm offer = (OfferQuestionForm)form;
			offer.reset();
			request.getSession().removeAttribute("ERROR_MSG");
			request.getSession().removeAttribute("_urldefaultpage");
			request.getSession().removeAttribute("idInactivoAPeticionDelUsuario");
			request.getSession().removeAttribute("usuarioInactivoAPeticionDelUsuario");
			request.getSession().removeAttribute("usuarioInactivoPorVigencia");
			request.getSession().removeAttribute("idInactivoPorVigencia");
			request.getSession().removeAttribute("idInactivoPorAdmor");				
			loginUserJCF(idcandidato2  ,request,response,mapping);

			long idCandidato = 0;
			UsuarioWebVO usuario = getUsuario(request.getSession());
			int offerID = Utils.parseInt(request.getParameter(ID_OFERTA_EMPLEO));
			if (offerID==-1) {
				String strIdOferta = (request.getSession().getAttribute(ID_OFERTA_EMPLEO)==null? "0": request.getSession().getAttribute(ID_OFERTA_EMPLEO).toString());  			
				offerID = Utils.parseInt(strIdOferta);
			}else if (offerID > -1)
				request.getSession().setAttribute(ID_OFERTA_EMPLEO, offerID);
			if (isLogged(request.getSession()) && null != usuario && usuario.getIdTipoUsuario() == TIPO_USUARIO.CANDIDATO.getIdTipoUsuario()) {
				// From CANDIDATE side
				request.getSession().setAttribute("_user", this.getUsuario(request.getSession()));
				idCandidato = usuario.getIdPropietario();
			}else {
				// From COMPANY side
				idCandidato = Utils.parseLong(request.getParameter("idc"));
			}

			if (idCandidato>0) {
				CandidatoBusDelegate service = CandidatoBusDelegateImpl.getInstance();
				try {
					//se consulta si esta inscrito al ppc
					int ppcEstatus = service.consultarPpcEstatus(idCandidato);
					if (ppcEstatus==ESTATUS.ACTIVO_PPC.getIdOpcion() || ppcEstatus==ESTATUS.INACTIVO_PPC.getIdOpcion())
						offer.setInscritoPPC(true);
					checkChanneling(offerID, idCandidato, offer);
				} catch (Exception e1) {
					logger.error(e1);
					e1.printStackTrace();
				}
			}

			if (offerID > -1) {

				offer.setIdOfertaEmpleo(offerID);

				offer.setIdCandidato(idCandidato);

				recordStartDbDurationTime(offer);    // Init dbTimeDurationMs

				OfertaJB ofertaJB = getoffer(offerID); /** Se consulta el detalle de la oferta de empleo **/
				boolean postulated = false;
				if (idCandidato > 0) postulated = isPostulated(offerID, idCandidato);
				offer.setOfertaJB(ofertaJB);
				// Load into Request OFERTA_TITULO and OFERTA_DESCRIPCION
				PropertiesLoader properties = PropertiesLoader.getInstance();
				if(offer.getOfertaJB().getEmpresaNombre()==null){
					offer.getOfertaJB().setEmpresaNombre("Empresa sin Nombre");
				}
				request.setAttribute("OFERTA_TITULO", offer.getOfertaJB().getTituloOferta());
				request.setAttribute("OFERTA_DESCRIPCION", offer.getOfertaJB().getFunciones());
				request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_NEXT).getPath());
				request.getSession().setAttribute(TITULO_PAGINA, "Empleo de " + limpiarTitulo(offer.getOfertaJB().getTituloOferta()));
				request.getSession().setAttribute(DESCRIPCION_PAGINA, "Detalle de la oferta de " +  offer.getOfertaJB().getTituloOferta() + " - " +  offer.getOfertaJB().getEmpresaNombre() + " ubicada en " +  offer.getOfertaJB().getUbicacion() + " para realizar la funcion y actividades de "  +  offer.getOfertaJB().getFunciones() + ".");
				request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/compartir-ofertaA.jpg");
				request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/compartir-oferta-tweetA.jpg");
				//request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+"/"+offerID+"-oferta-de-empleo-de-"+limpiarURL(offer.getOfertaJB().getTituloOferta())+"-"+limpiarURL(offer.getOfertaJB().getEmpresaNombre()));
			    StringBuilder urlEspecifica = new StringBuilder();
				urlEspecifica.append( properties.getProperty("app.context.url.ssl"));
				urlEspecifica.append("/");
				urlEspecifica.append(offerID);
				urlEspecifica.append("-oferta-de-empleo-de-");
				urlEspecifica.append(limpiarURL(offer.getOfertaJB().getTituloOferta()));
				urlEspecifica.append("-");
				urlEspecifica.append(limpiarURL(offer.getOfertaJB().getEmpresaNombre()));
				if(idcandidato2 != null) {
					urlEspecifica.append("?id_candidato=" + idcandidato2);
				}
				
				request.getSession().setAttribute(URL_ESPECIFICA, urlEspecifica.toString());
					
				increaseOfferCount(offerID); // Se contabiliza la consulta de la oferta
				List<OfertaPreguntaVO> preguntas = getOfertaPreguntasList(offerID);
				offer.setOfertaPreguntasList(preguntas);
				if (idCandidato > 0) {
					try {
						CandidatoBusDelegate services = CandidatoBusDelegateImpl.getInstance();
						PerfilJB profile = services.loadPerfil(idCandidato);
						if (null == profile) profile = new PerfilJB();
						if (null == profile.getApellido2()) profile.setApellido2("");
						offer.setPerfilJB(profile);
					} catch (Exception se) { se.printStackTrace(); logger.error(se);}
					int compatibility = IndexerServiceLocator.getIndexerServiceRemote().match(offerID, idCandidato);	
					offer.setCompatibility(compatibility);
					// Se registra para su almacenamiento en caso de vincular al candidato
					request.getSession().setAttribute(COMPATIBILIDAD_ATTR, compatibility);
					offer.setPostulated(postulated);
					int estatusOfertaCandidato = getEstatusOfertaCandidato(offerID, idCandidato);
					if (null != offer.getOfertaJB())
						offer.getOfertaJB().setEstatusOfertaCandidato(Integer.toString(estatusOfertaCandidato));
					// si provenimos de miEspacioCandidato, agregamos el atributo _urlpageinvoke que será utilizado en offerdetails.jsp para
					// determinar si se muestra la liga "agregar a mis ofertas de empleo"
					if ("miEspacioCandidato.do".equals(request.getParameter("_urlpageinvoke")))
						request.getSession().setAttribute("_urlpageinvoke", "miEspacioCandidato.do");
				}
				if ("ofertasRecientesTodas".equals(request.getParameter("_urlpageinvoke")))
					request.getSession().setAttribute("_urlpageinvoke", "ofertasRecientes.do?method=ofertasRecientesTodas");
				if ("ofertasDestacadasTodas".equals(request.getParameter("_urlpageinvoke")))
					request.getSession().setAttribute("_urlpageinvoke", "ofertasRecientes.do?method=ofertasDestacadasTodas");	
				if ("admonCandidatos".equals(request.getParameter("_urlpageinvoke")))
					request.getSession().setAttribute("_urlpageinvoke", "admonCandidatos.do?method=init&ido="+offerID);
				request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
				//y si no esta activa?
				logger.info("JGLC -> ESTATUS: " + ofertaJB.getEstatus());
				if (!ofertaJB.isActive()){
					if (null != request.getSession().getAttribute("_urlpageinvoke"))
						request.getSession().setAttribute("_urldefaultpage", request.getSession().getAttribute("_urlpageinvoke"));
					else request.getSession().setAttribute("_urldefaultpage", request.getContextPath() + PropertiesLoader.getInstance().getProperty("app.swb.redirect.home"));
				}
			}
			recordEndDbDurationTime(offer);
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e);
		}
		request.setAttribute("ESTATUS_SELECCIONADA", Integer.toString(ESTATUS.SELECCIONADA.getIdOpcion()));
		request.setAttribute("ESTATUS_POSTULADO", Integer.toString(ESTATUS.POSTULADO.getIdOpcion()));
		request.setAttribute("ESTATUS_VINCULADO", Integer.toString(ESTATUS.VINCULADO.getIdOpcion()));
		request.getSession().setAttribute("detalle", "Detalle");	

		return mapping.findForward(FORWARD_TEMPLATE_RESPONSIVE);
	}
	
	public void checkChanneling(long offerID, long idCandidato, OfferQuestionForm offer) {
		ModalidadOfertaVO mo = null;
		offer.setChanneling(true);
		offer.setCheckChanneling(false);
		try {
			//se consulta si la oferta está inscrita en algún subprograma/modalidad
			CandidatoBusDelegate service = CandidatoBusDelegateImpl.getInstance();
			OfertaBusDelegate offerDelegate = OfertaBusDelegateImpl.getInstance();
			try {
				mo = offerDelegate.obtenerModalidadOfertaByIdOferta(offerID);
			}catch(javax.ejb.EJBTransactionRolledbackException ejb) {
				offer.setChanneling(true);
				offer.setCheckChanneling(false);
			}
			if (null != mo && mo.getIdModalidadOferta()>0) {
				offer.setChanneling(false);
				ModalidadCandidatoVO mc = service.getModalidadCandidato(idCandidato, mo.getIdModalidad(), mo.getIdSubprograma());
				if (null != mc && mc.getIdModalidadCandidato()>0) {
					//se consulta si el candidato ha realizado el proceso de canalización
					List<CanalizacionCandidatoVO> cList = service.getCanalizacionCandidatoList(idCandidato);
					if (null != cList && !cList.isEmpty()) {
						//se consulta si el candidato está canalizado a otro programa
						offer.setCheckChanneling(true);
						for (CanalizacionCandidatoVO vo : cList) {
							if (null != vo.getFechaCanalizacion()) offer.setChanneling(true);
							if (vo.getIdModalidadCandidato() == mc.getIdModalidadCandidato()) {
								offer.setCheckChanneling(false);
								break;
							}
						}
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e);
		}
	}

	public ActionForward toRecuperaContrasena(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		PropertiesLoader properties = PropertiesLoader.getInstance();
		request.setAttribute(URL_REDIRECT_SWB, properties.getProperty("app.swb.redirect.login.recuperar"));
		return mapping.findForward(FORWARD_REDIRECT_SWB);
	}	

	// TODO POSIBLEMENTE YA NO SE UTILIZA
	public ActionForward loginUser(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			String idOfertaEmpleo = request.getParameter("idOfertaEmpleo");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			int estatusUsuario  = -1;
			LoginAction loginAction = new LoginAction();		
			request.getSession().removeAttribute("ERROR_MSG");

			SecutityDelegate services = SecutityDelegateImpl.getInstance();

			UsuarioVO usuario = null;

			if (username!=null && !username.trim().isEmpty())
				usuario = services.consultaUsuarioPorLogin(username);

			boolean isPswValid = false;

			if (usuario!=null){
				isPswValid = loginAction.validatePassword(usuario.getContrasena(), password);
				estatusUsuario = usuario.getEstatus();
			}


			if(usuario!=null && isPswValid && estatusUsuario == ESTATUS.ACTIVO.getIdOpcion()){
				//logger.info("Usuario autorizado ("+ username +"), se firma dentro del Portal");
				request.getSession().removeAttribute("idInactivoAPeticionDelUsuario");
				request.getSession().removeAttribute("usuarioInactivoAPeticionDelUsuario");
				request.getSession().removeAttribute("usuarioInactivoPorVigencia");
				loginAction.firmaUsuarioPortal(request, response, usuario);				
				PropertiesLoader properties = PropertiesLoader.getInstance();

				String urlredirect = properties.getProperty("app.swb.redirect.detalle.oferta");
				urlredirect += "method=init&id_oferta_empleo="+ idOfertaEmpleo;

				request.setAttribute(URL_REDIRECT_SWB, urlredirect);
				return mapping.findForward(FORWARD_REDIRECT_SWB);
				//return mapping.findForward("ACTION_OFERTA_PREVIA");

			} else if(usuario!=null && isPswValid && estatusUsuario == ESTATUS.INACTIVO_A_PETICION_DEL_CANDIDATO.getIdOpcion()) {	
				request.getSession().setAttribute("idInactivoAPeticionDelUsuario", ESTATUS.INACTIVO_A_PETICION_DEL_CANDIDATO.getIdOpcion());
				request.getSession().setAttribute("usuarioInactivoAPeticionDelUsuario", username);
				registraError(request, "seguridad.login.msg.usu.reactivate", username);
				request.getSession().setAttribute("ERROR_MSG", "ERROR");												
				return mapping.findForward("REACTIVAR_USUARIO");	

			} else if(usuario!=null && isPswValid && estatusUsuario == ESTATUS.INACTIVO_POR_VIGENCIA.getIdOpcion()) {	
				request.getSession().setAttribute("idInactivoPorVigencia", ESTATUS.INACTIVO_POR_VIGENCIA.getIdOpcion());
				request.getSession().setAttribute("usuarioInactivoPorVigencia", username);
				registraError(request, "seguridad.login.msg.usu.inactivo.vig", username);
				MessageLoader messageLoader = MessageLoader.getInstance();							
				String strMensajeError = messageLoader.getMessage("seguridad.login.msg.usu.inactivo.vig");
				request.getSession().setAttribute("ERROR_MSG", strMensajeError);				
				return mapping.findForward("REACTIVAR_USUARIO");	

			} else if(usuario!=null && isPswValid && estatusUsuario == ESTATUS.INACTIVO_POR_BAJA_ADMINISTRADOR.getIdOpcion()) {	
				request.getSession().setAttribute("idInactivoPorAdmor", ESTATUS.INACTIVO_POR_BAJA_ADMINISTRADOR.getIdOpcion());	
				registraError(request, "seguridad.login.msg.usu.inactivo.admor", username);
				request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
				request.getSession().setAttribute("ERROR_MSG", "ERROR");																
				return mapping.findForward(FORWARD_TEMPLATE_RESPONSIVE);				

			}  else {				
				//logger.info("Usuario NO autorizado ("+ username +"), se direcciona a pagina de error");
				request.getSession().removeAttribute("idInactivoAPeticionDelUsuario");
				request.getSession().removeAttribute("usuarioInactivoAPeticionDelUsuario");
				request.getSession().removeAttribute("usuarioInactivoPorVigencia");
				registraError(request, "seguridad.login.msg.pwd.no");		
				request.getSession().setAttribute("ERROR_MSG", "ERROR");								
				request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
				return mapping.findForward(FORWARD_TEMPLATE_RESPONSIVE);				
			}

		} catch (Exception e) {
			e.printStackTrace();
			request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
			return mapping.findForward(FORWARD_TEMPLATE_RESPONSIVE);
		}
	}
		
	public String loginUserJCF(String idcandidato2, HttpServletRequest request, HttpServletResponse response,ActionMapping mapping) {
		try {
			
			logger.info("Entrando al metodo JCF");
			String idOfertaEmpleo = request.getParameter("idOfertaEmpleo");
			//String idOfertaEmpleo = "3364996";
			//long idcandidato2 = 8103757;
			//String username = request.getParameter("username");
			//String password = request.getParameter("password");
			int estatusUsuario  = -1;
			LoginAction loginAction = new LoginAction();		
			//request.getSession().removeAttribute("ERROR_MSG");
			logger.info("Entrando SecutityDelegate" );
			SecutityDelegate services = SecutityDelegateImpl.getInstance();
			logger.info("Saliendo SecutityDelegate" );

			UsuarioVO usuario = null;
			
			logger.info(idcandidato2);
			if (idcandidato2 !=  null) {

				logger.info("Entrando services de la consultaporID");
				usuario = services.consultaCandidatoPorID(idcandidato2);
				
				logger.info("Salimos de la consulta");
				
			}
			logger.info("Candidato variables");
			if(usuario != null ){				
				logger.info(usuario.getCorreoElectronico());				
				logger.info(usuario.getIdUsuario());
			}else{
				logger.info("Candidato Viene nulo");
			}
			
			
				
			/*if (candidato.getCorreoElectronico() !=null && ! candidato.getCorreoElectronico().trim().isEmpty()){
				logger.info("Valida candidato");
				usuario = services.consultaUsuarioPorLogin(candidato.getCorreoElectronico());
				
			}*/
				

			boolean isPswValid = true;
			estatusUsuario = usuario.getEstatus();
			/*if (candidato !=null){
				logger.info("Valida contraseña");
				isPswValid = loginAction.validatePassword(candidato.getContrasena(), candidato.getContrasena());
				
				logger.info(isPswValid);
			}*/


			if(usuario!=null && isPswValid){
				logger.info("Valida contraseña ok");
				request.getSession().removeAttribute("idInactivoAPeticionDelUsuario");
				request.getSession().removeAttribute("usuarioInactivoAPeticionDelUsuario");
				request.getSession().removeAttribute("usuarioInactivoPorVigencia");
				logger.info("va a firmar");

				loginAction.firmaUsuarioPortal(request, response, usuario);				
				PropertiesLoader properties = PropertiesLoader.getInstance();
				logger.info("sale de firmaUsuarioPortal");

				String urlredirect = properties.getProperty("app.swb.redirect.detalle.oferta");
				urlredirect += "method=init&id_oferta_empleo="+ idOfertaEmpleo;

				logger.info("sale de urlredirect:  " + urlredirect);
				//request.getSession().setAttribute("_user", usuario);
				request.setAttribute(URL_REDIRECT_SWB, urlredirect);
				//
				//return mapping.findForward(FORWARD_REDIRECT_SWB);
				//return mapping.findForward("ACTION_OFERTA_PREVIA");

			} /*else if(usuario!=null && isPswValid && estatusUsuario == ESTATUS.INACTIVO_A_PETICION_DEL_CANDIDATO.getIdOpcion()) {	
				request.getSession().setAttribute("idInactivoAPeticionDelUsuario", ESTATUS.INACTIVO_A_PETICION_DEL_CANDIDATO.getIdOpcion());
				request.getSession().setAttribute("usuarioInactivoAPeticionDelUsuario", username);
				registraError(request, "seguridad.login.msg.usu.reactivate", username);
				request.getSession().setAttribute("ERROR_MSG", "ERROR");												
				return mapping.findForward("REACTIVAR_USUARIO");	

			} else if(usuario!=null && isPswValid && estatusUsuario == ESTATUS.INACTIVO_POR_VIGENCIA.getIdOpcion()) {	
				request.getSession().setAttribute("idInactivoPorVigencia", ESTATUS.INACTIVO_POR_VIGENCIA.getIdOpcion());
				request.getSession().setAttribute("usuarioInactivoPorVigencia", username);
				registraError(request, "seguridad.login.msg.usu.inactivo.vig", username);
				MessageLoader messageLoader = MessageLoader.getInstance();							
				String strMensajeError = messageLoader.getMessage("seguridad.login.msg.usu.inactivo.vig");
				request.getSession().setAttribute("ERROR_MSG", strMensajeError);				
				return mapping.findForward("REACTIVAR_USUARIO");	

			} else if(usuario!=null && isPswValid && estatusUsuario == ESTATUS.INACTIVO_POR_BAJA_ADMINISTRADOR.getIdOpcion()) {	
				request.getSession().setAttribute("idInactivoPorAdmor", ESTATUS.INACTIVO_POR_BAJA_ADMINISTRADOR.getIdOpcion());	
				registraError(request, "seguridad.login.msg.usu.inactivo.admor", username);
				request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
				request.getSession().setAttribute("ERROR_MSG", "ERROR");																
				return mapping.findForward(FORWARD_TEMPLATE_RESPONSIVE);				

			}  else {				
				//logger.info("Usuario NO autorizado ("+ username +"), se direcciona a pagina de error");
				request.getSession().removeAttribute("idInactivoAPeticionDelUsuario");
				request.getSession().removeAttribute("usuarioInactivoAPeticionDelUsuario");
				request.getSession().removeAttribute("usuarioInactivoPorVigencia");
				registraError(request, "seguridad.login.msg.pwd.no");		
				request.getSession().setAttribute("ERROR_MSG", "ERROR");								
				request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
				return mapping.findForward(FORWARD_TEMPLATE_RESPONSIVE);				
			}*/

		} catch (Exception e) {
			e.printStackTrace();
			request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
			//return mapping.findForward(FORWARD_TEMPLATE_RESPONSIVE);
		}
					
				
			
		return idcandidato2;
		
	
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
			msg = "Ha ocurrido un error, por favor intente mï¿½s tarde";
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
			return mapping.findForward(FORWARD_TEMPLATE_RESPONSIVE);
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
		request.getSession().setAttribute(TITULO_PAGINA, "Empleo de " + limpiarTitulo(offer.getOfertaJB().getTituloOferta()));
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Detalle de la oferta de " +  offer.getOfertaJB().getTituloOferta() + " - " +  offer.getOfertaJB().getEmpresaNombre() + " ubicada en " +  offer.getOfertaJB().getUbicacion() + " para realizar la funcion y actividades de "  +  offer.getOfertaJB().getFunciones() + ".");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));

		return mapping.findForward(FORWARD_TEMPLATE_RESPONSIVE);
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
		request.getSession().setAttribute(TITULO_PAGINA, "Empleo de " + limpiarTitulo(offer.getOfertaJB().getTituloOferta()));
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Detalle de la oferta " +  offer.getOfertaJB().getTituloOferta() + " - " +  offer.getOfertaJB().getEmpresaNombre() + " ubicada en " +  offer.getOfertaJB().getUbicacion() + " para realizar la funcion y actividades de "  +  offer.getOfertaJB().getFunciones() + ".");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));

		return mapping.findForward(FORWARD_TEMPLATE_RESPONSIVE);
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
                vo.setIdOficina(ConstantesGenerales.PORTAL_ID_OFICINA);
                vo.setIdVinculado((long) Catalogos.APLICACION.PORTAL_DEL_EMPLEO.getIdOpcion());
                vo.setIdUsuario(getUsuario(request.getSession()).getIdUsuario());
                vo.setPostulacionCartera(POSTULACION_CARTERA.NO.getIdOpcion());
                vo.setIdFuente((long) Catalogos.APLICACION.PORTAL_DEL_EMPLEO.getIdOpcion());
                //vo.setIdMotivo();   // At DB must be null

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
			return mapping.findForward(FORWARD_TEMPLATE_RESPONSIVE);
		}
		return null;
	}

	public ActionForward offerPost(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		OfferQuestionForm offer = (OfferQuestionForm)form;
		OfferBusDelegate services = OfferBusDelegateImpl.getInstance();
		request.removeAttribute("postuladoOK");

		PerfilJB perfilJB = offer.getPerfilJB();
		boolean inscritoPPC = offer.isInscritoPPC();

		try {
			List<OfertaCandidatoVO> list = services.findByOfferCandidate(offer.getIdOfertaEmpleo(), offer.getIdCandidato());
	    	byte[] buf = new byte[0];

			if (null == list || list.isEmpty()) {

				if(offer.getIdCandidato() >0){

					OfertaCandidatoVO vo = new OfertaCandidatoVO();
					vo.setIdUsuario(perfilJB.getIdUsuario());
					vo.setIdOficina(ConstantesGenerales.PORTAL_ID_OFICINA);
					vo.setIdFuente((long) Catalogos.APLICACION.PORTAL_DEL_EMPLEO.getIdOpcion());
					vo.setIdOfertaEmpleo(offer.getIdOfertaEmpleo());
					vo.setIdVinculado((long) Catalogos.APLICACION.PORTAL_DEL_EMPLEO.getIdOpcion());
					vo.setPostulacionCartera(POSTULACION_CARTERA.NO.getIdOpcion());
					vo.setIdCandidato(offer.getIdCandidato());
					vo.setFechaAlta(Calendar.getInstance().getTime());
					vo.setEstatus(ESTATUS.POSTULADO.getIdOpcion());

					vo.setCompatibilidad(recuperaCompatibilidad(request, vo.getIdOfertaEmpleo(), vo.getIdCandidato()));

					services.create(vo);
					increasePostCount(offer.getIdOfertaEmpleo());
					//CREACION DEL PDF
					buf = generarCartaPresentacion(form, request, response);
					//Genera Notificacion
					generarNotificacionPostulacion(vo, buf, inscritoPPC);						
					avisoCandidatoPostuladoAction(offer.getIdOfertaEmpleo(), request);
				} else {
					UsuarioWebVO usuario = getUsuario(request.getSession());
					logger.error("ERROR OfferDetailsAction.offerPost id_Candidato=0, id_oferta_empleo=" + offer.getIdOfertaEmpleo() + ", id_usuario=" + (usuario!=null?usuario.getIdUsuario():"SIN SESSION"));
				}

			}else {

				for (OfertaCandidatoVO ofertaCandidato :list) {
					if (ofertaCandidato==null) continue;

					if (ofertaCandidato.getEstatus() < ESTATUS.POSTULADO.getIdOpcion() && ofertaCandidato.getIdCandidato()>0){
						ofertaCandidato.setEstatus(ESTATUS.POSTULADO.getIdOpcion());						
					} else if(ofertaCandidato.getIdCandidato()<1){
						UsuarioWebVO usuario = getUsuario(request.getSession());
						logger.error("ERROR OfferDetailsAction.offerPost id_Candidato=" + ofertaCandidato.getIdCandidato() +  ", id_oferta_empleo=" + offer.getIdOfertaEmpleo() + ", id_usuario=" + (usuario!=null?usuario.getIdUsuario():"SIN SESSION"));
					}

					services.update(ofertaCandidato);
					//CREACION DEL PDF
					buf = generarCartaPresentacion(form, request, response);
					//Genera Notificacion
					generarNotificacionPostulacion(ofertaCandidato, buf, inscritoPPC);	
				}
			}			
			//Actualizacion de la tabla HistoricoBusquedaPPC
			String idMovimiento = (request.getSession().getAttribute("idMovimiento")==null? "0": request.getSession().getAttribute("idMovimiento").toString());
			if (!idMovimiento.equals("0")){
				SecutityDelegateImpl.getInstance().consultaHistoricoBusquedaPPC(Long.parseLong(idMovimiento));
			}


			offer.setPostulated(true);

			//increasePostCount(offer.getIdOfertaEmpleo());

			request.setAttribute("postuladoOK", true);

			/** Se envia notificacion de postulacion **/
			//long idOfertaEmpleo = offer.getIdOfertaEmpleo();			
			/* FIXME quitar 
			String idContacto = null;
			OfertaJB ofertajb = offer.getOfertaJB();
			if (ofertajb!=null){
				idContacto = ofertajb.getIdContacto();
			}
			 */
			//avisoCandidatoPostuladoAction(idOfertaEmpleo, request);

		}catch(Exception e) {
			e.printStackTrace();
			logger.error(e);
		}

		request.getSession().setAttribute(BODY_JSP, mapping.findForward("CANDIDATO_POSTULADO").getPath());
        PropertiesLoader properties = PropertiesLoader.getInstance();
        if(offer != null && offer.getOfertaJB() != null){
        	request.getSession().setAttribute(TITULO_PAGINA, "Empleo de " + limpiarTitulo(offer.getOfertaJB().getTituloOferta() != null ? offer.getOfertaJB().getTituloOferta() : ""));
    		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Detalle de la oferta " +  offer.getOfertaJB().getTituloOferta() != null ? offer.getOfertaJB().getTituloOferta() : "" + " - " +  offer.getOfertaJB().getEmpresaNombre() != null ? offer.getOfertaJB().getEmpresaNombre() : "" + " ubicada en " +  offer.getOfertaJB().getUbicacion() != null ? offer.getOfertaJB().getUbicacion() : "" + " para realizar la funcion y actividades de "  +  offer.getOfertaJB().getFunciones() != null ? offer.getOfertaJB().getFunciones() : "" + ".");
    		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
    		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
    		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
        }
		return mapping.findForward(FORWARD_TEMPLATE_RESPONSIVE);     
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

	private OfertaJB getoffer(long offerID) {
		OfertaJB offer = new OfertaJB();
		try {
			OfferBusDelegate services = OfferBusDelegateImpl.getInstance();
			OfertaEmpleoJB jb = services.buscarOfertaEmpleo(offerID);
			if (null != jb) {
				BeanUtils.copyProperties(offer, jb);
//				offer.setEmpresaNombre(jb.getEmpresa().getNombreEmpresa());
				offer.setEmpresaNombre(jb.getEmpresa().getNombreComercial());
				if (jb.getEstatusOffer() == ESTATUS.ACTIVO.getIdOpcion()
						|| jb.getEstatusOffer() == ESTATUS.FERIA_ACEPTADA_A_EVENTO.getIdOpcion() 
						|| jb.getEstatusOffer() == ESTATUS.ABRIENDO_ESPACIOS_ACTIVO.getIdOpcion()) {
					offer.setActive(true);
				}else offer.setActive(false);
			}
			if (null != offer && null != offer.getMapaUbicacion()) {
				if (!offer.getMapaUbicacion().startsWith("http://") && !offer.getMapaUbicacion().startsWith("https://") 
						&& offer.getMapaUbicacion().contains(",")) {					
					String mapaFormateado = "http://maps.google.com/?ll=" + offer.getMapaUbicacion() + "&z=15";
					offer.setMapaUbicacion(mapaFormateado);
				}
			}
		}catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
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

	/**
	private String generarCurriculum(PerfilJB perfilCandidato, String candidatoNombre){
		StringBuilder curriculum = new StringBuilder();
		//		if (perfilCandidato!=null && perfilCandidato.getConfidencialidad()>0 )
		//			logger.info("Confidencial "+perfilCandidato.getConfidencialidad());
        // TODO: Escape accented chars...
		curriculum.append(candidatoNombre +"<br/>");
		if (perfilCandidato!=null){
			if (perfilCandidato.getExpectativaPrincipal()!=null)
				curriculum.append("<b>Puesto solicitado:</b>" + perfilCandidato.getExpectativaPrincipal().getOcupacionDeseada() + "<br/>");
			if(perfilCandidato.getConfidencialidad()!=2)curriculum.append("<b>Edad:</b>" + perfilCandidato.getEdad() + "<br/>");
			if (perfilCandidato.getPerfilLaboral()!=null &&
					perfilCandidato.getPerfilLaboral().getContactoTelefono() == CONTACTO_TELEFONO.SI.getIdContactoTelefono()) {
				curriculum.append("<b>Telï¿½fono:</b> ");
				curriculum.append(perfilCandidato.getPrincipal().getAcceso() + 
						"-" + perfilCandidato.getPrincipal().getClave() + "-" + perfilCandidato.getPrincipal().getTelefono());

				if(null!=perfilCandidato.getPrincipal().getExtension() && !perfilCandidato.getPrincipal().getExtension().equalsIgnoreCase("")){
					curriculum.append(" ext. " + perfilCandidato.getPrincipal().getExtension());
				}

				curriculum.append("<br/>");		
				if(perfilCandidato.getPrincipal().getIdTipoTelefono() == TIPO_TELEFONO.FIJO.getIdOpcion()){
					curriculum.append("<b>Tipo de telï¿½fono:</b>" + TIPO_TELEFONO.FIJO.getOpcion());
				} else if(perfilCandidato.getPrincipal().getIdTipoTelefono() == TIPO_TELEFONO.CELULAR.getIdOpcion()){
					curriculum.append("<b>Tipo de telï¿½fono:</b>" + TIPO_TELEFONO.CELULAR.getOpcion());
				}				
				curriculum.append("<br/><b>Horario de contacto:</b>" + perfilCandidato.getHorarioLlamar());
			}
			if (perfilCandidato.getPerfilLaboral().getContactoCorreo() == CONTACTO_CORREO.SI.getIdContactoCorreo()){
				curriculum.append("<br/><b>Correo electrï¿½nico:</b>");

				curriculum.append(perfilCandidato.getCorreoElectronico());
			}
			curriculum.append("<br/>");		
			if(perfilCandidato.getConfidencialidad()!=2){
				curriculum.append("<b>Direcciï¿½n:</b>" + perfilCandidato.getColonia());
				curriculum.append("<br/><b>Cï¿½digo postal:</b>" + perfilCandidato.getCodigoPostal());
				curriculum.append("<br/><b>Entidad:</b>" + perfilCandidato.getEntidad());
				curriculum.append("<br/><b>Municipio o delegaciï¿½n:</b>" + perfilCandidato.getMunicipio());
			}
			curriculum.append("<br/><b>Disponibilidad para viajar:</b>" + 
					(perfilCandidato.getPerfilLaboral().getDisponibilidadViajar() > 0 ? mx.gob.stps.portal.core.oferta.detalle.helper.Utils.getLblDisponibilidad(perfilCandidato.getPerfilLaboral().getDisponibilidadViajar()) : ""));
			curriculum.append("<br/><b>Disponibilidad para radicar fuera:</b> " + 
					(perfilCandidato.getPerfilLaboral().getDisponibilidadRadicar() > 0 ? mx.gob.stps.portal.core.oferta.detalle.helper.Utils.getLblDisponibilidad(perfilCandidato.getPerfilLaboral().getDisponibilidadRadicar()) : ""));		
			curriculum.append("<br/><br/><b>Formaciï¿½n profesional</b><br/>");
			curriculum.append("<b>Grado de estudios:</b>" + (null != perfilCandidato.getGradoPrincipal().getNivel() ? perfilCandidato.getGradoPrincipal().getNivel() : "") + "<br/>");
			curriculum.append("<b>Carrera o especialidad:</b>" + (null != perfilCandidato.getGradoPrincipal().getCarrera() ? perfilCandidato.getGradoPrincipal().getCarrera() : "") + "<br/>");
			curriculum.append("<b>Situaciï¿½n acadï¿½mica:</b>" + (null != perfilCandidato.getGradoPrincipal().getSituacion() ? perfilCandidato.getGradoPrincipal().getSituacion() : "") + "<br/>");
			if (perfilCandidato.getIdiomaPrincipal()!=null ){
				curriculum.append("<b>Idioma:</b>" + (null != perfilCandidato.getIdiomaPrincipal().getIdioma() ? perfilCandidato.getIdiomaPrincipal().getIdioma() : "") + "<br/>");
				curriculum.append("<b>Certificaciï¿½n:</b>" + (null != perfilCandidato.getIdiomaPrincipal().getCertificacion() ? perfilCandidato.getIdiomaPrincipal().getCertificacion() : "") + "<br/>");
				curriculum.append("<b>Dominio:</b>" + (null != perfilCandidato.getIdiomaPrincipal().getDominio() ? perfilCandidato.getIdiomaPrincipal().getDominio() : "") + "<br/>");
			}
			curriculum.append("<b>Conocimientos:</b>" + (perfilCandidato!=null && null != perfilCandidato.getConocimientoPrincipal().getConocimientoHabilidad() ? perfilCandidato.getConocimientoPrincipal().getConocimientoHabilidad() : "") + "<br/>");
			curriculum.append("<b>Descripción:</b>" + (perfilCandidato!=null && null != perfilCandidato.getConocimientoPrincipal().getDescripcion() ? perfilCandidato.getConocimientoPrincipal().getDescripcion() : "") + "<br/>");
			curriculum.append("<b>Experiencia:</b>" + (perfilCandidato!=null && null != perfilCandidato.getConocimientoPrincipal().getExperiencia() ? perfilCandidato.getConocimientoPrincipal().getExperiencia() : "") + "<br/>");
			curriculum.append("<b>Habilidades y actitudes:</b>" + getHabilidades(perfilCandidato.getHabilidadesCandidato()) + "<br/>");
			//curriculum.append("<b>Descripciï¿½n:</b>" + (perfilCandidato!=null && null != perfilCandidato.getHabilidadPrincipal().getDescripcion() ? perfilCandidato.getHabilidadPrincipal().getDescripcion() : "") + "<br/>");
			//curriculum.append("<b>Experiencia:</b>" + (perfilCandidato!=null && null != perfilCandidato.getHabilidadPrincipal().getExperiencia() ? perfilCandidato.getHabilidadPrincipal().getExperiencia() : "") + "<br/>");		
			curriculum.append("<br/><b>Experiencia relevante</b><br/>");		
			curriculum.append("<b>A&ntilde;os de experiencia en el puesto solicitado:</b>" + (perfilCandidato!=null && perfilCandidato.getPerfilLaboral().getIdExperienciaTotal() > 0 ? EXPERIENCIA.getDescripcion((int)perfilCandidato.getPerfilLaboral().getIdExperienciaTotal()) : "") + "<br/>");

			if(null!=perfilCandidato.getPerfilLaboral().getExperiencia())
				curriculum.append("<b>Experiencia:</b>" + perfilCandidato.getPerfilLaboral().getExperiencia() + "<br/>");
		}
        //logger.debug(curriculum.toString());
		return curriculum.toString();
	}

	private String getHabilidades(List<CatalogoOpcionVO> habilidadesCandidato) {
		String habilidades ="";
		if(habilidadesCandidato.size()>0){
			for(CatalogoOpcionVO vo: habilidadesCandidato){

				if(vo!=null){

					if(habilidades.equals(""))habilidades=vo.getOpcion();
					else habilidades = habilidades+", "+vo.getOpcion();
				}

			}
		}

		return habilidades;
	}**/

	private void generarNotificacionPostulacion(OfertaCandidatoVO vo, byte[] buf, boolean inscritoPPC){

		String candidatoNombre = "";
		String candidatoEmail = "";
		String tituloOferta = "";
		String ofertaCorreoElectronicoContacto = "";
		String ofertaNombreEmpresa = "";

		long idCandidato = vo.getIdCandidato();	
		long idOfertaEmpleo = vo.getIdOfertaEmpleo();	

		//logger.info("---OfferDetailsAction.generarNotificacionPostulacion idCandidato:" + idCandidato + " idOfertaEmpleo:" + idOfertaEmpleo);
		try {			
			OfertaEmpleoVO oferta = OfertaBusDelegateImpl.getInstance().consultaOfertaEmpleo(idOfertaEmpleo);
			  Date date = Calendar.getInstance().getTime();
		      SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy");	
		      String today = formatter.format(date);
			
			//if(oferta.getContactoCorreo() == Catalogos.CONTACTO_CORREO.SI.getIdContactoCorreo()){
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
				//
				String pdfOutputName = "CP"+"_"+ perfilCandidato.getCurp()+"_"+today+".pdf";


				if (ofertaCorreoElectronicoContacto!=null && !ofertaCorreoElectronicoContacto.isEmpty()){

					NotificacionService notificacionService = new NotificacionService();
					notificacionService.notificacionPostuladoToEmpresa(empresaVO, vo, candidatoNombre,candidatoEmail, 
							tituloOferta, ofertaCorreoElectronicoContacto, ofertaNombreEmpresa, buf, pdfOutputName, inscritoPPC);
				}						
			//}
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

		//logger.info("Correo Candidato"+candidatoEmail);
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
        return mapping.findForward(FORWARD_TEMPLATE_RESPONSIVE);
	}*/
	/**/

	public ActionForward avisoCandidatoPostuladoAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		OfferQuestionForm offer = (OfferQuestionForm)form;

		long idOfertaEmpleo = offer.getIdOfertaEmpleo();
		/* FIXME quitar
		String idContacto = null;

		OfertaJB ofertajb = offer.getOfertaJB();
		if (ofertajb!=null){
			idContacto = ofertajb.getIdContacto();	
		}
		 */
		avisoCandidatoPostuladoAction(idOfertaEmpleo, request);

		request.getSession().setAttribute(BODY_JSP, mapping.findForward("CANDIDATO_POSTULADO").getPath());
        PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Empleo de " +  offer.getOfertaJB().getTituloOferta());
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Detalle de la oferta " +  offer.getOfertaJB().getTituloOferta() + " - " +  offer.getOfertaJB().getEmpresaNombre() + " ubicada en " +  offer.getOfertaJB().getUbicacion() + " para realizar la funcion y actividades de "  +  offer.getOfertaJB().getFunciones() + ".");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));

		return mapping.findForward(FORWARD_TEMPLATE_RESPONSIVE);
		//return mapping.findForward(FORWARD_TEMPLATE_REGISTRO_CANDIDATO);
	}

	public void avisoCandidatoPostuladoAction(long idOfertaEmpleo, HttpServletRequest request) {

		if (idOfertaEmpleo > 0 ) {

			try {

				OfertaBusDelegate ofertaServices = OfertaBusDelegateImpl.getInstance();
				OfertaEmpleoVO oferta = ofertaServices.consultaOfertaEmpleo(idOfertaEmpleo);

				if (oferta != null) {
					request.setAttribute("nombreContacto", oferta.getNombreContacto());					
					request.setAttribute("correoElectronico", oferta.getCorreoElectronicoContacto());					
					request.setAttribute("telefonos", oferta.getTelefonos());
					request.setAttribute("cargoContacto", oferta.getCargoContacto());					
					

					DomicilioVO domicilioVO = oferta.getDomicilio();
					if (domicilioVO != null) {

						String domicilio = new String();
						if (domicilioVO.getCalle()!=null && !"".equals(domicilioVO.getCalle()))
							domicilio = domicilioVO.getCalle();		

						if(domicilioVO.getNumeroExterior() != null && 
								domicilio!=null && !"".equals(domicilio) && 
								domicilioVO.getNumeroExterior()!=null    && 
								!"".equals(domicilioVO.getNumeroExterior()))							
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
		request.getSession().setAttribute(TITULO_PAGINA, "Postulado ");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "postulado, Secretaria del Trabajo y Previsi&oacute;n Social, Servicio Nacional de Empleo, Portal del Empleo");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));

		return mapping.findForward(FORWARD_TEMPLATE_RESPONSIVE);
	}

	public ActionForward regresaDetalle(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
        PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Detalle de la oferta");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Detalle de la oferta, Secretaria del Trabajo y Previsi&oacute;n Social, Servicio Nacional de Empleo, Portal del Empleo");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));

		return mapping.findForward(FORWARD_TEMPLATE_RESPONSIVE);
	}

	private int recuperaCompatibilidad(HttpServletRequest request, long idOfertaEmpleo, long idCandidato){
		int compatibilidad = 0;

		HttpSession session = request.getSession();
		Object compatibilidadAtt = session.getAttribute(COMPATIBILIDAD_ATTR);

		if (compatibilidadAtt==null){

			//logger.info("Atributo del porcentaje de compatibilidad no localizado, se invocara el proceso para su almacenamiento en la relacion del candidato con la oferta.");

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
		 byte[] pdf = null;
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
		    SimpleDateFormat formatter = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy");			
			//
			Date fechaInicio = oferta.getFechaInicio();		    
			Date date = Calendar.getInstance().getTime();
		    String today = formatter.format(date);
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
	             //ctx.setVariable("puesto_requerido", oferta.getOcupacionDescripcion());
	             ctx.setVariable("puesto_requerido", oferta.getSubAreaLaboralDescripcion());
	             ctx.setVariable("fecha_Inicio", fechaIni);	 
	             ctx.setVariable("fecha", today);
	            
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
	            // personales del candidato (Gï¿½nero, CURP, edad, Fecha nacimiento, Entidad de nacimiento, direcciï¿½n, telï¿½fono, correo electrï¿½nico)  
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

		        String html = HtmlUtils.processHtmlTemplate(templateName, ctx);
		        pdf = HtmlUtils.convertHtmlToPdfAndWriteToBrowseStream(html, request);

	        } catch (Exception e) {
	            e.printStackTrace();
	            logger.error(e);
	        }

	        return pdf;
	 }

	private boolean isDataConfidential(PerfilJB perfil, TIPO_USUARIO tipoUsuario) {
		if (perfil.getConfidencialidad() == CONFIDENCIALIDAD.SI.getIdOpcion()) {
			if (tipoUsuario != TIPO_USUARIO.CANDIDATO) {
				return true;
			}
		}
		return false;
	}

	private void recordStartDbDurationTime(final OfferQuestionForm form) {
		form.setDbDurationTimeMs(Calendar.getInstance().getTimeInMillis());
	}

	private void recordEndDbDurationTime(final OfferQuestionForm form) {
		Calendar start = Calendar.getInstance();
		start.setTimeInMillis(form.getDbDurationTimeMs());

		Calendar end = Calendar.getInstance();

		long dif = end.getTimeInMillis() - start.getTimeInMillis();

		form.setDbDurationTimeMs(dif); // Set new time in millis
	}


	public ActionForward generaCarta(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		
		try{
			response.setContentType("application/pdf");
			response.setHeader("Content-disposition","attachment; filename=cv.pdf");
			
			byte[] buf = generarCartaPresentacion(form, request, response);		
			
			OutputStream out = response.getOutputStream();
			//FileInputStream in = new FileInputStream(my_file);
			ByteArrayInputStream in = new ByteArrayInputStream(buf);
			
			byte[] buffer = new byte[4096];
			int length;
			while ((length = in.read(buffer)) > 0){
			    out.write(buffer, 0, length);
			}
			in.close();
			out.flush();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}
	
	private String limpiarTitulo(String cadena){
		cadena = cadena.toLowerCase();
		cadena = cadena.trim();
		cadena = cadena.replace("A", "a");
		cadena = cadena.replace("E", "e");
		cadena = cadena.replace("I", "i");
		cadena = cadena.replace("O", "o");
		cadena = cadena.replace("U", "u");
		cadena = cadena.replace("á", "&aacute;");
		cadena = cadena.replace("é", "&eacute;");
		cadena = cadena.replace("í", "&iacute;");
		cadena = cadena.replace("ó", "&oacute;");
		cadena = cadena.replace("ú", "&uacute;");
		cadena = cadena.replace("ñ", "&ntilde;");		
		cadena = cadena.replace("Á", "&aacute;");
		cadena = cadena.replace("É", "&eacute;");
		cadena = cadena.replace("Í", "&iacute;");
		cadena = cadena.replace("Ó", "&oacute;");
		cadena = cadena.replace("Ú", "&uacute;");
		cadena = cadena.replace("Ñ", "&ntilde;");
		cadena = cadena.replace("â", "a");
		cadena = cadena.replace("ê", "e");
		cadena = cadena.replace("î", "i");
		cadena = cadena.replace("ô", "o");
		cadena = cadena.replace("û", "u");	
		cadena = cadena.replace("Â", "a");
		cadena = cadena.replace("Ê", "e");
		cadena = cadena.replace("Î", "i");
		cadena = cadena.replace("Ô", "o");
		cadena = cadena.replace("Û", "u");
		cadena = cadena.replace("ä", "a");
		cadena = cadena.replace("ë", "e");
		cadena = cadena.replace("ï", "i");
		cadena = cadena.replace("ö", "o");
		cadena = cadena.replace("ü", "u");	
		cadena = cadena.replace("Ä", "a");
		cadena = cadena.replace("Ë", "e");
		cadena = cadena.replace("Ï", "i");
		cadena = cadena.replace("Ö", "o");
		cadena = cadena.replace("Ü", "u");
		cadena = cadena.replace("`", "");
		cadena = cadena.replace("'", "");
		cadena = cadena.replace(".", " ");
		cadena = cadena.replace(",", " ");
		cadena = cadena.replace(":", " ");
		cadena = cadena.replace("*", "");
		cadena = cadena.replace("<", "");
		cadena = cadena.replace(">", "");
		cadena = cadena.replace("|", "");
		cadena = cadena.replace("+", " ");
		cadena = cadena.replace("*", " ");
		cadena = cadena.replace("-", " ");	
		cadena = cadena.replace("_", " ");
		return cadena;
	}
	
	private String limpiarURL(String cadena){
		cadena = cadena.toLowerCase();
		cadena = cadena.trim();
		cadena = cadena.replaceAll(" +", "-");
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
		cadena = cadena.replace("â", "a");
		cadena = cadena.replace("ê", "e");
		cadena = cadena.replace("î", "i");
		cadena = cadena.replace("ô", "o");
		cadena = cadena.replace("û", "u");	
		cadena = cadena.replace("Â", "a");
		cadena = cadena.replace("Ê", "e");
		cadena = cadena.replace("Î", "i");
		cadena = cadena.replace("Ô", "o");
		cadena = cadena.replace("Û", "u");
		cadena = cadena.replace("ä", "a");
		cadena = cadena.replace("ë", "e");
		cadena = cadena.replace("ï", "i");
		cadena = cadena.replace("ö", "o");
		cadena = cadena.replace("ü", "u");	
		cadena = cadena.replace("Ä", "a");
		cadena = cadena.replace("Ë", "e");
		cadena = cadena.replace("Ï", "i");
		cadena = cadena.replace("Ö", "o");
		cadena = cadena.replace("Ü", "u");
		cadena = cadena.replace("`", "");
		cadena = cadena.replace("'", "");
		cadena = cadena.replace(".", "");
		cadena = cadena.replace(",", "");
		cadena = cadena.replace(";", "");
		cadena = cadena.replace(":", "");
		cadena = cadena.replace("*", "");
		cadena = cadena.replace("<", "");
		cadena = cadena.replace(">", "");
		cadena = cadena.replace("|", "");
		cadena = cadena.replace("+", "");
		cadena = cadena.replace("*", "");
		cadena = cadena.replace("Ñ", "n");	
		cadena = cadena.replace(" ", "-");
		cadena = cadena.replace("%", "");
		cadena = cadena.replace("_", "-");
		cadena = cadena.replace("$", "");
		cadena = cadena.replace("!", "");
		cadena = cadena.replace("(", "");
		cadena = cadena.replace(")", "");
		cadena = cadena.replace("\"", "-");
		cadena = cadena.replace("´", "");
		cadena = cadena.replace("¨", "");
		cadena = cadena.replace("{", "");
		cadena = cadena.replace("}", "");
		cadena = cadena.replace("[", "");
		cadena = cadena.replace("]", "");
		cadena = cadena.replace("#", "");
		cadena = cadena.replace("&", "-");
		cadena = cadena.replace("/", "-");
		cadena = cadena.replace("=", "-");
		cadena = cadena.replace("?", "-");
		cadena = cadena.replace("¿", "-");
		cadena = cadena.replace("°", "");
		cadena = cadena.replace("!", "");
		cadena = cadena.replace("¡", "");
		cadena = cadena.replace("@", "");
		if(cadena.length()>20){
			cadena = cadena.replace("-de-", "-");
			cadena = cadena.replace("-para-", "-");
			cadena = cadena.replace("-ante-", "-");
			cadena = cadena.replace("-bajo-", "-");
			cadena = cadena.replace("-con-", "-");
			cadena = cadena.replace("-contra-", "-");
			cadena = cadena.replace("-a-", "-");
			cadena = cadena.replace("-desde-", "-");
			cadena = cadena.replace("-durante-", "-");
			cadena = cadena.replace("-entre-", "-");
			cadena = cadena.replace("-hacia-", "-");
			cadena = cadena.replace("-hasta-", "-");
			cadena = cadena.replace("-para-", "-");
			cadena = cadena.replace("-por-", "-");
			cadena = cadena.replace("-segun-", "-");
			cadena = cadena.replace("-sin-", "-");
			cadena = cadena.replace("-sobre-", "-");
			cadena = cadena.replace("-tras-", "-");
			cadena = cadena.replace("-el-", "-");
			cadena = cadena.replace("-la-", "-");
			cadena = cadena.replace("-los-", "-");
			cadena = cadena.replace("-las-", "-");
		}
		return cadena;
	}
}
