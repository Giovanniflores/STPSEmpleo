/**
 * 
 */
package mx.gob.stps.portal.web.candidate.action;

import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_DOMINIO;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_IDIOMAS;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.JSP_IDIOMA;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.gob.stps.portal.core.candidate.vo.IdiomaVO;
import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.core.infra.utils.Constantes.MULTIREGISTRO;
import mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO;
import mx.gob.stps.portal.web.candidate.delegate.CandidatoBusDelegate;
import mx.gob.stps.portal.web.candidate.delegate.CandidatoBusDelegateImpl;
import mx.gob.stps.portal.web.candidate.form.IdiomaForm;
import mx.gob.stps.portal.web.infra.action.GenericAction;
import mx.gob.stps.portal.web.infra.delegate.CatalogoOpcionDelegateImpl;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.infra.utils.Utils;
import mx.gob.stps.portal.web.infra.vo.CatalogoVO;
import mx.gob.stps.portal.web.infra.vo.ResultVO;
import mx.gob.stps.portal.web.security.vo.UsuarioWebVO;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Felipe Juàrez Ramìrez
 * @since 23/03/2011
 * 
 */
public class IdiomaAction extends GenericAction {

	private static Logger logger = Logger.getLogger(IdiomaAction.class);
	private long[] filtro_idioma = { Constantes.IDIOMAS.NO_REQUISITO.getIdOpcion() };
	private int sumarParaLibrarIndiceCero = 2;

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

		IdiomaForm idiomaForm = (IdiomaForm) form;
		idiomaForm.setIdCandidato(this.getidCandidato(request.getSession()));
		List<CatalogoOpcionVO> opciones = new ArrayList<CatalogoOpcionVO>();

		try {
			CandidatoBusDelegate services = CandidatoBusDelegateImpl.getInstance();
			List<IdiomaVO> idiomasVO = services.initIdiomas(idiomaForm.getIdCandidato(),
					MULTIREGISTRO.ADICIONAL.getIdOpcion());

			// Inicializa arreglo
			idiomaForm.setIdiomas(new IdiomaVO[idiomasVO.size()]);

			// Copia arreglo
			idiomaForm.setIdiomas(idiomasVO.toArray(idiomaForm.getIdiomas()));
			// logger.info("IdiomaForm size: " +
			// idiomaForm.getIdiomas().length);

			opciones = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(CATALOGO_OPCION_IDIOMAS,
					filtro_idioma);
		} catch (ServiceLocatorException e) {
			logger.error(e);
		} catch (SQLException e) {
			logger.error(e);
		}

		if (request.getSession().getAttribute("depIdioma") == null) {
			// Carga en sesiòn los catalogos asociados a cada opcion
			// FIXME el valor de 2 esta fijo
			String[] depCat = new String[opciones.size() + sumarParaLibrarIndiceCero];
			depCat[0] = "0";

			for (CatalogoOpcionVO opcion : opciones) {
				depCat[(int) opcion.getIdCatalogoOpcion()] = String.valueOf(opcion.getIdCatalagoAsociado());
				System.out.println("IdiomaAction.init depCat[" + opcion.getIdCatalogoOpcion() + "]="
						+ opcion.getIdCatalagoAsociado());
			}
			request.getSession().setAttribute("depIdioma", depCat);
		}

