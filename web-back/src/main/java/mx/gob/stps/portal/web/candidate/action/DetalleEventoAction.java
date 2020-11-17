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

import mx.gob.stps.portal.mail.exception.MailException;
import mx.gob.stps.portal.mail.service.MailService;
import mx.gob.stps.portal.mail.template.FieldVO;
import mx.gob.stps.portal.persistencia.entity.CandidatoEvento;
import mx.gob.stps.portal.persistencia.entity.EventoFolio;
import mx.gob.stps.portal.persistencia.vo.ComprobanteRegistroEventoCandidatoVO;
import mx.gob.stps.portal.persistencia.vo.EmpresaEventoVO;
import mx.gob.stps.portal.persistencia.vo.EventoVO;
import mx.gob.stps.portal.persistencia.vo.OfertaEmpleoVO;
import mx.gob.stps.portal.utils.Catalogos.PLANTILLA_CORREO;
import mx.gob.stps.portal.utils.Catalogos;
import mx.gob.stps.portal.utils.PropertiesLoader;
import mx.gob.stps.portal.web.candidate.delegate.RegistroCandidatoEventoBusDelegate;
import mx.gob.stps.portal.web.candidate.delegate.RegistroCandidatoEventoBusDelegateImpl;
import mx.gob.stps.portal.web.candidate.form.RegistraCandidatoEventoForm;
import mx.gob.stps.portal.web.candidate.vo.CandidatoAjaxVO;
import mx.gob.stps.portal.web.infra.action.PagerAction;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.infra.utils.CandidatoHeadUtil;
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


public class DetalleEventoAction extends PagerAction {
	
	private RegistraCandidatoEventoForm registraCandidatoEventoForm = null;
	private List<EventoVO> eventos;//ya tampoco son necesarios
	private static final Integer HORARIO_INI_ATN = 9;
	private static final Integer HORARIO_FIN_ATN = 21;
	private EventoFolio eventoFolio;
	private CandidatoAjaxVO candidato;
	private MailService mailService = MailService.getInstance();
	private EventoVO eventoVO;
	public static final String rutaHeader = "bannerComprobanteFerias.jpg";
	public static final String rutaFooter="footerFerias.jpg";
	private List<OfertaEmpleoVO> ofertas;
	private CandidatoEvento eventoCandidato;
	public static final int BAREVTO = 5;//valor maximo para integrar el idevento al codigo de barras
	public static final int BARFOL = 7;//valor maximo par integrar el folio al codigo de barras
	
	
	private static Logger logger = Logger.getLogger(DetalleEventoAction.class);
	
