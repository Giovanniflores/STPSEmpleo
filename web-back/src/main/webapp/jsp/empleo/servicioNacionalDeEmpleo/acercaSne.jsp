<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURI}" />


<t:publicTemplate>
	<jsp:attribute name="titulo">Acerca del SNE</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Servicio Nacional de Empleo</jsp:attribute>  
    <jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
 	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		El Servicio Nacional de Empleo (SNE) es la institución pública a nivel nacional que se ocupa de atender de manera gratuita y personalizada los problemas de desempleo y subempleo en el país en beneficio de sus habitantes.
	</jsp:attribute>
	<jsp:body>
	
	<!-- div ruta_navegacion -->
    <div class="row">
      <div class="col-sm-12">
        <ol class="breadcrumb">
          <li>Ruta de navegación:</li>
           <li><a href="<c:url value="/inicio.do"/>">Inicio</a></li>
	       <li><a href="<c:url value="/jsp/empleo/servicioNacionalDeEmpleo/servicioNacionalDeEmpleo.jsp"/>">Servicio Nacional de Empleo</a></li>
           <li class="active">Acerca del SNE</li>
        </ol>
      </div>
    </div>
    <!-- /div ruta_navegacion -->

    <!-- div ruta_navegacion -->
    <div class="row">
    
      <jsp:include page="menu.jsp"/>
      
      <div class="col-sm-8 col-sm-pull-4">
      
      	 <jsp:include page="/WEB-INF/template/redes.jsp"/>
      
        <div class="panel panel-grey">
          <div class="panel-body">
          <div class="col-md-6">
            <img alt="" src="${context}/css/images/contenido/acerca_sne.png" class="img-responsive">
          </div>
          <div class="col-md-6">
            <h2>Acerca del SNE</h2>
            <p>El Servicio Nacional de Empleo (SNE) es la institución pública a nivel nacional que se ocupa de atender de manera gratuita y personalizada los problemas de desempleo y subempleo en el país en beneficio de sus habitantes.</p>
          </div>
        </div>
        </div>

        <div class="clearfix"></div>
        
        <div class="panel">
          <div class="panel-body">

	        <p>La misión del SNE está dirigida a prestar los servicios de:</p>
	        
	        <ul class="list-group-contenidos">
	          <li>Información, vinculación y orientación ocupacional.</li>
	          <li>Beneficios económicos y capacitación.</li>
	          <li>Apoyos a la movilidad laboral interna y externa.</li>
	        </ul>
	        
	        <p>Este servicio es coordinado por la <a target="_blank" href="http://www.stps.gob.mx">Secretar&iacute;a del Trabajo y Previsi&oacute;n Social</a> bajo la operaci&oacute;n federalizada de las 32 entidades federativas.</p>
	        
	        </div>
	     </div>
       
      </div>



    </div>
	</jsp:body>
</t:publicTemplate>
