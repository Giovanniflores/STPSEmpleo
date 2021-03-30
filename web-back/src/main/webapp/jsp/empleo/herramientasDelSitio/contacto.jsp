<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURI}" />

<t:publicTemplate>
	<jsp:attribute name="titulo">Contacto</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Herramientas del Sitio</jsp:attribute>  
    <jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
 	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		Secretaría del Trabajo y Previsión Social: Periférico Sur No. 4271, Col. Fuentes del Pedregal Delegación Tlalpan Ciudad de México C.P. 14149
	</jsp:attribute>
	<jsp:body>
		<!-- div ruta_navegacion -->
	    <div class="row">
	      <div class="col-sm-12">
	<!--         <ol class="breadcrumb">
	          <li>Ruta de navegación:</li>
	          <li><a href="<c:url value="/inicio.do"/>">Inicio</a></li>
	          <li><a href="<c:url value="/jsp/empleo/herramientasDelSitio/mapaDeSitio.jsp"/>">Herramientas del sitio</a></li>
	          <li><a href="">Herramientas del sitio</a></li> 
          	  <li class="active">Contacto</li>
	        </ol>-->
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
	            <h2 class="titulosh2">Contacto</h2>
	          </div>
	          <div class="panel-body">
		
		          
	            <h3>Secretaría del Trabajo y Previsión Social</h3>
	            <p>Boulevard Adolfo López Mateos 1968, Col. Los Alpes.</p>
				<p>Delegación Álvaro Obregón.</p>
				<p>Ciudad de México.</p>
				<p>C.P. 01010.</p>

	            
	            <h3>Subsecretaría de Empleo y Productividad Laboral</h3>
	            <p>La Morena 804</p>
	            <p>Colonia Narvarte Poniente</p>
	            <p>Ciudad de México.</p>
	            <p>C.P.03020</p>
	          
	          
	            <h3>Coordinación General del Servicio Nacional de Empleo</h3>
	            <p>La Morena 804, Col. Narvarte Poniente.</p>
	            <p>Delegación Benito Juárez.</p>
	            <p>Ciudad de México.</p>
	            <p>C.P. 03020</p>
	            
	            
	           
	            
	           <img alt="" src="${context}/css/images/tel_img.gif">
	            
	            <p><b>Para resolver cualquier duda o solicitar asesoría sobre el Portal del Empleo, está a tu disposición el siguiente número telefónico:</b></p>
	            <p>01 800 841 20 20</p>
	            <p>Llámanos de 8 am a 10 pm.</p>
	            <br></br>
	            <p><b>Servicio gratuito.</b></p>
	          </div>
	        </div>
	        
	      </div>
	      <!-- /div contenido -->
	    </div>
	    
	    
	    
	    
	</jsp:body>
</t:publicTemplate>