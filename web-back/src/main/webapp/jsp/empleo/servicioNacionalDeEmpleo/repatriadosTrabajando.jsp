<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURI}" />


<t:publicTemplate>
	<jsp:attribute name="titulo">Repatriados trabajando</jsp:attribute>
    <jsp:attribute name="palabraDescripcion">Servicio Nacional de Empleo</jsp:attribute>  
    <jsp:attribute name="tituloSitio">Portal del Empleo</jsp:attribute>
 	<jsp:attribute name="urlRedSocial"><c:url value="${url}"/></jsp:attribute>
	<jsp:attribute name="descripcionRedSocial">
		Es el subprograma que apoya a Solicitantes de empleo seleccionados que hayan sido repatriados por alg&uacute;n estado de la frontera norte del pa&iacute;s o alguno de los aeropuertos que las autoridades migratorias se&ntilde;alen como puntos de repatriaci&oacute;n de connacionales, que manifiesten a la OSNE no tener intenciones de emigrar nuevamente al extranjero y su inter&eacute;s por encontrar un empleo en su lugar de origen o residencia, as&iacute; como no haber sido beneficiado por este subprograma.
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
          <li><a href="${context}/jsp/empleo/servicioNacionalDeEmpleo/mecanismoMovilidadLaboralInternaExterna.jsp">Mecanismo de Movilidad Laboral interna y externa</a></li>
          <li class="active">Repatriados trabajando</li>
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
			<h2 class="titulosh2">Repatriados trabajando</h2>
		
	        <p>Es el subprograma que apoya a Solicitantes de empleo seleccionados que hayan sido repatriados por alg&uacute;n estado de la frontera norte del pa&iacute;s o alguno de los aeropuertos que las autoridades migratorias se&ntilde;alen como puntos de repatriaci&oacute;n de connacionales, que manifiesten a la OSNE no tener intenciones de emigrar nuevamente al extranjero y su inter&eacute;s por encontrar un empleo en su lugar de origen o residencia, as&iacute; como no haber sido beneficiado por este subprograma.</p>
<h3>Requisitos:</h3>
<ul class="list-group-contenidos">
    <li>Ser buscador de empleo.</li>
    <li>Edad 16 a&ntilde;os o m&aacute;s.</li>
    <li>Ser connacional repatriado.</li>
    <li>Estar registrado en el SNE.</li>
    <li>Estar registrado en los listados de eventos de repatriaci&oacute;n.</li>
</ul>
<h3>Documentaci&oacute;n:</h3>
<ul class="list-group-contenidos">
    <li>CURP (En caso de que el Solicitante de empleo repatriado no exhiba este documento, el personal de la OSNE consultar&aacute; la direcci&oacute;n electr&oacute;nica http://consultas.curp.gob.mx/CurpSP/ para obtener la clave respectiva y realizar el registro en el SNE, conservando copia en el expediente).</li>
    <li>Constancia de repatriaci&oacute;n emitida por el INM.</li>
    <li>Documento que muestre la CLABE INTERBANCARIA de 18 posiciones (Opcional).</li>
</ul>
<p>Los documentos se deber&aacute;n presentar en original y copia legible, una vez cotejada la informaci&oacute;n, se devolver&aacute;n los originales.</p>
<p>Para mayor informaci&oacute;n, acude a la <a href="<c:url value="/jsp/empleo/servicioNacionalDeEmpleo/ubicacion.jsp"/>">Oficina</a>  del Servicio Nacional de Empleo m&aacute;s cercana a tu domicilio. Tambi&eacute;n,  puedes llamarnos al 01 800 8 41 2020, sin ning&uacute;n costo, ll&aacute;manos de 8 am a 10 pm los 365 d&iacute;as del a&ntilde;o, y uno de nuestros consejeros con gusto te  atender&aacute;.</p>
       	   
       	   </div>
	      </div>
	     </div>



    </div>
	</jsp:body>
</t:publicTemplate>
