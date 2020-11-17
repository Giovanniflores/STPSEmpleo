<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles" %>
<%@ page import="mx.gob.stps.portal.web.infra.utils.Constantes"%>

<%
String bodyjsp = (String)session.getAttribute(Constantes.BODY_JSP);
String tituloPagina = (String)session.getAttribute("tituloPagina");
String descripcionPagina = (String)session.getAttribute("descripcionPagina");
String facebookImage = (String)session.getAttribute("facebookImage");
String twitterImage = (String)session.getAttribute("twitterImage");
String urlDefinida =(String)session.getAttribute("urlEspecifica");

String body = bodyjsp!=null ? bodyjsp : "/jsp/layout/body.jsp";
String titulo = tituloPagina!=null ? tituloPagina : "Portal de Empleo de M&eacute;xico";
String descripcion = descripcionPagina!=null ? descripcionPagina : "Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo.";
String facebook = facebookImage!=null ? facebookImage : "https://www.empleo.gob.mx/css/images/compartir-contenido.jpg";
String twitter = twitterImage!=null ? twitterImage : "https://www.empleo.gob.mx/css/images/contenido-compartir-tweetA.jpg";
String url = urlDefinida!=null ? urlDefinida : "https://www.empleo.gob.mx";
%>

<tiles:insert page="/jsp/layout/templateNoFrames/templateEspacioCandidato.jsp" flush="true">
	<tiles:put name="title" value="<%=titulo%>"/>
	<tiles:put name="description" value="<%=descripcion%>"/>
	<tiles:put name="facebook" value="<%=facebook%>"/>
	<tiles:put name="twitter" value="<%=twitter%>"/>
	<tiles:put name="url" value="<%=url%>"/>
	<tiles:put name="menu" value="/jsp/layout/menu.jsp"/>
	<tiles:put name="articulos" value="/jsp/layout/articulos.jsp"/>
	<tiles:put name="body" value="<%=body%>"/>
</tiles:insert>