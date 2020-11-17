/**
 * 
 */
package mx.gob.stps.portal.web.candidate.action;

import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_CANDIDATO_HABILIDAD;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_DOMINIO;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_ESTATUS_GRADO;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_EXPERIENCIA;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_GRADO_ESTUDIOS;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_IDIOMAS;
import static mx.gob.stps.portal.web.infra.utils.Constantes.BODY_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_TEMPLATE_MI_ESP_CAND;
import static mx.gob.stps.portal.web.infra.utils.Constantes.ID_CANDIDATO_INDEXABLE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.JSP_IDIOMA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TITULO_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.DESCRIPCION_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FACEBOOK_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TWITTER_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.URL_ESPECIFICA;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.gob.stps.portal.core.candidate.vo.CandidatoOtroEstudioVO;
import mx.gob.stps.portal.core.candidate.vo.ComputacionAvanzadaVO;
import mx.gob.stps.portal.core.candidate.vo.ConocerConfigVO;
import mx.gob.stps.portal.core.candidate.vo.ConocimientoComputacionVO;
import mx.gob.stps.portal.core.candidate.vo.EscolaridadVO;
import mx.gob.stps.portal.core.candidate.vo.GradoAcademicoVO;
import mx.gob.stps.portal.core.candidate.vo.IdiomaVO;
import mx.gob.stps.portal.core.candidate.vo.PerfilVO;
import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.exception.TechnicalException;
import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.core.infra.utils.Constantes.COMPU_AVANZADA;
import mx.gob.stps.portal.core.infra.utils.Constantes.COMPU_BASICA;
import mx.gob.stps.portal.core.infra.utils.Constantes.CONOC_HAB;
import mx.gob.stps.portal.core.infra.utils.Constantes.ESCOLARIDAD;
import mx.gob.stps.portal.core.infra.utils.Constantes.MULTIREGISTRO;
import mx.gob.stps.portal.core.infra.utils.Constantes.PUBLICAR_ESTANDARES;
import mx.gob.stps.portal.core.search.exception.IndexerException;
import mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO;
import mx.gob.stps.portal.persistencia.vo.ConocimientoHabilidadVO;
import mx.gob.stps.portal.persistencia.vo.EstandarConocerVO;
import mx.gob.stps.portal.web.candidate.delegate.CandidatoBusDelegate;
import mx.gob.stps.portal.web.candidate.delegate.CandidatoBusDelegateImpl;
import mx.gob.stps.portal.web.candidate.form.EscolaridadForm;
import mx.gob.stps.portal.web.candidate.vo.EscolaridadVOForm;
import mx.gob.stps.portal.web.infra.action.GenericAction;
import mx.gob.stps.portal.web.infra.delegate.CatalogoOpcionDelegateImpl;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.infra.utils.PropertiesLoader;
import mx.gob.stps.portal.web.infra.utils.Utils;
import mx.gob.stps.portal.web.infra.vo.ResultVO;
import mx.gob.stps.portal.web.offer.wrapper.PerfilJB;
import mx.gob.stps.portal.web.security.vo.UsuarioWebVO;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Clase Action que administra los datos de escolaridad de un candidato
 * @author Felipe Juarez Ramirez
 * @since 08/03/2011
 */
public class EscolaridadAction extends GenericAction {
	
	private static Logger logger = Logger.getLogger(EscolaridadAction.class);
	private int NUMERO_MAXIMO_DE_REGISTROS_DE_IDIOMA_POR_CANDIDATO = 3;
	private int sumarParaLibrarIndiceCero = 1;
	private long OPCION_NINGUNA_CERTIFICACION = 1L;
	private long OPCION_OTRA_CERTIFICACION = 2L;
	private static int NUMERO_MAXIMO_DE_REGISTROS_ADICIONALES_PERMITIDOS = 2;
	private long[] filtro_idioma = {Constantes.IDIOMAS.NO_REQUISITO.getIdOpcion()};

	
	/* (non-Javadoc)
	 * @see mx.gob.stps.portal.web.infra.action.GenericAction#init(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		EscolaridadForm escForm = (EscolaridadForm) form;
		escForm.reset();
		UsuarioWebVO usuario = getUsuario(session);
		if (usuario!=null) {
			escForm.setIdCandidato(usuario.getIdPropietario());
		}
		List<CatalogoOpcionVO> opcGrados= new ArrayList<CatalogoOpcionVO>();
		List<CatalogoOpcionVO> opcIdiomas= new ArrayList<CatalogoOpcionVO>();
		PerfilVO perfil = new PerfilVO();
		int estatusCV = 0;
		try {

			cargarCatalogosIdiomasEnForm(escForm);			
			List<IdiomaVO> listIdiomas = obtenerIdiomasCandidato(escForm.getIdCandidato());
			request.getSession().setAttribute("IDIOMAS_LIST",listIdiomas );						
			/**getOptionsSelectedLang(langsList, request);
			getOptionsSelectedLangCert(langsList, request);
			getOptionsSelectedLangDom(langsList, request);**/		
			inicializarDatosDeIdiomasEnForm(listIdiomas, escForm);
			CandidatoBusDelegate services = CandidatoBusDelegateImpl.getInstance();
			PerfilJB perfilJB = services.loadPerfil(usuario.getIdPropietario());
			List<ConocimientoHabilidadVO> conocsHabsVO = perfilJB.getConocimientos();
			this.setConocimientoForm(escForm, perfilJB.getConocimientoPrincipal());
			request.getSession().setAttribute("KNOWLEDGE_LIST", conocsHabsVO);
			List<ConocimientoHabilidadVO> skills = new ArrayList<ConocimientoHabilidadVO>();//ya no se utiliza
			request.getSession().setAttribute("SKILLS_LIST", skills);
			List<CandidatoOtroEstudioVO> otherStudies = services.otrosEstudiosList(escForm.getIdCandidato());			
			request.getSession().setAttribute("OTHER_STUDIES_LIST", otherStudies);
			//Carga los datos del perfil laboral
			perfil = services.initPerfilCandidato(escForm.getIdCandidato());
			estatusCV = services.getEstatusCV(escForm.getIdCandidato());
			
			//PPC
			PerfilVO perfilvo = services.initPerfil(escForm.getIdCandidato());
			escForm.setPPCStatusForm(perfilvo.getIdTrabaja(),perfilvo.getIdEstatusPPC(),false);
			//CandidatoVo candidatoVO = services.buscarDatosHeaderTemplateCandidato(escForm.getIdCandidato());
			List<Long> idHabilidades = services.consultaHabilidades(escForm.getIdCandidato());
			if (idHabilidades!=null){
				long[] idHabilidad = new long[idHabilidades.size()];
				
				int index = 0;
				for (Long id : idHabilidades){
					idHabilidad[index] = id;
					index++;
				}

				escForm.setIdHabilidad(idHabilidad);
			}
			
