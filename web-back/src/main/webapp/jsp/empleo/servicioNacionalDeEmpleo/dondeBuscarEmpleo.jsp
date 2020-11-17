<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURI}" />

<t:publicTemplate>
	<jsp:attribute name="titulo">D&oacute;nde buscar empleo</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Servicio Nacional de Empleo</jsp:attribute>  
    <jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
 	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		Algunas de las preguntas frecuentes que se hacen los buscadores de empleo respecto de &iquest;d&oacute;nde buscar y d&oacute;nde encontrar empleo? son las siguientes:
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
          <li><a href="<c:url value="/jsp/empleo/servicioNacionalDeEmpleo/talleresBuscadoresEmpleo.jsp"/>">Talleres para buscadores de empleo</a></li>
          <li><a href="<c:url value="/jsp/empleo/servicioNacionalDeEmpleo/talleresLinea.jsp"/>">Talleres en línea</a></li>
          <li class="active">¿Dónde buscar empleo?</li>
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
			<h2 class="titulosh2">¿Dónde buscar empleo?</h2>
		
	        <p>Algunas de las preguntas frecuentes que se hacen los buscadores de empleo respecto de &iquest;d&oacute;nde buscar y d&oacute;nde encontrar empleo? son las siguientes:</p>
<ul class="list-group-contenidos">
    <li>&iquest;D&oacute;nde encontrar un empleo?</li>
    <li>&iquest;Qui&eacute;nes ofrecen empleo?</li>
    <li>&iquest;A d&oacute;nde o a qui&eacute;n me dirijo?</li>
</ul>
<p>Los cambios en el mercado de trabajo no s&oacute;lo se dan entre quienes buscan y quienes ofrecen empleo. Los mecanismos y/o herramientas de intermediaci&oacute;n, basados en las Tecnolog&iacute;as de la Informaci&oacute;n y la Comunicaci&oacute;n (TIC), han permitido la aparici&oacute;n de una gran cantidad de agentes que ofrecen soluciones para buscadores y oferentes de empleo.</p>
<p>Estos cambios han provocado,  por un lado, que exista la posibilidad que para una oferta de empleo haya un mayor n&uacute;mero de solicitantes; por otro lado, poner en contacto &mdash;de manera m&aacute;s &aacute;gil y eficiente&mdash; a  buscadores y oferentes, utilizando herramientas sofisticadas que permiten realizar evaluaciones, a partir de an&aacute;lisis de datos, de los mejores candidatos para una vacante o de las vacantes m&aacute;s apegadas al perfil del buscador.</p>
<p>Algunos de los lugares o herramientas que pueden ayudar a los buscadores de empleo son los siguientes:</p>
<ul class="list-group-contenidos">
    <li>Agencias de colocaci&oacute;n privadas, tales como ADECCO, MANPOWER, BUMERAN, OCCMUNDIAL, EMPLEOMX, entre otras.</li>
    <li>Oficinas p&uacute;blicas de empleo: SNE.</li>
    <li>Organismos empresariales: COPARMEX, ASOCIACI&Oacute;N DE INDUSTRIALES, CANACINTRA, CANACO, etc.</li>
    <li>Fundaciones.</li>
    <li>Publicaciones en peri&oacute;dicos.</li>
    <li>Instituciones de educaci&oacute;n.</li>
    <li>Instalaciones de diferentes sistemas de transporte.</li>
    <li>Sindicatos.</li>
    <li>Colegios de profesionistas.</li>
    <li>Contactos personales (familiares, amigos, compa&ntilde;eros de escuela, etc.), que conforman las redes personales de cada uno.</li>
</ul>
       	   </div>
	      </div>
	     </div>



    </div>
	</jsp:body>
</t:publicTemplate>
