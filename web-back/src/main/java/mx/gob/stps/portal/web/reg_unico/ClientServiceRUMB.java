package mx.gob.stps.portal.web.reg_unico;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import mx.gob.stps.portal.utils.Catalogos;
import mx.gob.stps.portal.utils.ConstantesGenerales;
import mx.gob.stps.portal.utils.Password;
import mx.gob.stps.portal.web.infra.action.GenericAction;
import mx.gob.stps.portal.web.infra.utils.Constantes;
import mx.gob.stps.portal.web.infra.utils.PropertiesLoader;
import mx.gob.stps.portal.web.security.vo.UsuarioWebVO;

public class ClientServiceRUMB extends GenericAction {

	private static Logger logger = Logger.getLogger(ClientServiceRUMB.class);

	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		return new ActionForward();
	}

	public void redirectRegisterCandidatoRU(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String urlRUAccessPoint = PropertiesLoader.getInstance().getProperty("segob_access_point");
		UsuarioWebVO usuarioFirmado = getUsuario(request.getSession());
		try {
			String app = ConstantesGenerales.RU_PARAM_APP + "=" + Catalogos.APLICACION.PORTAL_DEL_EMPLEO.getIdOpcion();
			String op = ConstantesGenerales.RU_PARAM_OP + "="
					+ ConstantesGenerales.RU_ACCESS_POINT_OP.RU_ACCESS_POINT_OP_RC.getId(); // Operación
																							// registro
																							// de
																							// candidato
			String usr = ConstantesGenerales.RU_PARAM_USER + "=";
			String pass = ConstantesGenerales.RU_PARAM_PASS + "=";
			if (usuarioFirmado != null)
				usr = usr + usuarioFirmado.getUsuario();
			else
				usr = usr + ConstantesGenerales.USUARIO_ANONIMO;
			String token = app + "&" + op + "&" + usr + "&" + pass;
			logger.info("Antes del salto................................RU");
			logger.info("token:" + token);
			String tokenEncriptado = Password.encrypt(token);
			response.sendRedirect(urlRUAccessPoint + "?token=" + tokenEncriptado);

		} catch (Exception e) {
			throw e;
		}
	}

	public void redirectEditaCandidatoRU(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String urlRUAccessPoint = PropertiesLoader.getInstance().getProperty("ru_access_point");
		UsuarioWebVO user = getUsuario(request.getSession());
		try {
			String app = ConstantesGenerales.RU_PARAM_APP + "=" + Catalogos.APLICACION.PORTAL_DEL_EMPLEO.getIdOpcion();
			String op = ConstantesGenerales.RU_PARAM_OP + "="
					+ ConstantesGenerales.RU_ACCESS_POINT_OP.RU_ACCESS_POINT_OP_CC.getId();
			String usr = ConstantesGenerales.RU_PARAM_USER + "=" + user.getUsuario();
			String pass = ConstantesGenerales.RU_PARAM_PASS + "=" + user.getPwd();
			String idCand = ConstantesGenerales.RU_PARAM_ID_CANDIDATO + "=" + user.getIdPropietario();
			String token = app + "&" + op + "&" + usr + "&" + pass + "&" + idCand;
			logger.info("Antes del salto................................RU");
			logger.info("token:" + token);
			String tokenEncriptado = Password.encrypt(token);
			response.sendRedirect(urlRUAccessPoint + "?token=" + tokenEncriptado);

		} catch (Exception e) {
			throw e;
		}
	}

	public void redirectRegisterEmpresaRU(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String urlRUAccessPoint = PropertiesLoader.getInstance().getProperty("segob_access_point");
		try {
			String app = ConstantesGenerales.RU_PARAM_APP + "=" + Catalogos.APLICACION.PORTAL_DEL_EMPLEO.getIdOpcion();
			String op = ConstantesGenerales.RU_PARAM_OP + "="
					+ ConstantesGenerales.RU_ACCESS_POINT_OP.RU_ACCESS_POINT_OP_RE.getId();
			String usr = ConstantesGenerales.RU_PARAM_USER + "=" + ConstantesGenerales.USUARIO_ANONIMO;
			String pass = ConstantesGenerales.RU_PARAM_PASS + "=";
			String token = app + "&" + op + "&" + usr + "&" + pass;
			logger.info("Antes del salto................................RU");
			logger.info("token:" + token);
			String tokenEncriptado = Password.encrypt(token);
			response.sendRedirect(urlRUAccessPoint + "?token=" + tokenEncriptado);

		} catch (Exception e) {
			throw e;
		}
	}

	public void redirectEditaEmpresaRU(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String urlRUAccessPoint = PropertiesLoader.getInstance().getProperty("segob_access_point");//ru_access_point")+"-segob";
		UsuarioWebVO user = getUsuario(request.getSession());
		try {
			String app = ConstantesGenerales.RU_PARAM_APP + "=" + Catalogos.APLICACION.PORTAL_DEL_EMPLEO.getIdOpcion();
			String op = ConstantesGenerales.RU_PARAM_OP + "="
					+ ConstantesGenerales.RU_ACCESS_POINT_OP.RU_ACCESS_POINT_OP_CE.getId();
			String usr = ConstantesGenerales.RU_PARAM_USER + "=" + user.getUsuario();
			String pass = ConstantesGenerales.RU_PARAM_PASS + "=" + user.getPwd();
			String idEmpresa = ConstantesGenerales.RU_PARAM_ID_EMPRESA + "=" + user.getIdPropietario();
			String token = app + "&" + op + "&" + usr + "&" + pass + "&" + idEmpresa;
			logger.info("Antes del salto................................RU");
			logger.info("token:" + token);
			String tokenEncriptado = Password.encrypt(token);
			response.sendRedirect(urlRUAccessPoint + "?token=" + tokenEncriptado);

		} catch (Exception e) {
			throw e;
		}
	}
