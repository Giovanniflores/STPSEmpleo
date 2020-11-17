package mx.gob.stps.portal.web.candidate.action;

import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_ESTADO_CIVIL;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_HORARIO_CONTACTO;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_NO_TRABAJA;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_OTROS_MEDIOS;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_SI_TRABAJA;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_TIPO_TELEFONO;
import static mx.gob.stps.portal.web.infra.utils.Constantes.BODY_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_TEMPLATE_MI_ESP_CAND;
import static mx.gob.stps.portal.web.infra.utils.Constantes.ID_CANDIDATO_INDEXABLE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.LST_TELEFONOS_ADICIONALES;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TAB_MENU;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TAB_MIS_DATOS;
import static mx.gob.stps.portal.web.infra.utils.Constantes.USUARIO_APP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TITULO_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.DESCRIPCION_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FACEBOOK_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TWITTER_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.URL_ESPECIFICA;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.gob.stps.portal.core.candidate.vo.IdiomaVO;
import mx.gob.stps.portal.core.candidate.vo.OtroMedioVO;
import mx.gob.stps.portal.core.candidate.vo.PerfilVO;
import mx.gob.stps.portal.core.domicilio.vo.DomicilioVO;
import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.exception.TechnicalException;
import mx.gob.stps.portal.core.infra.mail.exception.MailException;
import mx.gob.stps.portal.core.infra.mail.service.NotificacionService;
import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.core.infra.utils.Constantes.CAMBIO_CORREO;
import mx.gob.stps.portal.core.infra.utils.Constantes.CONFIDENCIALIDAD;
import mx.gob.stps.portal.core.infra.utils.Constantes.CONTACTO_CORREO;
import mx.gob.stps.portal.core.infra.utils.Constantes.CONTACTO_TELEFONO;
import mx.gob.stps.portal.core.infra.utils.Constantes.ESTATUS;
import mx.gob.stps.portal.core.infra.utils.Constantes.GENERO;
import mx.gob.stps.portal.core.infra.utils.Constantes.MULTIREGISTRO;
import mx.gob.stps.portal.core.infra.utils.Constantes.PERFIL;
import mx.gob.stps.portal.core.infra.utils.Constantes.RECIBE_OFERTA;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_PROPIETARIO;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_USUARIO;
import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaCandidatoADesactivarVO;
import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaCandidatoVO;
import mx.gob.stps.portal.core.search.exception.IndexerException;
import mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO;
import mx.gob.stps.portal.persistencia.vo.TelefonoVO;
import mx.gob.stps.portal.utils.Catalogos.TRABAJA_ACTUALMENTE;
import mx.gob.stps.portal.web.address.delegate.DomicilioBusDelegate;
import mx.gob.stps.portal.web.address.delegate.DomicilioBusDelegateImpl;
import mx.gob.stps.portal.web.candidate.delegate.CandidatoBusDelegate;
import mx.gob.stps.portal.web.candidate.delegate.CandidatoBusDelegateImpl;
import mx.gob.stps.portal.web.candidate.form.PerfilForm;
import mx.gob.stps.portal.web.company.delegate.RegisterBusDelegate;
import mx.gob.stps.portal.web.company.delegate.RegisterBusDelegateImpl;
import mx.gob.stps.portal.web.infra.action.GenericAction;
import mx.gob.stps.portal.web.infra.delegate.CatalogoOpcionDelegateImpl;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.infra.utils.PropertiesLoader;
import mx.gob.stps.portal.web.infra.utils.Utils;
import mx.gob.stps.portal.web.infra.vo.CatalogoVO;
import mx.gob.stps.portal.web.infra.vo.ResultVO;
import mx.gob.stps.portal.web.security.delegate.SecutityDelegate;
import mx.gob.stps.portal.web.security.delegate.SecutityDelegateImpl;
import mx.gob.stps.portal.web.security.vo.UsuarioWebVO;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

//import mx.gob.stps.portal.core.infra.vo.TelefonoVO;

/**
 * @author Felipe Juï¿½rez Ramï¿½rez
 * @since 28 de Febrero de 2011
 * 
 */
public class PerfilAction extends GenericAction {

	private static Logger logger = Logger.getLogger(PerfilAction.class);
	private int DETECTA_CAMBIO_LONGITUD_PASSWORD = 4;

	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		int estatusCV = 0;
		HttpSession session = request.getSession(false);
		PerfilForm perfilForm = (PerfilForm) form;		
		UsuarioWebVO usuario = getUsuario(session);
		if (usuario!=null){
			perfilForm.setIdCandidato(usuario.getIdPropietario());
			perfilForm.setIdUsuario(usuario.getIdUsuario());
			perfilForm.setIdTipoUsuario(usuario.getIdTipoUsuario());		
			perfilForm.setUsuario(usuario.getUsuario());	
			perfilForm.setUsuario(getUsuarioCandidato(session));	
		}
		
