<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURI}" />

<t:publicTemplate>
	<jsp:attribute name="titulo">Estructura de la b&uacute;squeda</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Herramientas del Sitio</jsp:attribute>  
    <jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
 	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		Al realizar la b&uacute;squeda de ofertas laborales no es necesario seguir un orden en las palabras o frases a utilizar.
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
          	  <li class="active">Estructura de la búsqueda</li>
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
	            <h2 class="titulosh2">Estructura de la búsqueda</h2>
	          </div>
	          <div class="panel-body">
	          
	            <p>Al realizar la b&uacute;squeda de ofertas laborales no es necesario seguir un orden en las palabras o frases a utilizar.</p>
<p>Una vez que hayas establecido las palabras que conforman tu b&uacute;squeda, puedes colocarlas en el orden de tu preferencia sin afectar el resultado final. Por ejemplo:</p>
<ul class="list-group-contenidos">
    <li>Ingeniero civil con maestr&iacute;a</li>
    <li>Maestr&iacute;a Ingeniero civil</li>
</ul>
<ul class="list-group-contenidos">
    <li>Ingl&eacute;s Administrador $25,000.00</li>
    <li>Administradora $25,000.00 Ingl&eacute;s</li>
</ul>
<ul class="list-group-contenidos">
    <li>Ing. en computaci&oacute;n ingl&eacute;s 80%</li>
    <li>Ingenier&iacute;a en computaci&oacute;n ingl&eacute;s al 80%</li>
</ul>
	          </div>
	        </div>
	        
	      </div>
	      <!-- /div contenido -->
	    </div>
	    
	    
	    
	    
	</jsp:body>
</t:publicTemplate>
