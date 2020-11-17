package mx.gob.stps.portal.web.oferta.search.action;

import static mx.gob.stps.portal.web.infra.utils.Constantes.BODY_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_REDIRECT_SWB;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TITULO_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TWITTER_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.URL_ESPECIFICA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.DESCRIPCION_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FACEBOOK_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_REINTENTAR;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_TEMPLATE_RESPONSIVE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.URL_REDIRECT_SWB;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.thymeleaf.context.WebContext;

import mx.gob.stps.portal.persistencia.vo.ConocimientoHabilidadVO;
import mx.gob.stps.portal.persistencia.vo.OfertaExtranjeraDescripcionVO;
import mx.gob.stps.portal.persistencia.vo.OfertaExtranjeraPostulacionVO;
import mx.gob.stps.portal.persistencia.vo.OfertaExtranjeraVO;
import mx.gob.stps.portal.persistencia.vo.OfertasRecientesVO;
import mx.gob.stps.portal.persistencia.vo.TelefonoVO;
import mx.gob.stps.portal.core.candidate.vo.GradoAcademicoVO;
import mx.gob.stps.portal.core.candidate.vo.IdiomaVO;
import mx.gob.stps.portal.core.empresa.vo.EmpresaVO;
import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.mail.service.NotificacionService;
import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.core.infra.utils.Constantes.DISPONIBILIDAD_RADICAR;
import mx.gob.stps.portal.core.infra.utils.Constantes.DISPONIBILIDAD_VIAJAR;
import mx.gob.stps.portal.core.infra.utils.Constantes.EXPERIENCIA;
import mx.gob.stps.portal.core.infra.utils.Constantes.HABILIDADES_ACTITUDES;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_TELEFONO;
import mx.gob.stps.portal.core.oferta.busqueda.vo.OfertaPorCanalVO;
import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaCandidatoVO;
import mx.gob.stps.portal.web.candidate.delegate.CandidatoBusDelegate;
import mx.gob.stps.portal.web.candidate.delegate.CandidatoBusDelegateImpl;
import mx.gob.stps.portal.web.infra.utils.HtmlUtils;
import mx.gob.stps.portal.web.infra.utils.Utils;
import mx.gob.stps.portal.web.infra.action.PagerAction;
import mx.gob.stps.portal.web.infra.delegate.CatalogoOpcionDelegate;
import mx.gob.stps.portal.web.infra.delegate.CatalogoOpcionDelegateImpl;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.infra.utils.PropertiesLoader;
import mx.gob.stps.portal.web.offer.wrapper.PerfilJB;
import mx.gob.stps.portal.web.security.vo.UsuarioWebVO;
import mx.gob.stps.portal.web.oferta.extranjera.delegate.OfertaExtranjeraBusDelegateImpl;

public class OfertasMovilidadLaboralAction extends PagerAction {

	private static Logger logger = Logger.getLogger(OfertasMovilidadLaboralAction.class);
	
	@Override
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		this.PAGE_NUM_ROW = 10;					
	    this.PAGE_JUMP_SIZE = 10;
		try {
			List<OfertaExtranjeraVO> offerList = new ArrayList<OfertaExtranjeraVO>();
			offerList = OfertaExtranjeraBusDelegateImpl.getInstance().findAll();
			request.getSession().removeAttribute(FULL_LIST);		    
		    request.getSession().setAttribute(FULL_LIST, offerList); //paginacion
		    request.getSession().removeAttribute(NUM_PAGE_LIST);
		    request.getSession().setAttribute("NUM_REGISTROS", PAGE_NUM_ROW);
	        PropertiesLoader properties = PropertiesLoader.getInstance();
			request.getSession().setAttribute(TITULO_PAGINA, "Trabaja en el extranjero");
			request.getSession().setAttribute(DESCRIPCION_PAGINA, "Ofertas Movilidad Laboral, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo.");
			request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
			request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
			request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
		request.getSession().setAttribute(BODY_JSP, mapping.findForward("JSP_EXTERNAL").getPath());		
		return mapping.findForward(FORWARD_TEMPLATE_RESPONSIVE);
	}
	
