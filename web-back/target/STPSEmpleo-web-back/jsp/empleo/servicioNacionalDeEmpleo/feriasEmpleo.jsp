<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURI}" />

<t:publicTemplate>
	<jsp:attribute name="titulo">Ferias de Empleo</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Servicio Nacional de Empleo</jsp:attribute>  
    <jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
 	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		Las Ferias de Empleo son un medio de vinculación gratuito, directo y ágil que ofrece en todo el país la Secretaría del Trabajo y Previsión Social, a través del Servicio Nacional de Empleo. En éstas, es posible interactuar y relacionarse con representantes de empresas de diferentes sectores que requieren personal.
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
          <li class="active">Ferias de Empleo</li>
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
			<h2 class="titulosh2">Ferias de Empleo</h2>
		
	        <p>Las Ferias de Empleo son un medio de vinculación gratuito, directo y ágil que ofrece en todo el país la Secretaría del Trabajo y Previsión Social, a través del Servicio Nacional de Empleo. En éstas, es posible interactuar y relacionarse con representantes de empresas de diferentes sectores que requieren personal.</p>
<p>Las Ferias de Empleo ofrecen a los buscadores de empleo:</p>

<ul class="list-group-contenidos">
    <li>Las oportunidades de trabajo disponibles.</li>
    <li>Los perfiles laborales que demanda el sector productivo.</li>
    <li>Las condiciones de trabajo ofrecidas.</li>
</ul>

<p>Además, dan la opción de presentarse personalmente de manera directa y simultánea con varios empleadores, ahorrando tiempo y dinero en la búsqueda.</p>
<p>Para fomentar mayor facilidad y alcance, se ofrecen dos modalidades de Ferias de Empleo: presencial y virtual.</p>
       	   </div>
	      </div>
	     </div>



    </div>
	</jsp:body>
</t:publicTemplate>
