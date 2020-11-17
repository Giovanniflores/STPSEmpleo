package mx.gob.stps.portal.web.oferta.action;

import static mx.gob.stps.portal.web.infra.utils.Constantes.BODY_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_NEXT;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_TEMPLATE_MI_ESP_EMP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.JSP_CARRERA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.JSP_COMPETENCIA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.JSP_CONOCIMIENTO;
import static mx.gob.stps.portal.web.infra.utils.Constantes.JSP_ENTIDAD;
import static mx.gob.stps.portal.web.infra.utils.Constantes.JSP_HABILIDAD;
import static mx.gob.stps.portal.web.infra.utils.Constantes.JSP_IDIOMA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.JSP_SECTOR;
import static mx.gob.stps.portal.web.infra.utils.Constantes.NEXT_COMPETENCIA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.NEXT_CONOCIMIENTO;
import static mx.gob.stps.portal.web.infra.utils.Constantes.NEXT_ENTIDAD;
import static mx.gob.stps.portal.web.infra.utils.Constantes.NEXT_HABILIDAD;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TITULO_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.DESCRIPCION_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FACEBOOK_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TWITTER_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.URL_ESPECIFICA;
import mx.gob.stps.portal.core.infra.utils.Constantes.CERTIFICACION;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.stps.portal.core.domicilio.vo.CodigoPostalVO;
import mx.gob.stps.portal.core.domicilio.vo.DomicilioVO;
import mx.gob.stps.portal.core.domicilio.vo.MunicipioVO;
import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.exception.TechnicalException;
import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO;
import mx.gob.stps.portal.core.oferta.registro.vo.OfertaCarreraEspecialidadVO;
import mx.gob.stps.portal.core.oferta.registro.vo.OfertaIdiomaVO;
import mx.gob.stps.portal.core.oferta.registro.vo.RegistroContactoVO;
import mx.gob.stps.portal.core.oferta.registro.vo.RegistroEntidadesVO;
import mx.gob.stps.portal.core.oferta.registro.vo.RegistroIdiomaVO;
import mx.gob.stps.portal.core.oferta.registro.vo.RegistroRequisitosVO;
import mx.gob.stps.portal.core.oferta.registro.vo.RegistroUbicacionVO;
import mx.gob.stps.portal.core.oferta.registro.vo.RequisitoVO;
import mx.gob.stps.portal.core.oferta.vo.OfertaEmpleoVO;
import mx.gob.stps.portal.core.search.exception.IndexerException;
import mx.gob.stps.portal.web.address.action.DomicilioAction;
import mx.gob.stps.portal.web.address.delegate.DomicilioBusDelegate;
import mx.gob.stps.portal.web.address.delegate.DomicilioBusDelegateImpl;
import mx.gob.stps.portal.web.infra.delegate.CatalogoOpcionDelegate;
import mx.gob.stps.portal.web.infra.delegate.CatalogoOpcionDelegateImpl;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.infra.utils.PropertiesLoader;
import mx.gob.stps.portal.web.infra.vo.CatalogoVO;
import mx.gob.stps.portal.web.oferta.delegate.OfertaBusDelegate;
import mx.gob.stps.portal.web.oferta.delegate.OfertaBusDelegateImpl;
import mx.gob.stps.portal.web.oferta.form.EntidadesForm;
import mx.gob.stps.portal.web.oferta.form.RegistroContactoForm;
import mx.gob.stps.portal.web.oferta.form.RegistroRequisitosForm;
import mx.gob.stps.portal.web.oferta.form.RegistroUbicacionForm;
import mx.gob.stps.portal.web.oferta.form.RequisitosForm;
import mx.gob.stps.portal.web.oferta.vo.ElementoListaVO;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

//TODO ELIMINAR CLASE, SE CAMBIO LA CLASE PARA LA ADMINISTRACION DE OFERTAS
public class OfertaPlantillaAction extends DomicilioAction {

	private static Logger logger = Logger.getLogger(OfertaPlantillaAction.class);

	@Override
	public ActionForward init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		// RegistroUbicacionForm forma = (RegistroUbicacionForm)form;
		// forma.reset(mapping, request);

		try {
			// request.getSession().invalidate();

			String idOferta = request.getParameter("id");
			request.getSession().setAttribute("idOferta", idOferta);

			request.getSession().removeAttribute("listaConocimientos");
			request.getSession().removeAttribute("listaHabilidades");
			request.getSession().removeAttribute("listaCompetencias");
			request.getSession().removeAttribute("listaIdiomas");
			request.getSession().removeAttribute("listaCarrerasSeleccionadas");
			request.getSession().removeAttribute("listaSectoresSeleccionados");
			request.getSession().removeAttribute("listaEntidadesSeleccionadas");
			request.getSession().removeAttribute("listaPrestaciones");
			request.getSession().removeAttribute("ofertaVO");

			OfertaBusDelegate services = OfertaBusDelegateImpl.getInstance();

			OfertaEmpleoVO ofertaVO = services.obtenerOferta(Long.parseLong(idOferta));
			
			if (ofertaVO.getMapaUbicacion() == null)
				ofertaVO.setMapaUbicacion("");
			if (ofertaVO.getMapaUbicacion() != null && ofertaVO.getMapaUbicacion().compareTo("null") == 0)
				ofertaVO.setMapaUbicacion("");

			request.getSession().setAttribute("ofertaVO", ofertaVO);

			ArrayList<String> sectores = services.obtenerSectoresOferta(ofertaVO.getIdOfertaEmpleo());
			request.getSession().setAttribute("listaSectoresSeleccionados",sectores);

			ArrayList<String> prestaciones = services.obtenerPrestacionesOferta(ofertaVO.getIdOfertaEmpleo());
			request.getSession().setAttribute("listaPrestacionesSeleccionadas",prestaciones);

			ArrayList<RegistroEntidadesVO> entidades = services.obtenerEntidadesOferta(ofertaVO.getIdOfertaEmpleo());
			request.getSession().setAttribute("listaEntidadesSeleccionadas",entidades);

			ArrayList<OfertaCarreraEspecialidadVO> carreras = services.obtenerCarrerasEspecialidades(ofertaVO.getIdOfertaEmpleo());
			request.getSession().setAttribute("listaCarrerasEspecialidadesOferta", carreras);

			ArrayList<RequisitoVO> requisitos = services.obetenerRequisitos(ofertaVO.getIdOfertaEmpleo());
			request.getSession().setAttribute("listaRequisitosOferta",requisitos);

			ArrayList<OfertaIdiomaVO> idiomas = services.obtenerIdiomas(ofertaVO.getIdOfertaEmpleo());
			request.getSession().setAttribute("listaIdiomasOferta", idiomas);

			DomicilioVO domicilio = (DomicilioVO) services.obtenerDomicilio(Long.parseLong(idOferta));
			request.getSession().setAttribute("domicilio", domicilio);

			CodigoPostalVO cp = services.obtenerCodigoPostal(domicilio.getIdColonia());
			request.getSession().setAttribute("cp", cp);

			List<CatalogoOpcionVO> catAreasLaborales = services.obtenerCatalogoAlf(Constantes.CATALOGO_OPCION_AREA_LABORAL);
			request.setAttribute("CATALOGO_OPCION_AREA_LABORAL",catAreasLaborales);
			
			/*List<CatalogoOpcionVO> catOcupacion = services.obtenerCatalogoAlf(Constantes.CATALOGO_OPCION_OCUPACION);
			request.setAttribute("CATALOGO_OPCION_OCUPACION", catOcupacion);*/
			request.setAttribute("CATALOGO_OPCION_SECTOR", services.obtenerCatalogoAlf(Constantes.CATALOGO_OPCION_SUBSECTOR));
			request.setAttribute("CATALOGO_OPCION_HORARIO_LABORAL",services.obtenerCatalogo(Constantes.CATALOGO_OPCION_HORARIO_LABORAL));
			request.setAttribute("CATALOGO_OPCION_HORARIO_CONTACTO",services.obtenerCatalogo(Constantes.CATALOGO_OPCION_HORARIO_CONTACTO));
			request.setAttribute("CATALOGO_OPCION_TIPO_CONTRATO", services.obtenerCatalogo(Constantes.CATALOGO_OPCION_TIPO_CONTRATO));
			request.setAttribute("CATALOGO_OPCION_PRESTACIONES", services.obtenerCatalogo(Constantes.CATALOGO_OPCION_PRESTACIONES));
			request.setAttribute("CATALOGO_OPCION_JERARQUIA_PUESTO",services.obtenerCatalogo(Constantes.CATALOGO_OPCION_JERARQUIA_PUESTO));
			request.setAttribute("CATALOGO_OPCION_CAUSA_OFERTA", services.obtenerCatalogo(Constantes.CATALOGO_OPCION_CAUSA_OFERTA));

			long[] arr  = new long[]{Constantes.CATALOGO_OPCION_ENTIDAD_FEDERATIVA_NACIDO_EXTRANJERO};

			request.setAttribute("CATALOGO_OPCION_ENTIDAD_FEDERATIVA", services.obtenerCatalogoExcluyente(Constantes.CATALOGO_OPCION_ENTIDAD_FEDERATIVA, arr));

			//List<MunicipioVO> catMunicipios = (List<MunicipioVO>)request.getSession().getAttribute("CatalogoMunicipios");

			request.setAttribute("CATALOGO_OPCION_TIPO_DISCAPACIDAD",services.obtenerCatalogo(Constantes.CATALOGO_OPCION_TIPO_DISCAPACIDAD));
			request.setAttribute("CATALOGO_OPCION_TIPO_EMPLEO", services.obtenerCatalogoAlf(Constantes.CATALOGO_OPCION_TIPO_EMPLEO));

		} catch (BusinessException e) {
			logger.error(e); // TODO Notificar error al usuario
		} catch (ServiceLocatorException e) {
			logger.error(e); // TODO Notificar error al usuario
		}

