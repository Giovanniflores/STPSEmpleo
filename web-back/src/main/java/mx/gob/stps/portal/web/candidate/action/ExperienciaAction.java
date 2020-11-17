/**
 * 
 */
package mx.gob.stps.portal.web.candidate.action;

import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_EXPERIENCIA;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_JERARQUIA_PUESTO;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_OCUPACION;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_PERSONAS_CARGO;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_SUBSECTOR;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_TIPO_CONTRATO;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_TIPO_EMPLEO;
import static mx.gob.stps.portal.web.infra.utils.Constantes.BODY_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.ID_CANDIDATO_INDEXABLE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_TEMPLATE_MI_ESP_CAND;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TITULO_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.DESCRIPCION_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FACEBOOK_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TWITTER_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.URL_ESPECIFICA;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.gob.stps.portal.core.candidate.vo.ExpectativaLaboralVO;
import mx.gob.stps.portal.core.candidate.vo.ExpectativaLugarVO;
import mx.gob.stps.portal.core.candidate.vo.ExpectativaVO;
import mx.gob.stps.portal.core.candidate.vo.ExperienciaVO;
import mx.gob.stps.portal.core.candidate.vo.HistoriaLaboralVO;
import mx.gob.stps.portal.core.candidate.vo.PerfilVO;
import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.exception.TechnicalException;
import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.core.infra.utils.Constantes.DISPONIBILIDAD_RADICAR;
import mx.gob.stps.portal.core.infra.utils.Constantes.DISPONIBILIDAD_VIAJAR;
import mx.gob.stps.portal.core.infra.utils.Constantes.EXPERIENCIA_LABORAL;
import mx.gob.stps.portal.core.infra.utils.Constantes.MOSTRAR_EMPRESA;
import mx.gob.stps.portal.core.infra.utils.Constantes.MULTIREGISTRO;
import mx.gob.stps.portal.core.infra.utils.Constantes.TRABAJO_ACTUAL;
import mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO;
import mx.gob.stps.portal.core.search.exception.IndexerException;
import mx.gob.stps.portal.web.candidate.delegate.CandidatoBusDelegate;
import mx.gob.stps.portal.web.candidate.delegate.CandidatoBusDelegateImpl;
import mx.gob.stps.portal.web.candidate.form.ExperienciaForm;
import mx.gob.stps.portal.web.infra.action.GenericAction;
import mx.gob.stps.portal.web.infra.delegate.CatalogoOpcionDelegate;
import mx.gob.stps.portal.web.infra.delegate.CatalogoOpcionDelegateImpl;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.infra.utils.PropertiesLoader;
import mx.gob.stps.portal.web.infra.utils.Utils;
import mx.gob.stps.portal.web.infra.vo.ResultVO;
import mx.gob.stps.portal.web.security.vo.UsuarioWebVO;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Felipe Juarez Ramirez
 * @since 10/03/2011
 */
public class ExperienciaAction extends GenericAction {

	private static Logger logger = Logger.getLogger(ExperienciaAction.class);

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
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		ExperienciaForm experForm = (ExperienciaForm) form;
		UsuarioWebVO usuario = getUsuario(session);

		if (usuario != null)
			experForm.setIdCandidato(usuario.getIdPropietario());

