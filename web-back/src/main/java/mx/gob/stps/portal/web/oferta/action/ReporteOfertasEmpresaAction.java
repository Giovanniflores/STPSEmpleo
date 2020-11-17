package mx.gob.stps.portal.web.oferta.action;

import static mx.gob.stps.portal.web.infra.utils.Constantes.BODY_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_TEMPLATE_MI_ESP_EMP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.SIN_ARTICULOS;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TAB_MENU;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TAB_MIS_OFERTAS;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TITULO_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.DESCRIPCION_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FACEBOOK_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TWITTER_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.URL_ESPECIFICA;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.core.infra.utils.Constantes.ESTATUS;
import mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO;
import mx.gob.stps.portal.core.oferta.reporte.vo.ReporteOfertasEmpresaVO;
import mx.gob.stps.portal.web.infra.action.PagerAction;
import mx.gob.stps.portal.web.infra.delegate.CatalogoOpcionDelegateImpl;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.infra.utils.PropertiesLoader;
import mx.gob.stps.portal.web.infra.utils.Utils;
import mx.gob.stps.portal.web.infra.vo.CatalogoVO;
import mx.gob.stps.portal.web.oferta.delegate.OfertaBusDelegate;
import mx.gob.stps.portal.web.oferta.delegate.OfertaBusDelegateImpl;
import mx.gob.stps.portal.web.oferta.form.ReporteOfertasEmpresaForm;
import mx.gob.stps.portal.web.security.vo.UsuarioWebVO;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ReporteOfertasEmpresaAction extends PagerAction {
	
	private static Logger logger = Logger.getLogger(ReporteOfertasEmpresaAction.class);

	public ActionForward init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.removeAttribute("NUM_PAGE_LIST");
		session.removeAttribute("TOTAL_PAGES");
		session.removeAttribute("PAGE_JUMP_SIZE");
		session.removeAttribute("NUM_PAGE_JUMP");
		session.removeAttribute("NUM_RECORDS_VISIBLE");
		session.removeAttribute("NUM_RECORDS_TOTAL");		
		session.removeAttribute(FULL_LIST); 
		
		ReporteOfertasEmpresaForm reporteForm = (ReporteOfertasEmpresaForm) form;
		reporteForm.reset();
		UsuarioWebVO usuario = getUsuario(session);
		if (usuario!=null){
			reporteForm.setIdEmpresa(usuario.getIdPropietario());
			reporteForm.setIdUsuario(usuario.getIdUsuario());
			reporteForm.setIdTipoUsuario(usuario.getIdTipoUsuario());
		}	
		request.setAttribute(SIN_ARTICULOS, "true");
		ocultaBarraArticulos(request);
		session.setAttribute(TAB_MENU, TAB_MIS_OFERTAS);		
		request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
        PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Reporte de ofertas de empleo");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Espacio Empresa, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo ");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
		return mapping.findForward(FORWARD_TEMPLATE_MI_ESP_EMP);
	}
	
	public ActionForward filter(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		int totalOfertas = 0;
		int selectedStatus = 0;
		ReporteOfertasEmpresaForm reporteForm = (ReporteOfertasEmpresaForm) form;
		String rangeStatus = request.getParameter("estatusAdd");
		String rangeInit = parseDate(request.getParameter("dateInitAdd"));
		String rangeFinal = parseDate(request.getParameter("dateFinalAdd"));
		
		if(null!=rangeStatus && !rangeStatus.equalsIgnoreCase(""))
			selectedStatus = Integer.parseInt(rangeStatus);
		
		OfertaBusDelegate service = OfertaBusDelegateImpl.getInstance();
		try {
			List<ReporteOfertasEmpresaVO> ofertasEncontradas = 
					service.getOfertasEmpresaReporte(reporteForm.getIdEmpresa(), selectedStatus, rangeInit, rangeFinal);
			request.getSession().setAttribute(FULL_LIST, ofertasEncontradas); //paginacion
			page(mapping, form, request, response); // Primera paginacion
			if(null!=ofertasEncontradas && !ofertasEncontradas.isEmpty())
				totalOfertas = 	ofertasEncontradas.size();
			
			reporteForm.setOfertas(ofertasEncontradas);
			reporteForm.setTotalOfertas(totalOfertas);
			reporteForm.setFechaActual(new Date());			
			reporteForm.setFilterIdEstatus(selectedStatus);
			if(null!=rangeStatus && !rangeStatus.equalsIgnoreCase("")){
				reporteForm.setFilterDesEstatus(ESTATUS.getDescripcion(selectedStatus));
			} else {
				reporteForm.setFilterDesEstatus("NINGUNO");
			}				
			if(null!=rangeInit && !rangeInit.equalsIgnoreCase("") && null!=rangeFinal && !rangeFinal.equalsIgnoreCase("")){
				reporteForm.setFilterFechaIni(rangeInit);
				reporteForm.setFilterFechaFin(rangeFinal);							
			} else {
				reporteForm.setFilterFechaIni("");
				reporteForm.setFilterFechaFin("");											
			}
			//this.PAGE_NUM_ROW = 100;
			this.PAGE_NUM_ROW = 10;
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		}
		request.setAttribute(SIN_ARTICULOS, "true");
		ocultaBarraArticulos(request);
		session.setAttribute(TAB_MENU, TAB_MIS_OFERTAS);				
		request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
        PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Reporte de ofertas");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Reporte de ofertas, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo.");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
		return mapping.findForward(FORWARD_TEMPLATE_MI_ESP_EMP);		
	}
	
	
	public ActionForward excel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		String nombreArchivo = new String("\"Ofertas por empresa.xls\"");
		response.setContentType("application/vnd.ms-excel");
		//response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Content-Disposition","attachment; filename="+nombreArchivo);		
		PrintWriter pw;
		try {
			pw = response.getWriter();
			pw.write(getViewExcel(form, request));
			pw.flush();
			pw.close();
			response.flushBuffer();
		} catch (IOException e) { e.printStackTrace(); }
		return null;
	}
	
	private String getViewExcel(ActionForm form, HttpServletRequest request) {
		ReporteOfertasEmpresaForm reporteForm = (ReporteOfertasEmpresaForm) form;
		StringBuilder sb = new StringBuilder("<table id=\"dataTable\" class=\"offer\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"730px\">");
		sb.append("	<tbody>");
		//TODO: DATOS ADICIONALES DEL ENCABEZADO
		sb.append("		<tr class=\"temas\">");
		sb.append("			<th width=\"5%\">Folio</th>");
		sb.append("			<th width=\"10%\">T&iacute;tulo</th>");
		sb.append("			<th width=\"10%\">&Aacute;rea de negocio</th>");
		sb.append("			<th width=\"5%\">Nivel de estudios</th>");
		sb.append("			<th width=\"10%\">Carrera</th>");
		sb.append("			<th width=\"5%\">Ubicaci&oacute;n</th>");
		sb.append("			<th width=\"5%\">No. de Plazas</th>");
		sb.append("			<th width=\"5%\">G&eacute;nero</th>");	
		sb.append("			<th width=\"5%\">Experiencia</th>");
		sb.append("			<th width=\"5%\">Estatus</th>");
		sb.append("			<th width=\"5%\">Persona de contacto</th>");
		sb.append("			<th width=\"5%\">Tel&eacute;fono de contacto</th>");
		sb.append("			<th width=\"10%\">Correo electr&oacute;nico de contacto</th>");
		sb.append("			<th width=\"10%\">Empresa que ofrece la vacante</th>");
		sb.append("			<th width=\"5%\">Salario</th>");
		sb.append("		</tr>");
		int rowCounter = 0;
		@SuppressWarnings("unchecked")
		//Este se usa para imprimir solo las que estan en pantalla 
		//List<ReporteOfertasEmpresaVO> listaOfertas = (List<ReporteOfertasEmpresaVO>)request.getSession().getAttribute("PAGE_LIST");
		//Esta se usa para imprimir todas las ofertas encontradas
		List<ReporteOfertasEmpresaVO> listaOfertas = reporteForm.getOfertas();
		for(ReporteOfertasEmpresaVO ofertaVo: listaOfertas){
			sb.append("		<tr" + (rowCounter % 2 == 0 ? "" : " class=odd ") + ">");
			sb.append("			<td>" + ofertaVo.getIdOfertaEmpleo() + "</td>");
			sb.append("			<td>" + Utils.limpiarAcentos(ofertaVo.getTituloOferta()) + "</td>");
			if(null!=ofertaVo.getOcupacion()){
				sb.append("			<td>" + Utils.limpiarAcentos(null != ofertaVo.getSubAreaLaboralDescripcion() ? ofertaVo.getSubAreaLaboralDescripcion() : "") + "</td>");
			} else {
				sb.append("			<td></td>");
			}			
			if(null!=ofertaVo.getGradoEstudios()){
				sb.append("			<td>" + Utils.limpiarAcentos(ofertaVo.getGradoEstudios()) + "</td>");
			} else {
				sb.append("			<td></td>");
			}	
			if(null!=ofertaVo.getCarreraEspecialidad()){
				sb.append("			<td>" + Utils.limpiarAcentos(ofertaVo.getCarreraEspecialidad()) + "</td>");
			} else {
				sb.append("			<td></td>");
			}	
			sb.append("			<td>");
				if(null!=ofertaVo.getMunicipio()){
					sb.append(Utils.limpiarAcentos(ofertaVo.getMunicipio()) + ", ");				
				}		
				if(null!=ofertaVo.getEntidad()){
					sb.append(Utils.limpiarAcentos(ofertaVo.getEntidad()));
				}				
			sb.append("			</td>");
			sb.append("			<td>" + ofertaVo.getNumeroPlazas() + "</td>");
			if(null!=ofertaVo.getGenero()){
				sb.append("			<td>" + Utils.limpiarAcentos(ofertaVo.getGenero()) + "</td>");
			} else {
				sb.append("			<td></td>");
			}		
			if(null!=ofertaVo.getDescripcionExperienciaAnios()){
				sb.append("			<td>" + Utils.limpiarAcentos(ofertaVo.getDescripcionExperienciaAnios()) + "</td>");
			} else {
				sb.append("			<td></td>");
			}
			sb.append("			<td>" + Utils.limpiarAcentos(ofertaVo.getEstatus()) + "</td>");
			if(null!=ofertaVo.getNombreContacto()){
				sb.append("			<td>" + Utils.limpiarAcentos(ofertaVo.getNombreContacto()) + "</td>");
			} else {
				sb.append("			<td></td>");
			}			
			if(null!=ofertaVo.getTelefonoPrincipal()){
				sb.append("			<td>" + ofertaVo.getTelefonoPrincipal() + "</td>");	
			} else {
				sb.append("			<td></td>");
			}
			if(null!=ofertaVo.getCorreoElectronicoContacto()){
				sb.append("			<td>" + ofertaVo.getCorreoElectronicoContacto() + "</td>");
			} else {
				sb.append("			<td></td>");
			}			
			if(null!=ofertaVo.getEmpresaNombre()){
				sb.append("			<td>" + Utils.limpiarAcentos(ofertaVo.getEmpresaNombre()) + "</td>");
			} else {
				sb.append("			<td></td>");
			}				
			sb.append("			<td>" + ofertaVo.getSalario() + "</td>");			
			sb.append("		</tr>");
			rowCounter++;			
		}
		sb.append("		<tr" + (rowCounter % 2 == 0 ? "" : " class=odd ") + ">");
		sb.append("		</tr>");
		
		sb.append("	</tbody>");	  
		sb.append("</table>");
		return sb.toString();		
	}
	
	private String parseDate(String date) {
		if (null != date && !date.isEmpty())
			return date.substring(8,10) + "/" + date.substring(5,7) + "/" + date.substring(0,4);
		return null;
	}	
	
	public ActionForward estatusValidos(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			//UNA OFERTA SI PUEDE ESTAR EN 1,2,10,12,13,14,15,28,50
			long[] filtro = new long[] {3,4,5,6,7,8,9,11,16,17,18,19,20,21,22,23,24,25,26,27,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,84,85};
			List<CatalogoOpcionVO> opciones = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(Constantes.CATALOGO_OPCION_ESTATUS, filtro);
			redirectJsonCatalogo(opciones, response);
		} catch (Exception e) {logger.error(e);}		
		return null;
	}	
	
	private void redirectJsonCatalogo(List<CatalogoOpcionVO> opciones, HttpServletResponse response) throws IOException {
		CatalogoVO cat = getCatalogo(opciones);
		String json = toJson(cat);
		redirectJsonResponse(response, json);
	}		

}
