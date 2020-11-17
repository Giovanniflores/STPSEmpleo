<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="url" value="${pageContext.request.requestURI}" />


<t:publicTemplate>
	<jsp:attribute name="titulo">Abriendo Espacios</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Servicio Nacional de Empleo</jsp:attribute>  
    <jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
 	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		El Servicio Nacional de Empleo (SNE) cuenta con la estrategia Abriendo Espacios, cuyo objetivo es apoyar a los buscadores de empleo con discapacidad y adultos mayores, para reducir las dificultades que enfrentan al insertarse en el mercado laboral.
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
          <li class="active">Abriendo Espacios</li>
        </ol>
      </div>
    </div>
    <!-- /div ruta_navegacion -->

    <!-- div ruta_navegacion -->
    <div class="row">
    
      <jsp:include page="menu.jsp" />
      
      <div class="col-sm-8 col-sm-pull-4">
      	 
      	 <jsp:include page="/WEB-INF/template/redes.jsp" />
      
        <div class="clearfix"></div>
		
		 <div class="panel">
          <div class="panel-body">
			<h2 class="titulosh2">Abriendo Espacios</h2>
		
	        <p>El Servicio Nacional de Empleo (SNE) cuenta con la estrategia Abriendo Espacios, cuyo objetivo es apoyar a los buscadores de empleo con discapacidad y adultos mayores, para reducir las dificultades que enfrentan al insertarse en el mercado laboral.</p>
<p>Abriendo Espacios ofrece atenci&oacute;n personalizada, a trav&eacute;s de acciones de vinculaci&oacute;n, orientaci&oacute;n laboral, capacitaci&oacute;n, ocupaci&oacute;n por cuenta propia y, en algunos casos, evaluaci&oacute;n de habilidades y competencias.</p>
<p>Si eres un adulto mayor o persona con discapacidad y requieres apoyo para integrarte al mercado laboral, en el SNE puedes recibir la ayuda que necesitas.</p>
<p>
							<strong>Requisitos:</strong>
						</p>
<ol>
    <li>Ser mayor de 16 a&ntilde;os, si eres persona con discapacidad; y 60 a&ntilde;os, si eres adulto mayor.</li>
    <li>Solicitar el servicio directamente, no a trav&eacute;s de una tercera persona.</li>
    <li>Proporcionar con veracidad la informaci&oacute;n que se requiera.</li>
    <li>Observar el procedimiento de tr&aacute;mite establecido.</li>
</ol>
<!--  las 24 horas del día -->
<p>Para mayor informaci&oacute;n, visita <a
								href="http://www.abriendoespacios.gob.mx" target="_blank">www.abriendoespacios.gob.mx</a> o acude a la oficina el SNE m&aacute;s cercana a tu domicilio. Tambi&eacute;n, puedes llamarnos al 01 800 8 41 2020, sin ning&uacute;n costo, ll&aacute;manos de 8 am a 10 pm los 365 d&iacute;as del a&ntilde;o, y uno de nuestros consejeros con gusto te atender&aacute;.</p>
       	   </div>
	      </div>
	     </div>



    </div>
	</jsp:body>
</t:publicTemplate>