		ocultaBarraArticulos(request);
		request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
        PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Oferta empleo");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Oferta empleo, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo.");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
		return mapping.findForward(FORWARD_TEMPLATE_MI_ESP_EMP);
	}

	public ActionForward guardaRequisitosInit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		try {
			OfertaBusDelegate services = OfertaBusDelegateImpl.getInstance();
			request.setAttribute("CATALOGO_OPCION_GRADO_ESTUDIOS", services
					.obtenerCatalogo(Constantes.CATALOGO_OPCION_GRADO_ESTUDIOS));
			request.setAttribute(
					"CATALOGO_OPCION_CARRERA_ESPECIALIDAD",
					services.obtenerCatalogoAlf(Constantes.CATALOGO_OPCION_CARRERA_ESPECIALIDAD));
			request.setAttribute(
					"CATALOGO_OPCION_SITUACION_ACADEMICA",
					services.obtenerCatalogo(Constantes.CATALOGO_OPCION_SITUACION_ACADEMICA));
			request.setAttribute("CATALOGO_OPCION_IDIOMA",
					services.obtenerCatalogo(Constantes.CATALOGO_OPCION_IDIOMA));

			List<CatalogoOpcionVO> cat_experiencia = (List<CatalogoOpcionVO>) services
					.obtenerCatalogo(Constantes.CATALOGO_OPCION_EXPERIENCIA);
			List<CatalogoOpcionVO> cat_dominio = (List<CatalogoOpcionVO>) services
					.obtenerCatalogo(Constantes.CATALOGO_OPCION_DOMINIO);
			request.setAttribute("CATALOGO_OPCION_EXPERIENCIA", cat_experiencia);
			request.setAttribute("CATALOGO_OPCION_DOMINIO", cat_dominio);

			@SuppressWarnings("unchecked")
			List<RequisitoVO> listaRequisitosOferta = (List<RequisitoVO>) request
					.getSession().getAttribute("listaRequisitosOferta");
			List<RequisitoVO> listaConocimientosOferta = new ArrayList<RequisitoVO>();
			RequisitoVO conocimiento;
			request.getSession().removeAttribute("conocimientoPrincipal");
			int tipo_conocimiento = Constantes.CAT_TIPO_CONOCIMIENTO;
			for (int i = 0; i < listaRequisitosOferta.size(); i++) {
				if (listaRequisitosOferta.get(i).getTipo() == tipo_conocimiento) {
					conocimiento = listaRequisitosOferta.get(i);
					if (conocimiento.getPrincipal() == Constantes.MULTIREGISTRO.PRINCIPAL.getIdOpcion())
						request.getSession().setAttribute(
								"conocimientoPrincipal", conocimiento);
					else {

						for (CatalogoOpcionVO experiencia : cat_experiencia) {
							if (experiencia.getIdCatalogoOpcion() == conocimiento
									.getExperiencia())
								conocimiento.setExpDescripcion(experiencia
										.getOpcion());
						}
						for (CatalogoOpcionVO dominio : cat_dominio) {
							if (dominio.getIdCatalogoOpcion() == conocimiento
									.getDominio())
								conocimiento.setDomDescripcion(dominio
										.getOpcion());
						}

						listaConocimientosOferta.add(conocimiento);
					}

				}
			}
			request.getSession().setAttribute("listaConocimientos",
					listaConocimientosOferta);

			List<RequisitoVO> listaHabilidadesOferta = new ArrayList<RequisitoVO>();
			RequisitoVO habilidad;
			request.getSession().removeAttribute("habilidadPrincipal");
			int tipo_habilidad = Constantes.CAT_TIPO_HABILIDAD;
			for (int i = 0; i < listaRequisitosOferta.size(); i++) {
				if (listaRequisitosOferta.get(i).getTipo() == tipo_habilidad) {
					habilidad = listaRequisitosOferta.get(i);
					if (habilidad.getPrincipal() == Constantes.MULTIREGISTRO.PRINCIPAL.getIdOpcion())
						request.getSession().setAttribute("habilidadPrincipal",
								habilidad);
					else {

						for (CatalogoOpcionVO experiencia : cat_experiencia) {
							if (experiencia.getIdCatalogoOpcion() == habilidad
									.getExperiencia())
								habilidad.setExpDescripcion(experiencia
										.getOpcion());
						}
						for (CatalogoOpcionVO dominio : cat_dominio) {
							if (dominio.getIdCatalogoOpcion() == habilidad
									.getDominio())
								habilidad
										.setDomDescripcion(dominio.getOpcion());
						}

						listaHabilidadesOferta.add(habilidad);
					}

				}
			}
			request.getSession().setAttribute("listaHabilidades",
					listaHabilidadesOferta);

			List<RequisitoVO> listaCompetenciasOferta = new ArrayList<RequisitoVO>();
			RequisitoVO competencia;
			request.getSession().removeAttribute("competenciaPrincipal");
			int tipo_competencia = Constantes.CAT_TIPO_COMPETENCIA;
			for (int i = 0; i < listaRequisitosOferta.size(); i++) {
				if (listaRequisitosOferta.get(i).getTipo() == tipo_competencia) {
					competencia = listaRequisitosOferta.get(i);
					if (competencia.getPrincipal() == Constantes.MULTIREGISTRO.PRINCIPAL.getIdOpcion())
						request.getSession().setAttribute(
								"competenciaPrincipal", competencia);
					else {

						for (CatalogoOpcionVO experiencia : cat_experiencia) {
							if (experiencia.getIdCatalogoOpcion() == competencia
									.getExperiencia())
								competencia.setExpDescripcion(experiencia
										.getOpcion());
						}
						for (CatalogoOpcionVO dominio : cat_dominio) {
							if (dominio.getIdCatalogoOpcion() == competencia
									.getDominio())
								competencia.setDomDescripcion(dominio
										.getOpcion());
						}

						listaCompetenciasOferta.add(competencia);
					}

				}
			}
			request.getSession().setAttribute("listaCompetencias",
					listaCompetenciasOferta);

		} catch (BusinessException e) {
			logger.error(e); // TODO Notificar error al usuario
		} catch (ServiceLocatorException e) {
			logger.error(e); // TODO Notificar error al usuario
		}

		ocultaBarraArticulos(request);
		request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
        PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Oferta empleo");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Oferta empleo, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo.");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
		return mapping.findForward(FORWARD_TEMPLATE_MI_ESP_EMP);
	}

	public ActionForward guardaContactoInit(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			OfertaBusDelegate services = OfertaBusDelegateImpl.getInstance();
			request.setAttribute(
					"CATALOGO_OPCION_EMPRESA_OFRECE_EMPLEO",
					services.obtenerCatalogo(Constantes.CATALOGO_OPCION_EMPRESA_OFRECE_EMPLEO));
			request.setAttribute(
					"CATALOGO_OPCION_NOMBRE_CONTACTO_EMPRESA",
					services.obtenerCatalogo(Constantes.CATALOGO_OPCION_NOMBRE_CONTACTO_EMPRESA));
			request.setAttribute("CATALOGO_OPCION_MEDIO_CONTACTO", services
					.obtenerCatalogo(Constantes.CATALOGO_OPCION_MEDIO_CONTACTO));
			request.setAttribute(
					"CATALOGO_OPCION_DURACION_APROXIMADA",
					services.obtenerCatalogo(Constantes.CATALOGO_OPCION_DURACION_APROXIMADA));
			request.setAttribute(
					"CATALOGO_OPCION_HORARIO_CONTACTO",
					services.obtenerCatalogo(Constantes.CATALOGO_OPCION_HORARIO_CONTACTO));

			long idEmpresa = Long.parseLong(request.getSession().getAttribute("idEmpresa").toString());
			//request.setAttribute("tercerasEmpresas",services.obtenerTercerasEmpresas(idEmpresa));
			//request.setAttribute("Empresa", services.obtenerEmpresas(idEmpresa));

			//request.setAttribute("contactos",services.obtenerContactos(idEmpresa));

		} catch (BusinessException e) {
			logger.error(e); // TODO Notificar error al usuario
		} catch (ServiceLocatorException e) {
			logger.error(e); // TODO Notificar error al usuario
		}

		ocultaBarraArticulos(request);
		request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
        PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Oferta empleo");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Oferta empleo, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo.");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
		return mapping.findForward(FORWARD_TEMPLATE_MI_ESP_EMP);
	}

	public ActionForward conocimientoInit(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			OfertaBusDelegate services = OfertaBusDelegateImpl.getInstance();
			request.getSession()
					.setAttribute(
							"CATALOGO_OPCION_EXPERIENCIA",
							services.obtenerCatalogo(Constantes.CATALOGO_OPCION_EXPERIENCIA));
			request.getSession()
					.setAttribute(
							"CATALOGO_OPCION_DOMINIO",
							services.obtenerCatalogo(Constantes.CATALOGO_OPCION_DOMINIO));
		} catch (BusinessException e) {
			logger.error(e); // TODO Notificar error al usuario
		} catch (ServiceLocatorException e) {
			logger.error(e); // TODO Notificar error al usuario
		}

		request.getSession().setAttribute(BODY_JSP,
				mapping.findForward(JSP_CONOCIMIENTO).getPath());
		return mapping.findForward(JSP_CONOCIMIENTO);
	}

	public ActionForward habilidadInit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			OfertaBusDelegate services = OfertaBusDelegateImpl.getInstance();
			request.getSession()
					.setAttribute(
							"CATALOGO_OPCION_EXPERIENCIA",
							services.obtenerCatalogo(Constantes.CATALOGO_OPCION_EXPERIENCIA));
			request.getSession()
					.setAttribute(
							"CATALOGO_OPCION_DOMINIO",
							services.obtenerCatalogo(Constantes.CATALOGO_OPCION_DOMINIO));
		} catch (BusinessException e) {
			logger.error(e); // TODO Notificar error al usuario
		} catch (ServiceLocatorException e) {
			logger.error(e); // TODO Notificar error al usuario
		}
		request.getSession().setAttribute(BODY_JSP,
				mapping.findForward(JSP_CONOCIMIENTO).getPath());
		return mapping.findForward(JSP_HABILIDAD);
	}

	public ActionForward competenciaInit(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			OfertaBusDelegate services = OfertaBusDelegateImpl.getInstance();
			request.getSession()
					.setAttribute(
							"CATALOGO_OPCION_EXPERIENCIA",
							services.obtenerCatalogo(Constantes.CATALOGO_OPCION_EXPERIENCIA));
			request.getSession()
					.setAttribute(
							"CATALOGO_OPCION_DOMINIO",
							services.obtenerCatalogo(Constantes.CATALOGO_OPCION_DOMINIO));
		} catch (BusinessException e) {
			logger.error(e); // TODO Notificar error al usuario
		} catch (ServiceLocatorException e) {
			logger.error(e); // TODO Notificar error al usuario
		}
		request.getSession().setAttribute(BODY_JSP,
				mapping.findForward(JSP_CONOCIMIENTO).getPath());
		return mapping.findForward(JSP_COMPETENCIA);
	}

	public ActionForward idiomaInit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		@SuppressWarnings("unchecked")
		ArrayList<OfertaIdiomaVO> idiomas = (ArrayList<OfertaIdiomaVO>) request
				.getSession().getAttribute("listaIdiomasOferta");
		ArrayList<RegistroIdiomaVO> idiomasRegistro = new ArrayList<RegistroIdiomaVO>();
		RegistroIdiomaVO item;
		List<CatalogoOpcionVO> listaDominio = null;
		List<CatalogoOpcionVO> listaIdiomas = null;

		/*List<CatalogoOpcionVO> listaCertChino = (List<CatalogoOpcionVO>) request
				.getSession().getAttribute("CATALOGO_CERTIFICACION_CHINO");*/
		/*List<CatalogoOpcionVO> listaCertIngles = (List<CatalogoOpcionVO>) request
				.getSession().getAttribute("CATALOGO_CERTIFICACION_INGLES");*/
		/*List<CatalogoOpcionVO> listaCertFrances = (List<CatalogoOpcionVO>) request
				.getSession().getAttribute("CATALOGO_CERTIFICACION_FRANCES");*/
		/*List<CatalogoOpcionVO> listaCertItaliano = (List<CatalogoOpcionVO>) request
				.getSession().getAttribute("CATALOGO_CERTIFICACION_ITALIANO");*/
		/*List<CatalogoOpcionVO> listaCertJapones = (List<CatalogoOpcionVO>) request
				.getSession().getAttribute("CATALOGO_CERTIFICACION_JAPONES");*/
		/*List<CatalogoOpcionVO> listaCertAleman = (List<CatalogoOpcionVO>) request
				.getSession().getAttribute("CATALOGO_CERTIFICACION_ALEMAN");*/

		String iid=request.getParameter("iid");
		if(iid!=null){
			request.getSession().setAttribute("iid", iid);
		}
		try {

			OfertaBusDelegate services = OfertaBusDelegateImpl.getInstance();

			listaDominio = (List<CatalogoOpcionVO>) services
					.obtenerCatalogo(Constantes.CATALOGO_OPCION_DOMINIO);
			listaIdiomas = (List<CatalogoOpcionVO>) services
					.obtenerCatalogo(Constantes.CATALOGO_OPCION_IDIOMA);

			request.getSession().setAttribute("CATALOGO_OPCION_DOMINIO",
					listaDominio);
			request.getSession().setAttribute("CATALOGO_OPCION_IDIOMA",
					listaIdiomas);

		} catch (BusinessException e) {
			logger.error(e); // TODO Notificar error al usuario
		} catch (ServiceLocatorException e) {
			logger.error(e); // TODO Notificar error al usuario
		}

		CatalogoOpcionDelegate servicesCatalogos = CatalogoOpcionDelegateImpl.getInstance();
		
		for (OfertaIdiomaVO idioma : idiomas) {
			item = new RegistroIdiomaVO();
			item.setCertificacion(idioma.getIdCertificacion());
			item.setDominio(idioma.getIdDominio());
			item.setIdioma(idioma.getIdIdioma());
			item.setPrincipal(idioma.getPrincipal());

			for (CatalogoOpcionVO idCat : listaIdiomas) {
				if (idCat.getIdCatalogoOpcion() == idioma.getIdIdioma()) {
					item.setIdiomaDescripcion(idCat.getOpcion());
				}
			}

			for (CatalogoOpcionVO idDom : listaDominio) {
				if (idDom.getIdCatalogoOpcion() == idioma.getIdDominio()) {
					item.setDominioDescripcion(idDom.getOpcion());
				}

			}

			if (item.getIdioma() == Constantes.IDIOMAS.ALEMAN.getIdOpcion()) {
				try{									
					CatalogoOpcionVO vo = servicesCatalogos.findById(CERTIFICACION.ALEMAN.getIdOpcion(), item.getCertificacion());
					if (vo != null)
						item.setCertificacionDescripcion(vo.getOpcion());
				} catch(ServiceLocatorException e){
					logger.error("Error al invocar servicesCatalogos.findById para el idioma "+item.getIdioma());
					e.printStackTrace();
				}
			}

			if (item.getIdioma() == Constantes.IDIOMAS.CHINO.getIdOpcion()) {
				try{									
					CatalogoOpcionVO vo = servicesCatalogos.findById(CERTIFICACION.CHINO.getIdOpcion(), item.getCertificacion());
					if (vo != null)
						item.setCertificacionDescripcion(vo.getOpcion());
				} catch(ServiceLocatorException e){
					logger.error("Error al invocar servicesCatalogos.findById para el idioma "+item.getIdioma());
					e.printStackTrace();
				}
			}

			if (item.getIdioma() == Constantes.IDIOMAS.INGLES.getIdOpcion()) {
				try{									
					CatalogoOpcionVO vo = servicesCatalogos.findById(CERTIFICACION.INGLES.getIdOpcion(), item.getCertificacion());
					if (vo != null)
						item.setCertificacionDescripcion(vo.getOpcion());
				} catch(ServiceLocatorException e){
					logger.error("Error al invocar servicesCatalogos.findById para el idioma "+item.getIdioma());
					e.printStackTrace();
				}
			}

			if (item.getIdioma() == Constantes.IDIOMAS.JAPONES.getIdOpcion()) {
				try{									
					CatalogoOpcionVO vo = servicesCatalogos.findById(CERTIFICACION.JAPONES.getIdOpcion(), item.getCertificacion());
					if (vo != null)
						item.setCertificacionDescripcion(vo.getOpcion());
				} catch(ServiceLocatorException e){
					logger.error("Error al invocar servicesCatalogos.findById para el idioma "+item.getIdioma());
					e.printStackTrace();
				}
			}
			
			if (item.getIdioma() == Constantes.IDIOMAS.FRANCES.getIdOpcion()) {
				try{									
					CatalogoOpcionVO vo = servicesCatalogos.findById(CERTIFICACION.FRANCES.getIdOpcion(), item.getCertificacion());
					if (vo != null)
						item.setCertificacionDescripcion(vo.getOpcion());
				} catch(ServiceLocatorException e){
					logger.error("Error al invocar servicesCatalogos.findById para el idioma "+item.getIdioma());
					e.printStackTrace();
				}
			}

			if (item.getIdioma() == Constantes.IDIOMAS.ITALIANO.getIdOpcion()) {
				try{									
					CatalogoOpcionVO vo = servicesCatalogos.findById(CERTIFICACION.ITALIANO.getIdOpcion(), item.getCertificacion());
					if (vo != null)
						item.setCertificacionDescripcion(vo.getOpcion());
				} catch(ServiceLocatorException e){
					logger.error("Error al invocar servicesCatalogos.findById para el idioma "+item.getIdioma());
					e.printStackTrace();
				}
			}

			idiomasRegistro.add(item);
		}
		if(request.getSession().getAttribute("listaIdiomas")==null)
		request.getSession().setAttribute("listaIdiomas", idiomasRegistro);
		// RegistroIdiomaVO

		request.getSession().setAttribute(BODY_JSP,
				mapping.findForward(JSP_CONOCIMIENTO).getPath());
		return mapping.findForward(JSP_IDIOMA);
	}


	
	public ActionForward sectoresInit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		List<CatalogoOpcionVO> catSectores=null;
		
		try {
			
			OfertaBusDelegate services = OfertaBusDelegateImpl.getInstance();
			String sid=request.getParameter("sid");
			if(sid!=null) request.getSession().setAttribute("sid", sid);
			else sid=(String)request.getSession().getAttribute("sid");
			
			if(sid!=null){
				long[] arr=new long[1];
				arr[0]=Long.parseLong(sid);
				catSectores=(List<CatalogoOpcionVO>) services
				.obtenerCatalogoExcluyente(Constantes.CATALOGO_OPCION_SUBSECTOR, arr);
				}
			else
				catSectores = (List<CatalogoOpcionVO>) services
					.obtenerCatalogoAlf(Constantes.CATALOGO_OPCION_SUBSECTOR);
            
			
			
			ArrayList<ElementoListaVO> sectoresVO = new ArrayList<ElementoListaVO>();
			@SuppressWarnings("unchecked")
			ArrayList<String> listaSectoresSeleccionados = (ArrayList<String>) request
					.getSession().getAttribute("listaSectoresSeleccionados");
			
			if (listaSectoresSeleccionados == null) {
				listaSectoresSeleccionados=new ArrayList<String>();
			}
			
				ElementoListaVO sector;
				for (int i = 0; i < catSectores.size(); i++) {
					sector = new ElementoListaVO();
					sector.setId(catSectores.get(i).getIdCatalogoOpcion()
							+ "");
					sector.setDescripcion(catSectores.get(i).getOpcion() + "");
					sectoresVO.add(sector);

					for (int j = 0; j < listaSectoresSeleccionados.size(); j++) {
						if (sector.getId().compareTo(
								listaSectoresSeleccionados.get(j)) == 0) {
							sector.setSeleccionada("true");
						}

					}

				}

				request.getSession().setAttribute("listaSectoresVO", sectoresVO);
			//}

			
		} catch (ServiceLocatorException e) {
			logger.error(e); // TODO Notificar error al usuario
		}

		request.getSession().setAttribute(BODY_JSP,
				mapping.findForward(JSP_SECTOR).getPath());
		return mapping.findForward(JSP_SECTOR);
	}
	
	public ActionForward entidadesInit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		OfertaBusDelegate services = OfertaBusDelegateImpl.getInstance();

		
		String ident=request.getParameter("ident"); 
		String idmun=request.getParameter("idmun");
		
		if(ident!=null && idmun!=null){
		request.getSession().removeAttribute("ident");
		request.getSession().removeAttribute("idmun");
		request.getSession().setAttribute("ident", ident);
		request.getSession().setAttribute("idmun", idmun);
		}
		
		try {
			long[] arr = new long[1];
			arr[0] = 33;
			request.setAttribute("CATALOGO_OPCION_ENTIDAD_FEDERATIVA", services
					.obtenerCatalogoExcluyente(
							Constantes.CATALOGO_OPCION_ENTIDAD_FEDERATIVA, arr));

			@SuppressWarnings("unchecked")
			List<CatalogoOpcionVO> catEntidades = (List<CatalogoOpcionVO>) request
					.getSession().getAttribute(
							"CATALOGO_OPCION_ENTIDAD_FEDERATIVA");

			@SuppressWarnings("unchecked")
			ArrayList<RegistroEntidadesVO> listaEntidades = (ArrayList<RegistroEntidadesVO>) (request
					.getSession().getAttribute("listaEntidadesSeleccionadas") != null ? request
					.getSession().getAttribute("listaEntidadesSeleccionadas")
					: new ArrayList<RegistroEntidadesVO>());

			//@SuppressWarnings("unchecked")
			List<MunicipioVO> catMunicipios = (List<MunicipioVO>) request.getSession().getAttribute("CatalogoMunicipios");

			DomicilioBusDelegate serviceDomicilio = DomicilioBusDelegateImpl.getInstance();			
			RegistroEntidadesVO ent;
			for (int h = 0; h < listaEntidades.size(); h++) {
				ent = listaEntidades.get(h);
				try {
					// para completar la información de las entidades seleccionadas con la descripción de éstas
					RegistroEntidadesVO entidadMunicipio = serviceDomicilio.obtieneEntidadMunicipio(ent.getEntidad(), ent.getMunicipio());
					if (entidadMunicipio != null){
						ent.setEntidadDescripcion(entidadMunicipio.getEntidadDescripcion());					
						ent.setMunicipioDescripcion(entidadMunicipio.getMunicipioDescripcion());						
					}
				} catch (ServiceLocatorException e) {
					logger.error("Error al invocar serviceDomicilio.obtieneEntidadMunicipio");
					e.printStackTrace();					
				} catch (SQLException e) {
					logger.error("Error al invocar serviceDomicilio.obtieneEntidadMunicipio");
					e.printStackTrace();
				}
				//
				/*for (int j = 0; j < catEntidades.size(); j++) {
					if (catEntidades.get(j).getIdCatalogoOpcion() == ent
							.getEntidad()) {
						ent.setEntidadDescripcion(catEntidades.get(j)
								.getOpcion());
					}
				}

				for (int j = 0; j < catMunicipios.size(); j++) {
					if (catMunicipios.get(j).getIdMunicipio() == ent
							.getMunicipio()
							&& catMunicipios.get(j).getIdEntidad() == ent
									.getEntidad()) {
						ent.setMunicipioDescripcion(catMunicipios.get(j)
								.getMunicipio());
					}
				}*/
				//
			}

			request.getSession().removeAttribute("listaEntidadesSeleccionadas");
			request.getSession().setAttribute("listaEntidadesSeleccionadas",
					listaEntidades);

		} catch (ServiceLocatorException e) {
			logger.error(e); // TODO Notificar error al usuario
		}
		request.getSession().setAttribute(BODY_JSP,
				mapping.findForward(JSP_ENTIDAD).getPath());
		return mapping.findForward(JSP_ENTIDAD);
	}

	@SuppressWarnings("unchecked")
	public ActionForward registrarEntidadMunicipio(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		long ident=Long.parseLong((String)request.getSession().getAttribute("ident"));
		long idmun=Long.parseLong((String)request.getSession().getAttribute("idmun"));

		ArrayList<RegistroEntidadesVO> listaEntidades = null;
		List<CatalogoOpcionVO> catEntidades = (List<CatalogoOpcionVO>) request.getSession().getAttribute("CATALOGO_OPCION_ENTIDAD_FEDERATIVA");
		List<MunicipioVO> catMunicipios = (List<MunicipioVO>) request.getSession().getAttribute("CatalogoMunicipios");

		RegistroEntidadesVO item;
		listaEntidades = (ArrayList<RegistroEntidadesVO>) (request.getSession()
				.getAttribute("listaEntidadesSeleccionadas") != null ? request
				.getSession().getAttribute("listaEntidadesSeleccionadas")
				: new ArrayList<RegistroEntidadesVO>());

		EntidadesForm ent = (EntidadesForm) form;

		String entidad = ent.getEntidad();
		String municipio = ent.getMunicipio();

		String parametro = null;

		int i = 0;
		while (true) {
			parametro = request.getParameter("elim" + (i + 1));
			if (parametro != null) {
				listaEntidades.remove(i);
			}
			i++;
			if (i >= listaEntidades.size())
				break;
		}

		if (entidad != null)
			if (entidad.compareTo("-1") != 0 && municipio.compareTo("-1") != 0 && noRepetir(entidad,municipio,listaEntidades,ident,idmun)) {
				item = new RegistroEntidadesVO();
				item.setEntidad(Long.parseLong(entidad));
				item.setMunicipio(Long.parseLong(municipio));
				DomicilioBusDelegate serviceDomicilio = DomicilioBusDelegateImpl.getInstance();	
				try {
					// para completar la información de las entidades seleccionadas con la descripción de éstas
					RegistroEntidadesVO entidadMunicipio = serviceDomicilio.obtieneEntidadMunicipio(item.getEntidad(), item.getMunicipio());
					if (entidadMunicipio != null){
						item.setEntidadDescripcion(entidadMunicipio.getEntidadDescripcion());					
						item.setMunicipioDescripcion(entidadMunicipio.getMunicipioDescripcion());						
					}
				} catch (ServiceLocatorException e) {
					logger.error("Error al invocar serviceDomicilio.obtieneEntidadMunicipio");
					e.printStackTrace();					
				} catch (SQLException e) {
					logger.error("Error al invocar serviceDomicilio.obtieneEntidadMunicipio");
					e.printStackTrace();
				}
				//
				/*for (int j = 0; j < catEntidades.size(); j++) {
					if (catEntidades.get(j).getIdCatalogoOpcion() == Long
							.parseLong(ent.getEntidad())) {
						item.setEntidadDescripcion(catEntidades.get(j)
								.getOpcion());
					}
				}
				for (int j = 0; j < catMunicipios.size(); j++) {
					if (catMunicipios.get(j).getIdMunicipio() == Long
							.parseLong(ent.getMunicipio())
							&& catMunicipios.get(j).getIdEntidad() == Long
									.parseLong(ent.getEntidad())) {
						item.setMunicipioDescripcion(catMunicipios.get(j)
								.getMunicipio());
					}

				}*/
				//
				// item.setEntidad(entidad);

				listaEntidades.add(item);
				request.getSession().removeAttribute(
						"listaEntidadesSeleccionadas");
				request.getSession().setAttribute(
						"listaEntidadesSeleccionadas", listaEntidades);

			}

		return mapping.findForward(NEXT_ENTIDAD);
	}

	
	private boolean  noRepetir(String entidad,String municipio,ArrayList<RegistroEntidadesVO> listaEntidades,long ident, long idmun ){
		
		long entLong= Long.parseLong(entidad);
		long munLong =Long.parseLong(municipio);
		
		
		for(RegistroEntidadesVO ent: listaEntidades){
			
			if(ent.getEntidad()==entLong && ent.getMunicipio()==munLong)
			   return false;
			
			if(entLong==ident && munLong==idmun)
				   return false;
				
			
		}
		
		return true;
	}

	public ActionForward registrarSectores(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws ClassNotFoundException {

		ArrayList<String> listaSectoresSeleccionados = new ArrayList<String>();
		
		/*if(listaSectoresSeleccionados==null)
		listaSectoresSeleccionados = new ArrayList<String>();
		*/
		ElementoListaVO hm;
		//request.getSession().removeAttribute("listaSectoresSeleccionados");
		@SuppressWarnings("unchecked")
		ArrayList<ElementoListaVO> listaSectoresVO = (ArrayList<ElementoListaVO>) request
				.getSession().getAttribute("listaSectoresVO");
		String parametro;
		int i = 0;
		while (true) {
			parametro = request.getParameter("c" + i );
			if (parametro != null) {
				hm = listaSectoresVO.get(i);
				hm.setSeleccionada("true");
				if (hm.getId().compareTo("-1") != 0)
					listaSectoresSeleccionados.add(hm.getId());
			} else {
				hm = listaSectoresVO.get(i);
				hm.setSeleccionada(null);
			}
			i++;
			if (i >= listaSectoresVO.size())
				break;
		}
		request.getSession().removeAttribute("listaSectoresSeleccionados");
		request.getSession().setAttribute("listaSectoresSeleccionados",
				listaSectoresSeleccionados);
		request.getSession().setAttribute("listaSectoresVO", listaSectoresVO);
		return mapping.findForward(JSP_SECTOR);
	}
	

	private ArrayList<ElementoListaVO> obtenerCarrerasSimilares(
			long estudios, long carreraSel) {

		ArrayList<ElementoListaVO> carreras = new ArrayList<ElementoListaVO>();
		ElementoListaVO elemento = new ElementoListaVO();
        List<CatalogoOpcionVO> opciones=null;
        
        if(estudios==0) return null;
        
		try {

			OfertaBusDelegate services = OfertaBusDelegateImpl.getInstance();
			
			if(estudios==Constantes.GRADO_ESTUDIOS.CARRERA_COMERCIAL.getIdOpcion())
			{
				opciones = services
				.obtenerCatalogoAlf((long)Constantes.CATALOGO.TECNICA_COMERCIAL_BASICA.getIdCatalogo());
		
			}

			if(estudios==Constantes.GRADO_ESTUDIOS.CARRERA_TECNICA.getIdOpcion())
			{
				opciones = services
				.obtenerCatalogoAlf((long)Constantes.CATALOGO.TECNICA_COMERCIAL_BASICA.getIdCatalogo());
		
			}

			if(estudios==Constantes.GRADO_ESTUDIOS.DOCTORADO.getIdOpcion())
			{
				opciones = services
				.obtenerCatalogoAlf((long)Constantes.CATALOGO.MAESTRIA_DOCTORADO.getIdCatalogo());
		
			}

			if(estudios==Constantes.GRADO_ESTUDIOS.LICENCIATURA.getIdOpcion())
			{
				opciones = services
				.obtenerCatalogoAlf((long)Constantes.CATALOGO.PROFESIONAL.getIdCatalogo());
		
			}
			
			
			if(estudios==Constantes.GRADO_ESTUDIOS.MAESTRIA.getIdOpcion())
			{
				opciones = services
				.obtenerCatalogoAlf((long)Constantes.CATALOGO.MAESTRIA_DOCTORADO.getIdCatalogo());
		
			}
			
			if(estudios==Constantes.GRADO_ESTUDIOS.PROFESIONAL_TECNICO.getIdOpcion())
			{
				opciones = services
				.obtenerCatalogoAlf((long)Constantes.CATALOGO.TECNICA_COMERCIAL_SUPERIOR.getIdCatalogo());
		
			}
			
			if(estudios==Constantes.GRADO_ESTUDIOS.SUPERIOR_UNIVERSITARIO.getIdOpcion())
			{
				opciones = services
				.obtenerCatalogoAlf((long)Constantes.CATALOGO.PROFESIONAL.getIdCatalogo());
		
			}
			
			if(estudios==Constantes.GRADO_ESTUDIOS.SIN_INSTRUCCION.getIdOpcion())
			{
				opciones = null;
		
			}
			
			if(estudios==Constantes.GRADO_ESTUDIOS.SECUNDARIA.getIdOpcion())
			{
				opciones = null;
		
			}
			if(estudios==Constantes.GRADO_ESTUDIOS.PREPA_VOCACIONAL.getIdOpcion())
			{
				opciones = services
				.obtenerCatalogoAlf((long)Constantes.CATALOGO.TECNICA_COMERCIAL_SUPERIOR.getIdCatalogo());
		
			}
			
			if(estudios==Constantes.GRADO_ESTUDIOS.PRIMARIA.getIdOpcion())
			{
				opciones = null;
		
			}
			
			if(estudios==Constantes.GRADO_ESTUDIOS.LEER_ESCRIBIR.getIdOpcion())
			{
				opciones=null;
		
			}
   
			if(opciones==null)
			{
				opciones=new ArrayList<CatalogoOpcionVO>();
				CatalogoOpcionVO op=new CatalogoOpcionVO();
				op.setIdCatalogoOpcion(1);
				op.setOpcion("Ninguna");
				opciones.add(op);
				
			}
			
			
			if(opciones!= null)
			for(CatalogoOpcionVO opcion:opciones){
				elemento=new ElementoListaVO();
				elemento.setId(opcion.getIdCatalogoOpcion()+"");
				elemento.setDescripcion(opcion.getOpcion());
				
				if(carreraSel!=opcion.getIdCatalogoOpcion())
				carreras.add(elemento);
				
			}
					

		} catch (ServiceLocatorException e) {
			logger.error(e);
		} 

		return carreras;

	}

	

	public ActionForward editarUbicacion(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		// logger.info("Se registra el tiempo para el lapso de asignacion : "+
		// paramform.getTiempoReasignacion());

		// int minutos = paramform.getTiempoReasignacion();
		long idOferta = Long.parseLong(request.getSession()
				.getAttribute("idOferta").toString());
		try {
			OfertaBusDelegate services = OfertaBusDelegateImpl.getInstance();
			long idEmpresa = Long.parseLong(request.getSession()
					.getAttribute("idEmpresa").toString());
			@SuppressWarnings("unchecked")
			ArrayList<RegistroEntidadesVO> listaEntidadesSeleccionadas = (ArrayList<RegistroEntidadesVO>) request
					.getSession().getAttribute("listaEntidadesSeleccionadas");
			@SuppressWarnings("unchecked")
			ArrayList<String> listaSectoresSeleccionados = (ArrayList<String>) request
					.getSession().getAttribute("listaSectoresSeleccionados");
			RegistroUbicacionForm f = (RegistroUbicacionForm) form;

			if (listaEntidadesSeleccionadas == null
					&& f.getPrefiereCandidatosEntidad() != null) {
				ArrayList<RegistroEntidadesVO> listaEnt = new ArrayList<RegistroEntidadesVO>();
				RegistroEntidadesVO entidad = new RegistroEntidadesVO();
				entidad.setEntidad(Long.parseLong(f
						.getPrefiereCandidatosEntidad()));
				entidad.setMunicipio(Long.parseLong(f
						.getPrefiereCandidatosMunicipio()));
				listaEnt.add(entidad);
				request.getSession().setAttribute(
						"listaEntidadesSeleccionadas", listaEnt);
			}

			if (listaEntidadesSeleccionadas != null
					&& f.getPrefiereCandidatosEntidad() != null) {
				RegistroEntidadesVO entidad = new RegistroEntidadesVO();
				entidad.setEntidad(Long.parseLong(f
						.getPrefiereCandidatosEntidad()));
				entidad.setMunicipio(Long.parseLong(f
						.getPrefiereCandidatosMunicipio()));
				listaEntidadesSeleccionadas.add(entidad);
				request.getSession().setAttribute(
						"listaEntidadesSeleccionadas",
						listaEntidadesSeleccionadas);
			}

			if (listaSectoresSeleccionados == null && f.getSector() != null) {
				ArrayList<String> listaSectores = new ArrayList<String>();
				String sector = f.getSector();
				listaSectores.add(sector);
				request.getSession().setAttribute("listaSectoresSeleccionados",
						listaSectores);
			}

			if (listaSectoresSeleccionados != null && f.getSector() != null) {
				String sector = f.getSector();
				listaSectoresSeleccionados.add(sector);
				request.getSession().setAttribute("listaSectoresSeleccionados",
						listaSectoresSeleccionados);
			}

			RegistroUbicacionVO registroUbicacionVO = converterUbicacion(form,
					idEmpresa);
			registroUbicacionVO.setIdOferta(idOferta);
			request.getSession().setAttribute("listaPrestaciones",
					registroUbicacionVO.getPrestaciones());

			request.getSession().setAttribute("registroUbicacionVO",
					registroUbicacionVO);

			// idOferta = services.guardarUbicacion(registroUbicacionVO);
			// idOferta = services.editarUbicacion(registroUbicacionVO);

			request.getSession().setAttribute("registroUbicacionVO",
					registroUbicacionVO);
			// request.getSession().setAttribute("idOferta", "" + idOferta);

			// request.getSession().setAttribute("id_oferta", "" + idOferta);

		} catch (Exception e) {
			logger.error(e); // TODO Notificar error al usuario
		}

		// request.getSession().setAttribute(BODY_JSP,
		// mapping.findForward(FORWARD_NEXT).getPath());
		return mapping.findForward(FORWARD_NEXT);
	}

	@SuppressWarnings("unchecked")
	public ActionForward guardarRequisitos(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		// logger.info("Se registra el tiempo para el lapso de asignacion : "+
		// paramform.getTiempoReasignacion());

		// int minutos = paramform.getTiempoReasignacion();

		OfertaBusDelegate services = OfertaBusDelegateImpl.getInstance();
		long idoferta = Long.parseLong(request.getSession()
				.getAttribute("idOferta").toString());

		ArrayList<RequisitoVO> listaConocimientos = (ArrayList<RequisitoVO>) request
				.getSession().getAttribute("listaConocimientos");
		ArrayList<RequisitoVO> listaHabilidades = (ArrayList<RequisitoVO>) request
				.getSession().getAttribute("listaHabilidades");
		ArrayList<RequisitoVO> listaCompetencias = (ArrayList<RequisitoVO>) request
				.getSession().getAttribute("listaCompetencias");
		ArrayList<RegistroIdiomaVO> listaIdiomas = (ArrayList<RegistroIdiomaVO>) request
				.getSession().getAttribute("listaIdiomas");
		ArrayList<String> listaCarrerasSeleccionadas = (ArrayList<String>) request
				.getSession().getAttribute("listaCarrerasSeleccionadas");
		ArrayList<String> listaSectoresSeleccionados = (ArrayList<String>) request
				.getSession().getAttribute("listaSectoresSeleccionados");
		ArrayList<RegistroEntidadesVO> listaEntidadesSeleccionadas = (ArrayList<RegistroEntidadesVO>) request
				.getSession().getAttribute("listaEntidadesSeleccionadas");
		String[] listaPrestaciones = (String[]) request.getSession()
				.getAttribute("listaPrestaciones");
		RegistroRequisitosVO registroRequisitosVO = converterRequisitos(form,
				idoferta, listaConocimientos, listaHabilidades,
				listaCompetencias, listaIdiomas, listaCarrerasSeleccionadas,
				listaSectoresSeleccionados, listaEntidadesSeleccionadas,
				listaPrestaciones);

		request.getSession().setAttribute("registroRequisitosVO",
				registroRequisitosVO);
		// services.guardarRequisitos(registroRequisitosVO);

		// request.getSession().setAttribute(BODY_JSP,
		// mapping.findForward(FORWARD_NEXT).getPath());
		return mapping.findForward(FORWARD_NEXT);
	}

	public ActionForward guardarContacto(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			
			long idEmpresa = Long.parseLong((String)request.getSession().getAttribute("idEmpresa"));
			long perfil = Long.parseLong((String)request.getSession().getAttribute("perfil"));
			
			//UsuarioWebVO usuario = getUsuario(request.getSession());
			//long idEmpresa = usuario.getIdPropietario();
			
			OfertaBusDelegate services = OfertaBusDelegateImpl.getInstance();
			long idoferta = Long.parseLong(request.getParameter("idOferta").toString());
			RegistroContactoVO registroContactoVO = converterContacto(form, idoferta);

			RegistroRequisitosVO registroRequisitosVO = (RegistroRequisitosVO) request.getSession().getAttribute("registroRequisitosVO");
			RegistroUbicacionVO registroUbicacionVO = (RegistroUbicacionVO) request.getSession().getAttribute("registroUbicacionVO");

			//long idOferta = services.guardarUbicacion(registroUbicacionVO);
			//registroRequisitosVO.setIdOferta(idOferta);
			//services.guardarRequisitos(registroRequisitosVO);
			
			//registroContactoVO.setIdOferta(idOferta);			
			registroContactoVO.setIdEmpresa(idEmpresa);
			
			//services.guardarContacto(registroContactoVO);
			
			long idOferta = services.guardarUbicacionTotal(registroUbicacionVO, registroRequisitosVO, registroContactoVO, perfil);
			request.getSession().setAttribute("nuevaOfertaGuardada", idOferta+"");
			
		} catch (BusinessException e) {
			logger.error(e); // TODO Notificar error al usuario
		} catch (ServiceLocatorException e) {
			logger.error(e); // TODO Notificar error al usuario
		} catch (IndexerException e) {
			logger.error(e); // TODO Notificar error al usuario
		} catch (TechnicalException e) {
			logger.error(e); // TODO Notificar error al usuario
		} catch (Exception e) {
			logger.error(e); // TODO Notificar error al usuario
		}

		// request.getSession().setAttribute(BODY_JSP,
		// mapping.findForward(FORWARD_JSP).getPath());
		// return mapping.findForward(FORWARD_TEMPLATE_WORK);
		return mapping.findForward(FORWARD_NEXT);
	}

	private RegistroContactoVO converterContacto(ActionForm forma, long idoferta) {
		RegistroContactoForm f = (RegistroContactoForm) forma;
		RegistroContactoVO vo = new RegistroContactoVO();

		vo.setIdOferta(idoferta);
		vo.setNombreEmpresa(Long.parseLong(f.getNombreEmpresa()));
		vo.setNombreContacto(Long.parseLong(f.getNombreContacto()));
		vo.setTelefonoContacto(f.getTeleconoContacto() != null ? Constantes.CONTACTO_TELEFONO.SI.getIdContactoTelefono() : Constantes.CONTACTO_TELEFONO.NO.getIdContactoTelefono());
		vo.setCorreoContacto(f.getCorreoContacto() != null ? Constantes.CONTACTO_CORREO.SI.getIdContactoCorreo() : Constantes.CONTACTO_CORREO.NO.getIdContactoCorreo());
		vo.setDomingo(f.getDomingo());
		vo.setLunes(f.getLunes());
		vo.setMartes(f.getMartes());
		vo.setMiercoles(f.getMiercoles());
		vo.setJueves(f.getJueves());
		vo.setViernes(f.getViernes());
		vo.setSabado(f.getSabado());
		vo.setHoraAtencionInicio(Long.parseLong(f.getHoraAtencionInicio()));
		vo.setHoraAtencionFin(Long.parseLong(f.getHoraAtencionFin()));
		vo.setDuracionAtencion(Long.parseLong(f.getDuracionAtencion()));

		return vo;

	}

	private RegistroRequisitosVO converterRequisitos(ActionForm forma,
			long idoferta, ArrayList<RequisitoVO> listaConocimientos,
			ArrayList<RequisitoVO> listaHabilidades,
			ArrayList<RequisitoVO> listaCompetencias,
			ArrayList<RegistroIdiomaVO> listaIdiomas,
			ArrayList<String> listaCarrerasSeleccionadas,
			ArrayList<String> listaSectoresSeleccionados,
			ArrayList<RegistroEntidadesVO> listaEntidadesSeleccionadas,
			String[] listaPrestaciones) {

		RegistroRequisitosForm f = (RegistroRequisitosForm) forma;
		RegistroRequisitosVO vo = new RegistroRequisitosVO();
		vo.setIdOferta(idoferta);
		vo.setListaConocimientos(listaConocimientos);
		vo.setListaHabilidades(listaHabilidades);
		vo.setListaCompetencias(listaCompetencias);
		vo.setListaIdiomas(listaIdiomas);
		vo.setListaCarrerasSeleccionadas(listaCarrerasSeleccionadas);
		vo.setListaSectoresSeleccionados(listaSectoresSeleccionados);
		vo.setListaEntidadesSeleccionados(listaEntidadesSeleccionadas);
		vo.setListaPrestaciones(listaPrestaciones);

		vo.setEstudios(Long.parseLong(f.getEstudios()));
		if(f.getSituacionAcademica()!=null)
		vo.setSituacionAcademica(Long.parseLong(f.getSituacionAcademica()));
		vo.setConocimientosGenerales(f.getConocimientosGenerales());
		vo.setAniosExperiencia(Integer.parseInt(f.getAniosExperiencia()));
		vo.setDisponibilidadViajar(f.getDisponibilidadViajar() != null ? 1 : 0);
		vo.setDisponibilidadRadicar(f.getDisponibilidadRadicar() != null ? 1
				: 0);
		vo.setEdadRequerida(f.getEdadRequerida() != null ? Constantes.EDAD_REQUISITO.NO.getIdOpcion() : Constantes.EDAD_REQUISITO.SI.getIdOpcion());
		
		if(f.getEdadDe()!=null ){
			if(f.getEdadDe().length()>0)
				vo.setEdadDe(Integer.parseInt(f.getEdadDe()));
				vo.setEdadRequerida(Constantes.EDAD_REQUISITO.SI.getIdOpcion());
			}
		else
			vo.setEdadDe(0);		
		
		if(f.getEdadA()!=null ){
			if(f.getEdadA().length()>0)
				vo.setEdadA(Integer.parseInt(f.getEdadA()));
				}
		else{
			vo.setEdadA(0);
		}
		
		vo.setGenero(Integer.parseInt(f.getGenero()));
		vo.setObservaciones(f.getObservaciones());
		if(f.getConocimiento1()!=null){
			if(f.getConocimiento1().length()>0){
				vo.setConocimiento1(f.getConocimiento1());
				vo.setDominioConoc(Integer.parseInt(f.getDominioConoc()));
				vo.setAniosExperienciaConoc(Integer.parseInt(f.getAniosExperienciaConoc()));
		}}
		if(f.getHabilidad1()!=null){
			if(f.getHabilidad1().length()>0){
					
		vo.setHabilidad1(f.getHabilidad1());
		vo.setDominioHabilidad(Integer.parseInt(f.getDominioHabilidad()));
		vo.setAniosExperienciaHabilidad(Integer.parseInt(f
				.getAniosExperienciaHabilidad()));
		}}
		if(f.getCompetencia1()!=null){
			if(f.getCompetencia1().length()>0){
		vo.setCompetencia1(f.getCompetencia1());
		vo.setDominioComp(Integer.parseInt(f.getDominioComp()));
		vo.setAniosExperienciaComp(Integer.parseInt(f.getAniosExperienciaComp()));
		}}
		if(f.getIdioma1()!=null){
			if(f.getIdioma1().length()>0){
		vo.setIdioma1(Long.parseLong(f.getIdioma1()));
		if(f.getCertificacionIdioma()!=null)
			if(f.getCertificacionIdioma().length()>0)
		vo.setCertificacionIdioma(Long.parseLong(f.getCertificacionIdioma()));
		if(f.getDominioIdioma()!=null)
			if(f.getDominioIdioma().length()>0)
		vo.setDominioIdioma(Long.parseLong(f.getDominioIdioma()));
		}}
		if(f.getCarrera()!=null)
			if(f.getCarrera().length()>0)
		vo.setCarrera(Long.parseLong(f.getCarrera()));
		return vo;

	}

	private RegistroUbicacionVO converterUbicacion(ActionForm forma,
			long idEmpresa) {
		RegistroUbicacionForm f = (RegistroUbicacionForm) forma;
		RegistroUbicacionVO vo = new RegistroUbicacionVO();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		vo.setTituloOferta(f.getTituloOferta());
		vo.setArea(Long.parseLong(f.getArea()));
		vo.setOcupacion(Long.parseLong(f.getOcupacion()));
		vo.setSector(Long.parseLong(f.getSector()));
		vo.setFunciones(f.getFunciones());
		vo.setHorario(Long.parseLong(f.getHorario()));
		vo.setDomingo(f.getDomingo());
		vo.setLunes(f.getLunes());
		vo.setMartes(f.getMartes());
		vo.setMiercoles(f.getMiercoles());
		vo.setJueves(f.getJueves());
		vo.setViernes(f.getViernes());
		vo.setSabado(f.getSabado());
		vo.setHEntrada(f.getHEntrada());
		vo.setHSalida(f.getHSalida());
		vo.setRolarTurnos(Integer.parseInt(f.getRolarTurnos()));
		vo.setEmpresaOfrece(f.getEmpresaOfrece());
		vo.setSalario(Float.parseFloat(f.getSalario()));
		// vo.setTipoContacto(Long.parseLong(f.getTipoContacto()));
		vo.setTipoContrato(Long.parseLong(f.getTipoContrato()));
		vo.setPrestaciones(f.getPrestaciones());
		vo.setJerarquia(Long.parseLong(f.getJerarquia()));
		vo.setNumeroPlazas(Integer.parseInt(f.getNumeroPlazas()));
		
		//vo.setLimitePostulantes(Integer.parseInt(f.getLimitePostulantes()));
		
		vo.setDicapacidad(Long.parseLong(f.getDicapacidad()));
		vo.setOrigenOferta(Long.parseLong(f.getOrigenOferta()));
		vo.setMapaUbicacion(f.getMapaUbicacion());
		vo.setIdEmpresa(idEmpresa);
		try {
			vo.setVigenciaInicio(sdf.parse(f.getVigenciainicio()));
			vo.setVigenciaFin(sdf.parse(f.getVigenciaFin()));
		} catch (java.text.ParseException pe) {
			pe.printStackTrace();
		}

		vo.setCalle(f.getCalle());
		vo.setCodigoPostal(f.getCodigoPostal());
		vo.setEntreCalle(f.getEntreCalle());
		vo.setFechaAlta(new Date());
		vo.setIdColonia(f.getIdColonia());
		vo.setIdEntidad(f.getIdEntidad());
		vo.setIdMunicipio(f.getIdMunicipio());
		vo.setIdTipoPropietario(f.getIdTipoPropietario());

		vo.setNumeroExterior(f.getNumeroExterior());
		vo.setNumeroInterior(f.getNumeroInterior());
		vo.setyCalle(f.getyCalle());
		return vo;

	}
	
	
	private boolean noRepetirConoc(String conoc,ArrayList<RequisitoVO> lista)
	{
		if(conoc==null) return false;
		for(RequisitoVO item:lista){
			if(conoc.compareTo(item.getDescripcion())==0)
				return false;
			
		}
		
		return true;
	}
	
	@SuppressWarnings("unchecked")
	public ActionForward registrarConocimiento(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		ArrayList<RequisitoVO> lista = null;
		RequisitoVO item;
		lista = (ArrayList<RequisitoVO>) (request.getSession().getAttribute(
				"listaConocimientos") != null ? request.getSession()
				.getAttribute("listaConocimientos")
				: new ArrayList<RequisitoVO>());
		List<CatalogoOpcionVO> listaDominio = (List<CatalogoOpcionVO>) request
				.getSession().getAttribute("CATALOGO_OPCION_DOMINIO");
		List<CatalogoOpcionVO> listaExperiencia = (List<CatalogoOpcionVO>) request
				.getSession().getAttribute("CATALOGO_OPCION_EXPERIENCIA");
		RequisitosForm req = (RequisitosForm) form;

		String conoc = req.getConocimiento();
		String exp = req.getExperiencia() + "";
		String dom = req.getDominio() + "";

		String parametro = null;

		int i = 0;
		while (true) {
			parametro = request.getParameter("elim" + (i + 1));
			if (parametro != null) {
				lista.remove(i);
			}
			i++;
			if (i >= lista.size())
				break;
		}

		if (conoc != null)
			if (conoc.length() > 1 &&  noRepetirConoc(conoc,lista)) {
				item = new RequisitoVO();
				item.setDescripcion(conoc);
				item.setExperiencia(Long.parseLong(exp));
				item.setDominio(Long.parseLong(dom));
				for (int j = 0; j < listaExperiencia.size(); j++) {
					if (listaExperiencia.get(j).getIdCatalogoOpcion() == Long
							.parseLong(exp)) {
						item.setExpDescripcion(listaExperiencia.get(j)
								.getOpcion());
					}
				}
				for (int j = 0; j < listaDominio.size(); j++) {
					if (listaDominio.get(j).getIdCatalogoOpcion() == Long
							.parseLong(dom)) {
						item.setDomDescripcion(listaDominio.get(j).getOpcion());
					}
				}
				lista.add(item);
				request.getSession().removeAttribute("listaConocimientos");
				request.getSession().setAttribute("listaConocimientos", lista);

			}

		return mapping.findForward(NEXT_CONOCIMIENTO);
	}

	@SuppressWarnings("unchecked")
	public ActionForward registrarHabilidades(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		ArrayList<RequisitoVO> lista = null;
		RequisitoVO item;
		lista = (ArrayList<RequisitoVO>) (request.getSession().getAttribute(
				"listaHabilidades") != null ? request.getSession()
				.getAttribute("listaHabilidades")
				: new ArrayList<RequisitoVO>());
		List<CatalogoOpcionVO> listaDominio = (List<CatalogoOpcionVO>) request
				.getSession().getAttribute("CATALOGO_OPCION_DOMINIO");
		List<CatalogoOpcionVO> listaExperiencia = (List<CatalogoOpcionVO>) request
				.getSession().getAttribute("CATALOGO_OPCION_EXPERIENCIA");
		RequisitosForm req = (RequisitosForm) form;

		String conoc = req.getConocimiento();
		String exp = req.getExperiencia() + "";
		String dom = req.getDominio() + "";

		String parametro = null;

		int i = 0;
		while (true) {
			parametro = request.getParameter("elim" + (i + 1));
			if (parametro != null) {
				lista.remove(i);
			}
			i++;
			if (i >= lista.size())
				break;
		}

		if (conoc != null)
			if (conoc.length() > 1 &&  noRepetirConoc(conoc,lista)) {

				item = new RequisitoVO();
				item.setDescripcion(conoc);
				item.setExperiencia(Long.parseLong(exp));
				item.setDominio(Long.parseLong(dom));

				for (int j = 0; j < listaExperiencia.size(); j++) {
					if (listaExperiencia.get(j).getIdCatalogoOpcion() == Long
							.parseLong(exp)) {
						item.setExpDescripcion(listaExperiencia.get(j)
								.getOpcion());
					}
				}
				for (int j = 0; j < listaDominio.size(); j++) {
					if (listaDominio.get(j).getIdCatalogoOpcion() == Long
							.parseLong(dom)) {
						item.setDomDescripcion(listaDominio.get(j).getOpcion());
					}
				}

				lista.add(item);
				request.getSession().removeAttribute("listaHabilidades");
				request.getSession().setAttribute("listaHabilidades", lista);

			}

		return mapping.findForward(NEXT_HABILIDAD);
	}

	@SuppressWarnings("unchecked")
	public ActionForward registrarCompetencias(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		ArrayList<RequisitoVO> lista = null;
		RequisitoVO item;
		lista = (ArrayList<RequisitoVO>) (request.getSession().getAttribute(
				"listaCompetencias") != null ? request.getSession()
				.getAttribute("listaCompetencias")
				: new ArrayList<RequisitoVO>());
		List<CatalogoOpcionVO> listaDominio = (List<CatalogoOpcionVO>) request
				.getSession().getAttribute("CATALOGO_OPCION_DOMINIO");
		List<CatalogoOpcionVO> listaExperiencia = (List<CatalogoOpcionVO>) request
				.getSession().getAttribute("CATALOGO_OPCION_EXPERIENCIA");
		RequisitosForm req = (RequisitosForm) form;

		String conoc = req.getConocimiento();
		String exp = req.getExperiencia() + "";
		String dom = req.getDominio() + "";

		String parametro = null;

		int i = 0;
		while (true) {
			parametro = request.getParameter("elim" + (i + 1));
			if (parametro != null) {
				lista.remove(i);
			}
			i++;
			if (i >= lista.size())
				break;
		}

		if (conoc != null)
			if (conoc.length() > 1  &&  noRepetirConoc(conoc,lista)) {
				item = new RequisitoVO();
				item.setDescripcion(conoc);
				item.setExperiencia(Long.parseLong(exp));
				item.setDominio(Long.parseLong(dom));

				for (int j = 0; j < listaExperiencia.size(); j++) {
					if (listaExperiencia.get(j).getIdCatalogoOpcion() == Long
							.parseLong(exp)) {
						item.setExpDescripcion(listaExperiencia.get(j)
								.getOpcion());
					}
				}
				for (int j = 0; j < listaDominio.size(); j++) {
					if (listaDominio.get(j).getIdCatalogoOpcion() == Long
							.parseLong(dom)) {
						item.setDomDescripcion(listaDominio.get(j).getOpcion());
					}
				}

				lista.add(item);
				request.getSession().removeAttribute("listaCompetencias");
				request.getSession().setAttribute("listaCompetencias", lista);

			}

		return mapping.findForward(NEXT_COMPETENCIA);
	}

	private boolean noRepetirIdioma(String conoc, ArrayList<RegistroIdiomaVO>lista, String iid){
		if(conoc==null) return false;
		for(RegistroIdiomaVO item: lista){
			if(item.getIdioma()==Long.parseLong(conoc) )
			  return false;
		}
		if(Long.parseLong(iid)==Long.parseLong(conoc))
		   return false;
		
		return true;
	}
	
	public ActionForward registrarIdiomas(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		ArrayList<RegistroIdiomaVO> lista = null;
		RegistroIdiomaVO item;
		lista = (ArrayList<RegistroIdiomaVO>) (request.getSession()
				.getAttribute("listaIdiomas") != null ? request.getSession()
				.getAttribute("listaIdiomas")
				: new ArrayList<RegistroIdiomaVO>());
		List<CatalogoOpcionVO> listaIdioma = (List<CatalogoOpcionVO>) request
				.getSession().getAttribute("CATALOGO_OPCION_IDIOMA");
		List<CatalogoOpcionVO> listaDominio = (List<CatalogoOpcionVO>) request
				.getSession().getAttribute("CATALOGO_OPCION_DOMINIO");

		/*List<CatalogoOpcionVO> listaCertChino = (List<CatalogoOpcionVO>) request
				.getSession().getAttribute("CATALOGO_CERTIFICACION_CHINO");*/
		/*List<CatalogoOpcionVO> listaCertIngles = (List<CatalogoOpcionVO>) request
				.getSession().getAttribute("CATALOGO_CERTIFICACION_INGLES");*/
		/*List<CatalogoOpcionVO> listaCertFrances = (List<CatalogoOpcionVO>) request
				.getSession().getAttribute("CATALOGO_CERTIFICACION_FRANCES");*/
		/*List<CatalogoOpcionVO> listaCertItaliano = (List<CatalogoOpcionVO>) request
				.getSession().getAttribute("CATALOGO_CERTIFICACION_ITALIANO");*/
		/*List<CatalogoOpcionVO> listaCertJapones = (List<CatalogoOpcionVO>) request
				.getSession().getAttribute("CATALOGO_CERTIFICACION_JAPONES");*/
		/*List<CatalogoOpcionVO> listaCertAleman = (List<CatalogoOpcionVO>) request
				.getSession().getAttribute("CATALOGO_CERTIFICACION_ALEMAN");*/

		RequisitosForm req = (RequisitosForm) form;
		String iid=(String)request.getSession().getAttribute("iid");

		String conoc = req.getConocimiento();
		String exp = req.getExperiencia() + "";
		String dom = req.getDominio() + "";

		String parametro = null;

		int i = 0;
		while (true) {
			parametro = request.getParameter("elim" + (i + 1));
			if (parametro != null) {
				lista.remove(i);
			}
			i++;
			if (i >= lista.size())
				break;
		}

		if (conoc != null)
			if (conoc.compareTo("-1") != 0 && noRepetirIdioma(conoc, lista,iid)) {
				item = new RegistroIdiomaVO();
				item.setIdioma(Long.parseLong(conoc));
				item.setCertificacion(Long.parseLong(exp));
				item.setDominio(Long.parseLong(dom));
				CatalogoOpcionDelegate servicesCatalogos = CatalogoOpcionDelegateImpl.getInstance();
				
				for (int j = 0; j < listaIdioma.size(); j++) {
					if (listaIdioma.get(j).getIdCatalogoOpcion() == Long
							.parseLong(conoc)) {
						item.setIdiomaDescripcion(listaIdioma.get(j)
								.getOpcion());
					}
				}
				for (int j = 0; j < listaDominio.size(); j++) {
					if (listaDominio.get(j).getIdCatalogoOpcion() == Long
							.parseLong(dom)) {
						item.setDominioDescripcion(listaDominio.get(j)
								.getOpcion());
					}
				}

				if (item.getIdioma() == Constantes.IDIOMAS.ALEMAN.getIdOpcion()) {
					try{									
						CatalogoOpcionVO vo = servicesCatalogos.findById(CERTIFICACION.ALEMAN.getIdOpcion(), item.getCertificacion());
						if (vo != null)
							item.setCertificacionDescripcion(vo.getOpcion());
					} catch(ServiceLocatorException e){
						logger.error("Error al invocar servicesCatalogos.findById para el idioma "+item.getIdioma());
						e.printStackTrace();
					}
				}

				if (item.getIdioma() == Constantes.IDIOMAS.CHINO.getIdOpcion()) {
					try{									
						CatalogoOpcionVO vo = servicesCatalogos.findById(CERTIFICACION.CHINO.getIdOpcion(), item.getCertificacion());
						if (vo != null)
							item.setCertificacionDescripcion(vo.getOpcion());
					} catch(ServiceLocatorException e){
						logger.error("Error al invocar servicesCatalogos.findById para el idioma "+item.getIdioma());
						e.printStackTrace();
					}
				}

				if (item.getIdioma() == Constantes.IDIOMAS.INGLES.getIdOpcion()) {
					try{									
						CatalogoOpcionVO vo = servicesCatalogos.findById(CERTIFICACION.INGLES.getIdOpcion(), item.getCertificacion());
						if (vo != null)
							item.setCertificacionDescripcion(vo.getOpcion());
					} catch(ServiceLocatorException e){
						logger.error("Error al invocar servicesCatalogos.findById para el idioma "+item.getIdioma());
						e.printStackTrace();
					}
				}

				if (item.getIdioma() == Constantes.IDIOMAS.JAPONES
						.getIdOpcion()) {
					try{							
						CatalogoOpcionVO vo = servicesCatalogos.findById(CERTIFICACION.JAPONES.getIdOpcion(), item.getCertificacion());
						if (vo != null)
							item.setCertificacionDescripcion(vo.getOpcion());
					} catch(ServiceLocatorException e){
						logger.error("Error al invocar servicesCatalogos.findById para el idioma "+item.getIdioma());
						e.printStackTrace();
					}
				}

				if (item.getIdioma() == Constantes.IDIOMAS.FRANCES
						.getIdOpcion()) {
					try{									
						CatalogoOpcionVO vo = servicesCatalogos.findById(CERTIFICACION.FRANCES.getIdOpcion(), item.getCertificacion());
						if (vo != null)
							item.setCertificacionDescripcion(vo.getOpcion());
					} catch(ServiceLocatorException e){
						logger.error("Error al invocar servicesCatalogos.findById para el idioma "+item.getIdioma());
						e.printStackTrace();
					}					
				}

				if (item.getIdioma() == Constantes.IDIOMAS.ITALIANO
						.getIdOpcion()) {
					try{									
						CatalogoOpcionVO vo = servicesCatalogos.findById(CERTIFICACION.ITALIANO.getIdOpcion(), item.getCertificacion());
						if (vo != null)
							item.setCertificacionDescripcion(vo.getOpcion());
					} catch(ServiceLocatorException e){
						logger.error("Error al invocar servicesCatalogos.findById para el idioma "+item.getIdioma());
						e.printStackTrace();
					}
				}

				lista.add(item);
				// request.getSession().removeAttribute("listaIdiomas");
				request.getSession().setAttribute("listaIdiomas", lista);

			}

		return mapping.findForward(JSP_IDIOMA);
	}

	@SuppressWarnings("unchecked")
	public ActionForward registrarEntidades(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		ArrayList<RegistroEntidadesVO> lista = null;
		RegistroEntidadesVO item;
		lista = (ArrayList<RegistroEntidadesVO>) (request.getSession()
				.getAttribute("listaEntidades") != null ? request.getSession()
				.getAttribute("listaEntidades")
				: new ArrayList<RegistroEntidadesVO>());

		EntidadesForm req = (EntidadesForm) form;

		String ent = req.getEntidad();
		String mun = req.getMunicipio();

		String parametro = null;

		int i = 0;
		while (true) {
			parametro = request.getParameter("elim" + (i + 1));
			if (parametro != null) {
				lista.remove(i);
			}
			i++;
			if (i >= lista.size())
				break;
		}

		if (ent != null)
			if (ent.compareTo("0") != 0) {
				item = new RegistroEntidadesVO();
				item.setEntidad(Long.parseLong(ent));
				item.setMunicipio(Long.parseLong(mun));
				lista.add(item);
				request.getSession().removeAttribute("listaEntidades");
				request.getSession().setAttribute("listaEntidades", lista);

			}

		return mapping.findForward(NEXT_ENTIDAD);
	}

	
	public ActionForward carrerasSimilaresInit(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		
		
		@SuppressWarnings("unchecked")
		List<OfertaCarreraEspecialidadVO> listaCarrerasEspecialidadesOferta = (List<OfertaCarreraEspecialidadVO>) request
				.getSession().getAttribute("listaCarrerasEspecialidadesOferta");

		long estudios = request.getSession().getAttribute("estudios") != null ? Long
				.parseLong(request.getSession().getAttribute("estudios")
						.toString()) : 0L;

				String carreraSel= request.getParameter("cid") != null ?  request.getParameter("cid")
						.toString() : "0";			
			
				String caid=(String)request.getSession().getAttribute("caid");		
						
				if(caid==null)
					request.getSession().removeAttribute("listaCarreras");
				else
					if(caid.compareTo(carreraSel)!=0)
						request.getSession().removeAttribute("listaCarreras");
						
				request.getSession().setAttribute("caid", carreraSel);
			//	request.getSession().removeAttribute("estudios");
		
				 if(request.getSession().getAttribute("listaCarreras")==null){			
						ArrayList<ElementoListaVO> carrerasSimilares = obtenerCarrerasSimilares(estudios, Long.parseLong(carreraSel));
			
           if(carrerasSimilares!=null && listaCarrerasEspecialidadesOferta!=null)
			for (OfertaCarreraEspecialidadVO carreraBD : listaCarrerasEspecialidadesOferta) {
				for (ElementoListaVO carrera : carrerasSimilares) {
					if (carrera.getId().compareTo(carreraBD.getId() + "") == 0)
						carrera.setSeleccionada("true");
				}
			}

			request.getSession().removeAttribute(
					"listaCarrerasEspecialidadesOferta");

			request.getSession().setAttribute("listaCarreras",
					carrerasSimilares);
				 }
		request.getSession().setAttribute(BODY_JSP,
				mapping.findForward(JSP_CARRERA).getPath());
		return mapping.findForward(JSP_CARRERA);
	}

	public ActionForward registrarCarrerasSimilares(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws ClassNotFoundException {

		ArrayList<String> listaCarrerasSeleccionadas = new ArrayList<String>();
		ElementoListaVO hm;
		//request.getSession().removeAttribute("listaCarrerasSeleccionadas");
		@SuppressWarnings("unchecked")
		ArrayList<ElementoListaVO> listaCarreras = (ArrayList<ElementoListaVO>) request
				.getSession().getAttribute("listaCarreras");
		String parametro;
		int i = 0;
		while (true) {
			parametro = request.getParameter("c" + (i + 1));
			if (parametro != null) {
				hm = listaCarreras.get(i);
				hm.setSeleccionada("true");
				listaCarrerasSeleccionadas.add(hm.getId());
			} else {
				hm = listaCarreras.get(i);
				hm.setSeleccionada(null);
			}
			i++;
			if (i >= listaCarreras.size())
				break;
		}
		request.getSession().removeAttribute("listaCarrerasSeleccionadas");
		request.getSession().setAttribute("listaCarrerasSeleccionadas",
				listaCarrerasSeleccionadas);
		return null;
	}
	public ActionForward obtenerCarreras(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		RegistroRequisitosForm paramForm = (RegistroRequisitosForm) form;
		paramForm.reset(mapping, request);
		int estudios = Integer.parseInt((String) request.getParameter("estudios"));
		List<CatalogoOpcionVO> opciones = null;
		request.getSession().setAttribute("estudios", estudios+"");
		request.getSession().removeAttribute("listaCarreras");
		CatalogoOpcionVO op;
		
		try {

			OfertaBusDelegate services = OfertaBusDelegateImpl.getInstance();
			
			if(estudios==Constantes.GRADO_ESTUDIOS.CARRERA_COMERCIAL.getIdOpcion())
			{
				opciones = services
				.obtenerCatalogoAlf((long)Constantes.CATALOGO.TECNICA_COMERCIAL_BASICA.getIdCatalogo());
		
			}

			if(estudios==Constantes.GRADO_ESTUDIOS.CARRERA_TECNICA.getIdOpcion())
			{
				opciones = services
				.obtenerCatalogoAlf((long)Constantes.CATALOGO.TECNICA_COMERCIAL_BASICA.getIdCatalogo());
		
			}

			if(estudios==Constantes.GRADO_ESTUDIOS.DOCTORADO.getIdOpcion())
			{
				opciones = services
				.obtenerCatalogoAlf((long)Constantes.CATALOGO.MAESTRIA_DOCTORADO.getIdCatalogo());
		
			}

			if(estudios==Constantes.GRADO_ESTUDIOS.LICENCIATURA.getIdOpcion())
			{
				opciones = services
				.obtenerCatalogoAlf((long)Constantes.CATALOGO.PROFESIONAL.getIdCatalogo());
		
			}
			
			
			if(estudios==Constantes.GRADO_ESTUDIOS.MAESTRIA.getIdOpcion())
			{
				opciones = services
				.obtenerCatalogoAlf((long)Constantes.CATALOGO.MAESTRIA_DOCTORADO.getIdCatalogo());
		
			}
			
			if(estudios==Constantes.GRADO_ESTUDIOS.PROFESIONAL_TECNICO.getIdOpcion())
			{
				opciones = services
				.obtenerCatalogoAlf((long)Constantes.CATALOGO.TECNICA_COMERCIAL_SUPERIOR.getIdCatalogo());
		
			}
			
			if(estudios==Constantes.GRADO_ESTUDIOS.SUPERIOR_UNIVERSITARIO.getIdOpcion())
			{
				opciones = services
				.obtenerCatalogoAlf((long)Constantes.CATALOGO.PROFESIONAL.getIdCatalogo());
		
			}
			
			if(estudios==Constantes.GRADO_ESTUDIOS.SIN_INSTRUCCION.getIdOpcion())
			{
				opciones = new ArrayList<CatalogoOpcionVO>();
				op=new CatalogoOpcionVO();
				op.setIdCatalogoOpcion(1);
				op.setOpcion("Ninguna");
				opciones.add(op);
		
			}
			
			if(estudios==Constantes.GRADO_ESTUDIOS.SECUNDARIA.getIdOpcion())
			{
				opciones = new ArrayList<CatalogoOpcionVO>();
				op=new CatalogoOpcionVO();
				op.setIdCatalogoOpcion(1);
				op.setOpcion("Ninguna");
				opciones.add(op);
		
			}
			if(estudios==Constantes.GRADO_ESTUDIOS.PREPA_VOCACIONAL.getIdOpcion())
			{
				opciones = services
				.obtenerCatalogoAlf((long)Constantes.CATALOGO.TECNICA_COMERCIAL_SUPERIOR.getIdCatalogo());
		
			}
			
			if(estudios==Constantes.GRADO_ESTUDIOS.PRIMARIA.getIdOpcion())
			{
				opciones = new ArrayList<CatalogoOpcionVO>();
				op=new CatalogoOpcionVO();
				op.setIdCatalogoOpcion(1);
				op.setOpcion("Ninguna");
				opciones.add(op);
		
			}
			
			if(estudios==Constantes.GRADO_ESTUDIOS.LEER_ESCRIBIR.getIdOpcion())
			{
				opciones = new ArrayList<CatalogoOpcionVO>();
				op=new CatalogoOpcionVO();
				op.setIdCatalogoOpcion(1);
				op.setOpcion("Ninguna");
				opciones.add(op);
		
			}

		
            
			CatalogoVO cat =null;
			if(opciones!=null){
				cat=getCatalogo(opciones);
				String json = toJson(cat);
				redirectJsonResponse(response, json);
			}

		} catch (ServiceLocatorException e) {
			logger.error(e);
		}  catch (IOException e) {
			logger.error(e);
		}

		return null;
	}
	
	public ActionForward obtenerEstudios(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		try {

			OfertaBusDelegate services = OfertaBusDelegateImpl.getInstance();

			@SuppressWarnings("unchecked")
			List<CatalogoOpcionVO> opciones = services
					.obtenerCatalogo(Constantes.CATALOGO_OPCION_GRADO_ESTUDIOS);

			CatalogoVO cat = getCatalogo(opciones);
			String json = toJson(cat);
			redirectJsonResponse(response, json);

		} catch (ServiceLocatorException e) {
			logger.error(e);
		} catch (BusinessException e) {
			logger.error(e);
		} catch (IOException e) {
			logger.error(e);
		}

		return null;
	}
	
	
}
