<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURI}" />

<t:publicTemplate>
	<jsp:attribute name="titulo">Certificaci&oacute;n de competencias</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Candidatos</jsp:attribute>  
    <jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
 	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		Puedes certificarte en diversos Estándares de Competencia que se encuentran inscritos en el Registro Nacional de Estándares de Competencia (RENEC) los cuales fueron desarrollados por las empresas, organizaciones e instituciones más relevantes de los diferentes sectores del país: productivo, educativo, social y de gobierno.
	</jsp:attribute>
	<jsp:body>
	
	<!-- div ruta_navegacion -->
    <div class="row">
      <div class="col-sm-12">
        <ol class="breadcrumb">
          <li>Ruta de navegación:</li>
          <li><a href="<c:url value="/inicio.do"/>">Inicio</a></li>
          <li><a href="<c:url value="/jsp/empleo/candidatos/candidatos.jsp"/>">Candidatos</a></li>
          <li><a href="<c:url value="/jsp/empleo/candidatos/capacitacionBecas.jsp"/>">Capacitación y becas</a></li>
          <li><a href="<c:url value="/jsp/empleo/candidatos/certificacionCompetenciasConocer.jsp"/>">Certificación de competencias (CONOCER)</a></li>
          <li class="active">Certificación de competencias</li>
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
			<h2 class="titulosh2">Certificación de competencias</h2>
		
	        <p>Puedes certificarte en diversos Estándares de Competencia que se encuentran inscritos en el Registro Nacional de Estándares de Competencia (RENEC) los cuales fueron desarrollados por las empresas, organizaciones e instituciones más relevantes de los diferentes sectores del país: productivo, educativo, social y de gobierno.</p>
			 <p><strong>CONOCER: Certificación de Competencias</strong></p>
			 <p>La siguiente imagen muestra información sobre los diversos Estándares de Competencia que se encuentran inscritos en el Registro Nacional de Estándares de Competencia (RENEC) y son los siguientes:</p>
			 <ol>
			 <li><strong>Identifica:</strong> Identifica en el RENEC algún estándar de competencia relacionado con las actividades que desempeñas en tu trabajo.</li>
			 <li><strong>Consulta:</strong> Consulta el listado de entidades de certificación y evaluación o de organismos certificadores que están autorizados para certificarte en el estándar de tu interés.</li>
			 <li><strong>Comunícate:</strong> Comunícate con la entidad de certificación y evaluación u organismo certificador.</li>
			 <li><strong>Proceso de evaluación:</strong> Solicita la evaluación de tu competencia.</li>
			 <li><strong>Certificado:</strong> Una vez demostrada tu competencia, recibe tu certificado.</li>
			 </ol>			 
			 <img alt="Conocer" src="${context}/css/images/contenido/esquema_candidatos.jpg" class="img-responsive"/>
			<p><strong>&iquest;Qu&eacute; beneficios obtendr&eacute; al certificarme?</strong></p>
<ul class="list-group-contenidos">
    <li>Mejorar&aacute;s tu desempe&ntilde;o en el trabajo.</li>
    <li>Podr&aacute;s integrarte al mercado laboral de manera exitosa.</li>
    <li>Tendr&aacute;s mayor movilidad laboral.</li>
    <li>Aumentar&aacute;s la estabilidad de tu empleo.</li>
    <li>Obtendr&aacute;s un documento oficial de alcance nacional que respalde tus conocimientos, habilidades, destrezas y comportamientos reconocido por los sectores productivos, social, educativo o de gobierno.</li>
    <li>Experimentar&aacute;s una superaci&oacute;n personal, mayor motivaci&oacute;n y mejores resultados en tu trabajo.</li>
    <li>Gozar&aacute;s del reconocimiento social por el trabajo que realizas.</li>
</ul>
<p><strong>CONOCER Tel.</strong><br />
Desde la Cd. de M&eacute;xico <strong>2282-0200</strong><br />
Del interior marca <strong>01 800 288 26 66</strong></p>
<p>M&aacute;s informaci&oacute;n en <a href="http://www.conocer.gob.mx" target="_blank">www.conocer.gob.mx</a></p>
		

       	   </div>
	      </div>
	     </div>



    </div>
	</jsp:body>
</t:publicTemplate>