	public ActionForward detail(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		UsuarioWebVO user = getUsuario(request.getSession());
		request.getSession().removeAttribute("ofertaExtranjera");
		long idOfertaExtranjera = Utils.parseLong(request.getParameter("id"));
		List<OfertaExtranjeraDescripcionVO> list = new ArrayList<OfertaExtranjeraDescripcionVO>();
		try {
			OfertaExtranjeraVO vo = OfertaExtranjeraBusDelegateImpl.getInstance().find(idOfertaExtranjera);
			if (null != vo)
				list = OfertaExtranjeraBusDelegateImpl.getInstance().getDescription(idOfertaExtranjera);
			request.getSession().setAttribute("requisitos",list);
			request.getSession().setAttribute("ofertaExtranjera",vo);
			request.getSession().setAttribute(TITULO_PAGINA, "Oferta de empleo en " + vo.getTituloOferta() + " en " + vo.getPais());
			if (null != user && user.getIdPropietario() > 0) {
				request.getSession().setAttribute("_user", user);
				CandidatoBusDelegate services = CandidatoBusDelegateImpl.getInstance();
				try {
					PerfilJB profile = services.loadPerfil(user.getIdPropietario());
					String profile_fullName = profile.getNombre() + " " + profile.getApellido1() + (profile.getApellido2() == null || profile.getApellido2().trim().equals("") ? "" : " " + profile.getApellido2());
					request.getSession().setAttribute("_NAME", profile_fullName);
					Integer compatibility = match(profile, vo);
					request.getSession().setAttribute("compatibility", compatibility);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (ServiceLocatorException e) {
			logger.error(e);
			e.printStackTrace();
		}
		request.getSession().setAttribute(BODY_JSP, mapping.findForward("JSP_DETAIL").getPath());
		return mapping.findForward(FORWARD_TEMPLATE_RESPONSIVE);
	}
	
	public ActionForward offerPost(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		//CREACION DEL PDF
		byte[] buf = new byte[0];
		UsuarioWebVO user = getUsuario(request.getSession());
		OfertaExtranjeraVO oferta = (OfertaExtranjeraVO)request.getSession().getAttribute("ofertaExtranjera");
		boolean isPostulated = false;
		try {
			isPostulated = OfertaExtranjeraBusDelegateImpl.getInstance().existOfertaExtranjeraPostulacion(user.getIdPropietario(), oferta.getIdOfertaExtranjera());
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		}
		if (!isPostulated) {
			buf = generarCartaPresentacion(form, request, response);
			generarNotificacionPostulacion(oferta, buf, user.getIdPropietario());
			request.getSession().setAttribute("_MSG", "Se postuló la oferta de empleo exitosamente.");
		}else request.getSession().setAttribute("_MSG", "Ya se encuentra postulado a la oferta de empleo.");
		oferta.setPostulated(isPostulated);
		request.setAttribute("_urlpageinvoke", "/trabaja-en-el-extranjero");
		request.getSession().setAttribute(BODY_JSP, mapping.findForward("JSP_DETAIL").getPath());
		return mapping.findForward(FORWARD_TEMPLATE_RESPONSIVE);
	}
	
	public ActionForward detailHome(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		long id = Utils.parseLong(request.getParameter("id"));
		String title = request.getParameter("tl");
		if (null != title && !title.isEmpty()) title = title.replace(" ", "-");
		String country = request.getParameter("cy");
		if (null != country && !country.isEmpty()) country = country.replace(" ", "-");
		String urlredirect = request.getContextPath()+"/"+id+"-busqueda-de-ofertas-de-empleo-en-el-extranjero-"+title+"-"+country;
		request.setAttribute(URL_REDIRECT_SWB, urlredirect);
		return mapping.findForward(FORWARD_REDIRECT_SWB);
	}

	public ActionForward ofertasCanadaTodas(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String nextAction = FORWARD_TEMPLATE_RESPONSIVE;
		try {
			List<OfertaPorCanalVO> ofertasCanal = new ArrayList<OfertaPorCanalVO>();
			@SuppressWarnings("unchecked")
			List<OfertasRecientesVO> ofertasCanada = (List<OfertasRecientesVO>)session.getAttribute("ofertasCanada");
		    if (null != ofertasCanada && !ofertasCanada.isEmpty()) {
		    	for (OfertasRecientesVO recent : ofertasCanada) {
		    		OfertaPorCanalVO channel = new OfertaPorCanalVO();
		    		channel.setTituloOferta(recent.getTituloOferta());
		    		channel.setUbicacion(recent.getUbicacion());
		    		channel.setEmpresa(recent.getEmpresa());
		    		channel.setSalario(recent.getSalario());
		    		channel.setFechaInicio(Utils.convert(recent.getVigencia()));
		    		ofertasCanal.add(channel);
		    	}
		    }
			
			this.PAGE_NUM_ROW = 10;					
		    this.PAGE_JUMP_SIZE = 10;

		    request.getSession().removeAttribute(FULL_LIST);		    
		    request.getSession().setAttribute(FULL_LIST, ofertasCanal); //paginacion
		    
		    request.getSession().removeAttribute(NUM_PAGE_LIST);
		    request.getSession().setAttribute("NUM_REGISTROS", PAGE_NUM_ROW);

		    request.getSession().removeAttribute("OFERTA_RECIENTE");		    
			session.setAttribute("OFERTA_RECIENTE", 3);
			
			request.getSession().setAttribute("TITULO", "Ofertas Canadá");
	        PropertiesLoader properties = PropertiesLoader.getInstance();
			request.getSession().setAttribute(TITULO_PAGINA, "Ofertas Canad&aacute;");
			request.getSession().setAttribute(DESCRIPCION_PAGINA, "Ofertas de empleo de Canad&aacute;, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo.");
			request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
			request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
			request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			nextAction = FORWARD_REINTENTAR;
		}
	    request.getSession().removeAttribute("_urlpageinvoke");
		request.setAttribute("_urlpageinvoke", "ofertasRecientes.do?method=ofertasCanada");
		request.getSession().setAttribute(BODY_JSP, mapping.findForward("JSP_TODAS").getPath());		
		return mapping.findForward(nextAction);
	}
	
	private int match(PerfilJB profile, OfertaExtranjeraVO offer) {
		int compatibility = 0;
		CatalogoOpcionDelegate service = CatalogoOpcionDelegateImpl.getInstance();
		if (profile.getPerfilLaboral().getDisponibilidadViajar() == DISPONIBILIDAD_VIAJAR.NO.getIdOpcion())
			return 0;	
		else compatibility += 20;
		if (profile.getPerfilLaboral().getDisponibilidadRadicar() == DISPONIBILIDAD_RADICAR.NO.getIdOpcion())
			return 0;
		else compatibility += 20;
		if (profile.getExpectativaLaboral().getSalarioPretendido() >= offer.getSalarioMensual())
			compatibility += 20;
		try {
			String primaryEmplacement = service.getOpcionById(Constantes.CATALOGO_OPCION_OCUPACION, profile.getExpectativaLaboral().getIdOcupacionDeseada());
			if (primaryEmplacement.equalsIgnoreCase(offer.getOcupacion()))
				compatibility += 20;
			String degree = service.getOpcionById(Constantes.CATALOGO_OPCION_CARRERA_ESPECIALIDAD, profile.getGradoPrincipal().getIdCarreraEspecialidad());
			if (degree.equalsIgnoreCase(offer.getEstudios()))
				compatibility += 20;
			String lang = service.getOpcionById(Constantes.CATALOGO_OPCION_IDIOMA, profile.getIdiomaPrincipal().getIdIdioma());
			if (lang.equalsIgnoreCase(offer.getNivelIdiomaPrincipal())) {
				compatibility += 20;
				if (profile.getIdiomaPrincipal().getIdDominio() == Constantes.DOMINIO.AVANZADO.getIdOpcion())
					compatibility += 20;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return compatibility;
	}
	
	private byte[] generarCartaPresentacion(ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		 byte[] pdf = null;
	        UsuarioWebVO usuario = getUsuario(request.getSession());
	        CandidatoBusDelegate services = CandidatoBusDelegateImpl.getInstance();
	        CatalogoOpcionDelegate catService = CatalogoOpcionDelegateImpl.getInstance();
	        long idCandidato = usuario.getIdPropietario();
	        PerfilJB perfil = null;
	        String CONFIDENCIAL = "Confidencial";
	        // Prepare the Thymeleaf evaluation context
	        final WebContext ctx = new WebContext(request, response, request.getServletContext());
           // Carga del template
	        String templateName ="cartaPresentacion/cartaTemplate";
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		    SimpleDateFormat formatter = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy");			
		    OfertaExtranjeraVO oferta = (OfertaExtranjeraVO)request.getSession().getAttribute("ofertaExtranjera");
			Date fechaInicio = oferta.getFechaRegistro();    
			Date date = Calendar.getInstance().getTime();
		    String today = formatter.format(date);
			String fechaIni = format.format(fechaInicio);
	        try {
	            if (idCandidato > -1)
	                perfil = services.loadPerfil(idCandidato);
	            if (perfil == null)
	                perfil = new PerfilJB();

	            //* BEGINING DATA GATHERING *//
	             ctx.setVariable("nombre_Contacto", "Servicio Nacional de Empleo");
	             ctx.setVariable("nombre_Empresa", "Servicio Nacional de Empleo");	            
	             ctx.setVariable("titulo_Oferta", oferta.getTituloOferta());
	             ctx.setVariable("id_Oferta", oferta.getIdOfertaExtranjera());	             
	             ctx.setVariable("puesto_requerido", oferta.getOcupacion());	 
	             ctx.setVariable("fecha_Inicio", fechaIni);	 
	             ctx.setVariable("fecha", today);
	            
	            // --- Profile --- //

	            String profile_fullName = perfil.getNombre() + " " + perfil.getApellido1() + (perfil.getApellido2() == null || perfil.getApellido2().trim().equals("") ? "" : " " + perfil.getApellido2());
	            
	            // ---ADRESS --//
	            StringBuilder direccion = new StringBuilder();
	            direccion.append(perfil.getCalle()).append(" "); // Calle
	            direccion.append(perfil.getNumeroExterior()); // # Exterior
	            if (perfil.getNumeroInterior() != null && !perfil.getNumeroInterior().equals("")) {
	            	direccion.append("-").append(perfil.getNumeroInterior()).append(" ");    // # Interior
	            }
	            ctx.setVariable("profile_fullName", profile_fullName.toUpperCase());
	            ctx.setVariable("direccion", CONFIDENCIAL);	          
	            ctx.setVariable("cp", CONFIDENCIAL);	   
	            ctx.setVariable("delegacion", CONFIDENCIAL);	   
	            ctx.setVariable("entidad", CONFIDENCIAL);
	            // --- Contact --- //
	            String contact_email = String.format("e-mail: %s", perfil.getCorreoElectronico());
	            //--
	            ctx.setVariable("contact_email", contact_email);

	            // Retrieve all phone numbers
	            // NOTE: PerfilJB.getSecundarios retrieves all phone numbers, including the main one.
	            for (int i = 0; i < perfil.getSecundarios().size(); i++) {
	                TelefonoVO telefonoVO = perfil.getSecundarios().get(i);
	                StringBuilder contact_phone = new StringBuilder();
	                contact_phone.append("Tel. ").append(TIPO_TELEFONO.getTipoTelefono(telefonoVO.getIdTipoTelefono())).append(": ");
	                contact_phone.append("(").append(telefonoVO.getAcceso()).append(")");    // Acceso
	                contact_phone.append("(").append(telefonoVO.getClave()).append(")");    // Clave Lada
	                contact_phone.append(telefonoVO.getTelefono());    // No. Telefonico
	                if (telefonoVO.getExtension() != null && !telefonoVO.getExtension().equals(""))
	                    contact_phone.append(" ext ").append(telefonoVO.getExtension()); // Extension
	                ctx.setVariable(String.format("contact_phone%d", i + 1), contact_phone.toString());
	            }
	            // --- Education --- //

	            GradoAcademicoVO gradoAcademicoVO = perfil.getGradoPrincipal();
	            String education_grade = gradoAcademicoVO.getNivel();  
	            String education_career = gradoAcademicoVO.getCarrera();
	            String education_status = gradoAcademicoVO.getSituacion();
	            // --
	            ctx.setVariable("education_grade", education_grade);
	            ctx.setVariable("education_career", education_career);
	            ctx.setVariable("education_status", education_status);
	            
	            // --- Knowledge --- //

	            IdiomaVO idiomaVO = perfil.getIdiomaPrincipal();
	            // Retrieve main language
	            if (idiomaVO != null) {
	                String knowledge_lang1 = idiomaVO.getIdioma();
	                String knowledge_langCert1 = idiomaVO.getCertificacion().equals("Ninguna")? "" : idiomaVO.getCertificacion();
	                String knowledge_langLevel1 = idiomaVO.getDominio();
	                // --
	                ctx.setVariable("knowledge_lang1", knowledge_lang1);
	                ctx.setVariable("knowledge_langCert1", knowledge_langCert1);
	                ctx.setVariable("knowledge_langLevel1", knowledge_langLevel1);
	            
	            }
	            // Retrieve aditional languages
	            if(perfil.getIdiomas()!=null && !perfil.getIdiomas().isEmpty())
		            for (int i = 0; i < perfil.getIdiomas().size(); i++) {
		                idiomaVO = perfil.getIdiomas().get(i);
		                // --
		                ctx.setVariable(String.format("knowledge_lang%d", i + 2), idiomaVO.getIdioma());
		                ctx.setVariable(String.format("knowledge_langCert%d", i + 2), idiomaVO.getCertificacion().equals("Ninguna")? "" : idiomaVO.getCertificacion());
		                ctx.setVariable(String.format("knowledge_langLevel%d", i + 2), idiomaVO.getDominio());                
		            }

				if (ctx.getVariables().containsKey("knowledge_lang1") ||
					ctx.getVariables().containsKey("knowledge_lang2") ||
					ctx.getVariables().containsKey("knowledge_lang3")) {
						ctx.setVariable("knowledge_lang", true);
					} else {
						ctx.setVariable("knowledge_lang", false);
					}

	            if (perfil.getConocimientoComputacionVO() != null) {
	                StringBuilder knowledge_computerBasics = new StringBuilder();
	                if (perfil.getConocimientoComputacionVO().getProcesadorTxt() == 1) {
	                    knowledge_computerBasics.append("Procesador de Texto").append(", ");
	                }
	                if (perfil.getConocimientoComputacionVO().getHojaCalculo() == 1) {
	                    knowledge_computerBasics.append("Hojas de Calculo").append(", ");
	                }
	                if (perfil.getConocimientoComputacionVO().getInternet() == 1) {
	                    knowledge_computerBasics.append("Internet").append(", ");
	                }
	                if (perfil.getConocimientoComputacionVO().getRedesSociales() == 1) {
	                    knowledge_computerBasics.append("Redes Sociales").append(", ");
	                }
	                if (knowledge_computerBasics.length() > 0) {
	                    knowledge_computerBasics.setLength(knowledge_computerBasics.length() - 2); // remove last two characters (comma and blank_space)
	                }
	                // --
	                ctx.setVariable("knowledge_computerBasics", knowledge_computerBasics.toString());

	                if (perfil.getConocimientoComputacionVO().getOtros() != null) {
	                    String knowledge_computerOthers = perfil.getConocimientoComputacionVO().getOtros();
	                    // --
	                    ctx.setVariable("knowledge_computerOthers", knowledge_computerOthers);
	                }
	            }

	            ConocimientoHabilidadVO conocimientoVO = perfil.getConocimientoPrincipal();
	            // Retrieve main understanding
	            if (conocimientoVO != null) {
	                String knowledge_understanding1 = conocimientoVO.getConocimientoHabilidad();
	                String knowledge_understandingExp1 = conocimientoVO.getExperiencia();
	                String knowledge_understandingDesc1 = conocimientoVO.getDescripcion();
	                // --
	                ctx.setVariable("knowledge_understanding1", knowledge_understanding1);
	                ctx.setVariable("knowledge_understandingExp1", knowledge_understandingExp1);
	                ctx.setVariable("knowledge_understandingDesc1", knowledge_understandingDesc1);
	            }
	            // Retrieve aditional understandings
	            for (int i = 0; i < perfil.getConocimientos().size(); i++) {
	                conocimientoVO = perfil.getConocimientos().get(i);
	                // --
	                ctx.setVariable(String.format("knowledge_understanding%d", i + 2), conocimientoVO.getConocimientoHabilidad());
	                ctx.setVariable(String.format("knowledge_understandingExp%d", i + 2), conocimientoVO.getExperiencia());
	                ctx.setVariable(String.format("knowledge_understandingDesc%d", i + 2), conocimientoVO.getDescripcion());
	            }

				if (ctx.getVariables().containsKey("knowledge_understanding1") ||
					ctx.getVariables().containsKey("knowledge_understanding2") ||
					ctx.getVariables().containsKey("knowledge_understanding3")) {
						ctx.setVariable("knowledge_understanding", true);
					} else {
						ctx.setVariable("knowledge_understanding", false);
					}
				
	            // Skills
	            List<Long> skillIds = services.consultaHabilidades(idCandidato);
	            StringBuilder knowledge_skillsAndAttitudes = new StringBuilder();
	            for (long i : skillIds) {
	                String opcion = "";
	                try {
	                    opcion = HABILIDADES_ACTITUDES.forIdOpcion(i).getOpcion();
	                } catch (Exception e) {
	                    logger.warn(e.getMessage());
	                }
	                knowledge_skillsAndAttitudes.append(opcion).append(", ");
	            }
	            if (knowledge_skillsAndAttitudes.length() > 0) {
	                knowledge_skillsAndAttitudes.setLength(knowledge_skillsAndAttitudes.length() - 2); // remove last two characters (comma and blank_space)
	            }
	            // --
	            ctx.setVariable("knowledge_skillsAndAttitudes", knowledge_skillsAndAttitudes.toString());

				// To know if knowledge must be displayed
	            if (ctx.getVariables().containsKey("knowledge_lang") ||
	                ctx.getVariables().containsKey("knowledge_computerBasics") ||
	                ctx.getVariables().containsKey("knowledge_computerOthers") ||
	                ctx.getVariables().containsKey("knowledge_understanding") ||
	                ctx.getVariables().containsKey("knowledge_skillsAndAttitudes")) {

	                ctx.setVariable("knowledge", true);
	            } else {
	                ctx.setVariable("knowledge", false);
	            }

	            // --- Requested Job --- //

	            String requestedJob_reqPost = catService.getOpcionById(mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_OCUPACION, perfil.getExpectativaPrincipal().getIdOcupacionDeseada());
	            String requestedJob_jobPostExp = EXPERIENCIA.getDescripcion((int) (perfil.getPerfilLaboral().getIdExperienciaTotal()));
	            String requestedJob_jobExpDesc = perfil.getPerfilLaboral().getExperiencia();// from perfil laboral
	            // --
	            ctx.setVariable("requestedJob_reqPost", requestedJob_reqPost);
	            ctx.setVariable("requestedJob_jobPostExp", requestedJob_jobPostExp);
	            ctx.setVariable("requestedJob_jobExpDesc", requestedJob_jobExpDesc);

				if ((requestedJob_reqPost != null && !requestedJob_reqPost.equals("")) ||
	                (requestedJob_jobPostExp != null && !requestedJob_jobPostExp.equals("")) ||
	                (requestedJob_jobExpDesc != null && !requestedJob_jobExpDesc.equals(""))) {
						ctx.setVariable("requestedJob", true);
	            } else {
	                ctx.setVariable("requestedJob", false);
	            }

		        String html = HtmlUtils.processHtmlTemplate(templateName, ctx);
		        pdf = HtmlUtils.convertHtmlToPdfAndWriteToBrowseStream(html, request);

	        } catch (Exception e) {
	            e.printStackTrace();
	            logger.error(e);
	        }
	        return pdf;
	 }
	
	private void generarNotificacionPostulacion(OfertaExtranjeraVO vo, byte[] buf, long idCandidato) {
		String tituloOferta = "";
		String candidatoEmail = "";
		String candidatoNombre = "";
		//String ofertaCorreoElectronicoContacto = "hlazaro@stps.gob.mx";
		String ofertaCorreoElectronicoContacto = "jvillanueva@stps.gob.mx";	

		System.out.println("Enviando correo -> " + ofertaCorreoElectronicoContacto);
		
		OfertaExtranjeraPostulacionVO post = new OfertaExtranjeraPostulacionVO();
		try {
			Date date = Calendar.getInstance().getTime();
			SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy");	
			String today = formatter.format(date);
			CandidatoBusDelegate candidateServices = CandidatoBusDelegateImpl.getInstance();
			PerfilJB perfilCandidato = candidateServices.loadPerfil(idCandidato);
			if (null!=perfilCandidato.getNombre() && !perfilCandidato.getNombre().equalsIgnoreCase("")){
				candidatoNombre = candidatoNombre + " " + perfilCandidato.getNombre();
			}				

			if(null!=perfilCandidato.getApellido1() && !perfilCandidato.getApellido1().equalsIgnoreCase("")){
				candidatoNombre = candidatoNombre + " " + perfilCandidato.getApellido1();
			}				

			if(null!=perfilCandidato.getApellido2() && !perfilCandidato.getApellido2().equalsIgnoreCase("")){
				candidatoNombre = candidatoNombre + " " + perfilCandidato.getApellido2();
			}			

			if(null!=perfilCandidato.getCorreoElectronico() && !perfilCandidato.getCorreoElectronico().equalsIgnoreCase("")){
				candidatoEmail = perfilCandidato.getCorreoElectronico();
			}
			if (null!=vo.getTituloOferta() && !vo.getTituloOferta().equalsIgnoreCase("")){
				tituloOferta = vo.getTituloOferta();
			}
			String pdfOutputName = "CP"+"_"+ perfilCandidato.getCurp()+"_"+today+".pdf";
			if (ofertaCorreoElectronicoContacto!=null && !ofertaCorreoElectronicoContacto.isEmpty()) {
				EmpresaVO empresa = new EmpresaVO();
				empresa.setContactoEmpresa("");
				OfertaCandidatoVO oc = new OfertaCandidatoVO();
				oc.setIdCandidato(idCandidato);
				oc.setFechaAlta(new Date());
				oc.setIdOfertaEmpleo(vo.getIdOfertaExtranjera());
				NotificacionService notificacionService = new NotificacionService();
				notificacionService.notificacionPostuladoToEmpresa(new EmpresaVO(), oc, candidatoNombre,candidatoEmail, 
				tituloOferta, ofertaCorreoElectronicoContacto, "", buf, pdfOutputName, false);
			}
			post.setIdCandidato(idCandidato);
			post.setIdOfertaExtranjera(vo.getIdOfertaExtranjera());
			post.setIdIdiomaC(perfilCandidato.getIdiomaPrincipal().getIdIdioma());
			post.setIdDominioC(perfilCandidato.getIdiomaPrincipal().getIdDominio());
			post.setIdOcupacionC(perfilCandidato.getExpectativaLaboral().getIdOcupacionDeseada());
			post.setSalarioC(perfilCandidato.getExpectativaLaboral().getSalarioPretendido());
			post.setIdNivelEstudioC(perfilCandidato.getGradoPrincipal().getIdNivelEstudio());
			post.setIdCarreraEspecialidadC(perfilCandidato.getGradoPrincipal().getIdCarreraEspecialidad());
			OfertaExtranjeraBusDelegateImpl.getInstance().saveMatch(post);
		} catch (BusinessException e) {
			e.printStackTrace(); logger.error(e);
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();  logger.error(e);
		}
	}
}
