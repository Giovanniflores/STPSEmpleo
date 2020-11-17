<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURI}" />

<t:publicTemplate>
	<jsp:attribute name="titulo">Vinculaci&oacute;n laboral del SNE</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Servicio Nacional de Empleo</jsp:attribute>  
    <jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
 	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		La Vinculaci&oacute;n Laboral que ofrece la Coordinaci&oacute;n General del Servicio Nacional de Empleo se da a  trav&eacute;s de sus diferentes estrategias las cuales  proporcionan asesor&iacute;a y orientaci&oacute;n ocupacional para la colocaci&oacute;n adecuada de los buscadores de empleo, desempleados y subempleados, adem&aacute;s de ofrecer mecanismos de vinculaci&oacute;n laboral a oferentes y demandantes de empleo, a trav&eacute;s de la planeaci&oacute;n y conducci&oacute;n de diversas estrategias, instrumentos y mecanismos de atenci&oacute;n, que permitan la efectiva incorporaci&oacute;n de la poblaci&oacute;n en el mercado de trabajo, incluidos aquellos basados en las tecnolog&iacute;as de la informaci&oacute;n y las telecomunicaciones como son:
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
          <li class="active">Vinculación laboral</li>
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
			<h2 class="titulosh2">Vinculación laboral</h2>
		
	        <p>La Vinculaci&oacute;n Laboral que ofrece la Coordinaci&oacute;n General del Servicio Nacional de Empleo se da a  trav&eacute;s de sus diferentes estrategias las cuales  proporcionan asesor&iacute;a y orientaci&oacute;n ocupacional para la colocaci&oacute;n adecuada de los buscadores de empleo, desempleados y subempleados, adem&aacute;s de ofrecer mecanismos de vinculaci&oacute;n laboral a oferentes y demandantes de empleo, a trav&eacute;s de la planeaci&oacute;n y conducci&oacute;n de diversas estrategias, instrumentos y mecanismos de atenci&oacute;n, que permitan la efectiva incorporaci&oacute;n de la poblaci&oacute;n en el mercado de trabajo, incluidos aquellos basados en las tecnolog&iacute;as de la informaci&oacute;n y las telecomunicaciones como son:</p>
<ul class="list-group-contenidos">
    <li>Bolsa de Trabajo.</li>
    <li>Centros de Intermediaci&oacute;n Laboral.</li>
    <!-- li>Kioscos de consulta del Portal del Empleo.</li -->
    <li>Ferias de Empleo.</li>
    <li>Abriendo Espacios.</li>
    <li>Portal del Empleo.</li>
    <li>Servicio Nacional de Empleo por Tel&eacute;fono.</li>
    <li>Sistema Estatal de Empleo.</li>
    <li>Talleres para buscadores de empleo.</li>
    <li>Observatorio Laboral.</li>
</ul>
       	   </div>
	      </div>
	     </div>



    </div>
	</jsp:body>
</t:publicTemplate>
