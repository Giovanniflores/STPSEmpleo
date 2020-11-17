<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURI}" />

<t:publicTemplate>
	<jsp:attribute name="titulo">B&uacute;squeda por lugar</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Herramientas del sitio</jsp:attribute> 
    <jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
 	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		Si deseas buscar ofertas laborales en un lugar espec&iacute;fico de la Rep&uacute;blica Mexicana, deber&aacute;s escribir el nombre del municipio (o delegaci&oacute;n) y la entidad federativa, separados por una coma (,).
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
          	  <li class="active">Búsqueda por lugar</li>
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
	            <h2 class="titulosh2">Búsqueda por lugar</h2>
	          </div>
	          <div class="panel-body">
	          
	            <p>Si deseas buscar ofertas laborales en alguna entidad federativa espec&iacute;fica de la Rep&uacute;blica Mexicana, 
	            deber&aacute;s elegirla en el campo &quot;&iquest;D&oacute;nde?&quot;. Por ejemplo:</p>
<ul class="list-group-contenidos">
    <li>&iquest;Qu&eacute; empleo buscas?<br>
	Ingeniero Civil Estudiante</li>
	<li>&iquest;D&oacute;nde?<br>
	Veracruz</li>
</ul>

	          </div>
	        </div>
	        
	      </div>
	      <!-- /div contenido -->
	    </div>
	    
	    
	    
	    
	</jsp:body>
</t:publicTemplate>