	@Override
	public ActionForward init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		List<EmpresaEventoVO> empresas = new ArrayList<EmpresaEventoVO>();
		RegistroCandidatoEventoBusDelegate regService = RegistroCandidatoEventoBusDelegateImpl.getInstance();
		registraCandidatoEventoForm = (RegistraCandidatoEventoForm)form;
		HttpSession session = request.getSession();
		try {
			candidato = CandidatoHeadUtil.actualizarEstatusPpcHedaer(request);
		} catch (ServiceLocatorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Long idEvento = null;
	//	request.getSession().removeAttribute("showExito");
	  //  request.getSession().removeAttribute("showError");
		if(request.getParameter("idEvento") != null){
			try {
				StringBuilder dom = new StringBuilder();
				idEvento = Long.parseLong(request.getParameter("idEvento"));
				eventoVO = regService.buscaEventoPorId(idEvento);
				registraCandidatoEventoForm.setIdEvento(idEvento);
				if(eventoVO.getHoraAtencionInicio() != null && eventoVO.getHoraAtencionFin() != null)
					eventoVO.setHorario("De "+getRightTime(Integer.parseInt(eventoVO.getHoraAtencionInicio()))+":00 "+obtainMeridian(Integer.parseInt(eventoVO.getHoraAtencionInicio()))+" A "+getRightTime(Integer.parseInt(eventoVO.getHoraAtencionFin()))+":00 "
				+obtainMeridian(Integer.parseInt(eventoVO.getHoraAtencionFin())));
				else
					eventoVO.setHorario("De "+getRightTime(HORARIO_INI_ATN)+":00 "+obtainMeridian(HORARIO_INI_ATN)+" A "+getRightTime(HORARIO_FIN_ATN)+":00 "+obtainMeridian(HORARIO_FIN_ATN));
				Catalogos.TIPO_VIALIDAD vialidad= Catalogos.TIPO_VIALIDAD.getTipoVialidad(eventoVO.getIdTipoVialidad());
				dom.append(vialidad.getTipoVialidad()).append(": ").append(eventoVO.getCalle());
				if(eventoVO.getEntreCalle() != null && eventoVO.getyCalle() != null)
					dom.append(", entre calles: ").append(eventoVO.getEntreCalle()).append(" y ").append(eventoVO.getyCalle());
				dom.append(", Número exterior: ").append(eventoVO.getNumeroExterior());
				if(eventoVO.getNumeroInterior() != null)
					dom.append(", Número interior: ").append(eventoVO.getNumeroInterior());
				if(eventoVO.getIdTipoAsentamiento() == 7){
					if(eventoVO.getColonia()!= null)
						dom.append(", Colonia: ").append(eventoVO.getColonia());
				}else{
					if(eventoVO.getIdTipoAsentamiento()!= null && eventoVO.getAsentamiento()!= null){
						Catalogos.TIPO_ASENTAMIENTO asentamiento = Catalogos.TIPO_ASENTAMIENTO.getTipoAsentamiento(eventoVO.getIdTipoAsentamiento());
						dom.append(", ").append(asentamiento.getDescripcion()).append(": ").append(eventoVO.getAsentamiento());
					}
				}
				dom.append(", Localidad: ").append(eventoVO.getLocalidad());
				dom.append(", Municipio o Delegación: ").append(eventoVO.getMunicipio());
				dom.append(", Entidad federativa: ").append(eventoVO.getEntidad());
				dom.append(", CP: ").append(eventoVO.getCodigoPostal());
				eventoVO.setLugar(dom.toString());
				registraCandidatoEventoForm.setEvento(eventoVO);
				session.removeAttribute("eventoSeleccionado");
				session.setAttribute("eventoSeleccionado",getEventoVO());
				empresas = regService.initEmpresasEvento(idEvento);
				registraCandidatoEventoForm.setEmpresasEvento(empresas);
				request.getSession().removeAttribute("registered");
				eventoCandidato = regService.initEventosCandidato(candidato.getIdCandidato(), idEvento);
				if(eventoCandidato == null){
					registraCandidatoEventoForm.setRegistrado(false);
					request.getSession().setAttribute("registered", 0);
				}else{
					registraCandidatoEventoForm.setRegistrado(true);
					request.getSession().setAttribute("registered", 1);
				}
				this.PAGE_NUM_ROW = 5;
				session.removeAttribute(PAGE_LIST+"_EMPRESAS");
				session.removeAttribute("NUM_RECORDS_TOTAL_EMPRESAS");
				session.removeAttribute(TOTAL_PAGES+"_EMPRESAS");
				session.removeAttribute("NUM_PAGE_LIST_EMPRESAS");
				session.removeAttribute("NUM_REGISTROS");	
				session.removeAttribute("PAGE_JUMP_SIZE_EMPRESAS");
				session.setAttribute("NUM_REGISTROS", PAGE_NUM_ROW);
				
				session.removeAttribute(FULL_LIST+"_EMPRESAS");
				session.setAttribute(FULL_LIST+"_EMPRESAS", empresas);
				//session.setAttribute(PAGE_LIST, empresas);
				session.setAttribute("NUM_RECORDS_TOTAL_EMPRESAS",empresas.size());
				request.getSession().removeAttribute("total");
				request.getSession().setAttribute("total", empresas.size());
				
				Integer totalPages = empresas.size()/PAGE_NUM_ROW; 
				if (empresas.size()%PAGE_NUM_ROW != 0){
					totalPages ++; 
				}
				session.setAttribute(TOTAL_PAGES+"_EMPRESAS", totalPages);				
								
				//Registros
				List rowsPage = getRows(1, empresas, session);    				//Se obtienen los registros a mostrar en la pagina
				session.removeAttribute("NUM_RECORDS_VISIBLE_EMPRESAS");
				session.setAttribute("NUM_RECORDS_VISIBLE_EMPRESAS", rowsPage.size());		//numero de registros mostrados en la pagina actual
				session.setAttribute("PAGE_JUMP_SIZE_EMPRESAS", PAGE_JUMP_SIZE);				
				session.setAttribute(PAGE_LIST+"_EMPRESAS" , rowsPage);
				session.setAttribute("NUM_PAGE_LIST_EMPRESAS", 1);
				} catch (ServiceLocatorException e1) {
					e1.printStackTrace();
				}
		}
		session.setAttribute(BODY_JSP, mapping.findForward("DETALLE_EVENTO").getPath());
		
		//return mapping.findForward(FORWARD_TEMPLATE_REGISTRO_CANDIDATO_EVENTO);
		PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Detalle del evento");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Detalle del evento, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
		return mapping.findForward(FORWARD_TEMPLATE_MI_ESP_CAND);
	}
	
