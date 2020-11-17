<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURI}" />

<t:publicTemplate>
	<jsp:attribute name="titulo">Uso de frases</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Herramientas del Sitio</jsp:attribute>  
    <jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
 	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		Si deseas buscar ofertas de empleo que requieran disponibilidad para viajar o para reubicarse en otros estados, puedes escribir Disponibilidad para viajar &oacute; Disponibilidad para radicar fuera, seg&uacute;n sea el caso.
	</jsp:attribute>
	<jsp:body>
		<!-- div ruta_navegacion -->
	    <div class="row">
	      <div class="col-sm-12">
	        <ol class="breadcrumb">
	          <li>Ruta de navegación:</li>
	           <li><a href="<c:url value="/inicio.do"/>">Inicio</a></li>
	          <li><a href="<c:url value="/jsp/empleo/herramientasDelSitio/mapaDeSitio.jsp"/>">Herramientas del sitio</a></li>
	          <li><a href="<c:url value="/jsp/empleo/herramientasDelSitio/necesitasAyuda.jsp"/>">¿Necesitas ayuda?</a></li>
	          <li><a href="<c:url value="/jsp/empleo/herramientasDelSitio/usoBuscador.jsp"/>">Uso del buscador</a></li>
          	  <li class="active">Uso de frases</li>
	        </ol>
	      </div>
	    </div>
	    <!-- /div ruta_navegacion -->
	    
	    <div class="row">
		  <!-- div menu -->
		  <jsp:include page="menu.jsp"/>
		  
		  <!-- div contenido -->
	      <div class="col-sm-8 col-sm-pull-4">
	      
	       <jsp:include page="/WEB-INF/template/redes.jsp"/>
	
	        <div class="panel panel-contacto">
	          <div class="panel-heading">
	            <h2 class="titulosh2">Uso de frases</h2>
	          </div>
	          <div class="panel-body">
	          
	            <p>
<pa p="" tu="" para="" utilizar="" puedes="" que="" comunes="" frases="" de="" lista="" una="" presenta="" se=""></pa>
</p>
<p>Si deseas buscar ofertas de empleo que requieran disponibilidad para viajar o para reubicarse en otros estados, puedes escribir Disponibilidad para viajar &oacute; Disponibilidad para radicar fuera, seg&uacute;n sea el caso.</p>
<p>Si deseas encontrar ofertas laborales que no requieran estudios previos, puedes escribir Sin instrucci&oacute;n &oacute; Sin estudios. Con esta frase el buscador eliminar&aacute; las ofertas de empleo que requieran cumplir con cualquier tipo de instrucci&oacute;n (nivel de estudios).</p>
<p>Para buscar ofertas laborales con una disponibilidad de horario definida puedes escribir cualquiera de las siguientes opciones, seg&uacute;n tu disponibilidad:</p>
<ul class="list-group-contenidos">
    <li>Nocturno</li>
    <li>Medio tiempo</li>
    <li>Tiempo completo</li>
    <li>Fines de semana</li>
    <li>Temporal</li>
    <li>Rolar turnos</li>
</ul>
<p>A continuaci&oacute;n, se presentan algunas de las palabras y frases m&aacute;s comunes que pueden ser utilizadas en tu b&uacute;squeda:</p>
<ul class="list-group-contenidos">
    <li>Sin estudios, Saber leer, Saber escribir&hellip;</li>
    <li>Primaria, Secundaria, Preparatoria&hellip;</li>
    <li class="no_line">Carrera t&eacute;cnica, T&eacute;cnico Superior Universitario, Licenciatura, Maestr&iacute;a, Doctorado&hellip;</li>
    <li>Medio tiempo, Tiempo completo&hellip;</li>
    <li>Ingl&eacute;s, Espa&ntilde;ol, Franc&eacute;s, Portugu&eacute;s, Italiano, Japon&eacute;s, Alem&aacute;n, Chino&hellip;</li>
</ul>
	          </div>
	        </div>
	        
	      </div>
	      <!-- /div contenido -->
	    </div>
	    
	    
	    
	    
	</jsp:body>
</t:publicTemplate>
