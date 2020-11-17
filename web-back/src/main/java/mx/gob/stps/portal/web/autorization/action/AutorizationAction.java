package mx.gob.stps.portal.web.autorization.action;

import static mx.gob.stps.portal.web.infra.utils.Constantes.BODY_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_REDIRECT_SWB;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_TEMPLATE_ESP_ADMIN;
import static mx.gob.stps.portal.web.infra.utils.Constantes.MSG_ERROR_SESSION;
import static mx.gob.stps.portal.web.infra.utils.Constantes.PARAM_ID_REGISTRO_POR_VALIDAR;
import static mx.gob.stps.portal.web.infra.utils.Constantes.URL_REDIRECT_SWB;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.gob.stps.portal.core.autorizacion.vo.RegistroPorValidarVO;
import mx.gob.stps.portal.core.candidate.vo.PerfilLaboralVo;
import mx.gob.stps.portal.core.domicilio.vo.DomicilioVO;
import mx.gob.stps.portal.core.empresa.vo.EmpresaFraudulentaVO;
import mx.gob.stps.portal.core.empresa.vo.EmpresaVO;
import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.exception.TechnicalException;
import mx.gob.stps.portal.core.infra.mail.exception.MailException;
import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.core.infra.utils.Constantes.ESTATUS;
import mx.gob.stps.portal.core.infra.utils.Constantes.MULTIREGISTRO;
import mx.gob.stps.portal.core.infra.utils.Constantes.PERFIL;
import mx.gob.stps.portal.core.infra.utils.Constantes.SUBTIPO_REGISTRO;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_PERSONA;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_REGISTRO;
import mx.gob.stps.portal.core.infra.utils.Constantes.VALIDACION_MOTIVO;
import mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO;
import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaEmpleoJB;
import mx.gob.stps.portal.core.oferta.vo.OfertaEmpleoVO;
import mx.gob.stps.portal.core.search.exception.IndexerException;
import mx.gob.stps.portal.core.seguridad.vo.UsuarioVO;
import mx.gob.stps.portal.core.testimonio.vo.TestimonioVO;
import mx.gob.stps.portal.persistencia.vo.TelefonoVO;
import mx.gob.stps.portal.web.autorization.delegate.AutorizationBusDelegate;
import mx.gob.stps.portal.web.autorization.delegate.AutorizationBusDelegateImpl;
import mx.gob.stps.portal.web.autorization.form.AutorizationForm;
import mx.gob.stps.portal.web.autorization.vo.OfertaDetalleVO;
import mx.gob.stps.portal.web.candidate.delegate.CandidatoBusDelegateImpl;
import mx.gob.stps.portal.web.company.delegate.RegisterBusDelegate;
import mx.gob.stps.portal.web.company.delegate.RegisterBusDelegateImpl;
import mx.gob.stps.portal.web.infra.action.PagerAction;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.infra.utils.PropertiesLoader;
import mx.gob.stps.portal.web.infra.utils.Utils;
import mx.gob.stps.portal.web.infra.vo.CatalogoVO;
import mx.gob.stps.portal.web.offer.delegate.OfferBusDelegateImpl;
import mx.gob.stps.portal.web.security.delegate.SecutityDelegateImpl;
import mx.gob.stps.portal.web.security.vo.UsuarioWebVO;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
//import mx.gob.stps.portal.persistencia.vo.TelefonoVO;

public class AutorizationAction extends PagerAction {

	private static Logger logger = Logger.getLogger(AutorizationAction.class);

	private static final String FORWARD_DETALLE_EMPRESA = "ACTION_DETALLE_EMPRESA";
	private static final String FORWARD_DETALLE_EMPRESA_FRAUDULENTA = "ACTION_DETALLE_EMPRESA_FRAUDULENTA";
	private static final String FORWARD_DETALLE_OFERTA = "ACTION_DETALLE_OFERTA";
	private static final String FORWARD_DETALLE_TESTIMONIO = "ACTION_DETALLE_TESTIMONIO";
	private static final String FORWARD_DETALLE_VIDEOC = "ACTION_DETALLE_VIDEOC";
	private static final String FORWARD_DETALLE_MOTIVO = "ACTION_DETALLE_MOTIVO";

	private static final String FORWARD_EDICION_EMPRESA = "ACTION_EDICION_EMPRESA";
	private static final String FORWARD_EDICION_EMPRESA_POR_AUT = "ACTION_EDICION_EMPRESA_POR_AUT";
	//RBM1  TK997 y TK998 entra a pantalla de edicion actual  se elimina
	//private static final String FORWARD_EDICION_OFERTA = "ACTION_EDICION_OFERTA";	
	private static final String FORWARD_EDICION_OFERTA_RU = "ACTION_EDICION_OFERTA_RU";
	//RBM1 TK997 y TK998 se redirecciona a RU
	private static final String FORWARD_RECHAZA_ADMIN= "ACTION_BUSQUEDA_ADMIN";
	private static final String FORWARD_RECHAZA_ADMIN_PENDIENTE_PUBLICAR= "ACTION_BUSQUEDA_ADMIN_PENDIENTE_PUBLICAR";

	@Override
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		ActionForward commonForward= commonInit(mapping, form, request, response, session);
			
		if(null!=commonForward){
			return commonForward;
		}
		