			if (perfil != null) {
				BeanUtils.copyProperties(escForm, perfil);
				//if (escForm.getSinEstudios() == ESCOLARIDAD.CON_ESTUDIOS.getIdOpcion()) {
					//Carga el grado academico principal
					List<GradoAcademicoVO> gradosVO = services.initGrados(escForm.getIdCandidato(), MULTIREGISTRO.PRINCIPAL.getIdOpcion());					
					if (gradosVO != null && !gradosVO.isEmpty()) {
						BeanUtils.copyProperties(escForm, gradosVO.get(0));
					}

				//Carga el apoyo prospera
				escForm.setApoyoProspera(perfilvo.getApoyoProspera());
				escForm.setFolioProspera(perfilvo.getFolioProspera());
				escForm.setFolioIntegranteProspera(perfilvo.getFolioIntegranteProspera());

					//FIXME: Por que hacemos esto otra vez? Checar contra setIdiomasForm
					//Carga el idioma principal
					/* 
					List<IdiomaVO> idiomasVO = services.initIdiomas(escForm.getIdCandidato(),MULTIREGISTRO.PRINCIPAL.getIdOpcion());
					if (idiomasVO != null && !idiomasVO.isEmpty()) {
						BeanUtils.copyProperties(escForm, idiomasVO.get(0));
					}
					*/

					/**Carga el conocimiento principal
					List<ConocimientoHabilidadVO> conocimientosVO = services.initConocsHabs(escForm.getIdCandidato(), CONOC_HAB.CONOCIMIENTO.getIdOpcion(),MULTIREGISTRO.PRINCIPAL.getIdOpcion());
					if (conocimientosVO != null && !conocimientosVO.isEmpty()) {
						this.setConocimientoForm(escForm, conocimientosVO.get(0));
					} else {
						escForm.setIdTipoConocimiento(CONOC_HAB.CONOCIMIENTO.getIdOpcion());
					}
					
					//Carga la habilidad principal
					List<ConocimientoHabilidadVO> habilidadesVO = services.initConocsHabs(escForm.getIdCandidato(), CONOC_HAB.HABILIDAD.getIdOpcion(), MULTIREGISTRO.PRINCIPAL.getIdOpcion());
					if (habilidadesVO != null && !habilidadesVO.isEmpty()) {
						this.setHabilidadForm(escForm, habilidadesVO.get(0));
					} else {
						escForm.setIdTipoHabilidad(CONOC_HAB.HABILIDAD.getIdOpcion());
					}
					Si tiene computacion avanzada
					if (escForm.getComputacionAvanzada() == COMPU_AVANZADA.SI.getIdOpcion()) {
						//Carga computacion avanzada principal
						List<ComputacionAvanzadaVO> compuAvanzadasVO = services.initCompuAvanzadas(escForm.getIdCandidato(), MULTIREGISTRO.PRINCIPAL.getIdOpcion());
						if (compuAvanzadasVO != null && !compuAvanzadasVO.isEmpty())
							this.setCompuAvanForm(escForm, compuAvanzadasVO.get(0));**/
				
					ConocimientoComputacionVO conocimientosComputacion = services.findConocimientosComputacion(escForm.getIdCandidato());
					if (null != conocimientosComputacion) {
						escForm.setConocimientoCompProcesadorTxt(conocimientosComputacion.getProcesadorTxt());
						escForm.setConocimientoCompHojaCal(conocimientosComputacion.getHojaCalculo());
						escForm.setConocimientoCompInternet(conocimientosComputacion.getInternet());
						escForm.setConocimientoCompRedes(conocimientosComputacion.getRedesSociales());
						escForm.setConocimientoCompOtros(conocimientosComputacion.getOtros());
						escForm.setIdConocimientoComputacion(conocimientosComputacion.getIdConocimientoComputacion());
						if (conocimientosComputacion.getProcesadorTxt() == 0 && conocimientosComputacion.getHojaCalculo() == 0 && conocimientosComputacion.getInternet() == 0 && conocimientosComputacion.getRedesSociales() == 0 && null == conocimientosComputacion.getOtros())
							escForm.setConocimientoCompNinguno(1);
					}else
						escForm.setConocimientoCompNinguno(1);
				//}
				
				//Consulta webservice Conocer
				request.setAttribute("tieneCertificacionesSI", PUBLICAR_ESTANDARES.SI.getIdOpcion());
				request.setAttribute("publicaCertificacionesCvSI", PUBLICAR_ESTANDARES.SI.getIdOpcion());
				request.setAttribute("publicaCertificacionesCvNO", PUBLICAR_ESTANDARES.NO.getIdOpcion());
				
				ConocerConfigVO conocerConfig = services.consultaConocerConfigByIdCandidato(escForm.getIdCandidato());

				if (conocerConfig != null && conocerConfig.getDeseaPublicar() != null && conocerConfig.getDeseaPublicar() == PUBLICAR_ESTANDARES.SI.getIdOpcion()){						
						
						escForm.setMostrarCertificacionesEnCV(PUBLICAR_ESTANDARES.SI.getIdOpcion());
						try{
							List<EstandarConocerVO> estandares = services.consultaConocer(escForm.getIdCandidato());
							
							if (estandares != null && !estandares.isEmpty()){
								escForm.setListaCertificaciones(estandares);
								escForm.setTieneCertificaciones(PUBLICAR_ESTANDARES.SI.getIdOpcion());
							} else{
								escForm.setTieneCertificaciones(PUBLICAR_ESTANDARES.NO.getIdOpcion());								
							}
								
						} catch(Exception e){
							logger.error("Error al consultar los estandares Conocer del candidato");
							e.printStackTrace();
						}
						
				} else{
						
						escForm.setMostrarCertificacionesEnCV(PUBLICAR_ESTANDARES.NO.getIdOpcion());
				}

			} else {
				//FIXME: QUE PASA SI NO TIENE PERFIL?
				/**/
				//Carga el grado academico principal
				List<GradoAcademicoVO> gradosVO = services.initGrados(escForm.getIdCandidato(), MULTIREGISTRO.PRINCIPAL.getIdOpcion());					
				if (gradosVO != null && !gradosVO.isEmpty()) {
					BeanUtils.copyProperties(escForm, gradosVO.get(0));
				}
				//FIXME: por que estamos haciendo esto otra vez? Checar contra setIdiomasForm
				//Carga el idioma principal
				/*
				List<IdiomaVO> idiomasVO = services.initIdiomas(escForm.getIdCandidato(),MULTIREGISTRO.PRINCIPAL.getIdOpcion());
				if (idiomasVO != null && !idiomasVO.isEmpty()) {
					BeanUtils.copyProperties(escForm, idiomasVO.get(0));
				}*/

				/**Carga el conocimiento principal
				List<ConocimientoHabilidadVO> conocimientosVO = services.initConocsHabs(escForm.getIdCandidato(), CONOC_HAB.CONOCIMIENTO.getIdOpcion(),MULTIREGISTRO.PRINCIPAL.getIdOpcion());
				if (conocimientosVO != null && !conocimientosVO.isEmpty()) {
					this.setConocimientoForm(escForm, conocimientosVO.get(0));
				} else {
					escForm.setIdTipoConocimiento(CONOC_HAB.CONOCIMIENTO.getIdOpcion());
				}
				
				//Carga la habilidad principal
				List<ConocimientoHabilidadVO> habilidadesVO = services.initConocsHabs(escForm.getIdCandidato(), CONOC_HAB.HABILIDAD.getIdOpcion(), MULTIREGISTRO.PRINCIPAL.getIdOpcion());
				if (habilidadesVO != null && !habilidadesVO.isEmpty()) {
					this.setHabilidadForm(escForm, habilidadesVO.get(0));
				} else {
					escForm.setIdTipoHabilidad(CONOC_HAB.HABILIDAD.getIdOpcion());
				}**/
				
				ConocimientoComputacionVO conocimientosComputacion = services.findConocimientosComputacion(escForm.getIdCandidato());
				if (null != conocimientosComputacion) {
					escForm.setConocimientoCompProcesadorTxt(conocimientosComputacion.getProcesadorTxt());
					escForm.setConocimientoCompHojaCal(conocimientosComputacion.getHojaCalculo());
					escForm.setConocimientoCompInternet(conocimientosComputacion.getInternet());
					escForm.setConocimientoCompRedes(conocimientosComputacion.getRedesSociales());
					escForm.setConocimientoCompOtros(conocimientosComputacion.getOtros());
					escForm.setIdConocimientoComputacion(conocimientosComputacion.getIdConocimientoComputacion());
					if (conocimientosComputacion.getProcesadorTxt() == 0 && conocimientosComputacion.getHojaCalculo() == 0 && conocimientosComputacion.getInternet() == 0 && conocimientosComputacion.getRedesSociales() == 0 && null == conocimientosComputacion.getOtros()){
						escForm.setConocimientoCompNinguno(1);
					}						
				} else {
					escForm.setConocimientoCompNinguno(1);				
				}				
			}
			
			if (session.getAttribute("depGrado") == null) {
				opcGrados = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(CATALOGO_OPCION_GRADO_ESTUDIOS, true);				
				//Carga en sesion los catalogos asociados a cada opcion
				String[] depCat = new String[opcGrados.size() + sumarParaLibrarIndiceCero];
				depCat[0] = "0";
				
				for (CatalogoOpcionVO opcion : opcGrados ) {
					depCat[(int) opcion.getIdCatalogoOpcion()] = String.valueOf(opcion.getIdCatalagoAsociado());
				}
				
				session.setAttribute("depGrado", depCat);
			}
			
			if (session.getAttribute("depIdioma") == null) {
				opcIdiomas = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(CATALOGO_OPCION_IDIOMAS, filtro_idioma);
				// Carga en sesion los catalogos asociados a cada opcion
				String[] depCat = new String[opcIdiomas.size() + sumarParaLibrarIndiceCero];
				depCat[0] = "0";

				for (CatalogoOpcionVO opcion : opcIdiomas) {
					depCat[(int) opcion.getIdCatalogoOpcion()] = String.valueOf(opcion.getIdCatalagoAsociado());
					//System.out.println("EscolaridadAction.init depCat[" + opcion.getIdCatalogoOpcion() + "] = " + opcion.getIdCatalagoAsociado());					
				}
				session.setAttribute("depIdioma", depCat);
			}
			
			//Sube constantes a sesion para presentar checkbox
			if (session.getAttribute("sinEstudiosSI") == null)
				session.setAttribute("sinEstudiosSI", ESCOLARIDAD.CON_ESTUDIOS.getIdOpcion());
			if (session.getAttribute("sinEstudiosNO") == null)
				session.setAttribute("sinEstudiosNO", ESCOLARIDAD.SIN_ESTUDIOS.getIdOpcion());
			if (session.getAttribute("cmpBasicaSI") == null)
				session.setAttribute("cmpBasicaSI", COMPU_BASICA.SI.getIdOpcion());
			if (session.getAttribute("cmpBasicaNO") == null)
				session.setAttribute("cmpBasicaNO", COMPU_BASICA.NO.getIdOpcion());
			if (session.getAttribute("cmpAvanSI") == null)
				session.setAttribute("cmpAvanSI", COMPU_AVANZADA.SI.getIdOpcion());
			if (session.getAttribute("cmpAvanNO") == null)
				session.setAttribute("cmpAvanNO", COMPU_AVANZADA.NO.getIdOpcion());
			
			//Datos para los tabs
			session.setAttribute("estatusCV", estatusCV);
			session.setAttribute("thisTabId", Constantes.EVALUA_CV_FLUJO_PANTALLA.ESCOLARIDAD.getIdOpcion());
			session.setAttribute("thisTabName", Constantes.EVALUA_CV_FLUJO_PANTALLA.ESCOLARIDAD.getOpcion());
			session.setAttribute("thisTabPercent", Constantes.EVALUA_CV_FLUJO_PANTALLA.ESCOLARIDAD.getPorcentaje());
			session.setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
			
