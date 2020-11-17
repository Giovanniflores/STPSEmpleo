<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURI}" />

<t:publicTemplate>
	<jsp:attribute name="titulo">Uso de números y caracteres especiales</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Candidatos</jsp:attribute>  
    <jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
 	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		Podr&aacute;s utilizar caracteres especiales cuando est&eacute;n acompa&ntilde;ados por n&uacute;meros o abreviaciones, como en los siguientes casos:
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
          	  <li class="active">Uso de números y caracteres especiales</li>
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
	            <h2 class="titulosh2">Uso de números y caracteres especiales</h2>
	          </div>
	          <div class="panel-body">
	          
	            <p>Podr&aacute;s utilizar caracteres especiales cuando est&eacute;n acompa&ntilde;ados por n&uacute;meros o abreviaciones, como en los siguientes casos:</p>
<h3>B&uacute;squeda por idioma con porcentaje de dominio (Uso de caracteres num&eacute;ricos y &quot;%&quot;)</h3>
<ul class="list-group-contenidos">
    <li>Para buscar ofertas laborales que requieran el dominio de un idioma debes completar tu b&uacute;squeda escribiendo el idioma que manejas seguido del porcentaje de dominio que tienes sobre &eacute;l.<br />
    <strong>Por ejemplo:</strong> ingl&eacute;s 80%.</li>
</ul>
<h3>B&uacute;squeda por salario (Uso de caracteres num&eacute;ricos, &quot;$&quot; y &quot;,&quot;)</h3>
<ul class="list-group-contenidos">
    <li>Puedes utilizar el signo de pesos (&quot;$&quot;) y la coma (&quot;,&quot;) para escribir cifras de salario.<br />
    <strong>Por ejemplo:</strong> $10,000 pesos &oacute; 10000 pesos</li>
    <li>En el ejemplo anterior, el sistema buscar&aacute; las ofertas laborales cuyo salario ofrecido sea mayor o igual al que pretendes, discriminando los que no cumplan con tu expectativa econ&oacute;mica.</li>
</ul>
<h3>B&uacute;squeda por edad (Uso de caracteres num&eacute;ricos)</h3>
<ul class="list-group-contenidos">
    <li>Para realizar una b&uacute;squeda por edad es necesario colocar el n&uacute;mero de a&ntilde;os que tienes seguido por las palabras &quot;a&ntilde;os de edad&quot;:<br />
    <strong>Por ejemplo:</strong> 25 a&ntilde;os de edad</li>
    <li>El buscador detectar&aacute; las ofertas laborales cuyo rango de edad coincida con la edad establecida en tu b&uacute;squeda.</li>
</ul>
<h3>B&uacute;squeda con abreviaciones (Uso de &quot;.&quot;)</h3>
<ul class="list-group-contenidos">
    <li>Para simplificar algunas de las palabras que componen tu b&uacute;squeda puedes utilizar las abreviaciones o acr&oacute;nimos com&uacute;nmente utilizados para esos t&eacute;rminos.<br />
    <strong>Por ejemplo:</strong> Ing. civil, Admor.</li>
    <li>Recuerda colocar &quot;.&quot; al final de cada abreviaci&oacute;n.</li>
</ul>
	          </div>
	        </div>
	        
	      </div>
	      <!-- /div contenido -->
	    </div>
	    
	    
	    
	    
	</jsp:body>
</t:publicTemplate>
