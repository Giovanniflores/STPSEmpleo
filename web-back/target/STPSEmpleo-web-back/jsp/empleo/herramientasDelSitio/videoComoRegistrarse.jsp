<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="ftp" value="${FTP_STATIC_CONTENT}" />
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURI}" />

<t:publicTemplate>
	<jsp:attribute name="titulo">Video: &iquest;C&oacute;mo registrarme al Portal del Empleo?</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Herramientas del Sitio</jsp:attribute>  
    <jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
 	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		Video: ¿Cómo registrarme al Portal del Empleo?
	</jsp:attribute>
	<jsp:body>
	
	<!-- div ruta_navegacion -->
    <div class="row">
      <div class="col-sm-12">
        <ol class="breadcrumb">
          <li>Ruta de navegación:</li>
<%--          <li> <a href="<c:url value="/inicio.do"/>">Inicio</a></li> --%>
          <li><a href="${context}">Inicio</a></li>
<%-- <li><a href="<c:url value="/jsp/empleo/herramientasDelSitio/mapaDeSitio.jsp"/>">Herramientas del sitio</a></li> --%>
          <li><a href="#">Herramientas del sitio</a></li>
          <li class="active">Video: ¿Cómo registrarme al Portal del Empleo?</li>
        </ol>
      </div>
    </div>
    <!-- /div ruta_navegacion -->
    

    <div class="row">
 
	  <!-- div contenido -->
      <div class="col-sm-12">
      
			<video style="width:100%" controls>
			  <source src="${ftp}/video/REGISTROYBUSQUEDA.mp4" type="video/mp4">
			  <source src="${ftp}/video/REGISTROYBUSQUEDA.mp4" type="video/ogg">
			Tu navegador no soporta el video
			</video>
        
      </div><!-- /div contenido -->
    </div>
    
	</jsp:body>
</t:publicTemplate>
