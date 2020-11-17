/**
 * 
 */
package mx.gob.stps.portal.web.address.action;

import static mx.gob.stps.portal.web.infra.utils.Constantes.BODY_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.DESCRIPCION_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FACEBOOK_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_TEMPLATE_FORM;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TITULO_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TWITTER_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.URL_ESPECIFICA;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.stps.portal.core.domicilio.vo.CodigoPostalVO;
import mx.gob.stps.portal.core.domicilio.vo.ColoniaVO;
import mx.gob.stps.portal.core.domicilio.vo.MunicipioVO;
import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.core.infra.utils.Utils;
import mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO;
import mx.gob.stps.portal.web.address.delegate.DomicilioBusDelegate;
import mx.gob.stps.portal.web.address.delegate.DomicilioBusDelegateImpl;
import mx.gob.stps.portal.web.address.form.DomicilioForm;
import mx.gob.stps.portal.web.infra.action.GenericAction;
import mx.gob.stps.portal.web.infra.delegate.CatalogoOpcionDelegate;
import mx.gob.stps.portal.web.infra.delegate.CatalogoOpcionDelegateImpl;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.infra.utils.PropertiesLoader;
import mx.gob.stps.portal.web.infra.vo.CatalogoVO;
import mx.gob.stps.portal.web.infra.vo.ResultVO;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Mario Alberto Vázquez Flores
 * @since 01/03/2011
 **/
public class DomicilioAction extends GenericAction {
	private static Logger logger = Logger.getLogger(DomicilioAction.class);

	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		
		DomicilioForm domicilio = (DomicilioForm) form;
		domicilio.reset(mapping, request);
		domicilio.setCalle("");
		domicilio.setNumeroExterior("");
		domicilio.setNumeroInterior("");
		domicilio.setEntreCalle("");
		domicilio.setyCalle("");

		PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
		request.getSession().setAttribute(TITULO_PAGINA, "Domicilio");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Reg&iacute;strate en nuestra Bolsa de trabajo y postula la oferta de empleo que m&aacute;s se apegue seg&uacute;n tu curriculum vitae sin costo f&aacute;cil y sin intermediarios.");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
		return mapping.findForward(FORWARD_TEMPLATE_FORM);
	}

	/**
	 * Obtiene Entidad Federativa usando codigo postal.
	 * 
	 * @param mapping
	 * @param form
	 * @param request, response
	 * @return null
	 * @throws IOException 
	 * @throws SQLException, ServiceLocatorException, IOException
	 */
	public ActionForward obtenerEntidadJSON(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException {
		DomicilioForm paramForm = (DomicilioForm) form;
		paramForm.reset(mapping, request);

		ResultVO msg;
		try {
			DomicilioBusDelegate services = DomicilioBusDelegateImpl.getInstance();

			// Obtiene idEntidad y idMunicipio segun el codigo postal
			CodigoPostalVO cpVO = services.obtenerEntidadMunicipioCp(paramForm.getCodigoPostal(), paramForm.getCpNuevo());

			// Obtiene Entidad por id
			List<CatalogoOpcionVO> opciones = services.obtenerEntidadPorId(Constantes.CATALOGO_OPCION_ENTIDAD_FEDERATIVA, cpVO.getIdEntidad());

			CatalogoVO cat = getCatalogo(opciones);
			String json = toJson(cat);
			redirectJsonResponse(response, json);
		} catch (ServiceLocatorException e) {
			logger.error(e);
		} catch (IOException e) {
			logger.error(e);
		} catch (SQLException e) {
			logger.error(e);
		} catch (BusinessException e) {
			logger.error(e);

			msg = new ResultVO(getMensaje(request, "dom.validacp.err"), ResultVO.TYPE_ERROR);
			paramForm.setMsg(msg);

			String json = toJson(msg);
			redirectJsonResponse(response, json);
		}

		return null;
	}

	/**
	 * Obtiene Municipios usando codigo postal.
	 * 
	 * @param mapping
	 * @param form
	 * @param request, response
	 * @return null
	 * @throws SQLException, ServiceLocatorException, IOException
	 */
	public ActionForward obtenerMunicipioJSON(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		DomicilioForm paramForm = (DomicilioForm) form;
		paramForm.reset(mapping, request);

		try {
			DomicilioBusDelegate services = DomicilioBusDelegateImpl.getInstance();

			// Obtiene idEntidad y idMunicipio segun el codigo postal
			CodigoPostalVO cpVO = services.obtenerEntidadMunicipioCp(paramForm.getCodigoPostal(), paramForm.getCpNuevo());

			// Obtiene municipios por id_entidad
			List<CatalogoOpcionVO> opciones = new ArrayList<CatalogoOpcionVO>();
			List<MunicipioVO> municipios = services.obtenerMunicipio(cpVO.getIdEntidad(), cpVO.getIdMunicipio());

			for (MunicipioVO elementoN : municipios) {
				CatalogoOpcionVO catMun = municipioCatOpcion(elementoN);
				opciones.add(catMun);
			}

			CatalogoVO cat = getCatalogo(opciones);
			String json = toJson(cat);
			redirectJsonResponse(response, json);

		} catch (ServiceLocatorException e) {
			logger.error(e);
		} catch (IOException e) {
			logger.error(e);
		} catch (SQLException e) {
			logger.error(e);
		} catch (BusinessException e) {
			logger.error(e);
		}

		return null;
	}

	/**
	 * Obtiene Colonias usando codigo postal.
	 * 
	 * @param mapping
	 * @param form
	 * @param request, response
	 * @return null
	 * @throws SQLException, ServiceLocatorException, IOException
	 */
	public ActionForward obtenerColoniaJSON(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		DomicilioForm paramForm = (DomicilioForm) form;
		paramForm.reset(mapping, request);

		try {
			// Obtiene colonias por idEntidad y idMunicipio
			List<CatalogoOpcionVO> opciones = new ArrayList<CatalogoOpcionVO>();

			DomicilioBusDelegate services = DomicilioBusDelegateImpl.getInstance();
			List<ColoniaVO> colonias = services.obtenerColoniasCp(paramForm.getCodigoPostal());

			for (ColoniaVO nElemento : colonias) {
				CatalogoOpcionVO catCol = coloniaCatOpcion(nElemento);
				opciones.add(catCol);
			}

			CatalogoVO cat = getCatalogo(opciones);
			String json = toJson(cat);
			redirectJsonResponse(response, json);

		} catch (ServiceLocatorException e) {
			logger.error(e);
		} catch (IOException e) {
			logger.error(e);
		} catch (SQLException e) {
			logger.error(e);
		}

		return null;
	}

	/**
	 * Obtiene Entidad Federativa usando AJAX.
	 * (Muestra todas las entidades)
	 * 
	 * @param mapping
	 * @param form
	 * @param request, response
	 * @return null
	 * @throws SQLException, ServiceLocatorException, IOException
	 */
	public ActionForward obtenerEntidad(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			CatalogoOpcionDelegate services = CatalogoOpcionDelegateImpl.getInstance();			

			long[] arrIds = new long[]{Constantes.CATALOGO_OPCION_ENTIDAD_FEDERATIVA_NACIDO_EXTRANJERO};
			List<CatalogoOpcionVO> opciones = services.consultarCatalogo(Constantes.CATALOGO_OPCION_ENTIDAD_FEDERATIVA, arrIds);

			CatalogoVO cat = getCatalogo(opciones);
			String json = toJson(cat);
			redirectJsonResponse(response, json);

		} catch (ServiceLocatorException e) {
			logger.error(e);
		} /*catch (BusinessException e) {
			logger.error(e);
		} */catch (IOException e) {
			logger.error(e);
		}

		return null;
	}

	/**
	 * Obtiene Municipios usando AJAX.
	 * (Requiere de un id entidad federativa)
	 * 
	 * @param mapping
	 * @param form
	 * @param request, response
	 * @return null
	 * @throws SQLException, ServiceLocatorException, IOException
	 */
	public ActionForward obtenerMunicipio(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		DomicilioForm paramForm = (DomicilioForm) form;
		paramForm.reset(mapping, request);

		try {
			List<CatalogoOpcionVO> opciones = null;
			
			if (paramForm.getIdEntidad()>0){
				DomicilioBusDelegate services = DomicilioBusDelegateImpl.getInstance();
				opciones = services.consultaMunicipios(paramForm.getIdEntidad());				
			} else {
				opciones = new ArrayList<CatalogoOpcionVO>();
			}

			/*for (MunicipioVO elementoN : municipios) {
				CatalogoOpcionVO cat = municipioCatOpcion(elementoN);
				opciones.add(cat);
			}*/

			CatalogoVO cat = getCatalogo(opciones);
			String json = toJson(cat);
			redirectJsonResponse(response, json);

		} catch (ServiceLocatorException e) {
			logger.error(e);
		} catch (SQLException e) {
			logger.error(e);
		} catch (IOException e) {
			logger.error(e);
		}

		return null;
	}

	/**
	 * Obtiene Colonias usando AJAX.
	 * (Requiere de un id entidad federativa
	 * y un id municipio)
	 * 
	 * @param mapping
	 * @param form
	 * @param request, response
	 * @return null
	 * @throws SQLException, ServiceLocatorException, IOException
	 */
	public ActionForward obtenerColonia(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		DomicilioForm paramForm = (DomicilioForm) form;
		paramForm.reset(mapping, request);

		try {
			DomicilioBusDelegate services = DomicilioBusDelegateImpl.getInstance();
			//List<ColoniaVO> colonias = services.obtenerColonias(paramForm.getIdEntidad(), paramForm.getIdMunicipio());
			List<CatalogoOpcionVO> opciones = services.consultaColonias(paramForm.getIdEntidad(), paramForm.getIdMunicipio());

			/*for (ColoniaVO nElemento : colonias) {
				CatalogoOpcionVO cat = coloniaCatOpcion(nElemento);
				opciones.add(cat);
			}*/

			CatalogoVO cat = getCatalogo(opciones);
			String json = toJson(cat);
			redirectJsonResponse(response, json);

		} catch (ServiceLocatorException e) {
			logger.error(e);
		} catch (SQLException e) {
			logger.error(e);
		} catch (IOException e) {
			logger.error(e);
		}

		return null;
	}

	/**
	 * Obtiene el codigo postal segun la relacion 
	 * Entidad-Municipio-Colonia)
	 * 
	 * @param mapping
	 * @param form
	 * @param request, response
	 * @return null
	 * @throws SQLException, ServiceLocatorException, IOException
	 */
	public ActionForward obtenerCodigoPostal(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		DomicilioForm paramForm = (DomicilioForm) form;
		paramForm.reset(mapping, request);

		try {
			DomicilioBusDelegate services = DomicilioBusDelegateImpl.getInstance();
			String codigoPostal = services.obtenerCodigoPostal(paramForm.getIdEntidad(), paramForm.getIdMunicipio(), paramForm.getIdColonia());
			
			paramForm.setCodigoPostal(codigoPostal);

			CodigoVO codVO = new CodigoVO();
			codVO.setCodigoPostal(paramForm.getCodigoPostal());

			String json = toJson(codVO);
			redirectJsonResponse(response, json);

		} catch (ServiceLocatorException e) {
			logger.error(e);
		} catch (SQLException e) {
			logger.error(e);
		} catch (IOException e) {
			logger.error(e);
		}

		return null;
	}
	
	/**
	 * Obtiene Localidades usando AJAX.
	 * (Requiere de un id entidad federativa
	 * y un id municipio)
	 * 
	 * @param mapping
	 * @param form
	 * @param request, response
	 * @return null
	 * @throws SQLException, ServiceLocatorException, IOException
	 */
	public ActionForward obtenerLocalidades(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			DomicilioBusDelegate services = DomicilioBusDelegateImpl.getInstance();
			List<CatalogoOpcionVO> opciones = services.consultaLocalidades(Utils.parseLong(request.getParameter("idEntidad")), Utils.parseLong(request.getParameter("idMunicipio")));
			CatalogoVO cat = getCatalogo(opciones);
			String json = toJson(cat);
			redirectJsonResponse(response, json);
		} catch (ServiceLocatorException e) {
			logger.error(e);
		} catch (SQLException e) {
			logger.error(e);
		} catch (IOException e) {
			logger.error(e);
		}
		return null;
	}
	
	private CatalogoOpcionVO municipioCatOpcion(MunicipioVO mun) {

		CatalogoOpcionVO cat = new CatalogoOpcionVO();

		cat.setIdCatalogo(mun.getIdEntidad());
		cat.setIdCatalogoOpcion(mun.getIdMunicipio());
		cat.setOpcion(mun.getMunicipio());
		cat.setFechaAlta(null);
		cat.setEstatus(0);
		cat.setFechaModificacion(null);

		return cat;
	}

	private CatalogoOpcionVO coloniaCatOpcion(ColoniaVO col) {

		CatalogoOpcionVO cat = new CatalogoOpcionVO();

		cat.setIdCatalogo(0);
		cat.setIdCatalogoOpcion(col.getIdColonia());
		cat.setOpcion(col.getDescColonia());
		cat.setFechaAlta(null);
		cat.setEstatus(0);
		cat.setFechaModificacion(null);

		return cat;
	}

	public class CodigoVO {

		String codigoPostal;

		/**
		 * @return the codigoPostal
		 */
		public String getCodigoPostal() {
			return codigoPostal;
		}

		/**
		 * @param codigoPostal
		 *            the codigoPostal to set
		 */
		public void setCodigoPostal(String codigoPostal) {
			this.codigoPostal = codigoPostal;
		}
	}
	
	
	public ActionForward obtenerCiudadCanada(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		DomicilioForm paramForm = (DomicilioForm) form;
		paramForm.reset(mapping, request);

		try {
			List<CatalogoOpcionVO> opciones = null;
			
			if (paramForm.getIdEntidad()>0){
				DomicilioBusDelegate services = DomicilioBusDelegateImpl.getInstance();
				opciones = services.consultaCiudadesCanada(paramForm.getIdEntidad());				
			} else {
				opciones = new ArrayList<CatalogoOpcionVO>();
			}

			/*for (MunicipioVO elementoN : municipios) {
				CatalogoOpcionVO cat = municipioCatOpcion(elementoN);
				opciones.add(cat);
			}*/

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

}
