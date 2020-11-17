package mx.gob.stps.portal.web.company.action;

import static mx.gob.stps.portal.core.infra.utils.Constantes.NUM_ULTIMAS_POSTULACIONES;
import static mx.gob.stps.portal.web.infra.utils.Constantes.BODY_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.DESCRIPCION_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FACEBOOK_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_TEMPLATE_MI_ESP_EMP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TAB_MENU;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TAB_MI_ESPACIO;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TITULO_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TWITTER_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.URL_ESPECIFICA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.USUARIO_APP;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import mx.gob.stps.portal.core.candidate.vo.OfertaPostulacionVO;
import mx.gob.stps.portal.core.empresa.vo.EmpresaVO;
import mx.gob.stps.portal.core.infra.utils.Constantes.ESTATUS;
import mx.gob.stps.portal.core.infra.utils.Constantes.PERFIL;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_USUARIO;
import mx.gob.stps.portal.core.oferta.vo.OfertaEmpleoVO;
import mx.gob.stps.portal.utils.Catalogos;
import mx.gob.stps.portal.utils.Catalogos.WS_CURP_ERROR;
import mx.gob.stps.portal.utils.Catalogos.WS_CURP_OPERACION;
import mx.gob.stps.portal.web.company.delegate.EmpresaRegistroBusDelegate;
import mx.gob.stps.portal.web.company.delegate.EmpresaRegistroBusDelegateImpl;
import mx.gob.stps.portal.web.company.delegate.RegisterBusDelegate;
import mx.gob.stps.portal.web.company.delegate.RegisterBusDelegateImpl;
import mx.gob.stps.portal.web.company.form.MiEspacioEmpForm;
import mx.gob.stps.portal.web.company.form.RegistroEmpresaForm;
import mx.gob.stps.portal.web.infra.utils.PropertiesLoader;
import mx.gob.stps.portal.web.infra.utils.Utils;
import mx.gob.stps.portal.web.infra.vo.ResultVO;
import mx.gob.stps.portal.web.oferta.delegate.OfertaBusDelegate;
import mx.gob.stps.portal.web.oferta.delegate.OfertaBusDelegateImpl;
import mx.gob.stps.portal.web.offer.delegate.RecentOfferSummaryBusDelegate;
import mx.gob.stps.portal.web.offer.delegate.RecentOfferSummaryBusDelegateImpl;
import mx.gob.stps.portal.web.security.vo.UsuarioWebVO;
import mx.gob.stps.portal.ws.renapo.vo.CurpVO;

public class MiEspacioEmpresasAction extends Action {
	private static Logger logger = Logger.getLogger(MiEspacioEmpresasAction.class);
	public static final String EXISTEN_OFERTAS_ACTIVAS = "existenOfertasActivas";
	public static final String SI = "Si";
	public static final String NO = "No";
	private final static String PERSONA_FISICA_SIN_CURP_VALIDADO = "PERSONA_FISICA_SIN_CURP_VALIDADO";

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		if (null != request.getParameter("method") && "responsivo".equals(request.getParameter("method")))
			return this.responsivo(mapping, form, request, response);
		session.removeAttribute(EXISTEN_OFERTAS_ACTIVAS);

		String jspForward = mapping.getInput();
		ActionForward forward = mapping.findForward(FORWARD_TEMPLATE_MI_ESP_EMP);

		UsuarioWebVO usuarioWeb = (UsuarioWebVO) session.getAttribute(USUARIO_APP);

