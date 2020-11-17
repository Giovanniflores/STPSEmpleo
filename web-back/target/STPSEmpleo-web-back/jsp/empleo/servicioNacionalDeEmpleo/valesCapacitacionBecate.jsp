<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURI}" />


<t:publicTemplate>
	<jsp:attribute name="titulo">Vales de capacitaci&oacute;n (B&eacute;cate)</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Servicio Nacional de Empleo</jsp:attribute>  
    <jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
 	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		Esta modalidad se enfoca en la atenci&oacute;n a personas que requieren adquirir los conocimientos, habilidades y destrezas para realizar una actividad productiva por cuenta propia.
	</jsp:attribute>
	<jsp:body>
	
	<!-- div ruta_navegacion -->
    <div class="row">
      <div class="col-sm-12">
        <ol class="breadcrumb">
          <li>Ruta de navegación:</li>
          <li><a href="/inicio.do">Inicio</a></li>
          <li><a href="<c:url value="/jsp/empleo/servicioNacionalDeEmpleo/servicioNacionalDeEmpleo.jsp"/>">Servicio Nacional de Empleo</a></li>
          <li><a href="<c:url value="/jsp/empleo/servicioNacionalDeEmpleo/programasServiciosEmpleo.jsp"/>">Programas y servicios de empleo</a></li>
          <li><a href="<c:url value="/jsp/empleo/servicioNacionalDeEmpleo/programasApoyoEmpleo.jsp"/>">Programas de Apoyo al Empleo</a></li>
          <li><a href="<c:url value="/jsp/empleo/servicioNacionalDeEmpleo/becasCapacitacionTrabajoBecate.jsp"/>">Becas a la Capacitación para el Trabajo (Bécate)</a></li>
          <li class="active">Vales de capacitación</li>
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
			<h2 class="titulosh2">Vales de capacitación</h2>
		
	        <p>Esta modalidad se enfoca en la atenci&oacute;n a personas que requieren adquirir los conocimientos, habilidades y destrezas para realizar una actividad productiva por cuenta propia.</p>
<p><strong>Apoyos</strong></p>
<p>Durante el periodo de capacitaci&oacute;n, tendr&aacute;s derecho a los siguientes beneficios:</p>
<ul class="list-group-contenidos">
    <li>Apoyo econ&oacute;mico de uno a dos salarios m&iacute;nimos mensuales.</li>
    <li>Pago de materiales e instructores para la capacitaci&oacute;n.</li>
    <li>Apoyo para el transporte.</li>
    <li>Seguro contra accidentes.</li>
</ul>
<p><strong>Requisitos</strong></p>
<p>Para poder participar en un curso en esta modalidad, deber&aacute;s cubrir los siguientes requisitos:</p>
<ul class="list-group-contenidos">
    <li>Ser buscador de empleo.</li>
    <li>Tener 16 a&ntilde;os o m&aacute;s.</li>
    <li>Cubrir el perfil requerido en el programa de capacitaci&oacute;n.</li>
    <li>Solicitar tu incorporaci&oacute;n de manera personal en las oficinas del <a href="<c:url value="/jsp/empleo/servicioNacionalDeEmpleo/ubicacion.jsp"/>">Servicio Nacional de Empleo</a> en tu localidad.</li>
</ul>
<p>Conoce algunos de los cursos que se han impartido en esta modalidad:</p>
<p>Para mayor informaci&oacute;n sobre los cursos disponibles, acude a las oficinas del <a href="<c:url value="/jsp/empleo/servicioNacionalDeEmpleo/ubicacion.jsp"/>">Servicio Nacional de Empleo</a> en tu localidad.</p>
<ol>
    <li>Cultura de belleza</li>
    <li>Corte y confecci&oacute;n</li>
    <li>Reposter&iacute;a</li>
    <li>Panader&iacute;a</li>
    <li>Bisuter&iacute;a</li>
    <li>Elaboraci&oacute;n de pi&ntilde;atas</li>
    <li>Carpinter&iacute;a</li>
    <li>Panader&iacute;a y reposter&iacute;a</li>
    <li>Manualidades</li>
    <li>Preparaci&oacute;n de alimentos y reposter&iacute;a</li>
</ol>
<!--  las 24 horas del día -->
<p>Para mayor información, acude a la <a href="<c:url value="/jsp/empleo/servicioNacionalDeEmpleo/ubicacion.jsp"/>">oficina</a> del Servicio Nacional de Empleo más cercana a tu domicilio. También, puedes llamarnos al 01 800 8 41 2020, sin ningún costo, ll&aacute;manos de 8 am a 10 pm los 365 días del año, y uno de nuestros consejeros con gusto te atenderá.</p>

       	   </div>
	      </div>
	     </div>



    </div>
	</jsp:body>
</t:publicTemplate>