			session.setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
			
			cargaCatalogos(session);		
			
		} catch (ServiceLocatorException e) {logger.error(e); e.printStackTrace();
		} catch (SQLException e) {logger.error(e); e.printStackTrace();
		} catch (IllegalAccessException e) {logger.error(e); e.printStackTrace();
		} catch (InvocationTargetException e) {logger.error(e); e.printStackTrace();
		} catch (BusinessException e) {logger.error(e); e.printStackTrace(); }

		PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Escolaridad y otros conocimientos");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Perfil, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
		return mapping.findForward(FORWARD_TEMPLATE_MI_ESP_CAND);
	}
	
	private List<IdiomaVO> obtenerIdiomasCandidato(long idCandidato){
		CandidatoBusDelegate services = CandidatoBusDelegateImpl.getInstance();
		List<IdiomaVO> langsList = null;
		try {
			langsList = services.buscarIdiomas(idCandidato);		
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return langsList;
	}
		
	private void cargarCatalogosIdiomasEnForm(EscolaridadForm escForm){
		try {
			escForm.setCatalogoIdiomas(CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(CATALOGO_OPCION_IDIOMAS, filtro_idioma, true));
			escForm.setCatalogoDominios(CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(CATALOGO_OPCION_DOMINIO, true));							
			escForm.setIdiomasDependientes(obtenerCatalogosAsociadosDeIdiomas());
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
			logger.error(e);
		}			
	}
	
	private String[] obtenerCatalogosAsociadosDeIdiomas(){		
		String[] idiomasDependientes = null;
		try{
			List<CatalogoOpcionVO> idiomas = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(CATALOGO_OPCION_IDIOMAS, filtro_idioma, true);			
			idiomasDependientes = new String[idiomas.size()+sumarParaLibrarIndiceCero];
			idiomasDependientes[0] = "0";

			for (CatalogoOpcionVO opcion : idiomas) {
				idiomasDependientes[(int) opcion.getIdCatalogoOpcion()] = String.valueOf(opcion.getIdCatalagoAsociado());	
			}
			
		}catch(Exception ex){
			logger.error(ex);
			ex.printStackTrace();
		}
		return idiomasDependientes;
	}
	
	
	
	/** Cargando catalogo en la session
	 * @param session
	 */
	private void cargaCatalogos(HttpSession session){	
		
		long[] filtro_experiencia = {1,8};
		
		try {
			List<CatalogoOpcionVO> opcGrados 		= CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(CATALOGO_OPCION_GRADO_ESTUDIOS, true);
			List<CatalogoOpcionVO> opcDominio 		= CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(CATALOGO_OPCION_DOMINIO, true);			
			List<CatalogoOpcionVO> opciExperiencia 	= CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(CATALOGO_OPCION_EXPERIENCIA, filtro_experiencia, true);
			List<CatalogoOpcionVO> opciEstatus 		= CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(CATALOGO_OPCION_ESTATUS_GRADO, true);			
			List<CatalogoOpcionVO> opcIdioma		= CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(CATALOGO_OPCION_IDIOMAS, filtro_idioma, true);
			List<CatalogoOpcionVO> opcHabilidades	= CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(CATALOGO_OPCION_CANDIDATO_HABILIDAD, true);
			
			session.setAttribute("opcGrados", opcGrados);
			session.setAttribute("opcDominio", opcDominio);
			session.setAttribute("opciExperiencia", opciExperiencia);
			session.setAttribute("opciEstatus", opciEstatus);
			session.setAttribute("opcIdioma", opcIdioma);
			session.setAttribute("opcHabilidades", opcHabilidades);						

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		
	}
	
	
	
	/**
	 * Recibe el formulario de Escolaridad para transformarlo y enviarlo a capa
	 *  de negocio.
	 * @author Felipe Juarez Ramirez
	 * @since 09/03/2011
	 * @param ActionMapping
	 * @param ActionForm
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @return ActionForward
	 * @throws IOException 
	 **/
	public ActionForward registrar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		EscolaridadForm escForm = (EscolaridadForm) form;
		EscolaridadVO escolaridad = new EscolaridadVO();
		this.setEscolaridad(escForm, escolaridad, request);		
		
		UsuarioWebVO usuario = getUsuario(request.getSession());
		if (usuario!=null){
			escolaridad.setIdCandidato(usuario.getIdPropietario());
			escolaridad.setIdUsuario(usuario.getIdUsuario());
		}

		int conocimientoCompProcesadorTxt = null != request.getParameter("conocimientoCompProcesadorTxt") ? 1 : 0;
		int conocimientoCompHojaCal =  null != request.getParameter("conocimientoCompHojaCal") ? 1 : 0;
		int conocimientoCompInternet = null != request.getParameter("conocimientoCompInternet") ? 1 : 0;
		int conocimientoCompRedes =  null != request.getParameter("conocimientoCompRedes") ? 1 : 0;
		//int conocimientoCompNinguno  =  null != request.getParameter("conocimientoCompNinguno") ? 1 : 0;
		
		try {
			CandidatoBusDelegate services = CandidatoBusDelegateImpl.getInstance();
			escolaridad = services.registrarEscolaridad(escolaridad);
			
			/** Se agrega el indicador para indexar al Candidato al FINALIZA su Session **/
			request.getSession().setAttribute(ID_CANDIDATO_INDEXABLE, escolaridad.getIdCandidato());
			//BolsasTrabajoBusDelegateImpl.getInstance().indexaCandidato(escolaridad.getIdCandidato());

			if (escolaridad.getSinEstudios() == ESCOLARIDAD.CON_ESTUDIOS.getIdOpcion()) {				
				escForm.setIdCandidatoGradoAcademico(escolaridad.getGrado().getIdCandidatoGradoAcademico());
				if(null!=escolaridad.getIdioma())					
					escForm.setIdCandidatoIdioma(escolaridad.getIdioma().getIdCandidatoIdioma());
				if(null!=escolaridad.getConocimiento())
					escForm.setIdCandidatoConocimiento(escolaridad.getConocimiento().getIdCandidatoConocimHabilidad());
				if(null!=escolaridad.getHabilidad())
					escForm.setIdCandidatoHabilidad(escolaridad.getHabilidad().getIdCandidatoConocimHabilidad());
			}
			
			if (conocimientoCompProcesadorTxt == 1 || conocimientoCompHojaCal == 1 || conocimientoCompInternet == 1 || conocimientoCompRedes == 1 || escForm.getConocimientoCompOtros().length() > 4)
				escForm.setConocimientoCompNinguno(0);
			else 
				escForm.setConocimientoCompNinguno(1);
			
			long idConocimientoComputacion = (escForm.getIdConocimientoComputacion() > 0 ? escForm.getIdConocimientoComputacion() : 0);
			
			if (usuario!=null){
				idConocimientoComputacion = services.actualizarCandidatoComputacion(idConocimientoComputacion, usuario.getIdPropietario(), conocimientoCompProcesadorTxt, conocimientoCompHojaCal, conocimientoCompInternet, conocimientoCompRedes, escForm.getConocimientoCompOtros());				
			}
			
			if (idConocimientoComputacion != -1)
				escForm.setIdConocimientoComputacion(idConocimientoComputacion);
			
			saveAddStudieList(request);
			saveAddLangList(request, escForm);
			saveAddKnowledgeList(request, escForm);
			//saveAddSkillList(request);			
			saveConocerPreferencias(form);	
			String mensaje = getMensaje(request,"can.guardar.ext");
			if (escForm.isMensajeExtra())
				mensaje = mensaje + "  \n " +  EscolaridadForm.getNotSetNoToTrabajando();
			escForm.setMsg(new ResultVO(mensaje, ResultVO.TYPE_SUCCESS));
		} catch (BusinessException e) {
			escForm.setMsg(new ResultVO(super.getMensaje(request, "can.guardar.err"), ResultVO.TYPE_ERROR));
			e.printStackTrace();
			logger.error("Excepcion de negocio al persistir escolaridad del candidato " + escolaridad.getIdCandidato(), e);
		} catch (ServiceLocatorException e) {
			escForm.setMsg(new ResultVO(super.getMensaje(request, "can.guardar.err"), ResultVO.TYPE_ERROR));
			e.printStackTrace();
			logger.error("Excepcion de localizacion de servicio al persistir escolaridad del candidato " + escolaridad.getIdCandidato(), e);
		} catch (PersistenceException e) {
			escForm.setMsg(new ResultVO(super.getMensaje(request, "can.guardar.err"), ResultVO.TYPE_ERROR));
			e.printStackTrace();
			logger.error("Excepcion al persistir escolaridad del candidato " + escolaridad.getIdCandidato(), e);
		} catch (TechnicalException e) {
			escForm.setMsg(new ResultVO(super.getMensaje(request, "can.guardar.err"), ResultVO.TYPE_ERROR));
			e.printStackTrace();
			logger.error("Excepcion tecnica al persistir escolaridad del candidato " + escolaridad.getIdCandidato(), e);
		} catch (SQLException e) {
			escForm.setMsg(new ResultVO(super.getMensaje(request, "can.guardar.err"), ResultVO.TYPE_ERROR));
			e.printStackTrace();
			logger.error("Excepcion de sql al persistir escolaridad del candidato " + escolaridad.getIdCandidato(), e);
		} catch (IndexerException e) {
			escForm.setMsg(new ResultVO(super.getMensaje(request, "aut.autorizacion.msg.indexer.error"), ResultVO.TYPE_ERROR));
			e.printStackTrace();
			logger.error("Excepcion al indexar candidato " + escolaridad.getIdCandidato(), e);
		} catch (Exception e) {
			escForm.setMsg(new ResultVO(super.getMensaje(request, "can.guardar.err"), ResultVO.TYPE_ERROR));
			e.printStackTrace();
			logger.error("Excepcion al persistir candidato " + escolaridad.getIdCandidato(), e);
		}
		
		EscolaridadVOForm vo = getEscolaridadVOForm(escForm);
		String json = toJson(vo);
		
		try {
			redirectJsonResponse(response, json);
		} catch (IOException e) {logger.error(e);}
		return null;
	}

	/**
	 * Copia los datos de Escolaridad del ActionForm al VO.
	 * @author Felipe Juarez Ramirez
	 * @since 09/03/2011
	 * @param EscolaridadVOForm (origen)
	 * @param EscolaridadVO (destino)
	 * @return void
	 **/
	private void setEscolaridad(EscolaridadForm origen, EscolaridadVO destino, HttpServletRequest request) {
		//Copia los atributos no repetidos (Arreglos)
		destino.setIdCandidato(origen.getIdCandidato());
		destino.setSinEstudios(ESCOLARIDAD.CON_ESTUDIOS.getIdOpcion());
		destino.setApoyoProspera(origen.getApoyoProspera());
		destino.setFolioProspera(origen.getFolioProspera());
		destino.setFolioIntegranteProspera(origen.getFolioIntegranteProspera());
		
		//Si tiene estudios
		if (destino.getSinEstudios() == ESCOLARIDAD.CON_ESTUDIOS.getIdOpcion()) {
			try {
				//Obtiene grado academico
				GradoAcademicoVO gradoVO = new GradoAcademicoVO();
				BeanUtils.copyProperties(gradoVO, origen);
				gradoVO.setPrincipal(MULTIREGISTRO.PRINCIPAL.getIdOpcion());
				destino.setGrado(gradoVO);
				//FIXME: Por que hacemos esto?????
				//Obtiene idioma
				IdiomaVO idiomaVO = new IdiomaVO();
				if(origen.getIdCandidatoIdioma()>0){
					BeanUtils.copyProperties(idiomaVO, origen);
				} else {
					idiomaVO.setIdCertificacion(Utils.parseLong(request.getParameter("idCertificacion")));
					idiomaVO.setIdDominio(Utils.parseLong(request.getParameter("idDominio")));
					idiomaVO.setIdIdioma(Utils.parseLong(request.getParameter("idIdiomaSelect")));					
				}				
				idiomaVO.setPrincipal(MULTIREGISTRO.PRINCIPAL.getIdOpcion());
				destino.setIdioma(idiomaVO);
			} catch (IllegalAccessException e) {
				logger.error(e);
			} catch (InvocationTargetException e) {
				logger.error(e);
			}
			/**Obtiene Conocimiento
			ConocimientoHabilidadVO conocimiento = new ConocimientoHabilidadVO();
			this.setConocimientoVO(conocimiento, origen);
			destino.setConocimiento(conocimiento);
			//Obtiene Habilidad
			ConocimientoHabilidadVO habilidad = new ConocimientoHabilidadVO();
			this.setHabilidadVO(habilidad, origen);
			destino.setHabilidad(habilidad);**/
			//Obtiene computacion basica
			destino.setComputacionBasica(origen.getComputacionBasica());
			//Si tiene computacion basica
			if (destino.getComputacionBasica() == COMPU_BASICA.SI.getIdOpcion()) {
				destino.setIdExperienciaCompu(origen.getIdExperienciaCompu());
				destino.setIdDominioCompu(origen.getIdDominioCompu());
				destino.setIdExperienciaInternet(origen.getIdExperienciaInternet());
				destino.setIdDominioInternet(origen.getIdDominioInternet());
				destino.setIdExperienciaOffice(origen.getIdExperienciaOffice());
				destino.setIdDominioOffice(origen.getIdDominioOffice());
			}
			//Obtiene computacion avanzada
			destino.setComputacionAvanzada(origen.getComputacionAvanzada());
			//Si tiene computacion avanzada
			if (destino.getComputacionAvanzada() == COMPU_AVANZADA.SI.getIdOpcion()) {
				ComputacionAvanzadaVO compuAvanVO = new ComputacionAvanzadaVO();
				this.setCompuAvanVO(compuAvanVO, origen);
				destino.setCompAvanzada(compuAvanVO);
			}
		} else {
			destino.setComputacionBasica(COMPU_BASICA.NO.getIdOpcion());
			destino.setComputacionAvanzada(COMPU_AVANZADA.NO.getIdOpcion());
		}
		
		destino.setIdHabilidad(origen.getIdHabilidad());

	}
	
	/**private void setHabilidadForm(EscolaridadForm destino, ConocimientoHabilidadVO origen) {
		destino.setIdCandidatoHabilidad(origen.getIdCandidatoConocimHabilidad());
		destino.setIdTipoHabilidad(origen.getIdTipoConocimHabilidad());
		destino.setHabilidad(origen.getConocimientoHabilidad());
		destino.setIdExperienciaHab(origen.getIdExperiencia());
		destino.setIdDominioHab(origen.getIdDominio());
		destino.setDescripcionHab(origen.getDescripcion());
	}
	
	private void setHabilidadVO(ConocimientoHabilidadVO destino, EscolaridadForm origen) {
		destino.setIdCandidatoConocimHabilidad(origen.getIdCandidatoHabilidad());
		destino.setIdTipoConocimHabilidad(Constantes.CONOC_HAB.HABILIDAD.getIdOpcion());
		destino.setConocimientoHabilidad(origen.getHabilidad());
		destino.setIdExperiencia(origen.getIdExperienciaHab());
		destino.setIdDominio(origen.getIdDominioHab());
		destino.setDescripcion(origen.getDescripcionHab());
		destino.setPrincipal(MULTIREGISTRO.PRINCIPAL.getIdOpcion());
	}**/
	
	private void setCompuAvanVO(ComputacionAvanzadaVO destino, EscolaridadForm origen) {
		destino.setIdCandidatoCompuAvanzada(origen.getIdCandidatoCompuAvanzada());
		destino.setSoftwareHardware(origen.getSoftwareHardware());
		destino.setIdExperiencia(origen.getIdExperiencia());
		destino.setIdDominio(origen.getIdDominioCmp());
		destino.setDescripcion(origen.getDescripcion());
		destino.setPrincipal(MULTIREGISTRO.PRINCIPAL.getIdOpcion());
	}
	
	private EscolaridadVOForm getEscolaridadVOForm(EscolaridadForm form){
		EscolaridadVOForm vo = new EscolaridadVOForm();
		try {
			BeanUtils.copyProperties(vo, form);
		} catch (Exception e) { 
			e.printStackTrace();
			logger.error(e);  
		}
		return vo;
	}
	
	@SuppressWarnings("unchecked")
	public ActionForward saveAddStudie(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		List<CandidatoOtroEstudioVO> otherStudies = null;
		UsuarioWebVO usuario = super.getUsuario(request.getSession());
    	int idTipoEstudio = Utils.parseInt(request.getParameter("studieTypeAdd"));
    	String nombreEstudio = request.getParameter("studieAdd");
    	String institucion = request.getParameter("institutionAdd");
    	String fechaInicio = request.getParameter("dateini");
    	String fechaFinal = request.getParameter("datefin");
    	int idEstatusAcademico = Utils.parseInt(request.getParameter("statusacademicAdd"));
    	CandidatoOtroEstudioVO studie = new CandidatoOtroEstudioVO();
    	studie.setIdEstatusAcademico(idEstatusAcademico);
    	studie.setNombreEstudio(nombreEstudio);
    	studie.setFechaFin(mx.gob.stps.portal.web.infra.utils.Utils.convertWebDate(fechaFinal));
    	studie.setFechaInicio(mx.gob.stps.portal.web.infra.utils.Utils.convertWebDate(fechaInicio));
    	studie.setInstitucion(institucion);
    	studie.setIdTipoEstudio(idTipoEstudio);
    	studie.setIdCandidato(usuario.getIdPropietario());
    	otherStudies = (List<CandidatoOtroEstudioVO>)request.getSession().getAttribute("OTHER_STUDIES_LIST");
		if (null != otherStudies) {
			if (otherStudies.isEmpty())
				studie.setPrincipal(Constantes.MULTIREGISTRO.PRINCIPAL.getIdOpcion());
			else
				studie.setPrincipal(Constantes.MULTIREGISTRO.ADICIONAL.getIdOpcion());
		}else {
			studie.setPrincipal(Constantes.MULTIREGISTRO.PRINCIPAL.getIdOpcion());
			otherStudies = new ArrayList<CandidatoOtroEstudioVO>();
		}
		CandidatoBusDelegate services = CandidatoBusDelegateImpl.getInstance();
    	try {
    		long idCandidatoOtroEstudio = services.registrarOtroEstudio(studie);
    		studie.setIdCandidatoOtroEstudio(idCandidatoOtroEstudio);
    		otherStudies.add(studie);
		} catch (Exception e) { logger.error(e); }	
		request.getSession().setAttribute("OTHER_STUDIES_LIST", otherStudies);
	    return mapping.findForward("ACTION_REGISTROS_TABLA");	            	    	
    }
	
	@SuppressWarnings("unchecked")
	public ActionForward deleteStudie(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException {
		String idEstudio = request.getParameter("idStudie");
		CandidatoBusDelegate services = CandidatoBusDelegateImpl.getInstance();
		List<CandidatoOtroEstudioVO> otherStudies = (List<CandidatoOtroEstudioVO>)request.getSession().getAttribute("OTHER_STUDIES_LIST");
		if (idEstudio!=null && otherStudies!=null && !otherStudies.isEmpty()) {
			int index = 0;
			boolean found = false;
			for (index = 0; index < otherStudies.size(); index++) {
				if (otherStudies.get(index).getIdCandidatoOtroEstudio() == Utils.parseLong(idEstudio)) {
					CandidatoOtroEstudioVO otroEstudioVO = otherStudies.get(index);
					try {
						services.eliminarOtroEstudio(otroEstudioVO.getIdCandidatoOtroEstudio());
					} catch (Exception e) { logger.error(e); }
					found = true;
					break;
				}
			}
			if (found) otherStudies.remove(index);	
		}
        return mapping.findForward("ACTION_REGISTROS_TABLA");
	}
	
	public void saveAddLangList(HttpServletRequest request) {
		List<IdiomaVO> idiomasVO = new ArrayList<IdiomaVO>();
		
		try {	
			request.getSession().removeAttribute("IDIOMAS_LIST");
			long idCandidato = getUsuario(request.getSession()).getIdPropietario();
			CandidatoBusDelegate services = CandidatoBusDelegateImpl.getInstance();
			
			for (int i=1; i<=NUMERO_MAXIMO_DE_REGISTROS_DE_IDIOMA_POR_CANDIDATO; i++) {
				long idioma = Utils.parseLong(request.getParameter("idIdiomaAdd" + i));
				if (idioma > 0) {		    	
			    		
					long idCertificacion = Utils.parseLong(request.getParameter("idCertificacionAdd" + i));
			    	long idDominio = Utils.parseLong(request.getParameter("idDominioAdd"  + i));
			    	long idCandidatoIdioma = Utils.parseLong(request.getParameter("idCandidatoIdioma" + i));
			    	IdiomaVO lang = new IdiomaVO();
			    	lang.setIdCertificacion(idCertificacion);
			    	lang.setIdDominio(idDominio);
			    	lang.setIdIdioma(idioma);
			    	lang.setIdCandidatoIdioma(idCandidatoIdioma);
		    							
					idiomasVO = services.buscarIdiomas(idCandidato);
					if (null != idiomasVO) {
						idiomasVO = new ArrayList<IdiomaVO>();
					}					
					lang.setPrincipal(obtenerTipoDeMultiregistroIdioma(idiomasVO));
					services.agregarIdioma(idCandidato, lang);					
					idiomasVO.add(lang);
				}
			}			
			request.getSession().setAttribute("IDIOMAS_LIST", idiomasVO);	  
			
    	} catch (Exception e) { 
    		e.printStackTrace();
    		logger.error(e); 
    	}					          	    
    }
	
	public void saveAddLangList(HttpServletRequest request, EscolaridadForm escForm) {	
		List<IdiomaVO> idiomasVO = escForm.getIdiomasAdicionales();
		try{
			long idCandidato = getUsuario(request.getSession()).getIdPropietario();
			for(IdiomaVO lang : idiomasVO){
				long idioma = lang.getIdIdioma();
				if (idioma > 0) {
					CandidatoBusDelegate services = CandidatoBusDelegateImpl.getInstance();
					idiomasVO = services.buscarIdiomas(idCandidato);
					if (null != idiomasVO) {
						idiomasVO = new ArrayList<IdiomaVO>();
					}										
					lang.setPrincipal(obtenerTipoDeMultiregistroIdioma(idiomasVO));
					services.agregarIdioma(idCandidato, lang);
					idiomasVO.add(lang);					
				}				
			}		
    	} catch (Exception e) { 
    		e.printStackTrace();
    		logger.error(e); 
    	}		
    }	
	
	private int obtenerTipoDeMultiregistroIdioma(List<IdiomaVO> idiomas){
		if (null != idiomas) {
			if (idiomas.isEmpty()){
				return Constantes.MULTIREGISTRO.PRINCIPAL.getIdOpcion();
			} else {
				return Constantes.MULTIREGISTRO.ADICIONAL.getIdOpcion();
			}
		} else {
			return Constantes.MULTIREGISTRO.PRINCIPAL.getIdOpcion();
		}		
	}
		
	private void inicializarDatosDeIdiomasEnForm(List<IdiomaVO> idiomas, EscolaridadForm form){
		int contadorIdiomasAdicionales=1;
		List<IdiomaVO> idiomasAdicionales = new ArrayList<IdiomaVO>();
		for(IdiomaVO vo: idiomas){
			if (vo.getPrincipal()==MULTIREGISTRO.PRINCIPAL.getIdOpcion()) {
				setIdiomaPrincipal(form, vo);
			}else {
				idiomasAdicionales.add(vo);
				if(contadorIdiomasAdicionales==1)
					form.setIdiomasMultiRegistro1(setIdiomasMultiRegistro(vo.getIdIdioma(),form.getIdiomasDependientes()));
				if(contadorIdiomasAdicionales==2)
					form.setIdiomasMultiRegistro2(setIdiomasMultiRegistro(vo.getIdIdioma(),form.getIdiomasDependientes()));
				if(contadorIdiomasAdicionales==3)
					form.setIdiomasMultiRegistro3(setIdiomasMultiRegistro(vo.getIdIdioma(),form.getIdiomasDependientes()));
				contadorIdiomasAdicionales++;
			}
		}
		form.setIdiomasAdicionales(idiomasAdicionales);		
		form.setTotalIdiomasAdicionales(idiomasAdicionales.size());
	}
	
	private void setIdiomaPrincipal(EscolaridadForm form, IdiomaVO idioma){
		form.setIdCandidatoIdioma(idioma.getIdCandidatoIdioma());
		form.setIdIdioma((int) idioma.getIdIdioma());
		form.setIdCertificacion(idioma.getIdCertificacion());
		form.setIdDominio(idioma.getIdDominio());		
	}		
		
	public ActionForward idiomaAdicional(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		long idIdiomaAdd=Long.parseLong(request.getParameter("idIdiomaAdd"));
		String idCertificacionIdiomaAddStr = request.getParameter("idCertificacionIdiomaAdd");
		
		long idCertificacionIdiomaAdd=0L;
		if(idCertificacionIdiomaAddStr != null && !idCertificacionIdiomaAddStr.isEmpty()){
			idCertificacionIdiomaAdd = Long.parseLong(request.getParameter("idCertificacionIdiomaAdd"));
		}
		String idDominioIdiomaAdd=request.getParameter("idDominioIdiomaAdd");
		long idDominio = 0;
		
		if(idIdiomaAdd>0 ){
			EscolaridadForm forma = (EscolaridadForm) form;
			
			boolean existe = existeIdiomaEnListado(forma.getIdiomasAdicionales(), idIdiomaAdd, idCertificacionIdiomaAdd);
			if(!existe){
				
				if(forma.getIdiomasAdicionales()==null || forma.getIdiomasAdicionales().isEmpty()){
					forma.setIdiomasAdicionales(new ArrayList<IdiomaVO>());
				}					
				
				if(idDominioIdiomaAdd!=null&&!idDominioIdiomaAdd.equals("")){
					idDominio = Long.parseLong(idDominioIdiomaAdd);
				}				
				
				agregarCatalogosIdiomasDependientes(forma, idIdiomaAdd);
				IdiomaVO idiomaNuevo = setIdiomaAdicional(idIdiomaAdd, idCertificacionIdiomaAdd, idDominio);
				forma.getIdiomasAdicionales().add(idiomaNuevo);				
			}				
			forma.setTotalIdiomasAdicionales(forma.getIdiomasAdicionales().size());
		}
		return mapping.findForward(JSP_IDIOMA);
	}
	
	private boolean existeIdiomaEnListado(List<IdiomaVO> listado, long idIdioma, long idCertificacion){
		boolean existe = false;
		if(listado!=null && !listado.isEmpty()){
			for(IdiomaVO vo: listado){
				if((vo.getIdIdioma()==idIdioma)&&(vo.getIdCertificacion()==idCertificacion)){
					existe=true;
					break;
				}
			}
		}
		return existe;
	}	
	
	private void agregarCatalogosIdiomasDependientes(EscolaridadForm forma, long idIdioma){
		int totalIdiomas = forma.getIdiomasAdicionales().size()+1;
		if(totalIdiomas==1)
			forma.setIdiomasMultiRegistro1(setIdiomasMultiRegistro(idIdioma,forma.getIdiomasDependientes()));
		if(totalIdiomas==2)
			forma.setIdiomasMultiRegistro2(setIdiomasMultiRegistro(idIdioma,forma.getIdiomasDependientes()));
		if(totalIdiomas==3)
			forma.setIdiomasMultiRegistro3(setIdiomasMultiRegistro(idIdioma,forma.getIdiomasDependientes()));		
	}
	
	private IdiomaVO setIdiomaAdicional(long idIdioma, long idCertificacion, long idDominio){
		IdiomaVO idiomaVo = new IdiomaVO();
		idiomaVo.setIdIdioma(idIdioma);
		idiomaVo.setIdDominio(idDominio);
		idiomaVo.setIdCertificacion(idCertificacion);
		idiomaVo.setPrincipal(MULTIREGISTRO.ADICIONAL.getIdOpcion());	
		return idiomaVo;
	}

	public ActionForward idiomaCertificacion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		EscolaridadForm forma = (EscolaridadForm) form;
		String idioma = request.getParameter("vIdioma");
		if(idioma!=null && !idioma.equals("")){
			long idIdiomaAdd=Long.parseLong(request.getParameter("vIdioma"));
			long idMultiRegistro=Long.parseLong(request.getParameter("idMultiRegistro"));

			if(idMultiRegistro==1){
				forma.getIdiomasAdicionales().get(0).setIdIdioma(idIdiomaAdd);
				forma.setIdiomasMultiRegistro1(setIdiomasMultiRegistro(idIdiomaAdd,forma.getIdiomasDependientes()));
			}
			if(idMultiRegistro==2){
				forma.getIdiomasAdicionales().get(1).setIdIdioma(idIdiomaAdd);
				forma.setIdiomasMultiRegistro2(setIdiomasMultiRegistro(idIdiomaAdd,forma.getIdiomasDependientes()));
			}
			if(idMultiRegistro==3){
				forma.getIdiomasAdicionales().get(2).setIdIdioma(idIdiomaAdd);
				forma.setIdiomasMultiRegistro3(setIdiomasMultiRegistro(idIdiomaAdd,forma.getIdiomasDependientes()));
			}
		}
		return mapping.findForward(JSP_IDIOMA);
	}
	//Methodo anterior antes de inversion de flujo
	public ActionForward idiomaDominio(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		EscolaridadForm forma = (EscolaridadForm) form;
		
		String idioma = request.getParameter("vIdioma");
		
		if(idioma!=null&&!idioma.equals("")){
			long idiomal = Long.valueOf(idioma);
			long idCertifiacionAdd=0;
			long idMultiRegistro=Long.parseLong(request.getParameter("idMultiRegistro"));
			long idDominio = 0L;
			String dominio = request.getParameter("idDominio");
			if(dominio != null && !dominio.equals("")){
				idDominio = Long.parseLong(dominio);
			}
			

			if(idMultiRegistro==1){
				forma.getIdiomasAdicionales().get(0).setIdIdioma(idiomal);
				forma.getIdiomasAdicionales().get(0).setIdCertificacion(idCertifiacionAdd);
				//forma.getIdiomasAdicionales().get(0).setIdCertificacion(0);
				forma.getIdiomasAdicionales().get(0).setIdDominio(idDominio);
				forma.setIdiomasMultiRegistro1(setIdiomasMultiRegistro(idiomal,forma.getIdiomasDependientes()));
				
			}
			if(idMultiRegistro==2){
				forma.getIdiomasAdicionales().get(1).setIdIdioma(idiomal);
				//forma.getIdiomasAdicionales().get(1).setIdCertificacion(idCertifiacionAdd);
				forma.getIdiomasAdicionales().get(1).setIdCertificacion(0);
				forma.getIdiomasAdicionales().get(1).setIdDominio(idDominio);
				forma.setIdiomasMultiRegistro2(setIdiomasMultiRegistro(idiomal,forma.getIdiomasDependientes()));
			}
			if(idMultiRegistro==3) {
				forma.getIdiomasAdicionales().get(2).setIdIdioma(idiomal);
				//forma.getIdiomasAdicionales().get(2).setIdCertificacion(idCertifiacionAdd);
				forma.getIdiomasAdicionales().get(2).setIdCertificacion(0);
				forma.getIdiomasAdicionales().get(2).setIdDominio(idDominio);
				forma.setIdiomasMultiRegistro3(setIdiomasMultiRegistro(idiomal,forma.getIdiomasDependientes()));
			}			
			//ajustarDominioIdioma(forma, idCertifiacionAdd, idMultiRegistro);
		}
		return mapping.findForward(JSP_IDIOMA);
	}
	
	public ActionForward guardarFormCertificacionAdd(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		EscolaridadForm forma = (EscolaridadForm) form;
		String certificacion = request.getParameter("idCertificacion");
		if(certificacion!=null&&!certificacion.equals("")){
			long idCertificacion = Long.parseLong(certificacion);
			
			Integer idMultiRegistro=Integer.parseInt(request.getParameter("idMultiRegistro"));
			CandidatoBusDelegate services = CandidatoBusDelegateImpl.getInstance();
			

			
				forma.getIdiomasAdicionales().get(idMultiRegistro-1).setIdCertificacion(idCertificacion);
						
			//ajustarDominioIdioma(forma, idCertifiacionAdd, idMultiRegistro);
		}
		return mapping.findForward(JSP_IDIOMA);
	}

	
	public ActionForward idiomaCertificacionAdd(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		EscolaridadForm forma = (EscolaridadForm) form;
		String idioma = request.getParameter("vIdioma");
		if(idioma!=null&&!idioma.equals("")){
			long idIdioma = Long.parseLong(idioma);
			long idDominio=Long.parseLong(request.getParameter("idDominio"));
			Integer idMultiRegistro=Integer.parseInt(request.getParameter("idMultiRegistro"));
			CandidatoBusDelegate services = CandidatoBusDelegateImpl.getInstance();
			

				forma.getIdiomasAdicionales().get(idMultiRegistro-1).setIdIdioma(idIdioma);
				forma.getIdiomasAdicionales().get(idMultiRegistro-1).setIdDominio(idDominio);
				forma.getIdiomasAdicionales().get(idMultiRegistro-1).setIdCertificacion(0);
				if(idMultiRegistro==1){
					forma.setIdiomasMultiRegistro1(setIdiomasMultiRegistro(idIdioma,forma.getIdiomasDependientes()));
				}
				if(idMultiRegistro==2){
					forma.setIdiomasMultiRegistro2(setIdiomasMultiRegistro(idIdioma,forma.getIdiomasDependientes()));
				}
				if(idMultiRegistro==3){
					forma.setIdiomasMultiRegistro3(setIdiomasMultiRegistro(idIdioma,forma.getIdiomasDependientes()));
				}
						
			//ajustarDominioIdioma(forma, idCertifiacionAdd, idMultiRegistro);
		}
		return mapping.findForward(JSP_IDIOMA);
	}

	
	private void ajustarDominioIdioma(EscolaridadForm forma, long idCertificacion, long idMultiRegistro){
		if(idCertificacion !=OPCION_NINGUNA_CERTIFICACION && idCertificacion!=OPCION_OTRA_CERTIFICACION){
			if(idMultiRegistro==1){
				forma.getIdiomasAdicionales().get(0).setIdDominio(Constantes.DOMINIO.AVANZADO.getIdOpcion());
			}
			if(idMultiRegistro==2){
				forma.getIdiomasAdicionales().get(1).setIdDominio(Constantes.DOMINIO.AVANZADO.getIdOpcion());
			}
			if(idMultiRegistro==3) {
				forma.getIdiomasAdicionales().get(2).setIdDominio(Constantes.DOMINIO.AVANZADO.getIdOpcion());
			}				
		} 		
	}

	public ActionForward eliminarIdiomaAdicional(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		long idIdiomaAdd=Long.parseLong(request.getParameter("idIdiomaEliminar"));
		long idCertificacionIdiomaAdd=Long.parseLong(request.getParameter("idCertificacionIdiomaEliminar"));
		EscolaridadForm forma = (EscolaridadForm) form;
		List<IdiomaVO> idiomasAdicionales = forma.getIdiomasAdicionales();
		CandidatoBusDelegate services = CandidatoBusDelegateImpl.getInstance();
		int indiceIdioma=0;
		for(IdiomaVO vo: idiomasAdicionales){
			if((vo.getIdIdioma()==idIdiomaAdd)&&(vo.getIdCertificacion()==idCertificacionIdiomaAdd)) {
				try {
					services.borrarIdioma(vo.getIdCandidatoIdioma());
				} catch (Exception e) { logger.error(e); e.printStackTrace();}
				idiomasAdicionales.remove(indiceIdioma);
				break;
			}
			indiceIdioma++;
		}
		for(int i=0;i<idiomasAdicionales.size();i++){
			if(i==0)forma.setIdiomasMultiRegistro1(setIdiomasMultiRegistro(idiomasAdicionales.get(i).getIdIdioma(),forma.getIdiomasDependientes()));
			if(i==1)forma.setIdiomasMultiRegistro2(setIdiomasMultiRegistro(idiomasAdicionales.get(i).getIdIdioma(),forma.getIdiomasDependientes()));
			if(i==2)forma.setIdiomasMultiRegistro3(setIdiomasMultiRegistro(idiomasAdicionales.get(i).getIdIdioma(),forma.getIdiomasDependientes()));
		}
		forma.setIdiomasAdicionales(idiomasAdicionales);
		forma.setTotalIdiomasAdicionales(forma.getIdiomasAdicionales().size());
		return mapping.findForward(JSP_IDIOMA);
	}
	
	private List<CatalogoOpcionVO> setIdiomasMultiRegistro(long idIdioma, String[] idiomasDep) {
		List<CatalogoOpcionVO> lista = new ArrayList<CatalogoOpcionVO>();
		try {
			lista = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(Long.valueOf(idiomasDep[(int) idIdioma]));
			
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	/* FIXME: Este codigo es candidato a eliminarse
	 * private void getOptionsSelectedLang(List<IdiomaVO> idiomasVO, HttpServletRequest request) {
		Iterator<IdiomaVO> it = idiomasVO.iterator();
		while (it.hasNext()) {
			IdiomaVO idioma = it.next();
			request.getSession().setAttribute("IDIOMAS_" + idioma.getIdCandidatoIdioma(), getSelectLang(idioma.getIdIdioma()));
		}
	}
	
	private void getOptionsSelectedLangCert(List<IdiomaVO> idiomasVO, HttpServletRequest request) {
		Iterator<IdiomaVO> it = idiomasVO.iterator();
		while (it.hasNext()) {
			IdiomaVO idioma = it.next();
			request.getSession().setAttribute("CERTIFICACIONES_" + idioma.getIdCandidatoIdioma(), getSelectLangCert(idioma.getIdIdioma(), idioma.getIdCertificacion()));
		}
	}
	
	private void getOptionsSelectedLangDom(List<IdiomaVO> idiomasVO, HttpServletRequest request) {
		Iterator<IdiomaVO> it = idiomasVO.iterator();
		while (it.hasNext()) {
			IdiomaVO idioma = it.next();
			request.getSession().setAttribute("DOMINIOS_" + idioma.getIdCandidatoIdioma(), getSelectLangDom(idioma.getIdDominio()));
		}
	}*/
	
	/*private String getSelectLang(long idIdioma) {
		StringBuilder langs = new StringBuilder();
		try {
			long[] filtro = {9};
			List<CatalogoOpcionVO> options = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(CATALOGO_OPCION_IDIOMAS, filtro, true);
			Iterator<CatalogoOpcionVO> it = options.iterator();
			while (it.hasNext()) {
				CatalogoOpcionVO vo = it.next();
				langs.append("<option ");
				if (vo.getIdCatalogoOpcion()==idIdioma)
					langs.append("selected ");
				langs.append("value=\"").append(vo.getIdCatalogoOpcion()).append("\">").append(vo.getOpcion()).append("</option>");
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return langs.toString();
	}
	
	private String getSelectLangCert(long idIdioma, long idCert) {
		StringBuilder langs = new StringBuilder();
		try {
			long idCatDep = 0;
			long[] filtro = {9};
			List<CatalogoOpcionVO> options = new ArrayList<CatalogoOpcionVO>();
			options = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(CATALOGO_OPCION_IDIOMAS, filtro);
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
				if (vo.getIdCatalogoOpcion()==idCert)
					langs.append("selected ");
				langs.append("value=\"").append(vo.getIdCatalogoOpcion()).append("\">").append(vo.getOpcion()).append("</option>");
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return langs.toString();
	}
	
	private String getSelectLangDom(long idDominio) {
		StringBuilder langs = new StringBuilder();
		try {
			List<CatalogoOpcionVO> options = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(CATALOGO_OPCION_DOMINIO);
			Iterator<CatalogoOpcionVO> it = options.iterator();
			while (it.hasNext()) {
				CatalogoOpcionVO vo = it.next();
				langs.append("<option ");
				if (vo.getIdCatalogoOpcion()==idDominio)
					langs.append("selected ");
				langs.append("value=\"").append(vo.getIdCatalogoOpcion()).append("\">").append(vo.getOpcion()).append("</option>");
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return langs.toString();
	}*/	
	
	public void saveAddStudieList(HttpServletRequest request) {
		List<CandidatoOtroEstudioVO> otherStudies = null;
		request.getSession().removeAttribute("OTHER_STUDIES_LIST");
		for (int i=1; i<11; i++) {
			long idTipoEstudio = Utils.parseLong(request.getParameter("studieTypeAdd" + i));
			if (idTipoEstudio > 0) {
				String nombreEstudio = null != request.getParameter("studieAdd"  + i) ? request.getParameter("studieAdd"  + i) : "";
		    	String institucion = null != request.getParameter("institutionAdd"  + i) ? request.getParameter("institutionAdd"  + i) : "";
		    	long dayIniAdd = Utils.parseLong(request.getParameter("dayIniAdd" + i));
		    	long dayFinAdd = Utils.parseLong(request.getParameter("dayFinAdd" + i));
		    	long monthIniAdd = Utils.parseLong(request.getParameter("monthIniAdd" + i));
		    	long monthFinAdd = Utils.parseLong(request.getParameter("monthFinAdd" + i));
		    	long yearIniAdd = Utils.parseLong(request.getParameter("yearIniAdd" + i));
		    	long yearFinAdd = Utils.parseLong(request.getParameter("yearFinAdd" + i));
		    	long idEstatusAcademico = Utils.parseLong(request.getParameter("statusAcademicAdd" + i));
		    	long idCandidatoOtroEstudio = Utils.parseLong(request.getParameter("idCandidateStudie" + i));
		    	CandidatoOtroEstudioVO studie = new CandidatoOtroEstudioVO();
		    	String fechaFin = "" + yearFinAdd + "-" + monthFinAdd + "-" + dayFinAdd; 
		    	String fechaInicio = "" + yearIniAdd + "-" + monthIniAdd + "-" + dayIniAdd; 
		    	studie.setFechaFin(mx.gob.stps.portal.web.infra.utils.Utils.convertWebDate(fechaFin));
		    	studie.setFechaInicio(mx.gob.stps.portal.web.infra.utils.Utils.convertWebDate(fechaInicio));
		    	studie.setIdCandidatoOtroEstudio(idCandidatoOtroEstudio);
		    	studie.setIdEstatusAcademico(idEstatusAcademico);
		    	studie.setIdTipoEstudio(idTipoEstudio);
		    	studie.setInstitucion(institucion);
		    	studie.setNombreEstudio(nombreEstudio);
		    	try {
					CandidatoBusDelegate services = CandidatoBusDelegateImpl.getInstance();
					otherStudies = services.otrosEstudiosList(getUsuario(request.getSession()).getIdPropietario());
					if (null == otherStudies) 
						otherStudies = new ArrayList<CandidatoOtroEstudioVO>();
					services.actualizarOtroEstudio(studie);
					otherStudies.add(studie);
		    	} catch (Exception e) { logger.error(e); }	
			}
		}
		request.getSession().setAttribute("OTHER_STUDIES_LIST", otherStudies);	            	    	
    }
	
	/**public void saveAddKnowledgeList(HttpServletRequest request) {
		List<ConocimientoHabilidadVO> conocsHabsVO = null;
		request.getSession().removeAttribute("KNOWLEDGE_LIST");
		CandidatoBusDelegate services = CandidatoBusDelegateImpl.getInstance();
		for (int i=1; i<3; i++) {
			String conocimientoHabilidad = request.getParameter("conocimientoAdd" + i);
			if (null != conocimientoHabilidad && !conocimientoHabilidad.isEmpty()) {
				long idCandidatoConocimHabilidad = Utils.parseLong(request.getParameter("idCandidatoConocAdd" + i));
				long idExperiencia = Utils.parseLong(request.getParameter("idExperienciaSelectAdd" + i));
		    	String descripcion = null != request.getParameter("descripcionConAdd"  + i) ? request.getParameter("descripcionConAdd"  + i) : "";
		    	ConocimientoHabilidadVO conocHabVO = new ConocimientoHabilidadVO();
		    	conocHabVO.setIdCandidatoConocimHabilidad(idCandidatoConocimHabilidad);
		    	conocHabVO.setConocimientoHabilidad(conocimientoHabilidad);
		    	conocHabVO.setDescripcion(descripcion);
		    	conocHabVO.setIdExperiencia(idExperiencia);
		    	conocHabVO.setIdTipoConocimHabilidad(CONOC_HAB.CONOCIMIENTO.getIdOpcion());
		    	try {
					services.actualizarConocHab(conocHabVO);
		    	} catch (Exception e) { logger.error(e); }	
			}
		}
		try {
			conocsHabsVO = services.buscarConocsHabs(getUsuario(request.getSession()).getIdPropietario(), CONOC_HAB.CONOCIMIENTO.getIdOpcion());
			if (null == conocsHabsVO) conocsHabsVO = new ArrayList<ConocimientoHabilidadVO>();
		} catch (Exception e) {
			conocsHabsVO = new ArrayList<ConocimientoHabilidadVO>();
		}
		request.getSession().setAttribute("KNOWLEDGE_LIST", conocsHabsVO);	            	    	
    }
	
	public void saveAddSkillList(HttpServletRequest request) {
		List<ConocimientoHabilidadVO> conocsHabsVO = null;
		request.getSession().removeAttribute("SKILLS_LIST");
		CandidatoBusDelegate services = CandidatoBusDelegateImpl.getInstance();
		for (int i=1; i<3; i++) {
			String conocimientoHabilidad = request.getParameter("conocimientoAddSkill" + i);
			if (null != conocimientoHabilidad && !conocimientoHabilidad.isEmpty()) {
				long idCandidatoConocimHabilidad = Utils.parseLong(request.getParameter("idCandidatoSkillAdd" + i));
				long idExperiencia = Utils.parseLong(request.getParameter("idExperienciaSelectAddSkill" + i));
		    	String descripcion = null != request.getParameter("descripcionSkillAdd"  + i) ? request.getParameter("descripcionSkillAdd"  + i) : "";
		    	ConocimientoHabilidadVO conocHabVO = new ConocimientoHabilidadVO();
		    	conocHabVO.setIdCandidatoConocimHabilidad(idCandidatoConocimHabilidad);
		    	conocHabVO.setConocimientoHabilidad(conocimientoHabilidad);
		    	conocHabVO.setDescripcion(descripcion);
		    	conocHabVO.setIdExperiencia(idExperiencia);
		    	conocHabVO.setIdTipoConocimHabilidad(CONOC_HAB.HABILIDAD.getIdOpcion());
		    	try {
					services.actualizarConocHab(conocHabVO);
		    	} catch (Exception e) { logger.error(e); }	
			}
		}
		try {
			conocsHabsVO = services.buscarConocsHabs(getUsuario(request.getSession()).getIdPropietario(), CONOC_HAB.HABILIDAD.getIdOpcion());
			if (null == conocsHabsVO) conocsHabsVO = new ArrayList<ConocimientoHabilidadVO>();
		} catch (Exception e) {
			conocsHabsVO = new ArrayList<ConocimientoHabilidadVO>();
		}
		request.getSession().setAttribute("SKILLS_LIST", conocsHabsVO);	            	    	
    }**/
	
	private void saveConocerPreferencias(ActionForm form){		

		try {
			EscolaridadForm escForm = (EscolaridadForm) form;			
			CandidatoBusDelegate services = CandidatoBusDelegateImpl.getInstance();		

			// recuperamos las preferencias almacenadas por el usuario
			ConocerConfigVO vo = services.consultaConocerConfigByIdCandidato(escForm.getIdCandidato());			
			
			if (vo != null && vo.getDeseaPublicar() != escForm.getMostrarCertificacionesEnCV()){
				// si ha cambiado de preferencias, las persistimos en bd
				vo.setDeseaPublicar(escForm.getMostrarCertificacionesEnCV());
				services.registrarConocerConfig(vo);
			}
			
		} catch (Exception e){
			logger.error("Error al guardar las preferencias del usuario del apartado Conocer");
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public ActionForward saveAddKnowledge(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	long idExperiencia = Utils.parseLong(request.getParameter("idExperienciaSelectAdd"));
    	String conocimientoHabilidad = request.getParameter("conocimientoAdd");
    	String descripcion = request.getParameter("descripcionConAdd");
    	ConocimientoHabilidadVO conocHabVO = new ConocimientoHabilidadVO();
    	conocHabVO.setDescripcion(descripcion);
    	conocHabVO.setIdExperiencia(idExperiencia);
    	conocHabVO.setConocimientoHabilidad(conocimientoHabilidad);
    	conocHabVO.setIdTipoConocimHabilidad(CONOC_HAB.CONOCIMIENTO.getIdOpcion());
    	conocHabVO.setIdCandidato(getUsuario(request.getSession()).getIdPropietario());
    	List<ConocimientoHabilidadVO> conocsHabsVO = (List<ConocimientoHabilidadVO>)request.getSession().getAttribute("KNOWLEDGE_LIST");
    	conocHabVO.setPrincipal(Constantes.MULTIREGISTRO.ADICIONAL.getIdOpcion());
		conocsHabsVO.add(conocHabVO);
		request.getSession().setAttribute("KNOWLEDGE_LIST", conocsHabsVO);
		return mapping.findForward("CONOCIMIENTOS");	            	    	
    }
	
	@SuppressWarnings("unchecked")
	public ActionForward deleteKnowledge(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		int row = Utils.parseInt(request.getParameter("idKnow"));
		List<ConocimientoHabilidadVO> conocList = new ArrayList<ConocimientoHabilidadVO>();
		List<ConocimientoHabilidadVO> conocTmpList = new ArrayList<ConocimientoHabilidadVO>();
		List<ConocimientoHabilidadVO> conocDelList = new ArrayList<ConocimientoHabilidadVO>();
		try {
			if (null != request.getSession().getAttribute("KNOWLEDGE_LIST"))
				conocList = (List<ConocimientoHabilidadVO>)request.getSession().getAttribute("KNOWLEDGE_LIST");
			if (conocList.isEmpty() || -1 == row) return mapping.findForward("CONOCIMIENTOS");
			for (ConocimientoHabilidadVO vo : conocList) {
				if (row == vo.getRow()) {
					if (vo.getIdCandidatoConocimHabilidad() > 0) {
						if (null != request.getSession().getAttribute("KNOWLEDGE_2DELIST"))
							conocDelList = (List<ConocimientoHabilidadVO>)request.getSession().getAttribute("KNOWLEDGE_2DELIST");
						conocDelList.add(vo);
						request.getSession().setAttribute("KNOWLEDGE_2DELIST", conocDelList);
					}
				}else conocTmpList.add(vo);
			}
		} catch (Exception e) {logger.error(e); }
		request.getSession().setAttribute("KNOWLEDGE_LIST", conocTmpList);
		return mapping.findForward("CONOCIMIENTOS");
	}
	
	private void setConocimientoForm(EscolaridadForm destino, ConocimientoHabilidadVO origen) {
		destino.setIdCandidatoConocimiento(origen.getIdCandidatoConocimHabilidad());
		destino.setIdTipoConocimiento(origen.getIdTipoConocimHabilidad());
		destino.setConocimiento(origen.getConocimientoHabilidad());
		destino.setIdExperienciaCon(origen.getIdExperiencia());
		destino.setIdDominioCon(origen.getIdDominio());
		destino.setDescripcionCon(origen.getDescripcion());
	}
	
	private ConocimientoHabilidadVO getConocimientoVO(EscolaridadForm origen) {
		ConocimientoHabilidadVO destino = new ConocimientoHabilidadVO();
		destino.setIdCandidatoConocimHabilidad(origen.getIdCandidatoConocimiento());
		destino.setIdTipoConocimHabilidad(Constantes.CONOC_HAB.CONOCIMIENTO.getIdOpcion());
		destino.setConocimientoHabilidad(origen.getConocimiento());
		destino.setIdExperiencia(origen.getIdExperienciaCon());
		destino.setIdDominio(origen.getIdDominioCon());
		destino.setDescripcion(origen.getDescripcionCon());
		destino.setPrincipal(MULTIREGISTRO.PRINCIPAL.getIdOpcion());
		return destino;
	}
	
	@SuppressWarnings("unchecked")
	private void saveAddKnowledgeList(HttpServletRequest request, EscolaridadForm escolaridadForm) {
		request.getSession().removeAttribute("KNOWLEDGE_LIST");
		CandidatoBusDelegate services = CandidatoBusDelegateImpl.getInstance();
		ConocimientoHabilidadVO principal = getConocimientoVO(escolaridadForm);
		try {
			services.update(principal);
		} catch (Exception e) { logger.error(e); }
		for (int i=1; i<NUMERO_MAXIMO_DE_REGISTROS_ADICIONALES_PERMITIDOS+1; i++) {
			String conocimientoHabilidad = request.getParameter("conocimientoAdd" + i);
			if (null != conocimientoHabilidad && !conocimientoHabilidad.isEmpty()) {
				long idCandidatoConocimHabilidad = Utils.parseLong(request.getParameter("idCandidatoConocAdd" + i));
				long idExperiencia = Utils.parseLong(request.getParameter("idExperienciaSelectAdd" + i));
		    	String descripcion = null != request.getParameter("descripcionConAdd"  + i) ? request.getParameter("descripcionConAdd"  + i) : "";
		    	ConocimientoHabilidadVO conocHabVO = new ConocimientoHabilidadVO();
		    	conocHabVO.setDescripcion(descripcion);
		    	conocHabVO.setIdExperiencia(idExperiencia);
		    	conocHabVO.setConocimientoHabilidad(conocimientoHabilidad);
		    	conocHabVO.setPrincipal(MULTIREGISTRO.ADICIONAL.getIdOpcion());
		    	conocHabVO.setIdCandidatoConocimHabilidad(idCandidatoConocimHabilidad);
		    	conocHabVO.setIdTipoConocimHabilidad(CONOC_HAB.CONOCIMIENTO.getIdOpcion());
		    	conocHabVO.setIdCandidato(getUsuario(request.getSession()).getIdPropietario());
		    	try {
					services.update(conocHabVO);
		    	} catch (Exception e) { logger.error(e); }	
			}
		}
		List <ConocimientoHabilidadVO> know2DeList = (List <ConocimientoHabilidadVO>)request.getSession().getAttribute("KNOWLEDGE_2DELIST");
		if (null != know2DeList && !know2DeList.isEmpty()) {
			try {
				for (ConocimientoHabilidadVO vo : know2DeList) {
					services.borrarConocHab(vo.getIdCandidatoConocimHabilidad());
				}
			} catch (Exception e) { logger.error(e); }
			request.getSession().removeAttribute("KNOWLEDGE_2DELIST");
		}
    }
}
