<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURI}" />

<t:publicTemplate>
	<jsp:attribute name="titulo">Frases textuales</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Herramientas del Sitio</jsp:attribute>  
    <jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
 	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		Se pueden realizar b&uacute;squedas de frases textuales escribiendo la frase que se desea buscar entre comillas.
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
          	  <li class="active">Frases textuales</li>
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
	            <h2 class="titulosh2">Frases textuales</h2>
	          </div>
	          <div class="panel-body">
	          
	            <p>Se pueden realizar b&uacute;squedas de frases textuales escribiendo la frase que se desea buscar entre comillas. Por ejemplo:</p>
<ul class="list-group-contenidos">
    <li>Ejemplo:  &quot;Profesor de ingl&eacute;s&quot;</li>
</ul>
<p>Al introducir esta b&uacute;squeda, los resultados que se obtienen son todas las ofertas que tienen, en alguno de sus campos, la frase exacta &quot;profesor de ingl&eacute;s&quot; y los sin&oacute;nimos que correspondan, tales como: &quot;maestro de ingl&eacute;s&quot;, &quot;maestra de ingl&eacute;s&quot;, &quot;profesora de ingl&eacute;s&quot;.</p>
<p>En este caso el orden en el que se escriben las palabras s&iacute; es importante, debido a que la b&uacute;squeda que se realiza es de la frase c&oacute;mo est&aacute; escrita entre comillas textualmente.</p>
<p>Cabe mencionar que la palabra &ldquo;de&rdquo; es omitida por el buscador al momento de realizar la b&uacute;squeda, ya que como se mencion&oacute; anteriormente en el presente documento, &eacute;sta es una palabra no relevante.</p>
	          </div>
	        </div>
	        
	      </div>
	      <!-- /div contenido -->
	    </div>
	    
	    
	    
	    
	</jsp:body>
</t:publicTemplate>