//
	//Modificacion para enviar a segob
	public void redirectRegistraOfertaRU(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String urlRUAccessPoint = PropertiesLoader.getInstance().getProperty("segob_access_point");//ru_access_point
		UsuarioWebVO user = getUsuario(request.getSession());
		try {
			String app = ConstantesGenerales.RU_PARAM_APP + "=" + Catalogos.APLICACION.PORTAL_DEL_EMPLEO.getIdOpcion();
			String op = ConstantesGenerales.RU_PARAM_OP + "="
					+ ConstantesGenerales.RU_ACCESS_POINT_OP.RU_ACCESS_POINT_OP_RO.getId();
			String usr = ConstantesGenerales.RU_PARAM_USER + "=" + user.getUsuario();
			String pass = ConstantesGenerales.RU_PARAM_PASS + "=" + user.getPwd();
			String idEmpresa = ConstantesGenerales.RU_PARAM_ID_EMPRESA + "=" + user.getIdPropietario();
			String token = app + "&" + op + "&" + usr + "&" + pass + "&" + idEmpresa;
			logger.info("Antes del salto................................RU");
			logger.info("token:" + token);
			String tokenEncriptado = Password.encrypt(token);
			response.sendRedirect(urlRUAccessPoint + "?token=" + tokenEncriptado);

		} catch (Exception e) {
			throw e;
		}
	}	
	
	//RBM1 TK990 TK995 Redirect Hacia RU para Abriendo Espacios
	public void redirectRegistraOfertaRUAbriendoEspacios(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String urlRUAccessPoint = PropertiesLoader.getInstance().getProperty("ru_access_point");
		UsuarioWebVO user = getUsuario(request.getSession());
		try {
			String app = ConstantesGenerales.RU_PARAM_APP + "=" + Catalogos.APLICACION.ABRIENDO_ESPACIOS.getIdOpcion();
			String op = ConstantesGenerales.RU_PARAM_OP + "="
					+ ConstantesGenerales.RU_ACCESS_POINT_OP.RU_ACCESS_POINT_OP_RO.getId();
			String usr = ConstantesGenerales.RU_PARAM_USER + "=" + user.getUsuario();
			String pass = ConstantesGenerales.RU_PARAM_PASS + "=" + user.getPwd();
			String idEmpresa = ConstantesGenerales.RU_PARAM_ID_EMPRESA + "=" + user.getIdPropietario();
			String token = app + "&" + op + "&" + usr + "&" + pass + "&" + idEmpresa;
			logger.info("Antes del salto................................RU");
			logger.info("token:" + token);
			String tokenEncriptado = Password.encrypt(token);
			response.sendRedirect(urlRUAccessPoint + "?token=" + tokenEncriptado);

		} catch (Exception e) {
			throw e;
		}
	}	

	public void redirectEditaOfertaRU(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String urlRUAccessPoint = PropertiesLoader.getInstance().getProperty("segob_access_point");
		UsuarioWebVO user = getUsuario(request.getSession());
		try {
			String app = ConstantesGenerales.RU_PARAM_APP + "=" + Catalogos.APLICACION.PORTAL_DEL_EMPLEO.getIdOpcion();
			String op = ConstantesGenerales.RU_PARAM_OP + "="
					+ ConstantesGenerales.RU_ACCESS_POINT_OP.RU_ACCESS_POINT_OP_CO.getId();
			String usr = ConstantesGenerales.RU_PARAM_USER + "=" + user.getUsuario();
			String pass = ConstantesGenerales.RU_PARAM_PASS + "=" + user.getPwd();
			String idEmpresa = ConstantesGenerales.RU_PARAM_ID_EMPRESA + "=" + user.getIdPropietario();
			String idOferta = ConstantesGenerales.RU_PARAM_ID_OFERTA + "=" + request.getParameter("id");

			String token = app + "&" + op + "&" + usr + "&" + pass + "&" + idEmpresa + "&" + idOferta;
			String idRegValidar = request.getParameter(Constantes.PARAM_ID_REGISTRO_POR_VALIDAR);
			if (idRegValidar != null && !"".equals(idRegValidar)) {
				token = token + "&" + Constantes.PARAM_ID_REGISTRO_POR_VALIDAR + "="
						+ request.getParameter(Constantes.PARAM_ID_REGISTRO_POR_VALIDAR);
			}

			logger.info("Antes del salto................................RU");
			logger.info("token:" + token);
			String tokenEncriptado = Password.encrypt(token);
			response.sendRedirect(urlRUAccessPoint + "?token=" + tokenEncriptado);

		} catch (Exception e) {
			throw e;
		}
	}

	public void redirectUsarPlangillaRU(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String urlRUAccessPoint = PropertiesLoader.getInstance().getProperty("segob_access_point");
		UsuarioWebVO user = getUsuario(request.getSession());
		try {
			String app = ConstantesGenerales.RU_PARAM_APP + "=" + Catalogos.APLICACION.PORTAL_DEL_EMPLEO.getIdOpcion();
			String op = ConstantesGenerales.RU_PARAM_OP + "="
					+ ConstantesGenerales.RU_ACCESS_POINT_OP.RU_ACCESS_POINT_OP_PO.getId();
			String usr = ConstantesGenerales.RU_PARAM_USER + "=" + user.getUsuario();
			String pass = ConstantesGenerales.RU_PARAM_PASS + "=" + user.getPwd();
			String idEmpresa = ConstantesGenerales.RU_PARAM_ID_EMPRESA + "=" + user.getIdPropietario();
			String idOferta = ConstantesGenerales.RU_PARAM_ID_OFERTA + "=" + request.getParameter("id");

			String token = app + "&" + op + "&" + usr + "&" + pass + "&" + idEmpresa + "&" + idOferta;
			logger.info("Antes del salto................................RU");
			logger.info("token:" + token);
			String tokenEncriptado = Password.encrypt(token);
			response.sendRedirect(urlRUAccessPoint + "?token=" + tokenEncriptado);

		} catch (Exception e) {
			throw e;
		}
	}
	//RBM1 6junio change Miguel	
	public void redirectComplCandidatoRU(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String urlRUAccessPoint = PropertiesLoader.getInstance().getProperty("ru_access_point");
		UsuarioWebVO user = getUsuario(request.getSession());
		try {
			String app = ConstantesGenerales.RU_PARAM_APP + "=" + Catalogos.APLICACION.PORTAL_DEL_EMPLEO.getIdOpcion();
			String op = ConstantesGenerales.RU_PARAM_OP + "="
					+ ConstantesGenerales.RU_ACCESS_POINT_OP.RU_ACCESS_POINT_OP_CCOM.getId();
			String usr = ConstantesGenerales.RU_PARAM_USER + "=" + user.getUsuario();
			String pass = ConstantesGenerales.RU_PARAM_PASS + "=" + user.getPwd();
			String idCand = ConstantesGenerales.RU_PARAM_ID_CANDIDATO + "=" + user.getIdPropietario();
			String token = app + "&" + op + "&" + usr + "&" + pass + "&" + idCand;
			logger.info("Antes del salto................................RU");
			logger.info("token:" + token);
			String tokenEncriptado = Password.encrypt(token);
			response.sendRedirect(urlRUAccessPoint + "?token=" + tokenEncriptado);
		} catch (Exception e) {
			throw e;
		}
	}


}
