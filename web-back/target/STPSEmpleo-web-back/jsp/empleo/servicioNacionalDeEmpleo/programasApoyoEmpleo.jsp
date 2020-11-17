<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURI}" />

<t:publicTemplate>
	<jsp:attribute name="titulo">Programas de Apoyo al Empleo</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Servicio Nacional de Empleo</jsp:attribute>  
    <jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
 	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		El objetivo del Programa de Apoyo al Empleo es promover la colocaci&oacute;n, en una ocupaci&oacute;n o actividad productiva, de personas desempleadas o subempleadas  mediante el otorgamiento de apoyos econ&oacute;micos o en especie que permitan la capacitaci&oacute;n, autoempleo o movilidad laboral requerida para su desarrollo.
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
          <li class="active">Programa de Apoyo al Empleo</li>
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
			<h2 class="titulosh2">Programa de Apoyo al Empleo</h2>
		
	        <p>El objetivo del Programa de Apoyo al Empleo es promover la colocaci&oacute;n, en una ocupaci&oacute;n o actividad productiva, de personas desempleadas o subempleadas  mediante el otorgamiento de apoyos econ&oacute;micos o en especie que permitan la capacitaci&oacute;n, autoempleo o movilidad laboral requerida para su desarrollo</p>
<p>La iniciativa tambi&eacute;n ofrece apoyos para que las personas repatriadas puedan integrarse sin dificultad a la fuerza laboral del pa&iacute;s.</p>
<p><a href="${context}/download/candidatos/2018.pdf" target="_blank">Descarga aqu&iacute; las bases de operaci&oacute;n.</a>
<!-- a href="${context}/download/candidatos/reglas_operación_PAE_2017.pdf" target="_blank">Descarga aqu&iacute; las bases de operaci&oacute;n.</a--></p>
       	   </div>
	      </div>
	     </div>



    </div>
	</jsp:body>
</t:publicTemplate>