		try {
			CandidatoBusDelegate services = CandidatoBusDelegateImpl.getInstance();
			
			String usuarioCandidato = services.obtenerLoginUsuario(perfilForm.getIdCandidato());
			perfilForm.setUsuario(usuarioCandidato);	
			perfilForm.setPermisoGeolocalizacion(services.consultarPermisoGeolocalizacion(perfilForm.getIdCandidato()));
			
			estatusCV = services.getEstatusCV(perfilForm.getIdCandidato());
			//Obtiene el estatus del candidato para mostrar el boton de activar/desactivar
			int estatusCandidato = services.consultarEstatus(perfilForm.getIdCandidato());			
			perfilForm.setEstatusCandidato(estatusCandidato);
			if (!services.isEffectiveLimitCandidate(perfilForm.getIdCandidato()))
				session.setAttribute("RENOVAR_VIGENCIA", "RENOVAR_VIGENCIA");
			else session.removeAttribute("RENOVAR_VIGENCIA");
			PerfilVO perfil = services.initPerfil(perfilForm.getIdCandidato());
			
			if (perfil != null) {
				BeanUtils.copyProperties(perfilForm, perfil);
				//PPC
				perfilForm.setPPCStatusForm(perfil.getIdTrabaja(), perfil.getIdEstatusPPC(), false);
				perfilForm.setConfidencialidad(perfil.getConfidencialidad());
				DomicilioBusDelegate serviceDom = DomicilioBusDelegateImpl.getInstance();
				DomicilioVO domicilioVO = serviceDom.buscarDomicilioIdPropietario(perfilForm.getIdCandidato(), TIPO_PROPIETARIO.CANDIDATO.getIdTipoPropietario());
				if (domicilioVO!=null){
					BeanUtils.copyProperties(perfilForm, domicilioVO);
				}

				if (perfilForm.getIdTipoPropietario() == 0) {
					perfilForm.setIdTipoPropietario(TIPO_PROPIETARIO.CANDIDATO.getIdTipoPropietario());
				}
				
				List<TelefonoVO> telefonosVO = services.initTelefonos(perfil.getIdCandidato(),TIPO_PROPIETARIO.CANDIDATO.getIdTipoPropietario());
				for (TelefonoVO telefonoVO : telefonosVO) {
					if (telefonoVO.getPrincipal() == MULTIREGISTRO.PRINCIPAL.getIdOpcion()) {
						this.setTelefonoForm(perfilForm, telefonoVO);
					}
				}
				
				if (perfilForm.getIdTipoPropietarioTel() == 0) {   // TODO: It's null, Fix it
					perfilForm.setIdTipoPropietarioTel(TIPO_PROPIETARIO.CANDIDATO.getIdTipoPropietario());
				} else {
                    // TODO: Default value is it ok?
                    perfilForm.setIdTipoPropietarioTel(TIPO_PROPIETARIO.CANDIDATO.getIdTipoPropietario());
                }

				perfilForm.setOtrosMediosVO(services.initOtrosMedios(perfilForm.getIdCandidato()));
				
				session.setAttribute(LST_TELEFONOS_ADICIONALES,telefonosVO);
				
				if (perfilForm.getTipoTelefonos() == null || perfilForm.getTipoTelefonos().isEmpty()) {
					long[] filtro = new long[] { 3, 4 };
					List<CatalogoOpcionVO> lstCat = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(CATALOGO_OPCION_TIPO_TELEFONO, filtro);
					Comparator<CatalogoOpcionVO> comparator = new Comparator<CatalogoOpcionVO>(){
						public int compare(CatalogoOpcionVO o1, CatalogoOpcionVO o2) {						
							if (o1==null) return 1;
							if (o2==null) return -1;
							String s1 = o1.getOpcion();
							String s2 = o2.getOpcion();							
							return s1.compareTo(s2);
						}
					};				
			    	Collections.sort(lstCat, comparator);
			    	Collections.reverse(lstCat);
					perfilForm.setTipoTelefonos(lstCat);
				}			
			}
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
			logger.error(e);
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			logger.error(e);
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			logger.error(e);
		} catch (BusinessException e) {
			e.printStackTrace();
			logger.error(e);
		} finally {
			if (usuario!=null){
				SecutityDelegateImpl.getInstance().actualizaDatosPersonales(usuario.getIdUsuario(), usuario.getIdPerfil());
			}
		}

		if (null != perfilForm.getCodigoPostal() && !perfilForm.getCodigoPostal().isEmpty())
			session.setAttribute("doknowpcyes", "checked");
		else
			session.setAttribute("doknowpcno", "checked");

		if (session.getAttribute("generoFEM") == null)
			session.setAttribute("generoFEM", GENERO.FEMENINO.getIdOpcion());
		
		if (session.getAttribute("generoMAS") == null)
			session.setAttribute("generoMAS", GENERO.MASCULINO.getIdOpcion());
		
		if (session.getAttribute("trabajaSI") == null)
			session.setAttribute("trabajaSI", TRABAJA_ACTUALMENTE.SI.getIdOpcion());
		
		if (session.getAttribute("trabajaNO") == null)
			session.setAttribute("trabajaNO", TRABAJA_ACTUALMENTE.NO.getIdOpcion());
		
		if (session.getAttribute("recibeTEL") == null)
			session.setAttribute("recibeTEL", RECIBE_OFERTA.TELEFONO.getIdRecibeOferta());
		
		if (session.getAttribute("recibeCORREO") == null)
			session.setAttribute("recibeCORREO", RECIBE_OFERTA.CORREO.getIdRecibeOferta());
		
