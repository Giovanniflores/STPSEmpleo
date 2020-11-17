package mx.gob.stps.portal.web.company.action;

import static mx.gob.stps.portal.web.infra.utils.Constantes.BODY_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_NEXT;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_REDIRECT_SWB;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_TEMPLATE_MI_ESP_EMP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.JSP_SUCCESS;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TAB_MENU;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TAB_MIS_CANDIDATOS;
import static mx.gob.stps.portal.web.infra.utils.Constantes.URL_REDIRECT_SWB;
import static mx.gob.stps.portal.web.infra.utils.Constantes.USUARIO_APP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TITULO_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.DESCRIPCION_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FACEBOOK_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TWITTER_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.URL_ESPECIFICA;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.stps.portal.core.candidate.vo.CandidatoVo;
import mx.gob.stps.portal.core.candidate.vo.ConocerConfigVO;
import mx.gob.stps.portal.core.candidate.vo.GradoAcademicoVO;
import mx.gob.stps.portal.core.candidate.vo.IdiomaVO;
import mx.gob.stps.portal.core.candidate.vo.InformacionGeneralVO;
import mx.gob.stps.portal.core.infra.utils.Constantes.MULTIREGISTRO;
import mx.gob.stps.portal.core.infra.utils.Constantes.PUBLICAR_ESTANDARES;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_USUARIO;
import mx.gob.stps.portal.core.oferta.registro.vo.RegistroEntidadesVO;
import mx.gob.stps.portal.core.search.BuildSearchMessage;
import mx.gob.stps.portal.core.search.SearchMessage;
import mx.gob.stps.portal.persistencia.vo.CandidatoGradoAcademicoVO;
import mx.gob.stps.portal.persistencia.vo.CandidatoVO;
import mx.gob.stps.portal.persistencia.vo.DomicilioVO;
import mx.gob.stps.portal.persistencia.vo.EstandarConocerVO;
import mx.gob.stps.portal.persistencia.vo.ExpectativaLaboralVO;
import mx.gob.stps.portal.persistencia.vo.TelefonoVO;
import mx.gob.stps.portal.utils.Catalogos;
import mx.gob.stps.portal.utils.ConstantesGenerales.TIPO_PROPIETARIO;
import mx.gob.stps.portal.web.address.delegate.DomicilioBusDelegate;
import mx.gob.stps.portal.web.address.delegate.DomicilioBusDelegateImpl;
import mx.gob.stps.portal.web.candidate.delegate.CandidatoBusDelegate;
import mx.gob.stps.portal.web.candidate.delegate.CandidatoBusDelegateImpl;
import mx.gob.stps.portal.web.infra.action.PagerAction;
import mx.gob.stps.portal.web.infra.utils.PropertiesLoader;
import mx.gob.stps.portal.web.infra.utils.Utils;
import mx.gob.stps.portal.web.jobvacancies.delegate.BolsasTrabajoBusDelegate;
import mx.gob.stps.portal.web.jobvacancies.delegate.BolsasTrabajoBusDelegateImpl;
import mx.gob.stps.portal.web.offer.delegate.RecentOfferSummaryBusDelegate;
import mx.gob.stps.portal.web.offer.delegate.RecentOfferSummaryBusDelegateImpl;
import mx.gob.stps.portal.web.reg_unico.SingleRegisterBusDelegate;
import mx.gob.stps.portal.web.reg_unico.impl.SingleRegisterBusDelegateImpl;
import mx.gob.stps.portal.web.security.vo.UsuarioWebVO;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.commons.beanutils.BeanUtils;

public class SearchCandidatesAction extends PagerAction {
	
