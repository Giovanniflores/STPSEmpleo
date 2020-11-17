<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="mx.gob.stps.portal.core.infra.utils.Constantes"%>
<%@ page import="mx.gob.stps.portal.core.oferta.busqueda.vo.OfertaPorCanalVO"%>
<%@ page import="mx.gob.stps.portal.web.company.form.ShowCompanyForm"%>
<%@ page import="java.util.Calendar"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    

<%
String context = request.getContextPath() +"/";
int MAX_OFFER_SHOW = 5;
int MAX_OFFERING_SHOW = 4;
ShowCompanyForm coviewForm  = (ShowCompanyForm) request.getSession().getAttribute("ShowCompanyForm");
long idEmpresa  = coviewForm.getIdEmpresa();
String strNombreEmpresa  = coviewForm.getNombreEmpresa();
String strDescripcion = coviewForm.getDescripcion();
String strWebSite  = coviewForm.getPaginaWeb();
double dblMinWage = coviewForm.getSalarioMinimo();
double dblMaxWage = coviewForm.getSalarioMaximo();
List<OfertaPorCanalVO> lstOfertas = coviewForm.getLstOfertas();
List<String> lstEmpresaOfrece = coviewForm.getLstEmpresaOfrece();

%>

<script src="dojotoolkit/dojo/dojo.js" djConfig="parseOnLoad: true, locale:'es'"></script>
<noscript>Funciones de JavaScript desactivadas por navegador</noscript>
<script type="text/javascript">
	function visitWebSite() {
		var answer = confirm("La liga se encuentra en un sitio externo ¿Continuar?");
		if (answer){
			var winCompanyWebSite = window.open("<%=strWebSite%>", "Website","height=500,width=900,resizable=yes,scrollbars=yes");
		}
	}
</script>

<div >
	<h2>${coviewForm.nombreEmpresa}</h2>
	<div>
		<div class="logo_empresa">
			<img alt="logo ${coviewForm.nombreEmpresa}" src="${context}imageAction.do?method=getImagen&<%="captcha?time="+ Calendar.getInstance().getTimeInMillis()%>"" width="90" height="90" >
			<br/>
			<%if(null!=strWebSite && !strWebSite.equalsIgnoreCase("")){
				out.print("<input type=\"button\" name=\"btnWebsite\" id=\"btnWebsite\" class=\"boton\" value=\"Visitar página web\" onclick=\"visitWebSite();\">");
			}%>		
		</div>
		<div>
			<h3>Acerca de <%=strNombreEmpresa%></h3> 
			<p><%=strDescripcion%></p>
		</div>		
	</div>
	<div >
		<div>
			<h3>Ofertas de empleo</h3>
			<%
			if(lstOfertas.size()==0){
				out.print("<p>Actualmente, la empresa no tiene ofertas de empleo activas.</p>");
			} else {
			    out.print("<ul>");
			    int count = 0;
				Iterator itOfertas = lstOfertas.iterator();				
				while(count<MAX_OFFER_SHOW && itOfertas.hasNext()){
					OfertaPorCanalVO oferta = (OfertaPorCanalVO) itOfertas.next();
					
					out.print("<li><a href=\"" + context + 
					"detalleoferta.do?method=init&id_oferta_empleo=" + oferta.getIdOfertaEmpleo() + "\" >" + 
					oferta.getTituloOferta() + "</a>");
					out.print("&nbsp;&nbsp;Ubicación:");
					out.print(oferta.getUbicacion());					
					out.print("&nbsp;&nbsp;Vigencia:");
					out.print(oferta.getFechaInicioString() + " - " +  oferta.getFechaFinString());
					out.print("</li>");					
					count++;
				}
				out.print("</ul>");				
			}
			%>
		</div>
		<div>
			<h3>Te ofrecemos</h3>	
			Salarios en un rango de $<%=dblMinWage%> a $<%=dblMaxWage%> m.n. *<br/>
			<%
			if(lstEmpresaOfrece.size()>0){
			    int count = 0;
				Iterator itEmpresaOfrece = lstEmpresaOfrece.iterator();
				
				
				out.println("<ul>");				
				while(count<MAX_OFFERING_SHOW && itEmpresaOfrece.hasNext()){
					String strOfrece = (String) itEmpresaOfrece.next();
					out.println("<li>" + strOfrece + "</li>");
					count++;
				}				
				out.println("</ul>");
			} 
			%>		
			<p>* Nota: Este es un sumario de las condiciones ofrecidas por la empresa para diferentes ofertas de empleo. Las condiciones son diferentes para cada oferta de empleo.</p>
		</div>
	</div>
	<%
	if(lstOfertas.size()>5){
		out.print("<a href=\"" + context + 
		"showMoreCompanyOffers.do?method=init&idEmpresa=" + idEmpresa  + "\">Más ofertas de empleo.</a>");
	}
	%>
</div>



