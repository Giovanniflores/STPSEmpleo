<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURI}" />

<t:publicTemplate>
	<jsp:attribute name="titulo">T&eacute;rminos simult&aacute;neos</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Candidatos</jsp:attribute>  
    <jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
 	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		Si dentro de la b&uacute;squeda incluyes dos o m&aacute;s t&eacute;rminos que correspondan a una misma categor&iacute;a como son las carreras profesionales o las ocupaciones, todos los t&eacute;rminos ser&aacute;n tomados en cuenta por el buscador y obtendr&aacute;s resultados m&aacute;s amplios correspondientes al total de los t&eacute;rminos buscados.
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
          	  <li class="active">Términos simultáneos</li>
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
	            <h2 class="titulosh2">Términos simultáneos</h2>
	          </div>
	          <div class="panel-body">
	          
	            <p>Si dentro de la b&uacute;squeda incluyes dos o m&aacute;s t&eacute;rminos que correspondan a una misma categor&iacute;a como son las carreras profesionales o las ocupaciones, todos los t&eacute;rminos ser&aacute;n tomados en cuenta por el buscador y obtendr&aacute;s resultados m&aacute;s amplios correspondientes al total de los t&eacute;rminos buscados.</p>
<ul class="list-group-contenidos">
    <li>Ejemplo: Contador, Administraci&oacute;n, Mercadotecnia...</li>
</ul>
	          </div>
	        </div>
	        
	      </div>
	      <!-- /div contenido -->
	    </div>
	    
	    
	    
	    
	</jsp:body>
</t:publicTemplate>