		// request.getSession().setAttribute(BODY_JSP,
		// mapping.findForward(FORWARD_JSP).getPath());
		return mapping.findForward(FORWARD_JSP);
		// return mapping.findForward(FORWARD_TEMPLATE_CAND);
	}

	public ActionForward saveAddLang(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		List<IdiomaVO> idiomasVO = null;
		long idCertificacion = Utils.parseLong(request.getParameter("idCertificacionAdd"));
		long idDominio = Utils.parseLong(request.getParameter("idDominioAdd"));
		long idioma = Utils.parseLong(request.getParameter("idIdiomaAdd"));
		IdiomaVO lang = new IdiomaVO();
		lang.setIdCertificacion(idCertificacion);
		lang.setIdDominio(idDominio);
		lang.setIdIdioma(idioma);
		try {
			CandidatoBusDelegate services = CandidatoBusDelegateImpl.getInstance();
			// Validar idioma duplicado
			idiomasVO = services.buscarIdiomas(getidCandidato(request.getSession()));
			if (null != idiomasVO) {
				if (idiomasVO.isEmpty())
					lang.setPrincipal(Constantes.MULTIREGISTRO.PRINCIPAL.getIdOpcion());
				else
					lang.setPrincipal(Constantes.MULTIREGISTRO.ADICIONAL.getIdOpcion());
			} else {
				lang.setPrincipal(Constantes.MULTIREGISTRO.PRINCIPAL.getIdOpcion());
				idiomasVO = new ArrayList<IdiomaVO>();
			}
			boolean _existe = false;
			for (IdiomaVO idiomaPrev : idiomasVO) {
				_existe = (idiomaPrev.getIdIdioma() == idioma);
				if (_existe)
					break;
			}
			if (!_existe) {
				services.agregarIdioma(getidCandidato(request.getSession()), lang);
				idiomasVO.add(lang);
			}
		} catch (Exception e) {
			logger.error(e);
		}
		request.getSession().setAttribute("IDIOMAS_LIST", idiomasVO);
		getOptionsSelectedLang(idiomasVO, request);
		getOptionsSelectedLangCert(idiomasVO, request);
		getOptionsSelectedLangDom(idiomasVO, request);
		return mapping.findForward(JSP_IDIOMA);
	}

	@SuppressWarnings("unchecked")
	public ActionForward deleteLang(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		List<IdiomaVO> idiomasVO = null;
		long idLang = Utils.parseLong(request.getParameter("idLang"));
		try {
			CandidatoBusDelegate services = CandidatoBusDelegateImpl.getInstance();
			services.borrarIdioma(idLang);
			idiomasVO = (List<IdiomaVO>) request.getSession().getAttribute("IDIOMAS_LIST");
			if (idLang != -1 && idiomasVO != null && !idiomasVO.isEmpty()) {
				int index = 0;
				boolean found = false;
				for (index = 0; index < idiomasVO.size(); index++) {
					if (idiomasVO.get(index).getIdCandidatoIdioma() == idLang) {
						found = true;
						break;
					}
				}
				if (found)
					idiomasVO.remove(index);
			}
			request.getSession().setAttribute("IDIOMAS_LIST", idiomasVO);
			if (idiomasVO != null && !idiomasVO.isEmpty()) {
				getOptionsSelectedLang(idiomasVO, request);
				getOptionsSelectedLangCert(idiomasVO, request);
				getOptionsSelectedLangDom(idiomasVO, request);
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return mapping.findForward("JSP_IDIOMA");
	}

	@SuppressWarnings("unchecked")
	public ActionForward updateCert(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		long idIdioma = Utils.parseLong(request.getParameter("idLang"));
		long idCandidatoIdioma = Utils.parseLong(request.getParameter("idCandLang"));
		List<IdiomaVO> idiomasVO = (List<IdiomaVO>) request.getSession().getAttribute("IDIOMAS_LIST");
		Iterator<IdiomaVO> it = idiomasVO.iterator();
		while (it.hasNext()) {
			IdiomaVO lang = it.next();
			if (lang.getIdCandidatoIdioma() == idCandidatoIdioma) {
				lang.setIdCandidatoIdioma(idCandidatoIdioma);
				lang.setIdIdioma(idIdioma);
				if (idIdioma == 1)
					lang.setIdCertificacion(2);
				else
					lang.setIdCertificacion(0);
				lang.setIdDominio(0);
			}
		}
		request.getSession().setAttribute("IDIOMAS_LIST", idiomasVO);
		getOptionsSelectedLang(idiomasVO, request);
		getOptionsSelectedLangCert(idiomasVO, request);
		getOptionsSelectedLangDom(idiomasVO, request);
		return mapping.findForward("JSP_IDIOMA");
	}

	@SuppressWarnings("unchecked")
	public ActionForward updateDom(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		long idCertificacion = Utils.parseLong(request.getParameter("idCert"));
		long idCandidatoIdioma = Utils.parseLong(request.getParameter("idCandLang"));
		List<IdiomaVO> idiomasVO = (List<IdiomaVO>) request.getSession().getAttribute("IDIOMAS_LIST");
		Iterator<IdiomaVO> it = idiomasVO.iterator();
		while (it.hasNext()) {
			IdiomaVO lang = it.next();
			if (lang.getIdCandidatoIdioma() == idCandidatoIdioma) {
				lang.setIdCandidatoIdioma(idCandidatoIdioma);
				lang.setIdCertificacion(idCertificacion);
				if (idCertificacion > 0 && idCertificacion != 2)
					lang.setIdDominio(3);
				else
					lang.setIdDominio(0);
			}
		}
		request.getSession().setAttribute("IDIOMAS_LIST", idiomasVO);
		getOptionsSelectedLang(idiomasVO, request);
		getOptionsSelectedLangCert(idiomasVO, request);
		getOptionsSelectedLangDom(idiomasVO, request);
		return mapping.findForward("JSP_IDIOMA");
	}

	public ActionForward agregarIdioma(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		IdiomaForm idiomaForm = (IdiomaForm) form;
		idiomaForm.setIdCandidato(this.getidCandidato(request.getSession()));
		try {
			CandidatoBusDelegate services = CandidatoBusDelegateImpl.getInstance();

			// Validar idioma duplicado
			List<IdiomaVO> idiomasVO = services.buscarIdiomas(idiomaForm.getIdCandidato());

			boolean _existe = false;
			for (IdiomaVO idioma : idiomasVO) {
				_existe = (idioma.getIdIdioma() == idiomaForm.getIdIdioma());
				if (_existe)
					break;
			}

			if (!_existe) {
				IdiomaVO idiomaVO = new IdiomaVO();
				BeanUtils.copyProperties(idiomaVO, idiomaForm);
				// logger.info("idiomaVO: " + idiomaVO.toString());
				services.agregarIdioma(idiomaForm.getIdCandidato(), idiomaVO);
				// logger.info("idGenerado: " +
				// idiomaVO.getIdCandidatoIdioma());
				idiomaForm.setIdCandidatoIdioma(idiomaVO.getIdCandidatoIdioma());
				idiomaForm.setMsg(new ResultVO(super.getMensaje(request, "can.guardar.idioma"), ResultVO.TYPE_SUCCESS));
			} else {
				idiomaForm
						.setMsg(new ResultVO(super.getMensaje(request, "can.guardar.idioma.err"), ResultVO.TYPE_ERROR));
			}

			String json = toJson(idiomaForm);
			redirectJsonResponse(response, json);

		} catch (ServiceLocatorException e) {
			logger.error(e);
		} catch (PersistenceException e) {
			logger.error(e);
		} catch (IllegalAccessException e) {
			logger.error(e);
		} catch (InvocationTargetException e) {
			logger.error(e);
		} catch (SQLException e) {
			logger.error(e);
		} catch (IOException e) {
			logger.error(e);
		}

		return null;
	}

	public ActionForward borrarIdioma(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		IdiomaForm idiomaForm = (IdiomaForm) form;
		// logger.info("id: " + idiomaForm.getIdCandidatoIdioma());

		try {
			CandidatoBusDelegate services = CandidatoBusDelegateImpl.getInstance();
			services.borrarIdioma(idiomaForm.getIdCandidatoIdioma());

			redirectJsonResponse(response, "");

		} catch (ServiceLocatorException e) {
			logger.error(e);
		} catch (PersistenceException e) {
			logger.error(e);
		} catch (IOException e) {
			logger.error(e);
		}

		return null;
	}

	public ActionForward cargarIdioma(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			CatalogoOpcionVO vo = new CatalogoOpcionVO();
			vo.setIdCatalogoOpcion(0);
			vo.setOpcion("");
			List<CatalogoOpcionVO> opciones = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(
					CATALOGO_OPCION_IDIOMAS, filtro_idioma, true);
			opciones.add(0, vo);
			CatalogoVO cat = getCatalogo(opciones);
			String json = toJson(cat);
			redirectJsonResponse(response, json);

		} catch (ServiceLocatorException e) {
			logger.error(e);
		} catch (IOException e) {
			logger.error(e);
		}

		return null;
	}

	public ActionForward cargarCertificacion(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			long idCatDep = Utils.parseLong(request.getParameter("idCatDep"));
			CatalogoOpcionVO vo = new CatalogoOpcionVO();
			vo.setIdCatalogoOpcion(0);
			vo.setOpcion("");
			List<CatalogoOpcionVO> opciones = CatalogoOpcionDelegateImpl.getInstance()
					.consultarCatalogo(idCatDep, true);
			opciones.add(0, vo);
			CatalogoVO cat = getCatalogo(opciones);
			String json = toJson(cat);
			redirectJsonResponse(response, json);

		} catch (ServiceLocatorException e) {
			logger.error(e);
		} catch (IOException e) {
			logger.error(e);
		}

		return null;
	}

	public ActionForward cargarDominio(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		List<CatalogoOpcionVO> opciones = new ArrayList<CatalogoOpcionVO>();
		try {
			CatalogoOpcionVO vo = new CatalogoOpcionVO();
			//vo.setIdCatalogoOpcion(0);
			//vo.setOpcion("");
			opciones = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(CATALOGO_OPCION_DOMINIO, true);
			//opciones.add(0, vo);
			CatalogoVO cat = getCatalogo(opciones);
			String json = toJson(cat);
			redirectJsonResponse(response, json);

		} catch (ServiceLocatorException e) {
			logger.error(e);
		} catch (IOException e) {
			logger.error(e);
		}

		return null;
	}

	private long getidCandidato(HttpSession session) {
		/*
		 * TODO Este codigo es para un candidato, falta definir el caso de un
		 * Administrador del portal.
		 */
		UsuarioWebVO usuario = super.getUsuario(session);
		return usuario.getIdPropietario();
	}

	private void getOptionsSelectedLang(List<IdiomaVO> idiomasVO, HttpServletRequest request) {
		Iterator<IdiomaVO> it = idiomasVO.iterator();
		while (it.hasNext()) {
			IdiomaVO idioma = it.next();
			request.getSession().setAttribute("IDIOMAS_" + idioma.getIdCandidatoIdioma(),
					getSelectLang(idioma.getIdIdioma()));
		}
	}

	private void getOptionsSelectedLangCert(List<IdiomaVO> idiomasVO, HttpServletRequest request) {
		Iterator<IdiomaVO> it = idiomasVO.iterator();
		while (it.hasNext()) {
			IdiomaVO idioma = it.next();
			request.getSession().setAttribute("CERTIFICACIONES_" + idioma.getIdCandidatoIdioma(),
					getSelectLangCert(idioma.getIdIdioma(), idioma.getIdCertificacion()));
		}
	}

	private void getOptionsSelectedLangDom(List<IdiomaVO> idiomasVO, HttpServletRequest request) {
		Iterator<IdiomaVO> it = idiomasVO.iterator();
		while (it.hasNext()) {
			IdiomaVO idioma = it.next();
			request.getSession().setAttribute("DOMINIOS_" + idioma.getIdCandidatoIdioma(),
					getSelectLangDom(idioma.getIdDominio()));
		}
	}

	private String getSelectLang(long idIdioma) {
		StringBuilder langs = new StringBuilder();
		try {
			List<CatalogoOpcionVO> options = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(
					CATALOGO_OPCION_IDIOMAS, filtro_idioma, true);
			Iterator<CatalogoOpcionVO> it = options.iterator();
			while (it.hasNext()) {
				CatalogoOpcionVO vo = it.next();
				langs.append("<option ");
				if (vo.getIdCatalogoOpcion() == idIdioma)
					langs.append("selected ");
				langs.append("value=\"").append(vo.getIdCatalogoOpcion()).append("\">").append(vo.getOpcion())
						.append("</option>\n");
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return langs.toString();
	}

	private String getSelectLangCert(long idIdioma, long idCert) {
		StringBuilder langs = new StringBuilder("<option value=\"0\"></option>\n");
		try {
			long idCatDep = 0;
			List<CatalogoOpcionVO> options = new ArrayList<CatalogoOpcionVO>();
			options = CatalogoOpcionDelegateImpl.getInstance()
					.consultarCatalogo(CATALOGO_OPCION_IDIOMAS, filtro_idioma);
			for (CatalogoOpcionVO opcion : options) {
				if (opcion.getIdCatalogoOpcion() == idIdioma) {
					idCatDep = opcion.getIdCatalagoAsociado();
					break;
				}
			}
			List<CatalogoOpcionVO> certs = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(idCatDep);
			Iterator<CatalogoOpcionVO> it = certs.iterator();
			while (it.hasNext()) {
				CatalogoOpcionVO vo = it.next();
				langs.append("<option ");
				if (vo.getIdCatalogoOpcion() == idCert)
					langs.append("selected ");
				langs.append("value=\"").append(vo.getIdCatalogoOpcion()).append("\">").append(vo.getOpcion())
						.append("</option>\n");
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return langs.toString();
	}

	private String getSelectLangDom(long idDominio) {
		StringBuilder langs = new StringBuilder("<option value=\"0\"></option>\n");
		try {
			List<CatalogoOpcionVO> options = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(
					CATALOGO_OPCION_DOMINIO);
			Iterator<CatalogoOpcionVO> it = options.iterator();
			while (it.hasNext()) {
				CatalogoOpcionVO vo = it.next();
				langs.append("<option ");
				if (vo.getIdCatalogoOpcion() == idDominio)
					langs.append("selected ");
				langs.append("value=\"").append(vo.getIdCatalogoOpcion()).append("\">").append(vo.getOpcion())
						.append("</option>\n");
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return langs.toString();
	}

	public ActionForward idiomas(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			List<CatalogoOpcionVO> opciones = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(
					CATALOGO_OPCION_IDIOMAS, filtro_idioma, true);
			redirectJsonCatalogo(opciones, response);
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}

		return null;
	}

	public ActionForward certificaciones(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			long idCatDep = Utils.parseLong(request.getParameter("idCatDep"));

			List<CatalogoOpcionVO> opciones = CatalogoOpcionDelegateImpl.getInstance()
					.consultarCatalogo(idCatDep, true);
			redirectJsonCatalogo(opciones, response);
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}

		return null;
	}

	public ActionForward dominios(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			List<CatalogoOpcionVO> opciones = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(
					CATALOGO_OPCION_DOMINIO, true);
			redirectJsonCatalogo(opciones, response);
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}

		return null;
	}

	private void redirectJsonCatalogo(List<CatalogoOpcionVO> opciones, HttpServletResponse response) throws IOException {
		CatalogoVO cat = getCatalogo(opciones);
		String json = toJson(cat);
		redirectJsonResponse(response, json);
	}

}
