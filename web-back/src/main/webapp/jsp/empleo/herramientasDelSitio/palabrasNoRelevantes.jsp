<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURI}" />

<t:publicTemplate>
	<jsp:attribute name="titulo">Palabras no relevantes</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Herramientas del Sitio</jsp:attribute>  
    <jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
 	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		Para evitar confusiones y resultados equivocados, el buscador sem&aacute;ntico eliminar&aacute; de forma autom&aacute;tica las palabras que no determinen el significado de la b&uacute;squeda. Si lo deseas puedes utilizarlas pero no tendr&aacute;n efecto en los resultados obtenidos.
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
          	  <li class="active">Palabras no relevantes</li>
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
	            <h2 class="titulosh2">Palabras no relevantes</h2>
	          </div>
	          <div class="panel-body">
	          
	            <p>Para evitar confusiones y resultados equivocados, el buscador sem&aacute;ntico eliminar&aacute; de forma autom&aacute;tica las palabras que no determinen el significado de la b&uacute;squeda. Si lo deseas puedes utilizarlas pero no tendr&aacute;n efecto en los resultados obtenidos.</p>
<p>A continuaci&oacute;n se enlistan las palabras que ser&aacute;n omitidas por el buscador sem&aacute;ntico:</p>
<ul class="list-group-contenidos">
    <li>a, ante, bajo, con, de, desde, durante, en, entre, excepto, hacia, hasta, mediante, para, por, salvo, seg&uacute;n</li>
    <li>mi, mis</li>
    <li>tu, tus</li>
    <li>su, sus</li>
    <li>nuestro, nuestra, nuestros, nuestras</li>
    <li>vuestro, vuestra, vuestros, vuestras</li>
    <li>el, la, lo, al</li>
    <li>los, las</li>
    <li>un, una</li>
    <li>unos, unas</li>
    <li>y, o</li>
    <li>y/o</li>
    <li>del</li>
</ul>
	          </div>
	        </div>
	        
	      </div>
	      <!-- /div contenido -->
	    </div>
	    
	    
	    
	    
	</jsp:body>
</t:publicTemplate>