		if (usuarioWeb != null && usuarioWeb.getIdTipoUsuario() == TIPO_USUARIO.EMPRESA.getIdTipoUsuario()) {

			// FIXME TEMPORAL SE DESHABILITA LA SESION ACTIVA
			/*
			 * if (session.getAttribute(FLAG_SESION_ACTIVA)==null){ / ** Cuando
			 * el usuario entra a su modulo correspondiente se establece la
			 * bandera de Sesion Activa ** / try{ SecutityDelegate
			 * secutityServices = SecutityDelegateImpl.getInstance();
			 * secutityServices.actualizaSesionActiva(usuarioWeb.getIdUsuario(),
			 * SESION_ACTIVA); session.setAttribute(FLAG_SESION_ACTIVA, "true");
			 * } catch(Exception e){logger.error(e);} }
			 */

			try {
				MiEspacioEmpForm miEspacioEmpForm = (MiEspacioEmpForm) form;

				long idEmpresa = usuarioWeb.getIdPropietario();
				if (idEmpresa > 0)
					session.setAttribute("idEmpresa", "" + idEmpresa);

				RegisterBusDelegate service = RegisterBusDelegateImpl.getInstance();

				EmpresaVO empresa = service.findEmpresaById(idEmpresa);

				if (empresa == null)
					empresa = service.consultaEmpresaPorAutorizar(idEmpresa);

				if (empresa != null) {
					miEspacioEmpForm.setIdEmpresa(idEmpresa);
					miEspacioEmpForm
							.setNombreEmpresa(null != empresa.getNombreEmpresa() ? empresa.getNombreEmpresa() : "");
					miEspacioEmpForm.setContactoEmpresa(empresa.getContactoEmpresa());
					miEspacioEmpForm.setIdPortalEmpleo(empresa.getIdPortalEmpleo());

					if (null != empresa.getFechaNacimiento()) {
						session.setAttribute("fechaNac", Utils.formatDDMMYYYY(empresa.getFechaNacimiento()));
					}

					if (esPersonaFisicaSinCurpValidado(empresa)) {
						request.setAttribute(PERSONA_FISICA_SIN_CURP_VALIDADO, PERSONA_FISICA_SIN_CURP_VALIDADO);
					}

					// Obtiene las ofertas y postulaciones recientes
					RecentOfferSummaryBusDelegate summaryServices = RecentOfferSummaryBusDelegateImpl.getInstance();

					miEspacioEmpForm.setOfertasRecientes(summaryServices.getMyRecentOffers(idEmpresa));

					miEspacioEmpForm.setPostulacionesRecientes(summaryServices.getRecentPostulations(idEmpresa));

					List<OfertaEmpleoVO> ultimasOfertas = ultimasOfertasActivas(idEmpresa);

					int totalUltimosPostulantes = 0;
					for (OfertaEmpleoVO of : ultimasOfertas)
						totalUltimosPostulantes += of.getTotalPostulados();

					// Verifica que exista al menos una oferta con estatus de
					// activa
					Long numeroOfertasActivas = summaryServices.getCountOfertasActivas(idEmpresa);

					if (numeroOfertasActivas >= 1)
						request.getSession().setAttribute(EXISTEN_OFERTAS_ACTIVAS, SI);
					else
						request.getSession().setAttribute(EXISTEN_OFERTAS_ACTIVAS, NO);

					request.setAttribute("ultimasOfertas", ultimasOfertas);
					request.setAttribute("totalUltimosPostulantes", totalUltimosPostulantes);

				} else {
					registraError(request, ""); // TODO
				}

				// recuperamos el total de postulantes
				OfertaBusDelegate serviceOffer = OfertaBusDelegateImpl.getInstance();
				List<OfertaPostulacionVO> listaPostulantes = serviceOffer.obtienePostulantesDeEmpresa(idEmpresa);

				// nos quedamos con los últimos postulantes con estatus activo
				List<OfertaPostulacionVO> listaPostulacionesRecientes = new ArrayList<OfertaPostulacionVO>();

				int numUltimosPostulantes = 0;

				for (OfertaPostulacionVO postulante : listaPostulantes) {

					if (postulante.getIdCandidato() > 0 && postulante.getEstatus() == ESTATUS.POSTULADO.getIdOpcion()) {

						numUltimosPostulantes++;
						listaPostulacionesRecientes.add(postulante);

					}
				}

				if (!listaPostulacionesRecientes.isEmpty()) {
					request.setAttribute("listaPostulacionesRecientes", listaPostulacionesRecientes);
				}

			} catch (Exception e) {
				logger.error(e);
				registraError(request, ""); // TODO
			}
			// RBM1 TK997 y TK998 25 abr 2018
		} else if (usuarioWeb != null && (usuarioWeb.getIdPerfil() == PERFIL.PUBLICADOR.getIdOpcion()
				|| usuarioWeb.getIdPerfil() == PERFIL.ADMINISTRADOR_SNETEL.getIdOpcion())) {
			// long idEmpresa = usuarioWeb.getIdPropietario();
			// if (idEmpresa > 0) session.setAttribute("idEmpresa", "" +
			// idEmpresa);
			logger.info("ACTION_PUBLICADOR usuarioWeb.getIdPerfil() " + usuarioWeb.getIdPerfil());
			// return new ActionForward(Constantes.FORWARD_ACTION_PUBLICADOR,
			// false);
			// TODO idRegValidar

			forward = mapping.findForward("ACTION_PUBLICADOR");
			return forward;

		} // RBM1 TK989 TK993
		else if (usuarioWeb == null) {
			forward = mapping.findForward("ACTION_HOME");
			return forward;
		}

