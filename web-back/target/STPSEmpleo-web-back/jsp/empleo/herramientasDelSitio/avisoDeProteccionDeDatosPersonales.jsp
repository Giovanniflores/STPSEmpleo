<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURI}" />

<t:publicTemplate>
	<jsp:attribute name="titulo">Aviso de protecci&oacute;n de datos personales</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Herramientas del sitio</jsp:attribute>  
    <jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
 	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		Los datos personales recabados serán protegidos de conformidad con lo establecido en los artículos 20, 24, 25 y 26 de la Ley Federal de Transparencia y Acceso a la Información Pública Gubernamental (LFTAIPG) y el 47 y 48 de su Reglamento y se incorporarán y tratarán únicamente con fines de registro, seguimiento y control de la población atendida...
	</jsp:attribute>
	<jsp:body>
		<!-- div ruta_navegacion -->
	    <div class="row">
	      <div class="col-sm-12">
	        <ol class="breadcrumb">
	          <li>Ruta de navegación:</li>
	           <li> <a href="<c:url value="/inicio.do"/>">Inicio</a></li>
<!-- 	          <li><a href="#">Inicio</a></li> -->
	          <li><a href="<c:url value="/jsp/empleo/herramientasDelSitio/mapaDeSitio.jsp"/>">Herramientas del sitio</a></li>
	          <li class="active">Aviso de Protección de Datos Personales</li>
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
	
	        <div class="panel">
	          <div class="panel-heading">
	            <h2 class="titulosh2">Aviso de Protección de Datos Personales</h2>
	          </div>
	          <div class="panel-body">
	            <p>Los datos personales recabados serán protegidos de conformidad con lo establecido en los artículos 20, 24, 25 y 26 de la Ley 
	            	Federal de Transparencia y Acceso a la Información Pública Gubernamental (LFTAIPG) y el 47 y 48 de su Reglamento y se incorporarán 
	            	y tratarán únicamente con fines de registro, seguimiento y control de la población atendida y beneficiada por los programas, servicios 
	            	y estrategias a cargo de la Coordinación General del Servicio Nacional de Empleo (CGSNE) en los sistemas de datos personales de los 
	            	que ésta es responsable y que se encuentran registrados en el listado de sistemas de datos personales ante el Instituto Federal de 
	            	Acceso a la Información y Protección de Datos (<a target="_blank" href="http://persona.ifai.org.mx/">http://persona.ifai.org.mx/</a>). 
	            	El interesado podrá presentar sus solicitudes de acceso y/o corrección de datos personales ante la Unidad de Enlace de la Secretaría del 
	            	Trabajo y Previsión Social en el domicilio ubicado en Boulevard Adolfo López Mateos (Periférico Sur) número 4271, colonia Fuentes del Pedregal, 
	            	Delegación Tlalpan, C.P. 14149, Distrito Federal, de acuerdo a lo establecido en los artículos 24 y 25 de la LFTAIPG y el 66, 76, 78 y 79 de su 
	            	Reglamento; asimismo, podrá manifestar sus dudas o comentarios al correo electrónico <a href="mailto:transparencia@stps.gob.mx">transparencia@stps.gob.mx</a>
	            </p>
	          </div>
	        </div>
	        
	      </div>
	      <!-- /div contenido -->
	    </div>
	    
	    
	    
	    
	</jsp:body>
</t:publicTemplate>
