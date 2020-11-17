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
import static mx.gob.stps.portal.web.infra.utils.Constantes.PARAM_ID_REGISTRO_POR_VALIDAR;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TITULO_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.DESCRIPCION_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FACEBOOK_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TWITTER_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.URL_ESPECIFICA;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.gob.stps.portal.core.domicilio.vo.CodigoPostalVO;
import mx.gob.stps.portal.core.domicilio.vo.DomicilioVO;
import mx.gob.stps.portal.core.domicilio.vo.MunicipioVO;
import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.exception.TechnicalException;
import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO;
import mx.gob.stps.portal.core.infra.utils.Constantes.CERTIFICACION;
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
import mx.gob.stps.portal.web.infra.utils.Utils;
import mx.gob.stps.portal.web.infra.vo.CatalogoVO;
import mx.gob.stps.portal.web.oferta.delegate.OfertaBusDelegate;
import mx.gob.stps.portal.web.oferta.delegate.OfertaBusDelegateImpl;
import mx.gob.stps.portal.web.oferta.form.EntidadesForm;
import mx.gob.stps.portal.web.oferta.form.RegistroContactoForm;
import mx.gob.stps.portal.web.oferta.form.RegistroRequisitosForm;
import mx.gob.stps.portal.web.oferta.form.RegistroUbicacionForm;
import mx.gob.stps.portal.web.oferta.form.RequisitosForm;
import mx.gob.stps.portal.web.oferta.vo.ElementoListaVO;
import mx.gob.stps.portal.web.security.vo.UsuarioWebVO;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

// TODO ELIMINAR CLASE, SE CAMBIO LA CLASE PARA LA ADMINISTRACION DE OFERTAS
public class OfertaEdicionAction extends DomicilioAction {

	private static Logger logger = Logger.getLogger(OfertaEdicionAction.class);

	@Override
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		// RegistroUbicacionForm forma = (RegistroUbicacionForm)form;
		// forma.reset(mapping, request);

		try {
			int user=0;
			
			if(request.getSession().getAttribute("perfil")!=null) {
				user= Utils.parseInt((String)request.getSession().getAttribute("perfil"));
			} else {
				UsuarioWebVO us=this.getUsuario(request.getSession());
				request.getSession().setAttribute("idEmpresa", us.getIdPropietario());
				//System.out.println("--OfertaEdicionAction.init-idEmpresa:" + us.getIdPropietario());
				user=(int)us.getIdTipoUsuario();
				request.getSession().setAttribute("perfil",user+"" );				 
			}	
			
			String idOferta = request.getParameter("id");
			request.getSession().setAttribute("idOferta", idOferta);
			request.setAttribute("idOferta", idOferta);
			
			if(user!=0 &&  user==Constantes.TIPO_USUARIO.ADMINISTRADOR.getIdTipoUsuario()){
				request.getSession().setAttribute("usuario","100");
			}else { 
				request.getSession().setAttribute("usuario","200");
			}
			 
			request.getSession().removeAttribute("listaConocimientos");
			request.getSession().removeAttribute("listaHabilidades");
			request.getSession().removeAttribute("listaCompetencias");
			request.getSession().removeAttribute("listaIdiomas");
			request.getSession().removeAttribute("listaCarrerasSeleccionadas");
			request.getSession().removeAttribute("listaSectoresSeleccionados");
			request.getSession().removeAttribute("listaEntidadesSeleccionadas");
			request.getSession().removeAttribute("listaPrestaciones");
			request.getSession().removeAttribute("ofertaVO");
			request.getSession().removeAttribute("tercerasEmpresas");			
			
			//System.out.println("----ofertaedicionaction ya borre ofertaVO y tercerasEmpresas");
			OfertaBusDelegate services = OfertaBusDelegateImpl.getInstance();

			OfertaEmpleoVO ofertaVO = services.obtenerOferta(Utils.parseLong(idOferta));
			if (ofertaVO.getMapaUbicacion() == null)
				ofertaVO.setMapaUbicacion("");
			if (ofertaVO.getMapaUbicacion() != null && ofertaVO.getMapaUbicacion().compareTo("null") == 0)
				ofertaVO.setMapaUbicacion("");
				
			request.getSession().setAttribute("ofertaVO", ofertaVO);

			ArrayList<String> sectores = services.obtenerSectoresOferta(ofertaVO.getIdOfertaEmpleo());
			request.getSession().setAttribute("listaSectoresSeleccionados", sectores);

			ArrayList<String> prestaciones = services.obtenerPrestacionesOferta(ofertaVO.getIdOfertaEmpleo());
			request.getSession().setAttribute("listaPrestacionesSeleccionadas", prestaciones);

			ArrayList<RegistroEntidadesVO> entidades = services.obtenerEntidadesOferta(ofertaVO.getIdOfertaEmpleo());
			request.getSession().setAttribute("listaEntidadesSeleccionadas", entidades);

			ArrayList<OfertaCarreraEspecialidadVO> carreras = services.obtenerCarrerasEspecialidades(ofertaVO.getIdOfertaEmpleo());
			request.getSession().setAttribute("listaCarrerasEspecialidadesOferta", carreras);

			ArrayList<RequisitoVO> requisitos = services.obetenerRequisitos(ofertaVO.getIdOfertaEmpleo());
			request.getSession().setAttribute("listaRequisitosOferta",requisitos);

			ArrayList<OfertaIdiomaVO> idiomas = services.obtenerIdiomas(ofertaVO.getIdOfertaEmpleo());
			request.getSession().setAttribute("listaIdiomasOferta", idiomas);

			DomicilioVO domicilio = (DomicilioVO) services.obtenerDomicilio(Utils.parseLong(idOferta));
			request.getSession().setAttribute("domicilio", domicilio);

			CodigoPostalVO cp = services.obtenerCodigoPostal(domicilio.getIdColonia());
			request.getSession().setAttribute("cp", cp);

			List<CatalogoOpcionVO> catAreasLaborales = services.obtenerCatalogoAlf(Constantes.CATALOGO_OPCION_AREA_LABORAL);
			request.setAttribute("CATALOGO_OPCION_AREA_LABORAL", catAreasLaborales);
			/*List<CatalogoOpcionVO> catOcupacion = services.obtenerCatalogoAlf(Constantes.CATALOGO_OPCION_OCUPACION);
			request.setAttribute("CATALOGO_OPCION_OCUPACION", catOcupacion);*/
			request.setAttribute("CATALOGO_OPCION_SECTOR", services.obtenerCatalogoAlf(Constantes.CATALOGO_OPCION_SUBSECTOR));
			request.setAttribute("CATALOGO_OPCION_HORARIO_LABORAL", services.obtenerCatalogo(Constantes.CATALOGO_OPCION_HORARIO_LABORAL));
			request.setAttribute("CATALOGO_OPCION_HORARIO_CONTACTO", services.obtenerCatalogo(Constantes.CATALOGO_OPCION_HORARIO_CONTACTO));
			request.setAttribute("CATALOGO_OPCION_TIPO_CONTRATO", services.obtenerCatalogo(Constantes.CATALOGO_OPCION_TIPO_CONTRATO));
			request.setAttribute("CATALOGO_OPCION_PRESTACIONES", services.obtenerCatalogo(Constantes.CATALOGO_OPCION_PRESTACIONES));
			request.setAttribute("CATALOGO_OPCION_JERARQUIA_PUESTO", services.obtenerCatalogo(Constantes.CATALOGO_OPCION_JERARQUIA_PUESTO));
			request.setAttribute("CATALOGO_OPCION_CAUSA_OFERTA", services.obtenerCatalogo(Constantes.CATALOGO_OPCION_CAUSA_OFERTA));

			//List<MunicipioVO> catMunicipios = (List<MunicipioVO>) request.getSession().getAttribute("CatalogoMunicipios");

			request.setAttribute("CATALOGO_OPCION_TIPO_DISCAPACIDAD", services.obtenerCatalogo(Constantes.CATALOGO_OPCION_TIPO_DISCAPACIDAD));
			request.setAttribute("CATALOGO_OPCION_TIPO_EMPLEO", services.obtenerCatalogoAlf(Constantes.CATALOGO_OPCION_TIPO_EMPLEO));

		} catch (BusinessException e) {
			logger.error(e); // TODO Notificar error al usuario
		} catch (ServiceLocatorException e) {
			logger.error(e); // TODO Notificar error al usuario
		}