		// }else {
		// forward = mapping.findForward("ACTION_LOGIN_EMPRESA");
		// }

		session.setAttribute(TAB_MENU, TAB_MI_ESPACIO);

		request.getSession().setAttribute("_urlpageinvoke", "miEspacioEmpresas.do");
		request.getSession().setAttribute(BODY_JSP, jspForward);
		PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Mi espacio empresa");
		request.getSession().setAttribute(DESCRIPCION_PAGINA,
				"Espacio Empresa, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo ");
		request.getSession().setAttribute(FACEBOOK_IMAGE,
				properties.getProperty("app.context.url.ssl") + "/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE,
				properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA,
				properties.getProperty("app.context.url.ssl") + response.encodeURL(request.getRequestURI().toString()));
		return forward;
	}

	private void registraError(HttpServletRequest request, String mensaje) {
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(mensaje));
		saveErrors(request, errors);
	}

	private boolean esPersonaFisicaSinCurpValidado(EmpresaVO empresa) {
		boolean esPersonaFisicaSinCurpValidado = false;
		if (empresa.getIdTipoEmpresa() == Catalogos.TIPO_EMPRESA.PRIVADA.getTipoEmpresa()
				&& empresa.getIdTipoPersona() == (long) Catalogos.TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona()
				&& (null == empresa.getCurpPF())) {
			// || null == empresa.getCurpValidada()
			// || empresa.getCurpValidada() != (short)
			// Catalogos.DECISION.SI.getIdOpcion().intValue())){

			esPersonaFisicaSinCurpValidado = true;
		}

		return esPersonaFisicaSinCurpValidado;
	}

	private List<OfertaEmpleoVO> ultimasOfertasActivas(long idEmpresa) throws Exception {
		List<OfertaEmpleoVO> ultimasOfertasActivas = new ArrayList<OfertaEmpleoVO>();

		OfertaBusDelegate services = OfertaBusDelegateImpl.getInstance();
		List<OfertaEmpleoVO> ofertas = services.consultaTotalPostuladosPorOferta(idEmpresa);

		int count = 0;
		for (OfertaEmpleoVO oferta : ofertas) {
			count++;
			ultimasOfertasActivas.add(oferta);
			// if (count == NUMERO_OFERTAS_RECIENTES) break;
		}

		/*
		 * AdmonCandidatosBusDelegate serviceCandidatos =
		 * AdmonCandidatosBusDelegateImpl.getInstance();
		 * 
		 * List<OfertaEmpleoVO> ofertas =
		 * services.obtenerOfertasEmpresa(idEmpresa);
		 * 
		 * List<OfertaEmpleoVO> ofertasActivas = new
		 * ArrayList<OfertaEmpleoVO>();
		 * 
		 * @SuppressWarnings("rawtypes") Iterator it = ofertas.iterator(); while
		 * (it.hasNext()){ OfertaEmpleoVO oferta = (OfertaEmpleoVO)it.next(); //
		 * obtenemos el total de postulados List<CandidatoVo> postulados =
		 * serviceCandidatos.obtenerPostulados(oferta.getIdEmpresa(),
		 * oferta.getIdOfertaEmpleo()); if (postulados != null &&
		 * !postulados.isEmpty()){ oferta.setTotalPostulados(postulados.size());
		 * } // consideramos únicamente las ofertas con estatus activo y que
		 * tengan algún postulado if (oferta.getEstatus() ==
		 * Constantes.ESTATUS.ACTIVO.getIdOpcion() &&
		 * oferta.getTotalPostulados() > 0){ ofertasActivas.add(oferta); } }
		 * 
		 * // procedemos a ordenar la lista de ofertas activas por fecha de
		 * modificación descendente Collections.sort(ofertasActivas, new
		 * Comparator<OfertaEmpleoVO>(){
		 * 
		 * @Override public int compare(OfertaEmpleoVO o1, OfertaEmpleoVO o2) {
		 * return
		 * o2.getFechaModificacion().compareTo(o1.getFechaModificacion()); }});
		 * // nos quedamos con las ultimas ofertas modificadas
		 * List<OfertaEmpleoVO> ultimasOfertasActivas = new
		 * ArrayList<OfertaEmpleoVO>();
		 * 
		 * @SuppressWarnings("rawtypes") Iterator it2 =
		 * ofertasActivas.iterator(); int i = 1;
		 * 
		 * while(it2.hasNext() && i <=
		 * Constantes.NUM_ULTIMAS_OFERTAS_POSTULANTES){ OfertaEmpleoVO
		 * ofertaActiva = (OfertaEmpleoVO)it2.next();
		 * ultimasOfertasActivas.add(ofertaActiva); i++; }
		 */

		return ultimasOfertasActivas;
	}

	public ActionForward validaCurp(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		RegistroEmpresaForm registroForm = (RegistroEmpresaForm) form;

		String type = "";
		String message = "";
		CurpVO datosCurp = null;

		try {

			CurpVO datosPersonales = new CurpVO();

			EmpresaRegistroBusDelegate delegate = EmpresaRegistroBusDelegateImpl.getInstance();

			String curp = null != request.getParameter("curp") ? (String) request.getParameter("curp") : null;

			if (curp != null) {

				datosPersonales.setCurp(curp);

				datosCurp = delegate.consultaCURPPorDatosPersonales(datosPersonales);

			}

			if (null != datosCurp && null != datosCurp.getOperacion()
					&& WS_CURP_OPERACION.OPERACION_EXITOSA == datosCurp.getOperacion()) {
				type = ResultVO.TYPE_SUCCESS;
				message = "CURP validado";

			} else if (null != datosCurp && null != datosCurp.getOperacion()
					&& WS_CURP_OPERACION.OPERACION_NO_EXITOSA == datosCurp.getOperacion()) {
				type = ResultVO.TYPE_ERROR;
				message = WS_CURP_ERROR.CURP_NO_EXISTE.getError();

			} else if (null != datosCurp && null != datosCurp.getWsError()) {
				type = ResultVO.TYPE_ERROR;

				if (datosCurp.getWsError().getIdError() > 0) {
					registroForm.setIdMotivoNoValidacion((long) datosCurp.getWsError().getIdError());
					message = datosCurp.getWsError().getError();
				}
				message = "se produjo un error en la consulta de Curp.";
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error(ex);
			type = ResultVO.TYPE_ERROR;
			message = WS_CURP_ERROR.ERROR_EN_CONSULTA.getError();
		}

		try {
			ResultVO resultado = new ResultVO(message, type);
			if (null != datosCurp && null != datosCurp.getCurp()) {
				resultado.setValue(datosCurp.getCurp());
			}
			String json = toJson(resultado);
			redirectJsonResponse(response, json);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	protected String toJson(Object obj) {
		return Utils.toJson(obj);
	}

	protected void redirectJsonResponse(HttpServletResponse response, String json) throws IOException {
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter pw = response.getWriter();
		pw.write(json);
		pw.flush();
		pw.close();
		response.flushBuffer();
	}

	public ActionForward responsivo(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String FORWARD_TEMPLATE_MI_ESP_EMP = "templateEspacioEmpresasResp";
		HttpSession session = request.getSession();
		session.removeAttribute(EXISTEN_OFERTAS_ACTIVAS);
		String jspForward = mapping.getInput();

		PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Mi espacio empresa");
		request.getSession().setAttribute(DESCRIPCION_PAGINA,
				"Espacio Empresa, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo ");
		request.getSession().setAttribute(FACEBOOK_IMAGE,
				properties.getProperty("app.context.url.ssl") + "/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE,
				properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA,
				properties.getProperty("app.context.url.ssl") + response.encodeURL(request.getRequestURI().toString()));
		ActionForward forward = mapping.findForward(FORWARD_TEMPLATE_MI_ESP_EMP);
		UsuarioWebVO usuarioWeb = (UsuarioWebVO) session.getAttribute(USUARIO_APP);
		if (usuarioWeb != null && usuarioWeb.getIdTipoUsuario() == TIPO_USUARIO.EMPRESA.getIdTipoUsuario()) {
			try {
				MiEspacioEmpForm miEspacioEmpForm = (MiEspacioEmpForm) form;
				long idEmpresa = usuarioWeb.getIdPropietario();
				if (idEmpresa > 0)
					session.setAttribute("idEmpresa", "" + idEmpresa);
				RegisterBusDelegate service = RegisterBusDelegateImpl.getInstance();
				EmpresaVO empresa = service.findEmpresaById(idEmpresa);
				if (empresa == null)
					empresa = service.consultaEmpresaPorAutorizar(idEmpresa);
				if (empresa != null) {
					miEspacioEmpForm.setIdEmpresa(idEmpresa);
					miEspacioEmpForm
							.setNombreEmpresa(null != empresa.getNombreEmpresa() ? empresa.getNombreEmpresa() : "");
					miEspacioEmpForm.setContactoEmpresa(empresa.getContactoEmpresa());
					miEspacioEmpForm.setIdPortalEmpleo(empresa.getIdPortalEmpleo());
					if (null != empresa.getFechaNacimiento()) {
						session.setAttribute("fechaNac", Utils.formatDDMMYYYY(empresa.getFechaNacimiento()));
					}
					if (esPersonaFisicaSinCurpValidado(empresa)) {
						request.setAttribute(PERSONA_FISICA_SIN_CURP_VALIDADO, PERSONA_FISICA_SIN_CURP_VALIDADO);
					}
					// Obtiene las ofertas y postulaciones recientes
					RecentOfferSummaryBusDelegate summaryServices = RecentOfferSummaryBusDelegateImpl.getInstance();
					miEspacioEmpForm.setOfertasRecientes(summaryServices.getMyRecentOffers(idEmpresa));
					miEspacioEmpForm.setPostulacionesRecientes(summaryServices.getRecentPostulations(idEmpresa));
					List<OfertaEmpleoVO> ultimasOfertas = ultimasOfertasActivas(idEmpresa);
					int totalUltimosPostulantes = 0;
					for (OfertaEmpleoVO of : ultimasOfertas)
						totalUltimosPostulantes += of.getTotalPostulados();
					// Verifica que exista al menos una oferta con estatus de
					// activa
					Long numeroOfertasActivas = summaryServices.getCountOfertasActivas(idEmpresa);
					if (numeroOfertasActivas >= 1)
						request.getSession().setAttribute(EXISTEN_OFERTAS_ACTIVAS, SI);
					else
						request.getSession().setAttribute(EXISTEN_OFERTAS_ACTIVAS, NO);
					request.setAttribute("ultimasOfertas", ultimasOfertas);
					request.setAttribute("totalUltimosPostulantes", totalUltimosPostulantes);

				} else {
					registraError(request, "");
				}
				// recuperamos el total de postulantes
				OfertaBusDelegate serviceOffer = OfertaBusDelegateImpl.getInstance();
				List<OfertaPostulacionVO> listaPostulantes = serviceOffer.obtienePostulantesDeEmpresa(idEmpresa);
				// nos quedamos con los últimos postulantes con estatus activo
				List<OfertaPostulacionVO> listaPostulacionesRecientes = new ArrayList<OfertaPostulacionVO>();
				int numUltimosPostulantes = 0;
				for (OfertaPostulacionVO postulante : listaPostulantes) {
					if (postulante.getIdCandidato() > 0 && postulante.getEstatus() == ESTATUS.POSTULADO.getIdOpcion()) {
						numUltimosPostulantes++;
						listaPostulacionesRecientes.add(postulante);
						if (numUltimosPostulantes == NUM_ULTIMAS_POSTULACIONES)
							break;
					}
				}
				if (!listaPostulacionesRecientes.isEmpty()) {
					request.setAttribute("listaPostulacionesRecientes", listaPostulacionesRecientes);
				}
			} catch (Exception e) {
				logger.error(e);
				registraError(request, "");
			}
		} else {
			forward = mapping.findForward("ACTION_LOGIN");
		}
		session.setAttribute(TAB_MENU, TAB_MI_ESPACIO);
		request.getSession().setAttribute("_urlpageinvoke", "miEspacioEmpresas.do?method=responsivo");
		request.getSession().setAttribute(BODY_JSP, jspForward);
		return forward;
	}
}