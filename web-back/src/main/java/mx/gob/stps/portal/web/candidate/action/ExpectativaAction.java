/**
 * 
 */
package mx.gob.stps.portal.web.candidate.action;

import mx.gob.stps.portal.core.candidate.vo.PerfilVO;
import mx.gob.stps.portal.core.domicilio.vo.DomicilioVO;
import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.exception.TechnicalException;
import mx.gob.stps.portal.core.infra.mail.exception.MailException;
import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.core.infra.utils.Constantes.*;
import mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO;
import mx.gob.stps.portal.core.search.exception.IndexerException;
import mx.gob.stps.portal.persistencia.vo.TelefonoVO;
import mx.gob.stps.portal.utils.Catalogos;
import mx.gob.stps.portal.utils.Catalogos.TRABAJA_ACTUALMENTE;
import mx.gob.stps.portal.utils.ConstantesGenerales;
import mx.gob.stps.portal.web.address.delegate.DomicilioBusDelegate;
import mx.gob.stps.portal.web.address.delegate.DomicilioBusDelegateImpl;
import mx.gob.stps.portal.web.candidate.delegate.CandidatoBusDelegate;
import mx.gob.stps.portal.web.candidate.delegate.CandidatoBusDelegateImpl;
import mx.gob.stps.portal.web.candidate.form.PerfilForm;
import mx.gob.stps.portal.web.infra.action.GenericAction;
import mx.gob.stps.portal.web.infra.delegate.CatalogoOpcionDelegateImpl;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.infra.utils.PropertiesLoader;
import mx.gob.stps.portal.web.infra.vo.ResultVO;
import mx.gob.stps.portal.web.security.vo.UsuarioWebVO;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static mx.gob.stps.portal.core.infra.utils.Constantes.*;
import static mx.gob.stps.portal.web.infra.utils.Constantes.*;

//import mx.gob.stps.portal.core.infra.vo.TelefonoVO;

/**
 * @author Felipe Juárez Ramírez
 * @since 10/03/2011
 * 
 */
public class ExpectativaAction extends GenericAction {