	public ActionForward reset(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		request.getSession().removeAttribute("showExito");
		request.getSession().removeAttribute("showError");
		return mapping.findForward(FORWARD_TEMPLATE_MI_ESP_CAND);
	}
	
	public String obtainMeridian(Integer time){
		String meridiano = null;
		if(time < 12 && time >=0)
			meridiano = "am";
		else if(time >= 12 && time <24)
			meridiano = "pm";
		else if(time == 24)
			meridiano = "am";
		return meridiano;
	}
	
	public Integer getRightTime(Integer time){
		Integer rightTime= null;
		if(time >= 8 && time <13){
			rightTime = time;
		}else if(time >12 && time <=24){
			switch (time){
				case 13:
					rightTime = 1;
					break;
				case 14:
					rightTime = 2;
					break;
				case 15:
					rightTime = 3;
					break;
				case 16:
					rightTime = 4;
					break;
				case 17:
					rightTime = 5;
				case 18:
					rightTime = 6;
					break;
				case 19:
					rightTime = 7;
					break;
				case 20:
					rightTime = 8;
					break;
				case 21:
					rightTime = 9;
					break;
				case 22:
					rightTime = 10;
					break;
				case 23:
					rightTime = 11;
					break;
				case 24:
					rightTime = 12;
					break;
				default:
					rightTime = 9;
			}
				
		}
		return rightTime;
	}
	
