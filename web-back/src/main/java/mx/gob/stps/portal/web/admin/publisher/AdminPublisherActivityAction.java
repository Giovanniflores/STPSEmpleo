package mx.gob.stps.portal.web.admin.publisher;

import static mx.gob.stps.portal.web.infra.utils.Constantes.BODY_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_TEMPLATE_ESP_ADMIN;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TITULO_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.DESCRIPCION_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FACEBOOK_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TWITTER_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.URL_ESPECIFICA;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import mx.gob.stps.portal.core.seguridad.vo.PublicadorVO;
import mx.gob.stps.portal.core.seguridad.vo.UsuarioVO;
import mx.gob.stps.portal.web.autorization.delegate.AutorizationBusDelegate;
import mx.gob.stps.portal.web.autorization.delegate.AutorizationBusDelegateImpl;
import mx.gob.stps.portal.web.infra.action.PagerAction;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.infra.utils.PropertiesLoader;
import mx.gob.stps.portal.web.security.delegate.SecutityDelegate;
import mx.gob.stps.portal.web.security.delegate.SecutityDelegateImpl;
import mx.gob.stps.portal.web.security.vo.UsuarioWebVO;

public class AdminPublisherActivityAction extends PagerAction {

	@Override
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.removeAttribute("NUM_PAGE_LIST");
		session.removeAttribute("TOTAL_PAGES");
		session.removeAttribute("PAGE_JUMP_SIZE");
		session.removeAttribute("NUM_PAGE_JUMP");
		session.removeAttribute("NUM_RECORDS_VISIBLE");
		session.removeAttribute("NUM_RECORDS_TOTAL");		
		session.removeAttribute(FULL_LIST); 
		
		AdminPublisherActivityForm activity = (AdminPublisherActivityForm)form;
		UsuarioWebVO userWeb = getUsuario(request.getSession());
		SecutityDelegate services = SecutityDelegateImpl.getInstance();
		
		if (null != userWeb) {
			UsuarioVO user;
			try {
				user = services.consultaUsuarioPorLogin(userWeb.getUsuario());
				activity.setReporter(user.getNombre() + " " + user.getApellido1() + " " + (null!=user.getApellido2() ?  user.getApellido2() : ""));
				request.getSession().setAttribute("activity", activity);
			} catch (ServiceLocatorException e) { e.printStackTrace(); }
		}
		
		request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());

		PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Administrador de publicaciones");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Administrador de publicaciones, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
		return mapping.findForward(FORWARD_TEMPLATE_ESP_ADMIN);
	}
	
	public ActionForward filter(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		long totalReview = 0, totalAuthorized = 0, totalRejected = 0, totalCompanies = 0, totalOffers = 0, totalTestimonies= 0, totalVideocv = 0;
		AdminPublisherActivityForm activity = (AdminPublisherActivityForm)form;
		AutorizationBusDelegate service = AutorizationBusDelegateImpl.getInstance();
		try {
			String name = (null != request.getParameter("name") ? request.getParameter("name") : "");
			String lastname = (null != request.getParameter("lastname") ? request.getParameter("lastname") : "");
			String rangeInit = parseDate(request.getParameter("dateInitAdd"));
			String rangeFinal = parseDate(request.getParameter("dateFinalAdd"));
			List<PublicadorVO> publisherList = service.productivityPublishersList(name, lastname, rangeInit, rangeFinal);
			request.getSession().setAttribute(FULL_LIST, publisherList); //paginacion
			page(mapping, form, request, response); // Primera paginacion
			Iterator<PublicadorVO> it = publisherList.iterator();
			while (it.hasNext()) {
				PublicadorVO vo = it.next();
				totalReview += vo.getTotal();
				totalAuthorized += vo.getAutorizados();
				totalRejected += vo.getRechazados();
				totalCompanies += vo.getEmpresas();
				totalOffers += vo.getOfertas();
				totalTestimonies += vo.getTestimonios();
				totalVideocv += vo.getVideocv();
			}
			activity.setTotalReview(totalReview);
			activity.setTotalAuthorized(totalAuthorized);
			activity.setTotalRejected(totalRejected);
			activity.setTotalCompanies(totalCompanies);
			activity.setTotalOffers(totalOffers);
			activity.setTotalTestimonies(totalTestimonies);
			activity.setTotalVideocv(totalVideocv);
			request.getSession().setAttribute("activity", activity);
			this.PAGE_NUM_ROW = 100;
		} catch (Exception e) { e.printStackTrace(); }
		request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
		return mapping.findForward(FORWARD_TEMPLATE_ESP_ADMIN);
	}
	
	public ActionForward excel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		String nombreArchivo = new String("\"ReportePublicadores.xls\"");
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Cache-Control", "no-cache");
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
		AdminPublisherActivityForm activity = (AdminPublisherActivityForm)form;
		StringBuilder sb = new StringBuilder("<table id=\"dataTable\" class=\"offer\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"730px\">");
		sb.append("	<tbody>");
		sb.append("		<tr class=\"temas\">");
		sb.append("			<th width=\"30%\">Publicador</th>");
		sb.append("			<th width=\"10%\">Registros revisados</th>");
		sb.append("			<th width=\"10%\">Registros autorizados</th>");
		sb.append("			<th width=\"10%\">Registros rechazados</th>");
		sb.append("			<th width=\"10%\">Empresas</th>");
		sb.append("			<th width=\"10%\">Ofertas</th>");
		sb.append("			<th width=\"10%\">Testimonios</th>");
		sb.append("			<th width=\"10%\">Video CV</th>");			
		sb.append("		</tr>");
		int rowCounter = 0;
		@SuppressWarnings("unchecked")
		List<PublicadorVO> publisherList = (List<PublicadorVO>)request.getSession().getAttribute("PAGE_LIST");
		for (PublicadorVO publisher : publisherList) {
			sb.append("		<tr" + (rowCounter % 2 == 0 ? "" : " class=odd ") + ">");
			sb.append("			<td>" + publisher.getNombre() + " " + publisher.getApellido1() + " " + (null != publisher.getApellido2() ? publisher.getApellido2() : "") + "</td>");
			sb.append("			<td>" + publisher.getTotal() + "</td>");
			sb.append("			<td>" + publisher.getAutorizados() + "</td>");
			sb.append("			<td>" + publisher.getRechazados() + "</td>");
			sb.append("			<td>" + publisher.getEmpresas() + "</td>");
			sb.append("			<td>" + publisher.getOfertas() + "</td>");
			sb.append("			<td>" + publisher.getTestimonios() + "</td>");
			sb.append("			<td>" + publisher.getVideocv() + "</td>");
			sb.append("		</tr>");
			rowCounter++;
		}
		sb.append("		<tr" + (rowCounter % 2 == 0 ? "" : " class=odd ") + ">");
		sb.append("			<td>Totales</td>");
		sb.append("			<td>" + activity.getTotalReview() + "</td>");
		sb.append("			<td>" + activity.getTotalAuthorized() + "</td>");
		sb.append("			<td>" + activity.getTotalRejected() + "</td>");
		sb.append("			<td>" + activity.getTotalCompanies() + "</td>");
		sb.append("			<td>" + activity.getTotalOffers() + "</td>");
		sb.append("			<td>" + activity.getTotalTestimonies() + "</td>");
		sb.append("			<td>" + activity.getTotalVideocv() + "</td>");
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
}