		if (session.getAttribute("recibeAMBAS") == null)
			session.setAttribute("recibeAMBAS", RECIBE_OFERTA.AMBOS.getIdRecibeOferta());
		
		if (session.getAttribute("confidenSI") == null)
			session.setAttribute("confidenSI", CONFIDENCIALIDAD.SI.getIdOpcion());
		
		if (session.getAttribute("confidenNO") == null)
			session.setAttribute("confidenNO", CONFIDENCIALIDAD.NO.getIdOpcion());
		
		if (session.getAttribute("ctcCorreoSI") == null)
			session.setAttribute("ctcCorreoSI", CONTACTO_CORREO.SI.getIdContactoCorreo());
		
		if (session.getAttribute("ctcCorreoNO") == null)
			session.setAttribute("ctcCorreoNO", CONTACTO_CORREO.NO.getIdContactoCorreo());
		
		if (session.getAttribute("ctcTelSI") == null)
			session.setAttribute("ctcTelSI", CONTACTO_TELEFONO.SI.getIdContactoTelefono());
		
		if (session.getAttribute("ctcTelNO") == null)
			session.setAttribute("ctcTelNO", CONTACTO_TELEFONO.NO.getIdContactoTelefono());
		
		//Para validacion de correo
		if (session.getAttribute("tipoCand") == null)
			session.setAttribute("tipoCand", TIPO_USUARIO.CANDIDATO.getIdTipoUsuario());
		
		if (session.getAttribute("cteUsu") == null)
			session.setAttribute("cteUsu", USUARIO_APP);
		
		session.setAttribute("estatusCV", estatusCV);
		session.setAttribute("thisTabId", Constantes.EVALUA_CV_FLUJO_PANTALLA.PERFIL.getIdOpcion());
		session.setAttribute("thisTabName", Constantes.EVALUA_CV_FLUJO_PANTALLA.PERFIL.getOpcion());
		session.setAttribute("thisTabPercent", Constantes.EVALUA_CV_FLUJO_PANTALLA.PERFIL.getPorcentaje());
		session.setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
		cargaCatalogos(session);
		
		session.setAttribute(TAB_MENU, TAB_MIS_DATOS);

		PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Datos personales");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Perfil, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
		return mapping.findForward(FORWARD_TEMPLATE_MI_ESP_CAND);
	}

	private void cargaCatalogos(HttpSession session){

		try {
			long[] filtro = {5, 6};
			List<CatalogoOpcionVO> estadoscivil = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(CATALOGO_OPCION_ESTADO_CIVIL, filtro, true);
			session.setAttribute("estadoscivil", estadoscivil);

			List<CatalogoOpcionVO> horarioscontac = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(CATALOGO_OPCION_HORARIO_CONTACTO, true);
			session.setAttribute("horarioscontac", horarioscontac);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
	}
	
	/* TODO: CAMBIO EN PROCESO  */
	public List<Long> obtenerIdsOfertasRelacionadasCandidatoDesactivar(List<OfertaCandidatoVO> listaOfertasCandidato){
		List<Long> lstIdsOfertas = new ArrayList<Long>();
		if(null!=listaOfertasCandidato && !listaOfertasCandidato.isEmpty()){
			Iterator<OfertaCandidatoVO> itOfertas = listaOfertasCandidato.iterator();
			while(itOfertas.hasNext()){
				OfertaCandidatoVO tmpOferta = (OfertaCandidatoVO) itOfertas.next();
				if(tmpOferta.getEstatus()==ESTATUS.VINCULADO.getIdOpcion() || tmpOferta.getEstatus()==ESTATUS.SELECCIONADO.getIdOpcion()
						|| tmpOferta.getEstatus()==ESTATUS.POSTULADO.getIdOpcion() || tmpOferta.getEstatus()==ESTATUS.EN_PROCESO.getIdOpcion()){
					lstIdsOfertas.add(tmpOferta.getIdOfertaEmpleo()); 
				}
			}
		}
		return lstIdsOfertas;
	}
	
	public  ActionForward desactivar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException {
		this.init(mapping, form, request, response);
		HttpSession session = request.getSession(false);
		UsuarioWebVO usuario = getUsuario(session);
		PerfilForm perfilForm = (PerfilForm)form;
		ResultVO msg =  null;
		CandidatoBusDelegate services = CandidatoBusDelegateImpl.getInstance();
		msg = new ResultVO(getMensaje(request, "can.desactivar.ext"), ResultVO.TYPE_SUCCESS);
		try{
			if(perfilForm.getIdCandidato()==0){
				PerfilVO perfil=services.initPerfil(usuario.getIdPropietario());
				setPerfilForm(perfilForm, perfil);
			}
			logger.info("perfilForm "+ perfilForm);
			if(usuario.getIdPerfil() == PERFIL.CANDIDATO.getIdOpcion()){
				services.desactivarCandidato(perfilForm.getIdCandidato(), usuario.getIdUsuario(), 
							Constantes.MOTIVO_DESACTIVACION_CANDIDATO.A_PETICION_DEL_USUARIO.getIdMotivo(), null);					
			} else if(usuario.getIdPerfil() == PERFIL.ADMINISTRADOR.getIdOpcion()) {
				String detalleDesactivacion = null;
				//COMENTAR EN PRODUCCION detalleDesactivacion = perfilForm.getDetalleDesactivacion();				
				services.desactivarCandidato(perfilForm.getIdCandidato(), usuario.getIdUsuario(), 
						Constantes.MOTIVO_DESACTIVACION_CANDIDATO.POR_MAL_USO_SERVICIOS_SNE.getIdMotivo(), detalleDesactivacion);
			}
			notificarEmpresasDesactivacionCandidato(perfilForm.getIdCandidato(), getNombreCompletoCandidato(perfilForm));
		} catch (Exception e) {
			msg = new ResultVO(getMensaje(request, "can.desactivar.err"), ResultVO.TYPE_ERROR);
			e.printStackTrace(); logger.error("Error al DESACTIVAR Candidato "+ e);
		}
		
		perfilForm.setMsg(msg);
		this.setResponseJSON(response, perfilForm);				
		return null;
	}	
	
	private String getNombreCompletoCandidato(PerfilForm perfilForm){
		String nombreCompletoCandidato = perfilForm.getNombre() + " " + perfilForm.getApellido1();
		if(null!=perfilForm.getApellido2() && !perfilForm.getApellido2().equalsIgnoreCase(""))
			nombreCompletoCandidato = nombreCompletoCandidato + " " + perfilForm.getApellido2();	
		return nombreCompletoCandidato;
	}
	
	private void notificarEmpresasDesactivacionCandidato(long idCandidato, String nombreCompletoCandidato){
		CandidatoBusDelegate services = CandidatoBusDelegateImpl.getInstance();
		List<OfertaCandidatoADesactivarVO> ofertasRelacionadas;
		try {
			ofertasRelacionadas = services.buscarOfertasRelacionadas(idCandidato);
			if(null!=ofertasRelacionadas && !ofertasRelacionadas.isEmpty()){
				Iterator<OfertaCandidatoADesactivarVO> itOfertas = ofertasRelacionadas.iterator();
				while(itOfertas.hasNext()){
					OfertaCandidatoADesactivarVO oferta = (OfertaCandidatoADesactivarVO) itOfertas.next();
					generarNotificacionDesactivacion(nombreCompletoCandidato, oferta);
				}			
			}						
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		}
	}
	
	private void generarNotificacionDesactivacion(String candidatoNombre, OfertaCandidatoADesactivarVO vo){
		NotificacionService notificacionService = new NotificacionService();
		try {
			notificacionService.notificacionCandidatoDesactivado(candidatoNombre, vo);
			logger.info("Correo Candidato desactivado ");
		} catch (MailException e) {
			e.printStackTrace();
		}		
	}	
	
	public  ActionForward reactivar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession(false);
		UsuarioWebVO usuario = getUsuario(session);
		PerfilForm perfilForm = (PerfilForm)form;
		ResultVO msg =  null;
		CandidatoBusDelegate services = CandidatoBusDelegateImpl.getInstance();
		try{
			if(usuario.getIdPerfil()== Constantes.PERFIL.ADMINISTRADOR.getIdOpcion()){
				services.reactivarCandidato(perfilForm.getIdCandidato(), usuario.getIdPropietario());
			}
		} catch (Exception e) {
			msg = new ResultVO(getMensaje(request, "can.guardar.err"), ResultVO.TYPE_ERROR);
			e.printStackTrace(); logger.error(e);
		}
		if(usuario.getIdPerfil()== Constantes.PERFIL.ADMINISTRADOR.getIdOpcion()){
			msg = new ResultVO(getMensaje(request, "can.reactivar.ext"), ResultVO.TYPE_SUCCESS);
			perfilForm.setMsg(msg);			
		}
		this.setResponseJSON(response, perfilForm);
		return null;		
	}		
	
	
	public ActionForward registrar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession(false);
		UsuarioWebVO usuario = getUsuario(session);
		PerfilForm perfilForm = (PerfilForm)form;
		perfilForm.setIdCandidato(usuario.getIdPropietario());
		perfilForm.setIdUsuario(usuario.getIdUsuario());
		perfilForm.setIdTipoUsuario(usuario.getIdTipoUsuario());
		PerfilVO perfilVo = new PerfilVO();
		convertir(perfilVo, perfilForm);
		ResultVO msg =  null;
		CandidatoBusDelegate services = CandidatoBusDelegateImpl.getInstance();
		
		try {		
			long list[];
			List<OtroMedioVO> otrosMedios = services.initOtrosMedios(perfilForm.getIdCandidato());
			list = new long[otrosMedios.size()];
			for (int i=0; i<otrosMedios.size(); i++) {
				OtroMedioVO om = otrosMedios.get(i);
				list[i] = om.getIdMedioBusqueda();
			}
			perfilVo.setIdOtrosMedios(list);			
			
			//Si el correoElectronico es diferente al actual
			if (null!=perfilForm.getCorreoElectronico() && null!=perfilForm.getCorreoAux() 
					&& !perfilForm.getCorreoElectronico().equals(perfilForm.getCorreoAux())
					&& ((perfilForm.isPpc() && !perfilForm.getCorreoElectronico().isEmpty())
							|| !perfilForm.isPpc())) {
				perfilVo.setCambioCorreo(CAMBIO_CORREO.SI.getIdOpcion());
			} else {
				perfilVo.setCambioCorreo(CAMBIO_CORREO.NO.getIdOpcion());
			}
			
			//PPC
			
			perfilVo = services.registrarPerfil(perfilVo);
			
			/** Se agrega el indicador para indexar al Candidato al FINALIZA su Session **/
			request.getSession().setAttribute(ID_CANDIDATO_INDEXABLE, perfilVo.getIdCandidato());
			//BolsasTrabajoBusDelegateImpl.getInstance().indexaCandidato(perfilVo.getIdCandidato());
			String mensaje = "";
			if (perfilForm.getPassword().length() > DETECTA_CAMBIO_LONGITUD_PASSWORD) {
				SecutityDelegate security = SecutityDelegateImpl.getInstance();
				//security.confirmaContrasenaCandidato(usuario.getIdPropietario(), perfilForm.getCorreoElectronico(), perfilForm.getPassword());
				//no se debe enviar correo al candidato
				security.cambioContrasenaCandidato(usuario.getIdPropietario(), perfilForm.getPassword()) ;
			}
			if((perfilForm.getCorreoAux().isEmpty() && perfilForm.getCorreoElectronico().isEmpty()) && perfilForm.isPpc())
			{
				
				mensaje = "Para el programa de PPC debe capturar su correo electrónico.";
				msg = new ResultVO(mensaje, ResultVO.TYPE_ERROR);
			}
			
			else{
			
				this.setPerfilForm(perfilForm, perfilVo);
				saveAddPhoneList(request);
				saveAddLangList(request);
				mensaje = getMensaje(request,"can.guardar.ext");
				if(perfilForm.isMensajeExtra()){
					mensaje = mensaje + "  \n " +  perfilForm.getNotSetNoToTrabajando();
				}
				msg = new ResultVO(mensaje, ResultVO.TYPE_SUCCESS);
			
			}
			
			
		} catch (BusinessException e) {
			msg = new ResultVO(getMensaje(request, "can.guardar.err"), ResultVO.TYPE_ERROR);
			e.printStackTrace(); 
			logger.error("Excepción de negocio al persistir perfil del candidato " + perfilVo.getIdCandidato(), e);
		} catch (ServiceLocatorException e) {
			e.printStackTrace(); 
			msg = new ResultVO(getMensaje(request, "can.guardar.err"), ResultVO.TYPE_ERROR);
			logger.error("Excepción de localización de servicio al persistir perfil del candidato " + perfilVo.getIdCandidato(), e);
		} catch (PersistenceException e) {
			msg = new ResultVO(getMensaje(request, "can.guardar.err"), ResultVO.TYPE_ERROR);
			e.printStackTrace(); 
			logger.error("Excepción al persistir perfil del candidato " + perfilVo.getIdCandidato(), e);
		} catch (TechnicalException e) {
			msg = new ResultVO(getMensaje(request, "can.guardar.err"), ResultVO.TYPE_ERROR);
			e.printStackTrace(); 
			logger.error("Excepción tecnica al persistir perfil del candidato " + perfilVo.getIdCandidato(), e);
		} catch (SQLException e) {
			msg = new ResultVO(getMensaje(request, "can.guardar.err"), ResultVO.TYPE_ERROR);
			e.printStackTrace(); 
			logger.error("Excepción de sql al persistir experiencia del candidato " + perfilVo.getIdCandidato(), e);
		} catch (MailException e) {
			msg = new ResultVO(getMensaje(request, "can.guardar.err"), ResultVO.TYPE_ERROR);
			e.printStackTrace();
			logger.error("Excepción al enviar correo en perfil del candidato " + perfilVo.getIdCandidato(), e);
		} catch (IndexerException e) {
			msg = new ResultVO(getMensaje(request, "aut.autorizacion.msg.indexer.error"), ResultVO.TYPE_ERROR);
			e.printStackTrace();
			logger.error("Excepción al indexar candidato " + perfilVo.getIdCandidato(), e);
		} catch (Exception e) {
			msg = new ResultVO(getMensaje(request, "can.guardar.err"), ResultVO.TYPE_ERROR);
			e.printStackTrace();
			logger.error("Excepción al persistir candidato " + perfilVo.getIdCandidato(), e);
		}
		perfilForm.setMsg(msg);
		this.setResponseJSON(response, perfilForm);
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public ActionForward saveAddPhone(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException {
		java.util.Date date = new Date();
		List<TelefonoVO> phoneAddList = null;
		UsuarioWebVO user = getUsuario(request.getSession());
		String clave = request.getParameter("claveAdd");
		String acceso = request.getParameter("accesoAdd");
		String telefono = request.getParameter("telefonoAdd");
		Integer idTipoTelefono = Utils.parseInt(request.getParameter("tipoAdd"));
		String extension = request.getParameter("extensionAdd");
		TelefonoVO phone = new TelefonoVO();
		phone.setAcceso(acceso);
		phone.setClave(clave);
		phone.setExtension(extension);
		phone.setFechaAlta(date);
		phone.setIdTipoTelefono((int) idTipoTelefono);
		phoneAddList = (List<TelefonoVO>) request.getSession().getAttribute(LST_TELEFONOS_ADICIONALES);
		if (null != phoneAddList) {
			if (phoneAddList.isEmpty()) {
				phone.setPrincipal(Constantes.MULTIREGISTRO.PRINCIPAL.getIdOpcion());
			} else {
				phone.setPrincipal(Constantes.MULTIREGISTRO.ADICIONAL.getIdOpcion());
			}
		} else {
			phone.setPrincipal(Constantes.MULTIREGISTRO.PRINCIPAL.getIdOpcion());
			phoneAddList = new ArrayList<TelefonoVO>();
		}
		phone.setIdTipoPropietario(Constantes.TIPO_PROPIETARIO.CANDIDATO.getIdTipoPropietario());
		phone.setIdPropietario(user.getIdPropietario());
		phone.setTelefono(telefono);
		RegisterBusDelegate service = RegisterBusDelegateImpl.getInstance();
		Long idPhone = 0L;
		try {
			idPhone = service.registrarTelefono(phone);
			phone.setIdTelefono(idPhone);
			phoneAddList.add(phone);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}

//		request.getSession().setAttribute(LST_TELEFONOS_ADICIONALES, phoneAddList);
//	    return mapping.findForward("ACTION_REGISTROS_TABLA");

		redirectJsonResponse(response, idPhone.toString());

		return null;
	}
	
	@SuppressWarnings("unchecked")
	public ActionForward deletePhone(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException {
		String idPhone = request.getParameter("idPhone");
		RegisterBusDelegate service = RegisterBusDelegateImpl.getInstance();
		List<TelefonoVO> phoneAddList = (List<TelefonoVO>)request.getSession().getAttribute(LST_TELEFONOS_ADICIONALES);
		if (idPhone!=null && phoneAddList!=null && !phoneAddList.isEmpty()) {
			int index = 0;
			boolean found = false;
			for (index = 0; index < phoneAddList.size(); index++) {
				if (phoneAddList.get(index).getIdTelefono() == Utils.parseLong(idPhone)) {
					TelefonoVO vo = phoneAddList.get(index);
					try {
						service.eliminarTelefono(vo);
					} catch (Exception e) { e.printStackTrace(); logger.error(e); }
					found = true;
					break;
				}
			}
			if (found) phoneAddList.remove(index);	
		}
        return mapping.findForward("ACTION_REGISTROS_TABLA");
	}
	
	private void saveAddPhoneList(HttpServletRequest request) {
		java.util.Date date = new Date();
		List<TelefonoVO> phoneAddList = new ArrayList<TelefonoVO>();
    	UsuarioWebVO user = getUsuario(request.getSession());
    	request.getSession().removeAttribute(LST_TELEFONOS_ADICIONALES);
    	for (int i=0; i<3; i++) {
    		long idTelefono = Utils.parseLong(request.getParameter("idTelefonoAdd" + i));
    		if (idTelefono > 0) {
	    		String clave = request.getParameter("claveAdd" + i);
	        	String acceso = request.getParameter("accesoAdd" + i);
	       		String telefono = request.getParameter("telefonoAdd" + i);
	       		Integer idTipoTelefono = Utils.parseInt(request.getParameter("idTipoTelefonoAdd" + i));
	       		String extension = request.getParameter("extensionAdd" + i);
	       		TelefonoVO phone = new TelefonoVO();
	       		phone.setClave(clave);
	       		phone.setIdTelefono(idTelefono);
	        	phone.setAcceso(acceso);
	       		phone.setExtension(extension);
	       		phone.setFechaAlta(date);
	       		phone.setIdTipoTelefono((int)idTipoTelefono);
	       		phone.setIdTipoPropietario(Constantes.TIPO_PROPIETARIO.CANDIDATO.getIdTipoPropietario());
	       		phone.setIdPropietario(user.getIdPropietario());
	        	phone.setTelefono(telefono);
	       		phone.setPrincipal(Constantes.MULTIREGISTRO.ADICIONAL.getIdOpcion());
	       		RegisterBusDelegate service = RegisterBusDelegateImpl.getInstance();
	       		try {
	       			service.actualizarTelefono(phone);
	       		} catch (Exception e) { e.printStackTrace(); logger.error(e); }
	       		phoneAddList.add(phone);
    		}
    	}
		request.getSession().setAttribute(LST_TELEFONOS_ADICIONALES, phoneAddList);	            	    	
    }
	
	private void saveAddLangList(HttpServletRequest request) throws ServiceLocatorException, SQLException {
		List<IdiomaVO> idiomasVO = null;
    	request.getSession().removeAttribute("IDIOMAS_LIST");
    	CandidatoBusDelegate services = CandidatoBusDelegateImpl.getInstance();

		idiomasVO = services.buscarIdiomas(getidCandidato(request.getSession()));
    	for (int i=0; i<3; i++) {
    		long idIdioma = Utils.parseLong(request.getParameter("idIdiomaAdd" + i));
    		if (idIdioma > 0) {
	    		long idCertificacion = Utils.parseLong(request.getParameter("idCertificacionAdd" + i));
	        	long idDominio = Utils.parseLong(request.getParameter("idDominioAdd" + i));
	       		long idCandidatoIdioma = Utils.parseLong(request.getParameter("idCandidatoIdioma" + i));
	       		IdiomaVO lang = new IdiomaVO();
	       		lang.setIdCandidatoIdioma(idCandidatoIdioma);
	       		lang.setIdCertificacion(idCertificacion);
	       		lang.setIdDominio(idDominio);
	       		lang.setIdIdioma(idIdioma);
				if (null != idiomasVO) {
					if (idiomasVO.isEmpty())
						lang.setPrincipal(Constantes.MULTIREGISTRO.PRINCIPAL.getIdOpcion());
					else
						lang.setPrincipal(Constantes.MULTIREGISTRO.ADICIONAL.getIdOpcion());
				}else {
					lang.setPrincipal(Constantes.MULTIREGISTRO.PRINCIPAL.getIdOpcion());
					idiomasVO = new ArrayList<IdiomaVO>();
				}
				boolean _existe = false;
				for (IdiomaVO idiomaPrev : idiomasVO ) {
					_existe = (idiomaPrev.getIdIdioma() == idIdioma);
					if (_existe)
						break;
				}
				if (!_existe) {
					services.agregarIdioma(getidCandidato(request.getSession()), lang);
					idiomasVO.add(lang);
				}
    		}
    	}
		request.getSession().setAttribute("IDIOMAS_LIST", idiomasVO);	            	    	
    }
	
	
	public ActionForward validarCorreo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException {
		PerfilForm perfilForm = (PerfilForm) form;
		boolean esCorreoUnico = false;
		
		try {
			SecutityDelegate service = SecutityDelegateImpl.getInstance();
			esCorreoUnico = service.esCorreoUnico(perfilForm.getCorreoElectronico());
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e);
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
			logger.error(e);
		}
		
		ResultVO resultVO = null;
		String messagePPC = messagePPC(perfilForm);
		
		if (esCorreoUnico) {
			
			
			resultVO = new ResultVO(getMensaje(request, "can.correo.negocio") + messagePPC, ResultVO.TYPE_SUCCESS);
		} else {
			resultVO = new ResultVO(getMensaje(request, "can.correo.negocio.err") + messagePPC, ResultVO.TYPE_ERROR);
		}
		
		String json = toJson(resultVO);
		redirectJsonResponse(response, json);
		
		return null;
	}

	private String messagePPC(PerfilForm form) {
		if(form.isPpc() && form.isTrabajando()){
			return " modifica el estatus de trabajas actualmente para que puedes aplicar para el programma de PPC ";
		}
		return "";
	}

	public ActionForward buscaTrabajo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<CatalogoOpcionVO> opciones = new ArrayList<CatalogoOpcionVO>();

		try {
			String trabaja = request.getParameter("idTrab");

			int idTrabaja = Utils.parseInt(trabaja);

			if (idTrabaja == TRABAJA_ACTUALMENTE.SI.getIdOpcion())
				opciones = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(CATALOGO_OPCION_SI_TRABAJA);
			else if (idTrabaja == TRABAJA_ACTUALMENTE.NO.getIdOpcion())
				opciones = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(CATALOGO_OPCION_NO_TRABAJA);
			
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
			logger.error(e);
		}
		CatalogoVO cat = getCatalogo(opciones);
		String json = toJson(cat);
		redirectJsonResponse(response, json);

		return null;
	}

	public ActionForward cargarEstadoCivil(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException {

		List<CatalogoOpcionVO> opciones = new ArrayList<CatalogoOpcionVO>();

		try {
			long[] filtro = {5, 6};
			opciones = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(CATALOGO_OPCION_ESTADO_CIVIL, filtro, true);
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
			logger.error(e);
		}

		CatalogoVO cat = getCatalogo(opciones);
		String json = toJson(cat);
		redirectJsonResponse(response, json);

		return null;
	}

	public ActionForward cargarHorarioContacto(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<CatalogoOpcionVO> opciones = new ArrayList<CatalogoOpcionVO>();

		try {
			opciones = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(CATALOGO_OPCION_HORARIO_CONTACTO, true);
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
			logger.error(e);
		}

		CatalogoVO cat = getCatalogo(opciones);
		String json = toJson(cat);
		redirectJsonResponse(response, json);

		return null;
	}

	public ActionForward cargarOtrosMedios(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<CatalogoOpcionVO> opciones = new ArrayList<CatalogoOpcionVO>();

		try {
			opciones = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(CATALOGO_OPCION_OTROS_MEDIOS, true);
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
			logger.error(e);
		}

		CatalogoVO cat = getCatalogo(opciones);
		String json = toJson(cat);
		redirectJsonResponse(response, json);

		return null;
	}

	private void convertir(PerfilVO destino, PerfilForm origen) {
		try {
			BeanUtils.copyProperties(destino, origen);
			if (null == origen.getInicioBusqueda())
				destino.setInicioBusqueda(new Date());			
			destino.setContactoCorreo(origen.getContactoCorreo());
			destino.setContactoTelefono(origen.getContactoTelefono());
			destino.setIdRecibeOferta(origen.getIdRecibeOferta());
			destino.setConfidencialidad(origen.getConfidencialidad());
			
			TelefonoVO principal = new TelefonoVO();
			principal.setIdTelefono(origen.getIdTelefono());
			
			principal.setIdPropietario(origen.getIdCandidato());
		
			principal.setIdTipoPropietario((int)origen.getIdTipoPropietarioTel());
			principal.setIdTipoTelefono((int)origen.getIdTipoTelefono1());
			principal.setAcceso(origen.getAcceso1());
			principal.setClave(origen.getClave1());
			principal.setTelefono(origen.getTelefono1());
			principal.setExtension(origen.getExtension1());
			principal.setPrincipal(MULTIREGISTRO.PRINCIPAL.getIdOpcion());
			
			destino.setPrincipal(principal);
			
			//segundo telefono
			if(!origen.getTelefono2().isEmpty()){
				
			
				destino.setSecundario(getTelefonoVOfromForm(origen,2));
			}
			else
			{
				destino.setSecundario(null);
			}
			
			if(!origen.getTelefono3().isEmpty()){
				destino.setTercero(getTelefonoVOfromForm(origen,3));
			}
			else{
				destino.setTercero(null);
			}
			
	
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			logger.error(e);
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			logger.error(e);
		}
	}
	
	private TelefonoVO getTelefonoVOfromForm(PerfilForm origen, int id) {
		TelefonoVO principal = new TelefonoVO();
		
	
		switch(id) {
		case 2:
			principal.setIdTipoPropietario((int)origen.getIdTipoPropietarioTel());
			principal.setIdTipoTelefono((int)origen.getIdTipoTelefono2());
			principal.setAcceso(origen.getAcceso2());
			principal.setClave(origen.getClave2());
			principal.setTelefono(origen.getTelefono2());
			principal.setExtension(origen.getExtension2());
			break;
		case 3:

			principal.setIdTipoPropietario((int)origen.getIdTipoPropietarioTel());
			principal.setIdTipoTelefono((int)origen.getIdTipoTelefono3());
			principal.setAcceso(origen.getAcceso3());
			principal.setClave(origen.getClave3());
			principal.setTelefono(origen.getTelefono3());
			principal.setExtension(origen.getExtension3());
			break;
			
		}
		principal.setPrincipal(MULTIREGISTRO.ADICIONAL.getIdOpcion());
		return principal;
	}

	private void setPerfilForm(PerfilForm destino, PerfilVO origen) {
		try {
			BeanUtils.copyProperties(destino, origen);
			if (destino.getContactoCorreo() == 0)
				destino.setContactoCorreo(CONTACTO_CORREO.NO.getIdContactoCorreo());
			if (destino.getContactoTelefono() == 0)
				destino.setContactoTelefono(CONTACTO_TELEFONO.NO.getIdContactoTelefono());
			if (destino.getIdRecibeOferta() == 0)
				destino.setIdRecibeOferta(RECIBE_OFERTA.NO.getIdRecibeOferta());
			TelefonoVO principal = origen.getPrincipal();
			this.setTelefonoForm(destino, principal);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			logger.error(e);
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			logger.error(e);
		}
	}
	
	private void setTelefonoForm(PerfilForm destino, TelefonoVO origen) {
		destino.setIdTelefono(origen.getIdTelefono());
		destino.setIdPropietario(origen.getIdPropietario());
		destino.setIdTipoPropietarioTel(origen.getIdTipoPropietario());
		destino.setIdTipoTelefono(origen.getIdTipoTelefono());
		destino.setAcceso(origen.getAcceso());
		destino.setClave(origen.getClave());
		destino.setTelefono(origen.getTelefono());
		destino.setExtension(origen.getExtension());
	}
	
	private void setResponseJSON(HttpServletResponse response, PerfilForm perfilForm) throws IOException {
		String json = toJson(perfilForm);
		redirectJsonResponse(response, json);
	}
	
	private long getidCandidato(HttpSession session) {
		UsuarioWebVO usuario = super.getUsuario(session);
		return usuario.getIdPropietario();
	}
	
	private String getUsuarioCandidato(HttpSession session) {
		UsuarioWebVO usuario = super.getUsuario(session);
		return usuario.getUsuario();
	}

	/**
	 * Redirecciona la respuesta enviando la salida JSON.
	 *
	 * @param response the response
	 * @param json the json
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void redirectJsonResponse(HttpServletResponse response, String json) throws IOException{
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter pw = response.getWriter();
		pw.write(json);
		pw.flush();
		pw.close();
		response.flushBuffer();
	}
	
	public  ActionForward renew(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession(false);
		PerfilForm perfilForm = (PerfilForm)form;
		ResultVO msg =  null;
		CandidatoBusDelegate services = CandidatoBusDelegateImpl.getInstance();
		msg = new ResultVO(getMensaje(request, "can.renovar.msg"), ResultVO.TYPE_SUCCESS);
		try {
			services.upgradeEffective(perfilForm.getIdCandidato());
			session.removeAttribute("RENOVAR_VIGENCIA");
		} catch (Exception e) {
			msg = new ResultVO(getMensaje(request, "can.renovar.err"), ResultVO.TYPE_ERROR);
			e.printStackTrace(); logger.error(e);
		}
		perfilForm.setMsg(msg);
		this.setResponseJSON(response, perfilForm);				
		return null;
	}	
}