		PerfilVO perfil = new PerfilVO();
		int estatusCV = 0;
		
		
		try {
			CandidatoBusDelegate services = CandidatoBusDelegateImpl.getInstance();
			estatusCV = services.getEstatusCV(experForm.getIdCandidato());
			
			
			perfil = services.initPerfilCandidato(experForm.getIdCandidato());
			PerfilVO perfilVo = services.initPerfil(experForm.getIdCandidato());
			experForm.setPPCStatusForm(perfilVo.getIdTrabaja(),perfilVo.getIdEstatusPPC(),false);
			if (perfil != null) {
				BeanUtils.copyProperties(experForm, perfil);
				List<HistoriaLaboralVO> histLaboralesVO = services.initHistLaboral(experForm.getIdCandidato(),
						MULTIREGISTRO.PRINCIPAL.getIdOpcion());
				if (histLaboralesVO != null && !histLaboralesVO.isEmpty()) {
					BeanUtils.copyProperties(experForm, histLaboralesVO.get(0));
					experForm.setIdHistorialLaboral(histLaboralesVO.get(0).getIdHistorialLaboral());
					if (experForm.getSinExperiencia() == EXPERIENCIA_LABORAL.SIN_EXPERIENCIA.getIdOpcion()) {
						experForm.setSalarioMensual("");
						experForm.setLaboresInicial("");
						experForm.setLaboresFinal("");
					}
					experForm.setDisponibilidadViajar(perfil.getDisponibilidadViajar());
					experForm.setDisponibilidadRadicar(perfil.getDisponibilidadRadicar());
				}
				// Change for PS_DSS requirement
				// List<ExpectativaLaboralVO> expecLaboralesVO =
				// services.initExpecLaboral(experForm.getIdCandidato(),
				// MULTIREGISTRO.PRINCIPAL.getIdOpcion());

				List<ExpectativaLaboralVO> expecLaboralesVO = services.buscarExpecLaboral(experForm.getIdCandidato());
				

				if (expecLaboralesVO != null && !expecLaboralesVO.isEmpty()) {

					experForm.setSalarioPretendido(expecLaboralesVO.get(0).getSalarioPretendido());
					// Cambio por requiremento ya no se va a ver este campo
					// experForm.setIdTipoEmpleoDeseado(expecLaboralesVO.get(0).getIdTipoEmpleoDeseado());
					experForm.setIdTipoContrato(expecLaboralesVO.get(0).getIdTipoContrato());
					experForm.setIdExpectativaLaboral(expecLaboralesVO.get(0).getIdExpectativaLaboral());
					experForm.setIdOcupacionDeseada(expecLaboralesVO.get(0).getIdOcupacionDeseada());
					experForm.setOcupacionDeseada(expecLaboralesVO.get(0).getOcupacionDeseada());
					experForm.setPuestoDeseado(expecLaboralesVO.get(0).getPuestoDeseado());
					experForm.setIdExperienciaTotal(expecLaboralesVO.get(0).getIdExperiencia());
					experForm.setIdExperienciaOcupacion(expecLaboralesVO.get(0).getIdExperiencia());
					
					// agregar segunda expectiva laboral
					// no estamos validando si hay mas que 3 porque en ferias
					// pueden tener 3 registros
					// en PE y SIISNE solo son 2 primero el principal y despues
					// el segundo dependiendo del ID_EXPECTATIVA_LABORALopc

					if (expecLaboralesVO.size() > 1) {
						experForm.setSalarioPretendido2(expecLaboralesVO.get(1).getSalarioPretendido());
						experForm.setIdTipoEmpleoDeseado2(expecLaboralesVO.get(1).getIdTipoEmpleoDeseado());
						experForm.setIdTipoContrato2(expecLaboralesVO.get(1).getIdTipoContrato());
						experForm.setIdExpectativaLaboral2(expecLaboralesVO.get(1).getIdExpectativaLaboral());
						experForm.setIdOcupacionDeseada2(expecLaboralesVO.get(1).getIdOcupacionDeseada());
						experForm.setOcupacionDeseada2(expecLaboralesVO.get(1).getOcupacionDeseada());
						experForm.setPuestoDeseado2(expecLaboralesVO.get(1).getPuestoDeseado());
						experForm.setIdExperienciaOcupacion2(expecLaboralesVO.get(1).getIdExperiencia());
					}
				}
			}
		} catch (ServiceLocatorException e) {
			logger.error(e); // TODO VERIFICAR MANEJO DE ERROR
		} catch (SQLException e) {
			logger.error(e); // TODO VERIFICAR MANEJO DE ERROR
		} catch (IllegalAccessException e) {
			logger.error(e); // TODO VERIFICAR MANEJO DE ERROR
		} catch (InvocationTargetException e) {
			logger.error(e); // TODO VERIFICAR MANEJO DE ERROR
		}
		// Sube constantes a sesion para presentar checkbox
		if (session.getAttribute("trabActualSI") == null)
			session.setAttribute("trabActualSI", TRABAJO_ACTUAL.SI.getIdOpcion());
		if (session.getAttribute("trabActualNO") == null)
			session.setAttribute("trabActualNO", TRABAJO_ACTUAL.NO.getIdOpcion());
		if (session.getAttribute("mostrarEmpSI") == null)
			session.setAttribute("mostrarEmpSI", MOSTRAR_EMPRESA.SI.getIdOpcion());
		if (session.getAttribute("mostrarEmpNO") == null)
			session.setAttribute("mostrarEmpNO", MOSTRAR_EMPRESA.NO.getIdOpcion());
		session.setAttribute("laboresInicial", experForm.getLaboresInicial());
		session.setAttribute("laboresFinal", experForm.getLaboresFinal());

