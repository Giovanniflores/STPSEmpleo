<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURI}" />

<t:publicTemplate>
	<jsp:attribute name="titulo">Cobertura del SNE</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Servicio Nacional de Empleo</jsp:attribute>  
    <jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
 	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		El Servicio Nacional de Empleo opera a trav&eacute;s de 169 oficinas a nivel nacional: 33 centrales y 136 unidades operativas.
	</jsp:attribute>
	<jsp:body>
	
	<!-- div ruta_navegacion -->
    <div class="row">
      <div class="col-sm-12">
        <ol class="breadcrumb">
          <li>Ruta de navegación:</li>
          <li><a href="<c:url value="/inicio.do"/>">Inicio</a></li>
          <li><a href="<c:url value="/jsp/empleo/servicioNacionalDeEmpleo/servicioNacionalDeEmpleo.jsp"/>">Servicio Nacional de Empleo</a></li>
          <li><a href="<c:url value="/jsp/empleo/servicioNacionalDeEmpleo/acercaSne.jsp"/>">Acerca del SNE </a></li>
          <li class="active">Cobertura</li>
        </ol>
      </div>
    </div>
    <!-- /div ruta_navegacion -->

    <!-- div ruta_navegacion -->
    <div class="row">
    
      <jsp:include page="menu.jsp"/>
      
      <div class="col-sm-8 col-sm-pull-4">
      	 
      	 <jsp:include page="/WEB-INF/template/redes.jsp"/>
      
        <div class="clearfix"></div>
		
		 <div class="panel">
          <div class="panel-body">
			<h2 class="titulosh2">Cobertura</h2>
		
	        <p class="textmin">El Servicio Nacional de Empleo opera a trav&eacute;s de 169 oficinas a nivel nacional: 33 centrales y 136 unidades operativas.</p>
<p class="textmin">El Gobierno Federal, a trav&eacute;s de la Coordinaci&oacute;n General del Servicio Nacional de Empleo  de la Secretar&iacute;a del Trabajo y Previsi&oacute;n Social, ejecuta las acciones propias del servicio, emite las normas de operaci&oacute;n y transfiere los recursos necesarios para su funcionamiento.</p>
<p class="textmin">Los gobiernos de las entidades federativas, a trav&eacute;s de sus oficinas del Servicio Nacional de Empleo, operan directamente los programas y subprogramas de ayuda y capacitaci&oacute;n para el trabajo, adem&aacute;s de aportar recursos.</p>
<div><img alt="Mapa de México" src="${context}/css/images/contenido/cobertura.png" class="img-responsive"></div>
<div><img alt="" src="${context}/css/images/contenido/cobertura1.png" class="img-responsive"></div>
       	   </div>
	      </div>
	     </div>



    </div>
	</jsp:body>
</t:publicTemplate>
