<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURI}" />


<t:publicTemplate>
	<jsp:attribute name="titulo">Observatorio Laboral</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Servicio Nacional de Empleo</jsp:attribute>  
    <jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
 	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		La Secretar&iacute;a del Trabajo y Previsi&oacute;n Social (STPS) pone a tu disposici&oacute;n, el Observatorio Laboral, un espacio que ofrece informaci&oacute;n relacionada con las caracter&iacute;sticas y el comportamiento de las ocupaciones y profesiones m&aacute;s representativas de M&eacute;xico.
	</jsp:attribute>
	<jsp:body>
	
	<!-- div ruta_navegacion -->
    <div class="row">
      <div class="col-sm-12">
        <ol class="breadcrumb">
          <li>Ruta de navegación:</li>
          <li><a href="<c:url value="/inicio.do"/>">Inicio</a></li>
          <li><a href="<c:url value="/jsp/empleo/servicioNacionalDeEmpleo/servicioNacionalDeEmpleo.jsp"/>">Servicio Nacional de Empleo</a></li>
          <li><a href="<c:url value="/jsp/empleo/servicioNacionalDeEmpleo/programasServiciosEmpleo.jsp"/>">Programas y servicios de empleo</a></li>
          <li><a href="<c:url value="/jsp/empleo/servicioNacionalDeEmpleo/vinculacionLaboral.jsp"/>">Vinculación laboral</a></li>
          <li class="active">Observatorio Laboral</li>
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
			<h2 class="titulosh2">Observatorio Laboral</h2>
		
	        <p>La Secretar&iacute;a del Trabajo y Previsi&oacute;n Social (STPS) pone a tu disposici&oacute;n, el Observatorio Laboral, un espacio que ofrece informaci&oacute;n relacionada con las caracter&iacute;sticas y el comportamiento de las ocupaciones y profesiones m&aacute;s representativas de M&eacute;xico.</p>
<p>Este espacio ofrece indicadores de ocupaci&oacute;n y empleo para carreras profesionales, universidades tecnol&oacute;gicas, carreras t&eacute;cnicas de nivel medio superior y ocupacionales, tanto a nivel nacional y estatal, entre los que destacan:</p>
<ul class="list-group-contenidos">
    <li>N&uacute;mero de ocupados</li>
    <li>Distribuci&oacute;n de g&eacute;nero</li>
    <li>Ingreso promedio mensual</li>
    <li>Edad de los ocupados</li>
    <li>Principales ocupaciones en que se ocupan</li>
    <li>Actividad econ&oacute;mica en la que se ocupan</li>
    <li>Regi&oacute;n en la que se ocupan</li>
</ul>
<p>As&iacute; mismo se ofrece informaci&oacute;n de becas, perfiles ocupacionales, cursos, matr&iacute;cula y egreso, vacantes, oferta educativa entre otras m&aacute;s.</p>
<p>El objetivo es ofrecer informaci&oacute;n acerca las profesiones y ocupaciones del pa&iacute;s, para que puedas complementar la toma de decisi&oacute;n sobre tu futuro laboral.</p>
<p>Para mayor informaci&oacute;n, visita <a target="_blank" href="http://www.observatoriolaboral.gob.mx">www.observatoriolaboral.gob.mx</a></p>
       	   </div>
	      </div>
	     </div>



    </div>
	</jsp:body>
</t:publicTemplate>
