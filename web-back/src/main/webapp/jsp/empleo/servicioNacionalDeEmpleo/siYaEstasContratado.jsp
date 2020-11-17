<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURI}" />

<t:publicTemplate>
	<jsp:attribute name="titulo">Si ya est&aacute;s contratado</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Servicio Nacional de Empleo</jsp:attribute>  
    <jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
 	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		Los siguientes consejos te ayudar&aacute;n a mantenerte vigente en el &aacute;rea laboral.
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
          <li class="active">Si ya estás contratado</li>
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
			<h2 class="titulosh2">Si ya estás contratado</h2>
		
	        <p>Da lo mejor de ti.</p>
<p>Los siguientes consejos te ayudar&aacute;n a mantenerte vigente en el &aacute;rea laboral.</p>
<ol>
    <li>Para tu contrataci&oacute;n presenta documentos y certificados ver&iacute;dicos y comprobables ante las instituciones que los emitieron. Informa siempre de cualquier cambio en tus datos personales.</li>
    <li>S&eacute; puntual con tu horario y con la entrega de tus trabajos. Solicita todas las explicaciones que necesites para entender la forma que aplica la empresa para medir que cumples con lo contratado.</li>
    <li>S&eacute; respetuoso con los jefes, cordial con tus compa&ntilde;eros y amable con el personal a tu cargo.</li>
    <li>Lee tu contrato y conoce cu&aacute;les son tus deberes y tus beneficios. T&uacute; tienes derechos pero tambi&eacute;n tienes obligaciones con la empresa, con tu familia y contigo mismo.</li>
    <li>Haz una lista de las herramientas, equipos, normas, computadoras y mobiliario que recibes para realizar tu trabajo. En esta lista incluye tu locker y las llaves de &aacute;reas comunes. Tu obligaci&oacute;n es conservarlos en buen estado y saber utilizar materiales, equipos y espacios. Evita prestar tus herramientas de trabajo sin documentarlo. Obt&eacute;n invariablemente permiso para hacerlo o para sacarlos de las instalaciones.</li>
    <li>No participes o propagues rumores o comentarios mal intencionados y sin sustento. Todos tenemos d&iacute;as malos, procura en la medida de lo posible, atender en el trabajo situaciones de trabajo y los asuntos de casa, en tu casa.</li>
    <li>Conoce los equipos de seguridad, de protecci&oacute;n civil y los de control ambiental que se requieran para actuar con responsabilidad.</li>
    <li>S&eacute; cooperativo, evita hacer lo m&iacute;nimo indispensable, colabora con las metas de tu empresa.</li>
    <li>No faltes. Pide permiso s&oacute;lo en los casos urgentes. En los casos extremos &iexcl;negocia! Procura reponer el tiempo.</li>
    <li>Avisa oportunamente sobre prescripciones m&eacute;dicas que puedan alterar tu conducta. Prefiere recetas m&eacute;dicas de instituciones de seguridad social. Proh&iacute;bete estados de embriaguez.</li>
    <li>Guarda discreci&oacute;n sobre la informaci&oacute;n, f&oacute;rmulas, claves y documentos reservados; no saques de la empresa ning&uacute;n material, producto o informe que comprometa tu relaci&oacute;n de trabajo.</li>
    <li>Aprende. Pon inter&eacute;s en saber bien lo que haces. Capac&iacute;tate. Acude a cursos sobre tu trabajo. Aprender es ganar un lugar en el futuro.</li>
</ol>
<p>Aqu&iacute; termina el m&oacute;dulo</p>
       	   </div>
	      </div>
	     </div>



    </div>
	</jsp:body>
</t:publicTemplate>
