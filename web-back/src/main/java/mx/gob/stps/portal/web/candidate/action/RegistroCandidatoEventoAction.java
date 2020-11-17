package mx.gob.stps.portal.web.candidate.action;

import static mx.gob.stps.portal.web.infra.utils.Constantes.BODY_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_TEMPLATE_MI_ESP_CAND;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TITULO_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.DESCRIPCION_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FACEBOOK_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TWITTER_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.URL_ESPECIFICA;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.persistencia.entity.CandidatoEvento;
import mx.gob.stps.portal.persistencia.entity.EventoFolio;
import mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO;
import mx.gob.stps.portal.persistencia.vo.ComprobanteRegistroEventoCandidatoVO;
import mx.gob.stps.portal.persistencia.vo.EmpresaEventoVO;
import mx.gob.stps.portal.persistencia.vo.EventoVO;
import mx.gob.stps.portal.persistencia.vo.OfertaEmpleoVO;
import mx.gob.stps.portal.utils.Catalogos;
import mx.gob.stps.portal.utils.PropertiesLoader;
import mx.gob.stps.portal.web.candidate.delegate.RegistroCandidatoEventoBusDelegate;
import mx.gob.stps.portal.web.candidate.delegate.RegistroCandidatoEventoBusDelegateImpl;
import mx.gob.stps.portal.web.candidate.form.RegistraCandidatoEventoForm;
import mx.gob.stps.portal.web.candidate.vo.CandidatoAjaxVO;
import mx.gob.stps.portal.web.infra.action.PagerAction;
import mx.gob.stps.portal.web.infra.delegate.CatalogoOpcionDelegate;
import mx.gob.stps.portal.web.infra.delegate.CatalogoOpcionDelegateImpl;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.infra.utils.CandidatoHeadUtil;
import mx.gob.stps.portal.web.infra.utils.TablaBusquedaEventosFerias;
import mx.gob.stps.portal.web.security.vo.UsuarioWebVO;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/*
 * KSV    22-10-2014
 * Clase que es el controlador para el registro del candidato al evento
 * */

public class RegistroCandidatoEventoAction extends PagerAction{
	
	private RegistraCandidatoEventoForm registraCandidatoEventoForm = null;
	private List<EventoVO> eventos;
	private static final Integer HORARIO_INI_ATN = 9;
	private static final Integer HORARIO_FIN_ATN = 21;
	private static final String TABLA_EMPRESAS = "_EMPRESAS";
	private EventoFolio eventoFolio;
	private CandidatoEvento eventoCandidato;
	private CandidatoAjaxVO candidato;
	private EventoVO eventoVO;
	public static final String rutaHeader = "bannerComprobanteFerias.jpg";
	public static final String rutaFooter="footerFerias.jpg";
	private List<OfertaEmpleoVO> ofertas;
	public static final int BAREVTO = 5;//valor maximo para integrar el idevento al codigo de barras
	public static final int BARFOL = 7;//valor maximo par integrar el folio al codigo de barras

	private static Logger logger = Logger.getLogger(RegistroCandidatoEventoAction.class);
			