		ocultaBarraArticulos(request);
		request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
        PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Editar oferta");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Editar oferta, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo.");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
		return mapping.findForward(FORWARD_TEMPLATE_MI_ESP_EMP);
	}

	public ActionForward obtenerCatEntidades(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException{		
		//long[] arr = new long[]{Constantes.CATALOGO_OPCION_ENTIDAD_FEDERATIVA_NACIDO_EXTRANJERO};
		
		try{
			DomicilioBusDelegate services = DomicilioBusDelegateImpl.getInstance();
			long[] arrIds = new long[]{Constantes.CATALOGO_OPCION_ENTIDAD_FEDERATIVA_NACIDO_EXTRANJERO};
			List<CatalogoOpcionVO> opciones = services.consultarCatalogo(Constantes.CATALOGO_OPCION_ENTIDAD_FEDERATIVA, arrIds);	
		
			CatalogoVO cat = getCatalogo(opciones);
			String json = toJson(cat);
			redirectJsonResponse(response, json);
		
		}catch (ServiceLocatorException e) {
			logger.error(e); // TODO Notificar error al usuario
		}catch (BusinessException e) {
			logger.error(e); // TODO Notificar error al usuario
		}
		
		return null;
	}
	
	public ActionForward obtenerAreas(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		try{
			OfertaBusDelegate services = OfertaBusDelegateImpl.getInstance();	
		List<CatalogoOpcionVO> opciones = services.obtenerCatalogoAlf(Constantes.CATALOGO_OPCION_AREA_LABORAL);
	
		CatalogoVO cat = getCatalogo(opciones);
		String json = toJson(cat);
		redirectJsonResponse(response, json);
		}
		catch (ServiceLocatorException e) {
		logger.error(e); // TODO Notificar error al usuario
			}
		
		return null;
	}
	
	public ActionForward obtenerOcupaciones(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		long area = Utils.parseLong(request.getParameter("idArea"));
		long op1=area*10000;
		long op2=(area+1)*10000;

		try {
			OfertaBusDelegate services = OfertaBusDelegateImpl.getInstance();	
			List<CatalogoOpcionVO> opciones = services.getOpcionByInterval(Constantes.CATALOGO_OPCION_OCUPACION,op1, op2);
		
			CatalogoVO cat = getCatalogo(opciones);
			String json = toJson(cat);
			redirectJsonResponse(response, json);
			
		}catch (BusinessException e) {
			logger.error(e); // TODO Notificar error al usuario
		} catch (ServiceLocatorException e) {
			logger.error(e);
		
		} catch (IOException e) {
			logger.error(e);
		}

		return null;
	}

	
	
	
	public ActionForward guardaRequisitosInit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		long idOferta = Utils.parseLong(request.getAttribute("idOferta").toString());
		request.setAttribute("idOferta", idOferta+"");
		
		
		try {
			OfertaBusDelegate services = OfertaBusDelegateImpl.getInstance();
			request.setAttribute("CATALOGO_OPCION_GRADO_ESTUDIOS", services.obtenerCatalogo(Constantes.CATALOGO_OPCION_GRADO_ESTUDIOS));
			request.setAttribute("CATALOGO_OPCION_CARRERA_ESPECIALIDAD",services.obtenerCatalogoAlf(Constantes.CATALOGO_OPCION_CARRERA_ESPECIALIDAD));
			request.setAttribute("CATALOGO_OPCION_SITUACION_ACADEMICA",services.obtenerCatalogo(Constantes.CATALOGO_OPCION_SITUACION_ACADEMICA));
			request.setAttribute("CATALOGO_OPCION_IDIOMA",services.obtenerCatalogo(Constantes.CATALOGO_OPCION_IDIOMA));

			List<CatalogoOpcionVO> cat_experiencia = (List<CatalogoOpcionVO>) services.obtenerCatalogo(Constantes.CATALOGO_OPCION_EXPERIENCIA);
			List<CatalogoOpcionVO> cat_dominio = (List<CatalogoOpcionVO>) services.obtenerCatalogo(Constantes.CATALOGO_OPCION_DOMINIO);
			request.setAttribute("CATALOGO_OPCION_EXPERIENCIA", cat_experiencia);
			request.setAttribute("CATALOGO_OPCION_DOMINIO", cat_dominio);

			@SuppressWarnings("unchecked")
			List<RequisitoVO> listaRequisitosOferta = (List<RequisitoVO>) request.getSession().getAttribute("listaRequisitosOferta");

			List<RequisitoVO> listaConocimientosOferta = new ArrayList<RequisitoVO>();
			RequisitoVO conocimiento = null;
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
			request.getSession().setAttribute("listaConocimientos", listaConocimientosOferta);

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
			request.getSession().setAttribute("listaHabilidades", listaHabilidadesOferta);

			request.getSession().removeAttribute("competenciaPrincipal");
			
			List<RequisitoVO> listaCompetenciasOferta = new ArrayList<RequisitoVO>();
			RequisitoVO competencia;
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

		request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
        PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Editar oferta");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Editar oferta, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo.");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
		return mapping.findForward(FORWARD_TEMPLATE_MI_ESP_EMP);
	}

	public ActionForward guardaContactoInit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		
		long idOferta = Utils.parseLong(request.getAttribute("idOferta").toString());
		request.setAttribute("idOferta", idOferta+"");
		
		try {
			OfertaBusDelegate services = OfertaBusDelegateImpl.getInstance();
			request.setAttribute("CATALOGO_OPCION_EMPRESA_OFRECE_EMPLEO", services.obtenerCatalogo(Constantes.CATALOGO_OPCION_EMPRESA_OFRECE_EMPLEO));
			request.setAttribute("CATALOGO_OPCION_NOMBRE_CONTACTO_EMPRESA", services.obtenerCatalogo(Constantes.CATALOGO_OPCION_NOMBRE_CONTACTO_EMPRESA));
			//System.out.println("--OfertaEdicionAction.init-CATALOGO_OPCION_NOMBRE_CONTACTO_EMPRESA:" + services.obtenerCatalogo(Constantes.CATALOGO_OPCION_NOMBRE_CONTACTO_EMPRESA));			
			request.setAttribute("CATALOGO_OPCION_MEDIO_CONTACTO", services.obtenerCatalogo(Constantes.CATALOGO_OPCION_MEDIO_CONTACTO));
			request.setAttribute("CATALOGO_OPCION_DURACION_APROXIMADA", services.obtenerCatalogo(Constantes.CATALOGO_OPCION_DURACION_APROXIMADA));
			request.setAttribute("CATALOGO_OPCION_HORARIO_CONTACTO", services.obtenerCatalogo(Constantes.CATALOGO_OPCION_HORARIO_CONTACTO));
			//ANTES DE MODIFICAR ESTE PEDAZO, CHECAR CON HAYDEE long idEmpresa = Utils.parseLong(request.getSession().getAttribute("idEmpresa").toString());
			UsuarioWebVO us=this.getUsuario(request.getSession());
			long idEmpresa = us.getIdPropietario();			
			//System.out.println("--guardaContactoInit---idEmpresa:" + idEmpresa);			
			//request.setAttribute("tercerasEmpresas", services.obtenerTercerasEmpresas(idEmpresa));			
			//ArrayList<TerceraEmpresaVO> emp=services.obtenerEmpresas(idEmpresa);
			//request.setAttribute("Empresa", emp);

			//request.setAttribute("contactos", services.obtenerContactos(idEmpresa));

		} catch (BusinessException e) {
			logger.error(e); // TODO Notificar error al usuario
		} catch (ServiceLocatorException e) {
			logger.error(e); // TODO Notificar error al usuario
		}
		request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
        PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Editar oferta");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Editar oferta, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo.");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
		return mapping.findForward(FORWARD_TEMPLATE_MI_ESP_EMP);
	}

	public ActionForward conocimientoInit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			OfertaBusDelegate services = OfertaBusDelegateImpl.getInstance();
			request.getSession().setAttribute("CATALOGO_OPCION_EXPERIENCIA", services.obtenerCatalogo(Constantes.CATALOGO_OPCION_EXPERIENCIA));
			request.getSession().setAttribute("CATALOGO_OPCION_DOMINIO", services.obtenerCatalogo(Constantes.CATALOGO_OPCION_DOMINIO));
		} catch (BusinessException e) {
			logger.error(e); // TODO Notificar error al usuario
		} catch (ServiceLocatorException e) {
			logger.error(e); // TODO Notificar error al usuario
		}

		request.getSession().setAttribute(BODY_JSP, mapping.findForward(JSP_CONOCIMIENTO).getPath());
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

	@SuppressWarnings("unchecked")
	public ActionForward idiomaInit(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();

		ArrayList<OfertaIdiomaVO> idiomas = (ArrayList<OfertaIdiomaVO>) session.getAttribute("listaIdiomasOferta");
		ArrayList<RegistroIdiomaVO> idiomasRegistro = new ArrayList<RegistroIdiomaVO>();
		RegistroIdiomaVO item;
		List<CatalogoOpcionVO> listaDominio = null;
		List<CatalogoOpcionVO> listaIdiomas = null;
		
		//List<CatalogoOpcionVO> listaCertChino = (List<CatalogoOpcionVO>) session.getAttribute("CATALOGO_CERTIFICACION_CHINO");
		//List<CatalogoOpcionVO> listaCertIngles = (List<CatalogoOpcionVO>) session.getAttribute("CATALOGO_CERTIFICACION_INGLES");
		//List<CatalogoOpcionVO> listaCertFrances = (List<CatalogoOpcionVO>) session.getAttribute("CATALOGO_CERTIFICACION_FRANCES");
		//List<CatalogoOpcionVO> listaCertItaliano = (List<CatalogoOpcionVO>) session.getAttribute("CATALOGO_CERTIFICACION_ITALIANO");
		//List<CatalogoOpcionVO> listaCertJapones = (List<CatalogoOpcionVO>) session.getAttribute("CATALOGO_CERTIFICACION_JAPONES");
		//List<CatalogoOpcionVO> listaCertAleman = (List<CatalogoOpcionVO>) session.getAttribute("CATALOGO_CERTIFICACION_ALEMAN");

		String iid=request.getParameter("iid");
		if(iid!=null){
			session.setAttribute("iid", iid);
		}
		
		try {

			OfertaBusDelegate services = OfertaBusDelegateImpl.getInstance();		

			listaDominio = (List<CatalogoOpcionVO>) services
					.obtenerCatalogo(Constantes.CATALOGO_OPCION_DOMINIO);
			listaIdiomas = (List<CatalogoOpcionVO>) services
					.obtenerCatalogo(Constantes.CATALOGO_OPCION_IDIOMA);

			session.setAttribute("CATALOGO_OPCION_DOMINIO",
					listaDominio);
			session.setAttribute("CATALOGO_OPCION_IDIOMA",
					listaIdiomas);

		} catch (BusinessException e) {
			logger.error(e); // TODO Notificar error al usuario
		} catch (ServiceLocatorException e) {
			logger.error(e); // TODO Notificar error al usuario
		}

		OfertaBusDelegate services = OfertaBusDelegateImpl.getInstance();		
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
		if(session.getAttribute("listaIdiomas")==null)
		session.setAttribute("listaIdiomas", idiomasRegistro);
		// RegistroIdiomaVO

		session.setAttribute(BODY_JSP,
				mapping.findForward(JSP_CONOCIMIENTO).getPath());
		return mapping.findForward(JSP_IDIOMA);
	}

	
	public ActionForward sectoresInit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		List<CatalogoOpcionVO> catSectores=null;
		
		try {
			
			OfertaBusDelegate services = OfertaBusDelegateImpl.getInstance();
			String sid=request.getParameter("sid");
			if(sid!=null) request.getSession().setAttribute("sid", sid);
			else sid=(String)request.getSession().getAttribute("sid");
			
			if(sid!=null){
				long[] arr=new long[1];
				arr[0]=Utils.parseLong(sid);
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
			long[] arr = new long[]{Constantes.CATALOGO_OPCION_ENTIDAD_FEDERATIVA_NACIDO_EXTRANJERO};
			request.setAttribute("CATALOGO_OPCION_ENTIDAD_FEDERATIVA", services.obtenerCatalogoExcluyente(Constantes.CATALOGO_OPCION_ENTIDAD_FEDERATIVA, arr));

			//@SuppressWarnings("unchecked")
			List<CatalogoOpcionVO> catEntidades = (List<CatalogoOpcionVO>) request.getSession().getAttribute("CATALOGO_OPCION_ENTIDAD_FEDERATIVA");

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
				
				/*
				for (int j = 0; j < catEntidades.size(); j++) {
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
				}
				*/
				try {
					// para completar la información de las entidades seleccionadas con la descripción de éstas
					RegistroEntidadesVO entidadMunicipio = serviceDomicilio.obtieneEntidadMunicipio(ent.getEntidad(), ent.getMunicipio());
					if (entidadMunicipio != null){
						ent.setEntidadDescripcion(entidadMunicipio.getEntidadDescripcion());					
						ent.setMunicipioDescripcion(entidadMunicipio.getMunicipioDescripcion());						
					}					
				} catch (SQLException e) {
					logger.error("Error al invocar serviceDomicilio.obtieneEntidadMunicipio");
					e.printStackTrace();
				}
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

		long ident=Utils.parseLong((String)request.getSession().getAttribute("ident"));
		long idmun=Utils.parseLong((String)request.getSession().getAttribute("idmun"));
		
		
		
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
				item.setEntidad(Utils.parseLong(entidad));
				item.setMunicipio(Utils.parseLong(municipio));	
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
				/*
				for (int j = 0; j < catEntidades.size(); j++) {
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
		
		long entLong= Utils.parseLong(entidad);
		long munLong =Utils.parseLong(municipio);
		
		
		for(RegistroEntidadesVO ent: listaEntidades){
			
			if(ent.getEntidad()==entLong && ent.getMunicipio()==munLong)
			   return false;
			
		}
		
		
		if(entLong==ident && munLong==idmun)
			   return false;
		
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
			ArrayList<ElementoListaVO> carrerasSimilares = obtenerCarrerasSimilares(estudios, Utils.parseLong(carreraSel));
			
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

	public ActionForward editarUbicacion(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		// logger.info("Se registra el tiempo para el lapso de asignacion : "+
		// paramform.getTiempoReasignacion());

		// int minutos = paramform.getTiempoReasignacion();
		long idOferta = Utils.parseLong(request.getParameter("idOferta").toString());
		request.setAttribute("idOferta", idOferta+"");
		
		
		try {
			long idEmpresa = Utils.parseLong(request.getSession().getAttribute("idEmpresa").toString());
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
				entidad.setEntidad(Utils.parseLong(f
						.getPrefiereCandidatosEntidad()));
				entidad.setMunicipio(Utils.parseLong(f
						.getPrefiereCandidatosMunicipio()));
				listaEnt.add(entidad);
				request.getSession().setAttribute(
						"listaEntidadesSeleccionadas", listaEnt);
			}

			if (listaEntidadesSeleccionadas != null
					&& f.getPrefiereCandidatosEntidad() != null) {
				RegistroEntidadesVO entidad = new RegistroEntidadesVO();
				entidad.setEntidad(Utils.parseLong(f
						.getPrefiereCandidatosEntidad()));
				entidad.setMunicipio(Utils.parseLong(f
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

			// request.getSession().setAttribute("id_oferta", "" + idOferta);

		} catch (Exception e) {
			logger.error(e); // TODO Notificar error al usuario
		}

		// request.getSession().setAttribute(BODY_JSP,
		// mapping.findForward(FORWARD_NEXT).getPath());
		return mapping.findForward(FORWARD_NEXT);
	}

	@SuppressWarnings("unchecked")
	public ActionForward guardarRequisitos(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		
		long idOferta = Utils.parseLong(request.getParameter("idOferta").toString());
		request.setAttribute("idOferta", idOferta+"");
		
		// try {
		long idoferta = Utils.parseLong(session.getAttribute("idOferta").toString());

		ArrayList<RequisitoVO> listaConocimientos = (ArrayList<RequisitoVO>) session.getAttribute("listaConocimientos");
		ArrayList<RequisitoVO> listaHabilidades = (ArrayList<RequisitoVO>) session.getAttribute("listaHabilidades");
		ArrayList<RequisitoVO> listaCompetencias = (ArrayList<RequisitoVO>) session.getAttribute("listaCompetencias");
		ArrayList<RegistroIdiomaVO> listaIdiomas = (ArrayList<RegistroIdiomaVO>) session.getAttribute("listaIdiomas");
		ArrayList<String> listaCarrerasSeleccionadas = (ArrayList<String>) session.getAttribute("listaCarrerasSeleccionadas");
		ArrayList<String> listaSectoresSeleccionados = (ArrayList<String>) session.getAttribute("listaSectoresSeleccionados");
		ArrayList<RegistroEntidadesVO> listaEntidadesSeleccionadas = (ArrayList<RegistroEntidadesVO>) session.getAttribute("listaEntidadesSeleccionadas");
		
		String[] listaPrestaciones = (String[]) session.getAttribute("listaPrestaciones");
		
		RegistroRequisitosVO registroRequisitosVO = converterRequisitos(form,
				idoferta, listaConocimientos, listaHabilidades,
				listaCompetencias, listaIdiomas, listaCarrerasSeleccionadas,
				listaSectoresSeleccionados, listaEntidadesSeleccionadas,
				listaPrestaciones);

		session.setAttribute("registroRequisitosVO", registroRequisitosVO);
		/*
		 * services.guardarRequisitos(registroRequisitosVO);
		 * 
		 * } catch (BusinessException e) { logger.error(e); // TODO Notificar
		 * error al usuario } catch (ServiceLocatorException e) {
		 * logger.error(e); // TODO Notificar error al usuario }
		 */

		// request.getSession().setAttribute(BODY_JSP,
		// mapping.findForward(FORWARD_NEXT).getPath());
		return mapping.findForward(FORWARD_NEXT);
	}

	public ActionForward guardarContacto(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		boolean flag=false;
		HttpSession session = request.getSession();
            
		long idOferta = Utils.parseLong(request.getSession().getAttribute("idOferta").toString());
		request.setAttribute("idOferta", idOferta+"");

		UsuarioWebVO usuario = getUsuario(request.getSession());

		try {			
			//ANTES DE MODIFICAR ESTE PEDAZO, CHECAR CON HAYDEE long idEmpresa = Utils.parseLong(request.getSession().getAttribute("idEmpresa").toString());
			UsuarioWebVO us=this.getUsuario(request.getSession());
			long idEmpresa = us.getIdPropietario();			
			//System.out.println("--OfertaEdicionAction.guardarContacto-idEmpresa:" + idEmpresa);
			//long perfil = Utils.parseLong(request.getSession().getAttribute("perfil").toString());
			
			//UsuarioWebVO usuario = getUsuario(session);
			//long idEmpresa = usuario.getIdPropietario();
    		long idRegValidar=0;     		
    		if(null!=request.getSession().getAttribute(PARAM_ID_REGISTRO_POR_VALIDAR)){
    			idRegValidar = (Long)request.getSession().getAttribute(PARAM_ID_REGISTRO_POR_VALIDAR);
    		} 		
    		//System.out.println("--OfertaEdicionAction--idRegValidar:" + idRegValidar);
			
			RegistroContactoVO registroContactoVO = converterContacto(form,idOferta);
			registroContactoVO.setIdEmpresa(idEmpresa);
			
			RegistroUbicacionVO registroUbicacionVO = (RegistroUbicacionVO)session.getAttribute("registroUbicacionVO");
			RegistroRequisitosVO registroRequisitosVO = (RegistroRequisitosVO) session.getAttribute("registroRequisitosVO");

			OfertaBusDelegate services = OfertaBusDelegateImpl.getInstance();
			services.editarUbicacionTotal(registroUbicacionVO, registroRequisitosVO, registroContactoVO,
					                      usuario.getIdTipoUsuario(), usuario.getIdUsuario(), idRegValidar);

			//System.out.println("--OfertaEdicionAction--flag:" + flag);
			session.setAttribute("idOfertaEditada", idOferta + "");

		} catch (BusinessException e) {
			logger.error(e); // TODO Notificar error al usuario
		} catch (ServiceLocatorException e) {
			logger.error(e); // TODO Notificar error al usuario
		} catch (TechnicalException e) {
			logger.error(e); // TODO Notificar error al usuario
		} catch (IndexerException e) {
			logger.error(e); // TODO Notificar error al usuario
		} catch (Exception e) {
			logger.error(e); // TODO Notificar error al usuario
		}

		if(usuario.getIdTipoUsuario() == Constantes.TIPO_USUARIO.ADMINISTRADOR.getIdTipoUsuario()) {
			flag=true;
		}
		// request.getSession().setAttribute(BODY_JSP,
		// mapping.findForward(FORWARD_JSP).getPath());
		// return mapping.findForward(FORWARD_TEMPLATE_WORK);
		if(flag)
			return mapping.findForward("JSP_AUTORIZATION_ACTUALIZAR");
		    //return mapping.findForward(AUTORIZACION);		
		else
			return mapping.findForward(FORWARD_NEXT);
		
	}
	
	private RegistroContactoVO converterContacto(ActionForm forma, long idoferta) {
		RegistroContactoForm f = (RegistroContactoForm) forma;
		RegistroContactoVO vo = new RegistroContactoVO();

		vo.setIdOferta(idoferta);
		vo.setNombreEmpresa(Utils.parseLong(f.getNombreEmpresa()));
		vo.setNombreContacto(Utils.parseLong(f.getNombreContacto()));
		vo.setTelefonoContacto(f.getTeleconoContacto() != null ? Constantes.CONTACTO_TELEFONO.SI.getIdContactoTelefono() : Constantes.CONTACTO_TELEFONO.NO.getIdContactoTelefono());
		vo.setCorreoContacto(f.getCorreoContacto() != null ? Constantes.CONTACTO_CORREO.SI.getIdContactoCorreo() : Constantes.CONTACTO_CORREO.NO.getIdContactoCorreo());
		vo.setDomingo(f.getDomingo());
		vo.setLunes(f.getLunes());
		vo.setMartes(f.getMartes());
		vo.setMiercoles(f.getMiercoles());
		vo.setJueves(f.getJueves());
		vo.setViernes(f.getViernes());
		vo.setSabado(f.getSabado());
		vo.setHoraAtencionInicio(Utils.parseLong(f.getHoraAtencionInicio()));
		vo.setHoraAtencionFin(Utils.parseLong(f.getHoraAtencionFin()));
		vo.setDuracionAtencion(Utils.parseLong(f.getDuracionAtencion()));

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
		
		vo.setEstudios(Utils.parseLong(f.getEstudios()));
		if(f.getSituacionAcademica()!=null)
		vo.setSituacionAcademica(Utils.parseLong(f.getSituacionAcademica()));
		vo.setConocimientosGenerales(f.getConocimientosGenerales());
		vo.setAniosExperiencia(Utils.parseInt(f.getAniosExperiencia()));
		vo.setDisponibilidadViajar(Utils.parseInt(f.getDisponibilidadViajar()));
		vo.setDisponibilidadRadicar(Utils.parseInt(f.getDisponibilidadRadicar()));
		vo.setEdadRequerida(f.getEdadRequerida() != null ? Constantes.EDAD_REQUISITO.NO.getIdOpcion() : Constantes.EDAD_REQUISITO.SI.getIdOpcion());
		
		if(f.getEdadDe()!=null ){
			if(f.getEdadDe().length()>0)
				vo.setEdadDe(Utils.parseInt(f.getEdadDe()));
				vo.setEdadRequerida(Constantes.EDAD_REQUISITO.SI.getIdOpcion());
			}
		else
			vo.setEdadDe(0);		
		
		if(f.getEdadA()!=null ){
			if(f.getEdadA().length()>0)
				vo.setEdadA(Utils.parseInt(f.getEdadA()));
				}
		else{
			vo.setEdadA(0);
		}
		vo.setGenero(Utils.parseInt(f.getGenero()));
		vo.setObservaciones(f.getObservaciones());
		if(f.getConocimiento1()!=null){
			if(f.getConocimiento1().length()>0){
				vo.setConocimiento1(f.getConocimiento1());
				vo.setDominioConoc(Utils.parseInt(f.getDominioConoc()));
				vo.setAniosExperienciaConoc(Utils.parseInt(f.getAniosExperienciaConoc()));
		}}
		if(f.getHabilidad1()!=null){
			if(f.getHabilidad1().length()>0){
					
		vo.setHabilidad1(f.getHabilidad1());
		vo.setDominioHabilidad(Utils.parseInt(f.getDominioHabilidad()));
		vo.setAniosExperienciaHabilidad(Utils.parseInt(f
				.getAniosExperienciaHabilidad()));
		}}
		if(f.getCompetencia1()!=null){
			if(f.getCompetencia1().length()>0){
		vo.setCompetencia1(f.getCompetencia1());
		vo.setDominioComp(Utils.parseInt(f.getDominioComp()));
		vo.setAniosExperienciaComp(Utils.parseInt(f.getAniosExperienciaComp()));
		}}
		if(f.getIdioma1()!=null){
			if(f.getIdioma1().length()>0){
		vo.setIdioma1(Utils.parseLong(f.getIdioma1()));
		if(f.getCertificacionIdioma()!=null)
			if(f.getCertificacionIdioma().length()>0)
		vo.setCertificacionIdioma(Utils.parseLong(f.getCertificacionIdioma()));
		if(f.getDominioIdioma()!=null)
			if(f.getDominioIdioma().length()>0)
		vo.setDominioIdioma(Utils.parseLong(f.getDominioIdioma()));
		}}
		if(f.getCarrera()!=null)
			if(f.getCarrera().length()>0)
		vo.setCarrera(Utils.parseLong(f.getCarrera()));
		
		return vo;

	}

	private RegistroUbicacionVO converterUbicacion(ActionForm forma,
			long idEmpresa) {
		RegistroUbicacionForm f = (RegistroUbicacionForm) forma;
		RegistroUbicacionVO vo = new RegistroUbicacionVO();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		vo.setTituloOferta(f.getTituloOferta());
		vo.setArea(Utils.parseLong(f.getArea()));
		vo.setOcupacion(Utils.parseLong(f.getOcupacion()));
		vo.setSector(Utils.parseLong(f.getSector()));
		vo.setFunciones(f.getFunciones());
		vo.setHorario(Utils.parseLong(f.getHorario()));
		vo.setDomingo(f.getDomingo());
		vo.setLunes(f.getLunes());
		vo.setMartes(f.getMartes());
		vo.setMiercoles(f.getMiercoles());
		vo.setJueves(f.getJueves());
		vo.setViernes(f.getViernes());
		vo.setSabado(f.getSabado());
		vo.setHEntrada(f.getHEntrada());
		vo.setHSalida(f.getHSalida());
		vo.setRolarTurnos(Utils.parseInt(f.getRolarTurnos()));
		vo.setEmpresaOfrece(f.getEmpresaOfrece());
		vo.setSalario(Float.parseFloat(f.getSalario()));
		// vo.setTipoContacto(Utils.parseLong(f.getTipoContacto()));
		vo.setTipoContrato(Utils.parseLong(f.getTipoContrato()));
		vo.setPrestaciones(f.getPrestaciones());
		vo.setJerarquia(Utils.parseLong(f.getJerarquia()));
		vo.setNumeroPlazas(Utils.parseInt(f.getNumeroPlazas()));

		vo.setLimitePostulantes(Utils.parseInt(f.getLimitePostulantes()));

		vo.setDicapacidad(Utils.parseLong(f.getDicapacidad()));
		vo.setOrigenOferta(Utils.parseLong(f.getOrigenOferta()));
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

	@SuppressWarnings("unchecked")
	public ActionForward registrarConocimiento(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		RequisitosForm req = (RequisitosForm) form;	
		ArrayList<RequisitoVO> lista = null;
		RequisitoVO item;
		lista = (request.getSession().getAttribute("listaConocimientos") != null ? 
				(ArrayList<RequisitoVO>) request.getSession().getAttribute("listaConocimientos")
				: new ArrayList<RequisitoVO>());
		List<CatalogoOpcionVO> listaDominio = (List<CatalogoOpcionVO>) request.getSession().getAttribute("CATALOGO_OPCION_DOMINIO");
		List<CatalogoOpcionVO> listaExperiencia = (List<CatalogoOpcionVO>) request.getSession().getAttribute("CATALOGO_OPCION_EXPERIENCIA");
		
		String conoc = "";
		try {		
			conoc = req.getConocimiento();			
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
			if (conoc != null ){
				if (conoc.length() > 1 && noRepetirConoc(conoc,lista)) {
					item = new RequisitoVO();
					item.setDescripcion(conoc);
					item.setExperiencia(Utils.parseLong(exp));
					item.setDominio(Utils.parseLong(dom));
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
			}
		} catch(Exception e) {		
			e.printStackTrace();
		} 
		return mapping.findForward(NEXT_CONOCIMIENTO);
	}

	private boolean noRepetirConoc(String conoc,ArrayList<RequisitoVO> lista)
	{
		if(conoc==null) return false;		
		
		for(RequisitoVO item:lista){
			if(conoc.compareTo(item.getDescripcion())==0){
				return false;			
			}	
		}
		
		return true;
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
			if (conoc.length() > 1 && noRepetirConoc(conoc,lista)) {

				item = new RequisitoVO();
				item.setDescripcion(conoc);
				item.setExperiencia(Utils.parseLong(exp));
				item.setDominio(Utils.parseLong(dom));

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
			if (conoc.length() > 1 && noRepetirConoc(conoc,lista)) {
				item = new RequisitoVO();
				item.setDescripcion(conoc);
				item.setExperiencia(Utils.parseLong(exp));
				item.setDominio(Utils.parseLong(dom));

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
			if(item.getIdioma()==Utils.parseLong(conoc) )
			  return false;
		}
		if(Utils.parseLong(iid)==Utils.parseLong(conoc))
		   return false;
		
		return true;
	}
	
	@SuppressWarnings("unchecked")
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
			if (conoc.compareTo("-1") != 0 && noRepetirIdioma(conoc,lista,iid)) {
				item = new RegistroIdiomaVO();
				item.setIdioma(Utils.parseLong(conoc));
				item.setCertificacion(Utils.parseLong(exp));
				item.setDominio(Utils.parseLong(dom));				
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
				item.setEntidad(Utils.parseLong(ent));
				item.setMunicipio(Utils.parseLong(mun));
				lista.add(item);
				request.getSession().removeAttribute("listaEntidades");
				request.getSession().setAttribute("listaEntidades", lista);

			}

		return mapping.findForward(NEXT_ENTIDAD);
	}

	/**
	 * Obtener Idiomas
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws ServiceLocatorException
	 * @throws BusinessException
	 * @throws IOException
	 */
	public ActionForward obtenerIdioma(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		try {

			OfertaBusDelegate services = OfertaBusDelegateImpl.getInstance();
			List<CatalogoOpcionVO> opciones = services.obtenerCatalogo(Constantes.CATALOGO_OPCION_IDIOMA);

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
	
	
	
	
	/**
	 * Obtener Idiomas
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws ServiceLocatorException
	 * @throws BusinessException
	 * @throws IOException
	 */
	public ActionForward obtenerIdiomaSinNinguno(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		try {
			long[] arr= {1};			
			
			OfertaBusDelegate services = OfertaBusDelegateImpl.getInstance();
			List<CatalogoOpcionVO> opciones = services.obtenerCatalogoExcluyente(Constantes.CATALOGO_OPCION_IDIOMA,arr);

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

	/**
	 * Obtener certificacion.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws ServiceLocatorException
	 * @throws BusinessException
	 * @throws IOException
	 */
	public ActionForward obtenerCertificacion(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		RequisitosForm paramForm = (RequisitosForm) form;
		paramForm.reset(mapping, request);
		int idioma = Utils.parseInt((String) request.getParameter("idioma"));
		List<CatalogoOpcionVO> opciones = null;
		try {

			OfertaBusDelegate services = OfertaBusDelegateImpl.getInstance();

			switch (idioma) {

			case 2:
				opciones = services
						.obtenerCatalogo(Constantes.CERTIFICACION.ALEMAN
								.getIdOpcion());
				break;
			case 3:
				opciones = services
						.obtenerCatalogo(Constantes.CERTIFICACION.CHINO
								.getIdOpcion());
				break;
			case 4:
				opciones = services
						.obtenerCatalogo(Constantes.CERTIFICACION.FRANCES
								.getIdOpcion());
				break;
			case 5:
				opciones = services
						.obtenerCatalogo(Constantes.CERTIFICACION.INGLES
								.getIdOpcion());
				break;
			case 6:
				opciones = services
						.obtenerCatalogo(Constantes.CERTIFICACION.ITALIANO
								.getIdOpcion());
				break;
			case 7:
				opciones = services
						.obtenerCatalogo(Constantes.CERTIFICACION.JAPONES
								.getIdOpcion());
				break;

			}

			CatalogoVO cat =null;
			if(opciones!=null){
				cat=getCatalogo(opciones);
			String json = toJson(cat);
			redirectJsonResponse(response, json);
			}
			

		} catch (ServiceLocatorException e) {
			logger.error(e);
		} catch (BusinessException e) {
			logger.error(e);
		} catch (IOException e) {
			logger.error(e);
		}

		return null;
	}

	
	/**
	 * Obtener grados de estudios
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws ServiceLocatorException
	 * @throws BusinessException
	 * @throws IOException
	 */
	public ActionForward obtenerEstudios(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		try {
			OfertaBusDelegate services = OfertaBusDelegateImpl.getInstance();
			List<CatalogoOpcionVO> opciones = services.obtenerCatalogo(Constantes.CATALOGO_OPCION_GRADO_ESTUDIOS);

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
	
	public ActionForward obtenerCarreras(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		RegistroRequisitosForm paramForm = (RegistroRequisitosForm) form;
		paramForm.reset(mapping, request);

		int estudios = Utils.parseInt(request.getParameter("estudios"));

		String catalogojson = null;

		request.getSession().setAttribute("estudios", estudios+"");
		request.getSession().removeAttribute("listaCarreras");
				
		try {
			CatalogoOpcionDelegate services = CatalogoOpcionDelegateImpl.getInstance();
			
			if(estudios==Constantes.GRADO_ESTUDIOS.CARRERA_COMERCIAL.getIdOpcion()){
				catalogojson = services.consultarCatalogoJSON(CATALOGO.TECNICA_COMERCIAL_BASICA.getIdCatalogo());
			
			} else if(estudios==Constantes.GRADO_ESTUDIOS.CARRERA_TECNICA.getIdOpcion()){
				catalogojson = services.consultarCatalogoJSON((long)Constantes.CATALOGO.TECNICA_COMERCIAL_BASICA.getIdCatalogo());
		
			} else if(estudios==Constantes.GRADO_ESTUDIOS.DOCTORADO.getIdOpcion()){
				catalogojson = services.consultarCatalogoJSON((long)Constantes.CATALOGO.MAESTRIA_DOCTORADO.getIdCatalogo());		
			
			} else if(estudios==Constantes.GRADO_ESTUDIOS.LICENCIATURA.getIdOpcion()) {
				catalogojson = services.consultarCatalogoJSON((long)Constantes.CATALOGO.PROFESIONAL.getIdCatalogo());
		
			} else if(estudios==Constantes.GRADO_ESTUDIOS.MAESTRIA.getIdOpcion()){
				catalogojson = services.consultarCatalogoJSON((long)Constantes.CATALOGO.MAESTRIA_DOCTORADO.getIdCatalogo());
		
			} else if(estudios==Constantes.GRADO_ESTUDIOS.PROFESIONAL_TECNICO.getIdOpcion()){
				catalogojson = services.consultarCatalogoJSON((long)Constantes.CATALOGO.TECNICA_COMERCIAL_SUPERIOR.getIdCatalogo());
		
			} else if(estudios==Constantes.GRADO_ESTUDIOS.SUPERIOR_UNIVERSITARIO.getIdOpcion()){
				catalogojson = services.consultarCatalogoJSON((long)Constantes.CATALOGO.PROFESIONAL.getIdCatalogo());
		
			} else if(estudios==Constantes.GRADO_ESTUDIOS.SIN_INSTRUCCION.getIdOpcion()){
				catalogojson = getCatalogoVacioJSON();
			
			} else if(estudios==Constantes.GRADO_ESTUDIOS.SECUNDARIA.getIdOpcion()){
				catalogojson = getCatalogoVacioJSON();
		
			} else if(estudios==Constantes.GRADO_ESTUDIOS.PREPA_VOCACIONAL.getIdOpcion()){
				catalogojson = services.consultarCatalogoJSON((long)Constantes.CATALOGO.TECNICA_COMERCIAL_SUPERIOR.getIdCatalogo());
		
			} else if(estudios==Constantes.GRADO_ESTUDIOS.PRIMARIA.getIdOpcion()){
				catalogojson = getCatalogoVacioJSON();
		
			} else if(estudios==Constantes.GRADO_ESTUDIOS.LEER_ESCRIBIR.getIdOpcion()){
				catalogojson = getCatalogoVacioJSON();
			}

			if(catalogojson!=null){
				redirectJsonResponse(response, catalogojson);
			}

		} catch (ServiceLocatorException e) {
			logger.error(e);
		}  catch (IOException e) {
			logger.error(e);
		}

		return null;
	}
	
	private String getCatalogoVacioJSON(){
		List<CatalogoOpcionVO> opciones = new ArrayList<CatalogoOpcionVO>();
		
		CatalogoOpcionVO op = new CatalogoOpcionVO();
		op.setIdCatalogoOpcion(1);
		op.setOpcion("Ninguna");
		opciones.add(op);

		CatalogoVO cat = getCatalogo(opciones);
		String json = toJson(cat);
		
		return json;
	}
	
	public ActionForward areaLaboral(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();

		@SuppressWarnings("unchecked")
		List<CatalogoOpcionVO> opciones = (List<CatalogoOpcionVO>)session.getAttribute("CATALOGO_OPCION_AREA_LABORAL");		
		
		CatalogoVO cat = getCatalogo(opciones);
		String json = toJson(cat);
		redirectJsonResponse(response, json);
		
		return null;
	} 	
	
	
	public ActionForward verificaFechaInicial(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException {

	        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
            
	        String fecha =(String) request.getParameter("fecha");
			String resultado=null;
			
			try{
			if(sdf.parse(fecha).compareTo(new Date())<0){
				resultado="NO";
			}
			else{
				resultado="SI";
			}
			
			String json = toJson(resultado);
			redirectJsonResponse(response, json);
			}
			catch(ParseException e){
				e.printStackTrace();
			}
		
		return null;
	}
	
}