	private static Logger logger = Logger.getLogger(ExpectativaAction.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.gob.stps.portal.web.infra.action.GenericAction#init(org.apache.struts
	 * .action.ActionMapping, org.apache.struts.action.ActionForm,
	 * javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		PerfilForm perfilForm = (PerfilForm) form;		
		UsuarioWebVO usuario = getUsuario(session);
		if (usuario!=null){
			perfilForm.setIdCandidato(usuario.getIdPropietario());
			perfilForm.setIdUsuario(usuario.getIdUsuario());
			perfilForm.setIdTipoUsuario(usuario.getIdTipoUsuario());
			perfilForm.setUsuario(usuario.getUsuario());
		}
		else
		{
			//bye bye
			String fromAbriendoEspacios = request.getParameter("fromAbriendoEspacios");
			if(null!=fromAbriendoEspacios && fromAbriendoEspacios.equalsIgnoreCase("true")){
				request.getSession().setAttribute("bFromAbriendoEspacios", true);
			}
			
			request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
			PropertiesLoader properties = PropertiesLoader.getInstance();
			request.getSession().setAttribute(TITULO_PAGINA, "Situaci&oacute;n laboral | Portal del Empleo");
			request.getSession().setAttribute(DESCRIPCION_PAGINA, "Registro de la situacion laboral actual del candidato.");
			request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
			request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
			request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));	
	        return mapping.findForward(FORWARD_TEMPLATE_HOME); 
			
		}
		int estatusCV = 0;
		try {
			
			//Obtiene los datos del perfil del candidato
			CandidatoBusDelegate services = CandidatoBusDelegateImpl.getInstance();
			estatusCV = services.getEstatusCV(perfilForm.getIdCandidato());
			PerfilVO perfil = services.initPerfil(perfilForm.getIdCandidato());
			if (perfil != null) {
				BeanUtils.copyProperties(perfilForm, perfil);
				//Verificar estats candidato de ppc
				//PPC
				perfilForm.setPPCStatusForm(perfilForm.getIdTrabaja(), perfil.getIdEstatusPPC(), true);
			
				
				//Obtiene el domicilio
				DomicilioBusDelegate serviceDom = DomicilioBusDelegateImpl.getInstance();
				DomicilioVO domicilioVO = serviceDom.buscarDomicilioIdPropietario(perfilForm.getIdCandidato(), TIPO_PROPIETARIO.CANDIDATO.getIdTipoPropietario());
				//logger.info("domicilio: " + domicilioVO.toString());
				if(domicilioVO != null){
					BeanUtils.copyProperties(perfilForm, domicilioVO);
				}	
				
				if (perfilForm.getIdTipoPropietario() == 0) {
					perfilForm.setIdTipoPropietario(TIPO_PROPIETARIO.CANDIDATO.getIdTipoPropietario());
				}
				
				// Obtiene el telefono principal
				List<TelefonoVO> telefonosVO = services.initTelefonos(perfil.getIdCandidato(),TIPO_PROPIETARIO.CANDIDATO.getIdTipoPropietario());
				if(telefonosVO != null && !telefonosVO.isEmpty()){
					for (TelefonoVO telefonoVO : telefonosVO) {
						if (telefonoVO.getPrincipal() == MULTIREGISTRO.PRINCIPAL.getIdOpcion()) {
							this.setTelefonoForm(perfilForm, telefonoVO);
						}
					}					
				}
				
				if (perfilForm.getIdTipoPropietarioTel() == 0) {
					perfilForm.setIdTipoPropietarioTel(TIPO_PROPIETARIO.CANDIDATO.getIdTipoPropietario());
				}

				// Obtiene otros medios de busqueda
				perfilForm.setOtrosMediosVO(services.initOtrosMedios(perfilForm.getIdCandidato()));
				
				// Sube a sesion los telefonos adicionales
				session.setAttribute(LST_TELEFONOS_ADICIONALES,telefonosVO);
				
				if (perfilForm.getTipoTelefonos() == null || perfilForm.getTipoTelefonos().isEmpty()) {
					long[] filtro = new long[] { 3, 4 };
					
					perfilForm.setTipoTelefonos(CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(CATALOGO_OPCION_TIPO_TELEFONO, filtro));
				}
	
				if (perfilForm.getOtrosMedios() == null || perfilForm.getOtrosMedios().isEmpty()) {

					List<CatalogoOpcionVO> medios = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(CATALOGO_OPCION_OTROS_MEDIOS);
					
					if (medios!=null){
						
						Comparator<CatalogoOpcionVO> comparator = new Comparator<CatalogoOpcionVO>(){
							@Override
							public int compare(CatalogoOpcionVO o1, CatalogoOpcionVO o2) {
								int test = 0;
								
								if (o1.getIdCatalogoOpcion() == o2.getIdCatalogoOpcion())
									test = 0;
								else if (o1.getIdCatalogoOpcion() > o2.getIdCatalogoOpcion())
									test = 1;
								else if (o1.getIdCatalogoOpcion() < o2.getIdCatalogoOpcion())
									test = -1;
								
								return test;
						}};
							
						Collections.sort(medios, comparator);
					}

					perfilForm.setOtrosMedios(medios);
				
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
		}
		session.setAttribute("estatusCV", estatusCV);
		session.setAttribute("thisTabId", Constantes.EVALUA_CV_FLUJO_PANTALLA.EXPECTATIVAS.getIdOpcion());
		session.setAttribute("thisTabName", Constantes.EVALUA_CV_FLUJO_PANTALLA.EXPECTATIVAS.getOpcion());
		session.setAttribute("thisTabPercent", Constantes.EVALUA_CV_FLUJO_PANTALLA.EXPECTATIVAS.getPorcentaje());
		session.setAttribute("trabajaSI", TRABAJA_ACTUALMENTE.SI.getIdOpcion());
		session.setAttribute("trabajaNO", TRABAJA_ACTUALMENTE.NO.getIdOpcion());
		session.setAttribute("inicioBusqueda", perfilForm.getInicioBusqueda());
		cargaCatalogos(session);
		session.setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
		PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Situaci&oacute;n laboral");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Perfil, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
		return mapping.findForward(FORWARD_TEMPLATE_MI_ESP_CAND);
	}

	private void cargaCatalogos(HttpSession session){
		try {
			
			/**CatalogoOpcionDelegate catalogos = CatalogoOpcionDelegateImpl.getInstance();
			List<CatalogoOpcionVO> sectores = catalogos.consultarCatalogo(CATALOGO_OPCION_SECTOR, false);	
			session.setAttribute("sectores", sectores);
						
			List<CatalogoOpcionVO> tiposempleo = catalogos.consultarCatalogo(CATALOGO_OPCION_TIPO_EMPLEO);
			session.setAttribute("tiposempleo", tiposempleo);
			
			List<CatalogoOpcionVO> tiposcontrato = catalogos.consultarCatalogo(CATALOGO_OPCION_TIPO_CONTRATO);
			session.setAttribute("tiposcontrato", tiposcontrato);
			**/
			List<CatalogoOpcionVO> otrosMedios = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(CATALOGO_OPCION_OTROS_MEDIOS);
			session.setAttribute("otrosMedios", otrosMedios);
			List<CatalogoOpcionVO> medios = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(CATALOGO_OPCION_MEDIO_ENTERADO);
			session.setAttribute("medios", medios);
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e);
		}
	}
	
	public ActionForward registrar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException {
		//ExpectativaForm expForm = (ExpectativaForm) form;
		UsuarioWebVO usuario = getUsuario(request.getSession());
		PerfilForm perfilForm = (PerfilForm)form;
		perfilForm.setIdCandidato(usuario.getIdPropietario());
		perfilForm.setIdUsuario(usuario.getIdUsuario());
		perfilForm.setIdTipoUsuario(usuario.getIdTipoUsuario());
		PerfilVO perfilVo = new PerfilVO();
		convertir(perfilVo, perfilForm);
		//Otros medios de busqueda
		perfilVo.setIdOtrosMedios(perfilForm.getIdOtrosMedios());
		ResultVO msg =  new ResultVO(getMensaje(request, "can.guardar.ext"), ResultVO.TYPE_SUCCESS);
		try {
			/**ExpectativaVO expVO = new ExpectativaVO();
			setExpectativa(expForm, expVO);
			expVO.setIdCandidato(usuario.getIdPropietario());
			expVO.setIdUsuario(usuario.getIdUsuario());**/
			CandidatoBusDelegate services = CandidatoBusDelegateImpl.getInstance();
			perfilVo = services.registrarPerfil(perfilVo);
			
			/** Se agrega el indicador para indexar al Candidato al FINALIZA su Session **/
			request.getSession().setAttribute(ID_CANDIDATO_INDEXABLE, perfilVo.getIdCandidato());
			//BolsasTrabajoBusDelegateImpl.getInstance().indexaCandidato(perfilVo.getIdCandidato());
			
			/**expVO = services.registrarExpectativa(expVO);
			expForm.setIdExpectativaLaboral(expVO.getExpLaboral().getIdExpectativaLaboral());
			expForm.setIdExpectativaLugar(expVO.getExpLugar().getIdExpectativaLugar());**/

		} catch (BusinessException e) {
			e.printStackTrace();
			msg = new ResultVO(getMensaje(request, "can.guardar.err"), ResultVO.TYPE_ERROR);
			logger.error("Excepción de negocio al persistir expectativas del candidato " + perfilVo.getIdCandidato(), e);
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
			msg = new ResultVO(getMensaje(request, "can.guardar.err"), ResultVO.TYPE_ERROR);
			logger.error("Excepción de localización de servicio al persistir expectativas del candidato " + perfilVo.getIdCandidato(), e);
		} catch (PersistenceException e) {
			e.printStackTrace();
			msg = new ResultVO(getMensaje(request, "can.guardar.err"), ResultVO.TYPE_ERROR);
			logger.error("Excepción al persistir expectativas del candidato " + perfilVo.getIdCandidato(), e);
		} catch (TechnicalException e) {
			e.printStackTrace();
			msg = new ResultVO(getMensaje(request, "can.guardar.err"), ResultVO.TYPE_ERROR);
			logger.error("Excepción tecnica al persistir expectativas del candidato " + perfilVo.getIdCandidato(), e);
		} catch (SQLException e) {
			e.printStackTrace();
			msg = new ResultVO(getMensaje(request, "can.guardar.err"), ResultVO.TYPE_ERROR);
			logger.error("Excepción de sql al persistir expectativas del candidato " + perfilVo.getIdCandidato(), e);
		} catch (MailException e) {
			e.printStackTrace();
			msg = new ResultVO(getMensaje(request, "can.guardar.err"), ResultVO.TYPE_ERROR);
			logger.error("Excepción al enviar correo en expectativas del candidato " + perfilVo.getIdCandidato(), e);
		} catch (IndexerException e) {
			e.printStackTrace();
			msg = new ResultVO(getMensaje(request, "aut.autorizacion.msg.indexer.error"), ResultVO.TYPE_ERROR);
			logger.error("Excepción al indexar candidato " + perfilVo.getIdCandidato(), e);
		} catch (Exception e) {
			e.printStackTrace();
			msg = new ResultVO(getMensaje(request, "can.guardar.err"), ResultVO.TYPE_ERROR);
			logger.error("Excepción al persistir expectativas del candidato " + perfilVo.getIdCandidato(), e);
		}
		perfilForm.setMsg(msg);
		this.setResponseJSON(response, perfilForm);
		return null;
	}

	private void setResponseJSON(HttpServletResponse response, PerfilForm perfilForm) throws IOException {
		String json = toJson(perfilForm);
		redirectJsonResponse(response, json);
	}
	
	private void convertir(PerfilVO destino, PerfilForm origen) {
		try {
			BeanUtils.copyProperties(destino, origen);
			destino.setContactoCorreo(origen.getContactoCorreo());
			destino.setContactoTelefono(origen.getContactoTelefono());
			destino.setIdRecibeOferta(origen.getIdRecibeOferta());
			TelefonoVO principal = new TelefonoVO();
			principal.setIdTelefono(origen.getIdTelefono());
			principal.setIdPropietario(origen.getIdCandidato());
			principal.setIdTipoPropietario((int)origen.getIdTipoPropietarioTel());
			principal.setIdTipoTelefono((int)origen.getIdTipoTelefono());
			principal.setAcceso(origen.getAcceso());
			principal.setClave(origen.getClave());
			principal.setTelefono(origen.getTelefono());
			principal.setExtension(origen.getExtension());
			principal.setPrincipal(MULTIREGISTRO.PRINCIPAL.getIdOpcion());
			destino.setPrincipal(principal);
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
		
	
	/**private void setExpectativa(ExpectativaForm origen, ExpectativaVO destino) {
		destino.setIdCandidato(origen.getIdCandidato());
		destino.setDisponibilidadViajar(origen.getDisponibilidadViajar());
		destino.setDisponibilidadRadicar(origen.getDisponibilidadRadicar());
		
		try {
			ExpectativaLaboralVO expLaboralVO = new ExpectativaLaboralVO();
			BeanUtils.copyProperties(expLaboralVO, origen);
			
			expLaboralVO.setPrincipal(MULTIREGISTRO.PRINCIPAL.getIdOpcion());
			
			destino.setExpLaboral(expLaboralVO);
			
			ExpectativaLugarVO expLugarVO = new ExpectativaLugarVO();
			
			BeanUtils.copyProperties(expLugarVO, origen);
			expLugarVO.setPrincipal(MULTIREGISTRO.PRINCIPAL.getIdOpcion());
			destino.setExpLugar(expLugarVO);
			
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			logger.error(e); // TODO Auto-generated catch block
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e); // TODO Auto-generated catch block
		}
	}

	private void setResponseJSON(HttpServletResponse response,ExpectativaForm expForm) throws IOException {
		ExpectativaVOForm expectativaVOForm = new ExpectativaVOForm();
		expectativaVOForm.setIdExpectativaLaboral(expForm.getIdExpectativaLaboral());
		expectativaVOForm.setIdExpectativaLugar(expForm.getIdExpectativaLugar());
		expectativaVOForm.setMsg(expForm.getMsg());
		
		String json = toJson(expectativaVOForm);
		redirectJsonResponse(response, json);
	}**/

	public ActionForward misofertas(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		return mapping.findForward("FORWARD_MIS_OFERTAS");
	}
}