	private static Logger logger = Logger.getLogger(SearchCandidatesAction.class);
	private static final String FORWARD_ACTION_EMPRESA = "ACTION_EMPRESA";
	public static final String EXISTEN_OFERTAS_ACTIVAS = "existenOfertasActivas";
	public static final String SI = "Si";
	public static final String NO = "No";
	@Override
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {		
		HttpSession session = request.getSession();
		ocultaBarraArticulos(request);
		
		session.removeAttribute("existenOfertasActivas");
		UsuarioWebVO usuarioWeb = (UsuarioWebVO)session.getAttribute(USUARIO_APP);
		
		RecentOfferSummaryBusDelegate summaryServices = RecentOfferSummaryBusDelegateImpl.getInstance();
		
		Long numeroOfertasActivas = 0L;
		
		if (usuarioWeb!=null){
			numeroOfertasActivas = summaryServices.getCountOfertasActivas(usuarioWeb.getIdPropietario());	
		}

		/**
		 * Valida que no se pueda ingresar por medio de URL a al bï¿½squeda de candidatos,
		 * solo se podra ingresar cuando la empresa cuente con al menos una oferta con
		 * estatus de Activa.
		 * 
		 */
		if(numeroOfertasActivas >= 1){
			request.getSession().setAttribute(EXISTEN_OFERTAS_ACTIVAS, SI);
			
			request.getSession().setAttribute(TAB_MENU, TAB_MIS_CANDIDATOS);
			request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
			//System.out.println("-----SearchCandidatesAction.init:" + request.getSession().getAttribute(EXISTEN_OFERTAS_ACTIVAS));

	        PropertiesLoader properties = PropertiesLoader.getInstance();
			request.getSession().setAttribute(TITULO_PAGINA, "B&uacute;squeda Candidatos");
			request.getSession().setAttribute(DESCRIPCION_PAGINA, "B&uacute;squeda Candidatos, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo.");
			request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
			request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
			request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
			return mapping.findForward(FORWARD_TEMPLATE_MI_ESP_EMP);
		}else{
			request.getSession().setAttribute(EXISTEN_OFERTAS_ACTIVAS, NO);
			ActionForward forward =mapping.findForward(FORWARD_ACTION_EMPRESA);
			//System.out.println("-----SearchCandidatesAction.init:" + request.getSession().getAttribute(EXISTEN_OFERTAS_ACTIVAS));
			return forward;			
		}				
	}