	public ActionForward redirectToVirtualEvent(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		Long idEventoVirtual = getEventoVO().getIdEvento();
		//PropertiesLoader properties = PropertiesLoader.getInstance();
		String urlFeriasEventoVirtual= PropertiesLoader.getInstance().getProperty("app.domain.ferias.virtual");
		try {
			response.sendRedirect(urlFeriasEventoVirtual+"/content/virtual/HomeFeriasVirtuales.jsf?faces-redirect=true&idEventoSel="+idEventoVirtual);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public ActionForward registraAEvento(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		RegistroCandidatoEventoBusDelegate regService = RegistroCandidatoEventoBusDelegateImpl.getInstance();
		RegistraCandidatoEventoForm regForm = (RegistraCandidatoEventoForm) form;
		Long idRegistro = null;
		request.getSession().removeAttribute("showExito");
		request.getSession().removeAttribute("registered");
		request.getSession().removeAttribute("showError");
		try {
			eventoFolio = regService.findEventoFolio(getEventoVO().getIdEvento());
			int folioReg = (int) eventoFolio.getFolioCandidato()+1;
			idRegistro = regService.registrarCandidatoEvento(candidato.getIdCandidato(), (int) getEventoVO().getIdEvento(), folioReg, candidato.getIdUsuario());
			if(idRegistro != null){
				request.getSession().setAttribute("registered", 1);
				request.getSession().setAttribute("showExito", 1);
			}else{
				request.getSession().setAttribute("registered", 0);
				request.getSession().setAttribute("showError", 1);
			}
			eventoFolio.setFolioCandidato(folioReg);
			regService.updateFolio(eventoFolio);
			regService.registraBitacora(candidato.getIdUsuario(), idRegistro, folioReg, candidato.getIdCandidato(), (int) getEventoVO().getIdEvento());
			
			ofertas = regService.consultaOfertasCompatiblesEvento((int) getEventoVO().getIdEvento(), candidato.getIdCandidato(), Integer.valueOf(candidato.getPpcEstatusIdOpcion()));
			List<FieldVO> parametros = new ArrayList<FieldVO>();
			if(candidato.getCorreoElectronico() != null){
				parametros.add(FieldVO.getInstance("nombre", candidato.getNombre()));
				parametros.add(FieldVO.getInstance("mensaje", getEventoVO().getEvento()));
				parametros.add(FieldVO.getInstance("fecha", getEventoVO().getFechaE()));
				parametros.add(FieldVO.getInstance("lugar", getEventoVO().getSede()));
				String horario = getEventoVO().getHorario();
				parametros.add(FieldVO.getInstance("horario", horario));
				parametros.add(FieldVO.getInstance("oferta", "A continuación le mostramos algunas ofertas compatibles con su perfil."));
				String correo = candidato.getCorreoElectronico();
				String asunto = "Notificación de registro a evento Ferias del empleo";
				String remitente = PropertiesLoader.getInstance().getProperty("email.remitente.ferias");
				if(!ofertas.isEmpty()){
					for(int i=0;i<ofertas.size();i++){
						parametros.add(FieldVO.getInstance("titOf"+(i+1), ofertas.get(i).getTituloOferta()));
						parametros.add(FieldVO.getInstance("emprOf"+(i+1),ofertas.get(i).getNombreEmpresa()));
						parametros.add(FieldVO.getInstance("salMe"+(i+1),ofertas.get(i).getSalarioStr()));
					}
					mailService.sendMail(PLANTILLA_CORREO.CANDIDATO_REGISTRO_EVENTO, remitente, asunto, correo, parametros);
				}else{
					String mensajeOfertas="No se encontraron ofertas compatibles en los eventos que está registrado. Se enviarán nuevamente, a este mismo correo, dos días antes del evento, las ofertas compatibles.";
					parametros.add(FieldVO.getInstance("noHay", mensajeOfertas));
					mailService.sendMail(PLANTILLA_CORREO.CANDIDATO_REGISTRO_EVENTO_NOHAY, remitente, asunto, correo, parametros);
				}
			}
			
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		}catch(MailException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		//return page(mapping,form,request,response);
		request.getSession().setAttribute(BODY_JSP, mapping.findForward("DETALLE_EVENTO").getPath());
		
		//return mapping.findForward(FORWARD_TEMPLATE_REGISTRO_CANDIDATO_EVENTO);
		PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Detalle del evento");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Detalle del evento, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
		return mapping.findForward(FORWARD_TEMPLATE_MI_ESP_CAND);
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
		try {
			OutputStream servletOutputStream = response.getOutputStream();
			//regService.imprimeComprobantePDF(servletOutputStream, ofertas, rutaHeader, rutaFooter, candidato.getIdCandidato(), getEventoVO().getIdEvento());
			ofertas = regService.consultaOfertasCompatiblesEvento((int) getEventoVO().getIdEvento(), candidato.getIdCandidato(), Integer.valueOf(candidato.getPpcEstatusIdOpcion()));
			SimpleDateFormat sd = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy', Hora: ' H:mm", new Locale("es","ES"));
			ComprobanteRegistroEventoCandidatoVO comprobante = new ComprobanteRegistroEventoCandidatoVO();
			eventoCandidato = regService.initEventosCandidato(candidato.getIdCandidato(), getEventoVO().getIdEvento());
			comprobante.setFolioRegCandi(eventoCandidato.getFolioRegistro());
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
		return null;
		
		
		
	}
	
	
	public EventoVO getEventoVO() {
		return eventoVO;
	}

	public void setEventoVO(EventoVO eventoVO) {
		this.eventoVO = eventoVO;
	}



}
