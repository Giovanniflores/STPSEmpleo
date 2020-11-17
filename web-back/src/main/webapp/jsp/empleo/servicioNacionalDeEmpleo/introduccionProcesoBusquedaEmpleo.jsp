<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="url" value="${pageContext.request.requestURI}" />

<t:publicTemplate>
	<jsp:attribute name="titulo">Introducci&oacute;n al proceso de b&uacute;squeda de empleo</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Servicio Nacional de Empleo</jsp:attribute>  
    <jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
 	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		El nuevo contexto socioecon&oacute;mico del pa&iacute;s y la inclusi&oacute;n de &eacute;ste en el mercado internacional, requiere que los buscadores de empleo tengan en cuenta varias cosas antes del inicio de su b&uacute;squeda. Entre los principales puntos que deben considerar est&aacute;n los siguientes:
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
          <li class="active">Introducción al proceso de búsqueda de empleo</li>
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
			<h2 class="titulosh2">Introducción al proceso de búsqueda de empleo</h2>
		
	        <p>El nuevo contexto socioecon&oacute;mico del pa&iacute;s y la inclusi&oacute;n de &eacute;ste en el mercado internacional, requiere que los buscadores de empleo tengan en cuenta varias cosas antes del inicio de su b&uacute;squeda. Entre los principales puntos que deben considerar est&aacute;n los siguientes:</p>
<ul class="list-group-contenidos">
    <li>Haber identificado las habilidades que poseen.</li>
    <li>Conocer las cualidades que est&aacute;n requiriendo las empresas a quienes pretenden incorporarse a ellas (liderazgo, actitud positiva, comunicaci&oacute;n, flexibilidad, trabajo en equipo, dedicaci&oacute;n, confianza, honestidad, integridad e iniciativa).</li>
    <li>Identificar sus propias debilidades, carencias y deficiencias.</li>
</ul>
<p>Para ello, el buscador puede dar respuesta a preguntas como las siguientes:</p>
<ul class="list-group-contenidos">
    <li>&iquest;Cu&aacute;les son mis expectativas?</li>
    <li>&iquest;Qu&eacute; necesidades tengo?</li>
    <li>&iquest;Qu&eacute; tipo de relaci&oacute;n he construido con quienes me relaciono?</li>
    <li>&iquest;Me cuesta trabajo adaptarme a los cambios?</li>
    <li>&iquest;Me gusta llegar a tiempo a mi trabajo?</li>
    <li>&iquest;Me interesa continuar aprendiendo?</li>
    <li>&iquest;Podr&iacute;a hacer trabajos independientes?</li>
    <li>&iquest;Soy constante en lo que inicio?</li>
    <li>&iquest;Puedo trabajar bajo presi&oacute;n?</li>
    <li>&iquest;Siempre tomo mis propias decisiones?</li>
    <li>&iquest;Me muevo con iniciativa?</li>
    <li>&iquest;Qu&eacute; tipo de trabajos me interesan?</li>
</ul>
       	   </div>
	      </div>
	     </div>



    </div>
	</jsp:body>
</t:publicTemplate>