	public ActionForward init(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) {
		request.getSession().removeAttribute("showExito");
		request.getSession().removeAttribute("showError");
		if(request.getParameter("imprimir")== null){
			request.getSession().removeAttribute("eventoSeleccionado");
		}
		CatalogoOpcionDelegate catService = CatalogoOpcionDelegateImpl.getInstance();
		List<CatalogoOpcionVO> locationEntityCatalog = null;
		HttpSession session = request.getSession();
		try {
			candidato = CandidatoHeadUtil.actualizarEstatusPpcHedaer(request);
		} catch (ServiceLocatorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setEventos(new ArrayList<EventoVO>());
		RegistroCandidatoEventoBusDelegate regService = RegistroCandidatoEventoBusDelegateImpl.getInstance();
		registraCandidatoEventoForm = (RegistraCandidatoEventoForm) form; 
		try{
			locationEntityCatalog = catService.consultarCatalogo(Constantes.CATALOGO_OPCION_ENTIDAD_FEDERATIVA);
			  CatalogoOpcionVO locationEntityTmp = null;
		        for (CatalogoOpcionVO locationEntity : locationEntityCatalog) {
		        	if (locationEntity.getIdCatalogoOpcion() == Constantes.ENTIDADES_FEDERATIVAS.NACIDO_EN_EL_EXTRANJERO.getIdEntidad()) { // EXCLUDE 'Nacido en el extranjero'
		        		locationEntityTmp = locationEntity;
		        		break;
		        	}
		        }
		        if (locationEntityTmp != null) {
		        	locationEntityCatalog.remove(locationEntityTmp);
		        }
			registraCandidatoEventoForm.setLocationEntityCatalog(locationEntityCatalog);
			setEventos(regService.initEventosFerias(null));
			registraCandidatoEventoForm.setEventos(getEventos());
			registraCandidatoEventoForm.setEventosSize(getEventos().size());
			request.getSession().removeAttribute("total");
			request.getSession().setAttribute("total", eventos.size());
			logger.info("nombre =  "+candidato.getNombre());
		}catch(ServiceLocatorException e){
			e.printStackTrace();
		}
		this.PAGE_NUM_ROW = 5;
		/*request.getSession().removeAttribute("NUM_PAGE_LIST");
		request.getSession().removeAttribute("NUM_REGISTROS");			
		request.getSession().setAttribute("NUM_REGISTROS", PAGE_NUM_ROW);
		
		request.getSession().removeAttribute("total");			
		request.getSession().setAttribute("total", getEventos().size());*/
		session.removeAttribute(PAGE_LIST);
		session.removeAttribute("NUM_RECORDS_TOTAL");
		session.removeAttribute(TOTAL_PAGES);
		session.removeAttribute("NUM_PAGE_LIST");
		session.removeAttribute("NUM_REGISTROS");	
		session.removeAttribute("PAGE_JUMP_SIZE");
		session.setAttribute("NUM_REGISTROS", PAGE_NUM_ROW);
		
		session.removeAttribute(FULL_LIST);
		session.setAttribute(FULL_LIST, getEventos());
		//session.setAttribute(PAGE_LIST, empresas);
		session.setAttribute("NUM_RECORDS_TOTAL",getEventos().size());
		request.getSession().removeAttribute("total");
		request.getSession().setAttribute("total", getEventos().size());
		
		Integer totalPages = getEventos().size()/PAGE_NUM_ROW; 
		if (getEventos().size()%PAGE_NUM_ROW != 0){
			totalPages ++; 
		}
		session.setAttribute(TOTAL_PAGES, totalPages);				
						
		//Registros
		List rowsPage = getRows(1, getEventos(), session);    				//Se obtienen los registros a mostrar en la pagina
		session.removeAttribute("NUM_RECORDS_VISIBLE");
		session.setAttribute("NUM_RECORDS_VISIBLE", rowsPage.size());		//numero de registros mostrados en la pagina actual
		session.setAttribute("PAGE_JUMP_SIZE", PAGE_JUMP_SIZE);				
		session.setAttribute(PAGE_LIST, rowsPage);
		session.setAttribute("NUM_PAGE_LIST", 1);
		
		
		session.setAttribute(BODY_JSP, mapping.findForward("JSP").getPath());
		PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Registrarme a un evento Ferias de Empleo");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Espacio Candidato, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
		return mapping.findForward(FORWARD_TEMPLATE_MI_ESP_CAND);
		//return mapping.findForward(FORWARD_TEMPLATE_REGISTRO_CANDIDATO_EVENTO);//se quitó para probar nuevo template
	}

	
	
	public ActionForward cargaEventos(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		setEventos(new ArrayList<EventoVO>());
		Integer idEntidad= null;
		RegistroCandidatoEventoBusDelegate regService = RegistroCandidatoEventoBusDelegateImpl.getInstance();
		registraCandidatoEventoForm = (RegistraCandidatoEventoForm) form;
		HttpSession session = request.getSession();
		
		if(request.getParameter("locationEntity") != null){
			idEntidad = Integer.parseInt(request.getParameter("locationEntity"));
			if(idEntidad == 0)
				idEntidad = null;
		}
		//logger.info("el identidad es "+idEntidad);
		try {
			setEventos(regService.initEventosFerias(idEntidad));
			registraCandidatoEventoForm.setEventos(getEventos());
			registraCandidatoEventoForm.setEventosSize(getEventos().size());
			session.removeAttribute(FULL_LIST);
			session.setAttribute(FULL_LIST, getEventos());
			request.getSession().removeAttribute("total");
			request.getSession().setAttribute("total", eventos.size());
			
		} catch (ServiceLocatorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return page(mapping,form,request,response);
	}
	
	public ActionForward cargaDetalleEvento(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		List<EmpresaEventoVO> empresas = new ArrayList<EmpresaEventoVO>();
		RegistroCandidatoEventoBusDelegate regService = RegistroCandidatoEventoBusDelegateImpl.getInstance();
		registraCandidatoEventoForm = (RegistraCandidatoEventoForm)form;
		Long idEvento = null;
		HttpSession session = request.getSession();
		if(request.getParameter("idEvento") != null){
			StringBuilder dom = new StringBuilder();
			idEvento = Long.parseLong(request.getParameter("idEvento"));
			//setIdEvento(idEvento.intValue());
			//registraCandidatoEventoForm.setIdEvento(idEvento);
			for(EventoVO evento : getEventos()){
				if(evento.getIdEvento() == idEvento){
					setEventoVO(evento);
					if(evento.getHoraAtencionInicio() != null && evento.getHoraAtencionFin() != null)
						evento.setHorario("De "+evento.getHoraAtencionInicio()+":00 A "+evento.getHoraAtencionFin()+":00");
					else
						evento.setHorario("De "+HORARIO_INI_ATN+":00 A "+HORARIO_FIN_ATN+":00");
					Catalogos.TIPO_VIALIDAD vialidad= Catalogos.TIPO_VIALIDAD.getTipoVialidad(eventoVO.getIdTipoVialidad());
					dom.append(vialidad.getTipoVialidad()).append(": ").append(eventoVO.getCalle());
					if(evento.getEntreCalle() != null && evento.getyCalle() != null)
						dom.append(", entre calles: ").append(evento.getEntreCalle()).append(" y ").append(evento.getyCalle());
					dom.append(", Número exterior: ").append(evento.getNumeroExterior());
					if(evento.getNumeroInterior() != null)
						dom.append(", Número interior: ").append(evento.getNumeroInterior());
					if(evento.getIdColonia() != null)
						dom.append(", Colonia: ").append(evento.getColonia());
					dom.append(", Localidad: ").append(evento.getLocalidad());
					dom.append(", Municipio o Delegación: ").append(evento.getMunicipio());
					dom.append(", Entidad federativa: ").append(evento.getEntidad());
					dom.append(", CP: ").append(evento.getCodigoPostal());
					evento.setLugar(dom.toString());
					registraCandidatoEventoForm.setEvento(evento);
					
				}
			}
			try {
				empresas = regService.initEmpresasEvento(idEvento);
				registraCandidatoEventoForm.setEmpresasEvento(empresas);
		   } catch (ServiceLocatorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.PAGE_NUM_ROW = 5;
			request.getSession().removeAttribute("NUM_PAGE_LIST");
			request.getSession().removeAttribute("NUM_REGISTROS");			
			request.getSession().setAttribute("NUM_REGISTROS", PAGE_NUM_ROW);
			session.removeAttribute(FULL_LIST);
			session.setAttribute(FULL_LIST, empresas);
			//request.getSession().removeAttribute("total");
			//request.getSession().setAttribute("total", empresas.size());	
		}
		session.setAttribute(BODY_JSP, mapping.findForward("DETALLE_EVENTO").getPath());
		//return mapping.findForward(FORWARD_TEMPLATE_REGISTRO_CANDIDATO_EVENTO);//se quitó pa probar nuevo template
		PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Registro de candidato");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Registro de candidato, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
		return mapping.findForward(FORWARD_TEMPLATE_MI_ESP_CAND);
	}
	
	@SuppressWarnings("unchecked")
	public ActionForward orderByColumn(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		registraCandidatoEventoForm = (RegistraCandidatoEventoForm) form; 
		String orderType = request.getParameter("orderType");
		String columnNumber = request.getParameter("columnNumber");
		List<EventoVO> resultado = null;
		
		try{	
			registraCandidatoEventoForm.setOrderType(orderType);
			registraCandidatoEventoForm.setColumnNumber(Integer.parseInt(columnNumber));
				
			resultado = (List<EventoVO>) session.getAttribute(FULL_LIST);
			
			TablaBusquedaEventosFerias tabla = new TablaBusquedaEventosFerias(resultado);
			tabla.ordenarTabla(Integer.parseInt(columnNumber), orderType);
			resultado = tabla.getLista();
		
			session.removeAttribute(FULL_LIST);
			session.setAttribute(FULL_LIST, resultado);
			this.PAGE_NUM_ROW = 5;
			request.getSession().removeAttribute("NUM_PAGE_LIST");
			request.getSession().removeAttribute("NUM_REGISTROS");			
			request.getSession().setAttribute("NUM_REGISTROS", PAGE_NUM_ROW);
			request.getSession().removeAttribute("total");			
			request.getSession().setAttribute("total", resultado.size());
			//logger.info("se ordenó la lista");
		}catch(Exception e){
			logger.error(e);
		}
		//return mapping.findForward(FORWARD_TEMPLATE_REGISTRO_CANDIDATO_EVENTO);
		return page(mapping, form, request, response);
	}

	public ActionForward pageTable (ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		String difTablaPager = request.getParameter("tablaPager");
		int pagenum = 1;
		return this.page(pagenum, mapping, session, difTablaPager);
	}
	
	public ActionForward redirectToVirtualEvent(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		if(request.getParameter("idEventoVirtual")!= null){
			Long idEventoVirtual =Long.parseLong(request.getParameter("idEventoVirtual"));
			PropertiesLoader properties = PropertiesLoader.getInstance();
			String urlFeriasEventoVirtual= properties.getProperty("app.domain.ferias.virtual");
			try {
				response.sendRedirect(urlFeriasEventoVirtual+"/content/virtual/HomeFeriasVirtuales.jsf?faces-redirect=true&idEventoSel="+idEventoVirtual);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public ActionForward imprimirComprobante(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		
		ofertas = new ArrayList<OfertaEmpleoVO>();
		RegistroCandidatoEventoBusDelegate regService = RegistroCandidatoEventoBusDelegateImpl.getInstance();
		response.setContentType("application/pdf");
		response.addHeader("Content-disposition","attachment; filename=\"comprobanteRegistro.pdf\"");
		UsuarioWebVO usuarioFirmado = null;
		if(request.getSession().getAttribute("USUARIO_APP") != null){
			usuarioFirmado =(UsuarioWebVO) request.getSession().getAttribute("USUARIO_APP");
		}
		if(request.getSession().getAttribute("eventoSeleccionado")!= null){
			setEventoVO((EventoVO) request.getSession().getAttribute("eventoSeleccionado"));
		
		try {
			
			OutputStream servletOutputStream = response.getOutputStream();
			//regService.imprimeComprobantePDF(servletOutputStream, ofertas, rutaHeader, rutaFooter, candidato.getIdCandidato(), getEventoVO().getIdEvento());
			ofertas = regService.consultaOfertasCompatiblesEvento((int) getEventoVO().getIdEvento(), candidato.getIdCandidato(), Integer.valueOf(candidato.getPpcEstatusIdOpcion()));
			SimpleDateFormat sd = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy', Hora: ' H:mm", new Locale("es","ES"));
			ComprobanteRegistroEventoCandidatoVO comprobante = new ComprobanteRegistroEventoCandidatoVO();
			setEventoCandidato(regService.initEventosCandidato(candidato.getIdCandidato(), getEventoVO().getIdEvento()));
			comprobante.setFolioRegCandi(getEventoCandidato().getFolioRegistro());
			comprobante.setFechaEvento(getEventoVO().getFechaE());
			comprobante.setHorarioAtnEvento(getEventoVO().getHorario());
			comprobante.setLugarEvento(getEventoVO().getEntidad()+", "+getEventoVO().getSede());
			comprobante.setNombreCandidato(candidato.getNombre());
			comprobante.setNombreEvento(getEventoVO().getEvento());
			comprobante.setUsuarioCandidato(usuarioFirmado.getUsuario());
			comprobante.setOfertasCompatibles(ofertas);
			List<ComprobanteRegistroEventoCandidatoVO> lista = new ArrayList<ComprobanteRegistroEventoCandidatoVO>();
		
				//comprobante = eventoFacade.consultaCandidatoEventoImprimir(candidato.getIdCandidato(), getEventoVO().getIdEvento());
				long now = System.currentTimeMillis();
				Calendar calendar = Calendar.getInstance();
				calendar.setTimeInMillis(now);
				Date fechaHoy = calendar.getTime();
				comprobante.setFechaComprobante(sd.format(fechaHoy));
				String idevto = String.valueOf(getEventoVO().getIdEvento());
				StringBuilder s = new StringBuilder();
				
				int tam = idevto.length();
				int agreg = BAREVTO-tam;
				for(int j = 0; j <agreg; j++){
					s.append("0");
				}
				s.append(idevto);
				Long folR = comprobante.getFolioRegCandi();
				String folre = folR.toString();
				tam = folre.length();
				agreg = BARFOL - tam;
				for(int k = 0; k < agreg; k++){
					s.append("0");
				}
				s.append(folre);
				String codigo = s.toString();
				comprobante.setCodigoBarras(codigo);
				comprobante.setOfertasCompatibles(ofertas);
				lista.add(comprobante);
			
			HashMap parametros = new HashMap();
			InputStream istr = null;
			
			parametros.put("logo", this.getClass().getClassLoader().getResourceAsStream(rutaHeader));
			parametros.put("logoFooter",this.getClass().getClassLoader().getResourceAsStream(rutaFooter));
			JRBeanCollectionDataSource jrb = new JRBeanCollectionDataSource(lista);
			logger.info("el jrb es "+jrb.toString());
			if(ofertas.isEmpty()){
					istr = this.getClass().getClassLoader().getResourceAsStream("comprobanteRegistroCandidatoNoCompat.jrxml");
				
			}else{
					istr = this.getClass().getClassLoader().getResourceAsStream("comprobanteRegistroCandidato.jrxml");
			}
			
			
			logger.info("el istr sí está "+istr.toString());
			JasperDesign jasperDes = JRXmlLoader.load(istr);
			JasperReport reporJ = JasperCompileManager.compileReport(jasperDes);
			JasperPrint impRep = JasperFillManager.fillReport(reporJ, parametros,jrb);
			//JasperViewer.viewReport(impRep);
			
			JasperExportManager.exportReportToPdfStream(impRep, servletOutputStream);
			//logger.info("el servlet output "+servletOutputStream.toString());
			
			
			servletOutputStream.flush();
			servletOutputStream.close();
			
			request.getSession().removeAttribute("eventoSeleccionado");
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		} catch (JRException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e);
		}
		}
		return null;
		
	}
	
	public RegistraCandidatoEventoForm getRegistraCandidatoEventoForm() {
		return registraCandidatoEventoForm;
	}

	public void setRegistraCandidatoEventoForm(
			RegistraCandidatoEventoForm registraCandidatoEventoForm) {
		this.registraCandidatoEventoForm = registraCandidatoEventoForm;
	}

	public List<EventoVO> getEventos() {
		return eventos;
	}

	public void setEventos(List<EventoVO> eventos) {
		this.eventos = eventos;
	}




	public EventoFolio getEventoFolio() {
		return eventoFolio;
	}


	public void setEventoFolio(EventoFolio eventoFolio) {
		this.eventoFolio = eventoFolio;
	}


	public EventoVO getEventoVO() {
		return eventoVO;
	}


	public void setEventoVO(EventoVO eventoVO) {
		this.eventoVO = eventoVO;
	}


	public List<OfertaEmpleoVO> getOfertas() {
		return ofertas;
	}


	public void setOfertas(List<OfertaEmpleoVO> ofertas) {
		this.ofertas = ofertas;
	}



	public CandidatoEvento getEventoCandidato() {
		return eventoCandidato;
	}



	public void setEventoCandidato(CandidatoEvento eventoCandidato) {
		this.eventoCandidato = eventoCandidato;
	}
	

}