		session.setAttribute(BODY_JSP, mapping.getInput());
		return mapping.findForward(FORWARD_TEMPLATE_ESP_ADMIN);
	}
	

	
	public ActionForward initDetalle(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		ActionForward commonForward= commonInit(mapping, form, request, response, session);
		
		if(null!=commonForward){
			return commonForward;
		}
		
		
		UsuarioWebVO usuario = getUsuario(session);
		
		if(usuario.getIdRegValidar()<=0){
			session.setAttribute(BODY_JSP, mapping.getInput());
			return mapping.findForward(FORWARD_TEMPLATE_ESP_ADMIN);
		}
			
		AutorizationForm autForm = (AutorizationForm)form;		
		autForm.setIdRegValidar(usuario.getIdRegValidar());
		
		return detalleRegistro( mapping,  form,  request,  response) ;
	}
	
	
	public ActionForward commonInit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
	
		AutorizationForm autForm = (AutorizationForm)form;		
		UsuarioWebVO usuario = getUsuario(session);

		this.PAGE_NUM_ROW = 10;
		this.PAGE_JUMP_SIZE = 10;
		session.setAttribute("NUM_REGISTROS", PAGE_NUM_ROW);
		
		// Si el usuario no es publicador no se le permite el acceso
		if (usuario !=null && usuario.getIdPerfil() != PERFIL.PUBLICADOR.getIdOpcion() &&
			usuario.getIdPerfil() != PERFIL.ADMINISTRADOR_SNETEL.getIdOpcion()){
			
			String msgerr = "No cuenta con los permisos para ingresar al modulo de autorización.";
			session.setAttribute(MSG_ERROR_SESSION, msgerr);

			PropertiesLoader properties = PropertiesLoader.getInstance();
			request.setAttribute(URL_REDIRECT_SWB, properties.getProperty("app.swb.redirect.logout.home"));
			return mapping.findForward(FORWARD_REDIRECT_SWB);
		}

		if (usuario!=null)
			estableNombreUsuario(request, usuario.getIdUsuario());
		
		estableRegistrosPorValidar(request, (usuario!=null? usuario.getIdUsuario() : 0), autForm);
		return null;
	}

	/** Se consultan y asignan los Registros por Validar al Publicador  **/
	private void estableRegistrosPorValidar(HttpServletRequest request, long idUsuario, AutorizationForm autForm){
		HttpSession session = request.getSession();
		
		if (autForm.getRegistros()==null || autForm.getRegistros().isEmpty() || autForm.getIdRegValidar()>0){
			
			try {
				List<RegistroPorValidarVO> registros = getDelegate().asignaRegistrosPorValidar(idUsuario);
				autForm.setRegistros(registros);

			} catch (ServiceLocatorException e) {
				e.printStackTrace(); logger.error(e);
				registraError(request, "aut.error.locator");
			} catch (SQLException e) {
				e.printStackTrace(); logger.error(e);
				registraError(request, "aut.error.sql.persist");
			} catch (BusinessException e) {
				e.printStackTrace(); logger.error(e);
				registraError(request, "errors.invalid", e.getMessage());
			} catch (Exception e) {
				e.printStackTrace(); logger.error(e);
				registraError(request, "aut.error.sql.persist");
			}
		}
		
		session.setAttribute(FULL_LIST, (autForm.getRegistros()!=null ? autForm.getRegistros() : new ArrayList<RegistroPorValidarVO>()));
		
	}
	
	private void estableNombreUsuario(HttpServletRequest request, long idUsuario){
		HttpSession session = request.getSession();
		
		try {
			UsuarioVO usuarioVo = SecutityDelegateImpl.getInstance().consultaUsuario(idUsuario);
			
			session.setAttribute("TITULO", "Publicador");
			session.setAttribute("datosAdmin", usuarioVo.getNombre()+" "+usuarioVo.getApellido1()+" "+usuarioVo.getApellido2());

		} catch (ServiceLocatorException e) {
			e.printStackTrace(); logger.error(e);
			registraError(request, "aut.error.locator");
		 } catch (Exception e) {
			e.printStackTrace(); logger.error(e);
			registraError(request, "aut.error.sql.persist");
		}	
	}
	
	public ActionForward detalleRegistro(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		AutorizationForm autForm = (AutorizationForm)form;

		if (autForm.getIdRegValidar()<=0){ 
			registraError(request, "aut.error.requerido", "Identificador del registro a validar");
			return mapping.findForward(FORWARD_TEMPLATE_ESP_ADMIN);
		}

		/*for (Object c: autForm.getRegistros()){	
			RegistroPorValidarVO vo = (RegistroPorValidarVO)c;
			logger.info("vo="+vo.getIdRegistro());
		}*/

		RegistroPorValidarVO registroSeleccionado = buscaRegistroSeleccionado(autForm.getIdRegValidar(), autForm.getRegistros());			

		if (registroSeleccionado==null){
			registraError(request, "aut.error.requerido", "El registro a validar");
			return mapping.findForward(FORWARD_TEMPLATE_ESP_ADMIN);
		}

		String forwardName = null;

		try {
			UsuarioWebVO usuario = getUsuario(session);
			boolean esAdministrador= false;
			if(usuario.getIdPerfil()==PERFIL.ADMINISTRADOR.getIdOpcion())esAdministrador=true;

			request.getSession().setAttribute("ES_ADMINISTRADOR", esAdministrador);
			getDelegate().estableceRegistroEnRevision(registroSeleccionado.getIdRegValidar(), usuario.getIdUsuario());

			if (registroSeleccionado.getIdTipoRegistro() == TIPO_REGISTRO.EMPRESA.getIdTipoRegistro()){

				long idEmpresa = registroSeleccionado.getIdRegistro();

				try {
					List<EmpresaFraudulentaVO> empresasFraudulentas = getDelegate().consultaEmpresasFraudulentasSimilares(idEmpresa);
					// Empresas fraudulenta similares
					autForm.setEmpresasFraudulentas(empresasFraudulentas);

				} catch (SQLException e) {
					e.printStackTrace(); logger.error(e);
					registraError(request, "aut.error.sql.persist");
				}

				RegisterBusDelegate service = RegisterBusDelegateImpl.getInstance();
				EmpresaVO empresaVo = null;

				try {
					if (registroSeleccionado.getIdSubTipoRegistro() == SUBTIPO_REGISTRO.EMPRESA_POR_AUTORIZAR.getIdSubTipoRegistro()){
						empresaVo = service.consultaEmpresaPorAutorizar(idEmpresa);
					}else if (registroSeleccionado.getIdSubTipoRegistro() == SUBTIPO_REGISTRO.EMPRESA.getIdSubTipoRegistro()) {
						empresaVo = service.findEmpresaById(idEmpresa);
					}
				} catch (SQLException e) {
					e.printStackTrace(); logger.error(e);
					registraError(request, "aut.error.sql.persist");
				}

				if (empresaVo!=null){

					autForm.setEmpresa(empresaVo);

					if (empresaVo.getTelefonos()!=null && !empresaVo.getTelefonos().isEmpty()){
						for (TelefonoVO telefono : empresaVo.getTelefonos()){
							if (telefono.getPrincipal() == MULTIREGISTRO.PRINCIPAL.getIdOpcion()){
								autForm.setTelefonoVO(telefono);
								break;
							}
						}
					}

					// Se verifican las coincidencias con las empresas fraudulentas
					estableceCamposCoincidencia(empresaVo, autForm.getEmpresasFraudulentas());
				}

				forwardName = FORWARD_DETALLE_EMPRESA;

			}else if (registroSeleccionado.getIdTipoRegistro() == TIPO_REGISTRO.OFERTA.getIdTipoRegistro()){

				long idOfertaEmpleo = registroSeleccionado.getIdRegistro();

				OfertaEmpleoJB ofertaEmpleoJB = OfferBusDelegateImpl.getInstance().buscarOfertaEmpleo(idOfertaEmpleo);
				OfertaEmpleoVO ofertaEmpleoVO = ofertaEmpleoJB.getOferta();

				OfertaDetalleVO ofertaDetalle = createOfertaDetalleVO(ofertaEmpleoJB);
				ofertaDetalle.setIdEmpresa(ofertaEmpleoVO.getIdEmpresa());

				if(Constantes.EDAD_REQUISITO.SI.getIdOpcion() == (long)ofertaEmpleoVO.getEdadRequisito()){
					ofertaDetalle.setEdadRequisito(Constantes.EDAD_REQUISITO.SI.getIdOpcion());
					ofertaDetalle.setEdadMinima(String.valueOf(ofertaEmpleoVO.getEdadMinima()));
					ofertaDetalle.setEdadMaxima(String.valueOf(ofertaEmpleoVO.getEdadMaxima()));
				} else {
					ofertaDetalle.setEdadRequisito(Constantes.EDAD_REQUISITO.NO.getIdOpcion());
				}

				if(Constantes.DISPONIBILIDAD_RADICAR.NO.getIdOpcion() == (long)ofertaEmpleoVO.getDisponibilidadRadicar()){
					ofertaDetalle.setDisponibilidadRadicar(Constantes.DISPONIBILIDAD_RADICAR.NO.getOpcion());
				} else {
					ofertaDetalle.setDisponibilidadRadicar(Constantes.DISPONIBILIDAD_RADICAR.SI.getOpcion());
				}

				if(Constantes.DISPONIBILIDAD_VIAJAR.NO.getIdOpcion() == (long)ofertaEmpleoVO.getDisponibilidadViajar()){
					ofertaDetalle.setDisponibilidadViajar(Constantes.DISPONIBILIDAD_VIAJAR.NO.getOpcion());
				} else {
					ofertaDetalle.setDisponibilidadViajar(Constantes.DISPONIBILIDAD_VIAJAR.SI.getOpcion());
				}			
				
				ofertaDetalle.setDescripcionesDiscapacidades(ofertaEmpleoVO.getDescripcionDiscapacidades(ofertaEmpleoVO.getDiscapacidades()));

				autForm.setOfertaDetalle(ofertaDetalle);

				request.setAttribute("estatusOferta", ofertaEmpleoJB.getEstatus());

				if ("true".equals(request.getParameter("desdeBusquedaOfertas"))){
					request.setAttribute("desdeBusquedaOfertas", "true");
				}
				if ("true".equals(request.getParameter("desdeAdminPendientesPublicar"))){
					request.setAttribute("desdeAdminPendientesPublicar", "true");
				}else{

					request.setAttribute("desdeAdminPendientesPublicar", "false");

				}

				List<EmpresaFraudulentaVO> empresasFraudulentas = getDelegate().consultaEmpresasFraudulentasOferta(ofertaDetalle.getIdEmpresa());
				// Empresas fraudulenta similares
				autForm.setEmpresasFraudulentas(empresasFraudulentas);
				
				request.getSession().setAttribute("activa", ESTATUS.ACTIVO.getOpcion());
				request.getSession().setAttribute("inactiva", ESTATUS.INACTIVO.getOpcion());
				request.getSession().setAttribute("modificada", ESTATUS.MODIFICADA.getOpcion());
				request.getSession().setAttribute("registrada", ESTATUS.REGISTRADA.getOpcion());
				request.getSession().setAttribute("prevalidada", ESTATUS.PREVALIDADA.getOpcion());
				request.getSession().setAttribute("pendientePublicar", ESTATUS.PENDIENTE_PUBLICAR.getOpcion());
				request.getSession().setAttribute("activaAbriendoEspacios", ESTATUS.ABRIENDO_ESPACIOS_ACTIVO.getOpcion());

				forwardName = FORWARD_DETALLE_OFERTA;
			}else if (registroSeleccionado.getIdTipoRegistro() == TIPO_REGISTRO.TESTIMONIO.getIdTipoRegistro()){

				long idTestimonio = registroSeleccionado.getIdRegistro();
				TestimonioVO testimonioVO = getDelegate().consultaTestimonio(idTestimonio);
				autForm.setTestimonio(testimonioVO);

				forwardName = FORWARD_DETALLE_TESTIMONIO;
			}else if (registroSeleccionado.getIdTipoRegistro() == TIPO_REGISTRO.VIDEO_CURRICULO.getIdTipoRegistro()){

				long idCandidato = registroSeleccionado.getIdRegistro();
				PerfilLaboralVo perfilLaboral = CandidatoBusDelegateImpl.getInstance().consultaPerfilLaboral(idCandidato);
				autForm.setPerfilLaboral(perfilLaboral);

				forwardName = FORWARD_DETALLE_VIDEOC;

			}

		} catch (PersistenceException e) {
			e.printStackTrace(); logger.error(e);
			//registraError(request, "aut.error.sql.persist");
			//request.getSession().setAttribute(BODY_JSP, mapping.getInput());
			//forwardName = FORWARD_TEMPLATE_ESP_ADMIN;
		} catch (ServiceLocatorException e) {
			e.printStackTrace(); logger.error(e);
			//registraError(request, "aut.error.locator");
			//request.getSession().setAttribute(BODY_JSP, mapping.getInput());
			//forwardName = FORWARD_TEMPLATE_ESP_ADMIN;
		} catch (BusinessException e) {
			e.printStackTrace(); logger.error(e);
			//registraError(request, "aut.autorizacion.msg.error");
			//request.getSession().setAttribute(BODY_JSP, mapping.getInput());
			//forwardName = FORWARD_TEMPLATE_ESP_ADMIN;
		} catch(Exception e){			
			e.printStackTrace(); logger.error(e);
			//registraError(request, "aut.autorizacion.msg.error");
			//request.getSession().setAttribute(BODY_JSP, mapping.getInput());
			//forwardName = FORWARD_TEMPLATE_ESP_ADMIN;
		}

		request.getSession().setAttribute(BODY_JSP, mapping.findForward(forwardName).getPath()); //srojas		
		return mapping.findForward(FORWARD_TEMPLATE_ESP_ADMIN); //srojas
	}


	public ActionForward registraEmpresaFraudulenta(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		AutorizationForm autForm = (AutorizationForm)form;

		if (autForm.getIdEmpresaFraudulenta()<=0){
			registraError(request, "aut.error.requerido", "El identificador del registro por rechazar");
			request.getSession().setAttribute(BODY_JSP, mapping.getInput());
			return mapping.findForward(FORWARD_TEMPLATE_ESP_ADMIN);
		}

		UsuarioWebVO usuario = getUsuario(session);

		try {
			getDelegate().enviarEmpFraudulenta(autForm.getIdEmpresaFraudulenta(), usuario.getIdUsuario());
			List<RegistroPorValidarVO> registros = getDelegate().actualizaRegistrosPorValidar(usuario.getIdUsuario());

			autForm.reset();
			autForm.setRegistros(registros);

			session.setAttribute(FULL_LIST, registros);

			registraMensaje(request, "aut.autorizacion.msg.exito");
		}catch (PersistenceException e) {
			e.printStackTrace(); logger.error(e);
			registraError(request, "aut.error.sql.persist");
		} catch (ServiceLocatorException e) {
			e.printStackTrace(); logger.error(e);
			registraError(request, "aut.error.locator");
		} catch(BusinessException e){
			e.printStackTrace(); logger.error(e);
			registraError(request, "aut.autorizacion.msg.error");
		} catch(Exception e){
			e.printStackTrace(); logger.error(e);
			registraError(request, "aut.autorizacion.msg.error");
		}
		request.getSession().setAttribute(BODY_JSP, mapping.getInput());
		return mapping.findForward(FORWARD_TEMPLATE_ESP_ADMIN);
	}

	public ActionForward detalleEmpresa(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		//HttpSession session = request.getSession();
		AutorizationForm autForm = (AutorizationForm)form;

		String forwardName = null;

		try {
			long idEmpresa = Utils.parseLong(request.getParameter("idEmpresa"));

			RegisterBusDelegate service = RegisterBusDelegateImpl.getInstance();
			EmpresaVO empresaVo = service.findEmpresaById(idEmpresa);

			if (empresaVo!=null){

				autForm.setEmpresa(empresaVo);

				if (empresaVo.getTelefonos()!=null && !empresaVo.getTelefonos().isEmpty()){
					for (TelefonoVO telefono : empresaVo.getTelefonos()){
						if (telefono.getPrincipal() == MULTIREGISTRO.PRINCIPAL.getIdOpcion()){
							autForm.setTelefonoVO(telefono);
							break;
						}
					}
				}
			}

			forwardName = FORWARD_DETALLE_EMPRESA;
			request.setAttribute("OFERTA_DETALLE_EMP", "SOLO_DETALLE_EMP");

		} catch (PersistenceException e) {
			e.printStackTrace(); logger.error(e);
			registraError(request, "aut.error.sql.persist");
			request.getSession().setAttribute(BODY_JSP, mapping.getInput());
			forwardName = FORWARD_TEMPLATE_ESP_ADMIN;
		} catch (ServiceLocatorException e) {
			e.printStackTrace(); logger.error(e);
			registraError(request, "aut.error.locator");
			request.getSession().setAttribute(BODY_JSP, mapping.getInput());
			forwardName = FORWARD_TEMPLATE_ESP_ADMIN;
		} catch (BusinessException e) {
			e.printStackTrace(); logger.error(e);
			registraError(request, "aut.autorizacion.msg.error");
			request.getSession().setAttribute(BODY_JSP, mapping.getInput());
			forwardName = FORWARD_TEMPLATE_ESP_ADMIN;
		} catch(Exception e){
			e.printStackTrace(); logger.error(e);
			registraError(request, "aut.autorizacion.msg.error");
			request.getSession().setAttribute(BODY_JSP, mapping.getInput());
			forwardName = FORWARD_TEMPLATE_ESP_ADMIN;
		}

		return mapping.findForward(forwardName);
	}
	
	public ActionForward detalleEmpresaFraudulenta(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		AutorizationForm autForm = (AutorizationForm)form;
		EmpresaFraudulentaVO empresaFraudulentaVo;
		try {
			empresaFraudulentaVo = getDelegate().detalleEmpresaFraudulenta(autForm.getIdEmpresaFraudulenta());
			autForm.setEmpresaFraudulenta(empresaFraudulentaVo);
		} catch (ServiceLocatorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mapping.findForward(FORWARD_DETALLE_EMPRESA_FRAUDULENTA);
		
	}

	public ActionForward detalleEmpFraudulenta(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		AutorizationForm autForm = (AutorizationForm)form;

		long idEmpresa = Utils.parseLong(request.getParameter("idEmpresa"));

		EmpresaFraudulentaVO empresaFraudulenta = null;

		// Se ubica el registro de la empresa fraudulenta
		for (EmpresaFraudulentaVO empfrau : autForm.getEmpresasFraudulentas()){
			if (idEmpresa == empfrau.getIdEmpresa()){
				empresaFraudulenta = empfrau;
				break;
			}
		}

		if (empresaFraudulenta!=null){
			if (empresaFraudulenta.getDomicilios()!=null){
				for (DomicilioVO domicilio : empresaFraudulenta.getDomicilios()){
					empresaFraudulenta.setDomicilio(domicilio);
					break;
				}
			}

			if (empresaFraudulenta.getTelefonos()!=null){
				for (TelefonoVO telefono : empresaFraudulenta.getTelefonos()){
					empresaFraudulenta.setTelefono(telefono);
					break;
				}
			}			

			autForm.setEmpresaFraudulenta(empresaFraudulenta);
		}

		return mapping.findForward(FORWARD_DETALLE_EMPRESA_FRAUDULENTA);
	}

	public ActionForward autorizarRegistro(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		AutorizationForm autForm = (AutorizationForm)form;

		if (autForm.getIdRegValidar()<=0){
			registraError(request, "aut.error.requerido", "El identificador del registro por validar");
			request.getSession().setAttribute(BODY_JSP, mapping.getInput());
			return mapping.findForward(FORWARD_TEMPLATE_ESP_ADMIN);
		}

		UsuarioWebVO usuario = getUsuario(session);

		try {
			AutorizationBusDelegate services = getDelegate();

			services.autorizaRegistro(autForm.getIdRegValidar(), usuario.getIdUsuario());

			//List<RegistroPorValidarVO> registros = services.actualizaRegistrosPorValidar(usuario.getIdUsuario());
			List<RegistroPorValidarVO> registros = depuraRegistroPorValidar(autForm.getIdRegValidar(), autForm.getRegistros());
			
			autForm.reset();
			autForm.setRegistros(registros);

			session.setAttribute(FULL_LIST, registros);

			registraMensaje(request, "aut.autorizacion.msg.exito");

			// Verifica si ya no cuenta con registros, para volver a asignar
			estableRegistrosPorValidar(request, (usuario!=null? usuario.getIdUsuario() : 0), autForm);
			
		} catch (PersistenceException e) {
			e.printStackTrace(); logger.error(e);
			registraError(request, "aut.error.sql.persist");
		} catch (ServiceLocatorException e) {
			e.printStackTrace(); logger.error(e);
			registraError(request, "aut.error.locator");
		} catch(BusinessException e){
			e.printStackTrace(); logger.error(e);
			registraError(request, "aut.autorizacion.msg.error");
		} /*catch(SQLException e){
			e.printStackTrace(); logger.error(e);
			registraError(request, "aut.error.sql.persist");
		} */catch(TechnicalException e){
			e.printStackTrace(); logger.error(e);
			registraError(request, "aut.autorizacion.msg.error");
		} catch(MailException e){
			e.printStackTrace(); logger.error(e);
			registraError(request, "aut.error.mail");
		} catch(IndexerException e){
			e.printStackTrace(); logger.error(e);
			registraError(request, "aut.autorizacion.msg.indexer.error");
		} catch(Exception e){
			e.printStackTrace(); logger.error(e);
			registraError(request, "aut.autorizacion.msg.error");
		}

		request.getSession().setAttribute(BODY_JSP, mapping.getInput());
		return mapping.findForward(FORWARD_TEMPLATE_ESP_ADMIN);
	}

	public ActionForward rechazarRegistro(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		AutorizationForm autForm = (AutorizationForm)form;
		AutorizationBusDelegate services = getDelegate();
		if (autForm.getIdRegValidar()<=0){
			registraError(request, "aut.error.requerido", "Identificador del registro a validar");
			request.getSession().setAttribute(BODY_JSP, mapping.getInput());
			return mapping.findForward(FORWARD_TEMPLATE_ESP_ADMIN);
		}

		UsuarioWebVO usuario = getUsuario(session);
		boolean esAdministrador= false;
		
		if(usuario.getIdPerfil()==PERFIL.ADMINISTRADOR.getIdOpcion())esAdministrador=true;
		
		try {
			RegistroPorValidarVO registro = buscaRegistroSeleccionado(autForm.getIdRegValidar(), autForm.getRegistros());
			String mensaje = null;
			String nombreEmpresa = null;

			long[] reqDetalle = services.consultaRequiereDetalle();

			int indexFound = Arrays.binarySearch(reqDetalle, autForm.getIdMotivoRechazo());  			

			if (indexFound >= 0 && (null==autForm.getMotivoRechazo() || autForm.getMotivoRechazo().equalsIgnoreCase(""))) {
				registraError(request, "aut.error.detallerechazo");
				request.getSession().setAttribute(BODY_JSP, mapping.getInput());
				return mapping.findForward(FORWARD_TEMPLATE_ESP_ADMIN);				

			} else { 
				if (autForm.getIdMotivoRechazo()== VALIDACION_MOTIVO.RECHAZO_EMPRESA_FRAUDULENTA.getIdMotivo()){

					try {
						long idEmpresa = registro.getIdRegistro();

						RegisterBusDelegate service = RegisterBusDelegateImpl.getInstance();
						EmpresaVO empresa = null;

						if (registro.getIdSubTipoRegistro() == SUBTIPO_REGISTRO.EMPRESA_POR_AUTORIZAR.getIdSubTipoRegistro()){
							empresa = service.consultaEmpresaPorAutorizar(idEmpresa);

						}else if (registro.getIdSubTipoRegistro() == SUBTIPO_REGISTRO.EMPRESA.getIdSubTipoRegistro()) {
							empresa = service.findEmpresaById(idEmpresa);
						}

						if (empresa!=null){
							nombreEmpresa = empresa.getNombreEmpresa();

							if(TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona() == empresa.getIdTipoPersona()){
								mensaje = "aut.rechazo.emp.fisica.frau";
							} else if(TIPO_PERSONA.PERSONA_MORAL.getIdTipoPersona() == empresa.getIdTipoPersona()){
								mensaje = "aut.rechazo.emp.moral.frau";
							}
						}else{
							mensaje = "aut.autorizacion.msg.exito";
						}
					} catch (SQLException e) {
						e.printStackTrace(); logger.error(e);
						mensaje = "aut.error.sql.persist";
					}
				} else {
					mensaje = "aut.autorizacion.msg.exito";
				}

				getDelegate().rechazaRegistro(autForm.getIdRegValidar(), autForm.getIdMotivoRechazo(), autForm.getMotivoRechazo(), usuario.getIdUsuario());
				
				if(!esAdministrador){
					//List<RegistroPorValidarVO> registros = getDelegate().actualizaRegistrosPorValidar(usuario.getIdUsuario());
					List<RegistroPorValidarVO> registros = depuraRegistroPorValidar(autForm.getIdRegValidar(), autForm.getRegistros());

					autForm.reset();
					autForm.setRegistros(registros); // Actualiza los registros asignados
					session.setAttribute(FULL_LIST, registros);
					
					// Verifica si ya no cuenta con registros, para volver a asignar
					estableRegistrosPorValidar(request, (usuario!=null? usuario.getIdUsuario() : 0), autForm);
				}

				if (nombreEmpresa!=null){
					registraMensaje(request, mensaje, nombreEmpresa);	
				}else{
					registraMensaje(request, mensaje);
				}
			}

		} catch (PersistenceException e) {
			e.printStackTrace(); logger.error(e);
			registraError(request, "aut.error.sql.persist");
		} catch (ServiceLocatorException e) {
			e.printStackTrace(); logger.error(e);
			registraError(request, "aut.error.locator");
		} catch (BusinessException e) {
			e.printStackTrace(); logger.error(e);
			registraError(request, "aut.autorizacion.msg.error");
		} /*catch (SQLException e) {
			e.printStackTrace(); logger.error(e);
			registraError(request, "aut.error.sql.persist");
		} */catch(Exception e){
			e.printStackTrace(); logger.error(e);
			registraError(request, "aut.autorizacion.msg.error");
		}
		if(esAdministrador){
			boolean pendientePublicaAdmin = ((request.getParameter("desdeAdminPendientesPublicar")!=null)?Boolean.parseBoolean(request.getParameter("desdeAdminPendientesPublicar")):false);
			if(pendientePublicaAdmin)return mapping.findForward(FORWARD_RECHAZA_ADMIN_PENDIENTE_PUBLICAR);
			else return mapping.findForward(FORWARD_RECHAZA_ADMIN);

		}else{
			request.getSession().setAttribute(BODY_JSP, mapping.getInput());
			return mapping.findForward(FORWARD_TEMPLATE_ESP_ADMIN);
		}

	}

	public ActionForward cancelarValidacionRegistro(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		AutorizationForm autForm = (AutorizationForm)form;

		if (autForm.getIdRegValidar()<=0) {
			registraError(request, "aut.error.requerido", "Identificador del registro a validar");
			request.getSession().setAttribute(BODY_JSP, mapping.getInput());
			return mapping.findForward(FORWARD_TEMPLATE_ESP_ADMIN);
		}

		try {
			UsuarioWebVO usuario = getUsuario(session);

			getDelegate().cancelaValidacionRegistro(autForm.getIdRegValidar(), usuario.getIdUsuario());

		} catch (PersistenceException e) {
			e.printStackTrace(); logger.error(e);
			registraError(request, "aut.error.sql.persist");
		} catch (ServiceLocatorException e) {
			e.printStackTrace(); logger.error(e);
			registraError(request, "aut.error.locator");
		} catch(Exception e){
			e.printStackTrace(); logger.error(e);
			registraError(request, "aut.autorizacion.msg.error");
		}

		request.getSession().setAttribute(BODY_JSP, mapping.getInput());
		return mapping.findForward(FORWARD_TEMPLATE_ESP_ADMIN);
	}

	public ActionForward actualizaRegistrosPorValidar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		AutorizationForm autForm = (AutorizationForm)form;

		UsuarioWebVO usuario = getUsuario(session);

		try {
			AutorizationBusDelegate services = getDelegate();

			List<RegistroPorValidarVO> registros = services.actualizaRegistrosPorValidar(usuario.getIdUsuario());

			autForm.reset();
			autForm.setRegistros(registros);

			session.setAttribute(FULL_LIST, registros);

			registraMensaje(request, "aut.autorizacion.msg.exito");

		} catch (PersistenceException e) {
			e.printStackTrace(); logger.error(e);
			registraError(request, "aut.error.sql.persist");
		} catch (ServiceLocatorException e) {
			e.printStackTrace(); logger.error(e);
			registraError(request, "aut.error.locator");
		} catch(SQLException e){
			e.printStackTrace(); logger.error(e);
			registraError(request, "aut.error.sql.persist");
		} catch (BusinessException e) {
			e.printStackTrace(); logger.error(e);
			registraError(request, "errors.invalid", e.getMessage());
		} catch(Exception e){
			e.printStackTrace(); logger.error(e);
			registraError(request, "aut.autorizacion.msg.error");
		}

		request.getSession().setAttribute(BODY_JSP, mapping.getInput());
		return mapping.findForward(FORWARD_TEMPLATE_ESP_ADMIN);
	}

	public ActionForward editarEmpresa(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		AutorizationForm autForm = (AutorizationForm)form;

		long idRegValidar = Utils.parseLong(request.getParameter("idRegValidar"));
		long idEmpresa = Utils.parseLong(request.getParameter("idEmpresa"));

		if (idRegValidar<=0){
			registraError(request, "aut.error.requerido", "Identificador del registro a validar");
			request.getSession().setAttribute(BODY_JSP, mapping.getInput());
			return mapping.findForward(FORWARD_TEMPLATE_ESP_ADMIN);
		}

		if (idEmpresa<=0){
			registraError(request, "aut.error.requerido", "Identificador de la Empresa");
			request.getSession().setAttribute(BODY_JSP, mapping.getInput());
			return mapping.findForward(FORWARD_TEMPLATE_ESP_ADMIN);
		}

		HttpSession session = request.getSession();
		UsuarioWebVO usuario = getUsuario(session);

		/** Se establece el identificador de la empresa que sera modificada por el administrador (publicador) **/
		usuario.setIdPropietario(idEmpresa);

		RegistroPorValidarVO registroSeleccionado = buscaRegistroSeleccionado(idRegValidar, autForm.getRegistros());

		ActionForward forward = null;
		if (registroSeleccionado.getIdSubTipoRegistro() == SUBTIPO_REGISTRO.EMPRESA_POR_AUTORIZAR.getIdSubTipoRegistro()){
			forward = mapping.findForward(FORWARD_EDICION_EMPRESA_POR_AUT);
		}else if (registroSeleccionado.getIdSubTipoRegistro() == SUBTIPO_REGISTRO.EMPRESA.getIdSubTipoRegistro()) {
			forward = mapping.findForward(FORWARD_EDICION_EMPRESA);
		}

		session.setAttribute(PARAM_ID_REGISTRO_POR_VALIDAR, idRegValidar);

		return forward;
	}

	public ActionForward editarOfertaEmpleo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		long idEmpresa = Utils.parseLong(request.getParameter("idEmpresa"));
		long idOfertaEmpleo = Utils.parseLong(request.getParameter("idOfertaEmpleo"));
		long idRegValidar = Utils.parseLong(request.getParameter("idRegValidar"));

		//AutorizationForm autForm = (AutorizationForm)form;

		if (idOfertaEmpleo<=0){
			registraError(request, "aut.error.requerido", "Identificador de la Oferta de Empleo");
			request.getSession().setAttribute(BODY_JSP, mapping.getInput());
			return mapping.findForward(FORWARD_TEMPLATE_ESP_ADMIN);
		}

		if (idRegValidar<=0){
			registraError(request, "aut.error.requerido", "Identificador del registro a validar");
			request.getSession().setAttribute(BODY_JSP, mapping.getInput());
			return mapping.findForward(FORWARD_TEMPLATE_ESP_ADMIN);
		}		

		if (idEmpresa<=0){
			registraError(request, "aut.error.requerido", "Identificador de la Empresa");
			request.getSession().setAttribute(BODY_JSP, mapping.getInput());
			return mapping.findForward(FORWARD_TEMPLATE_ESP_ADMIN);
		}

		//RegistroPorValidarVO registroSeleccionado = buscaRegistroSeleccionado(idRegValidar, autForm.getRegistros());

		HttpSession session = request.getSession();
		UsuarioWebVO usuario = getUsuario(session);
		usuario.setIdRegValidar(idRegValidar);

		/** Se establece el identificador de la Oferta de Empleo que será modificada por el administrador (publicador) **/
		usuario.setIdPropietario(idEmpresa);

		session.setAttribute(PARAM_ID_REGISTRO_POR_VALIDAR, idRegValidar);
		
		//RBM1 TK997 y TK998  24 abr 2018
		//return new ActionForward(mapping.findForward(FORWARD_EDICION_OFERTA).getPath()+"&id="+ idOfertaEmpleo, false);
		
		return new ActionForward(mapping.findForward(FORWARD_EDICION_OFERTA_RU).getPath()+"&id="+ idOfertaEmpleo+"&"+PARAM_ID_REGISTRO_POR_VALIDAR+"="+idRegValidar, false);
	}

	public ActionForward motivoRechazoDetalle(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		return mapping.findForward(FORWARD_DETALLE_MOTIVO);
	}	

	private AutorizationBusDelegate getDelegate(){
		return AutorizationBusDelegateImpl.getInstance();
	}

	private RegistroPorValidarVO buscaRegistroSeleccionado(long idRegValidar, List<RegistroPorValidarVO> registros){
		RegistroPorValidarVO registroSeleccionado = null;

		if (registros==null || registros.isEmpty()) return registroSeleccionado;

		for (RegistroPorValidarVO registro : registros){
			if (idRegValidar == registro.getIdRegValidar()){
				registroSeleccionado = registro; break;
			}
		}

		return registroSeleccionado;
	}

	public ActionForward motivosCatalogo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		AutorizationBusDelegate services = getDelegate();
		List<CatalogoOpcionVO> motivos = null;

		try {

			long idRegValidar = Utils.parseLong(request.getParameter("idRegValidar"));			
			AutorizationForm autForm = (AutorizationForm)form;

			if (idRegValidar>0){
				RegistroPorValidarVO registroSeleccionado = buscaRegistroSeleccionado(idRegValidar, autForm.getRegistros());
				long idTipoRegistro = registroSeleccionado.getIdTipoRegistro();
				int intTipoRegistro = (int) idTipoRegistro;

				motivos = services.consultaMotivosRechazo(intTipoRegistro);
			}				

		} catch (ServiceLocatorException e) {
			e.printStackTrace(); logger.error(e);
			motivos = new ArrayList<CatalogoOpcionVO>();
		}

		try {
			CatalogoVO cat = getCatalogo(motivos);
			String json = toJson(cat);
			redirectJsonResponse(response, json);
		} catch (IOException e) {
			e.printStackTrace(); logger.error(e);
		}

		return null;
	}

	private OfertaDetalleVO createOfertaDetalleVO(OfertaEmpleoJB ofertaEmpleoJB){
		OfertaDetalleVO vo = new OfertaDetalleVO();

		vo.setIdOfertaEmpleo(Utils.parseLong(ofertaEmpleoJB.getIdOfertaEmpleo()));
		vo.setTituloOferta(ofertaEmpleoJB.getTituloOferta());
		vo.setEmpresaNombre(ofertaEmpleoJB.getEmpresaNombre());
		vo.setTituloOferta(ofertaEmpleoJB.getTituloOferta());
		vo.setTipoContrato(ofertaEmpleoJB.getTipoContrato());
		vo.setHorarioLaboral(ofertaEmpleoJB.getHorarioLaboral());
		vo.setGradoEstudios(ofertaEmpleoJB.getGradoEstudios());
		vo.setEspecialidades(ofertaEmpleoJB.getEspecialidades());
		vo.setSituacionAcademica(ofertaEmpleoJB.getSituacionAcademica());
		vo.setHabilidadGeneral(ofertaEmpleoJB.getHabilidadGeneral());
		vo.setExperienciaAnios(ofertaEmpleoJB.getExperienciaAnios());
		vo.setRequisitos(ofertaEmpleoJB.getRequisitos());
		vo.setIdiomasCert(ofertaEmpleoJB.getIdiomasCert());
		vo.setEdadRequisito(ofertaEmpleoJB.getEdadRequisito());
		vo.setEdadMinima(ofertaEmpleoJB.getEdadMinima());
		vo.setEdadMaxima(ofertaEmpleoJB.getEdadMaxima());		
		vo.setDisponibilidadViajar(ofertaEmpleoJB.getDisponibilidadViajar());
		vo.setDisponibilidadRadicar(ofertaEmpleoJB.getDisponibilidadRadicar());
		vo.setObservaciones(ofertaEmpleoJB.getObservaciones());
		vo.setAreaLaboral(ofertaEmpleoJB.getAreaLaboral());
		//RBM1 TK1000  TK1001
		vo.setOcupacion(ofertaEmpleoJB.getOcupacion());  // ocupacion es subarealaboral
		//vo.setSector(ofertaEmpleoJB.getSector());
		vo.setSector(ofertaEmpleoJB.getJerarquia()); //se reemplaza por jerarquia que es el sector, se deja en campo sector para que se visualize correctamente en 
		
		//RBM1 TK1000  TK1001
		vo.setFunciones(ofertaEmpleoJB.getFunciones());
		vo.setHorario(ofertaEmpleoJB.getHorario());
		vo.setSalario(ofertaEmpleoJB.getSalario());
		vo.setEmpresaOfrece(ofertaEmpleoJB.getEmpresaOfrece());
		vo.setNumeroPlazas(ofertaEmpleoJB.getNumeroPlazas());
		vo.setUbicacion(ofertaEmpleoJB.getUbicacion());
		vo.setPrestaciones(ofertaEmpleoJB.getPrestaciones());
		vo.setHabilidades(ofertaEmpleoJB.getHabilidades());
		return vo;
	}

	/**
	 * Establece los campos de coincidencia en las empresas fraudulentas detectadas
	 * @param empresa
	 * @param empresasFraudulentas
	 */
	private void estableceCamposCoincidencia(EmpresaVO empresa, List<EmpresaFraudulentaVO> empresasFraudulentas){

		for (EmpresaFraudulentaVO empfrau : empresasFraudulentas){
			String coincidencias = verificaCamposCoincidencia(empresa, empfrau);
			empfrau.setCoincidencias(coincidencias);
		}
	}

	/**
	 * Verifica los campos que coinciden con los de una empresa fraudulenta
	 * @param empresa
	 * @param empfrau
	 * @return
	 */
	private String verificaCamposCoincidencia(EmpresaVO empresa, EmpresaFraudulentaVO empfrau){
		StringBuilder campos = new StringBuilder();

		if (equals(empresa.getIdPortalEmpleo(), empfrau.getIdPortalEmpleo()))
			campos.append("ID del Portal del Empleo : "+ empresa.getIdPortalEmpleo());

		if (equals(empresa.getCorreoElectronico(), empfrau.getCorreoElectronico()))
			campos.append("Correo electronico : "+ empresa.getCorreoElectronico());

		if(TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona() == empresa.getIdTipoPersona()){

			String nombreEmp = concatenar(empresa.getNombre(), empresa.getApellido1(), empresa.getApellido2());
			String nombreFra = concatenar(empfrau.getNombre(), empfrau.getApellido1(), empfrau.getApellido2());

			if (equals(nombreEmp, nombreFra))
				campos.append("Nombre "+ nombreEmp);

		} else if(TIPO_PERSONA.PERSONA_MORAL.getIdTipoPersona() == empresa.getIdTipoPersona()){
			if (equals(empresa.getRazonSocial(), empfrau.getRazonSocial()))
				campos.append("Razon social : "+ empresa.getRazonSocial());
		}

		if (empresa.getTelefonos()!=null && empfrau.getTelefonos()!=null && 
				!empresa.getTelefonos().isEmpty() && !empfrau.getTelefonos().isEmpty() ){

			for (TelefonoVO telefono : empresa.getTelefonos()){
				String telemp = concatenar(telefono.getAcceso(), telefono.getClave(), telefono.getTelefono(), telefono.getExtension());

				for (TelefonoVO telfrau : empfrau.getTelefonos()){
					String telfra = concatenar(telfrau.getAcceso(), telfrau.getClave(), telfrau.getTelefono(), telfrau.getExtension());

					if (equals(telemp, telfra)){
						campos.append("Telefono :");
						campos.append(" "+ telfrau.getAcceso());
						campos.append(" "+ telfrau.getClave());
						campos.append(" "+ telfrau.getTelefono());
						campos.append("Ext. "+ telfrau.getExtension());
					}
				}// for
			}// for
		}

		if (empresa.getDomicilio()!=null && empfrau.getDomicilios()!=null && !empfrau.getDomicilios().isEmpty()){

			DomicilioVO domicilio = empresa.getDomicilio();

			String domempr = concatenar(""+ domicilio.getIdEntidad(),
					""+ domicilio.getIdMunicipio(),
					""+ domicilio.getIdColonia(),
					domicilio.getCalle(),
					domicilio.getNumeroInterior(),
					domicilio.getNumeroExterior(),
					domicilio.getEntreCalle(),
					domicilio.getyCalle(),
					domicilio.getCodigoPostal());

			for (DomicilioVO domiciliofra : empfrau.getDomicilios()){

				String domfrau = concatenar(""+ domiciliofra.getIdEntidad(),
						""+ domiciliofra.getIdMunicipio(),
						""+ domiciliofra.getIdColonia(),
						domiciliofra.getCalle(),
						domiciliofra.getNumeroInterior(),
						domiciliofra.getNumeroExterior(),
						domiciliofra.getEntreCalle(),
						domiciliofra.getyCalle(),
						domiciliofra.getCodigoPostal());

				if (equals(domempr, domfrau)){
					campos.append("Domicilio :");
					campos.append(" "+ domiciliofra.getCalle());
					campos.append(" "+ domiciliofra.getNumeroInterior());
					campos.append(" "+ domiciliofra.getNumeroExterior());
					campos.append(" "+ domiciliofra.getEntreCalle());
					campos.append(" "+ domiciliofra.getyCalle());
					campos.append(" "+ domiciliofra.getCodigoPostal());
				}
			}
		}

		return campos.toString();
	}

	private boolean equals(String cad01, String cad02){
		boolean equals = false;

		if (cad01==null || cad01.isEmpty()) return false;
		if (cad02==null || cad02.isEmpty()) return false;

		cad01 = cad01.trim().toUpperCase();
		cad02 = cad02.trim().toUpperCase();

		equals = cad01.equals(cad02);

		return equals;
	}

	private String concatenar(String... cadenas){
		StringBuilder cadenafull = new StringBuilder();

		for (String cadena : cadenas){
			if (cadena!=null) cadena = cadena.trim();
			else cadena = "";

			cadenafull.append(cadena);
		}

		return cadenafull.toString();
	}

	private List<RegistroPorValidarVO> depuraRegistroPorValidar(long idRegValidar, List<RegistroPorValidarVO> registros){
		if (registros==null) return registros;
		if (registros.isEmpty()) return registros;
		
		int index = 0;
		
		for (RegistroPorValidarVO registro : registros){
			if (registro.getIdRegValidar() == idRegValidar){
				break;
			}
			
			index++;
		}
		
		registros.remove(index);
		
		return registros;
	}
	
}