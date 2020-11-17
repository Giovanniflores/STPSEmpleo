<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURI}" />

<t:publicTemplate>
	<jsp:attribute name="titulo">T&eacute;rminos por ra&iacute;z de palabra</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Herramientas del Sitio</jsp:attribute>  
    <jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
 	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		El buscador identificar&aacute; la ra&iacute;z de la palabra que se est&aacute; introduciendo como par&aacute;metro de b&uacute;squeda y buscar&aacute; los t&eacute;rminos que tengan la misma ra&iacute;z de dicha palabra,  ya que por definici&oacute;n, t&eacute;rminos con una ra&iacute;z com&uacute;n tienen significados similares.
	</jsp:attribute>
	<jsp:body>
		<!-- div ruta_navegacion -->
	    <div class="row">
	      <div class="col-sm-12">
	        <ol class="breadcrumb">
	          <li>Ruta de navegación:</li>
	           <li><a href="<c:url value="/inicio.do"/>">Inicio</a></li>
	          <li><a href="<c:url value="/jsp/empleo/herramientasDelSitio/mapaDeSitio.jsp"/>">Herramientas del sitio</a></li>
	          <li><a href="<c:url value="/jsp/empleo/herramientasDelSitio/necesitasAyuda.jsp"/>">¿Necesitas ayuda?</a></li>
	          <li><a href="<c:url value="/jsp/empleo/herramientasDelSitio/usoBuscador.jsp"/>">Uso del buscador</a></li>
          	  <li class="active">Términos por raíz de palabra</li>
	        </ol>
	      </div>
	    </div>
	    <!-- /div ruta_navegacion -->
	    
	    <div class="row">
		  <!-- div menu -->
		  <jsp:include page="menu.jsp"/>
		  
		  <!-- div contenido -->
	      <div class="col-sm-8 col-sm-pull-4">
	      
	       <jsp:include page="/WEB-INF/template/redes.jsp"/>
	
	        <div class="panel panel-contacto">
	          <div class="panel-heading">
	            <h2 class="titulosh2">Términos por raíz de palabra</h2>
	          </div>
	          <div class="panel-body">
	          
	            <p>El buscador identificar&aacute; la ra&iacute;z de la palabra que se est&aacute; introduciendo como par&aacute;metro de b&uacute;squeda y buscar&aacute; los t&eacute;rminos que tengan la misma ra&iacute;z de dicha palabra,  ya que por definici&oacute;n, t&eacute;rminos con una ra&iacute;z com&uacute;n tienen significados similares. Por ejemplo:</p>
<ul class="list-group-contenidos">
    <li>Ejemplo:   Profesor, Profesora, Profesorado, Profesores...</li>
</ul>
	          </div>
	        </div>
	        
	      </div>
	      <!-- /div contenido -->
	    </div>
	    
	    
	    
	    
	</jsp:body>
</t:publicTemplate>