	public ActionForward search(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		List<Long> indices = new ArrayList<Long>();
		HttpSession session = request.getSession();
		SearchMessage searchMessage = new SearchMessage();
		
		// Limpia las variables de la busqueda en caso de tener una busqueda anterior en la session
		//resetAttributes(request);
		
		String diferenciador = "";
		String idEntidad = "0";
		if (request.getParameter("idEntidad")!=null && !request.getParameter("idEntidad").isEmpty()) {
			idEntidad = request.getParameter("idEntidad");
		}
		
		if (request.getParameter("searchQ")!=null && !request.getParameter("searchQ").trim().isEmpty()) {
			String serchQ = request.getParameter("searchQ").trim();

			diferenciador = estableceDiferenciador(request);

			try {
				BolsasTrabajoBusDelegate services = BolsasTrabajoBusDelegateImpl.getInstance();
				//indices = services.buscarCandidatosEmpleo(serchQ);
				//searchMessage = services.MsgBuscarCandidatosEmpleo(serchQ);
				searchMessage = services.MsgBuscarCandidatosEmpleo(serchQ, Integer.parseInt(idEntidad));
				indices = searchMessage.getListCandidatos();
				
			} catch (Exception te) { te.printStackTrace();}
		}

		if (null == indices) indices = new ArrayList<Long>();

		String mensaje = BuildSearchMessage.getMessage(searchMessage);
		
		
		session.setAttribute(FULL_LIST + diferenciador, indices);
		session.setAttribute("NUM_REGISTROS", PAGE_NUM_ROW);
		session.setAttribute("searchMessage", mensaje);
		logger.info(mensaje);
		//session.setAttribute("searchQ", serchQ);
		
		session.removeAttribute("TOTAL_PAGES");
		session.removeAttribute("NUM_PAGE_LIST");
		
		ocultaBarraArticulos(request); // Indica que no se muestra la barra de articulos
		
		//page(mapping, form, request, response); // Primera paginacion
		
		request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_NEXT).getPath());
        PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "B&uacute;squeda Candidatos");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "B&uacute;squeda Candidatos, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo.");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
		return mapping.findForward(FORWARD_TEMPLATE_MI_ESP_EMP);
	}

	public ActionForward detail(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		InformacionGeneralVO infoVO = null;
		UsuarioWebVO user = (UsuarioWebVO)request.getSession().getAttribute(USUARIO_APP);
		if (null == user || user.getIdTipoUsuario() != TIPO_USUARIO.EMPRESA.getIdTipoUsuario())
			return this.redirect(mapping, form, request, response);
		long idCandidato = Utils.parseLong(request.getParameter("idc"));
		
		if (idCandidato > 0) {
			CandidatoBusDelegate candidateServices = CandidatoBusDelegateImpl.getInstance();

			infoVO = candidateServices.showCandidateDetail(idCandidato);
			
			candidateServices.contabilizaDetalleCandidato(idCandidato);

			try {
				// TODO: What is this for???
				request.removeAttribute("listaEstandares"); // Datos de certificaciones (si tiene y ha activado la opciï¿½n)
				ConocerConfigVO conocerConfigVO = candidateServices.consultaConocerConfigByIdCandidato(idCandidato);
				if (conocerConfigVO != null && conocerConfigVO.getDeseaPublicar() != null && conocerConfigVO.getDeseaPublicar() == PUBLICAR_ESTANDARES.SI.getIdOpcion()){
					List<EstandarConocerVO> estandares = candidateServices.consultaConocer(idCandidato);
					if (estandares != null && estandares.size() > 0) {
						request.setAttribute("listaEstandares", estandares);							
						logger.info("El candidato idCandidato="+idCandidato+" tiene "+estandares.size()+" estï¿½ndar(es) en el registro Conocer");							
					}
				}

				List<GradoAcademicoVO> gradoAcademicoVOs = candidateServices.buscarGrados(idCandidato);
				if (gradoAcademicoVOs != null && !gradoAcademicoVOs.isEmpty()) {
					Comparator<GradoAcademicoVO> comparator = new Comparator<GradoAcademicoVO>() {
						@Override
						public int compare(GradoAcademicoVO obj1, GradoAcademicoVO obj2) {
							//objects, including type-safe enums, follow this form
							//note that null objects will throw an exception here
							return mx.gob.stps.portal.utils.Utils.compareInt(obj1.getPrincipal(), obj2.getPrincipal());
						}
					};
					Collections.sort(gradoAcademicoVOs, comparator);
					infoVO.setSituacionAcademica(gradoAcademicoVOs.get(0).getSituacion());	// Principal one
				}

				List<IdiomaVO> idiomaVOs = candidateServices.buscarIdiomas(idCandidato);
				if (idiomaVOs != null && !idiomaVOs.isEmpty()) {
					Comparator<IdiomaVO> comparator = new Comparator<IdiomaVO>() {
						@Override
						public int compare(IdiomaVO obj1, IdiomaVO obj2) {
							//objects, including type-safe enums, follow this form
							//note that null objects will throw an exception here
							return mx.gob.stps.portal.utils.Utils.compareInt(obj1.getPrincipal(), obj2.getPrincipal());
						}
					};
					Collections.sort(idiomaVOs, comparator);
					infoVO.setIdiomas(idiomaVOs);
				}
				/**
				List<mx.gob.stps.portal.core.candidate.vo.ExpectativaLaboralVO> expectativaLaboralVOs = candidateServices.buscarExpecLaboral(idCandidato);
				if (expectativaLaboralVOs != null && !expectativaLaboralVOs.isEmpty()) {
					infoVO.setOcupacionDeseada1(expectativaLaboralVOs.get(0).getOcupacionDeseada());
					infoVO.setExperienciaOcupacion1(Catalogos.EXPERIENCIA.getDescripcion(expectativaLaboralVOs.get(0) != null ? expectativaLaboralVOs.get(0).getIdExperiencia() : 1));
					infoVO.setSalarioPretendido(mx.gob.stps.portal.core.infra.utils.Utils.formatMoney(Double.valueOf(String.valueOf(expectativaLaboralVOs.get(0).getSalarioPretendido()))));
					if (expectativaLaboralVOs.size() > 1) {
						infoVO.setOcupacionDeseada2(expectativaLaboralVOs.get(1).getOcupacionDeseada());
						infoVO.setExperienciaOcupacion2(Catalogos.EXPERIENCIA.getDescripcion(expectativaLaboralVOs.get(1) != null ? expectativaLaboralVOs.get(1).getIdExperiencia() : 1));
					}
				}**/

				List<TelefonoVO> telefonoVOs = candidateServices.getTelefonoAppService().consultarTelefonos(idCandidato, Catalogos.TIPO_PROPIETARIO.CANDIDATO.getIdTipoPropietario());
				if (telefonoVOs != null && !telefonoVOs.isEmpty()) {
					Comparator<TelefonoVO> comparator = new Comparator<TelefonoVO>() {
						@Override
						public int compare(TelefonoVO obj1, TelefonoVO obj2) {
							//objects, including type-safe enums, follow this form
							//note that null objects will throw an exception here
							return mx.gob.stps.portal.utils.Utils.compareInt(obj1.getPrincipal(), obj2.getPrincipal());
						}
					};
					Collections.sort(telefonoVOs, comparator);
					infoVO.setTelefonos(telefonoVOs);
				}

			} catch(Exception e){
				logger.error("Error al cargar el bloque de certificaciones del idCandidato="+idCandidato); e.printStackTrace();
			}
		}
		
		request.setAttribute("detalle", infoVO);
		request.setAttribute("idCandidato", idCandidato);

		String strDecVideo = getDecoratedVideo(infoVO);
		request.setAttribute("decoratedVideo", strDecVideo);
		
		request.getSession().setAttribute(BODY_JSP, mapping.findForward(JSP_SUCCESS).getPath());
        PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "B&uacute;squeda Candidatos");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "B&uacute;squeda Candidatos, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo.");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
		return mapping.findForward(FORWARD_TEMPLATE_MI_ESP_EMP);
	}
	
	private String getDecoratedVideo(InformacionGeneralVO infoVo){
		String strDecorated = "No disponible";
		char cAmpersand = '&';
		if(infoVo!=null && infoVo.getUrlVideoEstatus() ==1){			
			String strUrlVideo = infoVo.getUrlVideoCV();
			if(strUrlVideo.contains("http://www.youtube.com/watch?v=")){
				String strTemp = strUrlVideo.replace("http://www.youtube.com/watch?v=", "http://www.youtube.com/embed/");
				int iFin = strTemp.indexOf(cAmpersand);
				if(iFin>-1)
					strTemp = strTemp.substring(0,iFin);
				strDecorated =  strTemp; 
			} else if(strUrlVideo.contains("http://youtu.be/")){
				String strTemp = strUrlVideo.replace("http://youtu.be/", "http://www.youtube.com/embed/");				
				int iFin = strTemp.indexOf("?");
				if(iFin>-1){
					strTemp = strTemp.substring(0,iFin);
				}				
				strDecorated =  strTemp; 
			} else if(strUrlVideo.contains("http://www.youtube.com/embed/")) {
				strDecorated = strUrlVideo;
			} else {
				strDecorated = strUrlVideo;
			}				
		}
		return strDecorated;
	}
	
	protected String estableceDiferenciador(HttpServletRequest request){
		/*String query = request.getParameter("searchQ");
		String diferenciador = "";
		
		if (query!=null && !query.trim().isEmpty()){
			request.setAttribute("searchQ", query.trim());
			
			int hashcode = query.hashCode();
			hashcode = Math.abs(hashcode);
			
			diferenciador = hashcode>0?String.valueOf(hashcode):"";
		}

		return diferenciador;*/
		return "";
	}

	/*public ActionForward page(int pagenum, ActionMapping mapping, HttpSession session){
		return this.page(pagenum, mapping, session, "");
	}*/

	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected List getRows(int pagenum, List<?> rows, HttpSession session){

		List rowsPage = super.getRows(pagenum, rows);
		try {
			//BolsasTrabajoBusDelegate services = BolsasTrabajoBusDelegateImpl.getInstance();
			//rowsPage = services.buscadorCandidatos(rowsPage);
			SingleRegisterBusDelegate srbd = SingleRegisterBusDelegateImpl.getInstance();
			List<CandidatoVO> candidates = srbd.candidatoListByIndex(rowsPage);
			rowsPage = convert(candidates);
		} catch (Exception e) {
			e.printStackTrace();
			rowsPage = new ArrayList();
		}

		return rowsPage;
	}
	
	private List<CandidatoVo> convert(List<CandidatoVO> candidates) {
		List<CandidatoVo> list = new ArrayList<CandidatoVo>();
		DomicilioBusDelegate dbd = DomicilioBusDelegateImpl.getInstance();
		SingleRegisterBusDelegate srbd = SingleRegisterBusDelegateImpl.getInstance();
		for (CandidatoVO candidate : candidates) {
			CandidatoVo vo = new CandidatoVo();
			try {
				BeanUtils.copyProperties(vo, candidate);
				if (null == candidate.getSolicitanteVO()) continue;
				vo.setNombre(candidate.getSolicitanteVO().getNombre());
				vo.setApellido1(candidate.getSolicitanteVO().getApellido1());
				vo.setApellido2(candidate.getSolicitanteVO().getApellido2());
				List<CandidatoGradoAcademicoVO> grados = srbd.gradoAcademicoList(candidate.getIdCandidato());
				List<ExpectativaLaboralVO> expectativas = srbd.expectativaLaboralList(candidate.getIdCandidato());
				for (ExpectativaLaboralVO el : expectativas) {
					if (el.getPrincipal() == MULTIREGISTRO.PRINCIPAL.getIdOpcion()) {
						vo.setSalario(el.getSalarioPretendido());
						vo.setSubAreaLaboralDescripcion(el.getPuestoDeseado());
					}
				}
				for (CandidatoGradoAcademicoVO g : grados) {
					if (g.getPrincipal() == MULTIREGISTRO.PRINCIPAL.getIdOpcion()) {
						StringBuilder career = new StringBuilder();
						career.append(null != g.getNivelEstudio() ? g.getNivelEstudio() + " " : "");
						career.append(null != g.getCarreraEspecialidad() ? g.getCarreraEspecialidad() : "");
						vo.setCarrera(career.toString());
					}
				}
				DomicilioVO dom = dbd.getDomicilio(vo.getIdCandidato(), TIPO_PROPIETARIO.CANDIDATO.getIdTipoPropietario());
				if (null != dom) {
					RegistroEntidadesVO descdom = dbd.obtieneEntidadMunicipio(dom.getIdEntidad(), dom.getIdMunicipio());
					vo.setMunicipioEntidad(getDescDom(descdom.getEntidadDescripcion(), descdom.getMunicipioDescripcion()));
				}
				list.add(vo);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	private String getDescDom(String entidad, String municipio){
		StringBuilder municipioEntidad = new StringBuilder();
		if (null != entidad && !entidad.equalsIgnoreCase("null")) {
			municipioEntidad.append(entidad).append(",");
			if (null != municipio && !municipio.equalsIgnoreCase("null"))
				municipioEntidad.append(municipio);
		} else
			municipioEntidad.append("No disponible");
		return municipioEntidad.toString();
	}
	
	public ActionForward redirect(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		PropertiesLoader properties = PropertiesLoader.getInstance();
		request.setAttribute(URL_REDIRECT_SWB, properties.getProperty("app.swb.redirect.home"));
		return mapping.findForward(FORWARD_REDIRECT_SWB);
	}
}