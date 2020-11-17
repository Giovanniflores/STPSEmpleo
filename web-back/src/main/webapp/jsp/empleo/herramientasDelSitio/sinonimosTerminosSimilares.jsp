<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURI}" />

<t:publicTemplate>
	<jsp:attribute name="titulo">Sin&oacute;nimos y t&eacute;rminos similares</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Herramientas del Sitio</jsp:attribute>  
    <jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
 	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		Es importante mencionar que una b&uacute;squeda por carreras no siempre arrojar&aacute; los mismos resultados que una b&uacute;squeda por ocupaciones. Por ejemplo, es posible que la b&uacute;squeda &ldquo;Administraci&oacute;n&rdquo; no arroje los mismos resultados que la b&uacute;squeda &quot;Administrador&quot;.
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
          	  <li class="active">Sinónimos y términos similares</li>
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
	            <h2 class="titulosh2">Sinónimos y términos similares</h2>
	          </div>
	          <div class="panel-body">
	          
	            <p>Es importante mencionar que una b&uacute;squeda por carreras no siempre arrojar&aacute; los mismos resultados que una b&uacute;squeda por ocupaciones. Por ejemplo, es posible que la b&uacute;squeda &ldquo;Administraci&oacute;n&rdquo; no arroje los mismos resultados que la b&uacute;squeda &quot;Administrador&quot;.</p>
<p>Lo anterior, debido a que el buscador no considera como sin&oacute;nimos tu formaci&oacute;n acad&eacute;mica y tu perfil profesional. En otras palabras, es posible tener ofertas de empleo que requieran carreras que no guarden una relaci&oacute;n con las ocupaciones.</p>
	          </div>
	        </div>
	        
	      </div>
	      <!-- /div contenido -->
	    </div>
	    
	    
	    
	    
	</jsp:body>
</t:publicTemplate>
