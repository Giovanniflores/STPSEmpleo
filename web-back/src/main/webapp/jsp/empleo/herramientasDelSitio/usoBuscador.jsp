<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURI}" />

<t:publicTemplate>
	<jsp:attribute name="titulo">Uso del Buscador</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Herramientas del Sitio</jsp:attribute>  
    <jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
 	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		&iexcl;El Portal del Empleo trae para ti el nuevo Buscador Sem&aacute;ntico de ofertas laborales!
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
          	  <li class="active">Uso del Buscador</li>
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
	            <h2 class="titulosh2">Uso del Buscador</h2>
	          </div>
	          <div class="panel-body">
	          
	            <h3>Buscador Sem&aacute;ntico de ofertas laborales.</h3>
<p>&iexcl;El Portal del Empleo trae para ti el nuevo Buscador Sem&aacute;ntico de ofertas laborales!</p>
<p>El buscador te permitir&aacute; encontrar ofertas de empleo con s&oacute;lo escribir las palabras clave de tus preferencias laborales. El procedimiento es sencillo:</p>
<ol>
    <li>Escribe en el campo de texto &quot;&iquest;Qu&eacute; empleo buscas?&quot; las palabras clave de tu b&uacute;squeda de empleo.</li>
    <li>En el campo &quot;&iquest;D&oacute;nde?&quot;, elige el estado donde deseas encontrar empleo.</li>
    <li>Haz clic en el bot&oacute;n &quot;Buscar&quot; y obt&eacute;n m&uacute;ltiples opciones de empleo acordes con tu b&uacute;squeda.</li>
</ol>
<p>Te sugerimos consultar las siguientes recomendaciones para obtener mejores resultados en tu b&uacute;squeda de empleo:</p>
<ul class="list-group-contenidos">
    <li><a href="<c:url value="/jsp/empleo/herramientasDelSitio/estructuraBusqueda.jsp"/>">Estructura de la b&uacute;squeda</a></li>
    <li><a href="<c:url value="/jsp/empleo/herramientasDelSitio/busquedaLugar.jsp"/>">B&uacute;squeda por lugar</a></li>
    <li><a href="<c:url value="/jsp/empleo/herramientasDelSitio/frasesTextuales.jsp"/>">Frases textuales</a></li>
    <li><a href="<c:url value="/jsp/empleo/herramientasDelSitio/palabrasNoRelevantes.jsp"/>">Palabras no relevantes</a></li>
    <li><a href="<c:url value="/jsp/empleo/herramientasDelSitio/sinonimosTerminosSimilares.jsp"/>">Sin&oacute;nimos y t&eacute;rminos similares</a></li>
    <li><a href="<c:url value="/jsp/empleo/herramientasDelSitio/terminosPorRaizDePalabra.jsp"/>">T&eacute;rminos por ra&iacute;z de palabra</a></li>
    <li><a href="<c:url value="/jsp/empleo/herramientasDelSitio/terminosSimultaneos.jsp"/>">T&eacute;rminos simult&aacute;neos</a></li>
    <li><a href="<c:url value="/jsp/empleo/herramientasDelSitio/usoFrases.jsp"/>">Uso de frases</a></li>
    <li><a href="<c:url value="/jsp/empleo/herramientasDelSitio/usoNumerosCaracteresEspeciales.jsp"/>">Uso de n&uacute;meros y caracteres especiales</a></li>
</ul>
	          </div>
	        </div>
	        
	      </div>
	      <!-- /div contenido -->
	    </div>
	    
	    
	    
	    
	</jsp:body>
</t:publicTemplate>