		// Datos para los tabs
		session.setAttribute("estatusCV", estatusCV);
		session.setAttribute("thisTabId", Constantes.EVALUA_CV_FLUJO_PANTALLA.EXPERIENCIA.getIdOpcion());
		session.setAttribute("thisTabName", Constantes.EVALUA_CV_FLUJO_PANTALLA.EXPERIENCIA.getOpcion());
		session.setAttribute("thisTabPercent", Constantes.EVALUA_CV_FLUJO_PANTALLA.EXPERIENCIA.getPorcentaje());

		// Constants new
		if (session.getAttribute("dispViajarSI") == null)
			session.setAttribute("dispViajarSI", DISPONIBILIDAD_VIAJAR.SI.getIdOpcion());
		if (session.getAttribute("dispViajarNO") == null)
			session.setAttribute("dispViajarNO", DISPONIBILIDAD_VIAJAR.NO.getIdOpcion());
		if (session.getAttribute("dispRadicarSI") == null)
			session.setAttribute("dispRadicarSI", DISPONIBILIDAD_RADICAR.SI.getIdOpcion());
		if (session.getAttribute("dispRadicarNO") == null)
			session.setAttribute("dispRadicarNO", DISPONIBILIDAD_RADICAR.NO.getIdOpcion());

		session.setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());

		cargaCatalogos(session);

		PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Experiencia y expectativas");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Perfil, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
		return mapping.findForward(FORWARD_TEMPLATE_MI_ESP_CAND);
	}

	private void cargaCatalogos(HttpSession session) {

		try {
			CatalogoOpcionDelegate catalogos = CatalogoOpcionDelegateImpl.getInstance();

			long[] filtro = { 8 };
			List<CatalogoOpcionVO> aniosexperiencia = catalogos.consultarCatalogo(CATALOGO_OPCION_EXPERIENCIA, filtro,
					true);
			session.setAttribute("aniosexperiencia", aniosexperiencia);

			List<CatalogoOpcionVO> sectores = catalogos.consultarCatalogo(CATALOGO_OPCION_SUBSECTOR, false);
			session.setAttribute("sectores", sectores);

			List<CatalogoOpcionVO> jerarquias = catalogos.consultarCatalogo(CATALOGO_OPCION_JERARQUIA_PUESTO, true);
			session.setAttribute("jerarquias", jerarquias);

			List<CatalogoOpcionVO> personascargo = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(
					CATALOGO_OPCION_PERSONAS_CARGO, true);
			session.setAttribute("personascargo", personascargo);

			// Load for new
			List<CatalogoOpcionVO> tiposempleo = catalogos.consultarCatalogo(CATALOGO_OPCION_TIPO_EMPLEO);
			session.setAttribute("tiposempleo", tiposempleo);

			List<CatalogoOpcionVO> tiposcontrato = catalogos.consultarCatalogo(CATALOGO_OPCION_TIPO_CONTRATO);
			session.setAttribute("tiposcontrato", tiposcontrato);

		} catch (Exception e) {
			logger.error(e);
		}
	}

	public ActionForward ocupaciones(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		StringBuilder cadena = new StringBuilder();
		try {
			// String catalogo = request.getParameter("search");
			CatalogoOpcionDelegate services = CatalogoOpcionDelegateImpl.getInstance();
			List<CatalogoOpcionVO> resultado = services.consultarCatalogo(CATALOGO_OPCION_OCUPACION);
			if (resultado != null && !resultado.isEmpty()) {
				for (CatalogoOpcionVO vo : resultado) {
					cadena.append("<li onclick=\"setOcupacion(" + vo.getIdCatalogoOpcion() + ", '" + vo.getOpcion()
							+ "');\" id='" + vo.getIdCatalogoOpcion() + "'>" + vo.getOpcion() + "</li>");
				}
			}
			redirectJsonResponse(response, cadena.toString());
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

	/**
	 * Recibe el formulario de Experiencia para transformarlo y enviarlo a capa
	 * de negocio.
	 * 
	 * @since 10/03/2011
	 * @param ActionMapping
	 * @param ActionForm
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @return ActionForward
	 * @throws IOException
	 **/
	public ActionForward registrar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		ExpectativaVO expVO = new ExpectativaVO();
		ExperienciaVO experiencia = new ExperienciaVO();
		ExperienciaForm experienciaForm = (ExperienciaForm) form;
		// System.out.println("registrarLaboralVO: " +
		// experienciaForm.getIdHistorialLaboral());
		if ("on".equals(request.getParameter("nuncaHeTrabajado"))) {
			this.setSinExperiencia(experienciaForm, experiencia);
		} else {
			// experienciaForm.setLaboresInicial(formatoFechaCompuesto(experienciaForm.getLaboresInicial()));
			// experienciaForm.setLaboresFinal(formatoFechaCompuesto(experienciaForm.getLaboresFinal()));
			setExperiencia(experienciaForm, experiencia);
			setExpectativa(experienciaForm, expVO);
		}

		UsuarioWebVO usuario = getUsuario(request.getSession());
		if (usuario != null) {
			experiencia.setIdCandidato(usuario.getIdPropietario());
			experiencia.setIdUsuario(usuario.getIdUsuario());
			expVO.setIdCandidato(usuario.getIdPropietario());
			expVO.setIdUsuario(usuario.getIdUsuario());
		}

		try {
			CandidatoBusDelegate services = CandidatoBusDelegateImpl.getInstance();

			
			
			experiencia = services.registrarExperiencia(experiencia);
			expVO = services.registrarExpectativa(expVO);

			/**
			 * Se agrega el indicador para indexar al Candidato al FINALIZA su
			 * Session
			 **/
			request.getSession().setAttribute(ID_CANDIDATO_INDEXABLE, experiencia.getIdCandidato());
			// BolsasTrabajoBusDelegateImpl.getInstance().indexaCandidato(experiencia.getIdCandidato());

			experienciaForm.setIdHistorialLaboral(experiencia.getHistLaboral().getIdHistorialLaboral());
			
			
			
			String mensaje = getMensaje(request,"can.guardar.ext");
			if(experienciaForm.isMensajeExtra()){
				mensaje = mensaje + "  \n " +  experienciaForm.getNotSetNoToTrabajando();
			}
			
			experienciaForm.setMsg(new ResultVO(mensaje, ResultVO.TYPE_SUCCESS));
		} catch (BusinessException e) {
			experienciaForm.setMsg(new ResultVO(e.getMessage(), ResultVO.TYPE_ERROR));
			e.printStackTrace();
			logger.error("Excepción de negocio al persistir experiencia del candidato " + experiencia.getIdCandidato(), e);
		} catch (ServiceLocatorException e) {
			experienciaForm.setMsg(new ResultVO(super.getMensaje(request, "can.guardar.err"), ResultVO.TYPE_ERROR));
			e.printStackTrace();
			logger.error("Excepción de localización de servicio al persistir experiencia del candidato " + experiencia.getIdCandidato(), e);
		} catch (PersistenceException e) {
			experienciaForm.setMsg(new ResultVO(super.getMensaje(request, "can.guardar.err"), ResultVO.TYPE_ERROR));
			e.printStackTrace();
			logger.error("Excepción al persistir experiencia del candidato " + experiencia.getIdCandidato(), e);
		} catch (TechnicalException e) {
			experienciaForm.setMsg(new ResultVO(super.getMensaje(request, "can.guardar.err"), ResultVO.TYPE_ERROR));
			e.printStackTrace();
			logger.error("Excepción tecnica al persistir experiencia del candidato " + experiencia.getIdCandidato(), e);
		} catch (SQLException e) {
			experienciaForm.setMsg(new ResultVO(super.getMensaje(request, "can.guardar.err"), ResultVO.TYPE_ERROR));
			e.printStackTrace();
			logger.error("Excepción de sql al persistir experiencia del candidato " + experiencia.getIdCandidato(), e);
		} catch (IndexerException e) {
			experienciaForm.setMsg(new ResultVO(super.getMensaje(request, "aut.autorizacion.msg.indexer.error"), ResultVO.TYPE_ERROR));
			e.printStackTrace();
			logger.error("Excepción al indexar candidato " + experiencia.getIdCandidato(), e);
		} catch (Exception e) {
			experienciaForm.setMsg(new ResultVO(super.getMensaje(request, "can.guardar.err"), ResultVO.TYPE_ERROR));
			e.printStackTrace();
			logger.error("Excepción al persistir candidato " + experiencia.getIdCandidato(), e);
		}

		String json = toJson(experienciaForm);
		redirectJsonResponse(response, json);
		return null;
	}

	private void setExpectativa(ExperienciaForm origen, ExpectativaVO destino) {
		destino.setIdCandidato(origen.getIdCandidato());
		destino.setDisponibilidadViajar(origen.getDisponibilidadViajar());
		destino.setDisponibilidadRadicar(origen.getDisponibilidadRadicar());
		try {
			ExpectativaLaboralVO expLaboralVO = new ExpectativaLaboralVO();
			BeanUtils.copyProperties(expLaboralVO, origen);
			expLaboralVO.setIdAreaLaboralDeseada(Utils.parseLong(String.valueOf(origen.getIdOcupacionDeseada())
					.substring(0, 2)));
			expLaboralVO.setPrincipal(MULTIREGISTRO.PRINCIPAL.getIdOpcion());
			destino.setExpLaboral(expLaboralVO);
			ExpectativaLugarVO expLugarVO = new ExpectativaLugarVO();
			BeanUtils.copyProperties(expLugarVO, origen);
			expLugarVO.setPrincipal(MULTIREGISTRO.PRINCIPAL.getIdOpcion());
			expLaboralVO.setIdExperiencia(origen.getIdExperienciaOcupacion());
			if(origen.getIdExperienciaOcupacion2() != 0L ){
				expLaboralVO.setIdExpectativaLaboral2(origen.getIdExpectativaLaboral2());
				expLaboralVO.setIdExperiencia2(origen.getIdExperienciaOcupacion2());
				expLaboralVO.setIdOcupacionDeseada2(origen.getIdOcupacionDeseada2());
			}
			destino.setExpLugar(expLugarVO);
		} catch (IllegalAccessException e) {
			logger.error(e); // TODO Auto-generated catch block
		} catch (Exception e) {
			logger.error(e); // TODO Auto-generated catch block
		}
	}

	private void setExperiencia(ExperienciaForm origen, ExperienciaVO destino) {
		destino.setNuncaHeTrabajado(true);
		destino.setIdCandidato(origen.getIdCandidato());
		destino.setIdExperienciaTotal(origen.getIdExperienciaTotal());
		destino.setIdSectorMayorExpr(origen.getIdSectorMayorExpr());
		destino.setPuestoMayorExpr(origen.getPuestoMayorExpr());
		destino.setIdAreaLaboralMayorExpr(origen.getIdAreaLaboralMayorExpr());
		destino.setIdOcupacionMayorExpr(origen.getIdOcupacionMayorExpr());
		destino.setExperiencia(origen.getExperiencia());
		try {
			HistoriaLaboralVO histLaboral = new HistoriaLaboralVO();
			histLaboral.setIdHistorialLaboral(origen.getIdHistorialLaboral());
			histLaboral.setTrabajoActual(origen.getTrabajoActual());
			histLaboral.setIdSector(origen.getIdSector());
			histLaboral.setPuesto(origen.getPuesto());
			histLaboral.setIdAreaLaboral(origen.getIdAreaLaboral());
			histLaboral.setIdOcupacion(origen.getIdOcupacion());
			histLaboral.setEmpresa(origen.getEmpresa());
			histLaboral.setConfidencialidadEmpresa(origen.getConfidencialidadEmpresa());
			histLaboral.setAniosLaborados(origen.getAniosLaborados());
			histLaboral.setIdJerarquia(origen.getIdJerarquia());
			histLaboral.setPersonasCargo(origen.getPersonasCargo());
			histLaboral.setSalarioMensual(Utils.parseDouble(origen.getSalarioMensual()));
			histLaboral.setFuncion(origen.getFuncion());
			histLaboral.setLogro(origen.getLogro());
			if(origen.getLaboresInicial().contains("Día")){
				histLaboral.setLaboresInicial(null);
			}
			else{
				histLaboral.setLaboresInicial(Utils.convert(origen.getLaboresInicial()));
			}
			if(origen.getLaboresFinal().contains("Día")){
				histLaboral.setLaboresFinal(null);
			}
			else
			{
				histLaboral.setLaboresFinal(Utils.convert(origen.getLaboresFinal()));
			}
			histLaboral.setPrincipal(MULTIREGISTRO.PRINCIPAL.getIdOpcion());
			destino.setHistLaboral(histLaboral);
			
		} catch (Exception e) {
			logger.error(e);
		}
	}

	private void setSinExperiencia(ExperienciaForm origen, ExperienciaVO destino) {
		destino.setNuncaHeTrabajado(false);
		destino.setIdCandidato(origen.getIdCandidato());
		destino.setIdExperienciaTotal(0);
		destino.setIdSectorMayorExpr(0);
		destino.setPuestoMayorExpr("");
		destino.setIdAreaLaboralMayorExpr(0);
		destino.setIdOcupacionMayorExpr(0);
		HistoriaLaboralVO histLaboral = new HistoriaLaboralVO();
		histLaboral.setPrincipal(MULTIREGISTRO.PRINCIPAL.getIdOpcion());
		histLaboral.setPuesto(" ");
		histLaboral.setIdHistorialLaboral(origen.getIdHistorialLaboral());
		histLaboral.setIdSector(0);
		histLaboral.setIdAreaLaboral(0);
		histLaboral.setAreaLaboral(" ");
		histLaboral.setIdOcupacion(0);
		histLaboral.setOcupacion(" ");
		histLaboral.setEmpresa(" ");
		histLaboral.setConfidencialidadEmpresa(0);
		histLaboral.setLaboresInicial(new Date());
		histLaboral.setLaboresFinal(new Date());
		histLaboral.setAniosLaborados(0);
		histLaboral.setAnios(" ");
		histLaboral.setIdJerarquia(0);
		histLaboral.setJerarquia(" ");
		histLaboral.setPersonasCargo(0);
		histLaboral.setPersonas(" ");
		histLaboral.setSalarioMensual(0);
		histLaboral.setFuncion(" ");
		histLaboral.setLogro(" ");
		destino.setHistLaboral(histLaboral);
	}

	public static String formatoFechaCompuesto(String date) {
		if (null == date)
			return "";
		String year = date.substring(6, 10);
		String month = date.substring(3, 5);
		String day = date.substring(0, 2);
		return year + "-" + month + "-" + day;
	}

}
