<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.Calendar" %>
<%@ page import="mx.gob.stps.portal.web.infra.utils.PropertiesLoader"%>

<% 
   String context = (String)application.getAttribute("DOMAIN_PORTAL");  
   String contextApp = request.getContextPath() +"/";
   if (null != request.getSession().getAttribute("FROM_CIL"))
	   context = (String)application.getAttribute("DOMAIN_PORTAL_CIL");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd"> 
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es" lang="es"> 
<head> 
<link rel="P3Pv1" href="https://www.empleo.gob.mx/w3c/p3p.xml" /> 
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" /> 

<!--  
<c:if test="${not empty TITULO}">
	<title>${TITULO}</title>
</c:if>
<c:if test="${empty TITULO}">
	<title><tiles:getAsString name="title"/></title>
</c:if>
-->
<title><tiles:getAsString name="title" /> | Portal del Empleo</title>
	<meta property="og:title" content="<tiles:getAsString name="title" /> | Portal del Empleo">	
	<meta name="twitter:title" content="<tiles:getAsString name="title" /> | Portal del Empleo">
	<meta property="og:description" content="<tiles:getAsString name="description" />"/>
	<meta name="twitter:description" content="<tiles:getAsString name="description" />"/>
	<meta name="description" content="<tiles:getAsString name="title" />, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo"/>
	<meta property="og:url" content="<tiles:getAsString name="url" />">
	<meta name="twitter:image:src" content="<tiles:getAsString name="twitter" />">
	<meta property="og:image" content="<tiles:getAsString name="facebook" />">
<meta property="og:site_name" content="Portal del Empleo">	
<meta property="og:image:type" content="image/jpeg">
<meta property="og:image:width" content="1200">
<meta property="og:image:height" content="627">
<meta property="fb:app_id" content="308925772806125" />
<meta name="twitter:card" content="summary_large_image">
<meta name="twitter:site" content="@empleogob_mx">
<meta name="twitter:creator" content="@InfotecMexico">
<meta name="author" content="infotec">

<style type="text/css">
	@import "dojotoolkit/dijit/themes/claro/claro.css";
	@import "dojotoolkit/dojo/resources/dojo.css";
</style> 

<style>
.claro .dijitDialogTitleBar {
	background-color: #F47513;
	color: #FFFFFF;
    font-weight: bold;
    text-decoration: none;
    }
</style>

<script src="dojotoolkit/dojo/dojo.js" djConfig="parseOnLoad: true, locale:'es'"></script>




<link href='https://fonts.googleapis.com/css?family=Droid+Sans' rel='stylesheet' type='text/css' />
<link href="<%=contextApp%>/css/cil/estilos_lightBox.css" rel="stylesheet" type="text/css" />
<link href="<%=contextApp%>/css/cil/estilos_tabla.css" rel="stylesheet" type="text/css" />
<link href="<%=contextApp%>/css/cil/estilos_genericos.css" rel="stylesheet" type="text/css" />

<!-- Nuevo código analytics multidominio 01/06/2017 -->
<script> 
	(function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
	(i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
	m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
	})(window,document,'script','//www.google-analytics.com/analytics.js','ga');
    ga('create', 'UA-26166631-1', 'auto', {'allowLinker': true});
	ga('require', 'displayfeatures', 'linker');
	ga('linker:autoLink', ['publicaciones.empleo.gob.mx'] );
	ga('send', 'pageview');
</script>

</head>
<body class='claro'>

<div id="wrapper" class="container">
<jsp:include page="header.jsp" />

<div id="cuerpo_int">
        
        <div id="contenido_principal" class="sinCol">

        
        <div class="row">
        <div id="ruta_navegacion">
        
	      <div class="col-sm-12">
	      	<c:if test="${not empty FROM_CIL && FROM_CIL}">
	        <ol class="breadcrumb">
	          <li>Ruta de navegación:</li>
	          <li>Inicio</li>
	          <li>Herramientas del sitio</li>
	          <li>Centros de Intermediación Laboral</li>
	        </ol>
	        </c:if>
	        
	        <c:if test="${empty FROM_CIL || !FROM_CIL}">
	        <ol class="breadcrumb">
	          <li>Ruta de navegación:</li>
	          <li><a href="https://www.empleo.gob.mx/"  >Inicio</a></li>
	          <li class="active"><c:if test="${not empty TITULO}">${TITULO}</c:if></li>
	        </ol>
	        </c:if>
	      </div>
	    </div>
	    </div>  
	        
        <div class="frames"> 
	<br/>
<br/><c:if test="${not empty TITULO}"><title>${TITULO}</title></c:if>

	<c:if test="${not empty USUARIO_APP && (USUARIO_APP.administrador || USUARIO_APP.publicador)}">
    		<div id="navegacion">
        		<tiles:insert name="menu"/>
        	</div>
    	</c:if>
        
        <div id="contenido_principal">
			<tiles:insert name="body"/>          
    	</div>
</div></div>

        </div>

       <jsp:include page="footer.jsp" />
</div>
</div>
</body></html>

<%
	Calendar start = (Calendar)request.getAttribute("TIME-START");
	if (start!=null){
		Calendar end = Calendar.getInstance();
		long dif = end.getTimeInMillis() - start.getTimeInMillis();
		out.write("<!-- "+ dif +" ms -->");
	}	
%>